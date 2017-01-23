package hospital.web.controller;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.Message;
import hospital.businessentity.Tool;
import hospital.businessentity.UsingPriceHistory;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.entity.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import logic.data.Tools;
import mondrian.rolap.BitKey.Big;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SessionScoped
@ManagedBean(name = "infoController")
public class InfoController {

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "LogicTreatment")
	ILogicTreatmentLocal logicTreatment;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal logicXray;

	@EJB(beanName = "ExaminationLogic")
	ILogicExaminationLocal examLogic;

	// Propertys begin
	// Lists
	private List<Soum> sums;
	private List<Aimag> aimags;
	private List<Degree> degrees;
	private List<Menu> menus;
	private List<String> subOrganizationTypes;
	private List<Organization> organizations;
	private List<Organization> organizationsByLogeedUser;
	private List<Employee> employees;
	private List<Users> users;
	private List<Role> roles;
	private List<SubOrganization> subOrganizations;
	private List<SubOrganizationType> subOrganizationTypee;
	private List<Medicine> medicines;
	private List<View_ConstantATC> atcs;
	private List<View_ConstantMedicineType> mTypes;
	private List<UsingPriceHistory> duph;
	private List<Measurement> measurements;

	// Cursor
	private Organization currentOrganization;
	private Degree currentDegree;
	private Aimag currentAimag;
	private Users currentUser;
	private Employee currentEmployee;
	private SubOrganization currentSubOrganization;
	private Role currentRole;
	private Medicine currentMedicine;

	// Filter
	private boolean updateSums = false;
	private Message message;
	private TreeNode treeRoles;
	private TreeNode[] selectedNodes;
	private int[] inspectionTimeInterval = { 15, 30, 45, 60, 75, 90, 105, 120,
			135, 150, 165, 180 };
	private String filterKey = "";
	private String filterKey1 = "";
	private BigDecimal filterPkId;
	private BigDecimal selectedPkId;
	private DegreePriceHistory cPriceHistory;
	private List<DegreePriceHistory> degreePriceHistorys;
	// Propertys end
	private Date beginDate;
	private Date endDate;
	private boolean filterCheck = false;

	// Diagnose Begin
	private List<Diagnose> listDiagnoses;
	private int selectedPageNumber;
	private int pageCount;
	private List<DiagnoseGroup> listDiagnoseGroups;
	private List<DiagnoseGroupDtl> diagnoseGroupDtlsTmp;
	private List<DiagnoseGroupDtl> diagnoseGroupDtls;
	private DiagnoseGroup currentDiagnoseGroup;
	private Diagnose currentDiagnose;

	// End Begin

	public InfoController() {

	}

	// Role Мод бүтэцийг үүсгэх функц
	public void createTreeRole() {
		Role role = new Role();
		role.setPkId(null);
		treeRoles = new DefaultTreeNode(role, null);
		List<Role> list = getRoles();
		for (Role rol : list) {
			if (rol.getParentPkId() == null) {
				TreeNode node = new DefaultTreeNode(rol, treeRoles);
				if (currentUser != null && currentUser.getRolePkIds() != null
						&& currentUser.getRolePkIds().size() > 0
						&& currentUser.getRolePkIds().contains(rol.getPkId())) {
					node.setSelected(true);
				}
				treeRoles(rol, node);
			}
		}
	}

	public void treeRoles(Role role, TreeNode root) {
		List<Role> list = getRoles();
		for (Role rol : list) {
			if (role.getPkId() != null && rol.getParentPkId() != null
					&& rol.getParentPkId().compareTo(role.getPkId()) == 0) {
				TreeNode node = new DefaultTreeNode(rol, root);
				treeRoles(rol, node);
			}
		}
	}

	public void refreshList() {
		if (degreePriceHistorys == null)
			degreePriceHistorys = new ArrayList<DegreePriceHistory>();
		try {
			degreePriceHistorys = logicTreatment.getPriceHistories(
					currentDegree.getPkId(),
					userSessionController.getLoggedInfo());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void saveCPriceHistory() {
		try {
			RequestContext context = RequestContext.getCurrentInstance();
			logicTreatment.savePriceHistory(cPriceHistory,
					userSessionController.getLoggedInfo(), selectedPkId);
			cPriceHistory = null;
			userSessionController.showMessage(99);
			context.update("form:degreeList");

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	// Шинэ Онош үүсгэх
	public void newDiagnose() {
		currentDiagnose = new Diagnose();
		currentDiagnose.setStatus(Tool.ADDED);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('register').show();");
	}

	// Шинэ Онош-ийн бүлэг үүсгэх
	public void newDiagnoseGroup() {
		currentDiagnoseGroup = new DiagnoseGroup();
		currentDiagnoseGroup.setStatus(Tool.ADDED);

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:register");
		context.execute("PF('register').show();");
	}

	// Шинэ Role үүсгэх
	public void newRole() {
		currentRole = new Role();
		currentRole.setStatus(Tool.ADDED);
	}

	// Шинэ байгууллага үүсгэх
	public void newOrganization() {
		currentOrganization = new Organization();
		currentOrganization.setStatus(Tool.ADDED);
	}

	//
	public String modifiedUser(Users user) {
		currentUser = user;
		currentUser.setHasPasswordChanged(false);
		List<BigDecimal> rolePkIds = new ArrayList<BigDecimal>();
		try {
			rolePkIds = infoLogic.getRolePkIds(currentUser.getPkId());
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		currentUser.setRolePkIds(rolePkIds);
		createTreeRole();
		currentUser.setStatus(Tool.MODIFIED);
		return "user_register";
	}

	public void listDiagnoseGroup() {
		try {
			listDiagnoseGroups = infoLogic.getListDiagnoseGroup();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void calcDiagnoseList() {
		DiagnoseGroupDtl diagnoseGroupDtl = diagnoseGroupDtls
				.get(diagnoseGroupDtls.size() - 1);
		if (diagnoseGroupDtl.getStatus().equals(Tool.LAST)
				&& diagnoseGroupDtl.getBeginNumber() != null
				&& diagnoseGroupDtl.getEndNumber() != null
				&& !diagnoseGroupDtl.getBeginNumber().equals("")
				&& !diagnoseGroupDtl.getEndNumber().equals("")) {

			diagnoseGroupDtl.setStatus(Tool.ADDED);
			diagnoseGroupDtlsTmp.add(diagnoseGroupDtl);

			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:diagnoseTypeList");
		}
	}

	public void removeDiagnoseGroupByIndex(int index) {
		if (diagnoseGroupDtlsTmp.size() > index) {
			diagnoseGroupDtlsTmp.remove(index);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:diagnoseTypeList");
		}
	}

	public void updateDiagnoseGroup(BigDecimal pkId) {
		currentDiagnoseGroup = new DiagnoseGroup();
		currentDiagnoseGroup.setPkId(pkId);
		currentDiagnoseGroup.setStatus(Tool.MODIFIED);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('confirmDialogDelete').show();");
	}

	public void deleteDiagnoseGroup(BigDecimal pkId) {
		currentDiagnoseGroup = new DiagnoseGroup();
		currentDiagnoseGroup.setPkId(pkId);
		currentDiagnoseGroup.setStatus(Tool.DELETE);

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('confirmDialogDelete').show();");
	}

	public String saveDiagnoseGroup() {
		if (!currentDiagnoseGroup.getStatus().equals(Tool.DELETE)) {
			if ("".equals(currentDiagnoseGroup.getName())) {
				getUserSessionController().showMessage(37);
				return "";
			}

			if (diagnoseGroupDtlsTmp.size() < 1) {
				getUserSessionController().showMessage(38);
				return "";
			}

			for (DiagnoseGroupDtl groupDtl : diagnoseGroupDtlsTmp) {
				System.out.println(groupDtl.getBeginNumber().compareTo(
						groupDtl.getEndNumber()));
				if (groupDtl.getBeginNumber()
						.compareTo(groupDtl.getEndNumber()) >= 0) {
					getUserSessionController().showMessage(39);
					return "";
				}
			}
		}

		try {
			infoLogic.saveDiagnoseGroup(currentDiagnoseGroup,
					diagnoseGroupDtlsTmp, getUserSessionController()
							.getLoggedInfo());
			diagnoseGroupDtlsTmp.clear();
			diagnoseGroupDtls.clear();
			currentDiagnoseGroup = null;
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		return "diagnosegroup_list";
	}

	public void deleteUser(Users user) {
		currentUser = user;
		currentUser.setStatus(Tool.DELETE);
	}

	public String deleteUser() {
		RequestContext context = RequestContext.getCurrentInstance();

		try {
			if (infoLogic.isRelated(currentUser.getPkId(), 3)) {
				infoLogic.saveUser(currentUser);
				users = infoLogic.getUsers(userSessionController
						.getLoggedInfo());
				userSessionController.showMessage(98);

			} else {
				context.execute("PF('confirmDialogDelete').hide();");
				userSessionController
						.showErrorMessage("Энэ хэрэглэгч ажилтны бүртгэлтэй байна");
				currentUser.setStatus(Tool.UNCHANGED);

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return "user_list";
	}

	// Оношлогоо
	public void diagnoses() {
		try {
			pageCount = infoLogic.getDiagnoseCount();
			listDiagnoses = infoLogic.getListDiagnose(getSelectedPageNumber(), getFilterKey());
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	// Байгууллага засах
	public String modifiedOrganization(Organization organization) {
		currentOrganization = organization;
		currentOrganization.setStatus(Tool.MODIFIED);
		return "company_register";
	}

	// Role засах
	public String modifiedRole(Role role) {
		currentRole = role;
		currentRole.setStatus(Tool.MODIFIED);
		try {
			currentRole.setMenuPkIds(infoLogic.getMenuPkIdByRole(currentRole
					.getPkId()));
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "role_register";
	}

	public void deleteOrganization(Organization organization) {
		System.out.println("organization name : " + organization.getName());
		currentOrganization = organization;
		currentOrganization.setStatus(Tool.DELETE);
	}

	public void deleteRole(Role role) {
		currentRole = role;
		currentRole.setStatus(Tool.DELETE);
	}

	public String saveRole() {
		String ret = "";
		try {
			infoLogic.saveRole(currentRole);
			ret = "role_list";
			roles = null;
			treeRoles = null;
			currentRole = null;
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return ret;
	}

	// Аймаг сонгосон үед дараагийн combo-д сумуудыг харуулна
	public void updateSums() {
		updateSums = true;
		for (Aimag item : aimags) {
			if (currentOrganization.getAimagPkId().compareTo(item.getPkId()) == 0)
				setCurrentAimag(item);
		}
	}

	// Email шалгах
	public boolean isEmail(final String hex) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Matcher matcher;
		Pattern pattern;
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(hex);
		return matcher.matches();
	}

	public void setUserRolePkIds() {
		currentUser.setRolePkIds(new ArrayList<BigDecimal>());
		setUserRolePkIds(treeRoles);
	}

	public void setUserRolePkIds(TreeNode node) {
		List<TreeNode> nodes = node.getChildren();
		for (TreeNode treeNode : nodes) {
			setUserRolePkIds(treeNode);
		}
		Role role = (Role) node.getData();
		if (role == null || !node.isSelected())
			return;
		List<BigDecimal> bigDecimals = currentUser.getRolePkIds();
		bigDecimals.add(role.getPkId());
		currentUser.setRolePkIds(bigDecimals);
	}

	// Хэрэглэгч хадгалах
	public String saveUser() {
		String ret = "";

		if (!Tool.DELETE.equals(currentUser.getStatus())) {
			setUserRolePkIds();

			if (currentUser.getName() == null
					|| currentUser.getName().isEmpty()) {
				showMessage(9);
				return ret;
			}

			if (currentUser.getId() == null || currentUser.getId().isEmpty()) {
				showMessage(10);
				return ret;
			}

			if (currentUser.getPassword() == null
					|| currentUser.getPassword().isEmpty()) {
				showMessage(11);
				return ret;
			}

			if (currentUser.getRolePkIds().size() < 1) {
				showMessage(14);
				return ret;
			}

			if (currentUser.getConfirmPassword() == null
					|| currentUser.getConfirmPassword().isEmpty()) {
				showMessage(12);
				return ret;
			}

			if (!currentUser.getPassword().equals(
					currentUser.getConfirmPassword())) {
				showMessage(13);
				return ret;
			}
		}
		try {
			infoLogic.saveUser(currentUser);
			treeRoles = null;
			organizationsByLogeedUser = null;
			organizations = null;
			currentUser = null;
			selectedNodes = null;
			users = null;
			ret = "user_list";
		} catch (Exception ex) {
			ret = null;
			if ("duplicate".equals(ex.getMessage())) {
				showMessage(8);
			}
		}

		return ret;
	}

	// Байгууллагын бүртгэл дээрээс хадгалах дархад ажиллана
	public String saveOrganization() {
		String ret = "";
		if (currentOrganization.getName() == null
				|| currentOrganization.getName().isEmpty()) {
			showMessage(2);
			return ret;
		}

		if (currentOrganization.getEmail() == null
				|| currentOrganization.getEmail().isEmpty()) {
			showMessage(3);
			return ret;
		}

		if (!isEmail(currentOrganization.getEmail())) {
			showMessage(7);
			return ret;
		}

		if (currentOrganization.getPhoneNumber() == null
				|| currentOrganization.getPhoneNumber().isEmpty()) {
			showMessage(4);
			return ret;
		}

		if (currentOrganization.getAddress() == null
				|| currentOrganization.getAddress().isEmpty()) {
			showMessage(5);
			return ret;
		}

		if (currentOrganization.getAddress() == null
				|| currentOrganization.getAddress().isEmpty()) {
			showMessage(6);
			return ret;
		}

		try {
			infoLogic.saveOrganization(currentOrganization);
			ret = "company_list";
			organizations = null;
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		return ret;
	}

	// Алдааны мессеж харуулна
	public void showMessage(int index) {
		getUserSessionController().showMessage(index);
	}

	public List<Organization> getOrganizations() {
		try {
			if(organizations == null)
			organizations = infoLogic.getOrganizations();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public Organization getCurrentOrganization() {
		if (currentOrganization == null) {
			newOrganization();
		}
		return currentOrganization;
	}

	public void setCurrentOrganization(Organization currentOrganization) {
		this.currentOrganization = currentOrganization;
	}

	public List<Aimag> getAimags() {
		try {
			if (aimags == null) {
				aimags = infoLogic.getAimags();
				if (aimags.size() > 0) {
					currentAimag = aimags.get(0);
				}
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
			aimags = new ArrayList<Aimag>();
		}
		return aimags;
	}

	public void setAimags(List<Aimag> aimags) {
		this.aimags = aimags;
	}

	public List<Soum> getSums() {
		if (sums == null || sums.size() < 1) {
			try {
				if (currentAimag != null
						&& currentAimag.getPkId() != null
						&& BigDecimal.ZERO.compareTo(currentAimag.getPkId()) != 0)
					sums = infoLogic.getSoums(currentAimag.getPkId());
				else
					sums = new ArrayList<Soum>();
			} catch (Exception ex) {
				sums = new ArrayList<Soum>();
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return sums;
	}

	public void setSums(List<Soum> sums) {
		this.sums = sums;
	}

	public Aimag getCurrentAimag() {
		return currentAimag;
	}

	public void setCurrentAimag(Aimag currentAimag) {
		if (sums == null || sums.size() < 1 || updateSums) {
			try {
				sums = infoLogic.getSoums(currentAimag.getPkId());
				updateSums = false;
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		this.currentAimag = currentAimag;
	}

	public boolean isUpdateSums() {
		return updateSums;
	}

	public void setUpdateSums(boolean updateSums) {
		this.updateSums = updateSums;
	}

	public Message getMessage() {
		if (message == null)
			message = new Message();
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	// Шинэ Зэрэг үүсгэх
	public void newDegree() {
		currentDegree = new Degree();
	}

	public List<Degree> getDegrees() {
		try {
			degrees = infoLogic.getDegrees();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}

	public Degree getCurrentDegree() {
		return currentDegree;
	}

	public void setCurrentDegree(Degree currentDegree) {
		this.currentDegree = currentDegree;
	}

	// Шинэ Кабинет үүсгэх
	public void newSubOrganization() {
		currentSubOrganization = new SubOrganization();
		currentSubOrganization.setStatus(Tool.ADDED);
	}

	// Кабинет засах
	public String modifiedSubOrganization(SubOrganization suborganization) {
		currentSubOrganization = suborganization;
		currentSubOrganization.setStatus(Tool.MODIFIED);
		return "suborganization_register";
	}

	public void deleteSubOrganization(SubOrganization subOrganization) {
		System.out.println("organization name : " + subOrganization.getName());
		currentSubOrganization = subOrganization;
		currentSubOrganization.setStatus(Tool.DELETE);
	}

	//
	public String deleteSubOrganization() {
		try {
			if (infoLogic.isRelated(currentSubOrganization.getPkId(), 1)) {
				infoLogic.saveSubOrganization(currentSubOrganization,
						userSessionController.getLoggedInfo());
				subOrganizations = infoLogic.getSubOrganizations();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update(":form:subOrganizationList");

			} else {
				userSessionController
						.showErrorMessage("Энэ кабинет ажилтны бүртгэлтэй байна");
				currentSubOrganization.setStatus(Tool.UNCHANGED);

			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		return "suborganization_list";
	}

	public List<SubOrganization> getSubOrganizations() {
		if (subOrganizations == null) {
			try {
				subOrganizations = infoLogic.getSubOrganizations();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return subOrganizations;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public SubOrganization getCurrentSubOrganization() {
		return currentSubOrganization;
	}

	public void setSubCurrentOrganization(SubOrganization currentSubOrganization) {
		this.currentSubOrganization = currentSubOrganization;
	}

	public List<String> getSubOrganizationTypes() {
		return subOrganizationTypes;
	}

	public void setSubOrganizationTypes(List<String> subOrganizationTypes) {
		this.subOrganizationTypes = subOrganizationTypes;
	}

	public String saveSubOrganization() {
		String ret = "";
		if (currentSubOrganization.getName() == null
				|| currentSubOrganization.getName().isEmpty()) {
			showMessage(78);
			return ret;
		}

		if (currentSubOrganization.getRoomNumber() == null
				|| currentSubOrganization.getRoomNumber().isEmpty()) {
			showMessage(79);
			return ret;
		}

		try {
			infoLogic.saveSubOrganization(currentSubOrganization,
					userSessionController.getLoggedInfo());
			ret = "suborganization_list";
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		try {
			subOrganizations = infoLogic.getSubOrganizations();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		return ret;
	}

	// Шинэ ажилтан үүсгэх
	public void newEmployee() {
		try {
			currentEmployee = new Employee();
			currentEmployee.setId(infoLogic.getEmployeeGeneratedId());
			currentEmployee.setStatus(Tool.ADDED);
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	// Ажилтан засах
	public String modifiedEmployee(Employee employee) {
		currentEmployee = employee;
		currentEmployee.setStatus(Tool.MODIFIED);
		return "employee_register";
	}

	// Ажилтан Устгах
	public void deleteEmployee(Employee employee) {
		currentEmployee = employee;
		currentEmployee.setStatus(Tool.DELETE);

	}

	public String deleteEmployee() {
		try {
			if (infoLogic.isRelated(currentEmployee.getPkId(), 2)) {
				infoLogic.saveEmployee(currentEmployee);
				employees = infoLogic.getEmployees();
			} else {
				userSessionController
						.showErrorMessage("Тухайн ажилтан эмчилгээ хийсэн тул устгах боломжгүй!");
				currentEmployee.setStatus(Tool.UNCHANGED);
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		return "employee_list";
	}

	public List<Employee> getEmployees() {
		if (employees == null) {
			try {
				employees = infoLogic.getEmployees();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Employee getCurrentEmployee() {
		if (currentEmployee == null)
			newEmployee();
		return currentEmployee;

	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	public String saveEmployee() {
		String ret = "";
		if (currentEmployee.isInspect() && (currentEmployee.getInspectionPassword() == null || currentEmployee.getInspectionPassword().isEmpty())) {
			showMessage(93);
			return ret;
		}
		if (currentEmployee.isInspect() && !currentEmployee.getInspectionPassword().equals(currentEmployee.getInspectionPassword1())) {
			showMessage(94);
			return ret;
		}
		
		if (currentEmployee.getSubOrganizationPkId() == null) {
			showMessage(70);
			return ret;
		}

		if (currentEmployee.getId() == null
				|| currentEmployee.getId().isEmpty()) {
			showMessage(71);
			return ret;
		}

		if (currentEmployee.getLastName() == null
				|| currentEmployee.getLastName().isEmpty()) {
			showMessage(72);
			return ret;
		}

		if (currentEmployee.getFirstName() == null
				|| currentEmployee.getFirstName().isEmpty()) {
			showMessage(73);
			return ret;
		}

		if (currentEmployee.getPhone() == null
				|| currentEmployee.getPhone().isEmpty()) {
			showMessage(74);
			return ret;
		}

		if (currentEmployee.getDegreePkId() == null) {
			showMessage(75);
			return ret;
		}

		try {
			// currentEmployee.setId(infoLogic.getEmployeeGeneratedId());
			infoLogic.saveEmployee(currentEmployee);
			employees = infoLogic.getEmployees();
			ret = "employee_list";
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return ret;
	}

	public Users getCurrentUser() {
		if (currentUser == null)
			newUser();
		return currentUser;
	}

	public void setCurrentUser(Users currentUser) {
		this.currentUser = currentUser;
	}

	public List<Menu> getMenus() {
		if (menus == null || menus.size() < 1) {

			try {
				menus = infoLogic.getMenus();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}

		}
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public TreeNode getTreeRoles() {
		if (treeRoles == null)
			createTreeRole();
		return treeRoles;
	}

	public void setTreeRoles(TreeNode treeRoles) {
		this.treeRoles = treeRoles;
	}

	public void setOrganizationsByLogeedUser(
			List<Organization> organizationsByLogeedUser) {
		this.organizationsByLogeedUser = organizationsByLogeedUser;
	}

	public List<Organization> getOrganizationsByLogeedUser() {
		if (organizationsByLogeedUser == null) {
			organizationsByLogeedUser = new ArrayList<Organization>();
			if (getUserSessionController().getLoggedInfo() == null
					|| getUserSessionController().getLoggedInfo()
							.getOrganization() == null) {
				organizationsByLogeedUser.addAll(getOrganizations());
			} else {
				organizationsByLogeedUser.add(getUserSessionController()
						.getLoggedInfo().getOrganization());
			}
		}

		return organizationsByLogeedUser;
	}

	// Шинэ Хэрэглэгч үүсгэх
	public void newUser() {
		currentUser = new Users();
		currentUser.setStatus(Tool.ADDED);
	}

	public Role getCurrentRole() {
		if (currentRole == null) {
			newRole();
		}
		return currentRole;
	}

	public void setRoles(List<Role> roles) {
		if (roles == null || roles.size() < 1) {
			try {
				roles = infoLogic.getRoles();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		this.roles = roles;
	}

	public List<Role> getRoles() {
		if (roles == null || roles.size() < 1) {
			try {
				roles = infoLogic.getRoles();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return roles;
	}

	public void setCurrentRole(Role currentRole) {
		this.currentRole = currentRole;
	}

	public List<Users> getUsers() {
		if (users == null) {
			try {
				users = infoLogic.getUsers(getUserSessionController()
						.getLoggedInfo());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public void filterEmployeeBySubOrganization(String son) {
		try {
			employees = infoLogic.getEmployees();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		ArrayList<Employee> filtered = new ArrayList<Employee>();
		if ("all".equals(son) || son == null || "".equals(son)) {

		}

		else {
			for (Employee emp : employees) {
				if (emp.getSubOrganizationName().equals(son))
					filtered.add(emp);
			}
			employees = filtered;
		}
	}

	public void filterEmployeeByDegree(String dn) {
		try {
			employees = infoLogic.getEmployees();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		ArrayList<Employee> filtered = new ArrayList<Employee>();
		if ("all".equals(dn) || dn == null || "".equals(dn)) {

		}

		else {
			for (Employee emp : employees) {
				if (emp.getDegreeName().equals(dn))
					filtered.add(emp);
			}
			employees = filtered;
		}
	}

	public String getFilterKey() {
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public String getFilterKey1() {
		return filterKey1;
	}

	public void setFilterKey1(String filterKey1) {
		this.filterKey1 = filterKey1;
	}

	public List<SubOrganizationType> getSubOrganizationTypee() {
		if (subOrganizationTypee == null) {
			try {
				subOrganizationTypee = infoLogic.getSubOrganizationTypes();
			} catch (Exception ex) {
			}
		}
		return subOrganizationTypee;
	}

	public void setSubOrganizationTypee(
			List<SubOrganizationType> subOrganizationTypee) {
		this.subOrganizationTypee = subOrganizationTypee;
	}

	public void filterSubOrga(String key) {
		try {
			subOrganizations = infoLogic.getSubOrganizations();
		} catch (Exception ex) {
		}

		ArrayList<SubOrganization> filtered = new ArrayList<SubOrganization>();
		if ("all".equals(key) || key == null || "".equals(key)) {

		}

		else {
			for (SubOrganization emp : subOrganizations) {
				if (emp.getType().equals(key))
					filtered.add(emp);
			}
			subOrganizations = filtered;
		}
	}

	public int[] getInspectionTimeInterval() {
		return inspectionTimeInterval;
	}

	public void setInspectionTimeInterval(int[] inspectionTimeInterval) {
		this.inspectionTimeInterval = inspectionTimeInterval;
	}

	public List<CustomerRequest> getEmployeeRequestList() {
		List<CustomerRequest> employeeRequests = new ArrayList<CustomerRequest>();

		try {
			employeeRequests = infoLogic.getEmployeeRequests(
					getUserSessionController().getLoggedInfo(), getBeginDate(),
					getEndDate());
		} catch (Exception ex) {

		}

		return employeeRequests;
	}

	@SuppressWarnings("deprecation")
	public Date getBeginDate() {
		if (beginDate == null) {
			beginDate = new Date();
			beginDate.setHours(0);
			beginDate.setMinutes(0);
			beginDate.setSeconds(0);
		}
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@SuppressWarnings("deprecation")
	public Date getEndDate() {
		if (endDate == null) {
			endDate = new Date();
			endDate.setHours(23);
			endDate.setMinutes(59);
			endDate.setSeconds(59);
		}
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void filterEmployeeByInspect() {
		if (filterCheck == true)
			filterKey = "Тийм";
		else
			filterKey = "all";

		try {
			employees = infoLogic.getEmployees();
		} catch (Exception ex) {
		}

		ArrayList<Employee> filtered = new ArrayList<Employee>();
		if ("all".equals(filterKey) || filterKey == null
				|| "".equals(filterKey)) {

		}

		else {
			for (Employee emp : employees) {
				if (emp.getInspectStatus().equals(filterKey))
					filtered.add(emp);
			}
			employees = filtered;
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update(":form:employeeList");
	}

	public boolean isFilterCheck() {
		return filterCheck;
	}

	public void setFilterCheck(boolean filterCheck) {
		this.filterCheck = filterCheck;
	}

	public void setStmpImageDefault() {
		currentOrganization.setStmp(Tool.UserDefaultImage);
	}

	public void setLogoImageDefault() {
		currentOrganization.setLogo(Tool.UserDefaultImage);
	}

	public List<Medicine> getMedicines() {
		if (medicines == null)
			medicines = new ArrayList<Medicine>();
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public void setCurrentMedicine(Medicine currentMedicine) {
		this.currentMedicine = currentMedicine;
	}

	public Medicine getCurrentMedicine() {
		if (currentMedicine == null)
			currentMedicine = new Medicine();
		return currentMedicine;
	}

	public void newMedicine() {
		currentMedicine = new Medicine();
		currentMedicine.setMaxAge(150);
		currentMedicine.setStatus(Tool.ADDED);
	}

	public String saveMedicine() {
		String ret = "";
		try {
			if (currentMedicine.getName().isEmpty()
					|| currentMedicine.getName() == null
					|| "".equals(currentMedicine.getName())
					|| " ".equals(currentMedicine.getName())) {
				userSessionController.showMessage(40);
				return ret;
			} else if (currentMedicine.getiName().isEmpty()
					|| currentMedicine.getiName() == null
					|| "".equals(currentMedicine.getiName())
					|| " ".equals(currentMedicine.getiName())) {
				userSessionController.showMessage(41);
				return ret;
			} else if (currentMedicine.getId().isEmpty()
					|| currentMedicine.getId() == null
					|| "".equals(currentMedicine.getId())
					|| " ".equals(currentMedicine.getId())) {
				userSessionController.showMessage(42);
				return ret;
			}
			if (currentMedicine.isActiveAllAge()) {
				currentMedicine.setMinAge(0);
				currentMedicine.setMaxAge(150);

			} else if (currentMedicine.getMinAge() > currentMedicine
					.getMaxAge()) {
				int temp = currentMedicine.getMinAge();
				currentMedicine.setMinAge(currentMedicine.getMaxAge());
				currentMedicine.setMaxAge(temp);

			}

			infoLogic.saveMedicine(currentMedicine,
					userSessionController.getLoggedInfo());
			ret = "medicine_list";
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return ret;
	}

	public String modifiedMedicine(Medicine medicine) {
		medicine.setStatus(Tool.MODIFIED);
		setCurrentMedicine(medicine);
		return "medicine_register";
	}

	public void deleteMedicine(Medicine medicine) {

		try {
			medicine.setStatus(Tool.DELETE);
			infoLogic.saveMedicine(medicine,
					userSessionController.getLoggedInfo());
			userSessionController.showMessage(98);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void setCurrentSubOrganization(SubOrganization currentSubOrganization) {
		this.currentSubOrganization = currentSubOrganization;
	}

	public void setAtcs(List<View_ConstantATC> atcs) {
		this.atcs = atcs;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public void setmTypes(List<View_ConstantMedicineType> mTypes) {
		this.mTypes = mTypes;
	}

	public List<View_ConstantATC> getAtcs() {
		if (atcs == null)
			try {
				atcs = infoLogic.getAtcs();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		return atcs;
	}

	public List<View_ConstantMedicineType> getmTypes() {
		if (mTypes == null)
			try {
				mTypes = infoLogic.getMedicineTypes();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		return mTypes;
	}

	public List<Measurement> getMeasurements() {
		if (measurements == null)
			try {
				measurements = infoLogic.getMeasurements();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		return measurements;
	}

	public void refreshMedicineList() {
		try {
			setMedicines(infoLogic.getMedicine(getFilterPkId(),
					userSessionController.getLoggedInfo(), getFilterKey()));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public BigDecimal getFilterPkId() {
		if (filterPkId == null)
			filterPkId = new BigDecimal(0);
		return filterPkId;
	}

	public void setFilterPkId(BigDecimal filterPkId) {
		this.filterPkId = filterPkId;
	}

	public List<Diagnose> getListDiagnoses() {
		if (listDiagnoses == null)
			listDiagnoses = new ArrayList<Diagnose>();
		return listDiagnoses;
	}

	public void setListDiagnoses(List<Diagnose> listDiagnoses) {
		this.listDiagnoses = listDiagnoses;
	}

	public int getSelectedPageNumber() {
		if (selectedPageNumber < 1)
			selectedPageNumber = 1;
		if (selectedPageNumber > pageCount)
			selectedPageNumber = pageCount;
		return selectedPageNumber;
	}

	public void setSelectedPageNumber(int selectedPageNumber) {
		this.selectedPageNumber = selectedPageNumber;
	}

	public int getPageCount() {
		if (pageCount == 0)
			pageCount = 1;
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<DiagnoseGroup> getListDiagnoseGroups() {
		if (listDiagnoseGroups == null)
			listDiagnoseGroups = new ArrayList<DiagnoseGroup>();
		return listDiagnoseGroups;
	}

	public void setListDiagnoseGroups(List<DiagnoseGroup> listDiagnoseGroups) {
		this.listDiagnoseGroups = listDiagnoseGroups;
	}

	public List<DiagnoseGroupDtl> getDiagnoseGroupDtls() {
		if (diagnoseGroupDtls == null)
			diagnoseGroupDtls = new ArrayList<DiagnoseGroupDtl>();
		diagnoseGroupDtls.clear();
		for (DiagnoseGroupDtl dtl : getDiagnoseGroupDtlsTmp()) {
			diagnoseGroupDtls.add(dtl);
		}
		DiagnoseGroupDtl diagnoseGroupDtl = new DiagnoseGroupDtl();
		diagnoseGroupDtl.setStatus(Tool.LAST);
		diagnoseGroupDtls.add(diagnoseGroupDtl);
		return diagnoseGroupDtls;
	}

	public void setDiagnoseGroupDtls(List<DiagnoseGroupDtl> diagnoseGroupDtls) {
		this.diagnoseGroupDtls = diagnoseGroupDtls;
	}

	public List<DiagnoseGroupDtl> getDiagnoseGroupDtlsTmp() {
		if (diagnoseGroupDtlsTmp == null)
			diagnoseGroupDtlsTmp = new ArrayList<DiagnoseGroupDtl>();
		return diagnoseGroupDtlsTmp;
	}

	public void setDiagnoseGroupDtlsTmp(
			List<DiagnoseGroupDtl> diagnoseGroupDtlsTmp) {
		this.diagnoseGroupDtlsTmp = diagnoseGroupDtlsTmp;
	}

	public DiagnoseGroup getCurrentDiagnoseGroup() {
		if (currentDiagnoseGroup == null) {
			currentDiagnoseGroup = new DiagnoseGroup();
			currentDiagnoseGroup.setStatus(Tool.ADDED);
		}
		return currentDiagnoseGroup;
	}

	public void setCurrentDiagnoseGroup(DiagnoseGroup currentDiagnoseGroup) {
		this.currentDiagnoseGroup = currentDiagnoseGroup;
	}

	public Diagnose getCurrentDiagnose() {
		if (currentDiagnose == null) {
			currentDiagnose = new Diagnose();
			currentDiagnose.setStatus(Tool.ADDED);
		}
		return currentDiagnose;
	}

	public void setCurrentDiagnose(Diagnose currentDiagnose) {
		this.currentDiagnose = currentDiagnose;
	}

	public BigDecimal getSelectedPkId() {
		if (selectedPkId == null)
			selectedPkId = BigDecimal.ZERO;
		return selectedPkId;
	}

	public void setSelectedPkId(BigDecimal selectedPkId) {
		RequestContext context = RequestContext.getCurrentInstance();
		this.selectedPkId = selectedPkId;
		context.update("form:degreeListss");
	}

	public DegreePriceHistory getcPriceHistory() {
		if (cPriceHistory == null)
			cPriceHistory = new DegreePriceHistory();
		return cPriceHistory;
	}

	public void setcPriceHistory(DegreePriceHistory cPriceHistory) {
		this.cPriceHistory = cPriceHistory;
	}

	public List<DegreePriceHistory> getDegreePriceHistorys() {
		if (degreePriceHistorys == null)
			degreePriceHistorys = new ArrayList<DegreePriceHistory>();
		try {
			degreePriceHistorys = logicTreatment.getPriceHistories(
					selectedPkId, userSessionController.getLoggedInfo());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return degreePriceHistorys;
	}

	public void setDegreePriceHistorys(
			List<DegreePriceHistory> degreePriceHistorys) {
		this.degreePriceHistorys = degreePriceHistorys;
	}

	public List<UsingPriceHistory> getDuph() {
		duph = new ArrayList<UsingPriceHistory>();
		try {
			for (Degree d : infoLogic.getDegrees()) {
				UsingPriceHistory uph = new UsingPriceHistory();
				uph.setPkId(d.getPkId());
				List<DegreePriceHistory> ph = logicTreatment.getPriceHistories(
						uph.getPkId(), userSessionController.getLoggedInfo());
				uph.setPriceUsageDate(ph.get(0).getPriceUsageDate());
				uph.setName(ph.get(0).getDegreeName());
				uph.setPrice(ph.get(0).getPrice());
				uph.setRePrice(ph.get(0).getRePrice());
				uph.setCount(ph.size());
				duph.add(uph);
			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		return duph;
	}

	public void setDuph(List<UsingPriceHistory> duph) {
		this.duph = duph;
	}
	
	public void uploadStamp(FileUploadEvent event) {
		try {
			String  fileStamp  =  Tool.CToL(getFileName(event.getFile().getFileName()));
			if (copyFile(getApplicationController().getImagePath() +fileStamp ,event.getFile().getInputstream())) {
				getCurrentEmployee().setStamp(fileStamp);
			}
			System.out.println("uploadStamp");
		}catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}
	
	public void uploadSignature(FileUploadEvent event) {
		try {
			String  fileNewSignature  =  Tool.CToL(getFileName(event.getFile().getFileName()));
		if (copyFile(getApplicationController().getImagePath() + fileNewSignature, event.getFile().getInputstream())) {
			getCurrentEmployee().setSignature(fileNewSignature);
			}
		}catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}
	
	private boolean copyFile(String fileName, InputStream in) {
		try {
			// write the inputStream to a FileOutputStream
			OutputStream out;
			out = new FileOutputStream(new File(fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			in.close();
			out.flush();
			out.close();
			return true;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			return false;
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			return false;
		}
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
	
	private String getFileName(String name) {
		return Tools.newPkId() + "." + FilenameUtils.getExtension(name);
	}

}

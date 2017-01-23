package hospital.web.controller;

import java.awt.Color;
import java.awt.Label;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicHomeLocal;
import hospital.businesslogic.interfaces.IDashboardLogic;
import hospital.entity.ConditionalPrescription;
import hospital.entity.Customer;
import hospital.entity.Diagnose;
import hospital.entity.DiagnoseGroup;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequest;
import hospital.entity.Inspection;
import hospital.entity.Medicine;
import hospital.entity.Organization;
import hospital.entity.Post;
import hospital.entity.PostMap;
import hospital.entity.Role;
import hospital.entity.SubOrganization;
import hospital.entity.Surgery;
import hospital.entity.Users;
import hospital.entity.Xray;
import hospital.entity.LoginDialog;

@SessionScoped
@ManagedBean(name = "homeController")
public class HomeController {
	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	@ManagedProperty(value = "#{inspectionController}")
	private InspectionController inspectionController;
	@EJB(beanName = "LogicHome")
	ILogicHomeLocal  loHomeLocal;
	List<Organization> organizations;
	List<Role>  rolelist;
	List<SubOrganization> subOrganizations;
	List<SubOrganization> listsubOrganizations;
	List<Users>  users;
	List<Employee>  employees;
	List<Customer>  customers;
	List<Diagnose>  diagnoses;
	List<Medicine>  medicines;
	List<DiagnoseGroup>  diagnoseGroups;
	List<Xray>  xrays;
	List<EmployeeRequest> employeeRequests;
	List<Examination>  examinations;
	List<ExaminationRequest> examinationRequests;
	List<ConditionalPrescription> conditionalPrescriptions;
	List<Surgery>  surgeries;
	List<Inspection>  inspections;
	List<String>  liststring;
	List<String>  selectedSubOrgaValue;
	List<LoginDialog> loginDialogs;
	
	private  List<Employee> employeelist;
	private List<String> selectedEmployee;
	private  Post  post;
	private Employee  employee;
	private  String selecttext;
	private  List<Post>  listpost;
	private List<Post>  listreceiverPkId;
	private  List<Post>  listpostgroup;
	private  List<Post>  listemployee;
	private  List<Post> listpostEmployeePkId;
	private Post  posts;
	private Date selectDate;
	private List<Post>  lispostPkId;
	private List<Post> listPostMapPkId;
	private List<String>  listbgColor;
	@PostConstruct
	public void init(){
		postView();
		setSelectName();
	}
	public  void  setSelectName(){
		try {
			listsubOrganizations  =  loHomeLocal.getSelectionSubOrganization();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public  void  action(){
		List<BigDecimal> bigDecimal  =  new ArrayList<BigDecimal>();
		for (int i = 0; i < getSelectedSubOrgaValue().size(); i++) {
			bigDecimal.add(new BigDecimal(getSelectedSubOrgaValue().get(i)));
		}
		System.out.println("DD" + bigDecimal.size());
		try {
			if(bigDecimal.size() < 1){
				employeelist  =  new ArrayList<>();
			}else {
				employeelist  =  loHomeLocal.getSelectionEmployee(bigDecimal);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public  String getName(String name ){
		String ret = "";
		try {

			switch (name) {
			case "Байгууллага":
				organizations  =  loHomeLocal.getOrganizations();
				ret= " " + organizations.size() +"";
				break;
			case "Хандах эрх":
				rolelist   = loHomeLocal.getAccessLaw();
				ret= " " + rolelist.size()+ "";
				break;
			case "Хэрэглэгч":
				users   =  loHomeLocal.getUsers();
				ret  = " " + users.size() + " ";
				break;
			case "Эдийн засгийн хуанли":
				break;
			case "Кабинет":
				subOrganizations  =  loHomeLocal.getRoom();
				ret = " " + subOrganizations.size() +" ";
				break;
			case "Ажилтан":
				employees   =  loHomeLocal.getEmployee();
				ret = " " + employees.size() +" ";
				break;
			case "Эмчилгээ":
				break;
			case "Оношилгоо":
				xrays  =  loHomeLocal.getDiagnose();
				ret = " " + xrays.size() +" ";
				break;
			case "Үйлчлүүлэгч ":
				customers   =  loHomeLocal.getCustomer();
				ret = " " + customers.size() +" ";
				break;
			case "Захиалга":
				break;
			case "Кассын жагсаалт":
				employeeRequests  =  loHomeLocal.getCahsierList();
				ret = " " + employeeRequests.size() +" ";
				break;
			case "Оношилгооны жагсаалт":
				inspections  =  loHomeLocal.getInspection();
				ret = " " + inspections.size() +" ";
				break;
			case "Тайлан":
				break;
			case "Эм":
				medicines  =  loHomeLocal.getMedicine();
				ret = " " + medicines.size() +" ";
				break;
			case "Шинжилгээний жагсаалт":
				examinations  =  loHomeLocal.getSurveyList();
				ret = " " + examinations.size() +"";
				break;
			case "Оношийн бүлэг":
				diagnoseGroups =  loHomeLocal.getDiagnoseGroup();
				ret = " "+ diagnoseGroups.size() +" ";
				break;
			case "Онош":
				diagnoses  = loHomeLocal.getDiagnosist();
				ret = " " + diagnoses.size() ;
				break;
			case "Шинжигээний багц":
				examinations  = loHomeLocal.getSurveyPackage();
				ret = "" + examinations.size() +"";
				break;
			case "Болзолт жор":
				conditionalPrescriptions  = loHomeLocal.getConditionalPrescriptions();
				ret = " " + conditionalPrescriptions.size() +"";
				break;
			case "Шинжилгээний хүсэлт":
				examinationRequests  =  loHomeLocal.getExaminationRequestList();
				ret = " " + examinationRequests.size() +"";
				break;
			case "Шинжилгээний хариу":
				examinationRequests  =  loHomeLocal.getExaminationRequestResult();
				ret = " " + examinationRequests.size() +"";
				break;
			case "Хагалгаа":
				surgeries  = loHomeLocal.getSurvey();
				ret = " " + surgeries.size() +" ";
				break;
			case "Хийгдэх хагалгааны жагсаалт":
				break;
			case "Хийгдэх эмчилгээний жагсаалт":
				break;
			case "Төлөвлөлтийн цагалбар":
				break;
			case "Удирдлага хяналт":
				break;
			case "Эмчийн үзлэг ":
				inspections  =  loHomeLocal.getInspection();
				ret = " " + inspections.size() +" ";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ret;
	}
	public  String  getActive(String st){
		String  str="";
		try {
			switch (st) {
			case "Оношилгоо":
				xrays =  loHomeLocal.getisDiagnose();
				str = "" + xrays.size() +"";
				break;
			case "Хагалгаа":
				surgeries  =  loHomeLocal.getisActiveSurvey();
				str  = "" + surgeries.size() + " ";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return str;
	}
	public  void savePost(){
		try {
			List<BigDecimal>  bigDecimals  =  new ArrayList<>();
			for (int i = 0; i < getSelectedEmployee().size(); i++) {
				bigDecimals.add( new BigDecimal(getSelectedEmployee().get(i)));
			}		
			if (getSelectedEmployee()==null || getSelectedEmployee().isEmpty()) {
				userSessionController.showErrorMessage("Кабинет сонгоогүй байна");
				return;
			}
			else if(getSelectedSubOrgaValue()==null  || getSelectedSubOrgaValue().isEmpty()){
				userSessionController.showErrorMessage("Ажилтан  сонгоогүй байна");
				return;
			}
			else if (getSelecttext()==null || getSelecttext().isEmpty()) {
				userSessionController.showErrorMessage("Пост оруулаагүй байна");
				return;
			}
			RequestContext  context  =  RequestContext.getCurrentInstance();
			Date  d   =  new Date();
			loHomeLocal.setPostSave(getPost(), getSelecttext(),userSessionController.getLoggedInfo().getEmployeePkId(),bigDecimals,d);
			setPost(null);
			context.update(":form:panels");
			listpost  =  loHomeLocal.getPostView(userSessionController.getLoggedInfo().getEmployee().getPkId());
			getUserSessionController().setShowMessage("Амжилттай хадгаллаа");


		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void showMessage(int index) {
		getUserSessionController().showMessage(index);
	}
	public  void  postView(){
		try {
			loHomeLocal.getPostPostPkIdView(userSessionController.getLoggedInfo().getEmployee().getPkId());
			listpost  =  loHomeLocal.getPostView(userSessionController.getLoggedInfo().getEmployee().getPkId());
			System.out.println( "reciever " + listreceiverPkId.size());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public  List<Post> getpostViewList(BigDecimal pkId){
		List<Post>  p  = new ArrayList<>();
		try {
			p   =  loHomeLocal.getPostMapReceiverPkId(pkId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return p;
	}
	public  void  deletePost(Post  p ){
		try {
			p.setStatus(Tool.DELETE);
			loHomeLocal.setPostDeleteModify(p);
			listpost  =  loHomeLocal.getPostView(userSessionController.getLoggedInfo().getEmployee().getPkId());

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void  editPost(Post  p){
		if (userSessionController.getLoggedInfo().getEmployeePkId().equals(p.getSenderEmployeePkId())) {
			try {
				BigDecimal  postMapPkid = null;
				lispostPkId  = loHomeLocal.getPostPostPkId(p.getPostpkid());
				for (int i = 0; i < lispostPkId.size(); i++) {
					postMapPkid= lispostPkId.get(i).getPostMaPkId();
				}
				listpost  =  loHomeLocal.getPostView(userSessionController.getLoggedInfo().getEmployee().getPkId());
				listPostMapPkId  =  loHomeLocal.getPostPostUpdate(postMapPkid);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		else 
		{
			userSessionController.showErrorMessage("Засварлах  боломжгүй");
		}
	}
	public void  update(Post pos){
		pos.setStatus(Tool.MODIFIED);
		try {
			loHomeLocal.setPostDeleteModify(pos);
			listpost  =  loHomeLocal.getPostView(userSessionController.getLoggedInfo().getEmployee().getPkId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void panelupdate(){
		getSelectedEmployee().clear();
		getSelectedSubOrgaValue().clear();
		setSelecttext(null);
	}
	public String  bgColorString() {
		String bgcolors="";
		listbgColor  =  new ArrayList<>();
		listbgColor.add("#2d8aeb");
		listbgColor.add("#ff6868");
		listbgColor.add("#ffa93c");
		listbgColor.add("#05a705");
		Random  random  =  new Random();
		for (int j = 0; j < userSessionController.getMenus().size(); j++) {
			bgcolors =	listbgColor.get(random.nextInt(listbgColor.size()));
		}
		return  bgcolors;
	}
	public String  columnDivide(int k){
		k++;
		if (k%6==0) return "</p><p hidden>";
		return "";

	}
	public List<Post> getLispostPkId() {
		return lispostPkId;
	}
	public void setLispostPkId(List<Post> lispostPkId) {
		this.lispostPkId = lispostPkId;
	}
	public List<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}
	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
	public ILogicHomeLocal getLoHomeLocal() {
		return loHomeLocal;
	}
	public void setLoHomeLocal(ILogicHomeLocal loHomeLocal) {
		this.loHomeLocal = loHomeLocal;
	}
	public List<Role> getRolelist() {
		return rolelist;
	}
	public void setRolelist(List<Role> rolelist) {
		this.rolelist = rolelist;
	}
	public List<SubOrganization> getSubOrganizations() {
		return subOrganizations;
	}
	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}
	public List<Users> getUsers() {
		return users;
	}
	public void setUsers(List<Users> users) {
		this.users = users;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<Diagnose> getDiagnoses() {
		return diagnoses;
	}
	public void setDiagnoses(List<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}
	public List<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	public List<DiagnoseGroup> getDiagnoseGroups() {
		return diagnoseGroups;
	}
	public void setDiagnoseGroups(List<DiagnoseGroup> diagnoseGroups) {
		this.diagnoseGroups = diagnoseGroups;
	}
	public List<Xray> getXrays() {
		return xrays;
	}
	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}
	public List<EmployeeRequest> getEmployeeRequests() {
		return employeeRequests;
	}
	public void setEmployeeRequests(List<EmployeeRequest> employeeRequests) {
		this.employeeRequests = employeeRequests;
	}
	public List<Examination> getExaminations() {
		return examinations;
	}
	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}
	public List<ConditionalPrescription> getConditionalPrescriptions() {
		return conditionalPrescriptions;
	}
	public void setConditionalPrescriptions(List<ConditionalPrescription> conditionalPrescriptions) {
		this.conditionalPrescriptions = conditionalPrescriptions;
	}
	public List<Surgery> getSurgeries() {
		return surgeries;
	}
	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}
	public List<ExaminationRequest> getExaminationRequests() {
		return examinationRequests;
	}
	public void setExaminationRequests(List<ExaminationRequest> examinationRequests) {
		this.examinationRequests = examinationRequests;
	}
	public List<Inspection> getInspections() {
		return inspections;
	}
	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}
	public List<SubOrganization> getListsubOrganizations() {
		return listsubOrganizations;
	}
	public void setListsubOrganizations(List<SubOrganization> listsubOrganizations) {
		this.listsubOrganizations = listsubOrganizations;
	}
	public List<String> getListstring() {
		return liststring;
	}
	public void setListstring(List<String> liststring) {
		this.liststring = liststring;
	}
	public List<String> getSelectedSubOrgaValue() {
		return selectedSubOrgaValue;
	}
	public void setSelectedSubOrgaValue(List<String> selectedSubOrgaValue) {
		this.selectedSubOrgaValue = selectedSubOrgaValue;
	}
	public List<Employee> getEmployeelist() {
		return employeelist;
	}
	public void setEmployeelist(List<Employee> employeelist) {
		this.employeelist = employeelist;
	}
	public List<String> getSelectedEmployee() {
		return selectedEmployee;
	}
	public void setSelectedEmployee(List<String> selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	public Post getPost() {
		if (post==null) {
			post  =  new Post();
			post.setStatus(Tool.ADDED);
		}
		else if(post==null) {
			post  =  new Post();
			post.setStatus(Tool.DELETE);
		}
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}

	public Employee getEmployee() {
		if (employee==null) {
			employee  =  new Employee();
			employee.setStatus(Tool.ADDED);
		}
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public InspectionController getInspectionController() {
		return inspectionController;
	}
	public void setInspectionController(InspectionController inspectionController) {
		this.inspectionController = inspectionController;
	}
	public String getSelecttext() {
		return selecttext;
	}
	public void setSelecttext(String selecttext) {
		this.selecttext = selecttext;
	}
	public List<Post> getListpost() {
		return listpost;
	}
	public void setListpost(List<Post> listpost) {
		this.listpost = listpost;
	}
	public Post getPosts() {
		return posts;
	}
	public void setPosts(Post posts) {
		this.posts = posts;
	}
	public List<Post> getListpostgroup() {
		return listpostgroup;
	}
	public void setListpostgroup(List<Post> listpostgroup) {
		this.listpostgroup = listpostgroup;
	}
	public List<Post> getListemployee() {
		return listemployee;
	}
	public void setListemployee(List<Post> listemployee) {
		this.listemployee = listemployee;
	}

	public List<Post> getListpostEmployeePkId() {
		return listpostEmployeePkId;
	}
	public void setListpostEmployeePkId(List<Post> listpostEmployeePkId) {
		this.listpostEmployeePkId = listpostEmployeePkId;
	}
	public Date getSelectDate() {
		if (selectDate==null) {
			selectDate  =  new Date();
		}
		return selectDate;
	}
	public void setSelectDate(Date selectDate) {
		this.selectDate = selectDate;
	}
	public List<String> getListbgColor() {
		return listbgColor;
	}
	public void setListbgColor(List<String> listbgColor) {
		this.listbgColor = listbgColor;
	}
	public List<Post> getListreceiverPkId() {
		return listreceiverPkId;
	}
	public void setListreceiverPkId(List<Post> listreceiverPkId) {
		this.listreceiverPkId = listreceiverPkId;
	}
	public List<Post> getListPostMapPkId() {
		return listPostMapPkId;
	}
	public void setListPostMapPkId(List<Post> listPostMapPkId) {
		this.listPostMapPkId = listPostMapPkId;
	}

}

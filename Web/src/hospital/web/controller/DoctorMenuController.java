package hospital.web.controller;

import hospital.businessentity.ExaRequest;
import hospital.businesslogic.interfaces.IInfoLogic;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.businesslogic.interfaces.ILogicMenuLocal;
import hospital.businesslogic.interfaces.IUserLogicLocal;
import hospital.entity.Customer;
import hospital.entity.CustomerSurgery;
import hospital.entity.CustomerTreatment;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.ExaminationValueQuestion;
import hospital.entity.Inspection;
import hospital.entity.InspectionForm;
import hospital.entity.Medicine;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.report.Am10;
import hospital.report.Am11;
import hospital.report.Am13a;
import hospital.report.Am25;
import hospital.report.Am29;
import hospital.report.Am8;
import hospital.report.Am9a;
import hospital.report.Gt15Pain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.json.simple.JSONObject;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "menuController")
public class DoctorMenuController {

	// @EJB(beanName = "PlanLogic")
	// IPlanLogicLocal planLogic;

	@EJB(beanName = "UserLogic")
	IUserLogicLocal userLogic;

	@EJB(beanName = "LogicMenu")
	ILogicMenuLocal menuLogic;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{inspectionController}")
	private InspectionController inspectionController;

	@ManagedProperty(value = "#{examinationController}")
	private ExaminationController examinationController;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	private List<Customer> customerList;
	private List<ExaminationValueQuestion> questions;
	private List<CustomerSurgery> surgeries;
	private List<Inspection> inspections;
	private List<ExaminationRequestCompleted> requestList;
	private ExaminationRequestCompleted cursorErc;
	private ExaRequest chosenDrequest;
	private Am8 currentAm8;
	private BigDecimal selectedInspectionPkId;
	private BigDecimal  selectedCustomerInspectionPkId;
	private List<SubOrganization> sos;
	private List<Employee> employees;
	private BigDecimal cursorSosPkId;
	private BigDecimal cursorEmployeePkId;
	private String employeeName;
	private String orderDescription;
	//маягт класс
	private Am8  am8SelectCustomer;
	private Am9a am9as;
	private Am9a am9asEmployee;
	private  Am10  am10;
	private  Am11  am11;
	private  Am13a  am13a;
	private  Am11  am11Employee;
	private  Am11  am11Employeecustomer;
	private Am25 am25;
	private Am25 am25Employee;
	private   Am25  am25Diagnose;
	private  Am29   am29;
	private  Am29   am29diagnose;
	private  Am29   am29Employee;
	private String onememu;
	private String am29dOneMenu;
	private  String am29bOneMenuText;
	private String dateString;
	private String  endDateString;
	private  List<Gt15Pain>  lGt15Pains;
	private  Gt15Pain  gt15Pains;
	private Date  selectInspectionDate;
	private  List<String> selectSubNameOneMenu;
	private List<String>  filteredValueStringList;
	public DoctorMenuController() {
	}
	public void getAm8() {
		try {
			getCurrentAm8();
			currentAm8 = menuLogic.getAm8(getSelectedCustomerInspectionPkId());
			am8SelectCustomer  = menuLogic.getAm8Cutomer(getInspectionController().getSelectedCustomer().getPkId());
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:mp");
			context.update("form:mp_content");
		} catch (Exception ex) {

		}
	}
	public void getAm9a(){
		try {
			System.out.println( "selectedInspection " + getSelectedInspectionPkId() + "employee" +getInspectionController().getCurrentEmployee().getPkId()+"customer"+ getInspectionController().getSelectedCustomer().getPkId());
			getAm9as();
			am9as= menuLogic.getAm9a(getSelectedInspectionPkId());
			System.out.println("TEST : " + am9as.getMedicines().size());
			am9asEmployee  =  menuLogic.getAm9aDiagnose(getInspectionController().getCurrentEmployee().getPkId(), getInspectionController().getSelectedCustomer().getPkId(),getSelectedInspectionPkId());
			System.out.println("TEST Diagnose count: " + am9as.getCustomerDiagnose().size());
			RequestContext  context  =  RequestContext.getCurrentInstance();
			context.update("form:am9aselect");
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	public  void  am10a(){
		try {
			am10  =  menuLogic.getAm10(getInspectionController().getSelectedCustomer().getPkId());

			for (int i = 0; i < am10.getCustomer().size(); i++) {
				System.out.println(am10.getCustomer().get(i).getAge());
			}
		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
		}
	}
	public  void  am11(){
		try {
			am11  =  menuLogic.getAm11(getInspectionController().getSelectedCustomer().getPkId());
			for (int i = 0; i < am11.getCustomer().size(); i++) {
				System.out.println( "NAme :" +am11.getCustomer().get(i).getFirstName());
			}
			am11Employee  =  menuLogic.getAm11Employee(getInspectionController().getCurrentEmployee().getPkId());
			am11Employeecustomer  =  menuLogic.getAm11EmployeeCustomer(getInspectionController().getCurrentEmployee().getPkId(), getInspectionController().getSelectedCustomer().getPkId());
			System.out.println(am11Employeecustomer.getCustomerDiagnose().size());
		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
		}
	}

	public void  am13a(){
		try {
			am13a=  menuLogic.getAm13a(getInspectionController().getSelectedCustomer().getPkId());
			for (int i = 0; i < am13a.getCustomer().size(); i++) {
				System.out.println("Name " +am13a.getCustomer().get(i).getFirstName());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public  void  am25(){
		try {

			am25 = menuLogic.getAm25(getInspectionController().getSelectedCustomer().getPkId());
			am25Diagnose  =  menuLogic.getAm25Diagnose(getInspectionController().getCurrentEmployee().getPkId(), getInspectionController().getSelectedCustomer().getPkId());
			if (am25.getCustomerTreatments().get(0).getDateSimple().isEmpty() ||  am25.getCustomerTreatments().get(0).getDateSimple()==null) {
				System.out.println("Эмчилгээ эхлээгүй");
				userSessionController.showErrorMessage("Эмчилгээ эхлээгүй");
				userSessionController.setErrorMessage("Эмчилгээ эхлээгүй");
				return ;
			}
			else {
				dateString=	am25.getCustomerTreatments().get(0).getDateSimple();
				endDateString =  am25.getCustomerTreatments().get(am25.getCustomerTreatments().size()-1).getDateSimple();
			}
		} catch (Exception e) {
			getUserSessionController().showErrorMessage("am25 Эмчилгээ эхлээгүй" +e.getMessage());
		}
	}
	public  void  am29a(){
		try {
			System.out.println("am29");
			am29   =  menuLogic.getAm29Customer(getInspectionController().getSelectedCustomer().getPkId());
			am29diagnose =  menuLogic.getAm29Diagnose(getInspectionController().getCurrentEmployee().getPkId(), getInspectionController().getSelectedCustomer().getPkId());
			System.out.println("am29 Diagnose count :" + am29.getCustomerDiagnose().size());

		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
		}
	}
	public void gt15Pain(){
		try {
			lGt15Pains  =  menuLogic.getCustomerSubOrganization(getInspectionController().getSelectedCustomer().getPkId());
			
		} catch (Exception e) {
			getUserSessionController().showErrorMessage("Gt15Pain " + e.getMessage());
		}
		
	}
	public void loadExaRequestData() {
		try {
			getRequestList();
			requestList = menuLogic.getCompletedRequests(null);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('exaRequestList').show();");
			context.update("form:exaRequestList");
			context.update("form:exaList");

		} catch (Exception ex) {

		}
	}
	public String getDate(){
		String dateFormatString;
		Date  date =  new Date();
		SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		dateFormatString  =  simpleDateFormat.format(date);
		return  dateFormatString;
	}

	public void fillExmaination(ExaminationRequestCompleted erc) {
		ExaRequest er = new ExaRequest(erc);
		setChosenDrequest(er);
		examinationController.setChosenDrequest(er);
		try {
			examinationController.printExaminationHdr(er);
		} catch (Exception ex) {

		}
	}

	public void soPicked() {
		try {
			RequestContext context = RequestContext.getCurrentInstance();
			setEmployees(menuLogic.getEmployees(cursorSosPkId));

		} catch (Exception ex) {

		}
	}

	public void employeePicked() {
		try {
			getEmployeeName();
			if (cursorEmployeePkId != null && cursorEmployeePkId.compareTo(BigDecimal.ZERO) != 0)
				setEmployeeName(menuLogic.getCursorEmployee(cursorEmployeePkId).getFirstName());
			System.out.println(getEmployeeName());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void loadData() {
		try {
			getInspections();
			inspections = menuLogic.getInspections(getInspectionController().getSelectedCustomer().getPkId(),getInspectionController().getCurrentEmployee().getPkId());
			setSos(infoLogic.getSubOrganizations());

			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:am8");
			context.update("form:qo");
			context.update("form:rotk");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public List<ExaminationValueQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<ExaminationValueQuestion> questions) {
		this.questions = questions;
	}

	public List<CustomerSurgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<CustomerSurgery> surgeries) {
		this.surgeries = surgeries;
	}

	public List<Inspection> getInspections() {
		if (inspections == null)
			inspections = new ArrayList<Inspection>();

		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public InspectionController getInspectionController() {
		return inspectionController;
	}

	public void setInspectionController(InspectionController inspectionController) {
		this.inspectionController = inspectionController;
	}

	public BigDecimal getSelectedInspectionPkId() {
		return selectedInspectionPkId;
	}

	public void setSelectedInspectionPkId(BigDecimal selectedInspectionPkId) {
		this.selectedInspectionPkId = selectedInspectionPkId;
	}

	public Am8 getCurrentAm8() {
		return currentAm8;
	}

	public void setCurrentAm8(Am8 currentAm8) {
		this.currentAm8 = currentAm8;
	}

	public List<ExaminationRequestCompleted> getRequestList() {
		return requestList;
	}

	public void setRequestList(List<ExaminationRequestCompleted> requestList) {
		this.requestList = requestList;
	}

	public ExaminationController getExaminationController() {
		return examinationController;
	}

	public void setExaminationController(ExaminationController examinationController) {
		this.examinationController = examinationController;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public ExaRequest getChosenDrequest() {
		if (chosenDrequest == null)
			chosenDrequest = new ExaRequest();
		return chosenDrequest;
	}

	public void setChosenDrequest(ExaRequest chosenDrequest) {
		this.chosenDrequest = chosenDrequest;
	}

	public ExaminationRequestCompleted getCursorErc() {
		return cursorErc;
	}

	public void setCursorErc(ExaminationRequestCompleted cursorErc) {
		this.cursorErc = cursorErc;
	}

	public List<SubOrganization> getSos() {
		return sos;
	}

	public void setSos(List<SubOrganization> sos) {
		this.sos = sos;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public BigDecimal getCursorEmployeePkId() {
		return cursorEmployeePkId;
	}

	public void setCursorEmployeePkId(BigDecimal cursorEmployeePkId) {
		this.cursorEmployeePkId = cursorEmployeePkId;
	}

	public void setCursorSosPkId(BigDecimal cursorSosPkId) {
		this.cursorSosPkId = cursorSosPkId;
	}

	public BigDecimal getCursorSosPkId() {
		return cursorSosPkId;
	}

	public String getEmployeeName() {
		if (employeeName == null)
			employeeName = "";
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getOrderDescription() {
		if(orderDescription == null)
			orderDescription = "";
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}

	public Am9a getAm9as() {
		return am9as;
	}

	public void setAm9as(Am9a am9as) {
		this.am9as = am9as;
	}

	public Am9a getAm9asEmployee() {
		return am9asEmployee;
	}

	public void setAm9asEmployee(Am9a am9asEmployee) {
		this.am9asEmployee = am9asEmployee;
	}

	public Am10 getAm10() {
		return am10;
	}

	public void setAm10(Am10 am10) {
		this.am10 = am10;
	}

	public Am11 getAm11() {
		return am11;
	}

	public void setAm11(Am11 am11) {
		this.am11 = am11;
	}

	public Am13a getAm13a() {
		return am13a;
	}

	public void setAm13a(Am13a am13a) {
		this.am13a = am13a;
	}

	public Am11 getAm11Employee() {
		return am11Employee;
	}

	public void setAm11Employee(Am11 am11Employee) {
		this.am11Employee = am11Employee;
	}

	public Am11 getAm11Employeecustomer() {
		return am11Employeecustomer;
	}

	public void setAm11Employeecustomer(Am11 am11Employeecustomer) {
		this.am11Employeecustomer = am11Employeecustomer;
	}

	public Am25 getAm25() {
		return am25;
	}

	public void setAm25(Am25 am25) {
		this.am25 = am25;
	}

	public Am25 getAm25Employee() {
		return am25Employee;
	}

	public void setAm25Employee(Am25 am25Employee) {
		this.am25Employee = am25Employee;
	}

	public Am25 getAm25Diagnose() {
		return am25Diagnose;
	}

	public void setAm25Diagnose(Am25 am25Diagnose) {
		this.am25Diagnose = am25Diagnose;
	}

	public Am29 getAm29() {
		return am29;
	}

	public void setAm29(Am29 am29) {
		this.am29 = am29;
	}

	public Am29 getAm29diagnose() {
		return am29diagnose;
	}

	public void setAm29diagnose(Am29 am29diagnose) {
		this.am29diagnose = am29diagnose;
	}

	public Am29 getAm29Employee() {
		return am29Employee;
	}

	public void setAm29Employee(Am29 am29Employee) {
		this.am29Employee = am29Employee;
	}

	public String getOnememu() {
		return onememu;
	}

	public void setOnememu(String onememu) {
		this.onememu = onememu;
	}

	public String getAm29dOneMenu() {
		return am29dOneMenu;
	}

	public void setAm29dOneMenu(String am29dOneMenu) {
		this.am29dOneMenu = am29dOneMenu;
	}

	public String getAm29bOneMenuText() {
		return am29bOneMenuText;
	}

	public void setAm29bOneMenuText(String am29bOneMenuText) {
		this.am29bOneMenuText = am29bOneMenuText;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public List<Gt15Pain> getlGt15Pains() {
		return lGt15Pains;
	}

	public void setlGt15Pains(List<Gt15Pain> lGt15Pains) {
		this.lGt15Pains = lGt15Pains;
	}
	public Gt15Pain getGt15Pains() {
		return gt15Pains;
	}
	public void setGt15Pains(Gt15Pain gt15Pains) {
		this.gt15Pains = gt15Pains;
	}
	public Date getSelectInspectionDate() {
		return selectInspectionDate;
	}
	public void setSelectInspectionDate(Date selectInspectionDate) {
		this.selectInspectionDate = selectInspectionDate;
	}

	public List<String> getSelectSubNameOneMenu() {
		return selectSubNameOneMenu;
	}
	public void setSelectSubNameOneMenu(List<String> selectSubNameOneMenu) {
		this.selectSubNameOneMenu = selectSubNameOneMenu;
	}
	public List<String> getFilteredValueStringList() {
		return filteredValueStringList;
	}
	public void setFilteredValueStringList(List<String> filteredValueStringList) {
		this.filteredValueStringList = filteredValueStringList;
	}
	public BigDecimal getSelectedCustomerInspectionPkId() {
		return selectedCustomerInspectionPkId;
	}
	public void setSelectedCustomerInspectionPkId(BigDecimal selectedCustomerInspectionPkId) {
		this.selectedCustomerInspectionPkId = selectedCustomerInspectionPkId;
	}
	public Am8 getAm8SelectCustomer() {
		return am8SelectCustomer;
	}
	public void setAm8SelectCustomer(Am8 am8SelectCustomer) {
		this.am8SelectCustomer = am8SelectCustomer;
	}
	
	
		
}

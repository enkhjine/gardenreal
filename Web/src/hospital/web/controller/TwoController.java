package hospital.web.controller;

import hospital.businessentity.CalendarItem;
import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.InspectionHistory;
import hospital.businessentity.LTime;
import hospital.businessentity.ReportFilter;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.ILogicCashierLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicPaymentLocal;
import hospital.businesslogic.interfaces.ILogicRequestLocal;
import hospital.businesslogic.interfaces.ILogicReservationLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Customer;
import hospital.entity.Xray;
import hospital.entity.XrayType;
import hospital.web.ajax.SOAPClientSAAJ;
import hospital.entity.EconomicCalendar;
import hospital.entity.EconomicCalendarDtl;
import hospital.entity.EconomicCalendarHdr;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.Payment;
import hospital.entity.PaymentHistory;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import logic.data.CustomHashMap;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

@SessionScoped
@ManagedBean(name = "twoController")
public class TwoController {
	
	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;
	
	@EJB(beanName = "LogicPayment")
	ILogicPaymentLocal logicPayment;
	
	@EJB(beanName = "LogicCashier")
	ILogicCashierLocal logicCashier;
	
	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;
	
	@EJB(beanName = "LogicXray")
	ILogicXrayLocal xrayLogic;
	
	@EJB(beanName = "LogicRequest")
	ILogicRequestLocal logicRequest;
	
	@EJB(beanName = "LogicReservation")
	ILogicReservationLocal reservationLogic;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{logicOneController}")
	private InspectionController logicOneController;
	
	@ManagedProperty(value = "#{customerController}")
	private CustomerController customerController;
	
	private List<hospital.businessentity.Calendar> calendars;
	private List<hospital.businessentity.Calendar> emplouyeeCalendars;
	private String calendarStr;
	private String employeeCalendarStr;
	private List<SubOrganization> subOrganizations;
	private SubOrganization subOrganization;
	private List<Employee> filteredEmployees;
	private List<Employee> selectedEmployees;
	private List<Employee> lstSelectionEmployees;
	private String selectedSubOrganizationStr;
	private EconomicCalendarHdr economicCalendarHdr;
	private BigDecimal subOrganizationPkId;
	private List<CalendarItem> calendarItemsAsd1;
	
	//RequestCustomer begin
	private List<CalendarItem> calendarItems;
	private List<Customer> listCustomer;
	private Customer selectedCustomer;
	private String selectedCustomerReg;
	private List<EmployeeRequest> listEmployeeRequests;
	private EconomicCalendar currentEconomicCalendar;
	//RequestCustomer end
	
	//Propertys begin
	private int selectedMonth;
	private int selectedDay;
	private int currentYear;
	//Propertys end
	
	//Propertys begin
	private Customer selectedCashCustomer;
	private List<CashBusinessEntity> cashHdrs;
	private Payment payment;
	private Date beginDate;
	private Date endDate;
	private PaymentHistory history;
	private List<EmployeeRequest> listEmployeeRequest;
	private List<InspectionHistory> inspectionHistories;
	private List<Employee> listEmployee;
	private List<Xray> listXray;
	private List<Xray> xrays;
	private List<XrayType> listXrayType;
	private XrayType selectedXrayType;
	private List<Xray> listFilteredXray;
	private Xray selectedXray;
	private int deleteIndexOfList;
	private Date beginFilterBeginDate;
	private Date beginFilterEndDate;
	private List<Xray> listCashXray;
	private List<Employee> listCashEmployee;
	private List<PaymentHistory> paymentHistories;
	private Customer customerCash;
	private Payment cashPayment;
	private List<PaymentHistory> listLoanEmployee;
	private boolean actionProgress;
	private boolean calculateCashier;
	private boolean timeChoose;
	private boolean timeAllDay;
	//Propertys end
	
	//changePassword begin
	private String oldPassword;
	private String newPassword;
	private String newPasswordConfirm;
	private List<Employee> employees;
	private Employee selectedEmployee;
	private SubOrganization selectedSubOrganization;
	//changePassword end
	
	private UploadedFile file;
	
	private String hereggui;
	private ReportFilter filter;
	private int isCalendar = 0;
	private boolean isReportCashier;
	
	private List<EconomicCalendarHdr> calendarHdrs;
	private Date minDate;
	
	public void clearPayment(){
		payment = new Payment();
		selectedCashCustomer = new Customer();
		selectedCustomer = new Customer();
		listEmployee = new ArrayList<Employee>();
		cashHdrs = new ArrayList<CashBusinessEntity>();
		listXray = new ArrayList<Xray>();
		xrays = new ArrayList<Xray>();
	}
	
	public String deletetEmployeeCalendar(BigDecimal calendarPkId) {
		String ret = "";
		
		try {
			logicTwo.deletetEmployeeCalendar(calendarPkId);
			ret = "employeeCalendar";
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public String saveEconomicCalendar(){
		String ret = "";
		
		if((getApplicationController().getCurrentEconomicCalendar().getBeginHour() > getApplicationController().getCurrentEconomicCalendar().getEndHour()) || (getApplicationController().getCurrentEconomicCalendar().getBeginHour() == getApplicationController().getCurrentEconomicCalendar().getEndHour() && getApplicationController().getCurrentEconomicCalendar().getBeginMinute() > getApplicationController().getCurrentEconomicCalendar().getEndMinute())){
			getUserSessionController().showMessage(16);
			return ret;
		}
		
		if((getApplicationController().getCurrentEconomicCalendar().getLunchBeginHour() > getApplicationController().getCurrentEconomicCalendar().getLunchEndHour()) || (getApplicationController().getCurrentEconomicCalendar().getLunchBeginHour() == getApplicationController().getCurrentEconomicCalendar().getLunchEndHour() && getApplicationController().getCurrentEconomicCalendar().getLunchBeginMinute() > getApplicationController().getCurrentEconomicCalendar().getLunchEndMinute())){
			getUserSessionController().showMessage(17);
			return ret;
		}
		
		if((getApplicationController().getCurrentEconomicCalendar().getLunchBeginHour() > getApplicationController().getCurrentEconomicCalendar().getLunchEndHour()) || (getApplicationController().getCurrentEconomicCalendar().getLunchBeginHour() == getApplicationController().getCurrentEconomicCalendar().getLunchEndHour() && getApplicationController().getCurrentEconomicCalendar().getLunchBeginMinute() > getApplicationController().getCurrentEconomicCalendar().getLunchEndMinute())){
			getUserSessionController().showMessage(18);
			return ret;
		}
		
		if((getApplicationController().getCurrentEconomicCalendar().getSaturDayBeginHour() > getApplicationController().getCurrentEconomicCalendar().getSaturDayEndHour()) || (getApplicationController().getCurrentEconomicCalendar().getSaturDayBeginHour() == getApplicationController().getCurrentEconomicCalendar().getSaturDayEndHour() && getApplicationController().getCurrentEconomicCalendar().getSaturDayBeginMinute() > getApplicationController().getCurrentEconomicCalendar().getSaturDayEndMinute())){
			getUserSessionController().showMessage(19);
			return ret;
		}
		
		try{
			logicTwo.saveEconomicCalendar(getUserSessionController().getLoggedInfo(), getApplicationController().getCurrentEconomicCalendar());
			calendars = null;
			ret = getUserSessionController().getUrl("calendar");
			getApplicationController().setCurrentEconomicCalendar(null);
		}catch(Exception ex){
			
		}
		
		return ret;
	}
	
	public String filterCalendar(){
		calendars = null;
		return "calendar";
	}

	public TwoController() {
		
	}
	
	public String saveEconomicCalendarOnlyEmployee() {
		String ret = "";
		if((economicCalendarHdr.getEndTimeHour() < economicCalendarHdr.getBeginTimeHour()) || (economicCalendarHdr.getEndTimeHour() == economicCalendarHdr.getBeginTimeHour() && economicCalendarHdr.getBeginTimeMinute() > economicCalendarHdr.getEndTimeMinute())){
			getUserSessionController().showMessage(22);
			return ret;
		}
		
//		if(logicTwo.isDuplicateEconomicCalendar(economicCalendarHdr)) {
//			
//			return ret;
//		}
		
		try{
			lstSelectionEmployees = new ArrayList<Employee>();
			lstSelectionEmployees.add(getUserSessionController().getLoggedInfo().getEmployee());
			economicCalendarHdr.setType(1);
			economicCalendarHdr.setTypePkId(getUserSessionController().getLoggedInfo().getEmployee().getPkId());
			economicCalendarHdr.setEndDate(economicCalendarHdr.getBeginDate());
			logicTwo.saveEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), economicCalendarHdr, lstSelectionEmployees);
			lstSelectionEmployees = null;
			filteredEmployees = null;
			economicCalendarHdr = null;
			calendars = null;
			emplouyeeCalendars = null;
			calendarHdrs = null;
			ret = "employeeCalendar";
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public String saveEmployeeCalendarHdrByEmployee() {
		String ret = "";
		
		if(economicCalendarHdr.getBeginDate().after(economicCalendarHdr.getEndDate())){
			getUserSessionController().showMessage(21);
			return ret;
		}
		
		if((economicCalendarHdr.getEndTimeHour() < economicCalendarHdr.getBeginTimeHour()) || (economicCalendarHdr.getEndTimeHour() == economicCalendarHdr.getBeginTimeHour() && economicCalendarHdr.getBeginTimeMinute() > economicCalendarHdr.getEndTimeMinute())){
			getUserSessionController().showMessage(22);
			return ret;
		}
		
		try {
			if(logicTwo.isDuplicateEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), economicCalendarHdr)) {
				getUserSessionController().showMessage(95);
				return ret;
			}
		
			lstSelectionEmployees = new ArrayList<Employee>();
			lstSelectionEmployees.add(getUserSessionController().getLoggedInfo().getEmployee());
			economicCalendarHdr.setType(1);
			economicCalendarHdr.setTypePkId(getUserSessionController().getLoggedInfo().getEmployee().getPkId());
			logicTwo.saveEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), economicCalendarHdr, lstSelectionEmployees);
			lstSelectionEmployees = null;
			filteredEmployees = null;
			economicCalendarHdr = null;
			calendars = null;
			emplouyeeCalendars = null;
			calendarHdrs = null;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('economicCalendarDay').hide();");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public String saveEmployeeCalendarHdr(){
		String ret = "";
		
		if(lstSelectionEmployees == null || lstSelectionEmployees.size() < 1){
			getUserSessionController().showMessage(20);
			return ret;
		}
		
		if(!economicCalendarHdr.getEndDate().after(economicCalendarHdr.getBeginDate())){
			getUserSessionController().showMessage(21);
			return ret;
		}
		
		if((economicCalendarHdr.getEndTimeHour() < economicCalendarHdr.getBeginTimeHour()) || (economicCalendarHdr.getEndTimeHour() == economicCalendarHdr.getBeginTimeHour() && economicCalendarHdr.getBeginTimeMinute() > economicCalendarHdr.getEndTimeMinute())){
			getUserSessionController().showMessage(22);
			return ret;
		}
		
		try{
			logicTwo.saveEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), economicCalendarHdr, lstSelectionEmployees);
			lstSelectionEmployees = null;
			filteredEmployees = null;
			economicCalendarHdr = null;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('economicCalendarDay').hide();");
		}catch(Exception ex){
			
		}
		
		return ret;
	}
	
	public void selectEmployee(){
		selectedEmployees.size();
		if(lstSelectionEmployees == null) lstSelectionEmployees = new ArrayList<Employee>();
		lstSelectionEmployees.clear();
		for (Employee employee : selectedEmployees) {
			
			int employeeCount = 0;
			for(Employee emp : lstSelectionEmployees){if(emp.getPkId().compareTo(employee.getPkId()) == 0) employeeCount++;}
			if(employeeCount == 0) lstSelectionEmployees.add(employee);
		}
	}
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}
	
	private int daysOfMonth(int year, int month){
		int days = 31;
		
		if(month == 4 || month == 6 || month == 9 || month == 11)
		{
			days = 30;
		}else if(month == 2){
			days = 28;
			if(year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)){
				days = 29;
			}
		}
		
		return days;
	}

	public List<hospital.businessentity.Calendar> getCalendars() {
		if(calendars == null || calendars.size() < 1){
			try{
				calendars = new ArrayList<hospital.businessentity.Calendar>();
				EconomicCalendar economicCalendar = logicTwo.getEconomicCalendar(getUserSessionController().getLoggedInfo());
				int year = (new Date()).getYear() + 1900;
				for(int mon = 0; mon < 12; mon++){
					for(int day = 1; day <= daysOfMonth(year, mon + 1); day++){
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, mon);
						calendar.set(Calendar.DATE, day);
						int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
						
						hospital.businessentity.Calendar cal = new hospital.businessentity.Calendar();
						cal.setTitle("Ажлийн цаг");
						cal.setBy(year);
						cal.setbM(mon);
						cal.setBd(day);
						cal.setEy(year);
						cal.seteM(mon);
						cal.setEd(day);
						cal.setBackgroundColor(Tool.WorkStatusBgColor);
						cal.setBorderColor(Tool.WorkStatusBgColor);
						
						if(dayOfWeek == 7 && economicCalendar.getIsSaturday() == 1){
							cal.setBh(economicCalendar.getSaturDayBeginHour());
							cal.setBm(economicCalendar.getSaturDayBeginMinute());
							cal.setEh(economicCalendar.getSaturDayEndHour());
							cal.setEm(economicCalendar.getSaturDayEndMinute());
							calendars.add(cal);
						}else if(dayOfWeek == 1 && economicCalendar.getIsSunday() == 1){
							cal.setBh(economicCalendar.getSunDayBeginHour());
							cal.setBm(economicCalendar.getSunDayBeginMinute());
							cal.setEh(economicCalendar.getSunDayEndHour());
							cal.setEm(economicCalendar.getSunDayEndMinute());
							calendars.add(cal);
						}else if(dayOfWeek >= 2 && dayOfWeek <= 6){
							cal.setBh(economicCalendar.getBeginHour());
							cal.setBm(economicCalendar.getBeginMinute());
							cal.setEh(economicCalendar.getLunchBeginHour());
							cal.setEm(economicCalendar.getLunchBeginMinute());
							calendars.add(cal);
							
							cal = new hospital.businessentity.Calendar();
							cal.setTitle("Ажлийн цаг");
							cal.setBy(year);
							cal.setbM(mon);
							cal.setBd(day);
							cal.setEy(year);
							cal.seteM(mon);
							cal.setEd(day);
							cal.setBackgroundColor(Tool.WorkStatusBgColor);
							cal.setBorderColor(Tool.WorkStatusBgColor);
							cal.setBh(economicCalendar.getLunchEndHour());
							cal.setBm(economicCalendar.getLunchEndMinute());
							cal.setEh(economicCalendar.getEndHour());
							cal.setEm(economicCalendar.getEndMinute());
							calendars.add(cal);
						}
					}
				}
				
			}catch(Exception ex){
				
			}
			
		}
		return calendars;
	}
	
	public List<hospital.businessentity.Calendar> getCalendarsFilter() {
		if(calendars == null || calendars.size() < 1){
			try{
				calendars = new ArrayList<hospital.businessentity.Calendar>();
				List<String> calendarHdrsStr = new ArrayList<String>();
				List<EconomicCalendarHdr> calendarHdrs = logicTwo.getEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), getSubOrganizationPkId());
				CustomHashMap customHashMap = new CustomHashMap();
				for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs) {
					if(calendarHdrsStr.contains(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate())) {
						List<EconomicCalendarHdr> value = (List<EconomicCalendarHdr>) customHashMap.get(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate());
						value.add(economicCalendarHdr);
						customHashMap.put(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate(), value);
					}else {
						List<EconomicCalendarHdr> value = new ArrayList<EconomicCalendarHdr>();
						value.add(economicCalendarHdr);
						customHashMap.put(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate(), value);
						calendarHdrsStr.add(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate());
					}
				}
				EconomicCalendar economicCalendar = logicTwo.getEconomicCalendar(getUserSessionController().getLoggedInfo());
				int year = (new Date()).getYear() + 1900;
				for(int mon = 0; mon < 12; mon++){
					for(int day = 1; day <= daysOfMonth(year, mon + 1); day++){
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.YEAR, year);
						calendar.set(Calendar.MONTH, mon);
						calendar.set(Calendar.DATE, day);
						int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
						
						hospital.businessentity.Calendar cal = new hospital.businessentity.Calendar();
						cal.setTitle("Ажлийн цаг");
						cal.setBy(year);
						cal.setbM(mon);
						cal.setBd(day);
						cal.setEy(year);
						cal.seteM(mon);
						cal.setEd(day);
						cal.setBackgroundColor(Tool.WorkStatusBgColor);
						cal.setBorderColor(Tool.WorkStatusBgColor);
						
						if(dayOfWeek == 7 && economicCalendar.getIsSaturday() == 1){
							cal.setBh(economicCalendar.getSaturDayBeginHour());
							cal.setBm(economicCalendar.getSaturDayBeginMinute());
							cal.setEh(economicCalendar.getSaturDayEndHour());
							cal.setEm(economicCalendar.getSaturDayEndMinute());
							calendars.add(cal);
						}else if(dayOfWeek == 1 && economicCalendar.getIsSunday() == 1){
							cal.setBh(economicCalendar.getSunDayBeginHour());
							cal.setBm(economicCalendar.getSunDayBeginMinute());
							cal.setEh(economicCalendar.getSunDayEndHour());
							cal.setEm(economicCalendar.getSunDayEndMinute());
							calendars.add(cal);
						}else if(dayOfWeek >= 2 && dayOfWeek <= 6){
							cal.setBh(economicCalendar.getBeginHour());
							cal.setBm(economicCalendar.getBeginMinute());
							cal.setEh(economicCalendar.getLunchBeginHour());
							cal.setEm(economicCalendar.getLunchBeginMinute());
							calendars.add(cal);
							
							cal = new hospital.businessentity.Calendar();
							cal.setTitle("Ажлийн цаг");
							cal.setBy(year);
							cal.setbM(mon);
							cal.setBd(day);
							cal.setEy(year);
							cal.seteM(mon);
							cal.setEd(day);
							cal.setBackgroundColor(Tool.WorkStatusBgColor);
							cal.setBorderColor(Tool.WorkStatusBgColor);
							cal.setBh(economicCalendar.getLunchEndHour());
							cal.setBm(economicCalendar.getLunchEndMinute());
							cal.setEh(economicCalendar.getEndHour());
							cal.setEm(economicCalendar.getEndMinute());
							calendars.add(cal);
						}
						
						if(!calendarHdrsStr.contains(calendar.getTime().getYear()+"-"+calendar.getTime().getMonth()+"-"+calendar.getTime().getDate())) continue;
						//if(calendarHdrs != null && calendarHdrs.size() > 0){
							List<EconomicCalendarHdr> calendarHdrs2 = (List<EconomicCalendarHdr>) customHashMap.get(calendar.getTime().getYear()+"-"+calendar.getTime().getMonth()+"-"+calendar.getTime().getDate());
							for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs2) {
								if(economicCalendarHdr.getEmployees() != null && economicCalendarHdr.getEmployees().size() > 0){
									for (Employee employe : economicCalendarHdr.getEmployees()) {
										cal = new hospital.businessentity.Calendar();
										cal.setTitle(employe.getFirstName() + " - " + economicCalendarHdr.getName());
										cal.setBy(year);
										cal.setbM(mon);
										cal.setBd(day);
										cal.setEy(year);
										cal.seteM(mon);
										cal.setEd(day);
										cal.setBackgroundColor(Tool.LunchStatusBgColor);
										cal.setBorderColor(Tool.LunchStatusBgColor);
										cal.setBh(economicCalendarHdr.getBeginTimeHour());
										cal.setBm(economicCalendarHdr.getBeginTimeMinute());
										cal.setEh(economicCalendarHdr.getEndTimeHour());
										cal.setEm(economicCalendarHdr.getEndTimeMinute());
										calendars.add(cal);
									}
								}else {
									cal = new hospital.businessentity.Calendar();
									cal.setTitle(economicCalendarHdr.getSubOrganizationName() + " |- " + economicCalendarHdr.getName());
									cal.setBy(year);
									cal.setbM(mon);
									cal.setBd(day);
									cal.setEy(year);
									cal.seteM(mon);
									cal.setEd(day);
									cal.setBackgroundColor(Tool.LunchStatusBgColor);
									cal.setBorderColor(Tool.LunchStatusBgColor);
									cal.setBh(economicCalendarHdr.getBeginTimeHour());
									cal.setBm(economicCalendarHdr.getBeginTimeMinute());
									cal.setEh(economicCalendarHdr.getEndTimeHour());
									cal.setEm(economicCalendarHdr.getEndTimeMinute());
									calendars.add(cal);
								}
							}
						//}
					}
				}
				
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
			
		}
		return calendars;
	}

	public void setCalendars(List<hospital.businessentity.Calendar> calendars) {
		this.calendars = calendars;
	}

	public String getCalendarStr() {
		calendarStr = "";
			for(hospital.businessentity.Calendar calendar : getCalendarsFilter()){
				calendarStr += "{ "
					+ "title : '" + calendar.getTitle() + "', "
					+ "start : new Date("+calendar.getBy()+", "+calendar.getbM()+", "+calendar.getBd()+", "+calendar.getBh()+", "+calendar.getBm()+"), "
					+ "end : new Date("+calendar.getEy()+", "+calendar.geteM()+", "+calendar.getEd()+", "+calendar.getEh()+", "+calendar.getEm()+"), "
					+ "allDay : false, "
					+ "backgroundColor : \""+calendar.getBackgroundColor()+"\", "
					+ "borderColor : \""+calendar.getBorderColor()+"\" }, ";
			
		}
		return calendarStr;
	}

	public void setCalendarStr(String calendarStr) {
		this.calendarStr = calendarStr;
	}

	public List<SubOrganization> getSubOrganizations() {
		if(subOrganizations == null || subOrganizations.size() < 1){
			try{
				subOrganizations = logicTwo.getSubOrganizations(getUserSessionController().getLoggedInfo());
				if(subOrganizations.size() > 0){
					setSubOrganization(subOrganizations.get(0));
				}
			}catch(Exception ex){
				
			}
		}
		return subOrganizations;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public SubOrganization getSubOrganization() {
		return subOrganization;
	}

	public void setSubOrganization(SubOrganization subOrganization) {
		filteredEmployees = null;
		this.subOrganization = subOrganization;
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("@(.employeeTable)");
	}

	public List<Employee> getFilteredEmployees() {
		if(filteredEmployees == null && subOrganization != null){
			try{
				filteredEmployees = logicTwo.getEmployeeBySubOrganizationPkId(subOrganization.getPkId());
			}catch(Exception ex){
				
			}
		}
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	public List<Employee> getSelectedEmployees() {
		return selectedEmployees;
	}

	public void setSelectedEmployees(List<Employee> selectedEmployees) {
		this.selectedEmployees = selectedEmployees;
	}

	public List<Employee> getLstSelectionEmployees() {
		if(lstSelectionEmployees == null || lstSelectionEmployees.size() < 1){
			lstSelectionEmployees = new ArrayList<Employee>();
		}
		return lstSelectionEmployees;
	}

	public void setLstSelectionEmployees(List<Employee> lstSelectionEmployees) {
		this.lstSelectionEmployees = lstSelectionEmployees;
	}

	public EconomicCalendarHdr getEconomicCalendarHdr() {
		if(economicCalendarHdr == null){
			economicCalendarHdr = new EconomicCalendarHdr();
			economicCalendarHdr.setType(0);
			economicCalendarHdr.setStatus(Tool.ADDED);
			economicCalendarHdr.setBeginDate(new Date());
			economicCalendarHdr.setEndDate(new Date());
		}
		return economicCalendarHdr;
	}

	public void setEconomicCalendarHdr(EconomicCalendarHdr economicCalendarHdr) {
		this.economicCalendarHdr = economicCalendarHdr;
	}

	public String getLstSelectionEmployeeNames() {
		String lstSelectionEmployeeNames = "";
		boolean isFirst = true;
		if(lstSelectionEmployees != null && lstSelectionEmployees.size() > 0){
			for (Employee item : lstSelectionEmployees) {
				if(!isFirst){
					lstSelectionEmployeeNames += ", ";
				}
				lstSelectionEmployeeNames = lstSelectionEmployeeNames + item.getFirstName();
				isFirst = false;
			}
		}
		return lstSelectionEmployeeNames;
	}

	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId == null ? BigDecimal.ZERO : subOrganizationPkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}
	
	public void setSelectedSubOrganizationStr(String sss){
		BigDecimal bigDecimal = new BigDecimal(sss);
		try{
			getEconomicCalendarHdr().setTypePkId(bigDecimal);
			//setSelectedSubOrganization(logicTwo.getByPkId(SubOrganization.class, bigDecimal));
			setSubOrganization(logicTwo.getByPkId(SubOrganization.class, bigDecimal));
			setIsCalendar(0);
		}catch(Exception ex){
			
		}
	}
	
	public String getSelectedSubOrganizationStr() {
		return selectedSubOrganizationStr;
	}
	
	public List<EmployeeRequest> getEmployeeRequests1(){
		Date date1 = new Date();
		Date date2 = new Date();
		date1.setYear(getUserSessionController().getCurrentYear() - 1900);
		date1.setMonth(getUserSessionController().getCurrentMonth() - 1);
		date1.setDate(getUserSessionController().getCurrentDay());
		date2.setYear(getUserSessionController().getCurrentYear() - 1900);
		date2.setMonth(getUserSessionController().getCurrentMonth() - 1);
		date2.setDate(getUserSessionController().getCurrentDay());
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(24);
		date2.setMinutes(59);
		date2.setSeconds(59);
		List<EmployeeRequest> employeeRequests = new ArrayList<EmployeeRequest>();
		try{
			employeeRequests = reservationLogic.getEmployeeRequestByDate(date1, date2, getUserSessionController().getLoggedInfo());
		}catch(Exception ex){
			
		}
		return employeeRequests;
	}
	
	public List<CalendarItem> getCalendarItemsAsd1() {
		return calendarItemsAsd1;
	}
	
	public void fillCalendar()
	{
		calendarItemsAsd1 = new ArrayList<CalendarItem>();
		EconomicCalendar economicCalendar =  getApplicationController().getCurrentEconomicCalendar();
		List<EmployeeRequest> employeeRequests = getEmployeeRequests1();
		List<EconomicCalendarDtl> calendarDtls = getEconomicCalendarDtl();
		try{
			
		
			Employee emp = logicTwo.getByPkId(Employee.class, getUserSessionController().getCurrentEmployeePkId());
			LTime mbTime = new LTime();mbTime.setHour(economicCalendar.getBeginHour());mbTime.setMinute(economicCalendar.getBeginMinute());
			LTime meTime = new LTime();meTime.setHour(economicCalendar.getEndHour());meTime.setMinute(economicCalendar.getEndMinute());
			LTime lbTime = new LTime();lbTime.setHour(economicCalendar.getLunchBeginHour());lbTime.setMinute(economicCalendar.getLunchBeginMinute());
			LTime leTime = new LTime();leTime.setHour(economicCalendar.getLunchEndHour());leTime.setMinute(economicCalendar.getLunchEndMinute());
			for(LTime in = mbTime; Tool.isAfterLTime(mbTime, meTime) == 1; in.addMinute(emp.getInspectionTime())){
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.setEmployeePkId(emp.getPkId());
				LTime time = new LTime();time.setHour(in.getHour());time.setMinute(in.getMinute());time.addMinute(emp.getInspectionTime());
				
				EconomicCalendarDtl calendarDtl = null;
				LTime timeb;
				LTime timee;
				for(EconomicCalendarDtl dtl : calendarDtls){
					timeb = new LTime();timeb.setHour(dtl.getCalendarHdr().getBeginTimeHour());timeb.setMinute(dtl.getCalendarHdr().getBeginTimeMinute());
					timee = new LTime();timee.setHour(dtl.getCalendarHdr().getEndTimeHour());timee.setMinute(dtl.getCalendarHdr().getEndTimeMinute());
					if(((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)) || (Tool.isAfterLTime(timeb, in) == 1 && Tool.isAfterLTime(time, timee) == 1) && dtl.getEmployee().getPkId().compareTo(emp.getPkId()) == 0)
						calendarDtl = dtl;
				}
				
				if(calendarDtl != null){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + calendarDtl.getCalendarHdr().getName());
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				}else if((Tool.isAfterLTime(lbTime, in) == 1 || Tool.isAfterLTime(lbTime, time) == 1) && (Tool.isAfterLTime(in, leTime) == 1 || Tool.isAfterLTime(time, leTime) == 1)){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " Цайны цаг");
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				}else {
					EmployeeRequest request = null;
					for(EmployeeRequest employeeRequest : employeeRequests){
						if(employeeRequest.getEmployeePkId().compareTo(emp.getPkId()) != 0) continue;
						timeb = new LTime();timeb.setHour(employeeRequest.getBeginTime());timeb.setMinute(employeeRequest.getBeginMinute());
						timee = new LTime();timee.setHour(employeeRequest.getEndTime());timee.setMinute(employeeRequest.getEndMinute());
						if((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)){
							request = employeeRequest;
						}
					}
					
					if(request != null){
						calendarItem.setName(Tool.toTimeOrMinuteString(request.getBeginTime()) + ":" + Tool.toTimeOrMinuteString(request.getBeginMinute()) + " - " + Tool.toTimeOrMinuteString(request.getEndTime()) + ":" + Tool.toTimeOrMinuteString(request.getEndMinute()) + " " + request.getCustomerLastname() + " " + request.getCustomerFirstname() + " (" + request.getDescription() + ") ");
						if(request.getMood() == 2 || request.getMood() == 4) 
							calendarItem.setClassCss("calendarItemSelect1"); 
						else 
							calendarItem.setClassCss("calendarItemSelect");
						calendarItem.setBeginHour(request.getBeginTime());
						calendarItem.setBeginMinute(request.getBeginMinute());
						calendarItem.setEndHour(request.getEndTime());
						calendarItem.setEndMinute(request.getEndMinute());
						calendarItem.setEmployeeRequestPkId(request.getPkId());
					}else {
						calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " цаг захиалах");
						calendarItem.setClassCss("calendarItem");
						calendarItem.setBeginHour(in.getHour());
						calendarItem.setBeginMinute(in.getMinute());
						calendarItem.setEndHour(time.getHour());
						calendarItem.setEndMinute(time.getMinute());
						calendarItem.setEmployeeRequestPkId(BigDecimal.ONE);
					}
				}
				calendarItemsAsd1.add(calendarItem);
			}
		}catch(Exception ex){
			
		}
	}
	
	public void setCalendarItemsAsd1(List<CalendarItem> calendarItemsAsd1) {
		this.calendarItemsAsd1 = calendarItemsAsd1;
	}
	
	public List<CalendarItem> getCalendarItemsAsd(BigDecimal pkId) {
		calendarItems = new ArrayList<CalendarItem>();
		EconomicCalendar economicCalendar =  getApplicationController().getCurrentEconomicCalendar();
		List<EmployeeRequest> employeeRequests = getEmployeeRequests();
		List<EconomicCalendarDtl> calendarDtls = getEconomicCalendarDtl();
		
		for(Employee emp : getEmployees()){
			if(pkId.compareTo(emp.getPkId()) != 0) continue;
			LTime mbTime = new LTime();mbTime.setHour(economicCalendar.getBeginHour());mbTime.setMinute(economicCalendar.getBeginMinute());
			LTime meTime = new LTime();meTime.setHour(economicCalendar.getEndHour());meTime.setMinute(economicCalendar.getEndMinute());
			LTime lbTime = new LTime();lbTime.setHour(economicCalendar.getLunchBeginHour());lbTime.setMinute(economicCalendar.getLunchBeginMinute());
			LTime leTime = new LTime();leTime.setHour(economicCalendar.getLunchEndHour());leTime.setMinute(economicCalendar.getLunchEndMinute());
			for(LTime in = mbTime; Tool.isAfterLTime(mbTime, meTime) == 1; in.addMinute(emp.getInspectionTime())){
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.setEmployeePkId(emp.getPkId());
				LTime time = new LTime();time.setHour(in.getHour());time.setMinute(in.getMinute());time.addMinute(emp.getInspectionTime());
				
				EconomicCalendarDtl calendarDtl = null;
				LTime timeb;
				LTime timee;
				for(EconomicCalendarDtl dtl : calendarDtls){
					timeb = new LTime();timeb.setHour(dtl.getCalendarHdr().getBeginTimeHour());timeb.setMinute(dtl.getCalendarHdr().getBeginTimeMinute());
					timee = new LTime();timee.setHour(dtl.getCalendarHdr().getEndTimeHour());timee.setMinute(dtl.getCalendarHdr().getEndTimeMinute());
					if(((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)) || (Tool.isAfterLTime(timeb, in) == 1 && Tool.isAfterLTime(time, timee) == 1) && dtl.getEmployee().getPkId().compareTo(emp.getPkId()) == 0)
						calendarDtl = dtl;
				}
				
				if(calendarDtl != null){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + calendarDtl.getCalendarHdr().getName());
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				}else if((Tool.isAfterLTime(lbTime, in) == 1 || Tool.isAfterLTime(lbTime, time) == 1) && (Tool.isAfterLTime(in, leTime) == 1 || Tool.isAfterLTime(time, leTime) == 1)){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " Цайны цаг");
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				}else {
					EmployeeRequest request = null;
					for(EmployeeRequest employeeRequest : employeeRequests){
						if(employeeRequest.getEmployeePkId().compareTo(emp.getPkId()) != 0) continue;
						timeb = new LTime();timeb.setHour(employeeRequest.getBeginTime());timeb.setMinute(employeeRequest.getBeginMinute());
						timee = new LTime();timee.setHour(employeeRequest.getEndTime());timee.setMinute(employeeRequest.getEndMinute());
						if((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)){
							request = employeeRequest;
						}
					}
					
					if(request != null){
						calendarItem.setName(Tool.toTimeOrMinuteString(request.getBeginTime()) + ":" + Tool.toTimeOrMinuteString(request.getBeginMinute()) + " - " + Tool.toTimeOrMinuteString(request.getEndTime()) + ":" + Tool.toTimeOrMinuteString(request.getEndMinute()) + " " + request.getCustomerLastname() + " " + request.getCustomerFirstname() + " (" + request.getDescription() + ") ");
						calendarItem.setClassCss("calendarItemSelect");
						calendarItem.setBeginHour(request.getBeginTime());
						calendarItem.setBeginMinute(request.getBeginMinute());
						calendarItem.setEndHour(request.getEndTime());
						calendarItem.setEndMinute(request.getEndMinute());
						calendarItem.setEmployeeRequestPkId(request.getPkId());
					}else {
						calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " цаг захиалах");
						calendarItem.setClassCss("calendarItem");
						calendarItem.setBeginHour(in.getHour());
						calendarItem.setBeginMinute(in.getMinute());
						calendarItem.setEndHour(time.getHour());
						calendarItem.setEndMinute(time.getMinute());
						calendarItem.setEmployeeRequestPkId(BigDecimal.ONE);
					}
				}
				calendarItems.add(calendarItem);
			}
		}
		return calendarItems;
	}
	
	@SuppressWarnings("deprecation")
	public int getCurrentYear() {
		if(currentYear == 0){
			currentYear = (new Date()).getYear() + 1900;
		}
		return currentYear;
	}
	
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}
	
	@SuppressWarnings("deprecation")
	public int getSelectedMonth() {
		if(selectedMonth == 0){
			selectedMonth = (new Date()).getMonth() + 1;
			this.selectedDay = (new Date()).getDate();
		}
		return selectedMonth;
	}
	
	public int getSelectedDay() {
		return selectedDay;
	}

	public void setSelectedDay(int selectedDay) {
		this.selectedDay = selectedDay;
	}
	
	public void setSelectedMonth(int selectedMonth) {
		this.selectedMonth = selectedMonth;
	}
	
	public List<EmployeeRequest> getEmployeeRequests(){
		Date date1 = new Date();
		Date date2 = new Date();
		date1.setYear(getCurrentYear() - 1900);
		date1.setMonth(getSelectedMonth() - 1);
		date1.setDate(getSelectedDay());
		date2.setYear(getCurrentYear() - 1900);
		date2.setMonth(getSelectedMonth() - 1);
		date2.setDate(getSelectedDay());
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(24);
		date2.setMinutes(59);
		date2.setSeconds(59);
		List<EmployeeRequest> employeeRequests = new ArrayList<EmployeeRequest>();
		try{
			employeeRequests = reservationLogic.getEmployeeRequestByDate(date1, date2, getUserSessionController().getLoggedInfo());
		}catch(Exception ex){
			
		}
		return employeeRequests;
	}
	
	@SuppressWarnings("deprecation")
	public List<EconomicCalendarDtl> getEconomicCalendarDtl(){
		Date date1 = new Date();
		Date date2 = new Date();
		date1.setYear(getCurrentYear() - 1900);
		date1.setMonth(getSelectedMonth() - 1);
		date1.setDate(getSelectedDay());
		date2.setYear(getCurrentYear() - 1900);
		date2.setMonth(getSelectedMonth() - 1);
		date2.setDate(getSelectedDay());
		date1.setHours(0);
		date1.setMinutes(0);
		date1.setSeconds(0);
		date2.setHours(24);
		date2.setMinutes(59);
		date2.setSeconds(59);
		List<EconomicCalendarDtl> calendarDtls = new ArrayList<EconomicCalendarDtl>();
		try{
			calendarDtls = logicTwo.getEconomicCalendarDtlByDate(date1, date2, getUserSessionController().getLoggedInfo(), getSelectedSubOrganization().getPkId(), getCurrentYear() - 1900, getSelectedMonth() - 1, getSelectedDay());
		}catch(Exception ex){
			
		}
		return calendarDtls;
	}
	
	public SubOrganization getSelectedSubOrganization() {
		if(selectedSubOrganization == null){
			try{
				List<SubOrganization> list = logicTwo.getSubOrganizations(getUserSessionController().getLoggedInfo());
				if(list != null && list.size() > 0)
					selectedSubOrganization = list.get(0);
			}catch(Exception ex){
				
			}
		}
		return selectedSubOrganization;
	}
	
	public void setSelectedSubOrganization(
			SubOrganization selectedSubOrganization) {
		this.employees = null;
		this.selectedEmployee = null;
		this.selectedSubOrganization = selectedSubOrganization;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public List<CalendarItem> getCalendarItems() {
		calendarItems = new ArrayList<CalendarItem>();
		EconomicCalendar economicCalendar =  getApplicationController().getCurrentEconomicCalendar();
		List<EmployeeRequest> employeeRequests = getEmployeeRequests();
		List<EconomicCalendarDtl> calendarDtls = getEconomicCalendarDtl();
		LTime mbTime = new LTime();mbTime.setHour(economicCalendar.getBeginHour());mbTime.setMinute(economicCalendar.getBeginMinute());
		LTime meTime = new LTime();meTime.setHour(economicCalendar.getEndHour());meTime.setMinute(economicCalendar.getEndMinute());
		LTime lbTime = new LTime();lbTime.setHour(economicCalendar.getLunchBeginHour());lbTime.setMinute(economicCalendar.getLunchBeginMinute());
		LTime leTime = new LTime();leTime.setHour(economicCalendar.getLunchEndHour());leTime.setMinute(economicCalendar.getLunchEndMinute());
		for(LTime in = mbTime; Tool.isAfterLTime(mbTime, meTime) == 1; in.addMinute(30)){
			for(Employee emp : getEmployees()){
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.setEmployeePkId(emp.getPkId());
				LTime time = new LTime();time.setHour(in.getHour());time.setMinute(in.getMinute());time.addMinute(30);
				
				EconomicCalendarDtl calendarDtl = null;
				LTime timeb;
				LTime timee;
				for(EconomicCalendarDtl dtl : calendarDtls){
					timeb = new LTime();timeb.setHour(dtl.getCalendarHdr().getBeginTimeHour());timeb.setMinute(dtl.getCalendarHdr().getBeginTimeMinute());
					timee = new LTime();timee.setHour(dtl.getCalendarHdr().getEndTimeHour());timee.setMinute(dtl.getCalendarHdr().getEndTimeMinute());
					if(((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)) || (Tool.isAfterLTime(timeb, in) == 1 && Tool.isAfterLTime(time, timee) == 1) && dtl.getEmployee().getPkId().compareTo(emp.getPkId()) == 0)
						calendarDtl = dtl;
				}
				
				if(calendarDtl != null){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + calendarDtl.getCalendarHdr().getName());
					calendarItem.setClassCss("calendarItemComplete");
				}else if((Tool.isAfterLTime(lbTime, in) == 1 || Tool.isAfterLTime(lbTime, time) == 1) && (Tool.isAfterLTime(in, leTime) == 1 || Tool.isAfterLTime(time, leTime) == 1)){
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " Цайны цаг");
					calendarItem.setClassCss("calendarItemComplete");
				}else {
					EmployeeRequest request = null;
					for(EmployeeRequest employeeRequest : employeeRequests){
						if(employeeRequest.getEmployeePkId().compareTo(emp.getPkId()) != 0) continue;
						timeb = new LTime();timeb.setHour(employeeRequest.getBeginTime());timeb.setMinute(employeeRequest.getBeginMinute());
						timee = new LTime();timee.setHour(employeeRequest.getEndTime());timee.setMinute(employeeRequest.getEndMinute());
						if((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1) && (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)){
							request = employeeRequest;
						}
					}
					
					if(request != null){
						calendarItem.setName(Tool.toTimeOrMinuteString(request.getBeginTime()) + ":" + Tool.toTimeOrMinuteString(request.getBeginMinute()) + " - " + Tool.toTimeOrMinuteString(request.getEndTime()) + ":" + Tool.toTimeOrMinuteString(request.getEndMinute()) + " " + request.getCustomerLastname() + " " + request.getCustomerFirstname() + " (" + request.getDescription() + ") ");
						calendarItem.setClassCss("calendarItemSelect");
						calendarItem.setBeginHour(request.getBeginTime());
						calendarItem.setBeginMinute(request.getBeginMinute());
						calendarItem.setEndHour(request.getEndTime());
						calendarItem.setEndMinute(request.getEndMinute());
						calendarItem.setEmployeeRequestPkId(request.getPkId());
					}else {
						calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute()) + " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":" + Tool.toTimeOrMinuteString(time.getMinute()) + " цаг захиалах");
						calendarItem.setClassCss("calendarItem");
						calendarItem.setBeginHour(in.getHour());
						calendarItem.setBeginMinute(in.getMinute());
						calendarItem.setEndHour(time.getHour());
						calendarItem.setEndMinute(time.getMinute());
						calendarItem.setEmployeeRequestPkId(BigDecimal.ONE);
					}
				}
				calendarItems.add(calendarItem);
			}
		}
		return calendarItems;
	}
	
	public void setCalendarItems(List<CalendarItem> calendarItems) {
		this.calendarItems = calendarItems;
	}

	public List<Customer> getListCustomer() {
		try{
			listCustomer = logicCustomer.getCustomers(getUserSessionController().getLoggedInfo().getOrganization().getPkId());
		}catch(Exception ex){
			
		}
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public Customer getSelectedCustomer() {
		if(getLogicOneController().isSelectCustomer()){
			selectedCustomer = getCustomerController().getCustomer();
		}else if(selectedCustomer == null){
			selectedCustomer = new Customer();
			selectedCustomer.setPkId(BigDecimal.ZERO);
		}
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public String getSelectedCustomerReg() {
		return selectedCustomerReg;
	}

	public void setSelectedCustomerReg(String selectedCustomerReg) {
		this.selectedCustomerReg = selectedCustomerReg;
	}
	
	public InspectionController getLogicOneController() {
		return logicOneController;
	}
	
	public void setLogicOneController(InspectionController logicOneController) {
		this.logicOneController = logicOneController;
	}
	
	public List<CashBusinessEntity> getCashHdrs() {
		if(cashHdrs == null) {
			cashHdrs = new ArrayList<CashBusinessEntity>();
			CashBusinessEntity businessEntity = new CashBusinessEntity();
			businessEntity.setEmployeeRequest(new EmployeeRequest());
			businessEntity.setInspection(new Inspection());
			businessEntity.setDtl(new InspectionDtl());
			businessEntity.setCustomer(new Customer());
			businessEntity.setTreatment(new Treatment());
			businessEntity.setEmployee(new Employee());
			businessEntity.setStatus(Tool.ADDED);
			cashHdrs.add(businessEntity);
		}
		return cashHdrs;
	}
	
	public void setCashHdrs(List<CashBusinessEntity> cashHdrs) {
		this.cashHdrs = cashHdrs;
	}

	public Customer getSelectedCashCustomer() {
		if(selectedCashCustomer == null) selectedCashCustomer = new Customer();
		return selectedCashCustomer;
	}
	
	public Payment getPayment() {
		if(payment == null) payment = new Payment();
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public List<Integer> getDiscountPercents(){
		List<Integer> ret = new ArrayList<Integer>();
		for(int i = 1; i <= 100; i++){
			ret.add(i);		}
		return ret;
	}

	public Date getBeginDate() {
		if(beginDate == null) beginDate = new Date();
		beginDate.setHours(0);
		beginDate.setMinutes(0); 
		beginDate.setSeconds(0);
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		if(endDate == null) endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String selectedCashCustomer(BigDecimal pkId){
		try{
			//setSelectedCustomer(logicTwo.getByPkId(Customer.class, customerPkId));
			//setSelectedCashCustomer(logicTwo.getByPkId(Customer.class, customerPkId));
			 selectedCustomer = new Customer();
			 selectedCustomer.setRegNumber(logicTwo.getByPkId(Customer.class, pkId).getRegNumber());
			//fillCustomer1();
			setCalculateCashier(true);
		}catch(Exception ex){
			
		}

		return "cash_register";
	}
	
	public void paymentHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:requestHistoryDialog");
		context.execute("PF('paymentHistory').show();");
	}
	
	public void loanHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		if(getListLoanEmployee().size() < 1){
			getUserSessionController().showMessage(32);
			return;
		}
		try{
			
		}catch(Exception ex){
			
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('paymentHistory').show();");
	}
	
	public void requestHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		try{
			listEmployeeRequest = reservationLogic.getEmployeeRequests(selectedCashCustomer.getPkId());
		}catch(Exception ex){
			
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:requestHistoryDialog");
		context.execute("PF('requestHistory').show();");
	}
	
	public void inspectionHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		try{
			setListEmployeeRequests(reservationLogic.getEmployeeInspectionHistory(selectedCashCustomer, getUserSessionController().getLoggedInfo(), getBeginFilterBeginDate(), getBeginFilterEndDate()));
			inspectionHistories = logicTwo.getInspectionHistories(selectedCashCustomer.getPkId());
		}catch(Exception ex){
			
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionHistoryDialog");
		context.execute("PF('inspectionHistory').show();");
	}

	public PaymentHistory getHistory() {
		if(history == null) history = new PaymentHistory();
		return history;
	}

	public void setHistory(PaymentHistory history) {
		this.history = history;
	}

	public List<EmployeeRequest> getListEmployeeRequest() {
		if(listEmployeeRequest == null) return new ArrayList<EmployeeRequest>();
		return listEmployeeRequest;
	}

	public void setListEmployeeRequest(List<EmployeeRequest> listEmployeeRequest) {
		this.listEmployeeRequest = listEmployeeRequest;
	}
	
	public List<InspectionHistory> getInspectionHistories() {
		if(inspectionHistories == null) return new ArrayList<InspectionHistory>();
		return inspectionHistories;
	}
	
	public void setInspectionHistories(List<InspectionHistory> inspectionHistories) {
		this.inspectionHistories = inspectionHistories;
	}

	public void setListXray(List<Xray> listXray) {
		this.listXray = listXray;
	}
	
	public List<Xray> getXrays() {
		if(xrays == null) {
			xrays = new ArrayList<Xray>();
		}
		return xrays;
	}
	
	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}
	
	public List<XrayType> getListXrayType() {
		try{
			if(listXrayType == null){
				listXrayType = xrayLogic.getXrayTypes(getUserSessionController().getLoggedInfo());
			}
			if(selectedXrayType == null && listXrayType.size() > 0) setSelectedXrayType(listXrayType.get(0));
		}catch(Exception ex){
			
		}
		return listXrayType;
	}
	
	public void setListXrayType(List<XrayType> listXrayType) {
		this.listXrayType = listXrayType;
	}
	
	public List<Xray> getListFilteredXray() {
		if(listFilteredXray == null) listFilteredXray = new ArrayList<Xray>();
		return listFilteredXray;
	}
	
	public void setListFilteredXray(List<Xray> listFilteredXray) {
		this.listFilteredXray = listFilteredXray;
	}

	public Xray getSelectedXray() {
		return selectedXray;
	}

	public void setSelectedXray(Xray selectedXray) {
		this.selectedXray = selectedXray;
	}
	
	public XrayType getSelectedXrayType() {
		return selectedXrayType;
	}
	
	public void setSelectedXrayType(XrayType selectedXrayType) {
		try{
			listFilteredXray = xrayLogic.getXrayByDianoseTypePkId(selectedXrayType.getPkId());
		}catch(Exception ex){
			
		}
		this.selectedXrayType = selectedXrayType;
	}
	
	public int getDeleteIndexOfList() {
		return deleteIndexOfList;
	}
	
	public void setDeleteIndexOfList(int deleteIndexOfList) {
		this.deleteIndexOfList = deleteIndexOfList;
	}
	
	public String deleteIndexOfList(){
		getXrays().remove(deleteIndexOfList);
		return "cash_register";
	}
	
	public List<EmployeeRequest> getListEmployeeRequests() {
		if(listEmployeeRequests == null) listEmployeeRequests = new ArrayList<EmployeeRequest>();
		return listEmployeeRequests;
	}
	
	public void setListEmployeeRequests(
			List<EmployeeRequest> listEmployeeRequests) {
		this.listEmployeeRequests = listEmployeeRequests;
	}
	
	public BigDecimal getSumAmountInspectionHistory(){
		BigDecimal bigDecimal = BigDecimal.ZERO;
		for(EmployeeRequest item : listEmployeeRequests){
			bigDecimal = bigDecimal.add(item.getSumAmount());
		}
		return bigDecimal;
	}
	
	public Date getBeginFilterBeginDate() {
		if(beginFilterBeginDate == null) beginFilterBeginDate = new Date();
		beginFilterBeginDate.setHours(1);
		beginFilterBeginDate.setMinutes(0);
		beginFilterBeginDate.setSeconds(1);
		return beginFilterBeginDate;
	}
	
	public void setBeginFilterBeginDate(Date beginFilterBeginDate) {
		this.beginFilterBeginDate = beginFilterBeginDate;
	}
	
	public Date getBeginFilterEndDate() {
		if(beginFilterEndDate == null) beginFilterEndDate = new Date();
		beginFilterEndDate.setHours(23);
		beginFilterEndDate.setMinutes(59);
		beginFilterEndDate.setSeconds(59);
		return beginFilterEndDate;
	}
	
	public void setBeginFilterEndDate(Date beginFilterEndDate) {
		this.beginFilterEndDate = beginFilterEndDate;
	}

	public List<Employee> getListCashEmployee() {
		return listCashEmployee;
	}

	public void setListCashEmployee(List<Employee> listCashEmployee) {
//		for (Employee employee : listCashEmployee) {
//			getCashPayment().setAmount3(getCashPayment().getAmount3().add(employee.getPrice()));
//		}
		this.listCashEmployee = listCashEmployee;
	}

	public List<Xray> getListCashXray() {
		return listCashXray;
	}

	public void setListCashXray(List<Xray> listCashXray) {
		this.listCashXray = listCashXray;
	}

	public Customer getCustomerCash() {
		return customerCash;
	}

	public void setCustomerCash(Customer customerCash) {
		this.customerCash = customerCash;
	}

	public Payment getCashPayment() {
		if(cashPayment == null) cashPayment = new Payment();
		return cashPayment;
	}

	public void setCashPayment(Payment cashPayment) {
		this.cashPayment = cashPayment;
	}

	public List<PaymentHistory> getListLoanEmployee() {
		if(listLoanEmployee == null) listLoanEmployee = new ArrayList<PaymentHistory>();
		return listLoanEmployee;
	}

	public void setListLoanEmployee(List<PaymentHistory> listLoanEmployee) {
		this.listLoanEmployee = listLoanEmployee;
	}

	public String getHereggui() {
		return hereggui;
	}

	public void setHereggui(String hereggui) {
		this.hereggui = hereggui;
	}
	
	public List<Employee> getListEmployee() {
		return listEmployee;
	}
	
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	public UploadedFile getFile() {
		return file;
	}
	
	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	public void upload(){
		System.out.println("asdas");
	}
	
	public boolean isActionProgress() {
		return actionProgress;
	}
	
	public void setActionProgress(boolean actionProgress) {
		this.actionProgress = actionProgress;
	}

	public boolean isCalculateCashier() {
		if(calculateCashier){
			calculateCashier = false;
			return true;
		}
		return calculateCashier;
	}

	public void setCalculateCashier(boolean calculateCashier) {
		this.calculateCashier = calculateCashier;
	}
	
	public String getOldPassword() {
		return oldPassword;
	}
	
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPasswordConfirm() {
		return newPasswordConfirm;
	}
	
	public void setNewPasswordConfirm(String newPasswordConfirm) {
		this.newPasswordConfirm = newPasswordConfirm;
	}
	
	public String changePassword(){
		try{
			
			if(!getNewPassword().equals(getNewPasswordConfirm())){
				getUserSessionController().showMessage(35);
				return "";
			}
			
			if(!getUserSessionController().getLoggedInfo().getUser().getPassword().equals(Tool.MD5(getOldPassword()))){
				getUserSessionController().showMessage(34);
				return "";
			}
			
			logicTwo.changePassword(getUserSessionController().getLoggedInfo(), getNewPassword());
			
			getUserSessionController().logout();
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('prifileDialog').hide();");
		}catch(Exception ex){
			
		}
		
		return "login";
	}

	public ReportFilter getFilter() {
		if(filter == null) filter = new ReportFilter();
		return filter;
	}

	public void setFilter(ReportFilter filter) {
		this.filter = filter;
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public int getIsCalendar() {
		return isCalendar;
	}

	public void setIsCalendar(int isCalendar) {
		this.isCalendar = isCalendar;
	}
	
	public EconomicCalendar getCurrentEconomicCalendar() {
		if(currentEconomicCalendar == null) {
			try{
				currentEconomicCalendar = logicTwo.getEconomicCalendar();
			}catch(Exception ex){
				
			}
		}
		return currentEconomicCalendar;
	}
	
	public void setCurrentEconomicCalendar(
			EconomicCalendar currentEconomicCalendar) {
		this.currentEconomicCalendar = currentEconomicCalendar;
	}
	
	public List<PaymentHistory> getPaymentHistories() {
		if(paymentHistories == null) paymentHistories = new ArrayList<PaymentHistory>();
		return paymentHistories;
	}
	
	public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}
	
	public void reGenerateCustomerId(){
		RequestContext context = RequestContext.getCurrentInstance();
		try{
			logicCustomer.reGenerateCardNumber();
			context.execute("alert('Амжилттай');");
		}catch(Exception ex){
			context.execute("alert('Алдаа гарлаа');");
		}
	}
	
	public CustomerController getCustomerController() {
		return customerController;
	}
	public void setCustomerController(CustomerController customerController) {
		this.customerController = customerController;
	}
	
	public Employee getSelectedEmployee() {
		if(selectedEmployee == null){
			try{
				List<Employee> employees = logicRequest.getEmployeeBySubOrganization(getSelectedSubOrganization().getPkId());
				if(employees != null && employees.size() > 0)
					selectedEmployee = employees.get(0);
			}catch(Exception ex){
				
			}
		}
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}
	
	public boolean isReportCashier() {
		if(isReportCashier){
			isReportCashier = false;
			return true;
		}
		return isReportCashier;
	}

	public void setReportCashier(boolean isReportCashier) {
		this.isReportCashier = isReportCashier;
	}
	
	public String getEmployeeCalendarStr() {
		employeeCalendarStr = "";
		for(hospital.businessentity.Calendar calendar : getEmplouyeeCalendars()){
			employeeCalendarStr += "{ "
				+ "title : '" + calendar.getTitle() + "', "
				+ "start : new Date("+calendar.getBy()+", "+calendar.getbM()+", "+calendar.getBd()+", "+calendar.getBh()+", "+calendar.getBm()+"), "
				+ "end : new Date("+calendar.getEy()+", "+calendar.geteM()+", "+calendar.getEd()+", "+calendar.getEh()+", "+calendar.getEm()+"), "
				+ "allDay : false, "
				+ "backgroundColor : \""+calendar.getBackgroundColor()+"\", "
				+ "borderColor : \""+calendar.getBorderColor()+"\" }, ";
			//System.out.println("new Date("+calendar.getBy()+", "+calendar.getbM()+", "+calendar.getBd()+", "+calendar.getBh()+", "+calendar.getBm()+") --- " +"new Date("+calendar.getEy()+", "+calendar.geteM()+", "+calendar.getEd()+", "+calendar.getEh()+", "+calendar.getEm()+")");
		}
		return employeeCalendarStr;
	}
	
	public void setEmployeeCalendarStr(String employeeCalendarStr) {
		this.employeeCalendarStr = employeeCalendarStr;
	}
	
	public void calculateEmployeeCalendar() {
		try{
			emplouyeeCalendars = new ArrayList<hospital.businessentity.Calendar>();
			List<String> calendarHdrsStr = new ArrayList<String>();
			List<EconomicCalendarHdr> calendarHdrs = logicTwo.getEmployeeEconomicCalendarHdr(getUserSessionController().getLoggedInfo(), getSubOrganizationPkId());
			CustomHashMap customHashMap = new CustomHashMap();
			for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs) {
				if(calendarHdrsStr.contains(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate())) {
					List<EconomicCalendarHdr> value = (List<EconomicCalendarHdr>) customHashMap.get(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate());
					value.add(economicCalendarHdr);
					customHashMap.put(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate(), value);
				}else {
					List<EconomicCalendarHdr> value = new ArrayList<EconomicCalendarHdr>();
					value.add(economicCalendarHdr);
					customHashMap.put(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate(), value);
					calendarHdrsStr.add(economicCalendarHdr.getBeginDate().getYear()+"-"+economicCalendarHdr.getBeginDate().getMonth()+"-"+economicCalendarHdr.getBeginDate().getDate());
				}
			}
			EconomicCalendar economicCalendar = logicTwo.getEconomicCalendar(getUserSessionController().getLoggedInfo());
			int year = (new Date()).getYear() + 1900;
			for(int mon = 0; mon < 12; mon++){
				for(int day = 1; day <= daysOfMonth(year, mon + 1); day++){
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, year);
					calendar.set(Calendar.MONTH, mon);
					calendar.set(Calendar.DATE, day);
					int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
					
					hospital.businessentity.Calendar cal = new hospital.businessentity.Calendar();
					cal.setTitle("Ажлийн цаг");
					cal.setBy(year);
					cal.setbM(mon);
					cal.setBd(day);
					cal.setEy(year);
					cal.seteM(mon);
					cal.setEd(day);
					cal.setBackgroundColor(Tool.WorkStatusBgColor);
					cal.setBorderColor(Tool.WorkStatusBgColor);
					
					if(dayOfWeek == 7 && economicCalendar.getIsSaturday() == 1){
						cal.setBh(economicCalendar.getSaturDayBeginHour());
						cal.setBm(economicCalendar.getSaturDayBeginMinute());
						cal.setEh(economicCalendar.getSaturDayEndHour());
						cal.setEm(economicCalendar.getSaturDayEndMinute());
						emplouyeeCalendars.add(cal);
					}else if(dayOfWeek == 1 && economicCalendar.getIsSunday() == 1){
						cal.setBh(economicCalendar.getSunDayBeginHour());
						cal.setBm(economicCalendar.getSunDayBeginMinute());
						cal.setEh(economicCalendar.getSunDayEndHour());
						cal.setEm(economicCalendar.getSunDayEndMinute());
						emplouyeeCalendars.add(cal);
					}else if(dayOfWeek >= 2 && dayOfWeek <= 6){
						cal.setBh(economicCalendar.getBeginHour());
						cal.setBm(economicCalendar.getBeginMinute());
						cal.setEh(economicCalendar.getLunchBeginHour());
						cal.setEm(economicCalendar.getLunchBeginMinute());
						emplouyeeCalendars.add(cal);
						
						cal = new hospital.businessentity.Calendar();
						cal.setTitle("Ажлийн цаг");
						cal.setBy(year);
						cal.setbM(mon);
						cal.setBd(day);
						cal.setEy(year);
						cal.seteM(mon);
						cal.setEd(day);
						cal.setBackgroundColor(Tool.WorkStatusBgColor);
						cal.setBorderColor(Tool.WorkStatusBgColor);
						cal.setBh(economicCalendar.getLunchEndHour());
						cal.setBm(economicCalendar.getLunchEndMinute());
						cal.setEh(economicCalendar.getEndHour());
						cal.setEm(economicCalendar.getEndMinute());
						emplouyeeCalendars.add(cal);
					}
					
					if(!calendarHdrsStr.contains(calendar.getTime().getYear()+"-"+calendar.getTime().getMonth()+"-"+calendar.getTime().getDate())) continue;
					//if(calendarHdrs != null && calendarHdrs.size() > 0){
						List<EconomicCalendarHdr> calendarHdrs2 = (List<EconomicCalendarHdr>) customHashMap.get(calendar.getTime().getYear()+"-"+calendar.getTime().getMonth()+"-"+calendar.getTime().getDate());
						for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs2) {
							if(economicCalendarHdr.getEmployees() != null && economicCalendarHdr.getEmployees().size() > 0){
								for (Employee employe : economicCalendarHdr.getEmployees()) {
									cal = new hospital.businessentity.Calendar();
									cal.setTitle(employe.getFirstName() + " - " + economicCalendarHdr.getName());
									cal.setBy(year);
									cal.setbM(mon);
									cal.setBd(day);
									cal.setEy(year);
									cal.seteM(mon);
									cal.setEd(day);
									cal.setBackgroundColor(Tool.LunchStatusBgColor);
									cal.setBorderColor(Tool.LunchStatusBgColor);
									cal.setBh(economicCalendarHdr.getBeginTimeHour());
									cal.setBm(economicCalendarHdr.getBeginTimeMinute());
									cal.setEh(economicCalendarHdr.getEndTimeHour());
									cal.setEm(economicCalendarHdr.getEndTimeMinute());
									emplouyeeCalendars.add(cal);
								}
							}else {
								cal = new hospital.businessentity.Calendar();
								cal.setTitle((economicCalendarHdr.getSubOrganizationName() == null ? "" : economicCalendarHdr.getSubOrganizationName() + " - ") + economicCalendarHdr.getName());
								cal.setBy(year);
								cal.setbM(mon);
								cal.setBd(day);
								cal.setEy(year);
								cal.seteM(mon);
								cal.setEd(day);
								cal.setBackgroundColor(Tool.LunchStatusBgColor);
								cal.setBorderColor(Tool.LunchStatusBgColor);
								cal.setBh(economicCalendarHdr.getBeginTimeHour());
								cal.setBm(economicCalendarHdr.getBeginTimeMinute());
								cal.setEh(economicCalendarHdr.getEndTimeHour());
								cal.setEm(economicCalendarHdr.getEndTimeMinute());
								emplouyeeCalendars.add(cal);
							}
						}
					//}
				}
			}
			
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public List<hospital.businessentity.Calendar> getEmplouyeeCalendars() {
		if(emplouyeeCalendars == null || emplouyeeCalendars.size() < 1){
			calculateEmployeeCalendar();
		}
		return emplouyeeCalendars;
	}
	
	public void setEmplouyeeCalendars(
			List<hospital.businessentity.Calendar> emplouyeeCalendars) {
		this.emplouyeeCalendars = emplouyeeCalendars;
	}
	
	public List<EconomicCalendarHdr> getCalendarHdrs() {
		try {
			calendarHdrs = logicTwo.getEconomicCalendarHdrs(getUserSessionController().getLoggedInfo().getEmployee().getPkId());
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return calendarHdrs;
	}
	
	public void setCalendarHdrs(List<EconomicCalendarHdr> calendarHdrs) {
		this.calendarHdrs = calendarHdrs;
	}
	
	public Date getMinDate() {
		if(minDate == null) {
			minDate = new Date();
			minDate.setHours(1);
			minDate.setMinutes(0);
			minDate.setSeconds(0);
		}
		return minDate;
	}
	
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	
	public void testSOAP(){
		SOAPClientSAAJ clientSAAJ = new SOAPClientSAAJ();
		clientSAAJ.exam();
	}

	public boolean isTimeChoose() {
		return timeChoose;
	}

	public void setTimeChoose(boolean timeChoose) {
		this.timeChoose = timeChoose;
	}

	public boolean isTimeAllDay() {
		return timeAllDay;
	}

	public void setTimeAllDay(boolean timeAllDay) {
		this.timeAllDay = timeAllDay;
	}
	
	public void timeChoose(){
		timeAllDay = !timeChoose;
		timeCheck();
	}
	
	public void timeAllDay(){
		timeChoose = !timeAllDay;
		timeCheck();
	}
	
	public void timeCheck(){
		if (timeChoose)
			RequestContext.getCurrentInstance().update("form:timeChoser");
		else if (timeAllDay){
			RequestContext.getCurrentInstance().update("form:timeChoser");
		}
			
	}
}

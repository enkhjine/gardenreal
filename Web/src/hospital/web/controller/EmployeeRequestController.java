package hospital.web.controller;

import hospital.businessentity.CalendarItem;
import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LTime;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.ILogicCashierLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicPaymentLocal;
import hospital.businesslogic.interfaces.ILogicRequestLocal;
import hospital.businesslogic.interfaces.ILogicReservationLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Aimag;
import hospital.entity.Customer;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.Soum;
import hospital.entity.Xray;
import mondrian.rolap.BitKey.Big;
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

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "requestController")
public class EmployeeRequestController implements Serializable {

	private static final long serialVersionUID = -5167315983167106056L;

	// Region - Logic
	@EJB(beanName = "LogicRequest")
	ILogicRequestLocal logicRequest;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicReservation")
	ILogicReservationLocal reservationLogic;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicPayment")
	ILogicPaymentLocal logicPayment;

	@EJB(beanName = "LogicCashier")
	ILogicCashierLocal logicCashier;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal xrayLogic;

	// EndRegion

	// Region - Controller

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{inspectionController}")
	private InspectionController inspectionController;

	@ManagedProperty(value = "#{customerController}")
	private CustomerController customerController;

	@ManagedProperty(value = "#{cashController}")
	private CashController cashController;

	// EndRegion

	// Region - Propertys

	private int selectedMonth;
	private int selectedDay;
	private int currentYear;
	private int currentYearIndex;
	private List<Integer> months;
	private List<Integer> days;
	private String customerRequestTitle;
	private List<Employee> employees;
	private SubOrganization selectedSubOrganization;
	private Employee selectedEmployee;
	private List<CalendarItem> employeeRequestCalendarItems;
	private List<CalendarItem> customerRequestCalendarItems;
	private List<SubOrganization> subOrganizations;
	private Customer selectedCustomer;
	private String selectedCustomerReg;
	private Customer customerCash;
	private EmployeeRequest employeeRequest;
	private Customer selectedCashCustomer;
	private List<CashBusinessEntity> cashHdrs;
	private List<PaymentHistory> listLoanEmployee;
	private Payment payment;
	private List<Employee> listEmployee;
	private List<Xray> xrays;
	private String selectedSubOrganizationStr;
	private EconomicCalendarHdr economicCalendarHdr;
	private SubOrganization subOrganization;
	private List<Employee> filteredEmployees;
	private boolean isReportCashier;
	private List<Employee> listCashEmployee;
	private Date orderDate;
	private List<Aimag> aimags;
	private List<Soum> sums;
	private Customer selCustomer;
	private List<SubOrganization> listSubOrganization;
	private List<CustomerRequest> crs;
	private List<CustomerRequest> customerRequests;
	private Date beginDate;
	private Date endDate;
	private Date selectedDate;
	private BigDecimal selectEmployeeRequestCustomerPkId;
	private String bgcolors;
	private List<EmployeeRequest> listemployeeRequest;
	// EndRegion

	private Customer sCustomer;

	// Region - Function

	public void fillRequests(BigDecimal employeePkId) {
		try {
			getCrs().clear();
			Date q = new Date();
			q.setDate(getSelectedDay());
			q.setMonth(getSelectedMonth() - 1);
			q.setYear(getCurrentYear() - 1900);
			crs = logicRequest.getEmployeeRequests(userSessionController.getLoggedInfo(), employeePkId, q);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:rdtl");
			context.update("form:doctorInspection");

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void removeRequest(CustomerRequest cr) {
		setSelectedCustomer(cr.getCustomer());
		setEmployeeRequest(cr.getEmployeeRequest());
		employeeRequest.setStatus(Tool.DELETE);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:delete");
	}

	public void refreshCustomerRequests() {
		RequestContext context = RequestContext.getCurrentInstance();
		getCustomerRequests().clear();
		getBeginDate();

		try {
			if (selectedCustomer != null)
				customerRequests = logicRequest.getCustomerRequests(selectedCustomer.getPkId());
			long failed = 0;
			if (customerRequests.size() >= 1) {
				for (CustomerRequest cr : customerRequests) {
					if (cr.getEmployeeRequest().getHasPayment() == 1)
						failed++;
				}
			}
			selectedCustomer.setRequestCount(customerRequests.size());
			selectedCustomer.setUnreliableCount(failed);
			selectedCustomer.setExecutoryCount(logicRequest.customerLoanAmount(selectedCustomer.getPkId()));

			this.selectedCashCustomer = selectedCustomer;
			context.update("form:daa1");
			context.update("form:daa2");
			context.update("form:daa3");
			context.update("form:crss");
			System.out.println("===================" + customerRequests.size());

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update(":form:crss");
	}

	@SuppressWarnings("deprecation")
	public void newEmployeeRequest(BigDecimal employeePkId) {
		try {
			employeeRequest = new EmployeeRequest();
			Date dte = new Date();
			dte.setYear(getCurrentYear() - 1900);
			dte.setMonth(getSelectedMonth() - 1);
			dte.setDate(getSelectedDay());
			employeeRequest.setBeginDate(dte);
			employeeRequest.setEndDate(dte);
			employeeRequest.setCustomerPkId(getSelectedCustomer().getPkId());
			employeeRequest.setEmployeePkId(employeePkId);
			// subotnik
			employeeRequest.setBeginTime(1);
			employeeRequest.setBeginMinute(1);
			employeeRequest.setEndTime(1);
			employeeRequest.setEndMinute(1);
			employeeRequest.setStatus(Tool.ADDED);
			employeeRequest.setId(infoLogic.getGeneratedPkId());
			employeeRequest.setGuest(1);
			employeeRequest.setArrivedDate(dte);
			employeeRequest.getArrivedDate().setHours(employeeRequest.getArrivedDate().getHours() + 1);
			selectedEmployee = reservationLogic.getEmployee(employeePkId, getSelectedCashCustomer().getPkId());

			if (BigDecimal.ZERO.compareTo(getSelectedCustomer().getPkId()) == 0) {
				// DAVAADORJ
				// RequestContext context = RequestContext.getCurrentInstance();
				// getCustomerController().newCustomer();
				// context.update("form:newCustomer");
				// context.execute("PF('newCustomer').show();");
				getUserSessionController().showMessage(28);
				return;
			}

			if (selectedEmployee == null) {
				getUserSessionController().showMessage(28);
				return;
			}

			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:register");
			context.execute("PF('register').show();");
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void employees() {
		try {
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
			employees = logicRequest.getEmployeeBySubOrganization(getSelectedSubOrganization().getPkId(), date1, date2);
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void guestUpdate(EmployeeRequest employeeRequest) {
		try {
			System.out.println(employeeRequest.getBeginDate());
			RequestContext context = RequestContext.getCurrentInstance();
			employeeRequest.setGuest(employeeRequest.getGuest() == 1 ? 0 : 1);
			employeeRequest.setArrivedDate(employeeRequest.getGuest() == 1 ? new Date() : null);
			employeeRequest.getArrivedDate().setHours(employeeRequest.getArrivedDate().getHours() + 1);
			logicRequest.setGuestUpdate(employeeRequest);
			fillRequests(employeeRequest.getEmployeePkId());

			context.update("form:doctorInspection");
			if (employeeRequest.getBgColor().equals("red")) {
				System.out.println("color: " + employeeRequest.getBgColor());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String saveEmployeeRequest() {
		try {
			if (employeeRequest.getMood() == 3) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("PF('register').hide();");
				context.execute("PF('delete').hide();");
				userSessionController.showMessage(89);
			}

			// EmployeeRequest request =
			// reservationLogic.getDupplicateEmployeeRequestByDate(employeeRequest);

			// if(request != null &&
			// Tool.ADDED.equals(employeeRequest.getStatus())){
			// getUserSessionController().showErrorMessage("Тухайн үйлчлүүлэгч
			// "+request.getEmployee().getFirstName()+" эмчид
			// "+getCurrentYear()+"/"+getSelectedMonth()+"/"+getSelectedDay()+"
			// өдөр "+request.getBeginTime()+" цагаас цаг захайлсан байна. ");
			// return getUserSessionController().getUrl("customerRequest");
			// }

			BigDecimal employeeRequestPkId = reservationLogic.saveEmployeeRequest(employeeRequest,
					getUserSessionController().getLoggedInfo());
			if (employeeRequest.isPaidRequestAmount()) {
				getCashController()
						.setPaymentPkId(logicCashier.getPaymentPkIdByEmployeeRequestPkId(employeeRequestPkId));
				isReportCashier = true;
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		employeeRequest.setDescription("");
		return getUserSessionController().getUrl("customerRequest");
	}

	@SuppressWarnings("deprecation")
	public String newEmployeeRequest(java.lang.Integer beginHour, java.lang.Integer beginMinute,
			java.lang.Integer endHour, java.lang.Integer endMinute, BigDecimal employeePkId,
			BigDecimal employeeRequestPkId) {
		String ret = "";
		RequestContext context = RequestContext.getCurrentInstance();
		if (BigDecimal.ONE.compareTo(employeeRequestPkId) == 0) {
			try {
				Date beginDate = new Date();
				Date endDate = new Date();
				beginDate.setYear(getCurrentYear() - 1900);
				beginDate.setMonth(getSelectedMonth() - 1);
				beginDate.setDate(getSelectedDay());
				endDate.setYear(getCurrentYear() - 1900);
				endDate.setMonth(getSelectedMonth() - 1);
				endDate.setDate(getSelectedDay());
				employeeRequest = new EmployeeRequest();
				employeeRequest.setCustomerPkId(getSelectedCustomer().getPkId());
				employeeRequest.setEmployeePkId(employeePkId);
				// subotnik
				employeeRequest.setBeginDate(beginDate);
				employeeRequest.setBeginTime(beginHour);
				employeeRequest.setBeginMinute(beginMinute);
				employeeRequest.setEndDate(endDate);
				employeeRequest.setEndTime(endHour);
				employeeRequest.setEndMinute(endMinute);
				employeeRequest.setStatus(Tool.ADDED);
				employeeRequest.setPayment(true);

				if (reservationLogic.getDuplicateRequest(employeeRequest.getBeginDate(), employeeRequest.getEndDate(),
						getSelectedCustomer().getPkId())) {
					getUserSessionController().showMessage(33);
				} else {
					employeeRequest.setId(infoLogic.getGeneratedPkId());
					// //selectedEmployee = logicTwo.getByPkId(Employee.class,
					// employeePkId);
					selectedEmployee = reservationLogic.getEmployee(employeePkId, getSelectedCashCustomer().getPkId());

					if (BigDecimal.ZERO.compareTo(getSelectedCustomer().getPkId()) == 0) {
						getUserSessionController().showMessage(28);
						return ret;
					}

					if (selectedEmployee == null) {
						getUserSessionController().showMessage(28);
						return ret;
					}
					context.update("form:register");
					context.execute("PF('register').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		} else if (BigDecimal.ZERO.compareTo(employeeRequestPkId) == 0) {

		} else {
			try {
				employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, employeeRequestPkId);
				if (employeeRequest != null) {
					if (employeeRequest.getMood() == 2 || employeeRequest.getMood() == 4) {
						// getUserSessionController().showMessage(30);
						getCashController().selectedEmployeeRequestSet(employeeRequest.getPkId());
						context.execute("newTab();");
					} else {
						employeeRequest.setStatus(Tool.DELETE);
						selectedEmployee = logicTwo.getByPkId(Employee.class, employeePkId);
						Customer cr = logicTwo.getByPkId(Customer.class, employeeRequest.getCustomerPkId());
						employeeRequest.setCustomerFirstname(cr.getFirstName());
						context.update("form:delete");
						context.execute("PF('delete').show();");
					}
				} else {
					EmployeeRequestHistory employeeRequestHistory = logicTwo.getByPkId(EmployeeRequestHistory.class,
							employeeRequestPkId);
					if (employeeRequestHistory.getMood() == 2) {
						getCashController().setPaymentPkId(employeeRequestHistory.getPaymentPkId());
						getCashController().setReportCashier(true);
						ret = "customerRequest";
					}
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return ret;
	}

	@SuppressWarnings("deprecation")
	public void newEmployeeRequest(java.lang.Integer beginHour, java.lang.Integer beginMinute,
			java.lang.Integer endHour, java.lang.Integer endMinute, BigDecimal employeePkId,
			BigDecimal employeeRequestPkId, Customer selectedCustomer, String description) {
		RequestContext context = RequestContext.getCurrentInstance();
		setSelectedCustomer(selectedCustomer);
		if (BigDecimal.ONE.compareTo(employeeRequestPkId) == 0) {
			try {
				Date beginDate = new Date();
				Date endDate = new Date();
				beginDate.setYear(getCurrentYear() - 1900);
				beginDate.setMonth(getSelectedMonth() - 1);
				beginDate.setDate(getSelectedDay());
				endDate.setYear(getCurrentYear() - 1900);
				endDate.setMonth(getSelectedMonth() - 1);
				endDate.setDate(getSelectedDay());
				employeeRequest = new EmployeeRequest();
				employeeRequest.setCustomerPkId(getSelectedCustomer().getPkId());
				employeeRequest.setEmployeePkId(employeePkId);
				employeeRequest.setBeginDate(beginDate);
				employeeRequest.setBeginTime(beginHour);
				employeeRequest.setBeginMinute(beginMinute);
				employeeRequest.setEndDate(endDate);
				employeeRequest.setEndTime(endHour);
				employeeRequest.setEndMinute(endMinute);
				employeeRequest.setStatus(Tool.ADDED);
				employeeRequest.setPayment(true);
				employeeRequest.setDescription("Давтан үзлэг:" + description);
				employeeRequest.setMood(3);

				if (reservationLogic.getDuplicateRequest(employeeRequest.getBeginDate(), employeeRequest.getEndDate(),
						getSelectedCustomer().getPkId())) {
					getUserSessionController().showMessage(33);
				} else {
					employeeRequest.setId(infoLogic.getGeneratedPkId());
					selectedEmployee = logicTwo.getByPkId(Employee.class, employeePkId);

					if (BigDecimal.ZERO.compareTo(getSelectedCustomer().getPkId()) == 0) {
						getUserSessionController().showMessage(28);
						return;
					}

					if (selectedEmployee == null) {
						getUserSessionController().showMessage(28);
						return;
					}
					context.update("form:register");
					context.execute("PF('register').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		} else if (BigDecimal.ZERO.compareTo(employeeRequestPkId) == 0) {

		} else {
			try {
				employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, employeeRequestPkId);
				if (employeeRequest.getMood() == 2 || employeeRequest.getMood() == 4) {
					getUserSessionController().showMessage(30);
				} else {
					employeeRequest.setStatus(Tool.DELETE);
					selectedEmployee = logicTwo.getByPkId(Employee.class, employeePkId);
					context.update("form:delete");
					context.execute("PF('delete').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void newEmployeeRequest1(java.lang.Integer beginHour, java.lang.Integer beginMinute,
			java.lang.Integer endHour, java.lang.Integer endMinute, BigDecimal employeePkId,
			BigDecimal employeeRequestPkId, Customer selectedCustomer, String employeeName, String description) {
		RequestContext context = RequestContext.getCurrentInstance();
		setSelectedCustomer(selectedCustomer);
		if (BigDecimal.ONE.compareTo(employeeRequestPkId) == 0) {
			try {
				Date beginDate = new Date();
				Date endDate = new Date();
				beginDate.setYear(getCurrentYear() - 1900);
				beginDate.setMonth(getSelectedMonth() - 1);
				beginDate.setDate(getSelectedDay());
				endDate.setYear(getCurrentYear() - 1900);
				endDate.setMonth(getSelectedMonth() - 1);
				endDate.setDate(getSelectedDay());
				employeeRequest = new EmployeeRequest();
				employeeRequest.setCustomerPkId(getSelectedCustomer().getPkId());
				employeeRequest.setEmployeePkId(employeePkId);
				// subotnik
				employeeRequest.setBeginDate(beginDate);
				employeeRequest.setBeginTime(beginHour);
				employeeRequest.setBeginMinute(beginMinute);
				employeeRequest.setEndDate(endDate);
				employeeRequest.setEndTime(endHour);
				employeeRequest.setEndMinute(endMinute);
				employeeRequest.setStatus(Tool.ADDED);
				employeeRequest.setDescription("(" + employeeName + ")" + description);
				employeeRequest.setMood(3);

				if (reservationLogic.getDuplicateRequest(employeeRequest.getBeginDate(), employeeRequest.getEndDate(),
						getSelectedCustomer().getPkId())) {
					getUserSessionController().showMessage(33);
				} else {
					employeeRequest.setId(infoLogic.getGeneratedPkId());
					selectedEmployee = logicTwo.getByPkId(Employee.class, employeePkId);

					if (BigDecimal.ZERO.compareTo(getSelectedCustomer().getPkId()) == 0) {
						getUserSessionController().showMessage(28);
						return;
					}

					if (selectedEmployee == null) {
						getUserSessionController().showMessage(28);
						return;
					}
					context.update("form:register");
					context.execute("PF('register').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		} else if (BigDecimal.ZERO.compareTo(employeeRequestPkId) == 0) {

		} else {
			try {
				employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, employeeRequestPkId);
				if (employeeRequest.getMood() == 2 || employeeRequest.getMood() == 4) {
					getUserSessionController().showMessage(30);
				} else {
					employeeRequest.setStatus(Tool.DELETE);
					selectedEmployee = logicTwo.getByPkId(Employee.class, employeePkId);
					context.update("form:delete");
					context.execute("PF('delete').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void employeeRequestList() {
		if (selectedCustomer != null && selectedCustomer.getPkId() != null
				&& selectedCustomer.getPkId().compareTo(BigDecimal.ZERO) != 0) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('employeeDetail').show();");
		}
	}

	public void fillCustomer() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			/*
			 * if (!Tool.isRegNumber(selectedCustomer.getRegNumber())) {
			 * selectedCustomer = null; return; }
			 */
			Customer customer = getCustomerController().getCurrentCustomer();
			if (customer.getRegNumber() != null && customer.getPkId() != null) {
				customer.setRegNumber(selectedCustomer.getRegNumber());
				selectedCustomerReg = selectedCustomer.getRegNumber();
				selectedCustomer = logicCustomer.getCustomerByRegNumber(selectedCustomer.getRegNumber());
				aimags = infoLogic.getAimags();
				if (selectedCustomer != null)
					sums = infoLogic.getSoums(selectedCustomer.getAimagPkId());
				if (selectedCustomer == null) {
					getCustomerController().getCurrentCustomer().setCardNumber(infoLogic.getGeneratedPkId());
					getCustomerController().getCurrentCustomer().setStatus(Tool.ADDED);

					context.update(":form:registrrrrr");
					context.execute("PF('showNoneFilledCustomer').show();");
					return;
				}
			}
			getCustomerRequests();
			selectedCashCustomer = selectedCustomer;
			if (selectedCustomer.getPkId() != null)
				customerRequests = logicRequest.getCustomerRequests(userSessionController.getLoggedInfo(),
						selectedCustomer.getPkId(), getBeginDate(), getEndDate());

		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public List<Employee> getSignedEmployees(BigDecimal employeePkId) {
		List<Employee> signedEmployees = new ArrayList<Employee>();
		Employee emp = new Employee();
		try {
			emp = logicRequest.getSignedEmployees(employeePkId);
			if (emp.getPkId() != null)
				signedEmployees.add(emp);
		} catch (Exception ex) {
			getUserSessionController().setErrorMessage(ex.getMessage());
		}

		return signedEmployees;
	}

	public void calculateEmployeeCalendarItem(BigDecimal employeePkId) {
		employeeRequestCalendarItems = new ArrayList<CalendarItem>();
		EconomicCalendar economicCalendar = getApplicationController().getCurrentEconomicCalendar();
		List<EmployeeRequest> employeeRequests = getEmployeeRequests();
		List<EconomicCalendarDtl> calendarDtls = getEconomicCalendarDtl();

		for (Employee emp : getSignedEmployees(employeePkId)) {
			LTime mbTime = new LTime();
			mbTime.setHour(economicCalendar.getBeginHour());
			mbTime.setMinute(economicCalendar.getBeginMinute());
			LTime meTime = new LTime();
			meTime.setHour(economicCalendar.getEndHour());
			meTime.setMinute(economicCalendar.getEndMinute());
			LTime lbTime = new LTime();
			lbTime.setHour(economicCalendar.getLunchBeginHour());
			lbTime.setMinute(economicCalendar.getLunchBeginMinute());
			LTime leTime = new LTime();
			leTime.setHour(economicCalendar.getLunchEndHour());
			leTime.setMinute(economicCalendar.getLunchEndMinute());
			for (LTime in = mbTime; Tool.isAfterLTime(mbTime, meTime) == 1; in.addMinute(emp.getInspectionTime())) {
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.setEmployeePkId(emp.getPkId());
				LTime time = new LTime();
				time.setHour(in.getHour());
				time.setMinute(in.getMinute());
				time.addMinute(emp.getInspectionTime());

				EconomicCalendarDtl calendarDtl = null;
				LTime timeb;
				LTime timee;
				for (EconomicCalendarDtl dtl : calendarDtls) {
					timeb = new LTime();
					timeb.setHour(dtl.getCalendarHdr().getBeginTimeHour());
					timeb.setMinute(dtl.getCalendarHdr().getBeginTimeMinute());
					timee = new LTime();
					timee.setHour(dtl.getCalendarHdr().getEndTimeHour());
					timee.setMinute(dtl.getCalendarHdr().getEndTimeMinute());
					if (((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1)
							&& (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1))
							|| (Tool.isAfterLTime(timeb, in) == 1 && Tool.isAfterLTime(time, timee) == 1)
									&& dtl.getEmployee().getPkId().compareTo(emp.getPkId()) == 0)
						calendarDtl = dtl;
				}

				if (calendarDtl != null) {
					calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":"
							+ Tool.toTimeOrMinuteString(in.getMinute()) + " - "
							+ Tool.toTimeOrMinuteString(time.getHour()) + ":"
							+ Tool.toTimeOrMinuteString(time.getMinute()) + calendarDtl.getCalendarHdr().getName());
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				} else if ((Tool.isAfterLTime(lbTime, in) == 1 || Tool.isAfterLTime(lbTime, time) == 1)
						&& (Tool.isAfterLTime(in, leTime) == 1 || Tool.isAfterLTime(time, leTime) == 1)) {
					calendarItem.setName(
							Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute())
									+ " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":"
									+ Tool.toTimeOrMinuteString(time.getMinute()) + " Цайны цаг");
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getHour());
				} else {
					EmployeeRequest request = null;

					List<EmployeeRequest> lstRequest = new ArrayList<EmployeeRequest>();
					for (EmployeeRequest empReq : employeeRequests) {
						if (empReq.getEmployeePkId().compareTo(emp.getPkId()) == 0) {
							lstRequest.add(empReq);
						}
					}

					for (EmployeeRequest employeeRequest : lstRequest) {
						timeb = new LTime();
						timeb.setHour(employeeRequest.getBeginTime());
						timeb.setMinute(employeeRequest.getBeginMinute());
						timee = new LTime();
						timee.setHour(employeeRequest.getEndTime());
						timee.setMinute(employeeRequest.getEndMinute());
						if ((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1)
								&& (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)) {
							request = employeeRequest;
						}
					}

					if (request != null) {
						calendarItem.setName(Tool.toTimeOrMinuteString(request.getBeginTime()) + ":"
								+ Tool.toTimeOrMinuteString(request.getBeginMinute()) + " - "
								+ Tool.toTimeOrMinuteString(request.getEndTime()) + ":"
								+ Tool.toTimeOrMinuteString(request.getEndMinute()) + " "
								+ request.getCustomerLastname() + " " + request.getCustomerFirstname() + " ("
								+ request.getDescription() + ") ");
						if (request.getMood() == 2 || request.getMood() == 4)
							calendarItem.setClassCss("calendarItemSelect1");
						else
							calendarItem.setClassCss("calendarItemSelect");
						calendarItem.setBeginHour(request.getBeginTime());
						calendarItem.setBeginMinute(request.getBeginMinute());
						calendarItem.setEndHour(request.getEndTime());
						calendarItem.setEndMinute(request.getEndMinute());
						calendarItem.setEmployeeRequestPkId(request.getPkId());
					} else {
						calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":"
								+ Tool.toTimeOrMinuteString(in.getMinute()) + " - "
								+ Tool.toTimeOrMinuteString(time.getHour()) + ":"
								+ Tool.toTimeOrMinuteString(time.getMinute()) + " Цаг захиалах");
						calendarItem.setClassCss("calendarItem");
						calendarItem.setBeginHour(in.getHour());
						calendarItem.setBeginMinute(in.getMinute());
						calendarItem.setEndHour(time.getHour());
						calendarItem.setEndMinute(time.getMinute());
						calendarItem.setEmployeeRequestPkId(BigDecimal.ONE);
					}
				}
				employeeRequestCalendarItems.add(calendarItem);
			}
		}
	}

	public void calculateCalendarItem() {
		customerRequestCalendarItems = new ArrayList<CalendarItem>();
		EconomicCalendar economicCalendar = getApplicationController().getCurrentEconomicCalendar();
		List<EmployeeRequest> employeeRequests = getEmployeeRequests();
		List<EconomicCalendarDtl> calendarDtls = getEconomicCalendarDtl();

		for (Employee emp : getEmployees()) {
			LTime mbTime = new LTime();
			mbTime.setHour(economicCalendar.getBeginHour());
			mbTime.setMinute(economicCalendar.getBeginMinute());
			LTime meTime = new LTime();
			meTime.setHour(economicCalendar.getEndHour());
			meTime.setMinute(economicCalendar.getEndMinute());
			LTime lbTime = new LTime();
			lbTime.setHour(economicCalendar.getLunchBeginHour());
			lbTime.setMinute(economicCalendar.getLunchBeginMinute());
			LTime leTime = new LTime();
			leTime.setHour(economicCalendar.getLunchEndHour());
			leTime.setMinute(economicCalendar.getLunchEndMinute());
			for (LTime in = mbTime; Tool.isAfterLTime(mbTime, meTime) == 1; in.addMinute(emp.getInspectionTime())) {
				CalendarItem calendarItem = new CalendarItem();
				calendarItem.setEmployeePkId(emp.getPkId());
				LTime time = new LTime();
				time.setHour(in.getHour());
				time.setMinute(in.getMinute());
				time.addMinute(emp.getInspectionTime());

				EconomicCalendarDtl calendarDtl = null;
				LTime timeb;
				LTime timee;
				for (EconomicCalendarDtl dtl : calendarDtls) {
					timeb = new LTime();
					timeb.setHour(dtl.getCalendarHdr().getBeginTimeHour());
					timeb.setMinute(dtl.getCalendarHdr().getBeginTimeMinute());
					timee = new LTime();
					timee.setHour(dtl.getCalendarHdr().getEndTimeHour());
					timee.setMinute(dtl.getCalendarHdr().getEndTimeMinute());
					if (((Tool.isAfterTime(timeb, in) == 1 || Tool.isAfterTime(timeb, time) == 1)
							&& (Tool.isAfterTime(in, timee) == 1 || Tool.isAfterTime(time, timee) == 1))
							&& (Tool.isAfterTime(timeb, in) == 1 && Tool.isAfterTime(time, timee) == 1)
									&& dtl.getEmployee().getPkId().compareTo(emp.getPkId()) == 0)
					{
						calendarDtl = dtl;
					}
				}

				if (calendarDtl != null) {
					calendarItem.setName(
							Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute())
									+ " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":"
									+ Tool.toTimeOrMinuteString(time.getMinute()) + " "
									+ calendarDtl.getCalendarHdr().getName());
					calendarItem.setClassCss("calendarItemComplete1");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getMinute());
				} else if ((Tool.isAfterLTime(lbTime, in) == 1 || Tool.isAfterLTime(lbTime, time) == 1)
						&& (Tool.isAfterLTime(in, leTime) == 1 || Tool.isAfterLTime(time, leTime) == 1)) {
					calendarItem.setName(
							Tool.toTimeOrMinuteString(in.getHour()) + ":" + Tool.toTimeOrMinuteString(in.getMinute())
									+ " - " + Tool.toTimeOrMinuteString(time.getHour()) + ":"
									+ Tool.toTimeOrMinuteString(time.getMinute()) + " Цайны цаг");
					calendarItem.setClassCss("calendarItemComplete");
					calendarItem.setBeginHour(in.getHour());
					calendarItem.setBeginMinute(in.getMinute());
					calendarItem.setEndHour(time.getHour());
					calendarItem.setEndMinute(time.getMinute());
				} else {
					EmployeeRequest request = null;

					List<EmployeeRequest> lstRequest = new ArrayList<EmployeeRequest>();
					for (EmployeeRequest empReq : employeeRequests) {
						if (empReq.getEmployeePkId().compareTo(emp.getPkId()) == 0) {
							lstRequest.add(empReq);
						}
					}

					for (EmployeeRequest employeeRequest : lstRequest) {
						timeb = new LTime();
						timeb.setHour(employeeRequest.getBeginTime());
						timeb.setMinute(employeeRequest.getBeginMinute());
						timee = new LTime();
						timee.setHour(employeeRequest.getEndTime());
						timee.setMinute(employeeRequest.getEndMinute());
						if ((Tool.isAfterLTime(timeb, in) == 1 || Tool.isAfterLTime(timeb, time) == 1)
								&& (Tool.isAfterLTime(in, timee) == 1 || Tool.isAfterLTime(time, timee) == 1)) {
							request = employeeRequest;
						}
					}

					if (request != null) {
						calendarItem.setName(Tool.toTimeOrMinuteString(request.getBeginTime()) + ":"
								+ Tool.toTimeOrMinuteString(request.getBeginMinute()) + " - "
								+ Tool.toTimeOrMinuteString(request.getEndTime()) + ":"
								+ Tool.toTimeOrMinuteString(request.getEndMinute()) + " "
								+ request.getCustomerLastname() + " " + request.getCustomerFirstname() + " ("
								+ request.getDescription() + ") ");

						calendarItem.setClassCss("calendarItemSelect");
						Date beginDate = new Date();
						beginDate.setYear(getCurrentYear() - 1900);
						beginDate.setMonth(getSelectedMonth() - 1);
						beginDate.setDate(getSelectedDay());
						beginDate.setHours(time.getHour());
						beginDate.setMinutes(time.getMinute());
						if (beginDate.before(new Date())) {
							calendarItem.setClassCss(calendarItem.getClassCss() + "0");
						}

						if (request.getIsExpress() == 1)
							calendarItem.setClassCss("calendarItemSelect1");
						if (request.getHasPayment() == 1)
							calendarItem.setClassCss("calendarItemSelect2");

						calendarItem.setBeginHour(request.getBeginTime());
						calendarItem.setBeginMinute(request.getBeginMinute());
						calendarItem.setEndHour(request.getEndTime());
						calendarItem.setEndMinute(request.getEndMinute());
						calendarItem.setEmployeeRequestPkId(request.getPkId());
					} else {
						calendarItem.setName(Tool.toTimeOrMinuteString(in.getHour()) + ":"
								+ Tool.toTimeOrMinuteString(in.getMinute()) + " - "
								+ Tool.toTimeOrMinuteString(time.getHour()) + ":"
								+ Tool.toTimeOrMinuteString(time.getMinute()) + " Цаг захиалах");
						calendarItem.setClassCss("calendarItem");
						calendarItem.setBeginHour(in.getHour());
						calendarItem.setBeginMinute(in.getMinute());
						calendarItem.setEndHour(time.getHour());
						calendarItem.setEndMinute(time.getMinute());
						calendarItem.setEmployeeRequestPkId(BigDecimal.ONE);

						Date beginDate = new Date();
						beginDate.setYear(getCurrentYear() - 1900);
						beginDate.setMonth(getSelectedMonth() - 1);
						beginDate.setDate(getSelectedDay());
						beginDate.setHours(time.getHour());
						beginDate.setMinutes(time.getMinute());
						if (beginDate.before(new Date())) {
							calendarItem.setClassCss(calendarItem.getClassCss() + "0");
						}
					}
				}
				customerRequestCalendarItems.add(calendarItem);
			}
		}
	}

	// EndRegion

	// Region - Propertys Get, Set

	@SuppressWarnings("deprecation")
	public List<EmployeeRequest> getEmployeeRequests() {
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
		try {
			employeeRequests = reservationLogic.getEmployeeRequestByDate(date1, date2,
					getUserSessionController().getLoggedInfo());
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return employeeRequests;
	}

	@SuppressWarnings("deprecation")
	public List<EconomicCalendarDtl> getEconomicCalendarDtl() {
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
		try {
			calendarDtls = logicTwo.getEconomicCalendarDtlByDate(date1, date2,
					getUserSessionController().getLoggedInfo(), getSelectedSubOrganization().getPkId(),
					getCurrentYear() - 1900, getSelectedMonth() - 1, getSelectedDay());
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return calendarDtls;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public SubOrganization getSelectedSubOrganization() {
		if (selectedSubOrganization == null) {
			try {
				List<SubOrganization> list = logicTwo.getSubOrganizations(getUserSessionController().getLoggedInfo());
				if (list != null && list.size() > 0)
					selectedSubOrganization = list.get(0);
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return selectedSubOrganization;
	}

	public void setSelectedSubOrganization(SubOrganization selectedSubOrganization) {
		this.employees = null;
		this.selectedEmployee = null;
		this.selectedSubOrganization = selectedSubOrganization;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	@SuppressWarnings("deprecation")
	public int getSelectedMonth() {
		if (selectedMonth == 0) {
			selectedMonth = (new Date()).getMonth() + 1;
			this.selectedDay = (new Date()).getDate();
		}
		return selectedMonth;
	}

	public void setSelectedMonth(int selectedMonth) {
		this.selectedMonth = selectedMonth;
	}

	public int getSelectedDay() {
		return selectedDay;
	}

	public void setSelectedDay(int selectedDay) {
		this.selectedDay = selectedDay;
	}

	@SuppressWarnings("deprecation")
	public int getCurrentYear() {
		if (currentYear == 0) {
			currentYear = (new Date()).getYear() + 1900;
		}
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public List<Integer> getMonths() {
		months = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++)
			months.add(i);
		return months;
	}

	public void setMonths(List<Integer> months) {
		this.months = months;
	}

	@SuppressWarnings("deprecation")
	public int getCurrentYearIndex() {
		Date dte = new Date();
		currentYearIndex = 1900 + dte.getYear();
		return currentYearIndex;
	}

	public void setCurrentYearIndex(int currentYearIndex) {
		this.currentYearIndex = currentYearIndex;
	}

	public List<Integer> getDays() {
		days = new ArrayList<Integer>();
		for (int i = 1; i <= Tool.daysOfMonth(getCurrentYear(), getSelectedMonth()); i++)
			days.add(i);
		return days;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}

	@SuppressWarnings("static-access")
	public String getCustomerRequestTitle() {
		customerRequestTitle = getCurrentYear() + " оны " + getSelectedMonth() + " сарын " + getSelectedDay() + " , ";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getCurrentYear());
		calendar.set(Calendar.MONTH, getSelectedMonth() - 1);
		calendar.set(Calendar.DATE, getSelectedDay());
		int dayOfWeek = calendar.get(calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			customerRequestTitle = customerRequestTitle + "Ням гараг";
			break;
		case 2:
			customerRequestTitle = customerRequestTitle + "Даваа гараг";
			break;
		case 3:
			customerRequestTitle = customerRequestTitle + "Мягмар гараг";
			break;
		case 4:
			customerRequestTitle = customerRequestTitle + "Лхагва гараг";
			break;
		case 5:
			customerRequestTitle = customerRequestTitle + "Пүрэв гараг";
			break;
		case 6:
			customerRequestTitle = customerRequestTitle + "Баасан гараг";
			break;
		case 7:
			customerRequestTitle = customerRequestTitle + "Бямбаа гараг";
			break;
		default:
			break;
		}
		return customerRequestTitle;
	}

	public void setCustomerRequestTitle(String customerRequestTitle) {
		this.customerRequestTitle = customerRequestTitle;
	}

	public Employee getSelectedEmployee() {
		if (selectedEmployee == null) {
			try {
				List<Employee> employees = logicRequest
						.getEmployeeBySubOrganization(getSelectedSubOrganization().getPkId());
				if (employees != null && employees.size() > 0)
					selectedEmployee = employees.get(0);
				System.out.println(beginDate + " and  TIme " + endDate);
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public Customer getSelectedCustomer() {
		if (getInspectionController().isSelectCustomer()) {
			if (getCustomerController().getCustomer().getPkId() != null)
				selectedCustomer = getCustomerController().getCustomer();
		} else if (selectedCustomer == null) {
			selectedCustomer = new Customer();
			selectedCustomer.setPkId(BigDecimal.ZERO);
		}
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;

	}

	public CustomerController getCustomerController() {
		return customerController;
	}

	public void setCustomerController(CustomerController customerController) {
		this.customerController = customerController;
	}

	public String getSelectedCustomerReg() {
		return selectedCustomerReg;
	}

	public void setSelectedCustomerReg(String selectedCustomerReg) {
		this.selectedCustomerReg = selectedCustomerReg;
	}

	public Customer getCustomerCash() {
		return customerCash;
	}

	public void setCustomerCash(Customer customerCash) {
		this.customerCash = customerCash;
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public String dateDayColor(int n) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, (new Date()).getYear() + 1900);
		calendar.set(Calendar.MONTH, getSelectedMonth() - 1);
		calendar.set(Calendar.DATE, n);
		if (calendar.get(calendar.DAY_OF_WEEK) == 1 || calendar.get(calendar.DAY_OF_WEEK) == 7)
			return "color: #ccc;";
		return "";
	}

	public Customer getSelectedCashCustomer() {
		if (selectedCashCustomer == null)
			selectedCashCustomer = new Customer();
		return selectedCashCustomer;
	}

	public void setSelectedCashCustomer(Customer selectedCashEmployee) {
		cashHdrs = new ArrayList<CashBusinessEntity>();
		if (selectedCashEmployee != null) {
			try {

				listLoanEmployee = logicPayment.getEmployeeLoanPayments(selectedCashEmployee.getPkId());

				cashHdrs = logicCashier.getCashHdrsByCustomerPkId(selectedCashEmployee.getPkId(),
						getUserSessionController().getLoggedInfo());
				payment = new Payment();
				payment.setId(logicPayment.getPaymentId(getUserSessionController().getLoggedInfo()));
				payment.setCustomerPkId(selectedCashEmployee.getPkId());
				List<BigDecimal> employeePkIds = new ArrayList<BigDecimal>();
				for (CashBusinessEntity item : cashHdrs) {
					if (employeePkIds.contains(item.getEmployee().getPkId()))
						continue;
					employeePkIds.add(item.getEmployee().getPkId());
				}
				listEmployee = logicPayment.getEmployeeByPaymentHistorys(selectedCashEmployee.getPkId(),
						getUserSessionController().getLoggedInfo());
				// for (Employee element : listEmployee) {
				// if(element.getMood() == 4) continue;
				// payment.setAmount3(payment.getAmount3().add(element.getPrice()));
				// }
				List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
				for (CashBusinessEntity item : cashHdrs) {
					// subotnik
					payment.setBasicAmount(payment.getBasicAmount().add(item.getAllAmount()));
					// payment.setAmount1(payment.getAmount1().add(
					// item.getDiscoutDtlAmount()));
					payment.setDiscountPercent(BigDecimal.ZERO);
					bigDecimals.add(item.getTreatment().getPkId());
				}

				List<Xray> xrays = xrayLogic.getXrayByEmployeePkId(selectedCashEmployee.getPkId(),
						getUserSessionController().getLoggedInfo());
				setXrays(new ArrayList<Xray>());
				for (Xray xray : xrays) {
					xray.setStatus(Tool.UNCHANGED);
					getXrays().add(xray);
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		this.selectedCashCustomer = selectedCashEmployee;
	}

	public List<CashBusinessEntity> getCashHdrs() {
		if (cashHdrs == null) {
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

	public List<PaymentHistory> getListLoanEmployee() {
		if (listLoanEmployee == null)
			listLoanEmployee = new ArrayList<PaymentHistory>();
		return listLoanEmployee;
	}

	public void setListLoanEmployee(List<PaymentHistory> listLoanEmployee) {
		this.listLoanEmployee = listLoanEmployee;
	}

	public Payment getPayment() {
		if (payment == null)
			payment = new Payment();
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public List<Xray> getXrays() {
		if (xrays == null) {
			xrays = new ArrayList<Xray>();
		}
		return xrays;
	}

	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}

	public void setSelectedSubOrganizationStr(String sss) {
		BigDecimal bigDecimal = new BigDecimal(sss);
		try {
			getEconomicCalendarHdr().setTypePkId(bigDecimal);
			// setSelectedSubOrganization(logicTwo.getByPkId(SubOrganization.class,
			// bigDecimal));
			setSubOrganization(logicTwo.getByPkId(SubOrganization.class, bigDecimal));
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public String getSelectedSubOrganizationStr() {
		return selectedSubOrganizationStr;
	}

	public EconomicCalendarHdr getEconomicCalendarHdr() {
		if (economicCalendarHdr == null) {
			economicCalendarHdr = new EconomicCalendarHdr();
			economicCalendarHdr.setType(0);
			economicCalendarHdr.setStatus(Tool.LAST);
			economicCalendarHdr.setBeginDate(new Date());
			economicCalendarHdr.setEndDate(new Date());
		}
		return economicCalendarHdr;
	}

	public void setEconomicCalendarHdr(EconomicCalendarHdr economicCalendarHdr) {
		this.economicCalendarHdr = economicCalendarHdr;
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
		if (filteredEmployees == null && subOrganization != null) {
			try {
				filteredEmployees = logicTwo.getEmployeeBySubOrganizationPkId(subOrganization.getPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	public EmployeeRequest getEmployeeRequest() {
		if (employeeRequest == null)
			employeeRequest = new EmployeeRequest();
		employeeRequest.isHasInsurance();
		employeeRequest.isPayment();
		return employeeRequest;
	}

	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}

	public boolean isReportCashier() {
		if (isReportCashier) {
			isReportCashier = false;
			return true;
		}
		return isReportCashier;
	}

	public void setReportCashier(boolean isReportCashier) {
		this.isReportCashier = isReportCashier;
	}

	public List<Employee> getListCashEmployee() {
		return listCashEmployee;
	}

	public void setListCashEmployee(List<Employee> listCashEmployee) {
		this.listCashEmployee = listCashEmployee;
	}

	public List<CalendarItem> getCustomerRequestCalendarItems() {
		return customerRequestCalendarItems;
	}

	public void setCustomerRequestCalendarItems(List<CalendarItem> customerRequestCalendarItems) {
		this.customerRequestCalendarItems = customerRequestCalendarItems;
	}

	public InspectionController getInspectionController() {
		return inspectionController;
	}

	public void setInspectionController(InspectionController inspectionController) {
		this.inspectionController = inspectionController;
	}

	public List<CalendarItem> getEmployeeRequestCalendarItems() {
		return employeeRequestCalendarItems;
	}

	public void setEmployeeRequestCalendarItems(List<CalendarItem> employeeRequestCalendarItems) {
		this.employeeRequestCalendarItems = employeeRequestCalendarItems;
	}

	public Date getOrderDate() {
		if (orderDate == null)
			orderDate = new Date();
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void changeDate1() {

		setCurrentYear(getOrderDate().getYear() + 1900);
		setSelectedMonth(getOrderDate().getMonth() + 1);
		setSelectedDay(getOrderDate().getDate());

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:panel");

	}

	public List<Aimag> getAimags() {
		if (aimags == null)
			aimags = new ArrayList<Aimag>();
		return aimags;
	}

	public void setAimags(List<Aimag> aimags) {
		this.aimags = aimags;
	}

	public List<Soum> getSums() {
		if (sums == null)
			sums = new ArrayList<Soum>();
		return sums;
	}

	public void setSums(List<Soum> sums) {
		this.sums = sums;
	}

	public List<SubOrganization> getSubOrganizations() {
		try {
			subOrganizations = logicRequest.getOrganizations();
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return subOrganizations;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public CashController getCashController() {
		return cashController;
	}

	public void setCashController(CashController cashController) {
		this.cashController = cashController;
	}

	public Customer getSelCustomer() {
		return selCustomer;
	}

	public void setSelCustomer(Customer selCustomer) {
		if (selCustomer != null && selCustomer.getPkId() != null) {
			selectedCustomer = selCustomer;
		}
		this.selCustomer = selCustomer;
	}

	public void cleanSelectedCustomer() {
		selectedCustomer = null;
	}

	public List<SubOrganization> getListSubOrganization() {
		if (listSubOrganization == null) {
			try {
				listSubOrganization = infoLogic.getListSubOrganization();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listSubOrganization;
	}

	public void setListSubOrganization(List<SubOrganization> listSubOrganization) {
		this.listSubOrganization = listSubOrganization;
	}

	public List<CustomerRequest> getCrs() {
		if (crs == null)
			crs = new ArrayList<CustomerRequest>();
		return crs;
	}

	public void setCrs(List<CustomerRequest> crs) {
		this.crs = crs;
	}

	public List<CustomerRequest> getCustomerRequests() {
		if (customerRequests == null)
			customerRequests = new ArrayList<CustomerRequest>();
		return customerRequests;
	}

	public void setCustomerRequests(List<CustomerRequest> customerRequests) {
		this.customerRequests = customerRequests;
	}

	public Date getBeginDate() {
		if (beginDate == null)
			beginDate = new Date();
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		if (endDate == null)
			endDate = new Date();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDateString(Date date) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public BigDecimal getSelectEmployeeRequestCustomerPkId() {
		return selectEmployeeRequestCustomerPkId;
	}

	public void setSelectEmployeeRequestCustomerPkId(BigDecimal selectEmployeeRequestCustomerPkId) {
		this.selectEmployeeRequestCustomerPkId = selectEmployeeRequestCustomerPkId;
	}

	public String getBgcolors() {
		return bgcolors;
	}

	public void setBgcolors(String bgcolors) {
		this.bgcolors = bgcolors;
	}

	public List<EmployeeRequest> getListemployeeRequest() {
		return listemployeeRequest;
	}

	public void setListemployeeRequest(List<EmployeeRequest> listemployeeRequest) {
		this.listemployeeRequest = listemployeeRequest;
	}
	// EndRegion

	public Customer getsCustomer() {
		if (getCustomerController().isSelectCustomer()) {
			sCustomer = getCustomerController().getCustomer();
			if (getCustomerController().getCustomer().getPkId() != null)
				selectedCustomer = getCustomerController().getCustomer();
		} else if (selectedCustomer == null) {
			sCustomer = new Customer();
			sCustomer.setPkId(BigDecimal.ZERO);
		}
		return sCustomer;
	}

	public void setsCustomer(Customer sCustomer) {
		this.sCustomer = sCustomer;
	}

	public Date getSelectedDate() {
		if (selectedDate == null)
			selectedDate = new Date();
		selectedDate.setDate(getSelectedDay());
		selectedDate.setMonth(getSelectedMonth() - 1);
		selectedDate.setYear(getCurrentYear() - 1900);

		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}
}

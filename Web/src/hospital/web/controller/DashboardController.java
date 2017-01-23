package hospital.web.controller;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.EmployeeRequestCount;
import hospital.businessentity.ExaminationDashboard;
import hospital.businessentity.ExaminationEmployeeDashboard;
import hospital.businessentity.IncomeBySubOrga;
import hospital.businessentity.PaymentEntity;
import hospital.businessentity.Tool;
import hospital.businesslogic.LogicDashboard;
import hospital.businesslogic.interfaces.IDashboardLogicLocal;
import hospital.entity.Customer;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequestPaymentMap;
import hospital.entity.IncomePlan;
import hospital.entity.LoginDialog;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.entity.ViewEmployee;
import hospital.entity.ViewIncomePlan;
import mondrian.rolap.BitKey.Big;
import hospital.entity.PaymentDtl;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@SessionScoped
@ManagedBean(name = "dashboardController")
public class DashboardController {

	private List<SubOrganization> subOrganizations;
	private List<Employee> employees;
	private List<CustomerRequest> customerRequests;
	private List<Treatment> treatments;
	private List<Customer> customers;
	@SuppressWarnings("deprecation")
	private int month = new Date().getMonth() + 1;
	@SuppressWarnings("deprecation")
	private int year = new Date().getYear();
	private List<IncomePlan> monthlyPlan;
	private IncomePlan cursorPriceHistory;
	private SubOrganization selectedSubOrganization;
	private List<BigDecimal> yearList;
	private List<BigDecimal> monthList;
	private List<BigDecimal> seasonList;
	private List<BigDecimal> weekList;
	private BigDecimal cursorYear;
	private BigDecimal cursorYear1;
	private BigDecimal cursorYear2;
	private BigDecimal cursorYearChart1;
	private BigDecimal cursorSeason;
	private BigDecimal cursorMonth;
	private BigDecimal cursorWeek;
	private SubOrganization cursorSubOrganization;
	private int lineChartTypeSelector = 11;
	private int pieChartTypeSelector = 11;
	private List<Employee> employeeWithInspectionCount;
	private List<ExaminationDashboard> examinationCounts;
	private List<ExaminationEmployeeDashboard> examinationEmployees;
	private List<LoginDialog> loginDialogs;
	private List<LoginDialog> upLoginDialogs;
	private Date beginDate;
	private Date endDate;
	private Date monthBeginDate;
	private String beginDateStr;
	private String endDateStr;
	private String monthBeginDateStr;
	private LoginDialog lodialog;
	private String editorText;
	private String repormText;
	private List<PaymentDtl> paymentDtl;
	private List<EmployeeRequestCount> employeeRequestCount;
	private List<PaymentEntity> paymentDtlType;
	private List<PaymentEntity> paymentTotal;
	private String  subOrgaSelectOne;
	private TreeNode treeDashboard;
	private boolean filterCheck;
	

	private long allCustomerCount;

	@EJB(beanName = "DashboardLogic")
	IDashboardLogicLocal dashboardLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@PostConstruct
	public void init() {
		try {
			subOrganizations = dashboardLogic.getSubOrganizations();
			setCursorSubOrganization(subOrganizations.get(0));
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public DashboardController() {

	}

	public List<SubOrganization> getSubOrganizations() {
		if (subOrganizations == null)
			subOrganizations = new ArrayList<SubOrganization>();
		return subOrganizations;
	}

	public List<PaymentDtl> getPaymentType() {
		if (paymentDtl == null)
			paymentDtl = new ArrayList<PaymentDtl>();
		return paymentDtl;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public List<Employee> getEmployees() {
		if (employees == null)
			employees = new ArrayList<Employee>();
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<CustomerRequest> getCustomerRequests() {
		if (customerRequests == null)
			customerRequests = new ArrayList<CustomerRequest>();
		return customerRequests;
	}

	public void setCustomerRequests(List<CustomerRequest> customerRequests) {
		this.customerRequests = customerRequests;
	}

	public List<Treatment> getTreatments() {
		if (treatments == null)
			treatments = new ArrayList<Treatment>();
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public List<Customer> getCustomers() {
		if (customers == null)
			customers = new ArrayList<Customer>();
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public SubOrganization getCursorSubOrganization() {
		if (cursorSubOrganization == null)
			cursorSubOrganization = new SubOrganization();
		return cursorSubOrganization;
	}

	public void setCursorSubOrganization(SubOrganization cursorSubOrganization) {
		this.cursorSubOrganization = cursorSubOrganization;
	}

	@SuppressWarnings("deprecation")
	public String createAreaChart() {
		String data = " ";
		data = data + barChartCategory();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getCursorYearChart1().intValue());
		calendar.set(Calendar.MONTH, getMonth() - 1);
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		Date beginDate = new Date();
		Date endDate = new Date();
		beginDate.setYear(getCursorYearChart1().intValue()-1900);
		endDate.setYear(getCursorYearChart1().intValue()-1900);
		beginDate.setMonth(month - 1);
		endDate.setMonth(month - 1);
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);

		try {
			data = data + "var  barChartData = [ ";
			beginDate.setDate(1);
			endDate.setDate(numDays);
			List<IncomeBySubOrga> real = dashboardLogic.getDailyBillBySubOrganization(beginDate, endDate);
			for (IncomeBySubOrga so : real) {
				data = data + "{ ";
				if (so.getAmount() != null)
					data = data + "y: " + so.getAmount().toString() + ",";
				else
					data = data + "y: 0,";
				data = data + "color: colors[0],";
				data = data + "drilldown: {";
				data = data + "name: ['" + so.getSubOrganizationName() + "'],";
				String categories = "categories: [ ";
				String values = "data: [ ";
				for (ViewEmployee employee : so.getIncomeByEmployee()) {
					categories = categories + "'" + employee.getFirstName() + "',";
					if (employee.getAmount() != null)
						values = values + employee.getAmount().toString() + ",";
					else
						values = values + "0,";
				}
				categories = categories.substring(0, categories.length() - 1);
				values = values.substring(0, values.length() - 1);
				categories = categories + "]";
				values = values + "]";
				data = data + categories + ",";
				data = data + values;

				data = data + "}";
				data = data + "},";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "];";
		}

		catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}

		try {
			data = data + "var barChartData2 = [ ";
			List<ViewIncomePlan> incomePlan = dashboardLogic.getPlanHistory();
			for (ViewIncomePlan plan : incomePlan) {
				data = data + "{ y: ";
				if (plan != null){
					data = data + plan.getPrice();
				}
				else {
					data = data + "0";
				}
				data = data + ", color: colors[1]},";
			}

			data = data.substring(0, data.length() - 1);
			data = data + "];";
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("drawBarChart();");
		return data;

	}

	public String barChartCategory() {
		String cat = "var categories1 = [ ";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getCursorYearChart1().intValue());
		calendar.set(Calendar.MONTH, getMonth() - 1);
		int numDays = calendar.getActualMaximum(Calendar.DATE);
		Date beginDate = new Date();
		Date endDate = new Date();
		beginDate.setYear(getCursorYearChart1().intValue()-1900);
		endDate.setYear(getCursorYearChart1().intValue()-1900);
		beginDate.setMonth(month - 1);
		endDate.setMonth(month - 1);
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		try {
			beginDate.setDate(1);
			endDate.setDate(numDays);
			List<IncomeBySubOrga> income = dashboardLogic.getDailyBillBySubOrganization(beginDate, endDate);
			for (IncomeBySubOrga sbOrga : income){
				cat = cat + "'" + sbOrga.getSubOrganizationName() + "',";
			}
				
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
		cat = cat.substring(0, cat.length() - 1);
		cat = cat + "];";
		return cat;
	}

	@SuppressWarnings("deprecation")
	public String createSubOrgaLineChart() {
		Date beginDate = new Date();
		Date endDate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getCursorYear1().intValue());
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		beginDate = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		endDate = calendar.getTime();

		beginDate.setHours(0);
		beginDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);

		String data = "xAxis: {";
		try {
			data = data + "\n categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']";
			data = data + "\n },";
			data = data + "\n series: [";
			getEmployees();

			beginDate.setMonth(0);
			endDate.setMonth(11);
			List<IncomeBySubOrga> incomeBySubOrga = dashboardLogic.getMonthlyBillBySubOrganization(beginDate, endDate);
			subOrganizations = dashboardLogic.getSubOrganizations();
			for (SubOrganization subOrga : subOrganizations) {
				data = data + "\n { name: " + "'" + subOrga.getName() + "',";
				data = data + "\n data: [ ";

				for (int i = 1; i <= 12; i++) {
					String value = " null,";
					for (IncomeBySubOrga income : incomeBySubOrga) {
						if (subOrga.getPkId().compareTo(income.getSubOrganizationPkId()) == 0
								&& income.getMonth() == i) {
							if (income.getAmount() != null)
								value = income.getAmount().toString() + ",";
							else
								value = "null,";
							break;
						} else {
							value = "null,";
						}
					}
					data = data + value;
				}
				data = data.substring(0, data.length() - 1);
				data = data + "]},";

			}

			data = data + "]";
			// System.out.println(data);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("deprecation")
	public String createSubAmbuLineChart() {
		Date beginDate = new Date();
		Date endDate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, getCursorYear2().intValue());
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		beginDate = calendar.getTime();
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		endDate = calendar.getTime();
		
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		
		String data = "\n series: [";
		try {
			beginDate.setDate(1);
			int i;
			BigDecimal tempSum;
			List<PaymentDtl> incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
			data = data + "\n { name : 'Дүрс оношлогоо'" + ",";
			data = data + "\n data : [";
			for (i = 0; i < 12; i++) {
				endDate.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				tempSum = BigDecimal.ZERO;
				beginDate.setMonth(i);
				endDate.setMonth(i);
				incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
				for (PaymentDtl income : incomeByType) {
					if (Tool.INSPECTIONTYPE_XRAY.equals(income.getType())) 
						tempSum = tempSum.add(income.getAmount());
				}
				data = data + tempSum.toString() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "]},";
			data = data + "\n { name : 'Эмчийн үзлэг'" + ",";
			data = data + "\n data : [";
			for (i = 0; i < 12; i++) {
				endDate.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				tempSum = BigDecimal.ZERO;
				beginDate.setMonth(i);
				endDate.setMonth(i);
				incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
				for (PaymentDtl income : incomeByType) {
					if (Tool.INSPECTIONPAYMENT.equals(income.getType())) 
						tempSum = tempSum.add(income.getAmount());
				}
				data = data + tempSum.toString() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "]},";
			data = data + "\n { name : 'Лабораторийн шинжилгээ'" + ",";
			data = data + "\n data : [";
			for (i = 0; i < 12; i++) {
				endDate.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				tempSum = BigDecimal.ZERO;
				beginDate.setMonth(i);
				endDate.setMonth(i);
				incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
				for (PaymentDtl income : incomeByType) {
					if (Tool.INSPECTIONTYPE_EXAMINATION.equals(income.getType())) 
						tempSum = tempSum.add(income.getAmount());
				}
				data = data + tempSum.toString() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "]},";
			data = data + "\n { name : 'Хагалгаа'" + ",";
			data = data + "\n data : [";
			for (i = 0; i < 12; i++) {
				endDate.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				tempSum = BigDecimal.ZERO;
				beginDate.setMonth(i);
				endDate.setMonth(i);
				incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
				for (PaymentDtl income : incomeByType) {
					if (Tool.INSPECTIONTYPE_SURGERY.equals(income.getType()))
						tempSum = tempSum.add(income.getAmount());
				}
				data = data + tempSum.toString() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "]},";
			data = data + "\n { name : 'Эмчилгээ'" + ",";
			data = data + "\n data : [";
			for (i = 0; i < 12; i++) {
				endDate.setDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				tempSum = BigDecimal.ZERO;
				beginDate.setMonth(i);
				endDate.setMonth(i);
				incomeByType = dashboardLogic.getMonthlyBillByPaymentDtlType(beginDate, endDate);
				for (PaymentDtl income : incomeByType) {
					if (Tool.INSPECTIONTYPE_TREATMENT.equals(income.getType()))
						tempSum = tempSum.add(income.getAmount());
				}
				data = data + tempSum.toString() + ",";
			}
			data = data.substring(0, data.length() - 1);
			data = data + "]},";
			data = data + "]";
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
		return data;
	}

	@SuppressWarnings("deprecation")
	public String createLineChart() {

		Date beginDate = new Date();
		Date endDate = new Date();

		beginDate.setHours(0);
		beginDate.setMinutes(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		String data = "xAxis: { ";
		switch (lineChartTypeSelector) {
		case 11: {
			// Default Жилийг сараар
			beginDate.setYear(getCursorYear().intValue() - 1900);
			endDate.setYear(getCursorYear().intValue() - 1900);
			try {
				data = data + "\n categories: ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12']";
				data = data + "\n },";
				data = data + "\n series: [";
				getEmployees();
				employees = dashboardLogic.getEmployees(cursorSubOrganization.getPkId());

				beginDate.setMonth(0);
				endDate.setMonth(11);
				List<Employee> employeePayment = dashboardLogic.getPaymentByEmployee(cursorSubOrganization.getPkId(),
						beginDate, endDate);

				for (Employee e : employees) {
					data = data + "\n { name: " + "'" + e.getFirstName() + "',";
					data = data + "\n data: [";

					for (int i = 1; i <= 12; i++) {
						String value = "";
						for (Employee payment : employeePayment) {
							if (e.getPkId().compareTo(payment.getPkId()) == 0 && payment.getMonth() == i) {
								if (payment.getSumAmount() != null)
									value = payment.getSumAmount().toString() + ",";
								else
									value = "0,";
								break;
							} else {
								value = "0,";
							}
						}
						data = data + value;
					}
					data = data + "]},";

				}
				
				data = data + "]";
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.toString());
				ex.printStackTrace();
			}
			break;
		}
		case 22: {
			// Жилийг улиралд хувааснаар
			beginDate.setYear(getCursorYear().intValue() - 1900);
			endDate.setYear(getCursorYear().intValue() - 1900);
			try {
				data = data + "\n categories: ['1-р улирал', '2-р улирал', '3-р улирал', '4-р улирал']";
				data = data + "\n },";
				data = data + "\n series: [";

				getEmployees();
				employees = dashboardLogic.getEmployees(getCursorSubOrganization().getPkId());

				for (Employee e : employees) {
					data = data + "\n { name: " + "'" + e.getFirstName() + "',";
					data = data + "\n data: [";

					for (int i = 0, j = 0; i <= 3; i++, j += 3) {

						beginDate.setMonth(i);
						endDate.setMonth(j);
						beginDate.setDate(1);
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.MONTH, i);
						int numDays = calendar.getActualMaximum(Calendar.DATE);
						endDate.setDate(numDays);

						if (dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0) == null) {
							data = data + "null,";
						} else {
							data = data + dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0)
									.toString() + ",";
						}
					}
					data = data + "]},";
				}

				data = data + "]";
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.toString());
				ex.printStackTrace();
			}
			break;
		}
		case 33: {
			// Улирлыг сард хувааснаар
			beginDate.setYear(getCursorYear().intValue() - 1900);
			endDate.setYear(getCursorYear().intValue() - 1900);
			int i = 0, j = 0;
			try {

				if (cursorSeason.compareTo(new BigDecimal(1)) == 0) {
					data = data + "\n categories: ['1-р сар', '2-р сар', '3-р сар']";
					i = 1;
					j = 3;
				} else if (cursorSeason.compareTo(new BigDecimal(2)) == 0) {
					i = 4;
					j = 6;
					data = data + "\n categories: ['4-р сар', '5-р сар', '6-р сар']";
				} else if (cursorSeason.compareTo(new BigDecimal(3)) == 0) {
					i = 7;
					j = 9;
					data = data + "\n categories: ['7-р сар', '8-р сар', '9-р сар']";
				} else if (cursorSeason.compareTo(new BigDecimal(4)) == 0) {
					i = 10;
					j = 12;
					data = data + "\n categories: ['10-р сар', '11-р сар', '12-р сар']";
				}
				data = data + "\n },";
				data = data + "\n series: [";

				getEmployees();
				employees = dashboardLogic.getEmployees(getCursorSubOrganization().getPkId());

				for (Employee e : employees) {
					data = data + "\n { name: " + "'" + e.getFirstName() + "',";
					data = data + "\n data: [";

					for (int k = i; k <= j; k++) {
						beginDate.setMonth(k - 1);
						endDate.setMonth(k - 1);
						beginDate.setDate(1);
						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.MONTH, k);
						int numDays = calendar.getActualMaximum(Calendar.DATE);
						endDate.setDate(numDays);

						if (dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0) == null) {
							data = data + "null,";
						} else {
							data = data + dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0)
									.toString() + ",";
						}
					}
					data = data + "]},";

				}

				data = data + "]";
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.toString());
				ex.printStackTrace();
			}
			break;
		}
		case 44: {
			// Сарыг 7 хоногт хувааснаар
			beginDate.setYear(getCursorYear().intValue() - 1900);
			endDate.setYear(getCursorYear().intValue() - 1900);
			beginDate.setMonth(getCursorMonth().intValue() - 1);
			endDate.setMonth(getCursorMonth().intValue() - 1);
			try {
				data = data
						+ "\n categories: ['1-р долоо хоног', '2-р долоо хоног', '3-р долоо хоног', '4-р долоо хоног','5-р долоо хоног' ]";
				data = data + "\n },";
				data = data + "\n series: [";

				getEmployees();
				employees = dashboardLogic.getEmployees(getCursorSubOrganization().getPkId());

				for (Employee e : employees) {
					data = data + "\n { name: " + "'" + e.getFirstName() + "',";
					data = data + "\n data: [";
					int j = 1;
					for (int i = 1; i <= 5; i++, j += 7) {

						Calendar calendar = Calendar.getInstance();
						calendar.set(Calendar.MONTH, cursorMonth.intValue() - 1);

						int numDays = calendar.getActualMaximum(Calendar.DATE);

						if (j > numDays)
							j = numDays;
						beginDate.setDate(j);
						if (i * 7 > numDays)
							endDate.setDate(numDays);
						else
							endDate.setDate(j + 6);

						if (dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0) == null) {
							data = data + "null,";
						} else {
							data = data + dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0)
									.toString() + ",";
						}
					}
					data = data + "]},";

				}

				data = data + "]";
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.toString());
				ex.printStackTrace();
			}
			break;
		}

		case 55: {
			// 7 хоногийг өдөрт хувааснаар
			beginDate.setYear(getCursorYear().intValue() - 1900);
			endDate.setYear(getCursorYear().intValue() - 1900);
			beginDate.setMonth(getCursorMonth().intValue() - 1);
			endDate.setMonth(getCursorMonth().intValue() - 1);

			try {
				int i = 0;
				int j = 0;
				data = data
						+ "\n categories: ['1-р өдөр', '2-р өдөр', '3-р өдөр', '4-р өдөр','5-р өдөр', '6-р өдөр', '7-р өдөр' ]";
				if (getCursorWeek().intValue() == 1) {
					i = 1;
					j = 7;
				} else if (getCursorWeek().intValue() == 2) {
					i = 8;
					j = 14;
				} else if (getCursorWeek().intValue() == 3) {
					i = 15;
					j = 21;
				} else if (getCursorWeek().intValue() == 4) {
					i = 22;
					j = 28;
				} else if (getCursorWeek().intValue() == 5) {
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.MONTH, cursorMonth.intValue() - 1);
					int numDays = calendar.getActualMaximum(Calendar.DATE);
					if (numDays < 29) {
						i = 99;
						j = 999;
					}
				}
				data = data + "\n },";
				data = data + "\n series: [";

				getEmployees();
				employees = dashboardLogic.getEmployees(getCursorSubOrganization().getPkId());

				for (Employee e : employees) {
					data = data + "\n { name: " + "'" + e.getFirstName() + "',";
					data = data + "\n data: [";

					for (int k = i; k <= j; k++) {
						if (i == 99) {
							data = data + "'null','null','null','null','null','null','null',";
							break;
						} else {
							beginDate.setDate(k);
							Calendar calendar = Calendar.getInstance();
							calendar.set(Calendar.MONTH, getCursorMonth().intValue() - 1);
							@SuppressWarnings("unused")
							int numDays = calendar.getActualMaximum(Calendar.DATE);
							endDate.setDate(k);

							if (dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0) == null) {
								data = data + "null,";
							} else {
								data = data + dashboardLogic.getBillByEmployee(e.getPkId(), beginDate, endDate).get(0)
										.toString() + ",";
							}
						}
					}
					data = data + "]},";

				}

				data = data + "]";
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.toString());
				ex.printStackTrace();
			}

		}
			break;
		}
		return data;

	}
	

	public void loadData() {
		getSubOrganizations();
		getEmployees();
		getCustomerRequests();
		getTreatments();
		getCustomerRequests();
		employeeWithInspection();
		examinationDashboard();

		try {
			employees = dashboardLogic.getEmployees(BigDecimal.ZERO);

			if (getCursorSubOrganization() == null || getCursorSubOrganization().getPkId() == null
					|| getCursorSubOrganization().getName() == null) {

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:soSelector");
		context.update("form:lineChartScript form:barChartScript form:subOrgaLineChartScript");
	}

	public List<IncomePlan> getMonthlyPlan() {
		if (monthlyPlan == null)
			monthlyPlan = new ArrayList<IncomePlan>();

		return monthlyPlan;
	}

	public void setMonthlyPlan(List<IncomePlan> monthlyPlan) {
		this.monthlyPlan = monthlyPlan;
	}

	public IncomePlan getCursorPriceHistory() {
		if (cursorPriceHistory == null)
			cursorPriceHistory = new IncomePlan();
		return cursorPriceHistory;
	}

	public void setCursorPriceHistory(IncomePlan cursorPriceHistory) {
		this.cursorPriceHistory = cursorPriceHistory;
	}

	public void saveCursorPriceHistory() {
		try {
			dashboardLogic.saveMonthlyPlan(cursorPriceHistory, userSessionController.getLoggedInfo());
			userSessionController.showMessage(99);
			cursorPriceHistory.setPrice(BigDecimal.ZERO);
			updatePriceList();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}

	}

	public void updatePriceList() {
		try {
			getMonthlyPlan();
			cursorPriceHistory.setPrice(BigDecimal.ZERO);
			monthlyPlan = dashboardLogic.getPlanHistory(cursorPriceHistory.getSubOrganizationPkId());
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:priceList form:cp");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
	}
	
	public void filterPaymentDtlType(){
		
		try {
			getSubOrgaSelectOne();
			paymentDtlType = dashboardLogic.getPaymentDtlType(getSubOrgaSelectOne(), getBeginDate(), getEndDate());
			paymentTotal = dashboardLogic.getTotalPaymentDtl(getSubOrgaSelectOne(), getBeginDate(), getEndDate());
			
			
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
	}

	public SubOrganization getSelectedSubOrganization() {
		if (selectedSubOrganization == null)
			selectedSubOrganization = new SubOrganization();
		return selectedSubOrganization;
	}

	public void setSelectedSubOrganization(SubOrganization selectedSubOrganization) {
		this.selectedSubOrganization = selectedSubOrganization;
	}

	public List<BigDecimal> getYearList() {
		if (yearList == null)
			yearList = new ArrayList<BigDecimal>();
		yearList.clear();
		@SuppressWarnings("deprecation")
		int year = new Date().getYear();
		for (int i = 2014; i <= year + 1900; i++)
			yearList.add(new BigDecimal(i));
		return yearList;
	}

	public List<BigDecimal> getSeasonList() {
		if (seasonList == null)
			seasonList = new ArrayList<BigDecimal>();
		seasonList.clear();
		for (int i = 1; i <= 4; i++)
			seasonList.add(new BigDecimal(i));
		return seasonList;
	}

	public void setSeasonList(List<BigDecimal> seasonList) {
		this.seasonList = seasonList;
	}

	public void setYearList(List<BigDecimal> yearList) {
		this.yearList = yearList;
	}

	public List<BigDecimal> getMonthList() {
		if (monthList == null)
			monthList = new ArrayList<BigDecimal>();
		monthList.clear();
		for (int i = 1; i <= 12; i++)
			monthList.add(new BigDecimal(i));
		return monthList;
	}

	public void setMonthList(List<BigDecimal> monthList) {
		this.monthList = monthList;
	}

	public List<BigDecimal> getWeekList() {
		if (weekList == null)
			weekList = new ArrayList<BigDecimal>();
		weekList.clear();
		for (int i = 1; i <= 5; i++)
			weekList.add(new BigDecimal(i));

		return weekList;
	}

	public void setWeekList(List<BigDecimal> weekList) {
		this.weekList = weekList;
	}

	@SuppressWarnings("deprecation")
	public BigDecimal getCursorYear() {
		if (cursorYear == null)
			cursorYear = new BigDecimal(new Date().getYear() + 1900);
		return cursorYear;
	}

	public void setCursorYear(BigDecimal cursorYear) {
		this.cursorYear = cursorYear;
	}

	public BigDecimal getCursorSeason() {
		if (cursorSeason == null)
			cursorSeason = new BigDecimal(1);
		return cursorSeason;
	}

	public void setCursorSeason(BigDecimal cursorSeason) {
		this.cursorSeason = cursorSeason;
	}

	@SuppressWarnings("deprecation")
	public BigDecimal getCursorMonth() {
		if (cursorMonth == null)
			cursorMonth = new BigDecimal(new Date().getMonth() + 1);
		return cursorMonth;
	}

	public void setCursorMonth(BigDecimal cursorMonth) {
		this.cursorMonth = cursorMonth;
	}

	public BigDecimal getCursorWeek() {
		if (cursorWeek == null)
			cursorWeek = new BigDecimal(1);
		return cursorWeek;
	}

	public void setCursorWeek(BigDecimal cursorWeek) {
		this.cursorWeek = cursorWeek;
	}

	public int getLineChartTypeSelector() {
		if (lineChartTypeSelector == 0)
			lineChartTypeSelector = 11;
		return lineChartTypeSelector;
	}

	public void setLineChartTypeSelector(int lineChartTypeSelector) {
		this.lineChartTypeSelector = lineChartTypeSelector;
	}

	public void yearSelected() {
		setLineChartTypeSelector(11);
		createLineChart();
	}

	public void yearSelected1() {
		createSubOrgaLineChart();
	}
	
	public void yearSelected2(){
		createSubAmbuLineChart();
	}
	
	public void yearSelectedChart1(){
		createAreaChart();
	}

	public void seasonSelected() {
		if (getCursorSeason().intValue() == 6) {
			setLineChartTypeSelector(22);
			createLineChart();
		} else {
			setLineChartTypeSelector(33);
			createLineChart();
		}
	}

	public void monthSelected() {
		setLineChartTypeSelector(44);
		createLineChart();
	}

	public void weekSelected() {
		setLineChartTypeSelector(55);
		createLineChart();
	}

	public void subOrganizationSelected() {
		createLineChart();
	}

	public int getPieChartTypeSelector() {
		return pieChartTypeSelector;
	}

	public void setPieChartTypeSelector(int pieChartTypeSelector) {
		this.pieChartTypeSelector = pieChartTypeSelector;
	}

	public void employeeWithInspection() {
		try {
			List<Employee> filter = dashboardLogic.getEmployeeWithInspectionCount(getBeginDate(), getEndDate());
			getEmployeeWithInspectionCount().clear();
			for (int i = filter.size() - 1; i >= 0; i--) {
				employeeWithInspectionCount.add(filter.get(i));
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
	}
	
	public void employeeRequest(){
		try{
			getEmployeeRequestCount();
			employeeRequestCount = dashboardLogic.getEmployeeRequestCount(getMonthBeginDate(), getEndDate());
			
		}
		catch (Exception ex){
			userSessionController.showErrorMessage(ex.toString());
		}
	}

	public void examinationDashboard() {
		try {

			examinationCounts = dashboardLogic.getExaminationCount(getBeginDate(), getEndDate());
			for (ExaminationDashboard examinationDashbaord : examinationCounts) {
			//System.out.println("Customer Count: = " + examinationDashbaord.getCustomerCount());
				setAllCustomerCount(examinationDashbaord.getCustomerCount());
			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
	}

	public void examinationEmployee(BigDecimal examinationPkId) {

		try {

			examinationEmployees = dashboardLogic.getEmployeeCount(examinationPkId, getAllCustomerCount(),
					getBeginDate(), getEndDate());

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:exaEmp");
		context.execute("examinationEmployees.show()");
	}

	public void loginDialogShow() {

		try {

			loginDialogs = dashboardLogic.getLoginDialog(getBeginDate());
			// getUserSessionController().CheckLogin();

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}
	}

	public void upDialogShow(BigDecimal dialogPkId) {
		try {

			upLoginDialogs = dashboardLogic.setLoginDialog(dialogPkId);

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.toString());
			ex.printStackTrace();
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:updateDialog");
		context.execute("PF('updatedialogshow').show();");
	}

	public void deleteDialogShow(LoginDialog d) {
		try {
			d.setStatus(Tool.DELETE);
			dashboardLogic.deleteLoginDialog(d);
			getUserSessionController().showWarningMessage("Устгагдлаа");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:loginDialogsContainer");
		} catch (Exception e) {
			userSessionController.showErrorMessage(e.toString());
			e.printStackTrace();
		}
	}

	public void saveLoginDialog() {
		Date d = new Date();
		try {

			setEditorText(getEditorText().replaceAll("&nbsp;", " "));

			// System.out.println("EditorText: = " + getEditorText() +
			// "tugsgul");

			if (!getEditorText().trim().equals("")) {
				dashboardLogic.saveLoginDialog(getLodialog(), getEditorText(), d);
				getUserSessionController().showWarningMessage("Амжиллттай хадгалагдлаа");
				setEditorText("");
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:loginDialogsContainer");
				context.execute("PF('newdialogshow').hide();");
			} else {
				getUserSessionController().showWarningMessage("Мэдээллээ оруулна уу!!!");
			}

		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateLoginDialog(LoginDialog dialog) {

		dialog.setStatus(Tool.MODIFIED);
		try {
			dashboardLogic.updateLoginDialog(dialog);
			upLoginDialogs = dashboardLogic.setLoginDialog(dialog.getPkId());
			getUserSessionController().showWarningMessage("Амжилттай шинэчлэгдлээ");
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:loginDialogsContainer");
			context.execute("PF('updatedialogshow').hide();");

		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void createTreeTable(List<EmployeeRequestCount> list, List<EmployeeRequestCount> list1){
		
		EmployeeRequestCount erc = new EmployeeRequestCount();
		treeDashboard = new DefaultTreeNode(erc, null);
		for (EmployeeRequestCount emp : list){
			EmployeeRequestCount temp = new EmployeeRequestCount();
			temp.setSubOrganizationName(emp.getSubOrganizationName());
			temp.setEmployeeFirstName(null);
			temp.setTreeTable(false);
			TreeNode node = new DefaultTreeNode(temp, treeDashboard);
			for (EmployeeRequestCount emp1 : list1){
				if (emp.getSubOrganizationName().equals(emp1.getSubOrganizationName())){
				EmployeeRequestCount temp1 = new EmployeeRequestCount();
				temp1.setTreeTable(true);
				temp1.setSubOrganizationName(emp1.getEmployeeFirstName());
				temp1.setTotal(emp1.getTotal());
				temp1.setNotInsurance(emp1.getNotInsurance());
				temp1.setIsInsurance(emp1.getIsInsurance());
				temp1.setPreliminary(emp1.getPreliminary());
				temp1.setLive(emp1.getLive());
				temp1.setExpress(emp1.getExpress());
				temp1.setInMood(emp1.getInMood());
				temp1.setSaveMood(emp1.getSaveMood());
				temp1.setOutMood(emp1.getOutMood());
				temp1.setReInspection(emp1.getReInspection());
				temp1.setInspection(emp1.getInspection());
				TreeNode n = new DefaultTreeNode(temp1, node);
				}
			}
		}
	}
	
	public TreeNode filterTreeTable(){
		try{
			List<EmployeeRequestCount> list = dashboardLogic.getSubOrganizationName(getMonthBeginDate(), getEndDate());
			List<EmployeeRequestCount> list1 = dashboardLogic.getEmployeeRequestCount(getMonthBeginDate(), getEndDate());
			createTreeTable(list, list1);
		}
		catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		return treeDashboard;
	}

	public List<Employee> getEmployeeWithInspectionCount() {
		if (employeeWithInspectionCount == null)
			employeeWithInspectionCount = new ArrayList<Employee>();
		return employeeWithInspectionCount;
	}

	public void setEmployeeWithInspectionCount(List<Employee> employeeWithInspectionCount) {

		this.employeeWithInspectionCount = employeeWithInspectionCount;
	}

	boolean bool = false;

	public void setReporm() {
		bool = true;
		Reporm();
	}

	Timer timer = new Timer();

	@PostConstruct
	public void Reporm() {
		// System.out.println("Success!!!");
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (bool) {
					RequestContext context = RequestContext.getCurrentInstance();
					context.execute("PF('repormdialogshow').show();");
					bool = false;
				}
			}
		}, 5000, 1000);
	}

	@SuppressWarnings("deprecation")
	public Date getBeginDate() {
		if (beginDate == null) {
			beginDate = new Date();
			beginDate.setMonth(0);
			beginDate.setDate(1);
			beginDate.setHours(0);
			beginDate.setMinutes(0);
		}
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
	

	public Date getMonthBeginDate() {
		if (monthBeginDate == null){
			monthBeginDate = new Date();
			monthBeginDate.setMonth(endDate.getMonth());
			monthBeginDate.setDate(1);
			monthBeginDate.setHours(0);
			monthBeginDate.setMinutes(0);
		}
		return monthBeginDate;
	}

	public void setMonthBeginDate(Date monthBeginDate) {
		this.monthBeginDate = monthBeginDate;
	}

	public BigDecimal getCursorYear1() {
		if (cursorYear1 == null)
			cursorYear1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		return cursorYear1;
	}

	public void setCursorYear1(BigDecimal cursorYear1) {
		this.cursorYear1 = cursorYear1;
	}
	

	public BigDecimal getCursorYear2() {
		if (cursorYear2 == null)
			cursorYear2 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		
		return cursorYear2;
	}

	public void setCursorYear2(BigDecimal cursorYear2) {
		this.cursorYear2 = cursorYear2;
	}

	public List<ExaminationDashboard> getExaminationCounts() {
		if (examinationCounts == null)
			examinationCounts = new ArrayList<ExaminationDashboard>();
		return examinationCounts;
	}

	public void setExaminationCounts(List<ExaminationDashboard> examinationCounts) {
		this.examinationCounts = examinationCounts;
	}

	public List<ExaminationEmployeeDashboard> getExaminationEmployees() {
		if (examinationEmployees == null)
			examinationEmployees = new ArrayList<ExaminationEmployeeDashboard>();
		return examinationEmployees;
	}

	public void setExaminationEmployees(List<ExaminationEmployeeDashboard> examinationEmployees) {
		this.examinationEmployees = examinationEmployees;
	}

	public long getAllCustomerCount() {
		return allCustomerCount;
	}

	public void setAllCustomerCount(long allCustomerCount) {
		this.allCustomerCount = allCustomerCount;
	}

	public String getEditorText() {
		return editorText;
	}

	public void setEditorText(String editorText) {
		this.editorText = editorText;
	}

	public LoginDialog getLodialog() {
		if (!Tool.ADDED.equals(lodialog)) {
			lodialog = new LoginDialog();
			lodialog.setStatus(Tool.ADDED);
		}
		return lodialog;
	}

	public void setLodialog(LoginDialog lodialog) {
		this.lodialog = lodialog;
	}

	public List<LoginDialog> getLoginDialogs() {
		return loginDialogs;
	}

	public void setLoginDialogs(List<LoginDialog> loginDialogs) {
		this.loginDialogs = loginDialogs;
	}

	public List<LoginDialog> getUpLoginDialogs() {
		return upLoginDialogs;
	}

	public void setUpLoginDialogs(List<LoginDialog> upLoginDialogs) {
		this.upLoginDialogs = upLoginDialogs;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public List<EmployeeRequestCount> getEmployeeRequestCount() {
		if(employeeRequestCount == null)
			employeeRequestCount = new ArrayList<EmployeeRequestCount>();
			return employeeRequestCount;
	}

	public void setEmployeeRequestCount(List<EmployeeRequestCount> employeeRequestCount) {
		this.employeeRequestCount = employeeRequestCount;
	}

	public String getSubOrgaSelectOne() {
		return subOrgaSelectOne;
	}

	public void setSubOrgaSelectOne(String subOrgaSelectOne) {
		this.subOrgaSelectOne = subOrgaSelectOne;
	}

	public List<PaymentEntity> getPaymentDtlType() {
		return paymentDtlType;
	}

	public void setPaymentDtlType(List<PaymentEntity> paymentDtlType) {
		this.paymentDtlType = paymentDtlType;
	}

	public String getBeginDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		beginDateStr = sdfr.format(getBeginDate());
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		endDateStr = sdfr.format(getEndDate());
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getMonthBeginDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		monthBeginDateStr = sdfr.format(getMonthBeginDate());
		return monthBeginDateStr;
	}

	public void setMonthBeginDateStr(String monthBeginDateStr) {
		this.monthBeginDateStr = monthBeginDateStr;
	}

	public BigDecimal getCursorYearChart1() {
		if (cursorYearChart1 == null)
			cursorYearChart1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		return cursorYearChart1;
	}

	public void setCursorYearChart1(BigDecimal cursorYearChart1) {
		this.cursorYearChart1 = cursorYearChart1;
	}

	public List<PaymentEntity> getPaymentTotal() {
		if (paymentTotal == null)
			paymentTotal = new ArrayList<PaymentEntity>();
		return paymentTotal;
	}

	public void setPaymentTotal(List<PaymentEntity> paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public TreeNode getTreeDashboard() {
		if (treeDashboard == null){
			filterTreeTable();
		}
		return treeDashboard;
	}

	public void setTreeDashboard(TreeNode treeDashboard) {
		this.treeDashboard = treeDashboard;
	}

	public boolean isFilterCheck() {
		return filterCheck;
	}

	public void setFilterCheck(boolean filterCheck) {
		this.filterCheck = filterCheck;
	}


}
package hospital.web.controller;

import hospital.businessentity.ReportFilter;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicReportLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Item;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.entity.ViewCustomer;
import hospital.report.DiscountPriceReport;
import hospital.report.DoctorService;
import hospital.report.InspectionTypeReport;
import hospital.report.ItemReportHdr;
import hospital.report.MedicalExaminationReport;
import hospital.report.ReportMonth;
import hospital.report.ReportSubOrganization;
import hospital.report.TreatmentRequestReport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SessionScoped
@ManagedBean(name = "reportController")
public class ReportController {

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicReport")
	ILogicReportLocal logicReport;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{twoController}")
	private TwoController twoController;

	@ManagedProperty(value = "#{logicOneController}")
	private InspectionController oneController;

	private ReportFilter reportFillter;

	private Date beginDate;
	private Date endDate;

	private BigDecimal meAllAmount1;
	private BigDecimal meAllAmount2;
	private BigDecimal meAllAmount3;

	private ReportFilter filter;
	private List<Employee> employees;
	private List<ViewCustomer> customers;
	private List<Item> items;
	private List<SubOrganization> subOrganizations;
	private List<Employee> filteredEmployees;
	private List<Employee> filteredEmployees1;

	// 1
	private List<DoctorService> doctorServices;
	private List<ReportMonth> reportMonths;
	private List<MedicalExaminationReport> examinationReports;
	private List<ItemReportHdr> reportHdrs;
	private List<InspectionTypeReport> inspectionTypeReports;
	private List<DiscountPriceReport> discountPriceReports;

	// 8
	private List<ReportSubOrganization> report8List;
	private ReportSubOrganization report8;
	private String reportDateStr1;
	private String reportDateStr2;
	private String reportDateStr3;
	private String reportDateStr4;
	private String reportDateStr5;

	// CustomerTReatmentReport
	private List<Treatment> treatments;
	private List<TreatmentRequestReport> treatmentRequests;
	private BigDecimal selectedTreatmentPkId;
	private String selectedTreatmentName;

	public ReportController() {
		super();
	}

	public String InspectionHistoryByCash() {

		return "";
	}

	public void prepareTreatmentRequest() {
		getTreatments();
		try {
			treatments = logicReport.getRequestTreatments();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:reportTreatments");
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void treatmentSelected() {
		getTreatmentRequests();
		try {
			treatmentRequests = logicReport.getTreatmentRequest(getBeginDate(), getEndDate(),
					getSelectedTreatmentPkId());
			for(Treatment t:treatments)
			{
				if(t.getPkId().compareTo(getSelectedTreatmentPkId())==0)
					selectedTreatmentName = t.getName();
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:printableSection");
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	private String getDateStr(Date dte) {
		String ret = "";
		switch (dte.getMonth()) {
		case (0):
			ret = "I";
			break;
		case (1):
			ret = "II";
			break;
		case (2):
			ret = "III";
			break;
		case (3):
			ret = "IV";
			break;
		case (4):
			ret = "V";
			break;
		case (5):
			ret = "VI";
			break;
		case (6):
			ret = "VII";
			break;
		case (7):
			ret = "VIII";
			break;
		case (8):
			ret = "IX";
			break;
		case (9):
			ret = "X";
			break;
		case (10):
			ret = "XI";
			break;
		case (11):
			ret = "XII";
			break;
		}
		ret += "/";
		ret += dte.getDate();
		return ret;
	}

	public void report8() {
		try {
			report8List = logicReport.getReport8(getFilter().getYear(), getFilter().getMonth(), getFilter().getWeek());
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, getFilter().getYear());
			cal.set(Calendar.MONTH, getFilter().getMonth() - 1);
			cal.set(Calendar.WEEK_OF_MONTH, getFilter().getWeek());
			cal.set(Calendar.DAY_OF_WEEK, 2);
			Date date1 = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, 3);
			Date date2 = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, 4);
			Date date3 = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, 5);
			Date date4 = cal.getTime();
			cal.set(Calendar.DAY_OF_WEEK, 6);
			Date date5 = cal.getTime();
			reportDateStr1 = getDateStr(date1);
			reportDateStr2 = getDateStr(date2);
			reportDateStr3 = getDateStr(date3);
			reportDateStr4 = getDateStr(date4);
			reportDateStr5 = getDateStr(date5);
			for (ReportSubOrganization org : report8List) {
				getReport8().setSumAmount(getReport8().getSumAmount().add(org.getSumAmount()));
				getReport8().setSumCount(getReport8().getSumCount() + org.getSumCount());
				getReport8().setValue1Amount(getReport8().getValue1Amount().add(org.getValue1Amount()));
				getReport8().setValue1Count(getReport8().getValue1Count() + org.getValue1Count());
				getReport8().setValue2Amount(getReport8().getValue2Amount().add(org.getValue2Amount()));
				getReport8().setValue2Count(getReport8().getValue2Count() + org.getValue2Count());
				getReport8().setValue3Amount(getReport8().getValue3Amount().add(org.getValue3Amount()));
				getReport8().setValue3Count(getReport8().getValue3Count() + org.getValue3Count());
				getReport8().setValue4Amount(getReport8().getValue4Amount().add(org.getValue4Amount()));
				getReport8().setValue4Count(getReport8().getValue4Count() + org.getValue4Count());
				getReport8().setValue5Amount(getReport8().getValue5Amount().add(org.getValue5Amount()));
				getReport8().setValue5Count(getReport8().getValue5Count() + org.getValue5Count());
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:report8Dialog");
			context.execute("PF('report8Dialog').show();");
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void doctorServiceReport() {
		// Ð­Ð¼Ñ‡Ð¸Ð¹Ð½ Ò¯Ð¹Ð»Ñ‡Ð¸Ð»Ð³Ñ�Ñ�Ð½Ð¸Ð¹ Ñ‚Ð°Ð¹Ð»Ð°Ð½
		// /Ð¨Ð¸Ð½Ð¶Ð¸Ð»Ð³Ñ�Ñ�/
		try {

			if (filter.getSubOrganizationPkId() != null && !filter.getSubOrganizationPkId().equals(BigDecimal.ZERO)) {
				doctorServices = logicReport.getDoctorServices(filter.getBeginDate(), filter.getEndDate(),
						filter.getSubOrganizationPkId(), filter.getEmployeePkId());

			} else
				doctorServices = logicReport.getDoctorServices(filter.getBeginDate(), filter.getEndDate(),
						getUserSessionController().getLoggedInfo());
			// filter.setAllAmount(BigDecimal.ZERO);
			// for (DoctorService item : doctorServices) {
			// filter.setAllAmount(filter.getAllAmount().add(
			// item.getSumAmount()));
			// }

		} catch (Exception ex) {

		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:doctorServiceReport");
		context.execute("PF('doctorServiceReport').show()");
	}

	public void doctorMedicalExaminationReport() {
		// Ò®Ð·Ð»Ñ�Ð³Ð¸Ð¹Ð½ Ñ‚Ð°Ð¹Ð»Ð°Ð½
		try {

			examinationReports = logicReport.getDoctorMedicalExaminationReport(filter.getSubOrganizationPkId(),
					filter.getBeginDate(), filter.getEndDate(), getUserSessionController().getLoggedInfo());
			filter.setAllAmount(BigDecimal.ZERO);
			// for (DoctorService item : doctorServices) {
			// filter.setAllAmount(filter.getAllAmount().add(item.getSumAmount()));
			// }
			examinationReports = logicReport.getDoctorMedicalExaminationReport(filter.getSubOrganizationPkId(),
					filter.getBeginDate(), filter.getEndDate(), getUserSessionController().getLoggedInfo());

			setMeAllAmount1(BigDecimal.ZERO);
			setMeAllAmount2(BigDecimal.ZERO);
			setMeAllAmount3(BigDecimal.ZERO);

			for (MedicalExaminationReport medicalReport : examinationReports) {
				System.out.println("ExaminationReports List: = " + examinationReports);
				System.out.println("getSumAmount1: = " + medicalReport.getSumAmount1());
				System.out.println("getSumAmount2: = " + medicalReport.getSumAmount2());
				System.out.println("getSumAmount3: = " + medicalReport.getSumAmount3());
				setMeAllAmount1(getMeAllAmount1().add(medicalReport.getSumAmount1()));
				setMeAllAmount2(getMeAllAmount2().add(medicalReport.getSumAmount2()));
				setMeAllAmount3(getMeAllAmount3().add(medicalReport.getSumAmount3()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:doctorMedicalExaminationReport");
		context.execute("PF('doctorMedicalExaminationReport').show()");
	}

	public void cashierMothReport() {
		// ÐšÐ°Ñ�Ñ�Ñ‹Ð½ Ñ�Ð°Ñ€Ñ‹Ð½ Ð¼Ñ�Ð´Ñ�Ñ�

		try {

			reportMonths = logicReport.getCashierMothReport(filter.getBeginDate(), filter.getEndDate(), getUserSessionController().getLoggedInfo());
			filter.setAllAmount(BigDecimal.ZERO);
			filter.setAllAmount1(BigDecimal.ZERO);
			filter.setAllAmount2(BigDecimal.ZERO);
			filter.setAllCustomerCount1(0);
			filter.setAllCustomerCount2(0);
			filter.setAllCustomerCount3(0);
			filter.setAllCustomerCount4(0);
			filter.setAllCustomerCount5(0);
			filter.setAllCustomerCount6(0);
			filter.setAllInAmount1(BigDecimal.ZERO);
			filter.setAllInAmount2(BigDecimal.ZERO);
			filter.setAllInAmount3(BigDecimal.ZERO);
			filter.setAllInAmount4(BigDecimal.ZERO);
			filter.setAllInAmount5(BigDecimal.ZERO);
			filter.setAllInAmount6(BigDecimal.ZERO);
			for (ReportMonth month : reportMonths) {
				filter.setAllAmount(filter.getAllAmount().add(month.getSumAmount()));
				filter.setAllAmount1(filter.getAllAmount1().add(month.getSumAmount1()));
				filter.setAllAmount2(filter.getAllAmount2().add(month.getSumAmount2()));
				filter.setAllCustomerCount1(filter.getAllCustomerCount1() + month.getCustomerCount1());
				filter.setAllCustomerCount2(filter.getAllCustomerCount2() + month.getCustomerCount2());
				filter.setAllCustomerCount3(filter.getAllCustomerCount3() + month.getCustomerCount3());
				filter.setAllCustomerCount4(filter.getAllCustomerCount4() + month.getCustomerCount4());
				filter.setAllCustomerCount5(filter.getAllCustomerCount5() + month.getCustomerCount5());
				filter.setAllCustomerCount6(filter.getAllCustomerCount6() + month.getCustomerCount6());
				filter.setAllInAmount1(filter.getAllInAmount1().add(month.getInAmount1()));
				filter.setAllInAmount2(filter.getAllInAmount2().add(month.getInAmount2()));
				filter.setAllInAmount3(filter.getAllInAmount3().add(month.getInAmount3()));
				filter.setAllInAmount4(filter.getAllInAmount4().add(month.getInAmount4()));
				filter.setAllInAmount5(filter.getAllInAmount5().add(month.getInAmount5()));
				filter.setAllInAmount6(filter.getAllInAmount6().add(month.getInAmount6()));
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		// getUserSessionController().showMessage(49);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:cashierMothReport");
		context.execute("PF('cashierMothReport').show()");
	}

	public void doctorInspectionHistory() {
		// Ð­Ð¼Ñ‡Ð¸Ð»Ð³Ñ�Ñ�Ð½Ð¸Ð¹ Ñ‚Ò¯Ò¯Ñ…

		try {

			// filter.setCustomer(logicTwo.getByPkId(ViewCustomer.class,
			// filter.getCustomerPkId()));
			// doctorServices = logicReport.getDoctorInspectionHistory(filter
			// .getCustomerPkId(), filter.getBeginDate(), filter
			// .getEndDate(), getUserSessionController().getLoggedInfo());
			// filter.setAllAmount(BigDecimal.ZERO);
			// for (DoctorService item : doctorServices) {
			// filter.setAllAmount(filter.getAllAmount().add(
			// item.getSumAmount()));
			// }

		} catch (Exception ex) {

		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:doctorInspectionHistory");
		context.execute("PF('doctorInspectionHistory').show()");
	}

	@SuppressWarnings("unused")
	public String inspectionHistory() {
		try {
			List<EmployeeRequest> employeeRequests = new ArrayList<EmployeeRequest>();
			employeeRequests.add(new EmployeeRequest());
			JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(employeeRequests);
			JRDataSource dataSource = new JRBeanCollectionDataSource(employeeRequests);
			// getApplicationController().getPath() +
			// "report/reports/inspectionHistory.jrxml";
			String reportPath = "D:\\DentalHospital\\DentalHospital\\Web\\WebContent\\resource\\report\\inspectionHistory.jasper";

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("companyName", "companyName Ã�â€�Ã�Â°Ã�Â²Ã�Â°Ã�Â°Ã�Â´Ã�Â¾Ã‘â‚¬Ã�Â¶");
			parameters.put("mmm", getTwoController().getSelectedCashCustomer().getCustomerImage());

			JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, dataSource);
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=inspectionHistory.pdf");
			ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
			FacesContext.getCurrentInstance().responseComplete();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return "";
	}

	public void itemReport() {

		try {
			reportHdrs = logicReport.getItemReport(filter.getSubOrganizationPkId(), filter.getCustomerPkId(), filter.getBeginDate(), filter.getEndDate(),
					getUserSessionController().getLoggedInfo());
			filter.setAllAmount(BigDecimal.ZERO);
			for (ItemReportHdr item : reportHdrs) {
				filter.setAllAmount(filter.getAllAmount().add(item.getSumAmount()));
			}
		} catch (Exception ex) {

		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:itemReport");
		context.execute("PF('itemReport').show()");
	}

	// Ã�Å¡Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘â€¹Ã�Â½ Ã‘ï¿½Ã�Â°Ã‘â‚¬Ã‘â€¹Ã�Â½
	// Ã�Â¼Ã‘ï¿½Ã�Â´Ã‘ï¿½Ã‘ï¿½
	public InspectionController getOneController() {
		return oneController;
	}

	public void setOneController(InspectionController oneController) {
		this.oneController = oneController;
	}

	public TwoController getTwoController() {
		return twoController;
	}

	public void setTwoController(TwoController twoController) {
		this.twoController = twoController;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public ReportFilter getReportFillter() {
		if (reportFillter == null)
			reportFillter = new ReportFilter();
		return reportFillter;
	}

	public void setReportFillter(ReportFilter reportFillter) {
		this.reportFillter = reportFillter;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	@SuppressWarnings("deprecation")
	public Date getBeginDate() {
		if (beginDate == null)
			beginDate = new Date();
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(1);
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@SuppressWarnings("deprecation")
	public Date getEndDate() {
		if (endDate == null)
			endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Employee> getEmployees() {
		try {
			employees = logicTwo.getEmployeeReport(getUserSessionController().getLoggedInfo());
		} catch (Exception ex) {

		}
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<DoctorService> getDoctorServices() {
		return doctorServices;
	}

	public void setDoctorServices(List<DoctorService> doctorServices) {
		this.doctorServices = doctorServices;
	}

	public ReportFilter getFilter() {
		if (filter == null) {
			filter = new ReportFilter();
			Date dte = new Date();
			filter.setYear(1900 + dte.getYear());
			filter.setMonth(1 + dte.getMonth());
			filter.setWeek(1);
		}
		return filter;
	}

	public void setFilter(ReportFilter filter) {
		this.filter = filter;
	}

	public List<ViewCustomer> getCustomers() {
		try {
			customers = logicCustomer.getCustomerReport();
		} catch (Exception ex) {

		}
		return customers;
	}

	public void setCustomers(List<ViewCustomer> customers) {
		this.customers = customers;
	}

	public List<ReportMonth> getReportMonths() {
		return reportMonths;
	}

	public void setReportMonths(List<ReportMonth> reportMonths) {
		this.reportMonths = reportMonths;
	}

	public List<MedicalExaminationReport> getExaminationReports() {
		return examinationReports;
	}

	public void setExaminationReports(List<MedicalExaminationReport> examinationReports) {
		this.examinationReports = examinationReports;
	}

	public List<ItemReportHdr> getReportHdrs() {
		return reportHdrs;
	}

	public void setReportHdrs(List<ItemReportHdr> reportHdrs) {
		this.reportHdrs = reportHdrs;
	}

	public List<Item> getItems() {
		try {
			items = logicTwo.getItems(getUserSessionController().getLoggedInfo());
		} catch (Exception ex) {

		}
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<SubOrganization> getSubOrganizations() {
		try {
			subOrganizations = logicTwo.getSubOrganizations(getUserSessionController().getLoggedInfo());
		} catch (Exception ex) {

		}
		return subOrganizations;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}
	
	public void fillInspectionType() {
		//Эмчийн үзлэгийн тайлан (АМ-1Б)
		try {
			getInspectionTypeReports();
			inspectionTypeReports = logicReport.getInspectionTypeReport(filter);
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionTypeReport");
		context.execute("PF('inspectionTypeReport').show();");
	}
	
	public List<Employee> getFilteredEmployees1() {
		try {
			if(filteredEmployees1 == null) filteredEmployees1 = logicTwo.getEmployeesBySubOrganizationPkId (getUserSessionController().getLoggedInfo(), getFilter().getSubOrganizationPkId());
		} catch (Exception ex) {

		}
		return filteredEmployees1;
	}
	
	public void setFilteredEmployees1(List<Employee> filteredEmployees1) {
		this.filteredEmployees1 = filteredEmployees1;
	}

	public List<Employee> getFilteredEmployees() {
		try {
			filteredEmployees = logicTwo.getEmployeesBySubOrganizationPkId(getUserSessionController().getLoggedInfo(),
					getFilter().getSubOrganizationPkId());
		} catch (Exception ex) {

		}
		return filteredEmployees;
	}

	public void setFilteredEmployees(List<Employee> filteredEmployees) {
		this.filteredEmployees = filteredEmployees;
	}

	public void discountPriceReport() {

		try {

			discountPriceReports = logicReport.getDiscountPriceReport(filter.getBeginDate(), filter.getEndDate(),
					getUserSessionController().getLoggedInfo());

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:discountPriceReport");
		context.execute("PF('discountPriceReport').show()");
	}

	public List<DiscountPriceReport> getDiscountPriceReports() {
		if (discountPriceReports == null)
			discountPriceReports = new ArrayList<DiscountPriceReport>();
		return discountPriceReports;
	}

	public void setDiscountPriceReports(List<DiscountPriceReport> discountPriceReports) {
		this.discountPriceReports = discountPriceReports;
	}

	public List<InspectionTypeReport> getInspectionTypeReports() {
		if (inspectionTypeReports == null)
			inspectionTypeReports = new ArrayList<InspectionTypeReport>();
		return inspectionTypeReports;
	}

	public void setInspectionTypeReports(List<InspectionTypeReport> inspectionTypeReports) {
		this.inspectionTypeReports = inspectionTypeReports;
	}

	public BigDecimal getMeAllAmount1() {
		return meAllAmount1;
	}

	public void setMeAllAmount1(BigDecimal meAllAmount1) {
		this.meAllAmount1 = meAllAmount1;
	}

	public BigDecimal getMeAllAmount2() {
		return meAllAmount2;
	}

	public void setMeAllAmount2(BigDecimal meAllAmount2) {
		this.meAllAmount2 = meAllAmount2;
	}

	public BigDecimal getMeAllAmount3() {
		return meAllAmount3;
	}

	public void setMeAllAmount3(BigDecimal meAllAmount3) {
		this.meAllAmount3 = meAllAmount3;
	}

	public List<ReportSubOrganization> getReport8List() {
		if (report8List == null)
			report8List = new ArrayList<>();
		return report8List;
	}

	public void setReport8List(List<ReportSubOrganization> report8List) {
		this.report8List = report8List;
	}

	public ReportSubOrganization getReport8() {
		if (report8 == null) {
			report8 = new ReportSubOrganization();
			report8.setSumAmount(BigDecimal.ZERO);
			report8.setSumCount(0);
			report8.setValue1Amount(BigDecimal.ZERO);
			report8.setValue1Count(0);
			report8.setValue2Amount(BigDecimal.ZERO);
			report8.setValue2Count(0);
			report8.setValue3Amount(BigDecimal.ZERO);
			report8.setValue3Count(0);
			report8.setValue4Amount(BigDecimal.ZERO);
			report8.setValue4Count(0);
			report8.setValue5Amount(BigDecimal.ZERO);
			report8.setValue5Count(0);
		}
		return report8;
	}

	public void setReport8(ReportSubOrganization report8) {
		this.report8 = report8;
	}

	public String getReportDateStr1() {
		return reportDateStr1;
	}

	public void setReportDateStr1(String reportDateStr1) {
		this.reportDateStr1 = reportDateStr1;
	}

	public String getReportDateStr2() {
		return reportDateStr2;
	}

	public void setReportDateStr2(String reportDateStr2) {
		this.reportDateStr2 = reportDateStr2;
	}

	public String getReportDateStr3() {
		return reportDateStr3;
	}

	public void setReportDateStr3(String reportDateStr3) {
		this.reportDateStr3 = reportDateStr3;
	}

	public String getReportDateStr5() {
		return reportDateStr5;
	}

	public void setReportDateStr5(String reportDateStr5) {
		this.reportDateStr5 = reportDateStr5;
	}

	public String getReportDateStr4() {
		return reportDateStr4;
	}

	public void setReportDateStr4(String reportDateStr4) {
		this.reportDateStr4 = reportDateStr4;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		if (treatments == null)
			treatments = new ArrayList<Treatment>();
		this.treatments = treatments;
	}

	public List<TreatmentRequestReport> getTreatmentRequests() {
		if (treatmentRequests == null)
			treatmentRequests = new ArrayList<TreatmentRequestReport>();
		return treatmentRequests;
	}

	public void setTreatmentRequests(List<TreatmentRequestReport> treatmentRequests) {
		this.treatmentRequests = treatmentRequests;
	}

	public BigDecimal getSelectedTreatmentPkId() {
		return selectedTreatmentPkId;
	}

	public void setSelectedTreatmentPkId(BigDecimal selectedTreatmentPkId) {
		this.selectedTreatmentPkId = selectedTreatmentPkId;
	}

	public String getSelectedTreatmentName() {
		if (selectedTreatmentName == null)
			selectedTreatmentName = "";
		return selectedTreatmentName;
	}

	public void setSelectedTreatmentName(String selectedTreatmentName) {
		this.selectedTreatmentName = selectedTreatmentName;
	}

}

package hospital.businesslogic;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.ReportFilter;
import hospital.businessentity.SurgeryReport;
import hospital.businessentity.Tool;
import hospital.businessentity.TreatmentReport;
import hospital.businesslogic.interfaces.ILogicReportLocal;
import hospital.entity.Xray;
import hospital.entity.XrayPrice;
import hospital.entity.XrayRequest;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequest;
import hospital.entity.ExaminationPrice;
import hospital.entity.ExaminationRequestHistory;
import hospital.entity.Item;
import hospital.entity.Organization;
import hospital.entity.Payment;
import hospital.entity.PaymentDtl;
import hospital.entity.PaymentHistory;
import hospital.entity.PriceHistory;
import hospital.entity.SubOrganization;
import hospital.entity.Surgery;
import hospital.entity.Treatment;
import hospital.entity.Customer;
import hospital.entity.CustomerTreatment;
import hospital.entity.CustomerTreatmentDtl;
import hospital.entity.CustomerTreatmentHistory;
import hospital.entity.Diagnose;
import hospital.entity.View_Xray;
import hospital.report.DiscountPriceReport;
import hospital.report.DoctorService;
import hospital.report.DoctorServiceEmployee;
import hospital.report.DoctorServiceTreatment;
import hospital.report.InspectionTypeReport;
import hospital.report.ItemReportDtl;
import hospital.report.ItemReportHdr;
import hospital.report.MEReport;
import hospital.report.MedicalExaminationReport;
import hospital.report.ReportEmployee;
import hospital.report.ReportMonth;
import hospital.report.ReportSubOrganization;
import hospital.report.ReportTreatment;
import hospital.report.TreatmentRequestReport;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;

@Stateless(name = "LogicReport", mappedName = "hospital.businesslogic.LogicReport")
public class LogicReport extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicReport, ILogicReportLocal {

	@Resource
	SessionContext sessionContext;

	public LogicReport() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	public List<ItemReportHdr> getItemReport(BigDecimal itemPkId, Date beginDate, Date endDate, LoggedUser loggedUser)
			throws Exception {

		List<ItemReportHdr> reportHdrs = new ArrayList<ItemReportHdr>();

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("itemPkId", itemPkId);

		jpql.append("SELECT NEW hospital.report.ItemReportHdr(a, b, c, d, e, f) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
		jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
		jpql.append("INNER JOIN Treatment d ON d.pkId = c.typePkId ");
		jpql.append("INNER JOIN TreatmentDtl e ON d.pkId = e.treatmentPkId ");
		jpql.append("INNER JOIN Item f ON e.itemPkId = f.pkId ");
		jpql.append("INNER JOIN Measurement g on f.measurementPkId = g.pkId ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		jpql.append("AND a.mood IN (2, 4) ");

		if (itemPkId != null && BigDecimal.ZERO.compareTo(itemPkId) != 0)
			jpql.append("AND f.pkId = :itemPkId ");

		List<ItemReportHdr> itemReportHdrs = getByQuery(ItemReportHdr.class, jpql.toString(), parameters);

		List<BigDecimal> employeePkIds = new ArrayList<BigDecimal>();
		for (ItemReportHdr itemReportHdr : itemReportHdrs) {

			if (!employeePkIds.contains(itemReportHdr.getEmployeeRequest().getEmployeePkId())) {
				// Ð¨Ð¸Ð½Ñ�Ñ�Ñ€ Ð½Ñ�Ð¼Ñ�Ñ…
				employeePkIds.add(itemReportHdr.getEmployeeRequest().getEmployeePkId());
				ItemReportHdr hdr = new ItemReportHdr();
				hdr.setEmployeeRequest(itemReportHdr.getEmployeeRequest());
				hdr.setInspection(itemReportHdr.getInspection());
				hdr.setInspectionDtl(itemReportHdr.getInspectionDtl());
				hdr.setTreatment(itemReportHdr.getTreatment());
				hdr.setTreatmentDtl(itemReportHdr.getTreatmentDtl());
				hdr.setItem(itemReportHdr.getItem());
				hdr.setSumAmount(BigDecimal.ZERO);
				hdr.setItemReportDtls(new ArrayList<ItemReportDtl>());
				hdr.setSubOrganizationName("");
				hdr.setEmployeeFirstName("");

				Employee employee = getByPkId(Employee.class, itemReportHdr.getEmployeeRequest().getEmployeePkId());
				if (employee != null) {
					hdr.setEmployeeFirstName(employee.getFirstName());
					SubOrganization organization = getByPkId(SubOrganization.class, employee.getSubOrganizationPkId());
					if (organization != null)
						hdr.setSubOrganizationName(organization.getName());
				}
				reportHdrs.add(hdr);
			}

			for (ItemReportHdr hdr : reportHdrs) {
				if (hdr.getEmployeeRequest().getEmployeePkId()
						.compareTo(itemReportHdr.getEmployeeRequest().getEmployeePkId()) != 0)
					continue;

				if (hdr.getItemPkIds().contains(itemReportHdr.getItem().getPkId())) {

					for (ItemReportDtl item : hdr.getItemReportDtls()) {
						if (item.getItemPkId().compareTo(itemReportHdr.getItem().getPkId()) != 0)
							continue;
						item.setItemCount(item.getItemCount().add(itemReportHdr.getTreatmentDtl().getItemCount()));
					}

				} else {
					ItemReportDtl dtl = new ItemReportDtl();
					dtl.setItemPkId(itemReportHdr.getItem().getPkId());
					dtl.setItemName(itemReportHdr.getItem().getName());
					dtl.setItemPrice(itemReportHdr.getItem().getEntityPrice());
					dtl.setItemCount(itemReportHdr.getTreatmentDtl().getItemCount());
					dtl.setMeasurement(itemReportHdr.getItem().getMeasurementName());

					hdr.getItemPkIds().add(itemReportHdr.getItem().getPkId());
					hdr.getItemReportDtls().add(dtl);
				}
			}

		}

		return reportHdrs;
	}
	
	@Override
	public List<ItemReportHdr> getItemReport(BigDecimal subOrganizationPkId, BigDecimal itemPkId, Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception{
		
		List<ItemReportHdr> reportHdrs = new ArrayList<ItemReportHdr>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		parameters.put("itemPkId", itemPkId);
		
		jpql.append("SELECT NEW hospital.report.ItemReportHdr(a, c, b.customerTreatmentPkId) FROM Employee a ");
		jpql.append("INNER JOIN CustomerTreatmentDtl b ON a.pkId = b.employeePkId ");
		jpql.append("INNER JOIN SubOrganization c ON c.pkId = a.subOrganizationPkId ");
		jpql.append("WHERE b.treatmentDate > :beginDate ");
		jpql.append("AND b.treatmentDate < :endDate ");
		if(subOrganizationPkId != null && BigDecimal.ZERO.compareTo(subOrganizationPkId) != 0) 
			jpql.append("AND c.pkId = :subOrganizationPkId ");
		
		List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
		List<ItemReportDtl> dtls = new ArrayList<ItemReportDtl>();
		List<ItemReportHdr> hdrs = getByQuery(ItemReportHdr.class, jpql.toString(), parameters);
		for (ItemReportHdr itemReportHdr : hdrs) {
			CustomerTreatment customerTreatment = getByPkId(CustomerTreatment.class, itemReportHdr.getCustomerTreatmentPkId());
			CustomerTreatmentHistory customerTreatmentHistory = getByPkId(CustomerTreatmentHistory.class, itemReportHdr.getCustomerTreatmentPkId());
			BigDecimal treatmentPkId = BigDecimal.ZERO;
			Date date = new Date();
			if(customerTreatmentHistory != null){
				treatmentPkId = customerTreatmentHistory.getTreatmentPkId();
				date = customerTreatmentHistory.getCreatedDate() == null ? new Date() : customerTreatmentHistory.getCreatedDate();
			}
			if(customerTreatment != null){
				
				treatmentPkId = customerTreatment.getTreatmentPkId();
				date = customerTreatment.getCreatedDate() == null ? new Date() : customerTreatment.getCreatedDate();
			}
			if(BigDecimal.ZERO.compareTo(treatmentPkId) != 0){
				parameters.put("treatmentPkId", treatmentPkId);
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM Item a ");
				jpql.append("INNER JOIN TreatmentDtl b ON a.pkId = b.itemPkId ");
				jpql.append("WHERE b.treatmentPkId = :treatmentPkId ");
				List<Item> items = getByQuery(Item.class, jpql.toString(), parameters);
				parameters.put("date", date);
				for (Item item : items) {
					parameters.put("itemPkId", item.getPkId());
					jpql = new StringBuilder();
					jpql.append("SELECT a FROM PriceHistory a ");
					jpql.append("WHERE a.itemPkId = :itemPkId ");
					jpql.append("AND a.priceUsageDate < :date ");
					List<PriceHistory> histories = getByQuery(PriceHistory.class, jpql.toString(), parameters);
					ItemReportDtl dtl = new ItemReportDtl();
					dtl.setEmployeePkId(itemReportHdr.getEmployeePkId());
					dtl.setItemName(item.getName());
					dtl.setItemCount(BigDecimal.ONE);
					dtl.setItemPrice(BigDecimal.ZERO);
					if(histories.size() > 0) {
						dtl.setItemPrice(histories.get(0).getPrice());
					}
					dtls.add(dtl);
				}
			}
			if(bigDecimals.contains(itemReportHdr.getEmployeePkId())) continue;
			bigDecimals.add(itemReportHdr.getEmployeePkId());
			itemReportHdr.setItemReportDtls(new ArrayList<ItemReportDtl>());
			reportHdrs.add(itemReportHdr);
		}
		
		for (ItemReportHdr itemReportHdr : reportHdrs) {
			for (ItemReportDtl itemReportDtl : dtls) {
				if(itemReportHdr.getEmployeePkId().compareTo(itemReportDtl.getEmployeePkId()) == 0){
					itemReportHdr.getItemReportDtls().add(itemReportDtl);
					itemReportHdr.setCount(itemReportHdr.getCount() + ((long)Integer.parseInt(itemReportDtl.getItemCount().toString())));
					itemReportHdr.setSumAmount(itemReportHdr.getSumAmount().add(itemReportDtl.getItemPrice()));
				}
			}
		}
		
//		jpql = new StringBuilder();
		
//		jpql.append("SELECT NEW hospital.report.ItemReportHdr(d, e) FROM TreatmentDtl a ");
//		jpql.append("INNER JOIN TreatmentDtl b ON a.pkId = b.pkId ");
//		jpql.append("INNER JOIN CustomerTreatment c ON b.treatmentPkId = c.treatmentPkId ");
//		jpql.append("INNER JOIN Employee d ON d.pkId = c.employeePkId ");
//		jpql.append("INNER JOIN SubOrganization e ON d.subOrganizationPkId = e.pkId ");
//		jpql.append("WHERE c.createdDate > :beginDate ");
//		jpql.append("AND c.createdDate < :endDate ");
//		if(subOrganizationPkId != null && BigDecimal.ZERO.compareTo(subOrganizationPkId) != 0) jpql.append("AND d.subOrganizationPkId = :subOrganizationPkId ");
//		if(itemPkId != null && BigDecimal.ZERO.compareTo(itemPkId) != 0) jpql.append("AND a.pkId = : ");
//		jpql.append("GROUP BY d, e ");
		
//		reportHdrs = getByQuery(ItemReportHdr.class, jpql.toString(), parameters);
		
		return reportHdrs;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<MedicalExaminationReport> getDoctorMedicalExaminationReport(BigDecimal subOrganizationPkId,
			Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		List<MedicalExaminationReport> examinationReports = new ArrayList<MedicalExaminationReport>();
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("subOrganizationPkId", subOrganizationPkId);

		// List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		for (Calendar cal = calendar; cal.getTime().compareTo(endDate) != 1; cal.add(Calendar.DATE, 1)) {

			MedicalExaminationReport medicalExaminationReport = new MedicalExaminationReport();
			medicalExaminationReport.setDate(cal.getTime());

			List<SubOrganization> organizations = new ArrayList<SubOrganization>();
			if (subOrganizationPkId != null && BigDecimal.ZERO.compareTo(subOrganizationPkId) != 0) {

				jpql = new StringBuilder();
				jpql.delete(0, jpql.length());
				jpql.append("SELECT a ");
				jpql.append("FROM SubOrganization a ");
				jpql.append("WHERE a.pkId = :subOrganizationPkId ");

				organizations = getByQuery(SubOrganization.class, jpql.toString(), parameters);

			} else {
				organizations = getAll(SubOrganization.class);
			}

			Date bDate = cal.getTime();
			bDate.setHours(0);
			bDate.setMinutes(0);
			bDate.setSeconds(0);
			Date eDate = cal.getTime();
			eDate.setHours(23);
			eDate.setMinutes(59);
			eDate.setSeconds(59);

			SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			String bdateStr = sdfr.format(bDate);
			String edateStr = sdfr.format(eDate);

			parameters.put("beginDate", bdateStr);
			parameters.put("endDate", edateStr);
			System.out.println(endDate);
			System.out.println(bdateStr + " - " + edateStr);
			int count = 0;
			int subcount = 0;
			int exacount = 0;

			for (SubOrganization subOrganization : organizations) {
				MedicalExaminationReport subOrganizationReport = new MedicalExaminationReport();
				subOrganizationReport.setSubOrganizationName(subOrganization.getName());

				parameters.put("subOrganizationId", subOrganization.getPkId());

				jpql = new StringBuilder();
				jpql.delete(0, jpql.length());
				jpql.append("SELECT a ");
				jpql.append("FROM Employee a ");
				jpql.append("WHERE a.subOrganizationPkId = :subOrganizationId ");

				List<Employee> employees = getByQuery(Employee.class, jpql.toString(), parameters);

				for (Employee employee : employees) {
					MedicalExaminationReport examinationReport = new MedicalExaminationReport();
					examinationReport.setSumAmount1(BigDecimal.ZERO);
					examinationReport.setSumAmount2(BigDecimal.ZERO);
					examinationReport.setSumAmount3(BigDecimal.ZERO);
					examinationReport.setGenderCount1(0);
					examinationReport.setGenderCount2(0);
					examinationReport.setGenderCount3(0);
					examinationReport.setGenderCount4(0);
					examinationReport.setEmployeeName(employee.getFirstName());
					// ********* Parameters ************
					parameters.put("employeePkId", employee.getPkId());
					parameters.put("eDate", endDate);

					jpql = new StringBuilder();
					jpql.delete(0, jpql.length());
					jpql.append("SELECT NEW hospital.report.MEReport(d.price, d.rePrice, e.gender, a.inspectionType) ");
					jpql.append("FROM Inspection a ");
					jpql.append("INNER JOIN Employee b ON b.pkId = a.employeePkId ");
					jpql.append("INNER JOIN Degree c ON c.pkId = b.degreePkId ");
					jpql.append("INNER JOIN DegreePriceHistory d ON d.degreePkId = c.pkId ");
					jpql.append("INNER JOIN Customer e ON e.pkId = a.customerPkId ");
					jpql.append("WHERE b.pkId = :employeePkId ");
					jpql.append("AND a.inspectionDate BETWEEN :beginDate AND :endDate ");
					jpql.append("AND d.priceUsageDate <= :eDate ");

					List<MEReport> meReport = getByQuery(MEReport.class, jpql.toString(), parameters);

					for (MEReport meReports : meReport) {
						count++;
						subcount++;
						exacount++;
						if (meReports.getInspectionType() == 0) {
							if (meReports.getGender() != 0) {
								examinationReport.setGenderCount1(examinationReport.getGenderCount1() + 1);
							} else {
								examinationReport.setGenderCount2(examinationReport.getGenderCount2() + 1);
							}
							examinationReport
									.setSumAmount1(examinationReport.getSumAmount1().add(meReports.getPrice()));
						} else {
							if (meReports.getGender() != 0) {
								examinationReport.setGenderCount3(examinationReport.getGenderCount3() + 1);
							} else {
								examinationReport.setGenderCount4(examinationReport.getGenderCount4() + 1);
							}
							examinationReport
									.setSumAmount2(examinationReport.getSumAmount2().add(meReports.getRePrice()));
						}
					}
					if (count > 0) {
						subOrganizationReport.getExaminationReports().add(examinationReport);
						count = 0;
					}
				}
				if (subcount > 0) {
					medicalExaminationReport.getSubOrganizationReports().add(subOrganizationReport);
					subcount = 0;
				}
			}
			if (exacount > 0) {
				examinationReports.add(medicalExaminationReport);
				exacount = 0;
			}
		}

		return examinationReports;
	}

	@Override
	public List<ReportSubOrganization> getReport8(int year, int month, int week) throws Exception {
		List<ReportSubOrganization> list = new ArrayList<ReportSubOrganization>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.WEEK_OF_MONTH, week);
		cal.set(Calendar.DAY_OF_WEEK, 2);
		Date date1Begin = cal.getTime();
		date1Begin.setHours(0);
		date1Begin.setMinutes(1);
		date1Begin.setSeconds(1);
		Date date1End = cal.getTime();
		date1End.setHours(23);
		date1End.setMinutes(59);
		date1End.setSeconds(59);
		cal.set(Calendar.DAY_OF_WEEK, 3);
		Date date2Begin = cal.getTime();
		date2Begin.setHours(0);
		date2Begin.setMinutes(1);
		date2Begin.setSeconds(1);
		Date date2End = cal.getTime();
		date2End.setHours(23);
		date2End.setMinutes(59);
		date2End.setSeconds(59);
		cal.set(Calendar.DAY_OF_WEEK, 4);
		Date date3Begin = cal.getTime();
		date3Begin.setHours(0);
		date3Begin.setMinutes(1);
		date3Begin.setSeconds(1);
		Date date3End = cal.getTime();
		date3End.setHours(23);
		date3End.setMinutes(59);
		date3End.setSeconds(59);
		cal.set(Calendar.DAY_OF_WEEK, 5);
		Date date4Begin = cal.getTime();
		date4Begin.setHours(0);
		date4Begin.setMinutes(1);
		date4Begin.setSeconds(1);
		Date date4End = cal.getTime();
		date4End.setHours(23);
		date4End.setMinutes(59);
		date4End.setSeconds(59);
		cal.set(Calendar.DAY_OF_WEEK, 6);
		Date date5Begin = cal.getTime();
		date5Begin.setHours(0);
		date5Begin.setMinutes(1);
		date5Begin.setSeconds(1);
		Date date5End = cal.getTime();
		date5End.setHours(23);
		date5End.setMinutes(59);
		date5End.setSeconds(59);
		System.out.println("CALENDAR : " + date1Begin);
		System.out.println("CALENDAR : " + date1End);
		System.out.println("CALENDAR : " + date2Begin);
		System.out.println("CALENDAR : " + date2End);
		System.out.println("CALENDAR : " + date3Begin);
		System.out.println("CALENDAR : " + date3End);
		System.out.println("CALENDAR : " + date4Begin);
		System.out.println("CALENDAR : " + date4End);
		System.out.println("CALENDAR : " + date5Begin);
		System.out.println("CALENDAR : " + date5End);

		jpql.append("SELECT NEW hospital.report.ReportSubOrganization(a.pkId ,a.name) FROM SubOrganization a ");
		list = getByQuery(ReportSubOrganization.class, jpql.toString(), parameters);
		for (ReportSubOrganization reportSubOrganization : list) {
			List<ReportEmployee> employees = new ArrayList<ReportEmployee>();
			parameters.put("subOrganizationPkId", reportSubOrganization.getSubOrganizationPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.report.ReportEmployee(a.pkId ,a.lastName ,a.firstName) FROM Employee a ");
			jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ");
			employees = getByQuery(ReportEmployee.class, jpql.toString(), parameters);
			reportSubOrganization.setSumAmount(BigDecimal.ZERO);
			reportSubOrganization.setSumCount(0);
			reportSubOrganization.setValue1Amount(BigDecimal.ZERO);
			reportSubOrganization.setValue1Count(0);
			reportSubOrganization.setValue2Amount(BigDecimal.ZERO);
			reportSubOrganization.setValue2Count(0);
			reportSubOrganization.setValue3Amount(BigDecimal.ZERO);
			reportSubOrganization.setValue3Count(0);
			reportSubOrganization.setValue4Amount(BigDecimal.ZERO);
			reportSubOrganization.setValue4Count(0);
			reportSubOrganization.setValue5Amount(BigDecimal.ZERO);
			reportSubOrganization.setValue5Count(0);
			reportSubOrganization.setEmployees(employees);
			for (ReportEmployee reportEmployee : employees) {
				List<ReportTreatment> treatments = new ArrayList<ReportTreatment>();
				parameters.put("employeePkId", reportEmployee.getEmployeePkId());
				parameters.put("beginDate", date1Begin);
				parameters.put("endDate", date5End);
				jpql = new StringBuilder();
				jpql.append("SELECT NEW hospital.report.ReportTreatment(a.typePkId, a.type) FROM PaymentDtl a ");
				jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
				jpql.append("WHERE a.employeePkId = :employeePkId ");
				jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
				jpql.append("GROUP BY a.typePkId, a.type ");
				treatments = getByQuery(ReportTreatment.class, jpql.toString(), parameters);
				reportEmployee.setListReportTreatment(treatments);
				reportEmployee.setSumAmount(BigDecimal.ZERO);
				reportEmployee.setSumCount(0);
				reportEmployee.setValue1Amount(BigDecimal.ZERO);
				reportEmployee.setValue1Count(0);
				reportEmployee.setValue2Amount(BigDecimal.ZERO);
				reportEmployee.setValue2Count(0);
				reportEmployee.setValue3Amount(BigDecimal.ZERO);
				reportEmployee.setValue3Count(0);
				reportEmployee.setValue4Amount(BigDecimal.ZERO);
				reportEmployee.setValue4Count(0);
				reportEmployee.setValue5Amount(BigDecimal.ZERO);
				reportEmployee.setValue5Count(0);
				for (ReportTreatment reportTreatment : treatments) {
					parameters.put("typePkId", reportTreatment.getTypePkId());
					parameters.put("beginDate", date1Begin);
					parameters.put("endDate", date5End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setSumAmount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setSumCount(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee.setSumAmount(reportEmployee.getSumAmount().add(reportTreatment.getSumAmount()));
					reportEmployee.setSumCount(reportEmployee.getSumCount() + reportTreatment.getSumCount());

					reportSubOrganization
							.setSumAmount(reportSubOrganization.getSumAmount().add(reportTreatment.getSumAmount()));
					reportSubOrganization
							.setSumCount(reportSubOrganization.getSumCount() + reportTreatment.getSumCount());
					// 1
					parameters.put("beginDate", date1Begin);
					parameters.put("endDate", date1End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue1Amount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue1Count(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee
							.setValue1Amount(reportEmployee.getValue1Amount().add(reportTreatment.getValue1Amount()));
					reportEmployee.setValue1Count(reportEmployee.getValue1Count() + reportTreatment.getValue1Count());

					reportSubOrganization.setValue1Amount(
							reportSubOrganization.getValue1Amount().add(reportTreatment.getValue1Amount()));
					reportSubOrganization
							.setValue1Count(reportSubOrganization.getValue1Count() + reportTreatment.getValue1Count());
					// 2
					parameters.put("beginDate", date2Begin);
					parameters.put("endDate", date2End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue2Amount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue2Count(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee
							.setValue2Amount(reportEmployee.getValue2Amount().add(reportTreatment.getValue2Amount()));
					reportEmployee.setValue2Count(reportEmployee.getValue2Count() + reportTreatment.getValue2Count());

					reportSubOrganization.setValue2Amount(
							reportSubOrganization.getValue2Amount().add(reportTreatment.getValue2Amount()));
					reportSubOrganization
							.setValue2Count(reportSubOrganization.getValue2Count() + reportTreatment.getValue2Count());
					// 3
					parameters.put("beginDate", date3Begin);
					parameters.put("endDate", date3End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue3Amount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue3Count(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee
							.setValue3Amount(reportEmployee.getValue3Amount().add(reportTreatment.getValue3Amount()));
					reportEmployee.setValue3Count(reportEmployee.getValue3Count() + reportTreatment.getValue3Count());

					reportSubOrganization.setValue3Amount(
							reportSubOrganization.getValue3Amount().add(reportTreatment.getValue3Amount()));
					reportSubOrganization
							.setValue3Count(reportSubOrganization.getValue3Count() + reportTreatment.getValue3Count());
					// 4
					parameters.put("beginDate", date4Begin);
					parameters.put("endDate", date4End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue4Amount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue4Count(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee
							.setValue4Amount(reportEmployee.getValue4Amount().add(reportTreatment.getValue4Amount()));
					reportEmployee.setValue4Count(reportEmployee.getValue4Count() + reportTreatment.getValue4Count());

					reportSubOrganization.setValue4Amount(
							reportSubOrganization.getValue4Amount().add(reportTreatment.getValue4Amount()));
					reportSubOrganization
							.setValue4Count(reportSubOrganization.getValue4Count() + reportTreatment.getValue4Count());
					// 5
					parameters.put("beginDate", date5Begin);
					parameters.put("endDate", date5End);
					jpql = new StringBuilder();
					jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue5Amount((BigDecimal) getByQuerySingle(jpql.toString(), parameters));
					jpql = new StringBuilder();
					jpql.append("SELECT a.pkId FROM PaymentDtl a ");
					jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
					jpql.append("WHERE a.employeePkId = :employeePkId ");
					jpql.append("AND a.typePkId = :typePkId ");
					jpql.append("AND b.date BETWEEN :beginDate AND :endDate ");
					reportTreatment.setValue5Count(getByQuery(BigDecimal.class, jpql.toString(), parameters).size());

					reportEmployee
							.setValue5Amount(reportEmployee.getValue5Amount().add(reportTreatment.getValue5Amount()));
					reportEmployee.setValue5Count(reportEmployee.getValue5Count() + reportTreatment.getValue5Count());

					reportSubOrganization.setValue5Amount(
							reportSubOrganization.getValue5Amount().add(reportTreatment.getValue5Amount()));
					reportSubOrganization
							.setValue5Count(reportSubOrganization.getValue5Count() + reportTreatment.getValue5Count());

					if (Tool.INSPECTIONPAYMENT.equals(reportTreatment.getType())) {

					}
					if (Tool.INSPECTIONTYPE_XRAY.equals(reportTreatment.getType())) {
						Xray item = getByPkId(Xray.class, reportTreatment.getTypePkId());
						reportTreatment.setTreatmentName(item.getName());
					}
					if (Tool.INSPECTIONTYPE_TREATMENT.equals(reportTreatment.getType())) {
						Treatment item = getByPkId(Treatment.class, reportTreatment.getTypePkId());
						reportTreatment.setTreatmentName(item.getName());
					}
					if (Tool.INSPECTIONTYPE_EXAMINATION.equals(reportTreatment.getType())) {
						Examination item = getByPkId(Examination.class, reportTreatment.getTypePkId());
						reportTreatment.setTreatmentName(item.getName());
					}
					if (Tool.INSPECTIONTYPE_SURGERY.equals(reportTreatment.getType())) {
						Surgery item = getByPkId(Surgery.class, reportTreatment.getTypePkId());
						reportTreatment.setTreatmentName(item.getName());
					}
				}
			}
		}
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ReportMonth> getCashierMothReport(Date beginDate, Date endDate, LoggedUser loggedUser)
			throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		List<ReportMonth> reportMonths = new ArrayList<ReportMonth>();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(beginDate);
		int index = 0;
		for (Calendar cal = calendar; cal.getTime().compareTo(endDate) != 1; cal.add(Calendar.DATE, 1)) {
			index++;
			ReportMonth reportMonth = new ReportMonth();
			reportMonth.setDate(cal.getTime());
			reportMonth.setIndex(index);
			reportMonth.setCustomerCount1(0);
			reportMonth.setInAmount1(BigDecimal.ZERO);
			reportMonth.setCustomerCount2(0);
			reportMonth.setInAmount2(BigDecimal.ZERO);
			reportMonth.setCustomerCount3(0);
			reportMonth.setInAmount3(BigDecimal.ZERO);
			reportMonth.setCustomerCount4(0);
			reportMonth.setInAmount4(BigDecimal.ZERO);
			reportMonth.setCustomerCount5(0);
			reportMonth.setInAmount5(BigDecimal.ZERO);
			reportMonth.setCustomerCount6(0);
			reportMonth.setInAmount6(BigDecimal.ZERO);
			reportMonth.setSumAmount(reportMonth.getSumAmount());
			reportMonth.setSumAmount1(BigDecimal.ZERO);
			reportMonth.setSumAmount2(BigDecimal.ZERO);

			Date bDate = cal.getTime();
			bDate.setHours(0);
			bDate.setMinutes(0);
			bDate.setSeconds(0);
			Date eDate = cal.getTime();
			eDate.setHours(23);
			eDate.setMinutes(59);
			eDate.setSeconds(59);

			parameters.put("bDate", bDate);
			parameters.put("eDate", eDate);
			List<PaymentDtl> dtls;
			
			//
			jpql = new StringBuilder();
			jpql.append("SELECT DISTINCT a FROM PaymentHistory a ");
			jpql.append("WHERE a.date BETWEEN :bDate AND :eDate ");
			List<PaymentHistory> histories = getByQuery(PaymentHistory.class, jpql.toString(), parameters);
			for (PaymentHistory paymentHistory : histories) {
				reportMonth.setSumAmount1(reportMonth.getSumAmount1().add(paymentHistory.getCashInAmount()));
				reportMonth.setSumAmount2(reportMonth.getSumAmount2().add(paymentHistory.getCardInAmount()));
			}

			// INSPECTION
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PaymentDtl a ");
			jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			jpql.append("AND a.type = :paymentType ");

			parameters.put("paymentType", Tool.INSPECTIONPAYMENT);
			dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			reportMonth.setCustomerCount1(reportMonth.getCustomerCount1() + dtls.size());
			for (PaymentDtl paymentDtl : dtls) {
				reportMonth.setInAmount1(reportMonth.getInAmount1().add(paymentDtl.getAmount()));
			}
			// REINSPECTION
			// jpql = new StringBuilder();
			// jpql.append("SELECT a FROM PaymentDtl a ");
			// jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			// jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			// jpql.append("AND a.type = :paymentType ");
			//
			// parameters.put("paymentType", Tool.INSPECTIONPAYMENT);
			// dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			// reportMonth.setCustomerCount2(reportMonth.getCustomerCount2() +
			// dtls.size());
			// for (PaymentDtl paymentDtl : dtls) {
			// reportMonth.setInAmount2(reportMonth.getInAmount2().add(paymentDtl.getAmount()));
			// }
			// EXAMINATION
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PaymentDtl a ");
			jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			jpql.append("AND a.type = :paymentType ");

			parameters.put("paymentType", Tool.INSPECTIONTYPE_EXAMINATION);
			dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			reportMonth.setCustomerCount3(reportMonth.getCustomerCount3() + dtls.size());
			for (PaymentDtl paymentDtl : dtls) {
				reportMonth.setInAmount3(reportMonth.getInAmount3().add(paymentDtl.getAmount()));
			}
			// TREATMENT
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PaymentDtl a ");
			jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			jpql.append("AND a.type = :paymentType ");

			parameters.put("paymentType", Tool.INSPECTIONTYPE_TREATMENT);
			dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			reportMonth.setCustomerCount4(reportMonth.getCustomerCount4() + dtls.size());
			for (PaymentDtl paymentDtl : dtls) {
				reportMonth.setInAmount4(reportMonth.getInAmount4().add(paymentDtl.getAmount()));
			}
			// XRAY
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PaymentDtl a ");
			jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			jpql.append("AND a.type = :paymentType ");

			parameters.put("paymentType", Tool.INSPECTIONTYPE_XRAY);
			dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			reportMonth.setCustomerCount5(reportMonth.getCustomerCount5() + dtls.size());
			for (PaymentDtl paymentDtl : dtls) {
				reportMonth.setInAmount5(reportMonth.getInAmount5().add(paymentDtl.getAmount()));
			}
			// SURGERY
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PaymentDtl a ");
			jpql.append("INNER JOIN Payment b ON a.paymentPkId = b.pkId ");
			jpql.append("WHERE b.date BETWEEN :bDate AND :eDate ");
			jpql.append("AND a.type = :paymentType ");

			parameters.put("paymentType", Tool.INSPECTIONTYPE_SURGERY);
			dtls = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
			reportMonth.setCustomerCount6(reportMonth.getCustomerCount6() + dtls.size());
			for (PaymentDtl paymentDtl : dtls) {
				reportMonth.setInAmount6(reportMonth.getInAmount6().add(paymentDtl.getAmount()));
			}

			reportMonths.add(reportMonth);
		}

		return reportMonths;
	}

	@Override
	public List<DoctorService> getDoctorInspectionHistory(BigDecimal customerPkId, Date beginDate, Date endDate,
			LoggedUser loggedUser) throws Exception {
		//
		//
		List<DoctorService> doctorServices = new ArrayList<DoctorService>();
		//
		// CustomHashMap parameters = new CustomHashMap();
		// StringBuilder jpql = new StringBuilder();
		//
		// parameters.put("customerPkId", customerPkId);
		// parameters.put("beginDate", beginDate);
		// parameters.put("endDate", endDate);
		// jpql.append("SELECT a FROM EmployeeRequest a ");
		// jpql.append("WHERE a.customerPkId = :customerPkId ");
		// jpql.append("AND a.date BETWEEN :beginDate AND :endDate ");
		//
		// List<EmployeeRequest> employeeRequests =
		// getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		// for (EmployeeRequest employeeRequest : employeeRequests) {
		// Employee employee = getByPkId(Employee.class,
		// employeeRequest.getEmployeePkId());
		// if(employee == null) continue;
		// BigDecimal discountPercent = BigDecimal.ZERO;
		// Degree degree = getByPkId(Degree.class, employee.getDegreePkId());
		// if(degree != null) discountPercent = degree.getDiscountPercent();
		//
		// SubOrganization organization = getByPkId(SubOrganization.class,
		// employee.getSubOrganizationPkId());
		// DoctorService doctorService = new DoctorService();
		// doctorService.setSumAmount(BigDecimal.ZERO);
		// doctorService.setBeginDate(beginDate);
		// doctorService.setEndDate(endDate);
		// doctorService.setEmployeeName(employee.getFirstName());
		// doctorService.setSubOrganizationName(organization.getName());
		// doctorService.setAllCount(0);
		// //subotnik
		// doctorService.setDate(employeeRequest.getBeginDate());
		//
		// parameters.put("requestPkId", employeeRequest.getPkId());
		// jpql = new StringBuilder();
		// jpql.append("SELECT a FROM Treatment a ");
		// jpql.append("INNER JOIN InspectionDtl b ON a.pkId = b.treatmentPkId
		// ");
		// jpql.append("INNER JOIN Inspection c ON c.pkId = b.inspectionPkId ");
		// jpql.append("WHERE c.requestPkId = :requestPkId ");
		// List<Treatment> list = getByQuery(Treatment.class, jpql.toString(),
		// parameters);
		// for (Treatment treatment : list) {
		// BigDecimal amount = treatment.getPrice();
		// amount = amount.divide(new
		// BigDecimal(100)).multiply(discountPercent);
		// amount = amount.add(treatment.getPrice());
		// if(doctorService.getDtls().contains(treatment.getPkId())){
		// for(DoctorServiceDtl doctorServiceDtl :
		// doctorService.getDoctorServiceDtls()){
		// if(treatment.getPkId().compareTo(doctorServiceDtl.getTreatmentPkId())
		// != 0) continue;
		// doctorServiceDtl.setAllCount(doctorServiceDtl.getAllCount() + 1);
		// doctorServiceDtl.setAmount(doctorServiceDtl.getAmount().add(amount));
		// doctorService.setSumAmount(doctorService.getSumAmount().add(amount));
		// }
		// }else {
		// doctorService.getDtls().add(treatment.getPkId());
		// DoctorServiceDtl doctorServiceDtl = new DoctorServiceDtl();
		// doctorServiceDtl.setAllCount(1);
		// doctorServiceDtl.setName(treatment.getName());
		// doctorServiceDtl.setAmount(amount);
		// doctorServiceDtl.setTreatmentPkId(treatment.getPkId());
		// doctorService.setSumAmount(doctorService.getSumAmount().add(amount));
		// doctorService.getDoctorServiceDtls().add(doctorServiceDtl);
		// }
		// }
		//
		// doctorServices.add(doctorService);
		// }
		//
		return doctorServices;
	}

	@Override
	public List<DoctorService> getDoctorServices(Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception {
		List<DoctorService> doctorServices = new ArrayList<DoctorService>();

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql.append("SELECT NEW hospital.report.DoctorService(a.pkId, a.name, SUM(c.amount))  FROM SubOrganization a ");
		jpql.append("INNER JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("INNER JOIN PaymentDtl c ON b.pkId = c.employeePkId ");
		jpql.append("INNER JOIN Payment d ON c.paymentPkId = d.pkId ");
		jpql.append("WHERE d.date > :beginDate AND d.date < :endDate ");
		jpql.append("GROUP BY a.pkId, a.name ");

		doctorServices = getByQuery(DoctorService.class, jpql.toString(), parameters);

		for (DoctorService doctorService : doctorServices) {
			parameters.put("subOrganizationPkId", doctorService.getSubOrganizationPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.report.DoctorServiceEmployee(a.pkId, a.firstName, SUM(b.amount)) ");
			jpql.append("FROM Employee a ");
			jpql.append("INNER JOIN PaymentDtl b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN Payment c ON b.paymentPkId = c.pkId ");
			jpql.append("WHERE c.date > :beginDate AND c.date < :endDate ");
			jpql.append("AND a.subOrganizationPkId = :subOrganizationPkId ");
			jpql.append("GROUP BY a.pkId, a.firstName ");

			List<DoctorServiceEmployee> doctorServiceEmployees = getByQuery(DoctorServiceEmployee.class,
					jpql.toString(), parameters);

			for (DoctorServiceEmployee doctorServiceEmployee : doctorServiceEmployees) {
				parameters.put("employeePkId", doctorServiceEmployee.getEmployeePkId());
				jpql = new StringBuilder();
				jpql.append("SELECT NEW hospital.report.DoctorServiceTreatment(a.id, a.name ) ");
				jpql.append("FROM Treatment a ");

				List<DoctorServiceTreatment> doctorServiceTreatments = getByQuery(DoctorServiceTreatment.class,
						jpql.toString(), parameters);

				doctorServiceEmployee.setDoctorServiceTreatments(doctorServiceTreatments);
			}

			doctorService.setDoctorServiceEmployees(doctorServiceEmployees);
		}
		return doctorServices;
	}

	@Override
	public List<InspectionTypeReport> getInspectionTypeReport(ReportFilter filter) throws Exception {
		System.out.println(filter.getSubOrganizationPkId() + " - " + filter.getEmployeePkId() + " => " + filter.getBeginDate() + ":" + filter.getEndDate());
		
		List<InspectionTypeReport> list= new ArrayList<InspectionTypeReport>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", filter.getBeginDate());
		parameters.put("endDate", filter.getEndDate());
		parameters.put("subOrgPkId", filter.getSubOrganizationPkId());
		parameters.put("empPkId", filter.getEmployeePkId());
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.report.InspectionTypeReport(a, b, d) FROM Inspection a ");
		jpql.append("LEFT JOIN ViewCustomer b ON a.customerPkId = b.pkId ");
		jpql.append("LEFT JOIN Aimag d on d.pkId = b.aimagPkId  ");
		jpql.append("LEFT JOIN Employee e ON a.employeePkId = e.pkId ");
		jpql.append("WHERE a.inspectionDate between :beginDate AND :endDate ");
		if(filter.getSubOrganizationPkId() != null && BigDecimal.ZERO.compareTo(filter.getSubOrganizationPkId()) != 0){
			if(filter.getEmployeePkId() != null && BigDecimal.ZERO.compareTo(filter.getEmployeePkId()) != 0){
				jpql.append("AND e.pkId = :empPkId ");
			}else {
				jpql.append("AND e.subOrganizationPkId = :subOrgPkId ");
			}
		}
		
		list = getByQuery(InspectionTypeReport.class, jpql.toString(), parameters);
		

		return list;
	}

	@Override
	public List<DoctorService> getDoctorServices(Date beginDate, Date endDate, BigDecimal employeePkId,
			BigDecimal subOrganizationPkId) throws Exception {
		List<DoctorService> doctorServices = new ArrayList<DoctorService>();

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("employeePkId", employeePkId);
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		jpql.append("SELECT NEW hospital.report.DoctorService(a.pkId, a.name, SUM(c.amount))  FROM SubOrganization a ");
		jpql.append("INNER JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("INNER JOIN PaymentDtl c ON b.pkId = c.employeePkId ");
		jpql.append("INNER JOIN Payment d ON c.paymentPkId = d.pkId ");
		jpql.append("WHERE d.date > :beginDate AND d.date < :endDate ");
		jpql.append("AND b.pkId = :employeePkId");
		jpql.append("AND a.pkId = :subOrganizationPkId");
		jpql.append("GROUP BY a.pkId, a.name ");

		doctorServices = getByQuery(DoctorService.class, jpql.toString(), parameters);

		for (DoctorService doctorService : doctorServices) {
			parameters.put("subOrganizationPkId", doctorService.getSubOrganizationPkId());
			jpql = new StringBuilder();
			jpql.append(
					"SELECT NEW hospital.report.DoctorServiceEmployee(a.pkId, a.firstName, SUM(b.amount)) FROM Employee a ");
			jpql.append("INNER JOIN PaymentDtl b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN Payment c ON b.paymentPkId = c.pkId ");
			jpql.append("WHERE c.date > :beginDate AND c.date < :endDate ");
			jpql.append("AND a.subOrganizationPkId = :subOrganizationPkId ");
			jpql.append("GROUP BY a.pkId, a.firstName ");

			List<DoctorServiceEmployee> doctorServiceEmployees = getByQuery(DoctorServiceEmployee.class,
					jpql.toString(), parameters);

			for (DoctorServiceEmployee doctorServiceEmployee : doctorServiceEmployees) {
				parameters.put("employeePkId", doctorServiceEmployee.getEmployeePkId());
				jpql = new StringBuilder();
				jpql.append("SELECT NEW hospital.report.DoctorServiceTreatment(a.id, a.name ) ");
				jpql.append("FROM Treatment a ");

				List<DoctorServiceTreatment> doctorServiceTreatments = getByQuery(DoctorServiceTreatment.class,
						jpql.toString(), parameters);

				doctorServiceEmployee.setDoctorServiceTreatments(doctorServiceTreatments);
			}

			doctorService.setDoctorServiceEmployees(doctorServiceEmployees);
		}
		return doctorServices;
	}

	@Override
	public List<DiscountPriceReport> getDiscountPriceReport(Date beginDate, Date endDate, LoggedUser loggedUser)
			throws Exception {
		List<DiscountPriceReport> list = new ArrayList<DiscountPriceReport>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.report.DiscountPriceReport(a.itemName, a.price) ");
		jpql.append("FROM PriceHistory a ");
		// jpql.append("INNER JOIN Sum f on f.pkId = b.sumPkId ");

		list = getByQuery(DiscountPriceReport.class, jpql.toString(), parameters);

		return list;
	}

	@Override
	public List<TreatmentRequestReport> getTreatmentRequest(Date beginDate, Date endDate, BigDecimal treatmentPkId)
			throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setHours(23);
		endDate.setMinutes(59);
		parameters.put("treatmentPkId", treatmentPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql.append(
				"SELECT NEW hospital.report.TreatmentRequestReport(a.pkId , a.times ,  a.inspectionPkId ,  b , f.name ) ");
		jpql.append("FROM CustomerTreatment a ");
		jpql.append("LEFT JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("LEFT JOIN Inspection c ON c.pkId = a.inspectionPkId  ");
		jpql.append("LEFT JOIN Employee d ON d.pkId = c.employeePkId ");
		jpql.append("LEFT JOIN SubOrganization f ON f.pkId = d.subOrganizationPkId ");
		jpql.append("WHERE a.treatmentPkId = :treatmentPkId ");
		jpql.append("AND a.createdDate between :beginDate AND :endDate ");
		
		List<TreatmentRequestReport> list = new ArrayList<TreatmentRequestReport>();
		list = getByQuery(TreatmentRequestReport.class, jpql.toString(), parameters);
		StringBuilder jpql1 = new StringBuilder();
		jpql1.append(
				"SELECT NEW hospital.report.TreatmentRequestReport(a.pkId , a.times , a.inspectionPkId ,  b , f.name ) ");
		jpql1.append("FROM CustomerTreatmentHistory a ");
		jpql1.append("LEFT JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql1.append("LEFT JOIN Inspection c ON c.pkId = a.inspectionPkId  ");
		jpql1.append("LEFT JOIN Employee d ON d.pkId = c.employeePkId ");
		jpql1.append("LEFT JOIN SubOrganization f ON f.pkId = d.subOrganizationPkId ");
		jpql1.append("WHERE a.treatmentPkId = :treatmentPkId ");
		jpql1.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
		
		List<TreatmentRequestReport> list1 = new ArrayList<TreatmentRequestReport>();
		list1 = getByQuery(TreatmentRequestReport.class, jpql1.toString(), parameters);
		list.addAll(list1);
		for (TreatmentRequestReport trp : list) {
			if (trp.getInspectionPkId() != null) {
				parameters.put("inspectionPkId", trp.getInspectionPkId());
				jpql.delete(0, jpql.length());
				jpql.append("SELECT a ");
				jpql.append("FROM Diagnose a ");
				jpql.append("INNER JOIN CustomerDiagnose b ON a.pkId = b.diagnosePkId  ");
				jpql.append("WHERE b.inspectionPkId = :inspectionPkId ");
				jpql.append("AND b.type = 1 ");
				trp.setDiagnoses(getByQuery(Diagnose.class, jpql.toString(), parameters));
			}
		}

		for (TreatmentRequestReport trp : list) {

			parameters.put("requestPkId", trp.getRequestPkId());
			jpql.delete(0, jpql.length());
			jpql.append("SELECT a ");
			jpql.append("FROM CustomerTreatmentDtl a ");
			jpql.append("WHERE a.customerTreatmentPkId = :requestPkId ");
			trp.setDtls(getByQuery(CustomerTreatmentDtl.class, jpql.toString(), parameters));

		}

		return list;
	}

	@Override
	public List<Treatment> getRequestTreatments() throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a ");
		jpql.append("FROM Treatment a ");
		jpql.append("INNER JOIN CustomerTreatment b ON a.pkId = b.treatmentPkId ");
		List<Treatment> list = new ArrayList<Treatment>();
		list = getByQuery(Treatment.class, jpql.toString(), parameters);
		jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a ");
		jpql.append("FROM Treatment a ");
		jpql.append("INNER JOIN CustomerTreatmentHistory b ON a.pkId = b.treatmentPkId ");
		List<Treatment> list1 = new ArrayList<Treatment>();
		list1 = getByQuery(Treatment.class, jpql.toString(), parameters);
		List<Treatment> qq = new ArrayList<Treatment>();
		qq.addAll(list);
		for (Treatment t : list) {
			for (Treatment tt : list1)
				if (t.getPkId().compareTo(tt.getPkId()) != 0)
					qq.add(tt);
		}
		return list;
	}

}

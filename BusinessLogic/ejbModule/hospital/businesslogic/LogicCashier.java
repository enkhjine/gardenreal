package hospital.businesslogic;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentInspection;
import hospital.businessentity.PaymentPackageSelectItem;
import hospital.businessentity.PaymentPackageSelectType;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicCashierLocal;
import hospital.entity.Customer;
import hospital.entity.Degree;
import hospital.entity.DegreePriceHistory;
import hospital.entity.Element;
import hospital.entity.ElementPrice;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.Examination;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationPrice;
import hospital.entity.PaymentDtl;
import hospital.entity.PaymentPackage;
import hospital.entity.PaymentPackageDtl;
import hospital.entity.Surgery;
import hospital.entity.SurgeryPrice;
import hospital.entity.TreatmentPrice;
import hospital.entity.TreatmentType;
import hospital.entity.Xray;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.InspectionDtlExaminationDtlMap;
import hospital.entity.Payment;
import hospital.entity.PriceHistory;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.entity.XrayPrice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicCashier", mappedName = "hospital.businesslogic.LogicCashier")
public class LogicCashier extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicCashier,
		ILogicCashierLocal {


	@Resource
	SessionContext sessionContext;

	public LogicCashier() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}
	
	public List<TreatmentType> getTreatmentType() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM TreatmentType a ");
		jpql.append("INNER JOIN Treatment b ON a.pkId = b.treatmentTypePkId ");
		return getByQuery(TreatmentType.class, jpql.toString(), null);
	}
	
	public List<Treatment> getTreatments(BigDecimal treatmentTypePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("treatmentTypePkId", treatmentTypePkId);
		jpql.append("SELECT a FROM Treatment a ");
		jpql.append("WHERE a.treatmentTypePkId = :treatmentTypePkId ");
		
		List<Treatment> list = getByQuery(Treatment.class, jpql.toString(), parameters);
		parameters.put("dte", new Date());
		for (Treatment treatment : list) {
			parameters.put("treatmentPkId", treatment.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM TreatmentPrice a ");
			jpql.append("WHERE a.treatmentPkId = :treatmentPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<TreatmentPrice> prices = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);
			if(prices.size() > 0){
				treatment.setPrice(prices.get(0).getPrice());
			}
		}
		return list;
	}

	public List<CashBusinessEntity> getCashHdrsByCustomerPkId(BigDecimal pkId,
			LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("organizationPkId", loggedUser.getOrganization()
				.getPkId());
		parameters.put("employeePkId", pkId);
		parameters.put("mood", 2);
		parameters.put("mood1", 4);
		jpql.append("SELECT NEW hospital.businessentity.CashBusinessEntity(a, b, c, d, e, f, h) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
		jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
		//jpql.append("INNER JOIN Treatment d ON c.treatmentPkId = d.pkId ");
		jpql.append("INNER JOIN Customer e ON e.pkId = b.customerPkId ");
		jpql.append("INNER JOIN Employee f ON f.pkId = b.employeePkId ");
		jpql.append("INNER JOIN Degree h ON h.pkId = f.degreePkId ");
		jpql.append("WHERE (a.mood = :mood OR a.mood = :mood1) AND a.organizationPkId = :organizationPkId AND e.pkId = :employeePkId ");
		jpql.append("AND a.pkId NOT IN (SELECT g.employeeRequestPkId FROM EmployeeRequestPaymentMap g) ");

		List<CashBusinessEntity> businessEntities = getByQuery(
				CashBusinessEntity.class, jpql.toString(), parameters);
		return businessEntities;
	}

	public List<Employee> getCashEmployeeRequest(BigDecimal employeePkId)
			throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = getByPkId(Employee.class, employeePkId);
		if (employee != null) {
			SubOrganization organization = getByPkId(SubOrganization.class,
					employee.getSubOrganizationPkId());
			Degree degree = getByPkId(Degree.class, employee.getDegreePkId());
			if (organization != null)
				employee.setSubOrganizationName(organization.getName());
			if (degree != null) {
				employee.setDegreeName(degree.getName());
				Date dte = new Date();
				StringBuilder jpql = new StringBuilder();
				CustomHashMap parameters = new CustomHashMap();
				parameters.put("itemPkId", degree.getPkId());
				parameters.put("priceUsageDate", dte);
				jpql.append("SELECT a FROM PriceHistory a ");
				jpql.append("WHERE a.itemPkId = :itemPkId ");
				jpql.append("AND a.priceUsageDate < :priceUsageDate ");
				jpql.append("ORDER BY a.priceUsageDate DESC ");
				List<PriceHistory> histories = getByQuery(PriceHistory.class,
						jpql.toString(), parameters);

				if (histories.size() > 0)
					employee.setPrice(histories.get(0).getPrice());

				employees.add(employee);
			}
		}

		return employees;
	}
	
	public List<Employee> getEmployeeListByXray() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM Employee a ");
		jpql.append("INNER JOIN XrayEmployeeMap b ON a.pkId = b.employeePkId ");
		List<Employee> employees = getByQuery(Employee.class, jpql.toString(), null);
		return employees;
	}
	
	public List<PaymentInspection> getCashInspections(BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		
		jpql.append("SELECT NEW hospital.businessentity.PaymentInspection(b, a, c, d, e, f) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Inspection c ON a.pkId = c.requestPkId ");
		jpql.append("INNER JOIN InspectionDtl d ON c.pkId = d.inspectionPkId ");
		jpql.append("INNER JOIN Degree e ON b.degreePkId = e.pkId ");
		jpql.append("INNER JOIN Treatment f ON d.treatmentPkId = f.pkId ");
		jpql.append("WHERE a.paymentPkId IS NULL ");
		jpql.append("AND a.customerPkId = :customerPkId ");
		jpql.append("AND a.mood IN (2, 4) ");
		
		List<PaymentInspection> inspections = getByQuery(PaymentInspection.class, jpql.toString(), parameters);
		
		return inspections;
	}
	
	public List<CashByInspectionDtl> getListRequestInspectionDtl(BigDecimal paymentPkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("paymentPkId", paymentPkId);
		parameters.put("INSPECTIONPAYMENT", Tool.INSPECTIONPAYMENT);
		jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(a.employeePkId, b.firstName, a.amount) FROM PaymentDtl a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND a.type = :INSPECTIONPAYMENT ");
		
		List<CashByInspectionDtl> byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
//		List<EmployeeRequestHistory> employeeRequests = getByAnyField(EmployeeRequestHistory.class, "paymentPkId", paymentPkId);
//		int reInspection = 0;
//		if(employeeRequests.size() < 1) {
//			List<EmployeeRequest> employeeRequestsas = getByAnyField(EmployeeRequest.class, "paymentPkId", paymentPkId);
//			if(employeeRequestsas.size() > 0) reInspection = employeeRequestsas.get(0).getReInspection();
//		}else {
//			reInspection = employeeRequests.get(0).getReInspection();
//		}
//		for (CashByInspectionDtl cashByInspectionDtl : byInspectionDtls) {
//			cashByInspectionDtl.setPrice(getDegreePriceByEmployeePkId(cashByInspectionDtl.getDegreePkId(), reInspection));
//		}
		return byInspectionDtls;
	}
	
	public List<CashByInspectionDtl> getListXrayInspectionDtl(BigDecimal paymentPkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("paymentPkId", paymentPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_XRAY);
		jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(b.name, a.amount) FROM PaymentDtl a ");
		jpql.append("INNER JOIN Xray b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND a.type = :type ");
		
		List<CashByInspectionDtl> byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
		return byInspectionDtls;
	}
	
	public List<ExaminationDtl> getExaminationDtlsByExaminationPkId(BigDecimal examinationPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ExaminationDtl> dtls = new ArrayList<ExaminationDtl>();
		
		parameters.put("dte", new Date());
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT NEW hospital.entity.ExaminationDtl(a, b) FROM ExaminationDtl a ");
		jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
		jpql.append("WHERE a.examinationPkId = :examinationPkId ");
		
		dtls = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
		
		for (ExaminationDtl examinationDtl : dtls) {
			parameters.put("elementPkId", examinationDtl.getElementPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM ElementPrice a ");
			jpql.append("WHERE a.elementPkId = :elementPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
			examinationDtl.setElementPricePkId(BigDecimal.ZERO);
			examinationDtl.setPrice(BigDecimal.ZERO);
			if(elementPrices.size() > 0){
				examinationDtl.setElementPricePkId(elementPrices.get(0).getPkId());
				examinationDtl.setPrice(elementPrices.get(0).getPrice());
			}
		}
		
		return dtls;
	}
	
	public List<CashByInspectionDtl> getListExaminationInspectionDtl(BigDecimal paymentPkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("paymentPkId", paymentPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_EXAMINATION);
		jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(a.pkId, b.name, a.amount, a.typePkId) FROM PaymentDtl a ");
		jpql.append("LEFT JOIN Examination b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND b.pkId IS NOT NULL ");
		jpql.append("AND a.type = :type ");
		
		List<CashByInspectionDtl> byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
		for (CashByInspectionDtl cashByInspectionDtl : byInspectionDtls) {
			cashByInspectionDtl.setExaminationDtls(getExaminationDtlsByExaminationPkId(cashByInspectionDtl.getTypePkId()));
		}
		return byInspectionDtls;
	}
	
	public List<CashByInspectionDtl> getListTreatmentInspectionDtl(BigDecimal paymentPkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("paymentPkId", paymentPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_TREATMENT);
		jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(b.name, a.amount) FROM PaymentDtl a ");
		jpql.append("INNER JOIN Treatment b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND a.type = :type ");
		
		List<CashByInspectionDtl> byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
		return byInspectionDtls;
	} 
	
	public List<CashByInspectionDtl> getListSurgeryInspectionDtl(BigDecimal paymentPkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("paymentPkId", paymentPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_SURGERY);
		jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(b.name, a.amount) FROM PaymentDtl a ");
		jpql.append("INNER JOIN Surgery b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND a.type = :type ");
		
		List<CashByInspectionDtl> byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
		return byInspectionDtls;
	}
	
	public Payment getCashPayment(BigDecimal paymentPkId) throws Exception{
		Payment payment = getByPkId(Payment.class, paymentPkId);
		return payment;
	}
	
	public Customer getCustomerByRequestPkId(BigDecimal employeeRequestPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("employeeRequestPkId", employeeRequestPkId);
		jpql.append("SELECT a FROM Customer a ");
		jpql.append("LEFT JOIN EmployeeRequest b ON a.pkId = b.customerPkId ");
		jpql.append("LEFT JOIN EmployeeRequestHistory c ON a.pkId = c.customerPkId ");
		jpql.append("WHERE b.pkId = :employeeRequestPkId OR c.pkId = :employeeRequestPkId ");
		
		List<Customer> customers = getByQuery(Customer.class, jpql.toString(), parameters);
		if(customers.size() > 0) return customers.get(0);
		return null;
	}
	
	public List<Inspection> getInspectionHistoryByCustomer(BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT NEW hospital.entity.Inspection(a.requestPkId, b.firstName, a.inspectionDate) FROM Inspection a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		
		List<Inspection> inspections = getByQuery(Inspection.class, jpql.toString(), parameters);
		return inspections;
	}
	
	public BigDecimal getDegreePriceByEmployeePkId(BigDecimal employeePkId, int isSecond) throws Exception {
		//HALIT
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("dte", new Date());
		parameters.put("employeePkId", employeePkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM Employee a ");
		jpql.append("INNER JOIN Degree b ON a.degreePkId = b.pkId ");
		jpql.append("INNER JOIN DegreePriceHistory c ON b.pkId = c.degreePkId ");
		jpql.append("WHERE c.priceUsageDate < :dte ");
		jpql.append("AND a.pkId = :employeePkId ");
		jpql.append("ORDER BY c.priceUsageDate DESC ");
		List<DegreePriceHistory> degreePriceHistories = getByQuery(DegreePriceHistory.class, jpql.toString(), parameters);
		if(degreePriceHistories.size() > 0) {
			if(isSecond == 1) {
				return degreePriceHistories.get(0).getRePrice();
			}else {
				return degreePriceHistories.get(0).getPrice();
			}
		}
		
		return BigDecimal.ZERO;
	}
	
	public CashByInspectionDtl getRequestGyCashInspectionDtl(BigDecimal employeePkId, int isSecond) throws Exception{
		CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
		Employee employee = getByPkId(Employee.class, employeePkId);
		byInspectionDtl.setName(employee.getFirstName());
		byInspectionDtl.setPrice(getDegreePriceByEmployeePkId(employeePkId, isSecond));
		byInspectionDtl.setInspectionStatus(isSecond == 1 ? "Давтан" : "Анхан");
		return byInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListCashByInspectionDtl(BigDecimal employeeRequestPkId) throws Exception{
		StringBuilder jpql  = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeeRequestPkId", employeeRequestPkId);
		parameters.put("dte", new Date());
		
		jpql.append("SELECT b FROM Inspection a ");
		jpql.append("INNER JOIN InspectionDtl b ON a.pkId = b.inspectionPkId ");
		jpql.append("WHERE a.requestPkId = :employeeRequestPkId ");
		
		
		List<InspectionDtl> inspectionDtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		List<CashByInspectionDtl> byInspectionDtls = new ArrayList<CashByInspectionDtl>();
		
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM Employee a ");
		jpql.append("INNER JOIN EmployeeRequest b on a.pkId = b.employeePkId ");
		jpql.append("WHERE a.pkId = :employeeRequestPkId ");
		List<Employee> employees = getByQuery(Employee.class, jpql.toString(), parameters);
		for (InspectionDtl inspectionDtl : inspectionDtls) {
			BigDecimal price = BigDecimal.ZERO;
			String name = "";
			String employeeName = "";
			List<Employee> employeesList = new ArrayList<Employee>();
			if(employees.size() > 0) employeeName = employees.get(0).getFirstName();
			
			parameters.put("typePkId", inspectionDtl.getTypePkId());
			jpql = new StringBuilder();
			if(Tool.INSPECTIONTYPE_XRAY.equals(inspectionDtl.getType())) {
				jpql.append("SELECT a FROM XrayPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				jpql.append("AND a.xrayPkId = :typePkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<XrayPrice> prices = getByQuery(XrayPrice.class, jpql.toString(), parameters);
				if(prices.size() > 0) price = prices.get(0).getPrice();
				
				Xray xray = getByPkId(Xray.class, inspectionDtl.getTypePkId());
				if(xray != null) {
					name = xray.getName();
				}
				
			}else if(Tool.INSPECTIONTYPE_TREATMENT.equals(inspectionDtl.getType())) {
				jpql.append("SELECT a FROM TreatmentPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				jpql.append("AND a.treatmentPkId = :typePkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<TreatmentPrice> prices = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);
				if(prices.size() > 0) price = prices.get(0).getPrice();
				
				Treatment treatment = getByPkId(Treatment.class, inspectionDtl.getTypePkId());
				if(treatment != null) {
					name = treatment.getName();
//					
//					parameters.put("treatmentPkId", treatment.getPkId());
//					jpql = new StringBuilder();
//					jpql.append("SELECT a FROM Employee a ");
//					jpql.append("INNER JOIN SubOrganization b ON a.subOrganizationPkId = b.pkId ");
//					jpql.append("INNER JOIN TreatmentType c ON b.subOrganizationTypePkId = c.subOrganizationTypePkId ");
//					jpql.append("INNER JOIN Treatment d ON c.pkId = d.treatmentTypePkId ");
//					jpql.append("WHERE d.pkId = :treatmentPkId");
//					
//					employeesList = getByQuery(Employee.class, jpql.toString(), parameters);
				}
				
			}else if(Tool.INSPECTIONTYPE_EXAMINATION.equals(inspectionDtl.getType())) {
				jpql.append("SELECT a FROM ExaminationPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				jpql.append("AND a.examinationPkId = :typePkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<ExaminationPrice> prices = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
				if(prices.size() > 0) price = prices.get(0).getPrice();
				
				Examination examination = getByPkId(Examination.class, inspectionDtl.getTypePkId());
				if(examination != null) {
					name = examination.getName();
					
					parameters.put("inspectionDtlPkId", inspectionDtl.getPkId());
					jpql = new StringBuilder();
					jpql.append("SELECT NEW hospital.entity.ExaminationDtl(c, b, a) ");
					jpql.append("FROM ElementPrice a ");
					jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
					jpql.append("INNER JOIN ExaminationDtl c ON b.pkId = c.elementPkId ");
					jpql.append("INNER JOIN InspectionDtlExaminationDtlMap d ON c.pkId = d.examinationDtlPkId ");
					jpql.append("WHERE d.inspectionDtlPkId = :inspectionDtlPkId ");
					
					List<ExaminationDtl> maps = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
					if(maps.size() > 0){
						price = BigDecimal.ZERO;
						for(ExaminationDtl dtl : maps){
							price = price.add(dtl.getPrice());
							inspectionDtl.getExaminationDtls().add(dtl);
						}
					}
					
//					
//					parameters.put("examinationPkId", examination.getPkId());
//					jpql = new StringBuilder();
//					jpql.append("SELECT a FROM Employee a ");
//					jpql.append("INNER JOIN ExaminationDoctor b ON a.pkId = b.employeePkId ");
//					jpql.append("WHERE b.examinationPkId = :examinationPkId ");
//					employeesList = getByQuery(Employee.class, jpql.toString(), parameters);
				}
			}else if(Tool.INSPECTIONTYPE_SURGERY.equals(inspectionDtl.getType())) {
				jpql.append("SELECT a FROM SurgeryPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				jpql.append("AND a.surgeryPkId = :typePkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<SurgeryPrice> prices = getByQuery(SurgeryPrice.class, jpql.toString(), parameters);
				if(prices.size() > 0) price = prices.get(0).getPrice();
				
				Surgery surgery = getByPkId(Surgery.class, inspectionDtl.getTypePkId());
				if(surgery != null) {
					name = surgery.getName();
//					
//					parameters.put("surgeryPkId", surgery.getPkId());
//					jpql = new StringBuilder();
//					jpql.append("SELECT a FROM Employee a ");
//					jpql.append("INNER JOIN SurgeryDoctor b ON a.pkId = b.employeePkId ");
//					jpql.append("WHERE b.surgeryPkId = :surgeryPkId ");
//					employeesList = getByQuery(Employee.class, jpql.toString(), parameters);
				}
			}
			
			CashByInspectionDtl dtl = new CashByInspectionDtl();
			dtl.setType(inspectionDtl.getType());
			dtl.setTypePkId(inspectionDtl.getTypePkId());
			dtl.setPrice(price);
			dtl.setName(name);
			dtl.setUserCount(inspectionDtl.getUsed());
			dtl.setQty(inspectionDtl.getQty());
			dtl.setTimes(inspectionDtl.getTimes());
			dtl.setDayLength(inspectionDtl.getDayLength());
			dtl.setEmployeeName(employeeName);
			dtl.setAmount(BigDecimal.ZERO);
			dtl.setAllAmount(price.multiply(new BigDecimal(inspectionDtl.getDayLength())));
			dtl.setListEmployee(employeesList);
			dtl.setInspectionDtlPkId(inspectionDtl.getPkId());
			dtl.setInspectionPkId(inspectionDtl.getInspectionPkId());
			dtl.setDescription(inspectionDtl.getDescription());
			dtl.getExaminationDtls().addAll(inspectionDtl.getExaminationDtls());
			if(inspectionDtl.getUsed() == 1) {
				dtl.setSelected(true);
				dtl.setStatus(Tool.UNCHANGED);
			}
			byInspectionDtls.add(dtl);
		}
		
		return byInspectionDtls;
	}
	
	public BigDecimal getPaymentPkIdByEmployeeRequestPkId(BigDecimal employeeRequestPkId) throws Exception{
		EmployeeRequest employeeRequest = getByPkId(EmployeeRequest.class, employeeRequestPkId);
		if(employeeRequest != null) return employeeRequest.getPaymentPkId();
		return null;
	}
	
	public List<PaymentPackage> getListPaymentPackage() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM PaymentPackage a ");
		jpql.append("ORDER BY a.updatedDate DESC ");
		List<PaymentPackage> list = getByQuery(PaymentPackage.class, jpql.toString(), null);
		return list;
	}
	
	public List<PaymentPackageSelectItem> getPackageSelectItems(String type, BigDecimal typePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("type", type);
		parameters.put("pkId", typePkId);
		parameters.put("dte", new Date());
		if(Tool.INSPECTIONTYPE_XRAY.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectItem('XRAY', a.id, a.name, a.pkId) ");
			jpql.append("FROM Xray a ");
			jpql.append("WHERE a.xrayTypePkId = :pkId ");
		}
		
		if(Tool.INSPECTIONTYPE_TREATMENT.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectItem('TREATMENT', a.id, a.name, a.pkId) ");
			jpql.append("FROM Treatment a ");
			jpql.append("WHERE a.treatmentTypePkId = :pkId ");
		}
		
		if(Tool.INSPECTIONTYPE_EXAMINATION.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectItem('EXAMINATION', a.id, a.name, a.pkId) ");
			jpql.append("FROM Examination a ");
			jpql.append("WHERE a.examinationTypePkId = :pkId ");
		}
		
		if(Tool.INSPECTIONTYPE_SURGERY.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectItem('SURGERY', a.name, a.name, a.pkId) ");
			jpql.append("FROM Surgery a ");
			jpql.append("WHERE a.surgeryTypePkId = :pkId ");
		}
		
//		if(Tool.INSPECTIONTYPE_MEDICINE.equals(type)){
//			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectItem(:type, a.name, a.pkId) ");
//			jpql.append("FROM XrayType a ");
//			jpql.append("WHERE a.xrayTypePkId = :pkId ");
//		}
		
		List<PaymentPackageSelectItem> list = getByQuery(PaymentPackageSelectItem.class, jpql.toString(), parameters);
		
		for (PaymentPackageSelectItem paymentPackageSelectItem : list) {
			jpql = new StringBuilder();
			parameters.put("pkId", paymentPackageSelectItem.getPkId());
			if(Tool.INSPECTIONTYPE_XRAY.equals(paymentPackageSelectItem.getType())){
				jpql.append("SELECT a FROM XrayPrice a ");
				jpql.append("WHERE a.xrayPkId = :pkId ");
				jpql.append("AND a.beginDate < :dte ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<XrayPrice> list2 = getByQuery(XrayPrice.class, jpql.toString(), parameters);
				if(list2.size() > 0){
					paymentPackageSelectItem.setPrice(list2.get(0).getPrice());
				}
			}
			
			if(Tool.INSPECTIONTYPE_TREATMENT.equals(paymentPackageSelectItem.getType())){
				jpql.append("SELECT a FROM TreatmentPrice a ");
				jpql.append("WHERE a.treatmentPkId = :pkId ");
				jpql.append("AND a.beginDate < :dte ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<TreatmentPrice> list2 = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);
				if(list2.size() > 0){
					paymentPackageSelectItem.setPrice(list2.get(0).getPrice());
				}
			}
			
			if(Tool.INSPECTIONTYPE_EXAMINATION.equals(paymentPackageSelectItem.getType())){
				jpql.append("SELECT a FROM ExaminationPrice a ");
				jpql.append("WHERE a.examinationPkId = :pkId ");
				jpql.append("AND a.beginDate < :dte ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<ExaminationPrice> list2 = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
				if(list2.size() > 0){
					paymentPackageSelectItem.setPrice(list2.get(0).getPrice());
				}
			}
			
			if(Tool.INSPECTIONTYPE_SURGERY.equals(paymentPackageSelectItem.getType())){
				jpql.append("SELECT a FROM SurgeryPrice a ");
				jpql.append("WHERE a.surgeryPkId = :pkId ");
				jpql.append("AND a.beginDate < :dte ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<SurgeryPrice> list2 = getByQuery(SurgeryPrice.class, jpql.toString(), parameters);
				if(list2.size() > 0){
					paymentPackageSelectItem.setPrice(list2.get(0).getPrice());
				}
			}
		}
		
		return list;
	}
	
	public List<PaymentPackageSelectType> getPackageSelectTypes(String type) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("type", type);
		if(Tool.INSPECTIONTYPE_XRAY.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectType('XRAY', a.name, a.pkId) ");
			jpql.append("FROM XrayType a ");
		}
		
		if(Tool.INSPECTIONTYPE_TREATMENT.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectType('TREATMENT', a.name, a.pkId) ");
			jpql.append("FROM TreatmentType a ");
		}
		
		if(Tool.INSPECTIONTYPE_EXAMINATION.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectType('EXAMINATION', a.name, a.pkId) ");
			jpql.append("FROM ExaminationType a ");
		}
		
		if(Tool.INSPECTIONTYPE_SURGERY.equals(type)){
			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectType('SURGERY', a.name, a.pkId) ");
			jpql.append("FROM SurgeryType a ");
		}
		
//		if(Tool.INSPECTIONTYPE_MEDICINE.equals(type)){
//			jpql.append("SELECT NEW hospital.businessentity.PaymentPackageSelectType(:type, a.name, a.pkId) ");
//			jpql.append("FROM XrayType a ");
//		}
		
		List<PaymentPackageSelectType> list = getByQuery(PaymentPackageSelectType.class, jpql.toString(), parameters);
		return list;
	}
	
	public void savePaymentPackage(PaymentPackage paymentPackage, List<PaymentPackageDtl> paymentPackageDtls, LoggedUser loggedUser) throws Exception{
		BigDecimal paymentPackagePkId = Tools.newPkId();
		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		paymentPackage.setUpdatedBy(loggedUser.getUser().getPkId());
		paymentPackage.setUpdatedDate(dte);
		if(Tool.ADDED.equals(paymentPackage.getStatus())){
			paymentPackage.setCreatedBy(loggedUser.getUser().getPkId());
			paymentPackage.setCreatedDate(dte);
			paymentPackage.setPkId(paymentPackagePkId);
			
			insert(paymentPackage);
		}
		if(Tool.MODIFIED.equals(paymentPackage.getStatus())){
			paymentPackagePkId = paymentPackage.getPkId();
			
			update(paymentPackage);
			deleteByAnyField(PaymentPackageDtl.class, "paymentPackagePkId", paymentPackagePkId);
		}
		List<PaymentPackageDtl> dtls = new ArrayList<PaymentPackageDtl>();
		for(PaymentPackageDtl dtl : paymentPackageDtls){
			PaymentPackageDtl packageDtl = new PaymentPackageDtl();
			pkId = pkId.add(BigDecimal.ONE);
			packageDtl.setPkId(pkId);
			packageDtl.setPaymentPackagePkId(paymentPackagePkId);
			packageDtl.setType(dtl.getType());
			packageDtl.setTypePkId(dtl.getTypePkId());
			packageDtl.setAmount(dtl.getAmount());
			dtls.add(packageDtl);
		}
		insert(dtls);
	}
	
	public PaymentPackage getPaymentPackageByPkId(BigDecimal paymentPackagePkId) throws Exception{
		return getByPkId(PaymentPackage.class, paymentPackagePkId);
	}
	
	public List<PaymentPackageDtl> getPaymentPackageDtlsByPaymentPackagePkId(BigDecimal paymentPackagePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("paymentPackagePkId", paymentPackagePkId);
		
		jpql.append("SELECT a FROM PaymentPackageDtl a ");
		jpql.append("WHERE a.paymentPackagePkId = :paymentPackagePkId ");
		
		List<PaymentPackageDtl> dtls = getByQuery(PaymentPackageDtl.class, jpql.toString(), parameters);
		for (PaymentPackageDtl paymentPackageDtl : dtls) {
			jpql = new StringBuilder();
			parameters.put("typePkId", paymentPackageDtl.getTypePkId());
			if(Tool.INSPECTIONTYPE_XRAY.equals(paymentPackageDtl.getType())){
				jpql.append("SELECT a FROM Xray a ");
				jpql.append("WHERE a.pkId = :typePkId ");
				
				List<Xray> list = getByQuery(Xray.class, jpql.toString(), parameters);
				if(list.size() > 0) {
					paymentPackageDtl.setId(list.get(0).getId());
					paymentPackageDtl.setName(list.get(0).getName());
				}
			}
			if(Tool.INSPECTIONTYPE_TREATMENT.equals(paymentPackageDtl.getType())){
				jpql.append("SELECT a FROM Treatment a ");
				jpql.append("WHERE a.pkId = :typePkId ");
				
				List<Treatment> list = getByQuery(Treatment.class, jpql.toString(), parameters);
				if(list.size() > 0) {
					paymentPackageDtl.setId(list.get(0).getId());
					paymentPackageDtl.setName(list.get(0).getName());
				}
			}
			if(Tool.INSPECTIONTYPE_SURGERY.equals(paymentPackageDtl.getType())){
				jpql.append("SELECT a FROM Surgery a ");
				jpql.append("WHERE a.pkId = :typePkId ");
				
				List<Surgery> list = getByQuery(Surgery.class, jpql.toString(), parameters);
				if(list.size() > 0) {
					paymentPackageDtl.setId(list.get(0).getName());
					paymentPackageDtl.setName(list.get(0).getName());
				}
			}
			if(Tool.INSPECTIONTYPE_EXAMINATION.equals(paymentPackageDtl.getType())){
				jpql.append("SELECT a FROM Examination a ");
				jpql.append("WHERE a.pkId = :typePkId ");
				
				List<Examination> list = getByQuery(Examination.class, jpql.toString(), parameters);
				if(list.size() > 0) {
					paymentPackageDtl.setId(list.get(0).getId());
					paymentPackageDtl.setName(list.get(0).getName());
				}
			}
		}
		return dtls;
	}
	
	public void deletePaymentPackage(BigDecimal paymentPackagePkId) throws Exception{
		deleteByAnyField(PaymentPackageDtl.class, "paymentPackagePkId", paymentPackagePkId);
		deleteByPkId(PaymentPackage.class, paymentPackagePkId);
	}

}

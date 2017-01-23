package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.entity.*;

import javax.ejb.Stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicCustomer", mappedName = "hospital.businesslogic.LogicCustomer")
public class LogicCustomer extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicCustomer, ILogicCustomerLocal {

	@Resource
	SessionContext sessionContext;

	public LogicCustomer() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<SubOrganization> getSubOrganizations() throws Exception {
		return getAll(SubOrganization.class);

	}

	@Override
	public List<Customer> getCustomers(String filterKey) throws Exception {

		List<Customer> list = new ArrayList<Customer>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("filter", "%" + filterKey + "%");
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Customer(a, d.nameMn, b.name, c.name, f.nameMn ) ");
		jpql.append("FROM Customer a ");
		jpql.append("LEFT JOIN Aimag b ON a.aimagPkId = b.pkId ");
		jpql.append("LEFT JOIN Soum c ON a.sumPkId = c.pkId ");
		jpql.append("LEFT JOIN View_ConstantItype d ON a.insuranceTypePkId = d.pkId ");
		jpql.append("LEFT JOIN View_ConstantArrange f ON a.arrangePkId = f.pkId ");
		if (!filterKey.equals("")) {
			jpql.append("WHERE a.lastName LIKE :filter OR a.firstName LIKE :filter OR a.regNumber LIKE :filter ");
		}

		list = getByQuery(Customer.class, jpql.toString(), parameters, 0, 1000);

		return list;
	}

	public String getCustomerCardNumber() throws Exception {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.cardNumber FROM Customer a ");
		jpql.append("ORDER BY a.cardNumber DESC ");

		List<String> list = getByQuery(String.class, jpql.toString(), null, 0, 1);
		String lastCardNumber = (list == null || list.size() < 1) ? "0" : list.get(0);
		int cardNumber = Integer.parseInt(lastCardNumber);
		cardNumber++;
		return getCusStringtomerCardNumberString(cardNumber);
	}

	public String getCusStringtomerCardNumberString(int cardNumber) {
		String lastCardNumber = "";
		if (cardNumber < 10000000)
			lastCardNumber += "0";
		if (cardNumber < 1000000)
			lastCardNumber += "0";
		if (cardNumber < 100000)
			lastCardNumber += "0";
		if (cardNumber < 10000)
			lastCardNumber += "0";
		if (cardNumber < 1000)
			lastCardNumber += "0";
		if (cardNumber < 100)
			lastCardNumber += "0";
		if (cardNumber < 10)
			lastCardNumber += "0";
		lastCardNumber += "" + cardNumber;
		return lastCardNumber;
	}

	public void reGenerateCardNumber() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Customer a");
		List<Customer> customers = getByQuery(Customer.class, jpql.toString(), null);
		int index = 0;
		for (Customer customer : customers) {
			index++;
			customer.setCardNumber(getCusStringtomerCardNumberString(index));
		}

		update(customers);
	}

	@Override
	public Customer saveCustomer(Customer customer, LoggedUser lu) throws Exception {

		if (Tool.ADDED.equals(customer.getStatus())) {
			List<Customer> customers = getByAnyField(Customer.class, "regNumber", customer.getRegNumber());
			if (customers.size() > 0)
				throw new Exception("duplicate");
			customer.setPkId(Tools.newPkId());
			customer.setOrganizationPkId(lu.getUser().getOrganizationPkId());
			customer.setRegNumber(customer.getRegNumber().toUpperCase());
			customer.setCardNumber(getCustomerCardNumber());
			customer.setFirstName(customer.getFirstName().trim());
			customer.setLastName(customer.getLastName().trim());
			if (!Tool.UserDefaultImage.equals(customer.getCustomerImage())) {
				CustomerImage ci = new CustomerImage();
				ci.setPkId(Tools.newPkId());
				ci.setImage(customer.getCustomerImage());
				ci.setCustomerPkId(customer.getPkId());
				insert(ci);
			}
			insert(customer);

		} else if (Tool.MODIFIED.equals(customer.getStatus())) {
			List<Customer> customers = getByAnyField(Customer.class, "regNumber", customer.getRegNumber());
			if (customers.size() > 1)
				throw new Exception("duplicate");

			customer.getListCustomerImage();
			if (customer.getListCustomerImage() != null && customer.getListCustomerImage().size() >= 1) {
				CustomerImage ci = getByPkId(CustomerImage.class, customer.getListCustomerImage().get(0));
				ci.setImage(customer.getCustomerImage());
				ci.setCustomerPkId(customer.getPkId());
				update(ci);
			}

			update(customer);
		} else if (Tool.DELETE.equals(customer.getStatus())) {
			deleteByPkId(Customer.class, customer.getPkId());
		}
		return customer;

	}

	@Override
	public List<Profession> getProfessions() throws Exception {
		return getAll(Profession.class);
	}

	@Override
	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, Date beginDate, Date endDate, int status,
			String filterKey, int typestatus, int intguest) throws Exception {

		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setHours(23);
		endDate.setMinutes(59);

		StringBuilder jpql = new StringBuilder();
		StringBuilder jpqlHis = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("userPkId", lu.getUser().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("fKey", "%" + filterKey + "%");

		jpqlHis.append("SELECT  NEW hospital.businessentity.CustomerRequest(a, b, c ) ");
		jpqlHis.append("FROM EmployeeRequestHistory a ");
		jpqlHis.append("LEFT JOIN Employee b ON a.employeePkId = b.pkId ");
		jpqlHis.append("LEFT JOIN Customer c ON a.customerPkId = c.pkId ");
		jpqlHis.append("LEFT JOIN Inspection d ON a.pkId = d.requestPkId ");
		jpqlHis.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpqlHis.append("AND a.organizationPkId = :organizationPkId ");
		jpqlHis.append("AND b.userPkId = :userPkId ");
		jpqlHis.append("AND b.pkId IS NOT NULL ");
		jpqlHis.append("AND c.pkId IS NOT NULL ");
		jpql.append("SELECT  NEW hospital.businessentity.CustomerRequest(a, b, c )");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("LEFT JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("LEFT JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("LEFT JOIN Inspection d ON a.pkId = d.requestPkId ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		jpql.append("AND b.userPkId = :userPkId ");
		jpql.append("AND b.pkId IS NOT NULL ");
		jpql.append("AND c.pkId IS NOT NULL ");

		if (status == 1) {
			// Үзлэгт орсон
			jpqlHis.append("AND a.mood in (2, 4) ");
			jpql.append("AND a.mood in (2, 4) ");
		}
		if (status == 2) {
			// Үзлэгт орох
			jpqlHis.append("AND a.mood in (1, 0) ");
			jpql.append("AND a.mood in (1, 0) ");
		}
		if (status == 3) {
			// Түр хадгалсан
			jpqlHis.append("AND a.mood in (5, 6) ");
			jpql.append("AND a.mood in (5, 6) ");
		}
		if (status == 4) {
			// Дахин үзлэг
			jpqlHis.append("AND a.mood in (3) ");
			jpql.append("AND a.mood in (3) ");
		}
		if (typestatus == 0) {
			// Өвчний анхан утга
			jpqlHis.append("AND d.inspectionType in (0) ");
			jpql.append("AND d.inspectionType in (0) ");
		}
		if (typestatus == 1) {
			// Өвчний учир давтан
			jpqlHis.append("AND d.inspectionType in (1) ");
			jpql.append("AND d.inspectionType in (1) ");
		}
		if (typestatus == 2) {
			// Урьдчилан сэргийлэх
			jpqlHis.append("AND d.inspectionType in (2) ");
			jpql.append("AND d.inspectionType in (2) ");
		}
		if (typestatus == 3) {
			// Гэрийн хяналт
			jpqlHis.append("AND d.inspectionType in (3) ");
			jpql.append("AND d.inspectionType in (3) ");
		}
		if (typestatus == 4) {
			// Диспенсерийн хяналт
			jpqlHis.append("AND d.inspectionType in (4) ");
			jpql.append("AND d.inspectionType in (4) ");
		}
		if (intguest == -1) {
			jpqlHis.append(
					"AND ((CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 0 OR (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 1) ");
			jpql.append(
					"AND ((CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 0 OR (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 1) ");
		}
		if (intguest == 0) {
			// Ирээгүй
			jpqlHis.append("AND (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 0 ");
			jpql.append("AND (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 0 ");
		}
		if (intguest == 1) {
			// ирсэн
			jpqlHis.append("AND (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 1 ");
			jpql.append("AND (CASE WHEN a.guest IS NULL THEN 0 ELSE a.guest END) = 1 ");
		}
		jpql.append("AND (c.firstName LIKE :fKey OR c.lastName LIKE :fKey OR c.regNumber LIKE :fKey ) ");
		jpqlHis.append("AND (c.firstName LIKE :fKey OR c.lastName LIKE :fKey OR c.regNumber LIKE :fKey ) ");

		List<CustomerRequest> employeeRequests = getByQuery(CustomerRequest.class, jpql.toString(), parameters);

		List<CustomerRequest> employeeRequestHistorys = getByQuery(CustomerRequest.class, jpqlHis.toString(),
				parameters);
		employeeRequests.addAll(employeeRequestHistorys);
		for (CustomerRequest customerRequest : employeeRequests) {
			jpql = new StringBuilder();
			parameters.put("requestPkId", customerRequest.getEmployeeRequest().getPkId());
			jpql.append("SELECT a FROM Inspection a WHERE a.requestPkId = :requestPkId");
			List<Inspection> inspections = getByQuery(Inspection.class, jpql.toString(), parameters);
			if (inspections.size() > 0)
				customerRequest.setInspection(inspections.get(0));

			parameters.put("customerPkId", customerRequest.getCustomer().getPkId());

			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.Payment(a.amount, SUM(b.cashInAmount)) FROM Payment a ");
			jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
			jpql.append("WHERE a.customerPkId = :customerPkId ");
			jpql.append("GROUP BY a.amount ");
			List<Payment> list = getByQuery(Payment.class, jpql.toString(), parameters);
			BigDecimal loanAmount = BigDecimal.ZERO;
			// for (Payment payment : list) {
			// loanAmount = loanAmount.add(payment.getCustomerPkId())
			// .subtract(payment.getAmount());
			// }

			customerRequest.setLoanAmount(loanAmount);
		}
		for (CustomerRequest c : employeeRequests) {
			CustomHashMap map = new CustomHashMap();
			map.put("inspectionPkId", c.getEmployeeRequest().getPkId());
			map.put("employeePkId", c.getEmployeeRequest().getEmployeePkId());
			StringBuilder dfp = new StringBuilder();
			dfp.append("SELECT DISTINCT dia ");
			dfp.append("FROM Inspection d ");
			dfp.append("INNER JOIN Customer  cs ON  d.customerPkId = cs.pkId ");
			dfp.append("INNER JOIN  CustomerDiagnose  cd ON  d.pkId = cd.inspectionPkId ");
			dfp.append(" INNER JOIN Diagnose   dia  ON  cd.diagnosePkId = dia.pkId ");
			dfp.append("WHERE cd.type=1 AND  d.requestPkId=:inspectionPkId AND d.employeePkId= :employeePkId ");
			List<Diagnose> dlist = getByQuery(Diagnose.class, dfp.toString(), map);
			if (dlist.size() > 0) {
				c.setDiagnose(dlist);
			}
		}

		parameters.put("dte", endDate);
		for(CustomerRequest customerRequest : employeeRequests){
			parameters.put("customerPkId", customerRequest.getCustomer().getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a.pkId FROM EmployeeRequest a ");
			jpql.append("WHERE a.customerPkId = :customerPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("AND a.hasPayment = 0 ");
			
			customerRequest.getCustomer().setUldegdelStatus("no");
			if(getByQuery(BigDecimal.class, jpql.toString(), parameters).size() > 0){
				customerRequest.getCustomer().setUldegdelStatus("yes");
			}
		}
		
		return employeeRequests;
	}

	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, Date beginDate, Date endDate,
			BigDecimal subOrganizationPkId, boolean self) throws Exception {

		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setHours(23);
		endDate.setMinutes(59);

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("userPkId", lu.getUser().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c, d.name) ");
		jpql.append("FROM EmployeeRequestHistory a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("INNER JOIN SubOrganization d ON b.subOrganizationPkId = d.pkId ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");

		if (subOrganizationPkId.compareTo(BigDecimal.ZERO) != 0) {
			parameters.put("subOrganizationPkId", subOrganizationPkId);
			jpql.append("AND b.subOrganizationPkId = :subOrganizationPkId ");
		}
		if (self == true) {
			List<Employee> employees = getByAnyField(Employee.class, "userPkId", lu.getUser().getPkId());
			List<BigDecimal> pkIds = new ArrayList<BigDecimal>();
			for (Employee employee : employees) {
				pkIds.add(employee.getPkId());
			}
			parameters.put("pkIds", pkIds);
			if (pkIds.size() > 0) {
				jpql.append("AND a.employeePkId IN :pkIds ");
			}
		}

		List<CustomerRequest> employeeRequests = getByQuery(CustomerRequest.class, jpql.toString(), parameters);

		/*
		 * for (CustomerRequest customerRequest : employeeRequests) { jpql = new
		 * StringBuilder(); parameters.put("requestPkId",
		 * customerRequest.getEmployeeRequest() .getPkId()); jpql.append(
		 * "SELECT a FROM Inspection a WHERE a.requestPkId = :requestPkId");
		 * List<Inspection> inspections = getByQuery(Inspection.class,
		 * jpql.toString(), parameters); if (inspections.size() > 0)
		 * customerRequest.setInspection(inspections.get(0)); }
		 */

		return employeeRequests;
	}

	@Override
	public List<Payment> getCustomerPayment(LoggedUser lu, BigDecimal customerPkId) throws Exception {

		List<Payment> list = new ArrayList<Payment>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append(
				"SELECT NEW hospital.entity.Payment(a.customerPkId, a.amount, SUM(b.cashInAmount)) from Payment a ");
		jpql.append("inner join PaymentHistory b ON a.pkId = b.paymentPkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("GROUP BY a.customerPkId, a.amount ");

		list = getByQuery(Payment.class, jpql.toString(), parameters);

		Payment payment = new Payment();
		payment.setCustomerPkId(customerPkId);
		payment.setAmount(BigDecimal.ZERO);
		for (Payment paymt : list) {
			payment.setAmount(payment.getAmount().subtract(paymt.getAmount()));
		}
		list.clear();
		list.add(payment);
		return list;
	}

	@Override
	public List<ViewConstantJob> getViewConstantJobs() throws Exception {
		// Ажлын газрын жагсаалт
		return getAll(ViewConstantJob.class);
	}

	@Override
	public List<ViewConstantIsChild> getViewConstantIsChilds() throws Exception {
		// Хүүхэд бол бөглөх жагсаалт
		return getAll(ViewConstantIsChild.class);
	}

	@Override
	public List<ViewConstantSocialStatus> getViewConstantSocialStatuss() throws Exception {
		// Нийгмйн байдлын жагсаалт
		return getAll(ViewConstantSocialStatus.class);
	}

	public List<ViewCustomer> getCustomerReport() throws Exception {
		List<ViewCustomer> customers = getAll(ViewCustomer.class);
		return customers;
	}

	public List<Customer> getCustomers() throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		jpql.append("SELECT a FROM Customer a ");
		jpql.append("ORDER BY a.pkId DESC ");

		List<Customer> customers = getByQuery(Customer.class, jpql.toString(), parameters);
		return customers;
	}

	public List<Customer> getCustomers(BigDecimal organizationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", organizationPkId);
		jpql.append("SELECT a FROM Customer a WHERE a.organizationPkId = :organizationPkId ");
		jpql.append("ORDER BY a.pkId DESC ");

		List<Customer> customers = getByQuery(Customer.class, jpql.toString(), parameters);
		return customers;
	}
	
	public BigDecimal customerLoanAmount(BigDecimal customerPkId) throws Exception{
		BigDecimal ret = BigDecimal.ZERO;
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Payment a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		
		List<Payment> payments = getByQuery(Payment.class, jpql.toString(), parameters);
		
		for (Payment payment : payments) {
			ret = ret.add(payment.getAmount().subtract(payment.getPaidAmount()));
		}
		
		return ret;
	}

	public Customer getCustomerByRegNumber(String regNumber) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("regNumber", regNumber);
		jpql.append("SELECT a FROM Customer a WHERE a.regNumber = :regNumber ");

		List<Customer> customers = getByQuery(Customer.class, jpql.toString(), parameters);
		if (customers == null || customers.size() < 1)
			return null;

		parameters.put("customerPkId", customers.get(0).getPkId());
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM EmployeeRequest a WHERE a.customerPkId = :customerPkId ");
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);

		Date ddd = new Date();
		parameters.put("ddd", ddd);

		jpql = new StringBuilder();
		jpql.append(
				"SELECT a FROM EmployeeRequest a WHERE a.customerPkId = :customerPkId AND a.beginDate > :ddd AND a.mood = 1 ");
		List<EmployeeRequest> employeeRequests1 = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);

		jpql = new StringBuilder();
		jpql.append(
				"SELECT a FROM EmployeeRequest a WHERE a.customerPkId = :customerPkId AND a.endDate < :ddd AND a.mood = 1 ");
		List<EmployeeRequest> employeeRequests2 = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);

		Customer customer = customers.get(0);
		customer.setRequestCount(employeeRequests.size());
		customer.setExecutoryCount(customerLoanAmount(customers.get(0).getPkId()));
		customer.setUnreliableCount(employeeRequests2.size());

		return customer;
	}

	@Override
	public List<ViewConstantCountry> getViewConstantCountries() throws Exception {
		// TODO Auto-generated method stub
		return getAll(ViewConstantCountry.class);
	}

	@Override
	public List<View_ConstantFamilyRelation> getFamilyRelations() throws Exception {
		// TODO Auto-generated method stub
		return getAll(View_ConstantFamilyRelation.class);
	}

	@Override
	public List<View_ConstantArrange> getArranges() throws Exception {
		// TODO Auto-generated method stub
		return getAll(View_ConstantArrange.class);
	}

	@Override
	public List<ExaminationRequestCompleted> getExaminationRequestCompleted(BigDecimal customerPkId, Date beginDate,
			Date endDate, String filterKey, BigDecimal examinationTypePkId) throws Exception {

		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setHours(23);
		endDate.setMinutes(59);
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("filterKey", "%" + filterKey + "%");
		parameters.put("examinationTypePkId", examinationTypePkId);
		jpql.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(a, b.name, c, d.name, e.firstName) ");
		jpql.append("FROM ExaminationRequestCompleted a ");
		jpql.append("INNER JOIN Examination b ON a.examinationPkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("INNER JOIN Users d ON a.updatedBy = d.pkId ");
		jpql.append("INNER JOIN Employee e ON a.employeePkId = e.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("AND a.requestDate  BETWEEN :beginDate AND :endDate ");
		jpql.append("AND (b.name LIKE :filterKey ) ");
		if (examinationTypePkId.compareTo(BigDecimal.ZERO) != 0) {
			jpql.append("AND b.examinationTypePkId = :examinationTypePkId ");
		}
		List<ExaminationRequestCompleted> list = new ArrayList<ExaminationRequestCompleted>();
		list = getByQuery(ExaminationRequestCompleted.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<ExaminationType> getExaminationTypesWithRequestCount(BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT NEW hospital.entity.ExaminationType(a.pkId, a.name, COUNT(c.pkId)) ");
		jpql.append("FROM ExaminationType a ");
		jpql.append("INNER JOIN Examination b ON a.pkId = b.examinationTypePkId ");
		jpql.append("INNER JOIN ExaminationRequestCompleted c ON c.examinationPkId = b.pkId ");
		jpql.append("WHERE c.customerPkId =:customerPkId ");
		jpql.append("GROUP BY a.pkId, a.name ");

		jpql.append(" ");

		return getByQuery(ExaminationType.class, jpql.toString(), parameters);
	}

	@Override
	public boolean checkRegNumber(String regNumber) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("regNumber", "%" + regNumber + "%");
		jpql.append("SELECT a.regNumber ");
		jpql.append("FROM Customer a ");
		jpql.append("WHERE a.regNumber LIKE :regNumber ");
		if (getByQuery(String.class, jpql.toString(), parameters).size() > 0)
			return true;
		else
			return false;
	}

}

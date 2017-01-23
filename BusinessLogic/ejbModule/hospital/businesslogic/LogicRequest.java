package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicRequestLocal;
import hospital.entity.Degree;
import hospital.entity.DegreePriceHistory;
import hospital.entity.Employee;
import hospital.entity.EmployeeMemo;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.Payment;
import hospital.entity.SubOrganization;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.apache.taglibs.standard.tag.common.sql.ParamTagSupport;

import logic.data.CustomHashMap;

@Stateless(name = "LogicRequest", mappedName = "hospital.businesslogic.InfoLogic")
public class LogicRequest extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicRequest,
		ILogicRequestLocal {

	@Resource
	SessionContext sessionContext;

	public LogicRequest() {

	}

	public List<Employee> getEmployeeBySubOrganization(
			BigDecimal subOrganizationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		jpql.append("SELECT a FROM Employee a WHERE a.subOrganizationPkId = :subOrganizationPkId AND a.isInspect = 1 ");
		List<Employee> employees = getByQuery(Employee.class, jpql.toString(),
				parameters);
		Date beginDate = new Date();
		beginDate.setHours(1);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		Date endDate = new Date();
		endDate.setHours(1);
		endDate.setMinutes(2);
		endDate.setSeconds(0);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		for (Employee employee : employees) {
			parameters.put("employeePkId", employee.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
			jpql.append("AND a.employeePkId = :employeePkId ");
			
			List<EmployeeRequest> employeeRequests = getByQuery(
					EmployeeRequest.class, jpql.toString(), parameters);
			employee.setRequestCount(employeeRequests.size());
		}
		
		return employees;
	}
	
	public List<Employee> getEmployeeBySubOrganization(
			BigDecimal subOrganizationPkId, Date beginDate, Date endDate) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		jpql.append("SELECT a FROM Employee a WHERE a.subOrganizationPkId = :subOrganizationPkId AND a.isInspect = 1 ");
		List<Employee> employees = getByQuery(Employee.class, jpql.toString(),
				parameters);
		Date bDate = new Date();
		Date eDate = new Date();
		bDate.setHours(0);
		bDate.setMinutes(0);
		bDate.setSeconds(0);
		eDate.setHours(23);
		eDate.setMinutes(59);
		eDate.setSeconds(59);
		parameters.put("beginDate", bDate);
		parameters.put("endDate", eDate);
		for (Employee employee : employees) {
			parameters.put("employeePkId", employee.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
			jpql.append("AND a.employeePkId = :employeePkId ");
			jpql.append("AND a.beginDate = a.endDate ");
			List<EmployeeRequest> employeeRequests = getByQuery(
					EmployeeRequest.class, jpql.toString(), parameters);
			employee.setRequestCount(employeeRequests.size());
			
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		}
		
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		
		for (Employee employee : employees) {
			parameters.put("employeePkId", employee.getPkId());
			
			jpql = new StringBuilder();
			jpql.append("SELECT a.pkId FROM EmployeeRequest a ");
			jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
			jpql.append("AND a.employeePkId = :employeePkId ");
			
			employee.setEmployeeRequestCount(getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			
			jpql = new StringBuilder();
			jpql.append("SELECT a.pkId FROM EmployeeRequestHistory a ");
			jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
			jpql.append("AND a.employeePkId = :employeePkId ");
			
			employee.setEmployeeRequestCount(employee.getEmployeeRequestCount() + getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
		}
		
		return employees;
	}
	
	public List<SubOrganization> getOrganizations() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM SubOrganization a ");
		jpql.append("INNER JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("WHERE b.isInspect = 1 ");
		jpql.append("AND a.orderable = 1 ");
		return getByQuery(SubOrganization.class, jpql.toString(), null);
	}

	public Employee getSignedEmployees(BigDecimal employeePkId)
			throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);
		
		return getByPkId(Employee.class, employeePkId);
	}

	@Override
	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, BigDecimal employeePkId, Date orderDate) throws Exception {
		Date beginDate = new Date();;
		Date endDate = new Date();
		beginDate.setMonth(orderDate.getMonth());
		beginDate.setYear(orderDate.getYear());
		beginDate.setDate(orderDate.getDate());
		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setMonth(orderDate.getMonth());
		endDate.setYear(orderDate.getYear());
		endDate.setDate(orderDate.getDate());
		endDate.setHours(23);
		endDate.setMinutes(59);

		StringBuilder jpql = new StringBuilder();
		StringBuilder jpqlHis = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("employeePkId", employeePkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		

		jpqlHis.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c) ");
		jpqlHis.append("FROM EmployeeRequestHistory a ");
		jpqlHis.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpqlHis.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpqlHis.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpqlHis.append("AND a.organizationPkId = :organizationPkId ");
		jpqlHis.append("AND a.employeePkId = :employeePkId ");
		

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		jpql.append("AND a.employeePkId = :employeePkId ");
		

		List<CustomerRequest> employeeRequests = getByQuery(
				CustomerRequest.class, jpql.toString(), parameters);

		List<CustomerRequest> employeeRequestHistorys = getByQuery(
				CustomerRequest.class, jpqlHis.toString(), parameters);
		employeeRequests.addAll(employeeRequestHistorys);
		for (CustomerRequest customerRequest : employeeRequests) {
			BigDecimal loadAmount = BigDecimal.ZERO;
			customerRequest.setLoanAmount(loadAmount);
		}

		return employeeRequests;
	}
	
	public BigDecimal customerLoanAmount(BigDecimal customerPkId) throws Exception{
		BigDecimal ret = BigDecimal.ZERO;
		Date date = new Date();
		date.setDate(23);
		date.setMinutes(59);
		date.setSeconds(59);
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("dte", date);
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM EmployeeRequest a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("AND a.endDate < :dte ");
		jpql.append("AND a.hasPayment = 0 ");
		
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		
		for (EmployeeRequest employeeRequest : employeeRequests) {
			Employee employee = getByPkId(Employee.class, employeeRequest.getEmployeePkId());
			if(employee != null) {
				Degree degree = getByPkId(Degree.class, employee.getDegreePkId());
				if(degree != null) {
					jpql = new StringBuilder();
					parameters.put("degreePkId", degree.getPkId());
					jpql.append("SELECT a FROM DegreePriceHistory a ");
					jpql.append("WHERE a.degreePkId = :degreePkId ");
					jpql.append("AND a.priceUsageDate < :dte ");
					jpql.append("ORDER BY a.priceUsageDate DESC ");
					List<DegreePriceHistory> degreePriceHistories = getByQuery(DegreePriceHistory.class, jpql.toString(), parameters);
					if(degreePriceHistories.size() > 0) {
						if(employeeRequest.getReInspection() == 1){
							ret = ret.add(degreePriceHistories.get(0).getRePrice());
						}else {
							ret = ret.add(degreePriceHistories.get(0).getPrice());
						}
					}
				}
			}
		}
		
		
		return ret;
	}
	
	public List<CustomerRequest> getCustomerRequests(BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpqlHis = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("dte", new Date());

		jpqlHis.append("SELECT NEW hospital.businessentity.CustomerRequest(a) ");
		jpqlHis.append("FROM EmployeeRequestHistory a ");
		jpqlHis.append("WHERE a.customerPkId = :customerPkId ");
		jpqlHis.append("AND a.endDate < :dte ");		

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("AND a.endDate < :dte ");

		List<CustomerRequest> employeeRequests = getByQuery(
				CustomerRequest.class, jpql.toString(), parameters);

		List<CustomerRequest> employeeRequestHistorys = getByQuery(
				CustomerRequest.class, jpqlHis.toString(), parameters);
		employeeRequests.addAll(employeeRequestHistorys);

		return employeeRequests;
	}

	@Override
	public List<CustomerRequest> getCustomerRequests(LoggedUser lu, BigDecimal customerPkId, Date beginDate, Date endDate) throws Exception {
		
		beginDate.setHours(00);
		beginDate.setMinutes(00);
		endDate.setHours(23);
		endDate.setMinutes(59);
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpqlHis = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("customerPkId", customerPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("dte", new Date());

		jpqlHis.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c, d.name, e.name ) ");
		jpqlHis.append("FROM EmployeeRequestHistory a ");
		jpqlHis.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpqlHis.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpqlHis.append("INNER JOIN SubOrganization d ON b.subOrganizationPkId = d.pkId ");
		jpqlHis.append("INNER JOIN Users e ON e.pkId = a.createdBy ");
		jpqlHis.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpqlHis.append("AND a.organizationPkId = :organizationPkId ");
		jpqlHis.append("AND a.customerPkId = :customerPkId ");
		jpqlHis.append("AND a.endDate < :dte ");		

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c, d.name, e.name) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("INNER JOIN SubOrganization d ON b.subOrganizationPkId = d.pkId ");
		jpql.append("INNER JOIN Users e ON e.pkId = a.createdBy ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		jpql.append("AND a.customerPkId = :customerPkId ");
		jpql.append("AND a.endDate < :dte ");

		List<CustomerRequest> employeeRequests = getByQuery(
				CustomerRequest.class, jpql.toString(), parameters);

		List<CustomerRequest> employeeRequestHistorys = getByQuery(
				CustomerRequest.class, jpqlHis.toString(), parameters);
		employeeRequests.addAll(employeeRequestHistorys);
		for (CustomerRequest customerRequest : employeeRequests) {
			BigDecimal loadAmount = BigDecimal.ZERO;
			customerRequest.setLoanAmount(loadAmount);
		}

		return employeeRequests;
	}

	@Override
	public void setGuestUpdate(EmployeeRequest employeeRequest) throws Exception {
		update(employeeRequest);
	}
}

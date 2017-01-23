package hospital.businesslogic.interfaces;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicRequestLocal {
	public List<Employee> getEmployeeBySubOrganization(BigDecimal subOrganizationPkId) throws Exception;
	public List<Employee> getEmployeeBySubOrganization(BigDecimal subOrganizationPkId, Date beginDate, Date endDate) throws Exception;
	public void setGuestUpdate(EmployeeRequest employeeRequest) throws Exception;
	public Employee getSignedEmployees(BigDecimal employeePkId) throws Exception;
	public List<SubOrganization> getOrganizations() throws Exception;
	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, BigDecimal employeePkId, Date orderDate) throws Exception;
	public List<CustomerRequest> getCustomerRequests(LoggedUser lu, BigDecimal customerPkId, Date beginDate, Date endDate) throws Exception;
	public List<CustomerRequest> getCustomerRequests(BigDecimal customerPkId) throws Exception;
	public BigDecimal customerLoanAmount(BigDecimal customerPkId) throws Exception;
	

}

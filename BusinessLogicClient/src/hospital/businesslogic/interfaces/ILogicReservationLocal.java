package hospital.businesslogic.interfaces;

import hospital.businessentity.LoggedUser;
import hospital.entity.Customer;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicReservationLocal {
	public List<EmployeeRequest> getEmployeeRequestByDate(Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<EmployeeRequest> getEmployeeRequests(BigDecimal pkId) throws Exception;
	public List<EmployeeRequest> getEmployeeInspectionHistory(Customer customer, LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception;
	public boolean getDuplicateRequest(Date beginDate, Date endDate, BigDecimal customerPkId) throws Exception;
	
	public BigDecimal saveEmployeeRequest(EmployeeRequest employeeRequest, LoggedUser loggedUser) throws Exception;
	public EmployeeRequest getDupplicateEmployeeRequestByDate(EmployeeRequest employeeRequest) throws Exception;
	public Employee getEmployee(BigDecimal employeePkId, BigDecimal customerPkId) throws Exception;
}

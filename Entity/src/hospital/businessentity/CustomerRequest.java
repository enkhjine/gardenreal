package hospital.businessentity;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.Inspection;
import hospital.entity.Post;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerRequest {

	private EmployeeRequest employeeRequest;
	private EmployeeRequestHistory requestHistory;
	private Employee employee;

	private Customer customer;
	private Inspection inspection;
	private List<Diagnose> diagnose;
	private Date inspectionDate;
	private BigDecimal loanAmount;
	private boolean selected;

	public CustomerRequest() {
		super();
	}

	public CustomerRequest(EmployeeRequest employeeRequest, Employee employee, Customer customer, String name) {
		super();
		this.employeeRequest = employeeRequest;
		this.employee = employee;
		this.customer = customer;
		this.employee.setSubOrganizationName(name);

	}
	
	public CustomerRequest(EmployeeRequest employeeRequest, Employee employee, Customer customer, String name, String eName) {
		super();
		this.employeeRequest = employeeRequest;
		this.employee = employee;
		this.customer = customer;
		this.employee.setSubOrganizationName(name);

		this.employeeRequest.setCeName(eName);
	
	}
	public CustomerRequest(EmployeeRequest employeeRequest, Employee employee, Customer customer) {
		super();
		this.employeeRequest = employeeRequest;
		this.employee = employee;
		this.customer = customer;

	}

	public CustomerRequest(EmployeeRequestHistory requestHistory, Employee employee, Customer customer) {
		super();
		this.requestHistory = requestHistory;
		this.employee = employee;
		this.customer = customer;
		this.employeeRequest = new EmployeeRequest();
		this.employeeRequest.setPkId(requestHistory.getPkId());
		this.employeeRequest.setId(requestHistory.getId());
		this.employeeRequest.setOrganizationPkId(requestHistory.getOrganizationPkId());
		this.employeeRequest.setCustomerPkId(requestHistory.getCustomerPkId());
		this.employeeRequest.setEmployeePkId(requestHistory.getEmployeePkId());
		this.employeeRequest.setDescription(requestHistory.getDescription());
		this.employeeRequest.setMood(requestHistory.getMood());
		this.employeeRequest.setCreatedBy(requestHistory.getCreatedBy());
		this.employeeRequest.setCreatedDate(requestHistory.getCreatedDate());
		this.employeeRequest.setUpdatedBy(requestHistory.getUpdatedBy());
		this.employeeRequest.setUpdatedDate(requestHistory.getUpdatedDate());
		this.employeeRequest.setBeginDate(requestHistory.getBeginDate());
		this.employeeRequest.setEndDate(requestHistory.getEndDate());
		this.employeeRequest.setPaymentPkId(requestHistory.getPaymentPkId());
		this.employeeRequest.setArrivedDate(requestHistory.getArrivedDate());
		this.employeeRequest.setHasPayment(requestHistory.getHasPayment());
		this.employeeRequest.setIsExpress(requestHistory.getIsExpress());
		this.employeeRequest.setGuest(requestHistory.getGuest());
	}
	
	public CustomerRequest(EmployeeRequestHistory requestHistory, Employee employee, Customer customer, String name) {
		super();
		this.requestHistory = requestHistory;
		this.employee = employee;
		this.customer = customer;
		this.employeeRequest = new EmployeeRequest();
		this.employeeRequest.setPkId(requestHistory.getPkId());
		this.employeeRequest.setId(requestHistory.getId());
		this.employeeRequest.setOrganizationPkId(requestHistory.getOrganizationPkId());
		this.employeeRequest.setCustomerPkId(requestHistory.getCustomerPkId());
		this.employeeRequest.setEmployeePkId(requestHistory.getEmployeePkId());
		this.employeeRequest.setDescription(requestHistory.getDescription());
		this.employeeRequest.setMood(requestHistory.getMood());
		this.employeeRequest.setCreatedBy(requestHistory.getCreatedBy());
		this.employeeRequest.setCreatedDate(requestHistory.getCreatedDate());
		this.employeeRequest.setUpdatedBy(requestHistory.getUpdatedBy());
		this.employeeRequest.setUpdatedDate(requestHistory.getUpdatedDate());
		this.employeeRequest.setBeginDate(requestHistory.getBeginDate());
		this.employeeRequest.setEndDate(requestHistory.getEndDate());
		this.employeeRequest.setPaymentPkId(requestHistory.getPaymentPkId());
		this.employeeRequest.setArrivedDate(requestHistory.getArrivedDate());
		this.employeeRequest.setHasPayment(requestHistory.getHasPayment());
		this.employeeRequest.setIsExpress(requestHistory.getIsExpress());
		this.employeeRequest.setGuest(requestHistory.getGuest());
		this.employee.setSubOrganizationName(name);
	}
	
	public CustomerRequest(EmployeeRequestHistory requestHistory, Employee employee, Customer customer, String name, String eName) {
		super();
		this.requestHistory = requestHistory;
		this.employee = employee;
		this.customer = customer;
		this.employeeRequest = new EmployeeRequest();
		this.employeeRequest.setPkId(requestHistory.getPkId());
		this.employeeRequest.setId(requestHistory.getId());
		this.employeeRequest.setOrganizationPkId(requestHistory.getOrganizationPkId());
		this.employeeRequest.setCustomerPkId(requestHistory.getCustomerPkId());
		this.employeeRequest.setEmployeePkId(requestHistory.getEmployeePkId());
		this.employeeRequest.setDescription(requestHistory.getDescription());
		this.employeeRequest.setMood(requestHistory.getMood());
		this.employeeRequest.setCreatedBy(requestHistory.getCreatedBy());
		this.employeeRequest.setCreatedDate(requestHistory.getCreatedDate());
		this.employeeRequest.setUpdatedBy(requestHistory.getUpdatedBy());
		this.employeeRequest.setUpdatedDate(requestHistory.getUpdatedDate());
		this.employeeRequest.setBeginDate(requestHistory.getBeginDate());
		this.employeeRequest.setEndDate(requestHistory.getEndDate());
		this.employeeRequest.setPaymentPkId(requestHistory.getPaymentPkId());
		this.employeeRequest.setArrivedDate(requestHistory.getArrivedDate());
		this.employeeRequest.setHasPayment(requestHistory.getHasPayment());
		this.employeeRequest.setIsExpress(requestHistory.getIsExpress());
		this.employeeRequest.setGuest(requestHistory.getGuest());
		this.employee.setSubOrganizationName(name);
		this.employeeRequest.setCeName(eName);
	}
	
	public CustomerRequest(EmployeeRequest employeeRequest){
		this.employeeRequest = employeeRequest;
	}
	
	public CustomerRequest(EmployeeRequestHistory requestHistory){
		this.requestHistory = requestHistory;
		this.employeeRequest = new EmployeeRequest();
		this.employeeRequest.setPkId(requestHistory.getPkId());
		this.employeeRequest.setId(requestHistory.getId());
		this.employeeRequest.setOrganizationPkId(requestHistory.getOrganizationPkId());
		this.employeeRequest.setCustomerPkId(requestHistory.getCustomerPkId());
		this.employeeRequest.setEmployeePkId(requestHistory.getEmployeePkId());
		this.employeeRequest.setDescription(requestHistory.getDescription());
		this.employeeRequest.setMood(requestHistory.getMood());
		this.employeeRequest.setCreatedBy(requestHistory.getCreatedBy());
		this.employeeRequest.setCreatedDate(requestHistory.getCreatedDate());
		this.employeeRequest.setUpdatedBy(requestHistory.getUpdatedBy());
		this.employeeRequest.setUpdatedDate(requestHistory.getUpdatedDate());
		this.employeeRequest.setBeginDate(requestHistory.getBeginDate());
		this.employeeRequest.setEndDate(requestHistory.getEndDate());
		this.employeeRequest.setPaymentPkId(requestHistory.getPaymentPkId());
		this.employeeRequest.setArrivedDate(requestHistory.getArrivedDate());
		this.employeeRequest.setHasPayment(requestHistory.getHasPayment());
		this.employeeRequest.setIsExpress(requestHistory.getIsExpress());
		this.employeeRequest.setGuest(requestHistory.getGuest());
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}

	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public String getInspectionDate() {
		if (inspectionDate == null)
			return "Үзлэгт ороогүй";
		{
			String dateString = new SimpleDateFormat("dd-MM-yyyy").format(this.inspectionDate);


			return dateString;
		}

	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public BigDecimal getLoanAmount() {
		return loanAmount == null ? BigDecimal.ZERO : loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public EmployeeRequestHistory getRequestHistory() {
		return requestHistory;
	}

	public void setRequestHistory(EmployeeRequestHistory requestHistory) {
		this.requestHistory = requestHistory;
	}
	public List<Diagnose> getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(List<Diagnose> diagnose) {
		this.diagnose = diagnose;
	}

	public boolean isSelected() {
		if(employeeRequest != null) {
			selected = employeeRequest.getGuest() == 1;
		}
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}

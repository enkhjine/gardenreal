package hospital.businessentity;

import hospital.entity.Customer;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;

public class InspectionHistory {
	
	private Inspection inspection;
	private Customer customer;
	private Employee employee;
	private EmployeeRequest request;
	
	public InspectionHistory(){
		super();
	}
	
	public InspectionHistory(Inspection inspection, Customer customer, Employee employee, EmployeeRequest request){
		this.inspection = inspection;
		this.customer = customer;
		this.employee = employee;
		this.request = request;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public EmployeeRequest getRequest() {
		return request;
	}
	
	public void setRequest(EmployeeRequest request) {
		this.request = request;
	}
	
}

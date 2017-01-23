package hospital.businessentity;

import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.ViewCustomer;

import java.math.BigDecimal;

public class EmployeeRequestEntity {

	private EmployeeRequest employeeRequest;
	private ViewCustomer customer;
	private Inspection inspection;
	
	public EmployeeRequestEntity(){
		super();
	}
	
	public EmployeeRequestEntity(EmployeeRequest employeeRequest, ViewCustomer  customer, Inspection inspection){
		this.employeeRequest = employeeRequest;
		this.customer = customer;
		this.inspection = inspection;
	}
	
	public ViewCustomer getCustomer() {
		return customer;
	}
	
	public void setCustomer(ViewCustomer customer) {
		this.customer = customer;
	}
	
	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}
	
	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}
	
	public Inspection getInspection() {
		return inspection;
	}
	
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	
}

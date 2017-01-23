package hospital.report;

import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerTreatment;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.Organization;

public class Am29 {
	private  Organization  organization;
	private  List<Customer>customers;
	private  List<CustomerDiagnose> customerDiagnose;
	private  List<CustomerTreatment>  customerTreatment;
	private Diagnose  diagnose;
	private  Employee  employee;
	public Am29(){
		super();
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public List<CustomerDiagnose> getCustomerDiagnose() {
		return customerDiagnose;
	}
	public void setCustomerDiagnose(List<CustomerDiagnose> customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Diagnose getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}
	public List<CustomerTreatment> getCustomerTreatment() {
		return customerTreatment;
	}
	public void setCustomerTreatment(List<CustomerTreatment> customerTreatment) {
		this.customerTreatment = customerTreatment;
	}
	
}

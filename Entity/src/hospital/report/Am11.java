package hospital.report;

import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.Organization;
import hospital.entity.SubOrganization;

public class Am11 {
	SubOrganization subOrganization;
	Organization organization;
	Diagnose  diagnose;
	List<CustomerDiagnose> customerDiagnose;
	List<Customer >customer;
	List<Employee>  employees;
	public  Am11(){
		super();
	}
	public SubOrganization getSubOrganization() {
		return subOrganization;
	}
	public void setSubOrganization(SubOrganization subOrganization) {
		this.subOrganization = subOrganization;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}



	public List<Customer> getCustomer() {
		return customer;
	}



	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
	public List<CustomerDiagnose> getCustomerDiagnose() {
		return customerDiagnose;
	}
	public void setCustomerDiagnose(List<CustomerDiagnose> customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}
	public Diagnose getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(Diagnose diagnose) {
		this.diagnose = diagnose;
	}


}

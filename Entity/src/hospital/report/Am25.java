package hospital.report;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerTreatment;
import hospital.entity.Employee;

import java.util.List;

import hospital.entity.Organization;
import hospital.entity.SubOrganization;

public class Am25 {
	private Organization  organization;
	private List<CustomerTreatment> customerTreatments;
	private Customer  customers;
	private  Employee employees;
	private   SubOrganization  subOrganization;
	private List<CustomerDiagnose> customerDiagnose;
	private int treatmentcount;
	public  Am25(){
		super();
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<CustomerTreatment> getCustomerTreatments() {
		return customerTreatments;
	}
	public void setCustomerTreatments(List<CustomerTreatment> customerTreatments) {
		this.customerTreatments = customerTreatments;
	}
	public Employee getEmployees() {
		return employees;
	}
	public void setEmployees(Employee employees) {
		this.employees = employees;
	}
	public SubOrganization getSubOrganization() {
		return subOrganization;
	}
	public void setSubOrganization(SubOrganization subOrganization) {
		this.subOrganization = subOrganization;
	}
	public List<CustomerDiagnose> getCustomerDiagnose() {
		return customerDiagnose;
	}
	public void setCustomerDiagnose(List<CustomerDiagnose> customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}
	public Customer getCustomers() {
		return customers;
	}
	public void setCustomers(Customer customers) {
		this.customers = customers;
	}
	public int getTreatmentcount() {
		return treatmentcount;
	}
	public void setTreatmentcount(int treatmentcount) {
		this.treatmentcount = treatmentcount;
	}

}

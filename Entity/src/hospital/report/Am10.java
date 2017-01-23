package hospital.report;

import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.Organization;

public class Am10 {
	List<Customer > customer;
	Organization  organization;
	public  Am10(){
		super();
	}
	public List<Customer> getCustomer() {
		return customer;
	}
	public void setCustomer(List<Customer> list) {
		this.customer = list;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}

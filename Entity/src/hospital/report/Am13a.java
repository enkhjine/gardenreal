package hospital.report;

import java.util.List;

import hospital.entity.Customer;
import hospital.entity.ExaminationValueHdr;
import hospital.entity.Organization;
import hospital.entity.Treatment;

public class Am13a {
	List<Customer> customer;
	List<ExaminationValueHdr> evhs;
	String diagnoseName;
	List<Treatment> treatments;
	Organization  organization;
	public  Am13a(){
		super();
	}

	public List<Customer> getCustomer() {
		return customer;
	}


	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}


	public List<ExaminationValueHdr> getEvhs() {
		return evhs;
	}

	public void setEvhs(List<ExaminationValueHdr> evhs) {
		this.evhs = evhs;
	}

	public String getDiagnoseName() {
		return diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	

}

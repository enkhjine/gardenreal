package hospital.businessentity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerTreatment;

public class TreatmentRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	Customer customer;
	List<CustomerTreatment> customerTreatments;
	String employeeName;
	int vas;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public List<CustomerTreatment> getCustomerTreatments() {
		if (customerTreatments == null)
			customerTreatments = new ArrayList<CustomerTreatment>();
		return customerTreatments;
	}

	public void setCustomerTreatments(List<CustomerTreatment> customerTreatments) {
		this.customerTreatments = customerTreatments;
	}

	public TreatmentRequest() {
		super();
	}

	public TreatmentRequest(Customer customer, List<CustomerTreatment> customerTreatments, String employeeName) {
		super();
		this.customer = customer;
		this.customerTreatments = customerTreatments;
		this.employeeName = employeeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getVas() {
		return vas;
	}

	public void setVas(int vas) {
		this.vas = vas;
	}

	public void changeVas() {
		for (CustomerTreatment ct : customerTreatments) {
			ct.setVas(vas);
		}
	}
}

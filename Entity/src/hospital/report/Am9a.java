package hospital.report;

import java.math.BigDecimal;
import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerMedicine;
import hospital.entity.Employee;
import hospital.entity.Medicine;
import hospital.entity.Organization;

public class Am9a {
	Customer customer;
	List<Medicine> medicines;
	CustomerMedicine customerMedicine;
	BigDecimal inspectionPkId;
	String diagnoseName;
	Employee  emp;
	Organization  organization;
	List<CustomerDiagnose>  customerDiagnose;
	public Am9a(){
		super();
	}
	public Am9a(Customer customer, List<Medicine> medicines, BigDecimal inspectionPkId, String diagnoseName,Employee employee,Organization  org) {
		super();
		this.customer = customer;
		this.medicines = medicines;
		this.inspectionPkId = inspectionPkId;
		this.diagnoseName = diagnoseName;
		this.emp = employee;
		this.organization  = org;
	}

	public Am9a(Customer customer, CustomerMedicine customerMedicine, BigDecimal inspectionPkId){
		super();
		this.customer = customer;
		this.customerMedicine = customerMedicine;
		this.inspectionPkId = inspectionPkId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Medicine> getMedicines() {
		return medicines;
	}
	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}
	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}
	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}
	public String getDiagnoseName() {
		return diagnoseName;
	}
	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<CustomerDiagnose> getCustomerDiagnose() {
		return customerDiagnose;
	}
	public void setCustomerDiagnose(List<CustomerDiagnose> customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}
	
}

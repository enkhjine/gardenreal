package hospital.report;

import java.math.BigDecimal;
import java.util.List;

import hospital.entity.Customer;
import hospital.entity.Medicine;

public class Am9b {
	Customer customer;
	List<Medicine> medicines;
	BigDecimal inspectionPkId;
	String diagnoseName;
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
	public Am9b(Customer customer, List<Medicine> medicines, BigDecimal inspectionPkId, String diagnoseName) {
		super();
		this.customer = customer;
		this.medicines = medicines;
		this.inspectionPkId = inspectionPkId;
		this.diagnoseName = diagnoseName;
	}
	
	

}

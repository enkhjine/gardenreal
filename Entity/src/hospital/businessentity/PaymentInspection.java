package hospital.businessentity;

import hospital.entity.Degree;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.Treatment;

import java.math.BigDecimal;

public class PaymentInspection {
	private Employee employee;
	private EmployeeRequest employeeRequest;
	private EmployeeRequestHistory employeeRequestHistory;
	private Inspection inspection;
	private InspectionDtl inspectionDtl;
	private Degree degree;
	private Treatment treatment;
	private BigDecimal amount;
	
	public PaymentInspection(){
		super();
	}
	
	public PaymentInspection(Employee employee, EmployeeRequest employeeRequest, Inspection inspection, InspectionDtl inspectionDtl, Degree degree, Treatment treatment){
		this.employee = employee;
		this.employeeRequest = employeeRequest;
		this.inspection = inspection;
		this.inspectionDtl = inspectionDtl;
		this.degree = degree;
		this.treatment = treatment;
	}
	
	public PaymentInspection(Employee employee, EmployeeRequestHistory employeeRequestHistory, Inspection inspection, InspectionDtl inspectionDtl, Degree degree, Treatment treatment){
		this.employee = employee;
		this.setEmployeeRequestHistory(employeeRequestHistory);
		this.inspection = inspection;
		this.inspectionDtl = inspectionDtl;
		this.degree = degree;
		this.treatment = treatment;
	}
	
	public Degree getDegree() {
		return degree;
	}
	
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}
	
	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}
	
	public InspectionDtl getInspectionDtl() {
		return inspectionDtl;
	}
	
	public void setInspectionDtl(InspectionDtl inspectionDtl) {
		this.inspectionDtl = inspectionDtl;
	}
	
	public Treatment getTreatment() {
		return treatment;
	}
	
	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}
	
	public Inspection getInspection() {
		return inspection;
	}
	
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public EmployeeRequestHistory getEmployeeRequestHistory() {
		return employeeRequestHistory;
	}

	public void setEmployeeRequestHistory(EmployeeRequestHistory employeeRequestHistory) {
		this.employeeRequestHistory = employeeRequestHistory;
	}
}

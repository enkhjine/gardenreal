package hospital.businessentity;

import hospital.entity.Customer;
import hospital.entity.Degree;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.Treatment;

import java.math.BigDecimal;

public class CashBusinessEntity {

	private EmployeeRequest employeeRequest;
	private Inspection inspection;
	private InspectionDtl dtl;
	private Customer customer;
	private Treatment treatment;
	private Employee employee;
	private String type;
	private String status;
	private Degree degree;
	private BigDecimal discoutDtlAmount;
	private BigDecimal allAmount;
	
	public CashBusinessEntity(){
		super();
	}
	
	public CashBusinessEntity(EmployeeRequest employeeRequest, Inspection inspection, InspectionDtl dtl, Treatment treatment, Customer customer, Employee employee, Degree degree){
		this.employeeRequest = employeeRequest;
		this.inspection = inspection;
		this.dtl = dtl;
		this.treatment = treatment;
		this.customer = customer;
		this.employee = employee;
		this.degree = degree;
		this.status = Tool.UNCHANGED;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
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

	public InspectionDtl getDtl() {
		return dtl;
	}

	public void setDtl(InspectionDtl dtl) {
		this.dtl = dtl;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public Degree getDegree() {
		return degree;
	}
	
	public void setDegree(Degree degree) {
		this.degree = degree;
	}

	public BigDecimal getDiscoutDtlAmount() {
		if(this.degree == null) return BigDecimal.ZERO;
		if(this.dtl == null) return BigDecimal.ZERO;
		this.discoutDtlAmount = this.getAllAmount();
		this.discoutDtlAmount = this.discoutDtlAmount.divide(new BigDecimal(100));
		this.discoutDtlAmount = this.discoutDtlAmount.multiply(this.degree.getDiscountPercent());
		this.discoutDtlAmount = this.discoutDtlAmount.add(this.getAllAmount());
		return this.discoutDtlAmount;
	}

	public void setDiscoutDtlAmount(BigDecimal discoutDtlAmount) {
		this.discoutDtlAmount = discoutDtlAmount;
	}
	
	public BigDecimal getAllAmount() {
		this.allAmount = this.getTreatment().getPrice();
		return allAmount;
	}
	
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	
}

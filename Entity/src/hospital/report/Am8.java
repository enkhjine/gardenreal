package hospital.report;

import java.math.BigDecimal;

import hospital.entity.Aimag;
import hospital.entity.Customer;
import hospital.entity.Employee;
import hospital.entity.Soum;
import hospital.entity.ViewConstantJob;

public class Am8 {
	Customer customer;
	Employee mainEmployee;
	Employee employee;
	String diagnoseName;
	BigDecimal inspectionPkId;
	private Aimag  aimag;
	private Soum soum;
	private ViewConstantJob  jobs;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getMainEmployee() {
		return mainEmployee;
	}

	public void setMainEmployee(Employee mainEmployee) {
		this.mainEmployee = mainEmployee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getDiagnoseName() {
		return diagnoseName;
	}

	public void setDiagnoseName(String diagnoseName) {
		this.diagnoseName = diagnoseName;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}
	
	public Am8()
	{
		super();
	}

	public Am8(Customer customer, Employee mainEmployee,  String diagnoseName,
			BigDecimal inspectionPkId) {
		super();
		this.customer = customer;
		this.mainEmployee = mainEmployee;
		this.diagnoseName = diagnoseName;
		this.inspectionPkId = inspectionPkId;
	}
	public  Am8(Customer  c, Aimag  a , Soum sum, ViewConstantJob  job){
		this.customer  = c;
		this.aimag=a;
		this.soum =sum;
		this.jobs  =job;
	}

	public Aimag getAimag() {
		return aimag;
	}

	public void setAimag(Aimag aimag) {
		this.aimag = aimag;
	}

	public Soum getSoum() {
		return soum;
	}

	public void setSoum(Soum soum) {
		this.soum = soum;
	}

	public ViewConstantJob getJobs() {
		return jobs;
	}

	public void setJobs(ViewConstantJob jobs) {
		this.jobs = jobs;
	}

}

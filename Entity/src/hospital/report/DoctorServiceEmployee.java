package hospital.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DoctorServiceEmployee {

	private BigDecimal employeePkId;
	private String employeeName;
	private int allCount;
	private int count;
	private BigDecimal allAmount;
	private List<DoctorServiceTreatment> doctorServiceTreatments;
	
	public DoctorServiceEmployee(){
		super();
	}
	
	public DoctorServiceEmployee(BigDecimal employeePkId, String employeeName, BigDecimal allAmount){
		this.employeePkId = employeePkId;
		this.employeeName = employeeName;
		this.allAmount = allAmount;
		this.allCount = 0;
		this.count = 0;
	}
	
	public BigDecimal getAllAmount() {
		return allAmount;
	}
	
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	
	public int getAllCount() {
		return allCount;
	}
	
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public List<DoctorServiceTreatment> getDoctorServiceTreatments() {
		if(doctorServiceTreatments == null) doctorServiceTreatments = new ArrayList<>();
		return doctorServiceTreatments;
	}
	
	public void setDoctorServiceTreatments(
			List<DoctorServiceTreatment> doctorServiceTreatments) {
		this.doctorServiceTreatments = doctorServiceTreatments;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
}

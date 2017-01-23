package hospital.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorService {
	private BigDecimal subOrganizationPkId;
	private String subOrganizationName;
	private int allCount;
	private int count;
	private BigDecimal allAmount;
	private List<DoctorServiceEmployee> doctorServiceEmployees;
	
	public DoctorService(){
		super();
	}
	
	public DoctorService(BigDecimal subOrganizationPkId, String subOrganizationName, BigDecimal allAmount){
		this.subOrganizationPkId = subOrganizationPkId;
		this.subOrganizationName = subOrganizationName;
		this.allAmount = allAmount;
		this.count = 0;
		this.allCount = 0;
	}
	
	public String getSubOrganizationName() {
		return subOrganizationName;
	}
	
	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}
	
	public BigDecimal getAllAmount() {
		return allAmount;
	}
	
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public int getAllCount() {
		return allCount;
	}
	
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	public List<DoctorServiceEmployee> getDoctorServiceEmployees() {
		if(doctorServiceEmployees == null) doctorServiceEmployees = new ArrayList<>();
		return doctorServiceEmployees;
	}
	
	public void setDoctorServiceEmployees(
			List<DoctorServiceEmployee> doctorServiceEmployees) {
		this.doctorServiceEmployees = doctorServiceEmployees;
	}
	
	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId;
	}
	
	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}
	
}

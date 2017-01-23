package hospital.businessentity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hospital.entity.*;

public class CustomerInspectionInfo {
	private String subOrgaName;
	private String employeeName;
	private Date inspectionDate;
	private List<CustomerInspectionInfoDtl> inspectionDtl;
	
	public CustomerInspectionInfo() {
		super();
	}
	
	public String getSubOrgaName() {
		return subOrgaName;
	}
	public void setSubOrgaName(String subOrgaName) {
		this.subOrgaName = subOrgaName;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inpsectionDate) {
		this.inspectionDate = inpsectionDate;
	}
	public List<CustomerInspectionInfoDtl> getInspectionDtl() {
		return inspectionDtl;
	}
	public void setInspectionDtl(List<CustomerInspectionInfoDtl> inspectionDtl) {
		this.inspectionDtl = inspectionDtl;
	}
	public String getDateString() {
		if (inspectionDate != null){
			SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
			return (ft.format(inspectionDate));
		}
		else return "";
	}
}
package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Inspection")
public class Inspection implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;

	@Column(name = "Description")
	private String description;

	/*
	 * 0 - Өвчний учир анхан
	 * 1 - Өвчний учир давтан
	 * 2 - Урьдчилан сэргийлэх 
	 * 3 - Гэрийн хяналт
	 * 4 - Диспенсерийн хяналт 
	*/
	@Column(name = "EmployeeInspectionType")
	private int employeeInspectionType;
	
	@Column(name = "InspectionType")
	private int inspectionType;
	
	
	@Column(name = "RequestPkId")
	private BigDecimal requestPkId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InspectionStartDate")
	private Date inspectionStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "InspectionDate")
	private Date inspectionDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Transient
	private String employeeName;
	
	@Transient
	private String subOrgaName;
	
	@Transient
	private BigDecimal subOrgaPkId;
	
	@Transient
	private String other;
	
	@Transient
	private String status;
	@Transient
	private  String inspectionStatus;
	
	@Transient
	private List<InspectionDtl> dtls;

	public Inspection() {
		super();
		employeeInspectionType = -1;
	}
	
	public Inspection(BigDecimal requestPkId, String employeeName, Date inspectionDate) {
		this.requestPkId = requestPkId;
		this.employeeName = employeeName;
		this.inspectionDate = inspectionDate;
	}
	
	public Inspection(BigDecimal pkId, String subOrgaName, String employeeName, Date inspectionDate) {
		this.pkId = pkId;
		this.subOrgaName = subOrgaName;
		this.employeeName = employeeName;
		this.inspectionDate = inspectionDate;
	}
	
	public Inspection(Inspection inspection, String employeeName, String subOrgaName, BigDecimal subOrgaPkId) {
		super();
		this.pkId = inspection.getPkId();
		this.employeePkId = inspection.getEmployeePkId();
		this.customerPkId = inspection.getCustomerPkId();
		this.description = inspection.getDescription();
		this.requestPkId = inspection.getRequestPkId();
		this.inspectionType = inspection.getInspectionType();
		this.employeeInspectionType = inspection.getEmployeeInspectionType();
		this.inspectionDate = inspection.getInspectionDateDate();
		this.inspectionStartDate = inspection.getInspectionStartDateDate();
		this.createdDate = inspection.getCreatedDate();
		this.createdBy = inspection.getCreatedBy();
		this.updatedDate = inspection.getUpdatedDate();
		this.updatedBy = inspection.getUpdatedBy();
		
		this.employeeName = employeeName;
		this.subOrgaName = subOrgaName;
		this.subOrgaPkId = subOrgaPkId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getCustomperPkId() {
		return customerPkId;
	}

	public void setCustomperPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getInspectionDateDate() {
		return inspectionDate;
	}
	
	public String getInspectionDate() {
		if (inspectionDate == null)
			return "Үзлэгт ороогүй";
		{
			SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm ");
			return (ft.format(inspectionDate));
		}
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSubOrgaName() {
		return subOrgaName;
	}

	public void setSubOrgaName(String subOrgaName) {
		this.subOrgaName = subOrgaName;
	}
	
	public List<InspectionDtl> getDtls() {
		if(dtls == null) dtls = new ArrayList<>();
		return dtls;
	}
	
	public void setDtls(List<InspectionDtl> dtls) {
		this.dtls = dtls;
	}

	public BigDecimal getSubOrgaPkId() {
		return subOrgaPkId;
	}

	public void setSubOrgaPkId(BigDecimal subOrgaPkId) {
		this.subOrgaPkId = subOrgaPkId;
	}

	public int getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(int inspectionType) {
		this.inspectionType = inspectionType;
	}

	public String getInspectionStatus() {
		if(getInspectionType()==0)
			inspectionStatus = "";
		  else 
			if (getInspectionType() == 1)
				inspectionStatus = "анхан";
			else if (getInspectionType() == 2)
				inspectionStatus = "давтан";
			else if (getInspectionType() == 3)
				inspectionStatus = "сэргийлэх";
			else if (getInspectionType() == 4)
				inspectionStatus = "гэр хяналт";
			else if (getInspectionType() == 5)
				inspectionStatus = "Дис хяналт";
		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getInspectionStartDate() {
		if (inspectionStartDate == null)
			return "Үзлэгт ороогүй";
		else {
			SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd hh:mm ");
			return (ft.format(inspectionStartDate));
		}
	}
	
	public Date getInspectionStartDateDate() {
		return inspectionStartDate;
	}

	public void setInspectionStartDate(Date inspectionStartDate) {
		this.inspectionStartDate = inspectionStartDate;
	}

	public int getEmployeeInspectionType() {
		return employeeInspectionType;
	}

	public void setEmployeeInspectionType(int employeeInspectionType) {
		this.employeeInspectionType = employeeInspectionType;
	}
	
}
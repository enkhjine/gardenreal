package hospital.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CustomerSurgery")
public class CustomerSurgery {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "InspectionDtlPkId")
	private BigDecimal inspectionDtlPkId;
	
	@Column(name = "SurgeryPkId")
	private BigDecimal surgeryPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;
	
	@Column(name = "SurgeryDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date surgeryDate;
	
	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Transient
	private String surgeryName;

	@Transient
	private String employeeName;
	
	@Transient
	private String status;
	
	public CustomerSurgery() {
		super();
	}
	
	public CustomerSurgery(CustomerSurgery surgery, String surgeryName, String employeeName) {
		super();
		this.pkId = surgery.getPkId();
		this.inspectionPkId = surgery.getInspectionPkId();
		this.surgeryPkId = surgery.getSurgeryPkId();
		this.employeePkId = surgery.getEmployeePkId();
		this.customerPkId = surgery.getCustomerPkId();
		this.surgeryDate = surgery.getSurgeryDate();
		this.createdDate = surgery.getCreatedDate();
		this.createdBy = surgery.getCreatedBy();
		this.updatedDate = surgery.getUpdatedDate();
		this.updatedBy = surgery.getUpdatedBy();
		this.surgeryName = surgeryName;
		this.employeeName = employeeName;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
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

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public String getDateString (Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public BigDecimal getSurgeryPkId() {
		return surgeryPkId;
	}

	public void setSurgeryPkId(BigDecimal surgeryPkId) {
		this.surgeryPkId = surgeryPkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public BigDecimal getInspectionDtlPkId() {
		return inspectionDtlPkId;
	}

	public void setInspectionDtlPkId(BigDecimal inspectionDtlPkId) {
		this.inspectionDtlPkId = inspectionDtlPkId;
	}
}
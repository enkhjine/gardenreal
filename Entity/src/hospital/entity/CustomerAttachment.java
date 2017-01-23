package hospital.entity;

import java.io.File;
import java.io.FileInputStream;
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
@Table(name = "CustomerAttachment")
public class CustomerAttachment {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;
	
	@Column(name = "AttachmentType")
	private String attachmentType;
	
	@Column(name = "FileName")
	private String fileName;
	
	@Column(name = "Description")
	private String description;
	
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
	private String status;
	
	public CustomerAttachment() {
		super();
	}
	
	public CustomerAttachment(CustomerAttachment attachment) {
		super();
		this.pkId = attachment.getPkId();
		this.inspectionPkId = attachment.getInspectionPkId();
		this.employeePkId = attachment.getEmployeePkId();
		this.customerPkId = attachment.getCustomerPkId();
		this.createdDate = attachment.getCreatedDate();
		this.createdBy = attachment.getCreatedBy();
		this.updatedDate = attachment.getUpdatedDate();
		this.updatedBy = attachment.getUpdatedBy();
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
	
	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAttachmentType() {
		return attachmentType;
	}

	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
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
@Table(name = "CustomerXray")
public class CustomerXray {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "InspectionDtlPkId")
	private BigDecimal inspectionDtlPkId;
	
	@Column(name = "XrayPkId")
	private BigDecimal xrayPkId;
	
	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "SenderEmployeePkId")
	private BigDecimal senderEmployeePkId;
	
	@Column(name = "XrayDescription")
	private String xrayDescription; 
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
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
	private String xrayName;
	
	@Transient
	private String employeeName;
	
	@Transient
	private String senderEmployeeName;
	
	@Transient
	private String status;
	
	public CustomerXray() {
		super();
	}
	
	public CustomerXray(CustomerXray customerXray, Xray xray, String employeeName, String senderEmployeeName) {
		super();
		this.pkId = customerXray.getPkId();
		this.inspectionPkId = customerXray.getInspectionPkId();
		this.inspectionDtlPkId = customerXray.getInspectionDtlPkId();
		this.xrayPkId = customerXray.getXrayPkId();
		this.paymentPkId = customerXray.getPaymentPkId();
		this.employeePkId = customerXray.getEmployeePkId();
		this.senderEmployeePkId = customerXray.getSenderEmployeePkId();
		this.xrayDescription = customerXray.getXrayDescription(); 
		this.image = customerXray.getImage();
		this.date = customerXray.getDate();
		this.createdDate = customerXray.getCreatedDate();
		this.createdBy = customerXray.getCreatedBy();
		this.updatedDate = customerXray.getUpdatedDate();
		this.updatedBy = customerXray.getUpdatedBy();
		
		this.xrayName = xray.getName();
		this.employeeName = employeeName;
		this.senderEmployeeName = senderEmployeeName;
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

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public BigDecimal getXrayPkId() {
		return xrayPkId;
	}

	public void setXrayPkId(BigDecimal xrayPkId) {
		this.xrayPkId = xrayPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getXrayDescription() {
		return xrayDescription;
	}

	public void setXrayDescription(String xrayDescription) {
		this.xrayDescription = xrayDescription;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getSenderEmployeePkId() {
		return senderEmployeePkId;
	}

	public void setSenderEmployeePkId(BigDecimal senderEmployeePkId) {
		this.senderEmployeePkId = senderEmployeePkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getInspectionDtlPkId() {
		return inspectionDtlPkId;
	}

	public void setInspectionDtlPkId(BigDecimal inspectionDtlPkId) {
		this.inspectionDtlPkId = inspectionDtlPkId;
	}

	public String getXrayName() {
		return xrayName;
	}

	public void setXrayName(String xrayName) {
		this.xrayName = xrayName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getSenderEmployeeName() {
		return senderEmployeeName;
	}

	public void setSenderEmployeeName(String senderEmployeeName) {
		this.senderEmployeeName = senderEmployeeName;
	}
	public String getStringDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return (ft.format(date));
	}
}
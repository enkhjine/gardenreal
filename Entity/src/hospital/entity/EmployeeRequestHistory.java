package hospital.entity;

import java.io.Serializable;
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
@Table(name = "EmployeeRequestHistory")
public class EmployeeRequestHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;

	@Column(name = "customerPkId")
	private BigDecimal customerPkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "Description")
	private String description;

	@Column(name = "Mood")
	private int mood;
	
	@Column(name = "Prevention")
	private int prevention;
	
	@Column(name = "PreventionOrgName")
	private String preventionOrgName;

	@Column(name = "ReInspection")
	private int reInspection;

	@Column(name = "IsInsurance")
	private int isInsurance;

	@Column(name = "HasPayment")
	private int hasPayment;

	@Column(name = "IsExpress")
	private int isExpress;

	@Column(name = "SaveMood")
	private int saveMood;

	@Column(name = "Guest")
	private int guest;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ArrivedDate")
	private Date arrivedDate;

	@Transient
	String dateString;

	public EmployeeRequestHistory() {
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getReInspection() {
		return reInspection;
	}

	public void setReInspection(int reInspection) {
		this.reInspection = reInspection;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getDateString() {
		dateString = new SimpleDateFormat("yyyy-MM-dd").format(getBeginDate());
		return dateString;
	}

	public int getGuest() {
		return guest;
	}

	public void setGuest(int guest) {
		this.guest = guest;
	}

	public int getHasPayment() {
		return hasPayment;
	}

	public void setHasPayment(int hasPayment) {
		this.hasPayment = hasPayment;
	}

	public int getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(int isExpress) {
		this.isExpress = isExpress;
	}

	public int getIsInsurance() {
		return isInsurance;
	}

	public void setIsInsurance(int isInsurance) {
		this.isInsurance = isInsurance;
	}

	public int getSaveMood() {
		return saveMood;
	}

	public void setSaveMood(int saveMood) {
		this.saveMood = saveMood;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getArrivedDate() {
		return arrivedDate;
	}

	public void setArrivedDate(Date arrivedDate) {
		this.arrivedDate = arrivedDate;
	}
	
	public int getPrevention() {
		return prevention;
	}
	
	public void setPrevention(int prevention) {
		this.prevention = prevention;
	}
	
	public String getPreventionOrgName() {
		return preventionOrgName;
	}
	
	public void setPreventionOrgName(String preventionOrgName) {
		this.preventionOrgName = preventionOrgName;
	}

}

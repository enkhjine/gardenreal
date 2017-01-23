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
@Table(name = "CustomerTreatmentDtl")
public class CustomerTreatmentDtl {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "CustomerTreatmentPkId")
	private BigDecimal customerTreatmentPkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "TreatmentDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date treatmentDate;

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

	@Column(name = "Vas")
	private int vas;

	@Transient
	private String status;

	@Transient
	String employeeName;

	public CustomerTreatmentDtl() {
		super();
	}

	public CustomerTreatmentDtl(CustomerTreatmentDtl ctd, String name) {
		super();
		this.pkId = ctd.getPkId();
		this.customerTreatmentPkId = ctd.getCustomerTreatmentPkId();
		this.employeePkId = ctd.getEmployeePkId();
		this.treatmentDate = ctd.getTreatmentDate();
		this.createdBy = ctd.getCreatedBy();
		this.createdDate = ctd.getCreatedDate();
		this.updatedBy = ctd.getUpdatedBy();
		this.updatedDate = ctd.getUpdatedDate();
		this.vas = ctd.getVas();
		this.employeeName = name;

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

	public BigDecimal getCustomerTreatmentPkId() {
		return customerTreatmentPkId;
	}

	public void setCustomerTreatmentPkId(BigDecimal customerTreatmentPkId) {
		this.customerTreatmentPkId = customerTreatmentPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public Date getTreatmentDate() {
		return treatmentDate;
	}

	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate = treatmentDate;
	}

	public int getVas() {
		return vas;
	}

	public void setVas(int vas) {
		this.vas = vas;
	}

	public String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}

	public String getDstring(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public String getMstring(Date date) {
		if (date != null)
			return new SimpleDateFormat("MM/dd").format(date);
		else
			return "";
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
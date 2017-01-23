package hospital.entity;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.PaymentInspection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CustomerMatter")
public class CustomerMatter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Matter")
	private String matter;
	
	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "MatterDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date matterDate;
	
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
	private String doctorName;
	
	@Transient
	private String status;
	
	public CustomerMatter() {
		super();
	}
	
	public CustomerMatter(CustomerMatter problem, String firstName, String lastName) {
		super();
		this.pkId = problem.getPkId();
		this.matter = problem.getMatter();
		this.customerPkId = problem.getCustomerPkId();
		this.employeePkId = problem.getEmployeePkId();
		this.matterDate = problem.getMatterDate();
		this.createdBy = problem.getCreatedBy();
		this.createdDate = problem.getCreatedDate();
		this.updatedBy = problem.getUpdatedBy();
		this.updatedDate = problem.getUpdatedDate();
		if(lastName.length() > 0)
			this.doctorName = lastName.substring(0, 1);
		this.doctorName = this.doctorName + "." + firstName; 
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getStringDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return (ft.format(createdDate));
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getMatter() {
		return matter;
	}

	public void setMatter(String matter) {
		this.matter = matter;
	}

	public Date getMatterDate() {
		return matterDate;
	}

	public void setMatterDate(Date matterDate) {
		this.matterDate = matterDate;
	}
}
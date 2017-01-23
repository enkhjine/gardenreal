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
@Table(name = "CustomerPlan")
public class CustomerPlan {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "PlanType")
	private String planType;
	
	@Column(name = "[Plan]")
	private String plan;
	
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
	
	public CustomerPlan() {
		super();
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

	public String getDateString (Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}
}
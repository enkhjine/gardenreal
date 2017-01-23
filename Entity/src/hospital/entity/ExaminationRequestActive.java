package hospital.entity;

import hospital.businessentity.ExaRequest;

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
@Table(name = "ExaminationRequestActive")
public class ExaminationRequestActive implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;

	@Column(name = "ExaminationPkId")
	private BigDecimal examinationPkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "RequestDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date requestDate;

	/**
	 * 0-> хүсэлт 1-> хийгдэх 2-> түр хадгалсан 3-> баталгаажсан
	 **/

	@Column(name = "Mood")
	private int mood;

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
	private String examinationName;

	@Transient
	private boolean complete;

	@Transient
	private Customer customer;
	
	public ExaminationRequestActive() {
		super();
	}

	public ExaminationRequestActive(BigDecimal pkId, BigDecimal customerPkId,
			BigDecimal examinationPkId, Date requestDate,
			String examinationName, int mood, Customer customer) {
		super();
		this.pkId = pkId;
		this.customerPkId = customerPkId;
		this.examinationPkId = examinationPkId;
		this.requestDate = requestDate;
		this.examinationName = examinationName;
		this.mood = mood;
		this.customer = customer;

	}

	public ExaminationRequestActive(BigDecimal pkId, BigDecimal customerPkId,
			BigDecimal examinationPkId, Date requestDate, int mood,
			Date createdDate, BigDecimal createdBy, Date updatedDate,
			BigDecimal updatedBy, String examinationName, Customer customer) {
		super();
		this.pkId = pkId;
		this.customerPkId = customerPkId;
		this.examinationPkId = examinationPkId;
		this.requestDate = requestDate;
		this.mood = mood;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.examinationName = examinationName;
		this.customer = customer;
	}

	public ExaminationRequestActive(ExaRequest er) {
		super();
		this.pkId = er.getPkId();
		this.customerPkId = er.getCustomerPkId();
		this.examinationPkId = er.getExaminationPkId();
		this.requestDate = er.getRequestDate();
		this.mood = er.getMood();
		this.createdDate = er.getCreatedDate();
		this.createdBy = er.getCreatedBy();
		this.updatedDate = er.getUpdatedDate();
		this.updatedBy = er.getUpdatedBy();
		this.employeePkId = er.getEmployeePkId();
		

	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		if (complete)
			setMood(0);
		else
			setMood(1);
		this.complete = complete;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd  hh:mm").format(date);
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

}
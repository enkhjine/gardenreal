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
@Table(name = "ExaminationRequest")
public class ExaminationRequest implements Serializable {
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
	
	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;
	
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

	public ExaminationRequest() {
		super();
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		if (complete)
			setMood(1);
		else
			setMood(0);
		this.complete = complete;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public ExaminationRequest(BigDecimal pkId, BigDecimal customerPkId,
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

	public ExaminationRequest(BigDecimal pkId, BigDecimal customerPkId,
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
	
	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}
	
	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}
}
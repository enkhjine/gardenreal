package hospital.businessentity;

import hospital.entity.Customer;
import hospital.entity.ExaminationRequestActive;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.ExaminationRequestTempSave;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExaRequest {
	private static final long serialVersionUID = 1L;

	private BigDecimal pkId;
	private BigDecimal customerPkId;
	private BigDecimal examinationPkId;
	private Date requestDate;
	/**
	 * 0-> хүсэлт 1-> хийгдэх 2-> түр хадгалсан 3-> баталгаажсан
	 **/
	private int mood;
	private String examinationName;
	private boolean complete;
	private Customer customer;
	private Date createdDate;
	private Date updatedDate;
	private BigDecimal updatedBy;
	private BigDecimal createdBy;
	private BigDecimal employeePkId;

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

	public ExaRequest() {
		super();
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

	public ExaRequest(BigDecimal pkId, BigDecimal customerPkId,
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

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}
	public ExaRequest(ExaminationRequestActive er) {
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
	public ExaRequest(ExaminationRequestTempSave er) {
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
	public ExaRequest(ExaminationRequestCompleted er) {
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
		this.customer = er.getCustomer();
		this.examinationName = er.getExaminationName();
		this.employeePkId = er.getEmployeePkId();

	}
	

	public ExaRequest(BigDecimal pkId, BigDecimal customerPkId,
			BigDecimal examinationPkId, Date requestDate, int mood,
			String examinationName, Customer customer, Date createdDate,
			Date updatedDate, BigDecimal createdBy, BigDecimal updatedBy) {
		super();
		this.pkId = pkId;
		this.customerPkId = customerPkId;
		this.examinationPkId = examinationPkId;
		this.requestDate = requestDate;
		this.mood = mood;
		this.examinationName = examinationName;
		this.customer = customer;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;

	}
	

	public ExaRequest(BigDecimal pkId, BigDecimal customerPkId,
			BigDecimal examinationPkId, Date requestDate, int mood,
			String examinationName, Customer customer, Date createdDate,
			Date updatedDate, BigDecimal createdBy, BigDecimal updatedBy, BigDecimal employeePkId) {
		super();
		this.pkId = pkId;
		this.customerPkId = customerPkId;
		this.examinationPkId = examinationPkId;
		this.requestDate = requestDate;
		this.mood = mood;
		this.examinationName = examinationName;
		this.customer = customer;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.employeePkId = employeePkId;

	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

}

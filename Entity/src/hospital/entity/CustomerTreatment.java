package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@Table(name = "CustomerTreatment")
public class CustomerTreatment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;

	@Column(name = "InspectionDtlPkId")
	private BigDecimal inspectionDtlPkId;

	@Column(name = "TreatmentPkId")
	private BigDecimal treatmentPkId;

	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;

	@Column(name = "Qty")
	private String qty;

	@Column(name = "Times")
	private int times;

	@Column(name = "DayLength")
	private int dayLength;

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

	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;

	@Column(name = "DoneCount")
	private int doneCount;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "Description")
	private String description;

	@Transient
	private String treatmentName;

	@Transient
	private String employeeName;

	@Transient
	private String status;

	@Transient
	private boolean done;

	@Transient
	private int vas;

	@Transient
	private List<Diagnose> diagnoseList;

	@Transient
	private String eName;

	public CustomerTreatment() {
		super();
	}

	public CustomerTreatment(CustomerTreatment customerTreatment, Treatment treatment, String employeeName, String eName) {
		super();
		this.pkId = customerTreatment.getPkId();
		this.inspectionPkId = customerTreatment.getInspectionPkId();
		this.inspectionDtlPkId = customerTreatment.getInspectionDtlPkId();
		this.treatmentPkId = customerTreatment.getTreatmentPkId();
		this.paymentPkId = customerTreatment.getPaymentPkId();
		this.qty = customerTreatment.getQty();
		this.times = customerTreatment.getTimes();
		this.dayLength = customerTreatment.getDayLength();
		this.createdDate = customerTreatment.getCreatedDate();
		this.createdBy = customerTreatment.getCreatedBy();
		this.updatedDate = customerTreatment.getUpdatedDate();
		this.updatedBy = customerTreatment.getUpdatedBy();
		this.doneCount = customerTreatment.getDoneCount();
		this.customerPkId = customerTreatment.getCustomerPkId();
		this.employeePkId = customerTreatment.getEmployeePkId();
		this.description = customerTreatment.getDescription();
		this.treatmentName = treatment.getName();
		this.employeeName = employeeName;
		this.eName = eName;
	}

	public CustomerTreatment(CustomerTreatment customerTreatment, Treatment treatment) {
		super();
		this.pkId = customerTreatment.getPkId();
		this.inspectionPkId = customerTreatment.getInspectionPkId();
		this.inspectionDtlPkId = customerTreatment.getInspectionDtlPkId();
		this.treatmentPkId = customerTreatment.getTreatmentPkId();
		this.paymentPkId = customerTreatment.getPaymentPkId();
		this.qty = customerTreatment.getQty();
		this.times = customerTreatment.getTimes();
		this.dayLength = customerTreatment.getDayLength();
		this.createdDate = customerTreatment.getCreatedDate();
		this.createdBy = customerTreatment.getCreatedBy();
		this.updatedDate = customerTreatment.getUpdatedDate();
		this.updatedBy = customerTreatment.getUpdatedBy();
		this.doneCount = customerTreatment.getDoneCount();
		this.customerPkId = customerTreatment.getCustomerPkId();
		this.employeePkId = customerTreatment.getEmployeePkId();
		this.description = customerTreatment.getDescription();
		this.treatmentName = treatment.getName();
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

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentPkId) {
		this.treatmentPkId = treatmentPkId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getDayLength() {
		return dayLength;
	}

	public void setDayLength(int dayLength) {
		this.dayLength = dayLength;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public BigDecimal getInspectionDtlPkId() {
		return inspectionDtlPkId;
	}

	public void setInspectionDtlPkId(BigDecimal inspectionDtlPkId) {
		this.inspectionDtlPkId = inspectionDtlPkId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
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

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public void changer() {
		if (done)
			this.setDoneCount(this.getDoneCount() + 1);
		else
			this.setDoneCount(this.getDoneCount() - 1);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getDateSimple() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getCreatedDate());
	}

	public List<Diagnose> getDiagnoseList() {
		return diagnoseList;
	}

	public void setDiagnoseList(List<Diagnose> diagnoseList) {
		this.diagnoseList = diagnoseList;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}
	

}
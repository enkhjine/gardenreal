package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EmployeeRequest")
public class EmployeeRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;

	@Column(name = "OrganizationPkId", length = 18, nullable = false)
	private BigDecimal organizationPkId;

	@Column(name = "CustomerPkId", length = 18, nullable = false)
	private BigDecimal customerPkId;

	@Column(name = "EmployeePkId", length = 18, nullable = false)
	private BigDecimal employeePkId;

	/**
	 * 0 - Tulbur 1 - Daatgal
	 */
	@Column(name = "IsInsurance")
	private int insurance;

	@Column(name = "Mood")
	private int mood;
	
	/**
	 * 0 - Урьдчилан сэргийлэх үзлэг биш
	 * 1 - Урьдчилан сэргийлэх үзлэг мөн
	 * */
	@Column(name = "Prevention")
	private int prevention;
	
	@Column(name = "PreventionOrgName")
	private String preventionOrgName;

	/**
	 * 0 - aliig ni ch bogloogui 1 - emr hadgalagdsan 2 - ocs hadgalagdsan 3 -
	 * emr ocs hadgalagdsan
	 */
	@Column(name = "SaveMood")
	private int saveMood;

	/**
	 * 0 - baih ym bol anhan uzleg 1 - baih ym bol davtan uzleg
	 */
	@Column(name = "ReInspection")
	private int reInspection;

	/**
	 * 1 - Zahialga ogohdoo mongoo tulsun
	 */
	@Column(name = "HasPayment")
	private int hasPayment;
	/**
	 * урьдчилсан захиалга дээр 1 бол ирсэн 0 бол ирээгүй
	 */
	@Column(name = "Guest")
	private int guest;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EndDate")
	private Date endDate;

	@Column(name = "Description")
	private String description;

	/*
	 * 0 1 - Яаралтай
	 */
	@Column(name = "IsExpress")
	private int isExpress;

	@Column(name = "CreatedBy", length = 18, nullable = false)
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate", length = 18, nullable = false)
	private Date createdDate;

	@Column(name = "UpdatedBy", length = 18, nullable = false)
	private BigDecimal updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate", length = 18, nullable = false)
	private Date updatedDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ArrivedDate", length = 18, nullable = false)
	private Date arrivedDate;

	@Transient
	private int beginTime;

	@Transient
	private int beginMinute;

	@Transient
	private int endTime;

	@Transient
	private int endMinute;

	@Transient
	private String status;

	@Transient
	private String customerLastname;

	@Transient
	private String customerFirstname;

	@Transient
	private Employee employee;

	@Transient
	private Inspection inspection;

	@Transient
	private CustomerDiagnose customerDiagnose;

	@Transient
	private List<InspectionDtl> inspectionDtls;

	@Transient
	private List<Treatment> treatments;

	@Transient
	private BigDecimal sumAmount;

	@Transient
	private String buttonStatus;

	@Transient
	private String inspectionStatus;

	@Transient
	private String dateString;

	@Transient
	private boolean firstInspection;

	@Transient
	private boolean resInspection;

	@Transient
	private boolean payment;

	@Transient
	private boolean hasInsurance;

	@Transient
	private boolean paidRequestAmount;

	@Transient
	private boolean express;

	@Transient
	private String bgColor;

	@Transient
	private String ceName;

	@Transient
	private String guestStringStatus;

	@Transient
	private String requestDayStr;
	
	@Transient
	private String requestDateStr;

	@Transient
	private boolean freeInspection;
	
	@Transient
	private String paymentTypeString;
	
	@Transient
	private boolean hasPrevention;

	public EmployeeRequest() {
		super();
	}

	public EmployeeRequest(BigDecimal pkId, String id, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, Date date, String description, int mood, int beginTime, int beginMinute,
			int endTime, int endMinute, BigDecimal createdBy, Date createdDate, BigDecimal updatedBy, Date updatedDate,
			Employee employee, Inspection inspection) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.mood = mood;
		this.beginTime = beginTime;
		this.beginMinute = beginMinute;
		this.endTime = endTime;
		this.endMinute = endMinute;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.employee = employee;
		this.inspection = inspection;
	}

	public EmployeeRequest(BigDecimal pkId, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, String description, Date beginDate, Date endDate, BigDecimal createdBy,
			Date createdDate, BigDecimal updatedBy, Date updatedDate, String customerLastname, String customerFirstname,
			int mood) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		// this.status = status;
		this.customerLastname = customerLastname;
		this.customerFirstname = customerFirstname;
		this.mood = mood;
	}

	public EmployeeRequest(BigDecimal pkId, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, String description, Date beginDate, Date endDate, BigDecimal createdBy,
			Date createdDate, BigDecimal updatedBy, Date updatedDate, String customerLastname, String customerFirstname,
			int mood, int hasPayment) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		// this.status = status;
		this.customerLastname = customerLastname;
		this.customerFirstname = customerFirstname;
		this.mood = mood;
		this.hasPayment = hasPayment;
	}

	public EmployeeRequest(BigDecimal pkId, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, String description, Date beginDate, Date endDate, BigDecimal createdBy,
			Date createdDate, BigDecimal updatedBy, Date updatedDate, String customerLastname, String customerFirstname,
			int mood, int isExpress, int isInsurance) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		// this.status = status;
		this.customerLastname = customerLastname;
		this.customerFirstname = customerFirstname;
		this.mood = mood;
		this.hasPayment = 1;
		this.isExpress = isExpress;
		this.insurance = isInsurance;
	}

	public EmployeeRequest(BigDecimal pkId, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, String description, Date beginDate, Date endDate, BigDecimal createdBy,
			Date createdDate, BigDecimal updatedBy, Date updatedDate, String customerLastname, String customerFirstname,
			int mood, int isExpress, int isInsurance, int hasPayment) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		// this.status = status;
		this.customerLastname = customerLastname;
		this.customerFirstname = customerFirstname;
		this.mood = mood;
		this.hasPayment = hasPayment;
		this.isExpress = isExpress;
		this.insurance = isInsurance;
	}

	public EmployeeRequest(BigDecimal pkId, BigDecimal organizationPkId, BigDecimal customerPkId,
			BigDecimal employeePkId, String description, int hasPayment, Date beginDate,
			Date endDate, BigDecimal createdBy, Date createdDate, BigDecimal updatedBy, Date updatedDate,
			Employee employee) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.customerPkId = customerPkId;
		this.employeePkId = employeePkId;
		this.description = description;
		this.hasPayment = hasPayment;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.employee = employee;
		if(hasPayment == 0) {
			paymentTypeString = "Төлөөгүй";
		}
		if(hasPayment == 2) {
			paymentTypeString = "Төлбөргүй";
		}
		if(hasPayment == 1) {
			paymentTypeString = "Төлсөн";
		}
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
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

	public int getBeginTime() {
		beginTime = getBeginDate().getHours();
		return beginTime;
	}

	public void setBeginTime(int beginTime) {
		getBeginDate().setHours(beginTime);
		this.beginTime = beginTime;
	}

	public int getBeginMinute() {
		beginMinute = getBeginDate().getMinutes();
		return beginMinute;
	}

	public void setBeginMinute(int beginMinute) {
		getBeginDate().setMinutes(beginMinute);
		this.beginMinute = beginMinute;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getEndMinute() {
		endMinute = getEndDate().getMinutes();
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		getEndDate().setMinutes(endMinute);
		this.endMinute = endMinute;
	}

	public int getEndTime() {
		endTime = getEndDate().getHours();
		return endTime;
	}

	public void setEndTime(int endTime) {
		getEndDate().setHours(endTime);
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCustomerFirstname() {
		return customerFirstname;
	}

	public void setCustomerFirstname(String customerFirstname) {
		this.customerFirstname = customerFirstname;
	}

	public String getCustomerLastname() {
		return customerLastname;
	}

	public void setCustomerLastname(String customerLastname) {
		this.customerLastname = customerLastname;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public List<InspectionDtl> getInspectionDtls() {
		return inspectionDtls;
	}

	public void setInspectionDtls(List<InspectionDtl> inspectionDtls) {
		this.inspectionDtls = inspectionDtls;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getButtonStatus() {
		if (getMood() == 0 || getMood() == 1 || getMood() == 3 || getMood() == 5 || getMood() == 6)
			setButtonStatus("false");
		else if (getMood() == 2 || getMood() == 4)
			setButtonStatus("true");
		return buttonStatus;
	}

	public void setButtonStatus(String buttonStatus) {
		this.buttonStatus = buttonStatus;
	}

	public String getInspectionStatus() {
		if (getMood() == 0)
			inspectionStatus = "";
		else if (getMood() == 1){
			if(getSaveMood() == 0)
				inspectionStatus = "Орох";
			else if (getSaveMood() == 1 || getSaveMood() == 2)
				inspectionStatus = "Түр хадгалсан";
			
		} else if (getMood() == 2)
			inspectionStatus = "Орсон";
		else if (getMood() == 3)
			inspectionStatus = "Дахин үзлэг";
		else if (getMood() == 5)
			inspectionStatus = "Түр хадгалсан";
		else if (getMood() == 6)
			inspectionStatus = "Түр хадгалсан";
		else if (getMood() == 4)
			inspectionStatus = "Үзлэгт орсон";

		return inspectionStatus;
	}

	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}

	public String getDateString() {
		dateString = new SimpleDateFormat("yyyy-MM-dd").format(getBeginDate());
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Date getBeginDate() {
		if (beginDate == null)
			beginDate = new Date();
		beginDate.setSeconds(0);
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		if (endDate == null)
			endDate = new Date();
		endDate.setSeconds(0);
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public int getReInspection() {
		return reInspection;
	}

	public void setReInspection(int reInspection) {
		this.reInspection = reInspection;
	}

	public boolean isFirstInspection() {
		firstInspection = getReInspection() == 0;
		return firstInspection;
	}

	public void setFirstInspection(boolean firstInspection) {
		setReInspection(1);
		if (firstInspection)
			setReInspection(0);
		this.firstInspection = firstInspection;
	}

	public boolean isResInspection() {
		resInspection = getReInspection() == 1;
		return resInspection;
	}

	public void setResInspection(boolean resInspection) {
		setReInspection(0);
		if (resInspection)
			setReInspection(1);
		this.resInspection = resInspection;
	}

	public boolean isHasInsurance() {
		hasInsurance = getInsurance() == 1;
		return hasInsurance;
	}

	public void setHasInsurance(boolean hasInsurance) {
		setInsurance(0);
		setHasPayment(0);
		if (hasInsurance) {
			setInsurance(1);
		}
		this.hasInsurance = hasInsurance;
	}

	public boolean isPayment() {
		payment = false;
		if (getHasPayment() == 1) {
			payment = true;
		}
		return payment;
	}

	public void setPayment(boolean payment) {
		setInsurance(1);
		setHasPayment(0);
		if (payment) {
			setInsurance(0);
		}
		this.payment = payment;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public int getHasPayment() {
		return hasPayment;
	}

	public void setHasPayment(int hasPayment) {
		this.hasPayment = hasPayment;
	}

	public boolean isPaidRequestAmount() {
		paidRequestAmount = getHasPayment() == 1;
		return paidRequestAmount;
	}

	public void setPaidRequestAmount(boolean paidRequestAmount) {
		setHasPayment(0);
		if (paidRequestAmount)
			setHasPayment(2);
		this.paidRequestAmount = paidRequestAmount;
	}

	public int getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(int isExpress) {
		this.isExpress = isExpress;
	}

	public boolean isExpress() {
		express = getIsExpress() == 1;
		return express;
	}

	public void setExpress(boolean express) {
		setIsExpress(0);
		if (express)
			setIsExpress(1);
		this.express = express;
	}

	public String getBgColor() {
		bgColor = "green";
		if (getBeginMinute() == getEndMinute() && getBeginTime() == getEndTime())
			bgColor = "rgb(255, 165, 0)";
		if (getIsExpress() == 1)
			bgColor = "red";
		if (getPrevention() == 1) bgColor = "#33afde";
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public CustomerDiagnose getCustomerDiagnose() {
		return customerDiagnose;
	}

	public void setCustomerDiagnose(CustomerDiagnose customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}

	public String getCeName() {
		return ceName;
	}

	public void setCeName(String ceName) {
		this.ceName = ceName;
	}

	public int getSaveMood() {
		return saveMood;
	}

	public void setSaveMood(int saveMood) {
		this.saveMood = saveMood;
	}

	public int getGuest() {
		return guest;
	}

	public void setGuest(int guest) {
		this.guest = guest;
	}

	public String getGuestStringStatus() {
		if (getGuest() == 1)
			guestStringStatus = "ирээгүй";
		else if (getGuest() == 2)
			guestStringStatus = "ирсэн";
		return guestStringStatus;
	}

	public void setGuestStringStatus(String guestStringStatus) {
		this.guestStringStatus = guestStringStatus;
	}

	public Date getArrivedDate() {
		return arrivedDate;
	}

	public void setArrivedDate(Date arrivedDate) {
		this.arrivedDate = arrivedDate;
	}
	
	public String getRequestDayStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		requestDayStr = format.format(getBeginDate());
		return requestDayStr;
	}
	
	public void setRequestDayStr(String requestDayStr) {
		this.requestDayStr = requestDayStr;
	}

	public String getRequestDateStr() {
		if (getBeginTime() == getEndTime() && getBeginMinute() == getEndMinute()) {
			requestDateStr = "Шууд";
		} else {
			requestDateStr = getBeginTime() + ":" + getBeginMinute() + "-" + getEndTime() + ":" + getEndMinute();
		}
		if (getIsExpress() == 1)
			requestDateStr = "Яаралтай";
		return requestDateStr;
	}

	public void setRequestDateStr(String requestDateStr) {
		this.requestDateStr = requestDateStr;
	}

	public boolean isFreeInspection() {
		return freeInspection;
	}

	public void setFreeInspection(boolean freeInspection) {
		this.freeInspection = freeInspection;
	}
	
	public String getPaymentTypeString() {
		return paymentTypeString;
	}
	
	public void setPaymentTypeString(String paymentTypeString) {
		this.paymentTypeString = paymentTypeString;
	}
	
	public int getPrevention() {
		return prevention;
	}
	
	public void setPrevention(int prevention) {
		this.prevention = prevention;
	}
	
	public boolean isHasPrevention() {
		hasPrevention = true;
		if(this.prevention == 0) hasPrevention = false; 
		return hasPrevention;
	}
	
	public void setHasPrevention(boolean hasPrevention) {
		setPrevention(hasPrevention ? 1 : 0);
		this.hasPrevention = hasPrevention;
	}
	
	public String getPreventionOrgName() {
		return preventionOrgName;
	}
	
	public void setPreventionOrgName(String preventionOrgName) {
		this.preventionOrgName = preventionOrgName;
	}
}

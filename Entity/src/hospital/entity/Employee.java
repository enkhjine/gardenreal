package hospital.entity;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.PaymentInspection;

import java.io.Serializable;
import java.math.BigDecimal;
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
 @Table(name = "Employee")
 public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "SubOrganizationPkId")
	private BigDecimal subOrganizationPkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "RegNumber")
	private String regNumber;

	@Column(name = "RoomNumber")
	private String roomNumber;

	@Column(name = "DegreePkId")
	private BigDecimal degreePkId;

	@Column(name = "IsInspect")
	private byte isInspect;

	@Column(name = "IsDentist")
	private byte isDentist;

	@Column(name = "Email")
	private String email;

	@Column(name = "Phone")
	private String phone;

	@Column(name = "UserPkId")
	private BigDecimal userPkId;

	@Column(name = "InspectionTime")
	private int inspectionTime;
	
	@Column(name = "Signature")
	private String signature;
	
	@Column(name = "Stamp")
	private String stamp;
	
	@Column(name = "InspectionPassword")
	private String inspectionPassword;

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
	private boolean inspect;

	@Transient
	private String status;

	@Transient
	private String inspectStatus;

	@Transient
	private String subOrganizationName;

	@Transient
	private String degreeName;

	@Transient
	private BigDecimal price;

	@Transient
	private BigDecimal requestPkId;

	@Transient
	private String priceHistoryName;

	@Transient
	private int mood;
	
	@Transient
	private List<PaymentInspection> paymentInspections;
	
	@Transient
	private BigDecimal sumAmount;
	
	@Transient
	private BigDecimal discountPercent;

	@Transient
	private int requestCount;
	
	@Transient
	private int  employeeRequestCount;
	
	@Transient
	private long inspectionCount;
	
	@Transient
	private int month;
	
	@Transient
	private String inspectionPassword1;
	
	@Transient
	private String inspectionPassword2;
	
	@Transient
	private long employeeInspectionCount;
	
	@Transient
	private long subOrganizationInspectionCount;
	
	public Employee() {
		super();
	}
	 
	public Employee(BigDecimal employeePkId, String employeeFirstName,
			BigDecimal requestPkId, BigDecimal degreePkId, String degreeName,
			BigDecimal pkId, String itemName, BigDecimal price, int mood, String subOrganizationName) {
		this.pkId = employeePkId;
		this.firstName = employeeFirstName;
		this.requestPkId = requestPkId;
		this.degreePkId = degreePkId;
		this.degreeName = degreeName;
		//this.pkId = pkId;
		this.priceHistoryName = itemName;
		this.price = price;
		this.mood = mood;
		this.subOrganizationName = subOrganizationName;
	}

	public Employee(BigDecimal pkId, BigDecimal subOrganizationPkId, String id,
			String lastName, String firstName, String regNumber,
			String roomNumber, BigDecimal degreePkId, byte isInspect,
			byte isDentist, String email, String phone, BigDecimal userPkId,
			int inspectionTime, Date createdDate, BigDecimal createdBy,
			Date updatedDate, BigDecimal updatedBy,long requestCount) {
		super();
		this.pkId = pkId;
		this.subOrganizationPkId = subOrganizationPkId;
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.roomNumber = roomNumber;
		this.degreePkId = degreePkId;
		this.isInspect = isInspect;
		this.isDentist = isDentist;
		this.email = email;
		this.phone = phone;
		this.userPkId = userPkId;
		this.inspectionTime = inspectionTime;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.requestCount = Integer.parseInt(requestCount + "");
	}

	public Employee(BigDecimal pkId, BigDecimal subOrganizationPkId,
			String subOrganizationName, String lastName, String firstName,
			String regNumber, String roomNumber, BigDecimal degreePkId,
			String email, String id, String phone, String degreeName,
			byte isInspect, byte isDentist, int inspectionTime, BigDecimal userPkId) {
		super();
		this.pkId = pkId;
		this.subOrganizationPkId = subOrganizationPkId;
		this.subOrganizationName = subOrganizationName;
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.roomNumber = roomNumber;
		this.degreePkId = degreePkId;
		this.email = email;
		this.phone = phone;
		this.degreeName = degreeName;
		this.isInspect = isInspect;
		this.isDentist = isDentist;
		this.inspectionTime = inspectionTime;
		this.userPkId = userPkId;

	}
	
	public Employee(BigDecimal employeePkId, String employeeFirstName, BigDecimal requestPkId, BigDecimal degreePkId, String degreeName, int mood, String subOrganizationName){
		this.pkId = employeePkId;
		this.firstName = employeeFirstName;
		this.requestPkId = requestPkId;
		this.degreePkId = degreePkId;
		this.degreeName = degreeName;
		this.mood = mood;
		this.subOrganizationName = subOrganizationName;
	}
	
	public Employee(Employee employee, int month, BigDecimal sumAmount) {
		super();
		this.pkId = employee.getPkId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.subOrganizationPkId = employee.getSubOrganizationPkId();
		this.month = month;
		this.sumAmount = sumAmount;
	}
	
	public Employee (String firstName, String subOrganizationName, long inspectionCount)
	{
		this.firstName = firstName;
		this.subOrganizationName = subOrganizationName;
		this.inspectionCount = inspectionCount;
	}
	
	

	public String getInspectStatus() {
		return isInspect == 1 ? "Тийм" : "Үгүй";
	}

	public void setInspectStatus(String inspectStatus) {
		this.inspectStatus = inspectStatus;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
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

	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public BigDecimal getDegreePkId() {
		return degreePkId;
	}

	public void setDegreePkId(BigDecimal degreePkId) {
		this.degreePkId = degreePkId;
	}

	public byte getIsInspect() {
		return isInspect;
	}

	public void setIsInspect(byte isInspect) {
		this.isInspect = isInspect;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public BigDecimal getUserPkId() {
		return userPkId;
	}

	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
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

	public int getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(int inpectionTime) {
		this.inspectionTime = inpectionTime;
	}

	public boolean isInspect() {
		return this.isInspect == 1;
	}

	public void setInspect(boolean inspect) {
		this.isInspect = (byte) (inspect ? 1 : 0);
	}

	public boolean isDentist() {
		return this.isDentist == 1;
	}

	public void setDentist(boolean dentist) {
		this.isDentist = (byte) (dentist ? 1 : 0);
	}

	public byte getIsDentist() {
		return isDentist;
	}

	public void setIsDentist(byte isDentist) {
		this.isDentist = isDentist;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public String getPriceHistoryName() {
		return priceHistoryName;
	}

	public void setPriceHistoryName(String priceHistoryName) {
		this.priceHistoryName = priceHistoryName;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}
	
	public BigDecimal getSumAmount() {
		return sumAmount;
	}
	
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	
	public BigDecimal getDiscountPercent() {
		return discountPercent == null ? BigDecimal.ZERO : discountPercent;
	}
	
	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}
	
	public int getRequestCount() {
		return requestCount;
	}
	
	public void setRequestCount(int requestCount) {
		this.requestCount = requestCount;
	}
	
	public long getInspectionCount() {
		return inspectionCount;
	}
	
	public void setInspectionCount(long inspectionCount) {
		this.inspectionCount = inspectionCount;
	}
	
	public List<PaymentInspection> getPaymentInspections() {
		if(paymentInspections == null) paymentInspections = new ArrayList<PaymentInspection>();
		return paymentInspections;
	}
	
	public void setPaymentInspections(List<PaymentInspection> paymentInspections) {
		this.paymentInspections = paymentInspections;
		this.sumAmount = BigDecimal.ZERO;
		for (PaymentInspection paymentInspection : paymentInspections) {
			this.sumAmount = this.sumAmount.add(paymentInspection.getAmount());
		}
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
	}
	
	public String getStamp() {
		return stamp;
	}
	
	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	public String getInspectionPassword() {
		return inspectionPassword;
	}
	
	public void setInspectionPassword(String inspectionPassword) {
		this.inspectionPassword = inspectionPassword;
	}
	
	public String getInspectionPassword1() {
		return inspectionPassword1;
	}
	
	public void setInspectionPassword1(String inspectionPassword1) {
		this.inspectionPassword1 = inspectionPassword1;
	}
	
	public String getInspectionPassword2() {
		return inspectionPassword2;
	}
	
	public void setInspectionPassword2(String inspectionPassword2) {
		this.inspectionPassword2 = inspectionPassword2;
	}

	public int getEmployeeRequestCount() {
		return employeeRequestCount;
	}

	public void setEmployeeRequestCount(int employeeRequestCount) {
		this.employeeRequestCount = employeeRequestCount;
	}
	
	public long getEmployeeInspectionCount() {
		return employeeInspectionCount;
	}
	
	public void setEmployeeInspectionCount(long employeeInspectionCount) {
		this.employeeInspectionCount = employeeInspectionCount;
	}
	
	public long getSubOrganizationInspectionCount() {
		return subOrganizationInspectionCount;
	}
	
	public void setSubOrganizationInspectionCount(long subOrganizationInspectionCount) {
		this.subOrganizationInspectionCount = subOrganizationInspectionCount;
	}
	
}
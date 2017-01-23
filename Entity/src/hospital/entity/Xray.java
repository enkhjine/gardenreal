package hospital.entity;

import hospital.businessentity.Tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Xray")
public class Xray implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Id")
	private String id;

	@Column(name = "XrayTypePkId")
	private BigDecimal xrayTypePkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "RoomNumber")
	private String roomNumber;

	@Column(name = "IsActive")
	private byte isActive;

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
	
	@Column(name = "windowType")
	private String windowType;

	@Transient
	private BigDecimal priceIn;

	@Transient	
	private Date priceInUsageDate;
	
	@Transient
	private EmployeeRequest employeeRequest;

	@Transient
	private boolean active;

	@Transient
	String xrayTypeName;

	@Transient
	String status;

	@Transient
	private BigDecimal requestPkId;
	
	@Transient
	private BigDecimal employeeRequestPkId;
	
	@Transient
	private String description;
	
	public Xray() {
		super();
	}
	public Xray(String name , String dtldescription){
		this.name  =name;
		this.description = dtldescription;
	}

	public Xray(BigDecimal pkId, BigDecimal xrayTypePkId, String name,
			String roomNumber, BigDecimal priceIn, Date priceInUsageDate,
			byte isActive, Date createdDate, BigDecimal createdBy,
			Date updatedDate, BigDecimal updatedBy, BigDecimal requestPkId) {
		super();
		this.pkId = pkId;
		this.xrayTypePkId = xrayTypePkId;
		this.name = name;
		this.roomNumber = roomNumber;
		this.priceIn = priceIn;
		this.priceInUsageDate = priceInUsageDate;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.requestPkId = requestPkId;
	}

	public Xray(BigDecimal pkId, BigDecimal xrayTypePkId, String name,
			String roomNumber, BigDecimal priceIn, Date priceInUsageDate,
			String xrayTypeName) {
		super();
		this.pkId = pkId;
		this.xrayTypePkId = xrayTypePkId;
		this.name = name;
		this.roomNumber = roomNumber;
		this.priceIn = priceIn;
		this.priceInUsageDate = priceInUsageDate;
		
		this.xrayTypeName = xrayTypeName;
	}
	
	public Xray(BigDecimal pkId, String id, BigDecimal xrayTypePkId, String name,
			String roomNumber, BigDecimal priceIn, Date priceInUsageDate,
			String xrayTypeName) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.xrayTypePkId = xrayTypePkId;
		this.name = name;
		this.roomNumber = roomNumber;
		this.priceIn = priceIn;
		this.priceInUsageDate = priceInUsageDate;	
		this.xrayTypeName = xrayTypeName;
	}

	public Xray(BigDecimal pkId, String id, BigDecimal xrayTypePkId,
			String name, String roomNumber, byte isActive,
			Date createdDate, BigDecimal createdBy, Date updatedDate,
			BigDecimal updatedBy, BigDecimal priceIn) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.xrayTypePkId = xrayTypePkId;
		this.name = name;
		this.roomNumber = roomNumber;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.priceIn = priceIn;
	}

	public Xray(BigDecimal pkId, String name, BigDecimal priceIn, BigDecimal requestPkId, BigDecimal employeeRequestPkId) {
		super();
		this.pkId = pkId;
		this.name = name;
		this.priceIn = priceIn;
		this.requestPkId = requestPkId;
		this.status = Tool.UNCHANGED;
		this.employeeRequestPkId = employeeRequestPkId;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getXrayTypePkId() {
		return xrayTypePkId;
	}

	public void setXrayTypePkId(BigDecimal xrayTypePkId) {
		this.xrayTypePkId = xrayTypePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public BigDecimal getPriceIn() {
		return priceIn;
	}

	public void setPriceIn(BigDecimal priceIn) {
		this.priceIn = priceIn;
	}

	public Date getPriceInUsageDate() {
		return priceInUsageDate;
	}

	public void setPriceInUsageDate(Date priceInUsageDate) {
		this.priceInUsageDate = priceInUsageDate;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
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

	public boolean isActive() {
		return this.isActive == 1 ? true : false;
	}

	public void setActive(boolean active) {
		this.isActive = (byte) (active ? 1 : 0);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getXrayTypeName() {
		return xrayTypeName;
	}

	public void setXrayTypeName(String xrayTypeName) {
		this.xrayTypeName = xrayTypeName;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}
	
	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}
	
	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}
	
	public BigDecimal getEmployeeRequestPkId() {
		return employeeRequestPkId;
	}
	
	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWindowType() {
		return windowType;
	}
	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}
	
	
}

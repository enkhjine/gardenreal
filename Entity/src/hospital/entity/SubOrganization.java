package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SubOrganization")
public class SubOrganization implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;

	@Column(name = "RoomNumber")
	private String roomNumber;

	@Column(name = "SubOrganizationTypePkId")
	private BigDecimal subOrganizationTypePkId;

	@Column(name = "inspectionTime")
	private int inspectionTime;

	@Column(name = "Name")
	private String name;
	
	/**
	 * 0 - Захиалга өгөхгүй
	 * 1 - Захиалга өгнө
	 * */
	@Column(name = "Orderable")
	private int orderable;

	@Transient
	private String status;

	@Transient
	private long employeeCount;

	@Transient
	private String type;
	
	@Transient
	private long requestCount;

	public SubOrganization() {
		super();
	}
	
	public SubOrganization(BigDecimal pkId, String name, long requestCount){
		this.pkId = pkId;
		this.name = name;
		this.requestCount = requestCount;
	}

	public SubOrganization(BigDecimal pkId, BigDecimal organizationPkId,
			String roomNumber, BigDecimal subOrganizationTypePkId,
			int inspectionTime, String name, long employeeCount, String type) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.roomNumber = roomNumber;
		this.subOrganizationTypePkId = subOrganizationTypePkId;
		this.inspectionTime = inspectionTime;
		this.name = name;
		this.employeeCount = employeeCount;
		this.type = type;

	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getInspectionTime() {
		return inspectionTime;
	}

	public void setInspectionTime(int inspectionTime) {
		this.inspectionTime = inspectionTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(long employeeCount) {
		this.employeeCount = employeeCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSubOrganizationTypePkId() {
		return subOrganizationTypePkId;
	}

	public void setSubOrganizationTypePkId(BigDecimal subOrganizationTypePkId) {
		this.subOrganizationTypePkId = subOrganizationTypePkId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public long getRequestCount() {
		return requestCount;
	}
	
	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}
	
	public int getOrderable() {
		return orderable;
	}
	
	public void setOrderable(int orderable) {
		this.orderable = orderable;
	}

}
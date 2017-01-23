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
@Table(name = "ExaminationGroup")
public class ExaminationGroup {
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "IsActive")
	private byte isActive;

	@Transient
	private String activeStatus;

	@Transient
	private boolean active;

	@Transient
	private long elementCount;

	@Transient
	private String status;

	public ExaminationGroup() {
		super();
	}

	public ExaminationGroup(BigDecimal pkId, String id, String name,
			BigDecimal createdBy, Date createdDate, BigDecimal updatedBy,
			Date updatedDate, byte isActive, long elementCount) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.name = name;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.isActive = isActive;
		this.elementCount = elementCount;
		
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getActiveStatus() {
		return isActive == 1 ? "Идэвхигүй" : "Идэвхитэй";
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public boolean isActive() {
		return this.isActive == 1 ? true : false;
	}

	public void setActive(boolean active) {
		this.isActive = (byte) (active ? 1 : 0);
	}

	public long getElementCount() {
		return elementCount;
	}

	public void setElementCount(long elementCount) {
		this.elementCount = elementCount;
	}
	
	public String getDateString (Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}

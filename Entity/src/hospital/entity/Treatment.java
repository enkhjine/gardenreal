package hospital.entity;

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
@Table(name = "Treatment")
public class Treatment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Id")
	private String id;

	@Transient
	private String status;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;

	@Column(name = "TreatmentTypePkId")
	private BigDecimal treatmentTypePkId;

	@Column(name = "Name")
	private String name;

	@Transient
	private BigDecimal price;

	@Column(name = "Cost")
	private BigDecimal cost;

	@Transient
	private Date usageDate;

	@Column(name = "IsActive")
	private byte isActive;
	
	/**
	 * 0 - FALSE
	 * 1 - TRUE
	 * **/
	@Column(name = "IsDiscount")
	private byte isDiscount;

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
	private boolean hasDtl;

	@Transient
	private boolean active;

	@Transient
	private long itemCount;

	@Transient
	private BigDecimal amount;

	@Transient
	private String activeStatus;

	@Transient
	private String treatmentFullName;
	
	@Transient
	private boolean discount;
	
	@Transient
	private int times;
	
	@Transient
	private String qty;
	
	@Transient
	private int daylength;
	
	@Transient
	private boolean icdMap;

	public Treatment() {
		super();
	}
	public Treatment(String tname ,String  q , int t,int d ){
		this.name=tname;
		this.qty=q;
		this.times =t;
		this.daylength=d;
	}

	public String getActiveStatus() {
		return isActive == 1 ? "Идэвхигүй" : "Идэвхитэй";
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Treatment(BigDecimal pkId, BigDecimal organizationPkId,
			BigDecimal treatmentTypePkId, String name, BigDecimal price,
			Date usageDate, byte isActive, Date createdDate,
			BigDecimal createdBy, Date updatedDate, BigDecimal updatedBy,
			BigDecimal cost, long itemCount) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.treatmentTypePkId = treatmentTypePkId;
		this.name = name;
		this.price = price;
		this.usageDate = usageDate;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.cost = cost;
		this.itemCount = itemCount;

	}
	
	public Treatment(BigDecimal pkId, String id, BigDecimal organizationPkId,
			BigDecimal treatmentTypePkId, String name, BigDecimal price,
			Date usageDate, byte isActive, Date createdDate,
			BigDecimal createdBy, Date updatedDate, BigDecimal updatedBy,
			BigDecimal cost, long itemCount) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.organizationPkId = organizationPkId;
		this.treatmentTypePkId = treatmentTypePkId;
		this.name = name;
		this.price = price;
		this.usageDate = usageDate;
		this.isActive = isActive;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.cost = cost;
		this.itemCount = itemCount;

	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getTreatmentTypePkId() {
		return treatmentTypePkId;
	}

	public void setTreatmentTypePkId(BigDecimal treatmentTypePkId) {
		this.treatmentTypePkId = treatmentTypePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getUsageDate() {
		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public boolean isActive() {
		return this.isActive == 1 ? true : false;
	}

	public void setActive(boolean active) {
		this.isActive = (byte) (active ? 1 : 0);
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

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isHasDtl() {
		return hasDtl;
	}

	public void setHasDtl(boolean hasDtl) {
		this.hasDtl = hasDtl;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationTypePkId(BigDecimal organizationTypePkId) {
		this.organizationPkId = organizationTypePkId;
	}

	public long getItemCount() {
		return itemCount;
	}

	public void setItemCount(long itemCount) {
		this.itemCount = itemCount;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTreatmentFullName() {
		return treatmentFullName;
	}

	public void setTreatmentFullName(String treatmentFullName) {
		this.treatmentFullName = treatmentFullName;
	}
	
	public byte getIsDiscount() {
		return isDiscount;
	}
	
	public void setIsDiscount(byte isDiscount) {
		this.isDiscount = isDiscount;
	}
	
	public boolean isDiscount() {
		discount = true;
		if(getIsDiscount() == 1) discount = false;
		return discount;
	}
	
	public void setDiscount(boolean discount) {
		setIsDiscount((byte)1);
		if(discount) setIsDiscount((byte)0);
		this.discount = discount;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	
	
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public int getDaylength() {
		return daylength;
	}
	public void setDaylength(int daylength) {
		this.daylength = daylength;
	}
	public boolean isIcdMap() {
		return icdMap;
	}
	public void setIcdMap(boolean icdMap) {
		this.icdMap = icdMap;
	}

}

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
@Table(name = "Item")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;

	@Column(name = "Name")
	private String name;
	
	@Column(name = "EntityPrice")
	private BigDecimal entityPrice;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PriceUsageDate")
	private Date priceUsageDate;	
	

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
	
	@Column(name = "MeasurementPkId")
	private BigDecimal measurementPkId;
	
	

	@Transient
	private String status;
	
	@Transient
	private String id;
	
	@Transient 
	private BigDecimal itemCount;
	
	@Transient
	private BigDecimal cost;
	
	@Transient
	private BigDecimal amount;
	
	@Transient
	private String measurementName;
	
	
	public Item(){
		super();
	}

	public Item(BigDecimal pkId, BigDecimal organizationPkId, String name, BigDecimal entityPrice, Date priceUsageDate, BigDecimal measurementPkId, String measurementName) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.name = name;
		this.entityPrice = entityPrice;
		this.priceUsageDate = priceUsageDate;
		this.measurementPkId = measurementPkId;
		this.measurementName = measurementName;
	}
	
	public Item(BigDecimal pkId, BigDecimal organizationPkId, String name, BigDecimal itemCount,  BigDecimal entityPrice, Date priceUsageDate, BigDecimal measurementPkId, String measurementName) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.name = name;
		this.itemCount = itemCount;		
		this.entityPrice = entityPrice;
		this.priceUsageDate = priceUsageDate;
		this.measurementPkId = measurementPkId;
		this.measurementName = measurementName;
	}


	
	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getItemCount() {
		return itemCount == null ? BigDecimal.ZERO : itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}
	
	public BigDecimal getCost() {
		return cost == null ? BigDecimal.ZERO : cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}



	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}



	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getEntityPrice() {
		return entityPrice;
	}

	public void setEntityPrice(BigDecimal entityPrice) {
		this.entityPrice = entityPrice;
	}

	public Date getPriceUsageDate() {
		return priceUsageDate;
	}

	public void setPriceUsageDate(Date priceUsageDate) {
		this.priceUsageDate = priceUsageDate;
	}
	
	public BigDecimal getAmount() {
		if(amount == null)
			return BigDecimal.ZERO;
		else
			return amount;
		
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getMeasurementName() {
		return measurementName;
	}
	public BigDecimal getMeasurementPkId() {
		return measurementPkId;
	}
	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}
	public void setMeasurementPkId(BigDecimal measurementPkId) {
		this.measurementPkId = measurementPkId;
	}
	
	
}

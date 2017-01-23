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
@Table(name = "PriceHistory")
public class PriceHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ItemPkId")
	private BigDecimal itemPkId;

	@Column(name = "ItemName")
	private String itemName;

	@Column(name = "PriceUsageDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceUsageDate;

	@Column(name = "Price")
	private BigDecimal price;

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
	private String status;

	@Transient
	private String measurement;

	@Transient
	private String puds;
	
	@Transient
	private BigDecimal employeePkId;
	
	@Transient
	private String employeeFirstName;
	
	@Transient
	private BigDecimal requestPkId;
	
	@Transient
	private BigDecimal degreePkId;
	
	@Transient
	private String degreeName;
	
	@Transient
	private int mood;
	
	@Transient
	private String subOrganizationName;
	
	@Transient
	private String dateString;

	public PriceHistory() {
		super();
	}
	
	public PriceHistory(BigDecimal employeePkId, String employeeFirstName, BigDecimal requestPkId, BigDecimal degreePkId, String degreeName, BigDecimal pkId, String itemName, BigDecimal price, int mood, String subOrganizationName){
		this.employeePkId = employeePkId;
		this.employeeFirstName = employeeFirstName;
		this.requestPkId = requestPkId;
		this.degreePkId = degreePkId;
		this.degreeName = degreeName;
		this.pkId = pkId;
		this.itemName = itemName;
		this.price = price;
		this.mood = mood;
		this.subOrganizationName = subOrganizationName;
	}

	public PriceHistory(BigDecimal pkId, BigDecimal itemPkId, String itemName,
			BigDecimal price, Date priceUsageDate, String measurement) {
		super();
		this.pkId = pkId;
		this.itemPkId = itemPkId;
		this.itemName = itemName;
		this.price = price;
		this.priceUsageDate = priceUsageDate;
		this.measurement = measurement;

	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getItemPkId() {
		return itemPkId;
	}

	public void setItemPkId(BigDecimal itemPkId) {
		this.itemPkId = itemPkId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getPriceUsageDate() {
		return priceUsageDate;
	}

	public void setPriceUsageDate(Date priceUsageDate) {
		this.priceUsageDate = priceUsageDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCreatedDate() {
		String dateString = new SimpleDateFormat("dd-MM-yyyy")
				.format(this.createdDate);
		return dateString;

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

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPuds() {
		if(priceUsageDate != null)
		puds = new SimpleDateFormat("dd-MM-yyyy")
				.format(this.priceUsageDate);
		else 
			puds = "";
		return puds;
	}

	public void setPuds(String puds) {
		this.puds = puds;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public BigDecimal getDegreePkId() {
		return degreePkId;
	}

	public void setDegreePkId(BigDecimal degreePkId) {
		this.degreePkId = degreePkId;
	}

	public String getDegreeName() {
		return degreeName;
	}

	public void setDegreeName(String degreeName) {
		this.degreeName = degreeName;
	}
	
	public int getMood() {
		return mood;
	}
	
	public void setMood(int mood) {
		this.mood = mood;
	}
	
	public String getDateString() {
		if(updatedDate == null)
			dateString = "";
		else
		dateString = new SimpleDateFormat("dd-MM-yyyy")
		.format(this.updatedDate);
		return dateString;
	}
	
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	public String getSubOrganizationName() {
		return subOrganizationName;
	}
	
	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}
}

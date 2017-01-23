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
@Table(name = "DegreePriceHistory")
public class DegreePriceHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "DegreePkId")
	private BigDecimal degreePkId;

	@Column(name = "PriceUsageDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date priceUsageDate;

	@Column(name = "Price")
	private BigDecimal price;
	
	@Column(name = "RePrice")
	private BigDecimal rePrice;

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
	private String degreeName;

	@Transient
	private String updatedUserName;

	@Transient
	private String createdUserName;

	public DegreePriceHistory() {
		super();
	}
	

	public DegreePriceHistory(BigDecimal pkId, BigDecimal degreePkId,
			Date priceUsageDate, BigDecimal price, Date createdDate,
			BigDecimal createdBy, Date updatedDate, BigDecimal updatedBy,
			String degreeName, String updatedUserName, String createdUserName) {
		super();
		this.pkId = pkId;
		this.degreePkId = degreePkId;
		this.priceUsageDate = priceUsageDate;
		this.price = price;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.degreeName = degreeName;
		this.updatedUserName = updatedUserName;
		this.createdUserName = createdUserName;
	}

	public DegreePriceHistory(BigDecimal pkId, BigDecimal degreePkId,
			Date priceUsageDate, BigDecimal price, BigDecimal rePrice, Date createdDate,
			BigDecimal createdBy, Date updatedDate, BigDecimal updatedBy,
			String degreeName, String updatedUserName, String createdUserName) {
		super();
		this.pkId = pkId;
		this.degreePkId = degreePkId;
		this.priceUsageDate = priceUsageDate;
		this.price = price;
		this.rePrice = rePrice;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.updatedDate = updatedDate;
		this.updatedBy = updatedBy;
		this.degreeName = degreeName;
		this.updatedUserName = updatedUserName;
		this.createdUserName = createdUserName;
	}


	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
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
		String dateString = new SimpleDateFormat("yyyy-MM-dd")
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

	public String getUpdatedUserName() {
		return updatedUserName;
	}

	public void setUpdatedUserName(String updatedUserName) {
		this.updatedUserName = updatedUserName;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDateString(Date date) {
		return date == null ? "" : new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public BigDecimal getRePrice() {
		if(rePrice == null) rePrice = BigDecimal.ZERO;
		return rePrice;
	}
	
	public void setRePrice(BigDecimal rePrice) {
		this.rePrice = rePrice;
	}
}

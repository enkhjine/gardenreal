package hospital.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ElementPrice")
public class ElementPrice {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "ElementPkId")
	private BigDecimal elementPkId;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Column(name = "BeginDate")	
	@Temporal(TemporalType.TIMESTAMP)
	private Date beginDate;
	
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

	public ElementPrice() {
		super();
	}
	
	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getElementPkId() {
		return elementPkId;
	}

	public void setElementPkId(BigDecimal elementPkId) {
		this.elementPkId = elementPkId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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
	
}

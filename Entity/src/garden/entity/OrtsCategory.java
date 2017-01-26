package garden.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "OrtsCategory")
public class OrtsCategory implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
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
	
	public OrtsCategory(){
		super();
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

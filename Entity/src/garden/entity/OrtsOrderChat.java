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
import javax.persistence.Transient;

@Entity
@Table(name = "OrtsOrderChat")
public class OrtsOrderChat implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "IsSeen")
	private byte isSeen;
	
	@Column(name = "UserPkId")
	private BigDecimal userPkId;
	
	@Column(name = "OrderPkId")
	private BigDecimal orderPkId;
	
	@Column(name = "HasGarden")
	private byte hasGarden;
	
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
	private boolean seen;
	
	@Transient
	private boolean garden;
	
	public OrtsOrderChat(){
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

	public byte getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(byte isSeen) {
		this.isSeen = isSeen;
	}

	public BigDecimal getUserPkId() {
		return userPkId;
	}

	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
	}

	public BigDecimal getOrderPkId() {
		return orderPkId;
	}

	public void setOrderPkId(BigDecimal orderPkId) {
		this.orderPkId = orderPkId;
	}

	public byte getHasGarden() {
		return hasGarden;
	}

	public void setHasGarden(byte hasGarden) {
		this.hasGarden = hasGarden;
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
	
	public boolean isGarden() {
		garden = getHasGarden() == 1 ? true : false;
		return garden;
	}
	
	public void setGarden(boolean garden) {
		setHasGarden(garden ? (byte) 1 : 0);
		this.garden = garden;
	}
	
	public boolean isSeen() {
		seen = getIsSeen() == 1 ? true : false;
		return seen;
	}
	
	public void setSeen(boolean seen) {
		setIsSeen(seen ? (byte) 1 : 0);
		this.seen = seen;
	}
	
	

}

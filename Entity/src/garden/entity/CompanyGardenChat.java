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
@Table(name = "CompanyGardenChat")
public class CompanyGardenChat implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "IsSeen")
	private byte isSeen;
	
	@Column(name = "UserPkId")
	private BigDecimal userPkId;

	@Column(name = "IsGarden")
	private byte isGarden;
	
	@Column(name = "CompanyPkId")
	private BigDecimal companyPkId;
	
	@Column(name = "GardenPkId")
	private BigDecimal gardenPkId;
	
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
	
	public CompanyGardenChat(){
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

	public byte getIsGarden() {
		return isGarden;
	}

	public void setIsGarden(byte isGarden) {
		this.isGarden = isGarden;
	}

	public BigDecimal getCompanyPkId() {
		return companyPkId;
	}

	public void setCompanyPkId(BigDecimal companyPkId) {
		this.companyPkId = companyPkId;
	}

	public BigDecimal getGardenPkId() {
		return gardenPkId;
	}

	public void setGardenPkId(BigDecimal gardenPkId) {
		this.gardenPkId = gardenPkId;
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

	public boolean isSeen() {
		seen = getIsSeen() == 1 ? true : false;
		return seen;
	}

	public void setSeen(boolean seen) {
		setIsSeen(seen ? (byte) 1 : 0);
		this.seen = seen;
	}

	public boolean isGarden() {
		garden = getIsGarden() == 1 ? true : false;
		return garden;
	}

	public void setGarden(boolean garden) {
		setIsGarden(garden ? (byte) 1 : 0);
		this.garden = garden;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

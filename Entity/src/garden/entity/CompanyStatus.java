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
@Table(name = "CompanyStatus")
public class CompanyStatus implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "IsOnline")
	private byte isOnline;
	
	@Column(name = "UserPkId")
	private BigDecimal userPkId;
	
	@Column(name = "CompanyPkId")
	private BigDecimal companyPkId;
	
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
	private boolean online;
	
	public CompanyStatus(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public byte getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(byte isOnline) {
		this.isOnline = isOnline;
	}

	public BigDecimal getUserPkId() {
		return userPkId;
	}

	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
	}

	public BigDecimal getCompanyPkId() {
		return companyPkId;
	}

	public void setCompanyPkId(BigDecimal companyPkId) {
		this.companyPkId = companyPkId;
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

	public boolean isOnline() {
		online = getIsOnline() == 1 ? true : false;
		return online;
	}

	public void setOnline(boolean online) {
		setIsOnline(online ? (byte) 1 : 0);
		this.online = online;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

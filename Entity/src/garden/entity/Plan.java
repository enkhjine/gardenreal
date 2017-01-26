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
@Table(name = "APlan")
public class Plan implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "GardenPkId")
	private BigDecimal gardenPkId;
	
	@Column(name = "CreatedPkId")
	private BigDecimal createdPkId;
	
	@Column(name = "IsConfirm")
	private byte isConfirm;
	
	@Column(name = "PlanDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date planDate;
	
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
	private boolean confirm;
	
	public Plan(){
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

	public BigDecimal getGardenPkId() {
		return gardenPkId;
	}

	public void setGardenPkId(BigDecimal gardenPkId) {
		this.gardenPkId = gardenPkId;
	}

	public BigDecimal getCreatedPkId() {
		return createdPkId;
	}

	public void setCreatedPkId(BigDecimal createdPkId) {
		this.createdPkId = createdPkId;
	}

	public byte getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(byte isConfirm) {
		this.isConfirm = isConfirm;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
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
	
	public boolean isConfirm() {
		confirm = false;
		if(getIsConfirm() == 1) confirm = true;
		return confirm;
	}
	
	public void setConfirm(boolean confirm) {
		setIsConfirm(confirm ? (byte) 1 : 0);
		this.confirm = confirm;
	}

}

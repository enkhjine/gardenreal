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
@Table(name = "OrtsOrder")
public class OrtsOrder implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "IsSeen")
	private byte isSeen;
	
	@Column(name = "IsConfirm")
	private byte isConfirm;
	
	@Column(name = "IsRecieved")
	private byte isRecieved;
	
	@Column(name = "RecieverPkId")
	private BigDecimal recieverPkId;
	
	@Column(name = "Status")
	private int status;
	
	@Column(name = "TotalPrice")
	private BigDecimal totalPrice;
	
	@Column(name = "GardenPkId")
	private BigDecimal gardenPkId;
	
	@Column(name = "CompanyPkId")
	private BigDecimal companyPkId;
	
	@Column(name = "ChildrenCount")
	private BigDecimal childrenCount;
	
	@Column(name = "PlanPkId")
	private BigDecimal planPkId;
	
	@Column(name = "StartDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "EndDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
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
	private boolean confirm;
	
	@Transient
	private boolean recieved;
	
	public OrtsOrder(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getIsSeen() {
		return isSeen;
	}

	public void setIsSeen(byte isSeen) {
		this.isSeen = isSeen;
	}

	public byte getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(byte isConfirm) {
		this.isConfirm = isConfirm;
	}

	public byte getIsRecieved() {
		return isRecieved;
	}

	public void setIsRecieved(byte isRecieved) {
		this.isRecieved = isRecieved;
	}

	public BigDecimal getRecieverPkId() {
		return recieverPkId;
	}

	public void setRecieverPkId(BigDecimal recieverPkId) {
		this.recieverPkId = recieverPkId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getGardenPkId() {
		return gardenPkId;
	}

	public void setGardenPkId(BigDecimal gardenPkId) {
		this.gardenPkId = gardenPkId;
	}

	public BigDecimal getCompanyPkId() {
		return companyPkId;
	}

	public void setCompanyPkId(BigDecimal companyPkId) {
		this.companyPkId = companyPkId;
	}

	public BigDecimal getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(BigDecimal childrenCount) {
		this.childrenCount = childrenCount;
	}

	public BigDecimal getPlanPkId() {
		return planPkId;
	}

	public void setPlanPkId(BigDecimal planPkId) {
		this.planPkId = planPkId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public boolean isConfirm() {
		confirm = getIsConfirm() == 1 ? true : false;
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		setIsConfirm(confirm ? (byte) 1 : 0);
		this.confirm = confirm;
	}

	public boolean isRecieved() {
		recieved = getIsRecieved() == 1 ? true : false;
		return recieved;
	}

	public void setRecieved(boolean recieved) {
		setIsRecieved(recieved ? (byte) 1 : 0);
		this.recieved = recieved;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

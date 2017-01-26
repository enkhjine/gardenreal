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
@Table(name = "FoodPlanPlanCategory")
public class FoodPlanPlanCategory implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "PlanCategoryPkId")
	private BigDecimal planCategoryPkId;
	
	@Column(name = "FoodPkId")
	private BigDecimal foodPkId;
	
	@Column(name = "GardenPkId")
	private BigDecimal gardenPkId;
	
	@Column(name = "Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "IsConfirm")
	private byte isConfirm;
	
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
	
	public FoodPlanPlanCategory(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getPlanCategoryPkId() {
		return planCategoryPkId;
	}

	public void setPlanCategoryPkId(BigDecimal planCategoryPkId) {
		this.planCategoryPkId = planCategoryPkId;
	}

	public BigDecimal getFoodPkId() {
		return foodPkId;
	}

	public void setFoodPkId(BigDecimal foodPkId) {
		this.foodPkId = foodPkId;
	}

	public BigDecimal getGardenPkId() {
		return gardenPkId;
	}

	public void setGardenPkId(BigDecimal gardenPkId) {
		this.gardenPkId = gardenPkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public byte getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(byte isConfirm) {
		this.isConfirm = isConfirm;
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

	public boolean isConfirm() {
		confirm = getIsConfirm() == 1 ? true : false;
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		setIsConfirm(confirm ? (byte) 1 : 0);
		this.confirm = confirm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

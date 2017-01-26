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
@Table(name = "OrtsOrderItem")
public class OrtsOrderItem implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrtsPkId")
	private BigDecimal ortsPkId;

	@Column(name = "OrderPkId")
	private BigDecimal orderPkId;
	
	@Column(name = "PlanPkId")
	private BigDecimal planPkId;
	
	@Column(name = "CustomOrtsPkId")
	private BigDecimal customOrtsPkId;
	
	@Column(name = "Size")
	private BigDecimal size;
	
	@Column(name = "ChildrenCount")
	private BigDecimal childrenCount;
	
	@Column(name = "OrderPrice")
	private BigDecimal orderPrice;
	
	@Column(name = "TotalPrice")
	private BigDecimal totalPrice;
	
	@Column(name = "DoneSize")
	private BigDecimal doneSize;
	
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
	
	public OrtsOrderItem(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getOrtsPkId() {
		return ortsPkId;
	}

	public void setOrtsPkId(BigDecimal ortsPkId) {
		this.ortsPkId = ortsPkId;
	}

	public BigDecimal getOrderPkId() {
		return orderPkId;
	}

	public void setOrderPkId(BigDecimal orderPkId) {
		this.orderPkId = orderPkId;
	}

	public BigDecimal getPlanPkId() {
		return planPkId;
	}

	public void setPlanPkId(BigDecimal planPkId) {
		this.planPkId = planPkId;
	}

	public BigDecimal getCustomOrtsPkId() {
		return customOrtsPkId;
	}

	public void setCustomOrtsPkId(BigDecimal customOrtsPkId) {
		this.customOrtsPkId = customOrtsPkId;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public BigDecimal getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(BigDecimal childrenCount) {
		this.childrenCount = childrenCount;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDoneSize() {
		return doneSize;
	}

	public void setDoneSize(BigDecimal doneSize) {
		this.doneSize = doneSize;
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

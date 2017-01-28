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

import garden.businessentity.Tool;

@Entity
@Table(name = "FoodOrts")
public class FoodOrts implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "FoodPkId")
	private BigDecimal foodPkId;
	
	@Column(name = "OrtsPkId")
	private BigDecimal ortsPkId;
	
	@Column(name = "Ilchleg")
	private BigDecimal ilchleg;
	
	@Column(name = "Uurag")
	private BigDecimal uurag;
	
	@Column(name = "Uuhtos")
	private BigDecimal uuhtos;
	
	@Column(name = "Nuursus")
	private BigDecimal nuursus;
	
	@Column(name = "Size")
	private BigDecimal size;
	
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
	private String name;
	
	@Transient
	private String status;
	
	public FoodOrts(){
		super();
		this.status = Tool.LAST;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getFoodPkId() {
		return foodPkId;
	}

	public void setFoodPkId(BigDecimal foodPkId) {
		this.foodPkId = foodPkId;
	}

	public BigDecimal getOrtsPkId() {
		return ortsPkId;
	}

	public void setOrtsPkId(BigDecimal ortsPkId) {
		this.ortsPkId = ortsPkId;
	}

	public BigDecimal getIlchleg() {
		return ilchleg;
	}

	public void setIlchleg(BigDecimal ilchleg) {
		this.ilchleg = ilchleg;
	}

	public BigDecimal getUurag() {
		if(uurag == null) uurag = BigDecimal.ZERO;
		return uurag;
	}

	public void setUurag(BigDecimal uurag) {
		this.uurag = uurag;
	}

	public BigDecimal getUuhtos() {
		if(uuhtos == null) uuhtos = BigDecimal.ZERO;
		return uuhtos;
	}

	public void setUuhtos(BigDecimal uuhtos) {
		this.uuhtos = uuhtos;
	}

	public BigDecimal getNuursus() {
		if(nuursus == null) nuursus = BigDecimal.ZERO;
		return nuursus;
	}

	public void setNuursus(BigDecimal nuursus) {
		this.nuursus = nuursus;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void calcInch(){
		this.ilchleg = getUurag().multiply(new BigDecimal(4));
		this.ilchleg = this.ilchleg.add(getUuhtos().multiply(new BigDecimal(9)));
		this.ilchleg = this.ilchleg.add(getNuursus().multiply(new BigDecimal(4)));
	}

}

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
@Table(name = "Food")
public class Food implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Quality")
	private String quality;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "CategoryPkId")
	private BigDecimal categoryPkId;
	
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
	private String status;
	
	@Transient
	private String imageStatus;
	
	@Transient
	private BigDecimal ilchleg;
	
	@Transient
	private BigDecimal uurag;
	
	@Transient
	private BigDecimal uuhtos;
	
	@Transient
	private BigDecimal nuursus;
	
	@Transient
	private BigDecimal size;
	
	public Food(){
		super();
		this.status = Tool.ADDED;
	}
	
	public Food(BigDecimal pkId, String name, BigDecimal ilchleg, BigDecimal uurag, BigDecimal uuhtos, BigDecimal nuursus, BigDecimal size){
		super();
		this.pkId = pkId;
		this.name = name;
		this.ilchleg = ilchleg;
		this.uurag = uurag;
		this.uuhtos = uuhtos;
		this.nuursus = nuursus;
		this.size = size;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getCategoryPkId() {
		return categoryPkId;
	}

	public void setCategoryPkId(BigDecimal categoryPkId) {
		this.categoryPkId = categoryPkId;
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
	
	public String getImageStatus() {
		imageStatus = "оруулаагүй байна.";
		if(this.image != null && this.image.length() > 1) imageStatus = "хуулсан"; 
		return imageStatus;
	}
	
	public void setImageStatus(String imageStatus) {
		this.imageStatus = imageStatus;
	}

	public BigDecimal getIlchleg() {
		return ilchleg;
	}

	public void setIlchleg(BigDecimal ilchleg) {
		this.ilchleg = ilchleg;
	}

	public BigDecimal getUurag() {
		return uurag;
	}

	public void setUurag(BigDecimal uurag) {
		this.uurag = uurag;
	}

	public BigDecimal getUuhtos() {
		return uuhtos;
	}

	public void setUuhtos(BigDecimal uuhtos) {
		this.uuhtos = uuhtos;
	}

	public BigDecimal getNuursus() {
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

}

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
@Table(name = "Orts")
public class Orts implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Column(name = "Size")
	private BigDecimal size;
	
	@Column(name = "FreshSize")
	private BigDecimal freshSize;
	
	@Column(name = "Negj")
	private int negj;
	
	@Column(name = "Description")
	private String description;

	@Column(name = "CategoryPkId")
	private BigDecimal categoryPkId;
	
	@Column(name = "Ilchleg")
	private BigDecimal ilchleg;
	
	@Column(name = "Uurag")
	private BigDecimal uurag;
	
	@Column(name = "Uuhtos")
	private BigDecimal uuhtos;
	
	@Column(name = "Nuursus")
	private BigDecimal nuursus;
	
	@Column(name = "Image")
	private String image;

	@Column(name = "Image1")
	private String image1;
	
	@Column(name = "Image2")
	private String image2;
	
	@Column(name = "ParentPkId")
	private BigDecimal parentPkId;
	
	@Column(name = "Shinjilgee")
	private String shinjilgee;
	
	@Column(name = "HasPlus")
	private byte hasPlus;
	
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
	private boolean plus;
	
	public Orts(){
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public BigDecimal getFreshSize() {
		return freshSize;
	}

	public void setFreshSize(BigDecimal freshSize) {
		this.freshSize = freshSize;
	}

	public int getNegj() {
		return negj;
	}

	public void setNegj(int negj) {
		this.negj = negj;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getCategoryPkId() {
		return categoryPkId;
	}

	public void setCategoryPkId(BigDecimal categoryPkId) {
		this.categoryPkId = categoryPkId;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public BigDecimal getParentPkId() {
		return parentPkId;
	}

	public void setParentPkId(BigDecimal parentPkId) {
		this.parentPkId = parentPkId;
	}

	public String getShinjilgee() {
		return shinjilgee;
	}

	public void setShinjilgee(String shinjilgee) {
		this.shinjilgee = shinjilgee;
	}

	public byte getHasPlus() {
		return hasPlus;
	}

	public void setHasPlus(byte hasPlus) {
		this.hasPlus = hasPlus;
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

	public boolean isPlus() {
		plus = getHasPlus() == 1 ? true : false;
		return plus;
	}

	public void setPlus(boolean plus) {
		setHasPlus(plus ? (byte) 1 : 0);
		this.plus = plus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

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
@Table(name = "CustomOrts")
public class CustomOrts implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
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
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Image")
	private String image;
	
	@Column(name = "StartDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name = "EndDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;
	
	@Column(name = "Counts")
	private BigDecimal counts;
	
	@Column(name = "GardenPkId")
	private BigDecimal gardenPkId;
	
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
	
	public CustomOrts(){
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public BigDecimal getCounts() {
		return counts;
	}

	public void setCounts(BigDecimal counts) {
		this.counts = counts;
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

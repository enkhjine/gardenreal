package hospital.entity;

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
@Table(name = "Surgery")
public class Surgery implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "SurgeryTypePkId")
	private BigDecimal surgeryTypePkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Active")
	private int active;
	
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
	private BigDecimal price;

	@Transient
	private Date usageDate;
	@Transient
	private String treatmentFullName;
	
	@Transient
	private boolean discount;
	
	@Transient
	private String status;
	
	@Transient
	private BigDecimal surgeryPricePkId;

	public Surgery() {
		super();
	}
	
	public Surgery(Surgery surgery, View_Surgery price) {
		this.pkId = surgery.getPkId();
		this.organizationPkId = surgery.getOrganizationPkId();
		this.surgeryTypePkId = surgery.getSurgeryTypePkId();
		this.name = surgery.getName();
		this.active = surgery.getActive();
		this.createdDate = surgery.getCreatedDate();
		this.createdBy = surgery.getCreatedBy();
		this.updatedDate = surgery.getUpdatedDate();
		this.updatedBy = surgery.getUpdatedBy();
		this.price = price.getPrice();
		this.usageDate = price.getBeginDate();
		this.surgeryPricePkId = price.getSurgeryPricePkId();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getSurgeryTypePkId() {
		return surgeryTypePkId;
	}

	public void setSurgeryTypePkId(BigDecimal surgeryTypePkId) {
		this.surgeryTypePkId = surgeryTypePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUsageDate() {
		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public String getTreatmentFullName() {
		return treatmentFullName;
	}

	public void setTreatmentFullName(String treatmentFullName) {
		this.treatmentFullName = treatmentFullName;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getSurgeryPricePkId() {
		return surgeryPricePkId;
	}

	public void setSurgeryPricePkId(BigDecimal surgeryPricePkId) {
		this.surgeryPricePkId = surgeryPricePkId;
	}
}
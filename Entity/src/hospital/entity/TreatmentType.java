package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "TreatmentType")
public class TreatmentType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "SubOrganizationTypePkId")
	private BigDecimal subOrganizationTypePkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "SubOrganizationTypeName")
	private String subOrganizationTypeName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Transient
	private String status;

	@Transient
	private String subOrganizationName;

	@Transient
	private List<TreatmentTypeEmployeeMap> maps;

	public TreatmentType() {
		super();
	}

	public TreatmentType(BigDecimal pkId, BigDecimal subOrganizationTypePkId,
			String name, String subOrganizationTypeName) {
		super();
		this.pkId = pkId;
		this.subOrganizationTypePkId = subOrganizationTypePkId;
		this.name = name;
		this.subOrganizationTypeName = subOrganizationTypeName;

	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getSubOrganizationTypePkId() {
		return subOrganizationTypePkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationTypePkId) {
		this.subOrganizationTypePkId = subOrganizationTypePkId;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubOrganizationTypeName() {
		return this.subOrganizationTypeName;
	}

	public void setSubOrganizationTypeName(String subOrganizationTypeName) {
		this.subOrganizationTypeName = subOrganizationTypeName;
	}

	public void setSubOrganizationTypePkId(BigDecimal subOrganizationTypePkId) {
		this.subOrganizationTypePkId = subOrganizationTypePkId;
	}

	public List<TreatmentTypeEmployeeMap> getMaps() {
		return maps;
	}

	public void setMaps(List<TreatmentTypeEmployeeMap> maps) {
		this.maps = maps;
	}

}

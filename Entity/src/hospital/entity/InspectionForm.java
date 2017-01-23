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
@Table(name = "InspectionForm")
public class InspectionForm implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;

	@Column(name= "InspectionType")
	private String inspectionType;
	
	@Column(name = "InspectionPain")
	private String inspectionPain;
	
	@Column(name = "InspectionMedicalHistory")
	private String inspectionMedicalHistory;
	
	@Column(name = "InspectionCheckup")
	private String inspectionCheckup;
	
	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Transient
	private String status;
	
	public InspectionForm(){
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}

	public String getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		this.inspectionType = inspectionType;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInspectionPain() {
		return inspectionPain;
	}

	public void setInspectionPain(String inspectionPain) {
		this.inspectionPain = inspectionPain;
	}

	public String getInspectionMedicalHistory() {
		return inspectionMedicalHistory;
	}

	public void setInspectionMedicalHistory(String inspectionMedicalHistory) {
		this.inspectionMedicalHistory = inspectionMedicalHistory;
	}

	public String getInspectionCheckup() {
		return inspectionCheckup;
	}

	public void setInspectionCheckup(String inspectionCheckup) {
		this.inspectionCheckup = inspectionCheckup;
	}
}
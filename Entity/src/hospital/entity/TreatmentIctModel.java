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
@Table(name="TreatmentIctModel")
public class TreatmentIctModel implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId",length=18,nullable = false)
	private BigDecimal pkId;
	
	@Column(name="TreatmentName")
	private String treatmentName;
	
	@Column(name="TreatmentMapPkId")
	private BigDecimal treatmentMapPkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Transient
	private String status;
	
	public TreatmentIctModel() {
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public BigDecimal getTreatmentMapPkId() {
		return treatmentMapPkId;
	}

	public void setTreatmentMapPkId(BigDecimal treatmentMapPkId) {
		this.treatmentMapPkId = treatmentMapPkId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

}

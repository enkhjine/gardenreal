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
@Table(name="TreatmentIcdMap")
public class TreatmentIcdMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId" , length=18 , nullable=false)
	private BigDecimal pkId;
	
	@Column(name="TreatmentPkId")
	private BigDecimal treatmentPkId;
	
	@Column(name="IcdPkId")
	private BigDecimal icdPkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Transient
	private String status;
	
	public TreatmentIcdMap(){
		super();
	}
	
	public TreatmentIcdMap(BigDecimal trPkId){
		super();
		this.treatmentPkId = trPkId;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentPkId) {
		this.treatmentPkId = treatmentPkId;
	}

	public BigDecimal getIcdPkId() {
		return icdPkId;
	}

	public void setIcdPkId(BigDecimal icdPkId) {
		this.icdPkId = icdPkId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
}

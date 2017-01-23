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
@Table(name = "TreatmentDtl")

public class TreatmentDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "ItemPkId")
	private BigDecimal itemPkId;	

	@Column(name = "TreatmentPkId")
	private BigDecimal treatmentPkId;
	
	@Column(name = "ItemCount")
	private BigDecimal itemCount;
	
	@Transient
	private String status;
	
	@Transient
	private String itemName;
	
	@Transient
	private String treatmentName;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getItemPkId() {
		return itemPkId;
	}

	public void setItemPkId(BigDecimal itemPkId) {
		this.itemPkId = itemPkId;
	}

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentItemPkId) {
		this.treatmentPkId = treatmentItemPkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getItemCount() {
		return itemCount;
	}

	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}
	
	
	
	
	
	
	


}

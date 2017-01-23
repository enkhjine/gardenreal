package hospital.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "View_Treatment")
public class View_Treatment {
	@Id
	@Column(name = "TreatmentPkId", length = 18, nullable = false)
	private BigDecimal treatmentPkId;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentPkId) {
		this.treatmentPkId = treatmentPkId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	
	
	

}

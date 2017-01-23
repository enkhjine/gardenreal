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
@Table(name = "View_Surgery")
public class View_Surgery {
	@Id
	@Column(name = "SurgeryPkId", length = 18, nullable = false)
	private BigDecimal surgeryPkId;
	
	@Column(name = "SurgeryPricePkId")
	private BigDecimal surgeryPricePkId;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	public BigDecimal getSurgeryPkId() {
		return surgeryPkId;
	}

	public void setSurgeryPkId(BigDecimal surgeryPkId) {
		this.surgeryPkId = surgeryPkId;
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

	public BigDecimal getSurgeryPricePkId() {
		return surgeryPricePkId;
	}

	public void setSurgeryPricePkId(BigDecimal surgeryPricePkId) {
		this.surgeryPricePkId = surgeryPricePkId;
	}
}
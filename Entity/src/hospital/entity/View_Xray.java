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
@Table(name = "View_Xray")
public class View_Xray {

	@Id
	@Column(name = "XrayPkId", length = 18, nullable = false)
	private BigDecimal xrayPkId;

	@Column(name = "price")
	private BigDecimal price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	public BigDecimal getXrayPkId() {
		return xrayPkId;
	}

	public void setXrayPkId(BigDecimal xrayPkId) {
		this.xrayPkId = xrayPkId;
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

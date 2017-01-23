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


@Entity
@Table(name = "View_Examination")
public class View_Examination implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ExaminationPkId", length = 18, nullable = false)
	private BigDecimal examinationPkId;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;

	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
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
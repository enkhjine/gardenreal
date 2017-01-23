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
@Table(name = "ReturnPaymentHistory")
public class ReturnPaymentHistory implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	private Date date;
	
	@Column(name = "CashInAmount")
	private BigDecimal cashInAmount;
	
	@Column(name = "CardInAmount")
	private BigDecimal cardInAmount;
	
	public ReturnPaymentHistory(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getCashInAmount() {
		return cashInAmount;
	}

	public void setCashInAmount(BigDecimal cashInAmount) {
		this.cashInAmount = cashInAmount;
	}

	public BigDecimal getCardInAmount() {
		return cardInAmount;
	}

	public void setCardInAmount(BigDecimal cardInAmount) {
		this.cardInAmount = cardInAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

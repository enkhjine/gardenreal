package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PaymentDtl")
public class PaymentDtl {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "PaymentPkId", nullable = false)
	private BigDecimal paymentPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "TypePkId")
	private BigDecimal typePkId;
	
	@Column(name = "Amount", nullable = false)
	private BigDecimal amount;
	
	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;
	
	public PaymentDtl(){
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
	
	public BigDecimal getAmount() {
		if(amount == null) amount = BigDecimal.ZERO;
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public BigDecimal getTypePkId() {
		return typePkId;
	}
	
	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}
	
	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}
	
	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public BigDecimal getDiscountAmount() {
		if(discountAmount == null) discountAmount = BigDecimal.ZERO;
		return discountAmount;
	}
	
	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

}

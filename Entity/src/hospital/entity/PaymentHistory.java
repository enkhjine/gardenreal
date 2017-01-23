package hospital.entity;

import hospital.businessentity.Tool;

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

import com.sun.tools.xjc.dtd.parser.InputEntity;

@Entity
@Table(name = "PaymentHistory")
public class PaymentHistory implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;
	
	@Column(name = "CashInAmount")
	private BigDecimal cashInAmount;
	
	@Column(name = "CardInAmount")
	private BigDecimal cardInAmount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	private Date date;
	
	@Transient
	private BigDecimal paidAmount;
	
	@Transient
	private BigDecimal customerPkId;
	
	@Transient
	private String cardNumber;
	
	@Transient
	private String lastName;
	
	@Transient
	private String firstName;
	
	@Transient
	private String regNumber;
	
	@Transient
	private int age;
	
	@Transient
	private String gender;
	
	@Transient
	private BigDecimal cost;
	
	@Transient
	private String type;
	
	@Transient
	private BigDecimal amount;
	
	@Transient
	private BigDecimal amountPercent;
	
	@Transient
	private String status;
	
	@Transient
	private BigDecimal requestPkId;
	
	public PaymentHistory(){
		super();
	}
	
	public PaymentHistory(BigDecimal bigDecimal){
		this.amount = bigDecimal;
	}
	
	public PaymentHistory(BigDecimal requestPkId, BigDecimal customerPkId, String cardNumber,
			String lastName, String firstName, String regNumber, int age,
			String gender, BigDecimal cost) {
		super();
		this.customerPkId = customerPkId;
		this.cardNumber = cardNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.age = age;
		this.gender = gender;
		this.cost = cost;
		this.amount = cost;
		this.type = Tool.UNCHANGED;
		this.requestPkId = requestPkId;
	}

	public PaymentHistory(BigDecimal customerPkId, String cardNumber,
			String lastName, String firstName, String regNumber, int age,
			String gender, BigDecimal cost) {
		super();
		this.customerPkId = customerPkId;
		this.cardNumber = cardNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.age = age;
		this.gender = gender;
		this.cost = cost;
		this.amount = cost;
		this.type = Tool.UNCHANGED;
	}
	
	public PaymentHistory(BigDecimal paymentPkId, BigDecimal customerPkId, String cardNumber, String lastName, String firstName, String regNumber, int age, String gender, BigDecimal amount, BigDecimal payment){
		this.paymentPkId = paymentPkId;
		this.customerPkId = customerPkId;
		this.cardNumber = cardNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.age = age;
		this.gender = gender;
		this.amount = amount;
		this.cost = amount.subtract(payment);
		this.customerPkId = customerPkId;
		this.type = Tool.MODIFIED;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public BigDecimal getAmount() {
		return amount == null ? BigDecimal.ZERO : amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public BigDecimal getAmountPercent() {
		if(getAmount().compareTo(BigDecimal.ZERO) == 0) return BigDecimal.ZERO;
		amountPercent = getPayment().multiply(new BigDecimal(100));
		int ret = amountPercent.intValue()/getAmount().intValue();
		return new BigDecimal(ret);
	}
	
	public BigDecimal getPayment(){
		return getCardInAmount().add(getCashInAmount());
	}
	
	public void setAmountPercent(BigDecimal amountPercent) {
		this.amountPercent = amountPercent;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public BigDecimal getRequestPkId() {
		return requestPkId;
	}
	
	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public BigDecimal getCashInAmount() {
		return cashInAmount == null ? BigDecimal.ZERO : cashInAmount;
	}

	public void setCashInAmount(BigDecimal cashInAmount) {
		this.cashInAmount = cashInAmount;
	}

	public BigDecimal getCardInAmount() {
		return cardInAmount == null ? BigDecimal.ZERO : cardInAmount;
	}

	public void setCardInAmount(BigDecimal cardInAmount) {
		this.cardInAmount = cardInAmount;
	}
	
	public BigDecimal getPaidAmount() {
		paidAmount = getCardInAmount().add(getCashInAmount());
		return paidAmount;
	}
	
	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}
}

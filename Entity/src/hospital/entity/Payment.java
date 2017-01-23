package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Payment")
public class Payment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Id")
	private String id;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;
	
	@Column(name = "BasicAmount")
	private BigDecimal basicAmount;
	
	/**
	 * Хөнгөлөлт 
	 * **/
	@Column(name = "DiscountPercent")
	private BigDecimal discountPercent;
	
	@Column(name = "DiscountAmount")
	private BigDecimal discountAmount;

	/**
	 * Төлөгдөх дүн 
	 * **/
	@Column(name = "Amount")
	private BigDecimal amount;
	
	/**
	 * Төлсөн дүн 
	 * **/
	@Column(name = "PaidAmount")
	private BigDecimal paidAmount;
	
	@Column(name = "Description")
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date")
	private Date date;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Transient
	private BigDecimal treatmentAmount;
	
	@Transient
	private BigDecimal xrayAmount;
	
	@Transient
	private BigDecimal surgeryAmount;
	
	@Transient
	private BigDecimal examnationAmount;
	
	@Transient
	private BigDecimal balanceAmount;
	
	@Transient
	private BigDecimal employeeRequestPkId;
	
	@Transient
	private List<BigDecimal> employeeRequestPkIds;
	
	@Transient
	private String dateStr;
	
	@Column(name = "hasLottery")
	private int hasLottery;
	
	@Column(name = "registerNo")
	private String registerNo;
	
	@Column(name = "billId")
	private String billId;
	
	@Column(name = "macAddress")
	private String macAddress;
	
	@Column(name = "billDate")
	private String billDate;
	
	@Column(name = "lottery")
	private String lottery;
	
	@Column(name = "billType")
	private String billType;
	
	@Column(name = "internalCode")
	private String internalCode;
	
	@Column(name = "qrData")
	private String qrData;
	
	@Column(name = "merchantId")
	private String merchantId;
	
	@Transient
	private BigDecimal amountPercent;
	
	@Transient
	private BigDecimal cashInAmount;
	
	@Transient
	private BigDecimal cardInAmount;
	
	public Payment(){
		super();
	}
	
	public Payment(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
		super();
	}
	
	public Payment(BigDecimal bigDecimal, BigDecimal bigDecimal2, BigDecimal bigDecimal3) {
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getBasicAmount() {
		return basicAmount ==  null ? BigDecimal.ZERO : basicAmount;
	}

	public void setBasicAmount(BigDecimal basicAmount) {
		this.basicAmount = basicAmount;
	}

	public BigDecimal getDiscountPercent() {
		return discountPercent ==  null ? BigDecimal.ZERO : discountPercent;
	}

	public void setDiscountPercent(BigDecimal discountPercent) {
		this.discountPercent = discountPercent;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount ==  null ? BigDecimal.ZERO : discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getAmount() {
		return amount ==  null ? BigDecimal.ZERO : amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount ==  null ? BigDecimal.ZERO : paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
		this.cashInAmount = paidAmount.subtract(getCardInAmount());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getExamnationAmount() {
		return examnationAmount == null ? BigDecimal.ZERO : examnationAmount;
	}
	
	public void setExamnationAmount(BigDecimal examnationAmount) {
		this.examnationAmount = examnationAmount;
	}
	
	public BigDecimal getTreatmentAmount() {
		return treatmentAmount == null ? BigDecimal.ZERO : treatmentAmount;
	}
	
	public void setTreatmentAmount(BigDecimal treatmentAmount) {
		this.treatmentAmount = treatmentAmount;
	}
	
	public BigDecimal getXrayAmount() {
		return xrayAmount == null ? BigDecimal.ZERO : xrayAmount;
	}
	
	public void setXrayAmount(BigDecimal xrayAmount) {
		this.xrayAmount = xrayAmount;
	}
	
	public BigDecimal getBalanceAmount() {
		balanceAmount = getAmount().subtract(getPaidAmount());
		return balanceAmount;
	}
	
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	public BigDecimal getEmployeeRequestPkId() {
		return employeeRequestPkId;
	}
	
	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateStr() {
		if(date == null) date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		dateStr = dateFormat.format(date);
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	public BigDecimal getSurgeryAmount() {
		return surgeryAmount == null ? BigDecimal.ZERO : surgeryAmount;
	}
	
	public void setSurgeryAmount(BigDecimal surgeryAmount) {
		this.surgeryAmount = surgeryAmount;
	}

	public int getHasLottery() {
		return hasLottery;
	}

	public void setHasLottery(int hasLottery) {
		this.hasLottery = hasLottery;
	}

	public String getRegisterNo() {
		return registerNo;
	}

	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public String getLottery() {
		return lottery;
	}

	public void setLottery(String lottery) {
		this.lottery = lottery;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getInternalCode() {
		return internalCode;
	}

	public void setInternalCode(String internalCode) {
		this.internalCode = internalCode;
	}

	public String getQrData() {
		return qrData;
	}

	public void setQrData(String qrData) {
		this.qrData = qrData;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}	
	
	public BigDecimal getAmountPercent() {		 
		amountPercent = new BigDecimal(100*getPaidAmount().intValue()/getAmount().intValue());
		return amountPercent;
	}
	
	public void setAmountPercent(BigDecimal amountPercent) {
		this.amountPercent = amountPercent;
	}
	
	public List<BigDecimal> getEmployeeRequestPkIds() {
		if(employeeRequestPkIds == null) employeeRequestPkIds = new ArrayList<>();
		return employeeRequestPkIds;
	}
	
	public void setEmployeeRequestPkIds(List<BigDecimal> employeeRequestPkIds) {
		this.employeeRequestPkIds = employeeRequestPkIds;
	}
	
	public BigDecimal getCardInAmount() {
		if(cardInAmount == null) cardInAmount = BigDecimal.ZERO;
		return cardInAmount;
	}
	
	public void setCardInAmount(BigDecimal cardInAmount) {
		this.cardInAmount = cardInAmount;
		this.cashInAmount = this.paidAmount.subtract(cardInAmount);
	}
	
	public BigDecimal getCashInAmount() {
		if(cashInAmount == null) cashInAmount = BigDecimal.ZERO;
		return cashInAmount;
	}
	
	public void setCashInAmount(BigDecimal cashInAmount) {
		this.cashInAmount = cashInAmount;
		this.cardInAmount = this.paidAmount.subtract(cashInAmount);
	}
	
}

package hospital.report;

import java.math.BigDecimal;

public class ItemReportDtl {

	private BigDecimal itemPkId;
	private String itemName;
	private BigDecimal itemCount;
	private BigDecimal itemPrice;
	private BigDecimal sumAmount;
	private String measurement;
	private BigDecimal employeePkId;
	
	public ItemReportDtl(){
		super();
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public BigDecimal getItemCount() {
		return itemCount == null ? BigDecimal.ZERO : itemCount;
	}
	
	public void setItemCount(BigDecimal itemCount) {
		this.itemCount = itemCount;
	}
	
	public BigDecimal getItemPrice() {
		return itemPrice == null ? BigDecimal.ZERO : itemPrice;
	}
	
	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public BigDecimal getSumAmount() {
		sumAmount = getItemPrice().multiply(getItemCount());
		return sumAmount;
	}
	
	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public BigDecimal getItemPkId() {
		return itemPkId;
	}

	public void setItemPkId(BigDecimal itemPkId) {
		this.itemPkId = itemPkId;
	}
	
	public String getMeasurement() {
		return measurement;
	}
	
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
}

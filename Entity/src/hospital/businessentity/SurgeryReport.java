package hospital.businessentity;

import java.math.BigDecimal;

public class SurgeryReport {
	
	private BigDecimal customerPkId;
	private BigDecimal price;
	
	public SurgeryReport(){
		super();
	}
	
	public SurgeryReport(BigDecimal customerPkId, BigDecimal price){
		super();
		this.customerPkId = customerPkId;
		this.price = price;
	}
	
	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}
	
	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}

package hospital.businessentity;

import java.math.BigDecimal;

public class TreatmentReport {
	
	private BigDecimal customerPkId;
	private BigDecimal price;
	private int doneCount;
	
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

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}
	
	public TreatmentReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TreatmentReport(BigDecimal customerPkId, BigDecimal price) {
		super();
		this.customerPkId = customerPkId;
		this.price = price;
	}

	public TreatmentReport(BigDecimal customerPkId, BigDecimal price, int doneCount) {
		super();
		this.customerPkId = customerPkId;
		this.price = price;
		this.doneCount = doneCount;
	}
}

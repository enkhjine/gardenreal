package hospital.report;

import java.math.BigDecimal;

public class MEReport {

	private static final long serialVersionUID = 1L;
	
	private BigDecimal price;
	private BigDecimal rePrice;
	private int reInspection;
	private int gender;
	private int inspectionType;
	private BigDecimal customerPkId;
	
	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
		//return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getReInspection() {
		return reInspection;
	}
	public void setReInspection(int reInspection) {
		this.reInspection = reInspection;
	}
	public int getGender() {
		
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public MEReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BigDecimal getRePrice() {
		return rePrice == null ? BigDecimal.ZERO : rePrice;
	}
	
	public void setRePrice(BigDecimal rePrice) {
		this.rePrice = rePrice;
	}

	public int getInspectionType() {
		return inspectionType;
	}
	
	public void setInspectionType(int inspectionType) {
		this.inspectionType = inspectionType;
	}
	public MEReport(BigDecimal price, int reInspection, int gender, int inspectionType) {
		super();
		this.price = price;
		this.reInspection = reInspection;
		this.gender = gender;
		this.inspectionType = inspectionType;
	}
	
	public MEReport(BigDecimal price, BigDecimal rePrice, int gender, int inspectionType) {
		super();
		this.price = price;
		this.rePrice = rePrice;
		this.gender = gender;
		this.inspectionType = inspectionType;
	}
	
	public MEReport(BigDecimal price, BigDecimal rePrice, int inspectionType, BigDecimal customerPkId) {
		super();
		this.price = price;
		this.rePrice = rePrice;
		this.customerPkId = customerPkId;
		this.inspectionType = inspectionType;
	}
	
	public MEReport(int gender) {
		super();
		this.gender = gender;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the customerPkId
	 */
	public BigDecimal getCustomerPkId() {
		return customerPkId == null ? BigDecimal.ZERO : customerPkId;
	}
	/**
	 * @param customerPkId the customerPkId to set
	 */
	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}	
}

package hospital.businessentity;

import java.math.BigDecimal;
import java.util.Date;

public class CashierReportMonth {

	private Date date;
	private BigDecimal allAmount;
	private int value1;
	private int value2;
	
	public CashierReportMonth(){
		super();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getAllAmount() {
		return allAmount;
	}

	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	
	public int getValue1() {
		return value1;
	}
	
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	
	public int getValue2() {
		return value2;
	}
	
	public void setValue2(int value2) {
		this.value2 = value2;
	}
	
}

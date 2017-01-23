package hospital.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportMonth {

	private int index;
	private Date date;
	private int firstInspection;
	private int repeatInspection;
	private BigDecimal inAmount1;
	private int customerCount1;
	private BigDecimal inAmount2;
	private int customerCount2;
	private BigDecimal inAmount3;
	private int customerCount3;
	private BigDecimal inAmount4;
	private int customerCount4;
	private BigDecimal inAmount5;
	private int customerCount5;
	private BigDecimal inAmount6;
	private int customerCount6;
	private BigDecimal sumAmount;
	private BigDecimal sumAmount1;
	private BigDecimal sumAmount2;
	private String dateStr;
	
	public ReportMonth(){
		super();
	}

	public ReportMonth(int index, Date date, int firstInspection, int repeatInspection, BigDecimal inAmount1,
			int customerCount1, BigDecimal inAmount2, int customerCount2, BigDecimal inAmount3, int customerCount3,
			BigDecimal inAmount4, int customerCount4, BigDecimal inAmount5, int customerCount5, BigDecimal inAmount6,
			int customerCount6, BigDecimal sumAmount, String dateStr) {
		super();
		this.index = index;
		this.date = date;
		this.firstInspection = firstInspection;
		this.repeatInspection = repeatInspection;
		this.inAmount1 = inAmount1;
		this.customerCount1 = customerCount1;
		this.inAmount2 = inAmount2;
		this.customerCount2 = customerCount2;
		this.inAmount3 = inAmount3;
		this.customerCount3 = customerCount3;
		this.inAmount4 = inAmount4;
		this.customerCount4 = customerCount4;
		this.inAmount5 = inAmount5;
		this.customerCount5 = customerCount5;
		this.inAmount6 = inAmount6;
		this.customerCount6 = customerCount6;
		this.sumAmount = sumAmount;
		this.dateStr = dateStr;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getFirstInspection() {
		return firstInspection;
	}

	public void setFirstInspection(int firstInspection) {
		this.firstInspection = firstInspection;
	}

	public int getRepeatInspection() {
		return repeatInspection;
	}

	public void setRepeatInspection(int repeatInspection) {
		this.repeatInspection = repeatInspection;
	}

	public BigDecimal getInAmount1() {
		return inAmount1 == null ? BigDecimal.ZERO : inAmount1;
	}

	public void setInAmount1(BigDecimal inAmount1) {
		this.inAmount1 = inAmount1;
	}

	public int getCustomerCount1() {
		return customerCount1;
	}

	public void setCustomerCount1(int customerCount1) {
		this.customerCount1 = customerCount1;
	}

	public BigDecimal getInAmount2() {
		return inAmount2 == null ? BigDecimal.ZERO : inAmount2;
	}

	public void setInAmount2(BigDecimal inAmoint2) {
		this.inAmount2 = inAmoint2;
	}

	public int getCustomerCount2() {
		return customerCount2;
	}

	public void setCustomerCount2(int customerCount2) {
		this.customerCount2 = customerCount2;
	}

	public BigDecimal getInAmount3() {
		return inAmount3 == null ? BigDecimal.ZERO : inAmount3;
	}

	public void setInAmount3(BigDecimal inAmoint3) {
		this.inAmount3 = inAmoint3;
	}
	
	public int getCustomerCount3() {
		return customerCount3;
	}

	public void setCustomerCount3(int customerCount3) {
		this.customerCount3 = customerCount3;
	}
	
	public BigDecimal getInAmount4() {
		return inAmount4 == null ? BigDecimal.ZERO : inAmount4;
	}

	public void setInAmount4(BigDecimal inAmount4) {
		this.inAmount4 = inAmount4;
	}

	public int getCustomerCount4() {
		return customerCount4;
	}

	public void setCustomerCount4(int customerCount4) {
		this.customerCount4 = customerCount4;
	}

	public BigDecimal getInAmount5() {
		return inAmount5 == null ? BigDecimal.ZERO : inAmount5;
	}

	public void setInAmount5(BigDecimal inAmount5) {
		this.inAmount5 = inAmount5;
	}

	public int getCustomerCount5() {
		return customerCount5;
	}

	public void setCustomerCount5(int customerCount5) {
		this.customerCount5 = customerCount5;
	}

	public BigDecimal getInAmount6() {
		return inAmount6 == null ? BigDecimal.ZERO : inAmount6;
	}

	public void setInAmount6(BigDecimal inAmount6) {
		this.inAmount6 = inAmount6;
	}

	public int getCustomerCount6() {
		return customerCount6;
	}

	public void setCustomerCount6(int customerCount6) {
		this.customerCount6 = customerCount6;
	}

	public BigDecimal getSumAmount() {
		sumAmount = getInAmount1().add(getInAmount2().add(getInAmount3()
					.add(getInAmount4().add(getInAmount5().add(getInAmount6())))));
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public String getDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		dateStr = sdfr.format(getDate());
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public BigDecimal getSumAmount1() {
		return sumAmount1;
	}
	
	public void setSumAmount1(BigDecimal sumAmount1) {
		this.sumAmount1 = sumAmount1;
	}
	
	public BigDecimal getSumAmount2() {
		return sumAmount2;
	}
	
	public void setSumAmount2(BigDecimal sumAmount2) {
		this.sumAmount2 = sumAmount2;
	}
	
}

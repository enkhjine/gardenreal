package hospital.businessentity;

import hospital.entity.ViewCustomer;
import hospital.report.ItemReportDtl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportFilter {

	private String filteredType;
	private Date beginDate;
	private Date endDate;
	private BigDecimal allAmount;
	private BigDecimal allAmount1;
	private BigDecimal allAmount2;
	private BigDecimal customerPkId;
	private ViewCustomer customer;
	private BigDecimal itemPkId;
	private ItemReportDtl item;
	private String beginDateStr;
	private String endDateStr;
	private BigDecimal subOrganizationPkId;
	private BigDecimal employeePkId;
	private int allCustomerCount1;
	private int allCustomerCount2;
	private int allCustomerCount3;
	private int allCustomerCount4;
	private int allCustomerCount5;
	private int allCustomerCount6;
	private BigDecimal allInAmount1;
	private BigDecimal allInAmount2;
	private BigDecimal allInAmount3;
	private BigDecimal allInAmount4;
	private BigDecimal allInAmount5;
	private BigDecimal allInAmount6;
	private int year;
	private int month;
	private int week;
	private int day;
	
	public ReportFilter(){
		super();
	}
	
	@SuppressWarnings("deprecation")
	public Date getBeginDate() {
		if(beginDate == null) {
			beginDate = new Date();
		}
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@SuppressWarnings("deprecation")
	public Date getEndDate(){
		if(endDate == null) endDate = new Date();
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFilteredType() {
		if(filteredType == null || "".equals(filteredType)) filteredType = "between";
		return filteredType;
	}

	public void setFilteredType(String filteredType) {
		this.filteredType = filteredType;
	}

	public BigDecimal getAllAmount() {
		return allAmount == null ? BigDecimal.ZERO : allAmount;
	}

	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}

	public String getBeginDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		beginDateStr = sdfr.format(getBeginDate());
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public String getEndDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		endDateStr = sdfr.format(getEndDate());
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public ViewCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(ViewCustomer customer) {
		this.customer = customer;
	}
	

	public BigDecimal getItemPkId() {
		return itemPkId;
	}

	public void setItemPkId(BigDecimal itemPkId) {
		this.itemPkId = itemPkId;
	}

	public ItemReportDtl getItem() {
		return item;
	}

	public void setItem(ItemReportDtl item) {
		this.item = item;
	}

	
	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId == null ? BigDecimal.ZERO : subOrganizationPkId;
	}
	
	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId == null ? BigDecimal.ZERO : employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public int getAllCustomerCount1() {
		return allCustomerCount1 == 0 ? 0 : allCustomerCount1;
	}

	public void setAllCustomerCount1(int allCustomerCount1) {
		this.allCustomerCount1 = allCustomerCount1;
	}

	public int getAllCustomerCount2() {
		return allCustomerCount2 == 0 ? 0 : allCustomerCount2;
	}

	public void setAllCustomerCount2(int allCustomerCount2) {
		this.allCustomerCount2 = allCustomerCount2;
	}

	public int getAllCustomerCount3() {
		return allCustomerCount3 == 0 ? 0 : allCustomerCount3;
	}

	public void setAllCustomerCount3(int allCustomerCount3) {
		this.allCustomerCount3 = allCustomerCount3;
	}

	public int getAllCustomerCount4() {
		return allCustomerCount4 == 0 ? 0 : allCustomerCount4;
	}

	public void setAllCustomerCount4(int allCustomerCount4) {
		this.allCustomerCount4 = allCustomerCount4;
	}

	public int getAllCustomerCount5() {
		return allCustomerCount5 == 0 ? 0 : allCustomerCount5;
	}

	public void setAllCustomerCount5(int allCustomerCount5) {
		this.allCustomerCount5 = allCustomerCount5;
	}

	public int getAllCustomerCount6() {
		return allCustomerCount6 == 0 ? 0 : allCustomerCount6;
	}

	public void setAllCustomerCount6(int allCustomerCount6) {
		this.allCustomerCount6 = allCustomerCount6;
	}

	public BigDecimal getAllInAmount1() {
		return allInAmount1 == null ? BigDecimal.ZERO : allInAmount1;
	}

	public void setAllInAmount1(BigDecimal allInAmount1) {
		this.allInAmount1 = allInAmount1;
	}

	public BigDecimal getAllInAmount2() {
		return allInAmount2 == null ? BigDecimal.ZERO : allInAmount2;
	}

	public void setAllInAmount2(BigDecimal allInAmount2) {
		this.allInAmount2 = allInAmount2;
	}

	public BigDecimal getAllInAmount3() {
		return allInAmount3 == null ? BigDecimal.ZERO : allInAmount3;
	}

	public void setAllInAmount3(BigDecimal allInAmount3) {
		this.allInAmount3 = allInAmount3;
	}

	public BigDecimal getAllInAmount4() {
		return allInAmount4 == null ? BigDecimal.ZERO : allInAmount4;
	}

	public void setAllInAmount4(BigDecimal allInAmount4) {
		this.allInAmount4 = allInAmount4;
	}

	public BigDecimal getAllInAmount5() {
		return allInAmount5 == null ? BigDecimal.ZERO : allInAmount5;
	}

	public void setAllInAmount5(BigDecimal allInAmount5) {
		this.allInAmount5 = allInAmount5;
	}

	public BigDecimal getAllInAmount6() {
		return allInAmount6 == null ? BigDecimal.ZERO : allInAmount6;
	}

	public void setAllInAmount6(BigDecimal allInAmount6) {
		this.allInAmount6 = allInAmount6;
	}
	
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMonth() {
		return month;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public int getWeek() {
		return week;
	}
	
	public void setWeek(int week) {
		this.week = week;
	}
	
	public BigDecimal getAllAmount1() {
		return allAmount1;
	}
	
	public void setAllAmount1(BigDecimal allAmount1) {
		this.allAmount1 = allAmount1;
	}
	
	public BigDecimal getAllAmount2() {
		return allAmount2;
	}
	
	public void setAllAmount2(BigDecimal allAmount2) {
		this.allAmount2 = allAmount2;
	}
	
}

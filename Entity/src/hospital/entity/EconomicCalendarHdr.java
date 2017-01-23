package hospital.entity;

import hospital.businessentity.Tool;

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
@Table(name = "EconomicCalendarHdr")
public class EconomicCalendarHdr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	/**
	 * 0 - Tasag
	 * 1 - Ажилтан
	 * **/
	@Column(name = "Type")
	private int type;
	
	@Column(name = "TypePkId")
	private BigDecimal typePkId;
	
	@Column(name = "Name")
	private String name;
	
	/**
	 * NULL - 0 - ALL
	 * 0YYYYYYN - Doloo honog
	 * 1 - sar
	 * 10 - sar odoroor
	 * 11 - sar doloo honogoor
	 * **/
	@Column(name = "ConfigurationStr")
	private String configurationStr;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BeginDate")
	private Date beginDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "EndDate")
	private Date endDate;
	
	/**
	 * 0 - buh odor
	 * 1 - 
	 * **/
	@Column(name = "RateType")
	private int rateType;
	
	@Column(name = "BeginTimeHour")
	private int beginTimeHour;
	
	@Column(name = "BeginTimeMinute")
	private int beginTimeMinute;
	
	@Column(name = "EndTimeHour")
	private int endTimeHour;
	
	@Column(name = "EndTimeMinute")
	private int endTimeMinute;
	
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
	private Date beginTime;
	
	@Transient
	private Date endTime;
	
	@Transient
	private String status;
	
	@Transient
	private boolean rateAll;
	
	@Transient
	private boolean rateNotAll;
	
	@Transient
	private String dateStr;
	
	@Transient
	private String timeBegin;
	
	@Transient
	private String timeEnd;
	
	@Transient
	private List<Employee> employees;
	
	@Transient
	private String subOrganizationName;
	
	@Transient
	private boolean check1;
	
	@Transient
	private boolean check2;
	
	@Transient
	private boolean check3;
	
	@Transient
	private boolean check4;
	
	@Transient
	private boolean check5;
	
	@Transient
	private boolean check6;
	
	@Transient
	private boolean check7;
	
	@Transient
	private long dtlCount;
	
	@Transient
	private int monthBeginDate;
	
	@Transient
	private int monthEndDate;
	
	@Transient
	private int monthIndex;
	
	@Transient
	private boolean ch1;
	
	@Transient
	private boolean ch2;
	
	@Transient
	private boolean ch3;
	
	@Transient
	private boolean ch4;
	
	@Transient
	private boolean ch5;
	
	@Transient
	private boolean ch6;
	
	@Transient
	private boolean ch7;
	
	@Transient
	private boolean chDay;
	
	@Transient
	private boolean chMonth;
	
	@Transient
	private boolean configType1;
	
	@Transient
	private boolean configType2;
	
	public EconomicCalendarHdr(){
		super();
	}
	
	public EconomicCalendarHdr(BigDecimal pkId, long dtlCount){
		this.pkId = pkId;
		this.dtlCount = dtlCount;
	}

	public EconomicCalendarHdr(BigDecimal pkId, BigDecimal organizationPkId,
			int type, BigDecimal typePkId, String name, int rateType, int beginTimeHour, int beginTimeMinute,
			int endTimeHour, int endTimeMinute, long dtlCount) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.type = type;
		this.typePkId = typePkId;
		this.name = name;
		this.rateType = rateType;
		this.beginTimeHour = beginTimeHour;
		this.beginTimeMinute = beginTimeMinute;
		this.endTimeHour = endTimeHour;
		this.endTimeMinute = endTimeMinute;
		this.dtlCount = dtlCount;
	}
	
	public EconomicCalendarHdr(Employee employee, BigDecimal organizationPkId, String subOrganizationName, String name, Date beginDate, Date endDate) {
		super();
		Employee employee2 = (Employee)Tool.deepClone(employee);
		employee2.setFirstName("Үзлэг : " + (subOrganizationName == null ? "" : subOrganizationName));
		this.employees = new ArrayList<>();this.employees.add(employee2);
		this.organizationPkId = organizationPkId;
		this.name = name;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.beginTimeHour = beginDate.getHours();
		this.beginTimeMinute = beginDate.getMinutes();
		this.endTimeHour = endDate.getHours();
		this.endTimeMinute = endDate.getMinutes();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	/**
	 * 0 - Tasag
	 * **/
	public int getType() {
		return type;
	}

	/**
	 * 0 - Tasag
	 * **/
	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getTypePkId() {
		return typePkId;
	}

	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getRateType() {
		return rateType;
	}

	public void setRateType(int rateType) {
		this.rateType = rateType;
	}

	public int getBeginTimeHour() {
		return beginTimeHour;
	}

	public void setBeginTimeHour(int beginTimeHour) {
		this.beginTimeHour = beginTimeHour;
	}

	public int getBeginTimeMinute() {
		return beginTimeMinute;
	}

	public void setBeginTimeMinute(int beginTimeMinute) {
		this.beginTimeMinute = beginTimeMinute;
	}

	public int getEndTimeHour() {
		return endTimeHour;
	}

	public void setEndTimeHour(int endTimeHour) {
		this.endTimeHour = endTimeHour;
	}

	public int getEndTimeMinute() {
		return endTimeMinute;
	}

	public void setEndTimeMinute(int endTimeMinute) {
		this.endTimeMinute = endTimeMinute;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean isRateAll() {
		this.rateAll = !rateNotAll;
		return rateAll;
	}
	
	public void setRateAll(boolean rateAll) {
		this.rateNotAll = !rateAll;
		if(rateAll) setConfigurationStr("");
		this.rateAll = rateAll;
	}
	
	public boolean isRateNotAll() {
		this.rateNotAll = !rateAll;
		return rateNotAll;
	}
	
	public void setRateNotAll(boolean rateNotAll) {
		this.rateAll = !rateNotAll;
		if(this.rateAll) setConfigurationStr("");
		this.rateNotAll = rateNotAll;
	}
	
	public String getDateStr() {
		return dateStr;
	}
	
	@SuppressWarnings("deprecation")
	public void setDateStr(String dateStr) {
		beginDate = new Date();
		endDate = new Date();
		if(dateStr.length() > 10) {
			beginDate.setDate(getTimeInt(dateStr.substring(3, 5)));
			beginDate.setMonth(getTimeInt(dateStr.substring(0, 2)) - 1);
			beginDate.setYear(getTimeInt(dateStr.substring(6, 10)));
			endDate.setDate(getTimeInt(dateStr.substring(16, 18)));
			endDate.setMonth(getTimeInt(dateStr.substring(13, 15)) - 1);
			endDate.setYear(getTimeInt(dateStr.substring(19, 23)));
		}
		this.dateStr = dateStr;
	}
	
	public String getTimeBegin() {
		timeBegin = getTimeStr(beginTimeHour) + ":" + getTimeStr(beginTimeMinute);
		return timeBegin;
	}
	
	public void setTimeBegin(String timeBegin) {
		beginTimeHour = getTimeInt(timeBegin.split(":")[0]);
		beginTimeMinute = getTimeInt(timeBegin.split(":")[1]);
		this.timeBegin = timeBegin;
	}
	
	public String getTimeEnd() {
		timeEnd = getTimeStr(endTimeHour) + ":" + getTimeStr(endTimeMinute);
		return timeEnd;
	}
	
	public void setTimeEnd(String timeEnd) {
		endTimeHour = getTimeInt(timeEnd.split(":")[0]);
		endTimeMinute = getTimeInt(timeEnd.split(":")[1]);
		this.timeEnd = timeEnd;
	}
	
	private String getTimeStr(int i){
		String ret = i < 10 ? "0" : "";
		ret = ret + i;
		return ret;
	}
	
	private int getTimeInt(String str){
		int k = 0;
		for(int i = 0; i < str.length(); i++){
			k = k*10 + Integer.parseInt(str.charAt(i) + "");
		}
		if(k > 1900) k -= 1900;
		return k;
	}
	
	public List<Employee> getEmployees() {
		return employees;
	}
	
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	public String getSubOrganizationName() {
		return subOrganizationName;
	}
	
	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public boolean isCheck1() {
		return check1;
	}

	public void setCheck1(boolean check1) {
		calculateWeekStr();
		this.check1 = check1;
	}

	public boolean isCheck2() {
		return check2;
	}

	public void setCheck2(boolean check2) {
		calculateWeekStr();
		this.check2 = check2;
	}

	public boolean isCheck3() {
		return check3;
	}

	public void setCheck3(boolean check3) {
		calculateWeekStr();
		this.check3 = check3;
	}

	public boolean isCheck4() {
		return check4;
	}

	public void setCheck4(boolean check4) {
		calculateWeekStr();
		this.check4 = check4;
	}

	public boolean isCheck5() {
		return check5;
	}

	public void setCheck5(boolean check5) {
		calculateWeekStr();
		this.check5 = check5;
	}

	public boolean isCheck6() {
		return check6;
	}

	public void setCheck6(boolean check6) {
		calculateWeekStr();
		this.check6 = check6;
	}

	public boolean isCheck7() {
		return check7;
	}

	public void setCheck7(boolean check7) {
		calculateWeekStr();
		this.check7 = check7;
	}
	
	private void calculateWeekStr(){
		setConfigurationStr("0:"+(isCheck1()?"Y":"N")+(isCheck2()?"Y":"N")+(isCheck3()?"Y":"N")+(isCheck4()?"Y":"N")+(isCheck5()?"Y":"N")+(isCheck6()?"Y":"N")+(isCheck7()?"Y":"N"));
	}
	
	public long getDtlCount() {
		return dtlCount;
	}
	
	public void setDtlCount(long dtlCount) {
		this.dtlCount = dtlCount;
	}
	
	public String getConfigurationStr() {
		return configurationStr;
	}
	
	public void setConfigurationStr(String configurationStr) {
		this.configurationStr = configurationStr;
	}
	
	public int getMonthBeginDate() {
		return monthBeginDate;
	}
	
	public void setMonthBeginDate(int monthBeginDate) {
		setConfigurationStr("1:0:"+monthBeginDate+"-"+monthEndDate);
		this.monthBeginDate = monthBeginDate;
	}
	
	public int getMonthEndDate() {
		if(monthBeginDate > monthEndDate) monthEndDate = monthBeginDate;
		return monthEndDate;
	}
	
	public void setMonthEndDate(int monthEndDate) {
		setConfigurationStr("1:0:"+monthBeginDate+"-"+monthEndDate);
		this.monthEndDate = monthEndDate;
	}
	
	public boolean configCalendarByDay(){
		if(getConfigurationStr() == null || getConfigurationStr().length() < 1) return false;
		if(getConfigurationStr().charAt(2) == '0') return false;
		return true;
	}
	
	public boolean configCalendarByWeek(){
		if(getConfigurationStr() == null || getConfigurationStr().length() < 1) return true;
		if(getConfigurationStr().charAt(2) == '1') return false;
		return true;
	}
	
	public int getMonthIndex() {
		if(monthIndex == 0) monthIndex = 1;
		return monthIndex;
	}
	
	public void setMonthIndex(int monthIndex) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.monthIndex = monthIndex;
	}
	
	public boolean isCh1() {
		return ch1;
	}
	
	public void setCh1(boolean ch1) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch1 = ch1;
	}
	
	public boolean isCh2() {
		return ch2;
	}
	
	public void setCh2(boolean ch2) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch2 = ch2;
	}
	
	public boolean isCh3() {
		return ch3;
	}
	
	public void setCh3(boolean ch3) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch3 = ch3;
	}
	
	public boolean isCh4() {
		return ch4;
	}
	
	public void setCh4(boolean ch4) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch4 = ch4;
	}
	
	public boolean isCh5() {
		return ch5;
	}
	
	public void setCh5(boolean ch5) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch5 = ch5;
	}
	
	public boolean isCh6() {
		return ch6;
	}
	
	public void setCh6(boolean ch6) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch6 = ch6;
	}
	
	public boolean isCh7() {
		return ch7;
	}
	
	public void setCh7(boolean ch7) {
		setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.ch7 = ch7;
	}
	
	public boolean isChDay() {
		chDay = getConfigurationStr() == null || getConfigurationStr().length() < 3 || getConfigurationStr().charAt(2) == '0';
		return chDay;
	}
	
	public void setChDay(boolean chDay) {
		if(chDay) setConfigurationStr("1:0:"+getMonthBeginDate()+"-"+getMonthEndDate());
		this.chDay = chDay;
	}
	
	public boolean isChMonth() {
		chMonth = getConfigurationStr() != null && getConfigurationStr().length() > 3 && getConfigurationStr().charAt(2) == '1';
		return chMonth;
	}
	
	public void setChMonth(boolean chMonth) {
		if(chMonth) setConfigurationStr("1:1:"+getMonthIndex()+":"+(isCh1()?"Y":"N")+(isCh2()?"Y":"N")+(isCh3()?"Y":"N")+(isCh4()?"Y":"N")+(isCh5()?"Y":"N")+(isCh6()?"Y":"N")+(isCh7()?"Y":"N"));
		this.chMonth = chMonth;
	}
	
	public boolean isConfigType2() {
		return configType2;
	}
	
	public void setConfigType2(boolean configType2) {
		configType1 = !configType2;
		this.configType2 = configType2;
	}
	
	public boolean isConfigType1() {
		return configType1;
	}
	
	public void setConfigType1(boolean configType1) {
		calculateWeekStr();
		configType2 = !configType1;
		this.configType1 = configType1;
	}
	
	public String getBeginDateStr() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(getBeginDate());
	}
	
	public String getEndDateStr() {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(getEndDate());
	}
	
	public String getTimeStr() {
		return (getBeginTimeHour() < 10 ? "0"+getBeginTimeHour() : getBeginTimeHour()+"") + ":" + (getBeginTimeMinute() < 10 ? "0"+getBeginTimeMinute() : getBeginTimeMinute()+"") + " - " + (getEndTimeHour() < 10 ? "0"+getEndTimeHour() : getEndTimeHour()+"") + ":" + (getEndTimeMinute() < 10 ? "0"+getEndTimeMinute() : getEndTimeMinute()+"");
	}
	
	public Date getBeginTime() {
		if(beginTime == null) {
			beginTime = new Date();
		}
		if(getBeginTimeHour() != 0 || getBeginTimeMinute() != 0) {
			beginTime.setHours(getBeginTimeHour());
			beginTime.setMinutes(getBeginTimeMinute());
		}
		return beginTime;
	}
	
	public void setBeginTime(Date beginTime) {
		beginTimeHour = beginTime.getHours();
		beginTimeMinute = beginTime.getMinutes();
		this.beginTime = beginTime;
	}
	
	public Date getEndTime() {
		if(endTime == null) {
			endTime = new Date();
		}
		if(getEndTimeHour() != 0 || getEndTimeMinute() != 0) {
			endTime.setHours(getEndTimeHour());
			endTime.setMinutes(getEndTimeMinute());
		}
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		endTimeHour = endTime.getHours();
		endTimeMinute = endTime.getMinutes();
		this.endTime = endTime;
	}
	
}

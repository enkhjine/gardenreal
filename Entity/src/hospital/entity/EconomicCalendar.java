package hospital.entity;

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

@Entity
@Table(name = "EconomicCalendar")
public class EconomicCalendar implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "BeginHour")
	private int beginHour;
	
	@Column(name = "BeginMinute")
	private int beginMinute;
	
	@Column(name = "EndHour")
	private int endHour;
	
	@Column(name = "EndMinute")
	private int endMinute;
	
	@Column(name = "LunchBeginHour")
	private int lunchBeginHour;
	
	@Column(name = "LunchBeginMinute")
	private int lunchBeginMinute;
	
	@Column(name = "LunchEndHour")
	private int lunchEndHour;
	
	@Column(name = "LunchEndMinute")
	private int lunchEndMinute;
	
	/**
	 * 0 - Hagas Saind ajillahgui
	 * 1 - Hagas Saind ajilna
	 * **/
	@Column(name = "IsSaturday")
	private int isSaturday;
	
	@Column(name = "SaturDayBeginHour")
	private int saturDayBeginHour;
	
	@Column(name = "SaturDayBeginMinute")
	private int saturDayBeginMinute;
	
	@Column(name = "SaturDayEndHour")
	private int saturDayEndHour;
	
	@Column(name = "SaturdayEndMinute")
	private int saturDayEndMinute;
	
	/**
	 * 0 - Buten Saind ajillahgui
	 * 1 - Buten Saind ajilna
	 * **/
	@Column(name = "IsSunday")
	private int isSunday;
	
	@Column(name = "SunDayBeginHour")
	private int sunDayBeginHour;
	
	@Column(name = "SunDayBeginMinute")
	private int sunDayBeginMinute;
	
	@Column(name = "SunDayEndHour")
	private int sunDayEndHour;
	
	@Column(name = "SundayEndMinute")
	private int sunDayEndMinute;
	
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
	private String workTimeBegin;
	
	@Transient
	private String workTimeEnd;
	
	@Transient
	private String lunchTimeBegin;
	
	@Transient
	private String lunchTimeEnd;
	
	@Transient
	private String satTimeBegin;
	
	@Transient
	private String satTimeEnd;
	
	@Transient
	private String sunTimeBegin;
	
	@Transient
	private String sunTimeEnd;
	
	@Transient
	private Date date;
	
	@Transient
	private boolean saturday;
	
	@Transient
	private boolean sunday;
	
	public EconomicCalendar(){
		super();
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

	public int getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(int beginHour) {
		this.beginHour = beginHour;
	}

	public int getBeginMinute() {
		return beginMinute;
	}

	public void setBeginMinute(int beginMinute) {
		this.beginMinute = beginMinute;
	}

	public int getEndHour() {
		return endHour;
	}

	public void setEndHour(int endHour) {
		this.endHour = endHour;
	}

	public int getEndMinute() {
		return endMinute;
	}

	public void setEndMinute(int endMinute) {
		this.endMinute = endMinute;
	}

	public int getLunchBeginHour() {
		return lunchBeginHour;
	}

	public void setLunchBeginHour(int lunchBeginHour) {
		this.lunchBeginHour = lunchBeginHour;
	}

	public int getLunchBeginMinute() {
		return lunchBeginMinute;
	}

	public void setLunchBeginMinute(int lunchBeginMinute) {
		this.lunchBeginMinute = lunchBeginMinute;
	}

	public int getLunchEndHour() {
		return lunchEndHour;
	}

	public void setLunchEndHour(int lunchEndHour) {
		this.lunchEndHour = lunchEndHour;
	}

	public int getLunchEndMinute() {
		return lunchEndMinute;
	}

	public void setLunchEndMinute(int lunchEndMinute) {
		this.lunchEndMinute = lunchEndMinute;
	}

	public int getIsSaturday() {
		return isSaturday;
	}

	public void setIsSaturday(int isSaturday) {
		this.isSaturday = isSaturday;
	}

	public int getSaturDayBeginHour() {
		return saturDayBeginHour;
	}

	public void setSaturDayBeginHour(int saturDayBeginHour) {
		this.saturDayBeginHour = saturDayBeginHour;
	}

	public int getSaturDayBeginMinute() {
		return saturDayBeginMinute;
	}

	public void setSaturDayBeginMinute(int saturDayBeginMinute) {
		this.saturDayBeginMinute = saturDayBeginMinute;
	}

	public int getSaturDayEndHour() {
		return saturDayEndHour;
	}

	public void setSaturDayEndHour(int saturDayEndHour) {
		this.saturDayEndHour = saturDayEndHour;
	}

	public int getSaturDayEndMinute() {
		return saturDayEndMinute;
	}

	public void setSaturDayEndMinute(int saturdayEndMinute) {
		this.saturDayEndMinute = saturdayEndMinute;
	}

	public int getIsSunday() {
		return isSunday;
	}

	public void setIsSunday(int isSunday) {
		this.isSunday = isSunday;
	}

	public int getSunDayBeginHour() {
		return sunDayBeginHour;
	}

	public void setSunDayBeginHour(int sunDayBeginHour) {
		this.sunDayBeginHour = sunDayBeginHour;
	}

	public int getSunDayBeginMinute() {
		return sunDayBeginMinute;
	}

	public void setSunDayBeginMinute(int sunDayBeginMinute) {
		this.sunDayBeginMinute = sunDayBeginMinute;
	}

	public int getSunDayEndHour() {
		return sunDayEndHour;
	}

	public void setSunDayEndHour(int sunDayEndHour) {
		this.sunDayEndHour = sunDayEndHour;
	}

	public int getSunDayEndMinute() {
		return sunDayEndMinute;
	}

	public void setSunDayEndMinute(int sundayEndMinute) {
		this.sunDayEndMinute = sundayEndMinute;
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

	public String getWorkTimeBegin() {
		if(workTimeBegin == null || workTimeBegin.length() < 1){
			workTimeBegin = getStr(this.beginHour) + ":" + getStr(this.beginMinute);
		}
		return workTimeBegin;
	}

	public void setWorkTimeBegin(String workTimeBegin) {
		if(workTimeBegin.length() > 4){
			this.beginHour = getTime(workTimeBegin);
			this.beginMinute = getMinute(workTimeBegin);
		}
	}

	public String getWorkTimeEnd() {
		if(workTimeEnd == null || workTimeEnd.length() < 1){
			workTimeEnd = getStr(this.endHour) + ":" + getStr(this.endMinute);
		}
		return workTimeEnd;
	}

	public void setWorkTimeEnd(String workTimeEnd) {
		if(workTimeEnd.length() > 4){
			this.endHour = getTime(workTimeEnd);
			this.endMinute = getMinute(workTimeEnd);
		}
	}

	public String getLunchTimeBegin() {
		if(lunchTimeBegin == null || lunchTimeBegin.length() < 1){
			lunchTimeBegin = getStr(this.lunchBeginHour) + ":" + getStr(this.lunchBeginMinute);
		}
		return lunchTimeBegin;
	}

	public void setLunchTimeBegin(String lunchTimeBegin) {
		if(lunchTimeBegin.length() > 4){
			this.lunchBeginHour = getTime(lunchTimeBegin);
			this.lunchBeginMinute = getMinute(lunchTimeBegin);
		}
	}

	public String getLunchTimeEnd() {
		if(lunchTimeEnd == null || lunchTimeEnd.length() < 1){
			lunchTimeEnd = getStr(this.lunchEndHour) + ":" + getStr(this.lunchEndMinute);
		}
		return lunchTimeEnd;
	}

	public void setLunchTimeEnd(String lunchTimeEnd) {
		if(lunchTimeEnd.length() > 4){
			this.lunchEndHour = getTime(lunchTimeEnd);
			this.lunchEndMinute = getMinute(lunchTimeEnd);
		}
	}

	public String getSatTimeBegin() {
		if(satTimeBegin == null || satTimeBegin.length() < 1){
			satTimeBegin = getStr(this.saturDayBeginHour) + ":" + getStr(this.saturDayBeginMinute);
		}
		return satTimeBegin;
	}

	public void setSatTimeBegin(String satTimeBegin) {
		if(satTimeBegin.length() > 4){
			this.saturDayBeginHour = getTime(satTimeBegin);
			this.saturDayBeginMinute = getMinute(satTimeBegin);
		}
	}

	public String getSatTimeEnd() {
		if(satTimeEnd == null || satTimeEnd.length() < 1){
			satTimeEnd = getStr(this.saturDayEndHour) + ":" + getStr(this.saturDayEndMinute);
		}
		return satTimeEnd;
	}

	public void setSatTimeEnd(String satTimeEnd) {
		if(satTimeEnd.length() > 4){
			this.saturDayEndHour = getTime(satTimeEnd);
			this.saturDayEndMinute = getMinute(satTimeEnd);
		}
	}

	public String getSunTimeBegin() {
		if(sunTimeBegin == null || sunTimeBegin.length() < 1){
			sunTimeBegin = getStr(this.sunDayBeginHour) + ":" + getStr(this.sunDayBeginMinute);
		}
		return sunTimeBegin;
	}

	public void setSunTimeBegin(String sunTimeBegin) {
		if(sunTimeBegin.length() > 4){
			this.sunDayBeginHour = getTime(sunTimeBegin);
			this.sunDayBeginMinute = getMinute(sunTimeBegin);
		}
	}

	public String getSunTimeEnd() {
		if(sunTimeEnd == null || sunTimeEnd.length() < 1){
			sunTimeEnd = getStr(this.sunDayEndHour) + ":" + getStr(this.sunDayEndMinute);
		}
		return sunTimeEnd;
	}

	public void setSunTimeEnd(String sunTimeEnd) {
		if(sunTimeEnd.length() > 4){
			this.sunDayEndHour = getTime(sunTimeEnd);
			this.sunDayEndMinute = getMinute(sunTimeEnd);
		}
	}
	
	public Date getDate() {
		if(date == null) date = new Date();
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getStr(int s){
		return s < 10 ? "0" + s : s + ""; 
	}
	
	public boolean isSaturday() {
		return isSaturday == 1;
	}
	
	public void setSaturday(boolean saturday) {
		this.isSaturday = saturday ? 1 : 0;
	}
	
	public boolean isSunday() {
		return isSunday == 1;
	}
	
	public void setSunday(boolean sunday) {
		this.isSunday = sunday ? 1 : 0;
	}
	
	public int getTime(String time){
		int ret = 0;
		ret = ret * 10 + Integer.parseInt("" + time.charAt(0));
		ret = ret * 10 + Integer.parseInt("" + time.charAt(1));
		return ret;
	}
	
	public int getMinute(String time){
		int ret = 0;
		ret = ret * 10 + Integer.parseInt("" + time.charAt(3));
		ret = ret * 10 + Integer.parseInt("" + time.charAt(4));
		return ret;
	}
	
}

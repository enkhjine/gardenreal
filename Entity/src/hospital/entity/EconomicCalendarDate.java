package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EconomicCalendarDate")
public class EconomicCalendarDate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "HdrPkId")
	private BigDecimal hdrPkId;
	
	@Column(name = "Year")
	private int year;
	
	@Column(name = "Month")
	private int month;
	
	@Column(name = "Day")
	private int day;
	
	@Column(name = "Hour")
	private int hour;
	
	@Column(name = "Minute")
	private int minute;
	
	@Column(name = "Second")
	private int second;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getHdrPkId() {
		return hdrPkId;
	}

	public void setHdrPkId(BigDecimal hdrPkId) {
		this.hdrPkId = hdrPkId;
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

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

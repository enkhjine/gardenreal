package garden.businessentity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Plan {
	
	private Date date;
	private String dateStr;
	private String dateOfWeekStr;
	private String cssStr;
	private List<PlanDtl> dtls;
	private BigDecimal sumIlchleg;
	
	public Plan(){
		super();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getDateOfWeekStr() {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		dateOfWeekStr = "Ням";
		cssStr = "#ccc";
		if(dayOfWeek == 1) {dateOfWeekStr = "Даваа";cssStr = "#f5f5f5";}
		if(dayOfWeek == 2) {dateOfWeekStr = "Мягмар";cssStr = "#f5f5f5";}
		if(dayOfWeek == 3) {dateOfWeekStr = "Лхагва";cssStr = "#f5f5f5";}
		if(dayOfWeek == 4) {dateOfWeekStr = "Пүрэв";cssStr = "#f5f5f5";}
		if(dayOfWeek == 5) {dateOfWeekStr = "Баасан";cssStr = "#f5f5f5";}
		if(dayOfWeek == 6) dateOfWeekStr = "Бямба";
		return dateOfWeekStr;
	}
	
	public void setDateOfWeekStr(String dateOfWeekStr) {
		this.dateOfWeekStr = dateOfWeekStr;
	}
	
	public String getDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = sdf.format(date);
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	
	public String getCssStr() {
		return cssStr;
	}
	
	public void setCssStr(String cssStr) {
		this.cssStr = cssStr;
	}

	public List<PlanDtl> getDtls() {
		if(dtls == null) dtls = new ArrayList<>();
		return dtls;
	}

	public void setDtls(List<PlanDtl> dtls) {
		this.dtls = dtls;
	}

	public BigDecimal getSumIlchleg() {
		this.sumIlchleg = BigDecimal.ZERO;
		for (PlanDtl planDtl : dtls) {
			sumIlchleg = sumIlchleg.add(planDtl.getIlchleg());
		}
		return sumIlchleg;
	}

	public void setSumIlchleg(BigDecimal sumIlchleg) {
		this.sumIlchleg = sumIlchleg;
	}
	
}

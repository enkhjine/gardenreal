package hospital.businessentity;

import java.math.BigDecimal;

public class CalendarItem {
	
	private String name;
	private int beginHour;
	private int beginMinute;
	private int endHour;
	private int endMinute;
	private String classCss;
	private BigDecimal employeePkId;
	private BigDecimal employeeRequestPkId;
	
	public CalendarItem(){
		super();
	}
	
	public double getHeight(){
		double ret = ((getEndHour()*60 + getEndMinute()) - (getBeginHour()*60 + getBeginMinute()));
		//ret = ret / 2;
		ret = 3*ret;
		return ret;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
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

	public String getClassCss() {
		return classCss;
	}

	public void setClassCss(String classCss) {
		this.classCss = classCss;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getEmployeeRequestPkId() {
		if(employeeRequestPkId == null) employeeRequestPkId = BigDecimal.ZERO;
		return employeeRequestPkId;
	}

	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
}

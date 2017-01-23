package hospital.businessentity;

import java.math.BigDecimal;
import java.util.Date;

public class OrderedTreatment {
	BigDecimal pkId;
	String ttName;
	String tname;
	int times;
	int doneCount;
	String employeeName;
	Date orderedDate;

	public OrderedTreatment() {
		super();
	}

	public OrderedTreatment(BigDecimal pkId, String ttName, String tname, int times, int doneCount, String employeeName,
			Date orderedDate) {
		super();
		this.pkId = pkId;
		this.ttName = ttName;
		this.tname = tname;
		this.times = times;
		this.doneCount = doneCount;
		this.employeeName = employeeName;
		this.orderedDate = orderedDate;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getTtName() {
		return ttName;
	}

	public void setTtName(String ttName) {
		this.ttName = ttName;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getOrderedDate() {
		return orderedDate;
	}

	public void setOrderedDate(Date orderedDate) {
		this.orderedDate = orderedDate;
	}

}

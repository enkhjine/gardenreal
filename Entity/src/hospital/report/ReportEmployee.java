package hospital.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ReportEmployee {
	private BigDecimal employeePkId;
	private String lastName;
	private String firstName;
	private List<ReportTreatment> listReportTreatment;
	private BigDecimal sumAmount;
	private long sumCount;
	private BigDecimal value1Amount;
	private long value1Count;
	private BigDecimal value2Amount;
	private long value2Count;
	private BigDecimal value3Amount;
	private long value3Count;
	private BigDecimal value4Amount;
	private long value4Count;
	private BigDecimal value5Amount;
	private long value5Count;
	
	public ReportEmployee(){
		super();
	}
	
	public ReportEmployee(BigDecimal employeePkId, String lastName, String firstName){
		this.employeePkId = employeePkId;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public List<ReportTreatment> getListReportTreatment() {
		if(listReportTreatment == null) listReportTreatment = new ArrayList<>();
		return listReportTreatment;
	}
	
	public void setListReportTreatment(List<ReportTreatment> listReportTreatment) {
		this.listReportTreatment = listReportTreatment;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public long getSumCount() {
		return sumCount;
	}

	public void setSumCount(long sumCount) {
		this.sumCount = sumCount;
	}

	public BigDecimal getValue1Amount() {
		return value1Amount;
	}

	public void setValue1Amount(BigDecimal value1Amount) {
		this.value1Amount = value1Amount;
	}

	public long getValue1Count() {
		return value1Count;
	}

	public void setValue1Count(long value1Count) {
		this.value1Count = value1Count;
	}

	public long getValue2Count() {
		return value2Count;
	}

	public void setValue2Count(long value2Count) {
		this.value2Count = value2Count;
	}

	public BigDecimal getValue2Amount() {
		return value2Amount;
	}

	public void setValue2Amount(BigDecimal value2Amount) {
		this.value2Amount = value2Amount;
	}

	public BigDecimal getValue3Amount() {
		return value3Amount;
	}

	public void setValue3Amount(BigDecimal value3Amount) {
		this.value3Amount = value3Amount;
	}

	public long getValue3Count() {
		return value3Count;
	}

	public void setValue3Count(long value3Count) {
		this.value3Count = value3Count;
	}

	public BigDecimal getValue4Amount() {
		return value4Amount;
	}

	public void setValue4Amount(BigDecimal value4Amount) {
		this.value4Amount = value4Amount;
	}

	public long getValue4Count() {
		return value4Count;
	}

	public void setValue4Count(long value4Count) {
		this.value4Count = value4Count;
	}

	public BigDecimal getValue5Amount() {
		return value5Amount;
	}

	public void setValue5Amount(BigDecimal value5Amount) {
		this.value5Amount = value5Amount;
	}

	public long getValue5Count() {
		return value5Count;
	}

	public void setValue5Count(long value5Count) {
		this.value5Count = value5Count;
	}
	
}

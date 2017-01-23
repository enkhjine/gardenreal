package hospital.report;

import java.math.BigDecimal;

import hospital.businessentity.Tool;

public class ReportTreatment {
	private String treatmentName;
	private BigDecimal typePkId;
	private String type;
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
	
	public ReportTreatment(){
		super();
	}
	
	public ReportTreatment(BigDecimal typePkId, String type){
		if(Tool.INSPECTIONPAYMENT.equals(type)){
			treatmentName = "Эмчийн үзлэг";
		}
		this.typePkId = typePkId;
		this.type = type;
	}
	
	public String getTreatmentName() {
		return treatmentName;
	}
	
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	
	public BigDecimal getTypePkId() {
		return typePkId;
	}
	
	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
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
		if(value1Amount == null) value1Amount = BigDecimal.ZERO;
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
	
	public BigDecimal getValue2Amount() {
		if(value2Amount == null) value2Amount = BigDecimal.ZERO;
		return value2Amount;
	}
	
	public void setValue2Amount(BigDecimal value2Amount) {
		this.value2Amount = value2Amount;
	}
	
	public long getValue2Count() {
		return value2Count;
	}
	
	public void setValue2Count(long value2Count) {
		this.value2Count = value2Count;
	}
	
	public BigDecimal getValue3Amount() {
		if(value3Amount == null) value3Amount = BigDecimal.ZERO;
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
		if(value4Amount == null) value4Amount = BigDecimal.ZERO;
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
		if(value5Amount == null) value5Amount = BigDecimal.ZERO;
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

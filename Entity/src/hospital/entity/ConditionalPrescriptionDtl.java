package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hospital.businessentity.Tool;

@Entity
@Table(name = "ConditionalPrescriptionDtl")
public class ConditionalPrescriptionDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ConditionalPrescriptionPkId")
	private BigDecimal conditionalPrescriptionPkId;

	@Column(name = "ExaminationPkId")
	private BigDecimal examinationPkId;

	@Column(name = "MedicinePkId")
	private BigDecimal medicinePkId;

	@Column(name = "XrayPkId")
	private BigDecimal xrayPkId;

	@Column(name = "DiagnosePkId")
	private BigDecimal diagnosePkId;
	
	@Column(name="TreatmentPkId")
	private BigDecimal treatmentPkId;

	@Column(name = "RepeatType")
	private int repeatType;

	@Column(name = "RepeatCount")
	private int repeatCount;

	@Column(name = "Dose")
	private String dose;

	@Column(name = "ExpireDay")
	private int expireDay;

	@Column(name = "Description")
	private String description;
	
	@Column(name = "Cost")
	private BigDecimal cost;
	
	@Column(name = "M")
	private byte m;
	
	@Column(name = "D")
	private byte d;
	
	@Column(name = "E")
	private byte e;
	
	@Column(name = "N")
	private byte n;	
	
	@Column(name = "Time")
	private int time;
	
	@Column(name = "Day")
	private int day;
	
	@Transient
	private boolean selectM;
	
	@Transient
	private boolean selectD;
	
	@Transient
	private boolean selectE;
	
	@Transient
	private boolean selectN;
	
	@Transient
	private String id;
	
	@Transient
	private String name;

	@Transient
	String medId;

	@Transient
	String medTypeName;

	@Transient
	String medName;

	@Transient
	String exaName;

	@Transient
	String xrayName;

	@Transient
	String diagnoseNameMn;

	@Transient
	String diagnoseNameEn;

	@Transient
	String diagnoseNameRu;

	@Transient
	String diagnoseIcd;
	
	@Transient
	private  String iName;
	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	@Transient
	private String status;
	
	@Transient
	private  String  drugDose;
	@Transient
	private  String displayStr1;
	
	@Transient
	private  String displayStr2;
	
	@Transient 
	private  String treatmentName;

	public ConditionalPrescriptionDtl() {
		super();
	}
	
	public ConditionalPrescriptionDtl(ConditionalPrescriptionDtl dtl, String diagnoseName, String medicineName, String examiniationName, String xrayName){
		super();
		this.pkId = dtl.getPkId();
		this.conditionalPrescriptionPkId = dtl.getConditionalPrescriptionPkId();
		this.examinationPkId = dtl.getExaminationPkId();
		this.medicinePkId = dtl.getMedicinePkId();
		this.xrayPkId = dtl.getXrayPkId();
		this.diagnosePkId = dtl.getDiagnosePkId();
		this.repeatType = dtl.getRepeatType();
		this.repeatCount = dtl.getRepeatCount();
		this.dose = dtl.getDose();
		this.expireDay = dtl.getExpireDay();
		this.description = dtl.getDescription();
		this.cost = dtl.getCost();
		this.name = (diagnoseName == null ? "" : diagnoseName) + (medicineName == null ? "" : medicineName) + (examiniationName == null ? "" : examiniationName) + (xrayName == null ? "" : xrayName); 
	}
	
	public ConditionalPrescriptionDtl(BigDecimal pkId,
			BigDecimal conditionalPrescriptionPkId, BigDecimal examinationPkId,
			BigDecimal medicinePkId, BigDecimal xrayPkId,
			BigDecimal diagnosePkId, int repeatType, int repeatCount,
			String dose, int expireDay, String description, BigDecimal cost, String medId,
			String medTypeName, String medName, String exaName,
			String xrayName, String diagnoseNameMn, String diagnoseNameEn,
			String diagnoseNameRu, String diagnoseIcd,
			byte m, byte d, byte e, byte n, int time, int day) {
		super();
		this.pkId = pkId;
		this.conditionalPrescriptionPkId = conditionalPrescriptionPkId;
		this.examinationPkId = examinationPkId;
		this.medicinePkId = medicinePkId;
		this.xrayPkId = xrayPkId;
		this.diagnosePkId = diagnosePkId;
		this.repeatType = repeatType;
		this.repeatCount = repeatCount;
		this.dose = dose;
		this.expireDay = expireDay;
		this.description = description;
		this.cost = cost;
		this.medId = medId;
		this.medTypeName = medTypeName;
		this.medName = medName;
		this.exaName = exaName;
		this.xrayName = xrayName;
		this.diagnoseNameMn = diagnoseNameMn;
		this.diagnoseNameEn = diagnoseNameEn;
		this.diagnoseNameRu = diagnoseNameRu;
		this.diagnoseIcd = diagnoseIcd;
		
		this.m = m;
		this.d = d;
		this.e = e;
		this.n = n;
		this.time = time;
		this.day = day;
	}
	
	public ConditionalPrescriptionDtl(BigDecimal pkId,
			BigDecimal conditionalPrescriptionPkId, BigDecimal examinationPkId,
			BigDecimal medicinePkId, BigDecimal xrayPkId,
			BigDecimal diagnosePkId, int repeatType, int repeatCount,
			String dose, int expireDay, String description, BigDecimal cost, String medId,
			String medTypeName, String medName, String exaName,
			String xrayName, String diagnoseNameMn, String diagnoseNameEn,
			String diagnoseNameRu, String diagnoseIcd) {
		super();
		this.pkId = pkId;
		this.conditionalPrescriptionPkId = conditionalPrescriptionPkId;
		this.examinationPkId = examinationPkId;
		this.medicinePkId = medicinePkId;
		this.xrayPkId = xrayPkId;
		this.diagnosePkId = diagnosePkId;
		this.repeatType = repeatType;
		this.repeatCount = repeatCount;
		this.dose = dose;
		this.expireDay = expireDay;
		this.description = description;
		this.cost = cost;
		this.medId = medId;
		this.medTypeName = medTypeName;
		this.medName = medName;
		this.exaName = exaName;
		this.xrayName = xrayName;
		this.diagnoseNameMn = diagnoseNameMn;
		this.diagnoseNameEn = diagnoseNameEn;
		this.diagnoseNameRu = diagnoseNameRu;
		this.diagnoseIcd = diagnoseIcd;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}

	public BigDecimal getMedicinePkId() {
		return medicinePkId;
	}

	public void setMedicinePkId(BigDecimal medicinePkId) {
		this.medicinePkId = medicinePkId;
	}

	public BigDecimal getXrayPkId() {
		return xrayPkId;
	}

	public void setXrayPkId(BigDecimal xrayPkId) {
		this.xrayPkId = xrayPkId;
	}

	public BigDecimal getDiagnosePkId() {
		return diagnosePkId;
	}

	public void setDiagnosePkId(BigDecimal diagnosePkId) {
		this.diagnosePkId = diagnosePkId;
	}

	public String getMedName() {
		return medName;
	}

	public void setMedName(String medName) {
		this.medName = medName;
	}

	public String getExaName() {
		return exaName;
	}

	public void setExaName(String exaName) {
		this.exaName = exaName;
	}

	public String getXrayName() {
		return xrayName;
	}

	public void setXrayName(String xrayName) {
		this.xrayName = xrayName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(int repeatType) {
		this.repeatType = repeatType;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public int getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(int expireDay) {
		this.expireDay = expireDay;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMedId() {
		return medId;
	}

	public void setMedId(String medId) {
		this.medId = medId;
	}

	public String getMedTypeName() {
		return medTypeName;
	}

	public void setMedTypeName(String medTypeName) {
		this.medTypeName = medTypeName;
	}

	public String getDiagnoseNameMn() {
		return diagnoseNameMn;
	}

	public void setDiagnoseNameMn(String diagnoseNameMn) {
		this.diagnoseNameMn = diagnoseNameMn;
	}

	public String getDiagnoseNameEn() {
		return diagnoseNameEn;
	}

	public void setDiagnoseNameEn(String diagnoseNameEn) {
		this.diagnoseNameEn = diagnoseNameEn;
	}

	public String getDiagnoseNameRu() {
		return diagnoseNameRu;
	}

	public void setDiagnoseNameRu(String diagnoseNameRu) {
		this.diagnoseNameRu = diagnoseNameRu;
	}

	public String getDiagnoseIcd() {
		return diagnoseIcd;
	}

	public void setDiagnoseIcd(String diagnoseIcd) {
		this.diagnoseIcd = diagnoseIcd;
	}

	public BigDecimal getConditionalPrescriptionPkId() {
		return conditionalPrescriptionPkId;
	}

	public void setConditionalPrescriptionPkId(
			BigDecimal conditionalPrescriptionPkId) {
		this.conditionalPrescriptionPkId = conditionalPrescriptionPkId;

	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getDay() {
		return day;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public byte getM() {
		return m;
	}
	
	public void setM(byte m) {
		this.m = m;
	}
	
	public byte getN() {
		return n;
	}
	
	public void setN(byte n) {
		this.n = n;
	}
	
	public byte getD() {
		return d;
	}
	
	public void setD(byte d) {
		this.d = d;
	}
	
	public byte getE() {
		return e;
	}
	
	public void setE(byte e) {
		this.e = e;
	}
	
	public boolean isSelectM() {
		selectM = getM() == 1;
		return selectM;
	}

	public void setSelectM(boolean selectM) {
		setM((byte)(selectM ? 1 : 0));
		this.selectM = selectM;
	}

	public boolean isSelectD() {
		selectD = getD() == 1;
		return selectD;
	}

	public void setSelectD(boolean selectD) {
		setD((byte)(selectD ? 1 : 0));
		this.selectD = selectD;
	}

	public boolean isSelectE() {
		selectE = getE() == 1;
		return selectE;
	}

	public void setSelectE(boolean selectE) {
		setE((byte)(selectE ? 1 : 0));
		this.selectE = selectE;
	}

	public boolean isSelectN() {
		selectN = getN() == 1;
		return selectN;
	}

	public void setSelectN(boolean selectN) {
		setN((byte)(selectN ? 1 : 0));
		this.selectN = selectN;
	}

	public String getDisplayStr1() {
		if (Tool.LAST.equals(getStatus())) {
			displayStr1= "display:none;";
		}
		else  displayStr1="";
		return displayStr1;
	}

	public void setDisplayStr1(String displayStr1) {
		this.displayStr1 = displayStr1;
	}

	public String getDisplayStr2() {
		if (!Tool.LAST.equals(getStatus())) {
			displayStr2= "display:none;";
		}
		else  displayStr2="";
		return displayStr2;
	}

	public void setDisplayStr2(String displayStr2) {
		this.displayStr2 = displayStr2;
	}

	public String getDrugDose() {
		return drugDose;
	}

	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentPkId) {
		this.treatmentPkId = treatmentPkId;
	}

	public String getTreatmentName() {
		return treatmentName;
	}

	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	

}

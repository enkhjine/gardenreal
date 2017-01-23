package hospital.entity;

import hospital.businessentity.Tool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CustomerMedicine")
public class CustomerMedicine {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "MedicinePkId")
	private BigDecimal medicinePkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "RepeatType")
	private int repeatType;

	@Column(name = "RepeatCount")
	private int repeatCount;

	@Column(name = "Dose")
	private String dose;
	
	@Column(name = "Time")
	private int time;
	
	@Column(name = "Day")
	private int day;

	@Column(name = "ExpireDay")
	private int expireDay;
	
	@Column(name = "M")
	private byte m;
	
	@Column(name = "D")
	private byte d;
	
	@Column(name = "E")
	private byte e;
	
	@Column(name = "N")
	private byte n;

	@Column(name = "MedicineDescription")
	private String medicineDescription;
	
	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Transient
	private String id;
	
	@Transient
	private String name;
	
	@Transient
	private String iName;

	@Transient
	private String measurementName;
	
	@Transient
	private String status;
	
	@Transient
	private boolean selectM;
	
	@Transient
	private boolean selectD;
	
	@Transient
	private boolean selectE;
	
	@Transient
	private boolean selectN;
	
	@Transient
	private boolean select;
	
	@Transient
	private BigDecimal medicineTypePkId;
	
	@Transient
	private String drugDose;
	
	@Transient
	private String displayStr1;
	
	@Transient
	private BigDecimal measurementPkId;
	
	@Transient
	private String displayStr2;
	
	@Transient
	private String medicineType;
	
	@Transient
	private View_ConstantMedicineType constantMedicineType;
	
    @Transient
	private int calcDrugDose;
	
	@Transient
	private int calcDose;
	
	@Transient
	private String calcType;
	
	public CustomerMedicine() {
		super();
	}
	
	public CustomerMedicine(BigDecimal medicinePkId, BigDecimal employeePkId, String id, String name) {
		this.medicinePkId = medicinePkId;
		this.employeePkId = employeePkId;
		this.id = id;
		this.name = name;
		this.repeatType = 0;
		this.repeatCount = 0;
		this.expireDay = 0;
		this.dose = "";
		this.status = Tool.ADDED;
	}
	
	public CustomerMedicine(BigDecimal medicinePkId, String id, String name) {
		this.medicinePkId = medicinePkId;
		this.id = id;
		this.name = name;
		this.repeatType = 0;
		this.repeatCount = 0;
		this.expireDay = 0;
		this.dose = "";
		this.status = Tool.ADDED;
	}
	
	public CustomerMedicine(BigDecimal medicinePkId, String id, String name, String iName) {
		this.medicinePkId = medicinePkId;
		this.id = id;
		this.name = name;
		this.iName = iName;
		this.repeatType = 0;
		this.repeatCount = 0;
		this.expireDay = 0;
		this.dose = "";
		this.status = Tool.ADDED;
	}
	
	public CustomerMedicine(CustomerMedicine customerMedicine, Medicine medicine) {
		super();
		
		this.pkId = customerMedicine.getPkId();
		this.inspectionPkId = customerMedicine.getInspectionPkId();
		this.medicinePkId = customerMedicine.getMedicinePkId();
		this.employeePkId = customerMedicine.getEmployeePkId();
		this.repeatType = customerMedicine.getRepeatType();
		this.repeatCount = customerMedicine.getRepeatCount();
		this.dose = customerMedicine.getDose();
		this.time = customerMedicine.getTime();
		this.day = customerMedicine.getDay();
		this.expireDay = customerMedicine.getExpireDay();
		this.m = customerMedicine.getM();
		this.d = customerMedicine.getD();
		this.e = customerMedicine.getE();
		this.n = customerMedicine.getN();
		this.medicineDescription = customerMedicine.getMeasurementName();
		this.medicineDescription  = customerMedicine.getMedicineDescription();
		this.createdDate = customerMedicine.getCreatedDate();
		this.createdBy = customerMedicine.getCreatedBy();
		this.updatedDate = customerMedicine.getUpdatedDate();
		this.updatedBy = customerMedicine.getUpdatedBy();
		this.name = medicine.getName();
	}
	public CustomerMedicine(CustomerMedicine customerMedicine, Medicine medicine,View_ConstantMedicineType constantMedicineType) {
		super();
		
		this.pkId = customerMedicine.getPkId();
		this.inspectionPkId = customerMedicine.getInspectionPkId();
		this.medicinePkId = customerMedicine.getMedicinePkId();
		this.employeePkId = customerMedicine.getEmployeePkId();
		this.repeatType = customerMedicine.getRepeatType();
		this.repeatCount = customerMedicine.getRepeatCount();
		this.dose = customerMedicine.getDose();
		this.drugDose = medicine.getDrugDose();
		this.time = customerMedicine.getTime();
		this.day = customerMedicine.getDay();
		this.expireDay = customerMedicine.getExpireDay();
		this.m = customerMedicine.getM();
		this.d = customerMedicine.getD();
		this.e = customerMedicine.getE();
		this.n = customerMedicine.getN();
		this.medicineDescription = customerMedicine.getMeasurementName();
		this.medicineDescription  = customerMedicine.getMedicineDescription();
		this.createdDate = customerMedicine.getCreatedDate();
		this.createdBy = customerMedicine.getCreatedBy();
		this.updatedDate = customerMedicine.getUpdatedDate();
		this.updatedBy = customerMedicine.getUpdatedBy();
		this.constantMedicineType  = constantMedicineType;
		this.name = medicine.getName();
		this.iName  =  medicine.getiName();
	}
	
	public CustomerMedicine(CustomerMedicine customerMedicine, String id, String name){
		super();
		this.id = id;
		this.name = name;
		this.pkId = customerMedicine.getPkId();
		this.inspectionPkId = customerMedicine.getInspectionPkId();
		this.medicinePkId = customerMedicine.getMedicinePkId();
		this.employeePkId = customerMedicine.getEmployeePkId();
		this.repeatType = customerMedicine.getRepeatType();
		this.repeatCount = customerMedicine.getRepeatCount();
		this.dose = customerMedicine.getDose();
		this.time = customerMedicine.getTime();
		this.day = customerMedicine.getDay();
		this.expireDay = customerMedicine.getExpireDay();
		this.m = customerMedicine.getM();
		this.d = customerMedicine.getD();
		this.e = customerMedicine.getE();
		this.n = customerMedicine.getN();
		this.medicineDescription = customerMedicine.getMeasurementName();
		this.createdDate = customerMedicine.getCreatedDate();
		this.createdBy = customerMedicine.getCreatedBy();
		this.updatedDate = customerMedicine.getUpdatedDate();
		this.updatedBy = customerMedicine.getUpdatedBy();
		this.select = true;
	}
	
	public CustomerMedicine(CustomerMedicine customerMedicine, String id, String name, String iName, String drugDose, int calcDose, int calcDrugDose, String calcType, BigDecimal typePkId){
		super();
		this.id = id;
		this.name = name;
		this.iName = iName;
		this.pkId = customerMedicine.getPkId();
		this.inspectionPkId = customerMedicine.getInspectionPkId();
		this.medicinePkId = customerMedicine.getMedicinePkId();
		this.employeePkId = customerMedicine.getEmployeePkId();
		this.repeatType = customerMedicine.getRepeatType();
		this.repeatCount = customerMedicine.getRepeatCount();
		this.dose = customerMedicine.getDose();
		this.time = customerMedicine.getTime();
		this.day = customerMedicine.getDay();
		this.drugDose = drugDose;
		this.dose = customerMedicine.getDose();
		this.expireDay = customerMedicine.getExpireDay();
		this.m = customerMedicine.getM();
		this.d = customerMedicine.getD();
		this.e = customerMedicine.getE();
		this.n = customerMedicine.getN();
		this.medicineDescription = customerMedicine.getMeasurementName();
		this.createdDate = customerMedicine.getCreatedDate();
		this.createdBy = customerMedicine.getCreatedBy();
		this.updatedDate = customerMedicine.getUpdatedDate();
		this.updatedBy = customerMedicine.getUpdatedBy();
		this.calcDose = calcDose;
		this.calcDrugDose = calcDrugDose;
		this.calcType = calcType;
		this.select = true;
		this.medicineTypePkId = typePkId;
	}
	
	public CustomerMedicine(ConditionalPrescriptionDtl dtl, String id, String name){
		super();
		this.repeatType = dtl.getRepeatType();
		this.repeatCount = dtl.getRepeatCount();
		this.dose = dtl.getDose();
		this.medicinePkId = dtl.getMedicinePkId();
		this.id = id;
		this.name = name;
		this.select = true;
		this.day = dtl.getDay();
		this.time = dtl.getTime();
		this.m = dtl.getM();
		this.d = dtl.getD();
		this.e = dtl.getE();
		this.n = dtl.getN();
		this.repeatCount = dtl.getRepeatCount();
	}
	
	public CustomerMedicine(ConditionalPrescriptionDtl dtl, String drugDose, BigDecimal measurementPkId, String iname, String id, String name){
		super();
		this.repeatType = dtl.getRepeatType();
		this.repeatCount = dtl.getRepeatCount();
		this.dose = dtl.getDose();
		this.medicinePkId = dtl.getMedicinePkId();
		this.id = id;
		this.name = name;
		this.iName = iname;
		this.select = true;
		this.day = dtl.getDay();
		this.time = dtl.getTime();
		this.m = dtl.getM();
		this.d = dtl.getD();
		this.e = dtl.getE();
		this.n = dtl.getN();
		this.measurementPkId = measurementPkId;
		this.repeatCount = dtl.getRepeatCount();
		this.drugDose = drugDose;
	}
	
	public CustomerMedicine(ConditionalPrescriptionDtl dtl, String drugDose, BigDecimal measurementPkId, String iname, String id, String name, int calcDrugDose, int calcDose, String calcType, BigDecimal medicineTypePkId){
		super();
		this.repeatType = dtl.getRepeatType();
		this.repeatCount = dtl.getRepeatCount();
		this.dose = dtl.getDose();
		this.medicinePkId = dtl.getMedicinePkId();
		this.id = id;
		this.name = name;
		this.iName = iname;
		this.select = true;
		this.day = dtl.getDay();
		this.time = dtl.getTime();
		this.m = dtl.getM();
		this.d = dtl.getD();
		this.e = dtl.getE();
		this.n = dtl.getN();
		this.measurementPkId = measurementPkId;
		this.repeatCount = dtl.getRepeatCount();
		this.drugDose = drugDose;
		this.calcDose = calcDose;
		this.calcDrugDose = calcDrugDose;
		this.calcType = calcType;
		this.medicineTypePkId = medicineTypePkId;
	}
	
	public CustomerMedicine(DoctorRecipeDtl dtl, String id, String name){
		super();
		this.medicinePkId = dtl.getTypePkId();
//		this.qty = dtl.getQty();
//		this.times = dtl.getTimes();
//		this.dayLength = dtl.getDayLength();
		this.id = id;
		this.name = name;
		this.select = true;
	}
	
	public CustomerMedicine(DoctorRecipeDtl dtl, Medicine b){
		super();
		this.medicinePkId = dtl.getTypePkId();
		this.time = dtl.getTimes();
		this.day = dtl.getDayLength();
//		this.qty = dtl.getQty();
//		this.times = dtl.getTimes();
//		this.dayLength = dtl.getDayLength();
		this.id = b.getId();
		this.name = b.getName();
		this.iName = b.getiName();
		this.calcDrugDose = b.getCalcDrugDose();
		this.calcDose = b.getCalcDose();
		this.calcType = b.getCalcType();
		this.drugDose = b.getDrugDose();
		this.dose = b.getDrugDose();
		this.medicineTypePkId = b.getTypePkId();
		this.select = true;
	}
	
	public CustomerMedicine(CustomerMedicine customerMedicine, Medicine medicine, String measurementName) {
		super();
		this.pkId = customerMedicine.getPkId();
		this.inspectionPkId = customerMedicine.getInspectionPkId();
		this.medicinePkId = customerMedicine.getMedicinePkId();
		this.employeePkId = customerMedicine.getEmployeePkId();
		this.repeatType = customerMedicine.getRepeatType();
		this.repeatCount = customerMedicine.getRepeatCount();
		this.dose = customerMedicine.getDose();
		this.time = customerMedicine.getTime();
		this.day = customerMedicine.getDay();
		this.expireDay = customerMedicine.getExpireDay();
		this.m = customerMedicine.getM();
		this.d = customerMedicine.getD();
		this.e = customerMedicine.getE();
		this.n = customerMedicine.getN();
		this.medicineDescription = customerMedicine.getMeasurementName();
		this.createdDate = customerMedicine.getCreatedDate();
		this.createdBy = customerMedicine.getCreatedBy();
		this.updatedDate = customerMedicine.getUpdatedDate();
		this.updatedBy = customerMedicine.getUpdatedBy();
		
		this.name = medicine.getName();
		this.measurementName = measurementName;
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

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}

	public BigDecimal getMedicinePkId() {
		return medicinePkId;
	}

	public void setMedicinePkId(BigDecimal medicinePkId) {
		this.medicinePkId = medicinePkId;
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

	public String getMedicineDescription() {
		return medicineDescription;
	}

	public void setMedicineDescription(String medicineDescription) {
		this.medicineDescription = medicineDescription;
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

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDateString (Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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
	
	public boolean isSelect() {
		return select;
	}
	
	public void setSelect(boolean select) {
		this.select = select;
	}
	
	public String getDisplayStr1() {
		if(Tool.LAST.equals(getStatus())) displayStr1 = "display: none; ";
		else displayStr1 = "";
		return displayStr1;
	}
	
	public void setDisplayStr1(String displayStr1) {
		this.displayStr1 = displayStr1;
	}
	
	public String getDisplayStr2() {
		if(!Tool.LAST.equals(getStatus())) displayStr2 = "display: none; ";
		else displayStr2 = "";
		return displayStr2;
	}
	
	public void setDisplayStr2(String displayStr2) {
		this.displayStr2 = displayStr2;
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
	
	public String getiName() {
		return iName;
	}
	
	public void setiName(String iName) {
		this.iName = iName;
	}
	
	public String getDrugDose() {
		return drugDose;
	}
	
	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}
	
	public BigDecimal getMeasurementPkId() {
		return measurementPkId;
	}
	
	public void setMeasurementPkId(BigDecimal measurementPkId) {
		this.measurementPkId = measurementPkId;
	}
	
	public String getMedicineType() {
		return medicineType;
	}
	
	public void setMedicineType(String medicineType) {
		this.medicineType = medicineType;
	}
	
	public void calcRepeatCountByTimeDay(){
		if(getCalcDose() > 0 && getCalcDrugDose() > 0) {
			int t = 0;
			if(time > 0 && day > 0) t = time*day;
			t = t*getCalcDose();
			repeatCount = 0;
			while(t > 0){
				repeatCount++;
				t -= getCalcDrugDose();
			}
		}else {
			if(time > 0 && day > 0) repeatCount = time*day;
		}
	}
	
	public void calcMDEN(){
		setSelectM(false);
		setSelectD(false);
		setSelectE(false);
		setSelectN(false);
		medicineDescription = "";
		if(time == 1) {
			setSelectM(true);
			setSelectD(false);
			setSelectE(false);
			setSelectN(false);
			medicineDescription = "Өглөө ";
		}
		if(time == 2) {
			setSelectM(true);
			setSelectD(false);
			setSelectE(true);
			setSelectN(false);
			medicineDescription = "Өглөө, Шөнө ";
		}
		if(time == 3) {
			setSelectM(true);
			setSelectD(true);
			setSelectE(true);
			setSelectN(false);
			medicineDescription = "Өглөө, Өдөр, Шөнө ";
		}
		if(time >= 4) {
			setSelectM(true);
			setSelectD(true);
			setSelectE(true);
			setSelectN(true);
			medicineDescription = "Өглөө, Өдөр, Орой, Шөнө ";
		}
		medicineDescription+= time + " удаа";
	}

	public View_ConstantMedicineType getConstantMedicineType() {
		return constantMedicineType;
	}

	public void setConstantMedicineType(View_ConstantMedicineType constantMedicineType) {
		this.constantMedicineType = constantMedicineType;}
	public int getCalcDrugDose() {
		return calcDrugDose;
	}

	public void setCalcDrugDose(int calcDrugDose) {
		this.calcDrugDose = calcDrugDose;
	}

	public int getCalcDose() {
		return calcDose;
	}

	public void setCalcDose(int calcDose) {
		this.calcDose = calcDose;
	}

	public String getCalcType() {
		return calcType;
	}

	public void setCalcType(String calcType) {
		this.calcType = calcType;
	}
	
	public BigDecimal getMedicineTypePkId() {
		return medicineTypePkId;
	}
	
	public void setMedicineTypePkId(BigDecimal medicineTypePkId) {
		this.medicineTypePkId = medicineTypePkId;
	}
	
}
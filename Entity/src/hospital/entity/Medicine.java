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
@Table(name = "Medicine")
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "Id", unique = true)
	private String id;

	@Column(name = "Name")
	private String name;

	@Column(name = "IName")
	private String iName;

	@Column(name = "ATCPkId")
	private BigDecimal atcPkId;

	@Column(name = "TypePkId")
	private BigDecimal typePkId;

	@Column(name = "MeasurementPkId")
	private BigDecimal measurementPkId;

	@Column(name = "BioActive")
	private byte bioActive;
	/*1-A, 2-B, 3-C, 4-D, 5-X*/

	@Column(name = "MinAge")
	private int minAge;

	@Column(name = "MaxAge")
	private int maxAge;

	@Column(name = "WarningMessage")
	private String warningMessage;
	
	@Column(name = "DrugDose")
	private String drugDose;
	
	@Column(name = "DayDose")
	private String dayDose;
	
	@Column(name = "Dose")
	private String dose;

	@Column(name = "Description")
	private String description;
	
	@Column(name = "CalcDrugDose")
	private int calcDrugDose;
	
	@Column(name = "CalcDose")
	private int calcDose;
	
	@Column(name = "CalcType")
	private String calcType;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	Date createdDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	Date updatedDate;

	@Transient
	String status;

	@Transient
	String atcName;

	@Transient
	String typeName;

	@Transient
	String measurementName;

	@Transient
	String bioActiveName;

	@Transient
	boolean activeAllAge;
	
	@Transient
	String ageDimension;

	public Medicine() {
		super();
	}

	public Medicine(BigDecimal pkId, String id, String name, String iName,
			BigDecimal atcPkId, BigDecimal typePkId,
			BigDecimal measurementPkId, byte bioActive, int minAge, int maxAge,
			String warningMessage, String description, String atcName,
			String typeName, String measurementName) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.name = name;
		this.iName = iName;
		this.atcPkId = atcPkId;
		this.typePkId = typePkId;
		this.measurementPkId = measurementPkId;
		this.bioActive = bioActive;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.warningMessage = warningMessage;
		this.description = description;
		this.atcName = atcName;
		this.typeName = typeName;
		this.measurementName = measurementName;
	}
	
	public Medicine(BigDecimal pkId, String id, String name, String iName,
			BigDecimal atcPkId, BigDecimal typePkId,
			BigDecimal measurementPkId, byte bioActive, int minAge, int maxAge,
			String warningMessage, String description, String atcName,
			String typeName, String measurementName, String drugDose, String dayDose, String dose
			, int calcDose, int calcDrugDose, String calcType) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.name = name;
		this.iName = iName;
		this.atcPkId = atcPkId;
		this.typePkId = typePkId;
		this.measurementPkId = measurementPkId;
		this.bioActive = bioActive;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.warningMessage = warningMessage;
		this.description = description;
		this.atcName = atcName;
		this.typeName = typeName;
		this.measurementName = measurementName;
		this.drugDose = drugDose;
		this.dayDose = dayDose;
		this.dose = dose;
		this.calcDose = calcDose;
		this.calcDrugDose = calcDrugDose;
		this.calcType = calcType;
	}
	
	public Medicine(BigDecimal pkId, String id, String name, String iName,
			BigDecimal atcPkId, BigDecimal typePkId,
			BigDecimal measurementPkId, byte bioActive, int minAge, int maxAge,
			String warningMessage, String description, String atcName,
			String typeName, String measurementName, String drugDose, String dayDose, String dose) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.name = name;
		this.iName = iName;
		this.atcPkId = atcPkId;
		this.typePkId = typePkId;
		this.measurementPkId = measurementPkId;
		this.bioActive = bioActive;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.warningMessage = warningMessage;
		this.description = description;
		this.atcName = atcName;
		this.typeName = typeName;
		this.measurementName = measurementName;
		this.drugDose = drugDose;
		this.dayDose = dayDose;
		this.dose = dose;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
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

	public String getiName() {
		return iName;
	}

	public void setiName(String iName) {
		this.iName = iName;
	}

	public BigDecimal getAtcPkId() {
		return atcPkId;
	}

	public void setAtcPkId(BigDecimal atcPkId) {
		this.atcPkId = atcPkId;
	}

	public BigDecimal getTypePkId() {
		return typePkId;
	}

	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}

	public BigDecimal getMeasurementPkId() {
		return measurementPkId;
	}

	public void setMeasurementPkId(BigDecimal measurementPkId) {
		this.measurementPkId = measurementPkId;
	}

	public byte getBioActive() {
		return bioActive;
	}

	public void setBioActive(byte bioActive) {
		this.bioActive = bioActive;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public String getWarnigMessage() {
		return warningMessage;
	}

	public void setWarnigMessage(String warnigMessage) {
		this.warningMessage = warnigMessage;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAtcName() {
		return atcName;
	}

	public void setAtcName(String atcName) {
		this.atcName = atcName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMeasurementName() {
		return measurementName;
	}

	public void setMeasurementName(String measurementName) {
		this.measurementName = measurementName;
	}

	public String getBioActiveName() {
		if(getBioActive() == '1' || getBioActive() == 1)
			bioActiveName = "A";
		if(getBioActive() == '2' || getBioActive() == 2)
			bioActiveName = "B";
		if(getBioActive() == '3' || getBioActive() == 3)
			bioActiveName = "C";
		if(getBioActive() == '4' || getBioActive() == 4)
			bioActiveName = "D";
		if(getBioActive() == '5' || getBioActive() == 5)
			bioActiveName = "X";
		
		
		return bioActiveName;
	}

	public void setBioActiveName(String bioActiveName) {
		
			
		this.bioActiveName = bioActiveName;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isActiveAllAge() {
		if(getMaxAge() == 150 && getMinAge() == 0)
			return true;
		return activeAllAge;
	}

	public void setActiveAllAge(boolean activeAllAge) {
		this.activeAllAge = activeAllAge;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}
	
	public String getAgeDimension() {
		if(getMinAge()  == 0 && getMaxAge() == 150)
			ageDimension = "Бүх насныханд";
		if(getMaxAge() == 150 && getMinAge()>0)
			ageDimension = getMinAge() + " наснаас дээш";
		if(getMinAge() == 0 && getMaxAge()<150)
			ageDimension = getMaxAge() + " наснаас доош";
		return ageDimension;
	}
	public void setAgeDimension(String ageDimension) {
		this.ageDimension = ageDimension;
	}
	
	public String getDayDose() {
		return dayDose;
	}
	
	public void setDayDose(String dayDose) {
		this.dayDose = dayDose;
	}
	
	public String getDrugDose() {
		return drugDose;
	}
	
	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}
	
	public String getDose() {
		return dose;
	}
	
	public void setDose(String dose) {
		this.dose = dose;
	}

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

}

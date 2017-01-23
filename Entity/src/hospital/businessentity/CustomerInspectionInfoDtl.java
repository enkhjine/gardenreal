package hospital.businessentity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hospital.entity.*;

public class CustomerInspectionInfoDtl implements Comparable {
	private String type;
	private String typeName;
	private String name;
	private String measurement;
	private int expireDay;
	private String dose;
	private int repeatTime;
	private int repeatCount;
	private BigDecimal inspectionPkId;
	private int sortId;
	
	public CustomerInspectionInfoDtl() {
		super();
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
		if("DIAGNOSE".equals(type)) {
			this.typeName = "Онош";
			this.sortId = 1;
		} else if("EXAMINATION".equals(type)) {
			this.typeName = "Шинжилгээ";
			this.sortId = 2;
		} else if("MEDICINE".equals(type)) {
			this.typeName = "Эм";
			this.sortId = 3;
		} else if("TREATMENT".equals(type)) {
			this.typeName = "Эмчилгээ";
			this.sortId = 4;
		} else if("XRAY".equals(type)) {
			this.typeName = "Оношилгоо";
			this.sortId = 5;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public int getExpireDay() {
		return expireDay;
	}

	public void setExpireDay(int expireDay) {
		this.expireDay = expireDay;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public int getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(int repeatTime) {
		this.repeatTime = repeatTime;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	@Override
	public int compareTo(Object dtl) {
		// TODO Auto-generated method stub
		return this.sortId - ((CustomerInspectionInfoDtl)dtl).getSortId();
	}
}

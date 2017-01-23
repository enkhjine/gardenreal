package hospital.entity;

import hospital.businessentity.Tool;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DoctorRecipeDtl")
public class DoctorRecipeDtl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "DoctorRecipePkId")
	private BigDecimal doctorRecipePkId;
	
	/**
	 * XRAY
	 * EXAMINATION
	 * TREATMENT
	 * SURGERY
	 * MEDICINE
	 * */
	@Column(name = "Type")
	private String type;
	
	@Column(name = "TypePkId")
	private BigDecimal typePkId;
	
	@Column(name = "Qty")
	private String qty;
	
	@Column(name = "Times")
	private int times;
	
	@Column(name= "DayLength")
	private int dayLength;
	
	@Transient
	private String name;
	
	@Transient
	private BigDecimal cost;
	
	@Transient
	private String status;
	
	public DoctorRecipeDtl(){
		super();
	}
	
	public DoctorRecipeDtl(BigDecimal pkId, BigDecimal inspectionPkId,
			BigDecimal treatmentPkId, BigDecimal xrayPkId,
			BigDecimal toothPkId, BigDecimal cost,
			String treatmentName, String xrayName) {
		super();
		this.pkId = pkId;
		this.status = Tool.UNCHANGED;
	}
	
	public DoctorRecipeDtl(DoctorRecipeDtl dtl, String treatmentName, String xrayName, String exaName, String surgeryName, String medicineName) {
		super();
		this.pkId = dtl.getPkId();
		this.type = dtl.getType();
		this.typePkId = dtl.getTypePkId();
		this.qty = dtl.getQty();
		this.times = dtl.getTimes();
		this.dayLength = dtl.getDayLength();
		if("XRAY".equals(dtl.getType()))
			this.name = xrayName;
		else if("TREATMENT".equals(dtl.getType()))
			this.name = treatmentName;
		else if("EXAMINATION".equals(dtl.getType()))
			this.name = exaName;
		else if("SURGERY".equals(dtl.getType()))
			this.name = surgeryName;
		else if("MEDICINE".equals(dtl.getType()))
			this.name = medicineName;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTypePkId() {
		return typePkId;
	}

	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	public int getDayLength() {
		return dayLength;
	}

	public void setDayLength(int dayLength) {
		this.dayLength = dayLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getDoctorRecipePkId() {
		return doctorRecipePkId;
	}

	public void setDoctorRecipePkId(BigDecimal doctorRecipePkId) {
		this.doctorRecipePkId = doctorRecipePkId;
	}
}
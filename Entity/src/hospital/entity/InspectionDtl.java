package hospital.entity;

import hospital.businessentity.Tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "InspectionDtl")
public class InspectionDtl implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	/**
	 * XRAY
	 * EXAMINATION
	 * TREATMENT
	 * SURGERY
	 * */
	@Column(name = "Type")
	private String type;
	
	@Column(name = "TypePkId")
	private BigDecimal typePkId;
	
	@Column(name = "Used")
	private int used;
	
	@Column(name = "Qty")
	private String qty;
	
	@Column(name = "Times")
	private int times;
	
	@Column(name= "DayLength")
	private int dayLength;
	
	@Column(name = "Description")
	private String description;
	
	@Column(name = "Organ")
	private String organ;
	
	@Transient
	private String id;
	
	@Transient
	private String name;
	
	@Transient
	private BigDecimal cost;
	
	@Transient
	private boolean done;
	
	@Transient
	private String status;
	
	@Transient
	private boolean select;
	
	@Transient
	private String displayStr1;
	
	@Transient
	private String displayStr2;
	
	@Transient
	private int doneCount;
	
	@Transient
	private String typeName;
	
	@Transient
	private String doctorName;
	
	@Transient
	private Date inspectionDate;
	
	@Transient
	private Date labTestDate;
	
	@Transient
	private List<ExaminationDtl> examinationDtls;
	
	@Transient
	private String ocsListType;
	
	@Transient
	private BigDecimal examinationDtlsSumPrice;
	
	public InspectionDtl(){
		super();
	}
	
	public InspectionDtl(String type, BigDecimal typePkId, String bid, String bname, String cid, String cname, String did, String dname, String eid, String ename) {
		//xaxaxa
		super();
		this.type = type;
		this.typePkId = typePkId;
		if(Tool.INSPECTIONTYPE_TREATMENT.equals(type)){
			this.id = bid;
			this.name = bname;
		}else if(Tool.INSPECTIONTYPE_XRAY.equals(type)){
			this.id = cid;
			this.name = cname;
		}else if(Tool.INSPECTIONTYPE_SURGERY.equals(type)){
			this.id = did;
			this.name = dname;
		}else if(Tool.INSPECTIONTYPE_EXAMINATION.equals(type)){
			this.id = eid;
			this.name = ename;
		}else {
			this.id = (bid == null ? "" : bid) + (cid == null ? "" : cid) + (did == null ? "" : did) + (eid == null ? "" : eid);
			this.name = (bname == null ? "" : bname) + (cname == null ? "" : cname) + (dname == null ? "" : dname) + (ename == null ? "" : ename);
		}
		
	}
	
	public InspectionDtl(String type, BigDecimal typePkId, String id, String name) {
		super();
		this.type = type;
		this.typePkId = typePkId;
		this.id = id;
		this.name = name;
	}
	public InspectionDtl(BigDecimal pkId, BigDecimal inspectionPkId,
			BigDecimal treatmentPkId, BigDecimal xrayPkId,
			BigDecimal toothPkId, BigDecimal cost,
			String treatmentName, String xrayName) {
		super();
		this.pkId = pkId;
		this.inspectionPkId = inspectionPkId;
		this.status = Tool.UNCHANGED;
	}
	
	public InspectionDtl(BigDecimal pkId, String id, String name, int type) {
		this.typePkId = pkId;
		this.id = id;
		this.name = name;
		this.status = Tool.ADDED;
		if(type == 1) this.type = Tool.INSPECTIONTYPE_EXAMINATION;
		if(type == 2) this.type = Tool.INSPECTIONTYPE_SURGERY;
		if(type == 3) this.type = Tool.INSPECTIONTYPE_TREATMENT;
		if(type == 4) this.type = Tool.INSPECTIONTYPE_XRAY;
	}
	
	public InspectionDtl(InspectionDtl dtl, String treatmentName, String xrayName, String exaName, String surgeryName) {
		super();
		this.pkId = dtl.getPkId();
		this.inspectionPkId = dtl.getInspectionPkId();
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
		this.organ = dtl.getOrgan();
	}
	
	public InspectionDtl(InspectionDtl dtl, String treatmentName, String xrayName, String exaName, String surgeryName, String treatmentId, String xrayId, String exaId, String surgeryId) {
		super();
		this.pkId = dtl.getPkId();
		this.inspectionPkId = dtl.getInspectionPkId();
		this.type = dtl.getType();
		this.typePkId = dtl.getTypePkId();
		this.qty = dtl.getQty();
		this.times = dtl.getTimes();
		this.dayLength = dtl.getDayLength();
		if("XRAY".equals(dtl.getType())){
			this.id = xrayId;
			this.name = xrayName;
		}else if("TREATMENT".equals(dtl.getType())){
			this.id = treatmentId;
			this.name = treatmentName;
		}else if("EXAMINATION".equals(dtl.getType())){
			this.id = exaId;
			this.name = exaName;
		}else if("SURGERY".equals(dtl.getType())){
			this.id = surgeryId;
			this.name = surgeryName;
		}
	}
	
	public InspectionDtl(InspectionDtl dtl, String id, String name){
		super();
		this.pkId = dtl.getPkId();
		this.inspectionPkId = dtl.getInspectionPkId();
		this.type = dtl.getType();
		this.typePkId = dtl.getTypePkId();
		this.qty = dtl.getQty();
		this.times = dtl.getTimes();
		this.dayLength = dtl.getDayLength();
		this.id = id;
		this.name = name;
		this.select = true;
		this.description = dtl.getDescription();
	}
	
	public InspectionDtl(InspectionDtl dtl, String treatmentName, String treatmentTypeName, Date inspectionDate, int doneCount, String doctorName){
		super();
		this.pkId = dtl.getPkId();
		this.inspectionPkId = dtl.getInspectionPkId();
		this.type = dtl.getType();
		this.typePkId = dtl.getTypePkId();
		this.qty = dtl.getQty();
		this.times = dtl.getTimes();
		this.dayLength = dtl.getDayLength();
		this.name = treatmentName;
		this.typeName = treatmentTypeName;
		this.inspectionDate = inspectionDate;
		this.doneCount = doneCount;
		this.doctorName = doctorName;
	}
	
	public InspectionDtl(DoctorRecipeDtl dtl, String id, String name){
		super();
		this.pkId = dtl.getPkId();
		this.type = dtl.getType();
		this.typePkId = dtl.getTypePkId();
		this.qty = dtl.getQty();
		this.times = dtl.getTimes();
		this.dayLength = dtl.getDayLength();
		this.id = id;
		this.name = name;
		this.select = true;
	}
	
	public InspectionDtl(ConditionalPrescriptionDtl dtl, String id, String name){
		super();
		dtl.getRepeatType();
		dtl.getRepeatCount();
		dtl.getDose();
		if(dtl.getXrayPkId() != null){
			this.type = Tool.INSPECTIONTYPE_XRAY;
			this.typePkId = dtl.getXrayPkId();
		}else if(dtl.getExaminationPkId() != null){
			this.type = Tool.INSPECTIONTYPE_EXAMINATION;
			this.typePkId = dtl.getExaminationPkId();
		}
		this.description = dtl.getDescription(); 
		this.id = id;
		this.name = name;
		this.select = true;
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

	public int getUsed() {
		return used;
	}

	public void setUsed(int used) {
		this.used = used;
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
		if(cost == null) cost = BigDecimal.ZERO;
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		if(done)
			used = 1;
		this.done = done;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	
	public String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public List<ExaminationDtl> getExaminationDtls() {
		if(examinationDtls == null) examinationDtls = new ArrayList<>();
		return examinationDtls;
	}
	
	public void setExaminationDtls(List<ExaminationDtl> examinationDtls) {
		this.examinationDtls = examinationDtls;
	}
	
	public Date getLabTestDate() {
		if(labTestDate == null) labTestDate = new Date();
		return labTestDate;
	}
	
	public void setLabTestDate(Date labTestDate) {
		this.labTestDate = labTestDate;
	}

	public String getOcsListType() {
		ocsListType = Tool.INSPECTION;
		return ocsListType;
	}

	public void setOcsListType(String ocsListType) {
		this.ocsListType = ocsListType;
	}
	
	public BigDecimal getExaminationDtlsSumPrice() {
		if(examinationDtls != null && examinationDtls.size() > 0) {
			examinationDtlsSumPrice = BigDecimal.ZERO;
			for(ExaminationDtl dtl : examinationDtls) {
				examinationDtlsSumPrice = examinationDtlsSumPrice.add(dtl.getPrice());
			}
		}
		if(examinationDtlsSumPrice == null) examinationDtlsSumPrice = BigDecimal.ZERO;
		return examinationDtlsSumPrice;
	}
	
	public void setExaminationDtlsSumPrice(BigDecimal examinationDtlsSumPrice) {
		this.examinationDtlsSumPrice = examinationDtlsSumPrice;
	}
	
	public String getOrgan() {
		return organ;
	}
	
	public void setOrgan(String organ) {
		this.organ = organ;
	}
}
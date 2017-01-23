package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import hospital.businessentity.Tool;


@Entity
@Table(name = "ExaminationDtl")
public class ExaminationDtl implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "ExaminationPkId")
	private BigDecimal examinationPkId;
	
	@Column(name = "ElementPkId")
	private BigDecimal elementPkId;
	
	@Column(name = "MinValue")
	private BigDecimal minValue;
	
	@Column(name = "MaxValue")
	private BigDecimal maxValue;

	@Column(name = "IsActive")
	private byte isActive;
	
	@Transient
	private String elementNameMn;
	
	@Transient
	private String name;
	
	@Transient
	private String elementNameEn;
	
	@Transient 
	private BigDecimal measurementPkId;
	
	@Transient
	private BigDecimal elementPricePkId;
	
	@Transient
	private String status;
	
	@Transient
	boolean selected;
	
	@Transient
	private BigDecimal price;
	
	@Transient
	private Examination examination;
	
	@Transient
	private String displayStr1;
	
	@Transient
	private String displayStr2;
	
	@Transient
	private String id;
	
	@Transient
	private String description;
	
	@Transient
	private String done;
	
	@Transient
	private String ocsListType;
	
	
	public ExaminationDtl() {
		
	}
	
	public ExaminationDtl(ExaminationDtl dtl,Element element, ElementPrice elementPrice) {
		
		this.pkId = dtl.getPkId();
		this.examinationPkId = dtl.getExaminationPkId();
		this.elementPkId = element.getPkId();
		this.minValue = dtl.getMinValue();
		this.maxValue = dtl.getMaxValue();
		this.measurementPkId = element.getMeasurementPkId();
		
		this.elementNameMn = element.getNameMn();
		this.elementNameEn = element.getNameEn();
		this.name = element.getNameMn();
		this.price = elementPrice.getPrice();
		this.elementPricePkId = elementPrice.getPkId();
	}	
	
	public ExaminationDtl(ExaminationDtl dtl,Element element) {
		
		this.pkId = dtl.getPkId();
		this.examinationPkId = dtl.getExaminationPkId();
		this.elementPkId = element.getPkId();
		this.minValue = dtl.getMinValue();
		this.maxValue = dtl.getMaxValue();
		this.measurementPkId = element.getMeasurementPkId();
		
		this.elementNameMn = element.getNameMn();
		this.elementNameEn = element.getNameEn();
		this.name = element.getNameMn();
//		this.price = elementPrice.getPrice();
//		this.elementPricePkId = elementPrice.getPkId();
	}	
	
	public ExaminationDtl(BigDecimal pkId, BigDecimal examinationPkId,
			BigDecimal elementPkId, String elementNameMn) {
		super();
		this.pkId = pkId;
		this.examinationPkId = examinationPkId;
		this.elementPkId = elementPkId;
		this.elementNameMn = elementNameMn;
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

	public BigDecimal getElementPkId() {
		return elementPkId;
	}

	public void setElementPkId(BigDecimal elementPkId) {
		this.elementPkId = elementPkId;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public String getElementNameMn() {
		return elementNameMn;
	}

	public void setElementNameMn(String elementNameMn) {
		this.elementNameMn = elementNameMn;
	}

	public String getElementNameEn() {
		return elementNameEn;
	}

	public void setElementNameEn(String elementNameEn) {
		this.elementNameEn = elementNameEn;
	}

	public BigDecimal getMeasurementPkId() {
		return measurementPkId;
	}

	public void setMeasurementPkId(BigDecimal measurementPkId) {
		this.measurementPkId = measurementPkId;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getElementPricePkId() {
		return elementPricePkId;
	}

	public void setElementPricePkId(BigDecimal elementPricePkId) {
		this.elementPricePkId = elementPricePkId;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Examination getExamination() {
		return examination;
	}
	
	public void setExamination(Examination examination) {
		this.examination = examination;
	}
	
	public String getDisplayStr1() {
		displayStr1 = "";
		return displayStr1;
	}
	
	public void setDisplayStr1(String displayStr1) {
		this.displayStr1 = displayStr1;
	}
	
	public String getDisplayStr2() {
		displayStr2 = "display: none; ";
		return displayStr2;
	}
	
	public void setDisplayStr2(String displayStr2) {
		this.displayStr2 = displayStr2;
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

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public String getOcsListType() {
		ocsListType = Tool.INSPECTIONTYPE_EXAMINATION;
		return ocsListType;
	}

	public void setOcsListType(String ocsListType) {
		this.ocsListType = ocsListType;
	}

}

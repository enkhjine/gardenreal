package hospital.businessentity;

import hospital.entity.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TreatmentTypeTreatment {
	BigDecimal treatmentPkId;
	public Treatment treatment;
	public TreatmentType treatmentType;
	public TreatmentDtl treatmentDtl;
	BigDecimal treatmentTypePkId;
	BigDecimal treatmentDtlPkId;
	private String itemCountStr;
	private BigDecimal price;
	private Date usageDate;
	private String activeStatus;

	private boolean isTreatment;

	private String name;

	public BigDecimal getTreatmentPkId() {
		return treatmentPkId;
	}

	public void setTreatmentPkId(BigDecimal treatmentPkId) {
		this.treatmentPkId = treatmentPkId;
	}

	public BigDecimal getTreatmentTypePkId() {
		return treatmentTypePkId;
	}

	public void setTreatmentTypePkId(BigDecimal treatmentTypePkId) {
		this.treatmentTypePkId = treatmentTypePkId;
	}

	public BigDecimal getTreatmentDtlPkId() {
		return treatmentDtlPkId;
	}

	public void setTreatmentDtlPkId(BigDecimal treatmentDtlPkId) {
		this.treatmentDtlPkId = treatmentDtlPkId;
	}

	public TreatmentTypeTreatment(BigDecimal treatmentPkId,
			BigDecimal treatmentTypePkId, BigDecimal treatmentDtlPkId) {
		super();
		this.treatmentPkId = treatmentPkId;
		this.treatmentTypePkId = treatmentTypePkId;
		this.treatmentDtlPkId = treatmentDtlPkId;
	}

	public TreatmentTypeTreatment() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Treatment getTreatment() {
		if (treatment == null)
			treatment = new Treatment();
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public TreatmentType getTreatmentType() {
		if (treatmentType == null) {
			treatmentType = new TreatmentType();
		}
		return treatmentType;
	}

	public void setTreatmentType(TreatmentType treatmentType) {
		this.treatmentType = treatmentType;
	}

	public TreatmentDtl getTreatmentDtl() {
		if (treatmentDtl == null)
			treatmentDtl = new TreatmentDtl();
		return treatmentDtl;
	}

	public void setTreatmentDtl(TreatmentDtl treatmentDtl) {
		this.treatmentDtl = treatmentDtl;
	}

	public String getItemCountStr() {
		String ret = "";
		if (isTreatment)
			ret = this.itemCountStr + " Ширхэг бараа";
		return ret;
	}

	public void setItemCountStr(String itemCountStr) {
		this.itemCountStr = itemCountStr;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUsageDate() {

		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
	}

	public String usageDate() {
		String ret = "";
		if (isTreatment && usageDate != null)
			ret = new SimpleDateFormat("dd-MM-yyyy").format(this.usageDate);
		   


		return ret;
	}

	public String price() {
		String ret = "";
		if (isTreatment)
			ret = this.price + "";
		return ret;
	}

	public boolean isTreatment() {
		return isTreatment;
	}

	public void setTreatment(boolean isTreatment) {
		this.isTreatment = isTreatment;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

}

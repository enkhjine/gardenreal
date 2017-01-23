package hospital.businessentity;

import hospital.annotation.Label;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerEar implements Serializable {

	private static final long serialVersionUID = 1L;
	// Ó¨Ð²Ñ‡Ð½Ð¸Ð¹ Ñ‚Ò¯Ò¯Ñ…
	@Label(label = "symptomDate", labelType = "m")
	public Date symptomDate;

	@Label(label = "symptomDuration", labelType = "m")
	public String symptomDuration;

	@Label(label = "usedMedicine", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int usedMedicine;

	@Label(label = "earUsedMedicine", labelType = "m")
	public String earUsedMedicine;

	@Label(label = "earDischarge", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int earDischarge;

	@Label(label = "childUsedMedicine", labelType = "m")
	public String childUsedMedicine;

	@Label(label = "earDischargeOccurance", labelType = "m")
	public int earDischargeOccurance;

	@Label(label = "usedVaccine", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int usedVaccine;

	@Label(label = "childHardInfectiousDisease", labelType = "m")
	public String childHardInfectiousDisease;

	@Label(label = "headInjury", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int headInjury;

	@Label(label = "otherPain", labelType = "p")
	public String otherPain;

	// Ð—Ð¾Ð²Ð¸ÑƒÑ€
	@Label(label = "earDischargeSize", labelType = "p", fieldType = "select", answers = { "no", "low", "medium",
			"high" })
	public int earDischargeSize;

	@Label(label = "earBitingPain", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int earBitingPain;

	@Label(label = "earHearingLoss", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int earHearingLoss;

	@Label(label = "earBackPain", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int earBackPain;

	@Label(label = "earTinnitus", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int earTinnitus;

	@Label(label = "vertigo", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int vertigo;

	@Label(label = "balanceLoss", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int balanceLoss;

	// Ð‘Ð¾Ð´Ð¸Ñ‚ Ò¯Ð·Ð»Ñ�Ð³
	// Ð‘Ð°Ñ€ÑƒÑƒÐ½ Ñ‡Ð¸Ñ…
	@Label(label = "rShape", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int rShape;

	@Label(label = "rTouch", labelType = "c", fieldType = "select", answers = { "tenderness", "tendernessless" })
	public int rTouch;

	@Label(label = "rRame", labelType = "c", fieldType = "select", answers = { "normal", "dropsy" })
	public int rRame;

	@Label(label = "rRameTouch", labelType = "c", fieldType = "select", answers = { "tenderness", "tendernessless" })
	public int rRameTouch;

	@Label(label = "rOuterCanal", labelType = "c", fieldType = "select", answers = { "normal", "diminished_naturally",
			"diminished_adventitious" })
	public int rOuterCanal;

	@Label(label = "rInnerCanal", labelType = "c", fieldType = "select", answers = { "free", "airlock" })
	public int rInnerCanal;

	@Label(label = "rDischarge", labelType = "c", fieldType = "select", answers = { "no", "yes" })
	public int rDischarge;

	@Label(label = "rDischargeType", labelType = "c", fieldType = "select", answers = { "", "suppurative", "flavored",
			"green" })
	public int rDischargeType;

	@Label(label = "rCanalWall", labelType = "c", fieldType = "select", answers = { "normal", "bloatware" })
	public int rCanalWall;

	@Label(label = "rTambourine", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int rTambourine;

	@Label(label = "rTambourineType", labelType = "c", fieldType = "select", answers = { "", "other", "pearl_grey",
			"red" })
	public int rTambourineType;

	@Label(label = "rPerforationPose", labelType = "c")
	public String rPerforationPose;

	@Label(label = "rPerforationSize", labelType = "c")
	public String rPerforationSize;

	@Label(label = "rTambourineCave", labelType = "c", fieldType = "select", answers = { "", "whitish_pink", "red",
			"teratoid_change" })
	public int rTambourineCave;

	@Label(label = "rLimph", labelType = "c")
	public String rLimph;

	// Ð—Ò¯Ò¯Ð½ Ñ‡Ð¸Ñ…
	@Label(label = "lShape", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int lShape;

	@Label(label = "lTouch", labelType = "c", fieldType = "select", answers = { "tenderness", "tendernessless" })
	public int lTouch;

	@Label(label = "lRame", labelType = "c", fieldType = "select", answers = { "normal", "dropsy" })
	public int lRame;

	@Label(label = "lRameTouch", labelType = "c", fieldType = "select", answers = { "tenderness", "tendernessless" })
	public int lRameTouch;

	@Label(label = "lOuterCanal", labelType = "c", fieldType = "select", answers = { "normal", "diminished_naturally",
			"diminished_adventitious" })
	public int lOuterCanal;

	@Label(label = "lInnerCanal", labelType = "c", fieldType = "select", answers = { "free", "airlock" })
	public int lInnerCanal;

	@Label(label = "lDischarge", labelType = "c", fieldType = "select", answers = { "no", "yes" })
	public int lDischarge;

	@Label(label = "lDischargeType", labelType = "c", fieldType = "select", answers = { "", "suppurative", "flavored",
			"green" })
	public int lDischargeType;

	@Label(label = "lCanalWall", labelType = "c", fieldType = "select", answers = { "normal", "bloatware" })
	public int lCanalWall;

	@Label(label = "lTambourine", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int lTambourine;

	@Label(label = "lTambourineType", labelType = "c", fieldType = "select", answers = { "", "other", "pearl_grey",
			"red" })
	public int lTambourineType;

	@Label(label = "lPerforationPose", labelType = "c")
	public String lPerforationPose;

	@Label(label = "lPerforationSize", labelType = "c")
	public String lPerforationSize;

	@Label(label = "lTambourineCave", labelType = "c", fieldType = "select", answers = { "", "whitish_pink", "red",
			"teratoid_change" })
	public int lTambourineCave;

	@Label(label = "lLimph", labelType = "c")
	public String lLimph;

	@Label(label = "skinRateText", labelType = "p")
	public String rateEarText;

	@Label(label = "advice", labelType = "p")
	public String earScheduleText;

	@Label(label = "earRateText", labelType = "c")
	public String earRateText;

	@Label(label = "advice", labelType = "p")
	public String advice;

	public CustomerEar() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStringDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public Date getSymptomDate() {
		return symptomDate;
	}

	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}

	public String getSymptomDuration() {
		symptomDuration = "Тодорхойгүй";
		int year = 0;
		int month = 0;
		int date = 0;
		if (this.symptomDate != null)
			if (symptomDate.getYear() > new Date().getYear()) {
				symptomDuration = "Тодорхойгүй";
			} else {
				if (symptomDate.getMonth() > new Date().getMonth()) {
					if (symptomDate.getDate() > new Date().getDate()) {
						date = new Date().getDate() + 30 - symptomDate.getDate();
						month = new Date().getMonth() + 12 - symptomDate.getMonth();
						year = new Date().getYear() - symptomDate.getYear();
					} else {
						date = new Date().getDate() - symptomDate.getDate();
						month = new Date().getMonth() + 12 - symptomDate.getMonth();
						year = new Date().getYear() - symptomDate.getYear();
					}
				} else {
					date = new Date().getDate() - symptomDate.getDate();
					month = new Date().getMonth() - symptomDate.getMonth();
					year = new Date().getYear() - symptomDate.getYear();
				}

			}
		if (year == 0)
			symptomDuration = "";
		else
			symptomDuration = year + "жил" + " ";

		if (month == 0)
			symptomDuration = symptomDuration + "";
		else
			symptomDuration = symptomDuration + month + "сар" + " ";

		if (date == 0)
			symptomDuration = symptomDuration + "";
		else
			symptomDuration = symptomDuration + date + "өдөр";

		return symptomDuration;
	}

	public void setSymptomDuration(String symptomDuration) {
		this.symptomDuration = symptomDuration;
	}

	public int getUsedMedicine() {
		return usedMedicine;
	}

	public void setUsedMedicine(int usedMedicine) {
		this.usedMedicine = usedMedicine;
	}

	public int getEarDischarge() {
		return earDischarge;
	}

	public void setEarDischarge(int earDischarge) {
		this.earDischarge = earDischarge;
	}

	public int getEarDischargeOccurance() {
		return earDischargeOccurance;
	}

	public void setEarDischargeOccurance(int earDischargeOccurance) {
		this.earDischargeOccurance = earDischargeOccurance;
	}

	public int getUsedVaccine() {
		return usedVaccine;
	}

	public void setUsedVaccine(int usedVaccine) {
		this.usedVaccine = usedVaccine;
	}

	public int getHeadInjury() {
		return headInjury;
	}

	public void setHeadInjury(int headInjury) {
		this.headInjury = headInjury;
	}

	public int getEarDischargeSize() {
		return earDischargeSize;
	}

	public void setEarDischargeSize(int earDischargeSize) {
		this.earDischargeSize = earDischargeSize;
	}

	public int getEarBitingPain() {
		return earBitingPain;
	}

	public void setEarBitingPain(int earBitingPain) {
		this.earBitingPain = earBitingPain;
	}

	public int getEarHearingLoss() {
		return earHearingLoss;
	}

	public void setEarHearingLoss(int earHearingLoss) {
		this.earHearingLoss = earHearingLoss;
	}

	public int getEarBackPain() {
		return earBackPain;
	}

	public void setEarBackPain(int earBackPain) {
		this.earBackPain = earBackPain;
	}

	public int getEarTinnitus() {
		return earTinnitus;
	}

	public void setEarTinnitus(int earTinnitus) {
		this.earTinnitus = earTinnitus;
	}

	public int getVertigo() {
		return vertigo;
	}

	public void setVertigo(int vertigo) {
		this.vertigo = vertigo;
	}

	public int getBalanceLoss() {
		return balanceLoss;
	}

	public void setBalanceLoss(int balanceLoss) {
		this.balanceLoss = balanceLoss;
	}

	public int getrShape() {
		return rShape;
	}

	public void setrShape(int rShape) {
		this.rShape = rShape;
	}

	public int getrTouch() {
		return rTouch;
	}

	public void setrTouch(int rTouch) {
		this.rTouch = rTouch;
	}

	public int getrRame() {
		return rRame;
	}

	public void setrRame(int rRame) {
		this.rRame = rRame;
	}

	public int getrOuterCanal() {
		return rOuterCanal;
	}

	public void setrOuterCanal(int rOuterCanal) {
		this.rOuterCanal = rOuterCanal;
	}

	public int getrInnerCanal() {
		return rInnerCanal;
	}

	public void setrInnerCanal(int rInnerCanal) {
		this.rInnerCanal = rInnerCanal;
	}

	public int getrDischarge() {
		return rDischarge;
	}

	public void setrDischarge(int rDischarge) {
		this.rDischarge = rDischarge;
	}

	public int getrDischargeType() {
		return rDischargeType;
	}

	public void setrDischargeType(int rDischargeType) {
		this.rDischargeType = rDischargeType;
	}

	public int getrCanalWall() {
		return rCanalWall;
	}

	public void setrCanalWall(int rCanalWall) {
		this.rCanalWall = rCanalWall;
	}

	public int getrTambourine() {
		return rTambourine;
	}

	public void setrTambourine(int rTambourine) {
		this.rTambourine = rTambourine;
	}

	public String getrPerforationPose() {
		return rPerforationPose;
	}

	public void setrPerforationPose(String rPerforationPose) {
		this.rPerforationPose = rPerforationPose;
	}

	public String getrPerforationSize() {
		return rPerforationSize;
	}

	public void setrPerforationSize(String rPerforationSize) {
		this.rPerforationSize = rPerforationSize;
	}

	public int getrRameTouch() {
		return rRameTouch;
	}

	public void setrRameTouch(int rRameTouch) {
		this.rRameTouch = rRameTouch;
	}

	public int getrTambourineType() {
		return rTambourineType;
	}

	public void setrTambourineType(int rTambourineType) {
		this.rTambourineType = rTambourineType;
	}

	public int getrTambourineCave() {
		return rTambourineCave;
	}

	public void setrTambourineCave(int rTambourineCave) {
		this.rTambourineCave = rTambourineCave;
	}

	public String getrLimph() {
		return rLimph;
	}

	public void setrLimph(String rLimph) {
		this.rLimph = rLimph;
	}

	public int getlShape() {
		return lShape;
	}

	public void setlShape(int lShape) {
		this.lShape = lShape;
	}

	public int getlTouch() {
		return lTouch;
	}

	public void setlTouch(int lTouch) {
		this.lTouch = lTouch;
	}

	public int getlRame() {
		return lRame;
	}

	public void setlRame(int lRame) {
		this.lRame = lRame;
	}

	public int getlRameTouch() {
		return lRameTouch;
	}

	public void setlRameTouch(int lRameTouch) {
		this.lRameTouch = lRameTouch;
	}

	public int getlOuterCanal() {
		return lOuterCanal;
	}

	public void setlOuterCanal(int lOuterCanal) {
		this.lOuterCanal = lOuterCanal;
	}

	public int getlInnerCanal() {
		return lInnerCanal;
	}

	public void setlInnerCanal(int lInnerCanal) {
		this.lInnerCanal = lInnerCanal;
	}

	public int getlDischarge() {
		return lDischarge;
	}

	public void setlDischarge(int lDischarge) {
		this.lDischarge = lDischarge;
	}

	public int getlDischargeType() {
		return lDischargeType;
	}

	public void setlDischargeType(int lDischargeType) {
		this.lDischargeType = lDischargeType;
	}

	public int getlCanalWall() {
		return lCanalWall;
	}

	public void setlCanalWall(int lCanalWall) {
		this.lCanalWall = lCanalWall;
	}

	public int getlTambourine() {
		return lTambourine;
	}

	public void setlTambourine(int lTambourine) {
		this.lTambourine = lTambourine;
	}

	public int getlTambourineType() {
		return lTambourineType;
	}

	public void setlTambourineType(int lTambourineType) {
		this.lTambourineType = lTambourineType;
	}

	public String getlPerforationPose() {
		return lPerforationPose;
	}

	public void setlPerforationPose(String lPerforationPose) {
		this.lPerforationPose = lPerforationPose;
	}

	public String getlPerforationSize() {
		return lPerforationSize;
	}

	public void setlPerforationSize(String lPerforationSize) {
		this.lPerforationSize = lPerforationSize;
	}

	public int getlTambourineCave() {
		return lTambourineCave;
	}

	public void setlTambourineCave(int lTambourineCave) {
		this.lTambourineCave = lTambourineCave;
	}

	public String getlLimph() {
		return lLimph;
	}

	public void setlLimph(String lLimph) {
		this.lLimph = lLimph;
	}

	public String getEarUsedMedicine() {
		return earUsedMedicine;
	}

	public void setEarUsedMedicine(String earUsedMedicine) {
		this.earUsedMedicine = earUsedMedicine;
	}

	public String getChildUsedMedicine() {
		return childUsedMedicine;
	}

	public void setChildUsedMedicine(String childUsedMedicine) {
		this.childUsedMedicine = childUsedMedicine;
	}

	public String getChildHardInfectiousDisease() {
		return childHardInfectiousDisease;
	}

	public void setChildHardInfectiousDisease(String childHardInfectiousDisease) {
		this.childHardInfectiousDisease = childHardInfectiousDisease;
	}

	public String getRateEarText() {
		return rateEarText;
	}

	public void setRateEarText(String rateEarText) {
		this.rateEarText = rateEarText;
	}

	public String getEarScheduleText() {
		return earScheduleText;
	}

	public void setEarScheduleText(String earScheduleText) {
		this.earScheduleText = earScheduleText;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getEarRateText() {
		return earRateText;
	}

	public void setEarRateText(String earRateText) {
		this.earRateText = earRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
package hospital.businessentity;

import java.io.Serializable;
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

import hospital.annotation.Label;

public class CustomerColon implements Serializable {

	private static final long serialVersionUID = 1L;
	// Гэдэс өвдөнө
	@Label(label = "stomachache", labelType = "p", fieldType = "boolean")
	public boolean stomachache;
	// Гэдэс дүүрч цанхайна
	@Label(label = "hoove", labelType = "p", fieldType = "boolean")
	public boolean hoove;
	// Халуурна
	@Label(label = "fever", labelType = "p", fieldType = "boolean")
	public boolean fever;
	// Арьсаар тууралт гарна
	@Label(label = "rash", labelType = "p", fieldType = "boolean")
	public boolean rash;
	// Ам хаталт
	@Label(label = "thirsty", labelType = "p", fieldType = "boolean")
	public boolean thirsty;
	// Баасны хэмжээ
	@Label(label = "poopSize", labelType = "p", fieldType = "select", answers = { "", "high", "low" })
	public int poopSize;
	// Баасны хэлбэр
	@Label(label = "poopShape", labelType = "p", fieldType = "select", answers = { "", "shaped", "unshaped" })
	public int poopShape;
	// Усархаг, хоолны үлдэгдэлтэй
	@Label(label = "foodRemnant", labelType = "p", fieldType = "select", answers = { "", "have", "havenot" })
	public int foodRemnant;
	// Баас нь идээ, цус, салс агуулсан
	@Label(label = "poopWithBlood", labelType = "p", fieldType = "select", answers = { "", "have", "havenot" })
	public int poopWithBlood;
	// Суулгалтын хэмжээ
	@Label(label = "diarrhoeaSize", labelType = "p", fieldType = "select", answers = { "", "low", "high" })
	public int diarrhoeaSize;
	// Өдөрт хэдэн удаа
	@Label(label = "repeat", labelType = "p", fieldType = "select", answers = { "", "onetothree", "overthree" })
	public int repeat;
	// Дутуу задарсан хоолны үлдэгдэлтэй
	@Label(label = "poopRemnant", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopRemnant;
	// Өдөрт хэдэн удаа бие засдаг
	@Label(label = "poopRepeat", labelType = "p", fieldType = "select", answers = { "", "onetothree", "overthree" })
	public int poopRepeat;
	// Гүйцэд эсвэл дутуу бие засдаг
	@Label(label = "poopFinish", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopFinish;
	// Бие засах үед хэвлийгээр базалж өвддөг
	@Label(label = "painPooping", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int painPooping;
	// Баас хар гардаг
	@Label(label = "poopBlack", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopBlack;
	// Бие засах үед дүлэлт байдаг
	@Label(label = "poopForce", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopForce;
	// Бие засах нь хоол ундтай холбоотой
	@Label(label = "poopDependsFood", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopDependsFood;
	// Баас шингэрсэн
	@Label(label = "poopLiquid", labelType = "p", fieldType = "select", answers = { "", "no", "yes" })
	public int poopLiquid;
	// Баас тослог
	@Label(label = "poopLard", labelType = "p", fieldType = "select", answers = { "", "low", "high" })
	public int poopLard;
	// Хий дүлнэ
	@Label(label = "fakeForce", labelType = "p", fieldType = "boolean")
	public boolean fakeForce;

	// Хэвлий өвдөнө
	@Label(label = "bosomPain", labelType = "p", fieldType = "boolean")
	public boolean bosomPain;

	// Гэдэс хуржигнана
	@Label(label = "stomachGurgle", labelType = "p", fieldType = "boolean")
	public boolean stomachGurgle;

	// Хий гарна
	@Label(label = "farting", labelType = "p", fieldType = "boolean")
	public boolean farting;

	// Өвчин анх эхэлсэн:
	@Label(label = "illnessStart", labelType = "m")
	public String illnessStart;

	// Энэ удаагийн сэдрэл :
	@Label(label = "thisTime", labelType = "m")
	public String thisTime;

	// Сэдрэлийн шалтгаан:
	@Label(label = "reason", labelType = "m")
	public String reason;

	// Хувирч, өөрчлөгдсөн байдал:
	@Label(label = "changedForm", labelType = "m")
	public String changedForm;
	// Эм хэрэглэсэн эсэх:
	@Label(label = "medicineUsed", labelType = "m")
	public String medicineUsed;
	// Эм хэрэглэсэн хугацаа:
	@Label(label = "medicineUsedTime", labelType = "m")
	public String medicineUsedTime;
	// Эм хэрэглэсэн үр дүн:
	@Label(label = "medicineEffect", labelType = "m")
	public String medicineEffect;
	// Шинжилгээ хийлгэсэн эсэх:
	@Label(label = "labExamined", labelType = "m")
	public String labExamined;
	// Биеийн ерөнхий байдал:
	@Label(label = "patientStatus", labelType = "c", fieldType = "boolean", answers = { "", "psLow", "psMedium",
			"psHeavyish", "psHard", "psFatal" })
	public int patientStatus;

	// Ухаан санааны байдал:
	@Label(label = "mentality", labelType = "c", fieldType = "select", answers = { "", "mentClean", "mentStupor",
			"mentSopor", "mentComa" })
	public int mentality;

	// Ухаан санааны байдал:
	@Label(label = "skinStatus", labelType = "c", fieldType = "select", answers = { "", "color", "rack", "comedo",
			"temprature", "flexity", "idk", "dropsy", "skinCheckUp" })
	public int skinStatus;

	// өнгөц хүрэхэд
	@Label(label = "easyTouch", labelType = "c", fieldType = "select", answers = { "", "havePain", "haveNotPain" })
	public int easyTouch;

	// Хэвлийн булчингийн чангарал:
	@Label(label = "bosomTight", labelType = "c", fieldType = "select", answers = { "", "have", "haveNot" })
	public int bosomTight;

	// Хэвлийн аль хэсэгт өвдөлттэй байгааг:
	@Label(label = "whichSide", labelType = "c", fieldType = "select", answers = { "", "bosomTop", "bosomMiddle",
			"bosomBottom" })
	public int whichSide;

	// Хэвлийн гялтангийн цочролын шинж:
	@Label(label = "membranePainType", labelType = "c", fieldType = "select", answers = { "", "have", "haveNot" })
	public int membranePainType;

	// Хэвлийн чинэрэлт:
	@Label(label = "bosomGross", labelType = "c", fieldType = "select", answers = { "", "bosomTop", "bosomMiddle",
			"bosomBottom" })
	public int bosomGross;

	// Хэвлийн цагаан шугамын ивэрхий:
	@Label(label = "bosomHernia", labelType = "c", fieldType = "select", answers = { "", "have", "haveNot" })
	public int bosomHernia;

	// Хэвлий дэх томорсон бүтэц:
	@Label(label = "bosomSizeIncreasedSide", labelType = "c", fieldType = "select", answers = { "", "bosomTop",
			"bosomMiddle", "bosomBottom" })
	public int bosomSizeIncreasedSide;
	// Тахир гэдэс:
	@Label(label = "gralloch", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int gralloch;

	// Мухар гэдэс:
	@Label(label = "muleyStomach", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int muleyStomach;

	// Мухар гэдэс:
	@Label(label = "pourStomach", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int pourStomach;

	// Бүдүүн гэдэсний өгсөх ба уруудах хэсэг:
	@Label(label = "colonChange", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int colonChange;

	// Хөндлөн гэдэс:
	@Label(label = "landStomach", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int landStomach;

	// Бүдүүн гэдэсний элэг дэлүүний булан:
	@Label(label = "colonCorner", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int colonCorner;

	// Шулуун гэдэсний үзлэг:
	@Label(label = "recta", labelType = "c", fieldType = "select", answers = { "", "touchable", "untouchable" })
	public int recta;

	@Label(label = "colonRateText", labelType = "c")
	public String colonRateText;

	@Label(label = "advice", labelType = "c")
	public String advice;

	public boolean isStomachache() {
		return stomachache;
	}

	public void setStomachache(boolean stomachache) {
		this.stomachache = stomachache;
	}

	public boolean isHoove() {
		return hoove;
	}

	public void setHoove(boolean hoove) {
		this.hoove = hoove;
	}

	public boolean isFever() {
		return fever;
	}

	public void setFever(boolean fever) {
		this.fever = fever;
	}

	public boolean isRash() {
		return rash;
	}

	public void setRash(boolean rash) {
		this.rash = rash;
	}

	public boolean isThirsty() {
		return thirsty;
	}

	public void setThirsty(boolean thirsty) {
		this.thirsty = thirsty;
	}

	public int getPoopSize() {
		return poopSize;
	}

	public void setPoopSize(int poopSize) {
		this.poopSize = poopSize;
	}

	public int getPoopShape() {
		return poopShape;
	}

	public void setPoopShape(int poopShape) {
		this.poopShape = poopShape;
	}

	public int getFoodRemnant() {
		return foodRemnant;
	}

	public void setFoodRemnant(int foodRemnant) {
		this.foodRemnant = foodRemnant;
	}

	public int getPoopWithBlood() {
		return poopWithBlood;
	}

	public void setPoopWithBlood(int poopWithBlood) {
		this.poopWithBlood = poopWithBlood;
	}

	public int getDiarrhoeaSize() {
		return diarrhoeaSize;
	}

	public void setDiarrhoeaSize(int diarrhoeaSize) {
		this.diarrhoeaSize = diarrhoeaSize;
	}

	public int getRepeat() {
		return repeat;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}

	public int getPoopRemnant() {
		return poopRemnant;
	}

	public void setPoopRemnant(int poopRemnant) {
		this.poopRemnant = poopRemnant;
	}

	public int getPoopRepeat() {
		return poopRepeat;
	}

	public void setPoopRepeat(int poopRepeat) {
		this.poopRepeat = poopRepeat;
	}

	public int getPoopFinish() {
		return poopFinish;
	}

	public void setPoopFinish(int poopFinish) {
		this.poopFinish = poopFinish;
	}

	public int getPainPooping() {
		return painPooping;
	}

	public void setPainPooping(int painPooping) {
		this.painPooping = painPooping;
	}

	public int getPoopBlack() {
		return poopBlack;
	}

	public void setPoopBlack(int poopBlack) {
		this.poopBlack = poopBlack;
	}

	public int getPoopForce() {
		return poopForce;
	}

	public void setPoopForce(int poopForce) {
		this.poopForce = poopForce;
	}

	public int getPoopDependsFood() {
		return poopDependsFood;
	}

	public void setPoopDependsFood(int poopDependsFood) {
		this.poopDependsFood = poopDependsFood;
	}

	public int getPoopLiquid() {
		return poopLiquid;
	}

	public void setPoopLiquid(int poopLiquid) {
		this.poopLiquid = poopLiquid;
	}

	public int getPoopLard() {
		return poopLard;
	}

	public void setPoopLard(int poopLard) {
		this.poopLard = poopLard;
	}

	public boolean isFakeForce() {
		return fakeForce;
	}

	public void setFakeForce(boolean fakeForce) {
		this.fakeForce = fakeForce;
	}

	public boolean isBosomPain() {
		return bosomPain;
	}

	public void setBosomPain(boolean bosomPain) {
		this.bosomPain = bosomPain;
	}

	public boolean isStomachGurgle() {
		return stomachGurgle;
	}

	public void setStomachGurgle(boolean stomachGurgle) {
		this.stomachGurgle = stomachGurgle;
	}

	public boolean isFarting() {
		return farting;
	}

	public void setFarting(boolean farting) {
		this.farting = farting;
	}

	public String getIllnessStart() {
		return illnessStart;
	}

	public void setIllnessStart(String illnessStart) {
		this.illnessStart = illnessStart;
	}

	public String getThisTime() {
		return thisTime;
	}

	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getChangedForm() {
		return changedForm;
	}

	public void setChangedForm(String changedForm) {
		this.changedForm = changedForm;
	}

	public String getMedicineUsed() {
		return medicineUsed;
	}

	public void setMedicineUsed(String medicineUsed) {
		this.medicineUsed = medicineUsed;
	}

	public String getMedicineUsedTime() {
		return medicineUsedTime;
	}

	public void setMedicineUsedTime(String medicineUsedTime) {
		this.medicineUsedTime = medicineUsedTime;
	}

	public String getMedicineEffect() {
		return medicineEffect;
	}

	public void setMedicineEffect(String medicineEffect) {
		this.medicineEffect = medicineEffect;
	}

	public String getLabExamined() {
		return labExamined;
	}

	public void setLabExamined(String labExamined) {
		this.labExamined = labExamined;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getSkinStatus() {
		return skinStatus;
	}

	public void setSkinStatus(int skinStatus) {
		this.skinStatus = skinStatus;
	}

	public int getEasyTouch() {
		return easyTouch;
	}

	public void setEasyTouch(int easyTouch) {
		this.easyTouch = easyTouch;
	}

	public int getBosomTight() {
		return bosomTight;
	}

	public void setBosomTight(int bosomTight) {
		this.bosomTight = bosomTight;
	}

	public int getWhichSide() {
		return whichSide;
	}

	public void setWhichSide(int whichSide) {
		this.whichSide = whichSide;
	}

	public int getMembranePainType() {
		return membranePainType;
	}

	public void setMembranePainType(int membranePainType) {
		this.membranePainType = membranePainType;
	}

	public int getBosomGross() {
		return bosomGross;
	}

	public void setBosomGross(int bosomGross) {
		this.bosomGross = bosomGross;
	}

	public int getBosomHernia() {
		return bosomHernia;
	}

	public void setBosomHernia(int bosomHernia) {
		this.bosomHernia = bosomHernia;
	}

	public int getBosomSizeIncreasedSide() {
		return bosomSizeIncreasedSide;
	}

	public void setBosomSizeIncreasedSide(int bosomSizeIncreasedSide) {
		this.bosomSizeIncreasedSide = bosomSizeIncreasedSide;
	}

	public int getGralloch() {
		return gralloch;
	}

	public void setGralloch(int gralloch) {
		this.gralloch = gralloch;
	}

	public int getMuleyStomach() {
		return muleyStomach;
	}

	public void setMuleyStomach(int muleyStomach) {
		this.muleyStomach = muleyStomach;
	}

	public int getPourStomach() {
		return pourStomach;
	}

	public void setPourStomach(int pourStomach) {
		this.pourStomach = pourStomach;
	}

	public int getColonChange() {
		return colonChange;
	}

	public void setColonChange(int colonChange) {
		this.colonChange = colonChange;
	}

	public int getLandStomach() {
		return landStomach;
	}

	public void setLandStomach(int landStomach) {
		this.landStomach = landStomach;
	}

	public int getColonCorner() {
		return colonCorner;
	}

	public void setColonCorner(int colonCorner) {
		this.colonCorner = colonCorner;
	}

	public int getRecta() {
		return recta;
	}

	public void setRecta(int recta) {
		this.recta = recta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(int patientStatus) {
		this.patientStatus = patientStatus;
	}

	public String getColonRateText() {
		return colonRateText;
	}

	public void setColonRateText(String colonRateText) {
		this.colonRateText = colonRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
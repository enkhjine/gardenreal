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

public class CustomerStomach implements Serializable {

	private static final long serialVersionUID = 1L;
	// Ходоодны биж шинж
	@Label(label = "stomachBidgeBehavior", labelType = "p", fieldType = "boolean")
	public boolean stomachBidgeBehavior;
	// Цээж хорсоно
	@Label(label = "cehstBurning", labelType = "p", fieldType = "boolean")
	public boolean chestBurning;
	// Хэхэрнэ
	@Label(label = "belch", labelType = "p", fieldType = "boolean")
	public boolean belch;
	// агаараар
	@Label(label = "air", labelType = "p", fieldType = "boolean")
	public boolean air;
	// хүчиллэгээр
	@Label(label = "acid", labelType = "p", fieldType = "boolean")
	public boolean acid;
	// гашуунаар
	@Label(label = "salty", labelType = "p", fieldType = "boolean")
	public boolean salty;
	// Дотор муухайрна
	@Label(label = "nausea", labelType = "p", fieldType = "boolean")
	public boolean nausea;
	// Бөөлжинө
	@Label(label = "throwUp", labelType = "p", fieldType = "boolean")
	public boolean throwUp;
	// идсэн зүйлээр
	@Label(label = "byFood", labelType = "p", fieldType = "boolean")
	public boolean byFood;
	// цусаар
	@Label(label = "byBlood", labelType = "p", fieldType = "boolean")
	public boolean byBlood;
	// бор хүрэн зүйлээр
	@Label(label = "byBrownThing", labelType = "p", fieldType = "boolean")
	public boolean byBrownThing;
	// цөсөөр
	@Label(label = "byGall", labelType = "p", fieldType = "boolean")
	public boolean byGall;
	// баасаар
	@Label(label = "byPoop", labelType = "p", fieldType = "boolean")
	public boolean byPoop;
	// Хоолны дуршил өөрчлөгдөнө
	@Label(label = "appetiteChange", labelType = "p", fieldType = "boolean")
	public boolean appetiteChange;
	// Хоолонд дургүй болсон
	@Label(label = "appetiteNone", labelType = "p", fieldType = "boolean")
	public boolean appetiteNone;
	// Гэдэсний биж шинж
	@Label(label = "gutBidgeBehavior", labelType = "p", fieldType = "boolean")
	public boolean gutBidgeBehavior;
	// Баас хатна
	@Label(label = "poopDry", labelType = "p", fieldType = "boolean")
	public boolean poopDry;
	// Суулгана
	@Label(label = "diarrhea", labelType = "p", fieldType = "boolean")
	public boolean diarrhea;
	// Баас хар гарна
	@Label(label = "poopBlack", labelType = "p", fieldType = "boolean")
	public boolean poopBlack;
	// Өвдөлтийн шинж
	@Label(label = "painType", labelType = "p", fieldType = "boolean")
	public boolean painType;
	// Аюулхай орчим голомтот өвдөлттэй
	@Label(label = "nearEpigastrium", labelType = "p", fieldType = "boolean")
	public boolean nearEpigastrium;
	// Баруун хавирганы нуман доор өвдөнө
	@Label(label = "nearRightFlank", labelType = "p", fieldType = "boolean")
	public boolean nearRightFlank;
	// Өвдөлт
	@Label(label = "pain", labelType = "p", fieldType = "boolean")
	public boolean pain;
	// эрт
	@Label(label = "early", labelType = "p", fieldType = "boolean")
	public boolean early;
	// орой
	@Label(label = "late", labelType = "p", fieldType = "boolean")
	public boolean late;
	// шөнийн
	@Label(label = "night", labelType = "p", fieldType = "boolean")
	public boolean night;
	// өлөн үеийн эрч их
	@Label(label = "morePainfulWhenHungry", labelType = "p", fieldType = "boolean")
	public boolean morePainfulWhenHungry;
	// хоол хүнстэй холбоотой
	@Label(label = "dependsFood", labelType = "p", fieldType = "boolean")
	public boolean dependsFood;
	// Тэжээл дутал, цус багадалтын шинж
	@Label(label = "lackFood", labelType = "p", fieldType = "boolean")
	public boolean lackFood;
	// Турна
	@Label(label = "loseWeight", labelType = "p", fieldType = "boolean")
	public boolean loseWeight;
	// Цус багадна
	@Label(label = "anaemia", labelType = "p", fieldType = "boolean")
	public boolean anaemia;
	// Стресс
	@Label(label = "stress", labelType = "m", fieldType = "boolean")
	public boolean stress;
	// Архи, тамхи
	@Label(label = "alcohol", labelType = "m", fieldType = "boolean")
	public boolean alcohol;
	// Зохисгүй хооллолт
	@Label(label = "denutrition", labelType = "m", fieldType = "boolean")
	public boolean denutrition;
	// Эм (үрэвслийн эсрэг дааврын бус бэлдмэл)
	@Label(label = "medicine", labelType = "m", fieldType = "boolean")
	public boolean medicine;
	// Халдвар (Helicobacter pylori)
	@Label(label = "pylori", labelType = "m", fieldType = "boolean")
	public boolean pylori;
	// Антибиотикийн хэрэглээ
	@Label(label = "antibioticUsage", labelType = "m", fieldType = "boolean")
	public boolean antibioticUsage;
	// Халуун хоол хэрэглэх
	@Label(label = "hotFood", labelType = "m", fieldType = "boolean")
	public boolean hotFood;
	// Хүнд ачаалал
	@Label(label = "overwork", labelType = "m", fieldType = "boolean")
	public boolean overwork;
	// Бусад өвчин
	@Label(label = "otherIllness", labelType = "m", fieldType = "boolean")
	public boolean otherIllness;
	// PPI
	@Label(label = "ppi", labelType = "m", fieldType = "boolean")
	public boolean ppi;
	// Антацид
	@Label(label = "antacid", labelType = "m", fieldType = "boolean")
	public boolean antacid;
	// Рашаан
	@Label(label = "mineralWater", labelType = "m", fieldType = "boolean")
	public boolean mineralWater;
	// Мөөгөнцрийн эсрэг эм
	@Label(label = "medicineForFungal", labelType = "m", fieldType = "boolean")
	public boolean medicineForFungal;
	// Helicobacter pylori устгах эм
	@Label(label = "medicineForHelicoBacter", labelType = "m", fieldType = "boolean")
	public boolean medicineForHelicoBacter;
	// Ношпа
	@Label(label = "noshpa", labelType = "m", fieldType = "boolean")
	public boolean noshpa;
	// Гистамины Н2 саатуулагч
	@Label(label = "gistamin", labelType = "m", fieldType = "boolean")
	public boolean gistamin;
	// Атропин
	@Label(label = "apropin", labelType = "m", fieldType = "boolean")
	public boolean apropin;
	// Деклоденк
	@Label(label = "declodenl", labelType = "m", fieldType = "boolean")
	public boolean declodenk;
	// Дурангийн эмчилгээ
	@Label(label = "endoscope", labelType = "m", fieldType = "boolean")
	public boolean endoscope;
	// Цус тогтоох, цус нөхөх эмчилгээ
	@Label(label = "haemostasis", labelType = "m", fieldType = "boolean")
	public boolean haemostasis;
	// Биеийн ерөнхий байдал:
	@Label(label = "constitution", labelType = "c", fieldType = "boolean", answers = { "normal", "medium",
			"rather_heavy", "heavy" })
	public int constitution;
	// Ухаан санааны байдал:
	@Label(label = "mentality", labelType = "c", fieldType = "boolean", answers = { "clean", "stupor", "sopor",
			"coma" })
	public int mentality;
	// Тэжээл дуталд орсон
	@Label(label = "lackedFood", labelType = "c", fieldType = "boolean")
	public boolean lackedFood;
	// Захын тунгалгийн зангилаа томорсон
	@Label(label = "canaemia", labelType = "c", fieldType = "boolean")
	public boolean canaemia;
	// Хэл өнгөртэй
	@Label(label = "tongueFur", labelType = "c", fieldType = "boolean")
	public boolean tongueFur;
	// Аюулхайд эмзэглэлтэй
	@Label(label = "epigastriumPain", labelType = "c", fieldType = "boolean")
	public boolean epigastriumPain;
	// Менделийн шинжтэй
	@Label(label = "mendel", labelType = "c", fieldType = "boolean")
	public boolean mendel;
	// Ходоодны налуур эмзэглэлтэй
	@Label(label = "stomachPain", labelType = "c", fieldType = "boolean")
	public boolean stomachPain;
	// Ходоодны доод хил:
	@Label(label = "stomachBottomMargin", labelType = "c")
	public String stomachBottomMargin;

	@Label(label = "stomachRateText", labelType = "c")
	public String stomachRateText;

	@Label(label = "advice", labelType = "c")
	public String advice;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isStomachBidgeBehavior() {
		return stomachBidgeBehavior;
	}

	public void setStomachBidgeBehavior(boolean stomachBidgeBehavior) {
		this.stomachBidgeBehavior = stomachBidgeBehavior;
	}

	public boolean isChestBurning() {
		return chestBurning;
	}

	public void setChestBurning(boolean chestBurning) {
		this.chestBurning = chestBurning;
	}

	public boolean isBelch() {
		return belch;
	}

	public void setBelch(boolean belch) {
		this.belch = belch;
	}

	public boolean isAir() {
		return air;
	}

	public void setAir(boolean air) {
		this.air = air;
	}

	public boolean isAcid() {
		return acid;
	}

	public void setAcid(boolean acid) {
		this.acid = acid;
	}

	public boolean isSalty() {
		return salty;
	}

	public void setSalty(boolean salty) {
		this.salty = salty;
	}

	public boolean isNausea() {
		return nausea;
	}

	public void setNausea(boolean nausea) {
		this.nausea = nausea;
	}

	public boolean isThrowUp() {
		return throwUp;
	}

	public void setThrowUp(boolean throwUp) {
		this.throwUp = throwUp;
	}

	public boolean isByFood() {
		return byFood;
	}

	public void setByFood(boolean byFood) {
		this.byFood = byFood;
	}

	public boolean isByBlood() {
		return byBlood;
	}

	public void setByBlood(boolean byBlood) {
		this.byBlood = byBlood;
	}

	public boolean isByBrownThing() {
		return byBrownThing;
	}

	public void setByBrownThing(boolean byBrownThing) {
		this.byBrownThing = byBrownThing;
	}

	public boolean isByGall() {
		return byGall;
	}

	public void setByGall(boolean byGall) {
		this.byGall = byGall;
	}

	public boolean isByPoop() {
		return byPoop;
	}

	public void setByPoop(boolean byPoop) {
		this.byPoop = byPoop;
	}

	public boolean isAppetiteChange() {
		return appetiteChange;
	}

	public void setAppetiteChange(boolean appetiteChange) {
		this.appetiteChange = appetiteChange;
	}

	public boolean isAppetiteNone() {
		return appetiteNone;
	}

	public void setAppetiteNone(boolean appetiteNone) {
		this.appetiteNone = appetiteNone;
	}

	public boolean isGutBidgeBehavior() {
		return gutBidgeBehavior;
	}

	public void setGutBidgeBehavior(boolean gutBidgeBehavior) {
		this.gutBidgeBehavior = gutBidgeBehavior;
	}

	public boolean isPoopDry() {
		return poopDry;
	}

	public void setPoopDry(boolean poopDry) {
		this.poopDry = poopDry;
	}

	public boolean isDiarrhea() {
		return diarrhea;
	}

	public void setDiarrhea(boolean diarrhea) {
		this.diarrhea = diarrhea;
	}

	public boolean isPoopBlack() {
		return poopBlack;
	}

	public void setPoopBlack(boolean poopBlack) {
		this.poopBlack = poopBlack;
	}

	public boolean isPainType() {
		return painType;
	}

	public void setPainType(boolean painType) {
		this.painType = painType;
	}

	public boolean isNearEpigastrium() {
		return nearEpigastrium;
	}

	public void setNearEpigastrium(boolean nearEpigastrium) {
		this.nearEpigastrium = nearEpigastrium;
	}

	public boolean isNearRightFlank() {
		return nearRightFlank;
	}

	public void setNearRightFlank(boolean nearRightFlank) {
		this.nearRightFlank = nearRightFlank;
	}

	public boolean isPain() {
		return pain;
	}

	public void setPain(boolean pain) {
		this.pain = pain;
	}

	public boolean isEarly() {
		return early;
	}

	public void setEarly(boolean early) {
		this.early = early;
	}

	public boolean isLate() {
		return late;
	}

	public void setLate(boolean late) {
		this.late = late;
	}

	public boolean isNight() {
		return night;
	}

	public void setNight(boolean night) {
		this.night = night;
	}

	public boolean isMorePainfulWhenHungry() {
		return morePainfulWhenHungry;
	}

	public void setMorePainfulWhenHungry(boolean morePainfulWhenHungry) {
		this.morePainfulWhenHungry = morePainfulWhenHungry;
	}

	public boolean isDependsFood() {
		return dependsFood;
	}

	public void setDependsFood(boolean dependsFood) {
		this.dependsFood = dependsFood;
	}

	public boolean isLackFood() {
		return lackFood;
	}

	public void setLackFood(boolean lackFood) {
		this.lackFood = lackFood;
	}

	public boolean isLoseWeight() {
		return loseWeight;
	}

	public void setLoseWeight(boolean loseWeight) {
		this.loseWeight = loseWeight;
	}

	public boolean isAnaemia() {
		return anaemia;
	}

	public void setAnaemia(boolean anaemia) {
		this.anaemia = anaemia;
	}

	public boolean isStress() {
		return stress;
	}

	public void setStress(boolean stress) {
		this.stress = stress;
	}

	public boolean isAlcohol() {
		return alcohol;
	}

	public void setAlcohol(boolean alcohol) {
		this.alcohol = alcohol;
	}

	public boolean isDenutrition() {
		return denutrition;
	}

	public void setDenutrition(boolean denutrition) {
		this.denutrition = denutrition;
	}

	public boolean isMedicine() {
		return medicine;
	}

	public void setMedicine(boolean medicine) {
		this.medicine = medicine;
	}

	public boolean isPylori() {
		return pylori;
	}

	public void setPylori(boolean pylori) {
		this.pylori = pylori;
	}

	public boolean isAntibioticUsage() {
		return antibioticUsage;
	}

	public void setAntibioticUsage(boolean antibioticUsage) {
		this.antibioticUsage = antibioticUsage;
	}

	public boolean isHotFood() {
		return hotFood;
	}

	public void setHotFood(boolean hotFood) {
		this.hotFood = hotFood;
	}

	public boolean isOverwork() {
		return overwork;
	}

	public void setOverwork(boolean overwork) {
		this.overwork = overwork;
	}

	public boolean isOtherIllness() {
		return otherIllness;
	}

	public void setOtherIllness(boolean otherIllness) {
		this.otherIllness = otherIllness;
	}

	public boolean isPpi() {
		return ppi;
	}

	public void setPpi(boolean ppi) {
		this.ppi = ppi;
	}

	public boolean isAntacid() {
		return antacid;
	}

	public void setAntacid(boolean antacid) {
		this.antacid = antacid;
	}

	public boolean isMineralWater() {
		return mineralWater;
	}

	public void setMineralWater(boolean mineralWater) {
		this.mineralWater = mineralWater;
	}

	public boolean isMedicineForFungal() {
		return medicineForFungal;
	}

	public void setMedicineForFungal(boolean medicineForFungal) {
		this.medicineForFungal = medicineForFungal;
	}

	public boolean isMedicineForHelicoBacter() {
		return medicineForHelicoBacter;
	}

	public void setMedicineForHelicoBacter(boolean medicineForHelicoBacter) {
		this.medicineForHelicoBacter = medicineForHelicoBacter;
	}

	public boolean isNoshpa() {
		return noshpa;
	}

	public void setNoshpa(boolean noshpa) {
		this.noshpa = noshpa;
	}

	public boolean isGistamin() {
		return gistamin;
	}

	public void setGistamin(boolean gistamin) {
		this.gistamin = gistamin;
	}

	public boolean isApropin() {
		return apropin;
	}

	public void setApropin(boolean apropin) {
		this.apropin = apropin;
	}

	public boolean isDeclodenk() {
		return declodenk;
	}

	public void setDeclodenk(boolean declodenk) {
		this.declodenk = declodenk;
	}

	public boolean isEndoscope() {
		return endoscope;
	}

	public void setEndoscope(boolean endoscope) {
		this.endoscope = endoscope;
	}

	public boolean isHaemostasis() {
		return haemostasis;
	}

	public void setHaemostasis(boolean haemostasis) {
		this.haemostasis = haemostasis;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public boolean isLackedFood() {
		return lackedFood;
	}

	public void setLackedFood(boolean lackedFood) {
		this.lackedFood = lackedFood;
	}

	public boolean isCanaemia() {
		return canaemia;
	}

	public void setCanaemia(boolean canaemia) {
		this.canaemia = canaemia;
	}

	public boolean isTongueFur() {
		return tongueFur;
	}

	public void setTongueFur(boolean tongueFur) {
		this.tongueFur = tongueFur;
	}

	public boolean isEpigastriumPain() {
		return epigastriumPain;
	}

	public void setEpigastriumPain(boolean epigastriumPain) {
		this.epigastriumPain = epigastriumPain;
	}

	public boolean isMendel() {
		return mendel;
	}

	public void setMendel(boolean mendel) {
		this.mendel = mendel;
	}

	public boolean isStomachPain() {
		return stomachPain;
	}

	public void setStomachPain(boolean stomachPain) {
		this.stomachPain = stomachPain;
	}

	public String getStomachBottomMargin() {
		return stomachBottomMargin;
	}

	public void setStomachBottomMargin(String stomachBottomMargin) {
		this.stomachBottomMargin = stomachBottomMargin;
	}

	public String getStomachRateText() {
		return stomachRateText;
	}

	public void setStomachRateText(String stomachRateText) {
		this.stomachRateText = stomachRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
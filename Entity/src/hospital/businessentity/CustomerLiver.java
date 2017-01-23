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

public class CustomerLiver implements Serializable {

	private static final long serialVersionUID = 1L;
	// Мэдрэл сульдалын хам шинж
	@Label(label = "mentalityBehavior", labelType = "p", fieldType = "boolean")
	public boolean mentalityBehavior;
	// Цочромтгой болох
	@Label(label = "acrimony", labelType = "p", fieldType = "boolean")
	public boolean acrimony;
	// Ажлын чадвар алдагдах
	@Label(label = "losePerformance", labelType = "p", fieldType = "boolean")
	public boolean losePerformance;
	// Толгой өвдөх
	@Label(label = "headache", labelType = "p", fieldType = "boolean")
	public boolean headache;
	// Бөөлжис цутгах
	@Label(label = "sweat", labelType = "p", fieldType = "boolean")
	public boolean sweat;
	// Бөөлжис цутгах
	@Label(label = "throwUp", labelType = "p", fieldType = "boolean")
	public boolean throwUp;
	// Биж хам шинж
	@Label(label = "bidgeBehavior", labelType = "p", fieldType = "boolean")
	public boolean bidgeBehavior;
	// Хоолонд дургүй болох
	@Label(label = "loseAppetite", labelType = "p", fieldType = "boolean")
	public boolean loseAppetite;
	// Аюулхайд эвгүй оргих
	@Label(label = "epigastrium", labelType = "p", fieldType = "boolean")
	public boolean epigastrium;
	// Хэхрэх
	@Label(label = "belch", labelType = "p", fieldType = "boolean")
	public boolean belch;
	// Гэдэс дүүрэх
	@Label(label = "hoove", labelType = "p", fieldType = "boolean")
	public boolean hoove;
	// Хоолны шингэц муудах
	@Label(label = "lowDigest", labelType = "p", fieldType = "boolean")
	public boolean lowDigest;
	// Өтгөн хатах
	@Label(label = "constipation", labelType = "p", fieldType = "boolean")
	public boolean constipation;

	// Цусархагших хам шинж
	@Label(label = "bloodBehavior", labelType = "p", fieldType = "boolean")
	public boolean bloodBehavior;
	// Хамар буйлнаас цус гарах
	@Label(label = "noseBleed", labelType = "p", fieldType = "boolean")
	public boolean noseBleed;
	// Арьсанд цус хурах
	@Label(label = "afflux", labelType = "p", fieldType = "boolean")
	public boolean afflux;
	// Цусан толбо үүсэх
	@Label(label = "bloodSpot", labelType = "p", fieldType = "boolean")
	public boolean bloodSpot;

	// Цөс зогсонгшилын хам шинж
	@Label(label = "gallBehavior", labelType = "p", fieldType = "boolean")
	public boolean gallBehavior;
	// Арьс загатнах шинж
	@Label(label = "skinItch", labelType = "p", fieldType = "boolean")
	public boolean skinItch;

	// sharlana
	@Label(label = "icterus", labelType = "p", fieldType = "boolean")
	public boolean icterus;

	// nusuujnu
	@Label(label = "comedo", labelType = "p", fieldType = "boolean")
	public boolean comedo;

	// salst sharlana
	@Label(label = "glairIcterus", labelType = "p", fieldType = "boolean")
	public boolean glairIcterus;

	// shees sharlana
	@Label(label = "urnineIcterus", labelType = "p", fieldType = "boolean")
	public boolean urnineIcterus;

	// baas tsairna
	@Label(label = "poopDawn", labelType = "p", fieldType = "boolean")
	public boolean poopDawn;

	// Иммунн-үрэвслийн шинж
	@Label(label = "immmuBehavior", labelType = "p", fieldType = "boolean")
	public boolean immmuBehavior;

	// Haluurna
	@Label(label = "fever", labelType = "p", fieldType = "boolean")
	public boolean fever;

	// uy much uwdunu
	@Label(label = "articulationPain", labelType = "p", fieldType = "boolean")
	public boolean articulationPain;

	// bulchirhai tomorno
	@Label(label = "bigGland", labelType = "p", fieldType = "boolean")
	public boolean bigGland;

	// Өвдөлтийн-үрэвслийн шинж
	@Label(label = "painBehavior", labelType = "p", fieldType = "boolean")
	public boolean painBehavior;

	// Баруун хавирганы нуман доогуур унжирч өвдөнө
	@Label(label = "rightFlankPain", labelType = "p", fieldType = "boolean")
	public boolean rightFlankPain;
	// Зовиур:
	@Label(label = "otherPain", labelType = "p")
	public String otherPain;
	// Өвдөлт баруун гар, дал мөр, эгэм мөр, хүзүүрүү дамждаг уу ?
	@Label(label = "painTransfer", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int painTransfer;
	// Өвдөлтийн шинж чанар ямар байдаг вэ?:
	@Label(label = "painType", labelType = "m", fieldType = "select", answers = { "", "stick", "warm", "protracted" })
	public int painType;
	// Хооллолтын дэглэм барьдаг уу?
	@Label(label = "diet", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int diet;

	// Удаан өлөн явдаг уу?
	@Label(label = "longTimeHungry", labelType = "m", fieldType = "select", answers = { "", "yes", "no" })
	public int longTimeHungry;
	// Өтгөн
	@Label(label = "cloggy", labelType = "m", fieldType = "boolean")
	public boolean cloggy;
	// Өөх тостой
	@Label(label = "lardy", labelType = "m", fieldType = "boolean")
	public boolean lardy;
	// Хайрч шарсан
	@Label(label = "fried", labelType = "m", fieldType = "boolean")
	public boolean fried;
	// Жигнэсэн
	@Label(label = "steamed", labelType = "m", fieldType = "boolean")
	public boolean steamed;
	// Хийжүүлсэн ундаа
	@Label(label = "bubbly", labelType = "m", fieldType = "boolean")
	public boolean bubbly;
	// arhi
	@Label(label = "alchohol", labelType = "m", fieldType = "boolean")
	public boolean alchohol;
	// Дарс
	@Label(label = "vine", labelType = "m", fieldType = "boolean")
	public boolean vine;
	// shar airag
	@Label(label = "beer", labelType = "m", fieldType = "boolean")
	public boolean beer;
	//
	@Label(label = "HBsAG", labelType = "m", fieldType = "boolean")
	public boolean hBsAG;
	//
	@Label(label = "antiHCV", labelType = "m", fieldType = "boolean")
	public boolean antiHCV;

	@Label(label = "antiHDV", labelType = "m", fieldType = "boolean")
	public boolean antiHDV;
	// Биеийн ерөнхий байдал:
	@Label(label = "patientStatus", labelType = "c", fieldType = "boolean", answers = { "", "psLow", "psMedium",
			"psHeavyish", "psHard", "psFatal" })
	public int patientStatus;
	// Арьс салстын байдал:
	@Label(label = "glairStatus", labelType = "c", fieldType = "boolean", answers = { "", "moist", "dry", "itchScar" })
	public int glairStatus;
	// Арьсны өнгө:
	@Label(label = "skinColor", labelType = "c", fieldType = "boolean", answers = { "", "scNormal", "scYellow",
			"scYellowGreen", "scGreen" })
	public int skinColor;
	// Арьсны тууралт:
	@Label(label = "skinRash", labelType = "c", fieldType = "boolean")
	public int skinRash;

	@Label(label = "skinRashDescription", labelType = "c")
	public int skinRashDescription;
	// Арьсны уян хатан чанар:
	@Label(label = "skinFlex", labelType = "c", fieldType = "boolean", answers = { "", "sfNormal", "sfHigh",
			"sfAbnormal" })
	public int skinFlex;
	// Хавантай эсэх:
	@Label(label = "edema", labelType = "c", fieldType = "boolean", answers = { "", "yes", "no" })
	public int edema;
	// Хэвлийд эмзэглэлтэй эсэх:
	@Label(label = "bosomPain", labelType = "c", fieldType = "boolean", answers = { "", "yes", "no" })
	public int bosomPain;
	// Хэвлийд булчингийн чангарал байгаа эсэх :
	@Label(label = "bosomTight", labelType = "c", fieldType = "boolean", answers = { "", "yes", "no" })
	public int bosomTight;
	// Кера 1 шинж:
	@Label(label = "kera1", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int kera1;

	@Label(label = "ortner", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int ortner;

	@Label(label = "merf", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int merf;

	@Label(label = "muse", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int muse;

	@Label(label = "haritonov", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int haritonov;

	@Label(label = "vasilenko", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int vasilenko;

	@Label(label = "kurvuaz", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int kurvuaz;

	@Label(label = "lepene", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int lepene;

	@Label(label = "kera2", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int kera2;

	@Label(label = "boas", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int boas;
	// Гялтан цочролын шинж:
	@Label(label = "membrane", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int membrane;

	@Label(label = "liverRateText", labelType = "c")
	public String liverRateText;

	@Label(label = "advice", labelType = "c")
	public String advice;

	public boolean isMentalityBehavior() {
		return mentalityBehavior;
	}

	public void setMentalityBehavior(boolean mentalityBehavior) {
		this.mentalityBehavior = mentalityBehavior;
	}

	public boolean isAcrimony() {
		return acrimony;
	}

	public void setAcrimony(boolean acrimony) {
		this.acrimony = acrimony;
	}

	public boolean isLosePerformance() {
		return losePerformance;
	}

	public void setLosePerformance(boolean losePerformance) {
		this.losePerformance = losePerformance;
	}

	public boolean isHeadache() {
		return headache;
	}

	public void setHeadache(boolean headache) {
		this.headache = headache;
	}

	public boolean isSweat() {
		return sweat;
	}

	public void setSweat(boolean sweat) {
		this.sweat = sweat;
	}

	public boolean isThrowUp() {
		return throwUp;
	}

	public void setThrowUp(boolean throwUp) {
		this.throwUp = throwUp;
	}

	public boolean isBidgeBehavior() {
		return bidgeBehavior;
	}

	public void setBidgeBehavior(boolean bidgeBehavior) {
		this.bidgeBehavior = bidgeBehavior;
	}

	public boolean isLoseAppetite() {
		return loseAppetite;
	}

	public void setLoseAppetite(boolean loseAppetite) {
		this.loseAppetite = loseAppetite;
	}

	public boolean isEpigastrium() {
		return epigastrium;
	}

	public void setEpigastrium(boolean epigastrium) {
		this.epigastrium = epigastrium;
	}

	public boolean isBelch() {
		return belch;
	}

	public void setBelch(boolean belch) {
		this.belch = belch;
	}

	public boolean isHoove() {
		return hoove;
	}

	public void setHoove(boolean hoove) {
		this.hoove = hoove;
	}

	public boolean isLowDigest() {
		return lowDigest;
	}

	public void setLowDigest(boolean lowDigest) {
		this.lowDigest = lowDigest;
	}

	public boolean isConstipation() {
		return constipation;
	}

	public void setConstipation(boolean constipation) {
		this.constipation = constipation;
	}

	public boolean isBloodBehavior() {
		return bloodBehavior;
	}

	public void setBloodBehavior(boolean bloodBehavior) {
		this.bloodBehavior = bloodBehavior;
	}

	public boolean isNoseBleed() {
		return noseBleed;
	}

	public void setNoseBleed(boolean noseBleed) {
		this.noseBleed = noseBleed;
	}

	public boolean isAfflux() {
		return afflux;
	}

	public void setAfflux(boolean afflux) {
		this.afflux = afflux;
	}

	public boolean isBloodSpot() {
		return bloodSpot;
	}

	public void setBloodSpot(boolean bloodSpot) {
		this.bloodSpot = bloodSpot;
	}

	public boolean isGallBehavior() {
		return gallBehavior;
	}

	public void setGallBehavior(boolean gallBehavior) {
		this.gallBehavior = gallBehavior;
	}

	public boolean isSkinItch() {
		return skinItch;
	}

	public void setSkinItch(boolean skinItch) {
		this.skinItch = skinItch;
	}

	public boolean isIcterus() {
		return icterus;
	}

	public void setIcterus(boolean icterus) {
		this.icterus = icterus;
	}

	public boolean isComedo() {
		return comedo;
	}

	public void setComedo(boolean comedo) {
		this.comedo = comedo;
	}

	public boolean isGlairIcterus() {
		return glairIcterus;
	}

	public void setGlairIcterus(boolean glairIcterus) {
		this.glairIcterus = glairIcterus;
	}

	public boolean isUrnineIcterus() {
		return urnineIcterus;
	}

	public void setUrnineIcterus(boolean urnineIcterus) {
		this.urnineIcterus = urnineIcterus;
	}

	public boolean isPoopDawn() {
		return poopDawn;
	}

	public void setPoopDawn(boolean poopDawn) {
		this.poopDawn = poopDawn;
	}

	public boolean isImmmuBehavior() {
		return immmuBehavior;
	}

	public void setImmmuBehavior(boolean immmuBehavior) {
		this.immmuBehavior = immmuBehavior;
	}

	public boolean isFever() {
		return fever;
	}

	public void setFever(boolean fever) {
		this.fever = fever;
	}

	public boolean isArticulationPain() {
		return articulationPain;
	}

	public void setArticulationPain(boolean articulationPain) {
		this.articulationPain = articulationPain;
	}

	public boolean isBigGland() {
		return bigGland;
	}

	public void setBigGland(boolean bigGland) {
		this.bigGland = bigGland;
	}

	public boolean isPainBehavior() {
		return painBehavior;
	}

	public void setPainBehavior(boolean painBehavior) {
		this.painBehavior = painBehavior;
	}

	public boolean isRightFlankPain() {
		return rightFlankPain;
	}

	public void setRightFlankPain(boolean rightFlankPain) {
		this.rightFlankPain = rightFlankPain;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public int getPainTransfer() {
		return painTransfer;
	}

	public void setPainTransfer(int painTransfer) {
		this.painTransfer = painTransfer;
	}

	public int getPainType() {
		return painType;
	}

	public void setPainType(int painType) {
		this.painType = painType;
	}

	public int getDiet() {
		return diet;
	}

	public void setDiet(int diet) {
		this.diet = diet;
	}

	public boolean isCloggy() {
		return cloggy;
	}

	public void setCloggy(boolean cloggy) {
		this.cloggy = cloggy;
	}

	public boolean isLardy() {
		return lardy;
	}

	public void setLardy(boolean lardy) {
		this.lardy = lardy;
	}

	public boolean isFried() {
		return fried;
	}

	public void setFried(boolean fried) {
		this.fried = fried;
	}

	public boolean isSteamed() {
		return steamed;
	}

	public void setSteamed(boolean steamed) {
		this.steamed = steamed;
	}

	public boolean isBubbly() {
		return bubbly;
	}

	public void setBubbly(boolean bubbly) {
		this.bubbly = bubbly;
	}

	public boolean isAlchohol() {
		return alchohol;
	}

	public void setAlchohol(boolean alchohol) {
		this.alchohol = alchohol;
	}

	public boolean isVine() {
		return vine;
	}

	public void setVine(boolean vine) {
		this.vine = vine;
	}

	public boolean isBeer() {
		return beer;
	}

	public void setBeer(boolean beer) {
		this.beer = beer;
	}

	public boolean ishBsAG() {
		return hBsAG;
	}

	public void sethBsAG(boolean hBsAG) {
		this.hBsAG = hBsAG;
	}

	public boolean isAntiHCV() {
		return antiHCV;
	}

	public void setAntiHCV(boolean antiHCV) {
		this.antiHCV = antiHCV;
	}

	public boolean isAntiHDV() {
		return antiHDV;
	}

	public void setAntiHDV(boolean antiHDV) {
		this.antiHDV = antiHDV;
	}

	public int getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(int patientStatus) {
		this.patientStatus = patientStatus;
	}

	public int getGlairStatus() {
		return glairStatus;
	}

	public void setGlairStatus(int glairStatus) {
		this.glairStatus = glairStatus;
	}

	public int getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(int skinColor) {
		this.skinColor = skinColor;
	}

	public int getSkinRash() {
		return skinRash;
	}

	public void setSkinRash(int skinRash) {
		this.skinRash = skinRash;
	}

	public int getSkinRashDescription() {
		return skinRashDescription;
	}

	public void setSkinRashDescription(int skinRashDescription) {
		this.skinRashDescription = skinRashDescription;
	}

	public int getSkinFlex() {
		return skinFlex;
	}

	public void setSkinFlex(int skinFlex) {
		this.skinFlex = skinFlex;
	}

	public int getEdema() {
		return edema;
	}

	public void setEdema(int edema) {
		this.edema = edema;
	}

	public int getBosomPain() {
		return bosomPain;
	}

	public void setBosomPain(int bosomPain) {
		this.bosomPain = bosomPain;
	}

	public int getBosomTight() {
		return bosomTight;
	}

	public void setBosomTight(int bosomTight) {
		this.bosomTight = bosomTight;
	}

	public int getKera1() {
		return kera1;
	}

	public void setKera1(int kera1) {
		this.kera1 = kera1;
	}

	public int getOrtner() {
		return ortner;
	}

	public void setOrtner(int ortner) {
		this.ortner = ortner;
	}

	public int getMerf() {
		return merf;
	}

	public void setMerf(int merf) {
		this.merf = merf;
	}

	public int getMuse() {
		return muse;
	}

	public void setMuse(int muse) {
		this.muse = muse;
	}

	public int getHaritonov() {
		return haritonov;
	}

	public void setHaritonov(int haritonov) {
		this.haritonov = haritonov;
	}

	public int getVasilenko() {
		return vasilenko;
	}

	public void setVasilenko(int vasilenko) {
		this.vasilenko = vasilenko;
	}

	public int getKurvuaz() {
		return kurvuaz;
	}

	public void setKurvuaz(int kurvuaz) {
		this.kurvuaz = kurvuaz;
	}

	public int getLepene() {
		return lepene;
	}

	public void setLepene(int lepene) {
		this.lepene = lepene;
	}

	public int getKera2() {
		return kera2;
	}

	public void setKera2(int kera2) {
		this.kera2 = kera2;
	}

	public int getBoas() {
		return boas;
	}

	public void setBoas(int boas) {
		this.boas = boas;
	}

	public int getMembrane() {
		return membrane;
	}

	public void setMembrane(int membrane) {
		this.membrane = membrane;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLiverRateText() {
		return liverRateText;
	}

	public void setLiverRateText(String liverRateText) {
		this.liverRateText = liverRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public int getLongTimeHungry() {
		return longTimeHungry;
	}

	public void setLongTimeHungry(int longTimeHungry) {
		this.longTimeHungry = longTimeHungry;
	}

}
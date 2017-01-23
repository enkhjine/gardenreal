
package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerHematology  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Цайж, цонхийно 0 - үгүй 1 - тийм
	 */
	
	@Label(label="hemato_blanch" ,labelType="p" , fieldType="boolean")
	public boolean hemato_blanch;
	/*
	 * Ядарч сульдна 0 - үгүй 1 - тийм
	 */
	
	@Label(label="hemato_weary" ,labelType="p" , fieldType="boolean")
	public boolean hemato_weary;
	/*
	 * Ясаар өвднө 0 - үгүй 1 - тийм
	 */
	
	@Label(label="hemato_bonePain" ,labelType="p" , fieldType="boolean")
	public boolean hemato_bonePain;
	/*
	 * Шарлалт 0 - үгүй 1 - тийм
	 */
	
	@Label(label="jaundice" ,labelType="p" , fieldType="boolean")
	public boolean jaundice;
	/*
	 * Хавагнана 0 - үгүй 1 - тийм
	 */
	
	@Label(label="edemahemato" ,labelType="p" , fieldType="boolean")
	public boolean edema;
	/*
	 * Амьсгаадна 0 - үгүй 1 - тийм
	 */
	
	@Label(label="breathe" ,labelType="p" , fieldType="boolean")
	public boolean breathe;
	/*
	 * Турж эцнэ 0 - үгүй 1 - тийм
	 */
	
	@Label(label="atrophyhemato" ,labelType="p" , fieldType="boolean")
	public boolean atrophy;
	/*
	 * Үеэр өвднө 0 - үгүй 1 - тийм
	 */
	
	@Label(label="hemato_pain" ,labelType="p" , fieldType="boolean")
	public boolean hemato_pain;

	///
	/*
	 * Хоолонд дургүй болно 0 - үгүй 1 - тийм
	 */
	
	@Label(label="cookDisLike" ,labelType="p" , fieldType="boolean")
	public boolean cookDisLike;
	/*
	 * Хоолны шингэц муудна 0 - үгүй 1 - тийм
	 */
	
	@Label(label="cookDigestion" ,labelType="p" , fieldType="boolean")
	public boolean cookDigestion;
	/*
	 * Халуурна  0 - үгүй 1 - тийм
	 */
	
	@Label(label="feverhemato" ,labelType="p" , fieldType="boolean")
	public boolean fever;
	
	@Label(label="pain" ,labelType="p" , fieldType="select",  answers={"dayhemato","nightheamto"})
	public int  dayNight;
	/*
	 * Цус гоожно  0 - үгүй 1 - тийм
	 */
	
	@Label(label="haemorrhage" ,labelType="p" , fieldType="boolean")
	public boolean haemorrhage;
	
	@Label(label="pain",labelType="p",fieldType="select",answers={"none","NoseWhere","uterusWhere","gingivaWhere","otherWhere"})
	public int haemorrhageWhere;
	
	@Label(label="pain",labelType="p",fieldType="select" , answers={"hight","minum","medium"})
	public  int hematoOneMenuId;
	/*
	 * Булчирхай томорсон 0 - үгүй 1 - тийм
	 */
	
	@Label(label="bigGland" ,labelType="p" , fieldType="boolean")
	public boolean bigGland;
	
	@Label(label="haemorrhageOtherText",  labelType="p")
	public String haemorrhageOtherText;
	
	
	//Асуумж
	@Label(label = "when", labelType = "m")
	public String when;

	@Label(label = "thisTime", labelType = "m")
	public String thisTime;

	@Label(label = "thisTimeReason", labelType = "m")
	public String thisTimeReason;

	@Label(label = "changed", labelType = "m")
	public String changed;
	@Label(label="otherHematoTextMed",labelType="m")
	public String otherHematoTextMed;
	

	@Label(label = "usedThings", labelType = "m")
	public String usedThings;
	 /*
	  * Төлөвлөгөө
	  * */
	@Label(label="advice",labelType="p")
	public  String advice;
	/*
	 * Зовиур бичих
	 * */
	
	@Label(label="pain",labelType="p")
	public String hematologyStressPainText;
	
	/*
	 * Үнэлгээ
	 * */
	@Label(label="skinRateText" ,labelType="p")
	public String hematoRateText;
	
	//Арьс  салст
	@Label(label="skinPituitare",labelType="c",fieldType="select" ,answers={"normal","paleWhite","yellowBronzy","slightlyBlue","SlightlyBlack"})
	public int skinPituitare;  
	//хөх шилбэ хавантай эсэх
	@Label(label="footEdema",labelType="c",fieldType="select",answers={"no","yes"})
	public int footEdema;
	
	
	// Тугалгийн  зангилааны байрлал
	@Label(label="crystalNode",labelType="c",fieldType="boolean")
	public boolean crystalNode;
	
	@Label(label="locationCrystalBead",labelType="c",fieldType="select", answers={ "nick","earVicinage","jawDown","clavicularUpper","down","armpitHemato","groinHemato","sprawlyHemato" })
	public int locationCrystalBead;
	
	// тунгалагийн зангилааны  байдал
	@Label(label="statusCrystalBead",labelType="c",fieldType="select",answers={"hematohard","hematoeasy","hematobig","peeve","nopeeve","hematofreeMove","hematolimitMove","hematochangeColorSkin"})
	public  int statusCrystalBead;
	
	/*
	 * Дэлүү 
	 *  */
	
	@Label(label="milthemato",labelType="c", fieldType="select", answers={"hemotanormal","biggest","peeve","noPeeve"})
	public int milt;
	
	@Label(label="outbreak",labelType="c",fieldType="select",answers={"one","two","three","four"})
	public  int biggestHemota;
	
	//Зүрхний авиа
	@Label(label="heartToneHemato",labelType="c",fieldType="select",answers={"normalBold","regular","deafty","noregular"})
	public int heartTone;
	
	//   шуугиан
	@Label(label="noiseHemato",labelType="c",fieldType="select", answers={"no","hematoEasyHeart"})
	public  int noise;
	
	//уушги
	@Label(label="hematolung",labelType="c",fieldType="select",answers={"hematoBreathe","harshBreathe","dryDank"})
	public  int lung;
	// артерийн даралт
	@Label(label="aterialPressureHemato",labelType="c")
	public  String aterialPressure;
	
	@Label(label="hematoEruption",labelType="c" ,fieldType="select",answers={"agnimate","hematoSmall","hematoBig","lightBlue" ,"bloodshot"})
	public  int  hematoEruption;
	
	/*
	 *  Бөөр
	 *  */
	@Label(label="edemaKidney" ,labelType="c",fieldType="select", answers={"edemaNo","edemaYes"})
	public int  edemaSelectBox;
	
	@Label(label="outbreak", labelType="c",fieldType="select", answers={"no","eyelid","facehemato","foothemato","allBody","otherBody"})
	public int  kidneyWhere;
	
	@Label(label="outbreak",labelType="c")
	public String kidneyOtherText;
	
	//Ясны  өвдөлт
	@Label(label="boneAcutePain", labelType="c", fieldType="select"  ,answers={"hematono","hematoyes"})
	public int boneAcutePain;
	
	//Пастернацкын шинж
	@Label(label="pasternaltsi", labelType="c",fieldType="select", answers={"hematofold","hematounFold"})
	public  int pasternaltsi;
	//Өсөлт
	@Label(label="raisehemato",labelType="c",fieldType="select", answers= {"normal","hematolose"})
	public int  raise;
	
	//Акромегали
	@Label(label="acromegaly",labelType="c",fieldType="select"  , answers= {"hematono","hematoyes"})
	public int acromegaly;
	//edemaYesNo
	@Label(label="edemaYesNo",labelType="c",fieldType="select",answers={"hematono","hematoyes"})
	public  int edemaYesNo;
	//Мэдрэл
	@Label(label="nervehemato",labelType="c")
	public String nerve;
	
	
	//  Дотоод шүүрэл
	@Label(label="secretionInHemato",labelType="c")
	public String secretionIn;
	//Бусад
	@Label(label="hematoOtherText",labelType="c")
	public String hematoOtherText;
	
	//
    public String getSecretionIn() {
		return secretionIn;
	}

	public void setSecretionIn(String secretionIn) {
		this.secretionIn = secretionIn;
	}

	public CustomerHematology(){
    	super();
    }

	public boolean isHemato_blanch() {
		return hemato_blanch;
	}


	public void setHemato_blanch(boolean hemato_blanch) {
		this.hemato_blanch = hemato_blanch;
	}


	public boolean isHemato_weary() {
		return hemato_weary;
	}


	public void setHemato_weary(boolean hemato_weary) {
		this.hemato_weary = hemato_weary;
	}


	public boolean isHemato_bonePain() {
		return hemato_bonePain;
	}


	public void setHemato_bonePain(boolean hemato_bonePain) {
		this.hemato_bonePain = hemato_bonePain;
	}


	public boolean isJaundice() {
		return jaundice;
	}


	public void setJaundice(boolean jaundice) {
		this.jaundice = jaundice;
	}


	public boolean isEdema() {
		return edema;
	}


	public void setEdema(boolean edema) {
		this.edema = edema;
	}


	public boolean isBreathe() {
		return breathe;
	}


	public void setBreathe(boolean breathe) {
		this.breathe = breathe;
	}


	public boolean isAtrophy() {
		return atrophy;
	}


	public void setAtrophy(boolean atrophy) {
		this.atrophy = atrophy;
	}


	public boolean isHemato_pain() {
		return hemato_pain;
	}


	public void setHemato_pain(boolean hemato_pain) {
		this.hemato_pain = hemato_pain;
	}


	public boolean isCookDisLike() {
		return cookDisLike;
	}


	public void setCookDisLike(boolean cookDisLike) {
		this.cookDisLike = cookDisLike;
	}


	public boolean isCookDigestion() {
		return cookDigestion;
	}


	public void setCookDigestion(boolean cookDigestion) {
		this.cookDigestion = cookDigestion;
	}


	public boolean isFever() {
		return fever;
	}


	public void setFever(boolean fever) {
		this.fever = fever;
	}


	public boolean isHaemorrhage() {
		return haemorrhage;
	}


	public void setHaemorrhage(boolean haemorrhage) {
		this.haemorrhage = haemorrhage;
	}


	public boolean isBigGland() {
		return bigGland;
	}

	public void setBigGland(boolean bigGland) {
		this.bigGland = bigGland;
	}
	
	public int getDayNight() {
		return dayNight;
	}

	public void setDayNight(int dayNight) {
		this.dayNight = dayNight;
	}

	public String getHematologyStressPainText() {
		return hematologyStressPainText;
	}
	public void setHematologyStressPainText(String hematologyStressPainText) {
		this.hematologyStressPainText = hematologyStressPainText;
	}
	
	public int getMilt() {
		return milt;
	}

	public void setMilt(int milt) {
		this.milt = milt;
	}


	
	public int getKidneyWhere() {
		return kidneyWhere;
	}

	public void setKidneyWhere(int kidneyWhere) {
		this.kidneyWhere = kidneyWhere;
	}

	public int getEdemaSelectBox() {
		return edemaSelectBox;
	}

	public void setEdemaSelectBox(int edemaSelectBox) {
		this.edemaSelectBox = edemaSelectBox;
	}

	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getHematoRateText() {
		return hematoRateText;
	}
	public void setHematoRateText(String hematoRateText) {
		this.hematoRateText = hematoRateText;
	}
	public String getWhen() {
		return when;
	}
	public void setWhen(String when) {
		this.when = when;
	}
	public String getThisTime() {
		return thisTime;
	}
	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}
	public String getThisTimeReason() {
		return thisTimeReason;
	}
	public void setThisTimeReason(String thisTimeReason) {
		this.thisTimeReason = thisTimeReason;
	}
	public String getChanged() {
		return changed;
	}
	public void setChanged(String changed) {
		this.changed = changed;
	}
	public String getUsedThings() {
		return usedThings;
	}
	public void setUsedThings(String usedThings) {
		this.usedThings = usedThings;
	}
	public int getBoneAcutePain() {
		return boneAcutePain;
	}
	public void setBoneAcutePain(int boneAcutePain) {
		this.boneAcutePain = boneAcutePain;
	}
	public int getPasternaltsi() {
		return pasternaltsi;
	}
	public void setPasternaltsi(int pasternaltsi) {
		this.pasternaltsi = pasternaltsi;
	}
	public int getRaise() {
		return raise;
	}
	public void setRaise(int raise) {
		this.raise = raise;
	}
	public int getAcromegaly() {
		return acromegaly;
	}
	public void setAcromegaly(int acromegaly) {
		this.acromegaly = acromegaly;
	}
	public int getEdemaYesNo() {
		return edemaYesNo;
	}
	public void setEdemaYesNo(int edemaYesNo) {
		this.edemaYesNo = edemaYesNo;
	}
	public int getSkinPituitare() {
		return skinPituitare;
	}
	public void setSkinPituitare(int skinPituitare) {
		this.skinPituitare = skinPituitare;
	}
	public int getFootEdema() {
		return footEdema;
	}
	public void setFootEdema(int footEdema) {
		this.footEdema = footEdema;
	}
	public String getNerve() {
		return nerve;
	}
	public void setNerve(String nerve) {
		this.nerve = nerve;
	}
	public int  getHematoEruption() {
		return hematoEruption;
	}
	public void setHematoEruption(int hematoEruption) {
		this.hematoEruption = hematoEruption;
	}

	public int getLocationCrystalBead() {
		return locationCrystalBead;
	}

	public void setLocationCrystalBead(int locationCrystalBead) {
		this.locationCrystalBead = locationCrystalBead;
	}

	public int getStatusCrystalBead() {
		return statusCrystalBead;
	}

	public void setStatusCrystalBead(int statusCrystalBead) {
		this.statusCrystalBead = statusCrystalBead;
	}

	public int getHeartTone() {
		return heartTone;
	}

	public void setHeartTone(int heartTone) {
		this.heartTone = heartTone;
	}

	public int getNoise() {
		return noise;
	}

	public void setNoise(int noise) {
		this.noise = noise;
	}

	public String getAterialPressure() {
		return aterialPressure;
	}

	public void setAterialPressure(String aterialPressure) {
		this.aterialPressure = aterialPressure;
	}

	public int getLung() {
		return lung;
	}

	public void setLung(int lung) {
		this.lung = lung;
	}

	public int getBiggestHemota() {
		return biggestHemota;
	}

	public void setBiggestHemota(int biggestHemota) {
		this.biggestHemota = biggestHemota;
	}

	public String getHematoOtherText() {
		return hematoOtherText;
	}

	public void setHematoOtherText(String hematoOtherText) {
		this.hematoOtherText = hematoOtherText;
	}

	public String getOtherHematoTextMed() {
		return otherHematoTextMed;
	}

	public void setOtherHematoTextMed(String otherHematoTextMed) {
		this.otherHematoTextMed = otherHematoTextMed;
	}

	public int getHaemorrhageWhere() {
		return haemorrhageWhere;
	}

	public void setHaemorrhageWhere(int haemorrhageWhere) {
		this.haemorrhageWhere = haemorrhageWhere;
	}

	public int getHematoOneMenuId() {
		return hematoOneMenuId;
	}

	public void setHematoOneMenuId(int hematoOneMenuId) {
		this.hematoOneMenuId = hematoOneMenuId;
	}

	public String getHaemorrhageOtherText() {
		return haemorrhageOtherText;
	}

	public void setHaemorrhageOtherText(String haemorrhageOtherText) {
		this.haemorrhageOtherText = haemorrhageOtherText;
	}

	public boolean isCrystalNode() {
		return crystalNode;
	}

	public void setCrystalNode(boolean crystalNode) {
		this.crystalNode = crystalNode;
	}

	public String getKidneyOtherText() {
		return kidneyOtherText;
	}

	public void setKidneyOtherText(String kidneyOtherText) {
		this.kidneyOtherText = kidneyOtherText;
	}

    
}

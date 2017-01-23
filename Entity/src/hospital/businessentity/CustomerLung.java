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

public class CustomerLung implements Serializable {

	private static final long serialVersionUID = 1L;

	@Label(label = "symptomDate", labelType = "m")
	public Date symptomDate;

	@Label(label = "symptomDuration", labelType = "m")
	public String symptomDuration;

	/*
	 * 0 - үгүй 1 - тийм
	 */

	@Label(label = "usedMedicine", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int usedMedicine;

	@Label(label = "usedMedicineName", labelType = "m")
	public String usedMedicineName;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "cough", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int cough;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "phlegm", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int phlegm;

	@Label(label = "phlegmColor", labelType = "p")
	public String phlegmColor;

	/*
	 * 0 - Үгүй 1 - Бага 2 - Дунд 3 - Их
	 */
	@Label(label = "phlegmSize", labelType = "p", fieldType = "select", answers = { "", "psLow", "psMedium", "psHigh" })
	public int phlegmSize;

	/*
	 * 1 - Орой 2 - Өдөр 3 - Өглөө 4 - Хэдийд ч хамаагүй
	 */
	@Label(label = "phlegmWhen", labelType = "p", fieldType = "select", answers = {"", "pwNight", "pwDay", "pWmorning",
			"pwSometimes" })
	public int phlegmWhen;

	/*
	 * 0 - Үгүй 3 - Тийм 2 - Баруун 1 - Зүүн
	 */
	@Label(label = "phlegmPosition", labelType = "p", fieldType = "select", answers = { "", "no", "yes", "right",
			"left" })
	public int phlegmPosition;

	/*
	 * 0 - Муу 1 - Сайн 2 - Дунд
	 */
	@Label(label = "phlegmGetOff", labelType = "p", fieldType = "select", answers = { "", "pgoLow", "pgoMedium", "pgoHigh" })
	public int phlegmGetOff;

	@Label(label = "phlegmDescription", labelType = "p")
	public String phlegmDescription;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "chestHurt", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int chestHurt;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "fever", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int fever;

	@Label(label = "feverDescription", labelType = "p")
	public String feverDescription;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "dyspnea", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int dyspnea;

	/*
	
	 */
	@Label(label = "dyspneaLevelByMMRC", labelType = "p", fieldType = "select", answers = { "", "levelZero", "levelOne",
			"levelTwo", "levelThree", "levelFour" })
	public int dyspneaLevelByMMRC;

	/*
	 * 1 - авалт 2 - гаралт 3 - Хоёр фаз
	 */
	@Label(label = "phaseLoss", labelType = "p", fieldType = "select", answers = { "", "incoming", "outcoming",
			"twoPhases" })
	public int phaseLoss;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "feelUneasy", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int feelUneasy;

	/*
	 * 0 - 2> 1 - 2 2 - 2<= 3 - Байнга
	 */
	@Label(label = "uneasyOccuration", labelType = "p", fieldType = "select", answers = { "", "uoLessTwoInWeek",
			"uoTwoInweek", "uoOverTwoInWeek", "uoAlways" })
	public int uneasyOccuration;

	/*
	 * 0 - 2> 1 - monthly 2 2 - weekly 2 3 - Байнга
	 */
	@Label(label = "nightUneasy", labelType = "p", fieldType = "select", answers = { "", "nuLessTwoInMonth", "nuTwoInMonth",
			"nuOneInWeek", "nuAlways" })
	public int nightUneasy;

	/*
	 * 0 - 20> 1 - 20~30 2 - 30<
	 */
	@Label(label = "dayVariance", labelType = "p", fieldType = "select", answers = { "", "lessTwenty", "twentyToThirty",
			"overThirty" })
	public int dayVariance;

	@Label(label = "otherPain", labelType = "p")
	public String otherPain;

	/*
	 * 0 - дунд 1 - хүндэвтэр 2 - хүнд 3 - маш хүнд
	 */
	@Label(label = "patientStatus", labelType = "c", fieldType = "select", answers = { "", "psMedium", "psHeavyish", "psHard",
			"psFatal" })
	public int patientStatus;

	/*
	 * 0 - саруул 1 - бүдгэрсэн 2 - ухаангүй 3 - дэмийрсэн
	 */
	@Label(label = "mentality", labelType = "p", fieldType = "select", answers = { "", "mClean", "mBlury", "mDelirous",
			"mRaved" })
	public int mentality;

	/*
	 * 0 - идэвхитэй 1 - идэвхигүй 2 - албадмал
	 */
	@Label(label = "bodyStance", labelType = "c", fieldType = "select", answers = { "", "bsActive", "bsInactive",
			"bsForced" })
	public int bodyStance;

	@Label(label = "bodyStanceDescription", labelType = "c")
	public String bodyStanceDescription;

	/*
	 * 
	 */
	@Label(label = "constitution", labelType = "c", answers = { "", "normal", "abnormal" })
	public int constitution;

	@Label(label = "consitutionDescription", labelType = "c")
	public String consitutionDescription;

	/*
	 * 0 - хэвийн 1 - Жингийн илүүдэл 2 - Тарган 3 - Туранхай
	 */
	@Label(label = "weightStatus", labelType = "c", answers = { "", "weightNormal", "overweight", "fat", "skinny" })
	public int weightStatus;

	@Label(label = "skinColor", labelType = "c")
	public String skinColor;

	/*
	 * 0 - хөхрөлтгүй 1 - хөхрөлттэй
	 */
	@Label(label = "cyanosis", labelType = "c", answers = { "", "hasCyanosis" })
	public int cyanosis;

	/*
	 * 1 - бүлээн 2 - хүйтэн
	 */
	@Label(label = "skinTemperature", labelType = "c", answers = { "", "stWarm", "stCold" })
	public int skinTemperature;

	@Label(label = "mouthCaveInspection", labelType = "c")
	public String mouthCaveInspection;

	/*
	 * 1 - Эмзлэглэлтэй 0 - Эмзэглэлгүй
	 */
	@Label(label = "noseTouch", labelType = "c", answers = { "", "sensitive" })
	public int noseTouch;

	/*
	 * 1 - Эмзлэглэлтэй 0 - Эмзэглэлгүй
	 */
	@Label(label = "noseKnock", labelType = "c", answers = { "", "sensitive" })
	public int noseKnock;

	/*
	 * 1 - Тийм 0 - Үгүй
	 */
	@Label(label = "noseBreath", labelType = "c", answers = { "no", "yes" })
	public int noseBreath;

	/*
	 * 0 - Өргөсөлт 1 - Чинэрэлт 2 - Лугшилт
	 */
	@Label(label = "jugularVein", labelType = "c", answers = { "", "escalation", "insensate", "throb" })
	public int jugularVein;

	/*
	 * 1 - Өөрчлөлттэй 0 - Эрүүл
	 */
	@Label(label = "throatInspection", labelType = "c", answers = { "", "lChanged" })
	public int throatInspection;

	@Label(label = "throatChange", labelType = "c")
	public String throatChange;

	/*
	 * 1 - Өөрчлөлттэй 0 - Эрүүл
	 */
	@Label(label = "headInspection", labelType = "c", answers = { "0", "lChanged" })
	public int headInspection;

	@Label(label = "headChange", labelType = "c")
	public String headChange;

	/*
	 * 1 - Хавантай 0 - Хавангүй
	 */
	@Label(label = "sideEdema", labelType = "c", answers = { "0", "hasEdema" })
	public int sideEdema;

	@Label(label = "chestShape", labelType = "c")
	public String chestShape;

	/*
	 * 1 - Тийм 0 - Үгүй 2 - Баруун 3 - Зүүн
	 */
	@Label(label = "throatInspection", labelType = "c", answers = { "", "yes", "right", "left", "no" })
	public int chestSidesBreath;

	@Label(label = "respiratoryRate", labelType = "c")
	public int respiratoryRate;

	/*
	 * 1 - Эмгэг 0 - Эрүүл
	 */
	@Label(label = "chestTouch", labelType = "c", answers = { "", "healthy", "disease" })
	public int chestTouch;

	@Label(label = "chestTouchDesc", labelType = "c")
	public String chestTouchDesc;

	/*
	 * 0 - Насандаа тохирсон 1 - Хөшүүн 2 - Алдагдсан
	 */
	@Label(label = "chestElasticity", labelType = "c", answers = { "", "ageFit", "inflexible", "imbalanced" })
	public int chestElasticity;

	/*
	 * 0 - Тод чимээ 1 - Хэнгэргэн чимээ 2 - Хайрцган чимээ 3 - Дүлийвтэр 4 -
	 * Дүлий
	 */
	@Label(label = "lungKnock", labelType = "c", answers = { "", "resonance", "tympany", "boxed", "dull", "flatness" })
	public int lungKnock;

	/*
	 * 0 - Амьсгаа хэвийн 1 - Тодорсон 2 - Суларсан 3 - Ширүүн 4 - Хольмог 5 -
	 * Эмгэг гуурсан хоолойн амьсгал
	 */
	@Label(label = "lungListen", labelType = "c", answers = { "", "llNormal", "llEmersed", "llReduced", "llHeavy",
			"llMixed", "llBronchial" })
	public int lungListen;

	/*
	 * 0 - Хуурай хэржигнүүр 1 - Нойтон хэржигнүүр 2 - Шажигнуур 3 - Шүргэлцэх
	 * чимээ
	 */
	@Label(label = "additionalNoise", labelType = "c", answers = { "", "anDry", "anWet", "anCrackled", "anOsculatory" })
	public int additionalNoise;

	@Label(label = "edgeBloodOxygen", labelType = "c")
	public String edgeBloodOxygen;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Label(label = "heartBorderIncrease", labelType = "c", answers = { "no", "yes" })
	public int heartBorderIncrease;

	/*
	 * 0 - Тод 1 - Бүдэг 2 - Авианы өргөлт
	 */
	@Label(label = "heartTone", labelType = "c", answers = { "", "htClean", "htLow", "htEmphasis" })
	public int heartTone;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Label(label = "liverEnlarge", labelType = "c", answers = { "no", "yes" })
	public int liverEnlarge;

	@Label(label = "urineOutput", labelType = "c")
	public String urineOutput;

	@Label(label = "sleep", labelType = "c")
	public String sleep;

	@Label(label = "lungRateText", labelType = "c")
	public String lungRateText;

	@Label(label = "advice", labelType = "c")
	public String advice;

	@Transient
	private boolean doCough;

	@Transient
	private boolean doPhlegm;

	@Transient
	private boolean doChestHurt;

	@Transient
	private boolean doFever;

	@Transient
	private boolean doDyspnea;

	@Transient
	private boolean doFeelUneasy;

	public CustomerLung() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getSymptomDate() {
		return symptomDate;
	}

	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}

	public int getUsedMedicine() {
		return usedMedicine;
	}

	public void setUsedMedicine(int usedMedicine) {
		this.usedMedicine = usedMedicine;
	}

	public int getCough() {
		return cough;
	}

	public void setCough(int cough) {
		this.cough = cough;
	}

	public int getPhlegm() {
		return phlegm;
	}

	public void setPhlegm(int phlegm) {
		this.phlegm = phlegm;
	}

	public String getPhlegmColor() {
		return phlegmColor;
	}

	public void setPhlegmColor(String phlegmColor) {
		this.phlegmColor = phlegmColor;
	}

	public int getPhlegmSize() {
		return phlegmSize;
	}

	public void setPhlegmSize(int phlegmSize) {
		this.phlegmSize = phlegmSize;
	}

	public int getPhlegmWhen() {
		return phlegmWhen;
	}

	public void setPhlegmWhen(int phlegmWhen) {
		this.phlegmWhen = phlegmWhen;
	}

	public int getPhlegmPosition() {
		return phlegmPosition;
	}

	public void setPhlegmPosition(int phlegmPosition) {
		this.phlegmPosition = phlegmPosition;
	}

	public int getPhlegmGetOff() {
		return phlegmGetOff;
	}

	public void setPhlegmGetOff(int phlegmGetOff) {
		this.phlegmGetOff = phlegmGetOff;
	}

	public String getPhlegmDescription() {
		return phlegmDescription;
	}

	public void setPhlegmDescription(String phlegmDescription) {
		this.phlegmDescription = phlegmDescription;
	}

	public int getChestHurt() {
		return chestHurt;
	}

	public void setChestHurt(int chestHurt) {
		this.chestHurt = chestHurt;
	}

	public int getPhaseLoss() {
		return phaseLoss;
	}

	public void setPhaseLoss(int phaseLoss) {
		this.phaseLoss = phaseLoss;
	}

	public int getFeelUneasy() {
		return feelUneasy;
	}

	public void setFeelUneasy(int feelUneasy) {
		this.feelUneasy = feelUneasy;
	}

	public int getUneasyOccuration() {
		return uneasyOccuration;
	}

	public void setUneasyOccuration(int uneasyOccuration) {
		this.uneasyOccuration = uneasyOccuration;
	}

	public int getNightUneasy() {
		return nightUneasy;
	}

	public void setNightUneasy(int nightUneasy) {
		this.nightUneasy = nightUneasy;
	}

	public int getDayVariance() {
		return dayVariance;
	}

	public void setDayVariance(int dayVariance) {
		this.dayVariance = dayVariance;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getBodyStance() {
		return bodyStance;
	}

	public void setBodyStance(int bodyStance) {
		this.bodyStance = bodyStance;
	}

	public String getBodyStanceDescription() {
		return bodyStanceDescription;
	}

	public void setBodyStanceDescription(String bodyStanceDescription) {
		this.bodyStanceDescription = bodyStanceDescription;
	}

	public int getConstitution() {
		return constitution;
	}

	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	public String getConsitutionDescription() {
		return consitutionDescription;
	}

	public void setConsitutionDescription(String consitutionDescription) {
		this.consitutionDescription = consitutionDescription;
	}

	public int getWeightStatus() {
		return weightStatus;
	}

	public void setWeightStatus(int weightStatus) {
		this.weightStatus = weightStatus;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public int getCyanosis() {
		return cyanosis;
	}

	public void setCyanosis(int cyanosis) {
		this.cyanosis = cyanosis;
	}

	public String getUrineOutput() {
		return urineOutput;
	}

	public void setUrineOutput(String urineOutput) {
		this.urineOutput = urineOutput;
	}

	public int getSkinTemperature() {
		return skinTemperature;
	}

	public void setSkinTemperature(int skinTemperature) {
		this.skinTemperature = skinTemperature;
	}

	public String getMouthCaveInspection() {
		return mouthCaveInspection;
	}

	public void setMouthCaveInspection(String mouthCaveInspection) {
		this.mouthCaveInspection = mouthCaveInspection;
	}

	public int getNoseTouch() {
		return noseTouch;
	}

	public void setNoseTouch(int noseTouch) {
		this.noseTouch = noseTouch;
	}

	public int getNoseKnock() {
		return noseKnock;
	}

	public void setNoseKnock(int noseKnock) {
		this.noseKnock = noseKnock;
	}

	public int getNoseBreath() {
		return noseBreath;
	}

	public void setNoseBreath(int noseBreath) {
		this.noseBreath = noseBreath;
	}

	public int getJugularVein() {
		return jugularVein;
	}

	public void setJugularVein(int jugularVein) {
		this.jugularVein = jugularVein;
	}

	public int getThroatInspection() {
		return throatInspection;
	}

	public void setThroatInspection(int throatInspection) {
		this.throatInspection = throatInspection;
	}

	public String getThroatChange() {
		return throatChange;
	}

	public void setThroatChange(String throatChange) {
		this.throatChange = throatChange;
	}

	public int getHeadInspection() {
		return headInspection;
	}

	public void setHeadInspection(int headInspection) {
		this.headInspection = headInspection;
	}

	public String getHeadChange() {
		return headChange;
	}

	public void setHeadChange(String headChange) {
		this.headChange = headChange;
	}

	public int getSideEdema() {
		return sideEdema;
	}

	public void setSideEdema(int sideEdema) {
		this.sideEdema = sideEdema;
	}

	public String getChestShape() {
		return chestShape;
	}

	public void setChestShape(String chestShape) {
		this.chestShape = chestShape;
	}

	public int getChestSidesBreath() {
		return chestSidesBreath;
	}

	public void setChestSidesBreath(int chestSidesBreath) {
		this.chestSidesBreath = chestSidesBreath;
	}

	public int getRespiratoryRate() {
		return respiratoryRate;
	}

	public void setRespiratoryRate(int respiratoryRate) {
		respiratoryRate = respiratoryRate;
	}

	public int getChestTouch() {
		return chestTouch;
	}

	public void setChestTouch(int chestTouch) {
		this.chestTouch = chestTouch;
	}

	public String getChestTouchDesc() {
		return chestTouchDesc;
	}

	public void setChestTouchDesc(String chestTouchDesc) {
		this.chestTouchDesc = chestTouchDesc;
	}

	public int getChestElasticity() {
		return chestElasticity;
	}

	public void setChestElasticity(int chestElasticity) {
		this.chestElasticity = chestElasticity;
	}

	public int getLungKnock() {
		return lungKnock;
	}

	public void setLungKnock(int lungKnock) {
		this.lungKnock = lungKnock;
	}

	public int getLungListen() {
		return lungListen;
	}

	public void setLungListen(int lungListen) {
		this.lungListen = lungListen;
	}

	public int getAdditionalNoise() {
		return additionalNoise;
	}

	public void setAdditionalNoise(int additionalNoise) {
		this.additionalNoise = additionalNoise;
	}

	public String getEdgeBloodOxygen() {
		return edgeBloodOxygen;
	}

	public void setEdgeBloodOxygen(String edgeBloodOxygen) {
		this.edgeBloodOxygen = edgeBloodOxygen;
	}

	public int getHeartBorderIncrease() {
		return heartBorderIncrease;
	}

	public void setHeartBorderIncrease(int heartBorderIncrease) {
		this.heartBorderIncrease = heartBorderIncrease;
	}

	public int getHeartTone() {
		return heartTone;
	}

	public void setHeartTone(int heartTone) {
		this.heartTone = heartTone;
	}

	public int getLiverEnlarge() {
		return liverEnlarge;
	}

	public void setLiverEnlarge(int liverEnlarge) {
		this.liverEnlarge = liverEnlarge;
	}

	public String getSleep() {
		return sleep;
	}

	public void setSleep(String sleep) {
		this.sleep = sleep;
	}

	public boolean isDoCough() {
		if (this.cough == 1)
			doCough = true;
		else
			doCough = false;
		return doCough;
	}

	public void setDoCough(boolean doCough) {
		if (doCough)
			this.cough = 1;
		else
			this.cough = 0;
		this.doCough = doCough;
	}

	public boolean isDoChestHurt() {
		if (this.chestHurt == 1)
			doChestHurt = true;
		else
			doChestHurt = false;
		return doChestHurt;
	}

	public void setDoChestHurt(boolean doChestHurt) {
		if (doChestHurt)
			this.chestHurt = 1;
		else
			this.chestHurt = 0;
		this.doChestHurt = doChestHurt;
	}

	public boolean isDoFever() {
		if (this.fever == 1)
			doFever = true;
		else
			doFever = false;
		return doFever;
	}

	public void setDoFever(boolean doFever) {
		this.doFever = doFever;
	}

	public boolean isDoDyspnea() {
		if (this.dyspnea == 1)
			doDyspnea = true;
		else
			doDyspnea = false;
		return doDyspnea;
	}

	public void setDoDyspnea(boolean doDyspnea) {
		if (doDyspnea)
			this.dyspnea = 1;
		else
			this.dyspnea = 0;
		this.doDyspnea = doDyspnea;
	}

	public boolean isDoFeelUneasy() {
		if (this.feelUneasy == 1)
			doFeelUneasy = true;
		else
			doFeelUneasy = false;
		return doFeelUneasy;
	}

	public void setDoFeelUneasy(boolean doFeelUneasy) {
		if (doFeelUneasy)
			this.feelUneasy = 1;
		else
			this.feelUneasy = 0;
		this.doFeelUneasy = doFeelUneasy;
	}

	public boolean isDoPhlegm() {
		if (this.phlegm == 1)
			doPhlegm = true;
		else
			doPhlegm = false;
		return doPhlegm;
	}

	public void setDoPhlegm(boolean doPhlegm) {
		if (doPhlegm)
			this.phlegm = 1;
		else
			this.phlegm = 0;
		this.doPhlegm = doPhlegm;
	}

	public String getSymptomDuration() {
		return symptomDuration;
	}

	public void setSymptomDuration(String symptomDuration) {
		this.symptomDuration = symptomDuration;
	}

	public String getUsedMedicineName() {
		return usedMedicineName;
	}

	public void setUsedMedicineName(String usedMedicineName) {
		this.usedMedicineName = usedMedicineName;
	}

	public int getFever() {
		return fever;
	}

	public void setFever(int fever) {
		this.fever = fever;
	}

	public String getFeverDescription() {
		return feverDescription;
	}

	public void setFeverDescription(String feverDescription) {
		this.feverDescription = feverDescription;
	}

	public int getDyspnea() {
		return dyspnea;
	}

	public void setDyspnea(int dyspnea) {
		this.dyspnea = dyspnea;
	}

	public int getDyspneaLevelByMMRC() {
		return dyspneaLevelByMMRC;
	}

	public void setDyspneaLevelByMMRC(int dyspneaLevelByMMRC) {
		this.dyspneaLevelByMMRC = dyspneaLevelByMMRC;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public int getPatientStatus() {
		return patientStatus;
	}

	public void setPatientStatus(int patientStatus) {
		this.patientStatus = patientStatus;
	}

	public String getLungRateText() {
		return lungRateText;
	}

	public void setLungRateText(String lungRateText) {
		this.lungRateText = lungRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
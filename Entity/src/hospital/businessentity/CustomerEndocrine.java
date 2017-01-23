package hospital.businessentity;

import hospital.annotation.Label;

/**
 * @author USER
 *
 */
/**
 * @author USER
 *
 */
public class CustomerEndocrine {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="endocrinePainText" , labelType="p")
	public String endocrinePainText;
	
	@Label(label="endocrineMedText" , labelType="m")
	public String endocrineMedText;
	
	@Label(label="patienthood" , labelType="c" , fieldType="select" , answers={"phNormal" , "heavyish" , "heavy" , "veryHeavy"})
	public int patienthood;
	
	@Label(label="mentality" , labelType="c" , fieldType="select" , answers={"mNormal" , "aggravated" , "delirous" })
	public int mentality;
	
	@Label(label="skinPituitare" , labelType="c" , fieldType="select" , answers={"normal" , "abnormal"})
	public int skinPituitare;
	
	@Label(label="skinPituitareText" , labelType="c")
	public String skinPituitareText;
	
	@Label(label="breathingPerMinute" , labelType="c")
	public int breathingPerMinute;
	
	@Label(label="auscultation" , labelType="c" , fieldType="select" , answers={"lung" , "tube" , "wheeze" , "weak"})
	public int auscultation;
	
	@Label(label="auscultationText" , labelType="c" , fieldType="select" , answers={"right" , "left" , "right_and_left"})
	public int auscultationText;
	
	@Label(label="sphygmic" , labelType="c")
	public int sphygmic;
	
	@Label(label="cardiolineByKnock" , labelType="c" , fieldType="select" , answers={"normal" , "exaggerated"})
	public int cardiolineByKnock;
	
	@Label(label="cardiolineByKnockText" , labelType="c" , fieldType="select" , answers={"right" , "left" , "right_and_left"})
	public int cardiolineByKnockText;
	
	@Label(label="cardiorasperByAuscultation" , labelType="c" , fieldType="select" , answers={"distinctly" , "shady" , "ratherUnclear"})
	public int cardiorasperByAuscultation;
	
	@Label(label="cardioarrhythmias" , labelType="c" , fieldType="select" , answers={"regular" , "irregular"})
	public int cardioarrhythmias;
	
	@Label(label="cardioarrhythmiasText" , labelType="c" , fieldType="boolean")
	public boolean cardioarrhythmiasText;
	
	@Label(label="rightArterialPressure" , labelType="c")
	public String rightArterialPressure;
	
	@Label(label="leftArterialPressure" , labelType="c")
	public String leftArterialPressure;
	
	@Label(label="tongueMoisture" , labelType="c" , fieldType="select" , answers={"normal" , "dryness"})
	public int tongueMoisture;
	
	@Label(label="tongueFur" , labelType="c" , fieldType="select" , answers={"coated" , "notCoated"})
	public int tongueFur;
	
	@Label(label="abdomenEasyTouch" , labelType="c" , fieldType="select" , answers={"normal" , "pain" , "notMembraneFear" , "membraneFear"})
	public int abdomenEasyTouch;
	
	@Label(label="abdomenHardTouch" , labelType="c" , fieldType="select" , answers={"normal" , "pain" , "notMembraneFear" , "membraneFear"})
	public int abdomenHardTouch;
	
	@Label(label="easyTouchSide" , labelType="c")
	public String easyTouchSide;
	
	@Label(label="hardTouchSide" , labelType="c")
	public String hardTouchSide;
	
	@Label(label="hearing" , labelType="c" , fieldType="select" , answers={"normal" , "hypoacusis"})
	public int hearing;
	
	@Label(label="hearingText" , labelType="c" , fieldType="select" , answers={"right" , "left" , "right_and_left"})
	public int hearingText;
	
	@Label(label="reflex" , labelType="c" , fieldType="select" , answers={"save" , "notSave"})
	public int reflex;
	
	@Label(label="otherNervous" , labelType="c")
	public String otherNervous;
	
	@Label(label="sense" , labelType="c")
	public String sense;
	
	@Label(label="endocrineRateText" , labelType="c")
	public String endocrineRateText;
	
	@Label(label="endocrineAdvice" , labelType="p")
	public String endocrineAdvice;

	public String getEndocrinePainText() {
		return endocrinePainText;
	}

	public void setEndocrinePainText(String endocrinePainText) {
		this.endocrinePainText = endocrinePainText;
	}

	public String getEndocrineMedText() {
		return endocrineMedText;
	}

	public void setEndocrineMedText(String endocrineMedText) {
		this.endocrineMedText = endocrineMedText;
	}

	public int getPatienthood() {
		return patienthood;
	}

	public void setPatienthood(int patienthood) {
		this.patienthood = patienthood;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getSkinPituitare() {
		return skinPituitare;
	}

	public void setSkinPituitare(int skinPituitare) {
		this.skinPituitare = skinPituitare;
	}

	public String getSkinPituitareText() {
		return skinPituitareText;
	}

	public void setSkinPituitareText(String skinPituitareText) {
		this.skinPituitareText = skinPituitareText;
	}

	public int getBreathingPerMinute() {
		return breathingPerMinute;
	}

	public void setBreathingPerMinute(int breathingPerMinute) {
		this.breathingPerMinute = breathingPerMinute;
	}

	public int getAuscultation() {
		return auscultation;
	}

	public void setAuscultation(int auscultation) {
		this.auscultation = auscultation;
	}

	public int getAuscultationText() {
		return auscultationText;
	}

	public void setAuscultationText(int auscultationText) {
		this.auscultationText = auscultationText;
	}

	public int getSphygmic() {
		return sphygmic;
	}

	public void setSphygmic(int sphygmic) {
		this.sphygmic = sphygmic;
	}

	public int getCardiolineByKnock() {
		return cardiolineByKnock;
	}

	public void setCardiolineByKnock(int cardiolineByKnock) {
		this.cardiolineByKnock = cardiolineByKnock;
	}

	public int getCardiolineByKnockText() {
		return cardiolineByKnockText;
	}

	public void setCardiolineByKnockText(int cardiolineByKnockText) {
		this.cardiolineByKnockText = cardiolineByKnockText;
	}

	public int getCardiorasperByAuscultation() {
		return cardiorasperByAuscultation;
	}

	public void setCardiorasperByAuscultation(int cardiorasperByAuscultation) {
		this.cardiorasperByAuscultation = cardiorasperByAuscultation;
	}

	public int getCardioarrhythmias() {
		return cardioarrhythmias;
	}

	public void setCardioarrhythmias(int cardioarrhythmias) {
		this.cardioarrhythmias = cardioarrhythmias;
	}

	public boolean isCardioarrhythmiasText() {
		return cardioarrhythmiasText;
	}

	public void setCardioarrhythmiasText(boolean cardioarrhythmiasText) {
		this.cardioarrhythmiasText = cardioarrhythmiasText;
	}

	public String getRightArterialPressure() {
		return rightArterialPressure;
	}

	public void setRightArterialPressure(String rightArterialPressure) {
		this.rightArterialPressure = rightArterialPressure;
	}

	public String getLeftArterialPressure() {
		return leftArterialPressure;
	}

	public void setLeftArterialPressure(String leftArterialPressure) {
		this.leftArterialPressure = leftArterialPressure;
	}

	public int getTongueMoisture() {
		return tongueMoisture;
	}

	public void setTongueMoisture(int tongueMoisture) {
		this.tongueMoisture = tongueMoisture;
	}

	public int getTongueFur() {
		return tongueFur;
	}

	public void setTongueFur(int tongueFur) {
		this.tongueFur = tongueFur;
	}

	public int getAbdomenEasyTouch() {
		return abdomenEasyTouch;
	}

	public void setAbdomenEasyTouch(int abdomenEasyTouch) {
		this.abdomenEasyTouch = abdomenEasyTouch;
	}

	public int getAbdomenHardTouch() {
		return abdomenHardTouch;
	}

	public void setAbdomenHardTouch(int abdomenHardTouch) {
		this.abdomenHardTouch = abdomenHardTouch;
	}

	public String getEasyTouchSide() {
		return easyTouchSide;
	}

	public void setEasyTouchSide(String easyTouchSide) {
		this.easyTouchSide = easyTouchSide;
	}

	public String getHardTouchSide() {
		return hardTouchSide;
	}

	public void setHardTouchSide(String hardTouchSide) {
		this.hardTouchSide = hardTouchSide;
	}

	public int getHearing() {
		return hearing;
	}

	public void setHearing(int hearing) {
		this.hearing = hearing;
	}

	public int getHearingText() {
		return hearingText;
	}

	public void setHearingText(int hearingText) {
		this.hearingText = hearingText;
	}

	public int getReflex() {
		return reflex;
	}

	public void setReflex(int reflex) {
		this.reflex = reflex;
	}

	public String getOtherNervous() {
		return otherNervous;
	}

	public void setOtherNervous(String otherNervous) {
		this.otherNervous = otherNervous;
	}

	public String getSense() {
		return sense;
	}

	public void setSense(String sense) {
		this.sense = sense;
	}

	public String getEndocrineRateText() {
		return endocrineRateText;
	}

	public void setEndocrineRateText(String endocrineRateText) {
		this.endocrineRateText = endocrineRateText;
	}

	public String getEndocrineAdvice() {
		return endocrineAdvice;
	}

	public void setEndocrineAdvice(String endocrineAdvice) {
		this.endocrineAdvice = endocrineAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

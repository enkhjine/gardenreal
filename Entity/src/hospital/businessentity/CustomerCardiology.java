package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerCardiology  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Label(label="chestPain",labelType="p",fieldType="boolean")
	public boolean sternumPain;

	@Label(label="breathe",labelType="p",fieldType="boolean")
	public boolean breathing;
	
	@Label(label="cough",labelType="p",fieldType="boolean")
	public boolean cough;
	
	@Label(label="cardiologyBlueLight",labelType="p",fieldType="boolean")
	public boolean blue;
	
	@Label(label="cardiologyToBeat",labelType="p",fieldType="boolean")
	public boolean heart;
	
	@Label(label="transientToFaint",labelType="p",fieldType="boolean")
	public boolean faintFall;
	
	@Label(label="cardiologyEdema",labelType="p",fieldType="boolean")
	public boolean edema;
	
	@Label(label="otherPain",labelType="p")
	public String otherPain;
	
	@Label(label="whenNowPain",labelType="m")
	public String whenNowPain; 
	
	@Label(label="painHistory",labelType="m")
	public String painHistory; 
	
	@Label(label="lifeHistory",labelType="m")
	public String lifeHistory; 
	
	@Label(label="badCook",labelType="m",fieldType="boolean")
	public boolean cook;
	
	@Label(label="physicalInactivity",labelType="m",fieldType="boolean")
	public boolean physicalInactivity;

	@Label(label="stress",labelType="m",fieldType="boolean")
	public boolean stress;

	@Label(label="obesityCardiology",labelType="m",fieldType="boolean")
	public boolean obesity;
	
	@Label(label="smoking",labelType="m",fieldType="boolean")
	public boolean smoking;
	
	@Label(label="extendCardiology",labelType="m",fieldType="boolean")
	public boolean extend;

	@Label(label="noUseAlcohol",labelType="m",fieldType="boolean")
	public boolean noUseAlcohol;
	
	@Label(label="arterHypertension",labelType="m",fieldType="boolean")
	public boolean arterHypertension;
	
	@Label(label="gipyerkHol",labelType="m",fieldType="boolean")
	public boolean gipyerkHol;
	
	
	@Label(label="diabetes",labelType="m",fieldType="boolean")
	public boolean diabetes;
	
	@Label(label="eruption", labelType="c")
	public String observation;
	
	@Label(label="generalfLook",labelType="c")
	public String generalfLook;
	
	@Label(label="eruption" ,labelType="c")
	public String cardioLocation;
	
	@Label(label="skinBlue",labelType="c")
	public String skinBlue;
	
	@Label(label="edemaAny",labelType="c")
	public String edemaAny;
	
	@Label(label="venousPulse",labelType="c")
	public String venousPulse;
	
	@Label(label="eruption",labelType="c")
	public String touch;
	
	@Label(label="heartApical",labelType="c")
	public String heartApical;
	
	@Label(label="arterPulse",labelType="c")
	public String arterPulse;
	
	@Label(label="eruption",labelType="c")
	public String tab;

	@Label(label="heartLimit",labelType="c")
	public String heartLimit;
	
	@Label(label="eruption",labelType="c")
	public String listen;
	
	@Label(label="heartAudio",labelType="c")
	public String heartAudio;
	
	@Label(label="heartDust",labelType="c")
	public String heartDust;
	
	@Label(label="aDMeasurement",labelType="c")
	public String aDMeasurement;
	
	@Label(label="cardinoRateText",labelType="c")
	public String cardinoRateText;
	
	@Label(label="advice",labelType="p")
	public String advice;
	
	public boolean isSternumPain() {
		return sternumPain;
	}

	public void setSternumPain(boolean sternumPain) {
		this.sternumPain = sternumPain;
	}

	public boolean isBreathing() {
		return breathing;
	}

	public void setBreathing(boolean breathing) {
		this.breathing = breathing;
	}

	public boolean isCough() {
		return cough;
	}

	public void setCough(boolean cough) {
		this.cough = cough;
	}

	public boolean isBlue() {
		return blue;
	}

	public void setBlue(boolean blue) {
		this.blue = blue;
	}

	public boolean isHeart() {
		return heart;
	}

	public void setHeart(boolean heart) {
		this.heart = heart;
	}

	public boolean isFaintFall() {
		return faintFall;
	}

	public void setFaintFall(boolean faintFall) {
		this.faintFall = faintFall;
	}

	public boolean isEdema() {
		return edema;
	}

	public void setEdema(boolean edema) {
		this.edema = edema;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getWhenNowPain() {
		return whenNowPain;
	}

	public void setWhenNowPain(String whenNowPain) {
		this.whenNowPain = whenNowPain;
	}

	public String getPainHistory() {
		return painHistory;
	}

	public void setPainHistory(String painHistory) {
		this.painHistory = painHistory;
	}

	public String getLifeHistory() {
		return lifeHistory;
	}

	public void setLifeHistory(String lifeHistory) {
		this.lifeHistory = lifeHistory;
	}

	public boolean isCook() {
		return cook;
	}

	public void setCook(boolean cook) {
		this.cook = cook;
	}

	public boolean isPhysicalInactivity() {
		return physicalInactivity;
	}

	public void setPhysicalInactivity(boolean physicalInactivity) {
		this.physicalInactivity = physicalInactivity;
	}

	public boolean isStress() {
		return stress;
	}

	public void setStress(boolean stress) {
		this.stress = stress;
	}

	public boolean isObesity() {
		return obesity;
	}

	public void setObesity(boolean obesity) {
		this.obesity = obesity;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public boolean isNoUseAlcohol() {
		return noUseAlcohol;
	}

	public void setNoUseAlcohol(boolean noUseAlcohol) {
		this.noUseAlcohol = noUseAlcohol;
	}

	public boolean isArterHypertension() {
		return arterHypertension;
	}

	public void setArterHypertension(boolean arterHypertension) {
		this.arterHypertension = arterHypertension;
	}

	public boolean isGipyerkHol() {
		return gipyerkHol;
	}

	public void setGipyerkHol(boolean gipyerkHol) {
		this.gipyerkHol = gipyerkHol;
	}

	public boolean isDiabetes() {
		return diabetes;
	}

	public void setDiabetes(boolean diabetes) {
		this.diabetes = diabetes;
	}

	public String getGeneralfLook() {
		return generalfLook;
	}

	public void setGeneralfLook(String generalfLook) {
		this.generalfLook = generalfLook;
	}

	public String getSkinBlue() {
		return skinBlue;
	}

	public void setSkinBlue(String skinBlue) {
		this.skinBlue = skinBlue;
	}

	public String getEdemaAny() {
		return edemaAny;
	}

	public void setEdemaAny(String edemaAny) {
		this.edemaAny = edemaAny;
	}

	public String getVenousPulse() {
		return venousPulse;
	}

	public void setVenousPulse(String venousPulse) {
		this.venousPulse = venousPulse;
	}

	public String getHeartApical() {
		return heartApical;
	}

	public void setHeartApical(String heartApical) {
		this.heartApical = heartApical;
	}

	public String getArterPulse() {
		return arterPulse;
	}

	public void setArterPulse(String arterPulse) {
		this.arterPulse = arterPulse;
	}

	public String getHeartLimit() {
		return heartLimit;
	}

	public void setHeartLimit(String heartLimit) {
		this.heartLimit = heartLimit;
	}

	public String getHeartAudio() {
		return heartAudio;
	}

	public void setHeartAudio(String heartAudio) {
		this.heartAudio = heartAudio;
	}

	public String getHeartDust() {
		return heartDust;
	}

	public void setHeartDust(String heartDust) {
		this.heartDust = heartDust;
	}

	public String getaDMeasurement() {
		return aDMeasurement;
	}

	public void setaDMeasurement(String aDMeasurement) {
		this.aDMeasurement = aDMeasurement;
	}

	public boolean isExtend() {
		return extend;
	}

	public void setExtend(boolean extend) {
		this.extend = extend;
	}

	public String getCardinoRateText() {
		return cardinoRateText;
	}

	public void setCardinoRateText(String cardinoRateText) {
		this.cardinoRateText = cardinoRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}

	public String getListen() {
		return listen;
	}

	public void setListen(String listen) {
		this.listen = listen;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getCardioLocation() {
		return cardioLocation;
	}

	public void setCardioLocation(String cardioLocation) {
		this.cardioLocation = cardioLocation;
	}

	public String getTouch() {
		return touch;
	}

	public void setTouch(String touch) {
		this.touch = touch;
	}
	

	
}

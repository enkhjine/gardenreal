package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerTubeInflammation {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="hoopCoughLong" , labelType="p" , fieldType="boolean")
	public boolean hoopCoughLong;
	
	@Label(label="drySpit" , labelType="p" , fieldType="boolean")
	public boolean drySpit;
	
	@Label(label="soreThroat" , labelType="p" , fieldType="boolean")
	public boolean soreThroat;
	
	@Label(label="haveRunningNose" , labelType="p" , fieldType="boolean")
	public boolean haveRunningNose;
	
	@Label(label="hoarsen" , labelType="p" , fieldType="boolean")
	public boolean hoarsen;
	
	@Label(label="backSternum" , labelType="p" , fieldType="boolean")
	public boolean backSternum;
	
	@Label(label="kittle" , labelType="p" , fieldType="boolean")
	public boolean kittle;
	
	@Label(label="throatSpite" , labelType="p" , fieldType="boolean")
	public boolean throatSpite;
	
	@Label(label="throatGettingHot" , labelType="p" , fieldType="boolean")
	public boolean throatGettingHot;
	
	@Label(label="achromoderma" , labelType="p" , fieldType="boolean")
	public boolean achromoderma;
	
	@Label(label="noHyperpyrexia" , labelType="p" , fieldType="boolean")
	public boolean noHyperpyrexia;
	
	@Label(label="anger" , labelType="p" , fieldType="boolean")
	public boolean anger;
	
	@Label(label="noAppetite" , labelType="p" , fieldType="boolean")
	public boolean noAppetite;
	
	@Label(label="tired" , labelType="p" , fieldType="boolean")
	public boolean tired;
	
	@Label(label="nervousSyndrome" , labelType="p" , fieldType="boolean")
	public boolean nervousSyndrome;
	
	@Label(label="tubeHyperpyrexia" , labelType="p" , fieldType="boolean")
	public boolean tubeHyperpyrexia;
	
	@Label(label="tubeInflammationPainText" , labelType="p")
	public String tubeInflammationPainText;
	
	@Label(label="whenRun" , labelType="m" , fieldType="boolean")
	public boolean whenRun;
	
	@Label(label="breathChill" , labelType="m" , fieldType="boolean")
	public boolean breathChill;
	
	@Label(label="nightAndBedHyperpyrexia" , labelType="m" , fieldType="boolean")
	public boolean nightAndBedHyperpyrexia;
	
	@Label(label="aggressiveDry" , labelType="m" , fieldType="boolean")
	public boolean aggressiveDry;
	
	@Label(label="coughLongThenSpit" , labelType="m" , fieldType="boolean")
	public boolean coughLongThenSpit;
	
	@Label(label="foamy" , labelType="m" , fieldType="boolean")
	public boolean foamy;
	
	@Label(label="noCoughUpPhlegm" , labelType="m" , fieldType="boolean")
	public boolean noCoughUpPhlegm;
	
	@Label(label="whitishSlimy" , labelType="m" , fieldType="boolean")
	public boolean whitishSlimy;
	
	@Label(label="isHyperpyrexia" , labelType="m" , fieldType="select" , answers={"noHyperpyrexia" , "slightHyperpyrexia"})
	public int isHyperpyrexia;
	
	@Label(label="isAngry" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isAngry;
	
	@Label(label="tubeInflammationMedText" , labelType="m")
	public String tubeInflammationMedText;
	
	@Label(label="skinHood" , labelType="c" , fieldType="select" , answers={"normal" , "dry" , "pale"})
	public int skinHood;
	
	@Label(label="tonsils" , labelType="c" , fieldType="select" , answers={"normal" , "blush" , "swell"})
	public int tonsils;
	
	@Label(label="breathMuscle" , labelType="c" , fieldType="select" , answers={"part" , "notPart"})
	public int breathMuscle;
	
	@Label(label="chestAuscultate" , labelType="c" , fieldType="select" , answers={"aggressive" , "caDry" , "caWet"})
	public int chestAuscultate;
	
	@Label(label="chestAuscultate1" , labelType="c" , fieldType="select" , answers={"murmur" , "crump" , "whistle"})
	public int chestAuscultate1;
	
	@Label(label="chestAuscultate2" , labelType="c" , fieldType="select" , answers={"bigFoamy" , "mediumFoamy" , "unstableHear"})
	public int chestAuscultate2;
	
	@Label(label="chestKnock" , labelType="c")
	public String chestKnock;
	
	@Label(label="tympanicVoice" , labelType="c")
	public String tympanicVoice;
	
	@Label(label="tubeInflammationRateText" , labelType="p")
 	public String tubeInflammationRateText;
 	
 	@Label(label="tubeInflammationAdvice" , labelType="c")
 	public String tubeInflammationAdvice;

	public boolean isHoopCoughLong() {
		return hoopCoughLong;
	}

	public void setHoopCoughLong(boolean hoopCoughLong) {
		this.hoopCoughLong = hoopCoughLong;
	}

	public boolean isDrySpit() {
		return drySpit;
	}

	public void setDrySpit(boolean drySpit) {
		this.drySpit = drySpit;
	}

	public boolean isSoreThroat() {
		return soreThroat;
	}

	public void setSoreThroat(boolean soreThroat) {
		this.soreThroat = soreThroat;
	}

	public boolean isHaveRunningNose() {
		return haveRunningNose;
	}

	public void setHaveRunningNose(boolean haveRunningNose) {
		this.haveRunningNose = haveRunningNose;
	}

	public boolean isHoarsen() {
		return hoarsen;
	}

	public void setHoarsen(boolean hoarsen) {
		this.hoarsen = hoarsen;
	}

	public boolean isBackSternum() {
		return backSternum;
	}

	public void setBackSternum(boolean backSternum) {
		this.backSternum = backSternum;
	}

	public boolean isKittle() {
		return kittle;
	}

	public void setKittle(boolean kittle) {
		this.kittle = kittle;
	}

	public boolean isThroatSpite() {
		return throatSpite;
	}

	public void setThroatSpite(boolean throatSpite) {
		this.throatSpite = throatSpite;
	}

	public boolean isThroatGettingHot() {
		return throatGettingHot;
	}

	public void setThroatGettingHot(boolean throatGettingHot) {
		this.throatGettingHot = throatGettingHot;
	}

	public boolean isAchromoderma() {
		return achromoderma;
	}

	public void setAchromoderma(boolean achromoderma) {
		this.achromoderma = achromoderma;
	}

	public boolean isNoHyperpyrexia() {
		return noHyperpyrexia;
	}

	public void setNoHyperpyrexia(boolean noHyperpyrexia) {
		this.noHyperpyrexia = noHyperpyrexia;
	}

	public boolean isAnger() {
		return anger;
	}

	public void setAnger(boolean anger) {
		this.anger = anger;
	}

	public boolean isNoAppetite() {
		return noAppetite;
	}

	public void setNoAppetite(boolean noAppetite) {
		this.noAppetite = noAppetite;
	}

	public boolean isTired() {
		return tired;
	}

	public void setTired(boolean tired) {
		this.tired = tired;
	}

	public boolean isNervousSyndrome() {
		return nervousSyndrome;
	}

	public void setNervousSyndrome(boolean nervousSyndrome) {
		this.nervousSyndrome = nervousSyndrome;
	}

	public boolean isTubeHyperpyrexia() {
		return tubeHyperpyrexia;
	}

	public void setTubeHyperpyrexia(boolean tubeHyperpyrexia) {
		this.tubeHyperpyrexia = tubeHyperpyrexia;
	}

	public String getTubeInflammationPainText() {
		return tubeInflammationPainText;
	}

	public void setTubeInflammationPainText(String tubeInflammationPainText) {
		this.tubeInflammationPainText = tubeInflammationPainText;
	}

	public boolean isWhenRun() {
		return whenRun;
	}

	public void setWhenRun(boolean whenRun) {
		this.whenRun = whenRun;
	}

	public boolean isBreathChill() {
		return breathChill;
	}

	public void setBreathChill(boolean breathChill) {
		this.breathChill = breathChill;
	}

	public boolean isNightAndBedHyperpyrexia() {
		return nightAndBedHyperpyrexia;
	}

	public void setNightAndBedHyperpyrexia(boolean nightAndBedHyperpyrexia) {
		this.nightAndBedHyperpyrexia = nightAndBedHyperpyrexia;
	}

	public boolean isAggressiveDry() {
		return aggressiveDry;
	}

	public void setAggressiveDry(boolean aggressiveDry) {
		this.aggressiveDry = aggressiveDry;
	}

	public boolean isCoughLongThenSpit() {
		return coughLongThenSpit;
	}

	public void setCoughLongThenSpit(boolean coughLongThenSpit) {
		this.coughLongThenSpit = coughLongThenSpit;
	}

	public boolean isFoamy() {
		return foamy;
	}

	public void setFoamy(boolean foamy) {
		this.foamy = foamy;
	}

	public boolean isNoCoughUpPhlegm() {
		return noCoughUpPhlegm;
	}

	public void setNoCoughUpPhlegm(boolean noCoughUpPhlegm) {
		this.noCoughUpPhlegm = noCoughUpPhlegm;
	}

	public boolean isWhitishSlimy() {
		return whitishSlimy;
	}

	public void setWhitishSlimy(boolean whitishSlimy) {
		this.whitishSlimy = whitishSlimy;
	}

	public int getIsHyperpyrexia() {
		return isHyperpyrexia;
	}

	public void setIsHyperpyrexia(int isHyperpyrexia) {
		this.isHyperpyrexia = isHyperpyrexia;
	}

	public int getIsAngry() {
		return isAngry;
	}

	public void setIsAngry(int isAngry) {
		this.isAngry = isAngry;
	}

	public String getTubeInflammationMedText() {
		return tubeInflammationMedText;
	}

	public void setTubeInflammationMedText(String tubeInflammationMedText) {
		this.tubeInflammationMedText = tubeInflammationMedText;
	}

	public int getSkinHood() {
		return skinHood;
	}

	public void setSkinHood(int skinHood) {
		this.skinHood = skinHood;
	}

	public int getTonsils() {
		return tonsils;
	}

	public void setTonsils(int tonsils) {
		this.tonsils = tonsils;
	}

	public int getBreathMuscle() {
		return breathMuscle;
	}

	public void setBreathMuscle(int breathMuscle) {
		this.breathMuscle = breathMuscle;
	}

	public int getChestAuscultate() {
		return chestAuscultate;
	}

	public void setChestAuscultate(int chestAuscultate) {
		this.chestAuscultate = chestAuscultate;
	}

	public String getChestKnock() {
		return chestKnock;
	}

	public void setChestKnock(String chestKnock) {
		this.chestKnock = chestKnock;
	}

	public String getTympanicVoice() {
		return tympanicVoice;
	}

	public void setTympanicVoice(String tympanicVoice) {
		this.tympanicVoice = tympanicVoice;
	}

	public String getTubeInflammationRateText() {
		return tubeInflammationRateText;
	}

	public void setTubeInflammationRateText(String tubeInflammationRateText) {
		this.tubeInflammationRateText = tubeInflammationRateText;
	}

	public String getTubeInflammationAdvice() {
		return tubeInflammationAdvice;
	}

	public void setTubeInflammationAdvice(String tubeInflammationAdvice) {
		this.tubeInflammationAdvice = tubeInflammationAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getChestAuscultate1() {
		return chestAuscultate1;
	}

	public void setChestAuscultate1(int chestAuscultate1) {
		this.chestAuscultate1 = chestAuscultate1;
	}

	public int getChestAuscultate2() {
		return chestAuscultate2;
	}

	public void setChestAuscultate2(int chestAuscultate2) {
		this.chestAuscultate2 = chestAuscultate2;
	}


}

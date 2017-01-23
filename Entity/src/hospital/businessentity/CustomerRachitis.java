package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerRachitis {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="rachitisPanic" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisPanic;
	
	@Label(label="badForSleep" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int badForSleep;
	
	@Label(label="sweatWhenSleepAndSuckle" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int sweatWhenSleepAndSuckle;
	
	@Label(label="rachitisHead" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisHead;
	
	@Label(label="nuchaHair" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int nuchaHair;
	
	@Label(label="rachitisWorried" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisWorried;
	
	@Label(label="foreHead" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int foreHead;
	
	@Label(label="rachitisTooth" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisTooth;
	
	@Label(label="rachitisChest" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisChest;
	
	@Label(label="rachitisBandy" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisBandy;
	
	@Label(label="rachitisBack" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisBack;
	
	@Label(label="rachitisPee" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisPee;
	
	@Label(label="rachitisFoot" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisFoot;
	
	@Label(label="rachitisPainText" , labelType="p")
	public String rachitisPainText;
	
	@Label(label="rachitisWhen" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public String rachitisWhen;
	
	@Label(label="rachitisDVitamin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisDVitamin;
	
	@Label(label="usingDVitamin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int usingDVitamin;
	
	@Label(label="doseDVitamin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int doseDVitamin;
	
	@Label(label="howlongDVitamin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int howlongDVitamin;
	
	@Label(label="rachitisBefore" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisBefore;
	
	@Label(label="rachitisAir" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisAir;
	
	@Label(label="rachitisSun" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisSun;
	
	@Label(label="rachitisFood" , labelType="m" , fieldType="select" , answers={"ranormal" , "additionalFeed" , "tits"})
	public int rachitisFood;
	
	@Label(label="rachitisSuckleFor6months" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisSuckleFor6months;
	
	@Label(label="additionalFood" , labelType="m")
	public String additionalFood;
	
	@Label(label="foodInc" , labelType="m")
	public String foodInc;
	
	@Label(label="foodSequence" , labelType="m")
	public String foodSequence;
	
	@Label(label="momChild" , labelType="m")
	public String momChild;
	
	@Label(label="rachitisBirthOnTime" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisBirthOnTime;
	
	@Label(label="rachitisBirthWeight" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisBirthWeight;
	
	@Label(label="rachitisGrowUp" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisGrowUp;
	
	@Label(label="rachitisGeneration" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisGeneration;
	
	@Label(label="rachitisAllergy" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int rachitisAllergy;
	
	@Label(label="rachitisMedText" , labelType="m")
	public String rachitisMedText;
	
	@Label(label="worriedPanic" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int worriedPanic;
	
	@Label(label="sleepBad" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int sleepBad;
	
	@Label(label="sweatly" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int sweatly;
	
	@Label(label="emotionBad" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int emotionBad;
	
	@Label(label="emotionDecrease" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int emotionDecrease;
	
	@Label(label="nuchaHairLoss" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int nuchaHairLoss;
	
	@Label(label="gettingWet" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int gettingWet;
	
	@Label(label="amyotonia" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int amyotonia;
	
	@Label(label="poopBarken" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int poopBarken;
	
	@Label(label="frogAbdomen" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int frogAbdomen;
	
	@Label(label="parietalBone" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int parietalBone;
	
	@Label(label="nuchaBone" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int nuchaBone;
	
	@Label(label="dentition" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int dentition;
	
	@Label(label="pectus" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int pectus;
	
	@Label(label="rachides" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int rachides;
	
	@Label(label="squadHead" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int squadHead;
	
	@Label(label="hollowForehead" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int hollowForehead;
	
	@Label(label="saddleNose" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int saddleNose;
	
	@Label(label="pectusChanged" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int pectusChanged;
	
	@Label(label="rachidesChange" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int rachidesChange;
	
	@Label(label="narrowPelvis" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int narrowPelvis;
	
	@Label(label="bowlegOX" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int bowlegOX;
	
	@Label(label="lungInflammation" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int lungInflammation;
	
	@Label(label="liverEnlargement" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int liverEnlargement;
	
	@Label(label="spleenEnlargement" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int spleenEnlargement;
	
	@Label(label="spanaemia" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int spanaemia;
	
	@Label(label="caAndP" , labelType="c" , fieldType="select" , answers={"normal" , "decrease"})
	public int caAndP;
	
	@Label(label="alkalinity" , labelType="c" , fieldType="select" , answers={"normal" , "decrease"})
	public int alkalinity;
	
	@Label(label="dVitamin" , labelType="c" , fieldType="select" , answers={"normal" , "decrease" , "increase"})
	public int dVitamin;
	
	@Label(label="rentgen" , labelType="c", fieldType="select" , answers={"reabnormal" , "renormal"})
	public int rentgen;
	
	@Label(label="rachitisRateText" , labelType="p")
	public String rachitisRateText;
	
	@Label(label="rachitisAdvice" , labelType="c")
	public String rachitisAdvice;

	public int getRachitisPanic() {
		return rachitisPanic;
	}

	public void setRachitisPanic(int rachitisPanic) {
		this.rachitisPanic = rachitisPanic;
	}

	public int getBadForSleep() {
		return badForSleep;
	}

	public void setBadForSleep(int badForSleep) {
		this.badForSleep = badForSleep;
	}

	public int getSweatWhenSleepAndSuckle() {
		return sweatWhenSleepAndSuckle;
	}

	public void setSweatWhenSleepAndSuckle(int sweatWhenSleepAndSuckle) {
		this.sweatWhenSleepAndSuckle = sweatWhenSleepAndSuckle;
	}

	public int getRachitisHead() {
		return rachitisHead;
	}

	public void setRachitisHead(int rachitisHead) {
		this.rachitisHead = rachitisHead;
	}

	public int getNuchaHair() {
		return nuchaHair;
	}

	public void setNuchaHair(int nuchaHair) {
		this.nuchaHair = nuchaHair;
	}

	public int getRachitisWorried() {
		return rachitisWorried;
	}

	public void setRachitisWorried(int rachitisWorried) {
		this.rachitisWorried = rachitisWorried;
	}

	public int getForeHead() {
		return foreHead;
	}

	public void setForeHead(int foreHead) {
		this.foreHead = foreHead;
	}

	public int getRachitisTooth() {
		return rachitisTooth;
	}

	public void setRachitisTooth(int rachitisTooth) {
		this.rachitisTooth = rachitisTooth;
	}

	public int getRachitisChest() {
		return rachitisChest;
	}

	public void setRachitisChest(int rachitisChest) {
		this.rachitisChest = rachitisChest;
	}

	public int getRachitisBandy() {
		return rachitisBandy;
	}

	public void setRachitisBandy(int rachitisBandy) {
		this.rachitisBandy = rachitisBandy;
	}

	public int getRachitisBack() {
		return rachitisBack;
	}

	public void setRachitisBack(int rachitisBack) {
		this.rachitisBack = rachitisBack;
	}

	public int getRachitisPee() {
		return rachitisPee;
	}

	public void setRachitisPee(int rachitisPee) {
		this.rachitisPee = rachitisPee;
	}

	public int getRachitisFoot() {
		return rachitisFoot;
	}

	public void setRachitisFoot(int rachitisFoot) {
		this.rachitisFoot = rachitisFoot;
	}

	public String getRachitisPainText() {
		return rachitisPainText;
	}

	public void setRachitisPainText(String rachitisPainText) {
		this.rachitisPainText = rachitisPainText;
	}

	public String getRachitisWhen() {
		return rachitisWhen;
	}

	public void setRachitisWhen(String rachitisWhen) {
		this.rachitisWhen = rachitisWhen;
	}

	public int getRachitisDVitamin() {
		return rachitisDVitamin;
	}

	public void setRachitisDVitamin(int rachitisDVitamin) {
		this.rachitisDVitamin = rachitisDVitamin;
	}

	public int getUsingDVitamin() {
		return usingDVitamin;
	}

	public void setUsingDVitamin(int usingDVitamin) {
		this.usingDVitamin = usingDVitamin;
	}

	public int getDoseDVitamin() {
		return doseDVitamin;
	}

	public void setDoseDVitamin(int doseDVitamin) {
		this.doseDVitamin = doseDVitamin;
	}

	public int getHowlongDVitamin() {
		return howlongDVitamin;
	}

	public void setHowlongDVitamin(int howlongDVitamin) {
		this.howlongDVitamin = howlongDVitamin;
	}

	public int getRachitisBefore() {
		return rachitisBefore;
	}

	public void setRachitisBefore(int rachitisBefore) {
		this.rachitisBefore = rachitisBefore;
	}

	public int getRachitisAir() {
		return rachitisAir;
	}

	public void setRachitisAir(int rachitisAir) {
		this.rachitisAir = rachitisAir;
	}

	public int getRachitisSun() {
		return rachitisSun;
	}

	public void setRachitisSun(int rachitisSun) {
		this.rachitisSun = rachitisSun;
	}

	public int getRachitisFood() {
		return rachitisFood;
	}

	public void setRachitisFood(int rachitisFood) {
		this.rachitisFood = rachitisFood;
	}

	public int getRachitisSuckleFor6months() {
		return rachitisSuckleFor6months;
	}

	public void setRachitisSuckleFor6months(int rachitisSuckleFor6months) {
		this.rachitisSuckleFor6months = rachitisSuckleFor6months;
	}

	public String getAdditionalFood() {
		return additionalFood;
	}

	public void setAdditionalFood(String additionalFood) {
		this.additionalFood = additionalFood;
	}

	public String getFoodInc() {
		return foodInc;
	}

	public void setFoodInc(String foodInc) {
		this.foodInc = foodInc;
	}

	public String getFoodSequence() {
		return foodSequence;
	}

	public void setFoodSequence(String foodSequence) {
		this.foodSequence = foodSequence;
	}

	public String getMomChild() {
		return momChild;
	}

	public void setMomChild(String momChild) {
		this.momChild = momChild;
	}

	public int getRachitisBirthOnTime() {
		return rachitisBirthOnTime;
	}

	public void setRachitisBirthOnTime(int rachitisBirthOnTime) {
		this.rachitisBirthOnTime = rachitisBirthOnTime;
	}

	public int getRachitisBirthWeight() {
		return rachitisBirthWeight;
	}

	public void setRachitisBirthWeight(int rachitisBirthWeight) {
		this.rachitisBirthWeight = rachitisBirthWeight;
	}

	public int getRachitisGrowUp() {
		return rachitisGrowUp;
	}

	public void setRachitisGrowUp(int rachitisGrowUp) {
		this.rachitisGrowUp = rachitisGrowUp;
	}

	public int getRachitisGeneration() {
		return rachitisGeneration;
	}

	public void setRachitisGeneration(int rachitisGeneration) {
		this.rachitisGeneration = rachitisGeneration;
	}

	public int getRachitisAllergy() {
		return rachitisAllergy;
	}

	public void setRachitisAllergy(int rachitisAllergy) {
		this.rachitisAllergy = rachitisAllergy;
	}

	public String getRachitisMedText() {
		return rachitisMedText;
	}

	public void setRachitisMedText(String rachitisMedText) {
		this.rachitisMedText = rachitisMedText;
	}

	public int getWorriedPanic() {
		return worriedPanic;
	}

	public void setWorriedPanic(int worriedPanic) {
		this.worriedPanic = worriedPanic;
	}

	public int getSleepBad() {
		return sleepBad;
	}

	public void setSleepBad(int sleepBad) {
		this.sleepBad = sleepBad;
	}

	public int getSweatly() {
		return sweatly;
	}

	public void setSweatly(int sweatly) {
		this.sweatly = sweatly;
	}

	public int getEmotionBad() {
		return emotionBad;
	}

	public void setEmotionBad(int emotionBad) {
		this.emotionBad = emotionBad;
	}

	public int getEmotionDecrease() {
		return emotionDecrease;
	}

	public void setEmotionDecrease(int emotionDecrease) {
		this.emotionDecrease = emotionDecrease;
	}

	public int getNuchaHairLoss() {
		return nuchaHairLoss;
	}

	public void setNuchaHairLoss(int nuchaHairLoss) {
		this.nuchaHairLoss = nuchaHairLoss;
	}

	public int getGettingWet() {
		return gettingWet;
	}

	public void setGettingWet(int gettingWet) {
		this.gettingWet = gettingWet;
	}

	public int getAmyotonia() {
		return amyotonia;
	}

	public void setAmyotonia(int amyotonia) {
		this.amyotonia = amyotonia;
	}

	public int getPoopBarken() {
		return poopBarken;
	}

	public void setPoopBarken(int poopBarken) {
		this.poopBarken = poopBarken;
	}

	public int getFrogAbdomen() {
		return frogAbdomen;
	}

	public void setFrogAbdomen(int frogAbdomen) {
		this.frogAbdomen = frogAbdomen;
	}

	public int getParietalBone() {
		return parietalBone;
	}

	public void setParietalBone(int parietalBone) {
		this.parietalBone = parietalBone;
	}

	public int getNuchaBone() {
		return nuchaBone;
	}

	public void setNuchaBone(int nuchaBone) {
		this.nuchaBone = nuchaBone;
	}

	public int getDentition() {
		return dentition;
	}

	public void setDentition(int dentition) {
		this.dentition = dentition;
	}

	public int getPectus() {
		return pectus;
	}

	public void setPectus(int pectus) {
		this.pectus = pectus;
	}

	public int getRachides() {
		return rachides;
	}

	public void setRachides(int rachides) {
		this.rachides = rachides;
	}

	public int getSquadHead() {
		return squadHead;
	}

	public void setSquadHead(int squadHead) {
		this.squadHead = squadHead;
	}

	public int getHollowForehead() {
		return hollowForehead;
	}

	public void setHollowForehead(int hollowForehead) {
		this.hollowForehead = hollowForehead;
	}

	public int getSaddleNose() {
		return saddleNose;
	}

	public void setSaddleNose(int saddleNose) {
		this.saddleNose = saddleNose;
	}

	public int getPectusChanged() {
		return pectusChanged;
	}

	public void setPectusChanged(int pectusChanged) {
		this.pectusChanged = pectusChanged;
	}

	public int getRachidesChange() {
		return rachidesChange;
	}

	public void setRachidesChange(int rachidesChange) {
		this.rachidesChange = rachidesChange;
	}

	public int getNarrowPelvis() {
		return narrowPelvis;
	}

	public void setNarrowPelvis(int narrowPelvis) {
		this.narrowPelvis = narrowPelvis;
	}

	public int getBowlegOX() {
		return bowlegOX;
	}

	public void setBowlegOX(int bowlegOX) {
		this.bowlegOX = bowlegOX;
	}

	public int getLungInflammation() {
		return lungInflammation;
	}

	public void setLungInflammation(int lungInflammation) {
		this.lungInflammation = lungInflammation;
	}

	public int getLiverEnlargement() {
		return liverEnlargement;
	}

	public void setLiverEnlargement(int liverEnlargement) {
		this.liverEnlargement = liverEnlargement;
	}

	public int getSpleenEnlargement() {
		return spleenEnlargement;
	}

	public void setSpleenEnlargement(int spleenEnlargement) {
		this.spleenEnlargement = spleenEnlargement;
	}

	public int getSpanaemia() {
		return spanaemia;
	}

	public void setSpanaemia(int spanaemia) {
		this.spanaemia = spanaemia;
	}

	public int getCaAndP() {
		return caAndP;
	}

	public void setCaAndP(int caAndP) {
		this.caAndP = caAndP;
	}

	public int getAlkalinity() {
		return alkalinity;
	}

	public void setAlkalinity(int alkalinity) {
		this.alkalinity = alkalinity;
	}

	public int getdVitamin() {
		return dVitamin;
	}

	public void setdVitamin(int dVitamin) {
		this.dVitamin = dVitamin;
	}

	public int getRentgen() {
		return rentgen;
	}

	public void setRentgen(int rentgen) {
		this.rentgen = rentgen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRachitisRateText() {
		return rachitisRateText;
	}

	public void setRachitisRateText(String rachitisRateText) {
		this.rachitisRateText = rachitisRateText;
	}

	public String getRachitisAdvice() {
		return rachitisAdvice;
	}

	public void setRachitisAdvice(String rachitisAdvice) {
		this.rachitisAdvice = rachitisAdvice;
	}
	
	

}

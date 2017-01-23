package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerDiarrhoea {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="keck" , labelType="p" , fieldType="boolean")
	public boolean keck;
	
	@Label(label="slushyDefecate" , labelType="p" , fieldType="boolean")
	public boolean slushyDefecate;
	
	@Label(label="hyperpyrexia" , labelType="p" , fieldType="boolean")
	public boolean hyperpyrexia;
	
	@Label(label="extroversion" , labelType="p" , fieldType="boolean")
	public boolean extroversion;
	
	@Label(label="stomachPain" , labelType="p" , fieldType="boolean")
	public boolean stomachPain;
	
	@Label(label="footPain" , labelType="p" , fieldType="boolean")
	public boolean footPain;
	
	@Label(label="abdomenColic" , labelType="p" , fieldType="boolean")
	public boolean abdomenColic;
	
	@Label(label="tobeThirsty" , labelType="p" , fieldType="boolean")
	public boolean tobeThirsty;
	
	@Label(label="parchmentSkin" , labelType="p" , fieldType="boolean")
	public boolean parchmentSkin;
	
	@Label(label="heartPain" , labelType="p" , fieldType="boolean")
	public boolean heartPain;
	
	@Label(label="peeDecrease" , labelType="p" , fieldType="boolean")
	public boolean peeDecrease;
	
	@Label(label="exhausted" , labelType="p" , fieldType="boolean")
	public boolean exhausted;
	
	@Label(label="gettingTired" , labelType="p" , fieldType="boolean")
	public boolean gettingTired;
	
	@Label(label="gettingHeadache" , labelType="p" , fieldType="boolean")
	public boolean gettingHeadache;
	
	@Label(label="snivel" , labelType="p" , fieldType="boolean")
	public boolean snivel;
	
	@Label(label="tympanumInfection" , labelType="p" , fieldType="boolean")
	public boolean tympanumInfection;
	
	@Label(label="breathInfection" , labelType="p" , fieldType="boolean")
	public boolean breathInfection;
	
	@Label(label="chronicInfection" , labelType="p" , fieldType="boolean")
	public boolean chronicInfection;
	
	@Label(label="diarrhoeaPainText" , labelType="p")
	public String diarrhoeaPainText;
	
	@Label(label="yes" , labelType="m" , fieldType="boolean")
	public boolean yes;
	
	@Label(label="threeAndMore" , labelType="m" , fieldType="boolean")
	public boolean threeAndMore;
	
	@Label(label="toDiarrhoea" , labelType="m" , fieldType="boolean")
	public boolean toDiarrhoea;

	@Label(label="toDiarrhoeaPerDay" , labelType="m" , fieldType="boolean")
	public boolean toDiarrhoeaPerDay;
	
	@Label(label="toDiarrhoeaDays" , labelType="m" , fieldType="select" , answers={"14daysAndMore" , "lessThan14days"})
	public int toDiarrhoeaDays;
	
	@Label(label="diarrhoeaVolume" , labelType="m" , fieldType="select" , answers={"vNormal" , "less" , "more"})
	public int diarrhoeaVolume;
	
	@Label(label="poopImpurity" , labelType="m" , fieldType="select" , answers={"liquidFood" , "mucilagewithFood" , "mucilageAndBlood"})
	public int poopImpurity;
	
	@Label(label="whenPoop" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int whenPoop;
	
	@Label(label="whenPoopKeck" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int whenPoopKeck;
	
	@Label(label="whenPain" , labelType="m")
	public String whenPain;
	
	@Label(label="doctor" , labelType="m")
	public String doctor;
	
	@Label(label="usedPharmacy" , labelType="m")
	public String usedPharmacy;
	
	@Label(label="diarhoeaCause" , labelType="m" , fieldType="select" , answers={"badFood" , "nearlyDiarrhoea" , "otherInfection"})
	public int diarhoeaCause;
	
	@Label(label="livingCondition" , labelType="m" , fieldType="select" , answers={"normal" , "badCondition"})
	public int livingCondition;
	
	@Label(label="family" , labelType="m" , fieldType="select" , answers={"familyLot" , "familyFew"})
	public int family;
	
	@Label(label="withDiarrhoeaLast6Months" , labelType="m")
	public String withDiarrhoeaLast6Months;
	
	@Label(label="wentOtherCountries" , labelType="m")
	public String wentOtherCountries;
	
	@Label(label="donorLast6Months" , labelType="m")
	public String donorLast6Months;
	
	@Label(label="haveNearlyDiarrhoea" , labelType="m")
	public String haveNearlyDiarrhoea;
	
	@Label(label="drinkNotFureWater" , labelType="m")
	public String drinkNotFureWater;
	
	@Label(label="havePet" , labelType="m")
	public String havePet;
	
	@Label(label="shareSpoonOrCup" , labelType="m")
	public String shareSpoonOrCup;
	
	@Label(label="lastFood" , labelType="m")
	public String lastFood;
	
	@Label(label="diarrhoeaMedText" , labelType="m")
	public String diarrhoeaMedText;
	
	@Label(label="lookTired" , labelType="c" , fieldType="boolean")
	public boolean lookTired;
	
	@Label(label="movementSlow" , labelType="c" , fieldType="boolean")
	public boolean movementSlow;
	
	@Label(label="cParchmentSkin" , labelType="c" , fieldType="boolean")
	public boolean cParchmentSkin;
	
	@Label(label="lipsDry" , labelType="c" , fieldType="boolean")
	public boolean lipsDry;
	
	@Label(label="tongueDry" , labelType="c" , fieldType="boolean")
	public boolean tongueDry;
	
	@Label(label="rhagades" , labelType="c" , fieldType="boolean")
	public boolean rhagades;
	
	@Label(label="sinciput" , labelType="c" , fieldType="boolean")
	public boolean sinciput;
	
	@Label(label="arterialPressureDecrease" , labelType="c" , fieldType="boolean")
	public boolean arterialPressureDecrease;
	
	@Label(label="sinkEye" , labelType="c" , fieldType="boolean")
	public boolean sinkEye;
	
	@Label(label="cardiacArrhythmiasDiarrhoea" , labelType="c" , fieldType="boolean")
	public boolean cardiacArrhythmiasDiarrhoea;
	
	@Label(label="hyperpyrexia37To39" , labelType="c" , fieldType="boolean")
	public boolean hyperpyrexia37To39;
	
	@Label(label="epigastriumPain" , labelType="c" , fieldType="boolean")
	public boolean epigastriumPain;
	
	@Label(label="sigmaStomach" , labelType="c" , fieldType="boolean")
	public boolean sigmaStomach;
	
	@Label(label="getPanic" , labelType="c" , fieldType="boolean")
	public boolean getPanic;
	
	@Label(label="muscleTension" , labelType="c" , fieldType="boolean")
	public boolean muscleTension;

	@Label(label="diarrhoeaUvula" , labelType="c" , fieldType="boolean")
	public boolean diarrhoeaUvula;
	
	@Label(label="diarrhoeaCerumen" , labelType="c" , fieldType="boolean")
	public boolean diarrhoeaCerumen;
	
	@Label(label="diarrhoeaRateText" , labelType="p")
	public String diarrhoeaRateText;
	
	@Label(label="diarrhoeaAdvice" , labelType="c")
	public String diarrhoeaAdvice;

	public boolean isKeck() {
		return keck;
	}

	public void setKeck(boolean keck) {
		this.keck = keck;
	}

	public boolean isSlushyDefecate() {
		return slushyDefecate;
	}

	public void setSlushyDefecate(boolean slushyDefecate) {
		this.slushyDefecate = slushyDefecate;
	}

	public boolean isHyperpyrexia() {
		return hyperpyrexia;
	}

	public void setHyperpyrexia(boolean hyperpyrexia) {
		this.hyperpyrexia = hyperpyrexia;
	}

	public boolean isExtroversion() {
		return extroversion;
	}

	public void setExtroversion(boolean extroversion) {
		this.extroversion = extroversion;
	}

	public boolean isStomachPain() {
		return stomachPain;
	}

	public void setStomachPain(boolean stomachPain) {
		this.stomachPain = stomachPain;
	}

	public boolean isFootPain() {
		return footPain;
	}

	public void setFootPain(boolean footPain) {
		this.footPain = footPain;
	}

	public boolean isAbdomenColic() {
		return abdomenColic;
	}

	public void setAbdomenColic(boolean abdomenColic) {
		this.abdomenColic = abdomenColic;
	}

	public boolean isTobeThirsty() {
		return tobeThirsty;
	}

	public void setTobeThirsty(boolean tobeThirsty) {
		this.tobeThirsty = tobeThirsty;
	}

	public boolean isParchmentSkin() {
		return parchmentSkin;
	}

	public void setParchmentSkin(boolean parchmentSkin) {
		this.parchmentSkin = parchmentSkin;
	}

	public boolean isHeartPain() {
		return heartPain;
	}

	public void setHeartPain(boolean heartPain) {
		this.heartPain = heartPain;
	}

	public boolean isPeeDecrease() {
		return peeDecrease;
	}

	public void setPeeDecrease(boolean peeDecrease) {
		this.peeDecrease = peeDecrease;
	}

	public boolean isExhausted() {
		return exhausted;
	}

	public void setExhausted(boolean exhausted) {
		this.exhausted = exhausted;
	}

	public boolean isGettingTired() {
		return gettingTired;
	}

	public void setGettingTired(boolean gettingTired) {
		this.gettingTired = gettingTired;
	}

	public boolean isGettingHeadache() {
		return gettingHeadache;
	}

	public void setGettingHeadache(boolean gettingHeadache) {
		this.gettingHeadache = gettingHeadache;
	}

	public boolean isSnivel() {
		return snivel;
	}

	public void setSnivel(boolean snivel) {
		this.snivel = snivel;
	}

	public boolean isTympanumInfection() {
		return tympanumInfection;
	}

	public void setTympanumInfection(boolean tympanumInfection) {
		this.tympanumInfection = tympanumInfection;
	}

	public boolean isBreathInfection() {
		return breathInfection;
	}

	public void setBreathInfection(boolean breathInfection) {
		this.breathInfection = breathInfection;
	}

	public boolean isChronicInfection() {
		return chronicInfection;
	}

	public void setChronicInfection(boolean chronicInfection) {
		this.chronicInfection = chronicInfection;
	}

	public String getDiarrhoeaPainText() {
		return diarrhoeaPainText;
	}

	public void setDiarrhoeaPainText(String diarrhoeaPainText) {
		this.diarrhoeaPainText = diarrhoeaPainText;
	}

	public boolean isToDiarrhoea() {
		return toDiarrhoea;
	}

	public void setToDiarrhoea(boolean toDiarrhoea) {
		this.toDiarrhoea = toDiarrhoea;
	}

	public boolean isToDiarrhoeaPerDay() {
		return toDiarrhoeaPerDay;
	}

	public void setToDiarrhoeaPerDay(boolean toDiarrhoeaPerDay) {
		this.toDiarrhoeaPerDay = toDiarrhoeaPerDay;
	}

	public int getToDiarrhoeaDays() {
		return toDiarrhoeaDays;
	}

	public void setToDiarrhoeaDays(int toDiarrhoeaDays) {
		this.toDiarrhoeaDays = toDiarrhoeaDays;
	}

	public int getDiarrhoeaVolume() {
		return diarrhoeaVolume;
	}

	public void setDiarrhoeaVolume(int diarrhoeaVolume) {
		this.diarrhoeaVolume = diarrhoeaVolume;
	}

	public int getPoopImpurity() {
		return poopImpurity;
	}

	public void setPoopImpurity(int poopImpurity) {
		this.poopImpurity = poopImpurity;
	}

	public int getWhenPoop() {
		return whenPoop;
	}

	public void setWhenPoop(int whenPoop) {
		this.whenPoop = whenPoop;
	}

	public int getWhenPoopKeck() {
		return whenPoopKeck;
	}

	public void setWhenPoopKeck(int whenPoopKeck) {
		this.whenPoopKeck = whenPoopKeck;
	}

	public String getWhenPain() {
		return whenPain;
	}

	public void setWhenPain(String whenPain) {
		this.whenPain = whenPain;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getUsedPharmacy() {
		return usedPharmacy;
	}

	public void setUsedPharmacy(String usedPharmacy) {
		this.usedPharmacy = usedPharmacy;
	}

	public int getDiarhoeaCause() {
		return diarhoeaCause;
	}

	public void setDiarhoeaCause(int diarhoeaCause) {
		this.diarhoeaCause = diarhoeaCause;
	}

	public String getWithDiarrhoeaLast6Months() {
		return withDiarrhoeaLast6Months;
	}

	public void setWithDiarrhoeaLast6Months(String withDiarrhoeaLast6Months) {
		this.withDiarrhoeaLast6Months = withDiarrhoeaLast6Months;
	}

	public String getWentOtherCountries() {
		return wentOtherCountries;
	}

	public void setWentOtherCountries(String wentOtherCountries) {
		this.wentOtherCountries = wentOtherCountries;
	}

	public String getDonorLast6Months() {
		return donorLast6Months;
	}

	public void setDonorLast6Months(String donorLast6Months) {
		this.donorLast6Months = donorLast6Months;
	}

	public String getHaveNearlyDiarrhoea() {
		return haveNearlyDiarrhoea;
	}

	public void setHaveNearlyDiarrhoea(String haveNearlyDiarrhoea) {
		this.haveNearlyDiarrhoea = haveNearlyDiarrhoea;
	}

	public String getDrinkNotFureWater() {
		return drinkNotFureWater;
	}

	public void setDrinkNotFureWater(String drinkNotFureWater) {
		this.drinkNotFureWater = drinkNotFureWater;
	}

	public String getHavePet() {
		return havePet;
	}

	public void setHavePet(String havePet) {
		this.havePet = havePet;
	}

	public String getShareSpoonOrCup() {
		return shareSpoonOrCup;
	}

	public void setShareSpoonOrCup(String shareSpoonOrCup) {
		this.shareSpoonOrCup = shareSpoonOrCup;
	}

	public String getLastFood() {
		return lastFood;
	}

	public void setLastFood(String lastFood) {
		this.lastFood = lastFood;
	}

	public String getDiarrhoeaMedText() {
		return diarrhoeaMedText;
	}

	public void setDiarrhoeaMedText(String diarrhoeaMedText) {
		this.diarrhoeaMedText = diarrhoeaMedText;
	}

	public boolean isLookTired() {
		return lookTired;
	}

	public void setLookTired(boolean lookTired) {
		this.lookTired = lookTired;
	}

	public boolean isMovementSlow() {
		return movementSlow;
	}

	public void setMovementSlow(boolean movementSlow) {
		this.movementSlow = movementSlow;
	}

	public boolean iscParchmentSkin() {
		return cParchmentSkin;
	}

	public void setcParchmentSkin(boolean cParchmentSkin) {
		this.cParchmentSkin = cParchmentSkin;
	}

	public boolean isLipsDry() {
		return lipsDry;
	}

	public void setLipsDry(boolean lipsDry) {
		this.lipsDry = lipsDry;
	}

	public boolean isTongueDry() {
		return tongueDry;
	}

	public void setTongueDry(boolean tongueDry) {
		this.tongueDry = tongueDry;
	}

	public boolean isRhagades() {
		return rhagades;
	}

	public void setRhagades(boolean rhagades) {
		this.rhagades = rhagades;
	}

	public boolean isSinciput() {
		return sinciput;
	}

	public void setSinciput(boolean sinciput) {
		this.sinciput = sinciput;
	}

	public boolean isArterialPressureDecrease() {
		return arterialPressureDecrease;
	}

	public void setArterialPressureDecrease(boolean arterialPressureDecrease) {
		this.arterialPressureDecrease = arterialPressureDecrease;
	}

	public boolean isSinkEye() {
		return sinkEye;
	}

	public void setSinkEye(boolean sinkEye) {
		this.sinkEye = sinkEye;
	}

	public boolean isCardiacArrhythmiasDiarrhoea() {
		return cardiacArrhythmiasDiarrhoea;
	}

	public void setCardiacArrhythmiasDiarrhoea(boolean cardiacArrhythmiasDiarrhoea) {
		this.cardiacArrhythmiasDiarrhoea = cardiacArrhythmiasDiarrhoea;
	}

	public boolean isHyperpyrexia37To39() {
		return hyperpyrexia37To39;
	}

	public void setHyperpyrexia37To39(boolean hyperpyrexia37To39) {
		this.hyperpyrexia37To39 = hyperpyrexia37To39;
	}

	public boolean isEpigastriumPain() {
		return epigastriumPain;
	}

	public void setEpigastriumPain(boolean epigastriumPain) {
		this.epigastriumPain = epigastriumPain;
	}

	public boolean isSigmaStomach() {
		return sigmaStomach;
	}

	public void setSigmaStomach(boolean sigmaStomach) {
		this.sigmaStomach = sigmaStomach;
	}

	public boolean isGetPanic() {
		return getPanic;
	}

	public void setGetPanic(boolean getPanic) {
		this.getPanic = getPanic;
	}

	public boolean isMuscleTension() {
		return muscleTension;
	}

	public void setMuscleTension(boolean muscleTension) {
		this.muscleTension = muscleTension;
	}

	public boolean isDiarrhoeaUvula() {
		return diarrhoeaUvula;
	}

	public void setDiarrhoeaUvula(boolean diarrhoeaUvula) {
		this.diarrhoeaUvula = diarrhoeaUvula;
	}

	public boolean isDiarrhoeaCerumen() {
		return diarrhoeaCerumen;
	}

	public void setCiarrhoeaCerumen(boolean diarrhoeaCerumen) {
		this.diarrhoeaCerumen = diarrhoeaCerumen;
	}

	public String getDiarrhoeaRateText() {
		return diarrhoeaRateText;
	}

	public void setDiarrhoeaRateText(String diarrhoeaRateText) {
		this.diarrhoeaRateText = diarrhoeaRateText;
	}

	public String getDiarrhoeaAdvice() {
		return diarrhoeaAdvice;
	}

	public void setDiarrhoeaAdvice(String diarrhoeaAdvice) {
		this.diarrhoeaAdvice = diarrhoeaAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getLivingCondition() {
		return livingCondition;
	}

	public void setLivingCondition(int livingCondition) {
		this.livingCondition = livingCondition;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}

	public void setDiarrhoeaCerumen(boolean diarrhoeaCerumen) {
		this.diarrhoeaCerumen = diarrhoeaCerumen;
	}

	public boolean isYes() {
		return yes;
	}

	public void setYes(boolean yes) {
		this.yes = yes;
	}

	public boolean isThreeAndMore() {
		return threeAndMore;
	}

	public void setThreeAndMore(boolean threeAndMore) {
		this.threeAndMore = threeAndMore;
	}
	
	
}

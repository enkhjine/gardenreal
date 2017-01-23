package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerPediatricInfection {

	private static final long serialVersionUID = 1L;
	
	@Label(label="hyperpyrexia" , labelType="p" , fieldType="boolean")
	public boolean hyperpyrexia;
	
	@Label(label="umbilecalCicatrixPus" , labelType="p" , fieldType="boolean")
	public boolean umbilecalCicatrixPus;
	
	@Label(label="umbilecalCicatrix" , labelType="p" , fieldType="boolean")
	public boolean umbilecalCicatrix;
	
	@Label(label="umbilicalErubescence" , labelType="p" , fieldType="boolean")
	public boolean umbilicalErubescence;
	
	@Label(label="funisSwell" , labelType="p" , fieldType="boolean")
	public boolean funisSwell;
	
	@Label(label="funisGettingDark" , labelType="p" , fieldType="boolean")
	public boolean funisGettingDark;
	
	@Label(label="bloodFromCicatrix" , labelType="p" , fieldType="boolean")
	public boolean bloodFromCicatrix;
	
	@Label(label="intertrigo" , labelType="p" , fieldType="boolean")
	public boolean intertrigo;
	
	@Label(label="intertrigoCicatrix" , labelType="p" , fieldType="boolean")
	public boolean intertrigoCicatrix;
	
	@Label(label="cicatrixOnSkin" , labelType="p" , fieldType="boolean")
	public boolean cicatrixOnSkin;
	
	@Label(label="rubella" , labelType="p" , fieldType="boolean")
	public boolean rubella;
	
	@Label(label="bullaOnPlicaAndHair" , labelType="p" , fieldType="boolean")
	public boolean bullaOnPlicaAndHair;
	
	@Label(label="bullaOnPlica" , labelType="p" , fieldType="boolean")
	public boolean bullaOnPlica;
	
	@Label(label="papularOnSkin" , labelType="p" , fieldType="boolean")
	public boolean papularOnSkin;
	
	@Label(label="mammaryGland" , labelType="p" , fieldType="boolean")
	public boolean mammaryGland;
	
	@Label(label="mammaryGlandErubescence" , labelType="p" , fieldType="boolean")
	public boolean mammaryGlandErubescence;
	
	@Label(label="bullaOnNail" , labelType="p" , fieldType="boolean")
	public boolean bullaOnNail;
	
	@Label(label="bullaOnFace" , labelType="p" , fieldType="boolean")
	public boolean bullaOnFace;
	
	@Label(label="pediatricInfectionPainText" , labelType="p")
	public String pediatricInfectionPainText;
	
	@Label(label="pediatricHyperpyrexia" , labelType="m" , fieldType="select" , answers={"35.5AndLess" , "35.6To37.4" , "37.5To38.4" , "38.5AndMore"})
	public int pediatricHyperpyrexia;
	
	@Label(label="funisReveal" , labelType="m" , fieldType="select" , answers={"no" , "gettingDark" , "frReveal"})
	public int funisReveal;
	
	@Label(label="funisPus" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int funisPus;
	
	@Label(label="funisRevealToSkin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int funisRevealToSkin;
	
	@Label(label="funisCicatrixDejection" , labelType="m" , fieldType="select" , answers={"pusly" , "slimyWithPus"})
	public int funisCicatrixDejection;
	
	@Label(label="funisSideDropsically" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int funisSideDropsically;
	
	@Label(label="funisSphygmusFeel" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int funisSphygmusFeel;
	
	@Label(label="funisCicatrixDelay" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int funisCicatrixDelay;
	
	@Label(label="bloodFromFunis" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int bloodFromFunis;
	
	@Label(label="eruptionBreakOut" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int eruptionBreakOut;
	
	@Label(label="locationOfErysipelas" , labelType="m")
	public String locationOfErysipelas;
	
	@Label(label="eruptionOnScalp" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnScalp;
	
	@Label(label="eruptionOnFace" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnFace;
	
	@Label(label="eruptionOnNeck" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnNeck;
	
	@Label(label="eruptionOnBody" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnBody;
	
	@Label(label="eruptionOnBack" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnBack;
	
	@Label(label="eruptionOnArm" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnArm;
	
	@Label(label="eruptionOnHand" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnHand;
	
	@Label(label="eruptionOnGenitals" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnGenitals;
	
	@Label(label="eruptionOnFoot" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnFoot;
	
	@Label(label="eruptionOnClubfoot" , labelType="m" , fieldType="boolean")
	public boolean eruptionOnClubfoot;
	
	@Label(label="eruptionOnSkinGrave" , labelType="m" , fieldType="select" , answers={"yes" , "no"})
	public int eruptionOnSkinGrave;
	
	@Label(label="eruptionOnSkinLarge" , labelType="m" , fieldType="select" , answers={"yes" , "no"})
	public int eruptionOnSkinLarge;
	
	@Label(label="eruptionOnSkin" , labelType="m" , fieldType="select" , answers={"1DayAgo" , "2To4DaysAgo" , "5AndMoreDaysAgo"})
	public int eruptionOnSkin;
	
	@Label(label="eruptionBefore" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int eruptionBefore;
	
	@Label(label="eruptionSize" , labelType="m" , fieldType="select" , answers={"1-3mm" , "0.5-1cm" , "1-3cm"})
	public int eruptionSize;
	
	@Label(label="eruption" , labelType="m" , fieldType="select" , answers={"clear" , "unclear" , "pusly"})
	public int eruption;
	
	@Label(label="eruptionLate" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int eruptionLate;
	
	@Label(label="mCicatrixOnSkin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int mCicatrixOnSkin;
	
	@Label(label="mPapularOnSkin" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int mPapularOnSkin;
	
	@Label(label="eruptionOnSkinPeeve" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int eruptionOnSkinPeeve;
	
	@Label(label="isShowerEveryday" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isShowerEveryday;
	
	@Label(label="haveSelfBath" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int haveSelfBath;
	
	@Label(label="pediatricInfectionMedText" , labelType="m")
	public String pediatricInfectionMedText;
	
	@Label(label="pernicious" , labelType="c" , fieldType="select" , answers={"notRevealed" , "revealed"})
	public int pernicious;
	
	@Label(label="patientHood" , labelType="c" , fieldType="select" , answers={"phHealthy" , "phNormal" , "heavyish" , "heavy" , "veryHeavy"})
	public int patientHood;
	
	@Label(label="cPediatricHyperpyrexia" , labelType="c")
	public String cPediatricHyperpyrexia;
	
	@Label(label="mentality" , labelType="c" , fieldType="select" , answers={"mNormal" , "aggravated"})
	public int mentality;
	
	@Label(label="environmentRatio" , labelType="c" , fieldType="select" , answers={"contact" , "noContact"})
	public int environmentRatio;
	
	@Label(label="face" , labelType="c" , fieldType="select" , answers={"normal" , "pallescent" , "distressful"})
	public int face;
	
	@Label(label="race" , labelType="c" , fieldType="select" , answers={"normal" , "fair" , "abashed"})
	public int race;
	
	@Label(label="skinFlexible" , labelType="c" , fieldType="select" , answers={"normal" , "regressive"})
	public int skinFlexible;
	
	@Label(label="skinMoisture" , labelType="c" , fieldType="select" , answers={"normal" , "dry"})
	public int skinMoisture;
	
	@Label(label="erysipelas" , labelType="c" , fieldType="select" , answers={"blasted" , "blastedWithCicatrix" , "bulla" , "puslyBulla" , "skinSphacelate"})
	public int erysipelas;
	
	@Label(label="locationOfErysipelasCheck" , labelType="c")
	public String locationOfErysipelasCheck;
	
	@Label(label="cEruptionOnScalp" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnScalp;
	
	@Label(label="cEruptionOnFace" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnFace;
	
	@Label(label="cEruptionOnNeck" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnNeck;
	
	@Label(label="cEruptionOnBody" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnBody;
	
	@Label(label="cEruptionOnBack" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnBack;
	
	@Label(label="cEruptionOnArm" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnArm;
	
	@Label(label="cEruptionOnHand" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnHand;
	
	@Label(label="cEruptionOnGenitals" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnGenitals;
	
	@Label(label="cEruptionOnFoot" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnFoot;
	
	@Label(label="cEruptionOnClubfoot" , labelType="c" , fieldType="boolean")
	public boolean cEruptionOnClubfoot;
	
 	@Label(label="erysipelasCombination" , labelType="c" , fieldType="select" , answers={"few" , "lot"})
 	public int erysipelasCombination;
 	
 	@Label(label="erysipelasSize" , labelType="c" , fieldType="select" , answers={"1-3mm" , "0.5-1cm" , "1-3cm"})
 	public int erysipelasSize;
 	
 	@Label(label="erysipelasCapacity" , labelType="c" , fieldType="select" , answers={"clear" , "unclear" , "pusly"})
 	public int erysipelasCapacity;
 	
 	@Label(label="pusFromFunisCicatrix" , labelType="c" , fieldType="boolean")
	public boolean pusFromFunisCicatrix;
 	
 	@Label(label="funisAbashed" , labelType="c" , fieldType="boolean")
	public boolean funisAbashed;
 	
 	@Label(label="funisEdema" , labelType="c" , fieldType="boolean")
	public boolean funisEdema;
 	
 	@Label(label="funisDarkInflammation" , labelType="c" , fieldType="boolean")
	public boolean funisDarkInflammation;
 	
 	@Label(label="oralCavity" , labelType="c" , fieldType="select" , answers={"notPeeve" , "peeve" , "notUlcer" , "ulcer"})
 	public int oralCavity;
 	
 	@Label(label="color" , labelType="c")
 	public String color;
 	
 	@Label(label="otherDisease" , labelType="c" , fieldType="select" , answers={"notRevealed" , "revealed"})
 	public int otherDisease;
 	
 	@Label(label="pediatricInfectionRateText" , labelType="p")
 	public String pediatricInfectionRateText;
 	
 	@Label(label="pediatricInfectionAdvice" , labelType="c")
 	public String pediatricInfectionAdvice;

	public boolean isHyperpyrexia() {
		return hyperpyrexia;
	}

	public void setHyperpyrexia(boolean hyperpyrexia) {
		this.hyperpyrexia = hyperpyrexia;
	}

	public boolean isUmbilecalCicatrixPus() {
		return umbilecalCicatrixPus;
	}

	public void setUmbilecalCicatrixPus(boolean umbilecalCicatrixPus) {
		this.umbilecalCicatrixPus = umbilecalCicatrixPus;
	}

	public boolean isUmbilecalCicatrix() {
		return umbilecalCicatrix;
	}

	public void setUmbilecalCicatrix(boolean umbilecalCicatrix) {
		this.umbilecalCicatrix = umbilecalCicatrix;
	}

	public boolean isUmbilicalErubescence() {
		return umbilicalErubescence;
	}

	public void setUmbilicalErubescence(boolean umbilicalErubescence) {
		this.umbilicalErubescence = umbilicalErubescence;
	}

	public boolean isFunisSwell() {
		return funisSwell;
	}

	public void setFunisSwell(boolean funisSwell) {
		this.funisSwell = funisSwell;
	}

	public boolean isFunisGettingDark() {
		return funisGettingDark;
	}

	public void setFunisGettingDark(boolean funisGettingDark) {
		this.funisGettingDark = funisGettingDark;
	}

	public boolean isBloodFromCicatrix() {
		return bloodFromCicatrix;
	}

	public void setBloodFromCicatrix(boolean bloodFromCicatrix) {
		this.bloodFromCicatrix = bloodFromCicatrix;
	}

	public boolean isIntertrigo() {
		return intertrigo;
	}

	public void setIntertrigo(boolean intertrigo) {
		this.intertrigo = intertrigo;
	}

	public boolean isIntertrigoCicatrix() {
		return intertrigoCicatrix;
	}

	public void setIntertrigoCicatrix(boolean intertrigoCicatrix) {
		this.intertrigoCicatrix = intertrigoCicatrix;
	}

	public boolean isCicatrixOnSkin() {
		return cicatrixOnSkin;
	}

	public void setCicatrixOnSkin(boolean cicatrixOnSkin) {
		this.cicatrixOnSkin = cicatrixOnSkin;
	}

	public boolean isRubella() {
		return rubella;
	}

	public void setRubella(boolean rubella) {
		this.rubella = rubella;
	}

	public boolean isBullaOnPlicaAndHair() {
		return bullaOnPlicaAndHair;
	}

	public void setBullaOnPlicaAndHair(boolean bullaOnPlicaAndHair) {
		this.bullaOnPlicaAndHair = bullaOnPlicaAndHair;
	}

	public boolean isBullaOnPlica() {
		return bullaOnPlica;
	}

	public void setBullaOnPlica(boolean bullaOnPlica) {
		this.bullaOnPlica = bullaOnPlica;
	}

	public boolean isPapularOnSkin() {
		return papularOnSkin;
	}

	public void setPapularOnSkin(boolean papularOnSkin) {
		this.papularOnSkin = papularOnSkin;
	}

	public boolean isMammaryGland() {
		return mammaryGland;
	}

	public void setMammaryGland(boolean mammaryGland) {
		this.mammaryGland = mammaryGland;
	}

	public boolean isMammaryGlandErubescence() {
		return mammaryGlandErubescence;
	}

	public void setMammaryGlandErubescence(boolean mammaryGlandErubescence) {
		this.mammaryGlandErubescence = mammaryGlandErubescence;
	}

	public boolean isBullaOnNail() {
		return bullaOnNail;
	}

	public void setBullaOnNail(boolean bullaOnNail) {
		this.bullaOnNail = bullaOnNail;
	}

	public boolean isBullaOnFace() {
		return bullaOnFace;
	}

	public void setBullaOnFace(boolean bullaOnFace) {
		this.bullaOnFace = bullaOnFace;
	}

	public String getPediatricInfectionPainText() {
		return pediatricInfectionPainText;
	}

	public void setPediatricInfectionPainText(String pediatricInfectionPainText) {
		this.pediatricInfectionPainText = pediatricInfectionPainText;
	}

	public int getPediatricHyperpyrexia() {
		return pediatricHyperpyrexia;
	}

	public void setPediatricHyperpyrexia(int pediatricHyperpyrexia) {
		this.pediatricHyperpyrexia = pediatricHyperpyrexia;
	}

	public int getFunisReveal() {
		return funisReveal;
	}

	public void setFunisReveal(int funisReveal) {
		this.funisReveal = funisReveal;
	}

	public int getFunisPus() {
		return funisPus;
	}

	public void setFunisPus(int funisPus) {
		this.funisPus = funisPus;
	}

	public int getFunisRevealToSkin() {
		return funisRevealToSkin;
	}

	public void setFunisRevealToSkin(int funisRevealToSkin) {
		this.funisRevealToSkin = funisRevealToSkin;
	}

	public int getFunisCicatrixDejection() {
		return funisCicatrixDejection;
	}

	public void setFunisCicatrixDejection(int funisCicatrixDejection) {
		this.funisCicatrixDejection = funisCicatrixDejection;
	}

	public int getFunisSideDropsically() {
		return funisSideDropsically;
	}

	public void setFunisSideDropsically(int funisSideDropsically) {
		this.funisSideDropsically = funisSideDropsically;
	}

	public int getFunisSphygmusFeel() {
		return funisSphygmusFeel;
	}

	public void setFunisSphygmusFeel(int funisSphygmusFeel) {
		this.funisSphygmusFeel = funisSphygmusFeel;
	}

	public int getFunisCicatrixDelay() {
		return funisCicatrixDelay;
	}

	public void setFunisCicatrixDelay(int funisCicatrixDelay) {
		this.funisCicatrixDelay = funisCicatrixDelay;
	}

	public int getBloodFromFunis() {
		return bloodFromFunis;
	}

	public void setBloodFromFunis(int bloodFromFunis) {
		this.bloodFromFunis = bloodFromFunis;
	}

	public int getEruptionBreakOut() {
		return eruptionBreakOut;
	}

	public void setEruptionBreakOut(int eruptionBreakOut) {
		this.eruptionBreakOut = eruptionBreakOut;
	}

	public boolean isEruptionOnScalp() {
		return eruptionOnScalp;
	}

	public void setEruptionOnScalp(boolean eruptionOnScalp) {
		this.eruptionOnScalp = eruptionOnScalp;
	}

	public boolean isEruptionOnFace() {
		return eruptionOnFace;
	}

	public void setEruptionOnFace(boolean eruptionOnFace) {
		this.eruptionOnFace = eruptionOnFace;
	}

	public boolean isEruptionOnNeck() {
		return eruptionOnNeck;
	}

	public void setEruptionOnNeck(boolean eruptionOnNeck) {
		this.eruptionOnNeck = eruptionOnNeck;
	}

	public boolean isEruptionOnBody() {
		return eruptionOnBody;
	}

	public void setEruptionOnBody(boolean eruptionOnBody) {
		this.eruptionOnBody = eruptionOnBody;
	}

	public boolean isEruptionOnBack() {
		return eruptionOnBack;
	}

	public void setEruptionOnBack(boolean eruptionOnBack) {
		this.eruptionOnBack = eruptionOnBack;
	}

	public boolean isEruptionOnArm() {
		return eruptionOnArm;
	}

	public void setEruptionOnArm(boolean eruptionOnArm) {
		this.eruptionOnArm = eruptionOnArm;
	}

	public boolean isEruptionOnHand() {
		return eruptionOnHand;
	}

	public void setEruptionOnHand(boolean eruptionOnHand) {
		this.eruptionOnHand = eruptionOnHand;
	}

	public boolean isEruptionOnGenitals() {
		return eruptionOnGenitals;
	}

	public void setEruptionOnGenitals(boolean eruptionOnGenitals) {
		this.eruptionOnGenitals = eruptionOnGenitals;
	}

	public boolean isEruptionOnFoot() {
		return eruptionOnFoot;
	}

	public void setEruptionOnFoot(boolean eruptionOnFoot) {
		this.eruptionOnFoot = eruptionOnFoot;
	}

	public boolean isEruptionOnClubfoot() {
		return eruptionOnClubfoot;
	}

	public void setEruptionOnClubfoot(boolean eruptionOnClubfoot) {
		this.eruptionOnClubfoot = eruptionOnClubfoot;
	}

	public int getEruptionOnSkinGrave() {
		return eruptionOnSkinGrave;
	}

	public void setEruptionOnSkinGrave(int eruptionOnSkinGrave) {
		this.eruptionOnSkinGrave = eruptionOnSkinGrave;
	}

	public int getEruptionOnSkinLarge() {
		return eruptionOnSkinLarge;
	}

	public void setEruptionOnSkinLarge(int eruptionOnSkinLarge) {
		this.eruptionOnSkinLarge = eruptionOnSkinLarge;
	}

	public int getEruptionOnSkin() {
		return eruptionOnSkin;
	}

	public void setEruptionOnSkin(int eruptionOnSkin) {
		this.eruptionOnSkin = eruptionOnSkin;
	}

	public int getEruptionBefore() {
		return eruptionBefore;
	}

	public void setEruptionBefore(int eruptionBefore) {
		this.eruptionBefore = eruptionBefore;
	}

	public int getEruptionSize() {
		return eruptionSize;
	}

	public void setEruptionSize(int eruptionSize) {
		this.eruptionSize = eruptionSize;
	}

	public int getEruption() {
		return eruption;
	}

	public void setEruption(int eruption) {
		this.eruption = eruption;
	}

	public int getEruptionLate() {
		return eruptionLate;
	}

	public void setEruptionLate(int eruptionLate) {
		this.eruptionLate = eruptionLate;
	}

	public int getmCicatrixOnSkin() {
		return mCicatrixOnSkin;
	}

	public void setmCicatrixOnSkin(int mCicatrixOnSkin) {
		this.mCicatrixOnSkin = mCicatrixOnSkin;
	}

	public int getmPapularOnSkin() {
		return mPapularOnSkin;
	}

	public void setmPapularOnSkin(int mPapularOnSkin) {
		this.mPapularOnSkin = mPapularOnSkin;
	}

	public int getEruptionOnSkinPeeve() {
		return eruptionOnSkinPeeve;
	}

	public void setEruptionOnSkinPeeve(int eruptionOnSkinPeeve) {
		this.eruptionOnSkinPeeve = eruptionOnSkinPeeve;
	}

	public int getIsShowerEveryday() {
		return isShowerEveryday;
	}

	public void setIsShowerEveryday(int isShowerEveryday) {
		this.isShowerEveryday = isShowerEveryday;
	}

	public int getHaveSelfBath() {
		return haveSelfBath;
	}

	public void setHaveSelfBath(int haveSelfBath) {
		this.haveSelfBath = haveSelfBath;
	}

	public String getPediatricInfectionMedText() {
		return pediatricInfectionMedText;
	}

	public void setPediatricInfectionMedText(String pediatricInfectionMedText) {
		this.pediatricInfectionMedText = pediatricInfectionMedText;
	}

	public int getPernicious() {
		return pernicious;
	}

	public void setPernicious(int pernicious) {
		this.pernicious = pernicious;
	}

	public int getPatientHood() {
		return patientHood;
	}

	public void setPatientHood(int patientHood) {
		this.patientHood = patientHood;
	}

	public String getcPediatricHyperpyrexia() {
		return cPediatricHyperpyrexia;
	}

	public void setcPediatricHyperpyrexia(String cPediatricHyperpyrexia) {
		this.cPediatricHyperpyrexia = cPediatricHyperpyrexia;
	}

	public int getMentality() {
		return mentality;
	}

	public void setMentality(int mentality) {
		this.mentality = mentality;
	}

	public int getEnvironmentRatio() {
		return environmentRatio;
	}

	public void setEnvironmentRatio(int environmentRatio) {
		this.environmentRatio = environmentRatio;
	}

	public int getFace() {
		return face;
	}

	public void setFace(int face) {
		this.face = face;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}

	public int getSkinFlexible() {
		return skinFlexible;
	}

	public void setSkinFlexible(int skinFlexible) {
		this.skinFlexible = skinFlexible;
	}

	public int getSkinMoisture() {
		return skinMoisture;
	}

	public void setSkinMoisture(int skinMoisture) {
		this.skinMoisture = skinMoisture;
	}

	public int getErysipelas() {
		return erysipelas;
	}

	public void setErysipelas(int erysipelas) {
		this.erysipelas = erysipelas;
	}

	public boolean iscEruptionOnScalp() {
		return cEruptionOnScalp;
	}

	public void setcEruptionOnScalp(boolean cEruptionOnScalp) {
		this.cEruptionOnScalp = cEruptionOnScalp;
	}

	public boolean iscEruptionOnFace() {
		return cEruptionOnFace;
	}

	public void setcEruptionOnFace(boolean cEruptionOnFace) {
		this.cEruptionOnFace = cEruptionOnFace;
	}

	public boolean iscEruptionOnNeck() {
		return cEruptionOnNeck;
	}

	public void setcEruptionOnNeck(boolean cEruptionOnNeck) {
		this.cEruptionOnNeck = cEruptionOnNeck;
	}

	public boolean iscEruptionOnBody() {
		return cEruptionOnBody;
	}

	public void setcEruptionOnBody(boolean cEruptionOnBody) {
		this.cEruptionOnBody = cEruptionOnBody;
	}

	public boolean iscEruptionOnBack() {
		return cEruptionOnBack;
	}

	public void setcEruptionOnBack(boolean cEruptionOnBack) {
		this.cEruptionOnBack = cEruptionOnBack;
	}

	public boolean iscEruptionOnArm() {
		return cEruptionOnArm;
	}

	public void setcEruptionOnArm(boolean cEruptionOnArm) {
		this.cEruptionOnArm = cEruptionOnArm;
	}

	public boolean iscEruptionOnHand() {
		return cEruptionOnHand;
	}

	public void setcEruptionOnHand(boolean cEruptionOnHand) {
		this.cEruptionOnHand = cEruptionOnHand;
	}

	public boolean iscEruptionOnGenitals() {
		return cEruptionOnGenitals;
	}

	public void setcEruptionOnGenitals(boolean cEruptionOnGenitals) {
		this.cEruptionOnGenitals = cEruptionOnGenitals;
	}

	public boolean iscEruptionOnFoot() {
		return cEruptionOnFoot;
	}

	public void setcEruptionOnFoot(boolean cEruptionOnFoot) {
		this.cEruptionOnFoot = cEruptionOnFoot;
	}

	public boolean iscEruptionOnClubfoot() {
		return cEruptionOnClubfoot;
	}

	public void setcEruptionOnClubfoot(boolean cEruptionOnClubfoot) {
		this.cEruptionOnClubfoot = cEruptionOnClubfoot;
	}

	public int getErysipelasCombination() {
		return erysipelasCombination;
	}

	public void setErysipelasCombination(int erysipelasCombination) {
		this.erysipelasCombination = erysipelasCombination;
	}

	public int getErysipelasSize() {
		return erysipelasSize;
	}

	public void setErysipelasSize(int erysipelasSize) {
		this.erysipelasSize = erysipelasSize;
	}

	public int getErysipelasCapacity() {
		return erysipelasCapacity;
	}

	public void setErysipelasCapacity(int erysipelasCapacity) {
		this.erysipelasCapacity = erysipelasCapacity;
	}

	public boolean isPusFromFunisCicatrix() {
		return pusFromFunisCicatrix;
	}

	public void setPusFromFunisCicatrix(boolean pusFromFunisCicatrix) {
		this.pusFromFunisCicatrix = pusFromFunisCicatrix;
	}

	public boolean isFunisAbashed() {
		return funisAbashed;
	}

	public void setFunisAbashed(boolean funisAbashed) {
		this.funisAbashed = funisAbashed;
	}

	public boolean isFunisEdema() {
		return funisEdema;
	}

	public void setFunisEdema(boolean funisEdema) {
		this.funisEdema = funisEdema;
	}

	public boolean isFunisDarkInflammation() {
		return funisDarkInflammation;
	}

	public void setFunisDarkInflammation(boolean funisDarkInflammation) {
		this.funisDarkInflammation = funisDarkInflammation;
	}

	public int getOralCavity() {
		return oralCavity;
	}

	public void setOralCavity(int oralCavity) {
		this.oralCavity = oralCavity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getOtherDisease() {
		return otherDisease;
	}

	public void setOtherDisease(int otherDisease) {
		this.otherDisease = otherDisease;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPediatricInfectionRateText() {
		return pediatricInfectionRateText;
	}

	public void setPediatricInfectionRateText(String pediatricInfectionRateText) {
		this.pediatricInfectionRateText = pediatricInfectionRateText;
	}

	public String getPediatricInfectionAdvice() {
		return pediatricInfectionAdvice;
	}

	public void setPediatricInfectionAdvice(String pediatricInfectionAdvice) {
		this.pediatricInfectionAdvice = pediatricInfectionAdvice;
	}

	public String getLocationOfErysipelas() {
		return locationOfErysipelas;
	}

	public void setLocationOfErysipelas(String locationOfErysipelas) {
		this.locationOfErysipelas = locationOfErysipelas;
	}

	public String getLocationOfErysipelasCheck() {
		return locationOfErysipelasCheck;
	}

	public void setLocationOfErysipelasCheck(String locationOfErysipelasCheck) {
		this.locationOfErysipelasCheck = locationOfErysipelasCheck;
	}
	
 	
}

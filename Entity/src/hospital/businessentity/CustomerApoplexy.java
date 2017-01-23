package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerApoplexy {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="apoplexyEyeBlur" , labelType="p" , fieldType="boolean")
	public boolean apoplexyEyeBlur;
	
	@Label(label="apoplexyHeadache" , labelType="p" , fieldType="boolean")
	public boolean apoplexyHeadache;
	
	@Label(label="apoplexySwim" , labelType="p" , fieldType="boolean")
	public boolean apoplexySwim;
	
	@Label(label="apoplexyTongue" , labelType="p" , fieldType="boolean")
	public boolean apoplexyTongue;
	
	@Label(label="apoplexyFace" , labelType="p" , fieldType="boolean")
	public boolean apoplexyFace;
	
	@Label(label="apoplexyTinnitus" , labelType="p" , fieldType="boolean")
	public boolean apoplexyTinnitus;
	
	@Label(label="apoplexyFootArmDisentangle" , labelType="p" , fieldType="boolean")
	public boolean apoplexyFootArmDisentangle;
	
	@Label(label="apoplexyFootArmPalsy" , labelType="p" , fieldType="boolean")
	public boolean apoplexyFootArmPalsy;
	
	@Label(label="apoplexyFlux" , labelType="p" , fieldType="boolean")
	public boolean apoplexyFlux;
	
	@Label(label="apoplexyLoseBalance" , labelType="p" , fieldType="boolean")
	public boolean apoplexyLoseBalance;
	
	@Label(label="apoplexyPainText" , labelType="p")
	public String apoplexyPainText;
	
	@Label(label="apoplexyDistraction" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyDistraction;
	
	@Label(label="apoplexyADIncrease" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyADIncrease;
	
	@Label(label="apoplexyHeartPain" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyHeartPain;
	
	@Label(label="apoplexyUseAlcoholicCigar" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyUseAlcoholicCigar;
	
	@Label(label="apoplexySwallow" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexySwallow;
	
	@Label(label="apoplexyTongueFalter" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyTongueFalter;
	
	@Label(label="apoplexyFootArmMovement" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyFootArmMovement;
	
	@Label(label="apoplexyMuscle" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyMuscle;
	
	@Label(label="apoplexyFeel" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyFeel;
	
	@Label(label="apoplexyBalance" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyBalance;
	
	@Label(label="beforeApoplexy" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int beforeApoplexy;
	
	@Label(label="apoplexyPharmacy" , labelType="m")
	public String apoplexyPharmacy;
	
	@Label(label="apoplexyGeneration" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyGeneration;
	
	@Label(label="apoplexyMedText" , labelType="m")
	public String apoplexyMedText;
	
	@Label(label="apoplexyPupilSame" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyPupilSame;
	
	@Label(label="apoplexyEyeballMovement" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyEyeballMovement;
	
	@Label(label="apoplexyPupilLight" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyPupilLight;
	
	@Label(label="apoplexyNystagmus" , labelType="c")
	public String apoplexyNystagmus;
	
	@Label(label="apoplexyTrigeminus" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int apoplexyTrigeminus;
	
	@Label(label="apoplexyFaceFeelSame" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyFaceFeelSame;
	
	@Label(label="apoplexyHUN" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyHUN;
	
	@Label(label="apoplexySwallowReflex" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexySwallowReflex;
	
	@Label(label="apoplexyTongueLine" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyTongueLine;
	
	@Label(label="apoplexyHeadShoulderMovement" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyHeadShoulderMovement;
	
	@Label(label="apoplexyJointMovement" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int apoplexyJointMovement;
	
	@Label(label="apoplexyMusclePower" , labelType="c")
	public String apoplexyMusclePower;
	
	@Label(label="apoplexyHypertonic" , labelType="c")
	public String apoplexyHypertonic;
	
	@Label(label="apoplexyFiberReflex" , labelType="c")
	public String apoplexyFiberReflex;
	
	@Label(label="apoplexyPathogeneticReflex" , labelType="c")
	public String apoplexyPathogeneticReflex;
	
	@Label(label="apoplexyFeelPerfunctory" , labelType="c")
	public String apoplexyFeelPerfunctory;
	
	@Label(label="apoplexyFeelDeep" , labelType="c")
	public String apoplexyFeelDeep;
	
	@Label(label="apoplexyRombergTest" , labelType="c")
	public String apoplexyRombergTest;
	
	@Label(label="apoplexyFingerNoseTest" , labelType="c")
	public String apoplexyFingerNoseTest;
	
	@Label(label="apoplexyHeelKneeTest" , labelType="c")
	public String apoplexyHeelKneeTest;
	
	@Label(label="apoplexyRateText" , labelType="c")
	public String apoplexyRateText;
	
	@Label(label="apoplexyAdvice" , labelType="p")
	public String apoplexyAdvice;

	public boolean isApoplexyEyeBlur() {
		return apoplexyEyeBlur;
	}

	public void setApoplexyEyeBlur(boolean apoplexyEyeBlur) {
		this.apoplexyEyeBlur = apoplexyEyeBlur;
	}

	public boolean isApoplexyHeadache() {
		return apoplexyHeadache;
	}

	public void setApoplexyHeadache(boolean apoplexyHeadache) {
		this.apoplexyHeadache = apoplexyHeadache;
	}

	public boolean isApoplexySwim() {
		return apoplexySwim;
	}

	public void setApoplexySwim(boolean apoplexySwim) {
		this.apoplexySwim = apoplexySwim;
	}

	public boolean isApoplexyTongue() {
		return apoplexyTongue;
	}

	public void setApoplexyTongue(boolean apoplexyTongue) {
		this.apoplexyTongue = apoplexyTongue;
	}

	public boolean isApoplexyFace() {
		return apoplexyFace;
	}

	public void setApoplexyFace(boolean apoplexyFace) {
		this.apoplexyFace = apoplexyFace;
	}

	public boolean isApoplexyTinnitus() {
		return apoplexyTinnitus;
	}

	public void setApoplexyTinnitus(boolean apoplexyTinnitus) {
		this.apoplexyTinnitus = apoplexyTinnitus;
	}

	public boolean isApoplexyFootArmDisentangle() {
		return apoplexyFootArmDisentangle;
	}

	public void setApoplexyFootArmDisentangle(boolean apoplexyFootArmDisentangle) {
		this.apoplexyFootArmDisentangle = apoplexyFootArmDisentangle;
	}

	public boolean isApoplexyFootArmPalsy() {
		return apoplexyFootArmPalsy;
	}

	public void setApoplexyFootArmPalsy(boolean apoplexyFootArmPalsy) {
		this.apoplexyFootArmPalsy = apoplexyFootArmPalsy;
	}

	public boolean isApoplexyFlux() {
		return apoplexyFlux;
	}

	public void setApoplexyFlux(boolean apoplexyFlux) {
		this.apoplexyFlux = apoplexyFlux;
	}

	public boolean isApoplexyLoseBalance() {
		return apoplexyLoseBalance;
	}

	public void setApoplexyLoseBalance(boolean apoplexyLoseBalance) {
		this.apoplexyLoseBalance = apoplexyLoseBalance;
	}

	public String getApoplexyPainText() {
		return apoplexyPainText;
	}

	public void setApoplexyPainText(String apoplexyPainText) {
		this.apoplexyPainText = apoplexyPainText;
	}

	public int getApoplexyDistraction() {
		return apoplexyDistraction;
	}

	public void setApoplexyDistraction(int apoplexyDistraction) {
		this.apoplexyDistraction = apoplexyDistraction;
	}

	public int getApoplexyADIncrease() {
		return apoplexyADIncrease;
	}

	public void setApoplexyADIncrease(int apoplexyADIncrease) {
		this.apoplexyADIncrease = apoplexyADIncrease;
	}

	public int getApoplexyHeartPain() {
		return apoplexyHeartPain;
	}

	public void setApoplexyHeartPain(int apoplexyHeartPain) {
		this.apoplexyHeartPain = apoplexyHeartPain;
	}

	public int getApoplexyUseAlcoholicCigar() {
		return apoplexyUseAlcoholicCigar;
	}

	public void setApoplexyUseAlcoholicCigar(int apoplexyUseAlcoholicCigar) {
		this.apoplexyUseAlcoholicCigar = apoplexyUseAlcoholicCigar;
	}

	public int getApoplexySwallow() {
		return apoplexySwallow;
	}

	public void setApoplexySwallow(int apoplexySwallow) {
		this.apoplexySwallow = apoplexySwallow;
	}

	public int getApoplexyTongueFalter() {
		return apoplexyTongueFalter;
	}

	public void setApoplexyTongueFalter(int apoplexyTongueFalter) {
		this.apoplexyTongueFalter = apoplexyTongueFalter;
	}

	public int getApoplexyFootArmMovement() {
		return apoplexyFootArmMovement;
	}

	public void setApoplexyFootArmMovement(int apoplexyFootArmMovement) {
		this.apoplexyFootArmMovement = apoplexyFootArmMovement;
	}

	public int getApoplexyMuscle() {
		return apoplexyMuscle;
	}

	public void setApoplexyMuscle(int apoplexyMuscle) {
		this.apoplexyMuscle = apoplexyMuscle;
	}

	public int getApoplexyFeel() {
		return apoplexyFeel;
	}

	public void setApoplexyFeel(int apoplexyFeel) {
		this.apoplexyFeel = apoplexyFeel;
	}

	public int getApoplexyBalance() {
		return apoplexyBalance;
	}

	public void setApoplexyBalance(int apoplexyBalance) {
		this.apoplexyBalance = apoplexyBalance;
	}

	public int getBeforeApoplexy() {
		return beforeApoplexy;
	}

	public void setBeforeApoplexy(int beforeApoplexy) {
		this.beforeApoplexy = beforeApoplexy;
	}

	public String getApoplexyPharmacy() {
		return apoplexyPharmacy;
	}

	public void setApoplexyPharmacy(String apoplexyPharmacy) {
		this.apoplexyPharmacy = apoplexyPharmacy;
	}

	public int getApoplexyGeneration() {
		return apoplexyGeneration;
	}

	public void setApoplexyGeneration(int apoplexyGeneration) {
		this.apoplexyGeneration = apoplexyGeneration;
	}

	public String getApoplexyMedText() {
		return apoplexyMedText;
	}

	public void setApoplexyMedText(String apoplexyMedText) {
		this.apoplexyMedText = apoplexyMedText;
	}

	public int getApoplexyPupilSame() {
		return apoplexyPupilSame;
	}

	public void setApoplexyPupilSame(int apoplexyPupilSame) {
		this.apoplexyPupilSame = apoplexyPupilSame;
	}

	public int getApoplexyEyeballMovement() {
		return apoplexyEyeballMovement;
	}

	public void setApoplexyEyeballMovement(int apoplexyEyeballMovement) {
		this.apoplexyEyeballMovement = apoplexyEyeballMovement;
	}

	public int getApoplexyPupilLight() {
		return apoplexyPupilLight;
	}

	public void setApoplexyPupilLight(int apoplexyPupilLight) {
		this.apoplexyPupilLight = apoplexyPupilLight;
	}

	public String getApoplexyNystagmus() {
		return apoplexyNystagmus;
	}

	public void setApoplexyNystagmus(String apoplexyNystagmus) {
		this.apoplexyNystagmus = apoplexyNystagmus;
	}

	public int getApoplexyTrigeminus() {
		return apoplexyTrigeminus;
	}

	public void setApoplexyTrigeminus(int apoplexyTrigeminus) {
		this.apoplexyTrigeminus = apoplexyTrigeminus;
	}

	public int getApoplexyFaceFeelSame() {
		return apoplexyFaceFeelSame;
	}

	public void setApoplexyFaceFeelSame(int apoplexyFaceFeelSame) {
		this.apoplexyFaceFeelSame = apoplexyFaceFeelSame;
	}

	public int getApoplexyHUN() {
		return apoplexyHUN;
	}

	public void setApoplexyHUN(int apoplexyHUN) {
		this.apoplexyHUN = apoplexyHUN;
	}

	public int getApoplexySwallowReflex() {
		return apoplexySwallowReflex;
	}

	public void setApoplexySwallowReflex(int apoplexySwallowReflex) {
		this.apoplexySwallowReflex = apoplexySwallowReflex;
	}

	public int getApoplexyTongueLine() {
		return apoplexyTongueLine;
	}

	public void setApoplexyTongueLine(int apoplexyTongueLine) {
		this.apoplexyTongueLine = apoplexyTongueLine;
	}

	public int getApoplexyHeadShoulderMovement() {
		return apoplexyHeadShoulderMovement;
	}

	public void setApoplexyHeadShoulderMovement(int apoplexyHeadShoulderMovement) {
		this.apoplexyHeadShoulderMovement = apoplexyHeadShoulderMovement;
	}

	public int getApoplexyJointMovement() {
		return apoplexyJointMovement;
	}

	public void setApoplexyJointMovement(int apoplexyJointMovement) {
		this.apoplexyJointMovement = apoplexyJointMovement;
	}

	public String getApoplexyMusclePower() {
		return apoplexyMusclePower;
	}

	public void setApoplexyMusclePower(String apoplexyMusclePower) {
		this.apoplexyMusclePower = apoplexyMusclePower;
	}

	public String getApoplexyHypertonic() {
		return apoplexyHypertonic;
	}

	public void setApoplexyHypertonic(String apoplexyHypertonic) {
		this.apoplexyHypertonic = apoplexyHypertonic;
	}

	public String getApoplexyFiberReflex() {
		return apoplexyFiberReflex;
	}

	public void setApoplexyFiberReflex(String apoplexyFiberReflex) {
		this.apoplexyFiberReflex = apoplexyFiberReflex;
	}

	public String getApoplexyPathogeneticReflex() {
		return apoplexyPathogeneticReflex;
	}

	public void setApoplexyPathogeneticReflex(String apoplexyPathogeneticReflex) {
		this.apoplexyPathogeneticReflex = apoplexyPathogeneticReflex;
	}

	public String getApoplexyFeelPerfunctory() {
		return apoplexyFeelPerfunctory;
	}

	public void setApoplexyFeelPerfunctory(String apoplexyFeelPerfunctory) {
		this.apoplexyFeelPerfunctory = apoplexyFeelPerfunctory;
	}

	public String getApoplexyFeelDeep() {
		return apoplexyFeelDeep;
	}

	public void setApoplexyFeelDeep(String apoplexyFeelDeep) {
		this.apoplexyFeelDeep = apoplexyFeelDeep;
	}

	public String getApoplexyRombergTest() {
		return apoplexyRombergTest;
	}

	public void setApoplexyRombergTest(String apoplexyRombergTest) {
		this.apoplexyRombergTest = apoplexyRombergTest;
	}

	public String getApoplexyFingerNoseTest() {
		return apoplexyFingerNoseTest;
	}

	public void setApoplexyFingerNoseTest(String apoplexyFingerNoseTest) {
		this.apoplexyFingerNoseTest = apoplexyFingerNoseTest;
	}

	public String getApoplexyHeelKneeTest() {
		return apoplexyHeelKneeTest;
	}

	public void setApoplexyHeelKneeTest(String apoplexyHeelKneeTest) {
		this.apoplexyHeelKneeTest = apoplexyHeelKneeTest;
	}

	public String getApoplexyRateText() {
		return apoplexyRateText;
	}

	public void setApoplexyRateText(String apoplexyRateText) {
		this.apoplexyRateText = apoplexyRateText;
	}

	public String getApoplexyAdvice() {
		return apoplexyAdvice;
	}

	public void setApoplexyAdvice(String apoplexyAdvice) {
		this.apoplexyAdvice = apoplexyAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

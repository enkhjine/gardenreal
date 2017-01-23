package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerEye implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Label(label="outbreak",labelType="p")
	public String rightEyeToggle;
	
	@Label(label="EyeRed",labelType="p",fieldType="boolean")
	public boolean rightEyeRed;
	
	@Label(label="EyeFeelSharp",labelType="p",fieldType="boolean")
	public boolean rightEyeFeelSharp;
	
	@Label(label="CatchEyesOne",labelType="p",fieldType="boolean")
	public boolean rightCatchEyesOne;
	
	@Label(label="EyeItch",labelType="p",fieldType="boolean")
	public boolean rightEyeItch;
	
	@Label(label="EyePain",labelType="p",fieldType="boolean")
	public boolean rightEyePain;
	
	@Label(label="HeadPain",labelType="p",fieldType="boolean")
	public boolean rightHeadPain;
	
	@Label(label="EyeEdema",labelType="p",fieldType="boolean")
	public boolean rightEyeEdema;
	
	@Label(label="EyeLidRed",labelType="p",fieldType="boolean")
	public boolean rightEyeLidRed;
	
	
	@Label(label="EyeMatter",labelType="p",fieldType="boolean")
	public boolean rightEyeMatter;
	
	@Label(label="EyeSight",labelType="p",fieldType="boolean")
	public boolean rightEyeSight;
	
	@Label(label="EyeLight",labelType="p",fieldType="boolean")
	public boolean rightEyeLight;
	
	@Label(label="EyeTwo",labelType="p",fieldType="boolean")
	public boolean rightEyeTwo;
	
	@Label(label="EyeCross",labelType="p",fieldType="boolean")
	public boolean rightEyeCross;
	
	@Label(label="Exophthalmus",labelType="p",fieldType="boolean")
	public boolean rightExophthalmus;
	
	@Label(label="EyeSag",labelType="p",fieldType="boolean")
	public boolean rightEyeSag;
	
	@Label(label="EyeSlouch",labelType="p",fieldType="boolean")
	public boolean rightEyeSlouch;
	
	@Label(label="LidCircle",labelType="p",fieldType="boolean")
	public boolean rightLidCircle;
	
	@Label(label="FloatSee",labelType="p",fieldType="boolean")
	public boolean rightFloatSee;
	
	@Label(label="SparkSee",labelType="p",fieldType="boolean")
	public boolean rightSparkSee;
	
	@Label(label="EyeSocketPain",labelType="p",fieldType="boolean")
	public boolean rightEyeSocketPain;
	/*
	Зүүн нүд
	  */
	@Label(label="outbreak",labelType="p")
	public String painEyeLeft;
	@Label(label="EyeRed",labelType="p",fieldType="boolean")
	public boolean leftEyeRed;
	
	@Label(label="EyeFeelSharp",labelType="p",fieldType="boolean")
	public boolean leftEyeFeelSharp;
	
	@Label(label="CatchEyesOne",labelType="p",fieldType="boolean")
	public boolean leftCatchEyesOne;
	
	@Label(label="EyeItch",labelType="p",fieldType="boolean")
	public boolean leftEyeItch;
	
	@Label(label="EyePain",labelType="p",fieldType="boolean")
	public boolean leftEyePain;
	
	@Label(label="HeadPain",labelType="p",fieldType="boolean")
	public boolean leftHeadPain;
	
	@Label(label="EyeEdema",labelType="p",fieldType="boolean")
	public boolean leftEyeEdema;
	
	@Label(label="EyeLidRed",labelType="p",fieldType="boolean")
	public boolean leftEyeLidRed;
	
	
	@Label(label="EyeMatter",labelType="p",fieldType="boolean")
	public boolean leftEyeMatter;
	
	@Label(label="EyeSight",labelType="p",fieldType="boolean")
	public boolean leftEyeSight;
	
	@Label(label="EyeLight",labelType="p",fieldType="boolean")
	public boolean leftEyeLight;
	
	@Label(label="EyeTwo",labelType="p",fieldType="boolean")
	public boolean leftEyeTwo;
	
	@Label(label="EyeCross",labelType="p",fieldType="boolean")
	public boolean leftEyeCross;
	
	@Label(label="Exophthalmus",labelType="p",fieldType="boolean")
	public boolean leftExophthalmus;
	
	@Label(label="EyeSag",labelType="p",fieldType="boolean")
	public boolean leftEyeSag;
	
	@Label(label="EyeSlouch",labelType="p",fieldType="boolean")
	public boolean leftEyeSlouch;
	
	@Label(label="LidCircle",labelType="p",fieldType="boolean")
	public boolean leftLidCircle;
	
	@Label(label="FloatSee",labelType="p",fieldType="boolean")
	public boolean leftFloatSee;
	
	@Label(label="SparkSee",labelType="p",fieldType="boolean")
	public boolean leftSparkSee;
	
	@Label(label="EyeSocketPain",labelType="p",fieldType="boolean")
	public boolean leftEyeSocketPain;
	
	@Label(label="otherPain",labelType="p")
	public String otherPain;
	
	@Label(label="outbreak",labelType="m")
	public String painHistoryEye;
	
	@Label(label="thisTimeWhenPain",labelType="m")
	public String thisTimeWhenPain;
	
	@Label(label="thisEyePain",labelType="m")
	public String thisEyePain;
	
	@Label(label="thisChangePain",labelType="m")
	public String thisChangePain;
	
	@Label(label="thisSincePain",labelType="m")
	public String thisSincePain;
	
	@Label(label="thisDoTreatment",labelType="m")
	public String thisDoTreatment;
	
	@Label(label="thisEyeSincePain",labelType="m")
	public String thisEyeSincePain;
	
	@Label(label="outbreak",labelType="m")
	public String lifeHistoryEye;
	
	@Label(label="EyeLifejob",labelType="m")
	public String eyeLifejob;
	
	@Label(label="lifeAbout",labelType="m")
	public String lifeAbout;
	
	@Label(label="thisEyeLoad",labelType="m")
	public String thisEyeLoad;
	
	@Label(label="thisEyeInfectious",labelType="m")
	public String thisEyeInfectious;
	
	@Label(label="thisEyeExtends",labelType="m")
	public String thisEyeExtends;
	
	@Label(label="thisEyeAllergies",labelType="m")
	public String thisEyeAllergies;


	@Label(label="thisEyeSurgery",labelType="m")
	public String thisEyeSurgery;
	
	@Label(label="skinRateText",labelType="p")
	public String rateText;
	
	@Label(label="advice",labelType="p")
	public String advice;
	
	//right
	@Label(label="outbreak",labelType="c")
	public String inspectionnEyeRight;
	
	@Label(label="EyeSightPower" ,labelType="c",fieldType="select", answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int rgihtSightPower;

	@Label(label="EyePascal" ,labelType="c",fieldType="select",answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int rgihtEyePascal;
	
	@Label(label="EyeAstigmaticPower" ,labelType="c",fieldType="select", answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int rgihtAstigmaticPower;
	
	@Label(label="EyeSight",labelType="c")
	public String  rgihtSight;

	@Label(label="EyeColorAstigmatic",labelType="c")
	public String  rgihtColorAstigmatic;
	
	@Label(label="EyeEyeLocation",labelType="c")
	public String  rgihtEyeLocation;
	
	
	@Label(label="EyeEyeMove",labelType="c")
	public String  rgihtEyeMove;
	
	
	@Label(label="EyeEyePupilReaction",labelType="c")
	public String  rgihtEyePupilReaction;
	
	
	@Label(label="EyeLid",labelType="c")
	public String  rgihtLid;
	
	
	@Label(label="EyeMucous",labelType="c")
	public String  rgihtMucous;
	
	
	@Label(label="EyeEyeTear",labelType="c")
	public String  rgihtEyeTear;
	
	
	@Label(label="EyeHard",labelType="c")
	public String  rgihtHard;
	
	
	@Label(label="EyeCorneous",labelType="c")
	public String  rgihtCorneous;
	
	
	@Label(label="EyeBeforeCage",labelType="c")
	public String  rgihtBeforeCage;
	
	
	@Label(label="EyeIris",labelType="c")
	public String  rgihtIris;
	
	
	@Label(label="EyeEyePupil",labelType="c")
	public String  rgihtEyePupil;
	
	
	@Label(label="EyeCrystal",labelType="c")
	public String  rgihtCrystal;
	
	@Label(label="EyeGlassBody",labelType="c")
	public String  rgihtGlassBody;
	
	@Label(label="EyeNervousBlue",labelType="c")
	public String  rgihtNervousBlue;
	
	
	@Label(label="EyeYellowBlindSpot",labelType="c")
	public String  rgihtYellowBlindSpot;
	
	@Label(label="EyeRetina",labelType="c")
	public String  rgihtRetina;
	
	@Label(label="EyeRentinaSphygmic",labelType="c")
	public String  rgihtRentinaSphygmic;
	
	
	//left
	@Label(label="outbreak",labelType="c")
	public String inspectionnEyeLeft;
	
	@Label(label="EyeSightPower" ,labelType="c",fieldType="select", answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int leftSightPower;

	@Label(label="EyePascal" ,labelType="c",fieldType="select",answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int leftEyePascal;
	
	@Label(label="EyeAstigmaticPower" ,labelType="c",fieldType="select", answers={"zeroOne","zeroTwo","zeroThree","zeroFour","zeroFive","zeroSex","zeroSeven","zeroEight","EyeOne"})
	public int leftAstigmaticPower;
	
	@Label(label="EyeSight",labelType="c")
	public String  leftSight;

	@Label(label="EyeColorAstigmatic",labelType="c")
	public String  leftColorAstigmatic;
	
	@Label(label="EyeEyeLocation",labelType="c")
	public String  leftEyeLocation;
	
	
	@Label(label="EyeEyeMove",labelType="c")
	public String  leftEyeMove;
	
	
	@Label(label="EyeEyePupilReaction",labelType="c")
	public String  leftEyePupilReaction;
	
	
	@Label(label="EyeLid",labelType="c")
	public String  leftLid;
	
	
	@Label(label="EyeMucous",labelType="c")
	public String  leftMucous;
	
	
	@Label(label="EyeEyeTear",labelType="c")
	public String  leftEyeTear;
	
	
	@Label(label="EyeHard",labelType="c")
	public String  leftHard;
	
	
	@Label(label="EyeCorneous",labelType="c")
	public String  leftCorneous;
	
	
	@Label(label="EyeBeforeCage",labelType="c")
	public String  leftBeforeCage;
	
	
	@Label(label="EyeIris",labelType="c")
	public String  leftIris;
	
	
	@Label(label="EyeEyePupil",labelType="c")
	public String  leftEyePupil;
	
	
	@Label(label="EyeCrystal",labelType="c")
	public String  leftCrystal;
	
	@Label(label="EyeGlassBody",labelType="c")
	public String  leftGlassBody;
	
	@Label(label="EyeNervousBlue",labelType="c")
	public String  leftNervousBlue;
	
	
	@Label(label="EyeYellowBlindSpot",labelType="c")
	public String  leftYellowBlindSpot;
	
	@Label(label="EyeRetina",labelType="c")
	public String  leftRetina;
	
	@Label(label="EyeRentinaSphygmic",labelType="c")
	public String  leftRentinaSphygmic;
	
	public boolean isRightEyeRed() {
		return rightEyeRed;
	}

	public void setRightEyeRed(boolean rightEyeRed) {
		this.rightEyeRed = rightEyeRed;
	}

	public boolean isRightEyeFeelSharp() {
		return rightEyeFeelSharp;
	}

	public void setRightEyeFeelSharp(boolean rightEyeFeelSharp) {
		this.rightEyeFeelSharp = rightEyeFeelSharp;
	}

	public boolean isRightCatchEyesOne() {
		return rightCatchEyesOne;
	}

	public void setRightCatchEyesOne(boolean rightCatchEyesOne) {
		this.rightCatchEyesOne = rightCatchEyesOne;
	}

	public boolean isRightEyeItch() {
		return rightEyeItch;
	}

	public void setRightEyeItch(boolean rightEyeItch) {
		this.rightEyeItch = rightEyeItch;
	}

	public boolean isRightEyePain() {
		return rightEyePain;
	}

	public void setRightEyePain(boolean rightEyePain) {
		this.rightEyePain = rightEyePain;
	}

	public boolean isRightHeadPain() {
		return rightHeadPain;
	}

	public void setRightHeadPain(boolean rightHeadPain) {
		this.rightHeadPain = rightHeadPain;
	}

	public boolean isRightEyeEdema() {
		return rightEyeEdema;
	}

	public void setRightEyeEdema(boolean rightEyeEdema) {
		this.rightEyeEdema = rightEyeEdema;
	}

	public boolean isRightEyeLidRed() {
		return rightEyeLidRed;
	}

	public void setRightEyeLidRed(boolean rightEyeLidRed) {
		this.rightEyeLidRed = rightEyeLidRed;
	}

	public boolean isRightEyeMatter() {
		return rightEyeMatter;
	}

	public void setRightEyeMatter(boolean rightEyeMatter) {
		this.rightEyeMatter = rightEyeMatter;
	}

	public boolean isRightEyeSight() {
		return rightEyeSight;
	}

	public void setRightEyeSight(boolean rightEyeSight) {
		this.rightEyeSight = rightEyeSight;
	}

	public boolean isRightEyeLight() {
		return rightEyeLight;
	}

	public void setRightEyeLight(boolean rightEyeLight) {
		this.rightEyeLight = rightEyeLight;
	}

	public boolean isRightEyeTwo() {
		return rightEyeTwo;
	}

	public void setRightEyeTwo(boolean rightEyeTwo) {
		this.rightEyeTwo = rightEyeTwo;
	}

	public boolean isRightEyeCross() {
		return rightEyeCross;
	}

	public void setRightEyeCross(boolean rightEyeCross) {
		this.rightEyeCross = rightEyeCross;
	}

	public boolean isRightExophthalmus() {
		return rightExophthalmus;
	}

	public void setRightExophthalmus(boolean rightExophthalmus) {
		this.rightExophthalmus = rightExophthalmus;
	}

	public boolean isRightEyeSag() {
		return rightEyeSag;
	}

	public void setRightEyeSag(boolean rightEyeSag) {
		this.rightEyeSag = rightEyeSag;
	}

	public boolean isRightEyeSlouch() {
		return rightEyeSlouch;
	}

	public void setRightEyeSlouch(boolean rightEyeSlouch) {
		this.rightEyeSlouch = rightEyeSlouch;
	}

	public boolean isRightLidCircle() {
		return rightLidCircle;
	}

	public void setRightLidCircle(boolean rightLidCircle) {
		this.rightLidCircle = rightLidCircle;
	}

	public boolean isRightFloatSee() {
		return rightFloatSee;
	}

	public void setRightFloatSee(boolean rightFloatSee) {
		this.rightFloatSee = rightFloatSee;
	}

	public boolean isRightSparkSee() {
		return rightSparkSee;
	}

	public void setRightSparkSee(boolean rightSparkSee) {
		this.rightSparkSee = rightSparkSee;
	}

	public boolean isRightEyeSocketPain() {
		return rightEyeSocketPain;
	}

	public void setRightEyeSocketPain(boolean rightEyeSocketPain) {
		this.rightEyeSocketPain = rightEyeSocketPain;
	}

	public boolean isLeftEyeRed() {
		return leftEyeRed;
	}

	public void setLeftEyeRed(boolean leftEyeRed) {
		this.leftEyeRed = leftEyeRed;
	}

	public boolean isLeftEyeFeelSharp() {
		return leftEyeFeelSharp;
	}

	public void setLeftEyeFeelSharp(boolean leftEyeFeelSharp) {
		this.leftEyeFeelSharp = leftEyeFeelSharp;
	}

	public boolean isLeftCatchEyesOne() {
		return leftCatchEyesOne;
	}

	public void setLeftCatchEyesOne(boolean leftCatchEyesOne) {
		this.leftCatchEyesOne = leftCatchEyesOne;
	}

	public boolean isLeftEyeItch() {
		return leftEyeItch;
	}

	public void setLeftEyeItch(boolean leftEyeItch) {
		this.leftEyeItch = leftEyeItch;
	}

	public boolean isLeftEyePain() {
		return leftEyePain;
	}

	public void setLeftEyePain(boolean leftEyePain) {
		this.leftEyePain = leftEyePain;
	}

	public boolean isLeftHeadPain() {
		return leftHeadPain;
	}

	public void setLeftHeadPain(boolean leftHeadPain) {
		this.leftHeadPain = leftHeadPain;
	}

	public boolean isLeftEyeEdema() {
		return leftEyeEdema;
	}

	public void setLeftEyeEdema(boolean leftEyeEdema) {
		this.leftEyeEdema = leftEyeEdema;
	}

	public boolean isLeftEyeLidRed() {
		return leftEyeLidRed;
	}

	public void setLeftEyeLidRed(boolean leftEyeLidRed) {
		this.leftEyeLidRed = leftEyeLidRed;
	}

	public boolean isLeftEyeMatter() {
		return leftEyeMatter;
	}

	public void setLeftEyeMatter(boolean leftEyeMatter) {
		this.leftEyeMatter = leftEyeMatter;
	}

	public boolean isLeftEyeSight() {
		return leftEyeSight;
	}

	public void setLeftEyeSight(boolean leftEyeSight) {
		this.leftEyeSight = leftEyeSight;
	}

	public boolean isLeftEyeLight() {
		return leftEyeLight;
	}

	public void setLeftEyeLight(boolean leftEyeLight) {
		this.leftEyeLight = leftEyeLight;
	}

	public boolean isLeftEyeTwo() {
		return leftEyeTwo;
	}

	public void setLeftEyeTwo(boolean leftEyeTwo) {
		this.leftEyeTwo = leftEyeTwo;
	}

	public boolean isLeftEyeCross() {
		return leftEyeCross;
	}

	public void setLeftEyeCross(boolean leftEyeCross) {
		this.leftEyeCross = leftEyeCross;
	}

	public boolean isLeftExophthalmus() {
		return leftExophthalmus;
	}

	public void setLeftExophthalmus(boolean leftExophthalmus) {
		this.leftExophthalmus = leftExophthalmus;
	}

	public boolean isLeftEyeSag() {
		return leftEyeSag;
	}

	public void setLeftEyeSag(boolean leftEyeSag) {
		this.leftEyeSag = leftEyeSag;
	}

	public boolean isLeftEyeSlouch() {
		return leftEyeSlouch;
	}

	public void setLeftEyeSlouch(boolean leftEyeSlouch) {
		this.leftEyeSlouch = leftEyeSlouch;
	}

	public boolean isLeftLidCircle() {
		return leftLidCircle;
	}

	public void setLeftLidCircle(boolean leftLidCircle) {
		this.leftLidCircle = leftLidCircle;
	}

	public boolean isLeftFloatSee() {
		return leftFloatSee;
	}

	public void setLeftFloatSee(boolean leftFloatSee) {
		this.leftFloatSee = leftFloatSee;
	}

	public boolean isLeftSparkSee() {
		return leftSparkSee;
	}

	public void setLeftSparkSee(boolean leftSparkSee) {
		this.leftSparkSee = leftSparkSee;
	}

	public boolean isLeftEyeSocketPain() {
		return leftEyeSocketPain;
	}

	public void setLeftEyeSocketPain(boolean leftEyeSocketPain) {
		this.leftEyeSocketPain = leftEyeSocketPain;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getThisTimeWhenPain() {
		return thisTimeWhenPain;
	}

	public void setThisTimeWhenPain(String thisTimeWhenPain) {
		this.thisTimeWhenPain = thisTimeWhenPain;
	}

	public String getThisEyePain() {
		return thisEyePain;
	}

	public void setThisEyePain(String thisEyePain) {
		this.thisEyePain = thisEyePain;
	}

	public String getThisChangePain() {
		return thisChangePain;
	}

	public void setThisChangePain(String thisChangePain) {
		this.thisChangePain = thisChangePain;
	}

	public String getThisSincePain() {
		return thisSincePain;
	}

	public void setThisSincePain(String thisSincePain) {
		this.thisSincePain = thisSincePain;
	}

	public String getThisDoTreatment() {
		return thisDoTreatment;
	}

	public void setThisDoTreatment(String thisDoTreatment) {
		this.thisDoTreatment = thisDoTreatment;
	}

	public String getThisEyeSincePain() {
		return thisEyeSincePain;
	}

	public void setThisEyeSincePain(String thisEyeSincePain) {
		this.thisEyeSincePain = thisEyeSincePain;
	}

	public String getEyeLifejob() {
		return eyeLifejob;
	}

	public void setEyeLifejob(String eyeLifejob) {
		this.eyeLifejob = eyeLifejob;
	}

	public String getLifeAbout() {
		return lifeAbout;
	}

	public void setLifeAbout(String lifeAbout) {
		this.lifeAbout = lifeAbout;
	}

	public String getThisEyeLoad() {
		return thisEyeLoad;
	}

	public void setThisEyeLoad(String thisEyeLoad) {
		this.thisEyeLoad = thisEyeLoad;
	}

	public String getThisEyeInfectious() {
		return thisEyeInfectious;
	}

	public void setThisEyeInfectious(String thisEyeInfectious) {
		this.thisEyeInfectious = thisEyeInfectious;
	}

	public String getThisEyeExtends() {
		return thisEyeExtends;
	}

	public void setThisEyeExtends(String thisEyeExtends) {
		this.thisEyeExtends = thisEyeExtends;
	}

	public String getThisEyeAllergies() {
		return thisEyeAllergies;
	}

	public void setThisEyeAllergies(String thisEyeAllergies) {
		this.thisEyeAllergies = thisEyeAllergies;
	}

	public String getThisEyeSurgery() {
		return thisEyeSurgery;
	}

	public void setThisEyeSurgery(String thisEyeSurgery) {
		this.thisEyeSurgery = thisEyeSurgery;
	}

	public String getRateText() {
		return rateText;
	}

	public void setRateText(String rateText) {
		this.rateText = rateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public String getRightEyeToggle() {
		return rightEyeToggle;
	}

	public void setRightEyeToggle(String rightEyeToggle) {
		this.rightEyeToggle = rightEyeToggle;
	}

	public int getRgihtSightPower() {
		return rgihtSightPower;
	}

	public void setRgihtSightPower(int rgihtSightPower) {
		this.rgihtSightPower = rgihtSightPower;
	}
	

	public int getRgihtEyePascal() {
		return rgihtEyePascal;
	}

	public void setRgihtEyePascal(int rgihtEyePascal) {
		this.rgihtEyePascal = rgihtEyePascal;
	}

	public int getRgihtAstigmaticPower() {
		return rgihtAstigmaticPower;
	}

	public void setRgihtAstigmaticPower(int rgihtAstigmaticPower) {
		this.rgihtAstigmaticPower = rgihtAstigmaticPower;
	}

	public String getRgihtSight() {
		return rgihtSight;
	}

	public void setRgihtSight(String rgihtSight) {
		this.rgihtSight = rgihtSight;
	}

	public String getRgihtColorAstigmatic() {
		return rgihtColorAstigmatic;
	}

	public void setRgihtColorAstigmatic(String rgihtColorAstigmatic) {
		this.rgihtColorAstigmatic = rgihtColorAstigmatic;
	}

	public String getRgihtEyeLocation() {
		return rgihtEyeLocation;
	}

	public void setRgihtEyeLocation(String rgihtEyeLocation) {
		this.rgihtEyeLocation = rgihtEyeLocation;
	}

	public String getRgihtEyeMove() {
		return rgihtEyeMove;
	}

	public void setRgihtEyeMove(String rgihtEyeMove) {
		this.rgihtEyeMove = rgihtEyeMove;
	}

	public String getRgihtEyePupilReaction() {
		return rgihtEyePupilReaction;
	}

	public void setRgihtEyePupilReaction(String rgihtEyePupilReaction) {
		this.rgihtEyePupilReaction = rgihtEyePupilReaction;
	}

	public String getRgihtLid() {
		return rgihtLid;
	}

	public void setRgihtLid(String rgihtLid) {
		this.rgihtLid = rgihtLid;
	}

	public String getRgihtMucous() {
		return rgihtMucous;
	}

	public void setRgihtMucous(String rgihtMucous) {
		this.rgihtMucous = rgihtMucous;
	}

	public String getRgihtEyeTear() {
		return rgihtEyeTear;
	}

	public void setRgihtEyeTear(String rgihtEyeTear) {
		this.rgihtEyeTear = rgihtEyeTear;
	}

	public String getRgihtHard() {
		return rgihtHard;
	}

	public void setRgihtHard(String rgihtHard) {
		this.rgihtHard = rgihtHard;
	}

	public String getRgihtCorneous() {
		return rgihtCorneous;
	}

	public void setRgihtCorneous(String rgihtCorneous) {
		this.rgihtCorneous = rgihtCorneous;
	}

	public String getRgihtBeforeCage() {
		return rgihtBeforeCage;
	}

	public void setRgihtBeforeCage(String rgihtBeforeCage) {
		this.rgihtBeforeCage = rgihtBeforeCage;
	}

	public String getRgihtIris() {
		return rgihtIris;
	}

	public void setRgihtIris(String rgihtIris) {
		this.rgihtIris = rgihtIris;
	}

	public String getRgihtEyePupil() {
		return rgihtEyePupil;
	}

	public void setRgihtEyePupil(String rgihtEyePupil) {
		this.rgihtEyePupil = rgihtEyePupil;
	}

	public String getRgihtCrystal() {
		return rgihtCrystal;
	}

	public void setRgihtCrystal(String rgihtCrystal) {
		this.rgihtCrystal = rgihtCrystal;
	}

	public String getRgihtGlassBody() {
		return rgihtGlassBody;
	}

	public void setRgihtGlassBody(String rgihtGlassBody) {
		this.rgihtGlassBody = rgihtGlassBody;
	}

	public String getRgihtNervousBlue() {
		return rgihtNervousBlue;
	}

	public void setRgihtNervousBlue(String rgihtNervousBlue) {
		this.rgihtNervousBlue = rgihtNervousBlue;
	}

	public String getRgihtYellowBlindSpot() {
		return rgihtYellowBlindSpot;
	}

	public void setRgihtYellowBlindSpot(String rgihtYellowBlindSpot) {
		this.rgihtYellowBlindSpot = rgihtYellowBlindSpot;
	}

	public String getRgihtRetina() {
		return rgihtRetina;
	}

	public void setRgihtRetina(String rgihtRetina) {
		this.rgihtRetina = rgihtRetina;
	}

	public String getRgihtRentinaSphygmic() {
		return rgihtRentinaSphygmic;
	}

	public void setRgihtRentinaSphygmic(String rgihtRentinaSphygmic) {
		this.rgihtRentinaSphygmic = rgihtRentinaSphygmic;
	}

	public int getLeftSightPower() {
		return leftSightPower;
	}

	public void setLeftSightPower(int leftSightPower) {
		this.leftSightPower = leftSightPower;
	}

	public int getLeftEyePascal() {
		return leftEyePascal;
	}

	public void setLeftEyePascal(int leftEyePascal) {
		this.leftEyePascal = leftEyePascal;
	}

	public int getLeftAstigmaticPower() {
		return leftAstigmaticPower;
	}

	public void setLeftAstigmaticPower(int leftAstigmaticPower) {
		this.leftAstigmaticPower = leftAstigmaticPower;
	}

	public String getLeftSight() {
		return leftSight;
	}

	public void setLeftSight(String leftSight) {
		this.leftSight = leftSight;
	}

	public String getLeftColorAstigmatic() {
		return leftColorAstigmatic;
	}

	public void setLeftColorAstigmatic(String leftColorAstigmatic) {
		this.leftColorAstigmatic = leftColorAstigmatic;
	}

	public String getLeftEyeLocation() {
		return leftEyeLocation;
	}

	public void setLeftEyeLocation(String leftEyeLocation) {
		this.leftEyeLocation = leftEyeLocation;
	}

	public String getLeftEyeMove() {
		return leftEyeMove;
	}

	public void setLeftEyeMove(String leftEyeMove) {
		this.leftEyeMove = leftEyeMove;
	}

	public String getLeftEyePupilReaction() {
		return leftEyePupilReaction;
	}

	public void setLeftEyePupilReaction(String leftEyePupilReaction) {
		this.leftEyePupilReaction = leftEyePupilReaction;
	}

	public String getLeftLid() {
		return leftLid;
	}

	public void setLeftLid(String leftLid) {
		this.leftLid = leftLid;
	}

	public String getLeftMucous() {
		return leftMucous;
	}

	public void setLeftMucous(String leftMucous) {
		this.leftMucous = leftMucous;
	}

	public String getLeftEyeTear() {
		return leftEyeTear;
	}

	public void setLeftEyeTear(String leftEyeTear) {
		this.leftEyeTear = leftEyeTear;
	}

	public String getLeftHard() {
		return leftHard;
	}

	public void setLeftHard(String leftHard) {
		this.leftHard = leftHard;
	}

	public String getLeftCorneous() {
		return leftCorneous;
	}

	public void setLeftCorneous(String leftCorneous) {
		this.leftCorneous = leftCorneous;
	}

	public String getLeftBeforeCage() {
		return leftBeforeCage;
	}

	public void setLeftBeforeCage(String leftBeforeCage) {
		this.leftBeforeCage = leftBeforeCage;
	}

	public String getLeftIris() {
		return leftIris;
	}

	public void setLeftIris(String leftIris) {
		this.leftIris = leftIris;
	}

	public String getLeftEyePupil() {
		return leftEyePupil;
	}

	public void setLeftEyePupil(String leftEyePupil) {
		this.leftEyePupil = leftEyePupil;
	}

	public String getLeftCrystal() {
		return leftCrystal;
	}

	public void setLeftCrystal(String leftCrystal) {
		this.leftCrystal = leftCrystal;
	}

	public String getLeftGlassBody() {
		return leftGlassBody;
	}

	public void setLeftGlassBody(String leftGlassBody) {
		this.leftGlassBody = leftGlassBody;
	}

	public String getLeftNervousBlue() {
		return leftNervousBlue;
	}

	public void setLeftNervousBlue(String leftNervousBlue) {
		this.leftNervousBlue = leftNervousBlue;
	}

	public String getLeftYellowBlindSpot() {
		return leftYellowBlindSpot;
	}

	public void setLeftYellowBlindSpot(String leftYellowBlindSpot) {
		this.leftYellowBlindSpot = leftYellowBlindSpot;
	}

	public String getLeftRetina() {
		return leftRetina;
	}

	public void setLeftRetina(String leftRetina) {
		this.leftRetina = leftRetina;
	}

	public String getLeftRentinaSphygmic() {
		return leftRentinaSphygmic;
	}

	public void setLeftRentinaSphygmic(String leftRentinaSphygmic) {
		this.leftRentinaSphygmic = leftRentinaSphygmic;
	}

	public String getPainHistoryEye() {
		return painHistoryEye;
	}

	public void setPainHistoryEye(String painHistoryEye) {
		this.painHistoryEye = painHistoryEye;
	}

	public String getLifeHistoryEye() {
		return lifeHistoryEye;
	}

	public void setLifeHistoryEye(String lifeHistoryEye) {
		this.lifeHistoryEye = lifeHistoryEye;
	}

	public String getInspectionnEyeRight() {
		return inspectionnEyeRight;
	}

	public void setInspectionnEyeRight(String inspectionnEyeRight) {
		this.inspectionnEyeRight = inspectionnEyeRight;
	}

	public String getInspectionnEyeLeft() {
		return inspectionnEyeLeft;
	}

	public void setInspectionnEyeLeft(String inspectionnEyeLeft) {
		this.inspectionnEyeLeft = inspectionnEyeLeft;
	}

	public String getPainEyeLeft() {
		return painEyeLeft;
	}

	public void setPainEyeLeft(String painEyeLeft) {
		this.painEyeLeft = painEyeLeft;
	}
	
	
}

package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerTradition implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Label(label="generalBody",labelType="c",fieldType="select",answers={"medium","hardMedium","hard","reallyHard"})
	public int generalBody;
	
	@Label(label="tradMetation",labelType="c",fieldType="select",answers={"light","cloudy","cold"})
	public int tradMetation;
	
	@Label(label="skinMucous",labelType="c",fieldType="select",answers={"normal","noNormal"})
	public int skinMucous;
	
	@Label(label="outbreak",labelType="c")
	public String  windElementToggle;

	@Label(label="minuteOne",labelType="c")
	public String  minuteOne;
	
	@Label(label="sound",labelType="c",fieldType="select",answers={"lung","wheeze","flexibletube","weakBreath"})
	public int sound;
	
	
	@Label(label="sphygmic",labelType="c")
	public String sphygmic;
	
	@Label(label="powerRepletion",labelType="c")
	public String powerRepletion;
	
	@Label(label="heartLimit",labelType="c",fieldType="select",answers={"normal","big"})
	public int heartLimit;
	
	@Label(label="heartAudio",labelType="c",fieldType="select",answers={"bright","cloudy","cloudth","bakTemperature"})
	public int heartAudio;
	
	
	@Label(label="rightAd",labelType="c")
	public String  rightAd;
	
	
	@Label(label="leftAd",labelType="c")
	public int leftAd;
	
	@Label(label="rightAdNumber",labelType="c")
	public int rightAdNumber;
	
	
	@Label(label="leftAdNumber",labelType="c")
	public int leftAdNumber;
	//Хоол  шингээх  эрхтэн  тонтолцоо
	@Label(label="outbreak",labelType="c")
	public String cookInceptToggle;
	
	@Label(label="lingua",labelType="c",fieldType="select",answers={"normal","dry","noFurred","furred"})
	public int lingua;
	
	
	@Label(label="bowelInspection",labelType="c",fieldType="select",answers={"superficalPalpation","deepPalpation","pempared","normal","membran","membran"})
	public int bowelInspection;
	
	///Мэдрэлийн  эрхтэн  тогтолцоо
	@Label(label="outbreak",labelType="c")
	public String nervousToggle;
	
	@Label(label="listenRate",labelType="c",fieldType="select",answers={"light","cloudy","cold"})
	public int listenRate;
	
	
	@Label(label="reflex",labelType="c",fieldType="select",answers={"save","noSave"})
	public int reflex;
	
	@Label(label="other",labelType="c")
	public String other;
	
	@Label(label="mentalityLevel",labelType="c")
	public String mentalityLevel;
	
	//Өвөрчлөл
	@Label(label="outbreak",labelType="c")
	public String uvurchlulToggle;
	
	@Label(label="givingTrad",labelType="c",fieldType="select",answers={"hysteria","yellow","cancerSmatoch","hysteriaAndYellow","hysteriaAndcancerSmatoch ","YellowCancerSmatoch","hysteriayellowcancerSmatoch"})
	public int giving;
	
	
	@Label(label="emberBlazonry",labelType="c",fieldType="select",answers={"good","mild","poor"})
	public int emberBlazonry;
	
	
	//Үзэх шинжилгээ
	@Label(label="outbreak",labelType="c")
	public String inspectionToggle;
	
	@Label(label="faceTradition",labelType="c",fieldType="select",answers={"dry","brownBlackColor","other"})
	public int face;
	
	@Label(label="eye",labelType="c",fieldType="select",answers={"dry","whiteEyeBlue","lookUnstable","manyFlashes","tearoutflow","other"})
	public int eye;
	
	
	@Label(label="ear",labelType="c",fieldType="select",answers={"blueLight","clamourItch","other"})
	public int ears;
	
	@Label(label="nose",labelType="c",fieldType="select",answers={"blueLight","noseCongestion","smelNotFeel"})
	public int nose;
	
	@Label(label="lips",labelType="c",fieldType="select",answers={"blueLight","bending","shakingConvulsions","other"})
	public int lips;
	
	@Label(label="lingua",labelType="c",fieldType="select",answers={"redColorBlueLight","dry","Nocold","blisters","rigidShort","musty"})
	public int insExamLingua;
	
	
	@Label(label="dejecture",labelType="c",fieldType="select",answers={"liquid","bubbleMany","manure","other"})
	public int dejecture;
	
	
	@Label(label="pee",labelType="c",fieldType="select",answers={"Bluelight","waterCrystal","smellLitte","BigBubble","fineCrushed","transformatedLaterCrystal","other"})
	public int pee;
	
	
	@Label(label="perspiration",labelType="c",fieldType="select",answers={"chooseTrad","littleSize","other"})
	public int perspiration;
	
	@Label(label="hair",labelType="c",fieldType="select",answers={"blackBrownColor","BlackTrad","DryTrad","curlyTrad","other"})
	public int hair;
	
	
	@Label(label="tooth",labelType="c",fieldType="select",answers={"choose","invadingTrad","BigTrad","wastedgumsTrad","other"})
	public int tooth;
	
	@Label(label="nail",labelType="c",fieldType="select",answers={"blue","shrivel","thickens","solid","other"})
	public int nail;
		///	Бадган
	@Label(label="outbreak",labelType="c")
	public String inspectionStomatchtoggle;
	
	@Label(label="face",labelType="c",fieldType="select",answers={"none","puffedUp","lightColor","other"})
	public int cancerStomachface;

	@Label(label="eye",labelType="c",fieldType="select",answers={"none","whiteEye","fatty","lookTradEye","eyeBrow","other"})
	public int cancerStomacheye;
	
	@Label(label="ear",labelType="c",fieldType="select",answers={"none","whiteColor","lieMany","other"})
	public int cancerStomachears;
	
	@Label(label="nose",labelType="c",fieldType="select",answers={"none","nosedischargeTrad","other"})
	public int cancerStomachnose;
	
	@Label(label="lips",labelType="c",fieldType="select",answers={"none","lightColor","ColortoScum","juiceTraditation","other"})
	public int cancerStomachlips;
	
	@Label(label="lingua",labelType="c",fieldType="select",answers={"none","lightPinkColor","big","mildTraditation","nastyChapTrad","sweetTasteTrad","flavourTasteTrad","other"})
	public int cancerStomachnsExaLingua;
	
	@Label(label="dejecture",labelType="c",fieldType="select",answers={"none","lightYellowColor","dairyDrink","stickyTrad","lemonyFlovorous","other"})
	public int dcancerStomacheject;
	
	@Label(label="pee",labelType="c",fieldType="select",answers={"none","lightYellowColor","flovorouslitter" ,"bubbleSmallSlowClear","bunchedtoSunkenCurdled" ,"lightYellowColorSlushy","other" })
	public int cancerStomachpee;
	
	@Label(label="perspiration",labelType="c",fieldType="select",answers={"none","mildTraditation","other"})
	public int cancerStomachtooth;
	
	@Label(label="hair",labelType="c",fieldType="select",answers={"none","shinyDark","oilyTraditation","tangledTradition","other"})
	public int pcancerStomacherspi;
	
	@Label(label="tooth",labelType="c",fieldType="select",answers={"none","white","solidTraditation","other"})
	public int cancerStomachhair;
	
	@Label(label="nail",labelType="c",fieldType="select",answers={"none","lightTraditation","whiteSpotTrad","other"})
	public int cancerStomachnail;
	//Шар
	@Label(label="outbreak",labelType="c")
	public String yellowToggle;
	
	@Label(label="face",labelType="c",fieldType="select",answers={"none","oilyEasyMany","yellowColorTraditationColor","other"})
	public int yellowface;
	
	@Label(label="eye",labelType="c",fieldType="select",answers={"none","whiteEyeYellowColor","meatTraditation","EyefrownTrad","yellowwater","other"})
	public int yelloweye;
	
	@Label(label="ear",labelType="c",fieldType="select",answers={"none","redYellowColor","YellowWaterCerumen","other"})
	public int yellowears;
	
	@Label(label="nose",labelType="c",fieldType="select",answers={"none","noseRedHematology","other"})
	public int yellownose;
	
	@Label(label="lips",labelType="c",fieldType="select",answers={"none","titianColorTraditation","partchDrainedDry","hematologyOutTrad","other"})
	public int yellowlips;
	
	@Label(label="lingua",labelType="c",fieldType="select",answers={"none","redcolorTraditationColor","slightAnddeepyellowcolor","Tradittationredblisters","blackspotline","saltbittertoflow","other"})
	public int yellownsExalingua;
	
	@Label(label="dejecture",labelType="c",fieldType="select",answers={"none","yellowcolorTradd","hematologycompoundTrad","stinkyTraddd","other"})
	public int yelloweject;
	
	@Label(label="pee",labelType="c",fieldType="select",answers={"none","yellowtitianredcolor","smellsteamlongclear","bubblesmalllonclear","deeptoscum","deepcloddycurds","lastyellowchamouscolor","other"})
	public int yellowpee;

	@Label(label="perspiration",labelType="c",fieldType="select",answers={"none","manysizesmell","other"})
	public int yellowerspi;
	
	@Label(label="hair",labelType="c",fieldType="select",answers={"none","yellowLigthTrad","olivy","softredgrizzed","other"})
	public int yellowhair;
	
	@Label(label="tooth",labelType="c",fieldType="select",answers={"none","yellowingTrad","olivy","softgumsTrad","other"})
	public int yellowtooth;
	
	@Label(label="nail",labelType="c",fieldType="select",answers={"none","toyellowTrad","blacklineTraditation","other"})
	public int yellownail;
	
	//Хүрэлцэх шинжилгээ
	@Label(label="outbreak" ,labelType="c")
	public String examinationToggle;
	
	@Label(label="innerDeltaTrad",labelType="c",fieldType="select",answers={"none","blackwhiteTraditation","other"})
	public int innerDelta;
	
	@Label(label="innerYellowTrad",labelType="c",fieldType="select",answers={"none","intestinaltopTraditation","intestinalbottomTrad","other"})
	public int innerYellow;
	
	@Label(label="innerStomachTrad",labelType="c",fieldType="select",answers={"none","epicastricTrad","fireRevMinus","betinTrad","bladderTradditatiom","other"})
	public int innerStomach;
	
	@Label(label="innerDeltaTrad",labelType="c",fieldType="select",answers={"none","Th1Trad","Th6sphygmicTrad","Th7cardiologyTrad","L4colonoTrad","other"})
	public int backgroundDelta;
	
	@Label(label="innerYellowTrad",labelType="c",fieldType="select",answers={"none","Th2yellow","Th9liver","Th10gall","L5abdomen","other"})
	public int backgroundYellow;
	
	@Label(label="innerStomachTrad",labelType="c",fieldType="select",answers={"none","Th3canceofthestomach","Th11tospleen","Th12craw","S1bladder","other"})
	public int backgroundStomach;
	
	@Label(label="edemaTurgor",labelType="c",fieldType="select",answers={"none","dryroughcoldfastgrowingdecline","other"})
	public int edema;
	
	@Label(label="skin",labelType="c",fieldType="select",answers={"none","dry","rapidTrad","cold","other"})
	public int skin;
	
	@Label(label="edemaTurgor",labelType="c",fieldType="select",answers={"none","painhold","other"})
	public int edemaYellow;
	
	@Label(label="skin",labelType="c",fieldType="select",answers={"none","easyTraditation","olivy","holdTraditation","other"})
	public int skinYellow;
	
	@Label(label="edemaTurgor",labelType="c",fieldType="select",answers={"none","coldPainLittleTrad","other"})
	public int edemaStomach;
	
	@Label(label="skin",labelType="c",fieldType="select",answers={"none","collopTrad","olivy","cold","other"})
	public int skinStomach;
	
	@Label(label="starve",labelType="c",fieldType="select",answers={"none","NoRegular","other"})
	public int starve ;
	
	@Label(label="thirst",labelType="c",fieldType="select",answers={"none","NoRegular","other"})
	public int thirst;
	
	@Label(label="sleepTrad",labelType="c",fieldType="select",answers={"none","wakeTrad","toDreamTrad","other"})
	public int  sleep;
	
	@Label(label="talk",labelType="c",fieldType="select",answers={"none","fastTrad","toManyWord","other"})
	public int  talk;
	
	@Label(label="figure",labelType="c",fieldType="select",answers={"none","skinnyTrad","other"})
	public int figure;
	
	@Label(label="outer",labelType="c",fieldType="select",answers={"none","moveFastTrad","other"})
	public int outer;
	
	@Label(label="mental",labelType="c",fieldType="select",answers={"none","thinksTraditations","other"})
	public int mental;
	
	@Label(label="essence",labelType="c",fieldType="select",answers={"none","onlyTrad","CoupleTrad","bloodshotTrad","other"})
	public int essence;
	
	@Label(label="starve",labelType="c",fieldType="select",answers={"none","ToManyTrad","other"})
	public int starveYellow;
	
	@Label(label="thirst",labelType="c",fieldType="select",answers={"none","ToManyTrad","other"})
	public int thirstYellow;
	
	@Label(label="sleepTrad",labelType="c",fieldType="select",answers={"none","nightNoSleepTrad","DaySleepManyTrad","other"})
	public int sleepYellow;
	
	@Label(label="talk",labelType="c",fieldType="select",answers={"none","dramaticDryTrad","other"})
	public int talkYellow;
	
	@Label(label="figure",labelType="c",fieldType="select",answers={"none","MediumLevelTrad","other"})
	public int figureYellow;
	
	@Label(label="outer",labelType="c",fieldType="select",answers={"none","MediumLevelTrad","other"})
	public int outerYellow;
	
	@Label(label="mental",labelType="c",fieldType="select",answers={"none","fastMentalTrad","other"})
	public int mentalYellow;
	
	@Label(label="essence",labelType="c",fieldType="select",answers={"none","onlyTrad","CoupleTrad","bloodshotTrad","other"})
	public int essenceYellow;
	
	@Label(label="starve",labelType="c",fieldType="select",answers={"none","MinusTrad","other"})
	public int starveStomach;
	
	@Label(label="thirst",labelType="c",fieldType="select",answers={"none","MinusTrad","other"})
	public int thirstStomach;
	
	@Label(label="sleepTrad",labelType="c",fieldType="select",answers={"none","toManySleeps","other"})
	public int sleepStomach;
	
	@Label(label="talk",labelType="c",fieldType="select",answers={"none","slowTraditation","littleWordTalkTrad","other"})
	public int talkStomach;
	
	@Label(label="figure",labelType="c",fieldType="select",answers={"none","fatTrad","other"})
	public int figureStomach;
	
	@Label(label="outer",labelType="c",fieldType="select",answers={"none","MoveSlowTrad","other"})
	public int outerStomach;
	
	@Label(label="mental",labelType="c",fieldType="select",answers={"none","pacificSlowTrad","other"})
	public int mentalStomach;
	
	@Label(label="essence",labelType="c",fieldType="select",answers={"none","onlyTrad","CoupleTrad","bloodshotTrad","other"})
	public int essenceStomach;
	
	@Label(label="otherPain",labelType="c")
	public String otherPain;
	
	
	public int getGeneralBody() {
		return generalBody;
	}
	public void setGeneralBody(int generalBody) {
		this.generalBody = generalBody;
	}
	public int getTradMetation() {
		return tradMetation;
	}
	public void setTradMetation(int tradMetation) {
		this.tradMetation = tradMetation;
	}
	public int getSkinMucous() {
		return skinMucous;
	}
	public void setSkinMucous(int skinMucous) {
		this.skinMucous = skinMucous;
	}
	public String getMinuteOne() {
		return minuteOne;
	}
	public void setMinuteOne(String minuteOne) {
		this.minuteOne = minuteOne;
	}
	public int getSound() {
		return sound;
	}
	public void setSound(int sound) {
		this.sound = sound;
	}
	public String getSphygmic() {
		return sphygmic;
	}
	public void setSphygmic(String sphygmic) {
		this.sphygmic = sphygmic;
	}
	public String getPowerRepletion() {
		return powerRepletion;
	}
	public void setPowerRepletion(String powerRepletion) {
		this.powerRepletion = powerRepletion;
	}
	public int getHeartLimit() {
		return heartLimit;
	}
	public void setHeartLimit(int heartLimit) {
		this.heartLimit = heartLimit;
	}
	public int getHeartAudio() {
		return heartAudio;
	}
	public void setHeartAudio(int heartAudio) {
		this.heartAudio = heartAudio;
	}
	public String getRightAd() {
		return rightAd;
	}
	public void setRightAd(String rightAd) {
		this.rightAd = rightAd;
	}
	public int getLeftAd() {
		return leftAd;
	}
	public void setLeftAd(int leftAd) {
		this.leftAd = leftAd;
	}
	public int getRightAdNumber() {
		return rightAdNumber;
	}
	public void setRightAdNumber(int rightAdNumber) {
		this.rightAdNumber = rightAdNumber;
	}
	public int getLeftAdNumber() {
		return leftAdNumber;
	}
	public void setLeftAdNumber(int leftAdNumber) {
		this.leftAdNumber = leftAdNumber;
	}
	public int getLingua() {
		return lingua;
	}
	public void setLingua(int lingua) {
		this.lingua = lingua;
	}
	public int getBowelInspection() {
		return bowelInspection;
	}
	public void setBowelInspection(int bowelInspection) {
		this.bowelInspection = bowelInspection;
	}
	public int getListenRate() {
		return listenRate;
	}
	public void setListenRate(int listenRate) {
		this.listenRate = listenRate;
	}
	public int getReflex() {
		return reflex;
	}
	public void setReflex(int reflex) {
		this.reflex = reflex;
	}

	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

	public String getMentalityLevel() {
		return mentalityLevel;
	}
	public void setMentalityLevel(String mentalityLevel) {
		this.mentalityLevel = mentalityLevel;
	}
	public int getGiving() {
		return giving;
	}
	public void setGiving(int giving) {
		this.giving = giving;
	}
	public int getEmberBlazonry() {
		return emberBlazonry;
	}
	public void setEmberBlazonry(int emberBlazonry) {
		this.emberBlazonry = emberBlazonry;
	}
	public int getFace() {
		return face;
	}
	public void setFace(int face) {
		this.face = face;
	}
	public int getEye() {
		return eye;
	}
	public void setEye(int eye) {
		this.eye = eye;
	}
	public int getEars() {
		return ears;
	}
	public void setEars(int ears) {
		this.ears = ears;
	}
	public int getNose() {
		return nose;
	}
	public void setNose(int nose) {
		this.nose = nose;
	}
	public int getLips() {
		return lips;
	}
	public void setLips(int lips) {
		this.lips = lips;
	}
	public int getInsExamLingua() {
		return insExamLingua;
	}
	public void setInsExamLingua(int insExamLingua) {
		this.insExamLingua = insExamLingua;
	}
	public int getDejecture() {
		return dejecture;
	}
	public void setDejecture(int dejecture) {
		this.dejecture = dejecture;
	}
	public int getPee() {
		return pee;
	}
	public void setPee(int pee) {
		this.pee = pee;
	}
	public int getPerspiration() {
		return perspiration;
	}
	public void setPerspiration(int perspiration) {
		this.perspiration = perspiration;
	}
	public int getHair() {
		return hair;
	}
	public void setHair(int hair) {
		this.hair = hair;
	}
	public int getTooth() {
		return tooth;
	}
	public void setTooth(int tooth) {
		this.tooth = tooth;
	}
	public int getNail() {
		return nail;
	}
	public void setNail(int nail) {
		this.nail = nail;
	}
	public int getCancerStomachface() {
		return cancerStomachface;
	}
	public void setCancerStomachface(int cancerStomachface) {
		this.cancerStomachface = cancerStomachface;
	}
	public int getCancerStomacheye() {
		return cancerStomacheye;
	}
	public void setCancerStomacheye(int cancerStomacheye) {
		this.cancerStomacheye = cancerStomacheye;
	}
	public int getCancerStomachears() {
		return cancerStomachears;
	}
	public void setCancerStomachears(int cancerStomachears) {
		this.cancerStomachears = cancerStomachears;
	}
	public int getCancerStomachnose() {
		return cancerStomachnose;
	}
	public void setCancerStomachnose(int cancerStomachnose) {
		this.cancerStomachnose = cancerStomachnose;
	}
	public int getCancerStomachlips() {
		return cancerStomachlips;
	}
	public void setCancerStomachlips(int cancerStomachlips) {
		this.cancerStomachlips = cancerStomachlips;
	}
	public int getCancerStomachnsExaLingua() {
		return cancerStomachnsExaLingua;
	}
	public void setCancerStomachnsExaLingua(int cancerStomachnsExaLingua) {
		this.cancerStomachnsExaLingua = cancerStomachnsExaLingua;
	}
	public int getDcancerStomacheject() {
		return dcancerStomacheject;
	}
	public void setDcancerStomacheject(int dcancerStomacheject) {
		this.dcancerStomacheject = dcancerStomacheject;
	}
	public int getCancerStomachpee() {
		return cancerStomachpee;
	}
	public void setCancerStomachpee(int cancerStomachpee) {
		this.cancerStomachpee = cancerStomachpee;
	}
	public int getPcancerStomacherspi() {
		return pcancerStomacherspi;
	}
	public void setPcancerStomacherspi(int pcancerStomacherspi) {
		this.pcancerStomacherspi = pcancerStomacherspi;
	}
	public int getCancerStomachhair() {
		return cancerStomachhair;
	}
	public void setCancerStomachhair(int cancerStomachhair) {
		this.cancerStomachhair = cancerStomachhair;
	}

	public int getCancerStomachnail() {
		return cancerStomachnail;
	}
	public void setCancerStomachnail(int cancerStomachnail) {
		this.cancerStomachnail = cancerStomachnail;
	}
	public int getYellowface() {
		return yellowface;
	}
	public void setYellowface(int yellowface) {
		this.yellowface = yellowface;
	}

	public int getYelloweye() {
		return yelloweye;
	}
	public void setYelloweye(int yelloweye) {
		this.yelloweye = yelloweye;
	}
	public int getYellowears() {
		return yellowears;
	}
	public void setYellowears(int yellowears) {
		this.yellowears = yellowears;
	}
	public int getYellownose() {
		return yellownose;
	}
	public void setYellownose(int yellownose) {
		this.yellownose = yellownose;
	}
	public int getYellowlips() {
		return yellowlips;
	}
	public void setYellowlips(int yellowlips) {
		this.yellowlips = yellowlips;
	}
	public int getYellownsExalingua() {
		return yellownsExalingua;
	}
	public void setYellownsExalingua(int yellownsExalingua) {
		this.yellownsExalingua = yellownsExalingua;
	}
	public int getYelloweject() {
		return yelloweject;
	}
	public void setYelloweject(int yelloweject) {
		this.yelloweject = yelloweject;
	}

	public int getYellowpee() {
		return yellowpee;
	}
	public void setYellowpee(int yellowpee) {
		this.yellowpee = yellowpee;
	}
	public int getYellowerspi() {
		return yellowerspi;
	}
	public void setYellowerspi(int yellowerspi) {
		this.yellowerspi = yellowerspi;
	}
	public int getYellowhair() {
		return yellowhair;
	}
	public void setYellowhair(int yellowhair) {
		this.yellowhair = yellowhair;
	}
	public int getYellowtooth() {
		return yellowtooth;
	}
	public void setYellowtooth(int yellowtooth) {
		this.yellowtooth = yellowtooth;
	}
	public int getYellownail() {
		return yellownail;
	}
	public void setYellownail(int yellownail) {
		this.yellownail = yellownail;
	}
	public int getCancerStomachtooth() {
		return cancerStomachtooth;
	}
	public void setCancerStomachtooth(int cancerStomachtooth) {
		this.cancerStomachtooth = cancerStomachtooth;
	}
	public int getInnerDelta() {
		return innerDelta;
	}
	public void setInnerDelta(int innerDelta) {
		this.innerDelta = innerDelta;
	}
	public int getInnerYellow() {
		return innerYellow;
	}
	public void setInnerYellow(int innerYellow) {
		this.innerYellow = innerYellow;
	}
	public int getInnerStomach() {
		return innerStomach;
	}
	public void setInnerStomach(int innerStomach) {
		this.innerStomach = innerStomach;
	}
	public int getBackgroundDelta() {
		return backgroundDelta;
	}
	public void setBackgroundDelta(int backgroundDelta) {
		this.backgroundDelta = backgroundDelta;
	}
	public int getBackgroundYellow() {
		return backgroundYellow;
	}
	public void setBackgroundYellow(int backgroundYellow) {
		this.backgroundYellow = backgroundYellow;
	}
	public int getBackgroundStomach() {
		return backgroundStomach;
	}
	public void setBackgroundStomach(int backgroundStomach) {
		this.backgroundStomach = backgroundStomach;
	}
	public int getEdema() {
		return edema;
	}
	public void setEdema(int edema) {
		this.edema = edema;
	}
	public int getSkin() {
		return skin;
	}
	public void setSkin(int skin) {
		this.skin = skin;
	}
	public int getEdemaYellow() {
		return edemaYellow;
	}
	public void setEdemaYellow(int edemaYellow) {
		this.edemaYellow = edemaYellow;
	}
	public int getSkinYellow() {
		return skinYellow;
	}
	public void setSkinYellow(int skinYellow) {
		this.skinYellow = skinYellow;
	}
	public int getEdemaStomach() {
		return edemaStomach;
	}
	public void setEdemaStomach(int edemaStomach) {
		this.edemaStomach = edemaStomach;
	}
	public int getSkinStomach() {
		return skinStomach;
	}
	public void setSkinStomach(int skinStomach) {
		this.skinStomach = skinStomach;
	}
	public int getStarve() {
		return starve;
	}
	public void setStarve(int starve) {
		this.starve = starve;
	}
	public int getThirst() {
		return thirst;
	}
	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
	public int getSleep() {
		return sleep;
	}
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}
	public int getTalk() {
		return talk;
	}
	public void setTalk(int talk) {
		this.talk = talk;
	}
	public int getFigure() {
		return figure;
	}
	public void setFigure(int figure) {
		this.figure = figure;
	}
	public int getOuter() {
		return outer;
	}
	public void setOuter(int outer) {
		this.outer = outer;
	}
	public int getMental() {
		return mental;
	}
	public void setMental(int mental) {
		this.mental = mental;
	}
	public int getEssence() {
		return essence;
	}
	public void setEssence(int essence) {
		this.essence = essence;
	}
	public int getStarveYellow() {
		return starveYellow;
	}
	public void setStarveYellow(int starveYellow) {
		this.starveYellow = starveYellow;
	}
	public int getThirstYellow() {
		return thirstYellow;
	}
	public void setThirstYellow(int thirstYellow) {
		this.thirstYellow = thirstYellow;
	}
	public int getSleepYellow() {
		return sleepYellow;
	}
	public void setSleepYellow(int sleepYellow) {
		this.sleepYellow = sleepYellow;
	}
	public int getTalkYellow() {
		return talkYellow;
	}
	public void setTalkYellow(int talkYellow) {
		this.talkYellow = talkYellow;
	}
	public int getFigureYellow() {
		return figureYellow;
	}
	public void setFigureYellow(int figureYellow) {
		this.figureYellow = figureYellow;
	}
	public int getOuterYellow() {
		return outerYellow;
	}
	public void setOuterYellow(int outerYellow) {
		this.outerYellow = outerYellow;
	}
	public int getMentalYellow() {
		return mentalYellow;
	}
	public void setMentalYellow(int mentalYellow) {
		this.mentalYellow = mentalYellow;
	}
	public int getEssenceYellow() {
		return essenceYellow;
	}
	public void setEssenceYellow(int essenceYellow) {
		this.essenceYellow = essenceYellow;
	}
	public int getStarveStomach() {
		return starveStomach;
	}
	public void setStarveStomach(int starveStomach) {
		this.starveStomach = starveStomach;
	}
	public int getThirstStomach() {
		return thirstStomach;
	}
	public void setThirstStomach(int thirstStomach) {
		this.thirstStomach = thirstStomach;
	}
	public int getSleepStomach() {
		return sleepStomach;
	}
	public void setSleepStomach(int sleepStomach) {
		this.sleepStomach = sleepStomach;
	}
	public int getTalkStomach() {
		return talkStomach;
	}
	public void setTalkStomach(int talkStomach) {
		this.talkStomach = talkStomach;
	}
	public int getFigureStomach() {
		return figureStomach;
	}
	public void setFigureStomach(int figureStomach) {
		this.figureStomach = figureStomach;
	}
	public int getOuterStomach() {
		return outerStomach;
	}
	public void setOuterStomach(int outerStomach) {
		this.outerStomach = outerStomach;
	}
	public int getMentalStomach() {
		return mentalStomach;
	}
	public void setMentalStomach(int mentalStomach) {
		this.mentalStomach = mentalStomach;
	}
	public int getEssenceStomach() {
		return essenceStomach;
	}
	public void setEssenceStomach(int essenceStomach) {
		this.essenceStomach = essenceStomach;
	}
	public String getOtherPain() {
		return otherPain;
	}
	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}
	public String getWindElementToggle() {
		return windElementToggle;
	}
	public void setWindElementToggle(String windElementToggle) {
		this.windElementToggle = windElementToggle;
	}
	
	public String getCookInceptToggle() {
		return cookInceptToggle;
	}
	public void setCookInceptToggle(String cookInceptToggle) {
		this.cookInceptToggle = cookInceptToggle;
	}
	public String getNervousToggle() {
		return nervousToggle;
	}
	public void setNervousToggle(String nervousToggle) {
		this.nervousToggle = nervousToggle;
	}
	public String getUvurchlulToggle() {
		return uvurchlulToggle;
	}
	public void setUvurchlulToggle(String uvurchlulToggle) {
		this.uvurchlulToggle = uvurchlulToggle;
	}
	public String getInspectionToggle() {
		return inspectionToggle;
	}
	public void setInspectionToggle(String inspectionToggle) {
		this.inspectionToggle = inspectionToggle;
	}
	public String getExaminationToggle() {
		return examinationToggle;
	}
	public void setExaminationToggle(String examinationToggle) {
		this.examinationToggle = examinationToggle;
	}
	public String getInspectionStomatchtoggle() {
		return inspectionStomatchtoggle;
	}
	public void setInspectionStomatchtoggle(String inspectionStomatchtoggle) {
		this.inspectionStomatchtoggle = inspectionStomatchtoggle;
	}
	public String getYellowToggle() {
		return yellowToggle;
	}
	public void setYellowToggle(String yellowToggle) {
		this.yellowToggle = yellowToggle;
	}
	

}

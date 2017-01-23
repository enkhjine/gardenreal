package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerRehabilitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Зовиур
	//Асуумж
	
	@Label(label="chiefComplaint",labelType="m")
	public String chiefComplaint;

	@Label(label="Presentillness",labelType="m")
	public String illness;
	
	@Label(label="History",labelType="m")
	public String history;
	
	@Label(label="Reviewofsystem",labelType="m")
	public String reviewSystem;
	
	//бодит үзлэг
	@Label(label="eruption",labelType="c")
	public String neuroExamination;
	
	@Label(label="eruption",labelType="c")
	public String celebralFuntion;
	
	@Label(label="MentalState",labelType="c",fieldType="boolean")
	public boolean state;
	
	@Label(label="Orientation",labelType="c",fieldType="boolean")
	public boolean orientation;
	
	@Label(label="Memory",labelType="c",fieldType="boolean")
	public boolean memory;
	
	@Label(label="followCommand",labelType="c")
	public String followCommand;

	@Label(label="mMSE",labelType="c")
	public String mMSE;
	
	@Label(label="eruption",labelType="c")
	public String celebrallerFuntion;
	

	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean nystagmus;
	
	@Label(label="nystagmus",labelType="c",fieldType="select",answers={"minuss","pluss"})
	public int nystagmusChoose;
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean tandem;
	
	@Label(label="Tandemgait",labelType="c",fieldType="select",answers={"pluss","minuss"})
	public int tandemChoose;

	@Label(label="intentional",labelType="c",fieldType="select",answers={"minuss","pluss"})
	public int intentionalChoose;
	
	@Label(label="eruption",labelType="c")
	public String rightNoseTes;
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean fingerNose;

	@Label(label="Fingertonosetes",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int fingerNoseChoose;
	
	@Label(label="Heeltoshintes",labelType="c",fieldType="boolean")
	public boolean heel;
	
	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int heelChoose;
	
	@Label(label="Romberg",labelType="c",fieldType="boolean")
	public boolean romberg;

	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int rombergChoose;
	
	@Label(label="Rapidalternativemovement",labelType="c",fieldType="boolean")
	public boolean rapidMovement;
	
	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int rapidMovementChoose;
	
	
	//left
	@Label(label="eruption",labelType="c")
	public String leftNoseTes;
	
	@Label(label="Fingertonosetes",labelType="c",fieldType="boolean")
	public boolean leftFingerNose;

	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int leftFingerNoseChoose;
	
	@Label(label="Heeltoshintes",labelType="c",fieldType="boolean")
	public boolean leftheel;
	
	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int leftheelChoose;
	
	@Label(label="Rapidalternativemovement",labelType="c",fieldType="boolean")
	public boolean leftrapidMovement;

	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int leftrapidMovementChoose;
	
	
	@Label(label="Romberg",labelType="c",fieldType="boolean")
	public boolean leftromberg;
	
	@Label(label="eruption",labelType="c",fieldType="select",answers={"Uncheckable","intact","impaired"})
	public int leftrombergChose;
	
	@Label(label="eruption",labelType="c")
	public String cranialNerve;

	@Label(label="eruption",labelType="c")
	public String rgihtCranialNerve;
	
	@Label(label="CNOlfaction",labelType="c",fieldType="boolean")
	public boolean olfaction;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int olfactionChoose;	
	
	@Label(label="CNVisualacuity",labelType="c",fieldType="boolean")
	public boolean acuity;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int acuityChoose;	
	
	@Label(label="Lightreflex",labelType="c",fieldType="boolean")
	public boolean reflex;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int reflexChoose;
	
	@Label(label="Visualfielddefect",labelType="c",fieldType="boolean")
	public boolean defect;

	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int defectChoose;
	
	
	@Label(label="CNExtraocularmovement",labelType="c",fieldType="boolean")
	public boolean movement;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int movementChoose;
	
	@Label(label="Ptosis",labelType="c",fieldType="boolean")
	public boolean ptosis;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int ptosisChoose;
	
	@Label(label="CNFacialsense",labelType="c",fieldType="boolean")
	public boolean sense;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int senseChoose;
	
	//left
	@Label(label="eruption",labelType="c")
	public String leftCranialNerve;
	
	@Label(label="CNOlfaction",labelType="c",fieldType="boolean")
	public boolean leftolfaction;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int leftolfactionChoose;
	
	
	@Label(label="CNVisualacuity",labelType="c",fieldType="boolean")
	public boolean leftAcuity;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int leftAcuityChoose;
	
	@Label(label="Lightreflex",labelType="c",fieldType="boolean")
	public boolean leftReflex;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int leftReflexChoose;
	
	@Label(label="Visualfielddefect",labelType="c",fieldType="boolean")
	public boolean leftDefect;

	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int leftDefectChoose;	
	
	@Label(label="CNExtraocularmovement",labelType="c",fieldType="boolean")
	public boolean leftMovement;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int leftMovementChoose;
	
	@Label(label="Ptosis",labelType="c",fieldType="boolean")
	public boolean leftPtosis;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int leftPtosisChoose;
	
	@Label(label="CNFacialsense",labelType="c",fieldType="boolean")
	public boolean leftSense;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int leftSenseChoose;
	
	@Label(label="CN 7 Facial motor",labelType="c",fieldType="boolean")
	public boolean motor;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int motorChoose;
	
	@Label(label="CNhearing",labelType="c",fieldType="boolean")
	public boolean hearing;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int hearingChoose;
	
	@Label(label="CNGagreflex",labelType="c",fieldType="boolean")
	public boolean gagReflex;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int gagReflexChoose;
	
	@Label(label="CNTonguedeviation",labelType="c",fieldType="boolean")
	public boolean deviation;

	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int deviationChoose;
	
	//right
	@Label(label="CNFacialmotor",labelType="c",fieldType="boolean")
	public boolean rMotor;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int rMotorChoose;
	
	@Label(label="CNhearing",labelType="c",fieldType="boolean")
	public boolean rHearing;

	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int rHearingChoose;
	
	@Label(label="CNGagreflex",labelType="c",fieldType="boolean")
	public boolean rGagReflex;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int rGagReflexChoose;
	
	@Label(label="CNTonguedeviation",labelType="c",fieldType="boolean")
	public boolean rDeviation;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"pluss","minuss"})
	public int rDeviationChoose;
	
	
	// motor function
	
	@Label(label="eruption",labelType="c")
	public String motorToggle;
	
	@Label(label="LimitationofROM",labelType="c",fieldType="select",answers={"none","limit"})
	public int rom;
	
	@Label(label="Tone",labelType="c",fieldType="select",answers={"none","generallynormotonus","hypotonus","hypertonus"})
	public int tone;
	
	@Label(label="MAS",labelType="c",fieldType="select",answers={"none","generallynormotonus","hypotonus","hypertonus"})
	public int mas;
	
	//motor power
	@Label(label="eruption",labelType="c")
	public String rightPower;
	//rightPower
	@Label(label="power",labelType="c",fieldType="boolean")
	public boolean power;
	
	@Label(label="extremities",labelType="c",fieldType="boolean")
	public boolean extremities;

	@Label(label="proximal",labelType="c",fieldType="boolean")
	public boolean proximal;
	
	@Label(label="RT",labelType="c",fieldType="boolean")
	public boolean rt;	

	@Label(label="proximal",labelType="c",fieldType="select",answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int rtChooseValue;
	
	@Label(label="LT",labelType="c",fieldType="boolean")
	public boolean left;	

	@Label(label="",labelType="c",fieldType="select",answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftChooseValue;
	
	@Label(label="lowerextremities",labelType="c",fieldType="boolean")
	public boolean lowerExtremities;

	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean sensory;

	@Label(label="Pinprick",labelType="c",fieldType="select" ,answers={"none","intact","impaired","uncheck able "})
	public int sensoryChoose;
	
	@Label(label="uEx",labelType="c",fieldType="select" ,answers={"U/Ex","L/Ex"})
	public int uRightSensoryChoose;

	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean dtr;
	
	@Label(label="DTR",labelType="c",fieldType="select" ,answers={"Bj","Tj","Kj","Aj"})
	public int dtrChoose;
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean pathologic;
	
	@Label(label="pathologic",labelType="c",fieldType="select" ,answers={"Ankleclonus","Babinskissign","Hoffmanssigh"})
	public int pathologicChoose;
	
	//leftMotorPower
	@Label(label="eruption",labelType="c")
	public String leftMotorpowerL;
	
	@Label(label="extremities",labelType="c",fieldType="boolean")
	public boolean leftExtremities;
	
	@Label(label="distal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int distalChoose;
	
	@Label(label="proximal",labelType="c",fieldType="boolean")
	public boolean leftProximal;
	
	@Label(label="proximal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftProximalChoose;
	
	@Label(label="distal",labelType="c",fieldType="boolean")
	public boolean leftDistal;
	
	@Label(label="distal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftDistalChoose;
	
	@Label(label="Pinprick",labelType="c",fieldType="select" ,answers={"none","intact","impaired","Uncheckable "})
	public int leftPinprick;
	
	@Label(label="uEx",labelType="c",fieldType="select" ,answers={"none","intact","impaired","Uncheckable "})
	public int uExPinprick;
	
	@Label(label="Sensoryfunction",labelType="c",fieldType="boolean")
	public boolean leftsensory;
	
	@Label(label="DTR",labelType="c",fieldType="boolean")
	public boolean leftDtr;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"Bj","Tj","Kj","Aj"})
	public int leftDtrChoose;
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean leftLeftDistal;
	
	@Label(label="distal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftLeftDistalChoose;
	
	
	@Label(label="proximal",labelType="c",fieldType="boolean")
	public boolean leftLeftProximal;
	
	@Label(label="proximal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftLeftProximalChoose;
	
	
	@Label(label="lowerextremities",labelType="c",fieldType="boolean")
	public boolean leftlowerExtremities;

	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean leftRightdistal;
	
	@Label(label="distal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int leftRightdistalChoose;
	
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean lRightproximal;
	
	@Label(label="proximal",labelType="c",fieldType="select" ,answers={"normalrehab","goodrehab","fairrehab","poorrehab","tracerehab","zerorehab"})
	public int lRightproximalChoose;
	
	@Label(label="eruption",labelType="c",fieldType="boolean")
	public boolean leftPathologic;
	
	@Label(label="Pathologicreflex",labelType="c",fieldType="select" ,answers={"Ankleclonus ","Babinskissign","Hoffmanssigh"})
	public int leftPathologicChoose;
	
	
	
	
	@Label(label="power",labelType="c",fieldType="boolean")
	public boolean leftPower;
	
	@Label(label="swallowing",labelType="c",fieldType="boolean")
	public boolean swallowing;
	
	@Label(label="communication",labelType="c",fieldType="boolean")
	public boolean communication;
	
	@Label(label="times",labelType="c",fieldType="boolean")
	public boolean time;
	
	@Label(label="times",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int timeChoose;
	
	@Label(label="person",labelType="c",fieldType="boolean")
	public boolean person;
	
	@Label(label="person",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int personChoose;
	
	@Label(label="place",labelType="c",fieldType="boolean")
	public boolean place;
	
	@Label(label="place",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int placeChoose;
	
	@Label(label="remote",labelType="c",fieldType="boolean")
	public boolean remote;
	
	@Label(label="remote",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int remoteChoose;

	@Label(label="recent",labelType="c",fieldType="boolean")
	public boolean recent;
	
	@Label(label="recent",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int recentChoose;
	
	@Label(label="immediate",labelType="c",fieldType="boolean")
	public boolean immediate;
	
	@Label(label="immediate",labelType="c",fieldType="select" ,answers={"intact","impaired"})
	public int immediateChoose;
	
	
	@Label(label="protrusion",labelType="c",fieldType="boolean")
	public boolean protrusion;
	
	@Label(label="protrusion",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int protrusionChoose;
	
	@Label(label="closure",labelType="c",fieldType="boolean")
	public boolean closure;
	
	@Label(label="closure",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int closureChoose;
	
	@Label(label="protrusion",labelType="c",fieldType="boolean")
	public boolean tProtrusion;
	
	@Label(label="protrusion",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int tProtrusionChoose;
	
	@Label(label="lateralization",labelType="c",fieldType="boolean")
	public boolean lateralization;
	
	@Label(label="lateralization",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int lateralizationChoose;
	
	@Label(label="elevation",labelType="c",fieldType="boolean")
	public boolean elevation;
	
	@Label(label="elevation",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int elevationChoose;
	
	@Label(label="excursion",labelType="c",fieldType="boolean")
	public boolean excursion;
	
	@Label(label="excursion",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int excursionChoose;
	
	@Label(label="promptness",labelType="c",fieldType="boolean")
	public boolean promptness;
	
	@Label(label="promptness",labelType="c",fieldType="select" ,answers={"none","intact","impaired","closure"})
	public int promptnessChoose;
	
	@Label(label="feeding",labelType="c")
	public String feeding;
	
	@Label(label="eruption",labelType="c")
	public String functionExam;
	
	@Label(label="eruption",labelType="c")
	public String ambulation;
	@Label(label="rollingBed",labelType="c",fieldType="boolean")
	public boolean rollingBed;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int rollingBedChoose;
	@Label(label="sitUp",labelType="c",fieldType="boolean")
	public boolean sitUp;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int sitUpChoose;
	
	@Label(label="balanceStatic",labelType="c",fieldType="boolean")
	public boolean balanceStatic;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int balanceStaticChoose;
	
	@Label(label="sittingBalance",labelType="c",fieldType="boolean")
	public boolean sittingBalance;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int sittingBalanceChoose;
	
	@Label(label="sitStand",labelType="c",fieldType="boolean")
	public boolean sitStand;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int sitStandChoose;
	
	@Label(label="standing",labelType="c",fieldType="boolean")
	public boolean standing;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int standingChoose;
	
	@Label(label="gait",labelType="c",fieldType="boolean")
	public boolean gait;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int gaitChoose;
	
	@Label(label="eruption",labelType="c")
	public String  adlToggle;
	
	@Label(label="eating",labelType="c",fieldType="boolean")
	public boolean eating;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int eatingChoose;
	
	@Label(label="grooming",labelType="c",fieldType="boolean")
	public boolean grooming;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int groomingChoose;
	
	@Label(label="dressing",labelType="c",fieldType="boolean")
	public boolean dressing;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int dressingChoose;
	
	@Label(label="toileting",labelType="c",fieldType="boolean")
	public boolean toileting;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int toiletingChoose;
	
	@Label(label="bathing",labelType="c",fieldType="boolean")
	public boolean bathing;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int bathingChoose;
	
	@Label(label="transfer",labelType="c",fieldType="boolean")
	public boolean transfer;
	
	@Label(label="eruption",labelType="c",fieldType="select" ,answers={"independent","minimalassist","moderateassist","maximalassist"})
	public int transferChoose;


	@Label(label="intentional",labelType="c",fieldType="boolean")
	public boolean intentional;
	
	@Label(label="intentional",labelType="c",fieldType="select" ,answers={"Alert","Confusion","Drowsy","Stupor","Semicoma","Coma"})
	public int stateChoose;
	
	@Label(label="distal",labelType="c",fieldType="boolean")
	public boolean distal;
	
	@Label(label="eruption",labelType="c")
	public String  bowelToggle;
	
	@Label(label="bowelIncontinent",labelType="c",fieldType="boolean")
	public boolean bowelIncontinent;
	
	@Label(label="eruption",labelType="c")
	public String  bladdderToggle;
	
	@Label(label="defMethod",labelType="c")
	public String  defMethod;
	
	@Label(label="volContraction",labelType="c")
	public String  volContraction;
	
	
	@Label(label="passageSense",labelType="c")
	public String  passageSense;
	
	@Label(label="analTone",labelType="c")
	public String  analTone;

	@Label(label="eruption",labelType="c")
	public String  bladderToggle;
	
	@Label(label="bladderIncontinent",labelType="c",fieldType="boolean")
	public boolean bladderIncontinent;
	
	@Label(label="bladderVoidMethod",labelType="c")
	public String  bladderVoidMethod;
	
	@Label(label="bladderPaassage",labelType="c")
	public String  bladderPaassage;
	
	@Label(label="bladderFullSense",labelType="c")
	public String  bladderFullSense;
	
	@Label(label="bladderVoid",labelType="c")
	public String  bladderVoid;
	
 // ROM (Range of motion)
	@Label(label="eruption",labelType="c")
	public String  romRangeToggle;
	
	@Label(label="eruption",labelType="c")
	public String  upperExToggle;
		
	@Label(label="eruption",labelType="c")
	public String  upperExRightToggle;
	//right
	@Label(label="rshoulder",labelType="c",fieldType="boolean")
	public boolean shoulder;
	
	@Label(label="flexion",labelType="c")
	public String  flexion;
	
	@Label(label="extension",labelType="c")
	public String  extension;

	@Label(label="abduction",labelType="c")
	public String  abduction;
	
	@Label(label="adduction",labelType="c")
	public String  adduction;
	
	@Label(label="internalRotation",labelType="c")
	public String  internalRotation;
	
	@Label(label="externalRotation",labelType="c")
	public String  externalRotation;	
	
	@Label(label="relbow",labelType="c",fieldType="boolean")
	public boolean elbow;
	
	@Label(label="elbowFlexion",labelType="c")
	public String  elbowFlexion;
	
	@Label(label="elbowExtension",labelType="c")
	public String  elbowExtension;
	
	
	@Label(label="rforearm",labelType="c",fieldType="boolean")
	public boolean forearm;
	
	@Label(label="foreamPronation",labelType="c")
	public String  foreamPronation;
	
	@Label(label="foreamSupination",labelType="c")
	public String  foreamSupination;	
	
	@Label(label="rwrist",labelType="c",fieldType="boolean")
	public boolean wrist;

	@Label(label="wristFlexion",labelType="c")
	public String  wristFlexion;
	
	@Label(label="wristExtension",labelType="c")
	public String  wristExtension;

	@Label(label="wristFlexion",labelType="c")
	public String  wristUlna;
	
	@Label(label="wristExtension",labelType="c")
	public String  wristRadial;
	
	@Label(label="rhand",labelType="c",fieldType="boolean")
	public boolean hand;
	
	@Label(label="handMPJ",labelType="c")
	public String  handMPJ;
	
	@Label(label="handPip",labelType="c")
	public String  handPip;

	@Label(label="handFi",labelType="c")
	public String  handFi;
	//left
	@Label(label="eruption",labelType="c")
	public String  upperExleftToggle;
	
	@Label(label="rshoulder",labelType="c",fieldType="boolean")
	public boolean leftshoulder;
	
	@Label(label="flexion",labelType="c")
	public String  leftFlexion;

	@Label(label="extension",labelType="c")
	public String  leftExtension;
	
	@Label(label="abduction",labelType="c")
	public String  leftAbduction;
	
	@Label(label="adduction",labelType="c")
	public String  leftAdductions;
	
	@Label(label="internalRotation",labelType="c")
	public String  leftInsternal;

	@Label(label="externalRotation",labelType="c")
	public String  leftExternalRota;
	
	@Label(label="Elbow",labelType="c",fieldType="boolean")
	public boolean leftElbow;
	
	@Label(label="elbowFlexion",labelType="c")
	public String  leftelbowFlexion;

	@Label(label="elbowExtension",labelType="c")
	public String  leftElbowExtension;
	
	@Label(label="rforearm",labelType="c",fieldType="boolean")
	public boolean leftforearm;
	
	@Label(label="foreamPronation",labelType="c")
	public String  leftforeamPronation;

	@Label(label="foreamSupination",labelType="c")
	public String  leftforeamSupination;
	
	@Label(label="rwrist",labelType="c",fieldType="boolean")
	public boolean leftwrist;
	
	@Label(label="wristFlexion",labelType="c")
	public String  leftWristFlexion;

	@Label(label="wristExtension",labelType="c")
	public String  leftWristExtension;
	
	@Label(label="wristFlexion",labelType="c")
	public String  leftWristUlna;

	@Label(label="wristExtension",labelType="c")
	public String  leftWristRadial;
	
	@Label(label="rhand",labelType="c",fieldType="boolean")
	public boolean lefthand;
	
	@Label(label="handMPJ",labelType="c")
	public String  leftHandmpj;

	@Label(label="handPip",labelType="c")
	public String  leftHandpip;

	@Label(label="handFi",labelType="c")
	public String  leftHandFl;
	
	//Low extremity
	@Label(label="eruption",labelType="c")
	public String  lowExToggle;
	//right
	@Label(label="eruption",labelType="c")
	public String  lowExrightToggle;
	
	@Label(label="hip",labelType="c",fieldType="boolean")
	public boolean hip;

	@Label(label="hipExtension",labelType="c")
	public String  hipExtension;

	@Label(label="hipFlexin",labelType="c")
	public String  hipFlexin;

	@Label(label="hipUlna",labelType="c")
	public String  hipUlna;

	@Label(label="hipRadial",labelType="c")
	public String  hipRadial;
	
	@Label(label="knee",labelType="c",fieldType="boolean")
	public boolean knee;
	
	@Label(label="KneeExtension",labelType="c")
	public String  kneeExtension;

	@Label(label="kneeFlexion",labelType="c")
	public String  kneeFlexion;
	
	@Label(label="ankle",labelType="c",fieldType="boolean")
	public boolean ankle;
	
	@Label(label="ankleFlexion",labelType="c")
	public String  ankleFlexion;

	@Label(label="ankleExtension",labelType="c")
	public String  ankleExtension;

	
//left 
	@Label(label="eruption",labelType="c")
	public String  lowExLeftToggle;
	
	@Label(label="hip",labelType="c",fieldType="boolean")
	public boolean leftHip;
	
	@Label(label="hipExtension",labelType="c")
	public String  leftHipExtension;

	@Label(label="hipFlexin",labelType="c")
	public String  leftHipFlexion;

	@Label(label="hipUlna",labelType="c")
	public String  leftHipUlna;

	@Label(label="hipRadial",labelType="c")
	public String  leftHipRadial;
	
	@Label(label="knee",labelType="c",fieldType="boolean")
	public boolean leftKnee;
	
	@Label(label="KneeExtension",labelType="c")
	public String  leftKneeFlexion;

	@Label(label="kneeFlexion",labelType="c")
	public String  leftKneeExtension;
	
	@Label(label="ankle",labelType="c",fieldType="boolean")
	public boolean leftAnkle;
	
	@Label(label="ankleFlexion",labelType="c")
	public String  leftAnkleFlexion;

	@Label(label="ankleExtension",labelType="c")
	public String  leftAnkleExtension;
	
	//gait
	@Label(label="eruption",labelType="c")
	public String  gaitToggle;
	
	@Label(label="hemiplegic",labelType="c",fieldType="boolean")
	public boolean hemiplegic;
	
	@Label(label="ataxia",labelType="c",fieldType="boolean")
	public boolean ataxia;
	
	@Label(label="trendelenburg",labelType="c",fieldType="boolean")
	public boolean trendelenburg;
	
	@Label(label="parkinson",labelType="c",fieldType="boolean")
	public boolean parkinson;
	
	@Label(label="steppage",labelType="c",fieldType="boolean")
	public boolean steppage;
	
	@Label(label="skinRateText",labelType="p")
	public String  rateText;
	
	@Label(label="advice",labelType="p")
	public String advice;
	
	@Label(label="otherPain",labelType="p")
	public String otherPain;
	
	public String getChiefComplaint() {
		return chiefComplaint;
	}

	public void setChiefComplaint(String chiefComplaint) {
		this.chiefComplaint = chiefComplaint;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getReviewSystem() {
		return reviewSystem;
	}

	public void setReviewSystem(String reviewSystem) {
		this.reviewSystem = reviewSystem;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isOrientation() {
		return orientation;
	}

	public void setOrientation(boolean orientation) {
		this.orientation = orientation;
	}

	public boolean isMemory() {
		return memory;
	}

	public void setMemory(boolean memory) {
		this.memory = memory;
	}

	public String getFollowCommand() {
		return followCommand;
	}

	public void setFollowCommand(String followCommand) {
		this.followCommand = followCommand;
	}

	public String getmMSE() {
		return mMSE;
	}

	public void setmMSE(String mMSE) {
		this.mMSE = mMSE;
	}

	public boolean isFingerNose() {
		return fingerNose;
	}

	public void setFingerNose(boolean fingerNose) {
		this.fingerNose = fingerNose;
	}

	public boolean isHeel() {
		return heel;
	}

	public void setHeel(boolean heel) {
		this.heel = heel;
	}

	public boolean isRomberg() {
		return romberg;
	}

	public void setRomberg(boolean romberg) {
		this.romberg = romberg;
	}

	public boolean isTandem() {
		return tandem;
	}

	public void setTandem(boolean tandem) {
		this.tandem = tandem;
	}

	public boolean isRapidMovement() {
		return rapidMovement;
	}

	public void setRapidMovement(boolean rapidMovement) {
		this.rapidMovement = rapidMovement;
	}

	public boolean isOlfaction() {
		return olfaction;
	}

	public void setOlfaction(boolean olfaction) {
		this.olfaction = olfaction;
	}

	public boolean isAcuity() {
		return acuity;
	}

	public void setAcuity(boolean acuity) {
		this.acuity = acuity;
	}

	public boolean isReflex() {
		return reflex;
	}

	public void setReflex(boolean reflex) {
		this.reflex = reflex;
	}

	public boolean isDefect() {
		return defect;
	}

	public void setDefect(boolean defect) {
		this.defect = defect;
	}

	public boolean isMovement() {
		return movement;
	}

	public void setMovement(boolean movement) {
		this.movement = movement;
	}

	public boolean isPtosis() {
		return ptosis;
	}

	public void setPtosis(boolean ptosis) {
		this.ptosis = ptosis;
	}

	public boolean isSense() {
		return sense;
	}

	public void setSense(boolean sense) {
		this.sense = sense;
	}

	public boolean isLeftolfaction() {
		return leftolfaction;
	}

	public void setLeftolfaction(boolean leftolfaction) {
		this.leftolfaction = leftolfaction;
	}

	public boolean isLeftAcuity() {
		return leftAcuity;
	}

	public void setLeftAcuity(boolean leftAcuity) {
		this.leftAcuity = leftAcuity;
	}

	public boolean isLeftReflex() {
		return leftReflex;
	}

	public void setLeftReflex(boolean leftReflex) {
		this.leftReflex = leftReflex;
	}

	public boolean isLeftDefect() {
		return leftDefect;
	}

	public void setLeftDefect(boolean leftDefect) {
		this.leftDefect = leftDefect;
	}

	public boolean isLeftMovement() {
		return leftMovement;
	}

	public void setLeftMovement(boolean leftMovement) {
		this.leftMovement = leftMovement;
	}

	public boolean isLeftPtosis() {
		return leftPtosis;
	}

	public void setLeftPtosis(boolean leftPtosis) {
		this.leftPtosis = leftPtosis;
	}

	public boolean isLeftSense() {
		return leftSense;
	}

	public void setLeftSense(boolean leftSense) {
		this.leftSense = leftSense;
	}

	public boolean isMotor() {
		return motor;
	}

	public void setMotor(boolean motor) {
		this.motor = motor;
	}

	public boolean isHearing() {
		return hearing;
	}

	public void setHearing(boolean hearing) {
		this.hearing = hearing;
	}

	public boolean isGagReflex() {
		return gagReflex;
	}

	public void setGagReflex(boolean gagReflex) {
		this.gagReflex = gagReflex;
	}

	public boolean isDeviation() {
		return deviation;
	}

	public void setDeviation(boolean deviation) {
		this.deviation = deviation;
	}

	public boolean isrMotor() {
		return rMotor;
	}

	public void setrMotor(boolean rMotor) {
		this.rMotor = rMotor;
	}

	public boolean isrHearing() {
		return rHearing;
	}

	public void setrHearing(boolean rHearing) {
		this.rHearing = rHearing;
	}

	public boolean isrGagReflex() {
		return rGagReflex;
	}

	public void setrGagReflex(boolean rGagReflex) {
		this.rGagReflex = rGagReflex;
	}

	public boolean isrDeviation() {
		return rDeviation;
	}

	public void setrDeviation(boolean rDeviation) {
		this.rDeviation = rDeviation;
	}

	public int getRom() {
		return rom;
	}

	public void setRom(int rom) {
		this.rom = rom;
	}

	public int getTone() {
		return tone;
	}

	public void setTone(int tone) {
		this.tone = tone;
	}

	public int getMas() {
		return mas;
	}

	public void setMas(int mas) {
		this.mas = mas;
	}

	public boolean isPower() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	public boolean isExtremities() {
		return extremities;
	}

	public void setExtremities(boolean extremities) {
		this.extremities = extremities;
	}

	public boolean isProximal() {
		return proximal;
	}

	public void setProximal(boolean proximal) {
		this.proximal = proximal;
	}

	public boolean isRt() {
		return rt;
	}

	public void setRt(boolean rt) {
		this.rt = rt;
	}

	public int getRtChooseValue() {
		return rtChooseValue;
	}

	public void setRtChooseValue(int rtChooseValue) {
		this.rtChooseValue = rtChooseValue;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public int getLeftChooseValue() {
		return leftChooseValue;
	}

	public void setLeftChooseValue(int leftChooseValue) {
		this.leftChooseValue = leftChooseValue;
	}

	public boolean isLowerExtremities() {
		return lowerExtremities;
	}

	public void setLowerExtremities(boolean lowerExtremities) {
		this.lowerExtremities = lowerExtremities;
	}

	public boolean isSensory() {
		return sensory;
	}

	public void setSensory(boolean sensory) {
		this.sensory = sensory;
	}

	public boolean isDtr() {
		return dtr;
	}

	public void setDtr(boolean dtr) {
		this.dtr = dtr;
	}

	public boolean isSwallowing() {
		return swallowing;
	}

	public void setSwallowing(boolean swallowing) {
		this.swallowing = swallowing;
	}

	public boolean isPathologic() {
		return pathologic;
	}

	public void setPathologic(boolean pathologic) {
		this.pathologic = pathologic;
	}

	public boolean isCommunication() {
		return communication;
	}

	public void setCommunication(boolean communication) {
		this.communication = communication;
	}

	public boolean isBowelIncontinent() {
		return bowelIncontinent;
	}

	public void setBowelIncontinent(boolean bowelIncontinent) {
		this.bowelIncontinent = bowelIncontinent;
	}

	public boolean isBladderIncontinent() {
		return bladderIncontinent;
	}

	public void setBladderIncontinent(boolean bladderIncontinent) {
		this.bladderIncontinent = bladderIncontinent;
	}

	public boolean isTime() {
		return time;
	}

	public void setTime(boolean time) {
		this.time = time;
	}

	public int getTimeChoose() {
		return timeChoose;
	}

	public void setTimeChoose(int timeChoose) {
		this.timeChoose = timeChoose;
	}

	public boolean isPerson() {
		return person;
	}

	public void setPerson(boolean person) {
		this.person = person;
	}

	public int getPersonChoose() {
		return personChoose;
	}

	public void setPersonChoose(int personChoose) {
		this.personChoose = personChoose;
	}

	public boolean isPlace() {
		return place;
	}

	public void setPlace(boolean place) {
		this.place = place;
	}

	public int getPlaceChoose() {
		return placeChoose;
	}

	public void setPlaceChoose(int placeChoose) {
		this.placeChoose = placeChoose;
	}

	public boolean isRemote() {
		return remote;
	}

	public void setRemote(boolean remote) {
		this.remote = remote;
	}

	public int getRecentChoose() {
		return recentChoose;
	}

	public void setRecentChoose(int recentChoose) {
		this.recentChoose = recentChoose;
	}

	public boolean isImmediate() {
		return immediate;
	}

	public void setImmediate(boolean immediate) {
		this.immediate = immediate;
	}

	public int getImmediateChoose() {
		return immediateChoose;
	}

	public void setImmediateChoose(int immediateChoose) {
		this.immediateChoose = immediateChoose;
	}

	public int getRemoteChoose() {
		return remoteChoose;
	}

	public void setRemoteChoose(int remoteChoose) {
		this.remoteChoose = remoteChoose;
	}

	public boolean isRecent() {
		return recent;
	}

	public void setRecent(boolean recent) {
		this.recent = recent;
	}

	public String getFeeding() {
		return feeding;
	}

	public void setFeeding(String feeding) {
		this.feeding = feeding;
	}

	public boolean isProtrusion() {
		return protrusion;
	}

	public void setProtrusion(boolean protrusion) {
		this.protrusion = protrusion;
	}

	public int getProtrusionChoose() {
		return protrusionChoose;
	}

	public void setProtrusionChoose(int protrusionChoose) {
		this.protrusionChoose = protrusionChoose;
	}

	public boolean isClosure() {
		return closure;
	}

	public void setClosure(boolean closure) {
		this.closure = closure;
	}

	public int getClosureChoose() {
		return closureChoose;
	}

	public void setClosureChoose(int closureChoose) {
		this.closureChoose = closureChoose;
	}

	public boolean istProtrusion() {
		return tProtrusion;
	}

	public void settProtrusion(boolean tProtrusion) {
		this.tProtrusion = tProtrusion;
	}

	public int gettProtrusionChoose() {
		return tProtrusionChoose;
	}

	public void settProtrusionChoose(int tProtrusionChoose) {
		this.tProtrusionChoose = tProtrusionChoose;
	}

	public boolean isLateralization() {
		return lateralization;
	}

	public void setLateralization(boolean lateralization) {
		this.lateralization = lateralization;
	}

	public int getLateralizationChoose() {
		return lateralizationChoose;
	}

	public void setLateralizationChoose(int lateralizationChoose) {
		this.lateralizationChoose = lateralizationChoose;
	}

	public boolean isElevation() {
		return elevation;
	}

	public void setElevation(boolean elevation) {
		this.elevation = elevation;
	}

	public int getElevationChoose() {
		return elevationChoose;
	}

	public void setElevationChoose(int elevationChoose) {
		this.elevationChoose = elevationChoose;
	}

	public boolean isExcursion() {
		return excursion;
	}

	public void setExcursion(boolean excursion) {
		this.excursion = excursion;
	}

	public int getExcursionChoose() {
		return excursionChoose;
	}

	public void setExcursionChoose(int excursionChoose) {
		this.excursionChoose = excursionChoose;
	}

	public boolean isPromptness() {
		return promptness;
	}

	public void setPromptness(boolean promptness) {
		this.promptness = promptness;
	}

	public int getPromptnessChoose() {
		return promptnessChoose;
	}

	public void setPromptnessChoose(int promptnessChoose) {
		this.promptnessChoose = promptnessChoose;
	}

	public boolean isRollingBed() {
		return rollingBed;
	}

	public void setRollingBed(boolean rollingBed) {
		this.rollingBed = rollingBed;
	}

	public int getRollingBedChoose() {
		return rollingBedChoose;
	}

	public void setRollingBedChoose(int rollingBedChoose) {
		this.rollingBedChoose = rollingBedChoose;
	}

	public boolean isSitUp() {
		return sitUp;
	}

	public void setSitUp(boolean sitUp) {
		this.sitUp = sitUp;
	}

	public int getSitUpChoose() {
		return sitUpChoose;
	}

	public void setSitUpChoose(int sitUpChoose) {
		this.sitUpChoose = sitUpChoose;
	}

	public boolean isBalanceStatic() {
		return balanceStatic;
	}

	public void setBalanceStatic(boolean balanceStatic) {
		this.balanceStatic = balanceStatic;
	}

	public int getBalanceStaticChoose() {
		return balanceStaticChoose;
	}

	public void setBalanceStaticChoose(int balanceStaticChoose) {
		this.balanceStaticChoose = balanceStaticChoose;
	}

	public boolean isSittingBalance() {
		return sittingBalance;
	}

	public void setSittingBalance(boolean sittingBalance) {
		this.sittingBalance = sittingBalance;
	}

	public int getSittingBalanceChoose() {
		return sittingBalanceChoose;
	}

	public void setSittingBalanceChoose(int sittingBalanceChoose) {
		this.sittingBalanceChoose = sittingBalanceChoose;
	}

	public boolean isSitStand() {
		return sitStand;
	}

	public void setSitStand(boolean sitStand) {
		this.sitStand = sitStand;
	}

	public int getSitStandChoose() {
		return sitStandChoose;
	}

	public void setSitStandChoose(int sitStandChoose) {
		this.sitStandChoose = sitStandChoose;
	}

	public boolean isStanding() {
		return standing;
	}

	public void setStanding(boolean standing) {
		this.standing = standing;
	}

	public int getStandingChoose() {
		return standingChoose;
	}

	public void setStandingChoose(int standingChoose) {
		this.standingChoose = standingChoose;
	}

	public boolean isGait() {
		return gait;
	}

	public void setGait(boolean gait) {
		this.gait = gait;
	}

	public int getGaitChoose() {
		return gaitChoose;
	}

	public void setGaitChoose(int gaitChoose) {
		this.gaitChoose = gaitChoose;
	}

	public boolean isEating() {
		return eating;
	}

	public void setEating(boolean eating) {
		this.eating = eating;
	}

	public int getEatingChoose() {
		return eatingChoose;
	}

	public void setEatingChoose(int eatingChoose) {
		this.eatingChoose = eatingChoose;
	}

	public boolean isGrooming() {
		return grooming;
	}

	public void setGrooming(boolean grooming) {
		this.grooming = grooming;
	}

	public int getGroomingChoose() {
		return groomingChoose;
	}

	public void setGroomingChoose(int groomingChoose) {
		this.groomingChoose = groomingChoose;
	}

	public boolean isDressing() {
		return dressing;
	}

	public void setDressing(boolean dressing) {
		this.dressing = dressing;
	}

	public int getDressingChoose() {
		return dressingChoose;
	}

	public void setDressingChoose(int dressingChoose) {
		this.dressingChoose = dressingChoose;
	}

	public boolean isToileting() {
		return toileting;
	}

	public void setToileting(boolean toileting) {
		this.toileting = toileting;
	}

	public int getToiletingChoose() {
		return toiletingChoose;
	}

	public void setToiletingChoose(int toiletingChoose) {
		this.toiletingChoose = toiletingChoose;
	}

	public boolean isBathing() {
		return bathing;
	}

	public void setBathing(boolean bathing) {
		this.bathing = bathing;
	}

	public int getBathingChoose() {
		return bathingChoose;
	}

	public void setBathingChoose(int bathingChoose) {
		this.bathingChoose = bathingChoose;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}

	public int getTransferChoose() {
		return transferChoose;
	}

	public void setTransferChoose(int transferChoose) {
		this.transferChoose = transferChoose;
	}

	public boolean isNystagmus() {
		return nystagmus;
	}

	public void setNystagmus(boolean nystagmus) {
		this.nystagmus = nystagmus;
	}

	public boolean isIntentional() {
		return intentional;
	}

	public void setIntentional(boolean intentional) {
		this.intentional = intentional;
	}

	public int getStateChoose() {
		return stateChoose;
	}

	public void setStateChoose(int stateChoose) {
		this.stateChoose = stateChoose;
	}

	public int getFingerNoseChoose() {
		return fingerNoseChoose;
	}

	public void setFingerNoseChoose(int fingerNoseChoose) {
		this.fingerNoseChoose = fingerNoseChoose;
	}

	public int getHeelChoose() {
		return heelChoose;
	}

	public void setHeelChoose(int heelChoose) {
		this.heelChoose = heelChoose;
	}

	public int getRombergChoose() {
		return rombergChoose;
	}

	public void setRombergChoose(int rombergChoose) {
		this.rombergChoose = rombergChoose;
	}

	public int getTandemChoose() {
		return tandemChoose;
	}

	public void setTandemChoose(int tandemChoose) {
		this.tandemChoose = tandemChoose;
	}

	public int getRapidMovementChoose() {
		return rapidMovementChoose;
	}

	public void setRapidMovementChoose(int rapidMovementChoose) {
		this.rapidMovementChoose = rapidMovementChoose;
	}

	public boolean isLeftFingerNose() {
		return leftFingerNose;
	}

	public void setLeftFingerNose(boolean leftFingerNose) {
		this.leftFingerNose = leftFingerNose;
	}

	public int getLeftFingerNoseChoose() {
		return leftFingerNoseChoose;
	}

	public void setLeftFingerNoseChoose(int leftFingerNoseChoose) {
		this.leftFingerNoseChoose = leftFingerNoseChoose;
	}

	public boolean isLeftheel() {
		return leftheel;
	}

	public void setLeftheel(boolean leftheel) {
		this.leftheel = leftheel;
	}

	public int getLeftheelChoose() {
		return leftheelChoose;
	}

	public void setLeftheelChoose(int leftheelChoose) {
		this.leftheelChoose = leftheelChoose;
	}

	public boolean isLeftrapidMovement() {
		return leftrapidMovement;
	}

	public void setLeftrapidMovement(boolean leftrapidMovement) {
		this.leftrapidMovement = leftrapidMovement;
	}

	public int getLeftrapidMovementChoose() {
		return leftrapidMovementChoose;
	}

	public void setLeftrapidMovementChoose(int leftrapidMovementChoose) {
		this.leftrapidMovementChoose = leftrapidMovementChoose;
	}

	public boolean isLeftromberg() {
		return leftromberg;
	}

	public void setLeftromberg(boolean leftromberg) {
		this.leftromberg = leftromberg;
	}

	public int getLeftrombergChose() {
		return leftrombergChose;
	}

	public void setLeftrombergChose(int leftrombergChose) {
		this.leftrombergChose = leftrombergChose;
	}

	public int getNystagmusChoose() {
		return nystagmusChoose;
	}

	public void setNystagmusChoose(int nystagmusChoose) {
		this.nystagmusChoose = nystagmusChoose;
	}

	public int getIntentionalChoose() {
		return intentionalChoose;
	}

	public void setIntentionalChoose(int intentionalChoose) {
		this.intentionalChoose = intentionalChoose;
	}

	public boolean isDistal() {
		return distal;
	}

	public void setDistal(boolean distal) {
		this.distal = distal;
	}

	public int getDistalChoose() {
		return distalChoose;
	}

	public void setDistalChoose(int distalChoose) {
		this.distalChoose = distalChoose;
	}

	public boolean isLeftProximal() {
		return leftProximal;
	}

	public void setLeftProximal(boolean leftProximal) {
		this.leftProximal = leftProximal;
	}

	public int getLeftProximalChoose() {
		return leftProximalChoose;
	}

	public void setLeftProximalChoose(int leftProximalChoose) {
		this.leftProximalChoose = leftProximalChoose;
	}

	public boolean isLeftDistal() {
		return leftDistal;
	}

	public void setLeftDistal(boolean leftDistal) {
		this.leftDistal = leftDistal;
	}

	public int getLeftDistalChoose() {
		return leftDistalChoose;
	}

	public void setLeftDistalChoose(int leftDistalChoose) {
		this.leftDistalChoose = leftDistalChoose;
	}

	public int getLeftPinprick() {
		return leftPinprick;
	}

	public void setLeftPinprick(int leftPinprick) {
		this.leftPinprick = leftPinprick;
	}

	public int getuExPinprick() {
		return uExPinprick;
	}

	public void setuExPinprick(int uExPinprick) {
		this.uExPinprick = uExPinprick;
	}

	public boolean isLeftsensory() {
		return leftsensory;
	}

	public void setLeftsensory(boolean leftsensory) {
		this.leftsensory = leftsensory;
	}

	public boolean isLeftDtr() {
		return leftDtr;
	}

	public void setLeftDtr(boolean leftDtr) {
		this.leftDtr = leftDtr;
	}

	public int getLeftDtrChoose() {
		return leftDtrChoose;
	}

	public void setLeftDtrChoose(int leftDtrChoose) {
		this.leftDtrChoose = leftDtrChoose;
	}

	public boolean isLeftLeftDistal() {
		return leftLeftDistal;
	}

	public void setLeftLeftDistal(boolean leftLeftDistal) {
		this.leftLeftDistal = leftLeftDistal;
	}

	public int getLeftLeftDistalChoose() {
		return leftLeftDistalChoose;
	}

	public void setLeftLeftDistalChoose(int leftLeftDistalChoose) {
		this.leftLeftDistalChoose = leftLeftDistalChoose;
	}

	public boolean isLeftLeftProximal() {
		return leftLeftProximal;
	}

	public void setLeftLeftProximal(boolean leftLeftProximal) {
		this.leftLeftProximal = leftLeftProximal;
	}

	public int getLeftLeftProximalChoose() {
		return leftLeftProximalChoose;
	}

	public void setLeftLeftProximalChoose(int leftLeftProximalChoose) {
		this.leftLeftProximalChoose = leftLeftProximalChoose;
	}

	public boolean isLeftlowerExtremities() {
		return leftlowerExtremities;
	}

	public void setLeftlowerExtremities(boolean leftlowerExtremities) {
		this.leftlowerExtremities = leftlowerExtremities;
	}

	public boolean isLeftRightdistal() {
		return leftRightdistal;
	}

	public void setLeftRightdistal(boolean leftRightdistal) {
		this.leftRightdistal = leftRightdistal;
	}

	public int getLeftRightdistalChoose() {
		return leftRightdistalChoose;
	}

	public void setLeftRightdistalChoose(int leftRightdistalChoose) {
		this.leftRightdistalChoose = leftRightdistalChoose;
	}

	public boolean islRightproximal() {
		return lRightproximal;
	}

	public void setlRightproximal(boolean lRightproximal) {
		this.lRightproximal = lRightproximal;
	}

	public int getlRightproximalChoose() {
		return lRightproximalChoose;
	}

	public void setlRightproximalChoose(int lRightproximalChoose) {
		this.lRightproximalChoose = lRightproximalChoose;
	}

	public boolean isLeftExtremities() {
		return leftExtremities;
	}

	public void setLeftExtremities(boolean leftExtremities) {
		this.leftExtremities = leftExtremities;
	}

	public boolean isLeftPower() {
		return leftPower;
	}

	public void setLeftPower(boolean leftPower) {
		this.leftPower = leftPower;
	}

	public boolean isLeftPathologic() {
		return leftPathologic;
	}

	public void setLeftPathologic(boolean leftPathologic) {
		this.leftPathologic = leftPathologic;
	}

	public int getLeftPathologicChoose() {
		return leftPathologicChoose;
	}

	public void setLeftPathologicChoose(int leftPathologicChoose) {
		this.leftPathologicChoose = leftPathologicChoose;
	}

	public int getPathologicChoose() {
		return pathologicChoose;
	}

	public void setPathologicChoose(int pathologicChoose) {
		this.pathologicChoose = pathologicChoose;
	}

	public int getSensoryChoose() {
		return sensoryChoose;
	}

	public void setSensoryChoose(int sensoryChoose) {
		this.sensoryChoose = sensoryChoose;
	}

	public int getuRightSensoryChoose() {
		return uRightSensoryChoose;
	}

	public void setuRightSensoryChoose(int uRightSensoryChoose) {
		this.uRightSensoryChoose = uRightSensoryChoose;
	}

	public int getDtrChoose() {
		return dtrChoose;
	}

	public void setDtrChoose(int dtrChoose) {
		this.dtrChoose = dtrChoose;
	}

	public int getOlfactionChoose() {
		return olfactionChoose;
	}

	public void setOlfactionChoose(int olfactionChoose) {
		this.olfactionChoose = olfactionChoose;
	}

	public int getAcuityChoose() {
		return acuityChoose;
	}

	public void setAcuityChoose(int acuityChoose) {
		this.acuityChoose = acuityChoose;
	}

	public int getReflexChoose() {
		return reflexChoose;
	}

	public void setReflexChoose(int reflexChoose) {
		this.reflexChoose = reflexChoose;
	}

	public int getDefectChoose() {
		return defectChoose;
	}

	public void setDefectChoose(int defectChoose) {
		this.defectChoose = defectChoose;
	}

	public int getMovementChoose() {
		return movementChoose;
	}

	public void setMovementChoose(int movementChoose) {
		this.movementChoose = movementChoose;
	}

	public int getPtosisChoose() {
		return ptosisChoose;
	}

	public void setPtosisChoose(int ptosisChoose) {
		this.ptosisChoose = ptosisChoose;
	}

	public int getSenseChoose() {
		return senseChoose;
	}

	public void setSenseChoose(int senseChoose) {
		this.senseChoose = senseChoose;
	}

	public int getrMotorChoose() {
		return rMotorChoose;
	}

	public void setrMotorChoose(int rMotorChoose) {
		this.rMotorChoose = rMotorChoose;
	}

	public int getrHearingChoose() {
		return rHearingChoose;
	}

	public void setrHearingChoose(int rHearingChoose) {
		this.rHearingChoose = rHearingChoose;
	}

	public int getrGagReflexChoose() {
		return rGagReflexChoose;
	}

	public void setrGagReflexChoose(int rGagReflexChoose) {
		this.rGagReflexChoose = rGagReflexChoose;
	}

	public int getrDeviationChoose() {
		return rDeviationChoose;
	}

	public void setrDeviationChoose(int rDeviationChoose) {
		this.rDeviationChoose = rDeviationChoose;
	}

	public int getLeftolfactionChoose() {
		return leftolfactionChoose;
	}

	public void setLeftolfactionChoose(int leftolfactionChoose) {
		this.leftolfactionChoose = leftolfactionChoose;
	}

	public int getLeftAcuityChoose() {
		return leftAcuityChoose;
	}

	public void setLeftAcuityChoose(int leftAcuityChoose) {
		this.leftAcuityChoose = leftAcuityChoose;
	}

	public int getLeftReflexChoose() {
		return leftReflexChoose;
	}

	public void setLeftReflexChoose(int leftReflexChoose) {
		this.leftReflexChoose = leftReflexChoose;
	}

	public int getLeftDefectChoose() {
		return leftDefectChoose;
	}

	public void setLeftDefectChoose(int leftDefectChoose) {
		this.leftDefectChoose = leftDefectChoose;
	}

	public int getLeftMovementChoose() {
		return leftMovementChoose;
	}

	public void setLeftMovementChoose(int leftMovementChoose) {
		this.leftMovementChoose = leftMovementChoose;
	}

	public int getLeftPtosisChoose() {
		return leftPtosisChoose;
	}

	public void setLeftPtosisChoose(int leftPtosisChoose) {
		this.leftPtosisChoose = leftPtosisChoose;
	}

	public int getLeftSenseChoose() {
		return leftSenseChoose;
	}

	public void setLeftSenseChoose(int leftSenseChoose) {
		this.leftSenseChoose = leftSenseChoose;
	}

	public int getMotorChoose() {
		return motorChoose;
	}

	public void setMotorChoose(int motorChoose) {
		this.motorChoose = motorChoose;
	}

	public int getHearingChoose() {
		return hearingChoose;
	}

	public void setHearingChoose(int hearingChoose) {
		this.hearingChoose = hearingChoose;
	}

	public int getGagReflexChoose() {
		return gagReflexChoose;
	}

	public void setGagReflexChoose(int gagReflexChoose) {
		this.gagReflexChoose = gagReflexChoose;
	}

	public int getDeviationChoose() {
		return deviationChoose;
	}

	public void setDeviationChoose(int deviationChoose) {
		this.deviationChoose = deviationChoose;
	}

	public String getNeuroExamination() {
		return neuroExamination;
	}

	public void setNeuroExamination(String neuroExamination) {
		this.neuroExamination = neuroExamination;
	}

	public String getFunctionExam() {
		return functionExam;
	}

	public void setFunctionExam(String functionExam) {
		this.functionExam = functionExam;
	}

	public String getCelebralFuntion() {
		return celebralFuntion;
	}

	public void setCelebralFuntion(String celebralFuntion) {
		this.celebralFuntion = celebralFuntion;
	}

	public String getCelebrallerFuntion() {
		return celebrallerFuntion;
	}

	public void setCelebrallerFuntion(String celebrallerFuntion) {
		this.celebrallerFuntion = celebrallerFuntion;
	}

	public String getLeftNoseTes() {
		return leftNoseTes;
	}

	public void setLeftNoseTes(String leftNoseTes) {
		this.leftNoseTes = leftNoseTes;
	}

	public String getRightNoseTes() {
		return rightNoseTes;
	}

	public void setRightNoseTes(String rightNoseTes) {
		this.rightNoseTes = rightNoseTes;
	}

	public String getCranialNerve() {
		return cranialNerve;
	}

	public void setCranialNerve(String cranialNerve) {
		this.cranialNerve = cranialNerve;
	}

	public String getRgihtCranialNerve() {
		return rgihtCranialNerve;
	}

	public void setRgihtCranialNerve(String rgihtCranialNerve) {
		this.rgihtCranialNerve = rgihtCranialNerve;
	}

	public String getLeftCranialNerve() {
		return leftCranialNerve;
	}

	public void setLeftCranialNerve(String leftCranialNerve) {
		this.leftCranialNerve = leftCranialNerve;
	}

	public String getMotorToggle() {
		return motorToggle;
	}

	public void setMotorToggle(String motorToggle) {
		this.motorToggle = motorToggle;
	}

	public String getRightPower() {
		return rightPower;
	}

	public void setRightPower(String rightPower) {
		this.rightPower = rightPower;
	}

	public String getLeftMotorpowerL() {
		return leftMotorpowerL;
	}

	public void setLeftMotorpowerL(String leftMotorpowerL) {
		this.leftMotorpowerL = leftMotorpowerL;
	}

	public boolean isShoulder() {
		return shoulder;
	}

	public void setShoulder(boolean shoulder) {
		this.shoulder = shoulder;
	}

	public boolean isElbow() {
		return elbow;
	}

	public void setElbow(boolean elbow) {
		this.elbow = elbow;
	}

	public boolean isForearm() {
		return forearm;
	}

	public void setForearm(boolean forearm) {
		this.forearm = forearm;
	}

	public boolean isWrist() {
		return wrist;
	}

	public void setWrist(boolean wrist) {
		this.wrist = wrist;
	}

	public boolean isHand() {
		return hand;
	}

	public void setHand(boolean hand) {
		this.hand = hand;
	}

	public boolean isLeftshoulder() {
		return leftshoulder;
	}

	public void setLeftshoulder(boolean leftshoulder) {
		this.leftshoulder = leftshoulder;
	}

	public boolean isLeftforearm() {
		return leftforearm;
	}

	public void setLeftforearm(boolean leftforearm) {
		this.leftforearm = leftforearm;
	}

	public boolean isLeftwrist() {
		return leftwrist;
	}

	public void setLeftwrist(boolean leftwrist) {
		this.leftwrist = leftwrist;
	}

	public boolean isLefthand() {
		return lefthand;
	}

	public void setLefthand(boolean lefthand) {
		this.lefthand = lefthand;
	}

	public boolean isHip() {
		return hip;
	}

	public void setHip(boolean hip) {
		this.hip = hip;
	}

	public boolean isKnee() {
		return knee;
	}

	public void setKnee(boolean knee) {
		this.knee = knee;
	}

	public boolean isAnkle() {
		return ankle;
	}

	public void setAnkle(boolean ankle) {
		this.ankle = ankle;
	}

	public boolean isLeftHip() {
		return leftHip;
	}

	public void setLeftHip(boolean leftHip) {
		this.leftHip = leftHip;
	}

	public boolean isLeftKnee() {
		return leftKnee;
	}

	public void setLeftKnee(boolean leftKnee) {
		this.leftKnee = leftKnee;
	}

	public boolean isLeftAnkle() {
		return leftAnkle;
	}

	public void setLeftAnkle(boolean leftAnkle) {
		this.leftAnkle = leftAnkle;
	}

	public boolean isHemiplegic() {
		return hemiplegic;
	}

	public void setHemiplegic(boolean hemiplegic) {
		this.hemiplegic = hemiplegic;
	}

	public boolean isAtaxia() {
		return ataxia;
	}

	public void setAtaxia(boolean ataxia) {
		this.ataxia = ataxia;
	}

	public boolean isTrendelenburg() {
		return trendelenburg;
	}

	public void setTrendelenburg(boolean trendelenburg) {
		this.trendelenburg = trendelenburg;
	}

	public boolean isParkinson() {
		return parkinson;
	}

	public void setParkinson(boolean parkinson) {
		this.parkinson = parkinson;
	}

	public boolean isSteppage() {
		return steppage;
	}

	public void setSteppage(boolean steppage) {
		this.steppage = steppage;
	}

	public String getRomRangeToggle() {
		return romRangeToggle;
	}

	public void setRomRangeToggle(String romRangeToggle) {
		this.romRangeToggle = romRangeToggle;
	}

	public String getUpperExToggle() {
		return upperExToggle;
	}

	public void setUpperExToggle(String upperExToggle) {
		this.upperExToggle = upperExToggle;
	}

	public String getUpperExleftToggle() {
		return upperExleftToggle;
	}

	public void setUpperExleftToggle(String upperExleftToggle) {
		this.upperExleftToggle = upperExleftToggle;
	}

	public String getUpperExRightToggle() {
		return upperExRightToggle;
	}

	public void setUpperExRightToggle(String upperExRightToggle) {
		this.upperExRightToggle = upperExRightToggle;
	}

	public String getLowExToggle() {
		return lowExToggle;
	}

	public void setLowExToggle(String lowExToggle) {
		this.lowExToggle = lowExToggle;
	}

	public String getLowExrightToggle() {
		return lowExrightToggle;
	}

	public void setLowExrightToggle(String lowExrightToggle) {
		this.lowExrightToggle = lowExrightToggle;
	}

	public String getLowExLeftToggle() {
		return lowExLeftToggle;
	}

	public void setLowExLeftToggle(String lowExLeftToggle) {
		this.lowExLeftToggle = lowExLeftToggle;
	}

	public String getGaitToggle() {
		return gaitToggle;
	}

	public void setGaitToggle(String gaitToggle) {
		this.gaitToggle = gaitToggle;
	}

	public boolean isLeftElbow() {
		return leftElbow;
	}

	public void setLeftElbow(boolean leftElbow) {
		this.leftElbow = leftElbow;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getAbduction() {
		return abduction;
	}

	public void setAbduction(String abduction) {
		this.abduction = abduction;
	}

	public String getAdduction() {
		return adduction;
	}

	public void setAdduction(String adduction) {
		this.adduction = adduction;
	}

	public String getInternalRotation() {
		return internalRotation;
	}

	public void setInternalRotation(String internalRotation) {
		this.internalRotation = internalRotation;
	}

	public String getExternalRotation() {
		return externalRotation;
	}

	public void setExternalRotation(String externalRotation) {
		this.externalRotation = externalRotation;
	}

	public String getElbowFlexion() {
		return elbowFlexion;
	}

	public void setElbowFlexion(String elbowFlexion) {
		this.elbowFlexion = elbowFlexion;
	}

	public String getElbowExtension() {
		return elbowExtension;
	}

	public void setElbowExtension(String elbowExtension) {
		this.elbowExtension = elbowExtension;
	}

	public String getForeamPronation() {
		return foreamPronation;
	}

	public void setForeamPronation(String foreamPronation) {
		this.foreamPronation = foreamPronation;
	}

	public String getForeamSupination() {
		return foreamSupination;
	}

	public void setForeamSupination(String foreamSupination) {
		this.foreamSupination = foreamSupination;
	}

	public String getWristFlexion() {
		return wristFlexion;
	}

	public void setWristFlexion(String wristFlexion) {
		this.wristFlexion = wristFlexion;
	}

	public String getWristExtension() {
		return wristExtension;
	}

	public void setWristExtension(String wristExtension) {
		this.wristExtension = wristExtension;
	}

	public String getWristUlna() {
		return wristUlna;
	}

	public void setWristUlna(String wristUlna) {
		this.wristUlna = wristUlna;
	}

	public String getWristRadial() {
		return wristRadial;
	}

	public void setWristRadial(String wristRadial) {
		this.wristRadial = wristRadial;
	}

	public String getHandMPJ() {
		return handMPJ;
	}

	public void setHandMPJ(String handMPJ) {
		this.handMPJ = handMPJ;
	}

	public String getHandPip() {
		return handPip;
	}

	public void setHandPip(String handPip) {
		this.handPip = handPip;
	}

	public String getHandFi() {
		return handFi;
	}

	public void setHandFi(String handFi) {
		this.handFi = handFi;
	}

	public String getLeftFlexion() {
		return leftFlexion;
	}

	public void setLeftFlexion(String leftFlexion) {
		this.leftFlexion = leftFlexion;
	}

	public String getLeftExtension() {
		return leftExtension;
	}

	public void setLeftExtension(String leftExtension) {
		this.leftExtension = leftExtension;
	}

	public String getLeftAbduction() {
		return leftAbduction;
	}

	public void setLeftAbduction(String leftAbduction) {
		this.leftAbduction = leftAbduction;
	}

	public String getLeftAdductions() {
		return leftAdductions;
	}

	public void setLeftAdductions(String leftAdductions) {
		this.leftAdductions = leftAdductions;
	}

	public String getLeftInsternal() {
		return leftInsternal;
	}

	public void setLeftInsternal(String leftInsternal) {
		this.leftInsternal = leftInsternal;
	}

	public String getLeftExternalRota() {
		return leftExternalRota;
	}

	public void setLeftExternalRota(String leftExternalRota) {
		this.leftExternalRota = leftExternalRota;
	}

	public String getLeftelbowFlexion() {
		return leftelbowFlexion;
	}

	public void setLeftelbowFlexion(String leftelbowFlexion) {
		this.leftelbowFlexion = leftelbowFlexion;
	}

	public String getLeftElbowExtension() {
		return leftElbowExtension;
	}

	public void setLeftElbowExtension(String leftElbowExtension) {
		this.leftElbowExtension = leftElbowExtension;
	}

	public String getLeftforeamPronation() {
		return leftforeamPronation;
	}

	public void setLeftforeamPronation(String leftforeamPronation) {
		this.leftforeamPronation = leftforeamPronation;
	}

	public String getLeftforeamSupination() {
		return leftforeamSupination;
	}

	public void setLeftforeamSupination(String leftforeamSupination) {
		this.leftforeamSupination = leftforeamSupination;
	}

	public String getLeftWristFlexion() {
		return leftWristFlexion;
	}

	public void setLeftWristFlexion(String leftWristFlexion) {
		this.leftWristFlexion = leftWristFlexion;
	}

	public String getLeftWristExtension() {
		return leftWristExtension;
	}

	public void setLeftWristExtension(String leftWristExtension) {
		this.leftWristExtension = leftWristExtension;
	}

	public String getLeftWristUlna() {
		return leftWristUlna;
	}

	public void setLeftWristUlna(String leftWristUlna) {
		this.leftWristUlna = leftWristUlna;
	}

	public String getLeftWristRadial() {
		return leftWristRadial;
	}

	public void setLeftWristRadial(String leftWristRadial) {
		this.leftWristRadial = leftWristRadial;
	}

	public String getLeftHandmpj() {
		return leftHandmpj;
	}

	public void setLeftHandmpj(String leftHandmpj) {
		this.leftHandmpj = leftHandmpj;
	}

	public String getLeftHandpip() {
		return leftHandpip;
	}

	public void setLeftHandpip(String leftHandpip) {
		this.leftHandpip = leftHandpip;
	}

	public String getLeftHandFl() {
		return leftHandFl;
	}

	public void setLeftHandFl(String leftHandFl) {
		this.leftHandFl = leftHandFl;
	}

	public String getFlexion() {
		return flexion;
	}

	public void setFlexion(String flexion) {
		this.flexion = flexion;
	}

	public String getHipExtension() {
		return hipExtension;
	}

	public void setHipExtension(String hipExtension) {
		this.hipExtension = hipExtension;
	}

	public String getHipFlexin() {
		return hipFlexin;
	}

	public void setHipFlexin(String hipFlexin) {
		this.hipFlexin = hipFlexin;
	}

	public String getHipUlna() {
		return hipUlna;
	}

	public void setHipUlna(String hipUlna) {
		this.hipUlna = hipUlna;
	}

	public String getHipRadial() {
		return hipRadial;
	}

	public void setHipRadial(String hipRadial) {
		this.hipRadial = hipRadial;
	}


	public String getKneeExtension() {
		return kneeExtension;
	}

	public void setKneeExtension(String kneeExtension) {
		this.kneeExtension = kneeExtension;
	}

	public String getKneeFlexion() {
		return kneeFlexion;
	}

	public void setKneeFlexion(String kneeFlexion) {
		this.kneeFlexion = kneeFlexion;
	}

	public String getAnkleFlexion() {
		return ankleFlexion;
	}

	public void setAnkleFlexion(String ankleFlexion) {
		this.ankleFlexion = ankleFlexion;
	}

	public String getAnkleExtension() {
		return ankleExtension;
	}

	public void setAnkleExtension(String ankleExtension) {
		this.ankleExtension = ankleExtension;
	}

	public String getLeftHipExtension() {
		return leftHipExtension;
	}

	public void setLeftHipExtension(String leftHipExtension) {
		this.leftHipExtension = leftHipExtension;
	}

	public String getLeftHipFlexion() {
		return leftHipFlexion;
	}

	public void setLeftHipFlexion(String leftHipFlexion) {
		this.leftHipFlexion = leftHipFlexion;
	}

	public String getLeftHipUlna() {
		return leftHipUlna;
	}

	public void setLeftHipUlna(String leftHipUlna) {
		this.leftHipUlna = leftHipUlna;
	}

	public String getLeftHipRadial() {
		return leftHipRadial;
	}

	public void setLeftHipRadial(String leftHipRadial) {
		this.leftHipRadial = leftHipRadial;
	}

	public String getLeftKneeFlexion() {
		return leftKneeFlexion;
	}

	public void setLeftKneeFlexion(String leftKneeFlexion) {
		this.leftKneeFlexion = leftKneeFlexion;
	}

	public String getLeftKneeExtension() {
		return leftKneeExtension;
	}

	public void setLeftKneeExtension(String leftKneeExtension) {
		this.leftKneeExtension = leftKneeExtension;
	}

	public String getLeftAnkleFlexion() {
		return leftAnkleFlexion;
	}

	public void setLeftAnkleFlexion(String leftAnkleFlexion) {
		this.leftAnkleFlexion = leftAnkleFlexion;
	}

	public String getLeftAnkleExtension() {
		return leftAnkleExtension;
	}

	public void setLeftAnkleExtension(String leftAnkleExtension) {
		this.leftAnkleExtension = leftAnkleExtension;
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

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getAmbulation() {
		return ambulation;
	}

	public void setAmbulation(String ambulation) {
		this.ambulation = ambulation;
	}

	public String getAdlToggle() {
		return adlToggle;
	}

	public void setAdlToggle(String adlToggle) {
		this.adlToggle = adlToggle;
	}

	public String getBowelToggle() {
		return bowelToggle;
	}

	public void setBowelToggle(String bowelToggle) {
		this.bowelToggle = bowelToggle;
	}

	public String getBladdderToggle() {
		return bladdderToggle;
	}

	public void setBladdderToggle(String bladdderToggle) {
		this.bladdderToggle = bladdderToggle;
	}

	public String getDefMethod() {
		return defMethod;
	}

	public void setDefMethod(String defMethod) {
		this.defMethod = defMethod;
	}

	public String getVolContraction() {
		return volContraction;
	}

	public void setVolContraction(String volContraction) {
		this.volContraction = volContraction;
	}

	public String getPassageSense() {
		return passageSense;
	}

	public void setPassageSense(String passageSense) {
		this.passageSense = passageSense;
	}

	public String getAnalTone() {
		return analTone;
	}

	public void setAnalTone(String analTone) {
		this.analTone = analTone;
	}

	public String getBladderToggle() {
		return bladderToggle;
	}

	public void setBladderToggle(String bladderToggle) {
		this.bladderToggle = bladderToggle;
	}

	public String getBladderVoidMethod() {
		return bladderVoidMethod;
	}

	public void setBladderVoidMethod(String bladderVoidMethod) {
		this.bladderVoidMethod = bladderVoidMethod;
	}

	public String getBladderPaassage() {
		return bladderPaassage;
	}

	public void setBladderPaassage(String bladderPaassage) {
		this.bladderPaassage = bladderPaassage;
	}

	public String getBladderFullSense() {
		return bladderFullSense;
	}

	public void setBladderFullSense(String bladderFullSense) {
		this.bladderFullSense = bladderFullSense;
	}

	public String getBladderVoid() {
		return bladderVoid;
	}

	public void setBladderVoid(String bladderVoid) {
		this.bladderVoid = bladderVoid;
	}

	
	
}

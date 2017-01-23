package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerDiabetes {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="diabetes_fatGeneration" , labelType="p" , fieldType="boolean")
	public boolean diabetes_fatGeneration;
	
	@Label(label="diabetes_diabetesGeneration" , labelType="p" , fieldType="boolean")
	public boolean diabetes_diabetesGeneration;
	
	@Label(label="diabetes_weight" , labelType="p" , fieldType="boolean")
	public boolean diabetes_weight;
	
	@Label(label="diabetes_hepatite" , labelType="p" , fieldType="boolean")
	public boolean diabetes_hepatite;
	
	@Label(label="diabetes_pancreatitis" , labelType="p" , fieldType="boolean")
	public boolean diabetes_pancreatitis;
	
	@Label(label="diabetes_pregnantDiabetes" , labelType="p" , fieldType="boolean")
	public boolean diabetes_pregnantDiabetes;
	
	@Label(label="diabetes_child" , labelType="p" , fieldType="boolean")
	public boolean diabetes_child;
	
	@Label(label="diabetes_alcoholic" , labelType="p" , fieldType="boolean")
	public boolean diabetes_alcoholic;
	
	@Label(label="diabetes_cigarettes" , labelType="p" , fieldType="boolean")
	public boolean diabetes_cigarettes;
	
	@Label(label="diabetes_nutrition" , labelType="p" , fieldType="boolean")
	public boolean diabetes_nutrition;
	
	@Label(label="diabetes_nutritionCheck" , labelType="p", fieldType="select" , answers={"good" , "notGood" , "bad"})
	public int diabetes_nutritionCheck;
	
	@Label(label="diabetes_movement" , labelType="p" , fieldType="boolean")
	public boolean diabetes_movement;
	
	@Label(label="diabetes_movementCheck" , labelType="p", fieldType="select" , answers={"good" , "notGood" , "bad"})
	public int diabetes_movementCheck;
	
	@Label(label="diabetesYear" , labelType="p")
	public int diabetesYear;
	
	@Label(label="diabetespharmacy", labelType="p")
	public String diabetespharmacy;
	
	@Label(label="diabetesOtherPainText", labelType="p")
	public String diabetesOtherPainText;
	
	@Label(label="diabetesMedText" , labelType="m")
	public String diabetesMedText;
	
	@Label(label="weight" , labelType="c")
	public String weight;
	
	@Label(label="height" , labelType="c")
	public String height;
	
	@Label(label="fat" , labelType="c")
	public String fat;
	
	@Label(label="waistline" , labelType="c")
	public String waistline;
	
	@Label(label="BMI" , labelType="c")
	public String BMI;
	
	@Label(label="selfPeckishGlucose" , labelType="c")
	public String selfPeckishGlucose;
	
	@Label(label="selfGorgedGlucose" , labelType="c")
	public String selfGorgedGlucose;
	
	@Label(label="doctorHbA1CPercentage" , labelType="c")
	public String doctorHbA1CPercentage;
	
	@Label(label="doctorHbA1C" , labelType="c" , fieldType="select" , answers={"healthy" , "good" , "notGood" , "sickly"})
	public int doctorHbA1C;
	
	@Label(label="anti_GADIA2" , labelType="c")
	public String anti_GADIA2;
	
	@Label(label="anti_GADIA2Text" , labelType="c")
	public String anti_GADIA2Text;
	
	@Label(label="insulin" , labelType="c")
	public String insulin;
	
	@Label(label="insulinText" , labelType="c")
	public String insulinText;
	
	@Label(label="c_peptide" , labelType="c")
	public String c_peptide;
	
	@Label(label="c_peptideText" , labelType="c")
	public String c_peptideText;
	
	@Label(label="doctorPeckishGlucose" , labelType="c")
	public String doctorPeckishGlucose;
	
	@Label(label="doctorGorgedGlucose" , labelType="c")
	public String doctorGorgedGlucose;
	
	@Label(label="cardioCholesterol" , labelType="c")
	public String cardioCholesterol;
	
	@Label(label="cardioBNLP" , labelType="c")
	public String cardioBNLP;
	
	@Label(label="cardioINLP" , labelType="c")
	public String cardioINLP;
	
	@Label(label="cardioTriglycerides" , labelType="c")
	public String cardioTriglycerides;
	
	@Label(label="cardioExceptINLP" , labelType="c")
	public String cardioExceptINLP;
	
	@Label(label="cardioCholesterol_INLP" , labelType="c")
	public String cardioCholesterol_INLP;
	
	@Label(label="cardioCRP" , labelType="c")
	public String cardioCRP;
	
	@Label(label="cardioVegetative" , labelType="c")
	public String cardioVegetative;
	
	@Label(label="kidneySugar" , labelType="c")
	public String kidneySugar;
	
	@Label(label="kidneyKetone" , labelType="c")
	public String kidneyKetone;
	
	@Label(label="kidneyProtein" , labelType="c")
	public String kidneyProtein;
	
	@Label(label="kidneyErythrocyte" , labelType="c")
	public String kidneyErythrocyte;
	
	@Label(label="kidneyLeucocyte" , labelType="c")
	public String kidneyLeucocyte;
	
	@Label(label="kidneyCylinder" , labelType="c")
	public String kidneyCylinder;
	
	@Label(label="kidneyAlbumen" , labelType="c")
	public String kidneyAlbumen;
	
	@Label(label="kidneyCreatinine" , labelType="c")
	public String kidneyCreatinine;
	
	@Label(label="kidneyAlbumenCreatinine" , labelType="c")
	public String kidneyAlbumenCreatinine;
	
	@Label(label="footSkin" , labelType="c" , fieldType="select" , answers={"normal" , "abnormal"})
	public int footSkin;
	
	@Label(label="footSkinDryness" , labelType="c" , fieldType="select" , answers={"normal" , "abnormal"})
	public int footSkinDryness;
	
	@Label(label="footNail" , labelType="c" , fieldType="select" , answers={"normal" , "abnormal"})
	public int footNail;
	
	@Label(label="footForm" , labelType="c" , fieldType="select" , answers={"normal" , "abnormal"})
	public int footForm;
	
	@Label(label="footHorn" , labelType="c" , fieldType="boolean")
	public boolean footHorn;
	
	@Label(label="footInflammation" , labelType="c" , fieldType="boolean")
	public boolean footInflammation;
	
	@Label(label="footBeading" , labelType="c" , fieldType="boolean")
	public boolean footBeading;
	
	@Label(label="footCicatrix" , labelType="c" , fieldType="boolean")
	public boolean footCicatrix;
	
	@Label(label="footDisruption" , labelType="c" , fieldType="boolean")
	public boolean footDisruption;
	
	@Label(label="footAbscission" , labelType="c" , fieldType="boolean")
	public boolean footAbscission;
	
	@Label(label="footDorsalisPedis" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footDorsalisPedis;
	
	@Label(label="footTibialisPosterior" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footTibialisPosterior;
	
	@Label(label="footByConcussion" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footByConcussion;
	
	@Label(label="footByTouch" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footByTouch;
	
	@Label(label="footByTemperature" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footByTemperature;
	
	@Label(label="footByPain" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footByPain;
	
	@Label(label="footByMonofilament" , labelType="c" , fieldType="select" , answers={"normal" , "weak" ,"intactile"})
	public int footByMonofilament;
	
	@Label(label="footRightAnkle" , labelType="c")
	public String footRightAnkle;
	
	@Label(label="footLeftAnkle" , labelType="c")
	public String footLeftAnkle;
	
	@Label(label="footRightAchillesTendonIndex" , labelType="c")
	public String footRightAchillesTendonIndex;
	
	@Label(label="footLeftAchillesTendonIndex" , labelType="c")
	public String footLeftAchillesTendonIndex;
	
	@Label(label="diabetesRateText" , labelType="c")
	public String diabetesRateText;
	
	@Label(label="diabetesAdvice" , labelType="p")
	public String diabetesAdvice;

	public boolean isDiabetes_fatGeneration() {
		return diabetes_fatGeneration;
	}

	public void setDiabetes_fatGeneration(boolean diabetes_fatGeneration) {
		this.diabetes_fatGeneration = diabetes_fatGeneration;
	}

	public boolean isDiabetes_diabetesGeneration() {
		return diabetes_diabetesGeneration;
	}

	public void setDiabetes_diabetesGeneration(boolean diabetes_diabetesGeneration) {
		this.diabetes_diabetesGeneration = diabetes_diabetesGeneration;
	}

	public boolean isDiabetes_weight() {
		return diabetes_weight;
	}

	public void setDiabetes_weight(boolean diabetes_weight) {
		this.diabetes_weight = diabetes_weight;
	}

	public boolean isDiabetes_hepatite() {
		return diabetes_hepatite;
	}

	public void setDiabetes_hepatite(boolean diabetes_hepatite) {
		this.diabetes_hepatite = diabetes_hepatite;
	}

	public boolean isDiabetes_pancreatitis() {
		return diabetes_pancreatitis;
	}

	public void setDiabetes_pancreatitis(boolean diabetes_pancreatitis) {
		this.diabetes_pancreatitis = diabetes_pancreatitis;
	}

	public boolean isDiabetes_pregnantDiabetes() {
		return diabetes_pregnantDiabetes;
	}

	public void setDiabetes_pregnantDiabetes(boolean diabetes_pregnantDiabetes) {
		this.diabetes_pregnantDiabetes = diabetes_pregnantDiabetes;
	}

	public boolean isDiabetes_child() {
		return diabetes_child;
	}

	public void setDiabetes_child(boolean diabetes_child) {
		this.diabetes_child = diabetes_child;
	}

	public boolean isDiabetes_alcoholic() {
		return diabetes_alcoholic;
	}

	public void setDiabetes_alcoholic(boolean diabetes_alcoholic) {
		this.diabetes_alcoholic = diabetes_alcoholic;
	}

	public boolean isDiabetes_cigarettes() {
		return diabetes_cigarettes;
	}

	public void setDiabetes_cigarettes(boolean diabetes_cigarettes) {
		this.diabetes_cigarettes = diabetes_cigarettes;
	}

	public boolean isDiabetes_nutrition() {
		return diabetes_nutrition;
	}

	public void setDiabetes_nutrition(boolean diabetes_nutrition) {
		this.diabetes_nutrition = diabetes_nutrition;
	}

	public boolean isDiabetes_movement() {
		return diabetes_movement;
	}

	public void setDiabetes_movement(boolean diabetes_movement) {
		this.diabetes_movement = diabetes_movement;
	}

	public int getDiabetesYear() {
		return diabetesYear;
	}

	public void setDiabetesYear(int diabetesYear) {
		this.diabetesYear = diabetesYear;
	}

	public String getDiabetespharmacy() {
		return diabetespharmacy;
	}

	public void setDiabetespharmacy(String diabetespharmacy) {
		this.diabetespharmacy = diabetespharmacy;
	}

	public String getDiabetesOtherPainText() {
		return diabetesOtherPainText;
	}

	public void setDiabetesOtherPainText(String diabetesOtherPainText) {
		this.diabetesOtherPainText = diabetesOtherPainText;
	}

	public String getDiabetesMedText() {
		return diabetesMedText;
	}

	public void setDiabetesMedText(String diabetesMedText) {
		this.diabetesMedText = diabetesMedText;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getFat() {
		return fat;
	}

	public void setFat(String fat) {
		this.fat = fat;
	}

	public String getWaistline() {
		return waistline;
	}

	public void setWaistline(String waistline) {
		this.waistline = waistline;
	}

	public String getBMI() {
		return BMI;
	}

	public void setBMI(String bMI) {
		BMI = bMI;
	}

	public String getSelfPeckishGlucose() {
		return selfPeckishGlucose;
	}

	public void setSelfPeckishGlucose(String selfPeckishGlucose) {
		this.selfPeckishGlucose = selfPeckishGlucose;
	}

	public String getSelfGorgedGlucose() {
		return selfGorgedGlucose;
	}

	public void setSelfGorgedGlucose(String selfGorgedGlucose) {
		this.selfGorgedGlucose = selfGorgedGlucose;
	}

	public String getDoctorHbA1CPercentage() {
		return doctorHbA1CPercentage;
	}

	public void setDoctorHbA1CPercentage(String doctorHbA1CPercentage) {
		this.doctorHbA1CPercentage = doctorHbA1CPercentage;
	}

	public int getDoctorHbA1C() {
		return doctorHbA1C;
	}

	public void setDoctorHbA1C(int doctorHbA1C) {
		this.doctorHbA1C = doctorHbA1C;
	}

	public String getAnti_GADIA2() {
		return anti_GADIA2;
	}

	public void setAnti_GADIA2(String anti_GADIA2) {
		this.anti_GADIA2 = anti_GADIA2;
	}

	public String getAnti_GADIA2Text() {
		return anti_GADIA2Text;
	}

	public void setAnti_GADIA2Text(String anti_GADIA2Text) {
		this.anti_GADIA2Text = anti_GADIA2Text;
	}

	public String getInsulin() {
		return insulin;
	}

	public void setInsulin(String insulin) {
		this.insulin = insulin;
	}

	public String getInsulinText() {
		return insulinText;
	}

	public void setInsulinText(String insulinText) {
		this.insulinText = insulinText;
	}

	public String getC_peptide() {
		return c_peptide;
	}

	public void setC_peptide(String c_peptide) {
		this.c_peptide = c_peptide;
	}

	public String getC_peptideText() {
		return c_peptideText;
	}

	public void setC_peptideText(String c_peptideText) {
		this.c_peptideText = c_peptideText;
	}

	public String getDoctorPeckishGlucose() {
		return doctorPeckishGlucose;
	}

	public void setDoctorPeckishGlucose(String doctorPeckishGlucose) {
		this.doctorPeckishGlucose = doctorPeckishGlucose;
	}

	public String getDoctorGorgedGlucose() {
		return doctorGorgedGlucose;
	}

	public void setDoctorGorgedGlucose(String doctorGorgedGlucose) {
		this.doctorGorgedGlucose = doctorGorgedGlucose;
	}

	public String getCardioCholesterol() {
		return cardioCholesterol;
	}

	public void setCardioCholesterol(String cardioCholesterol) {
		this.cardioCholesterol = cardioCholesterol;
	}

	public String getCardioBNLP() {
		return cardioBNLP;
	}

	public void setCardioBNLP(String cardioBNLP) {
		this.cardioBNLP = cardioBNLP;
	}

	public String getCardioINLP() {
		return cardioINLP;
	}

	public void setCardioINLP(String cardioINLP) {
		this.cardioINLP = cardioINLP;
	}

	public String getCardioTriglycerides() {
		return cardioTriglycerides;
	}

	public void setCardioTriglycerides(String cardioTriglycerides) {
		this.cardioTriglycerides = cardioTriglycerides;
	}

	public String getCardioExceptINLP() {
		return cardioExceptINLP;
	}

	public void setCardioExceptINLP(String cardioExceptINLP) {
		this.cardioExceptINLP = cardioExceptINLP;
	}

	public String getCardioCholesterol_INLP() {
		return cardioCholesterol_INLP;
	}

	public void setCardioCholesterol_INLP(String cardioCholesterol_INLP) {
		this.cardioCholesterol_INLP = cardioCholesterol_INLP;
	}

	public String getCardioCRP() {
		return cardioCRP;
	}

	public void setCardioCRP(String cardioCRP) {
		this.cardioCRP = cardioCRP;
	}

	public String getCardioVegetative() {
		return cardioVegetative;
	}

	public void setCardioVegetative(String cardioVegetative) {
		this.cardioVegetative = cardioVegetative;
	}

	public String getKidneySugar() {
		return kidneySugar;
	}

	public void setKidneySugar(String kidneySugar) {
		this.kidneySugar = kidneySugar;
	}

	public String getKidneyKetone() {
		return kidneyKetone;
	}

	public void setKidneyKetone(String kidneyKetone) {
		this.kidneyKetone = kidneyKetone;
	}

	public String getKidneyProtein() {
		return kidneyProtein;
	}

	public void setKidneyProtein(String kidneyProtein) {
		this.kidneyProtein = kidneyProtein;
	}

	public String getKidneyErythrocyte() {
		return kidneyErythrocyte;
	}

	public void setKidneyErythrocyte(String kidneyErythrocyte) {
		this.kidneyErythrocyte = kidneyErythrocyte;
	}

	public String getKidneyLeucocyte() {
		return kidneyLeucocyte;
	}

	public void setKidneyLeucocyte(String kidneyLeucocyte) {
		this.kidneyLeucocyte = kidneyLeucocyte;
	}

	public String getKidneyCylinder() {
		return kidneyCylinder;
	}

	public void setKidneyCylinder(String kidneyCylinder) {
		this.kidneyCylinder = kidneyCylinder;
	}

	public String getKidneyAlbumen() {
		return kidneyAlbumen;
	}

	public void setKidneyAlbumen(String kidneyAlbumen) {
		this.kidneyAlbumen = kidneyAlbumen;
	}

	public String getKidneyCreatinine() {
		return kidneyCreatinine;
	}

	public void setKidneyCreatinine(String kidneyCreatinine) {
		this.kidneyCreatinine = kidneyCreatinine;
	}

	public String getKidneyAlbumenCreatinine() {
		return kidneyAlbumenCreatinine;
	}

	public void setKidneyAlbumenCreatinine(String kidneyAlbumenCreatinine) {
		this.kidneyAlbumenCreatinine = kidneyAlbumenCreatinine;
	}

	public int getFootSkin() {
		return footSkin;
	}

	public void setFootSkin(int footSkin) {
		this.footSkin = footSkin;
	}

	public int getFootSkinDryness() {
		return footSkinDryness;
	}

	public void setFootSkinDryness(int footSkinDryness) {
		this.footSkinDryness = footSkinDryness;
	}

	public int getFootNail() {
		return footNail;
	}

	public void setFootNail(int footNail) {
		this.footNail = footNail;
	}

	public int getFootForm() {
		return footForm;
	}

	public void setFootForm(int footForm) {
		this.footForm = footForm;
	}

	public boolean isFootHorn() {
		return footHorn;
	}

	public void setFootHorn(boolean footHorn) {
		this.footHorn = footHorn;
	}

	public boolean isFootInflammation() {
		return footInflammation;
	}

	public void setFootInflammation(boolean footInflammation) {
		this.footInflammation = footInflammation;
	}

	public boolean isFootBeading() {
		return footBeading;
	}

	public void setFootBeading(boolean footBeading) {
		this.footBeading = footBeading;
	}

	public boolean isFootCicatrix() {
		return footCicatrix;
	}

	public void setFootCicatrix(boolean footCicatrix) {
		this.footCicatrix = footCicatrix;
	}

	public boolean isFootDisruption() {
		return footDisruption;
	}

	public void setFootDisruption(boolean footDisruption) {
		this.footDisruption = footDisruption;
	}

	public boolean isFootAbscission() {
		return footAbscission;
	}

	public void setFootAbscission(boolean footAbscission) {
		this.footAbscission = footAbscission;
	}

	public int getFootDorsalisPedis() {
		return footDorsalisPedis;
	}

	public void setFootDorsalisPedis(int footDorsalisPedis) {
		this.footDorsalisPedis = footDorsalisPedis;
	}

	public int getFootTibialisPosterior() {
		return footTibialisPosterior;
	}

	public void setFootTibialisPosterior(int footTibialisPosterior) {
		this.footTibialisPosterior = footTibialisPosterior;
	}

	public int getFootByConcussion() {
		return footByConcussion;
	}

	public void setFootByConcussion(int footByConcussion) {
		this.footByConcussion = footByConcussion;
	}

	public int getFootByTouch() {
		return footByTouch;
	}

	public void setFootByTouch(int footByTouch) {
		this.footByTouch = footByTouch;
	}

	public int getFootByTemperature() {
		return footByTemperature;
	}

	public void setFootByTemperature(int footByTemperature) {
		this.footByTemperature = footByTemperature;
	}

	public int getFootByPain() {
		return footByPain;
	}

	public void setFootByPain(int footByPain) {
		this.footByPain = footByPain;
	}

	public int getFootByMonofilament() {
		return footByMonofilament;
	}

	public void setFootByMonofilament(int footByMonofilament) {
		this.footByMonofilament = footByMonofilament;
	}

	public String getFootRightAnkle() {
		return footRightAnkle;
	}

	public void setFootRightAnkle(String footRightAnkle) {
		this.footRightAnkle = footRightAnkle;
	}

	public String getFootLeftAnkle() {
		return footLeftAnkle;
	}

	public void setFootLeftAnkle(String footLeftAnkle) {
		this.footLeftAnkle = footLeftAnkle;
	}

	public String getFootRightAchillesTendonIndex() {
		return footRightAchillesTendonIndex;
	}

	public void setFootRightAchillesTendonIndex(String footRightAchillesTendonIndex) {
		this.footRightAchillesTendonIndex = footRightAchillesTendonIndex;
	}

	public String getFootLeftAchillesTendonIndex() {
		return footLeftAchillesTendonIndex;
	}

	public void setFootLeftAchillesTendonIndex(String footLeftAchillesTendonIndex) {
		this.footLeftAchillesTendonIndex = footLeftAchillesTendonIndex;
	}

	public String getDiabetesRateText() {
		return diabetesRateText;
	}

	public void setDiabetesRateText(String diabetesRateText) {
		this.diabetesRateText = diabetesRateText;
	}

	public String getDiabetesAdvice() {
		return diabetesAdvice;
	}

	public void setDiabetesAdvice(String diabetesAdvice) {
		this.diabetesAdvice = diabetesAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDiabetes_nutritionCheck() {
		return diabetes_nutritionCheck;
	}

	public void setDiabetes_nutritionCheck(int diabetes_nutritionCheck) {
		this.diabetes_nutritionCheck = diabetes_nutritionCheck;
	}

	public int getDiabetes_movementCheck() {
		return diabetes_movementCheck;
	}

	public void setDiabetes_movementCheck(int diabetes_movementCheck) {
		this.diabetes_movementCheck = diabetes_movementCheck;
	}
	
	

}

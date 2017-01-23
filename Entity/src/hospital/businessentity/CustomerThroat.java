package hospital.businessentity;

import hospital.annotation.Label;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerThroat implements Serializable {

	public static final long serialVersionUID = 1L;

	// Ovchnii tuuh
	@Label(label = "symptomDate", labelType = "m")
	public Date symptomDate;

	@Label(label = "symptomDuration", labelType = "m")
	public String symptomDuration;

	@Label(label = "usedMedicine", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int usedMedicine;

	@Label(label = "throatUsedMedicine", labelType = "m")
	public String throatUsedMedicine;

	@Label(label = "otherPain", labelType = "p")
	public String otherPain;

	@Label(label = "throatHurt", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int throatHurt;

	@Label(label = "throatHurtYear", labelType = "m")
	public int throatHurtYear;

	@Label(label = "throatHurtRelapse", labelType = "m")
	public int throatHurtRelapse;

	@Label(label = "jointHurt", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int jointHurt;

	@Label(label = "whichJoint", labelType = "m")
	public String whichJoint;

	@Label(label = "heartHurt", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int heartHurt;

	@Label(label = "kidneyHurt", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int kidneyHurt;

	@Label(label = "feelUneasy", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int feelUneasy;

	@Label(label = "voiceHardness", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int voiceHardness;

	@Label(label = "toxicEnvironment", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int toxicEnvironment;

	@Label(label = "coldEnvironment", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int coldEnvironment;

	@Label(label = "hasAllergy", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int hasAllergy;

	@Label(label = "vaccineComplete", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int vaccineComplete;

	@Label(label = "smoke", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int smoke;

	@Label(label = "smokingYear", labelType = "m")
	public int smokingYear;

	@Label(label = "smokePerDay", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int smokePerDay;

	@Label(label = "drinkAlcoholic", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int drinkAlcoholic;

	// Zoviur
	// Ð—Ð°Ð»Ð³Ð¸ÑƒÑ€ Ð·Ð¾Ð²Ð¸ÑƒÑ€
	@Label(label = "throatPain", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int throatPain;

	@Label(label = "salivaSwallowPain", labelType = "p", fieldType = "select", answers = { "no", "low", "medium",
			"high" })
	public int salivaSwallowPain;

	@Label(label = "fever", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int fever;

	@Label(label = "canSwallow", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int canSwallow;

	// Ð¢Ó©Ð²Ó©Ð½Ñ… Ð·Ð¾Ð²Ð¸ÑƒÑ€
	@Label(label = "voiceChange", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int voiceChange;

	@Label(label = "feltUneasy", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int feltUneasy;

	@Label(label = "choke", labelType = "p", fieldType = "select", answers = { "no", "low", "medium", "high" })
	public int choke;

	// Ð‘Ð¾Ð´Ð¸Ñ‚ Ò¯Ð·Ð»Ñ�Ð³
	@Label(label = "throatFork", labelType = "c", fieldType = "select", answers = { "", "rinne", "weber", "jelle",
			"valsalva" })
	public int throatFork;

	@Label(label = "hearingRecord", labelType = "c")
	public String hearingRecord;

	@Label(label = "level", labelType = "c")
	public String level;

	@Label(label = "mouthOpening", labelType = "c", fieldType = "select", answers = { "free", "restricted" })
	public int mouthOpening;

	@Label(label = "uvula", labelType = "c", fieldType = "select", answers = { "no", "yes" })
	public int uvula;

	@Label(label = "softPalatine", labelType = "c", fieldType = "select", answers = { "no", "yes" })
	public int softPalatine;

	@Label(label = "tonsillitis", labelType = "c", fieldType = "select", answers = { "normal", "enlarged" })
	public int tonsillitis;

	@Label(label = "chronica", labelType = "c", fieldType = "select", answers = { "", "zak", "giss", "transfiguration",
			"other" })
	public int chronica;

	@Label(label = "lacuni", labelType = "c", fieldType = "select", answers = { "normal", "dilated" })
	public int lacuni;

	@Label(label = "pharyngealBackWall", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int pharyngealBackWall;

	@Label(label = "pharyngealSideWall", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int pharyngealSideWall;

	@Label(label = "cavity", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int cavity;

	@Label(label = "laryngeal", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int laryngeal;

	@Label(label = "hurnOnTouch", labelType = "c", fieldType = "select", answers = { "normal", "tenderness" })
	public int hurtOnTouch;

	@Label(label = "nurjignah", labelType = "c", fieldType = "select", answers = { "silent", "resonant" })
	public int nurjignah;

	@Label(label = "breathProcess", labelType = "c", fieldType = "select", answers = { "free", "airlock" })
	public int breathProcess;

	@Label(label = "coverCatilage", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int coverCartilage;

	@Label(label = "laryngealMucous", labelType = "c", fieldType = "select", answers = { "", "whitish_pink", "red",
			"whitish" })
	public int laryngealMucous;

	@Label(label = "scoopCartilage", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int scoopCartilage;

	@Label(label = "voiceMinorStrings", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int voiceMinorStrings;

	@Label(label = "majorStrings", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int majorStrings;

	@Label(label = "accent", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int accent;

	@Label(label = "onFonation", labelType = "c")
	public String onFonation;

	@Label(label = "voiceStringTrouble", labelType = "c")
	public String voiceStringTrouble;

	@Label(label = "voiceStringLowerSpace", labelType = "c")
	public String voiceStringLowerSpace;

	@Label(label = "throatCircle", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int throatCircle;

	@Label(label = "throatLimph", labelType = "c")
	public String throatLimph;

	@Label(label = "other", labelType = "c")
	public String other;
	@Label(label = "skinRateText", labelType = "p")
	public String throatRateText;
	@Label(label = "throatScheduleText", labelType = "p")
	public String throatScheduleText;

	public CustomerThroat() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStringDate(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public Date getSymptomDate() {
		return symptomDate;
	}

	public void setSymptomDate(Date symptomDate) {
		this.symptomDate = symptomDate;
	}

	public String getSymptomDuration() {
		symptomDuration = "Тодорхойгүй";
		int year = 0;
		int month = 0;
		int date = 0;
		if (this.symptomDate != null)
			if (symptomDate.getYear() > new Date().getYear()) {
				symptomDuration = "Тодорхойгүй";
			} else {
				if (symptomDate.getMonth() > new Date().getMonth()) {
					if (symptomDate.getDate() > new Date().getDate()) {
						date = new Date().getDate() + 30 - symptomDate.getDate();
						month = new Date().getMonth() + 12 - symptomDate.getMonth();
						year = new Date().getYear() - symptomDate.getYear();
					} else {
						date = new Date().getDate() - symptomDate.getDate();
						month = new Date().getMonth() + 12 - symptomDate.getMonth();
						year = new Date().getYear() - symptomDate.getYear();
					}
				} else {
					date = new Date().getDate() - symptomDate.getDate();
					month = new Date().getMonth() - symptomDate.getMonth();
					year = new Date().getYear() - symptomDate.getYear();
				}

			}
		if (year == 0)
			symptomDuration = "";
		else
			symptomDuration = year + "жил" + " ";

		if (month == 0)
			symptomDuration = symptomDuration + "";
		else
			symptomDuration = symptomDuration + month + "сар" + " ";

		if (date == 0)
			symptomDuration = symptomDuration + "";
		else
			symptomDuration = symptomDuration + date + "өдөр";

		return symptomDuration;
	}

	public void setSymptomDuration(String symptomDuration) {
		this.symptomDuration = symptomDuration;
	}

	public int getUsedMedicine() {
		return usedMedicine;
	}

	public void setUsedMedicine(int usedMedicine) {
		this.usedMedicine = usedMedicine;
	}

	public int getThroatHurt() {
		return throatHurt;
	}

	public void setThroatHurt(int throatHurt) {
		this.throatHurt = throatHurt;
	}

	public int getThroatHurtYear() {
		return throatHurtYear;
	}

	public void setThroatHurtYear(int throatHurtYear) {
		this.throatHurtYear = throatHurtYear;
	}

	public int getThroatHurtRelapse() {
		return throatHurtRelapse;
	}

	public void setThroatHurtRelapse(int throatHurtRelapse) {
		this.throatHurtRelapse = throatHurtRelapse;
	}

	public int getJointHurt() {
		return jointHurt;
	}

	public void setJointHurt(int jointHurt) {
		this.jointHurt = jointHurt;
	}

	public int getHeartHurt() {
		return heartHurt;
	}

	public void setHeartHurt(int heartHurt) {
		this.heartHurt = heartHurt;
	}

	public int getKidneyHurt() {
		return kidneyHurt;
	}

	public void setKidneyHurt(int kidneyHurt) {
		this.kidneyHurt = kidneyHurt;
	}

	public int getThroatPain() {
		return throatPain;
	}

	public void setThroatPain(int throatPain) {
		this.throatPain = throatPain;
	}

	public int getSalivaSwallowPain() {
		return salivaSwallowPain;
	}

	public void setSalivaSwallowPain(int salivaSwallowPain) {
		this.salivaSwallowPain = salivaSwallowPain;
	}

	public int getFever() {
		return fever;
	}

	public void setFever(int fever) {
		this.fever = fever;
	}

	public int getCanSwallow() {
		return canSwallow;
	}

	public void setCanSwallow(int canSwallow) {
		this.canSwallow = canSwallow;
	}

	public int getFeelUneasy() {
		return feelUneasy;
	}

	public void setFeelUneasy(int feelUneasy) {
		this.feelUneasy = feelUneasy;
	}

	public int getVoiceHardness() {
		return voiceHardness;
	}

	public void setVoiceHardness(int voiceHardness) {
		this.voiceHardness = voiceHardness;
	}

	public int getToxicEnvironment() {
		return toxicEnvironment;
	}

	public void setToxicEnvironment(int toxicEnvironment) {
		this.toxicEnvironment = toxicEnvironment;
	}

	public int getColdEnvironment() {
		return coldEnvironment;
	}

	public void setColdEnvironment(int coldEnvironment) {
		this.coldEnvironment = coldEnvironment;
	}

	public int getHasAllergy() {
		return hasAllergy;
	}

	public void setHasAllergy(int hasAllergy) {
		this.hasAllergy = hasAllergy;
	}

	public int getVaccineComplete() {
		return vaccineComplete;
	}

	public void setVaccineComplete(int vaccineComplete) {
		this.vaccineComplete = vaccineComplete;
	}

	public int getSmoke() {
		return smoke;
	}

	public void setSmoke(int smoke) {
		this.smoke = smoke;
	}

	public int getSmokingYear() {
		return smokingYear;
	}

	public void setSmokingYear(int smokingYear) {
		this.smokingYear = smokingYear;
	}

	public int getSmokePerDay() {
		return smokePerDay;
	}

	public void setSmokePerDay(int smokePerDay) {
		this.smokePerDay = smokePerDay;
	}

	public int getDrinkAlcoholic() {
		return drinkAlcoholic;
	}

	public void setDrinkAlcoholic(int drinkAlcoholic) {
		this.drinkAlcoholic = drinkAlcoholic;
	}

	public int getVoiceChange() {
		return voiceChange;
	}

	public void setVoiceChange(int voiceChange) {
		this.voiceChange = voiceChange;
	}

	public int getFeltUneasy() {
		return feltUneasy;
	}

	public void setFeltUneasy(int feltUneasy) {
		this.feltUneasy = feltUneasy;
	}

	public int getChoke() {
		return choke;
	}

	public void setChoke(int choke) {
		this.choke = choke;
	}

	public int getThroatFork() {
		return throatFork;
	}

	public void setThroatFork(int throatFork) {
		this.throatFork = throatFork;
	}

	public String getHearingRecord() {
		return hearingRecord;
	}

	public void setHearingRecord(String hearingRecord) {
		this.hearingRecord = hearingRecord;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getMouthOpening() {
		return mouthOpening;
	}

	public void setMouthOpening(int mouthOpening) {
		this.mouthOpening = mouthOpening;
	}

	public int getUvula() {
		return uvula;
	}

	public void setUvula(int uvula) {
		this.uvula = uvula;
	}

	public int getSoftPalatine() {
		return softPalatine;
	}

	public void setSoftPalatine(int softPalatine) {
		this.softPalatine = softPalatine;
	}

	public int getTonsillitis() {
		return tonsillitis;
	}

	public void setTonsillitis(int tonsillitis) {
		this.tonsillitis = tonsillitis;
	}

	public int getChronica() {
		return chronica;
	}

	public void setChronica(int chronica) {
		this.chronica = chronica;
	}

	public int getLacuni() {
		return lacuni;
	}

	public void setLacuni(int lacuni) {
		this.lacuni = lacuni;
	}

	public int getPharyngealBackWall() {
		return pharyngealBackWall;
	}

	public void setPharyngealBackWall(int pharyngealBackWall) {
		this.pharyngealBackWall = pharyngealBackWall;
	}

	public int getPharyngealSideWall() {
		return pharyngealSideWall;
	}

	public void setPharyngealSideWall(int pharyngealSideWall) {
		this.pharyngealSideWall = pharyngealSideWall;
	}

	public int getCavity() {
		return cavity;
	}

	public void setCavity(int cavity) {
		this.cavity = cavity;
	}

	public int getLaryngeal() {
		return laryngeal;
	}

	public void setLaryngeal(int laryngeal) {
		this.laryngeal = laryngeal;
	}

	public int getHurtOnTouch() {
		return hurtOnTouch;
	}

	public void setHurtOnTouch(int hurtOnTouch) {
		this.hurtOnTouch = hurtOnTouch;
	}

	public int getNurjignah() {
		return nurjignah;
	}

	public void setNurjignah(int nurjignah) {
		this.nurjignah = nurjignah;
	}

	public int getBreathProcess() {
		return breathProcess;
	}

	public void setBreathProcess(int breathProcess) {
		this.breathProcess = breathProcess;
	}

	public int getCoverCartilage() {
		return coverCartilage;
	}

	public void setCoverCartilage(int coverCartilage) {
		this.coverCartilage = coverCartilage;
	}

	public int getLaryngealMucous() {
		return laryngealMucous;
	}

	public void setLaryngealMucous(int laryngealMucous) {
		this.laryngealMucous = laryngealMucous;
	}

	public int getScoopCartilage() {
		return scoopCartilage;
	}

	public void setScoopCartilage(int scoopCartilage) {
		this.scoopCartilage = scoopCartilage;
	}

	public int getVoiceMinorStrings() {
		return voiceMinorStrings;
	}

	public void setVoiceMinorStrings(int voiceMinorStrings) {
		this.voiceMinorStrings = voiceMinorStrings;
	}

	public int getMajorStrings() {
		return majorStrings;
	}

	public void setMajorStrings(int majorStrings) {
		this.majorStrings = majorStrings;
	}

	public int getAccent() {
		return accent;
	}

	public void setAccent(int accent) {
		this.accent = accent;
	}

	public String getOnFonation() {
		return onFonation;
	}

	public void setOnFonation(String onFonation) {
		this.onFonation = onFonation;
	}

	public String getVoiceStringTrouble() {
		return voiceStringTrouble;
	}

	public void setVoiceStringTrouble(String voiceStringTrouble) {
		this.voiceStringTrouble = voiceStringTrouble;
	}

	public String getVoiceStringLowerSpace() {
		return voiceStringLowerSpace;
	}

	public void setVoiceStringLowerSpace(String voiceStringLowerSpace) {
		this.voiceStringLowerSpace = voiceStringLowerSpace;
	}

	public int getThroatCircle() {
		return throatCircle;
	}

	public void setThroatCircle(int throatCircle) {
		this.throatCircle = throatCircle;
	}

	public String getThroatLimph() {
		return throatLimph;
	}

	public void setThroatLimph(String throatLimph) {
		this.throatLimph = throatLimph;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getThroatUsedMedicine() {
		return throatUsedMedicine;
	}

	public void setThroatUsedMedicine(String throatUsedMedicine) {
		this.throatUsedMedicine = throatUsedMedicine;
	}

	public String getWhichJoint() {
		return whichJoint;
	}

	public void setWhichJoint(String whichJoint) {
		this.whichJoint = whichJoint;
	}

	public String getThroatRateText() {
		return throatRateText;
	}

	public void setThroatRateText(String throatRateText) {
		this.throatRateText = throatRateText;
	}

	public String getThroatScheduleText() {
		return throatScheduleText;
	}

	public void setThroatScheduleText(String throatScheduleText) {
		this.throatScheduleText = throatScheduleText;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

}
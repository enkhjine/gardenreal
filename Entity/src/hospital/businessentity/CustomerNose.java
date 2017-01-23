package hospital.businessentity;

import hospital.annotation.Label;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerNose implements Serializable {

	public static final long serialVersionUID = 1L;

	// Ó¨Ð²Ñ‡Ð½Ð¸Ð¹ Ñ‚Ò¯Ò¯Ñ…
	@Label(label = "symptomDate", labelType = "m")
	public Date symptomDate;

	@Label(label = "symptomDuration", labelType = "m")
	public String symptomDuration;

	@Label(label = "usedMedicine", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int usedMedicine;

	@Label(label = "noseUsedMedicine", labelType = "m")
	public String noseUsedMedicine;

	@Label(label = "noseInjure", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int noseInjure;

	@Label(label = "noseInjureDate", labelType = "m")
	public Date noseInjureDate;

	@Label(label = "noseInjureReason", labelType = "m")
	public String noseInjureReason;

	@Label(label = "noseInjureOccurance", labelType = "m")
	public int noseInjureOccurance;

	@Label(label = "noseAllergy", labelType = "m", fieldType = "select", answers = { "no", "yes" })
	public int noseAllergy;

	@Label(label = "noseAllergies", labelType = "m")
	public String noseAllergies;

	@Label(label = "noseAllergyDate", labelType = "m")
	public Date noseAllergyDate;

	@Label(label = "otherPain", labelType = "p")
	public String otherPain;

	@Label(label = "noseAllergyDuration", labelType = "m")
	public int noseAllergyDuration;

	@Label(label = "noseAllergyMedicine", labelType = "m")
	public String noseAllergyMedicine;

	@Label(label = "noseAllergyDropUse", labelType = "m")
	public String noseAllergyDropUse;

	@Label(label = "noseAllergyDrops", labelType = "m" ,answers  = {"no", "yes"})
	public int noseAllergyDrops;

	@Label(label = "noseAllergyDropsUsingDuration", labelType = "m")
	public int noseAllergyDropsUsingDuration;

	// Ð—Ð¾Ð²Ð¸ÑƒÑ€
	@Label(label = "noseCongestion", fieldType = "select", answers = { "no", "low", "medium", "high" }, labelType = "p")
	public int noseCongestion;

	@Label(label = "smellLoss", fieldType = "select", answers = { "no", "low", "medium", "high" }, labelType = "p")
	public int smellLoss;

	@Label(label = "headAche", fieldType = "select", answers = { "no", "low", "medium", "high" }, labelType = "p")
	public int headAche;

	@Label(label = "frontDischarge", fieldType = "select", answers = { "no", "low", "medium", "high" }, labelType = "p")
	public int frontDischarge;

	@Label(label = "backDischarge", fieldType = "select", answers = { "no", "low", "medium", "high" }, labelType = "p")
	public int backDischarge;

	// Ð‘Ð¾Ð´Ð¸Ñ‚ Ò¯Ð·Ð»Ñ�Ð³
	@Label(label = "noseBreath", labelType = "c", fieldType = "select", answers = { "free", "airlockRight",
			"airlockLeft", "airlockBoth" })
	public int noseBreath;

	@Label(label = "noseCave", labelType = "c", fieldType = "select", answers = { "free", "airlock" })
	public int noseCave;

	@Label(label = "noseShape", labelType = "c", fieldType = "select", answers = { "free", "airlock" })
	public int noseShape;

	@Label(label = "nosePartition", labelType = "c", fieldType = "select", answers = { "straight", "curved",
			"other_changes" })
	public int nosePartition;

	@Label(label = "hurtWhenTouch", labelType = "c", fieldType = "select", answers = { "no", "yes" })
	public int hurtWhenTouch;

	@Label(label = "nosePath", labelType = "c", fieldType = "select", answers = { "straight", "curved",
			"other_changes" })
	public int nosePath;

	@Label(label = "noseMucosal", labelType = "c", fieldType = "select", answers = { "select", "pink", "salmon_pink",
			"bluish", "whitish", "other" })
	public int noseMucosal;

	@Label(label = "noseDischarge", labelType = "c", fieldType = "select", answers = { "normal", "teratoid" })
	public int noseDischarge;

	@Label(label = "noseTurbinates", labelType = "c", fieldType = "select", answers = { "normal", "hydropsy",
			"other_changes" })
	public int noseTurbinates;

	@Label(label = "noseSymptom", labelType = "c", fieldType = "select", answers = { "select", "flavored",
			"suppurative", "green", "creamy", "other_changes" })
	public int noseSymptom;

	@Label(label = "noseGate", labelType = "c", fieldType = "select", answers = { "normal", "abnormal" })
	public int noseGate;

	@Label(label = "noseLimph", labelType = "c")
	public String noseLimph;

	@Label(label = "noseSickness", labelType = "c", fieldType = "select", answers = { "normal", "medium", "very_high",
			"asphyxia" })
	public int noseSickness;
	@Label(label = "skinRateText", labelType = "p")
	public String noseRateText;
	
	@Label(label = "schedule", labelType = "c")
	public String noseScheduleText;

	@Label(label = "advice", labelType = "p")
	public String advice;

	public CustomerNose() {
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

	public int getNoseInjure() {
		return noseInjure;
	}

	public void setNoseInjure(int noseInjure) {
		this.noseInjure = noseInjure;
	}

	public Date getNoseInjureDate() {
		return noseInjureDate;
	}

	public void setNoseInjureDate(Date noseInjureDate) {
		this.noseInjureDate = noseInjureDate;
	}

	public String getNoseInjureReason() {
		return noseInjureReason;
	}

	public void setNoseInjureReason(String noseInjureReason) {
		this.noseInjureReason = noseInjureReason;
	}

	public int getNoseInjureOccurance() {
		return noseInjureOccurance;
	}

	public void setNoseInjureOccurance(int noseInjureOccurance) {
		this.noseInjureOccurance = noseInjureOccurance;
	}

	public int getNoseAllergy() {
		return noseAllergy;
	}

	public void setNoseAllergy(int noseAllergy) {
		this.noseAllergy = noseAllergy;
	}

	public String getNoseAllergies() {
		return noseAllergies;
	}

	public void setNoseAllergies(String noseAllergies) {
		this.noseAllergies = noseAllergies;
	}

	public Date getNoseAllergyDate() {
		return noseAllergyDate;
	}

	public void setNoseAllergyDate(Date noseAllergyDate) {
		this.noseAllergyDate = noseAllergyDate;
	}

	public int getNoseAllergyDuration() {
		return noseAllergyDuration;
	}

	public void setNoseAllergyDuration(int noseAllergyDuration) {
		this.noseAllergyDuration = noseAllergyDuration;
	}

	public int getNoseCongestion() {
		return noseCongestion;
	}

	public void setNoseCongestion(int noseCongestion) {
		this.noseCongestion = noseCongestion;
	}

	public int getSmellLoss() {
		return smellLoss;
	}

	public void setSmellLoss(int smellLoss) {
		this.smellLoss = smellLoss;
	}

	public int getHeadAche() {
		return headAche;
	}

	public void setHeadAche(int headAche) {
		this.headAche = headAche;
	}

	public int getFrontDischarge() {
		return frontDischarge;
	}

	public void setFrontDischarge(int frontDischarge) {
		this.frontDischarge = frontDischarge;
	}

	public int getBackDischarge() {
		return backDischarge;
	}

	public void setBackDischarge(int backDischarge) {
		this.backDischarge = backDischarge;
	}

	public int getNoseAllergyDrops() {
		return noseAllergyDrops;
	}

	public void setNoseAllergyDrops(int noseAllergyDrops) {
		this.noseAllergyDrops = noseAllergyDrops;
	}

	public int getNoseAllergyDropsUsingDuration() {
		return noseAllergyDropsUsingDuration;
	}

	public void setNoseAllergyDropsUsingDuration(int noseAllergyDropsUsingDuration) {
		this.noseAllergyDropsUsingDuration = noseAllergyDropsUsingDuration;
	}

	public int getNoseBreath() {
		return noseBreath;
	}

	public void setNoseBreath(int noseBreath) {
		this.noseBreath = noseBreath;
	}

	public int getNoseCave() {
		return noseCave;
	}

	public void setNoseCave(int noseCave) {
		this.noseCave = noseCave;
	}

	public int getNoseShape() {
		return noseShape;
	}

	public void setNoseShape(int noseShape) {
		this.noseShape = noseShape;
	}

	public int getNosePartition() {
		return nosePartition;
	}

	public void setNosePartition(int nosePartition) {
		this.nosePartition = nosePartition;
	}

	public int getHurtWhenTouch() {
		return hurtWhenTouch;
	}

	public void setHurtWhenTouch(int hurtWhenTouch) {
		this.hurtWhenTouch = hurtWhenTouch;
	}

	public int getNosePath() {
		return nosePath;
	}

	public void setNosePath(int nosePath) {
		this.nosePath = nosePath;
	}

	public int getNoseMucosal() {
		return noseMucosal;
	}

	public void setNoseMucosal(int noseMucosal) {
		this.noseMucosal = noseMucosal;
	}

	public int getNoseDischarge() {
		return noseDischarge;
	}

	public void setNoseDischarge(int noseDischarge) {
		this.noseDischarge = noseDischarge;
	}

	public int getNoseTurbinates() {
		return noseTurbinates;
	}

	public void setNoseTurbinates(int noseTurbinates) {
		this.noseTurbinates = noseTurbinates;
	}

	public int getNoseSymptom() {
		return noseSymptom;
	}

	public void setNoseSymptom(int noseSymptom) {
		this.noseSymptom = noseSymptom;
	}

	public int getNoseGate() {
		return noseGate;
	}

	public void setNoseGate(int noseGate) {
		this.noseGate = noseGate;
	}

	public String getNoseLimph() {
		return noseLimph;
	}

	public String getNoseRateText() {
		return noseRateText;
	}

	public void setNoseRateText(String noseRateText) {
		this.noseRateText = noseRateText;
	}

	public void setNoseLimph(String noseLimph) {
		this.noseLimph = noseLimph;
	}

	public int getNoseSickness() {
		return noseSickness;
	}

	public void setNoseSickness(int noseSickness) {
		this.noseSickness = noseSickness;
	}

	public String getNoseUsedMedicine() {
		return noseUsedMedicine;
	}

	public void setNoseUsedMedicine(String noseUsedMedicine) {
		this.noseUsedMedicine = noseUsedMedicine;
	}

	public String getNoseAllergyMedicine() {
		return noseAllergyMedicine;
	}

	public void setNoseAllergyMedicine(String noseAllergyMedicine) {
		this.noseAllergyMedicine = noseAllergyMedicine;
	}

	public String getNoseAllergyDropUse() {
		return noseAllergyDropUse;
	}

	public void setNoseAllergyDropUse(String noseAllergyDropUse) {
		this.noseAllergyDropUse = noseAllergyDropUse;
	}

	public String getConvertSymptomDate() {
		if (symptomDate == null) {
			symptomDate = new Date();
		}
		return new SimpleDateFormat("yyyy:MM:dd").format(symptomDate);
	}

	public String getNoseScheduleText() {
		return noseScheduleText;
	}

	public void setNoseScheduleText(String noseScheduleText) {
		this.noseScheduleText = noseScheduleText;
	}

	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

}
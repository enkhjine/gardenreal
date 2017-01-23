package hospital.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.swing.text.DateFormatter;

@Entity
@Table(name = "CustomerPastHistory")
public class CustomerPastHistory {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	// @Column(name = "InspectionPkId")
	// private BigDecimal inspectionPkId;

	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;

	// Birth
	@Column(name = "BirthDate")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "BirthPlace")
	private String birthPlace;

	/*
	 * 0 - Төрөх замаар 1 - Кесар хагалгаагаар
	 */
	@Column(name = "BirthWay")
	private int birthWay;

	/*
	 * 0 - Хугацаандаа 1 - Өмнө 2 - Хожуу
	 */
	@Column(name = "BirthInTime")
	private int birthInTime;

	@Column(name = "BirthDifferenceWeek")
	private String birthDifferenceWeek;

	// Child hood growth

	/*
	 * 0 - Хэвийн 1 - Хэвийн бус
	 */
	@Column(name = "ChildGrowth")
	private int childGrowth;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Kindergarten")
	private int kindergarten;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "School")
	private int school;

	// Immunization

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Vaccine")
	private int vaccine;

	// Past history

	@Column(name = "InfectiousDisease")
	private String infectiousDisease;

	@Column(name = "ChronicIllness")
	private String chronicIllness;

	@Column(name = "Injury")
	private String injury;

	@Column(name = "InjuryDate")
	@Temporal(TemporalType.DATE)
	private Date injuryDate;

	@Column(name = "Surgery")
	private String surgery;

	@Column(name = "SurgeryDate")
	@Temporal(TemporalType.DATE)
	private Date surgeryDate;

	// Life style

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Alcohol")
	private int alcohol;

	@Column(name = "AlcoholType")
	private String alcoholType;

	@Column(name = "AlcoholUsed")
	private String alcoholUsed;

	@Column(name = "AlcoholDose")
	private String alcoholDose;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Tobacco")
	private int tobacco;

	@Column(name = "TobaccoUsed")
	private String tobaccoUsed;

	@Column(name = "TobaccoType")
	private String tobaccoType;

	@Column(name = "TobaccoDose")
	private String tobaccoDose;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Drug")
	private int drug;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "DrugAddict")
	private int drugAddict;

	/*
	 * 0 - Махан 1 - Цагаан
	 */
	@Column(name = "Food")
	private int food;

	@Column(name = "FoodTimes")
	private String foodTimes;

	/*
	 * 0 - Хуурсан 1 - Шөлтэй
	 */
	@Column(name = "FoodType")
	private int foodType;

	/*
	 * 0 - Гэртээ 1 - Гадуур
	 */
	@Column(name = "FoodPlace")
	private int foodPlace;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Excercise")
	private int excercise;

	@Column(name = "ExcerciseWeek")
	private String excerciseWeek;

	@Column(name = "ExcerciseTime")
	private String excerciseTime;

	// Life Condition

	/*
	 * 0 - Орон сууц 1 - Гэр 2 - Хувийн орон сууц
	 */
	@Column(name = "Home")
	private int home;

	@Column(name = "Job")
	private String job;

	/*
	 * 0 - Энгийн 1 - Хүнд 2 - Хортой
	 */
	@Column(name = "JobCondition")
	private int jobCondition;

	@Column(name = "JobTime")
	private String jobTime;

	// Allergy

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Allergy")
	private int allergy;

	@Column(name = "AllergyFood")
	private String allergyFood;

	@Column(name = "AllergyMedicine")
	private String allergyMedicine;

	@Column(name = "AllergyOther")
	private String allergyOther;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "Travel")
	private int travel;

	@Column(name = "TravelPurpose")
	private String travelPurpose;

	/*
	 * 0 - Үгүй 1 - Тийм
	 */
	@Column(name = "BloodRefresh")
	private int bloodRefresh;

	@Column(name = "SameDisease")
	private String sameDisease;

	@Column(name = "FamilyDisease")
	private String familyDisease;

	@Column(name = "ChildHoodDisease")
	private String childHoodDisease;

	@Column(name = "MedicineUsage")
	private String medicineUsage;

	@Transient
	private String status;

	@Column(name = "VaccineCount")
	private int vaccineCount;

	@Column(name = "TreatmentHistoryCount")
	private int treatmentHistoryCount;

	@Column(name = "LifeStyleCount")
	private int lifeStyleCount;

	@Column(name = "LifeConditionCount")
	private int lifeConditionCount;

	@Column(name = "AllergyCount")
	private int allergyCount;

	@Column(name = "MedicineUsageCount")
	private int medicineUsageCount;

	@Column(name = "TravelCount")
	private int travelCount;

	@Column(name = "FamilyDiseaseCount")
	private int familyDiseaseCount;

	@Column(name = "Height")
	private BigDecimal height;
	
	@Column(name = "BodyWeight")
	private BigDecimal bodyWeight;
	
	@Column(name = "PressureHigh")
	private int pressureHigh;
	
	@Column(name = "PressureLow")
	private int pressureLow;
	
	@Transient
	private BigDecimal weightIndex;
	
	@Transient
	private int tmpVaccineCount;

	@Transient
	private int tmpLifeStyleCount;

	@Transient
	private int tmpLifeConditionCount;

	@Transient
	private int tmpAllergyCount;

	@Transient
	private int tmpMedicineUsageCount;

	@Transient
	private int tmpTravelCount;

	@Transient
	private int tmpFamilyDiseaseCount;

	@Transient
	private int tmpTreatmentHistoryCount;

	public CustomerPastHistory() {
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public int getBirthWay() {
		return birthWay;
	}

	public void setBirthWay(int birthWay) {
		this.birthWay = birthWay;
	}

	public int getBirthInTime() {
		return birthInTime;
	}

	public void setBirthInTime(int birthInTime) {
		this.birthInTime = birthInTime;
	}

	public String getBirthDifferenceWeek() {
		return birthDifferenceWeek;
	}

	public void setBirthDifferenceWeek(String birthDifferenceWeek) {
		this.birthDifferenceWeek = birthDifferenceWeek;
	}

	public int getChildGrowth() {
		return childGrowth;
	}

	public void setChildGrowth(int childGrowth) {
		this.childGrowth = childGrowth;
	}

	public int getKindergarten() {
		return kindergarten;
	}

	public void setKindergarten(int kindergarten) {
		this.kindergarten = kindergarten;
	}

	public int getSchool() {
		return school;
	}

	public void setSchool(int school) {
		this.school = school;
	}

	public int getVaccine() {
		return vaccine;
	}

	public void setVaccine(int vaccine) {
		this.vaccine = vaccine;
	}

	public String getInfectiousDisease() {
		return infectiousDisease;
	}

	public void setInfectiousDisease(String infectiousDisease) {
		this.infectiousDisease = infectiousDisease;
	}

	public String getChronicIllness() {
		return chronicIllness;
	}

	public void setChronicIllness(String chronicIllness) {
		this.chronicIllness = chronicIllness;
	}

	public String getInjury() {
		return injury;
	}

	public void setInjury(String injury) {
		this.injury = injury;
	}

	public Date getInjuryDate() {
		return injuryDate;
	}

	public void setInjuryDate(Date injuryDate) {
		this.injuryDate = injuryDate;
	}

	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public Date getSurgeryDate() {
		return surgeryDate;
	}

	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}

	public int getAlcohol() {
		return alcohol;
	}

	public void setAlcohol(int alcohol) {
		this.alcohol = alcohol;
	}

	public String getAlcoholType() {
		return alcoholType;
	}

	public void setAlcoholType(String alcoholType) {
		this.alcoholType = alcoholType;
	}

	public String getAlcoholUsed() {
		return alcoholUsed;
	}

	public void setAlcoholUsed(String alcoholUsed) {
		this.alcoholUsed = alcoholUsed;
	}

	public String getAlcoholDose() {
		return alcoholDose;
	}

	public void setAlcoholDose(String alcoholDose) {
		this.alcoholDose = alcoholDose;
	}

	public int getTobacco() {
		return tobacco;
	}

	public void setTobacco(int tobacco) {
		this.tobacco = tobacco;
	}

	public String getTobaccoUsed() {
		return tobaccoUsed;
	}

	public void setTobaccoUsed(String tobaccoUsed) {
		this.tobaccoUsed = tobaccoUsed;
	}

	public String getTobaccoType() {
		return tobaccoType;
	}

	public void setTobaccoType(String tobaccoType) {
		this.tobaccoType = tobaccoType;
	}

	public String getTobaccoDose() {
		return tobaccoDose;
	}

	public void setTobaccoDose(String tobaccoDose) {
		this.tobaccoDose = tobaccoDose;
	}

	public int getDrug() {
		return drug;
	}

	public void setDrug(int drug) {
		this.drug = drug;
	}

	public int getDrugAddict() {
		return drugAddict;
	}

	public void setDrugAddict(int drugAddict) {
		this.drugAddict = drugAddict;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}

	public String getFoodTimes() {
		return foodTimes;
	}

	public void setFoodTimes(String foodTimes) {
		this.foodTimes = foodTimes;
	}

	public int getFoodType() {
		return foodType;
	}

	public void setFoodType(int foodType) {
		this.foodType = foodType;
	}

	public int getFoodPlace() {
		return foodPlace;
	}

	public void setFoodPlace(int foodPlace) {
		this.foodPlace = foodPlace;
	}

	public int getExcercise() {
		return excercise;
	}

	public void setExcercise(int excercise) {
		this.excercise = excercise;
	}

	public String getExcerciseWeek() {
		return excerciseWeek;
	}

	public void setExcerciseWeek(String excerciseWeek) {
		this.excerciseWeek = excerciseWeek;
	}

	public String getExcerciseTime() {
		return excerciseTime;
	}

	public void setExcerciseTime(String excerciseTime) {
		this.excerciseTime = excerciseTime;
	}

	public int getHome() {
		return home;
	}

	public void setHome(int home) {
		this.home = home;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getJobCondition() {
		return jobCondition;
	}

	public void setJobCondition(int jobCondition) {
		this.jobCondition = jobCondition;
	}

	public String getJobTime() {
		return jobTime;
	}

	public void setJobTime(String jobTime) {
		this.jobTime = jobTime;
	}

	public int getAllergy() {
		return allergy;
	}

	public void setAllergy(int allergy) {
		this.allergy = allergy;
	}

	public String getAllergyFood() {
		return allergyFood;
	}

	public void setAllergyFood(String allergyFood) {
		this.allergyFood = allergyFood;
	}

	public String getAllergyMedicine() {
		return allergyMedicine;
	}

	public void setAllergyMedicine(String allergyMedicine) {
		this.allergyMedicine = allergyMedicine;
	}

	public String getAllergyOther() {
		return allergyOther;
	}

	public void setAllergyOther(String allergyOther) {
		this.allergyOther = allergyOther;
	}

	public int getTravel() {
		return travel;
	}

	public void setTravel(int travel) {
		this.travel = travel;
	}

	public String getTravelPurpose() {
		return travelPurpose;
	}

	public void setTravelPurpose(String travelPurpose) {
		this.travelPurpose = travelPurpose;
	}

	public int getBloodRefresh() {
		return bloodRefresh;
	}

	public void setBloodRefresh(int bloodRefresh) {
		this.bloodRefresh = bloodRefresh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSameDisease() {
		return sameDisease;
	}

	public void setSameDisease(String sameDisease) {
		this.sameDisease = sameDisease;
	}

	public String getFamilyDisease() {
		return familyDisease;
	}

	public void setFamilyDisease(String familyDisease) {
		this.familyDisease = familyDisease;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateString(Date date) {
		if (date == null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public String getDateBirth() {
		return new SimpleDateFormat("yyyy-MM-dd").format(getDateBirth());
	}

	public String getChildHoodDisease() {
		return childHoodDisease;
	}

	public void setChildHoodDisease(String childHoodDisease) {
		this.childHoodDisease = childHoodDisease;
	}

	public String getMedicineUsage() {
		return medicineUsage;
	}

	public void setMedicineUsage(String medicineUsage) {
		this.medicineUsage = medicineUsage;
	}

	public int getVaccineCount() {
		return vaccineCount;
	}

	public void setVaccineCount(int vaccineCount) {
		this.vaccineCount = vaccineCount;
	}

	public int getLifeStyleCount() {
		return lifeStyleCount;
	}

	public void setLifeStyleCount(int lifeStyleCount) {
		this.lifeStyleCount = lifeStyleCount;
	}

	public int getLifeConditionCount() {
		return lifeConditionCount;
	}

	public void setLifeConditionCount(int lifeConditionCount) {
		this.lifeConditionCount = lifeConditionCount;
	}

	public int getAllergyCount() {
		return allergyCount;
	}

	public void setAllergyCount(int allergyCount) {
		this.allergyCount = allergyCount;
	}

	public int getMedicineUsageCount() {
		return medicineUsageCount;
	}

	public void setMedicineUsageCount(int medicineUsageCount) {
		this.medicineUsageCount = medicineUsageCount;
	}

	public int getTravelCount() {
		return travelCount;
	}

	public void setTravelCount(int travelCount) {
		this.travelCount = travelCount;
	}

	public int getFamilyDiseaseCount() {
		return familyDiseaseCount;
	}

	public void setFamilyDiseaseCount(int familyDiseaseCount) {
		this.familyDiseaseCount = familyDiseaseCount;
	}

	public int getTmpVaccineCount() {
		tmpVaccineCount = 0;
		if (vaccine != 0)
			tmpVaccineCount++;
		return tmpVaccineCount;
	}

	public void setTmpVaccineCount(int tmpVaccineCount) {
		this.tmpVaccineCount = tmpVaccineCount;
	}

	public int getTreatmentHistoryCount() {
		return treatmentHistoryCount;
	}

	public void setTreatmentHistoryCount(int treatmentHistoryCount) {
		this.treatmentHistoryCount = treatmentHistoryCount;
	}

	public int getTmpTreatmentHistoryCount() {
		tmpTreatmentHistoryCount = 0;
		if (infectiousDisease != null && !infectiousDisease.equals(""))
			tmpTreatmentHistoryCount++;
		if (chronicIllness != null && !chronicIllness.equals(""))
			tmpTreatmentHistoryCount++;
		if (injury != null && !injury.equals(""))
			tmpTreatmentHistoryCount++;
		if (surgery != null && !surgery.equals(""))
			tmpTreatmentHistoryCount++;
		if (childHoodDisease != null && !childHoodDisease.equals(""))
			tmpTreatmentHistoryCount++;
		return tmpTreatmentHistoryCount;
	}

	public void setTmpTreatmentHistoryCount(int tmpTreatmentHistoryCount) {
		this.tmpTreatmentHistoryCount = tmpTreatmentHistoryCount;
	}

	public int getTmpLifeStyleCount() {
		tmpLifeStyleCount = 0;
		if (alcohol != 0)
			tmpLifeStyleCount++;
		if (alcoholUsed != null && !alcoholUsed.equals(""))
			tmpLifeStyleCount++;
		if (alcoholType != null && !alcoholType.equals(""))
			tmpLifeStyleCount++;
		if (alcoholDose != null && !alcoholDose.equals(""))
			tmpLifeStyleCount++;
		if (tobacco != 0)
			tmpLifeStyleCount++;
		if (tobaccoUsed != null && !tobaccoUsed.equals(""))
			tmpLifeStyleCount++;
		if (tobaccoType != null && !tobaccoType.equals(""))
			tmpLifeStyleCount++;
		if (tobaccoDose != null && !tobaccoDose.equals(""))
			tmpLifeStyleCount++;
		if (drug != 0)
			tmpLifeStyleCount++;
		if (drugAddict != 0)
			tmpLifeStyleCount++;
		if (food != 0)
			tmpLifeStyleCount++;
		if (foodPlace != 0)
			tmpLifeStyleCount++;
		if (excercise != 0)
			tmpLifeStyleCount++;
		if (excerciseWeek != null && !excerciseWeek.equals(""))
			tmpLifeStyleCount++;
		if (excerciseTime != null && !excerciseTime.equals(""))
			tmpLifeStyleCount++;

		return tmpLifeStyleCount;
	}

	public void setTmpLifeStyleCount(int tmpLifeStyleCount) {
		this.tmpLifeStyleCount = tmpLifeStyleCount;
	}

	public int getTmpLifeConditionCount() {
		tmpLifeConditionCount = 0;
		if (home != 0)
			tmpLifeConditionCount++;
		if (job != null && !job.equals(""))
			tmpLifeConditionCount++;
		if (jobCondition != 0)
			tmpLifeConditionCount++;
		if (jobTime != null && !jobTime.equals(""))
			tmpLifeConditionCount++;
		return tmpLifeConditionCount;
	}

	public void setTmpLifeConditionCount(int tmpLifeConditionInt) {
		this.tmpLifeConditionCount = tmpLifeConditionInt;
	}

	public int getTmpAllergyCount() {
		tmpAllergyCount = 0;
		if (allergyFood != null && !allergyFood.equals(""))
			tmpAllergyCount++;
		if (allergyMedicine != null && !allergyMedicine.equals(""))
			tmpAllergyCount++;
		if (allergyOther != null && !allergyOther.equals(""))
			tmpAllergyCount++;
		if (allergy != 0)
			tmpAllergyCount++;
		return tmpAllergyCount;
	}

	public void setTmpAllergyCount(int tmpAllergyCount) {
		this.tmpAllergyCount = tmpAllergyCount;
	}

	public int getTmpMedicineUsageCount() {
		tmpMedicineUsageCount = 0;
		if (travelPurpose != null && !travelPurpose.equals(""))
			tmpMedicineUsageCount++;
		return tmpMedicineUsageCount;
	}

	public void setTmpMedicineUsageCount(int tmpMedicineUsageCount) {
		this.tmpMedicineUsageCount = tmpMedicineUsageCount;
	}

	public int getTmpTravelCount() {
		tmpTravelCount = 0;
		if (travel != 0)
			tmpTravelCount++;
		if (travelPurpose != null && !travelPurpose.equals(""))
			tmpTravelCount++;
		if (bloodRefresh != 0)
			tmpTravelCount++;
		return tmpTravelCount;
	}

	public void setTmpTravelCount(int tmpTravelCount) {
		this.tmpTravelCount = tmpTravelCount;
	}

	public int getTmpFamilyDiseaseCount() {
		tmpFamilyDiseaseCount = 0;
		if (sameDisease != null && !sameDisease.equals(""))
			tmpFamilyDiseaseCount++;
		if (familyDisease != null && !familyDisease.equals(""))
			tmpFamilyDiseaseCount++;
		return tmpFamilyDiseaseCount;
	}

	public void setTmpFamilyDiseaseCount(int tmpFamilyDiseaseCount) {
		this.tmpFamilyDiseaseCount = tmpFamilyDiseaseCount;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getBodyWeight() {
		return bodyWeight;
	}

	public void setBodyWeight(BigDecimal bodyWeight) {
		this.bodyWeight = bodyWeight;
	}

	public String getWeightIndex() {
		if(height != null && !height.equals(BigDecimal.ZERO)
				&& bodyWeight != null && !bodyWeight.equals(BigDecimal.ZERO)){
			weightIndex = bodyWeight.divide(height.divide(new BigDecimal(100), 2, 0).pow(2), 2, 0);
			if(weightIndex.floatValue() < 18.5) {
				return weightIndex.toString() + " туранхай";
			} else if(weightIndex.floatValue() >= 18.5 && weightIndex.floatValue() < 25) {
				return weightIndex.toString() + " хэвийн жин";
			} else if(weightIndex.floatValue() > 24.9 && weightIndex.floatValue() < 30) {
				return weightIndex.toString() + " илүүдэл жинтэй";
			} else if(weightIndex.floatValue() >= 30.0  && weightIndex.floatValue() < 35) {
				return weightIndex.toString() + " таргалалт I зэрэг";
			} else if(weightIndex.floatValue() >= 35.0 && weightIndex.floatValue() < 40) {
				return weightIndex.toString() + " таргалалт II зэрэг";
			} else if(weightIndex.floatValue() > 40) {
				return weightIndex.toString() + " таргалалт III зэрэг";
			} else {
				return "";
			}
		} else
		return "";
	}

	public void setWeightIndex(BigDecimal weightIndex) {
		this.weightIndex = weightIndex;
	}

	public int getPressureHigh() {
		return pressureHigh;
	}

	public void setPressureHigh(int pressureHigh) {
		this.pressureHigh = pressureHigh;
	}

	public int getPressureLow() {
		return pressureLow;
	}

	public void setPressureLow(int pressureLow) {
		this.pressureLow = pressureLow;
	}
	
}
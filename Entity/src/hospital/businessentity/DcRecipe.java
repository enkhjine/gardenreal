package hospital.businessentity;

import hospital.entity.ConditionalPrescription;
import hospital.entity.ConditionalPrescriptionDtl;
import hospital.entity.DoctorRecipe;
import hospital.entity.DoctorRecipeDtl;

public class DcRecipe {
	
	private String name;
	private String type;
	private String recipeTypName;
	private DoctorRecipe doctorRecipe;
	private DoctorRecipeDtl doctorRecipeDtl;
	private ConditionalPrescription conditionalPrescription;
	private ConditionalPrescriptionDtl conditionalPrescriptionDtl;
	
	public DcRecipe(){
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getRecipeTypName() {
		return recipeTypName;
	}
	
	public void setRecipeTypName(String recipeTypName) {
		this.recipeTypName = recipeTypName;
	}

	public DoctorRecipe getDoctorRecipe() {
		return doctorRecipe;
	}

	public void setDoctorRecipe(DoctorRecipe doctorRecipe) {
		this.doctorRecipe = doctorRecipe;
	}

	public DoctorRecipeDtl getDoctorRecipeDtl() {
		return doctorRecipeDtl;
	}

	public void setDoctorRecipeDtl(DoctorRecipeDtl doctorRecipeDtl) {
		this.doctorRecipeDtl = doctorRecipeDtl;
	}
	
	public ConditionalPrescription getConditionalPrescription() {
		return conditionalPrescription;
	}
	
	public void setConditionalPrescription(ConditionalPrescription conditionalPrescription) {
		this.conditionalPrescription = conditionalPrescription;
	}
	
	public ConditionalPrescriptionDtl getConditionalPrescriptionDtl() {
		return conditionalPrescriptionDtl;
	}
	
	public void setConditionalPrescriptionDtl(ConditionalPrescriptionDtl conditionalPrescriptionDtl) {
		this.conditionalPrescriptionDtl = conditionalPrescriptionDtl;
	}
	
}

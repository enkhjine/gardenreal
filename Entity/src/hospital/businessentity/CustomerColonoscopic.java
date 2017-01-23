package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerColonoscopic implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 * **/
	
	@Label(label="provide",labelType="p")
	public String  provide;
	
	@Label(label="treatment",labelType="p",fieldType="select",answers={"one","two"})
	public int treatmentCol;
	
	@Label(label="grant",labelType="p",fieldType="select",answers={"accept","noAccept"})
	public int grantCol;
	
	@Label(label="medicine",labelType="p")
	public String medicineCol;
	
	@Label(label="mazeNews",labelType="p" ,fieldType="select",answers={"one","two"})
	public int mazeNews;
	
	@Label(label="microscope",labelType="p")
	public String microscope;
	
	@Label(label="colonoClean",labelType="p")
	public String colonoClean;
	
	@Label(label="microscopeTime",labelType="p")
	public String microscopeTime;
	
	@Label(label="endo",labelType="p")
	public String endo;
	
	@Label(label="colonoscopy",labelType="p",fieldType="select",answers={"first Time","revisit"})
	public   int colonoscopy;
	
	@Label(label="preparation",labelType="p",fieldType="select",  answers={"Good","mild","poor"})
	public int preparation;
	
	@Label(label="vascularPattern",labelType="p",fieldType="select",answers={"Normal pattern","Acute cases","Chronic cases","Less florid cases"})
	public int vascularPattern;
	
	@Label(label="surface",labelType="p",fieldType="select",answers={"Normal pattern","Granular","Red colored surface","Erytema of the mucosa","Uneven","Pseudopolyps","Imflammatory hyperplasia","with dispesed ligth reflex","increased redness"})
	public int surface;
	
	@Label(label="ulceration",labelType="p",fieldType="select", answers= {"none","Ulcerative colitis ","Crohn's disease "})
	public int ulceration;
	
	@Label(label="localisation",labelType="p",fieldType="select" , answers= {"none","Ulcerative colitis ","Crohn's disease "})
	public int localisation;
	@Label(label="friability",labelType="p",fieldType="select",answers={"Normal","Increased with bleeding at the slightest touch","Increased friability","often punctiform"})
	public int friability;
	
	@Label(label="edema",labelType="p",fieldType="select",answers={"None","Folds are theckened","Colon wall stiffened ","Edematous"})
	public int edema;
	
	@Label(label="secretion",labelType="p",fieldType="select",answers={"Normal","Pus", "Pus mixed in stool ","Passage of blood ", "Necrotic mucosa ","Blood mixed in stool  ", "Mucus"})
	public int secretion;
	
	@Label(label="impression",labelType="p",fieldType="select",answers={ "none","Polyps","Polyposis","Parasites","Diverticulosis","Tumors","Perianal hematoma","External hemorrhoids ","Fistulae","Crohn's disease ","IBD","Anastomoses","Solitary rectal ulcer ","Anitis","Chronic fissure  ","Bleeeding","Foreign bodies "})
	public int impression; 
	
	@Label(label="recommendation",labelType="p",fieldType="select",answers={"none","Follow up 2 years later "})
	public int recommendation;
	
	@Label(label="complication",labelType="p",fieldType="select",answers={"none"})
	public int complication;
	
	@Label(label="endoscopic",labelType="p",fieldType="select",answers={"none" , "EMR","ESD", "Polypectomy","Stent replacement", "Hemostasis"})
	public int endoscopic;
	
	@Label(label="sluredChooseValue",labelType="p",fieldType="select",answers={"Slured","irregular"})
	public int sluredChooseValue;
	
	@Label(label="ulcerationChooseValue",labelType="p",fieldType="select",answers={"with flat erosions","not always present "})
	public int ulcerationChooseValue;
	
	@Label(label="crohnChooseValue",labelType="p",fieldType="select",answers={"none","with circumscribed ", "with solitary ulcers ","with large fissures ", "with fistula","with aphtous ulcer ", "with deep longitudinal ulcer  ","with normally appeared altered mucosa", "with minimally altered mucosa "})
	public int crohnChooseValue;
	
	@Label(label="localUlcerativeChooseValue",labelType="p",fieldType="select",answers={"In rectum","Associated with other colon "})
	public int localUlcerativeChooseValue; 

	@Label(label="localChoneChooseValue",labelType="p",fieldType="select",answers={"Rectum often	 not involved","Proximal colon involved ","Changes in Ileum "})
	public int localChoneChooseValue;
	

	
	
	public int getColonoscopy() {
		return colonoscopy;
	}

	public void setColonoscopy(int colonoscopy) {
		this.colonoscopy = colonoscopy;
	}

	public int getPreparation() {
		return preparation;
	}

	public void setPreparation(int preparation) {
		this.preparation = preparation;
	}

	public int getVascularPattern() {
		return vascularPattern;
	}

	public void setVascularPattern(int vascularPattern) {
		this.vascularPattern = vascularPattern;
	}

	public int getSurface() {
		return surface;
	}

	public void setSurface(int surface) {
		this.surface = surface;
	}

	public int getUlceration() {
		return ulceration;
	}

	public void setUlceration(int ulceration) {
		this.ulceration = ulceration;
	}

	public int getLocalisation() {
		return localisation;
	}

	public void setLocalisation(int localisation) {
		this.localisation = localisation;
	}

	public int getFriability() {
		return friability;
	}

	public void setFriability(int friability) {
		this.friability = friability;
	}

	public int getEdema() {
		return edema;
	}

	public void setEdema(int edema) {
		this.edema = edema;
	}

	public int getSecretion() {
		return secretion;
	}

	public void setSecretion(int secretion) {
		this.secretion = secretion;
	}

	public int getImpression() {
		return impression;
	}

	public void setImpression(int impression) {
		this.impression = impression;
	}

	public int getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}

	public int getComplication() {
		return complication;
	}

	public void setComplication(int complication) {
		this.complication = complication;
	}

	public int getEndoscopic() {
		return endoscopic;
	}

	public void setEndoscopic(int endoscopic) {
		this.endoscopic = endoscopic;
	}

	public int getSluredChooseValue() {
		return sluredChooseValue;
	}

	public void setSluredChooseValue(int sluredChooseValue) {
		this.sluredChooseValue = sluredChooseValue;
	}

	public int getUlcerationChooseValue() {
		return ulcerationChooseValue;
	}

	public void setUlcerationChooseValue(int ulcerationChooseValue) {
		this.ulcerationChooseValue = ulcerationChooseValue;
	}

	public int getCrohnChooseValue() {
		return crohnChooseValue;
	}

	public void setCrohnChooseValue(int crohnChooseValue) {
		this.crohnChooseValue = crohnChooseValue;
	}

	public int getLocalUlcerativeChooseValue() {
		return localUlcerativeChooseValue;
	}

	public void setLocalUlcerativeChooseValue(int localUlcerativeChooseValue) {
		this.localUlcerativeChooseValue = localUlcerativeChooseValue;
	}

	public int getLocalChoneChooseValue() {
		return localChoneChooseValue;
	}

	public void setLocalChoneChooseValue(int localChoneChooseValue) {
		this.localChoneChooseValue = localChoneChooseValue;
	}

	public int getTreatmentCol() {
		return treatmentCol;
	}

	public void setTreatmentCol(int treatmentCol) {
		this.treatmentCol = treatmentCol;
	}

	public int getGrantCol() {
		return grantCol;
	}

	public void setGrantCol(int grantCol) {
		this.grantCol = grantCol;
	}

	public String getMedicineCol() {
		return medicineCol;
	}

	public void setMedicineCol(String medicineCol) {
		this.medicineCol = medicineCol;
	}

	public int getMazeNews() {
		return mazeNews;
	}

	public void setMazeNews(int mazeNews) {
		this.mazeNews = mazeNews;
	}

	public String getMicroscope() {
		return microscope;
	}

	public void setMicroscope(String microscope) {
		this.microscope = microscope;
	}

	public String getColonoClean() {
		return colonoClean;
	}

	public void setColonoClean(String colonoClean) {
		this.colonoClean = colonoClean;
	}

	public String getMicroscopeTime() {
		return microscopeTime;
	}

	public void setMicroscopeTime(String microscopeTime) {
		this.microscopeTime = microscopeTime;
	}

	public String getProvide() {
		return provide;
	}

	public void setProvide(String provide) {
		this.provide = provide;
	}

	public String getEndo() {
		return endo;
	}

	public void setEndo(String endo) {
		this.endo = endo;
	}

	
	
	
	
}

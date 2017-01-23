package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerThryoid {
	
	private static final long serialVersionUID = 1L;
	
	
	@Label(label="thryoidPain" , labelType="p" , fieldType="select" , answers={"hyperthyroidism" , "hypothyroidism"})
	public int thryoidPain;
	
	@Label(label="thryoidPainText" , labelType="p")
	public String thryoidPainText;
	
	@Label(label="thryoidMedText" , labelType="m")
	public String thryoidMedText;
	
	@Label(label="thryoidEnlargement" , labelType="c" , fieldType="select" , answers={"0_rating" , "1st_rating" , "2nd_rating"})
	public int thryoidEnlargement;
	
	@Label(label="eyeEmbole" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int eyeEmbole;
	
	@Label(label="eyeFeature" , labelType="c" , fieldType="select" , answers={"crauss" , "gref" , "koher" , "mebius" , "shtelva" , "delrimpl" , "elenic" , "rosenbakh" , "botkin" , "joffrua"})
	public int eyeFeature;
	
	@Label(label="mareFeature" , labelType="c" , fieldType="select" , answers={"revealed" , "notRevealed"})
	public int mareFeature;
	
	@Label(label="electricalFeature" , labelType="c" , fieldType="select" , answers={"revealed" , "notRevealed"})
	public int electricalFeature;
	
	
	@Label(label="dermographism" , labelType="c" , fieldType="select" , answers={"revealed" , "notRevealed"})
	public int dermographism;
	
	@Label(label="freeT3" , labelType="c")
	public String freeT3;
	
	@Label(label="freeT4" , labelType="c")
	public String freeT4;
	
	@Label(label="totalT3" , labelType="c")
	public String totalT3;
	
	@Label(label="totalT4" , labelType="c")
	public String totalT4;
	
	@Label(label="TSH" , labelType="c")
	public String TSH;
	
	@Label(label="TRAb" , labelType="c")
	public String TRAb;
	
	@Label(label="TGAb" , labelType="c")
	public String TGAb;
	
	@Label(label="TPOAb" , labelType="c")
	public String TPOAb;
	
	@Label(label="thryoidXrayExamination" , labelType="c")
	public String thryoidXrayExamination;
	
	@Label(label="thryoidNuclearExamination" , labelType="c")
	public String thryoidNuclearExamination;
	
	@Label(label="thryoidCellExamination" , labelType="c")
	public String thryoidCellExamination;
	
	@Label(label="thryoidRateText" , labelType="c")
	public String thryoidRateText;
	
	@Label(label="thryoidAdvice" , labelType="p")
	public String thryoidAdvice;

	public int getThryoidPain() {
		return thryoidPain;
	}

	public void setThryoidPain(int thryoidPain) {
		this.thryoidPain = thryoidPain;
	}

	public String getThryoidPainText() {
		return thryoidPainText;
	}

	public void setThryoidPainText(String thryoidPainText) {
		this.thryoidPainText = thryoidPainText;
	}

	public String getThryoidMedText() {
		return thryoidMedText;
	}

	public void setThryoidMedText(String thryoidMedText) {
		this.thryoidMedText = thryoidMedText;
	}

	public int getThryoidEnlargement() {
		return thryoidEnlargement;
	}

	public void setThryoidEnlargement(int thryoidEnlargement) {
		this.thryoidEnlargement = thryoidEnlargement;
	}

	public int getEyeEmbole() {
		return eyeEmbole;
	}

	public void setEyeEmbole(int eyeEmbole) {
		this.eyeEmbole = eyeEmbole;
	}

	public int getEyeFeature() {
		return eyeFeature;
	}

	public void setEyeFeature(int eyeFeature) {
		this.eyeFeature = eyeFeature;
	}

	public int getMareFeature() {
		return mareFeature;
	}

	public void setMareFeature(int mareFeature) {
		this.mareFeature = mareFeature;
	}

	public int getElectricalFeature() {
		return electricalFeature;
	}

	public void setElectricalFeature(int electricalFeature) {
		this.electricalFeature = electricalFeature;
	}

	public int getDermographism() {
		return dermographism;
	}

	public void setDermographism(int dermographism) {
		this.dermographism = dermographism;
	}

	public String getFreeT3() {
		return freeT3;
	}

	public void setFreeT3(String freeT3) {
		this.freeT3 = freeT3;
	}

	public String getFreeT4() {
		return freeT4;
	}

	public void setFreeT4(String freeT4) {
		this.freeT4 = freeT4;
	}

	public String getTotalT3() {
		return totalT3;
	}

	public void setTotalT3(String totalT3) {
		this.totalT3 = totalT3;
	}

	public String getTotalT4() {
		return totalT4;
	}

	public void setTotalT4(String totalT4) {
		this.totalT4 = totalT4;
	}

	public String getTSH() {
		return TSH;
	}

	public void setTSH(String tSH) {
		TSH = tSH;
	}

	public String getTRAb() {
		return TRAb;
	}

	public void setTRAb(String tRAb) {
		TRAb = tRAb;
	}

	public String getTGAb() {
		return TGAb;
	}

	public void setTGAb(String tGAb) {
		TGAb = tGAb;
	}

	public String getTPOAb() {
		return TPOAb;
	}

	public void setTPOAb(String tPOAb) {
		TPOAb = tPOAb;
	}

	public String getThryoidXrayExamination() {
		return thryoidXrayExamination;
	}

	public void setThryoidXrayExamination(String thryoidXrayExamination) {
		this.thryoidXrayExamination = thryoidXrayExamination;
	}

	public String getThryoidNuclearExamination() {
		return thryoidNuclearExamination;
	}

	public void setThryoidNuclearExamination(String thryoidNuclearExamination) {
		this.thryoidNuclearExamination = thryoidNuclearExamination;
	}

	public String getThryoidCellExamination() {
		return thryoidCellExamination;
	}

	public void setThryoidCellExamination(String thryoidCellExamination) {
		this.thryoidCellExamination = thryoidCellExamination;
	}

	public String getThryoidRateText() {
		return thryoidRateText;
	}

	public void setThryoidRateText(String thryoidRateText) {
		this.thryoidRateText = thryoidRateText;
	}

	public String getThryoidAdvice() {
		return thryoidAdvice;
	}

	public void setThryoidAdvice(String thryoidAdvice) {
		this.thryoidAdvice = thryoidAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

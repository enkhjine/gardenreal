package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerHeadache {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="haveHeadache" , labelType="p" , fieldType="boolean")
	public boolean haveHeadache;
	
	@Label(label="eyegroundPain" , labelType="p" , fieldType="boolean")
	public boolean eyegroundPain;
	
	@Label(label="haveHalfHeadache" , labelType="p" , fieldType="boolean")
	public boolean haveHalfHeadache;
	
	@Label(label="headHaze" , labelType="p" , fieldType="boolean")
	public boolean headHaze;
	
	@Label(label="headSwim" , labelType="p" , fieldType="boolean")
	public boolean headSwim;
	
	@Label(label="headachePainText" , labelType="p")
	public String headachePainText;
	
	@Label(label="headacheSite" , labelType="m")
	public String headacheSite;
	
	@Label(label="headacheSequence" , labelType="m")
	public String headacheSequence;
	
	@Label(label="headachePower" , labelType="m")
	public String headachePower;
	
	@Label(label="headacheWhenstart" , labelType="m")
	public String headacheWhenstart;
	
	@Label(label="headacheWhen" , labelType="m")
	public String headacheWhen;
	
	@Label(label="headacheRankle" , labelType="m")
	public String headacheRankle;
	
	@Label(label="headacheDuration" , labelType="m")
	public String headacheDuration;
	
	@Label(label="headacheGeneration" , labelType="m")
	public String headacheGeneration;
	
	@Label(label="headacheMedText" , labelType="m")
	public String headacheMedText;
	
	@Label(label="headacheSmellHearTaste" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheSmellHearTaste;
	
	@Label(label="headacheVision" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheVision;
	
	@Label(label="headachePupil" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headachePupil;
	
	@Label(label="headacheEyeMovement" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheEyeMovement;
	
	@Label(label="headacheTrigeminus" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheTrigeminus;
	
	@Label(label="headacheFace" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheFace;
	
	@Label(label="headacheHUN" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheHUN;
	
	@Label(label="headacheTongue" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheTongue;
	
	@Label(label="headacheNeckShoulder" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheNeckShoulder;
	
	@Label(label="headacheJoint" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheJoint;
	
	@Label(label="headacheFeel" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheFeel;
	
	@Label(label="headacheBalance" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheBalance;
	
	@Label(label="headacheMenen" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheMenen;
	
	@Label(label="headacheBackMovement" , labelType="c", fieldType="select" , answers={"yes" , "no"})
	public int headacheBackMovement;
	
	@Label(label="headacheRateText" , labelType="c")
	public String headacheRateText;
	
	@Label(label="headacheAdvice" , labelType="p")
	public String headacheAdvice;

	public boolean isHaveHeadache() {
		return haveHeadache;
	}

	public void setHaveHeadache(boolean haveHeadache) {
		this.haveHeadache = haveHeadache;
	}

	public boolean isEyegroundPain() {
		return eyegroundPain;
	}

	public void setEyegroundPain(boolean eyegroundPain) {
		this.eyegroundPain = eyegroundPain;
	}

	public boolean isHaveHalfHeadache() {
		return haveHalfHeadache;
	}

	public void setHaveHalfHeadache(boolean haveHalfHeadache) {
		this.haveHalfHeadache = haveHalfHeadache;
	}

	public boolean isHeadHaze() {
		return headHaze;
	}

	public void setHeadHaze(boolean headHaze) {
		this.headHaze = headHaze;
	}

	public boolean isHeadSwim() {
		return headSwim;
	}

	public void setHeadSwim(boolean headSwim) {
		this.headSwim = headSwim;
	}

	public String getHeadachePainText() {
		return headachePainText;
	}

	public void setHeadachePainText(String headachePainText) {
		this.headachePainText = headachePainText;
	}

	public String getHeadacheSite() {
		return headacheSite;
	}

	public void setHeadacheSite(String headacheSite) {
		this.headacheSite = headacheSite;
	}

	public String getHeadacheSequence() {
		return headacheSequence;
	}

	public void setHeadacheSequence(String headacheSequence) {
		this.headacheSequence = headacheSequence;
	}

	public String getHeadachePower() {
		return headachePower;
	}

	public void setHeadachePower(String headachePower) {
		this.headachePower = headachePower;
	}

	public String getHeadacheWhenstart() {
		return headacheWhenstart;
	}

	public void setHeadacheWhenstart(String headacheWhenstart) {
		this.headacheWhenstart = headacheWhenstart;
	}

	public String getHeadacheWhen() {
		return headacheWhen;
	}

	public void setHeadacheWhen(String headacheWhen) {
		this.headacheWhen = headacheWhen;
	}

	public String getHeadacheRankle() {
		return headacheRankle;
	}

	public void setHeadacheRankle(String headacheRankle) {
		this.headacheRankle = headacheRankle;
	}

	public String getHeadacheDuration() {
		return headacheDuration;
	}

	public void setHeadacheDuration(String headacheDuration) {
		this.headacheDuration = headacheDuration;
	}

	public String getHeadacheGeneration() {
		return headacheGeneration;
	}

	public void setHeadacheGeneration(String headacheGeneration) {
		this.headacheGeneration = headacheGeneration;
	}

	public String getHeadacheMedText() {
		return headacheMedText;
	}

	public void setHeadacheMedText(String headacheMedText) {
		this.headacheMedText = headacheMedText;
	}

	public int getHeadacheSmellHearTaste() {
		return headacheSmellHearTaste;
	}

	public void setHeadacheSmellHearTaste(int headacheSmellHearTaste) {
		this.headacheSmellHearTaste = headacheSmellHearTaste;
	}

	public int getHeadacheVision() {
		return headacheVision;
	}

	public void setHeadacheVision(int headacheVision) {
		this.headacheVision = headacheVision;
	}

	public int getHeadachePupil() {
		return headachePupil;
	}

	public void setHeadachePupil(int headachePupil) {
		this.headachePupil = headachePupil;
	}

	public int getHeadacheEyeMovement() {
		return headacheEyeMovement;
	}

	public void setHeadacheEyeMovement(int headacheEyeMovement) {
		this.headacheEyeMovement = headacheEyeMovement;
	}

	public int getHeadacheTrigeminus() {
		return headacheTrigeminus;
	}

	public void setHeadacheTrigeminus(int headacheTrigeminus) {
		this.headacheTrigeminus = headacheTrigeminus;
	}

	public int getHeadacheFace() {
		return headacheFace;
	}

	public void setHeadacheFace(int headacheFace) {
		this.headacheFace = headacheFace;
	}

	public int getHeadacheHUN() {
		return headacheHUN;
	}

	public void setHeadacheHUN(int headacheHUN) {
		this.headacheHUN = headacheHUN;
	}

	public int getHeadacheTongue() {
		return headacheTongue;
	}

	public void setHeadacheTongue(int headacheTongue) {
		this.headacheTongue = headacheTongue;
	}

	public int getHeadacheNeckShoulder() {
		return headacheNeckShoulder;
	}

	public void setHeadacheNeckShoulder(int headacheNeckShoulder) {
		this.headacheNeckShoulder = headacheNeckShoulder;
	}

	public int getHeadacheJoint() {
		return headacheJoint;
	}

	public void setHeadacheJoint(int headacheJoint) {
		this.headacheJoint = headacheJoint;
	}

	public int getHeadacheFeel() {
		return headacheFeel;
	}

	public void setHeadacheFeel(int headacheFeel) {
		this.headacheFeel = headacheFeel;
	}

	public int getHeadacheBalance() {
		return headacheBalance;
	}

	public void setHeadacheBalance(int headacheBalance) {
		this.headacheBalance = headacheBalance;
	}

	public int getHeadacheMenen() {
		return headacheMenen;
	}

	public void setHeadacheMenen(int headacheMenen) {
		this.headacheMenen = headacheMenen;
	}

	public int getHeadacheBackMovement() {
		return headacheBackMovement;
	}

	public void setHeadacheBackMovement(int headacheBackMovement) {
		this.headacheBackMovement = headacheBackMovement;
	}

	public String getHeadacheRateText() {
		return headacheRateText;
	}

	public void setHeadacheRateText(String headacheRateText) {
		this.headacheRateText = headacheRateText;
	}

	public String getHeadacheAdvice() {
		return headacheAdvice;
	}

	public void setHeadacheAdvice(String headacheAdvice) {
		this.headacheAdvice = headacheAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

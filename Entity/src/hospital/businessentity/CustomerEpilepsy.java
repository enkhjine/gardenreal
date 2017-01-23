package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerEpilepsy {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="haveEpilepsy" , labelType="p" , fieldType="boolean")
	public boolean haveEpilepsy;
	
	@Label(label="feelBeforeEpilepsy" , labelType="p" , fieldType="boolean")
	public boolean feelBeforeEpilepsy;
	
	@Label(label="epilepsyHeadache" , labelType="p" , fieldType="boolean")
	public boolean epilepsyHeadache;
	
	@Label(label="epilepsyForget" , labelType="p" , fieldType="boolean")
	public boolean epilepsyForget;
	
	@Label(label="distractionAfterEpilepsy" , labelType="p" , fieldType="boolean")
	public boolean distractionAfterEpilepsy;
	
	@Label(label="epilepsyGettingAngry" , labelType="p" , fieldType="boolean")
	public boolean epilepsyGettingAngry;
	
	@Label(label="epilepsyBadForSleep" , labelType="p" , fieldType="boolean")
	public boolean epilepsyBadForSleep;
	
	@Label(label="epilepsyPainText" , labelType="p")
	public String epilepsyPainText;
	
	@Label(label="epilepsyWhen" , labelType="m")
	public String epilepsyWhen;
	
	@Label(label="epilepsySequence" , labelType="m")
	public String epilepsySequence;
	
	@Label(label="epilepsyDuration" , labelType="m")
	public String epilepsyDuration;
	
	@Label(label="epilepsyHow" , labelType="m")
	public String epilepsyHow;
	
	@Label(label="isFeelBeforeEpilepsy" , labelType="m" , fieldType="select" , answers={"no" , "no"})
	public int isFeelBeforeEpilepsy;
	
	@Label(label="howAfterEpilepsy" , labelType="m")
	public String howAfterEpilepsy;
	
	@Label(label="epilepsyWhenIncrease" , labelType="m")
	public String epilepsyWhenIncrease;
	
	@Label(label="epilepsyPharmacy" , labelType="m")
	public String epilepsyPharmacy;
	
	@Label(label="epilepsyCharacter" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int epilepsyCharacter;
	
	@Label(label="epilepsyMind" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int epilepsyMind;
	
	@Label(label="isPeeWhenEpilepsy" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isPeeWhenEpilepsy;
	
	@Label(label="isEpilepsyStatus" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isEpilepsyStatus;
	
	@Label(label="epilepsyMedText" , labelType="m")
	public String epilepsyMedText;
	
	@Label(label="epilepsy12pairs" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int epilepsy12pairs;
	
	@Label(label="epilepsyMovementChanged" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int epilepsyMovementChanged;
	
	@Label(label="epilepsyBalanceChanged" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int epilepsyBalanceChanged;
	
	@Label(label="epilepsyAdvice" , labelType="p")
	public String epilepsyAdvice;
	
	@Label(label="epilepsyRateText" , labelType="c")
	public String epilepsyRateText;

	public boolean isHaveEpilepsy() {
		return haveEpilepsy;
	}

	public void setHaveEpilepsy(boolean haveEpilepsy) {
		this.haveEpilepsy = haveEpilepsy;
	}

	public boolean isFeelBeforeEpilepsy() {
		return feelBeforeEpilepsy;
	}

	public void setFeelBeforeEpilepsy(boolean feelBeforeEpilepsy) {
		this.feelBeforeEpilepsy = feelBeforeEpilepsy;
	}

	public boolean isEpilepsyHeadache() {
		return epilepsyHeadache;
	}

	public void setEpilepsyHeadache(boolean epilepsyHeadache) {
		this.epilepsyHeadache = epilepsyHeadache;
	}

	public boolean isEpilepsyForget() {
		return epilepsyForget;
	}

	public void setEpilepsyForget(boolean epilepsyForget) {
		this.epilepsyForget = epilepsyForget;
	}

	public boolean isDistractionAfterEpilepsy() {
		return distractionAfterEpilepsy;
	}

	public void setDistractionAfterEpilepsy(boolean distractionAfterEpilepsy) {
		this.distractionAfterEpilepsy = distractionAfterEpilepsy;
	}

	public boolean isEpilepsyGettingAngry() {
		return epilepsyGettingAngry;
	}

	public void setEpilepsyGettingAngry(boolean epilepsyGettingAngry) {
		this.epilepsyGettingAngry = epilepsyGettingAngry;
	}

	public boolean isEpilepsyBadForSleep() {
		return epilepsyBadForSleep;
	}

	public void setEpilepsyBadForSleep(boolean epilepsyBadForSleep) {
		this.epilepsyBadForSleep = epilepsyBadForSleep;
	}

	public String getEpilepsyPainText() {
		return epilepsyPainText;
	}

	public void setEpilepsyPainText(String epilepsyPainText) {
		this.epilepsyPainText = epilepsyPainText;
	}

	public String getEpilepsyWhen() {
		return epilepsyWhen;
	}

	public void setEpilepsyWhen(String epilepsyWhen) {
		this.epilepsyWhen = epilepsyWhen;
	}

	public String getEpilepsySequence() {
		return epilepsySequence;
	}

	public void setEpilepsySequence(String epilepsySequence) {
		this.epilepsySequence = epilepsySequence;
	}

	public String getEpilepsyDuration() {
		return epilepsyDuration;
	}

	public void setEpilepsyDuration(String epilepsyDuration) {
		this.epilepsyDuration = epilepsyDuration;
	}

	public String getEpilepsyHow() {
		return epilepsyHow;
	}

	public void setEpilepsyHow(String epilepsyHow) {
		this.epilepsyHow = epilepsyHow;
	}

	public int getIsFeelBeforeEpilepsy() {
		return isFeelBeforeEpilepsy;
	}

	public void setIsFeelBeforeEpilepsy(int isFeelBeforeEpilepsy) {
		this.isFeelBeforeEpilepsy = isFeelBeforeEpilepsy;
	}

	public String getHowAfterEpilepsy() {
		return howAfterEpilepsy;
	}

	public void setHowAfterEpilepsy(String howAfterEpilepsy) {
		this.howAfterEpilepsy = howAfterEpilepsy;
	}

	public String getEpilepsyWhenIncrease() {
		return epilepsyWhenIncrease;
	}

	public void setEpilepsyWhenIncrease(String epilepsyWhenIncrease) {
		this.epilepsyWhenIncrease = epilepsyWhenIncrease;
	}

	public String getEpilepsyPharmacy() {
		return epilepsyPharmacy;
	}

	public void setEpilepsyPharmacy(String epilepsyPharmacy) {
		this.epilepsyPharmacy = epilepsyPharmacy;
	}

	public int getEpilepsyCharacter() {
		return epilepsyCharacter;
	}

	public void setEpilepsyCharacter(int epilepsyCharacter) {
		this.epilepsyCharacter = epilepsyCharacter;
	}

	public int getEpilepsyMind() {
		return epilepsyMind;
	}

	public void setEpilepsyMind(int epilepsyMind) {
		this.epilepsyMind = epilepsyMind;
	}

	public int getIsPeeWhenEpilepsy() {
		return isPeeWhenEpilepsy;
	}

	public void setIsPeeWhenEpilepsy(int isPeeWhenEpilepsy) {
		this.isPeeWhenEpilepsy = isPeeWhenEpilepsy;
	}

	public int getIsEpilepsyStatus() {
		return isEpilepsyStatus;
	}

	public void setIsEpilepsyStatus(int isEpilepsyStatus) {
		this.isEpilepsyStatus = isEpilepsyStatus;
	}

	public String getEpilepsyMedText() {
		return epilepsyMedText;
	}

	public void setEpilepsyMedText(String epilepsyMedText) {
		this.epilepsyMedText = epilepsyMedText;
	}

	public int getEpilepsy12pairs() {
		return epilepsy12pairs;
	}

	public void setEpilepsy12pairs(int epilepsy12pairs) {
		this.epilepsy12pairs = epilepsy12pairs;
	}

	public int getEpilepsyMovementChanged() {
		return epilepsyMovementChanged;
	}

	public void setEpilepsyMovementChanged(int epilepsyMovementChanged) {
		this.epilepsyMovementChanged = epilepsyMovementChanged;
	}

	public int getEpilepsyBalanceChanged() {
		return epilepsyBalanceChanged;
	}

	public void setEpilepsyBalanceChanged(int epilepsyBalanceChanged) {
		this.epilepsyBalanceChanged = epilepsyBalanceChanged;
	}

	public String getEpilepsyAdvice() {
		return epilepsyAdvice;
	}

	public void setEpilepsyAdvice(String epilepsyAdvice) {
		this.epilepsyAdvice = epilepsyAdvice;
	}

	public String getEpilepsyRateText() {
		return epilepsyRateText;
	}

	public void setEpilepsyRateText(String epilepsyRateText) {
		this.epilepsyRateText = epilepsyRateText;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

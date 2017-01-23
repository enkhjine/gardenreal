package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerJaundice {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="palmJaundice" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int palmJaundice;
	
	@Label(label="skinJaundice" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int skinJaundice;
	
	@Label(label="afterBirthJaundice" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int afterBirthJaundice;
	
	@Label(label="afterBirth3daysJaundice" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int afterBirth3daysJaundice;
	
	@Label(label="jaundiceNotDescrease" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceNotDescrease;
	
	@Label(label="jaundice14days" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int jaundice14days;
	
	@Label(label="jaundiceWorried" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceWorried;
	
	@Label(label="badForSleep" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int badForSleep;
	
	@Label(label="badForSuckle" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int badForSuckle;
	
	@Label(label="poopGettingLight" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int poopGettingLight;
	
	@Label(label="peeColorChanged" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int peeColorChanged;
	
	@Label(label="gettingFull" , labelType="p" , fieldType="select" , answers={"no" , "yes"})
	public int gettingFull;
	
	@Label(label="jaundicePainText" , labelType="p")
	public String jaundicePainText;
	
	@Label(label="afterBirthHasJaundice" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int afterBirthHasJaundice;
	
	@Label(label="afterBirth3daysHasJaundice" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int afterBirth3daysHasJaundice;
	
	@Label(label="jaundiceContinued" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceContinued;
	
	@Label(label="jaundiceHasChanged" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceHasChanged;
	
	@Label(label="jaundiceHasIncreased" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceHasIncreased;
	
	@Label(label="palmHasJaundice" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int palmHasJaundice;
	
	@Label(label="jaundiceHasDecreased" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceHasDecreased;
	
	@Label(label="jaundiceIsIncreased" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int jaundiceIsIncreased;
	
	@Label(label="poopIsLight" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int poopIsLight;
	
	@Label(label="isBadForSleep" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isBadForSleep;
	
	@Label(label="isBadForSuckle" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isBadForSuckle;
	
	@Label(label="isWorried" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isWorried;
	
	@Label(label="isPeeLight" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isPeeLight;
	
	@Label(label="isGettingFull" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isGettingFull;
	
	@Label(label="isShocked" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isShocked;
	
	@Label(label="isNotSleep" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isNotSleep;
	
	@Label(label="jaundiceMedText" , labelType="m")
	public String jaundiceMedText;
	
	@Label(label="cSkinJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int cSkinJaundice;

	@Label(label="cPalmJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int cPalmJaundice;
	
	@Label(label="gumJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int gumJaundice;
	
	@Label(label="scleraJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int scleraJaundice;
	
	@Label(label="skinGreen" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int skinGreen;
	
	@Label(label="faceJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int faceJaundice;
	
	@Label(label="bodyJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int bodyJaundice;
	
	@Label(label="jointJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int jointJaundice;
	
	@Label(label="allBodyJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int allBodyJaundice;
	
	@Label(label="abdomenJaundice" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int abdomenJaundice;
	
	@Label(label="abdomenVen" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int abdomenVen;
	
	@Label(label="liverRightRib" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int liverRightRib;
	
	@Label(label="liverRightRibTouch" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int liverRightRibTouch;
	
	@Label(label="spleenTouch" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int spleenTouch;
	
	@Label(label="poopLight" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int poopLight;
	
	@Label(label="peeLight" , labelType="c" , fieldType="select" , answers={"no" , "yes"})
	public int peeLight;
	
	@Label(label="jaundiceRateText" , labelType="c")
	public String jaundiceRateText;
	
	@Label(label="jaundiceAdvice" , labelType="p")
	public String jaundiceAdvice;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getPalmJaundice() {
		return palmJaundice;
	}


	public void setPalmJaundice(int palmJaundice) {
		this.palmJaundice = palmJaundice;
	}


	public int getSkinJaundice() {
		return skinJaundice;
	}


	public void setSkinJaundice(int skinJaundice) {
		this.skinJaundice = skinJaundice;
	}


	public int getAfterBirthJaundice() {
		return afterBirthJaundice;
	}


	public void setAfterBirthJaundice(int afterBirthJaundice) {
		this.afterBirthJaundice = afterBirthJaundice;
	}


	public int getAfterBirth3daysJaundice() {
		return afterBirth3daysJaundice;
	}


	public void setAfterBirth3daysJaundice(int afterBirth3daysJaundice) {
		this.afterBirth3daysJaundice = afterBirth3daysJaundice;
	}


	public int getJaundiceNotDescrease() {
		return jaundiceNotDescrease;
	}


	public void setJaundiceNotDescrease(int jaundiceNotDescrease) {
		this.jaundiceNotDescrease = jaundiceNotDescrease;
	}


	public int getJaundice14days() {
		return jaundice14days;
	}


	public void setJaundice14days(int jaundice14days) {
		this.jaundice14days = jaundice14days;
	}


	public int getJaundiceWorried() {
		return jaundiceWorried;
	}


	public void setJaundiceWorried(int jaundiceWorried) {
		this.jaundiceWorried = jaundiceWorried;
	}


	public int getBadForSleep() {
		return badForSleep;
	}


	public void setBadForSleep(int badForSleep) {
		this.badForSleep = badForSleep;
	}


	public int getBadForSuckle() {
		return badForSuckle;
	}


	public void setBadForSuckle(int badForSuckle) {
		this.badForSuckle = badForSuckle;
	}


	public int getPoopGettingLight() {
		return poopGettingLight;
	}


	public void setPoopGettingLight(int poopGettingLight) {
		this.poopGettingLight = poopGettingLight;
	}


	public int getPeeColorChanged() {
		return peeColorChanged;
	}


	public void setPeeColorChanged(int peeColorChanged) {
		this.peeColorChanged = peeColorChanged;
	}


	public int getGettingFull() {
		return gettingFull;
	}


	public void setGettingFull(int gettingFull) {
		this.gettingFull = gettingFull;
	}


	public String getJaundicePainText() {
		return jaundicePainText;
	}


	public void setJaundicePainText(String jaundicePainText) {
		this.jaundicePainText = jaundicePainText;
	}


	public int getAfterBirthHasJaundice() {
		return afterBirthHasJaundice;
	}


	public void setAfterBirthHasJaundice(int afterBirthHasJaundice) {
		this.afterBirthHasJaundice = afterBirthHasJaundice;
	}


	public int getAfterBirth3daysHasJaundice() {
		return afterBirth3daysHasJaundice;
	}


	public void setAfterBirth3daysHasJaundice(int afterBirth3daysHasJaundice) {
		this.afterBirth3daysHasJaundice = afterBirth3daysHasJaundice;
	}


	public int getJaundiceContinued() {
		return jaundiceContinued;
	}


	public void setJaundiceContinued(int jaundiceContinued) {
		this.jaundiceContinued = jaundiceContinued;
	}


	public int getJaundiceHasChanged() {
		return jaundiceHasChanged;
	}


	public void setJaundiceHasChanged(int jaundiceHasChanged) {
		this.jaundiceHasChanged = jaundiceHasChanged;
	}


	public int getJaundiceHasIncreased() {
		return jaundiceHasIncreased;
	}


	public void setJaundiceHasIncreased(int jaundiceHasIncreased) {
		this.jaundiceHasIncreased = jaundiceHasIncreased;
	}


	public int getPalmHasJaundice() {
		return palmHasJaundice;
	}


	public void setPalmHasJaundice(int palmHasJaundice) {
		this.palmHasJaundice = palmHasJaundice;
	}


	public int getJaundiceHasDecreased() {
		return jaundiceHasDecreased;
	}


	public void setJaundiceHasDecreased(int jaundiceHasDecreased) {
		this.jaundiceHasDecreased = jaundiceHasDecreased;
	}


	public int getJaundiceIsIncreased() {
		return jaundiceIsIncreased;
	}


	public void setJaundiceIsIncreased(int jaundiceIsIncreased) {
		this.jaundiceIsIncreased = jaundiceIsIncreased;
	}


	public int getPoopIsLight() {
		return poopIsLight;
	}


	public void setPoopIsLight(int poopIsLight) {
		this.poopIsLight = poopIsLight;
	}


	public int getIsBadForSleep() {
		return isBadForSleep;
	}


	public void setIsBadForSleep(int isBadForSleep) {
		this.isBadForSleep = isBadForSleep;
	}


	public int getIsBadForSuckle() {
		return isBadForSuckle;
	}


	public void setIsBadForSuckle(int isBadForSuckle) {
		this.isBadForSuckle = isBadForSuckle;
	}


	public int getIsWorried() {
		return isWorried;
	}


	public void setIsWorried(int isWorried) {
		this.isWorried = isWorried;
	}


	public int getIsPeeLight() {
		return isPeeLight;
	}


	public void setIsPeeLight(int isPeeLight) {
		this.isPeeLight = isPeeLight;
	}


	public int getIsGettingFull() {
		return isGettingFull;
	}


	public void setIsGettingFull(int isGettingFull) {
		this.isGettingFull = isGettingFull;
	}


	public int getIsShocked() {
		return isShocked;
	}


	public void setIsShocked(int isShocked) {
		this.isShocked = isShocked;
	}


	public int getIsNotSleep() {
		return isNotSleep;
	}


	public void setIsNotSleep(int isNotSleep) {
		this.isNotSleep = isNotSleep;
	}


	public String getJaundiceMedText() {
		return jaundiceMedText;
	}


	public void setJaundiceMedText(String jaundiceMedText) {
		this.jaundiceMedText = jaundiceMedText;
	}


	public int getcSkinJaundice() {
		return cSkinJaundice;
	}


	public void setcSkinJaundice(int cSkinJaundice) {
		this.cSkinJaundice = cSkinJaundice;
	}


	public int getcPalmJaundice() {
		return cPalmJaundice;
	}


	public void setcPalmJaundice(int cPalmJaundice) {
		this.cPalmJaundice = cPalmJaundice;
	}


	public int getGumJaundice() {
		return gumJaundice;
	}


	public void setGumJaundice(int gumJaundice) {
		this.gumJaundice = gumJaundice;
	}


	public int getScleraJaundice() {
		return scleraJaundice;
	}


	public void setScleraJaundice(int scleraJaundice) {
		this.scleraJaundice = scleraJaundice;
	}


	public int getSkinGreen() {
		return skinGreen;
	}


	public void setSkinGreen(int skinGreen) {
		this.skinGreen = skinGreen;
	}


	public int getFaceJaundice() {
		return faceJaundice;
	}


	public void setFaceJaundice(int faceJaundice) {
		this.faceJaundice = faceJaundice;
	}


	public int getBodyJaundice() {
		return bodyJaundice;
	}


	public void setBodyJaundice(int bodyJaundice) {
		this.bodyJaundice = bodyJaundice;
	}


	public int getJointJaundice() {
		return jointJaundice;
	}


	public void setJointJaundice(int jointJaundice) {
		this.jointJaundice = jointJaundice;
	}


	public int getAllBodyJaundice() {
		return allBodyJaundice;
	}


	public void setAllBodyJaundice(int allBodyJaundice) {
		this.allBodyJaundice = allBodyJaundice;
	}


	public int getAbdomenJaundice() {
		return abdomenJaundice;
	}


	public void setAbdomenJaundice(int abdomenJaundice) {
		this.abdomenJaundice = abdomenJaundice;
	}


	public int getAbdomenVen() {
		return abdomenVen;
	}


	public void setAbdomenVen(int abdomenVen) {
		this.abdomenVen = abdomenVen;
	}


	public int getLiverRightRib() {
		return liverRightRib;
	}


	public void setLiverRightRib(int liverRightRib) {
		this.liverRightRib = liverRightRib;
	}


	public int getLiverRightRibTouch() {
		return liverRightRibTouch;
	}


	public void setLiverRightRibTouch(int liverRightRibTouch) {
		this.liverRightRibTouch = liverRightRibTouch;
	}


	public int getSpleenTouch() {
		return spleenTouch;
	}


	public void setSpleenTouch(int spleenTouch) {
		this.spleenTouch = spleenTouch;
	}


	public int getPoopLight() {
		return poopLight;
	}


	public void setPoopLight(int poopLight) {
		this.poopLight = poopLight;
	}


	public int getPeeLight() {
		return peeLight;
	}


	public void setPeeLight(int peeLight) {
		this.peeLight = peeLight;
	}


	public String getJaundiceRateText() {
		return jaundiceRateText;
	}


	public void setJaundiceRateText(String jaundiceRateText) {
		this.jaundiceRateText = jaundiceRateText;
	}


	public String getJaundiceAdvice() {
		return jaundiceAdvice;
	}


	public void setJaundiceAdvice(String jaundiceAdvice) {
		this.jaundiceAdvice = jaundiceAdvice;
	}
	
	
	
}

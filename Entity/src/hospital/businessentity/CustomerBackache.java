package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerBackache {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="haveBackache" , labelType="p", fieldType="boolean")
	public boolean haveBackache;
	
	@Label(label="backCongeal" , labelType="p", fieldType="boolean")
	public boolean backCongeal;
	
	@Label(label="backMovementRestricted" , labelType="p", fieldType="boolean")
	public boolean backMovementRestricted;
	
	@Label(label="backacheToFoot" , labelType="p", fieldType="boolean")
	public boolean backacheToFoot;
	
	@Label(label="footNumb" , labelType="p", fieldType="boolean")
	public boolean footNumb;
	
	@Label(label="footDisentangle" , labelType="p", fieldType="boolean")
	public boolean footDisentangle;
	
	@Label(label="backachePainText" , labelType="p")
	public String backachePainText;
	
	@Label(label="backacheSite" , labelType="m")
	public String backacheSite;
	
	@Label(label="isCauseMovement" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isCauseMovement;
	
	@Label(label="rankleRemission" , labelType="m")
	public String rankleRemission;
	
	@Label(label="backacheMutilation" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int backacheMutilation;
	
	@Label(label="backacheTransfer" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int backacheTransfer;
	
	@Label(label="backachePower" , labelType="m")
	public String backachePower;
	
	@Label(label="isFootNumb" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isFootNumb;
	
	@Label(label="isFootDisentangle" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isFootDisentangle;
	
	@Label(label="isFootFeelChange" , labelType="m" , fieldType="select" , answers={"no" , "yes"})
	public int isFootFeelChange;
	
	@Label(label="backacheMedText" , labelType="m")
	public String backacheMedText;
	
	@Label(label="backLine" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backLine;
	
	@Label(label="backMuscle" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backMuscle;
	
	@Label(label="backMovement" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backMovement;
	
	@Label(label="backParavertebral" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backParavertebral;
	
	@Label(label="backLasegue" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backLasegue;
	
	@Label(label="backMackiewicz" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backMackiewicz;
	
	@Label(label="backValyagi" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backValyagi;
	
	@Label(label="footPowerSame" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int footPowerSame;
	
	@Label(label="backReflex" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backReflex;
	
	@Label(label="backFeelChanged" , labelType="c" , fieldType="select" , answers={"yes" , "no"})
	public int backFeelChanged;
	
	@Label(label="backacheRateText" , labelType="c")
	public String backacheRateText;
	
	@Label(label="backacheAdvice" , labelType="p")
	public String backacheAdvice;

	public boolean isHaveBackache() {
		return haveBackache;
	}

	public void setHaveBackache(boolean haveBackache) {
		this.haveBackache = haveBackache;
	}

	public boolean isBackCongeal() {
		return backCongeal;
	}

	public void setBackCongeal(boolean backCongeal) {
		this.backCongeal = backCongeal;
	}

	public boolean isBackMovementRestricted() {
		return backMovementRestricted;
	}

	public void setBackMovementRestricted(boolean backMovementRestricted) {
		this.backMovementRestricted = backMovementRestricted;
	}

	public boolean isBackacheToFoot() {
		return backacheToFoot;
	}

	public void setBackacheToFoot(boolean backacheToFoot) {
		this.backacheToFoot = backacheToFoot;
	}

	public boolean isFootNumb() {
		return footNumb;
	}

	public void setFootNumb(boolean footNumb) {
		this.footNumb = footNumb;
	}

	public boolean isFootDisentangle() {
		return footDisentangle;
	}

	public void setFootDisentangle(boolean footDisentangle) {
		this.footDisentangle = footDisentangle;
	}

	public String getBackachePainText() {
		return backachePainText;
	}

	public void setBackachePainText(String backachePainText) {
		this.backachePainText = backachePainText;
	}

	public String getBackacheSite() {
		return backacheSite;
	}

	public void setBackacheSite(String backacheSite) {
		this.backacheSite = backacheSite;
	}

	public int getIsCauseMovement() {
		return isCauseMovement;
	}

	public void setIsCauseMovement(int isCauseMovement) {
		this.isCauseMovement = isCauseMovement;
	}

	public String getRankleRemission() {
		return rankleRemission;
	}

	public void setRankleRemission(String rankleRemission) {
		this.rankleRemission = rankleRemission;
	}

	public int getBackacheMutilation() {
		return backacheMutilation;
	}

	public void setBackacheMutilation(int backacheMutilation) {
		this.backacheMutilation = backacheMutilation;
	}

	public int getBackacheTransfer() {
		return backacheTransfer;
	}

	public void setBackacheTransfer(int backacheTransfer) {
		this.backacheTransfer = backacheTransfer;
	}

	public String getBackachePower() {
		return backachePower;
	}

	public void setBackachePower(String backachePower) {
		this.backachePower = backachePower;
	}

	public int getIsFootNumb() {
		return isFootNumb;
	}

	public void setIsFootNumb(int isFootNumb) {
		this.isFootNumb = isFootNumb;
	}

	public int getIsFootDisentangle() {
		return isFootDisentangle;
	}

	public void setIsFootDisentangle(int isFootDisentangle) {
		this.isFootDisentangle = isFootDisentangle;
	}

	public int getIsFootFeelChange() {
		return isFootFeelChange;
	}

	public void setIsFootFeelChange(int isFootFeelChange) {
		this.isFootFeelChange = isFootFeelChange;
	}

	public String getBackacheMedText() {
		return backacheMedText;
	}

	public void setBackacheMedText(String backacheMedText) {
		this.backacheMedText = backacheMedText;
	}

	public int getBackLine() {
		return backLine;
	}

	public void setBackLine(int backLine) {
		this.backLine = backLine;
	}

	public int getBackMuscle() {
		return backMuscle;
	}

	public void setBackMuscle(int backMuscle) {
		this.backMuscle = backMuscle;
	}

	public int getBackMovement() {
		return backMovement;
	}

	public void setBackMovement(int backMovement) {
		this.backMovement = backMovement;
	}

	public int getBackParavertebral() {
		return backParavertebral;
	}

	public void setBackParavertebral(int backParavertebral) {
		this.backParavertebral = backParavertebral;
	}

	public int getBackLasegue() {
		return backLasegue;
	}

	public void setBackLasegue(int backLasegue) {
		this.backLasegue = backLasegue;
	}

	public int getBackMackiewicz() {
		return backMackiewicz;
	}

	public void setBackMackiewicz(int backMackiewicz) {
		this.backMackiewicz = backMackiewicz;
	}

	public int getBackValyagi() {
		return backValyagi;
	}

	public void setBackValyagi(int backValyagi) {
		this.backValyagi = backValyagi;
	}

	public int getFootPowerSame() {
		return footPowerSame;
	}

	public void setFootPowerSame(int footPowerSame) {
		this.footPowerSame = footPowerSame;
	}

	public int getBackReflex() {
		return backReflex;
	}

	public void setBackReflex(int backReflex) {
		this.backReflex = backReflex;
	}

	public int getBackFeelChanged() {
		return backFeelChanged;
	}

	public void setBackFeelChanged(int backFeelChanged) {
		this.backFeelChanged = backFeelChanged;
	}

	public String getBackacheRateText() {
		return backacheRateText;
	}

	public void setBackacheRateText(String backacheRateText) {
		this.backacheRateText = backacheRateText;
	}

	public String getBackacheAdvice() {
		return backacheAdvice;
	}

	public void setBackacheAdvice(String backacheAdvice) {
		this.backacheAdvice = backacheAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

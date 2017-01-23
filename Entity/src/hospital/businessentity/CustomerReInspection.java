package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerReInspection {
	@Label(label = "pain", labelType = "p")
	public String pain;

	@Label(label = "inspection", labelType = "m")
	public String inspection;

	@Label(label = "checkUp", labelType = "c")
	public String checkUp;

	@Label(label = "advice", labelType = "p")
	public String advice;
	
	@Label(label = "skinRateText", labelType = "p")
	public String rateText;
	

	public CustomerReInspection() {
		super();
	}

	public String getPain() {
		return pain;
	}

	public void setPain(String pain) {
		this.pain = pain;
	}

	public String getInspection() {
		return inspection;
	}

	public void setInspection(String inspection) {
		this.inspection = inspection;
	}

	public String getCheckUp() {
		return checkUp;
	}

	public void setCheckUp(String checkUp) {
		this.checkUp = checkUp;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}
	public String getRateText() {
		return rateText;
	}
	public void setRateText(String rateText) {
		this.rateText = rateText;
	}
	

}

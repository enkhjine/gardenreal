package hospital.businessentity;

import hospital.annotation.Label;

public class CustomerPediatricsOther {
	
	private static final long serialVersionUID = 1L;
	
	@Label(label="pediatricsOtherPainText" , labelType="p")
	public String pediatricsOtherPainText;
	
	@Label(label="pediatricsOtherMedText" , labelType="m")
	public String pediatricsOtherMedText;
	
	@Label(label="pediatricsOtherInsText" , labelType="c")
	public String pediatricsOtherInsText;
	
	@Label(label="pediatricsOtherRateText" , labelType="c")
	public String pediatricsOtherRateText;
	
	@Label(label="pediatricsOtherAdvice" , labelType="p")
	public String pediatricsOtherAdvice;

	public String getPediatricsOtherPainText() {
		return pediatricsOtherPainText;
	}

	public void setPediatricsOtherPainText(String pediatricsOtherPainText) {
		this.pediatricsOtherPainText = pediatricsOtherPainText;
	}

	public String getPediatricsOtherMedText() {
		return pediatricsOtherMedText;
	}

	public void setPediatricsOtherMedText(String pediatricsOtherMedText) {
		this.pediatricsOtherMedText = pediatricsOtherMedText;
	}

	public String getPediatricsOtherInsText() {
		return pediatricsOtherInsText;
	}

	public void setPediatricsOtherInsText(String pediatricsOtherInsText) {
		this.pediatricsOtherInsText = pediatricsOtherInsText;
	}

	public String getPediatricsOtherRateText() {
		return pediatricsOtherRateText;
	}

	public void setPediatricsOtherRateText(String pediatricsOtherRateText) {
		this.pediatricsOtherRateText = pediatricsOtherRateText;
	}

	public String getPediatricsOtherAdvice() {
		return pediatricsOtherAdvice;
	}

	public void setPediatricsOtherAdvice(String pediatricsOtherAdvice) {
		this.pediatricsOtherAdvice = pediatricsOtherAdvice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}

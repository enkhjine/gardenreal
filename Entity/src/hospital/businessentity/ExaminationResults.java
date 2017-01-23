package hospital.businessentity;

import hospital.entity.ExaminationValueQuestion;
import hospital.entity.ExaminationValueAnswer;
import hospital.entity.ExaminationValueHdr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExaminationResults {

	private BigDecimal evqPkId;
	private BigDecimal examinationTemplatePkId;
	private String evqName;
	private List<String> values;
	private String range;
	private String measurement;
	private String value;
	private String answerType;
	private ExaminationValueQuestion evq;
	private String resultString;

	public ExaminationResults() {
		super();
	}

	public ExaminationResults(ExaminationValueQuestion question) {
		super();
		this.evqPkId = question.getPkId();
		this.examinationTemplatePkId = question.getExaminationTemplatePkId();
		this.evqName = question.getName();
		this.values = new ArrayList<String>();
		this.range = question.getReference();
		for (ExaminationValueAnswer eva : question.getAnswers()) {
			if (Tool.INPUTNUMBER.equals(eva.getAnswerType())) {
				if (this.getValue() != null)
					if (eva.getNumberValue() != null)
						this.setValue(this.getValue() + eva.getNumberValue().toString() + " ,");
					else
						this.setValue(this.getValue() + " " + " ,");
				else {
					if (eva.getNumberValue() != null)
						this.setValue(eva.getNumberValue().toString() + " ,");
					else
						this.setValue(" ");
				}
				this.setMeasurement(eva.getMeasurement());
			}
			if (Tool.INPUT.equals(eva.getAnswerType()) || Tool.SELECTABLE.equals(eva.getAnswerType())
					|| Tool.RADIO.equals(eva.getAnswerType()) || Tool.TEXTAREA.equals(eva.getAnswerType())) {
				if (this.getValue() != null)
					if (eva.getValue() != null)
						this.setValue(this.getValue() + eva.getValue() + " ,");
					else
						this.setValue(this.getValue() + " " + " ,");
				else {
					if (eva.getValue() != null)
						this.setValue(eva.getValue() + " ,");
					else
						this.setValue(" ");
				}
				this.setMeasurement(eva.getMeasurement());
			}
		}

	}

	public BigDecimal getEvqPkId() {
		return evqPkId;
	}

	public void setEvqPkId(BigDecimal evqPkId) {
		this.evqPkId = evqPkId;
	}

	public BigDecimal getExaminationTemplatePkId() {
		return examinationTemplatePkId;
	}

	public void setExaminationTemplatePkId(BigDecimal examinationTemplatePkId) {
		this.examinationTemplatePkId = examinationTemplatePkId;
	}

	public String getEvqName() {
		return evqName;
	}

	public void setEvqName(String evqName) {
		this.evqName = evqName;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public ExaminationValueQuestion getEvq() {
		return evq;
	}

	public void setEvq(ExaminationValueQuestion evq) {
		this.evq = evq;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getResultString() {
		return resultString;
	}

	public void setResultString(String resultString) {
		this.resultString = resultString;
	}

}
package hospital.entity;

import hospital.businessentity.Tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ExaminationValueAnswer")
public class ExaminationValueAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "QuestionPkId")
	private BigDecimal questionPkId;

	@Column(name = "AnswerType")
	private String answerType;

	@Column(name = "DefaultValue")
	private String defaultValue;

	@Column(name = "AnswerLabel")
	private String answerLabel;

	@Column(name = "HeaderLabel")
	private String headerLabel;

	@Column(name = "ParentPkId")
	private BigDecimal parentPkId;
	
	@Column(name = "Measurement")
	private String measurement;

	@OneToMany(mappedBy = "answer", cascade = CascadeType.ALL)
	private List<ExaminationValueAnswerOption> options;

	@ManyToOne
	@JoinColumn(name = "questionPkId", insertable = false, updatable = false)
	private ExaminationValueQuestion question;

	@Transient
	private boolean isBoolean;

	@Transient
	private boolean isSelectable;

	@Transient
	private boolean isInput;

	@Transient
	private boolean isRadio;

	@Transient
	private boolean isMulti;
	
	@Transient
	private boolean isLabel;
	
	@Transient
	private boolean isTextArea;

	@Transient
	private String value;

	@Transient
	private String status;

	@Transient
	private List<String> values;

	@Transient
	private boolean booleanValue;

	@Transient
	private String valueType;

	@Transient
	boolean disabled;
	
	@Transient
	private boolean isInputNumber;
	
	@Transient
	private BigDecimal numberValue;
	
	

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getQuestionPkId() {
		return questionPkId;
	}

	public void setQuestionPkId(BigDecimal questionPkId) {
		this.questionPkId = questionPkId;
	}

	public String getAnswerType() {
		return answerType;
	}

	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getAnswerLabel() {
		return answerLabel;
	}

	public void setAnswerLabel(String answerLabel) {
		this.answerLabel = answerLabel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@XmlTransient
	public List<ExaminationValueAnswerOption> getOptions() {
		return options;
	}

	public void setOptions(List<ExaminationValueAnswerOption> options) {
		this.options = options;
	}

	public ExaminationValueQuestion getQuestion() {
		return question;
	}

	public void setQuestion(ExaminationValueQuestion question) {
		this.question = question;
	}

	public boolean isBoolean() {
		if (this.answerType.equals(Tool.BOOLEAN))
			return !isBoolean;
		return isBoolean;

	}

	public void setBoolean(boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	public boolean isSelectable() {
		if (this.answerType.equals(Tool.SELECTABLE))
			return !isSelectable;
		return isSelectable;

	}

	public void setSelectable(boolean isSelectable) {
		this.isSelectable = isSelectable;
	}

	public boolean isInput() {
		if (this.answerType.equals(Tool.INPUT))
			return !isInput;
		return isInput;
	}

	public void setInput(boolean isInput) {
		this.isInput = isInput;
	}

	public boolean isRadio() {
		if (this.answerType.equals(Tool.RADIO))
			return !isRadio;
		return isRadio;

	}

	public void setRadio(boolean isRadio) {
		this.isRadio = isRadio;
	}

	public String getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(String headerLabel) {
		this.headerLabel = headerLabel;
	}

	public boolean isMulti() {
		if (this.answerType.equals(Tool.MULTISELECT))
			return !isMulti;
		return isMulti;
	}

	public void setMulti(boolean isMulti) {
		this.isMulti = isMulti;
	}

	public String getValue() {
		if(value == null)
			if(defaultValue !=null)
				value = defaultValue;
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public BigDecimal getParentPkId() {
		return parentPkId;
	}

	public void setParentPkId(BigDecimal parentPkId) {
		this.parentPkId = parentPkId;
	}

	public boolean isDisabled() {		
		if(this.status == null)
			if(this.parentPkId!=null)
				if(this.value ==null || this.values == null)
				disabled = true;
			else
				disabled = false;
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isLabel() {
		if (this.answerType.equals(Tool.LABEL))
			return !isLabel;
		return isLabel;
	}

	public void setLabel(boolean isLabel) {
		this.isLabel = isLabel;
	}

	public boolean isTextArea() {
		if (this.answerType.equals(Tool.TEXTAREA))
			return !isTextArea;
		return isTextArea;
	}

	public void setTextArea(boolean isTextArea) {
		this.isTextArea = isTextArea;
	}

	public boolean isInputNumber() {
		if (this.answerType.equals(Tool.INPUTNUMBER))
			return !isInputNumber;
		return isInputNumber;
		
	}

	public void setInputNumber(boolean isInputNumber) {
		this.isInputNumber = isInputNumber;
	}
	public BigDecimal getNumberValue() {
		return numberValue;
	}
	public void setNumberValue(BigDecimal numberValue) {
		this.numberValue = numberValue;
	}
	
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	
	
}

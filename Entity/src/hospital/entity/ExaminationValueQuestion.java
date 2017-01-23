package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ExaminationValueQuestion")
public class ExaminationValueQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ExaminationTemplatePkId")
	private BigDecimal examinationTemplatePkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "MinValue")
	private String minValue;

	@Column(name = "MaxValue")
	private String maxValue;

	@Transient
	private String reference;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<ExaminationValueAnswer> answers;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getExaminationTemplatePkId() {
		return examinationTemplatePkId;
	}

	public void setExaminationTemplatePkId(BigDecimal examinationTemplatePkId) {
		this.examinationTemplatePkId = examinationTemplatePkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public List<ExaminationValueAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<ExaminationValueAnswer> answers) {
		this.answers = answers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ExaminationValueQuestion() {
		super();
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getReference() {
		if(this.minValue == null)
			if(this.maxValue == null)
				reference = "";
			else
				reference = this.maxValue;
		else
			if(this.maxValue == null)
				reference = this.minValue;
			else
				reference = this.minValue + "-" + this.maxValue;
			
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

}

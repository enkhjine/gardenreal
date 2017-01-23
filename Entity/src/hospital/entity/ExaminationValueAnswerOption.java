package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ExaminationValueAnswerOption")
public class ExaminationValueAnswerOption implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "AnswerPkId")
	private BigDecimal answerPkId;

	@Column(name = "Name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "answerPkId", insertable = false, updatable = false)
	private ExaminationValueAnswer answer;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getAnswerPkId() {
		return answerPkId;
	}

	public void setAnswerPkId(BigDecimal answerPkId) {
		this.answerPkId = answerPkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ExaminationValueAnswer getAnswer() {
		return answer;
	}

	public void setAnswer(ExaminationValueAnswer answer) {
		this.answer = answer;
	}

}

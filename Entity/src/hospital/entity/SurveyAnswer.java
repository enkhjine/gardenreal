package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "SurveyAnswer")
public class SurveyAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "QuestionPkId")
	private BigDecimal questionPkId;

	@Column(name = "Title")
	private String title;

	@Column(name = "Answer")
	private String answer;

	@Column(name = "Description")
	private String description;

	@Column(name = "Point")
	private int point;
	
	@ManyToOne
	@JoinColumn(name = "questionPkId", insertable = false, updatable = false)
	private SurveyQuestion question;

	public SurveyAnswer() {
		super();
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public SurveyQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SurveyQuestion question) {
		this.question = question;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

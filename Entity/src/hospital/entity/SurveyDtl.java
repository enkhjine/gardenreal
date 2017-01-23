package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SurveyDtl")
public class SurveyDtl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "HdrPkId")
	private BigDecimal hdrPkId;
	
	@Column(name = "QuestionPkId")
	private BigDecimal questionPkId;
	
	@Column(name = "AnswerPkId")
	private BigDecimal answerPkId;
	
	@Column(name = "BasicPoint")
	private int basicPoint;
	
	@Column(name = "Point")
	private int Point;
	
	public SurveyDtl(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getHdrPkId() {
		return hdrPkId;
	}

	public void setHdrPkId(BigDecimal hdrPkId) {
		this.hdrPkId = hdrPkId;
	}

	public BigDecimal getQuestionPkId() {
		return questionPkId;
	}

	public void setQuestionPkId(BigDecimal questionPkId) {
		this.questionPkId = questionPkId;
	}

	public BigDecimal getAnswerPkId() {
		return answerPkId;
	}

	public void setAnswerPkId(BigDecimal answerPkId) {
		this.answerPkId = answerPkId;
	}

	public int getBasicPoint() {
		return basicPoint;
	}

	public void setBasicPoint(int basicPoint) {
		this.basicPoint = basicPoint;
	}

	public int getPoint() {
		return Point;
	}

	public void setPoint(int point) {
		Point = point;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

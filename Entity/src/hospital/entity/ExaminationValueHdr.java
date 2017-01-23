package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "ExaminationValueHdr")
public class ExaminationValueHdr implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "RequestPkId")
	private BigDecimal requestPkId;

	@Column(name = "QuestionPkId")
	private BigDecimal questionPkId;

	@Column(name = "AnswerPkId")
	private BigDecimal answerPkId;

	@Column(name = "Value")
	private String value;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Column(name = "LaborantPkId")
	private BigDecimal laborantPkId;

	@Column(name = "DoctorPkId")
	private BigDecimal doctorPkId;

	@Transient
	private String status;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
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

	public String getValue() {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigDecimal getDoctorPkId() {
		return doctorPkId;
	}

	public void setDoctorPkId(BigDecimal doctorPkId) {
		this.doctorPkId = doctorPkId;
	}

	public BigDecimal getLaborantPkId() {
		return laborantPkId;
	}

	public void setLaborantPkId(BigDecimal laborantPkId) {
		this.laborantPkId = laborantPkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

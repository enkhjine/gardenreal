package hospital.entity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ExaminationGroupDtl")
public class ExaminationGroupDtl {
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ExaminationPkId")
	private BigDecimal examinationPkId;

	@Column(name = "ExaminationGroupPkId")
	private BigDecimal examinationGroupPkId;

	@Column(name = "ExaminationDtlPkId")
	private BigDecimal examinationDtlPkId;

	@Transient
	private String examinationName;

	@Transient
	private String elementName;

	@Transient
	private Date updatedDate;

	@Transient
	private String userName;

	public ExaminationGroupDtl() {
		super();
	}

	public ExaminationGroupDtl(BigDecimal pkId, BigDecimal examinationPkId,
			BigDecimal examinationGroupPkId, BigDecimal examinationDtlPkId,
			String examinationName, String elementName, Date updatedDate,
			String userName) {
		super();
		this.pkId = pkId;
		this.examinationPkId = examinationPkId;
		this.examinationGroupPkId = examinationGroupPkId;
		this.examinationDtlPkId = examinationDtlPkId;
		this.examinationName = examinationName;
		this.elementName = elementName;
		this.updatedDate = updatedDate;
		this.userName = userName;
	}



	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}

	public BigDecimal getExaminationGroupPkId() {
		return examinationGroupPkId;
	}

	public void setExaminationGroupPkId(BigDecimal examinationGroupPkId) {
		this.examinationGroupPkId = examinationGroupPkId;
	}

	public BigDecimal getExaminationDtlPkId() {
		return examinationDtlPkId;
	}

	public void setExaminationDtlPkId(BigDecimal examinationDtlPkId) {
		this.examinationDtlPkId = examinationDtlPkId;
	}

	public String getExaminationName() {
		return examinationName;
	}

	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getDateString (Date date)
	{
		if (date == null)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}

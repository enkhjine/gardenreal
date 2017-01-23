package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "SurgeryPrice")
public class SurgeryPrice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "SurgeryPkId")
	private BigDecimal surgeryPkId;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "BeginDate")
	private Date beginDate;
	
	@Column(name = "CreatedBy")
	private BigDecimal createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;
	
	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	@Transient
	private String userName;
	
	@Transient
	private String treatmentName;	
	
	
	
	
	public SurgeryPrice(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getSurgeryPkId() {
		return surgeryPkId;
	}

	public void setSurgeryPkId(BigDecimal surgeryPkId) {
		this.surgeryPkId = surgeryPkId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}

	public SurgeryPrice(BigDecimal price, Date beginDate, Date updatedDate,
			String userName, String treatmentName) {
		super();
		this.price = price;
		this.beginDate = beginDate;
		this.updatedDate = updatedDate;
		this.userName = userName;
		this.treatmentName = treatmentName;
	}
	
	
	public String getDateString (Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}

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
@Table(name="SurgeryIctMap")

public class SurgeryIctMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId",length=18,nullable=false)
	private BigDecimal pkId;
	
	@Column(name="SurgerPkId")
	private  BigDecimal surgerPkId;
	
	@Column(name="IctPkId")
	private BigDecimal ictPkId;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateDate")
	private Date createDate;
	
	@Transient
	private String status;
	
	public SurgeryIctMap(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}


	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}


	public BigDecimal getSurgerPkId() {
		return surgerPkId;
	}


	public void setSurgerPkId(BigDecimal surgerPkId) {
		this.surgerPkId = surgerPkId;
	}


	public BigDecimal getIctPkId() {
		return ictPkId;
	}


	public void setIctPkId(BigDecimal ictPkId) {
		this.ictPkId = ictPkId;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}

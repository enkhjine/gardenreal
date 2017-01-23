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
@Table(name="SurgeryIctModel")
public class SurgeryIctModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId",length=18,nullable = false)
	private BigDecimal pkId;
	
	@Column(name="SurgeryName")
	private String surgeryName;
	
	@Column(name="SurgeryMapPkId")
	private BigDecimal surgeryMapPkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateDate")
	private Date createDate;
	
	@Transient
	private String status;
	
	public SurgeryIctModel(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getSurgeryName() {
		return surgeryName;
	}

	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}

	public BigDecimal getSurgeryMapPkId() {
		return surgeryMapPkId;
	}

	public void setSurgeryMapPkId(BigDecimal surgeryMapPkId) {
		this.surgeryMapPkId = surgeryMapPkId;
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

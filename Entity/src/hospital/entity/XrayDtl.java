package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="XrayDtl")
public class XrayDtl implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="PkId",length=18,nullable=false)
	private BigDecimal pkId;
	
	@Column(name="XrayRequestPkId")
	private BigDecimal xrayRequestPkId;
	
	@Column(name="XrayInspectionPain")
	private String  xrayInspectionPain;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateDate")
	private Date createDate;
	/*
	 *  1 бол  түр  хадгалах  2 бол  дуусгах
	 * **/
	@Column(name="RequestSaveMood")
	private int requestSaveMood;
	
	@Transient
	private String status;
	
	public XrayDtl(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getXrayRequestPkId() {
		return xrayRequestPkId;
	}

	public void setXrayRequestPkId(BigDecimal xrayRequestPkId) {
		this.xrayRequestPkId = xrayRequestPkId;
	}

	public String getXrayInspectionPain() {
		return xrayInspectionPain;
	}

	public void setXrayInspectionPain(String xrayInspectionPain) {
		this.xrayInspectionPain = xrayInspectionPain;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getRequestSaveMood() {
		return requestSaveMood;
	}

	public void setRequestSaveMood(int requestSaveMood) {
		this.requestSaveMood = requestSaveMood;
	}
	
}

package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ExaminationRequestDtl")
public class ExaminationRequestDtl {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "ExaminationRequestPkId")
	private BigDecimal examinationRequestPkId;
	
	@Column(name = "ExaminationDtlPkId")
	private BigDecimal examinationDtlPkId;
	
	public ExaminationRequestDtl(){
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public BigDecimal getPkId() {
		return pkId;
	}
	
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	
	public BigDecimal getExaminationDtlPkId() {
		return examinationDtlPkId;
	}
	
	public void setExaminationDtlPkId(BigDecimal examinationDtlPkId) {
		this.examinationDtlPkId = examinationDtlPkId;
	}
	
	public BigDecimal getExaminationRequestPkId() {
		return examinationRequestPkId;
	}
	
	public void setExaminationRequestPkId(BigDecimal examinationRequestPkId) {
		this.examinationRequestPkId = examinationRequestPkId;
	}
	
}

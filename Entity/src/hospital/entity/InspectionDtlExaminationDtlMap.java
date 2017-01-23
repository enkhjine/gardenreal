package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InspectionDtlExaminationDtlMap")
public class InspectionDtlExaminationDtlMap implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId")
	private BigDecimal pkId;
	
	@Column(name = "ExaminationDtlPkId")
	private BigDecimal examinationDtlPkId;
	
	@Column(name = "InspectionDtlPkId")
	private BigDecimal inspectionDtlPkId;
	
	public InspectionDtlExaminationDtlMap(){
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
	
	public BigDecimal getInspectionDtlPkId() {
		return inspectionDtlPkId;
	}
	
	public void setInspectionDtlPkId(BigDecimal inspectionDtlPkId) {
		this.inspectionDtlPkId = inspectionDtlPkId;
	}
	
}

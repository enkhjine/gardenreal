package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TreatmentTypeEmployeeMap")
public class TreatmentTypeEmployeeMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "TreatmentTypePkId")
	private BigDecimal treatmentTypePkId;

	@Transient
	private String employeeName;

	public TreatmentTypeEmployeeMap() {
		super();
	}

	public TreatmentTypeEmployeeMap(BigDecimal pkId, BigDecimal employeePkId,
			BigDecimal treatmentTypePkId, String employeeName) {
		super();
		this.pkId = pkId;
		this.employeePkId = employeePkId;
		this.treatmentTypePkId = treatmentTypePkId;
		this.employeeName = employeeName;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getTreatmentTypePkId() {
		return treatmentTypePkId;
	}

	public void setTreatmentTypePkId(BigDecimal treatmentTypePkId) {
		this.treatmentTypePkId = treatmentTypePkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}

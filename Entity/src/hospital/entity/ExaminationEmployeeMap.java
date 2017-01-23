package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ExaminationEmployeeMap")
public class ExaminationEmployeeMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "ExaminationPkId")
	private BigDecimal examinationPkId;

	/**
	 * 0-Laborant
	 * 1-Doctor
	 */
	@Column(name = "Type")
	private int type;

	@Transient
	private String employeeName;

	public ExaminationEmployeeMap() {
		super();
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

	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ConditionalPrescriptionSubOrganizationTypeMap")
public class ConditionalPrescriptionSubOrganizationTypeMap implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ConditionalPrescriptionPkId")
	private BigDecimal conditionalPrescriptionPkId;

	@Column(name = "SubOrganizationTypePkId")
	private BigDecimal subOrganizationTypePkId;

	@Transient
	private String conditionalPrescriptionName;

	@Transient
	private String subOrganizationTypeName;

	public ConditionalPrescriptionSubOrganizationTypeMap() {
		super();
	}

	public ConditionalPrescriptionSubOrganizationTypeMap(BigDecimal pkId,
			BigDecimal conditionalPrescriptionPkId,
			BigDecimal subOrganizationTypePkId,
			String conditionalPrescriptionName, String subOrganizationTypeName) {
		super();
		this.pkId = pkId;
		this.conditionalPrescriptionPkId = conditionalPrescriptionPkId;
		this.subOrganizationTypePkId = subOrganizationTypePkId;
		this.conditionalPrescriptionName = conditionalPrescriptionName;
		this.subOrganizationTypeName = subOrganizationTypeName;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getConditionalPrescriptionPkId() {
		return conditionalPrescriptionPkId;
	}

	public void setConditionalPrescriptionPkId(
			BigDecimal conditionalPrescriptionPkId) {
		this.conditionalPrescriptionPkId = conditionalPrescriptionPkId;
	}

	public BigDecimal getSubOrganizationTypePkId() {
		return subOrganizationTypePkId;
	}

	public void setSubOrganizationTypePkId(BigDecimal subOrganizationTypePkId) {
		this.subOrganizationTypePkId = subOrganizationTypePkId;
	}

	public String getConditionalPrescriptionName() {
		return conditionalPrescriptionName;
	}

	public void setConditionalPrescriptionName(
			String conditionalPrescriptionName) {
		this.conditionalPrescriptionName = conditionalPrescriptionName;
	}

	public String getSubOrganizationTypeName() {
		return subOrganizationTypeName;
	}

	public void setSubOrganizationTypeName(String subOrganizationTypeName) {
		this.subOrganizationTypeName = subOrganizationTypeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

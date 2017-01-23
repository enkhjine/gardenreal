package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "InsuranceAmbulanceConfigDtl")
public class InsuranceAmbulanceConfigDtl {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "DiagnoseGroupPkId")
	private BigDecimal diagnoseGroupPkId;
	
	@Column(name = "Cost")
	private BigDecimal cost;
	
	@Transient
	private String status;
	
	@Transient
	private String diagnoseGroupName;
	
	public InsuranceAmbulanceConfigDtl() {
		super();
	}

	public InsuranceAmbulanceConfigDtl(BigDecimal pkId,
			BigDecimal organizationPkId, BigDecimal diagnoseGroupPkId,
			String diagnoseGroupName, BigDecimal cost) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.diagnoseGroupPkId = diagnoseGroupPkId;
		this.diagnoseGroupName = diagnoseGroupName;
		this.cost = cost;
	}



	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	
	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}
	
	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public BigDecimal getDiagnoseGroupPkId() {
		return diagnoseGroupPkId;
	}

	public void setDiagnoseGroupPkId(BigDecimal diagnoseGroupPkId) {
		this.diagnoseGroupPkId = diagnoseGroupPkId;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDiagnoseGroupName() {
		return diagnoseGroupName;
	}
	
	public void setDiagnoseGroupName(String diagnoseGroupName) {
		this.diagnoseGroupName = diagnoseGroupName;
	}
}

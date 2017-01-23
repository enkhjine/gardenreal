package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "InsuranceConfig")
public class InsuranceConfig {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "Amount")
	private BigDecimal amount;
	
	/**
	 * 0 - Тохируулахгүй
	 * 1 - Тохируулна
	 * **/
	@Column(name = "HasAmbulance")
	private int hasAmbulance;
	
	public InsuranceConfig() {
		super();
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getHasAmbulance() {
		return hasAmbulance;
	}

	public void setHasAmbulance(int hasAmbulance) {
		this.hasAmbulance = hasAmbulance;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

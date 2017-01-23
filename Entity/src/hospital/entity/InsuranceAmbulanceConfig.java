package hospital.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "InsuranceAmbulanceConfig")
public class InsuranceAmbulanceConfig {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	/**
	 * 1 - Жил
	 * 2 - Улирал
	 * 3 - Сар
	 * */
	@Column(name = "Type")
	private int type;
	
	@Column(name = "FirstInspection")
	private int firstInspection;
	
	@Column(name = "ReInspection")
	private int reInspection;
	
	@Column(name = "Price")
	private BigDecimal price;
	
	public InsuranceAmbulanceConfig() {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getFirstInspection() {
		return firstInspection;
	}

	public void setFirstInspection(int firstInspection) {
		this.firstInspection = firstInspection;
	}

	public int getReInspection() {
		return reInspection;
	}

	public void setReInspection(int reInspection) {
		this.reInspection = reInspection;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

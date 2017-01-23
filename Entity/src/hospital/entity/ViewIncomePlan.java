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
@Table(name = "VIEW_IncomePLan")
public class ViewIncomePlan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId")
	private BigDecimal pkId;

	@Column(name = "SubOrganizationPkId")
	private BigDecimal subOrganizationPkId;
	
	@Column(name = "Price")
	private BigDecimal price;

	public ViewIncomePlan() {
		super();
	}
	
	public ViewIncomePlan(ViewIncomePlan incomePlan) {
		super();
		this.price = incomePlan.getPrice();
		this.pkId = incomePlan.getPkId();
		this.subOrganizationPkId = incomePlan.getSubOrganizationPkId();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
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
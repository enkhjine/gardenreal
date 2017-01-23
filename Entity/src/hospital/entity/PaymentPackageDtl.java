package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PaymentPackageDtl")
public class PaymentPackageDtl implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "PaymentPackagePkId")
	private BigDecimal paymentPackagePkId;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "TypePkId")
	private BigDecimal typePkId;
	
	@Column(name = "Amount")
	private BigDecimal amount;
	
	@Transient
	private String id;
	
	@Transient
	private String name;
	
	@Transient
	private String status;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getPaymentPackagePkId() {
		return paymentPackagePkId;
	}

	public void setPaymentPackagePkId(BigDecimal paymentPackagePkId) {
		this.paymentPackagePkId = paymentPackagePkId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getTypePkId() {
		return typePkId;
	}

	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}

	public BigDecimal getAmount() {
		if(amount == null) amount = BigDecimal.ZERO;
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if(BigDecimal.ZERO.compareTo(amount) == 0) return;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

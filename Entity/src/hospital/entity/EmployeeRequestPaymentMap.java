package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EmployeeRequestPaymentMap")
public class EmployeeRequestPaymentMap {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;
	
	@Column(name = "EmployeeRequestPkId")
	private BigDecimal employeeRequestPkId;
	
	public EmployeeRequestPaymentMap(){
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
	
	public BigDecimal getEmployeeRequestPkId() {
		return employeeRequestPkId;
	}
	
	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}
	
	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}
}

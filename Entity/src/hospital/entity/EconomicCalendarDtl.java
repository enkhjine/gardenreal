package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EconomicCalendarDtl")
public class EconomicCalendarDtl implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "HdrPkId")
	private BigDecimal hdrPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Transient
	private EconomicCalendarHdr calendarHdr;
	
	@Transient
	private Employee employee;
	
	public EconomicCalendarDtl(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getHdrPkId() {
		return hdrPkId;
	}

	public void setHdrPkId(BigDecimal hdrPkId) {
		this.hdrPkId = hdrPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public EconomicCalendarHdr getCalendarHdr() {
		return calendarHdr;
	}
	
	public void setCalendarHdr(EconomicCalendarHdr calendarHdr) {
		this.calendarHdr = calendarHdr;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}

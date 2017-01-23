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
@Table(name = "VIEW_Employee")
public class ViewEmployee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "FirstName")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "SubOrganizationPkId")
	private BigDecimal subOrganizationPkId;
	
	@Column(name = "SubOrganizationName")
	private String subOrganizationName;

	@Transient
	private BigDecimal amount;
	
	public ViewEmployee() {
		super();
	}
	
	public ViewEmployee(BigDecimal amount, ViewEmployee employee) {
		super();
		this.amount = amount;
		this.employeePkId = employee.getEmployeePkId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.subOrganizationName = employee.getSubOrganizationName();
		this.subOrganizationPkId = employee.getSubOrganizationPkId();
	}
	
	public ViewEmployee(BigDecimal amount, BigDecimal employeePkId, String firstName, String lastName, String subOrganizationName, BigDecimal subOrganizationPkId) {
		super();
		this.amount = amount;
		this.employeePkId = employeePkId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.subOrganizationName = subOrganizationName;
		this.subOrganizationPkId = subOrganizationPkId;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
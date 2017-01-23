package hospital.businessentity;

import hospital.entity.Employee;
import hospital.entity.Organization;
import hospital.entity.Users;

import java.io.Serializable;
import java.math.BigDecimal;

public class LoggedUser implements Serializable {
	
	private static final long serialVersionUID = -7317976082218869215L;
	
	private String username;
	private String password;
	private Organization organization;
	private Users user;
	private String usernameandcompanyname;
	private Employee employee;
	private BigDecimal employeePkId;
	private BigDecimal  suborganizationPkId;
	public LoggedUser(){
		super();
	}
	
	public LoggedUser(Users user, Organization organization){
		this.user = user;
		this.organization = organization;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUser(Users user) {
		this.user = user;
	}

	public String getUsernameandcompanyname() {
		usernameandcompanyname = "";
		if(user != null) usernameandcompanyname += user.getName() + " - ";
		if(organization != null) usernameandcompanyname += organization.getName();
		return usernameandcompanyname;
	}

	public void setUsernameandcompanyname(String usernameandcompanyname) {
		this.usernameandcompanyname = usernameandcompanyname;
	}
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employeePkId = employee.getPkId();
		this.suborganizationPkId = employee.getSubOrganizationPkId();
		this.employee = employee;
	}

	public BigDecimal getSuborganizationPkId() {
		return suborganizationPkId;
	}

	public void setSuborganizationPkId(BigDecimal suborganizationPkId) {
		this.suborganizationPkId = suborganizationPkId;
	}

}

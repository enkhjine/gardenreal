package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "StickyNoteType")
public class StickyNoteType implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "UserPkId")
	private BigDecimal userPkId;
	
	@Column(name = "BgColor")
	private String bgColorl;
	
	public BigDecimal getPkId() {
		return pkId;
	}
	
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public String getBgColorl() {
		return bgColorl;
	}
	
	public void setBgColorl(String bgColorl) {
		this.bgColorl = bgColorl;
	}
	
	public BigDecimal getUserPkId() {
		return userPkId;
	}
	
	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
	}
	
}

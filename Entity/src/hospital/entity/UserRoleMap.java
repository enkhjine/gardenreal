package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UserRoleMap")
public class UserRoleMap implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "UserPkId")
	private BigDecimal userPkId;
	

	@Column(name = "RolePkId")
	private BigDecimal rolePkId;


	public BigDecimal getPkId() {
		return pkId;
	}


	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}


	public BigDecimal getUserPkId() {
		return userPkId;
	}


	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
	}


	public BigDecimal getRolePkId() {
		return rolePkId;
	}


	public void setRolePkId(BigDecimal rolePkId) {
		this.rolePkId = rolePkId;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

	
	
	
	
}
package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RoleMenuMap")
public class RoleMenuMap implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId; 
	
	@Column(name = "RolePkId")
	private BigDecimal rolePkId;
	
	@Column(name = "MenuPkId")
	private BigDecimal menuPkId;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getRolePkId() {
		return rolePkId;
	}

	public void setRolePkId(BigDecimal rolePkId) {
		this.rolePkId = rolePkId;
	}

	public BigDecimal getMenuPkId() {
		return menuPkId;
	}

	public void setMenuPkId(BigDecimal menuPkId) {
		this.menuPkId = menuPkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

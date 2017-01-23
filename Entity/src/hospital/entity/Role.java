package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Role")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "ParentPkId")
	private BigDecimal parentPkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "SortString")
	private String sortString;
	
	@Transient
	private String status;
	
	@Transient
	private List<Menu> menus;
	
	@Transient
	private List<BigDecimal> menuPkIds;
	
	@Transient
	private int level;
	
	@Transient
	private String lName;
	
	@Transient
	private String menuNames;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getParentPkId() {
		return parentPkId;
	}

	public void setParentPkId(BigDecimal parentPkId) {
		this.parentPkId = parentPkId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSortString() {
		return sortString;
	}

	public void setSortString(String sortString) {
		this.sortString = sortString;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public List<Menu> getMenus() {
		if(menus == null) menus = new ArrayList<>();
		return menus;
	}
	
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public int getLevel() {
		return this.sortString.length();
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getlName() {
		String ln = "";
		for(int i = 4; i <= getLevel(); i = i + 3){
			ln = ln + "|--";
		}
		return ln + this.name;
	}
	
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	public List<BigDecimal> getMenuPkIds() {
		return menuPkIds;
	}
	
	public void setMenuPkIds(List<BigDecimal> menuPkIds) {
		this.menuPkIds = menuPkIds;
	}
	
	public String getMenuNames() {
		if(menuNames == null) menuNames = "";
		if(menuNames.length() > 100) menuNames = menuNames.substring(0, 100);
		return menuNames;
	}
	
	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}
	
}
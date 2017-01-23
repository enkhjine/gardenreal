package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Menu")
public class Menu implements Serializable{

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "Id")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Url")
	private String url;
	
	@Column(name = "Sort")
	private int sort;
	
	@Transient
	private BigDecimal rolePkId;
	
	public Menu(){
		super();
	}
	
	public Menu(BigDecimal rolePkId, String name){
		this.rolePkId = rolePkId;
		this.name = name;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
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

	public BigDecimal getRolePkId() {
		return rolePkId;
	}
	
	public void setRolePkId(BigDecimal rolePkId) {
		this.rolePkId = rolePkId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getSort() {
		return sort;
	}
	
	public void setSort(int sort) {
		this.sort = sort;
	}
	
}

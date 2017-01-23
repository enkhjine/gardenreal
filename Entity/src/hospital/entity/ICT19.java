package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "ICT19")
public class ICT19 implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId",length=18,nullable=false)
	private BigDecimal pkId;
	
	@Column(name="Id")
	private String id;
	
	@Column(name="NameEn")
	private String nameEn;
	
	@Column(name="NameMn")
	private String nameMn;
	
	@Transient
	private String status;
	
	public ICT19(){
	super();
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameMn() {
		return nameMn;
	}

	public void setNameMn(String nameMn) {
		this.nameMn = nameMn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}

package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Diagnose")
public class Diagnose implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "Id")
	private String id;
	
	@Column(name = "NameMn")
	private String nameMn;
	
	@Column(name = "NameEn")
	private String nameEn;
	
	@Column(name = "NameRu")
	private String nameRu;
	
	@Transient
	private String status;
	
	public Diagnose() {
		super();
	}
	
	public Diagnose(Diagnose diagnose, long count){
		this.pkId = diagnose.getPkId();
		this.id = diagnose.getId();
		this.nameMn = diagnose.getNameMn();
		this.nameEn = diagnose.getNameEn();
		this.nameRu = diagnose.getNameRu();
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
	
	public String getNameRu() {
		return nameRu;
	}
	
	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
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
	
}

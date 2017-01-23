package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Aimag")
public class Aimag implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;	
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "InsuranceCode")
	private String insuranceCode;
	
	@OneToMany(mappedBy = "aimag", cascade = CascadeType.ALL)
	private List<Soum> soums;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@XmlTransient
	public List<Soum> getSoums() {
		return soums;
	}
	
	public void setSums(List<Soum> soums) {
		this.soums = soums;
	}
	
	public String getInsuranceCode() {
		return insuranceCode;
	}
	
	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}
	
}

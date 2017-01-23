package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Measurement")
public class Measurement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
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

}

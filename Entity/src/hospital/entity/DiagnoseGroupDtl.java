package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "DiagnoseGroupDtl")
public class DiagnoseGroupDtl  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "DiagnoseGroupPkId")
	private BigDecimal diagnoseGroupPkId;
	
	@Column(name = "Char")
	private String character;
	
	@Column(name = "BeginNumber")
	private String beginNumber;
	
	@Column(name = "EndNumber")
	private String endNumber;
	
	@Transient
	private String status;
	
	public DiagnoseGroupDtl() {
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getDiagnoseGroupPkId() {
		return diagnoseGroupPkId;
	}

	public void setDiagnoseGroupPkId(BigDecimal diagnoseGroupPkId) {
		this.diagnoseGroupPkId = diagnoseGroupPkId;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getBeginNumber() {
		return beginNumber;
	}

	public void setBeginNumber(String beginNumber) {
		this.beginNumber = beginNumber;
	}

	public String getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(String endNumber) {
		this.endNumber = endNumber;
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

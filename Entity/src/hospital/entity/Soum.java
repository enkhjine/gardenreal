package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Soum")
public class Soum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "AimagPkId")
	private BigDecimal aimagPkId;
	
	@Column(name = "Sort")
	private int sort;
	
	@Column(name = "Name")
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "aimagPkId", insertable = false, updatable = false)
	private Aimag aimag;
	
	public Soum()
	{
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getAimagPkId() {
		return aimagPkId;
	}

	public void setAimagPkId(BigDecimal aimagPkId) {
		this.aimagPkId = aimagPkId;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Aimag getAimag() {
		return aimag;
	}
	
	public void setAimag(Aimag aimag) {
		this.aimag = aimag;
	}
	
}

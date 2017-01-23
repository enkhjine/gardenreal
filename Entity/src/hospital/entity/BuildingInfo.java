package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BuildingInfo")
public class BuildingInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;	
	
	/**
	 * 0 - BLOCK
	 * 1 - DAVHAR
	 * 2 - JIGUUR
	 * */
	@Column(name = "Type")
	private byte type;
	
	@Column(name = "Name")
	private String name;
	
	public BuildingInfo(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	/**
	 * 0 - BLOCK
	 * 1 - DAVHAR
	 * 2 - JIGUUR
	 * */
	public byte getType() {
		return type;
	}

	/**
	 * 0 - BLOCK
	 * 1 - DAVHAR
	 * 2 - JIGUUR
	 * */
	public void setType(byte type) {
		this.type = type;
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
	
	
}

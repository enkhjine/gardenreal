package hospital.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "View_ConstantArrange")
public class View_ConstantArrange {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "NameMn")
	private String nameMn;

	@Column(name = "NameEn")
	private String nameEn;

	@Column(name = "GroupId")
	private String groupId;

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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getNameMn() {
		return nameMn;
	}

	public void setNameMn(String nameMn) {
		this.nameMn = nameMn;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
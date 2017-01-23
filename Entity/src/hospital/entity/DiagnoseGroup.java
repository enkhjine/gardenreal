package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "DiagnoseGroup")
public class DiagnoseGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "CreatedBy")
	private BigDecimal createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;
	
	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;
	
	@Transient
	private List<DiagnoseGroupDtl> listDiagnoseGroupDtl;
	
	@Transient
	private String status;
	
	@Transient
	private String diagnoseGroupDtlName;
	
	public DiagnoseGroup() {
		super();
	}

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

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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
	
	public List<DiagnoseGroupDtl> getListDiagnoseGroupDtl() {
		if(listDiagnoseGroupDtl == null) listDiagnoseGroupDtl = new ArrayList<>();
		return listDiagnoseGroupDtl;
	}
	
	public void setListDiagnoseGroupDtl(
			List<DiagnoseGroupDtl> listDiagnoseGroupDtl) {
		this.listDiagnoseGroupDtl = listDiagnoseGroupDtl;
		diagnoseGroupDtlName = "";
		for (DiagnoseGroupDtl diagnoseGroupDtl : listDiagnoseGroupDtl) {
			diagnoseGroupDtlName += diagnoseGroupDtl.getCharacter() + "." + diagnoseGroupDtl.getBeginNumber() + " - " + diagnoseGroupDtl.getCharacter() + "." + diagnoseGroupDtl.getEndNumber();
			if(listDiagnoseGroupDtl.indexOf(diagnoseGroupDtl) != listDiagnoseGroupDtl.size() - 1) diagnoseGroupDtlName += " ,";
		}
	}
	
	public String getDiagnoseGroupDtlName() {
		return diagnoseGroupDtlName;
	}
	
	public void setDiagnoseGroupDtlName(String diagnoseGroupDtlName) {
		this.diagnoseGroupDtlName = diagnoseGroupDtlName;
	}
	
	
}

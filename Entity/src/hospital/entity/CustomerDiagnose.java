package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "CustomerDiagnose")
public class CustomerDiagnose implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;
	
	@Column(name = "InspectionPkId")
	private BigDecimal inspectionPkId;
	
	@Column(name = "DiagnosePkId")
	private BigDecimal diagnosePkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Column(name = "Organ")
	private String organ;
	
	@Column(name = "Date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	/* Урьдчилсан онош - 0
	 * Баталгаажсан онош - 1*/
	@Column(name= "Type")
	private int type;
	
	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;
	
	@Transient
	private String id;
	
	@Transient
	private String nameMn;
	
	@Transient
	private String nameEn;
	
	@Transient
	private String nameRu;
	
	@Transient
	private String status;
	
	public CustomerDiagnose() {
		super();
	}
	
	public CustomerDiagnose(CustomerDiagnose customerDiagnose, Diagnose diagnose) {
		super();
		this.pkId = customerDiagnose.getPkId();
		this.inspectionPkId = customerDiagnose.getInspectionPkId();
		this.diagnosePkId = customerDiagnose.getDiagnosePkId();
		this.employeePkId = customerDiagnose.getEmployeePkId();
		this.date = customerDiagnose.getDate();
		this.type = customerDiagnose.getType();
		this.createdBy = customerDiagnose.getCreatedBy();
		this.createdDate = customerDiagnose.getCreatedDate();
		this.updatedBy = customerDiagnose.getUpdatedBy();
		this.updatedDate = customerDiagnose.getUpdatedDate();
		this.organ = customerDiagnose.getOrgan();
		
		this.id = diagnose.getId();
		this.nameEn = diagnose.getNameEn();
		this.nameMn = diagnose.getNameMn();
		this.nameRu = diagnose.getNameRu();
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

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public BigDecimal getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BigDecimal getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(BigDecimal updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getNameMn() {
		if(nameMn == null)
			nameMn = "";
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

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getDiagnosePkId() {
		return diagnosePkId;
	}

	public void setDiagnosePkId(BigDecimal diagnosePkId) {
		this.diagnosePkId = diagnosePkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getDateString (Date date)
	{
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public String getTypeString() {
		if(type == 0)
			return "IMP";
		return "PRI";
	}

	public String getOrgan() {
		return organ;
	}

	public void setOrgan(String organ) {
		this.organ = organ;
	}
}
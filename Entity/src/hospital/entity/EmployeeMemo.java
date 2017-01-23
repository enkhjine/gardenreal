package hospital.entity;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.PaymentInspection;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
@Table(name = "EmployeeMemo")
public class EmployeeMemo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Note")
	private String note;
	//0 - Private  1 - Public
	@Column(name = "NoteType")
	private int noteType;
	
	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;
	
	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CreatedDate")
	private Date createdDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdatedDate")
	private Date updatedDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Transient
	private String doctorName;
	
	@Transient
	private String status;
	
	public EmployeeMemo() {
		super();
	}
	
	public EmployeeMemo(EmployeeMemo memo, String firstName, String lastName) {
		super();
		this.pkId = memo.getPkId();
		this.note = memo.getNote();
		this.noteType = memo.getNoteType();
		this.customerPkId = memo.getCustomerPkId();
		this.employeePkId = memo.getEmployeePkId();
		this.createdBy = memo.getCreatedBy();
		this.createdDate = memo.getCreatedDate();
		this.updatedBy = memo.getUpdatedBy();
		this.updatedDate = memo.getUpdatedDate();
		if(lastName.length() > 0)
			this.doctorName = lastName.substring(0, 1);
		this.doctorName = this.doctorName + "." + firstName; 
	}
	 
	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getNoteType() {
		return noteType;
	}

	public void setNoteType(int noteType) {
		this.noteType = noteType;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	
	public String getStringDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return (ft.format(createdDate));
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
}
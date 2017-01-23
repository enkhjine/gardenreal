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
@Table(name = "LoginDialog")
public class LoginDialog implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "Comments")
	private String comments;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate")
	private Date beginDate;
    
	@Transient
	private String  status;

	@Transient
	private String beginDateStr;
	
	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Date getBeginDate() {
		if(beginDate == null) beginDate = new Date();
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBeginDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		beginDateStr = sdfr.format(getBeginDate());
		return beginDateStr;
	}

	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}

	public LoginDialog(BigDecimal pkId, String comments, Date beginDate, String status, String beginDateStr) {
		super();
		this.pkId = pkId;
		this.comments = comments;
		this.beginDate = beginDate;
		this.status = status;
		this.beginDateStr = beginDateStr;
	}

	public LoginDialog(BigDecimal pkId, String comments, Date beginDate, String status) {
		super();
		this.pkId = pkId;
		this.comments = comments;
		this.beginDate = beginDate;
		this.status = status;
	}

	public LoginDialog(BigDecimal pkId, String comments, Date beginDate) {
		super();
		this.pkId = pkId;
		this.comments = comments;
		this.beginDate = beginDate;
	}
	
	public LoginDialog(String comments, Date beginDate) {
		super();
		this.comments = comments;
		this.beginDate = beginDate;
	}
	
	public LoginDialog(String comments) {
		super();
		this.comments = comments;
	}

	public LoginDialog() {
		super();
		// TODO Auto-generated constructor stub
	}	
}

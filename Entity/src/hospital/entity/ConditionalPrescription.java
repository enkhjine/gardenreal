package hospital.entity;

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
@Table(name = "ConditionalPrescription")
public class ConditionalPrescription implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Id")
	private String id;

	@Column(name = "StatementId")
	private String statementId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Price")
	private BigDecimal price;

	@Column(name = "UsageDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date usageDate;

	@Column(name = "CreatedBy")
	private BigDecimal createdBy;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "UpdatedBy")
	private BigDecimal updatedBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	@Column(name = "Cost")
	private BigDecimal cost; 

	@Transient
	private String status;
	
	@Transient
	private long dtlCount;
	
	@Transient
	private List<ConditionalPrescriptionDtl> dtls;

	public ConditionalPrescription() {
		super();
	}

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

	public String getStatementId() {
		return statementId;
	}

	public void setStatementId(String statementId) {
		this.statementId = statementId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getUsageDate() {
		return usageDate;
	}

	public void setUsageDate(Date usageDate) {
		this.usageDate = usageDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getDtlCount() {
		return dtlCount;
	}

	public void setDtlCount(long dtlCount) {
		this.dtlCount = dtlCount;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public ConditionalPrescription(BigDecimal pkId, String id,
			String statementId, String name, BigDecimal price, Date usageDate,
			BigDecimal createdBy, Date createdDate, BigDecimal updatedBy,
			Date updatedDate, BigDecimal cost,long dtlCount) {
		super();
		this.pkId = pkId;
		this.id = id;
		this.statementId = statementId;
		this.name = name;
		this.price = price;
		this.usageDate = usageDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.cost = cost;
		this.dtlCount = dtlCount;
	}
	
	public String getDateString (Date date)
	{
		if(date == null) date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public List<ConditionalPrescriptionDtl> getDtls() {
		if(dtls == null) dtls = new ArrayList<>();
		return dtls;
	}
	
	public void setDtls(List<ConditionalPrescriptionDtl> dtls) {
		this.dtls = dtls;
	}
	
}

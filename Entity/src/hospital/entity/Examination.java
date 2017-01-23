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
@Table(name = "Examination")
public class Examination implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "Name")
	private String name;

	@Column(name = "ExaminationTypePkId")
	private BigDecimal examinationTypePkId;

	@Column(name = "RoomNumber")
	private String roomNumber;

	@Column(name = "IsActive")
	private byte isActive;
	
	@Column(name ="Id")
	private String id;

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

	@Column(name = "ExaminationTemplatePkId")
	private BigDecimal examinationTemplatePkId;

	@Transient
	private boolean hasDtl;

	@Transient
	private boolean active;

	@Transient
	private BigDecimal price;

	@Transient
	private String examinationTypeName;

	@Transient
	private String status;

	@Transient
	private boolean selected;

	@Transient
	private Date beginDate;

	@Transient
	private long countDoneRequest;

	@Transient
	private long countTempRequest;

	@Transient
	private long countActiveRequest;

	@Transient
	private List<ExaminationDtl> examinationDtls;
	
	@Transient
	private String description;
	public Examination() {
		super();
	}
	public Examination(String ename ,String description){
		this.name  = ename;
		this.description  = description;
	}
	public Examination(Examination exa, String examinationTypeName,
			BigDecimal price) {
		this.pkId = exa.getPkId();
		this.name = exa.getName();
		this.examinationTypePkId = exa.getExaminationTypePkId();
		this.roomNumber = exa.getRoomNumber();
		this.isActive = exa.getIsActive();
		this.examinationTemplatePkId = exa.getExaminationTemplatePkId();
		this.examinationTypeName = examinationTypeName;
		this.price = price;
		this.id = exa.getId();

	}
	
	public Examination(Examination exa,  long countDoneRequest) {
		this.pkId = exa.getPkId();
		this.name = exa.getName();
		this.examinationTypePkId = exa.getExaminationTypePkId();
		this.roomNumber = exa.getRoomNumber();
		this.isActive = exa.getIsActive();
		this.examinationTemplatePkId = exa.getExaminationTemplatePkId();
		this.countDoneRequest = countDoneRequest;
		
		
		

	}
	
	public Examination(String name){
		this.name=name;
	}

	public Examination(BigDecimal pkId, String name,
			BigDecimal examinationTypePkId, String roomNumber, byte isActive,
			String id, BigDecimal createdBy, Date createdDate,
			BigDecimal updatedBy, Date updatedDate,
			BigDecimal examinationTemplatePkId, BigDecimal price) {
		super();
		this.pkId = pkId;
		this.name = name;
		this.examinationTypePkId = examinationTypePkId;
		this.roomNumber = roomNumber;
		this.isActive = isActive;
		this.id = id;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.examinationTemplatePkId = examinationTemplatePkId;
		this.price = price;
	}
	
	public Examination(BigDecimal pkId, String name,
			BigDecimal examinationTypePkId, String roomNumber, byte isActive,
			String id, BigDecimal createdBy, Date createdDate,
			BigDecimal updatedBy, Date updatedDate,
			BigDecimal examinationTemplatePkId) {
		super();
		this.pkId = pkId;
		this.name = name;
		this.examinationTypePkId = examinationTypePkId;
		this.roomNumber = roomNumber;
		this.isActive = isActive;
		this.id = id;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.examinationTemplatePkId = examinationTemplatePkId;
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

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
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

	public BigDecimal getExaminationTypePkId() {
		return examinationTypePkId;
	}

	public void setExaminationTypePkId(BigDecimal examinationTypePkId) {
		this.examinationTypePkId = examinationTypePkId;
	}

	public boolean isHasDtl() {
		return hasDtl;
	}

	public void setHasDtl(boolean hasDtl) {
		this.hasDtl = hasDtl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		if(price == null) price = BigDecimal.ZERO;
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<ExaminationDtl> getExaminationDtls() {
		if(examinationDtls == null) examinationDtls = new ArrayList<>();
		return examinationDtls;
	}

	public void setExaminationDtls(List<ExaminationDtl> examinationDtls) {
		this.examinationDtls = examinationDtls;
		calculatePrice();
	}

	public String getExaminationTypeName() {
		return examinationTypeName;
	}

	public void setExaminationTypeName(String examinationTypeName) {
		this.examinationTypeName = examinationTypeName;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public long getCountDoneRequest() {
		return countDoneRequest;
	}

	public void setCountDoneRequest(long countDoneRequest) {
		this.countDoneRequest = countDoneRequest;
	}

	public long getCountTempRequest() {
		return countTempRequest;
	}

	public void setCountTempRequest(long countTempRequest) {
		this.countTempRequest = countTempRequest;
	}

	public long getCountActiveRequest() {
		return countActiveRequest;
	}

	public void setCountActiveRequest(long countActiveRequest) {
		this.countActiveRequest = countActiveRequest;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Examination(BigDecimal pkId, String name, long countDoneRequest,
			long countTempRequest, long countActiveRequest) {
		super();
		this.pkId = pkId;
		this.name = name;
		this.countDoneRequest = countDoneRequest;
		this.countTempRequest = countTempRequest;
		this.countActiveRequest = countActiveRequest;
	}

	public String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public BigDecimal getExaminationTemplatePkId() {
		return examinationTemplatePkId;
	}

	public void setExaminationTemplatePkId(BigDecimal examinationTemplatePkId) {
		this.examinationTemplatePkId = examinationTemplatePkId;
	}

	public Date getBeginDate() {
		return beginDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public void calculatePrice(){
		if(getExaminationDtls() != null && getExaminationDtls().size() > 0){
			this.price = BigDecimal.ZERO;
			for(ExaminationDtl dtl : getExaminationDtls()){
				this.price = this.price.add(dtl.getPrice());
			}
		}
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
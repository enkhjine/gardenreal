package garden.entity;

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

import garden.businessentity.Tool;

@Entity
@Table(name = "OrtsSize")
public class OrtsSize implements Serializable{	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Size")
	private BigDecimal size;
	
	@Column(name = "ParentPkId")
	private BigDecimal parentPkId;
	
	@Column(name = "OrderStr")
	private String orderStr;
	
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
	private String status;
	
	@Transient
	private String pStr;
	
	@Transient
	private List<BigDecimal> parentPkIds;
	
	@Transient
	private List<String> parentNames;
	
	@Transient
	private String parentNamesStr;
	
	public OrtsSize(){
		super();
		this.status = Tool.ADDED;
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
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getSize() {
		return size;
	}

	public void setSize(BigDecimal size) {
		this.size = size;
	}

	public BigDecimal getParentPkId() {
		return parentPkId;
	}

	public void setParentPkId(BigDecimal parentPkId) {
		this.parentPkId = parentPkId;
	}

	public String getOrderStr() {
		return orderStr;
	}

	public void setOrderStr(String orderStr) {
		this.orderStr = orderStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getpStr() {
		if(pStr == null || pStr.length() < 1){
			pStr = "";
			int len = this.orderStr.length() - 3;
			while(len > 0){
				//pStr += "&nbsp;&nbsp;&nbsp;";
				pStr += "---";
				len -= 3;
			}
		}
		return pStr;
	}
	
	public void setpStr(String pStr) {
		this.pStr = pStr;
	}
	
	public List<BigDecimal> getParentPkIds() {
		if(parentPkIds == null) {
			parentPkIds = new ArrayList<>();
		}
		return parentPkIds;
	}
	
	public void setParentPkIds(List<BigDecimal> parentPkIds) {
		this.parentPkIds = parentPkIds;
	}
	
	public List<String> getParentNames() {
		if(parentNames == null) {
			parentNames = new ArrayList<>();
		}
		return parentNames;
	}
	
	public void setParentNames(List<String> parentNames) {
		this.parentNames = parentNames;
	}
	
	public String getParentNamesStr() {
		if(parentNamesStr == null || parentNamesStr.length() < 1) {
			parentNamesStr = "";
			for (String name : getParentNames()) {
				parentNamesStr += name + " -> ";
			}
			parentNamesStr += this.name;
		}
		return parentNamesStr;
	}
	
	public void setParentNamesStr(String parentNamesStr) {
		this.parentNamesStr = parentNamesStr;
	}

}

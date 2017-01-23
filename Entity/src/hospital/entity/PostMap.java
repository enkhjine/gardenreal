package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="PostMap")
public class PostMap implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId" ,length=18,nullable=false)
	private  BigDecimal pkId;
	
	@Column(name="PostPkId",length=18,nullable=false)
	private  BigDecimal postPkId;
	
	@Column(name="ReceiverEmployeePkId",length=18,nullable =false)
	private  BigDecimal receiverEmployeePkId;
	
	@Transient
	String status;
	
	public  PostMap(){
		super();
	}
	
	public PostMap(BigDecimal pkid,BigDecimal postid,BigDecimal recieverpkid)
	{
		 this.pkId=pkid;
		 this.postPkId=pkid;
		 this.receiverEmployeePkId= recieverpkid;
	}
	public BigDecimal getPkId() {
		return pkId;
	}
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	public BigDecimal getPostPkId() {
		return postPkId;
	}
	public void setPostPkId(BigDecimal postPkId) {
		this.postPkId = postPkId;
	}
	public BigDecimal getReceiverEmployeePkId() {
		return receiverEmployeePkId;
	}
	public void setReceiverEmployeePkId(BigDecimal receiverEmployeePkId) {
		this.receiverEmployeePkId = receiverEmployeePkId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
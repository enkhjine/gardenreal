package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
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
@Table(name="Post")
public class Post  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="PkId" ,length=18,nullable=false)
	private  BigDecimal pkId;
	
	@Column(name="SenderEmployeePkId" ,length=18,nullable=false)	
	private BigDecimal  senderEmployeePkId;
	
	@Column(name = "Post")
	private  String post;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CreateDate")
	private Date createDate; 
	
	@Transient
	String status;
	
	@Transient
	private String employeename;
	
	@Transient  
	private long countpost;
	
	@Transient  
	private String firstname;
	
	@Transient
	private  long  count;
	
	@Transient 
	private String username;
	
	@Transient
	private BigDecimal  postpkid;
	
	@Transient  
	private BigDecimal  receiverEmployeePkId;
	
	@Transient
	private  BigDecimal  postMaPkId;
	
	@Transient
	private String image;
	
	public Post() {
		super();
	}
	
	public Post(BigDecimal  pkid, BigDecimal senderEmployee,String post){
		this.pkId= pkid;
		this.senderEmployeePkId=senderEmployee;
		this.post=post;
	}
	public Post(String post,long reDecimal,Date  d,String names,BigDecimal senderpkid,
			BigDecimal pkid,BigDecimal postpkid,BigDecimal pmid){
		this.post=post;
		this.countpost  =  reDecimal;
		this.createDate  =d;
		this.firstname  =  names;
		this.senderEmployeePkId  = senderpkid;
		this.pkId  =pkid;
		this.postpkid  = postpkid;
		this.postMaPkId =pmid;
	}  
	public Post(String post,Date  d,String names,BigDecimal senderpkid,BigDecimal pkid,
			BigDecimal postpkid,BigDecimal pmid){
		this.post=post;
		this.createDate  =d;
		this.firstname  =  names;
		this.senderEmployeePkId  = senderpkid;
		this.pkId  =pkid;
		this.postpkid  = postpkid;
		this.postMaPkId =pmid;
	}  
	public Post(String name,Date d){
		 this.employeename  = name;
		 this.createDate  =d;
	}
	public Post(String fname ,Date cdate,String p,String img,BigDecimal postPkId,
			BigDecimal pkId,BigDecimal senderPkid){
		this.employeename =fname;
		this.createDate =  cdate;
		this.post=p;
		this.image =img;
		this.pkId  =  postPkId;
		this.postpkid = pkId;
		this.senderEmployeePkId=senderPkid;
	}
	public Post(String names){
		this.username  =names;
	}
	public  Post(BigDecimal pkId){
		this.receiverEmployeePkId  =pkId;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getPkId() {
		return pkId;
	}
	
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	public BigDecimal getSenderEmployeePkId() {
		return senderEmployeePkId;
	}
	public void setSenderEmployeePkId(BigDecimal senderEmployeePkId) {
		this.senderEmployeePkId = senderEmployeePkId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeename() {
		return employeename;
	}

	public void setEmployeename(String employeename) {
		this.employeename = employeename;
	}


	public long getCountpost() {
		return countpost;
	}

	public void setCountpost(long countpost) {
		this.countpost = countpost;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreateDate() {
		if (createDate==null) {
			createDate  =  new Date();
		}
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public BigDecimal getPostpkid() {
		return postpkid;
	}

	public void setPostpkid(BigDecimal postpkid) {
		this.postpkid = postpkid;
	}

	public BigDecimal getPostMaPkId() {
		return postMaPkId;
	}

	public void setPostMaPkId(BigDecimal postMaPkId) {
		this.postMaPkId = postMaPkId;
	}
	
	public String getCreatedDateStr(){
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return df.format(getCreateDate());
	}

	public BigDecimal getReceiverEmployeePkId() {
		return receiverEmployeePkId;
	}

	public void setReceiverEmployeePkId(BigDecimal receiverEmployeePkId) {
		this.receiverEmployeePkId = receiverEmployeePkId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
		
}
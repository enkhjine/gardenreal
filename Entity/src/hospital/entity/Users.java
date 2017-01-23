package hospital.entity;

import hospital.businessentity.Tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Users")
public class Users implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;
	
	@Column(name = "Id")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Password")
	private String password;	
	
	@Column(name = "IsActive")
	private byte isActive;
	
	@Column(name = "Image")
	private String image;
	
	@Transient
	private String status;
	
	@Transient
	private List<BigDecimal> rolePkIds;
	
	@Transient
	private String confirmPassword;
	
	@Transient
	private Organization organization;
	
	@Transient
	private boolean hasPasswordChanged;
	
	public Users(){
		super();
	}
	
	public Users(BigDecimal pkId, BigDecimal organizationPkId, String id,
			String name, String password, byte isActive, Organization organization) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.id = id;
		this.name = name;
		this.password = password;
		this.confirmPassword = password;
		this.isActive = isActive;
		this.organization = organization;
		this.hasPasswordChanged = false;
	}
	
	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal organizationPkId) {
		this.organizationPkId = organizationPkId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(!password.equals(this.password)) setHasPasswordChanged(true);
		this.password = password;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
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
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<BigDecimal> getRolePkIds() {
		return rolePkIds;
	}
	
	public void setRolePkIds(List<BigDecimal> rolePkIds) {
		this.rolePkIds = rolePkIds;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public String getImage() {
		if(image == null || image.length() < 10) image = Tool.UserDefaultImage;
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public boolean isHasPasswordChanged() {
		return hasPasswordChanged;
	}
	
	public void setHasPasswordChanged(boolean hasPasswordChanged) {
		this.hasPasswordChanged = hasPasswordChanged;
	}

}
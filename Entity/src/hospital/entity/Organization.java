package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Organization")
public class Organization implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "Email")
	private String email;
	
	@Column(name = "PhoneNumber")
	private String phoneNumber;
	
	@Column(name = "AimagPkId")
	private BigDecimal aimagPkId;
	
	@Column(name = "sumPkId")
	private BigDecimal sumPkId;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Logo")
	private String logo;
	
	@Column(name = "Stmp")
	private String stmp;
	
	/**
	 * 0 Эрүүл мэндийн даатгалаар үйлчилдэггүй
	 * 1 Эрүүл мэндийн даатгалаар үйлчилдэг
	 * **/
	@Column(name = "IsInsurance")
	private byte isInsurance;
	
	@Column(name = "IsActive")
	private byte isActive;
	
	@Transient
	private String status;
	
	@Transient
	private boolean insurance;
	
	@Transient
	private boolean active;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public BigDecimal getAimagPkId() {
		return aimagPkId;
	}

	public void setAimagPkId(BigDecimal aimagPkId) {
		this.aimagPkId = aimagPkId;
	}

	public BigDecimal getSumPkId() {
		return sumPkId;
	}

	public void setSumPkId(BigDecimal sumPkId) {
		this.sumPkId = sumPkId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getStmp() {
		return stmp;
	}

	public void setStmp(String stmp) {
		this.stmp = stmp;
	}

	public byte getIsInsurance() {
		return isInsurance;
	}

	public void setIsInsurance(byte isInsurance) {
		this.isInsurance = isInsurance;
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
	
	public boolean isInsurance() {
		return isInsurance == 1;
	}
	
	public void setInsurance(boolean insurance) {
		this.isInsurance = (byte)(insurance ? 1 : 0);
	}
	
	public byte getIsActive() {
		return isActive;
	}
	
	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return isActive == 1;
	}
	
	public void setActive(boolean active) {
		this.isActive = (byte)(active ? 1 : 0);
	}

}

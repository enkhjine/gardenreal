package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "VIEW_Customer")
public class ViewCustomer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "OrganizationPkId")
	private BigDecimal organizationPkId;

	@Column(name = "CardNumber")
	private String cardNumber;

	@Column(name = "ClanName")
	private String clanName;

	@Column(name = "LastName")
	private String lastName;

	@Column(name = "FirstName")
	private String firstName;

	@Column(name = "RegNumber")
	private String regNumber;

	@Column(name = "Age")
	private int age;

	/***
	 * 0 - Эм
	 * 1 - Эр
	 * */
	@Column(name = "Gender")
	private int gender;

	@Column(name = "AimagPkId")
	private BigDecimal aimagPkId;

	@Column(name = "SumPkId")
	private BigDecimal sumPkId;

	@Column(name = "District")
	private String district;

	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@Column(name = "Building")
	private String building;

	@Column(name = "SocialStatus")
	private String socialStatus;

	@Column(name = "Job")
	private BigDecimal job;

	@Column(name = "Rank")
	private String rank;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "StartDate")
	private Date startDate;

	@Column(name = "UserPkId")
	private BigDecimal userPkId;

	@Transient
	private String customerImage;

	public ViewCustomer() {
		super();
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

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerImage() {
		return customerImage;
	}

	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public String getLastName() {
		if(lastName == null || lastName.length() < 1){
			lastName = "н";
		}
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getRegNumber() {
		return regNumber == null ? "" : regNumber.toUpperCase();
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/***
	 * 0 - Эм
	 * 1 - Эр
	 * */
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
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

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getSocialStatus() {
		return socialStatus;
	}

	public void setSocialStatus(String socialStatus) {
		this.socialStatus = socialStatus;
	}

	public BigDecimal getJob() {
		return job;
	}

	public void setJob(BigDecimal job) {
		this.job = job;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public BigDecimal getUserPkId() {
		return userPkId;
	}

	public void setUserPkId(BigDecimal userPkId) {
		this.userPkId = userPkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

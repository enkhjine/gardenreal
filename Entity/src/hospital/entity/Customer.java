package hospital.entity;

import hospital.businessentity.Tool;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import sun.misc.BASE64Decoder;

@Entity
@Table(name = "Customer")
public class Customer implements Serializable {

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

	@Column(name = "RegNumber", unique = true)
	private String regNumber;

	/***
	 * 0 - Tool.WOMAN 1 - Tool.MAN
	 */
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

	@Column(name = "ArrangePkId")
	private BigDecimal arrangePkId;

	@Column(name = "InsuranceAimagPkId")
	private BigDecimal insuranceAimagPkId;

	@Column(name = "InsuranceSumPkId")
	private BigDecimal insuranceSumPkId;

	@Column(name = "InsuranceTypePkId")
	private BigDecimal insuranceTypePkId;

	@Column(name = "FamilyMemberName")
	private String familyMemberName;

	@Column(name = "FamilyRelationTypePkId")
	private BigDecimal familyMemberTypePkId;

	@Column(name = "FamilyPhoneNumber")
	private String familyPhoneNumber;

	@Column(name = "Email")
	private String email;

	@Column(name = "CitizenPkId")
	private BigDecimal citizenPkId;

	@Column(name = "BirthDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;

	/***
	 * 0 - Mongol 1 - Gadaad
	 */
	@Column(name = "IsLocal")
	private int isLocal;

	@Column(name = "InsuranceCardNumber")
	private String insuranceCardNumber;

	@Column(name = "ValidDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date validDate;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	private List<CustomerImage> listCustomerImage;

	@Transient
	private String customerImage;

	@Transient
	String status;

	@Transient
	String dateString;

	@Transient
	private int month;

	@Transient
	private long requestCount;

	@Transient
	private long requestFrailCount;

	@Transient
	private BigDecimal executoryCount;

	@Transient
	private long unreliableCount;

	@Transient
	String uldegdelStatus;

	@Transient
	int age;

	@Transient
	private String genderString;

	@Transient
	private String citizenName;

	@Transient
	private String relationName;

	@Transient
	private String genderStr;

	@Transient
	private char genderChar;

	@Transient
	private String insuranceType;

	@Transient
	private String address;
	
	@Transient
	private String arrangeName;
	
	@Transient
	private String aimagName;
	
	@Transient 
	private String sumName;
	
	@Transient 
	private Date bDate;

	public Customer() {
		super();
	}

	public Customer(BigDecimal pkId, BigDecimal organizationPkId, String cardNumber, String clanName, String lastName,
			String firstName, String regNumber, int gender, BigDecimal aimagPkId, BigDecimal sumPkId, String district,
			String phoneNumber, String building, String socialStatus, BigDecimal job, String rank, Date startDate,
			BigDecimal userPkId, BigDecimal arrangePkId, BigDecimal insuranceAimagPkId, BigDecimal insuranceSumPkId,
			BigDecimal insuranceTypePkId, String familyMemberName, BigDecimal familyMemberTypePkId,
			String familyPhoneNumber, String email, BigDecimal citizenPkId, Date birthDate, int isLocal,
			String citizenName, String relationName) {
		super();
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.cardNumber = cardNumber;
		this.clanName = clanName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.gender = gender;
		this.aimagPkId = aimagPkId;
		this.sumPkId = sumPkId;
		this.district = district;
		this.phoneNumber = phoneNumber;
		this.building = building;
		this.socialStatus = socialStatus;
		this.job = job;
		this.rank = rank;
		this.startDate = startDate;
		this.userPkId = userPkId;
		this.arrangePkId = arrangePkId;
		this.insuranceAimagPkId = insuranceAimagPkId;
		this.insuranceSumPkId = insuranceSumPkId;
		this.insuranceTypePkId = insuranceTypePkId;
		this.familyMemberName = familyMemberName;
		this.familyMemberTypePkId = familyMemberTypePkId;
		this.familyPhoneNumber = familyPhoneNumber;
		this.email = email;
		this.citizenPkId = citizenPkId;
		this.birthDate = birthDate;
		this.isLocal = isLocal;
		this.citizenName = citizenName;
		this.relationName = relationName;
	}

	public Customer(BigDecimal pkId, BigDecimal organizationPkId, String cardNumber, String clanName, String lastName,
			String firstName, String regNumber, int age, int gender, BigDecimal aimagPkId, BigDecimal sumPkId,
			String district, String phoneNumber, String building, String socialStatus, BigDecimal job, String rank,
			Date startDate, BigDecimal userPkId) {
		this.pkId = pkId;
		this.organizationPkId = organizationPkId;
		this.cardNumber = cardNumber;
		this.clanName = clanName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.regNumber = regNumber;
		this.gender = gender;
		this.aimagPkId = aimagPkId;
		this.sumPkId = sumPkId;
		this.district = district;
		this.phoneNumber = phoneNumber;
		this.building = building;
		this.socialStatus = socialStatus;
		this.job = job;
		this.rank = rank;
		this.startDate = startDate;
		this.userPkId = userPkId;
	}

	public Customer(Customer c , String insuranceType, String aimagName, String sumName, String arrangeName) {
		super();
		this.pkId=c.getPkId();
		this.organizationPkId=c.getOrganizationPkId();
		this.cardNumber=c.getCardNumber();
		this.clanName=c.getClanName();
		this.lastName=c.getLastName();
		this.firstName=c.getFirstName();
		this.regNumber=c.getRegNumber();
		this.gender=c.getGender();
		this.aimagPkId= c.getAimagPkId();
		this.sumPkId=c.getSumPkId();
		this.district=c.getDistrict();
		this.phoneNumber= c.getPhoneNumber();
		this.building=c.getBuilding();
		this.socialStatus=c.getSocialStatus();
		this.job=c.getJob();
		this.rank=c.getRank();
		this.startDate=c.getStartDate();
		this.userPkId=c.getUserPkId();
		this.arrangePkId=c.getArrangePkId();
		this.insuranceAimagPkId=c.getInsuranceAimagPkId();
		this.insuranceSumPkId=c.getInsuranceSumPkId();
		this.insuranceTypePkId=c.getInsuranceTypePkId();
		this.familyMemberName=c.getFamilyMemberName();
		this.familyMemberTypePkId=c.getFamilyMemberTypePkId();
		this.familyPhoneNumber=c.getFamilyPhoneNumber();
		this.email=c.getEmail();
		this.citizenPkId=c.getCitizenPkId();
		this.birthDate=c.getBirthDate();
		this.isLocal=c.getIsLocal();
		this.insuranceCardNumber=c.getInsuranceCardNumber();
		this.validDate=c.getValidDate();
		this.insuranceType = insuranceType;
		this.aimagName = aimagName;
		this.sumName = sumName;
		this.arrangeName = arrangeName;


	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getClanName() {
		return clanName;
	}

	public void setClanName(String clanName) {
		this.clanName = clanName;
	}

	public String getLastName() {
		if(lastName == null || lastName.length() < 1) lastName = "н";
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
		if(Tool.isRegNumber(regNumber)) {
			this.regNumber = regNumber;
		}else {
			this.regNumber = "";
		}
	}

	/***
	 * 0 - Эр 1 - Эм
	 */
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
		if (startDate == null)
			startDate = new Date();
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

	public String getDateString() {
		dateString = new SimpleDateFormat("yyyy-MM-dd").format(getStartDate());
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public BigDecimal getOrganizationPkId() {
		return organizationPkId;
	}

	public void setOrganizationPkId(BigDecimal oranizationPkId) {
		this.organizationPkId = oranizationPkId;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public long getRequestCount() {
		return requestCount;
	}

	public void setRequestCount(long requestCount) {
		this.requestCount = requestCount;
	}

	public long getRequestFrailCount() {
		return requestFrailCount;
	}

	public void setRequestFrailCount(long requestFrailCount) {
		this.requestFrailCount = requestFrailCount;
	}

	public BigDecimal getExecutoryCount() {
		if(executoryCount == null) executoryCount = BigDecimal.ZERO;
		return executoryCount;
	}

	public void setExecutoryCount(BigDecimal executoryCount) {
		this.executoryCount = executoryCount;
	}

	public long getUnreliableCount() {
		return unreliableCount;
	}

	public void setUnreliableCount(long unreliableCount) {
		this.unreliableCount = unreliableCount;
	}

	public String getUldegdelStatus() {
		return uldegdelStatus;
	}

	public void setUldegdelStatus(String uldegdelStatus) {
		this.uldegdelStatus = uldegdelStatus;
	}

	public ByteArrayInputStream getSSSS() {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] imageByte = null;
		BufferedImage image = null;
		ByteArrayInputStream bis = null;
		try {
			System.out.println(getCustomerImage());
			imageByte = decoder.decodeBuffer(getCustomerImage());
			bis = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bis;
	}

	@XmlTransient
	public List<CustomerImage> getListCustomerImage() {
		return listCustomerImage;
	}

	public void setListCustomerImage(List<CustomerImage> listCustomerImage) {
		this.listCustomerImage = listCustomerImage;
	}

	public String getCustomerImage() {
		if (customerImage == null || customerImage.length() < 1) {
			if (getListCustomerImage() == null || getListCustomerImage().size() < 1
					|| getListCustomerImage().get(0).getImage() == null)
				return Tool.UserDefaultImage;
			customerImage = getListCustomerImage().get(0).getImage();
		}
		return customerImage;
	}

	public void setCustomerImage(String customerImage) {
		this.customerImage = customerImage;
		if (getListCustomerImage() != null && getListCustomerImage().size() > 0) {
			getListCustomerImage().get(0).setImage(customerImage);
		}
	}

	public int getAge() {
		if (this.isLocal == 0) {
			String rn = getRegNumber();
			if (rn.length() > 9) {
				String gender = rn.substring(8, 9);
				int test = Integer.parseInt(gender);

				if (test % 2 == 0) {
					this.setGender(0);
				} else
					this.setGender(1);

				Date date = new Date();
				int year = Integer.parseInt(rn.substring(2, 4));
				int month = Integer.parseInt(rn.substring(4, 6));
				int day = Integer.parseInt(rn.substring(6, 8));

				if (month > 32 || month > 12 && month < 21) {
					//
				} else if (month > 20 && month < 33) {
					year = year + 2000;
					month = month - 20;
				} else {
					year = year + 1900;
				}
				int currentYear = date.getYear() + 1900;
				int currentMonth = date.getMonth() + 1;
				Date temp = new Date();
				
				if (currentMonth < month) {
					year++;
					this.setAge(currentYear - year);
					this.setMonth(currentMonth + 12 - month);
					temp.setYear(year-1900 - 1);
					temp.setMonth(month-1);
					temp.setDate(day);
				} else {
					this.setAge(currentYear - year);
					this.setMonth(currentMonth - month);
					temp.setYear(year-1900 );
					temp.setMonth(month-1);
					temp.setDate(day);
				}
					this.setbDate(temp);
			}
		} else if (this.isLocal == 1) {
			if (this.birthDate != null) {
				Date now = new Date();
				Date bb = getBirthDate();
				setbDate(bb);
				int tempMonth = now.getMonth();

				if (bb.getDate() > now.getDate()) {
					bb.setMonth(bb.getMonth() + 1);
				} 
				if (bb.getMonth() > now.getMonth()) {
					bb.setYear(bb.getYear() + 1);
					tempMonth = tempMonth + 12 ;
				}
				if (bb.getYear() < now.getYear()) {
					this.month = tempMonth - bb.getMonth();
					this.age = now.getYear() - bb.getYear();
				}

			}
		}
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getArrangePkId() {
		return arrangePkId;
	}

	public void setArrangePkId(BigDecimal arrangePkId) {
		this.arrangePkId = arrangePkId;
	}

	public BigDecimal getInsuranceAimagPkId() {
		return insuranceAimagPkId;
	}

	public void setInsuranceAimagPkId(BigDecimal insuranceAimagPkId) {
		this.insuranceAimagPkId = insuranceAimagPkId;
	}

	public BigDecimal getInsuranceSumPkId() {
		return insuranceSumPkId;
	}

	public void setInsuranceSumPkId(BigDecimal insuranceSumPkId) {
		this.insuranceSumPkId = insuranceSumPkId;
	}

	public BigDecimal getInsuranceTypePkId() {
		return insuranceTypePkId;
	}

	public void setInsuranceTypePkId(BigDecimal insuranceTypePkId) {
		this.insuranceTypePkId = insuranceTypePkId;
	}

	public String getFamilyMemberName() {
		return familyMemberName;
	}

	public void setFamilyMemberName(String familyMemberName) {
		this.familyMemberName = familyMemberName;
	}

	public BigDecimal getFamilyMemberTypePkId() {
		return familyMemberTypePkId;
	}

	public void setFamilyMemberTypePkId(BigDecimal familyMemberTypePkId) {
		this.familyMemberTypePkId = familyMemberTypePkId;
	}

	public String getFamilyPhoneNumber() {
		return familyPhoneNumber;
	}

	public void setFamilyPhoneNumber(String familyPhoneNumber) {
		this.familyPhoneNumber = familyPhoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getCitizenPkId() {
		return citizenPkId;
	}

	public void setCitizenPkId(BigDecimal citizenPkId) {
		this.citizenPkId = citizenPkId;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getIsLocal() {
		return isLocal;
	}

	public void setIsLocal(int isLocal) {
		this.isLocal = isLocal;
	}

	public String getGenderString() {
		if (this.gender == 0)
			genderString = Tool.WOMAN;
		else if (this.gender == 1)
			genderString = Tool.MAN;
		return genderString;
	}

	public void setGenderString(String genderString) {
		this.genderString = genderString;
	}

	public String getCitizenName() {
		return citizenName;
	}

	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public String getGenderStr() {
		genderStr = 0 == this.gender ? Tool.WOMAN : Tool.MAN;
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.gender = Tool.WOMAN.equals(gender) ? 0 : 1;
		this.genderStr = genderStr;
	}

	public char getGenderChar() {
		genderChar = 0 == this.gender ? 'F' : 'M';
		return genderChar;
	}

	public void setGenderChar(char genderChar) {
		this.gender = 'F' == genderChar ? 0 : 1;
		this.genderChar = genderChar;
	}

	public String getInsuranceCardNumber() {
		return insuranceCardNumber;
	}

	public void setInsuranceCardNumber(String insuranceCardNumber) {
		this.insuranceCardNumber = insuranceCardNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getArrangeName() {
		return arrangeName;
	}
	public void setArrangeName(String arrangeName) {
		this.arrangeName = arrangeName;
	}
	
	public String getAimagName() {
		return aimagName;
	}
	public void setAimagName(String aimagName) {
		this.aimagName = aimagName;
	}
	public void setSumName(String sumName) {
		this.sumName = sumName;
	}
	public String getSumName() {
		return sumName;
	}
	public String getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	public Date getbDate() {
		getAge();
		return bDate;
	}
	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}
}

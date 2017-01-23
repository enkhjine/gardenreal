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
@Table(name = "XrayRequest")
public class XrayRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "EmployeePkId")
	private BigDecimal employeePkId;

	@Column(name = "PaymentPkId")
	private BigDecimal paymentPkId;

	@Column(name = "CustomerPkId")
	private BigDecimal customerPkId;

	@Column(name = "XrayPkId")
	private BigDecimal xrayPkId;

	@Column(name = "RequestPkId")
	private BigDecimal requestPkId;

	@Column(name = "XrayEmployeePkId")
	private BigDecimal xrayEmployeePkId;

	@Column(name = "Image")
	private String image;

	@Column(name = "Description")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "RequestDate")
	private Date requestDate;

	@Column(name = "Note")
	private String note;

	@Transient
	String dateString;

	@Transient
	String customerFirstName;

	@Transient
	String customerLastName;

	@Transient
	String customerCardNumber;

	@Transient
	String customerRegNumber;

	@Transient
	int customerGender;

	@Transient
	int customerAge;

	@Transient
	Customer customer;

	@Transient
	String xrayName;

	@Transient
	String employeeName;

	@Transient
	String imageUrl;

	@Transient
	String imageStream;

	@Transient
	String descriptionTemp;

	@Transient
	boolean edit;

	@Transient
	List<String> imgList;

	@Transient
	List<Diagnose> diagnoseList;
	
	@Transient
	private  String employeeSignature;
	
	@Transient
	private String phoneNumber;
	
	@Transient
	private String xrayType;
	
	@Transient
	private String windowType;

	public XrayRequest() {
		super();
	}

	public XrayRequest(BigDecimal pkId, BigDecimal employeePkId, BigDecimal customerPkId, BigDecimal xrayPkId,
			String image, String description, Date requestDate, String customerFirstName, String customerLastName,
			String customerCardNumber, String customerRegNumber, int customerGender, int customerAge, String xrayName,
			String employeeName, BigDecimal requestPkId,String phonenumber,String signature) {

		super();
		this.pkId = pkId;
		this.employeePkId = employeePkId;
		this.customerPkId = customerPkId;
		this.xrayPkId = xrayPkId;
		this.image = image;
		this.requestDate = requestDate;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerCardNumber = customerCardNumber;
		this.customerRegNumber = customerRegNumber;
		this.customerGender = customerGender;
		this.customerAge = customerAge;
		this.xrayName = xrayName;
		this.employeeName = employeeName;
		this.requestPkId = requestPkId;
		this.description = description;
		this.descriptionTemp = description;
		this.employeeSignature  = signature;
		this.edit = false;
	}
	
	public XrayRequest(BigDecimal pkId, BigDecimal employeePkId, BigDecimal customerPkId, BigDecimal xrayPkId,
			String image, String description, Date requestDate, String customerFirstName, String customerLastName,
			String customerCardNumber, String customerRegNumber, int customerGender, int customerAge, String xrayName,
			String employeeName, BigDecimal requestPkId,String phonenumber,String signature, String windowType) {

		super();
		this.pkId = pkId;
		this.employeePkId = employeePkId;
		this.customerPkId = customerPkId;
		this.xrayPkId = xrayPkId;
		this.image = image;
		this.requestDate = requestDate;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerCardNumber = customerCardNumber;
		this.customerRegNumber = customerRegNumber;
		this.customerGender = customerGender;
		this.customerAge = customerAge;
		this.xrayName = xrayName;
		this.employeeName = employeeName;
		this.requestPkId = requestPkId;
		this.description = description;
		this.descriptionTemp = description;
		this.employeeSignature  = signature;
		this.edit = false;
		this.windowType = windowType;
	}


	public XrayRequest(BigDecimal pkId, BigDecimal employeePkId, BigDecimal customerPkId, BigDecimal xrayPkId,
			String image, String description, Date requestDate, String customerFirstName, String customerLastName,
			String customerCardNumber, String customerRegNumber, int customerGender, int customerAge, String xrayName,
			String employeeName, BigDecimal requestPkId, String note) {

		super();
		this.pkId = pkId;
		this.employeePkId = employeePkId;
		this.customerPkId = customerPkId;
		this.xrayPkId = xrayPkId;
		this.image = image;
		this.requestDate = requestDate;
		this.customerFirstName = customerFirstName;
		this.customerLastName = customerLastName;
		this.customerCardNumber = customerCardNumber;
		this.customerRegNumber = customerRegNumber;
		this.customerGender = customerGender;
		this.customerAge = customerAge;
		this.xrayName = xrayName;
		this.employeeName = employeeName;
		this.requestPkId = requestPkId;
		this.description = description;
		this.descriptionTemp = description;
		this.edit = false;
		this.note = note;
	}

	public XrayRequest(XrayRequest request, Customer customer, String name,String type) {
		this.pkId = request.pkId;
		this.customerPkId = request.customerPkId;
		this.employeePkId = request.employeePkId;
		this.paymentPkId = request.paymentPkId;
		this.xrayPkId = request.xrayPkId;
		this.requestPkId = request.requestPkId;
		this.xrayEmployeePkId = request.xrayEmployeePkId;
		this.image = request.image;
		this.description = request.description;
		this.requestDate = request.requestDate;
		this.note = request.note;
		this.customer = customer;
		this.xrayName = name;
		this.xrayType = type;
	}
	public XrayRequest(String lastName ,String firstName,String regNumber,String empName,String empSignature){
		this.customerLastName  =  lastName;
		this.customerFirstName= firstName;
		this.customerRegNumber  =regNumber;
		this.employeeName  =empName;
		this.employeeSignature  =  empSignature;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId;
	}

	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}

	public BigDecimal getXrayPkId() {
		return xrayPkId;
	}

	public void setXrayPkId(BigDecimal xrayPkId) {
		this.xrayPkId = xrayPkId;
	}

	public String getImage() {

		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerCardNumber() {
		return customerCardNumber;
	}

	public void setCustomerCardNumber(String customerCardNumber) {
		this.customerCardNumber = customerCardNumber;
	}

	public String getCustomerRegNumber() {
		return customerRegNumber;
	}

	public void setCustomerRegNumber(String customerRegNumber) {
		this.customerRegNumber = customerRegNumber;
	}

	public int getCustomerGender() {
		return customerGender;
	}

	public void setCustomerGender(int customerGender) {
		this.customerGender = customerGender;
	}

	public int getCustomerAge() {
		return customerAge;
	}

	public void setCustomerAge(int customerAge) {
		this.customerAge = customerAge;
	}

	public String getXrayName() {
		return xrayName;
	}

	public void setXrayName(String xrayName) {
		this.xrayName = xrayName;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getImageUrl() {
		// if (image == null || image.isEmpty() || image == "")
		// setImageUrl("");
		// else
		// setImageUrl(getCustomerFirstName() + "/" + getCustomerRegNumber()
		// + "/" + getXrayName());

		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public String getImageStream() {
		String[] spliter;
		if (this.image != null || !this.image.isEmpty() || this.image == "") {
			spliter = this.image.split("base64,", 2);
			imageStream = spliter[1];
		}

		return imageStream;
	}

	public void setImageStream(String imageStream) {
		this.imageStream = imageStream;
	}

	public String getDateString() {
		dateString = new SimpleDateFormat("yyyy-MM-dd").format(getRequestDate());
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionTemp() {
		return descriptionTemp;
	}

	public void setDescriptionTemp(String descriptionTemp) {
		this.descriptionTemp = descriptionTemp;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public List<String> getImgList() {

		if (this.image != null) {
			if (!this.image.equals("")) {
				imgList = new ArrayList<String>();
				String[] parts = this.image.split("\\?");
				for (int i = 0; i < parts.length; i++) {
					imgList.add(parts[i]);
				}
			}

		}
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Diagnose> getDiagnoseList() {
		return diagnoseList;
	}

	public void setDiagnoseList(List<Diagnose> diagnoseList) {
		this.diagnoseList = diagnoseList;
	}

	public BigDecimal getXrayEmployeePkId() {
		return xrayEmployeePkId;
	}

	public void setXrayEmployeePkId(BigDecimal xrayEmployeePkId) {
		this.xrayEmployeePkId = xrayEmployeePkId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getXrayType() {
		return xrayType;
	}

	public void setXrayType(String xrayType) {
		this.xrayType = xrayType;
	}

	public String getEmployeeSignature() {
		return employeeSignature;
	}

	public void setEmployeeSignature(String employeeSignature) {
		this.employeeSignature = employeeSignature;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getWindowType() {
		return windowType;
	}
	public void setWindowType(String windowType) {
		this.windowType = windowType;
	}
	
	

}
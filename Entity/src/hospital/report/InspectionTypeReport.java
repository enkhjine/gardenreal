package hospital.report;

import java.text.SimpleDateFormat;
import java.util.Date;

import hospital.entity.Aimag;
import hospital.entity.Inspection;
import hospital.entity.ViewCustomer;

public class InspectionTypeReport {
	Date inspectionDate;
	String customerLastName;
	String customerFirstName;
	String customerRegNumber;
	String customerAddress;
	String educationStatus;
	String professionStatus;
	String workPlace;
	String insuranceCardNumber;
	int age;
	String gender;
	byte inspectionType;
	String baseXray;
	String accidentXray;
	byte sicknessType;
	byte formStatus;
	byte treatment;
	byte spasm;
	
	public String dateString;
	
	public InspectionTypeReport()
	{
		super();
	}
	
	public InspectionTypeReport(String baseXray){
		super();
		this.baseXray = baseXray;
	}
	
	public InspectionTypeReport(Date inspectionDate, String customerLastName,
			String customerFirstName, String customerRegNumber,
			String customerAddress, String educationStatus,
			String professionStatus, String workPlace,
			String insuranceCardNumber, int age, String gender,
			byte inspectionType, String baseXray, String accidentXray,
			byte sicknessType, byte formStatus, byte treatment, byte spasm) {
		super();
		this.inspectionDate = inspectionDate;
		this.customerLastName = customerLastName;
		this.customerFirstName = customerFirstName;
		this.customerRegNumber = customerRegNumber;
		this.customerAddress = customerAddress;
		this.educationStatus = educationStatus;
		this.professionStatus = professionStatus;
		this.workPlace = workPlace;
		this.insuranceCardNumber = insuranceCardNumber;
		this.age = age;
		this.gender = gender;
		this.inspectionType = inspectionType;
		this.baseXray = baseXray;
		this.accidentXray = accidentXray;
		this.sicknessType = sicknessType;
		this.formStatus = formStatus;
		this.treatment = treatment;
		this.spasm = spasm;
	}
	
	public InspectionTypeReport(Inspection inspection, ViewCustomer customer, Aimag aimag){
		super();
		 
		this.inspectionDate = inspection.getInspectionDateDate();
		this.inspectionType = (byte) inspection.getInspectionType();
		this.customerAddress = "";
		if(aimag != null ) this.customerAddress += aimag.getName();
		if(customer != null){
			this.customerAddress += customer.getDistrict() + customer.getBuilding();
			this.customerLastName = customer.getLastName();
			this.customerFirstName = customer.getFirstName();
			this.customerRegNumber = customer.getRegNumber();
			this.educationStatus = customer.getSocialStatus();
			this.professionStatus =  customer.getRank();
			this.workPlace = customer.getRank();
			this.insuranceCardNumber = customer.getCardNumber();
			this.age = customer.getAge();
			this.gender = customer.getGender() == 0 ? "Эм" : "Эр";
		}
//		this.baseXray = inspection.inspectionType;
//		this.accidentXray = inspection.inspectionType;
//		this.sicknessType = inspection.inspectionType;
//		this.formStatus = inspection.inspectionType;
//		this.treatment = inspection.inspectionType;
//		this.spasm = inspection.getInspectionType();
	}
	
	public String getDateString() {
		if(getInspectionDate() == null)
			dateString = " ";
		else
			dateString = new SimpleDateFormat("dd-MM-yyyy").format(getInspectionDate());
			
		
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public Date getInspectionDate() {
		return inspectionDate;
	}

	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}

	public String getCustomerLastName() {
		return customerLastName;
	}

	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}

	public String getCustomerFirstName() {
		return customerFirstName;
	}

	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}

	public String getCustomerRegNumber() {
		return customerRegNumber;
		
	}

	public void setCustomerRegNumber(String customerRegNumber) {
		this.customerRegNumber = customerRegNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public String getProfessionStatus() {
		return professionStatus;
	}

	public void setProfessionStatus(String professionStatus) {
		this.professionStatus = professionStatus;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getInsuranceCardNumber() {
		return insuranceCardNumber;
	}

	public void setInsuranceCardNumber(String insuranceCardNumber) {
		this.insuranceCardNumber = insuranceCardNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public byte getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(byte inspectionType) {
		this.inspectionType = inspectionType;
	}

	public String getBaseXray() {
		return baseXray;
	}

	public void setBaseXray(String baseXray) {
		this.baseXray = baseXray;
	}

	public String getAccidentXray() {
		return accidentXray;
	}

	public void setAccidentXray(String accidentXray) {
		this.accidentXray = accidentXray;
	}

	public byte getSicknessType() {
		return sicknessType;
	}

	public void setSicknessType(byte sicknessType) {
		this.sicknessType = sicknessType;
	}

	public byte getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(byte formStatus) {
		this.formStatus = formStatus;
	}

	public byte getTreatment() {
		return treatment;
	}

	public void setTreatment(byte treatment) {
		this.treatment = treatment;
	}

	public byte getSpasm() {
		return spasm;
	}

	public void setSpasm(byte spasm) {
		this.spasm = spasm;
	}
	
	public String getStr(int i){
		if(this.inspectionType + 1 == i) return "( " + i + " )";
		return i+"";
	}
	
	public String subString(int i){
		if(getCustomerRegNumber().length() <= i) return "";
		return getCustomerRegNumber().substring(i,  i+1);
	}
	
}

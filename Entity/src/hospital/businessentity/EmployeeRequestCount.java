package hospital.businessentity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hospital.entity.EmployeeRequest;

public class EmployeeRequestCount {
	String subOrganizationName;
	String employeeFirstName;
	String employeeLastName;
	List<EmployeeRequest> requestList;
	BigDecimal isInsurance;
	BigDecimal notInsurance;
	BigDecimal total;
	BigDecimal preliminary;
	BigDecimal live;
	BigDecimal express;
	BigDecimal inMood;
	BigDecimal saveMood;
	BigDecimal outMood;
	BigDecimal reInspection;
	BigDecimal inspection;
	String begDate;
	String enDate;
	boolean isTreeTable;
	
	BigDecimal employeePkId;
	
	public EmployeeRequestCount() {
		super();
	}

	public EmployeeRequestCount(String son, String efn, String eln) {
		super();
		this.subOrganizationName = son;
		this.employeeFirstName = efn;
		this.employeeLastName = eln;
	}

	public EmployeeRequestCount(String son, String efn, String eln, BigDecimal employeePkId) {
		super();
		this.subOrganizationName = son;
		this.employeeFirstName = efn;
		this.employeeLastName = eln;
		this.employeePkId = employeePkId;
	}
	
	public EmployeeRequestCount(String name){
		super();
		this.subOrganizationName = name;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public String getEmployeeFirstName() {
		return employeeFirstName;
	}

	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}

	public String getEmployeeLastName() {
		return employeeLastName;
	}

	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}

	public List<EmployeeRequest> getRequestList() {
		if (requestList == null)
			requestList = new ArrayList<EmployeeRequest>();
		return requestList;
	}

	public BigDecimal getIsInsurance() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getInsurance()==1)
				a = a + 1;
		}
		isInsurance = new BigDecimal(a);
		return isInsurance;
	}

	public void setIsInsurance(BigDecimal isInsurance) {
		this.isInsurance = isInsurance;
	}

	public void setRequestList(List<EmployeeRequest> requestList) {
		this.requestList = requestList;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getNotInsurance() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getInsurance()==0)
				a = a + 1;
		}
		notInsurance = new BigDecimal(a);
		return notInsurance;
	}

	public void setNotInsurance(BigDecimal notInsurance) {
		this.notInsurance = notInsurance;
	}

	public BigDecimal getTotal() {
		int a = requestList.size();
		total = new BigDecimal(a);
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getPreliminary() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getIsExpress() == 0)
			if(e.getGuest() == 1)
				a = a + 1;
		}
		preliminary = new BigDecimal(a);
		return preliminary;
	}

	public void setPreliminary(BigDecimal preliminary) {
		this.preliminary = preliminary;
	}

	public BigDecimal getLive() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getIsExpress() == 0)
			if(e.getGuest() == 0)
				a = a + 1;
		}
		live = new BigDecimal(a);
		return live;
	}

	public void setLive(BigDecimal live) {
		this.live = live;
	}

	public BigDecimal getExpress() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getIsExpress() == 1)
				a = a + 1;
		}
		express = new BigDecimal(a);
		return express;
	}

	public void setExpress(BigDecimal express) {
		this.express = express;
	}

	public BigDecimal getInMood() {
		int a = 0;
		for (EmployeeRequest e : requestList){
			if(e.getMood() == 1){
				if(e.getSaveMood() == 0)
					a = a + 1;
			}
		}
		inMood = new BigDecimal(a);
		return inMood;
	}

	public void setInMood(BigDecimal inMood) {
		this.inMood = inMood;
	}

	public BigDecimal getSaveMood() {
		int a = 0;
		for(EmployeeRequest e : requestList){
			if(e.getMood() == 1){
				if(e.getSaveMood() == 1 || e.getSaveMood() == 2)
					a = a + 1;
			}
				
		}
		saveMood = new BigDecimal(a);
		return saveMood;
	}

	public void setSaveMood(BigDecimal saveMood) {
		this.saveMood = saveMood;
	}

	public BigDecimal getOutMood() {
		int a = 0;
		for(EmployeeRequest e : requestList){
			if(e.getMood() == 2)
				a = a + 1;
		}
		outMood = new BigDecimal(a);
		return outMood;
	}
	
	public BigDecimal getReInspection() {
		int a = 0;
		for(EmployeeRequest e :requestList){
			if(e.getReInspection() == 1)
				a = a + 1;
		}
		reInspection = new BigDecimal(a);
		return reInspection;
	}

	public void setReInspection(BigDecimal reInspection) {
		this.reInspection = reInspection;
	}

	public BigDecimal getInspection() {
		int a = 0;
		for(EmployeeRequest e :requestList){
			if(e.getReInspection() == 0)
				a = a + 1;
		}
		inspection = new BigDecimal(a);
		return inspection;
	}

	public void setInspection(BigDecimal inspection) {
		this.inspection = inspection;
	}

	public void setOutMood(BigDecimal outMood) {
		this.outMood = outMood;
	}

	public String getBegDate() {
		return begDate;
	}

	public void setBegDate(String begDate) {
		this.begDate = begDate;
	}

	public String getEnDate() {
		return enDate;
	}

	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}

	public boolean isTreeTable() {
		return isTreeTable;
	}

	public void setTreeTable(boolean isTreeTable) {
		this.isTreeTable = isTreeTable;
	}
	
	public String total(){
		String ret="";
		if (isTreeTable)
			ret = this.total + "";
		return ret;
	}

	public String notInsurance(){
		String ret="";
		if (isTreeTable)
			ret = this.notInsurance + "";
		return ret;
	}
	
	public String isInsurance(){
		String ret="";
		if (isTreeTable)
			ret = this.isInsurance + "";
		return ret;
	}
	
	public String premilanary(){
		String ret="";
		if (isTreeTable)
			ret = this.preliminary + "";
		return ret;
	}
	
	public String live(){
		String ret="";
		if (isTreeTable)
			ret = this.live + "";
		return ret;
	}
	
	public String express(){
		String ret="";
		if (isTreeTable)
			ret = this.express + "";
		return ret;
	}
	
	public String inMood(){
		String ret="";
		if (isTreeTable)
			ret = this.inMood + "";
		return ret;
	}
	
	public String saveMood(){
		String ret="";
		if (isTreeTable)
			ret = this.saveMood + "";
		return ret;
	}
	
	public String outMood(){
		String ret="";
		if (isTreeTable)
			ret = this.outMood + "";
		return ret;
	}
	
	public String reInspection(){
		String ret="";
		if (isTreeTable)
			ret = this.reInspection + "";
		return ret;
	}
	
	public String inspection(){
		String ret="";
		if (isTreeTable)
			ret = this.inspection + "";
		return ret;
	}


}
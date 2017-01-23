package hospital.businessentity;

import hospital.entity.Employee;
import hospital.entity.ExaminationDtl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CashByInspectionDtl {
	private String name;
	private String type;
	private BigDecimal typePkId;
	private BigDecimal price;
	private String qty;
	private int times;
	private int dayLength;
	private int treatmentCount;
	private int userCount;
	private String employeeName;
	private BigDecimal amount;
	private BigDecimal employeePkId;
	private List<Employee> listEmployee;
	private BigDecimal allAmount;
	private boolean selected;
	private BigDecimal inspectionPkId;
	private BigDecimal inspectionDtlPkId;
	private BigDecimal degreePkId;
	private String status;
	private List<ExaminationDtl> examinationDtls;
	private BigDecimal paymentDtlPkId;
	private boolean payment;
	private BigDecimal employeeRequestPkId;
	private String inspectionStatus;
	private Date requestDate;
	private String requestDateStr;
	private BigDecimal xrayEmployeePkId;
	private String description;
	
	public CashByInspectionDtl() {
		super();
	}
	
	public CashByInspectionDtl(BigDecimal degreePkId, String name) {
		super();
		this.degreePkId = degreePkId;
		this.name = name;
	}
	
	public CashByInspectionDtl(String name, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	public CashByInspectionDtl(BigDecimal paymentDtlPkId, String name, BigDecimal price){
		super();
		this.paymentDtlPkId = paymentDtlPkId;
		this.name = name;
		this.price = price;
	}
	
	public CashByInspectionDtl(BigDecimal paymentDtlPkId, String name, BigDecimal price, BigDecimal typePkId){
		super();
		this.paymentDtlPkId = paymentDtlPkId;
		this.name = name;
		this.price = price;
		this.typePkId = typePkId;
	}
	
	public CashByInspectionDtl(String name, String employeeName, BigDecimal price) {
		super();
		this.name = name;
		this.price = price;
		this.employeeName = employeeName == null ? "" : employeeName;
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

	public BigDecimal getTypePkId() {
		return typePkId;
	}

	public void setTypePkId(BigDecimal typePkId) {
		this.typePkId = typePkId;
	}

	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getQty() {
		return qty;
	}
	
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	public int getTimes() {
		return times <= 0 ? 1 : times;
	}
	
	public void setTimes(int times) {
		this.times = times;
	}
	
	public int getDayLength() {
		return dayLength <= 0 ? 1 : dayLength;
	}
	
	public void setDayLength(int dayLength) {
		this.dayLength = dayLength;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public List<Employee> getListEmployee() {
		if(listEmployee == null) listEmployee = new ArrayList<>();
		return listEmployee;
	}
	
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}
	
	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public BigDecimal getAllAmount() {
		return allAmount;
	}
	
	public void setAllAmount(BigDecimal allAmount) {
		this.allAmount = allAmount;
	}
	
	public int getTreatmentCount() {
		return treatmentCount;
	}
	
	public void setTreatmentCount(int treatmentCount) {
		this.treatmentCount = treatmentCount;
	}
	
	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}
	
	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}
	
	public BigDecimal getInspectionDtlPkId() {
		return inspectionDtlPkId;
	}
	
	public void setInspectionDtlPkId(BigDecimal inspectionDtlPkId) {
		this.inspectionDtlPkId = inspectionDtlPkId;
	}
	
	public BigDecimal getDegreePkId() {
		return degreePkId;
	}
	
	public void setDegreePkId(BigDecimal degreePkId) {
		this.degreePkId = degreePkId;
	}
	
	public BigDecimal typePkId() {
		return this.typePkId == null ? BigDecimal.ZERO : this.typePkId;
	}
	
	public int getUserCount() {
		return userCount;
	}
	
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	
	public int sumCount() {
		return userCount+treatmentCount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void calcPriceByExaminationDtl(){
		if(examinationDtls != null && examinationDtls.size() > 0) {
			this.price = BigDecimal.ZERO;
			for (ExaminationDtl examinationDtl : examinationDtls) {
				this.price = this.price.add(examinationDtl.getPrice());
			}
		}
	}
	
	public List<ExaminationDtl> getExaminationDtls() {
		if(examinationDtls == null) examinationDtls = new ArrayList<>();
		return examinationDtls;
	}
	
	public void setExaminationDtls(List<ExaminationDtl> examinationDtls) {
		this.examinationDtls = examinationDtls;
	}
	
	public BigDecimal getPaymentDtlPkId() {
		return paymentDtlPkId;
	}
	
	public void setPaymentDtlPkId(BigDecimal paymentDtlPkId) {
		this.paymentDtlPkId = paymentDtlPkId;
	}
	
	public boolean isPayment() {
		return payment;
	}
	
	public void setPayment(boolean payment) {
		this.payment = payment;
	}
	
	public BigDecimal getEmployeeRequestPkId() {
		return employeeRequestPkId;
	}
	
	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public String getInspectionStatus() {
		return inspectionStatus;
	}
	
	public void setInspectionStatus(String inspectionStatus) {
		this.inspectionStatus = inspectionStatus;
	}
	
	public Date getRequestDate() {
		return requestDate;
	}
	
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	
	public String getRequestDateStr() {
		if(requestDate == null) requestDate = new Date();
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		requestDateStr = df.format(requestDate);
		return requestDateStr;
	}
	
	public void setRequestDateStr(String requestDateStr) {
		this.requestDateStr = requestDateStr;
	}

	public BigDecimal getXrayEmployeePkId() {
		return xrayEmployeePkId;
	}

	public void setXrayEmployeePkId(BigDecimal xrayEmployeePkId) {
		this.xrayEmployeePkId = xrayEmployeePkId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}

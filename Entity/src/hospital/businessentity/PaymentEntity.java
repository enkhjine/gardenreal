package hospital.businessentity;

import hospital.entity.Customer;
import hospital.entity.Payment;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentEntity {
	private BigDecimal employeeRequestPkId;
	private Customer customer;
	private Payment payment;
	private List<BigDecimal> employeeRequestPkIds;
	private String serviceName;
	private long totalCustomer;
	private BigDecimal totalPayment;
	private long totalCustomerNumber;
	private BigDecimal totalPaymentNumber;
	List<PaymentEntity> paymentDtl;
	
	public PaymentEntity(){
		super();
	}
	
	public PaymentEntity(String sn, long tc, BigDecimal tp){
		super();
		this.serviceName = sn;
		this.totalCustomer = tc;
		this.totalPayment = tp;
	}
	
	public PaymentEntity(Payment payment, Customer customer){
		this.payment = payment;
		this.customer = customer;
	}
	
	public PaymentEntity(Customer customer, Payment payment) {	
		this.customer = customer;
		this.payment = payment;
	}
	
	public PaymentEntity(BigDecimal employeeRequestPkId, Customer customer, Payment payment) {
		this.employeeRequestPkId = employeeRequestPkId;
		this.customer = customer;
		this.payment = payment;
	}
	
	public PaymentEntity(BigDecimal pkId, String cardNumber, String lastName, String firstName, String regNumber, int age, int gender, BigDecimal employeeRequestPkId){
		customer = new Customer();
		customer.setPkId(pkId);
		customer.setCardNumber(cardNumber);
		customer.setLastName(lastName);
		customer.setFirstName(firstName);
		customer.setRegNumber(regNumber);
		customer.setAge(age);
		customer.setGender(gender);
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public PaymentEntity(long tc, BigDecimal tp){
		this.totalCustomerNumber = tc;
		this.totalPaymentNumber = tp;
	}
	
//	public List<EmployeeRequest> getListEmployeeRequest() {
//		if(listEmployeeRequest == null) listEmployeeRequest = new ArrayList<>();
//		return listEmployeeRequest;
//	}
//	
//	public void setListEmployeeRequest(List<EmployeeRequest> listEmployeeRequest) {
//		this.listEmployeeRequest = listEmployeeRequest;
//	}
//	
//	public BigDecimal getBasicAmount() {
//		return basicAmount == null ? BigDecimal.ZERO : basicAmount;
//	}
//	
//	public void setBasicAmount(BigDecimal basicAmount) {
//		this.basicAmount = basicAmount;
//	}
//	
//	public BigDecimal getAmount() {
//		return amount == null ? BigDecimal.ZERO : amount;
//	}
//	
//	public void setAmount(BigDecimal amount) {
//		this.amount = amount;
//	}
//	
//	public String getType() {
//		return type;
//	}
//	
//	public void setType(String type) {
//		this.type = type;
//	}
//
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public BigDecimal getEmployeeRequestPkId() {
		return employeeRequestPkId;
	}
	
	public void setEmployeeRequestPkId(BigDecimal employeeRequestPkId) {
		this.employeeRequestPkId = employeeRequestPkId;
	}
	
	public List<BigDecimal> getEmployeeRequestPkIds() {
		if(employeeRequestPkIds == null) employeeRequestPkIds = new ArrayList<>();
		return employeeRequestPkIds;
	}
	
	public void setEmployeeRequestPkIds(List<BigDecimal> employeeRequestPkIds) {
		this.employeeRequestPkIds = employeeRequestPkIds;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public BigDecimal getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(BigDecimal totalPayment) {
		this.totalPayment = totalPayment;
	}

	public long getTotalCustomer() {
		return totalCustomer;
	}

	public void setTotalCustomer(long totalCustomer) {
		this.totalCustomer = totalCustomer;
	}

	public long getTotalCustomerNumber() {
		return totalCustomerNumber;
	}

	public void setTotalCustomerNumber(long totalCustomerNumber) {
		this.totalCustomerNumber = totalCustomerNumber;
	}

	public BigDecimal getTotalPaymentNumber() {
		return totalPaymentNumber;
	}

	public void setTotalPaymentNumber(BigDecimal totalPaymentNumber) {
		this.totalPaymentNumber = totalPaymentNumber;
	}

	public List<PaymentEntity> getPaymentDtl() {
		if (paymentDtl == null)
			paymentDtl = new ArrayList<PaymentEntity>();
		return paymentDtl;
	}

	public void setPaymentDtl(List<PaymentEntity> paymentDtl) {
		this.paymentDtl = paymentDtl;
	}
	
	
}

package hospital.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import hospital.entity.Aimag;
import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerInspection;
import hospital.entity.CustomerPain;
import hospital.entity.CustomerPlan;
import hospital.entity.CustomerQuestion;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.Inspection;
import hospital.entity.Organization;
import hospital.entity.Soum;
import hospital.entity.SubOrganization;

public class Gt15Pain {
	private Customer customers;
	private Date  inspectionDate; 
	private String firstName;
	private SubOrganization subName;
	private List<Diagnose>  customerDiagnose;
	private List<SubOrganization> subOrganizations;
	private Organization  organization;
	private  Inspection  inspections;
	private BigDecimal  inspectionPkId;
	private  Employee  employees;
	private List<CustomerPain>  customerPain;
	private  List<CustomerQuestion>  customerQuestion;
	private List<CustomerInspection>  customerInspection;
	private List<CustomerPlan>  customerPlan;
	private Aimag  aimag;
	private Soum soum;
	public Gt15Pain(){
		super();
	}
	public  Gt15Pain(Date inspection,BigDecimal  pkId, String firstName, SubOrganization name ){
		this.inspectionDate =  inspection;
		this.inspectionPkId  =pkId;
		this.firstName  = firstName;
		this.subName  =name;
	}
	public  Gt15Pain( Inspection i,Employee  e  ){
		
		this.inspections  =i;
		this.employees  =e;
	}
	public Gt15Pain(List<SubOrganization> sb ){
		subOrganizations  =sb;
	}
	public Date getInspectionDate() {
		return inspectionDate;
	}
	public void setInspectionDate(Date inspectionDate) {
		this.inspectionDate = inspectionDate;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public SubOrganization getSubName() {
		return subName;
	}
	public void setSubName(SubOrganization subName) {
		this.subName = subName;
	}
	public String getInspectionDateSipmle(){
		return new  SimpleDateFormat("yyyy-MM-dd HH:mm").format(getInspectionDate());
	}

	public Customer getCustomers() {
		return customers;
	}
	public void setCustomers(Customer customers) {
		this.customers = customers;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public List<SubOrganization> getSubOrganizations() {
		return subOrganizations;
	}
	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}
	public List<Diagnose> getCustomerDiagnose() {
		return customerDiagnose;
	}
	public void setCustomerDiagnose(List<Diagnose> customerDiagnose) {
		this.customerDiagnose = customerDiagnose;
	}
	
	public Inspection getInspections() {
		return inspections;
	}
	public void setInspections(Inspection inspections) {
		this.inspections = inspections;
	}
	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}
	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}
	public Employee getEmployees() {
		return employees;
	}
	public void setEmployees(Employee employees) {
		this.employees = employees;
	}
	public List<CustomerPain> getCustomerPain() {
		return customerPain;
	}
	public void setCustomerPain(List<CustomerPain> customerPain) {
		this.customerPain = customerPain;
	}
	public List<CustomerQuestion> getCustomerQuestion() {
		return customerQuestion;
	}
	public void setCustomerQuestion(List<CustomerQuestion> customerQuestion) {
		this.customerQuestion = customerQuestion;
	}
	public List<CustomerInspection> getCustomerInspection() {
		return customerInspection;
	}
	public void setCustomerInspection(List<CustomerInspection> customerInspection) {
		this.customerInspection = customerInspection;
	}
	public List<CustomerPlan> getCustomerPlan() {
		return customerPlan;
	}
	public void setCustomerPlan(List<CustomerPlan> customerPlan) {
		this.customerPlan = customerPlan;
	}
	public Aimag getAimag() {
		return aimag;
	}
	public void setAimag(Aimag aimag) {
		this.aimag = aimag;
	}
	public Soum getSoum() {
		return soum;
	}
	public void setSoum(Soum soum) {
		this.soum = soum;
	}
	
	
	
}

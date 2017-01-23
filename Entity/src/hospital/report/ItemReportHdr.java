package hospital.report;

import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.Item;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.entity.TreatmentDtl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemReportHdr {
	private Item item;
	private BigDecimal itemPkId;
	private long count;
	private EmployeeRequest employeeRequest;
	private Inspection inspection;
	private InspectionDtl inspectionDtl;
	private Treatment treatment;
	private TreatmentDtl treatmentDtl;
	private String subOrganizationName;
	private String employeeFirstName;
	private BigDecimal employeePkId;
	private BigDecimal sumAmount;
	private List<ItemReportDtl> itemReportDtls;
	private List<BigDecimal> itemPkIds;
	private BigDecimal customerTreatmentPkId;
	
	public ItemReportHdr(){
		super();
	}
	
	public ItemReportHdr(BigDecimal itemPkId, long count){
		this.itemPkId = itemPkId;
		this.count = count;
	}

	public ItemReportHdr(EmployeeRequest employeeRequest,
			Inspection inspection, InspectionDtl inspectionDtl,
			Treatment treatment, TreatmentDtl treatmentDtl, Item item) {
		super();
		this.employeeRequest = employeeRequest;
		this.inspection = inspection;
		this.inspectionDtl = inspectionDtl;
		this.treatment = treatment;
		this.treatmentDtl = treatmentDtl;
		this.item = item;
	}
	
	public ItemReportHdr(Employee employee, SubOrganization organization){
		super();
		this.subOrganizationName = organization.getName();
		employeeFirstName = employee.getFirstName();
		this.employeePkId = employee.getPkId();
	}
	
	public ItemReportHdr(Employee employee, SubOrganization organization, BigDecimal customerTreatmentPkId){
		super();
		this.subOrganizationName = organization.getName();
		employeeFirstName = employee.getFirstName();
		this.employeePkId = employee.getPkId();
		this.customerTreatmentPkId = customerTreatmentPkId;
		this.sumAmount = BigDecimal.ZERO;
		this.count = 0;
	}

	public EmployeeRequest getEmployeeRequest() {
		return employeeRequest;
	}

	public void setEmployeeRequest(EmployeeRequest employeeRequest) {
		this.employeeRequest = employeeRequest;
	}

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public InspectionDtl getInspectionDtl() {
		return inspectionDtl;
	}

	public void setInspectionDtl(InspectionDtl inspectionDtl) {
		this.inspectionDtl = inspectionDtl;
	}

	public Treatment getTreatment() {
		return treatment;
	}

	public void setTreatment(Treatment treatment) {
		this.treatment = treatment;
	}

	public TreatmentDtl getTreatmentDtl() {
		return treatmentDtl;
	}

	public void setTreatmentDtl(TreatmentDtl treatmentDtl) {
		this.treatmentDtl = treatmentDtl;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}

	public List<ItemReportDtl> getItemReportDtls() {
		if(itemReportDtls == null) itemReportDtls = new ArrayList<>();
		return itemReportDtls;
	}

	public void setItemReportDtls(List<ItemReportDtl> itemReportDtls) {
		this.itemReportDtls = itemReportDtls;
	}

	public List<BigDecimal> getItemPkIds() {
		if(itemPkIds == null) itemPkIds = new ArrayList<>();
		return itemPkIds;
	}

	public void setItemPkIds(List<BigDecimal> itemPkIds) {
		this.itemPkIds = itemPkIds;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
	
	public BigDecimal getItemPkId() {
		return itemPkId;
	}
	
	public void setItemPkId(BigDecimal itemPkId) {
		this.itemPkId = itemPkId;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getCustomerTreatmentPkId() {
		return customerTreatmentPkId;
	}

	public void setCustomerTreatmentPkId(BigDecimal customerTreatmentPkId) {
		this.customerTreatmentPkId = customerTreatmentPkId;
	}
	
}

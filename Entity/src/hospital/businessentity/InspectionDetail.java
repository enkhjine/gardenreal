package hospital.businessentity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import hospital.entity.CustomerAttachment;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerInspection;
import hospital.entity.CustomerPain;
import hospital.entity.CustomerPlan;
import hospital.entity.CustomerQuestion;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;


public class InspectionDetail {
	
	private Inspection inspection;
	
	private List<JSONObject> pain;
	
	private List<JSONObject> checkup;
	
	private List<JSONObject> q;
	
	
	
	private List<CustomerDiagnose> customerDiagnoses;

	private List<InspectionDtl> inspectionDtl;
	
	private List<CustomerPlan> customerPlan;
	
	private List<CustomerPain> customerPain;
	
	private List<CustomerQuestion> customerQuestion;
	
	private List<CustomerAttachment> customerAttachment;
	
	private List<CustomerMedicalHistory> customerMedicalHistory;
	
	private BigDecimal  inspectionPkId;
	private List<CustomerInspection> customerInspection;
	private BigDecimal typeDtlPkId;
	private String typeDtl;
	
	public InspectionDetail(){
		super();
	}
	public  InspectionDetail(String dtl,BigDecimal pkId)
	{
		 this.typeDtl= dtl;
		 this.typeDtlPkId = pkId;
	}
	public Inspection getInspection() {
		return inspection;
	}
	public InspectionDetail(List<JSONObject>  p){
		this.pain  = p;
	}
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public List<JSONObject> getPain() {
		if(pain == null)
			pain = new ArrayList<JSONObject>();
		return pain;
	}

	public void setPain(List<JSONObject> pain) {
		this.pain = pain;
	}

	public List<CustomerDiagnose> getCustomerDiagnoses() {
		if(customerDiagnoses == null)
			customerDiagnoses = new ArrayList<CustomerDiagnose>();
		return customerDiagnoses;
	}

	public void setCustomerDiagnoses(List<CustomerDiagnose> customerDiagnoses) {
		this.customerDiagnoses = customerDiagnoses;
	}

	public List<InspectionDtl> getInspectionDtl() {
		if(inspectionDtl == null)
			inspectionDtl = new ArrayList<InspectionDtl>();
		return inspectionDtl;
	}

	public void setInspectionDtl(List<InspectionDtl> inspectionDtl) {
		this.inspectionDtl = inspectionDtl;
	}

	public List<CustomerPlan> getCustomerPlan() {
		if(customerPlan == null)
			customerPlan = new ArrayList<CustomerPlan>();
		return customerPlan;
	}

	public void setCustomerPlan(List<CustomerPlan> customerPlan) {
		this.customerPlan = customerPlan;
	}

	public List<CustomerPain> getCustomerPain() {
		if(customerPain == null)
			customerPain = new ArrayList<CustomerPain>();
		return customerPain;
	}

	public void setCustomerPain(List<CustomerPain> customerPain) {
		this.customerPain = customerPain;
	}

	public List<CustomerQuestion> getCustomerQuestion() {
		if(customerQuestion == null)
			customerQuestion = new ArrayList<CustomerQuestion>();
		return customerQuestion;
	}

	public void setCustomerQuestion(List<CustomerQuestion> customerQuestion) {
		this.customerQuestion = customerQuestion;
	}

	public List<CustomerAttachment> getCustomerAttachment() {
		if(customerAttachment == null)
			customerAttachment = new ArrayList<CustomerAttachment>();
		return customerAttachment;
	}

	public void setCustomerAttachment(List<CustomerAttachment> customerAttachment) {
		this.customerAttachment = customerAttachment;
	}

	public List<CustomerInspection> getCustomerInspection() {
		if(customerInspection == null)
			customerInspection = new ArrayList<CustomerInspection>();
		return customerInspection;
	}

	public void setCustomerInspection(List<CustomerInspection> customerInspection) {
		this.customerInspection = customerInspection;
	}

	public List<JSONObject> getCheckup() {
		if(checkup == null)
			checkup = new ArrayList<JSONObject>();
		return checkup;
	}

	public void setCheckup(List<JSONObject> checkup) {
		this.checkup = checkup;
	}

	public BigDecimal getTypeDtlPkId() {
		return typeDtlPkId;
	}

	public void setTypeDtlPkId(BigDecimal typeDtlPkId) {
		this.typeDtlPkId = typeDtlPkId;
	}

	public String getTypeDtl() {
		return typeDtl;
	}

	public void setTypeDtl(String typeDtl) {
		this.typeDtl = typeDtl;
	}
	
	public List<JSONObject> getQ() {
		if(q == null)
			q = new ArrayList<JSONObject>();
		return q;
	}
	public void setQ(List<JSONObject> q) {
		this.q = q;
	}
	
	public List<CustomerMedicalHistory> getCustomerMedicalHistory() {
		if(customerMedicalHistory == null)
			customerMedicalHistory = new ArrayList<CustomerMedicalHistory>();
		return customerMedicalHistory;
	}
	public void setCustomerMedicalHistory(List<CustomerMedicalHistory> customerMedicalHistory) {
		this.customerMedicalHistory = customerMedicalHistory;
	}
	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}
	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}
	
	
}
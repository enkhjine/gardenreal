package hospital.businesslogic.interfaces;

import hospital.businessentity.CustomerEar;
import hospital.businessentity.CustomerExamination;
import hospital.businessentity.CustomerLung;
import hospital.businessentity.CustomerMedicalHistory;
import hospital.businessentity.CustomerNose;
import hospital.businessentity.CustomerProblem;
import hospital.businessentity.CustomerRequest;
import hospital.businessentity.CustomerThroat;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.InspectionDetail;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.OrderedTreatment;
import hospital.entity.*;
import hospital.report.Gt15Pain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IInspectionLogicLocal {

	public void saveMedicalHistory(CustomerMedicalHistory cmh) throws Exception;

	public Inspection saveInspection(Inspection inspection, LoggedUser lu, List<InspectionDtl> inspectionDtls, 
			List<CustomerDiagnose> customerDiagnoses, List<CustomerMedicine> customerMedicines,EmployeeRequest er,
			List<CustomerPlan> customerPlans, List<CustomerQuestion> customerQuestions,
			List<CustomerAttachment> customerAttachments, List<CustomerPain> customerPains, CustomerPastHistory pastHistory)
			throws Exception;
	
	public Inspection saveReInspection(Inspection inspection, LoggedUser lu, EmployeeRequest er, 
			List<CustomerDiagnose> customerDiagnoses, List<CustomerPlan> customerPlans, List<CustomerQuestion> customerQuestions,
			List<CustomerAttachment> customerAttachments, List<CustomerPain> customerPains, CustomerInspection customerInspection, CustomerPastHistory pastHistory) throws Exception;
	
	public void saveEmployeeRequest(EmployeeRequest er) throws Exception;
	public List<Organization>  getOrganization(BigDecimal orPkId)  throws  Exception; 
	
	public void saveInspectionForm(Inspection inspection, List<InspectionForm> inspectionForm, LoggedUser lu) throws Exception;
	
	public CustomerPastHistory getCustomerPast(BigDecimal customerPkId) throws Exception;
	
	public void saveInspectionDtl(InspectionDtl inspectionDtl) throws Exception;

	public void saveEmployeeRequest(EmployeeRequest employeeRequest,
			LoggedUser loggedUser) throws Exception;

	public void saveXrayRequests(List<XrayRequest> xrayRequests)
			throws Exception;

	public void saveXrayRequest(XrayRequest xrayRequest)
			throws Exception;

	public void saveSurveyDtls(List<SurveyDtl> dtls, EmployeeRequest er,
			LoggedUser lu) throws Exception;	

	public List<InspectionDtl> getInspectionDtls() throws Exception;

	public List<InspectionDtl> getInspectionDtl(LoggedUser lu, BigDecimal inspectionPkId) throws Exception;
	public List<InspectionDtl> getInspectionDtl(List<BigDecimal> inspectionPkId) throws Exception;

	public List<CustomerMedicine> getCustomerMedicine(BigDecimal inspectionPkId) throws Exception;
	public List<CustomerMedicine> getCustomerMedicinesList(BigDecimal inspectionPkId) throws Exception;
	
	public List<CustomerDiagnose> getCustomerDiagnose(BigDecimal inspectionPkId) throws Exception;
	public List<CustomerDiagnose> getCustomerDiagnose(List<BigDecimal> inspectionPkId) throws Exception;
	public List<CustomerDiagnose> getCustomerDiagnoseLast(BigDecimal customerPkId, LoggedUser lu) throws Exception;
	
	public List<MedicalHistory> getMedicalHistory(BigDecimal customerPkId)
			throws Exception;

	public List<XrayRequest> getXrayHistory(BigDecimal customerPkId)
			throws Exception;

	public List<XrayRequest> getXrayHistory1(Date beginDate,
			Date endDate, String searchKey, boolean isDone, String searchKey1, BigDecimal employeePkId) throws Exception;

	public List<SubOrganization> listSubOrganizations(BigDecimal customerPkId,
			Date beginDate, Date endDate, boolean inspection,
			LoggedUser loggedUser) throws Exception;

	public List<SurveyQuestion> getSurveyQuestions(String groupId)
			throws Exception;

	public List<SurveyDtl> getSurveyDtl(BigDecimal requestPkId)
			throws Exception;

	public List<SurveyHdr> getSurveyHdr(BigDecimal customerPkId)
			throws Exception;

	public List<Inspection> getInspectionByCustomer(BigDecimal customerPkId, List<BigDecimal> employeePkId, List<BigDecimal> subOrgaPkId,
			BigDecimal currentEmployeePkId, boolean isMyInspection) throws Exception;
	
	public List<InspectionDtl> getInspectionDtlByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception;
	public List<CustomerMedicine> getCustomerMedicineByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception;
	public List<CustomerSurgery> getCustomerSurgeryByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception;
	public List<CustomerXray> getCustomerXrayByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception;
	
	public List<CustomerTreatment> getCustomerTreatmentByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception;
	
	public List<ExaminationRequestCompleted> getExaminationRequestCompletedByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, BigDecimal customerPkId) throws Exception;
	public List<ExaminationValueHdr> getExaminationResults(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, BigDecimal customerPkId, BigDecimal examinationPkId) throws Exception;
	public List<ExaminationRequestCompleted> getExaminationResultHeader(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId, BigDecimal customerPkId, BigDecimal examinationPkId) throws Exception;
	public List<ExaminationResults> getExaminationValueQuestions(BigDecimal examinationPkId) throws Exception;
	
	public List<ExaminationValueHdr> getExaminationValueHdrByExa(BigDecimal examinationPkId, List<BigDecimal> requestPkId) throws Exception;
	
	public InspectionForm getInspectionFormByType(BigDecimal inspectionPkId, String type) throws Exception;
	public CustomerPain getCustomerPainSingle(BigDecimal inspectionPkId) throws Exception;
	public CustomerPlan getCustomerPlanSingle(BigDecimal inspectionPkId) throws Exception;
	public CustomerQuestion getCustomerQuestionSingle(BigDecimal inspectionPkId) throws Exception;
	public CustomerInspection getCustomerInspectionSingle(BigDecimal inspectionPkId) throws Exception;
	public List<CustomerAttachment> getCustomerAttachment(List<BigDecimal> inspectionPkId) throws Exception;
	public List<CustomerAttachment> getCustomerAttachmentByType(BigDecimal inspectionPkId, String type) throws Exception;
	public List<CustomerAttachment> getCustomerAttachment(BigDecimal inspectionPkId) throws Exception;
	public List<CustomerPain> getCustomerPain(List<BigDecimal> inspectionPkId) throws Exception;
	public List<CustomerPlan> getCustomerPlan(List<BigDecimal> inspectionPkId) throws Exception;
	public List<CustomerQuestion> getCustomerQuestion(List<BigDecimal> inspectionPkId) throws Exception;
	public List<CustomerInspection> getCustomerInspection(List<BigDecimal> inspectionPkId) throws Exception;
	public List<InspectionForm> getCustomerEnt(List<BigDecimal> inspectionPkId) throws Exception;
	public InspectionForm getCustomerLung(BigDecimal inspectionPkId) throws Exception;
	public List<DoctorRecipe> getDoctorRecipe(BigDecimal employeePkId) throws Exception;
	public List<DoctorRecipeDtl> getDoctorRecipeDtl(BigDecimal doctorRecipePkId) throws Exception;
	public void saveDoctorRecipe(DoctorRecipe recipe, List<DoctorRecipeDtl> recipeDtl, LoggedUser lu) throws Exception;
	public void removeDiagnose(BigDecimal pkId) throws Exception;
	public List<Diagnose> getDiagnoseById(String id) throws Exception;
	public List<Diagnose> getDiagnoseByName(String name) throws Exception;
	public List<Diagnose> getTopDiagnose(BigDecimal employeePkId) throws Exception;
	public List<Employee> getListEmployeeBySubOrganizationPkId(BigDecimal subOrganizationPkId) throws Exception;
	public Gt15Pain  getCustomerSubOrganization(BigDecimal cutomerPkId,Date date,String subname)throws Exception;

	public List<Gt15Pain> getEmployeeSignature(BigDecimal inspectionPkId,Date date)throws Exception;
	public Inspection saveNewOrder(Inspection inspection, LoggedUser lu, List<InspectionDtl> inspectionDtls, List<CustomerDiagnose> customerDiagnoses, List<CustomerMedicine> customerMedicines,EmployeeRequest er) throws Exception;
	public List<InspectionDtl> getInspectionDtls(String type) throws Exception;
	public void saveEmployeeMemo(EmployeeMemo employeeMemo, LoggedUser lu) throws Exception;
	public List<EmployeeMemo> getPrivateMemo(BigDecimal employeePkId,BigDecimal customerPkId) throws Exception;
	public List<EmployeeMemo> getPublicMemo(BigDecimal customerPkId) throws Exception;
	public List<CustomerProblem> getCustomerProblem(BigDecimal customerPkId,int filterProblemList,int month) throws Exception;
	public String getSubOrgaTypePkId(BigDecimal subOrganizationPkId) throws Exception;
	public HashMap<String, Object> getLastInspectionDtlsByEmployee(BigDecimal employeePkId, String type) throws Exception;
	public HashMap<String, Object> getDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public HashMap<String, Object> getDtlByRecipePkId(BigDecimal pkId) throws Exception;
	public HashMap<String, Object> getDtlByConditionalPrescriptionPkId(BigDecimal pkId) throws Exception;
	public List<InspectionDtl> getCustomerTreatment(Date beginDate, Date endDate, BigDecimal customerPkId, String treatmentName,
			BigDecimal treatmentTypePkId) throws Exception;
	public List<InspectionDtl> getCustomerSurgery(Date beginDate, Date endDate, BigDecimal customerPkId, String surgeryName,
			BigDecimal surgeryTypePkId) throws Exception;
	public void saveCustomerPastHistory(CustomerPastHistory pastHistory, LoggedUser lu) throws Exception;
	public void saveCustomerMatter(CustomerMatter matter, LoggedUser lu) throws Exception;

	//public void saveCustomerSkin(CustomerSkin customerSkin, Inspection inspection) throws Exception;

	public  List<InspectionDetail>  getInspecionDtl(BigDecimal  inspectionPkId)  throws  Exception;
	public  List<Examination>  getExamination(List<BigDecimal>  pkId,Date d ) throws  Exception;
	public  List<CustomerSurgery> getCustomerSurgery(BigDecimal  pkId)  throws  Exception;
	public  List<Treatment>  getTreatment(List<BigDecimal>  pkId,Date date) throws  Exception;
	public  List<Xray>  getXray(List<BigDecimal>  pkId,Date d )  throws  Exception;
	public  List<Surgery>  getSurgery(List<BigDecimal>  pkId)  throws  Exception;
	public  List<InspectionForm>  getInspectionFormPrint(BigDecimal  inspectionPkId)  throws  Exception;
	
	public List<OrderedTreatment> getCustomerTreatments(Date beginDate, Date endDate,BigDecimal customerPkId ,String treatmentName, BigDecimal treatmentTypePkId ) throws Exception;

}
package hospital.businesslogic.interfaces;

import hospital.businessentity.ExaRequest;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.LoggedUser;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationDoctor;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationGroup;
import hospital.entity.ExaminationGroupDtl;
import hospital.entity.ExaminationLaborant;
import hospital.entity.ExaminationPrice;
import hospital.entity.ExaminationRequest;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.ExaminationTemplate;
import hospital.entity.ExaminationType;
import hospital.entity.ExaminationValueHdr;
import hospital.entity.ExaminationValueQuestion;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ILogicExaminationLocal {
	public List<ExaminationType> 	           	getExaminationTypeList() throws Exception;
	public List<Employee> 			           	getExaminationEmployeeList(int type) throws Exception;
	public List<Examination>		           	getExaminationList(BigDecimal examinationTypePkId,boolean isSetPrice) throws Exception;
	public List<Examination>		           	getExaminationList(String examinationSearchString,boolean isSetPrice) throws Exception;
	public List<ExaminationDtl>		           	getExaminationDtlList(BigDecimal examinationPkId) throws Exception;
	public ExaminationPrice			           	getExaminationPrice(BigDecimal examinationPkId) throws Exception;
	public List<ExaminationLaborant>           	getExaminationLaborantList(BigDecimal examinationPkId) throws Exception;
	public List<ExaminationDoctor>			   	getExaminationDoctorList(BigDecimal examinationPkId) throws Exception;
	public List<ExaminationGroup>   			getExaminationGroup(LoggedUser lu) throws Exception;
	public List<ExaminationGroupDtl> 			getExaminationGroupDtl(BigDecimal examinationGroupPkId) throws Exception;
	public List<Examination>					getExaminations(LoggedUser lu) throws Exception;
	public List<ExaminationRequest> 			getExaminationRequests(Date beginDate, Date endDate, String filterKey, BigDecimal examinationPkId, boolean doneStatus) throws Exception;
	public List<ExaRequest> 					getDrequests(BigDecimal examinationPkId, String filterKey, int mood, BigDecimal employeePkId) throws Exception;
	public List<Examination>					getExaminationsWithRequestCount(BigDecimal employeePkId) throws Exception;
	public List<ExaminationValueQuestion> 		getQuestions(ExaRequest er) throws Exception;
	public String 								getExaminationGroupId() throws Exception;
	public List<ExaminationValueHdr>			getHdrs(ExaRequest er) throws Exception;
	public List<ExaminationTemplate>			getTemplates() throws Exception;	
	public ExaRequest 							getRequest(BigDecimal requestPkId) throws Exception;
	public List<ExaminationValueQuestion>		getQuestions (BigDecimal examinationTemplatePkId) throws Exception;
	public boolean 								isDoctor(BigDecimal employeePkId) throws Exception;
	public List<String>							getElementNameMn() throws Exception;
	public List<String>							getElementNameEn() throws Exception;
	public List<Examination>					getEmrExaminationList(BigDecimal customerPkId) throws Exception;
	                                           
	public void 								saveExaminationRequests(List<ExaminationRequest> examinationRequests, LoggedUser lu) throws Exception;
	public void 								saveDrequest(List<ExaminationValueHdr> valueHdrs, LoggedUser lu, ExaRequest chosenExaRequest, int saveType) throws Exception;
	public void 								saveExaminationType(ExaminationType examinationType) throws Exception;
	public void 								saveExamination(Examination examination) throws Exception;
	public void 								saveExamination(Examination examination, List<ExaminationDtl> examinationDtls, ExaminationPrice examinationPrice,
												List<ExaminationLaborant> laborants,List<ExaminationDoctor> doctors, LoggedUser lu) throws Exception;	
	public void 								saveExaminationGroup(ExaminationGroup examinationGroup, List<ExaminationGroupDtl> examinationGroupDtls, LoggedUser lu) throws Exception;
	public void									saveExaminationValueHdrs(List<ExaminationValueHdr> valueHdrs, BigDecimal requestPkId) throws Exception;
	
}

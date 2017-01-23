package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import hospital.businessentity.LoggedUser;
import hospital.entity.ConditionalPrescription;
import hospital.entity.Customer;
import hospital.entity.Diagnose;
import hospital.entity.DiagnoseGroup;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequest;
import hospital.entity.Inspection;
import hospital.entity.Medicine;
import hospital.entity.Organization;
import hospital.entity.Post;
import hospital.entity.PostMap;
import hospital.entity.Role;
import hospital.entity.SubOrganization;
import hospital.entity.Surgery;
import hospital.entity.Treatment;
import hospital.entity.Users;
import hospital.entity.Xray;

@Local
public interface ILogicHomeLocal {
public   List<Organization> getOrganizations() throws Exception;
public   List<Role> getAccessLaw() throws Exception;
public   List<Users> getUsers() throws Exception;
public   List<SubOrganization> getRoom() throws Exception;
public   List<Employee> getEmployee() throws Exception;
public   List<Treatment> getTreatment() throws Exception;
public   List<Diagnose> getDiagnosist() throws Exception;

public   List<Customer> getCustomer() throws Exception;
////public   List<> getOrder() throws Exception;
public   List<Inspection> getInspection() throws Exception;
public   List<EmployeeRequest> getCahsierList() throws Exception;
public   List<Xray> getDiagnoseList() throws Exception;
//public   List<Organization> getReport() throws Exception;
public   List<Medicine> getMedicine() throws Exception;
public   List<Examination> getSurveyList() throws Exception;
public   List<DiagnoseGroup> getDiagnoseGroup() throws Exception;
public   List<Xray> getDiagnose() throws Exception;
public   List<Xray> getisDiagnose() throws Exception;
public   List<Examination> getSurveyPackage() throws Exception;
public   List<ConditionalPrescription> getConditionalPrescriptions() throws Exception;
public   List<ExaminationRequest> getSurveyRequest() throws Exception;
public   List<Surgery> getSurvey() throws Exception;
public   List<Surgery> getisActiveSurvey() throws Exception;
public  List<ExaminationRequest>  getExaminationRequestList() throws Exception;
public   List<ExaminationRequest> getExaminationRequestResult() throws Exception;
public   List<SubOrganization> getSelectionSubOrganization() throws Exception;
public   List<Employee> getSelectionEmployee( List<BigDecimal > keyValue) throws Exception;
public  void setPostSave(Post p,String text,BigDecimal bigDecimal,List<BigDecimal>  receiverPkIds,Date  time) throws Exception;
public List<Post> getPostView(BigDecimal id)throws Exception;
public List<Post>  getPostMapReceiverPkId(BigDecimal  pkId) throws  Exception;
public void setPostDeleteModify(Post  p)throws Exception;
public List<Post> getPostPostPkId(BigDecimal id)throws Exception;
public List<Post> getPostPostUpdate(BigDecimal id)throws Exception;
public List<Post> getPostPostPkIdView(BigDecimal id)throws Exception;
//
}

package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import org.json.simple.JSONObject;

import hospital.businessentity.InspectionDetail;
import hospital.entity.CustomerMedicine;
import hospital.entity.CustomerSurgery;
import hospital.entity.CustomerTreatment;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.Inspection;
import hospital.entity.InspectionForm;
import hospital.entity.Medicine;
import hospital.entity.Organization;
import hospital.entity.Surgery;
import hospital.entity.Treatment;
import hospital.report.Am10;
import hospital.report.Am11;
import hospital.report.Am13a;
import hospital.report.Am25;
import hospital.report.Am29;
import hospital.report.Am8;
import hospital.report.Am9a;
import hospital.report.Gt15Pain;

@Local
public interface ILogicMenuLocal {

	public List<Inspection> getInspections(BigDecimal customerPkId,BigDecimal  employee) throws Exception;
	public Am8 getAm8(BigDecimal inspectionPkId) throws Exception;
	public Am8 getAm8Cutomer(BigDecimal inspectionPkId) throws Exception;
	public List<ExaminationRequestCompleted>	getCompletedRequests(BigDecimal customerPkId) throws Exception;
	public List<Employee> getEmployees (BigDecimal soPkId) throws Exception; 
	public Employee getCursorEmployee(BigDecimal pkId) throws Exception;
	public Am9a getAm9a(BigDecimal inspectionPkId) throws Exception;
	public Am9a getAm9aDiagnose(BigDecimal employee  ,BigDecimal customer, BigDecimal  inspectionPkId) throws Exception;
	public Am10 getAm10(BigDecimal customerPkId) throws Exception;
	public Am11 getAm11(BigDecimal customerPkId) throws Exception;
	public Am11 getAm11Employee(BigDecimal employee) throws Exception;
	public Am11 getAm11EmployeeCustomer(BigDecimal employee,BigDecimal customer) throws Exception;
	public Am25 getAm25(BigDecimal customer) throws Exception;
	public Am25 getAm25Diagnose(BigDecimal employee ,BigDecimal customer) throws Exception;
	public Am29 getAm29Customer(BigDecimal customer) throws Exception;
	public List<Gt15Pain>  getCustomerSubOrganization(BigDecimal cutomerPkId)throws Exception;
	public Am29 getAm29Diagnose(BigDecimal employee ,BigDecimal customer) throws Exception;
//	public Am29 getAm29EmployeeId(BigDecimal employee ) throws Exception;
	public Am13a getAm13a(BigDecimal customerPkId) throws Exception;

}

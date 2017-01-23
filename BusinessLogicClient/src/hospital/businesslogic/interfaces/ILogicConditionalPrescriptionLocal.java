package hospital.businesslogic.interfaces;

import hospital.businessentity.LoggedUser;
import hospital.entity.ConditionalPrescription;
import hospital.entity.ConditionalPrescriptionDtl;
import hospital.entity.ConditionalPrescriptionSubOrganizationTypeMap;
import hospital.entity.CustomerMedicine;
import hospital.entity.ExaminationDtl;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.SubOrganizationType;
import hospital.entity.Treatment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicConditionalPrescriptionLocal {
	public String 						getCpId() throws Exception;
	public List<ConditionalPrescription> getCps(LoggedUser lu) throws Exception;
	public List<ConditionalPrescriptionDtl> getDtls(BigDecimal cpPkId ) throws Exception;
	public List<ConditionalPrescriptionSubOrganizationTypeMap> getMaps(BigDecimal cpPkId) throws Exception;
	
	
	
	public void saveCp(ConditionalPrescription conditionalPrescription, LoggedUser lu, List<ConditionalPrescriptionDtl> dtls, List<ConditionalPrescriptionSubOrganizationTypeMap> maps) throws Exception;

	public <T> List<T> getByAnyField(Class<T> type, String field, Object fieldValue) throws Exception;
	
	public Date getInspectionBeginDate(BigDecimal customerPkId) throws Exception;
	public Date getInspectionEndDate(BigDecimal customerPkId) throws Exception;
	public List<Inspection> getInspectionByDate(BigDecimal subOrganizationPkId, BigDecimal employeePkId, BigDecimal customerPkId, Date beginDate, Date endDate) throws Exception;
	public List<InspectionDtl> getExaminationInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public List<InspectionDtl> getXrayInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public List<InspectionDtl> getSurgeryInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public List<InspectionDtl> getTreatmentInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public List<CustomerMedicine> getMedicineInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public Treatment getTreatment(String type, String str) throws Exception;
	public List<ConditionalPrescription> getConditionalPrescriptions(BigDecimal subOrganizationPkId) throws Exception;
	public List<SubOrganizationType> getOrganizationTypes() throws Exception;
	public BigDecimal examinationDtlPrice(BigDecimal examinationPkId) throws Exception;
	public List<ExaminationDtl> getExaminationDtlsByExaminationPkId(BigDecimal examinationPkId) throws Exception;
	public BigDecimal getCost(String type, BigDecimal typePkId) throws Exception;
	public BigDecimal getSubOrganizationPkIdByEmployeePkId(BigDecimal employeePkId) throws Exception;
	public String getSubOrganizationNameByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
	public String getEmployeeNameByInspectionPkId(BigDecimal inspectionPkId) throws Exception;
}

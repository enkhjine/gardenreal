package hospital.businesslogic.interfaces;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.ReportFilter;
import hospital.entity.Xray;
import hospital.entity.Employee;
import hospital.entity.Treatment;
import hospital.report.DiscountPriceReport;
import hospital.report.DoctorService;
import hospital.report.InspectionTypeReport;
import hospital.report.ItemReportHdr;
import hospital.report.MedicalExaminationReport;
import hospital.report.ReportMonth;
import hospital.report.ReportSubOrganization;
import hospital.report.TreatmentRequestReport;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicReportLocal {
	public List<ItemReportHdr> getItemReport(BigDecimal subOrganizationPkId, BigDecimal itemPkId, Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<MedicalExaminationReport> getDoctorMedicalExaminationReport(BigDecimal subOrganizationPkId, Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<ReportMonth> getCashierMothReport(Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<DoctorService> getDoctorInspectionHistory(BigDecimal customerPkId, Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<DoctorService> getDoctorServices(Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<InspectionTypeReport> getInspectionTypeReport(ReportFilter filter) throws Exception; 
	public List<DoctorService> getDoctorServices(Date beginDate, Date endDate, BigDecimal employeePkId,	BigDecimal subOrganizationPkId) throws Exception;
	public List<DiscountPriceReport> getDiscountPriceReport(Date beginDate, Date endDate, LoggedUser loggedUser) throws Exception;
	public List<ReportSubOrganization> getReport8(int year, int month, int week) throws  Exception;
	public List<Treatment> getRequestTreatments() throws Exception;
	public List<TreatmentRequestReport> getTreatmentRequest(Date beginDate, Date endDate, BigDecimal treatmentPkId) throws Exception;
}

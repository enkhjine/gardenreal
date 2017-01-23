package hospital.businesslogic.interfaces;

import hospital.businessentity.InspectionHistory;
import hospital.businessentity.LoggedUser;
import hospital.entity.Customer;
import hospital.entity.Examination;
import hospital.entity.ExaminationType;
import hospital.entity.Xray;
import hospital.entity.XrayType;
import hospital.entity.EconomicCalendar;
import hospital.entity.EconomicCalendarDtl;
import hospital.entity.EconomicCalendarHdr;
import hospital.entity.Employee;
import hospital.entity.Item;
import hospital.entity.Payment;
import hospital.entity.SubOrganization;
import hospital.entity.Surgery;
import hospital.entity.SurgeryType;
import hospital.entity.Treatment;
import hospital.entity.ViewCustomer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicTwoLocal {
	public EconomicCalendar 			getEconomicCalendar(LoggedUser loggedUser) throws Exception;
	public EconomicCalendar 			getEconomicCalendar() throws Exception;
	public List<Treatment> 				getListTreatment() throws Exception;
	public List<EconomicCalendarDtl> 	getEconomicCalendarDtlByDate(Date beginDate, Date endDate, LoggedUser loggedUser, BigDecimal typePkId, int year, int month, int day) throws Exception;
	public List<EconomicCalendarHdr> 	getEconomicCalendarHdr(LoggedUser loggedUser, BigDecimal decimal) throws Exception;
	public List<EconomicCalendarHdr> 	getEmployeeEconomicCalendarHdr(LoggedUser loggedUser, BigDecimal decimal) throws Exception;
	public List<EconomicCalendarHdr> 	getEconomicCalendarListHdr(List<BigDecimal> employeePkIds, int year, int month, int day) throws Exception;
	public List<EconomicCalendarHdr>	getEconomicCalendarHdrs(BigDecimal customerPkId) throws Exception;
	
	
	public List<Employee> 				getEmployeesBySubOrganizationPkId(LoggedUser loggedUser, BigDecimal organizationPkId) throws Exception;
	public List<Employee> 				getEmployeeBySubOrganizationPkId(BigDecimal pkId) throws Exception;
	public List<Employee> 				getEmployeeReport(LoggedUser loggedUser) throws Exception;
	public List<ExaminationType> 		getExaminationTypes() throws Exception;
	public List<Examination> 			getExamination(BigDecimal examinationTypePkId) throws Exception;
	public List<SurgeryType>			getSurgeryType() throws Exception;
	public List<Surgery> 				getListSurgery(BigDecimal surgeryTypePkId) throws Exception;
	
	public List<InspectionHistory> 		getInspectionHistories(BigDecimal pkId) throws Exception;
	public Payment 						getPaymentByRequestPkId(BigDecimal employeeRequestPkId) throws Exception;
	public List<Item> 					getItems(LoggedUser loggedUser) throws Exception;
	public List<SubOrganization> 		getSubOrganizations(LoggedUser loggedUser) throws Exception;
	public <T> T 						getByPkId(Class<T> type, Object pkId) throws Exception;
	public String						getSystemConfigsByCustomerNo() throws Exception;
	
	public List<XrayType>				getXrayTypes() throws Exception;
	public List<Xray>					getXrays(BigDecimal xrayTypePkId) throws Exception;
	
	public boolean 						isDuplicateEconomicCalendar(EconomicCalendarHdr economicCalendarHdr) throws Exception;
	public boolean 						isDuplicateEconomicCalendarHdr(LoggedUser loggedUser, EconomicCalendarHdr calendarHdr) throws Exception;
	
	public void saveEconomicCalendar(LoggedUser loggedUser, EconomicCalendar calendar) throws Exception;
	public void saveEconomicCalendarHdr(LoggedUser loggedUser, EconomicCalendarHdr calendarHdr, List<Employee> employees) throws Exception;
	public void changePassword(LoggedUser loggedUser, String newPassword) throws Exception;
	public void deletetEmployeeCalendar(BigDecimal calendarPkId) throws Exception;
}

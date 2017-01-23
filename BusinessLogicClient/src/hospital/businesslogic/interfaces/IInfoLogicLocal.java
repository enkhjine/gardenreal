package hospital.businesslogic.interfaces;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface IInfoLogicLocal {
	public List<Aimag> 					getAimags() throws Exception;
	public List<Degree> 				getDegrees() throws Exception;
	public List<CustomerRequest> 		getEmployeeRequests(LoggedUser lu, Date beginDate, Date endDate) throws Exception;
	public List<Employee> 				getEmployees() throws Exception;
	public List<Soum> 					getSoums() throws Exception;
	public List<Soum> 					getSoums(BigDecimal aimagPkId) throws Exception;
	public List<Role> 					getRoles() throws Exception;
	public List<Menu> 					getMenus(LoggedUser loggedUser) throws Exception;
	public List<Menu> 					getMenus() throws Exception;
	public List<BigDecimal> 			getMenuPkIdByRole(BigDecimal rolePkId) throws Exception;
	public List<Menu> 					getMenusFilter(LoggedUser loggedUser) throws Exception;
	public List<UserRoleMap> 			getUserRoleMaps() throws Exception;
	public List<Users> 					getUsers(LoggedUser loggedUser) throws Exception;
	public List<Organization> 			getOrganizations() throws Exception;
	public List<SubOrganization> 		getSubOrganizations() throws Exception;
	public List<SubOrganizationType> 	getSubOrganizationTypes() throws Exception;
	public List<SubOrganizationType> 	getSubOrganizationTypes1() throws Exception;
	public List<Menu> 					getListMenus(LoggedUser loggedUser) throws Exception;
	public List<SubOrganization> 		getListSubOrganization() throws Exception;
	public String 						getEmployeeGeneratedId() throws Exception;
	public String						getGeneratedPkId() throws Exception;
	public SystemConfig 				getLicense() throws Exception;
	public List<BigDecimal> 			getRolePkIds(BigDecimal userPkId) throws Exception;	
	public boolean 						isRelated(BigDecimal pkId, int type) throws Exception;
	public List<Medicine>				getMedicine(BigDecimal atcPkId, LoggedUser lu, String filterKey) throws Exception;
	public List<View_ConstantATC>		getAtcs() throws Exception;
	public List<View_ConstantMedicineType> getMedicineTypes() throws Exception;
	public List<Measurement> 			getMeasurements() throws Exception;
	public String 						getGeneredMedicineId() throws Exception;
	public List<Diagnose>				getListDiagnose(int selectedPageNumber, String filterKey) throws Exception;
	public int 							getDiagnoseCount() throws Exception;
	public List<DiagnoseGroup>			getListDiagnoseGroup() throws Exception;
	public List<DiagnoseGroup>			getListDiagnoseGroup(LoggedUser loggedUser) throws Exception;
	public List<String>					getDiagnoseTypeList() throws Exception;
	public List<Medicine>				getAllMedicines() throws Exception;
	public List<Diagnose>				getDiagnoses(String diagnoseName) throws Exception;
	public List<Diagnose>				getDiagnoses(int first, int count, String sortField, String sortType, Map<String, String> filters) throws Exception;
	public List<Customer>				getCustomers(int first, int count, String sortField, String sortType, Map<String, String> filters) throws Exception;
	public long							getDiagnosesCount(Map<String, String> filters) throws Exception;
	public long							getCustomerCount(Map<String, String> filters) throws Exception;
	public UserConfig					getPanelPositions(LoggedUser lu) throws Exception;
	public List<TreatmentType>			getTreatmentTypes() throws Exception;
	public List<Treatment>				getTreatmentLsit(String search)  throws  Exception;
	public List<SurgeryType>			getSurgeryTypes() throws Exception;
	public boolean 						checkInspectionPassword(BigDecimal employeePkId, String inspectionPassword ) throws Exception;
	
	public void saveOrganization(Organization organization) throws Exception;
	public void saveRole(Role role) throws Exception;
	public void saveUser(Users user) throws Exception;
	public void saveSubOrganization(SubOrganization suborganization, LoggedUser lu) throws Exception;
	public void saveEmployee(Employee employee) throws Exception;
	public void saveLicense(SystemConfig config) throws Exception;
	public void saveMedicine(Medicine medicine, LoggedUser lu) throws Exception;
	public void saveDiagnoseGroup(DiagnoseGroup diagnoseGroup, List<DiagnoseGroupDtl> diagnoseGroupDtls, LoggedUser loggedUser) throws Exception;
	public void savePanelPositions(UserConfig userConfig, LoggedUser lu) throws Exception;
	//public void saveRequestCustomer(String id, BigDecimal customerPkId, BigDecimal employeePkId, Date date, int beginHour, int beginMinute, LoggedUser loggedUser) throws Exception;
}

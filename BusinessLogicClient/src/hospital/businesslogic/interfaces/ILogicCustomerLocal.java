package hospital.businesslogic.interfaces;


import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicCustomerLocal {
	public List<SubOrganization> getSubOrganizations() throws Exception;	
	public List <ViewConstantJob> getViewConstantJobs() throws Exception;
	//public List <Education> getEducations() throws Exception;
	public List <ViewConstantIsChild> getViewConstantIsChilds() throws Exception;
	public List <ViewConstantSocialStatus> getViewConstantSocialStatuss() throws Exception;
	public List<View_ConstantFamilyRelation> getFamilyRelations() throws Exception;
	public List<View_ConstantArrange> getArranges() throws Exception;
	public List <ViewConstantCountry> getViewConstantCountries() throws Exception;
	public List <Profession> getProfessions() throws Exception;
	public List<Customer> getCustomers(String filterKey) throws Exception;
	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, Date beginDate, Date endDate, int status, String filterKey, int typestatus,int intguest ) throws Exception;
	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu, Date beginDate, Date endDate, BigDecimal subOrganizationPkId, boolean self) throws Exception;
	public List<Payment> getCustomerPayment(LoggedUser lu, BigDecimal customerPkId) throws Exception;
	public Customer saveCustomer(Customer customer, LoggedUser lu) throws Exception;
	public <T> T getByPkId(Class<T> type, Object pkId) throws Exception;
	public <T> List<T> getByAnyField(Class<T> type, String field, Object fieldValue) throws Exception;
	public List<ExaminationRequestCompleted> getExaminationRequestCompleted(BigDecimal customerPkId, Date beginDate, Date endDate, String filterKey, BigDecimal examinationTypePkId) throws Exception;
	public List<ExaminationType> getExaminationTypesWithRequestCount(BigDecimal customerPkId) throws Exception;
	public boolean checkRegNumber(String regNumber) throws Exception; 
	
	public List<ViewCustomer> getCustomerReport() throws Exception;
	public List<Customer> getCustomers(BigDecimal organizationPkId) throws Exception;
	public List<Customer> getCustomers() throws Exception;
	public Customer getCustomerByRegNumber(String regNumber) throws Exception;
	
	
	public void reGenerateCardNumber() throws Exception;
}

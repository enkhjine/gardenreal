package hospital.businesslogic.interfaces;

import hospital.businessentity.CompletedTreatment;
import hospital.businessentity.CustomerRequest;
import hospital.businessentity.EmployeeRequestCount;
import hospital.businessentity.ExaminationDashboard;
import hospital.businessentity.ExaminationEmployeeDashboard;
import hospital.businessentity.IncomeBySubOrga;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentEntity;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IDashboardLogicLocal {
	public List<SubOrganization> getSubOrganizations() throws Exception;
	public List<Employee> getEmployees(BigDecimal subOrganizationPkId) throws Exception;	
	public List<IncomeBySubOrga> getDailyBillBySubOrganization(Date beginDate, Date endDate) throws Exception;
	public List<IncomeBySubOrga> getMonthlyBillBySubOrganization(Date beginDate, Date endDate) throws Exception;
	public List<ViewIncomePlan> getPlanHistory() throws Exception;
	public List<IncomePlan> getPlanHistory(BigDecimal subOrganizationPkId) throws Exception;
	public List<BigDecimal> getBillByEmployee(BigDecimal employeePkId, Date beginDate, Date endDate) throws Exception;
	public List<CompletedTreatment> getTreatmentCountBySubOrganization(BigDecimal subOrganizationPkId, Date beginDate, Date endDate) throws Exception;
	public List<Employee> getEmployeeWithInspectionCount(Date beginDate, Date endDate) throws Exception;
	public List<Employee> getPaymentByEmployee(BigDecimal subOrganizationPkId, Date beginDate, Date endDate) throws Exception;
	public void saveMonthlyPlan(IncomePlan incomePlan, LoggedUser lu) throws Exception;
	public List<ExaminationDashboard>  getExaminationCount(Date beginDate, Date endDate) throws Exception;
	public List<ExaminationEmployeeDashboard>  getEmployeeCount(BigDecimal examinationPkId, long allCustomerCount, Date beginDate, Date endDate) throws Exception;
	public List<LoginDialog> getLoginDialog(Date beginDate) throws Exception;
	public List<LoginDialog> setLoginDialog(BigDecimal dialogPkId) throws Exception;
	public List<PaymentDtl> getMonthlyBillByPaymentDtlType(Date beginDate, Date endDate) throws Exception;
	public List<EmployeeRequestCount> getEmployeeRequestCount(Date beginDate, Date endDate) throws Exception;
	public List<PaymentEntity> getPaymentDtlType(String pdt, Date beginDate, Date endDate) throws Exception;
	public void saveLoginDialog(LoginDialog lodialog, String comment ,Date  date) throws Exception;
	public void deleteLoginDialog(LoginDialog lodialog) throws Exception;
	public void updateLoginDialog(LoginDialog lodialog) throws Exception;
	public List<PaymentEntity> getTotalPaymentDtl(String pdt, Date beginDate, Date endDate) throws Exception;
	public List<EmployeeRequestCount> getSubOrganizationName(Date beginDate, Date endDate) throws Exception;
}

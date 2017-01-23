package hospital.businesslogic.interfaces;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentEntity;
import hospital.businessentity.PaymentInspection;
import hospital.entity.Customer;
import hospital.entity.InspectionDtl;
import hospital.entity.Xray;
import hospital.entity.Employee;
import hospital.entity.Payment;
import hospital.entity.PaymentHistory;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicPaymentLocal {
	public List<PaymentHistory> 		getPaymentHistorys(BigDecimal pkId) throws Exception;
	public Payment 						getPaymentBill(BigDecimal paymentPkId) throws Exception;
	public boolean 						isBackPaymentBill(BigDecimal paymentPkId) throws Exception;
	public Customer 					getCustomer(BigDecimal customerPkId) throws Exception;
	public void							billBack(BigDecimal paymentPkId, String password, String description) throws Exception;
	public String 						getPaymentId(LoggedUser loggedUser) throws Exception;
	public List<PaymentEntity> 			getPaymentList(Date beginDate, Date endDate) throws Exception;
	public List<PaymentEntity> 			getLoanPaymentList(LoggedUser loggedUser) throws Exception;
	public List<PaymentHistory> 		getPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception;
	public List<Employee> 				getEmployeeByPaymentHistorys(BigDecimal pkId, LoggedUser loggedUser) throws Exception;
	public List<PaymentHistory> 		getEmployeeLoanPayments(BigDecimal customerPkId) throws Exception;
	public List<PaymentHistory> 		getListPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception;
	public List<PaymentHistory> 		getLoanPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception;
	public List<Xray> 					getCashXrayBill(BigDecimal paymentPkId) throws Exception;
	public Employee						getDegreeByEmployee(BigDecimal employeePkId) throws Exception;
	public List<PaymentEntity>			getPaymentListByBetweenDate(Date beginDate, Date endDate) throws Exception;
	public List<InspectionDtl> 			getInspectionDtl(BigDecimal inspectionDtlPkId) throws Exception;
	public List<CashByInspectionDtl> 	getInspectionDtlByPaymentDtlPkId(BigDecimal inspectionDtlPkId) throws Exception;
	
	public BigDecimal savePayment(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listRequestByInspectionDtl, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception;
	public BigDecimal savePaymentByManyRequest(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listRequestByInspectionDtl, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception;
	public BigDecimal saveCashPayment(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception;
	public void savePaymentHistory(Payment payment, PaymentHistory history) throws Exception;
	
}

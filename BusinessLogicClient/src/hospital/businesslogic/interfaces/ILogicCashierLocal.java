package hospital.businesslogic.interfaces;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentInspection;
import hospital.businessentity.PaymentPackageSelectItem;
import hospital.businessentity.PaymentPackageSelectType;
import hospital.entity.Customer;

import hospital.entity.Inspection;
import hospital.entity.Xray;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.Payment;
import hospital.entity.PaymentPackage;
import hospital.entity.PaymentPackageDtl;
import hospital.entity.Treatment;
import hospital.entity.TreatmentType;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

@Local
public interface ILogicCashierLocal {
	public List<CashBusinessEntity> getCashHdrsByCustomerPkId(BigDecimal pkId, LoggedUser loggedUser) throws Exception;
	public List<Employee> getCashEmployeeRequest(BigDecimal employeePkId) throws Exception;
	public List<Employee> getEmployeeListByXray() throws Exception;
	public List<PaymentInspection> getCashInspections(BigDecimal customerPkId) throws Exception;
	public List<CashByInspectionDtl> getListXrayInspectionDtl(BigDecimal paymentPkId) throws Exception;
	public List<CashByInspectionDtl> getListRequestInspectionDtl(BigDecimal paymentPkId) throws Exception;
	public List<CashByInspectionDtl> getListExaminationInspectionDtl(BigDecimal paymentPkId) throws Exception;
	public List<CashByInspectionDtl> getListTreatmentInspectionDtl(BigDecimal paymentPkId) throws Exception;
	public List<CashByInspectionDtl> getListSurgeryInspectionDtl(BigDecimal paymentPkId) throws Exception;
	public Payment getCashPayment(BigDecimal paymentPkId) throws Exception;
	public Customer getCustomerByRequestPkId(BigDecimal employeeRequestPkId) throws Exception;
	public List<CashByInspectionDtl> getListCashByInspectionDtl(BigDecimal employeeRequestPkId) throws Exception;
	public CashByInspectionDtl getRequestGyCashInspectionDtl(BigDecimal employeePkId, int isSecond) throws Exception;
	public List<Inspection> getInspectionHistoryByCustomer(BigDecimal customerPkId) throws Exception;
	public BigDecimal getPaymentPkIdByEmployeeRequestPkId(BigDecimal employeeRequestPkId) throws Exception;
	public List<TreatmentType> getTreatmentType() throws Exception;
	public List<Treatment> getTreatments(BigDecimal treatmentTypePkId) throws Exception;
	public List<PaymentPackage> getListPaymentPackage() throws Exception;
	public List<PaymentPackageSelectType> getPackageSelectTypes(String type) throws Exception;
	public List<PaymentPackageSelectItem> getPackageSelectItems(String type, BigDecimal typePkId) throws Exception;
	public PaymentPackage getPaymentPackageByPkId(BigDecimal paymentPackagePkId) throws Exception;
	public List<PaymentPackageDtl> getPaymentPackageDtlsByPaymentPackagePkId(BigDecimal paymentPackagePkId) throws Exception;
	
	public void savePaymentPackage(PaymentPackage paymentPackage, List<PaymentPackageDtl> paymentPackageDtls, LoggedUser loggedUser) throws Exception;
	public void deletePaymentPackage(BigDecimal paymentPackagePkId) throws Exception;
}

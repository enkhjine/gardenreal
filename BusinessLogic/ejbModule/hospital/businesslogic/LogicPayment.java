package hospital.businesslogic;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.EmployeeRequestEntity;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentEntity;
import hospital.businessentity.PaymentInspection;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicPaymentLocal;
import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerTreatment;
import hospital.entity.Degree;
import hospital.entity.DegreePriceHistory;
import hospital.entity.ElementPrice;
import hospital.entity.ExaminationRequest;
import hospital.entity.ExaminationRequestDtl;
import hospital.entity.Xray;
import hospital.entity.XrayRequest;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.EmployeeRequestPaymentMap;
import hospital.entity.ExaminationDtl;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.InspectionDtlExaminationDtlMap;
import hospital.entity.Payment;
import hospital.entity.PaymentDtl;
import hospital.entity.PaymentHistory;
import hospital.entity.PriceHistory;
import hospital.entity.ReturnPayment;
import hospital.entity.ReturnPaymentDtl;
import hospital.entity.ReturnPaymentHistory;
import hospital.entity.SubOrganization;
import hospital.entity.Treatment;
import hospital.entity.ViewCustomer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.plaf.FontUIResource;

import org.hibernate.sql.Update;

import sun.nio.cs.HistoricallyNamedCharset;
import logic.data.CustomHashMap;
import logic.data.Tools;
//subotnik
/*import hospital.entity.EmployeeRequestPaymentMap;*/

@Stateless(name = "LogicPayment", mappedName = "hospital.businesslogic.LogicPayment")
public class LogicPayment extends logic.SuperBusinessLogic implements hospital.businesslogic.interfaces.ILogicPayment, ILogicPaymentLocal {

	@Resource
	SessionContext sessionContext;
	
	public LogicPayment() {
		
	}

	public void setDataBaseInfo() throws Exception {
		//dataBaseName = "Hospital"
	}
	
	public List<PaymentEntity> getPaymentListByBetweenDate(Date beginDate, Date endDate) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.PaymentEntity(b, a) FROM Payment a ");
		jpql.append("LEFT JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("WHERE a.date BETWEEN :beginDate AND :endDate ");
		jpql.append("AND b.pkId IS NOT NULL ");
		jpql.append("ORDER BY a.date DESC ");
		
		return getByQuery(PaymentEntity.class, jpql.toString(), parameters);
	}
	
	public BigDecimal getDegreePriceByDegreePkId(BigDecimal degreePkId, int isSecond) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("dte", new Date());
		parameters.put("degreePkId", degreePkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM Degree b ");
		jpql.append("INNER JOIN DegreePriceHistory c ON b.pkId = c.degreePkId ");
		jpql.append("WHERE c.priceUsageDate < :dte ");
		jpql.append("AND b.pkId = :degreePkId ");
		jpql.append("ORDER BY c.priceUsageDate DESC ");
		List<DegreePriceHistory> degreePriceHistories = getByQuery(DegreePriceHistory.class, jpql.toString(), parameters);
		if(degreePriceHistories.size() > 0) {
			if(isSecond == 1) {
				return degreePriceHistories.get(0).getRePrice();
			}else {
				return degreePriceHistories.get(0).getPrice();
			}
		}
		
		return BigDecimal.ZERO;
	}
	
	public BigDecimal saveCashPayment(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception{
		List<PaymentDtl> paymentDtls = new ArrayList<PaymentDtl>();
		List<XrayRequest> xrayRequests = new ArrayList<XrayRequest>();
		List<CustomerTreatment> customerTreatments = new ArrayList<CustomerTreatment>();
		List<InspectionDtl> inspectionDtls = new ArrayList<InspectionDtl>();
		List<ExaminationRequest> examinationRequests = new ArrayList<ExaminationRequest>();
		List<ExaminationRequestDtl> examinationRequestDtls = new ArrayList<ExaminationRequestDtl>();
		PaymentHistory history = new PaymentHistory();
		
		BigDecimal pkId = Tools.newPkId();
		BigDecimal examPkId = Tools.newDecimal10();
		Date dte = new Date();
		BigDecimal examinationRequestDtlPkId = Tools.newPkId();
		
		payment.setPkId(pkId);
		payment.setId(getPaymentId(loggedUser));
		payment.setDate(dte);
		payment.setOrganizationPkId(loggedUser.getOrganization().getPkId());
		payment.setDiscountAmount(payment.getBasicAmount().subtract(Tool.calculateDiscountAmount(payment.getBasicAmount(), payment.getDiscountPercent())));
		payment.setCreatedBy(loggedUser.getUser().getPkId());
		payment.setCreatedDate(dte);
		payment.setUpdatedBy(loggedUser.getUser().getPkId());
		payment.setUpdatedDate(dte);
		
		pkId = pkId.add(BigDecimal.ONE);
		history.setPkId(pkId);
		history.setPaymentPkId(payment.getPkId());
		history.setDate(dte);
		history.setCashInAmount(payment.getCashInAmount());
		history.setCardInAmount(payment.getCardInAmount());
		
		if(listTreatmentCashByInspectionDtl != null && listTreatmentCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
				if(dtl.getUserCount()+dtl.getTreatmentCount() == 0) continue;
				InspectionDtl dtl2 = getByPkId(InspectionDtl.class, dtl.getInspectionDtlPkId());
				if(dtl2 != null) {
					dtl2.setUsed(dtl.getUserCount() + dtl.getTreatmentCount());
					inspectionDtls.add(dtl2);
				}
				
				CustomerTreatment customerTreatment = new CustomerTreatment();
				pkId = pkId.add(BigDecimal.ONE);
				customerTreatment.setPkId(pkId);
				customerTreatment.setInspectionPkId(dtl.getInspectionPkId());
				customerTreatment.setTreatmentPkId(dtl.getTypePkId());
				customerTreatment.setPaymentPkId(payment.getPkId());
				customerTreatment.setInspectionDtlPkId(dtl.getInspectionDtlPkId());
				customerTreatment.setTimes(dtl.getTreatmentCount());
				customerTreatment.setCustomerPkId(payment.getCustomerPkId());
				customerTreatment.setQty(dtl.getQty());
				customerTreatment.setDayLength(dtl.sumCount());
				customerTreatment.setDescription(dtl.getDescription());
				customerTreatment.setCreatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setCreatedDate(dte);
				customerTreatment.setUpdatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setUpdatedDate(dte);
				
				customerTreatments.add(customerTreatment);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getAmount());
				paymentDtl.setDiscountAmount(dtl.getAmount().subtract(Tool.calculateDiscountAmount(dtl.getAmount(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listXrayCashByInspectionDtl != null && listXrayCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				XrayRequest xrayRequest = new XrayRequest();
				pkId = pkId.add(BigDecimal.ONE);
				xrayRequest.setPkId(pkId);
				xrayRequest.setCustomerPkId(payment.getCustomerPkId());
				xrayRequest.setEmployeePkId(loggedUser.getEmployeePkId());
				xrayRequest.setRequestPkId(payment.getEmployeeRequestPkId());
				xrayRequest.setRequestDate(dte);
				xrayRequest.setXrayPkId(dtl.getTypePkId());
				xrayRequest.setPaymentPkId(payment.getPkId());
				xrayRequest.setXrayEmployeePkId(dtl.getXrayEmployeePkId());
				
				xrayRequests.add(xrayRequest);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listSurgeryCashByInspectionDtl != null && listSurgeryCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listExamnationCashByInspectionDtl != null && listExamnationCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				ExaminationRequest examRequest = new ExaminationRequest();
				examPkId = examPkId.add(BigDecimal.ONE);
				examRequest.setPkId(examPkId);
				examRequest.setExaminationPkId(dtl.getTypePkId());
				examRequest.setCustomerPkId(payment.getCustomerPkId());
				examRequest.setRequestDate(dte);
				examRequest.setMood(0);
				examRequest.setPaymentPkId(payment.getPkId());
				examRequest.setEmployeePkId(loggedUser.getEmployeePkId());
				examRequest.setCreatedBy(loggedUser.getUser().getPkId());
				examRequest.setCreatedDate(dte);
				examRequest.setUpdatedBy(loggedUser.getUser().getPkId());
				examRequest.setUpdatedDate(dte);
				
				examinationRequests.add(examRequest);
				
				for(ExaminationDtl examinationDtl : dtl.getExaminationDtls()){
					ExaminationRequestDtl examinationRequestDtl = new ExaminationRequestDtl();
					examinationRequestDtlPkId = examinationRequestDtlPkId.add(BigDecimal.ONE);
					examinationRequestDtl.setPkId(examinationRequestDtlPkId);
					examinationRequestDtl.setExaminationRequestPkId(examRequest.getPkId());
					examinationRequestDtl.setExaminationDtlPkId(examinationDtl.getPkId());
					examinationRequestDtls.add(examinationRequestDtl);
				}
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		
		if(inspectionDtls.size() > 0) update(inspectionDtls);
		if(xrayRequests.size() > 0) insert(xrayRequests);
		if(customerTreatments.size() > 0) insert(customerTreatments);
		if(examinationRequests.size() > 0) insert(examinationRequests);
		if(examinationRequestDtls.size() > 0) insert(examinationRequestDtls);
		insert(payment);
		insert(history);
		insert(paymentDtls);
		return payment.getPkId();
	}
	
	public BigDecimal savePaymentByManyRequest(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listRequestByInspectionDtl, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception{
		List<PaymentDtl> paymentDtls = new ArrayList<PaymentDtl>();
		List<XrayRequest> xrayRequests = new ArrayList<XrayRequest>();
		List<CustomerTreatment> customerTreatments = new ArrayList<CustomerTreatment>();
		List<InspectionDtl> inspectionDtls = new ArrayList<InspectionDtl>();
		List<ExaminationRequest> examinationRequests = new ArrayList<ExaminationRequest>();
		List<ExaminationRequestDtl> examinationRequestDtls = new ArrayList<ExaminationRequestDtl>();
		List<EmployeeRequestPaymentMap> maps = new ArrayList<EmployeeRequestPaymentMap>();
		PaymentHistory paymentHistory = new PaymentHistory();
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		BigDecimal pkId = Tools.newPkId();
		BigDecimal examRequestPkId = Tools.newDecimal10();
		Date dte = new Date();
		BigDecimal examinationRequestDtlPkId = Tools.newPkId();
		
		parameters.put("employeeRequestPkIds", payment.getEmployeeRequestPkIds());
		jpql.append("SELECT a FROM EmployeeRequest a ");
		jpql.append("WHERE a.pkId IN :employeeRequestPkIds ");
		
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		
		payment.setPkId(pkId);
		payment.setDate(dte);
		payment.setId(getPaymentId(loggedUser));
		payment.setUpdatedBy(loggedUser.getUser().getPkId());
		payment.setUpdatedDate(dte);

		pkId = pkId.add(BigDecimal.ONE);
		paymentHistory.setPkId(pkId);
		paymentHistory.setCashInAmount(payment.getCashInAmount());
		paymentHistory.setCardInAmount(payment.getCardInAmount());
		paymentHistory.setDate(dte);
		
		paymentHistory.setPaymentPkId(payment.getPkId());
		
		if(listRequestByInspectionDtl != null && listRequestByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listRequestByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				if(dtl.isPayment()) continue;				
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(Tool.INSPECTIONPAYMENT);
				paymentDtl.setTypePkId(BigDecimal.ZERO);
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listTreatmentCashByInspectionDtl != null && listTreatmentCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
				if(dtl.getUserCount()+dtl.getTreatmentCount() == 0) continue;
				InspectionDtl dtl2 = getByPkId(InspectionDtl.class, dtl.getInspectionDtlPkId());
				if(dtl2 != null) {
					dtl2.setUsed(dtl.getUserCount() + dtl.getTreatmentCount());
					inspectionDtls.add(dtl2);
				}
				
				CustomerTreatment customerTreatment = new CustomerTreatment();
				pkId = pkId.add(BigDecimal.ONE);
				customerTreatment.setPkId(pkId);
				customerTreatment.setInspectionPkId(dtl.getInspectionPkId());
				customerTreatment.setTreatmentPkId(dtl.getTypePkId());
				customerTreatment.setPaymentPkId(payment.getPkId());
				customerTreatment.setInspectionDtlPkId(dtl.getInspectionDtlPkId());
//				customerTreatment.setEmployeePkId(employeeRequest.getEmployeePkId());
				customerTreatment.setCustomerPkId(payment.getCustomerPkId());
//				customerTreatment.setDoneCount(0);
				customerTreatment.setTimes(dtl.getTreatmentCount());
				customerTreatment.setQty(dtl.getQty());
				customerTreatment.setDayLength(dtl.sumCount());
				customerTreatment.setDescription(dtl.getDescription());
				customerTreatment.setCreatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setCreatedDate(dte);
				customerTreatment.setUpdatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setUpdatedDate(dte);
				
				customerTreatments.add(customerTreatment);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getAmount());
				paymentDtl.setDiscountAmount(dtl.getAmount().subtract(Tool.calculateDiscountAmount(dtl.getAmount(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listXrayCashByInspectionDtl != null && listXrayCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				XrayRequest xrayRequest = new XrayRequest();
				pkId = pkId.add(BigDecimal.ONE);
				xrayRequest.setPkId(pkId);
				xrayRequest.setCustomerPkId(payment.getCustomerPkId());
				xrayRequest.setEmployeePkId(dtl.getEmployeePkId());
				xrayRequest.setRequestPkId(payment.getEmployeeRequestPkId());
				xrayRequest.setRequestDate(dte);
				xrayRequest.setXrayPkId(dtl.getTypePkId());
				xrayRequest.setPaymentPkId(payment.getPkId());
				xrayRequest.setXrayEmployeePkId(dtl.getXrayEmployeePkId());
				
				xrayRequests.add(xrayRequest);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listSurgeryCashByInspectionDtl != null && listSurgeryCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
//				ExaminationRequest examRequest = new ExaminationRequest();
//				pkId = pkId.add(BigDecimal.ONE);
//				examRequest.setPkId(pkId);
//				examRequest.setExaminationPkId(dtl.getTypePkId());
//				examRequest.setCustomerPkId(payment.getCustomerPkId());
//				examRequest.setRequestDate(dte);
//				examRequest.setMood(0);
//				examRequest.setPaymentPkId(payment.getPkId());
//				examRequest.setEmployeePkId(dtl.getEmployeePkId());
//				examRequest.setCreatedBy(loggedUser.getUser().getPkId());
//				examRequest.setCreatedDate(dte);
//				examRequest.setUpdatedBy(loggedUser.getUser().getPkId());
//				examRequest.setUpdatedDate(dte);
//				
//				examinationRequests.add(examRequest);
//				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listExamnationCashByInspectionDtl != null && listExamnationCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				ExaminationRequest examRequest = new ExaminationRequest();
				examRequestPkId = examRequestPkId.add(BigDecimal.ONE);
				examRequest.setPkId(examRequestPkId);
				examRequest.setExaminationPkId(dtl.getTypePkId());
				examRequest.setCustomerPkId(payment.getCustomerPkId());
				examRequest.setRequestDate(dte);
				examRequest.setMood(0);
				examRequest.setPaymentPkId(payment.getPkId());
				examRequest.setEmployeePkId(dtl.getEmployeePkId());
				examRequest.setCreatedBy(loggedUser.getUser().getPkId());
				examRequest.setCreatedDate(dte);
				examRequest.setUpdatedBy(loggedUser.getUser().getPkId());
				examRequest.setUpdatedDate(dte);
				
				examinationRequests.add(examRequest);
				
				for(ExaminationDtl examinationDtl : dtl.getExaminationDtls()){
					ExaminationRequestDtl examinationRequestDtl = new ExaminationRequestDtl();
					examinationRequestDtlPkId = examinationRequestDtlPkId.add(BigDecimal.ONE);
					examinationRequestDtl.setPkId(examinationRequestDtlPkId);
					examinationRequestDtl.setExaminationRequestPkId(examRequest.getPkId());
					examinationRequestDtl.setExaminationDtlPkId(examinationDtl.getPkId());
					examinationRequestDtls.add(examinationRequestDtl);
				}
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		
		for(EmployeeRequest request : employeeRequests){
			EmployeeRequestPaymentMap employeeRequestPaymentMap = new EmployeeRequestPaymentMap();
			pkId = pkId.add(BigDecimal.ONE);
			employeeRequestPaymentMap.setPkId(pkId);
			employeeRequestPaymentMap.setPaymentPkId(payment.getPkId());
			employeeRequestPaymentMap.setEmployeeRequestPkId(request.getPkId());
			maps.add(employeeRequestPaymentMap);
		}
		
		if(inspectionDtls.size() > 0) update(inspectionDtls);
		if(xrayRequests.size() > 0) insert(xrayRequests);
		if(customerTreatments.size() > 0) insert(customerTreatments);
		if(examinationRequests.size() > 0) insert(examinationRequests);
		if(examinationRequestDtls.size() > 0) insert(examinationRequestDtls);
		insert(payment);
		insert(paymentDtls);
		insert(paymentHistory);
		for (EmployeeRequest employeeRequest : employeeRequests) {
			List<Inspection> inspections = getByAnyField(Inspection.class, "requestPkId", employeeRequest.getPkId());
			if(inspections.size() > 0){
				EmployeeRequestHistory employeeRequestHistory = new EmployeeRequestHistory();
				pkId = pkId.add(BigDecimal.ONE);
				employeeRequestHistory.setPkId(employeeRequest.getPkId());
				employeeRequestHistory.setId(employeeRequest.getId());
				employeeRequestHistory.setOrganizationPkId(employeeRequest.getOrganizationPkId());
				employeeRequestHistory.setCustomerPkId(employeeRequest.getCustomerPkId());
				employeeRequestHistory.setEmployeePkId(employeeRequest.getEmployeePkId());
				employeeRequestHistory.setDescription(employeeRequest.getDescription());
				employeeRequestHistory.setMood(employeeRequest.getMood());
				employeeRequestHistory.setReInspection(employeeRequest.getReInspection());
				employeeRequestHistory.setBeginDate(employeeRequest.getBeginDate());
				employeeRequestHistory.setEndDate(employeeRequest.getEndDate());
				employeeRequestHistory.setPaymentPkId(null);
				employeeRequestHistory.setCreatedBy(loggedUser.getUser().getPkId());
				employeeRequestHistory.setCreatedDate(dte);
				employeeRequestHistory.setUpdatedBy(loggedUser.getUser().getPkId());
				employeeRequestHistory.setUpdatedDate(dte);
				employeeRequestHistory.setHasPayment(1);
				employeeRequestHistory.setIsInsurance(employeeRequest.getInsurance());
				employeeRequestHistory.setIsExpress(employeeRequest.getIsExpress());
				employeeRequestHistory.setSaveMood(employeeRequest.getSaveMood());
				employeeRequestHistory.setGuest(employeeRequest.getGuest());
			
				deleteByPkId(EmployeeRequest.class, employeeRequest.getPkId());
				insert(employeeRequestHistory);
			}else {
				employeeRequest.setHasPayment(1);
				update(employeeRequest);
			}
		}
		insert(maps);
		return payment.getPkId();
	}
	
	public BigDecimal savePayment(Payment payment, LoggedUser loggedUser, List<CashByInspectionDtl> listRequestByInspectionDtl, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) throws Exception{
		List<PaymentDtl> paymentDtls = new ArrayList<PaymentDtl>();
		List<XrayRequest> xrayRequests = new ArrayList<XrayRequest>();
		List<CustomerTreatment> customerTreatments = new ArrayList<CustomerTreatment>();
		List<InspectionDtl> inspectionDtls = new ArrayList<InspectionDtl>();
		List<ExaminationRequest> examinationRequests = new ArrayList<ExaminationRequest>();
		List<ExaminationRequestDtl> examinationRequestDtls = new ArrayList<ExaminationRequestDtl>();
		PaymentHistory paymentHistory = new PaymentHistory();
		EmployeeRequestPaymentMap map = new EmployeeRequestPaymentMap();
		
		EmployeeRequest employeeRequest = getByPkId(EmployeeRequest.class, payment.getEmployeeRequestPkId());
		Employee employee = getByPkId(Employee.class, employeeRequest.getEmployeePkId());
		Degree degree = getByPkId(Degree.class, employee.getDegreePkId());
		degree.setPrice(getDegreePriceByDegreePkId(degree.getPkId(), employeeRequest.getReInspection()));
		BigDecimal pkId = Tools.newPkId();
		BigDecimal examRequestPkId = Tools.newDecimal10();
		Date dte = new Date();
		BigDecimal examinationRequestDtlPkId = Tools.newPkId();

		pkId = pkId.add(BigDecimal.ONE);
		paymentHistory.setPkId(pkId);
		paymentHistory.setCashInAmount(payment.getCashInAmount());
		paymentHistory.setCardInAmount(payment.getCardInAmount());
		paymentHistory.setDate(dte);
		
//		if(payment.getPkId() != null && payment.getPkId().compareTo(BigDecimal.ZERO) != 0) {
//			payment.setUpdatedBy(loggedUser.getUser().getPkId());
//			payment.setUpdatedDate(dte);
//			
//			payment.setDiscountAmount(payment.getBasicAmount().subtract(Tool.calculateDiscountAmount(payment.getBasicAmount(), payment.getDiscountPercent())));
//			payment.setBasicAmount(payment.getBasicAmount().add(degree.getPrice()));
//			payment.setAmount(payment.getAmount().add(degree.getPrice()));
//			payment.setPaidAmount(payment.getPaidAmount().add(degree.getPrice()));
//		}else {
			payment.setPkId(pkId);
			payment.setId(getPaymentId(loggedUser));
			payment.setDate(dte);
			payment.setCreatedBy(loggedUser.getUser().getPkId());
			payment.setCreatedDate(dte);
			payment.setDiscountAmount(payment.getBasicAmount().subtract(Tool.calculateDiscountAmount(payment.getBasicAmount(), payment.getDiscountPercent())));
		if(employeeRequest.getHasPayment() == 0) {
			PaymentDtl paymentDtl = new PaymentDtl();
			pkId = pkId.add(BigDecimal.ONE);
			paymentDtl.setPkId(pkId);
			paymentDtl.setPaymentPkId(payment.getPkId());
			paymentDtl.setEmployeePkId(employee.getPkId());
			paymentDtl.setType(Tool.INSPECTIONPAYMENT);
			paymentDtl.setTypePkId(BigDecimal.ZERO);
			paymentDtl.setAmount(degree.getPrice());
			paymentDtl.setDiscountAmount(degree.getPrice().subtract(Tool.calculateDiscountAmount(degree.getPrice(), payment.getDiscountPercent())));
			paymentDtls.add(paymentDtl);
		}
//		}
		payment.setUpdatedBy(loggedUser.getUser().getPkId());
		payment.setUpdatedDate(dte);
		
		paymentHistory.setPaymentPkId(payment.getPkId());
		
		if(listTreatmentCashByInspectionDtl != null && listTreatmentCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
				if(dtl.getUserCount()+dtl.getTreatmentCount() == 0) continue;
				InspectionDtl dtl2 = getByPkId(InspectionDtl.class, dtl.getInspectionDtlPkId());
				if(dtl2 != null) {
					dtl2.setUsed(dtl.getUserCount() + dtl.getTreatmentCount());
					inspectionDtls.add(dtl2);
				}
				
				CustomerTreatment customerTreatment = new CustomerTreatment();
				pkId = pkId.add(BigDecimal.ONE);
				customerTreatment.setPkId(pkId);
				customerTreatment.setInspectionPkId(dtl.getInspectionPkId());
				customerTreatment.setTreatmentPkId(dtl.getTypePkId());
				customerTreatment.setPaymentPkId(payment.getPkId());
				customerTreatment.setInspectionDtlPkId(dtl.getInspectionDtlPkId());
//				customerTreatment.setEmployeePkId(employeeRequest.getEmployeePkId());
				customerTreatment.setCustomerPkId(payment.getCustomerPkId());
//				customerTreatment.setDoneCount(0);
				customerTreatment.setTimes(dtl.getTreatmentCount());
				customerTreatment.setQty(dtl.getQty());
				customerTreatment.setDayLength(dtl.sumCount());
				customerTreatment.setDescription(dtl.getDescription());
				customerTreatment.setCreatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setCreatedDate(dte);
				customerTreatment.setUpdatedBy(loggedUser.getUser().getPkId());
				customerTreatment.setUpdatedDate(dte);
				
				customerTreatments.add(customerTreatment);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getAmount());
				paymentDtl.setDiscountAmount(dtl.getAmount().subtract(Tool.calculateDiscountAmount(dtl.getAmount(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listXrayCashByInspectionDtl != null && listXrayCashByInspectionDtl.size() > 0) {
			for(CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				XrayRequest xrayRequest = new XrayRequest();
				pkId = pkId.add(BigDecimal.ONE);
				xrayRequest.setPkId(pkId);
				xrayRequest.setCustomerPkId(payment.getCustomerPkId());
				xrayRequest.setEmployeePkId(employeeRequest.getEmployeePkId());
				xrayRequest.setRequestPkId(payment.getEmployeeRequestPkId());
				xrayRequest.setRequestDate(dte);
				xrayRequest.setXrayPkId(dtl.getTypePkId());
				xrayRequest.setPaymentPkId(payment.getPkId());
				xrayRequest.setXrayEmployeePkId(dtl.getXrayEmployeePkId());
				
				xrayRequests.add(xrayRequest);
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listSurgeryCashByInspectionDtl != null && listSurgeryCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
//				ExaminationRequest examRequest = new ExaminationRequest();
//				pkId = pkId.add(BigDecimal.ONE);
//				examRequest.setPkId(pkId);
//				examRequest.setExaminationPkId(dtl.getTypePkId());
//				examRequest.setCustomerPkId(payment.getCustomerPkId());
//				examRequest.setRequestDate(dte);
//				examRequest.setMood(0);
//				examRequest.setPaymentPkId(payment.getPkId());
//				examRequest.setEmployeePkId(dtl.getEmployeePkId());
//				examRequest.setCreatedBy(loggedUser.getUser().getPkId());
//				examRequest.setCreatedDate(dte);
//				examRequest.setUpdatedBy(loggedUser.getUser().getPkId());
//				examRequest.setUpdatedDate(dte);
//				
//				examinationRequests.add(examRequest);
//				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		if(listExamnationCashByInspectionDtl != null && listExamnationCashByInspectionDtl.size() > 0) {
			for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				ExaminationRequest examRequest = new ExaminationRequest();
				examRequestPkId = examRequestPkId.add(BigDecimal.ONE);
				examRequest.setPkId(examRequestPkId);
				examRequest.setExaminationPkId(dtl.getTypePkId());
				examRequest.setCustomerPkId(payment.getCustomerPkId());
				examRequest.setRequestDate(dte);
				examRequest.setMood(0);
				examRequest.setPaymentPkId(payment.getPkId());
				examRequest.setEmployeePkId(employeeRequest.getEmployeePkId());
				examRequest.setCreatedBy(loggedUser.getUser().getPkId());
				examRequest.setCreatedDate(dte);
				examRequest.setUpdatedBy(loggedUser.getUser().getPkId());
				examRequest.setUpdatedDate(dte);
				
				examinationRequests.add(examRequest);
				
				for(ExaminationDtl examinationDtl : dtl.getExaminationDtls()){
					ExaminationRequestDtl examinationRequestDtl = new ExaminationRequestDtl();
					examinationRequestDtlPkId = examinationRequestDtlPkId.add(BigDecimal.ONE);
					examinationRequestDtl.setPkId(examinationRequestDtlPkId);
					examinationRequestDtl.setExaminationRequestPkId(examRequest.getPkId());
					examinationRequestDtl.setExaminationDtlPkId(examinationDtl.getPkId());
					examinationRequestDtls.add(examinationRequestDtl);
				}
				
				PaymentDtl paymentDtl = new PaymentDtl();
				pkId = pkId.add(BigDecimal.ONE);
				paymentDtl.setPkId(pkId);
				paymentDtl.setPaymentPkId(payment.getPkId());
				paymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				paymentDtl.setType(dtl.getType());
				paymentDtl.setTypePkId(dtl.getTypePkId());
				paymentDtl.setAmount(dtl.getPrice());
				paymentDtl.setDiscountAmount(dtl.getPrice().subtract(Tool.calculateDiscountAmount(dtl.getPrice(), payment.getDiscountPercent())));
				paymentDtls.add(paymentDtl);
			}
		}
		
		EmployeeRequestHistory employeeRequestHistory = new EmployeeRequestHistory();
		pkId = pkId.add(BigDecimal.ONE);
		employeeRequestHistory.setPkId(employeeRequest.getPkId());
		employeeRequestHistory.setId(employeeRequest.getId());
		employeeRequestHistory.setOrganizationPkId(employeeRequest.getOrganizationPkId());
		employeeRequestHistory.setCustomerPkId(employeeRequest.getCustomerPkId());
		employeeRequestHistory.setEmployeePkId(employeeRequest.getEmployeePkId());
		employeeRequestHistory.setDescription(employeeRequest.getDescription());
		employeeRequestHistory.setMood(employeeRequest.getMood());
		employeeRequestHistory.setReInspection(employeeRequest.getReInspection());
		employeeRequestHistory.setBeginDate(employeeRequest.getBeginDate());
		employeeRequestHistory.setEndDate(employeeRequest.getEndDate());
		employeeRequestHistory.setPaymentPkId(null);
		employeeRequestHistory.setCreatedBy(loggedUser.getUser().getPkId());
		employeeRequestHistory.setCreatedDate(dte);
		employeeRequestHistory.setUpdatedBy(loggedUser.getUser().getPkId());
		employeeRequestHistory.setUpdatedDate(dte);
		employeeRequestHistory.setHasPayment(1);
		employeeRequestHistory.setIsInsurance(employeeRequest.getInsurance());
		employeeRequestHistory.setIsExpress(employeeRequest.getIsExpress());
		employeeRequestHistory.setSaveMood(employeeRequest.getSaveMood());
		employeeRequestHistory.setGuest(employeeRequest.getGuest());
		
		pkId = pkId.add(BigDecimal.ONE);
		map.setPkId(pkId);
		map.setPaymentPkId(payment.getPkId());
		map.setEmployeeRequestPkId(employeeRequestHistory.getPkId());
		
		if(inspectionDtls.size() > 0) update(inspectionDtls);
		if(xrayRequests.size() > 0) insert(xrayRequests);
		if(customerTreatments.size() > 0) insert(customerTreatments);
		if(examinationRequests.size() > 0) insert(examinationRequests);
		if(examinationRequestDtls.size() > 0) insert(examinationRequestDtls);
		if(payment.getPkId() != null && payment.getPkId().compareTo(BigDecimal.ZERO) != 0) update(payment);
		else insert(payment);
		insert(map);
		insert(paymentDtls);
		insert(paymentHistory);
		List<Inspection> inspections = getByAnyField(Inspection.class, "requestPkId", employeeRequest.getPkId());
		if(inspections.size() > 0){
			deleteByPkId(EmployeeRequest.class, employeeRequest.getPkId());
			insert(employeeRequestHistory);
		}else {
			employeeRequest.setHasPayment(1);
			update(employeeRequest);
			
		}
		return payment.getPkId();
	}
	
	public List<PaymentHistory> getPaymentHistorys(BigDecimal pkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("paymentPkId", pkId);
		jpql.append("SELECT a FROM PaymentHistory a WHERE a.paymentPkId = :paymentPkId ");
		
		return getByQuery(PaymentHistory.class, jpql.toString(), parameters);
	}
	
	public void savePaymentHistory(Payment payment, PaymentHistory history) throws Exception{
		
		
		BigDecimal pkId = Tools.newPkId();
		if(Tool.DELETE.equals(history.getStatus())){
			deleteByPkId(PaymentHistory.class, history.getPkId());
		}else if(Tool.MODIFIED.equals(history.getStatus())){
			update(history);
		}else {
			history.setPkId(pkId);
			payment.setPaidAmount(payment.getPaidAmount().add(history.getPayment()));
			
			insert(history);
			update(payment);
		}
	}
	
	public List<Employee> getEmployeeByPaymentHistorys(BigDecimal pkId, LoggedUser loggedUser) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("dte", new Date());
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("customerPkId", pkId);
		
		List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class, "SELECT e.employeeRequestPkId FROM EmployeeRequestPaymentMap e",  parameters);
		String str = "(0";
		for(BigDecimal bigDecimal : bigDecimals) if(bigDecimal != null) str = str + "," + bigDecimal;
		str = str + ")";
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Employee(a.pkId, a.firstName, b.pkId, a.degreePkId, c.name, b.mood, d.name) ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN EmployeeRequest b ON a.pkId = b.employeePkId ");
		jpql.append("INNER JOIN Degree c ON a.degreePkId = c.pkId ");
		jpql.append("INNER JOIN SubOrganization d ON a.subOrganizationPkId = d.pkId ");
		jpql.append("WHERE b.mood IN (2, 4) ");
		jpql.append("AND b.organizationPkId = :organizationPkId ");
		jpql.append("AND b.customerPkId = :customerPkId ");
		if(bigDecimals.size() > 0) jpql.append("AND b.pkId NOT IN "+str+" ");
		
		List<Employee> employees = getByQuery(Employee.class, jpql.toString(), parameters);
		for (Employee employee : employees) {
			parameters.put("itemPkId", employee.getDegreePkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM PriceHistory a ");
			jpql.append("WHERE a.priceUsageDate < :dte ");
			jpql.append("AND a.itemPkId = :itemPkId ");
			jpql.append("ORDER BY a.priceUsageDate DESC ");
			List<PriceHistory> histories = getByQuery(PriceHistory.class, jpql.toString(), parameters);
			if(histories.size() > 0){
				employee.setPrice(histories.get(0).getPrice());
				employee.setPriceHistoryName(histories.get(0).getItemName());
			}
		}
		
		return employees;
	}
	
	public List<PaymentHistory> getEmployeeLoanPayments(BigDecimal customerPkId) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("customerPkId", customerPkId);
		
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.PaymentHistory(b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount, SUM(b.payment)) ");
		jpql.append("FROM Payment a ");
		jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE c.pkId = :customerPkId ");
		jpql.append("GROUP BY b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount ");
		//jpql.append("HAVING SUM(b.payment) < a.amount ");
		
		List<PaymentHistory> histories = new ArrayList<PaymentHistory>();
		
		List<PaymentHistory> histories2 = getByQuery(PaymentHistory.class, jpql.toString(), parameters);
		for (PaymentHistory paymentHistory : histories2) {
			if(paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == -1) histories.add(paymentHistory);
		}
		
		return histories;
	}
	
	public List<PaymentEntity> getPaymentList(Date beginDate, Date endDate) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		if(beginDate.after(endDate)){
			beginDate = endDate;
		}
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		parameters.put("dte", endDate);
		
		parameters.put("dte1", beginDate);
		
		
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.PaymentEntity(a.pkId, a.cardNumber, a.lastName, a.firstName, a.regNumber, a.age, a.gender, b.pkId) ");
		jpql.append("FROM ViewCustomer a ");
		jpql.append("INNER JOIN EmployeeRequest b ON a.pkId = b.customerPkId ");
		jpql.append("WHERE b.hasPayment = 0 ");
		jpql.append("AND b.beginDate < :dte ");
		jpql.append("AND b.beginDate > :dte1 ");
		jpql.append("ORDER BY b.beginDate DESC");
		List<PaymentEntity> paymentEntities = getByQuery(PaymentEntity.class, jpql.toString(), parameters);
		
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.PaymentEntity(a.pkId, a.cardNumber, a.lastName, a.firstName, a.regNumber, a.age, a.gender, b.pkId) ");
		jpql.append("FROM ViewCustomer a ");
		jpql.append("INNER JOIN EmployeeRequest b ON a.pkId = b.customerPkId ");
		jpql.append("INNER JOIN Inspection c ON b.pkId = c.requestPkId ");
		jpql.append("WHERE c.pkId IN (SELECT d.inspectionPkId FROM InspectionDtl d) ");
		//jpql.append("AND b.mood IN (2, 4) ");
		jpql.append("AND b.beginDate < :dte ");
		jpql.append("AND b.beginDate > :dte1 ");
		jpql.append("ORDER BY b.beginDate DESC");
		
		List<PaymentEntity> paymentEntities1 = getByQuery(PaymentEntity.class, jpql.toString(), parameters);
		for (PaymentEntity paymentEntity : paymentEntities1) {
			parameters.put("rPkId", paymentEntity.getEmployeeRequestPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a.pkId FROM EmployeeRequest a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
			jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
			jpql.append("WHERE a.pkId = :rPkId ");
			List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class, jpql.toString(), parameters);
			if(bigDecimals.size() > 0) paymentEntities.add(paymentEntity);
		}
		
		return paymentEntities;
	}
	
	public List<PaymentEntity> getLoanPaymentList(LoggedUser loggedUser) throws Exception{
		List<PaymentEntity> paymentEntities = new ArrayList<PaymentEntity>();
		
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT NEW hospital.businessentity.PaymentEntity(a, b) FROM Payment a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("WHERE a.paidAmount < a.amount ");
		
		paymentEntities = getByQuery(PaymentEntity.class, jpql.toString(), null);
		return paymentEntities;
	}
	
	public Payment getPaymentBill(BigDecimal paymentPkId) throws Exception{
		Payment payment = getByPkId(Payment.class, paymentPkId);
		return payment;
	}
	
	public Customer getCustomer(BigDecimal customerPkId) throws Exception{
		Customer customer = getByPkId(Customer.class, customerPkId);
		return customer;
	}
	
	public void billBack(BigDecimal paymentPkId, String password, String description) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("paymentPkId", paymentPkId);
		
		if(description == null || description.length() < 10){
			throw new Exception("Тайлбар хамгийн багадаа 10 тэмдэгт байх ёстой.");
		}
		
		if(!"12345".equals(password)){
			throw new Exception("Нууц үг буруу байна.");
		}
		
		jpql.append("SELECT a FROM EmployeeRequestPaymentMap a WHERE a.paymentPkId = :paymentPkId ");
		List<EmployeeRequestPaymentMap> maps = getByQuery(EmployeeRequestPaymentMap.class, jpql.toString(), parameters);
		
		//REQUEST
		for (EmployeeRequestPaymentMap employeeRequestPaymentMap : maps) {
			EmployeeRequestHistory employeeRequestHistory = getByPkId(EmployeeRequestHistory.class, employeeRequestPaymentMap.getEmployeeRequestPkId());
			if(employeeRequestHistory != null){
				throw new Exception("Тухайн төлбөрийн баримт дээрх Хүсэлт-ийг буцаах боломжгүй байна.");
			}
		}
		
		//TREATMENT
		
		//EXAMINATION
		
		//SURGERY
		
		//XRAY
		jpql = new StringBuilder();
		jpql.append("SELECT a.pkId FROM XrayRequest a ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		jpql.append("AND a.image IS NOT NULL ");
		if(getByQuery(BigDecimal.class, jpql.toString(), parameters).size() > 0) throw new Exception("Тухайн төлбөрийн баримт дээрх Оншилгоо хийгдсэн байна.");
		
		Payment payment = getByPkId(Payment.class, paymentPkId);
		if(payment != null){
			List<PaymentDtl> paymentDtls = getByAnyField(PaymentDtl.class, "paymentPkId", paymentPkId);
			List<PaymentHistory> paymentHistorys = getByAnyField(PaymentHistory.class, "paymentPkId", paymentPkId);
			
			ReturnPayment returnPayment = new ReturnPayment();
			List<ReturnPaymentDtl> returnPaymentDtls = new ArrayList<ReturnPaymentDtl>();
			List<ReturnPaymentHistory> returnPaymentHistories = new ArrayList<ReturnPaymentHistory>();
			
			returnPayment.setPkId(payment.getPkId());
			returnPayment.setId(payment.getId());
			returnPayment.setOrganizationPkId(payment.getOrganizationPkId());
			returnPayment.setCustomerPkId(payment.getCustomerPkId());
			returnPayment.setDiscountPercent(payment.getDiscountPercent());
			returnPayment.setAmount(payment.getAmount());
			returnPayment.setCreatedDate(payment.getCreatedDate());
			returnPayment.setCreatedBy(payment.getCreatedBy());
			returnPayment.setUpdatedDate(payment.getUpdatedDate());
			returnPayment.setUpdatedBy(payment.getUpdatedBy());
			returnPayment.setDescription(payment.getDescription());
			returnPayment.setPaidAmount(payment.getPaidAmount());
			returnPayment.setBasicAmount(payment.getBasicAmount());
			returnPayment.setDiscountAmount(payment.getDiscountAmount());
			returnPayment.setDate(payment.getDate());
			returnPayment.setHasLottery(payment.getHasLottery());
			returnPayment.setRegisterNo(payment.getRegisterNo());
			returnPayment.setBillId(payment.getBillId());
			returnPayment.setMacAddress(payment.getMacAddress());
			returnPayment.setBillDate(payment.getBillDate());
			returnPayment.setLottery(payment.getLottery());
			returnPayment.setBillType(payment.getBillType());
			returnPayment.setInternalCode(payment.getInternalCode());
			returnPayment.setQrData(payment.getQrData());
			returnPayment.setMerchantId(payment.getMerchantId());
			returnPayment.setrDescription(description);
			
			for(PaymentDtl dtl : paymentDtls){
				ReturnPaymentDtl returnPaymentDtl = new ReturnPaymentDtl();
				returnPaymentDtl.setPkId(dtl.getPkId());
				returnPaymentDtl.setPaymentPkId(dtl.getPaymentPkId());
				returnPaymentDtl.setType(dtl.getType());
				returnPaymentDtl.setTypePkId(dtl.getTypePkId());
				returnPaymentDtl.setAmount(dtl.getAmount());
				returnPaymentDtl.setEmployeePkId(dtl.getEmployeePkId());
				returnPaymentDtl.setDiscountAmount(dtl.getDiscountAmount());
				returnPaymentDtls.add(returnPaymentDtl);
			}
			
			for(PaymentHistory paymentHistory : paymentHistorys){
				ReturnPaymentHistory returnPaymentHistory = new ReturnPaymentHistory();
				returnPaymentHistory.setPkId(paymentHistory.getPkId());
				returnPaymentHistory.setPaymentPkId(paymentHistory.getPaymentPkId());
				returnPaymentHistory.setDate(paymentHistory.getDate());
				returnPaymentHistory.setCashInAmount(paymentHistory.getCashInAmount());
				returnPaymentHistory.setCardInAmount(paymentHistory.getCardInAmount());
				returnPaymentHistories.add(returnPaymentHistory);
			}
			
			insert(returnPayment);
			insert(returnPaymentDtls);
			insert(returnPaymentHistories);
			deleteByAnyField(PaymentHistory.class, "paymentPkId", paymentPkId);
			deleteByAnyField(PaymentDtl.class, "paymentPkId", paymentPkId);
			deleteByPkId(Payment.class, paymentPkId);
			for(EmployeeRequestPaymentMap map : maps){
				EmployeeRequest employeeRequest = getByPkId(EmployeeRequest.class, map.getEmployeeRequestPkId());
				employeeRequest.setHasPayment(0);
				update(employeeRequest);
			}
			
			jpql = new StringBuilder();
			jpql.append("DELETE FROM XrayRequest a ");
			jpql.append("WHERE a.paymentPkId = :paymentPkId ");
			executeNonQuery(jpql.toString(), parameters);
			
			jpql = new StringBuilder();
			jpql.append("DELETE FROM ExaminationRequestDtl a ");
			jpql.append("WHERE a.examinationRequestPkId IN (SELECT b.pkId FROM ExaminationRequest b WHERE b.paymentPkId = :paymentPkId ) ");
			executeNonQuery(jpql.toString(), parameters);
			jpql = new StringBuilder();
			jpql.append("DELETE FROM ExaminationRequest a ");
			jpql.append("WHERE a.paymentPkId = :paymentPkId ");
			executeNonQuery(jpql.toString(), parameters);
			
			jpql = new StringBuilder();
			jpql.append("DELETE FROM CustomerTreatment a ");
			jpql.append("WHERE a.paymentPkId = :paymentPkId ");
			executeNonQuery(jpql.toString(), parameters);
			
			jpql = new StringBuilder();
			jpql.append("DELETE FROM EmployeeRequestPaymentMap a ");
			jpql.append("WHERE a.paymentPkId = :paymentPkId ");
			executeNonQuery(jpql.toString(), parameters);
		}
	}
	
	public boolean isBackPaymentBill(BigDecimal paymentPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("paymentPkId", paymentPkId);
		
		jpql.append("SELECT a FROM XrayRequest a ");
		jpql.append("WHERE a.paymentPkId = :paymentPkId ");
		List<XrayRequest> list = getByQuery(XrayRequest.class, jpql.toString(), parameters);
		
		jpql = new StringBuilder();
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		
		return true;
	}
	
	public List<PaymentHistory> getListPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		List<PaymentHistory> histories = new ArrayList<PaymentHistory>();
		
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.PaymentHistory(b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount, SUM(b.payment)) ");
		jpql.append("FROM Payment a ");
		jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE c.organizationPkId = :organizationPkId ");
		jpql.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount ");
		//jpql.append("HAVING SUM(b.payment) < a.amount ");
		
		List<PaymentHistory> histories2 = getByQuery(PaymentHistory.class, jpql.toString(), parameters);
		for (PaymentHistory paymentHistory : histories2) {
			if(paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 0 || paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 1) histories.add(paymentHistory);
		}
		
		return histories;
	}
	
	public List<PaymentHistory> getPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception{
		
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		List<PaymentHistory> histories = new ArrayList<PaymentHistory>();
		
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.PaymentHistory(b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount, SUM(b.payment), MAX(b.date)) ");
		jpql.append("FROM Payment a ");
		jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE c.organizationPkId = :organizationPkId ");
		jpql.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount ");
		//jpql.append("HAVING SUM(b.payment) < a.amount ");
		
		List<PaymentHistory> histories2 = getByQuery(PaymentHistory.class, jpql.toString(), parameters);
		for (PaymentHistory paymentHistory : histories2) {
			if(paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 0 || paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 1) histories.add(paymentHistory);
		}
		
		return histories;
	}
	
	public List<PaymentHistory> getLoanPaymentList(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception{
		
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		List<PaymentHistory> histories = new ArrayList<PaymentHistory>();
		
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.PaymentHistory(b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount, SUM(b.payment), MAX(b.date)) ");
		jpql.append("FROM Payment a ");
		jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE c.organizationPkId = :organizationPkId ");
		jpql.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY b.paymentPkId, a.customerPkId, c.cardNumber, c.lastName, c.firstName, c.regNumber, c.age, c.gender, a.amount ");
		//jpql.append("HAVING SUM(b.payment) < a.amount ");
		
		List<PaymentHistory> histories2 = getByQuery(PaymentHistory.class, jpql.toString(), parameters);
		for (PaymentHistory paymentHistory : histories2) {
			if(paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 0 || paymentHistory.getPayment().compareTo(paymentHistory.getAmount()) == 1) histories.add(paymentHistory);
		}
		
		return histories;
	}
	
	public String getPaymentId(LoggedUser loggedUser) throws Exception{
		
		String id = "";
		
		Date beginDate = new Date();
		Date endDate = new Date();
		
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(1);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM Payment a ");
		jpql.append("WHERE a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		
		int count = getByQuery(Payment.class, jpql.toString(), parameters).size();
		
		id = beginDate.getYear() - 100 + "" + ((beginDate.getMinutes() + 1) < 10 ? ("0" + (beginDate.getMinutes() + 1)) : (beginDate.getMinutes() + 1)) + (beginDate.getDate() < 10 ? "0" + beginDate.getDate() : beginDate.getDate()) ;
		if(count < 100) id = id + "0";
		if(count < 10) id = id + "0";
		id = id + (count + 1);
		return id;
	}
	
	public List<Xray> getCashXrayBill(BigDecimal paymentPkId) throws Exception{
		return null;
	}
	
	public Employee getDegreeByEmployee(BigDecimal employeePkId) throws Exception{
		Employee employee = getByPkId(Employee.class, employeePkId);
		Degree degree = getByPkId(Degree.class, employee.getDegreePkId());		
		employee.setSubOrganizationName(getByPkId(SubOrganization.class, employee.getSubOrganizationPkId()).getName());
		employee.setDegreeName(degree.getName());
		//employee.setPrice(degree.getInspectionPrice());
		return employee;
	}
	
	public List<InspectionDtl> getInspectionDtl(BigDecimal inspectionDtlPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("inspectionDtlPkId", inspectionDtlPkId);
		
		jpql.append("SELECT a FROM InspectionDtl a ");
		jpql.append("WHERE a.pkId = :inspectionDtlPkId ");
		
		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}
	
	public List<CashByInspectionDtl> getInspectionDtlByPaymentDtlPkId(BigDecimal paymentDtlPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("paymentDtlPkId", paymentDtlPkId);
		
		List<CashByInspectionDtl> byInspectionDtls = new ArrayList<CashByInspectionDtl>();
		
		jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM InspectionDtl a ");
		jpql.append("INNER JOIN Inspection b ON a.inspectionPkId = b.pkId ");
		jpql.append("INNER JOIN EmployeeRequestHistory c ON b.requestPkId = c.pkId ");
		jpql.append("INNER JOIN EmployeeRequestPaymentMap e ON e.employeeRequestPkId = c.pkId ");
		jpql.append("INNER JOIN PaymentDtl d ON e.paymentPkId = d.paymentPkId ");
		jpql.append("WHERE d.pkId = :paymentDtlPkId ");
		
		List<InspectionDtl> inspectionDtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		
		if(inspectionDtls.size() > 0){
			InspectionDtl dtl = inspectionDtls.get(0);
			
			List<InspectionDtlExaminationDtlMap> dtlExaminationDtlMaps = getByAnyField(InspectionDtlExaminationDtlMap.class, "inspectionDtlPkId", dtl.getPkId());
			if(dtlExaminationDtlMaps.size() > 0){
				parameters.put("inspectionDtlPkId", dtl.getPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(a.pkId, a.nameMn) FROM Element a ");
				jpql.append("INNER JOIN ExaminationDtl b ON a.pkId = b.elementPkId ");
				jpql.append("INNER JOIN InspectionDtlExaminationDtlMap c ON c.examinationDtlPkId = b.pkId ");
				jpql.append("WHERE c.inspectionDtlPkId = :inspectionDtlPkId ");
			}else {
				parameters.put("examinationPkId", dtl.getTypePkId());
				jpql = new StringBuilder();
				jpql.append("SELECT NEW hospital.businessentity.CashByInspectionDtl(a.pkId, a.nameMn) FROM Element a ");
				jpql.append("INNER JOIN ExaminationDtl b ON a.pkId = b.elementPkId ");
				jpql.append("WHERE b.examinationPkId = :examinationPkId ");
			}
			byInspectionDtls = getByQuery(CashByInspectionDtl.class, jpql.toString(), parameters);
			
			Inspection inspection = getByPkId(Inspection.class, dtl.getInspectionPkId());
			parameters.put("dte", inspection.getInspectionDateDate());
			for (CashByInspectionDtl cashByInspectionDtl : byInspectionDtls) {
				parameters.put("elementPkId", cashByInspectionDtl.getDegreePkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM ElementPrice a ");
				jpql.append("WHERE a.updatedDate < :dte ");
				jpql.append("AND a.elementPkId = :elementPkId ");
				
				List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
				if(elementPrices.size() > 0){
					cashByInspectionDtl.setPrice(elementPrices.get(0).getPrice());
				}
			}
		}
		return byInspectionDtls;
	}
	
}

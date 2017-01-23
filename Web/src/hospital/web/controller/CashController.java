package hospital.web.controller;

import hospital.businessentity.CashBusinessEntity;
import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.CustomerRequest;
import hospital.businessentity.InspectionHistory;
import hospital.businessentity.PaymentEntity;
import hospital.businessentity.PaymentPackageSelectItem;
import hospital.businessentity.PaymentPackageSelectType;
import hospital.businessentity.ReportFilter;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.ILogicCashierLocal;
import hospital.businesslogic.interfaces.ILogicConditionalPrescriptionLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicPaymentLocal;
import hospital.businesslogic.interfaces.ILogicRequestLocal;
import hospital.businesslogic.interfaces.ILogicReservationLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Customer;
//import hospital.entity.CustomerSkin;
import hospital.entity.Examination;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationType;
import hospital.entity.Xray;
import hospital.entity.XrayType;
import mondrian.rolap.BitKey.Big;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestPaymentMap;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.Payment;
import hospital.entity.PaymentHistory;
import hospital.entity.PaymentPackage;
import hospital.entity.PaymentPackageDtl;
import hospital.entity.Surgery;
import hospital.entity.SurgeryType;
import hospital.entity.Treatment;
import hospital.entity.TreatmentType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.mapping.ToOne;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.TreeNode;

@SessionScoped
@ManagedBean(name = "cashController")
public class CashController implements Serializable {
	private static final long serialVersionUID = 6927963307240943570L;

	//Region - Logic
	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;
	
	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;
	
	@EJB(beanName = "LogicPayment")
	ILogicPaymentLocal logicPayment;
	
	@EJB(beanName = "LogicCashier")
	ILogicCashierLocal logicCashier;
	
	@EJB(beanName = "LogicRequest")
	ILogicRequestLocal logicRequest;
	
	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;
	
	@EJB(beanName = "LogicXray")
	ILogicXrayLocal xrayLogic;
	
	@EJB(beanName = "LogicReservation")
	ILogicReservationLocal reservationLogic;
	
	@EJB(beanName = "LogicConditionalPrescription")
	ILogicConditionalPrescriptionLocal logicConPre;
	
	@EJB(beanName = "LogicXray")
	ILogicXrayLocal xrayLocal;
	
	//EndRegion
	
	//Region - Controll
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{inspectionController}")
	private InspectionController inspectionController;
	
	@ManagedProperty(value = "#{customerController}")
	private CustomerController customerController;
	
	//EndRegion
	
	//Ã�Å¡Ã�Â°Ã‘ï¿½Ã‘ï¿½Ã‘â€¹Ã�Â½ Ã�Â¶Ã�Â°Ã�Â³Ã‘ï¿½Ã�Â°Ã�Â°Ã�Â»Ã‘â€šÃ‘â€¹Ã�Â½ Prrpertys
	private List<PaymentEntity> paymentList;
	private List<PaymentEntity> tmpPaymentList;
	private List<PaymentEntity> tmpPaymentList1;
	private List<PaymentEntity> paymentFiltereedList;
	private List<PaymentEntity> loanPaymentList;
	private List<Employee> listEmployee;
	private List<CashBusinessEntity> cashHdrs;
	private List<EmployeeRequest> listEmployeeRequest;
	private List<Customer> listCustomer;
	private List<InspectionHistory> inspectionHistories;
	private List<XrayType> listXrayType;
	private List<Xray> listFilteredXray;
	private List<PaymentHistory> customerPaymentList;
	
	private Date beginDate;
	private Date endDate;
	
	//Cursor
	private List<EmployeeRequest> listEmployeeRequests;
	private List<PaymentHistory> listLoanEmployee;
	private List<PaymentHistory> histories;
	private Payment selectedPayment;
	private Customer selectedCustomer;
	private Customer selectedCashCustomer;
	private String selectedCustomerReg;
	private XrayType selectedXrayType;
	private Xray selectedXray;
	
	//Filter
	private ReportFilter filter;
	
	//Property
	private PaymentHistory history;
	private Customer customerCash;	
	private boolean isReportCashier;
	private boolean calculateCashier;
	private Date beginFilterBeginDate;
	private Date beginFilterEndDate;
	private int deleteIndexOfList;
	
	//Ã�Â¢Ã“Â©Ã�Â»Ã�Â±Ã“Â©Ã‘â‚¬Ã�Â¸Ã�Â¹Ã�Â½ Ã�Â±Ã�Â°Ã‘â‚¬Ã�Â¸Ã�Â¼Ã‘â€š Ã‘â€¦Ã‘ï¿½Ã�Â²Ã�Â»Ã‘ï¿½Ã‘â€¦Ã‘ï¿½Ã�Â´ Ã�Â°Ã‘Ë†Ã�Â¸Ã�Â³Ã�Â»Ã�Â°Ã�Â¶ Ã�Â±Ã�Â°Ã�Â¹Ã�Â³Ã�Â°Ã�Â° Property-Ã�Â½Ã‘Æ’Ã‘Æ’Ã�Â´
	private BigDecimal paymentPkId;
	private List<CashByInspectionDtl> listRequestInspectionDtl;
	private List<CashByInspectionDtl> listXrayInspectionDtl;
	private List<CashByInspectionDtl> listTreatmentInspectionDtl;
	private List<CashByInspectionDtl> listSurgeryInspectionDtl;
	private List<CashByInspectionDtl> listExamnationInspectionDtl;
	private Payment cash;
	
	//Ã�Â¢Ã“Â©Ã�Â»Ã�Â±Ã“Â©Ã‘â‚¬ Ã‘â€šÃ“Â©Ã�Â»Ã�Â´Ã“Â©Ã�Â³ Ã‘â€¦Ã‘ï¿½Ã‘ï¿½Ã�Â³Ã�Â¸Ã�Â¹Ã�Â½ property - Ã�Â½Ã‘Æ’Ã‘Æ’Ã�Â´	
	private Payment payment;
	private List<CashByInspectionDtl> listRequestByInspectionDtl;
	private List<CashByInspectionDtl> listCashByInspectionDtl;
	private List<CashByInspectionDtl> listXrayCashByInspectionDtl;
	private List<CashByInspectionDtl> listSurgeryCashByInspectionDtl;
	private List<CashByInspectionDtl> listTreatmentCashByInspectionDtl;
	private List<CashByInspectionDtl> listExamnationCashByInspectionDtl;
	
	private List<ExaminationType> examinationTypes;
	private ExaminationType selectedExaminationType;
	private List<Examination> examinations;
	private List<Examination> examinationsTmp;
	private Examination selectedExamination;
	
	private TreeNode examinationTreeNode;
	private TreeNode examinationTmpTreeNode;
	
	private List<XrayType> xrayTypes;
	private XrayType selectXrayType;
	private List<Xray> xrays;
	private Xray selectXray;
	
	//
	private Customer seCustomer;
	private List<Inspection> inspections;
	
	private List<PaymentEntity> listPaymentHistory;
	private BigDecimal sumAmount;
	private LazyDataModel<Customer> lazyDataModelCustomer;
	//DTL
	private List<CashByInspectionDtl> listCashByInspectionDtls;
	
	//SURGERY
	private List<SurgeryType> surgeryTypes;
	private SurgeryType selectedSurgeryType;
	private List<Surgery> listSurgery;
	private Surgery selectedSurgery;
	private List<Surgery> listChosenSurgery;
	
	private List<TreatmentType> treatmentTypes;
	private List<Treatment> treatments;
	private Treatment selectTreatment;
	private TreatmentType selectTreatmentType;
	private List<Treatment> listTreatment;
	private List<Employee> xrayEmployeeList;
	
	private String retBillPassword;
	private String retBillDescription;
	
	//
	private List<PaymentPackage> listPaymentPackage;
	private PaymentPackage paymentPackage;
	private BigDecimal paymentPackagePkId;
	private List<PaymentPackageDtl> paymentPackageDtls;
	private List<PaymentPackageDtl> tmpPaymentPackageDtls;
	private String selectedPaymentPackageType;
	private PaymentPackageSelectType packageSelectType;
	private List<PaymentPackageSelectType> packageSelectTypes;
	private PaymentPackageSelectItem packageSelectItem;
	private List<PaymentPackageSelectItem> packageSelectItems;
	
	public void cashBill(BigDecimal bigDecimal){
		paymentPkId = bigDecimal;
		cashBill();
	}
	
	public void cashBillBack(BigDecimal bigDecimal){
		try{
			paymentPkId = bigDecimal;
			cash = logicPayment.getPaymentBill(paymentPkId);
			if(cash != null){
				if(logicPayment.isBackPaymentBill(bigDecimal)){
					cashBillBack();
				}else {
					getUserSessionController().showErrorMessage("Төлбөрийн баримт буцаах боломжгүй байна.");
				}
			}else {
				getUserSessionController().showErrorMessage("Төлбөрийн баримт байхгүй байна.");
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void showCustomerInspectionHistory() {
		if(selectedCashCustomer != null && BigDecimal.ZERO.compareTo(selectedCashCustomer.getPkId()) != 0) {
			try {
				inspections = logicCashier.getInspectionHistoryByCustomer(selectedCashCustomer.getPkId());
				
				payment = new Payment();
				payment.setPkId(BigDecimal.ZERO);
				payment.setCustomerPkId(selectedCashCustomer.getPkId());
				listRequestByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				listCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				listXrayCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				listTreatmentCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				listExamnationCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				listSurgeryCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
				
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
			if(getInspections().size() > 0) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:inspectionHDialog");
				context.execute("PF('inspectionHistoryDialog').show();");
			}
		}
	}
	
	public void cashBillBack(){
		if(paymentPkId == null){
			return;
		}
		try{
			cash = logicPayment.getPaymentBill(paymentPkId);
			selectedCustomer = logicPayment.getCustomer(cash.getCustomerPkId());;
			cash.setXrayAmount(BigDecimal.ZERO);
			cash.setTreatmentAmount(BigDecimal.ZERO);
			cash.setSurgeryAmount(BigDecimal.ZERO);
			cash.setExamnationAmount(BigDecimal.ZERO);
			listRequestInspectionDtl = logicCashier.getListRequestInspectionDtl(paymentPkId);
			listXrayInspectionDtl = logicCashier.getListXrayInspectionDtl(paymentPkId);
			listTreatmentInspectionDtl = logicCashier.getListTreatmentInspectionDtl(paymentPkId);
			listSurgeryInspectionDtl = logicCashier.getListSurgeryInspectionDtl(paymentPkId);
			listExamnationInspectionDtl = logicCashier.getListExaminationInspectionDtl(paymentPkId);
			for (CashByInspectionDtl dtl : listXrayInspectionDtl) {
				cash.setXrayAmount(cash.getXrayAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listTreatmentInspectionDtl) {
				cash.setTreatmentAmount(cash.getTreatmentAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listSurgeryInspectionDtl) {
				cash.setSurgeryAmount(cash.getSurgeryAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listExamnationInspectionDtl) {
				cash.setExamnationAmount(cash.getExamnationAmount().add(dtl.getPrice()));
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:printCashBackReportDialog");
		context.execute("PF('printCashBackReportDialog').show();");
	}
	
	public void billback(){
		try{
			logicPayment.billBack(cash.getPkId(), retBillPassword, retBillDescription);
			if(cash.getHasLottery() == 1){
				getApplicationController().billBack(cash);
			}
			listPaymentHistory();
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('printCashBackReportDialog').hide()");
			context.update(":form:paymentHistoryTable :form:sumAmount :form:historyPaymentDialogReportPanel");
			getUserSessionController().showSuccessMessage("Төлбөрийн баримтыг амжилттай буцаалаа.");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void cashBill(){
		if(paymentPkId == null){
			return;
		}
		try{
			cash = logicPayment.getPaymentBill(paymentPkId);
			selectedCustomer = logicPayment.getCustomer(cash.getCustomerPkId());;
			cash.setXrayAmount(BigDecimal.ZERO);
			cash.setTreatmentAmount(BigDecimal.ZERO);
			cash.setSurgeryAmount(BigDecimal.ZERO);
			cash.setExamnationAmount(BigDecimal.ZERO);
			listRequestInspectionDtl = logicCashier.getListRequestInspectionDtl(paymentPkId);
			listXrayInspectionDtl = logicCashier.getListXrayInspectionDtl(paymentPkId);
			listTreatmentInspectionDtl = logicCashier.getListTreatmentInspectionDtl(paymentPkId);
			listSurgeryInspectionDtl = logicCashier.getListSurgeryInspectionDtl(paymentPkId);
			listExamnationInspectionDtl = logicCashier.getListExaminationInspectionDtl(paymentPkId);
			for (CashByInspectionDtl dtl : listXrayInspectionDtl) {
				cash.setXrayAmount(cash.getXrayAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listTreatmentInspectionDtl) {
				cash.setTreatmentAmount(cash.getTreatmentAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listSurgeryInspectionDtl) {
				cash.setSurgeryAmount(cash.getSurgeryAmount().add(dtl.getPrice()));
			}
			for (CashByInspectionDtl dtl : listExamnationInspectionDtl) {
				cash.setExamnationAmount(cash.getExamnationAmount().add(dtl.getPrice()));
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:printCashReportDialog");
		context.execute("PF('printCashReportDialog').show();");
	}
	
	public void showHistory(){
		if(selectedPayment == null) return;
		
		try{
			histories = logicPayment.getPaymentHistorys(selectedPayment.getPkId());
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		history = new PaymentHistory();
		history.setPaymentPkId(selectedPayment.getPkId());
		history.setDate(new Date());
		history.setCashInAmount(selectedPayment.getBalanceAmount());
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:historyDialog");
		context.execute("PF('historyDialog').show();");
		context.execute("historyDialogUpdate();");
	}
	
	public BigDecimal getSumAmountInspectionHistory(){
		BigDecimal bigDecimal = BigDecimal.ZERO;
		for(EmployeeRequest item : listEmployeeRequests){
			bigDecimal = bigDecimal.add(item.getSumAmount());
		}
		return bigDecimal;
	}
	
	public String saveHistory(){
		if(history == null || selectedPayment == null) return "";
		
		if(selectedPayment.getBalanceAmount().compareTo(history.getPayment()) == -1){
			getUserSessionController().showMessage(25);
			return "";
		}
		
		if(BigDecimal.ZERO.compareTo(history.getPayment()) == 0){
			getUserSessionController().showMessage(26);
			return "";
		}
		
		try{
			logicPayment.savePaymentHistory(selectedPayment, history);
		}catch(Exception ex){
			
		}
		
		return "cash_list";
	}
	
	public String selectedEmployeeRequest(BigDecimal employeeRequestPkId) {
		try {
			selectedCustomer = logicCashier.getCustomerByRequestPkId(employeeRequestPkId);
			selectedCashCustomer = selectedCustomer;
			//davaadorj
			listCashByInspectionDtl = logicCashier.getListCashByInspectionDtl(employeeRequestPkId);			
			listRequestByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listXrayCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listSurgeryCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listTreatmentCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listExamnationCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			for(CashByInspectionDtl dtl : listCashByInspectionDtl) {
				if(Tool.INSPECTIONTYPE_XRAY.equals(dtl.getType())) listXrayCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_TREATMENT.equals(dtl.getType())) listTreatmentCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_EXAMINATION.equals(dtl.getType())) listExamnationCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_SURGERY.equals(dtl.getType())) listSurgeryCashByInspectionDtl.add(dtl);
			}
			
			EmployeeRequest employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, employeeRequestPkId);
			CashByInspectionDtl byInspectionDtl = logicCashier.getRequestGyCashInspectionDtl(employeeRequest.getEmployeePkId(),employeeRequest.getHasPayment());
			if(employeeRequest.getHasPayment() == 1) {
				payment = logicTwo.getByPkId(Payment.class, employeeRequest.getPaymentPkId());
				payment.setEmployeeRequestPkId(employeeRequestPkId);
				getPayment().setBasicAmount(BigDecimal.ZERO);
				getPayment().setAmount(getPayment().getBasicAmount());
				getPayment().setPaidAmount(getPayment().getBasicAmount());
				
				listRequestByInspectionDtl.add(byInspectionDtl);
			}else {
				payment = new Payment();
				payment.setPkId(BigDecimal.ZERO);
				payment.setEmployeeRequestPkId(employeeRequestPkId);
				payment.setCustomerPkId(selectedCashCustomer.getPkId());
				getPayment().setBasicAmount(byInspectionDtl.getPrice());
				getPayment().setAmount(getPayment().getBasicAmount());
				getPayment().setPaidAmount(getPayment().getBasicAmount());
				byInspectionDtl.setSelected(true);
				
				byInspectionDtl.setSelected(false);
				listRequestByInspectionDtl.add(byInspectionDtl);
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "cash_register";
	}
	
	public String selectedEmployeeRequestSet(PaymentEntity paymentEntity){
		String ret = "cash_list";
		if(paymentEntity.getEmployeeRequestPkIds().size() > 1){
			ret = selectedEmployeeRequestSet(paymentEntity.getEmployeeRequestPkIds());
		}else {
//			try{
//				EmployeeRequest employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, paymentEntity.getEmployeeRequestPkId());
//				if(employeeRequest.getHasPayment() == 0){
//					selectedEmployeeRequestSet(paymentEntity.getEmployeeRequestPkId());
//					RequestContext context = RequestContext.getCurrentInstance();
//					context.update("form:requestPaymentDialog");
//					context.execute("PF('requestPaymentDialog').show();");
//					context.execute("hideLoader();");
//					ret = "";
//				}else {
					ret = selectedEmployeeRequestSet(paymentEntity.getEmployeeRequestPkId());
//				}
//			}catch(Exception ex){
//				getUserSessionController().showErrorMessage(ex.getMessage());
//			}
		}
		return ret;
	}
	
	public String selectedEmployeeRequestSet(List<BigDecimal> employeeRequestPkIds) {
		try {
			listRequestByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listXrayCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listTreatmentCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listExamnationCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listSurgeryCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			
			selectedCustomer = logicCashier.getCustomerByRequestPkId(employeeRequestPkIds.get(0));
			selectedCashCustomer = selectedCustomer;
			selectedCashCustomer.setExecutoryCount(logicRequest.customerLoanAmount(selectedCustomer.getPkId()));
			
			payment = new Payment();
			payment.setPkId(BigDecimal.ZERO);
			payment.setCustomerPkId(selectedCustomer.getPkId());
			getPayment().setBasicAmount(BigDecimal.ZERO);
			getPayment().setAmount(getPayment().getBasicAmount());
			getPayment().setPaidAmount(getPayment().getBasicAmount());
			//davaadorj
			List<BigDecimal> bigDecimals = new ArrayList<>();
			for (BigDecimal bigDecimal : employeeRequestPkIds) {
				if(bigDecimals.contains(bigDecimal)) continue;
				bigDecimals.add(bigDecimal);
				EmployeeRequest employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, bigDecimal);
				listCashByInspectionDtl = logicCashier.getListCashByInspectionDtl(bigDecimal);
				for(CashByInspectionDtl dtl : listCashByInspectionDtl) {
					dtl.setEmployeeRequestPkId(bigDecimal);
					dtl.setEmployeePkId(employeeRequest.getEmployeePkId());
					dtl.setSelected(true);
					if(Tool.INSPECTIONTYPE_XRAY.equals(dtl.getType())) listXrayCashByInspectionDtl.add(dtl);
					if(Tool.INSPECTIONTYPE_TREATMENT.equals(dtl.getType())) listTreatmentCashByInspectionDtl.add(dtl);
					if(Tool.INSPECTIONTYPE_EXAMINATION.equals(dtl.getType())) listExamnationCashByInspectionDtl.add(dtl);
					if(Tool.INSPECTIONTYPE_SURGERY.equals(dtl.getType())) listSurgeryCashByInspectionDtl.add(dtl);
				}
				
				CashByInspectionDtl byInspectionDtl = logicCashier.getRequestGyCashInspectionDtl(employeeRequest.getEmployeePkId(), employeeRequest.getReInspection());
				byInspectionDtl.setRequestDate(employeeRequest.getBeginDate());
				byInspectionDtl.setEmployeePkId(employeeRequest.getEmployeePkId());
				if(employeeRequest.getHasPayment() == 2){
					payment.setEmployeeRequestPkId(bigDecimal);
					payment.getEmployeeRequestPkIds().add(bigDecimal);
				}else {
					if(employeeRequest.getHasPayment() == 1) {
						//payment = logicTwo.getByPkId(Payment.class, employeeRequest.getPaymentPkId());
						//Payment pm = logicTwo.getByPkId(Payment.class, employeeRequest.getPaymentPkId());
						payment.setEmployeeRequestPkId(bigDecimal);
						payment.getEmployeeRequestPkIds().add(bigDecimal);
						//getPayment().setBasicAmount(BigDecimal.ZERO);
						//getPayment().setAmount(getPayment().getBasicAmount());
						//getPayment().setPaidAmount(getPayment().getBasicAmount());
						//byInspectionDtl.setPayment(false);
						//listRequestByInspectionDtl.add(byInspectionDtl);
					}else {
						payment.setEmployeeRequestPkId(bigDecimal);
						payment.getEmployeeRequestPkIds().add(bigDecimal);
						getPayment().setBasicAmount(byInspectionDtl.getPrice().add(getPayment().getBasicAmount()));
						getPayment().setAmount(getPayment().getBasicAmount());
						getPayment().setPaidAmount(getPayment().getBasicAmount());
						
						byInspectionDtl.setSelected(true);
						byInspectionDtl.setPayment(false);
						listRequestByInspectionDtl.add(byInspectionDtl);
					}
				}
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		calculatePayment2();
		return "cash_ManyRequests";
	}
	
	public void calculatePayment2(){
		getPayment().setTreatmentAmount(BigDecimal.ZERO);
		getPayment().setXrayAmount(BigDecimal.ZERO);
		getPayment().setExamnationAmount(BigDecimal.ZERO);
		getPayment().setSurgeryAmount(BigDecimal.ZERO);
		for (CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
			BigDecimal amount = dtl.getPrice().multiply(new BigDecimal(dtl.sumCount()));
			dtl.setAmount(amount);
			getPayment().setTreatmentAmount(getPayment().getTreatmentAmount().add(amount));
		}
		for(CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setSurgeryAmount(getPayment().getSurgeryAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setXrayAmount(getPayment().getXrayAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setExamnationAmount(getPayment().getExamnationAmount().add(amount));
		}
		getPayment().setBasicAmount(BigDecimal.ZERO);
		if(getPayment().getPkId() == null || getPayment().getPkId().compareTo(BigDecimal.ZERO) == 0) {
			for (CashByInspectionDtl dtl : listRequestByInspectionDtl) {
				if(!dtl.isSelected()) continue;
				if(dtl.isPayment()) continue;
				BigDecimal amount = dtl.getPrice();
				getPayment().setBasicAmount(getPayment().getBasicAmount().add(amount));
			}
		}
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getTreatmentAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getXrayAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getExamnationAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getSurgeryAmount()));
		getPayment().setAmount(getPayment().getBasicAmount());
		getPayment().setPaidAmount(getPayment().getBasicAmount());
		if(getPayment().getDiscountPercent().intValue() > 0) discountPayment();
	}
	
	public String selectedEmployeeRequestSet(BigDecimal employeeRequestPkId) {
		try {
			selectedCustomer = logicCashier.getCustomerByRequestPkId(employeeRequestPkId);
			selectedCashCustomer = selectedCustomer;
			selectedCashCustomer.setExecutoryCount(logicRequest.customerLoanAmount(selectedCustomer.getPkId()));
			//davaadorj
			listCashByInspectionDtl = logicCashier.getListCashByInspectionDtl(employeeRequestPkId);
			listRequestByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listXrayCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listTreatmentCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listExamnationCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			listSurgeryCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
			for(CashByInspectionDtl dtl : listCashByInspectionDtl) {
				dtl.setSelected(true);
				if(Tool.INSPECTIONTYPE_XRAY.equals(dtl.getType())) listXrayCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_TREATMENT.equals(dtl.getType())) listTreatmentCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_EXAMINATION.equals(dtl.getType())) listExamnationCashByInspectionDtl.add(dtl);
				if(Tool.INSPECTIONTYPE_SURGERY.equals(dtl.getType())) listSurgeryCashByInspectionDtl.add(dtl);
			}
			
			EmployeeRequest employeeRequest = logicTwo.getByPkId(EmployeeRequest.class, employeeRequestPkId);
			CashByInspectionDtl byInspectionDtl = logicCashier.getRequestGyCashInspectionDtl(employeeRequest.getEmployeePkId(), employeeRequest.getReInspection());
			byInspectionDtl.setRequestDate(employeeRequest.getBeginDate());
			if(employeeRequest.getHasPayment() == 2) {
				payment = new Payment();
				payment.setPkId(BigDecimal.ZERO);
				payment.setEmployeeRequestPkId(employeeRequestPkId);
				payment.setCustomerPkId(selectedCashCustomer.getPkId());
			}else {
				if(employeeRequest.getHasPayment() == 1) {
				//	payment = logicTwo.getPaymentByRequestPkId(employeeRequest.getPkId());
				//	payment.setEmployeeRequestPkId(employeeRequestPkId);
				//	getPayment().setBasicAmount(BigDecimal.ZERO);
				//	getPayment().setAmount(getPayment().getBasicAmount());
				//	getPayment().setPaidAmount(getPayment().getBasicAmount());
					
					//listRequestByInspectionDtl.add(byInspectionDtl);
					
					payment.setPkId(BigDecimal.ZERO);
					payment.setEmployeeRequestPkId(employeeRequestPkId);
					payment.setCustomerPkId(selectedCashCustomer.getPkId());
					getPayment().setBasicAmount(byInspectionDtl.getPrice());
					getPayment().setAmount(getPayment().getBasicAmount());
					getPayment().setPaidAmount(getPayment().getBasicAmount());
				}else {
					payment = new Payment();
					payment.setPkId(BigDecimal.ZERO);
					payment.setEmployeeRequestPkId(employeeRequestPkId);
					payment.setCustomerPkId(selectedCashCustomer.getPkId());
					getPayment().setBasicAmount(byInspectionDtl.getPrice());
					getPayment().setAmount(getPayment().getBasicAmount());
					getPayment().setPaidAmount(getPayment().getBasicAmount());
					
					byInspectionDtl.setSelected(false);
					listRequestByInspectionDtl.add(byInspectionDtl);
				}
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		calculatePayment();
		return "cash_Request";
	}
	
	public void calculatePayment1() {
		getPayment().setTreatmentAmount(BigDecimal.ZERO);
		getPayment().setXrayAmount(BigDecimal.ZERO);
		getPayment().setExamnationAmount(BigDecimal.ZERO);
		getPayment().setSurgeryAmount(BigDecimal.ZERO);
		for (CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
			BigDecimal amount = dtl.getPrice().multiply(new BigDecimal(dtl.getTreatmentCount()));
			dtl.setAmount(amount);
			getPayment().setTreatmentAmount(getPayment().getTreatmentAmount().add(amount));
		}
		for(CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setSurgeryAmount(getPayment().getSurgeryAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setXrayAmount(getPayment().getXrayAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setExamnationAmount(getPayment().getExamnationAmount().add(amount));
		}
		getPayment().setBasicAmount(BigDecimal.ZERO);
//		if(getPayment().getPkId() == null || getPayment().getPkId().compareTo(BigDecimal.ZERO) == 0) {
//			for (CashByInspectionDtl dtl : listRequestByInspectionDtl) {
//				BigDecimal amount = dtl.getPrice();
//				getPayment().setBasicAmount(getPayment().getBasicAmount().add(amount));
//			}
//		}
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getTreatmentAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getXrayAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getExamnationAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getSurgeryAmount()));
		getPayment().setAmount(getPayment().getBasicAmount());
		getPayment().setPaidAmount(getPayment().getBasicAmount());
		if(getPayment().getDiscountPercent().intValue() > 0) discountPayment();
	}
	
	public void calculatePayment() {
		getPayment().setTreatmentAmount(BigDecimal.ZERO);
		getPayment().setXrayAmount(BigDecimal.ZERO);
		getPayment().setExamnationAmount(BigDecimal.ZERO);
		getPayment().setSurgeryAmount(BigDecimal.ZERO);
		for (CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl) {
			BigDecimal amount = dtl.getPrice().multiply(new BigDecimal(dtl.sumCount()));
			dtl.setAmount(amount);
			getPayment().setTreatmentAmount(getPayment().getTreatmentAmount().add(amount));
		}
		for(CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setSurgeryAmount(getPayment().getSurgeryAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listXrayCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setXrayAmount(getPayment().getXrayAmount().add(amount));
		}
		for (CashByInspectionDtl dtl : listExamnationCashByInspectionDtl) {
			BigDecimal amount = (dtl.isSelected() && !Tool.LAST.equals(dtl.getStatus())) ? dtl.getPrice() : BigDecimal.ZERO;
			getPayment().setExamnationAmount(getPayment().getExamnationAmount().add(amount));
		}
		getPayment().setBasicAmount(BigDecimal.ZERO);
		if(getPayment().getPkId() == null || getPayment().getPkId().compareTo(BigDecimal.ZERO) == 0) {
			for (CashByInspectionDtl dtl : listRequestByInspectionDtl) {
				BigDecimal amount = dtl.getPrice();
				getPayment().setBasicAmount(getPayment().getBasicAmount().add(amount));
			}
		}
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getTreatmentAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getXrayAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getExamnationAmount()));
		getPayment().setBasicAmount(getPayment().getBasicAmount().add(getPayment().getSurgeryAmount()));
		getPayment().setAmount(getPayment().getBasicAmount());
		getPayment().setPaidAmount(getPayment().getBasicAmount());
		if(getPayment().getDiscountPercent().intValue() > 0) discountPayment();
	}
	
	public void discountPayment() {
		getPayment().setDiscountAmount(getPayment().getBasicAmount().subtract(getPayment().getPaidAmount()));
		getPayment().setAmount(Tool.calculateDiscountAmount(getPayment().getBasicAmount(), getPayment().getDiscountPercent()));
		getPayment().setPaidAmount(Tool.calculateDiscountAmount(getPayment().getBasicAmount(), getPayment().getDiscountPercent()));		
	}
	
	public String decimalConvertToString(BigDecimal bigDecimal) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(bigDecimal);
	}
	
//	public String selectedCashCustomer(BigDecimal pkId){
//		try{			selectedCustomer = new Customer();
//			selectedCustomer.setRegNumber(logicTwo.getByPkId(Customer.class, pkId).getRegNumber());
//			setCalculateCashier(true);
//		}catch(Exception ex){
//			getUserSessionController().showErrorMessage(ex.getMessage());
//		}
//
//		return "cash_register";
//	}
	
	public void fillCustomer(){
		try{
			if(!Tool.isRegNumber(selectedCustomer.getRegNumber())) {
				selectedCustomer.setRegNumber("");
				return;
			}
			Customer customer = getCustomerController().getCurrentCustomer();
			customer.setRegNumber(selectedCustomer.getRegNumber());
			selectedCustomerReg = selectedCustomer.getRegNumber();
			selectedCustomer = logicCustomer.getCustomerByRegNumber(selectedCustomer.getRegNumber());
			selectedCashCustomer = selectedCustomer;
			if(selectedCashCustomer != null)
				employeeCasherInfo(selectedCustomer);
			if(selectedCustomer == null){
				
				RequestContext context = RequestContext.getCurrentInstance();
				context.update(":form:registrrrrr");
				context.execute("PF('showNoneFilledCustomer').show();");
			}
		}catch(Exception ex){
			
		}
	}
	
	private void employeeCasherInfo(Customer customer){
		cashHdrs = new ArrayList<CashBusinessEntity>();
		if(selectedCashCustomer != null){
			try{
				payment = new Payment();
				payment.setId(logicPayment.getPaymentId(getUserSessionController().getLoggedInfo()));
				payment.setCustomerPkId(selectedCashCustomer.getPkId());
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		setSelectedCashCustomer(customer);
	}
	
	public void bridgePosAPI() {
		System.out.println("Hello World ! ! !");
	}
	
	public String saveCashPayment(){
		try {
			if(payment.getCustomerPkId() == null || BigDecimal.ZERO.compareTo(payment.getCustomerPkId()) == 0) {
				getUserSessionController().showMessage(27);
				return "cash_register";
			}
			
			if(BigDecimal.ZERO.compareTo(payment.getBasicAmount()) >= 0) {
				getUserSessionController().showMessage(84);
				return "cash_register";
			}
			//if()
			payment = getApplicationController().cashPaymentBridgePosAPI(payment, listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			paymentPkId = logicPayment.saveCashPayment(getPayment(), getUserSessionController().getLoggedInfo(), listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			isReportCashier = true;
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "cash_list";
	}
	
	public String savePayment(){
		try {
			if(BigDecimal.ZERO.compareTo(payment.getBasicAmount()) >= 0) {
				getUserSessionController().showMessage(84);
				return "cash_register";
			}
			payment = getApplicationController().paymentBridgePosAPI(payment, listRequestByInspectionDtl, listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			paymentPkId = logicPayment.savePayment(getPayment(), getUserSessionController().getLoggedInfo(), listRequestByInspectionDtl, listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			isReportCashier = true;
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "cash_list";
	}
	
	public String savePaymentByManyRequest(){
		try {
			if(BigDecimal.ZERO.compareTo(payment.getBasicAmount()) >= 0) {
				getUserSessionController().showMessage(84);
				return "cash_ManyRequests";
			}
			//if()
			payment = getApplicationController().paymentBridgePosAPI(payment, listRequestByInspectionDtl, listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			paymentPkId = logicPayment.savePaymentByManyRequest(getPayment(), getUserSessionController().getLoggedInfo(), listRequestByInspectionDtl, listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			isReportCashier = true;
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "cash_list";
	}
	
	public void listPaymentHistory() {
		try {
			listPaymentHistory = logicPayment.getPaymentListByBetweenDate(getFilter().getBeginDate(), getFilter().getEndDate());
			sumAmount = BigDecimal.ZERO;
			for (PaymentEntity pe : listPaymentHistory) {
				sumAmount = sumAmount.add(pe.getPayment().getAmount());
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void inspectionHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		try{
			inspectionHistories = logicTwo.getInspectionHistories(selectedCashCustomer.getPkId());
		}catch(Exception ex){
			
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionHistoryDialog");
		context.execute("PF('inspectionHistory').show();");
	}
	
	@SuppressWarnings("deprecation")
	public Date getBeginFilterBeginDate() {
		if(beginFilterBeginDate == null) beginFilterBeginDate = new Date();
		beginFilterBeginDate.setHours(1);
		beginFilterBeginDate.setMinutes(0);
		beginFilterBeginDate.setSeconds(1);
		return beginFilterBeginDate;
	}
	
	@SuppressWarnings("deprecation")
	public Date getBeginFilterEndDate() {
		if(beginFilterEndDate == null) beginFilterEndDate = new Date();
		beginFilterEndDate.setHours(23);
		beginFilterEndDate.setMinutes(59);
		beginFilterEndDate.setSeconds(59);
		return beginFilterEndDate;
	}
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}
	
	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
	
	public InspectionController getInspectionController() {
		return inspectionController;
	}
	
	public void setInspectionController(
			InspectionController inspectionController) {
		this.inspectionController = inspectionController;
	}
	
	public List<PaymentEntity> getPaymentList() {
		if(paymentList == null) paymentList = new ArrayList<PaymentEntity>();
		return paymentList;
	}
	
	public List<PaymentEntity> getTmpPaymentList() {
		if(tmpPaymentList == null) tmpPaymentList = new ArrayList<>();
		return tmpPaymentList;
	}
	
	public void setTmpPaymentList(List<PaymentEntity> tmpPaymentList) {
		this.tmpPaymentList = tmpPaymentList;
	}
	
	public void setTmpPaymentList1(List<PaymentEntity> tmpPaymentList1) {
		this.tmpPaymentList1 = tmpPaymentList1;
	}
	
	public List<PaymentEntity> getTmpPaymentList1() {
		return tmpPaymentList1;
	}
	
	public void paymentListToTmp(){
		tmpPaymentList1 = null;
		tmpPaymentList = new ArrayList<>();
		for(PaymentEntity entity : getPaymentList()){
			int count = 0;
			for(PaymentEntity pEntity :  tmpPaymentList){
				if(pEntity.getCustomer().getPkId().compareTo(entity.getCustomer().getPkId()) == 0){
					pEntity.getEmployeeRequestPkIds().add(entity.getEmployeeRequestPkId());
					count++;
				}
			}
			if(count == 0){
				PaymentEntity paymentEntity = new PaymentEntity();
				paymentEntity.setPayment(entity.getPayment());
				paymentEntity.setCustomer(entity.getCustomer());
				paymentEntity.setEmployeeRequestPkId(entity.getEmployeeRequestPkId());
				paymentEntity.getEmployeeRequestPkIds().add(entity.getEmployeeRequestPkId());
				tmpPaymentList.add(paymentEntity);
			}
		}
	}
	
	public void paymentList(){
		try{
			System.out.println(getBeginDate() + " - " + getEndDate());
			paymentList = logicPayment.getPaymentList(getBeginDate(), getEndDate());
			loanPaymentList = logicPayment.getLoanPaymentList(getUserSessionController().getLoggedInfo());
			paymentListToTmp();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:organizationList");
			context.update("form:organizationList2");
			context.update("form:loanCount");
			context.execute("slideLoan();");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void clearPayment(){
		payment = new Payment();
		selectedCashCustomer = new Customer();
		selectedCustomer = new Customer();
		listRequestByInspectionDtl = new ArrayList<>();
		listCashByInspectionDtl = new ArrayList<>();
		listXrayCashByInspectionDtl = new ArrayList<>();
		listTreatmentCashByInspectionDtl = new ArrayList<>();
		listExamnationCashByInspectionDtl = new ArrayList<>();
		listSurgeryCashByInspectionDtl = new ArrayList<>();
		listTreatmentCashByInspectionDtl = new ArrayList<>();
	}
	
	public void loanHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		try{
			
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('paymentHistory').show();");
	}

	public void requestHistory(){
		if(selectedCashCustomer == null || selectedCashCustomer.getPkId() == null || selectedCashCustomer.getPkId().compareTo(BigDecimal.ZERO) == 0) {
			getUserSessionController().showMessage(27);
			return;
		}
		try{
			listEmployeeRequest = reservationLogic.getEmployeeRequests(selectedCashCustomer.getPkId());
		}catch(Exception ex){
			
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:requestHistoryDialog");
		context.execute("PF('requestHistory').show();");
	}
	
	public void setPaymentList(List<PaymentEntity> paymentList) {
		this.paymentList = paymentList;
	}
	
	public List<PaymentEntity> getLoanPaymentList() {
		if(loanPaymentList == null) loanPaymentList = new ArrayList<PaymentEntity>();
		return loanPaymentList;
	}
	
	public void setLoanPaymentList(List<PaymentEntity> loanPaymentList) {
		this.loanPaymentList = loanPaymentList;
	}
	
	public ReportFilter getFilter() {
		if(filter == null) filter = new ReportFilter();
		return filter;
	}

	public void setFilter(ReportFilter filter) {
		this.filter = filter;
	}
	
	public PaymentHistory getHistory() {
		if(history == null) history = new PaymentHistory();
		return history;
	}

	public void setHistory(PaymentHistory history) {
		this.history = history;
	}
	
	public Customer getCustomerCash() {
		return customerCash;
	}

	public void setCustomerCash(Customer customerCash) {
		this.customerCash = customerCash;
	}
	
	public Customer getSelectedCustomer() {
		if(getCustomerController().isSelectCustomer()){
			selectedCustomer = getCustomerController().getCustomer();
			selectedCashCustomer = getCustomerController().getCustomer();
			payment = new Payment();
			payment.setCustomerPkId(selectedCashCustomer.getPkId());
			getCustomerController().setCurrentCustomer(null);
		}else if(selectedCustomer == null){
			selectedCustomer = new Customer();
			selectedCustomer.setPkId(BigDecimal.ZERO);
		}
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public CustomerController getCustomerController() {
		return customerController;
	}
	
	public void setCustomerController(CustomerController customerController) {
		this.customerController = customerController;
	}
	
	public void setCalculateCashier(boolean calculateCashier) {
		this.calculateCashier = calculateCashier;
	}
	
	public List<Employee> getListEmployee() {
		return listEmployee;
	}
	
	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	public List<CashBusinessEntity> getCashHdrs() {
		if(cashHdrs == null) {
			cashHdrs = new ArrayList<CashBusinessEntity>();
			CashBusinessEntity businessEntity = new CashBusinessEntity();
			businessEntity.setEmployeeRequest(new EmployeeRequest());
			businessEntity.setInspection(new Inspection());
			businessEntity.setDtl(new InspectionDtl());
			businessEntity.setCustomer(new Customer());
			businessEntity.setTreatment(new Treatment());
			businessEntity.setEmployee(new Employee());
			businessEntity.setStatus(Tool.ADDED);
			cashHdrs.add(businessEntity);
		}
		return cashHdrs;
	}
	
	public void setCashHdrs(List<CashBusinessEntity> cashHdrs) {
		this.cashHdrs = cashHdrs;
	}
	
	public Customer getSelectedCashCustomer() {
		if(selectedCashCustomer == null) selectedCashCustomer = new Customer();
		return selectedCashCustomer;
	}
	
	public void setSelectedCashCustomer(Customer selectedCashCustomer) {
		this.selectedCashCustomer = selectedCashCustomer;
	}
	
	public List<EmployeeRequest> getListEmployeeRequest() {
		if(listEmployeeRequest == null) return new ArrayList<EmployeeRequest>();
		return listEmployeeRequest;
	}

	public void setListEmployeeRequest(List<EmployeeRequest> listEmployeeRequest) {
		this.listEmployeeRequest = listEmployeeRequest;
	}
	
	public List<Customer> getListCustomer() {
		try{
			if(listCustomer == null) listCustomer = new ArrayList<Customer>();
		}catch(Exception ex){
			
		}
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}
	
	public String getSelectedCustomerReg() {
		return selectedCustomerReg;
	}

	public void setSelectedCustomerReg(String selectedCustomerReg) {
		this.selectedCustomerReg = selectedCustomerReg;
	}
	
	public List<InspectionHistory> getInspectionHistories() {
		if(inspectionHistories == null) return new ArrayList<InspectionHistory>();
		return inspectionHistories;
	}
	
	public void setInspectionHistories(List<InspectionHistory> inspectionHistories) {
		this.inspectionHistories = inspectionHistories;
	}
	
	public List<XrayType> getListXrayType() {
		try{
			if(listXrayType == null){
				listXrayType = xrayLogic.getXrayTypes(getUserSessionController().getLoggedInfo());
			}
			if(selectedXrayType == null && listXrayType.size() > 0) setSelectedXrayType(listXrayType.get(0));
		}catch(Exception ex){
			
		}
		return listXrayType;
	}
	
	public void setListXrayType(List<XrayType> listXrayType) {
		this.listXrayType = listXrayType;
	}
	
	public XrayType getSelectedXrayType() {
		return selectedXrayType;
	}
	
	public void setSelectedXrayType(XrayType selectedXrayType) {
		try{
			listFilteredXray = xrayLogic.getXrayByDianoseTypePkId(selectedXrayType.getPkId());
		}catch(Exception ex){
			
		}
		this.selectedXrayType = selectedXrayType;
	}
	
	public List<Xray> getListFilteredXray() {
		if(listFilteredXray == null) listFilteredXray = new ArrayList<Xray>();
		return listFilteredXray;
	}
	
	public void setListFilteredXray(List<Xray> listFilteredXray) {
		this.listFilteredXray = listFilteredXray;
	}
	
	public int getDeleteIndexOfList() {
		return deleteIndexOfList;
	}
	
	public void setDeleteIndexOfList(int deleteIndexOfList) {
		this.deleteIndexOfList = deleteIndexOfList;
	}
	
	public String deleteIndexOfList(){
		//getXrays().remove(deleteIndexOfList);
		return "cash_register";
	}
	
	public List<PaymentHistory> getCustomerPaymentList() {
		return customerPaymentList;
	}

	public void setCustomerPaymentList(List<PaymentHistory> customerPaymentList) {
		this.customerPaymentList = customerPaymentList;
	}
	
	public boolean isCalculateCashier() {
		if(getCustomerController().isHasSaveCustomer()){
			selectedCustomer = new Customer();
			selectedCustomer.setRegNumber(getCustomerController().getCustomerRegNumber());
			return true;
		}
		if(calculateCashier){
			calculateCashier = false;
			return true;
		}
		return calculateCashier;
	}
	
	public Payment getPayment() {
		if(payment == null) payment = new Payment();
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Xray getSelectedXray() {
		return selectedXray;
	}

	public void setSelectedXray(Xray selectedXray) {
		this.selectedXray = selectedXray;
	}

	public BigDecimal getPaymentPkId() {
		return paymentPkId;
	}

	public void setPaymentPkId(BigDecimal paymentPkId) {
		this.paymentPkId = paymentPkId;
	}

	public Payment getCash() {
		if(cash == null) cash = new Payment();
		return cash;
	}

	public void setCash(Payment cash) {
		this.cash = cash;
	}
	
	public boolean isReportCashier() {
		if(isReportCashier){
			isReportCashier = false;
			return true;
		}
		return isReportCashier;
	}
	
	public void setReportCashier(boolean isReportCashier) {
		this.isReportCashier = isReportCashier;
	}
	
	public List<PaymentHistory> getListLoanEmployee() {
		if(listLoanEmployee == null) listLoanEmployee = new ArrayList<PaymentHistory>();
		return listLoanEmployee;
	}

	public void setListLoanEmployee(List<PaymentHistory> listLoanEmployee) {
		this.listLoanEmployee = listLoanEmployee;
	}
	
	public Payment getSelectedPayment() {
		return selectedPayment;
	}

	public void setSelectedPayment(Payment selectedPayment) {
		this.selectedPayment = selectedPayment;
	}
	
	public List<PaymentHistory> getHistories() {
		if(histories == null) histories = new ArrayList<PaymentHistory>();
		return histories;
	}
	
	public void setHistories(List<PaymentHistory> histories) {
		this.histories = histories;
	}
	
	public List<EmployeeRequest> getListEmployeeRequests() {
		if(listEmployeeRequests == null) listEmployeeRequests = new ArrayList<EmployeeRequest>();
		return listEmployeeRequests;
	}
	
	public void setListEmployeeRequests(
			List<EmployeeRequest> listEmployeeRequests) {
		this.listEmployeeRequests = listEmployeeRequests;
	}
	
	public List<CashByInspectionDtl> getListCashByInspectionDtl() {
		if(listCashByInspectionDtl == null) listCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listCashByInspectionDtl;
	}
	
	public void setListCashByInspectionDtl(
			List<CashByInspectionDtl> listCashByInspectionDtl) {
		this.listCashByInspectionDtl = listCashByInspectionDtl;
	}
	
	public CashByInspectionDtl getLastCashByInspectionDtl() {
		CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
		byInspectionDtl.setStatus(Tool.LAST);
		byInspectionDtl.setSelected(false);
		return byInspectionDtl;
	}
	
	public void examinationChosen(Examination examination){
		for(Examination exa : getExaminationsTmp()){
			if(exa.getPkId().compareTo(examination.getPkId()) == 0){
				exa.setExaminationDtls(new ArrayList<ExaminationDtl>());
				exa.getExaminationDtls().addAll(examination.getExaminationDtls());
				exa.calculatePrice();
				examinationTmpTreeNode = null;
				return;
			}
		}
		Examination exam = (Examination) Tool.deepClone(examination);
		getExaminationsTmp().add(exam);
		examinationTmpTreeNode = null;
	}
	
	public void examinationChosen(ExaminationDtl examinationDtl){
		for(Examination examination : getExaminationsTmp()){
			if(examination.getPkId().compareTo(examinationDtl.getExaminationPkId()) == 0){
				examination.getExaminationDtls().add(examinationDtl);
				examination.calculatePrice();
				examinationTmpTreeNode = null;
				return;
			}
		}
		for(Examination examination : getExaminations()) {
			if(examination.getPkId().compareTo(examinationDtl.getExaminationPkId()) == 0) {
				Examination exa = (Examination) Tool.deepClone(examination);
				exa.setExaminationDtls(new ArrayList<ExaminationDtl>());
				exa.getExaminationDtls().add(examinationDtl);
				exa.calculatePrice();
				getExaminationsTmp().add(exa);
				examinationTmpTreeNode = null;
				return;
			}
		}
	}
	
	public void examinationDelete(Examination examination){
		getExaminationsTmp().remove(examination);
		examinationTmpTreeNode = null;
	}
	
	public void examinationDelete(ExaminationDtl examinationDtl){
		Examination examination = null;
		for(Examination exa : getExaminationsTmp()){
			if(exa.getPkId().compareTo(examinationDtl.getExaminationPkId()) == 0){
				examination = exa;
			}
		}
		if(examination != null) {
			examination.getExaminationDtls().remove(examinationDtl);
			examination.calculatePrice();
			if(examination.getExaminationDtls().size() < 1){
				getExaminationsTmp().remove(examination);
			}
		}
		examinationTmpTreeNode = null;
	}
	
	public void examinationTmpToListInspectionDtl(){
		CashByInspectionDtl byInspection = null;
		for(CashByInspectionDtl dtl : listExamnationCashByInspectionDtl){
			if(Tool.LAST.equals(dtl.getStatus())) byInspection = dtl;
		}
		if(byInspection != null) listExamnationCashByInspectionDtl.remove(byInspection);
		for(Examination examination : getExaminationsTmp()){
			CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
			
			byInspectionDtl.setName(examination.getName());
			byInspectionDtl.setType(Tool.INSPECTIONTYPE_EXAMINATION);
			byInspectionDtl.setTypePkId(examination.getPkId());
			byInspectionDtl.setPrice(examination.getPrice());
			byInspectionDtl.setExaminationDtls(examination.getExaminationDtls());
			byInspectionDtl.setSelected(true);
			byInspectionDtl.setStatus(Tool.ADDED);
			byInspectionDtl.calcPriceByExaminationDtl();
			
			listExamnationCashByInspectionDtl.add(byInspectionDtl);
		}
	}
	
	public void setExaminationsTmp(List<Examination> examinationsTmp) {
		this.examinationsTmp = examinationsTmp;
	}
	
	public List<Examination> getExaminationsTmp() {
		if(examinationsTmp == null) {
			examinationsTmp = new ArrayList<>();
		}
		return examinationsTmp;
	}
	
	public void setExaminationTmpTreeNode(TreeNode examinationTmpTreeNode) {
		this.examinationTmpTreeNode = examinationTmpTreeNode;
	}
	
	public TreeNode getExaminationTmpTreeNode() {
		if(examinationTmpTreeNode == null) {
			examinationTmpTreeNode = new DefaultTreeNode();
			if(getExaminationsTmp().size() > 0) {
				for (Examination examination : getExaminationsTmp()) {
					TreeNode treeNode = new DefaultTreeNode(examination, examinationTmpTreeNode);
					treeNode.setExpanded(true);
					for (ExaminationDtl dtl : examination.getExaminationDtls()) {
						new DefaultTreeNode(dtl, treeNode);
					}
				}
			}
		}
		return examinationTmpTreeNode;
	}
	
	public TreeNode getExaminationTreeNode() {
		if(examinationTreeNode == null) {
			examinationTreeNode = new DefaultTreeNode();
			if(getExaminations().size() > 0) {
				for (Examination examination : getExaminations()) {
					TreeNode treeNode = new DefaultTreeNode(examination, examinationTreeNode);
					for (ExaminationDtl dtl : examination.getExaminationDtls()) {
						new DefaultTreeNode(dtl, treeNode);
					}
				}
			}
		}
		return examinationTreeNode;
	}
	
	public void setExaminationTreeNode(TreeNode examinationTreeNode) {
		this.examinationTreeNode = examinationTreeNode;
	}
	
	public List<Examination> getExaminations() {
		if(examinations == null) {
			try {
				if(getSelectedExaminationType() != null)
					examinations = logicTwo.getExamination(getSelectedExaminationType().getPkId());
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return examinations;
	}
	
	public List<String> getLstTreatmentStr(String s) {
		List<String> lstTreatmentStr = new ArrayList<>();
		for (Treatment cd : getApplicationController().getListTreatment()) {
			if ((cd.getId() + " | " + cd.getName()).contains(s))
				lstTreatmentStr.add(cd.getId() + " | " + cd.getName());
		}
		return lstTreatmentStr;
	}
	
	public void removeListTreatment(Treatment treatment){
		if(treatment != null) listTreatment.remove(treatment);
	}
	
	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}
	
	public String refreshCashByRequest() {
		return "cash_Request";
	}
	
	public void addExamination() {
		try {
			if(selectedExamination != null) {
				for (CashByInspectionDtl exam : listExamnationCashByInspectionDtl) {
					if(Tool.LAST.equals(exam.getStatus())) {
						exam.setName(selectedExamination.getName());
						exam.setType(Tool.INSPECTIONTYPE_EXAMINATION);
						exam.setTypePkId(selectedExamination.getPkId());
						exam.setPrice(selectedExamination.getPrice());
						exam.setExaminationDtls(selectedExamination.getExaminationDtls());
//						exam.setqty;
//						exam.settimes;
//						exam.setdayLength;
//						exam.settreatmentCount;
//						exam.setuserCount;
//						exam.setemployeeName;
//						exam.setamount;
//						exam.setemployeePkId;
//						exam.setlistEmployee;
//						exam.setallAmount;
						exam.setSelected(true);
//						exam.setinspectionPkId;
//						exam.setinspectionDtlPkId;
//						exam.setdegreePkId;
						exam.setStatus(Tool.ADDED);
						exam.calcPriceByExaminationDtl();
					}
				}
				System.out.println(selectedExamination.getPkId());
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:myTable2");
				context.execute("PF('examinationSelectDialog').hide();calculatePayment();");
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public List<ExaminationType> getExaminationTypes() {
		if(examinationTypes == null) {
			try {
				examinationTypes = logicTwo.getExaminationTypes();
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return examinationTypes;
	}
	
	public void setExaminationTypes(List<ExaminationType> examinationTypes) {
		this.examinationTypes = examinationTypes;
	}
	
	public Examination getSelectedExamination() {
		return selectedExamination;
	}
	
	public void setSelectedExamination(Examination selectedExamination) {
		this.selectedExamination = selectedExamination;
	}
	
	public ExaminationType getSelectedExaminationType() {
		if(selectedExaminationType == null) {
			if(getExaminationTypes().size() > 0) {
				selectedExaminationType = getExaminationTypes().get(0);
			}
		}
		return selectedExaminationType;
	}
	
	public void setSelectedExaminationType(ExaminationType selectedExaminationType) {
		this.selectedExaminationType = selectedExaminationType;
		this.examinations = null;
		this.examinationTreeNode = null;
	}
	
	public void addExamination(CashByInspectionDtl byInspectionDtl) {
		if(Tool.LAST.equals(byInspectionDtl.getStatus())) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('examinationSelectDialog').show();");
		}
	}
	
	public void addXray(CashByInspectionDtl byInspectionDtl) {
		if(Tool.LAST.equals(byInspectionDtl.getStatus())) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('xraySelectDialog').show();");
		}
	}
	
	public List<CashByInspectionDtl> getListExamnationCashByInspectionDtl() {
		if(listExamnationCashByInspectionDtl == null) {
			listExamnationCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
		}
		if(listExamnationCashByInspectionDtl.size() < 1) {
			listExamnationCashByInspectionDtl.add(getLastCashByInspectionDtl());
		}else {
			if(!Tool.LAST.equals(listExamnationCashByInspectionDtl.get(listExamnationCashByInspectionDtl.size() - 1).getStatus())) {
				listExamnationCashByInspectionDtl.add(getLastCashByInspectionDtl());
			}
		}
		return listExamnationCashByInspectionDtl;
	}
	
	public void setListExamnationCashByInspectionDtl(
			List<CashByInspectionDtl> listExamnationCashByInspectionDtl) {
		this.listExamnationCashByInspectionDtl = listExamnationCashByInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListTreatmentCashByInspectionDtl() {
		if(listTreatmentCashByInspectionDtl == null) listTreatmentCashByInspectionDtl = new ArrayList<>();
		int countLastStatus = 0;
		for(CashByInspectionDtl byInspectionDtl : listTreatmentCashByInspectionDtl){
			if(Tool.LAST.equals(byInspectionDtl.getStatus())){
				countLastStatus++;
			}
		}
		if(countLastStatus == 0){
			CashByInspectionDtl byInspectionDtl = new  CashByInspectionDtl();
			byInspectionDtl.setStatus(Tool.LAST);
			listTreatmentCashByInspectionDtl.add(byInspectionDtl);
		}
		return listTreatmentCashByInspectionDtl;
	}
	
	public void onLstTreatmentStr(SelectEvent event) {
		try {
			String string = event.getObject().toString();
			String[] parts = string.split(" | ");
			Treatment cpDtl = null;
			if(parts.length > 0) {
				cpDtl = logicConPre.getTreatment("id", parts[0]);
			}
			if(cpDtl == null && parts.length > 1) {
				cpDtl = logicConPre.getTreatment("id", parts[1]);
			}
			if (cpDtl != null) {
				CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
				for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl){
					if(Tool.LAST.equals(dtl.getStatus())){
						byInspectionDtl = dtl;
					}
				}
				byInspectionDtl.setName(cpDtl.getName());
				byInspectionDtl.setType(Tool.INSPECTIONTYPE_TREATMENT);
				byInspectionDtl.setTypePkId(cpDtl.getPkId());
				byInspectionDtl.setPrice(cpDtl.getCost());
				byInspectionDtl.setDayLength(10000000);
				byInspectionDtl.setStatus(Tool.ADDED);
				byInspectionDtl.setInspectionDtlPkId(new BigDecimal(-1));
				byInspectionDtl.setAllAmount(BigDecimal.ZERO);
			} else {
				
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void setListTreatmentCashByInspectionDtl(
			List<CashByInspectionDtl> listTreatmentCashByInspectionDtl) {
		this.listTreatmentCashByInspectionDtl = listTreatmentCashByInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListXrayCashByInspectionDtl() {
		if(listXrayCashByInspectionDtl == null) {
			listXrayCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
		}
		if(listXrayCashByInspectionDtl.size() < 1) {
			listXrayCashByInspectionDtl.add(getLastCashByInspectionDtl());
		}else {
			if(!Tool.LAST.equals(listXrayCashByInspectionDtl.get(listXrayCashByInspectionDtl.size() - 1).getStatus())) {
				listXrayCashByInspectionDtl.add(getLastCashByInspectionDtl());
			}
		}
		return listXrayCashByInspectionDtl;
	}
	
	public void setListXrayCashByInspectionDtl(
			List<CashByInspectionDtl> listXrayCashByInspectionDtl) {
		this.listXrayCashByInspectionDtl = listXrayCashByInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListSurgeryCashByInspectionDtl() {
		if(listSurgeryCashByInspectionDtl == null) listSurgeryCashByInspectionDtl = new ArrayList<CashByInspectionDtl>();
		int count = 0;
		for (CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
			if(Tool.LAST.equals(dtl.getStatus())){
				count++;
			}
		}
		if(count == 0) {
			CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
			byInspectionDtl.setStatus(Tool.LAST);
			listSurgeryCashByInspectionDtl.add(byInspectionDtl);
		}
		return listSurgeryCashByInspectionDtl;
	}
	
	public void setListSurgeryCashByInspectionDtl(
			List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) {
		this.listSurgeryCashByInspectionDtl = listSurgeryCashByInspectionDtl;
	}
	
	public List<PaymentEntity> getListPaymentHistory() {
		if(listPaymentHistory == null) listPaymentHistory = new ArrayList<PaymentEntity>();
		return listPaymentHistory;
	}
	
	public void setListPaymentHistory(List<PaymentEntity> listPaymentHistory) {
		this.listPaymentHistory = listPaymentHistory;
	}
	
	public List<CashByInspectionDtl> getListExamnationInspectionDtl() {
		if(listExamnationInspectionDtl == null) listExamnationInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listExamnationInspectionDtl;
	}
	
	public void setListExamnationInspectionDtl(
			List<CashByInspectionDtl> listExamnationInspectionDtl) {
		this.listExamnationInspectionDtl = listExamnationInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListTreatmentInspectionDtl() {
		if(listTreatmentInspectionDtl == null) listTreatmentInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listTreatmentInspectionDtl;
	}
	
	public void setListTreatmentInspectionDtl(
			List<CashByInspectionDtl> listTreatmentInspectionDtl) {
		this.listTreatmentInspectionDtl = listTreatmentInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListXrayInspectionDtl() {
		if(listXrayInspectionDtl == null) listXrayInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listXrayInspectionDtl;
	}
	
	public void setListXrayInspectionDtl(
			List<CashByInspectionDtl> listXrayInspectionDtl) {
		this.listXrayInspectionDtl = listXrayInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListRequestInspectionDtl() {
		if(listRequestInspectionDtl == null) listRequestInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listRequestInspectionDtl;
	}
	
	public void setListRequestInspectionDtl(
			List<CashByInspectionDtl> listRequestInspectionDtl) {
		this.listRequestInspectionDtl = listRequestInspectionDtl;
	}
	
	public List<CashByInspectionDtl> getListRequestByInspectionDtl() {
		return listRequestByInspectionDtl;
	}
	
	public void setListRequestByInspectionDtl(
			List<CashByInspectionDtl> listRequestByInspectionDtl) {
		this.listRequestByInspectionDtl = listRequestByInspectionDtl;
	}
	
	public Customer getSeCustomer() {
		return seCustomer;
	}
	
	public void setSeCustomer(Customer seCustomer) {
		if(seCustomer != null && seCustomer.getPkId() != null && BigDecimal.ZERO.compareTo(seCustomer.getPkId()) != 0) {
			selectedCashCustomer = seCustomer;
			getPayment().setCustomerPkId(selectedCashCustomer.getPkId());
			try{
				selectedCashCustomer.setExecutoryCount(logicRequest.customerLoanAmount(selectedCashCustomer.getPkId()));
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		this.seCustomer = seCustomer;
	}

	public List<Inspection> getInspections() {
		if(inspections == null) inspections = new ArrayList<Inspection>();
		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}
	
	public List<CashByInspectionDtl> getListSurgeryInspectionDtl() {
		if(listSurgeryInspectionDtl == null) listSurgeryInspectionDtl = new ArrayList<CashByInspectionDtl>();
		return listSurgeryInspectionDtl;
	}
	
	public void setListSurgeryInspectionDtl(
			List<CashByInspectionDtl> listSurgeryInspectionDtl) {
		this.listSurgeryInspectionDtl = listSurgeryInspectionDtl;
	}
	
	public String getQrUrl(String qrCode) {
		String ret = "http://www.qr-code-generator.com/phpqrcode/getCode.php?cht=qr&chl=" + qrCode;
		return ret;
	}
	
	public List<XrayType> getXrayTypes() {
		if(xrayTypes == null) {
			try {
				xrayTypes = logicTwo.getXrayTypes();
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return xrayTypes;
	}
	
	public void setXrayTypes(List<XrayType> xrayTypes) {
		this.xrayTypes = xrayTypes;
	}
	
	public XrayType getSelectXrayType() {
		if(selectXrayType == null && getXrayTypes().size() > 0) {
			selectXrayType = getXrayTypes().get(0);
		}
		return selectXrayType;
	}
	
	public void setSelectXrayType(XrayType selectXrayType) {
		this.selectXrayType = selectXrayType;
		xrays = null;
	}
	
	public List<Xray> getXrays() {
		if(xrays == null && getSelectXrayType() != null) {
			try {
				xrays = logicTwo.getXrays(getSelectXrayType().getPkId());
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return xrays;
	}
	
	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}
	
	public Xray getSelectXray() {
		return selectXray;
	}
	
	public void setSelectXray(Xray selectXray) {
		this.selectXray = selectXray;
	}
	
	public void addXray() {
		try {
			if(selectXray != null) {
				for (CashByInspectionDtl exam : listXrayCashByInspectionDtl) {
					if(Tool.LAST.equals(exam.getStatus())) {
						exam.setName(selectXray.getName());
						exam.setType(Tool.INSPECTIONTYPE_XRAY);
						exam.setTypePkId(selectXray.getPkId());
						exam.setPrice(selectXray.getPriceIn());
//						exam.setqty;
//						exam.settimes;
//						exam.setdayLength;
//						exam.settreatmentCount;
//						exam.setuserCount;
//						exam.setemployeeName;
//						exam.setamount;
//						exam.setemployeePkId;
//						exam.setlistEmployee;
//						exam.setallAmount;
						exam.setSelected(true);
//						exam.setinspectionPkId;
//						exam.setinspectionDtlPkId;
//						exam.setdegreePkId;
						exam.setStatus(Tool.ADDED);
					}
				}
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:myTable1");
				context.execute("PF('xraySelectDialog').hide();calculatePayment();");
			}
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	/*private CustomerSkin customerSkin;
	public CustomerSkin getCustomerSkin() {if(customerSkin == null) customerSkin = new CustomerSkin();return customerSkin;}
	public void setCustomerSkin(CustomerSkin customerSkin) {this.customerSkin = customerSkin;}
	public void saveTest() {
		try {
			logicCashier.saveTest(customerSkin);
			customerSkin = null;
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	*/
	public List<PaymentEntity> getPaymentFiltereedList() {
		if(paymentFiltereedList == null) paymentFiltereedList = new ArrayList<>();
		return paymentFiltereedList;
	}
	
	public void setPaymentFiltereedList(List<PaymentEntity> paymentFiltereedList) {
		this.paymentFiltereedList = paymentFiltereedList;
	}

	public void showListExamnationInspectionDtl(BigDecimal paymentDtlPkId){
		try{
			RequestContext context = RequestContext.getCurrentInstance();
			listCashByInspectionDtls = logicPayment.getInspectionDtlByPaymentDtlPkId(paymentDtlPkId);
			System.out.println("InspectionDtlPkId (" + listCashByInspectionDtls.size() + ") : " + paymentDtlPkId);
			context.update("form:paymentDtl");
			context.execute("PF('paymentDtl').show();");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public List<CashByInspectionDtl> getListCashByInspectionDtls() {
		return listCashByInspectionDtls;
	}
	
	public void setListCashByInspectionDtls(List<CashByInspectionDtl> listCashByInspectionDtls) {
		this.listCashByInspectionDtls = listCashByInspectionDtls;
	}
	
	public LazyDataModel<Customer> getLazyDataModelCustomer() {
		return lazyDataModelCustomer;
	}
	
	public void setLazyDataModelCustomer(LazyDataModel<Customer> lazyDataModelCustomer) {
		this.lazyDataModelCustomer = lazyDataModelCustomer;
	}
	
	public List<SurgeryType> getSurgeryTypes() {
		if(surgeryTypes == null) {
			try{
				surgeryTypes = logicTwo.getSurgeryType();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return surgeryTypes;
	}
	
	public void setSurgeryTypes(List<SurgeryType> surgeryTypes) {
		this.surgeryTypes = surgeryTypes;
	}
	
	public SurgeryType getSelectedSurgeryType() {
		return selectedSurgeryType;
	}
	
	public void setSelectedSurgeryType(SurgeryType selectedSurgeryType) {
		if(selectedSurgeryType != null) {
			listSurgery = null;
			selectedSurgery = null;
			getListSurgery();
		}
		this.selectedSurgeryType = selectedSurgeryType;
	}
	
	public void chosenSurgery(){
		if(selectedSurgery != null) {
			getListChosenSurgery().add((Surgery) Tool.deepClone(selectedSurgery));
		}
	}
	
	public List<Surgery> getListSurgery() {
		if(listSurgery == null || listSurgery.size() < 1) {
			if(getSelectedSurgeryType() != null){
				try{
					listSurgery = logicTwo.getListSurgery(getSelectedSurgeryType().getPkId());
				}catch(Exception ex){
					getUserSessionController().showErrorMessage(ex.getMessage());
				}
			}
		}
		return listSurgery;
	}
	
	public void setListSurgery(List<Surgery> listSurgery) {
		this.listSurgery = listSurgery;
	}

	public List<Surgery> getListChosenSurgery() {
		if(listChosenSurgery == null) listChosenSurgery = new ArrayList<>();
		return listChosenSurgery;
	}

	public void setListChosenSurgery(List<Surgery> listChosenSurgery) {
		this.listChosenSurgery = listChosenSurgery;
	}
	
	public void insertSurgery(){
		if(getListChosenSurgery().size() > 0){
			if(listSurgeryCashByInspectionDtl == null) listSurgeryCashByInspectionDtl = new ArrayList<>();
			CashByInspectionDtl cashByInspectionDtl = null;
			for (CashByInspectionDtl dtl : listSurgeryCashByInspectionDtl) {
				if(Tool.LAST.equals(dtl.getStatus())){
					cashByInspectionDtl = dtl;
				}
			}
			if(cashByInspectionDtl != null) {
				listSurgeryCashByInspectionDtl.remove(cashByInspectionDtl);
			}
			for (Surgery surgery : getListChosenSurgery()) {
				CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
				byInspectionDtl.setType(Tool.INSPECTIONTYPE_SURGERY);
				byInspectionDtl.setTypePkId(surgery.getPkId());
				byInspectionDtl.setPrice(surgery.getPrice());
				byInspectionDtl.setName(surgery.getName());
				byInspectionDtl.setSelected(true);
				byInspectionDtl.setStatus(Tool.ADDED);
				listSurgeryCashByInspectionDtl.add(byInspectionDtl);
			}
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('surgeryChosen').hide();");
			context.update("form:myTable3");
		}
	}

	public Surgery getSelectedSurgery() {
		return selectedSurgery;
	}

	public void setSelectedSurgery(Surgery selectedSurgery) {
		this.selectedSurgery = selectedSurgery;
	}

	public BigDecimal getSumAmount() {
		return sumAmount;
	}

	public void setSumAmount(BigDecimal sumAmount) {
		this.sumAmount = sumAmount;
	}
	
	public List<TreatmentType> getTreatmentTypes() {
		if(treatmentTypes == null) {
			try{
				treatmentTypes = logicCashier.getTreatmentType();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return treatmentTypes;
	}
	
	public void setTreatmentTypes(List<TreatmentType> treatmentTypes) {
		this.treatmentTypes = treatmentTypes;
	}
	
	public TreatmentType getSelectTreatmentType() {
		return selectTreatmentType;
	}
	
	public void setSelectTreatmentType(TreatmentType selectTreatmentType) {
		this.selectTreatmentType = selectTreatmentType;
	}
	
	public void selectTreatmentType(){
		if(selectTreatmentType != null) {
			try{
				treatments = logicCashier.getTreatments(selectTreatmentType.getPkId());
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}
	
	public void chosenTreatmentType(){
		if(selectTreatment != null) {
			int count = 0;
			for (Treatment tr : getListTreatment()) {
				if(tr.getPkId().compareTo(selectTreatment.getPkId()) == 0) count++;
			}
			if(count < 1){
				getListTreatment().add(selectTreatment);
			}
		}
	}
	
	public List<Treatment> getTreatments() {
		return treatments;
	}
	
	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	public Treatment getSelectTreatment() {
		return selectTreatment;
	}
	
	public void setSelectTreatment(Treatment selectTreatment) {
		this.selectTreatment = selectTreatment;
	}
	
	public List<Treatment> getListTreatment() {
		if(listTreatment == null) {
			listTreatment = new ArrayList<>();
		}
		return listTreatment;
	}
	
	public void setListTreatment(List<Treatment> listTreatment) {
		this.listTreatment = listTreatment;
	}
	
	public void insertTreatments(){
		try{
			CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
			for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl){
				if(Tool.LAST.equals(dtl.getStatus())){
					byInspectionDtl = dtl;
				}
			}
			listTreatmentCashByInspectionDtl.remove(byInspectionDtl);
			for(Treatment treatment : getListTreatment()){
				int count = 0;
				for(CashByInspectionDtl dtl : listTreatmentCashByInspectionDtl){
					if(dtl.getTypePkId().compareTo(treatment.getPkId()) == 0) count++;
				}
				if(count < 1){
					CashByInspectionDtl inspectionDtl = new CashByInspectionDtl();
					inspectionDtl.setName(treatment.getName());
					inspectionDtl.setType(Tool.INSPECTIONTYPE_TREATMENT);
					inspectionDtl.setTypePkId(treatment.getPkId());
					inspectionDtl.setPrice(treatment.getPrice());
					inspectionDtl.setDayLength(10000000);
					inspectionDtl.setStatus(Tool.ADDED);
					inspectionDtl.setInspectionDtlPkId(new BigDecimal(-1));
					inspectionDtl.setAllAmount(BigDecimal.ZERO);
					listTreatmentCashByInspectionDtl.add(inspectionDtl);
				}
			}
			listTreatment = null;
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("@(.treatmentList1)");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public Date getBeginDate() {
		if(beginDate == null) beginDate = new Date();
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		if(endDate == null) endDate = new Date();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Employee> getXrayEmployeeList() {
		if(xrayEmployeeList == null) {
			try{
				xrayEmployeeList = logicCashier.getEmployeeListByXray();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return xrayEmployeeList;
	}
	
	public void setXrayEmployeeList(List<Employee> xrayEmployeeList) {
		this.xrayEmployeeList = xrayEmployeeList;
	}

	public String getRetBillPassword() {
		return retBillPassword;
	}

	public void setRetBillPassword(String retBillPassword) {
		this.retBillPassword = retBillPassword;
	}

	public String getRetBillDescription() {
		return retBillDescription;
	}

	public void setRetBillDescription(String retBillDescription) {
		this.retBillDescription = retBillDescription;
	}

	public List<PaymentPackage> getListPaymentPackage() {
		try{
			if(listPaymentPackage == null) {
				listPaymentPackage = logicCashier.getListPaymentPackage();
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return listPaymentPackage;
	}

	public void setListPaymentPackage(List<PaymentPackage> listPaymentPackage) {
		this.listPaymentPackage = listPaymentPackage;
	}
	
	public void paymentGroupDtlDelete(String type, BigDecimal typePkId){
		if(type == null || typePkId == null) return;
		for(PaymentPackageDtl dtl : getPaymentPackageDtls()){
			if(type.equals(dtl.getType()) && typePkId.compareTo(dtl.getTypePkId()) == 0){
				paymentPackageDtls.remove(dtl);
				RequestContext context = RequestContext.getCurrentInstance();
				context.execute("updateTmpPaymentPackageDtls();");
				return;
			}
		}
	}
	
	public List<PaymentPackageDtl> getPaymentPackageDtls() {
		if(paymentPackageDtls == null) paymentPackageDtls = new ArrayList<>();
		return paymentPackageDtls;
	}
	
	public void setPaymentPackageDtls(List<PaymentPackageDtl> paymentPackageDtls) {
		this.paymentPackageDtls = paymentPackageDtls;
	}
	
	public List<PaymentPackageDtl> getTmpPaymentPackageDtls() {
		tmpPaymentPackageDtls = new ArrayList<>();
		for (PaymentPackageDtl item : getPaymentPackageDtls()) {
			tmpPaymentPackageDtls.add(item);
		}
		PaymentPackageDtl paymentPackageDtl = new PaymentPackageDtl();
		paymentPackageDtl.setStatus(Tool.LAST);
		tmpPaymentPackageDtls.add(paymentPackageDtl);
		return tmpPaymentPackageDtls;
	}
	
	public void setTmpPaymentPackageDtls(List<PaymentPackageDtl> tmpPaymentPackageDtls) {
		this.tmpPaymentPackageDtls = tmpPaymentPackageDtls;
	}
	
	public String getSelectedPaymentPackageType() {
		if(selectedPaymentPackageType == null || "".equals(selectedPaymentPackageType)){
			selectedPaymentPackageType = Tool.INSPECTIONTYPE_XRAY;
		}
		return selectedPaymentPackageType;
	}
	
	public void setSelectedPaymentPackageType(String selectedPaymentPackageType) {
		packageSelectTypes = null;
		this.selectedPaymentPackageType = selectedPaymentPackageType;
	}
	
	public List<PaymentPackageSelectItem> getPackageSelectItems() {
		if(packageSelectItems == null) {
			packageSelectItems = new ArrayList<>();
			if(getPackageSelectType() != null){
				try{
					packageSelectItems = logicCashier.getPackageSelectItems(getPackageSelectType().getType(), getPackageSelectType().getPkId());
				}catch(Exception ex){
					getUserSessionController().showErrorMessage(ex.getMessage());
				}
			}
		}
		return packageSelectItems;
	}
	
	public void setPackageSelectItems(List<PaymentPackageSelectItem> packageSelectItems) {
		this.packageSelectItems = packageSelectItems;
	}
	
	public List<PaymentPackageSelectType> getPackageSelectTypes() {
		if(packageSelectTypes == null) {
			try{
				packageSelectTypes = logicCashier.getPackageSelectTypes(getSelectedPaymentPackageType());
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return packageSelectTypes;
	}
	
	public void setPackageSelectTypes(List<PaymentPackageSelectType> packageSelectTypes) {
		this.packageSelectTypes = packageSelectTypes;
	}
	
	public PaymentPackageSelectType getPackageSelectType() {
		return packageSelectType;
	}
	
	public void setPackageSelectType(PaymentPackageSelectType packageSelectType) {
		this.packageSelectType = packageSelectType;
	}
	
	public PaymentPackageSelectItem getPackageSelectItem() {
		return packageSelectItem;
	}
	
	public void setPackageSelectItem(PaymentPackageSelectItem packageSelectItem) {
		this.packageSelectItem = packageSelectItem;
	}
	
	public void selectedPSI(){
		if(packageSelectItem == null || packageSelectItem.getPkId() == null || packageSelectItem.getType() == null) return;
		for(PaymentPackageDtl dtl : getPaymentPackageDtls()){
			if(packageSelectItem.getPkId().compareTo(dtl.getTypePkId()) == 0 && packageSelectItem.getType().equals(dtl.getType())) {
				getUserSessionController().showWarningMessage("Сонгогдсон байна.");
				return;
			}
		}
		
		PaymentPackageDtl packageDtl = new PaymentPackageDtl();
		packageDtl.setPkId(BigDecimal.ZERO);
		packageDtl.setPaymentPackagePkId(BigDecimal.ZERO);
		packageDtl.setType(packageSelectItem.getType());
		packageDtl.setTypePkId(packageSelectItem.getPkId());
		packageDtl.setAmount(packageSelectItem.getPrice());
		packageDtl.setId(packageSelectItem.getId());
		packageDtl.setName(packageSelectItem.getName());
		packageDtl.setStatus(Tool.ADDED);
		getPaymentPackageDtls().add(packageDtl);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('selectTSDEM').hide();");
		context.execute("updateTmpPaymentPackageDtls();");;
	}
	
	public PaymentPackage getPaymentPackage() {
		if(paymentPackage == null) {
			paymentPackage = new PaymentPackage();
			paymentPackage.setStatus(Tool.ADDED);
		}
		paymentPackage.setAmount(BigDecimal.ZERO);
		for(PaymentPackageDtl dtl : getPaymentPackageDtls()){
			paymentPackage.setAmount(paymentPackage.getAmount().add(dtl.getAmount()));
		}
		return paymentPackage;
	}
	
	public void setPaymentPackage(PaymentPackage paymentPackage) {
		this.paymentPackage = paymentPackage;
	}
	
	public String savePaymentPackage(){
		String ret = "";
		
		try{
			if(getPaymentPackage().getName() == null || getPaymentPackage().getName().isEmpty()){
				getUserSessionController().showWarningMessage("Нэр оруулаагүй байна ! ! !");
				return "";
			}
			if(getPaymentPackage().getAmount() == null || getPaymentPackage().getAmount().compareTo(BigDecimal.ZERO) == 0){
				getUserSessionController().showWarningMessage("Үнэ байхгүй байна ! ! !");
				return "";
			}
			
			logicCashier.savePaymentPackage(getPaymentPackage(), getPaymentPackageDtls(), getUserSessionController().getLoggedInfo());
			listPaymentPackage = null;
			ret = "cash_list";
			getUserSessionController().showSuccessMessage("Амжилттай хадгаллаа.");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public void paymentGroupNew(){
		paymentPackage = null;
		paymentPackageDtls = null;
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:paymentGroupEdit");
		context.execute("PF('paymentGroupEdit').show();");
	}
	
	public void paymentGroupEdit(BigDecimal paymentPackagePkId){
		try{
			paymentPackage = logicCashier.getPaymentPackageByPkId(paymentPackagePkId);
			paymentPackage.setStatus(Tool.MODIFIED);
			paymentPackageDtls = logicCashier.getPaymentPackageDtlsByPaymentPackagePkId(paymentPackagePkId);
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:paymentGroupEdit");
			context.execute("PF('paymentGroupEdit').show();");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public String paymentGroupSelect(BigDecimal paymentPackagePkId){
		String ret = "";
		
		if(paymentPackagePkId == null) return ret;
		try{
			if(listXrayCashByInspectionDtl.size() > 0 && listXrayCashByInspectionDtl.get(listXrayCashByInspectionDtl.size() - 1).getStatus().equals(Tool.LAST)) {listXrayCashByInspectionDtl.remove(listXrayCashByInspectionDtl.size() - 1);}
			if(listSurgeryCashByInspectionDtl.size() > 0 && listSurgeryCashByInspectionDtl.get(listSurgeryCashByInspectionDtl.size() - 1).getStatus().equals(Tool.LAST)) {listSurgeryCashByInspectionDtl.remove(listSurgeryCashByInspectionDtl.size() - 1);}
			if(listTreatmentCashByInspectionDtl.size() > 0 && listTreatmentCashByInspectionDtl.get(listTreatmentCashByInspectionDtl.size() - 1).getStatus().equals(Tool.LAST)) {listTreatmentCashByInspectionDtl.remove(listTreatmentCashByInspectionDtl.size() - 1);}
			if(listExamnationCashByInspectionDtl.size() > 0 && listExamnationCashByInspectionDtl.get(listExamnationCashByInspectionDtl.size() - 1).getStatus().equals(Tool.LAST)) {listExamnationCashByInspectionDtl.remove(listExamnationCashByInspectionDtl.size() - 1);}
			List<PaymentPackageDtl> dtls = logicCashier.getPaymentPackageDtlsByPaymentPackagePkId(paymentPackagePkId);
			for (PaymentPackageDtl paymentPackageDtl : dtls) {
				if(Tool.INSPECTIONTYPE_XRAY.equals(paymentPackageDtl.getType())){
					CashByInspectionDtl exam = new CashByInspectionDtl();
					exam.setName(paymentPackageDtl.getName());
					exam.setType(Tool.INSPECTIONTYPE_XRAY);
					exam.setTypePkId(paymentPackageDtl.getTypePkId());
					exam.setPrice(paymentPackageDtl.getAmount());
					exam.setSelected(true);
					exam.setStatus(Tool.ADDED);
					listXrayCashByInspectionDtl.add(exam);
				}
				if(Tool.INSPECTIONTYPE_SURGERY.equals(paymentPackageDtl.getType())){
					CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
					byInspectionDtl.setType(Tool.INSPECTIONTYPE_SURGERY);
					byInspectionDtl.setTypePkId(paymentPackageDtl.getTypePkId());
					byInspectionDtl.setPrice(paymentPackageDtl.getAmount());
					byInspectionDtl.setName(paymentPackageDtl.getName());
					byInspectionDtl.setSelected(true);
					byInspectionDtl.setStatus(Tool.ADDED);
					listSurgeryCashByInspectionDtl.add(byInspectionDtl);
				}
				if(Tool.INSPECTIONTYPE_TREATMENT.equals(paymentPackageDtl.getType())){
					CashByInspectionDtl inspectionDtl = new CashByInspectionDtl();
					inspectionDtl.setName(paymentPackageDtl.getName());
					inspectionDtl.setType(Tool.INSPECTIONTYPE_TREATMENT);
					inspectionDtl.setTypePkId(paymentPackageDtl.getTypePkId());
					inspectionDtl.setPrice(paymentPackageDtl.getAmount());
					inspectionDtl.setDayLength(10000000);
					inspectionDtl.setStatus(Tool.ADDED);
					inspectionDtl.setInspectionDtlPkId(new BigDecimal(-1));
					inspectionDtl.setAllAmount(BigDecimal.ZERO);
					listTreatmentCashByInspectionDtl.add(inspectionDtl);
				}
				if(Tool.INSPECTIONTYPE_EXAMINATION.equals(paymentPackageDtl.getType())){
					CashByInspectionDtl byInspectionDtl = new CashByInspectionDtl();
					byInspectionDtl.setName(paymentPackageDtl.getName());
					byInspectionDtl.setType(Tool.INSPECTIONTYPE_EXAMINATION);
					byInspectionDtl.setTypePkId(paymentPackageDtl.getTypePkId());
					byInspectionDtl.setPrice(paymentPackageDtl.getAmount());
					byInspectionDtl.setSelected(true);
					byInspectionDtl.setStatus(Tool.ADDED);
					byInspectionDtl.calcPriceByExaminationDtl();
					listExamnationCashByInspectionDtl.add(byInspectionDtl);
				}
			}
			calculatePayment1();
			ret = "cash_register";
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public void paymentGroupDelete(BigDecimal paymentPackagePkId){
		try{
			this.paymentPackagePkId = paymentPackagePkId;
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('cnfD').show();");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public String paymentGroupDelete(){
		String ret = "";
		try{
			logicCashier.deletePaymentPackage(this.paymentPackagePkId);
			listPaymentPackage = null;
			ret = "cash_list";
			getUserSessionController().showSuccessMessage("Амжилттай устаглаа");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return ret;
	}
	
	public BigDecimal getPaymentPackagePkId() {
		return paymentPackagePkId;
	}
	
	public void setPaymentPackagePkId(BigDecimal paymentPackagePkId) {
		this.paymentPackagePkId = paymentPackagePkId;
	}
	
}

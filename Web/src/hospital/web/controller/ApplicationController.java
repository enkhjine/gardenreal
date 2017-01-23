package hospital.web.controller;

import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Customer;
import hospital.entity.Diagnose;
import hospital.entity.EconomicCalendar;
import hospital.entity.InspectionDtl;
import hospital.entity.Payment;
import hospital.entity.SubOrganization;
import hospital.entity.SurgeryType;
import hospital.entity.Treatment;
import hospital.entity.TreatmentType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import logic.data.Tools;

@ApplicationScoped
@ManagedBean(name = "applicationController")
public class ApplicationController implements Serializable {

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cssPath = "/resource/css/source/";
	private String jsPath = "/resource/js/source/";
	private String path = "/resource/js/source/";
	private String imagePath = "D:/MedITm/Rentgen/";
	private String inspectionImagePath = "D:/MedITm/";
	private boolean listCash;
	private List<Customer> listCustomer;
	private List<String> diagnoseTypeList;
	private LazyDataModel<Diagnose> lazyDiagnoseList;
	private LazyDataModel<Customer> lazyCustomerList;
	private LazyDataModel<Customer> lazyCustomerList1;

	private boolean diagnoseRefresh = false;
	private List<SubOrganization> subOrganization;
	private List<TreatmentType> treatmentType;
	private List<SurgeryType> surgeryType;
	private boolean subOrgaRefresh = false;

	private String customerNo;

	private volatile int count;

	private EconomicCalendar currentEconomicCalendar;

	// newORderPropertys
	private List<InspectionDtl> inspectionDtlTreatmentList;
	private List<InspectionDtl> inspectionDtlSurgeryList;
	private List<InspectionDtl> inspectionDtlXrayList;
	private List<InspectionDtl> inspectionDtlExaminationtList;

	private List<Treatment> listTreatment;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ApplicationController() {

	}

	public void test() {
		System.out.println("OUT : ");
	}
	
	public void test(String text) {
		System.out.println("OUT : " + text);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("ApplicationController : initData");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		if (fc.isProjectStage(ProjectStage.Production)) {
			setCssPath(request.getContextPath() + "/resource/css/source/");
			setJsPath(request.getContextPath() + "/resource/js/source/");
			setPath(request.getContextPath() + "/resource/");
		} else {
			setCssPath(request.getContextPath() + "/resource/css/source/");
			setJsPath(request.getContextPath() + "/resource/js/source/");
			setPath(request.getContextPath() + "/resource/");
		}
		// sendData();
		System.out.println("ApplicationController : initData");
	}
	
	public void billBack(Payment payment){
		System.out.println(mn.mta.pos.exam.BridgePosAPI.billBack(payment.getBillId(), payment.getBillDate()));
	}

	public void sendData() {
		System.out.println(mn.mta.pos.exam.BridgePosAPI.test());
	}

	public Payment paymentBridgePosAPI(Payment payment, List<CashByInspectionDtl> listRequestByInspectionDtl,
			List<CashByInspectionDtl> listTreatmentCashByInspectionDtl,
			List<CashByInspectionDtl> listXrayCashByInspectionDtl,
			List<CashByInspectionDtl> listExamnationCashByInspectionDtl,
			List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) {
		try {
			String ret = mn.mta.pos.exam.BridgePosAPI.pusBridgePosAPI(payment.getPkId(), payment.getPaidAmount(),
					BigDecimal.ZERO, payment.getPaidAmount(), BigDecimal.ZERO, BigDecimal.ZERO, "51", getCustomerNo(),
					payment.getDiscountPercent(), listRequestByInspectionDtl, listTreatmentCashByInspectionDtl,
					listXrayCashByInspectionDtl, listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			System.out.println("POSAPI");
			System.out.println(ret);
			if ("true".equals(mn.mta.pos.exam.BridgePosAPI.getValue("success", ret))) {
				payment.setHasLottery(1);
				payment.setRegisterNo(mn.mta.pos.exam.BridgePosAPI.getValue("registerNo", ret));
				payment.setBillId(mn.mta.pos.exam.BridgePosAPI.getValue("billId", ret));
				payment.setMacAddress(mn.mta.pos.exam.BridgePosAPI.getValue("macAddress", ret));
				payment.setBillDate(mn.mta.pos.exam.BridgePosAPI.getValue("date", ret));
				payment.setLottery(mn.mta.pos.exam.BridgePosAPI.getValue("lottery", ret));
				payment.setBillType(mn.mta.pos.exam.BridgePosAPI.getValue("billType", ret));
				payment.setInternalCode(mn.mta.pos.exam.BridgePosAPI.getValue("internalCode", ret));
				payment.setQrData(mn.mta.pos.exam.BridgePosAPI.getValue("qrData", ret));
				payment.setMerchantId(mn.mta.pos.exam.BridgePosAPI.getValue("merchantId", ret));

				if ("lotteryWarningMsg".equals(payment.getLottery())) {
					payment.setLottery(mn.mta.pos.exam.BridgePosAPI.getValue("lotteryWarningMsg", ret));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("POSAPI CONNECTION ERROR");
		}
		return payment;
	}

	public Payment cashPaymentBridgePosAPI(Payment payment, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl,
			List<CashByInspectionDtl> listXrayCashByInspectionDtl,
			List<CashByInspectionDtl> listExamnationCashByInspectionDtl,
			List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) {
		try {
			String ret = mn.mta.pos.exam.BridgePosAPI.cashPusBridgePosAPI(payment.getPkId(), payment.getPaidAmount(),
					BigDecimal.ZERO, payment.getPaidAmount(), BigDecimal.ZERO, BigDecimal.ZERO, "51", getCustomerNo(),
					payment.getDiscountPercent(), listTreatmentCashByInspectionDtl, listXrayCashByInspectionDtl,
					listExamnationCashByInspectionDtl, listSurgeryCashByInspectionDtl);
			System.out.println("POSAPI");
			System.out.println(ret);
			if ("true".equals(mn.mta.pos.exam.BridgePosAPI.getValue("success", ret))) {
				payment.setHasLottery(1);
				payment.setRegisterNo(mn.mta.pos.exam.BridgePosAPI.getValue("registerNo", ret));
				payment.setBillId(mn.mta.pos.exam.BridgePosAPI.getValue("billId", ret));
				payment.setMacAddress(mn.mta.pos.exam.BridgePosAPI.getValue("macAddress", ret));
				payment.setBillDate(mn.mta.pos.exam.BridgePosAPI.getValue("date", ret));
				payment.setLottery(mn.mta.pos.exam.BridgePosAPI.getValue("lottery", ret));
				payment.setBillType(mn.mta.pos.exam.BridgePosAPI.getValue("billType", ret));
				payment.setInternalCode(mn.mta.pos.exam.BridgePosAPI.getValue("internalCode", ret));
				payment.setQrData(mn.mta.pos.exam.BridgePosAPI.getValue("qrData", ret));
				payment.setMerchantId(mn.mta.pos.exam.BridgePosAPI.getValue("merchantId", ret));

				if ("lotteryWarningMsg".equals(payment.getLottery())) {
					payment.setLottery(mn.mta.pos.exam.BridgePosAPI.getValue("lotteryWarningMsg", ret));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("POSAPI CONNECTION ERROR");
		}
		return payment;
	}

	public String getCustomerNo() {
		if (customerNo == null || customerNo.length() < 5) {
			try {
				customerNo = logicTwo.getSystemConfigsByCustomerNo();
			} catch (Exception ex) {

			}
		}
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String url(String url) {
		return url;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isListCash() {
		if (listCash) {
			listCash = false;
			return true;
		}
		return listCash;
	}

	public void setListCash(boolean listCash) {
		this.listCash = listCash;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Customer> getListCustomer() {
		try {
			if (listCustomer == null) {
				listCustomer = logicCustomer.getCustomers();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return listCustomer;
	}

	public void setListCustomer(List<Customer> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public EconomicCalendar getCurrentEconomicCalendar() {
		if (currentEconomicCalendar == null) {
			try {
				currentEconomicCalendar = logicTwo.getEconomicCalendar();
			} catch (Exception ex) {

			}
		}
		return currentEconomicCalendar;
	}

	public void setCurrentEconomicCalendar(EconomicCalendar currentEconomicCalendar) {
		this.currentEconomicCalendar = currentEconomicCalendar;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public List<String> getDiagnoseTypeList() {
		if (diagnoseTypeList == null) {
			try {
				diagnoseTypeList = new ArrayList<String>();
				diagnoseTypeList.add("A");
				diagnoseTypeList.add("B");
				diagnoseTypeList.add("C");
				diagnoseTypeList.add("D");
				diagnoseTypeList.add("E");
				diagnoseTypeList.add("F");
				diagnoseTypeList.add("G");
				diagnoseTypeList.add("H");
				diagnoseTypeList.add("I");
				diagnoseTypeList.add("J");
				diagnoseTypeList.add("K");
				diagnoseTypeList.add("L");
				diagnoseTypeList.add("M");
				diagnoseTypeList.add("N");
				diagnoseTypeList.add("O");
				diagnoseTypeList.add("P");
				diagnoseTypeList.add("Q");
				diagnoseTypeList.add("R");
				diagnoseTypeList.add("S");
				diagnoseTypeList.add("T");
				diagnoseTypeList.add("U");
				diagnoseTypeList.add("V");
				diagnoseTypeList.add("W");
				diagnoseTypeList.add("X");
				diagnoseTypeList.add("Y");
				diagnoseTypeList.add("Z");
				// diagnoseTypeList = infoLogic.getDiagnoseTypeList();
			} catch (Exception ex) {

			}
		}
		return diagnoseTypeList;
	}

	public void setDiagnoseTypeList(List<String> diagnoseTypeList) {
		this.diagnoseTypeList = diagnoseTypeList;
	}

	public List<InspectionDtl> getInspectionDtlExaminationtList() {
		if (inspectionDtlExaminationtList == null) {
			try {
				inspectionDtlExaminationtList = logicInspection.getInspectionDtls(Tool.INSPECTIONTYPE_EXAMINATION);
			} catch (Exception ex) {

			}
		}
		return inspectionDtlExaminationtList;
	}

	public void setInspectionDtlExaminationtList(List<InspectionDtl> inspectionDtlExaminationtList) {
		this.inspectionDtlExaminationtList = inspectionDtlExaminationtList;
	}

	public List<InspectionDtl> getInspectionDtlSurgeryList() {
		if (inspectionDtlSurgeryList == null) {
			try {
				inspectionDtlSurgeryList = logicInspection.getInspectionDtls(Tool.INSPECTIONTYPE_SURGERY);
			} catch (Exception ex) {

			}
		}
		return inspectionDtlSurgeryList;
	}

	public void setInspectionDtlSurgeryList(List<InspectionDtl> inspectionDtlSurgeryList) {
		this.inspectionDtlSurgeryList = inspectionDtlSurgeryList;
	}

	public List<InspectionDtl> getInspectionDtlTreatmentList() {
		if (inspectionDtlTreatmentList == null) {
			try {
				inspectionDtlTreatmentList = logicInspection.getInspectionDtls(Tool.INSPECTIONTYPE_TREATMENT);
			} catch (Exception ex) {

			}
		}
		return inspectionDtlTreatmentList;
	}

	public void setInspectionDtlTreatmentList(List<InspectionDtl> inspectionDtlTreatmentList) {
		this.inspectionDtlTreatmentList = inspectionDtlTreatmentList;
	}

	public List<InspectionDtl> getInspectionDtlXrayList() {
		if (inspectionDtlXrayList == null) {
			try {
				inspectionDtlXrayList = logicInspection.getInspectionDtls(Tool.INSPECTIONTYPE_XRAY);
			} catch (Exception ex) {

			}
		}
		return inspectionDtlXrayList;
	}

	public void setInspectionDtlXrayList(List<InspectionDtl> inspectionDtlXrayList) {
		this.inspectionDtlXrayList = inspectionDtlXrayList;
	}

	public void newDecimal10() {
		System.out.println(Tools.newDecimal10());
	}

	public boolean isDiagnoseRefresh() {
		return diagnoseRefresh;
	}

	public void setDiagnoseRefresh(boolean diagnoseRefresh) {
		this.diagnoseRefresh = diagnoseRefresh;
	}

	public String getInspectionImagePath() {
		return inspectionImagePath;
	}

	public void setInspectionImagePath(String inspectionImagePath) {
		this.inspectionImagePath = inspectionImagePath;
	}

	public List<SubOrganization> getSubOrganization() {
		if (subOrgaRefresh || subOrganization == null) {
			try {
				subOrganization = infoLogic.getSubOrganizations();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return subOrganization;
	}

	public void setSubOrganization(List<SubOrganization> subOrganization) {
		this.subOrganization = subOrganization;
	}

	public boolean isSubOrgaRefresh() {
		return subOrgaRefresh;
	}

	public void setSubOrgaRefresh(boolean subOrgaRefresh) {
		this.subOrgaRefresh = subOrgaRefresh;
	}

	public List<TreatmentType> getTreatmentType() {
		if (treatmentType == null) {
			try {
				treatmentType = infoLogic.getTreatmentTypes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return treatmentType;
	}

	public void setTreatmentType(List<TreatmentType> treatmentType) {
		this.treatmentType = treatmentType;
	}

	public List<SurgeryType> getSurgeryType() {
		if (treatmentType == null) {
			try {
				surgeryType = infoLogic.getSurgeryTypes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return surgeryType;
	}

	public void setSurgeryType(List<SurgeryType> surgeryType) {
		this.surgeryType = surgeryType;
	}

	public LazyDataModel<Customer> getLazyCustomerList() {
		if (lazyCustomerList == null) {
			lazyCustomerList = new LazyDataModel<Customer>() {

				public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					List<Customer> data = new ArrayList<Customer>();
					String sortType = "";
					if (sortOrder.equals(SortOrder.ASCENDING)) {
						sortType = "ASC";
					} else if (sortOrder.equals(SortOrder.DESCENDING)) {
						sortType = "DESC";
					} else if (sortOrder.equals(SortOrder.UNSORTED)) {
						sortType = "";
					}
					List<Customer> result = new ArrayList<Customer>();
					try {
						Map<String, String> map = new HashMap<>();
						for (Map.Entry<String, String> entry : filters.entrySet()) {
							map.put(entry.getKey(), entry.getValue().toString());
						}
						result = infoLogic.getCustomers(first, pageSize, sortField, sortType, map);
						lazyCustomerList.setRowCount((int) infoLogic.getCustomerCount(map));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return result;
				}
			};
		}
		return lazyCustomerList;
	}

	public void setLazyCustomerList(LazyDataModel<Customer> lazyCustomerList) {
		this.lazyCustomerList = lazyCustomerList;
	}
	
	public LazyDataModel<Customer> getLazyCustomerList1() {
		if (lazyCustomerList1 == null) {
			lazyCustomerList1 = new LazyDataModel<Customer>() {

				public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					List<Customer> data = new ArrayList<Customer>();
					String sortType = "";
					if (sortOrder.equals(SortOrder.ASCENDING)) {
						sortType = "ASC";
					} else if (sortOrder.equals(SortOrder.DESCENDING)) {
						sortType = "DESC";
					} else if (sortOrder.equals(SortOrder.UNSORTED)) {
						sortType = "";
					}
					List<Customer> result = new ArrayList<Customer>();
					try {
						Map<String, String> map = new HashMap<>();
						for (Map.Entry<String, String> entry : filters.entrySet()) {
							map.put(entry.getKey(), entry.getValue().toString());
						}
						result = infoLogic.getCustomers(first, pageSize, sortField, sortType, map);
						lazyCustomerList1.setRowCount((int) infoLogic.getCustomerCount(map));
					} catch (Exception e) {
						e.printStackTrace();
					}
					return result;
				}
			};
		}
		return lazyCustomerList1;
	}

	public void setLazyCustomerList1(LazyDataModel<Customer> lazyCustomerList1) {
		this.lazyCustomerList1 = lazyCustomerList1;
	}

	public LazyDataModel<Diagnose> getLazyDiagnoseList() {
		if (lazyDiagnoseList == null) {
			lazyDiagnoseList = new LazyDataModel<Diagnose>() {
				private static final long serialVersionUID = 5473816916745269454L;

				@Override
				public List<Diagnose> load(int first, int pageSize, String sortField, SortOrder sortOrder,
						Map<String, String> filters) {
					String sortType = "";
					if (sortOrder.equals(SortOrder.ASCENDING)) {
						sortType = "ASC";
					} else if (sortOrder.equals(SortOrder.DESCENDING)) {
						sortType = "DESC";
					} else if (sortOrder.equals(SortOrder.UNSORTED)) {
						sortType = "";
					}
					List<Diagnose> result = new ArrayList<Diagnose>();
					try {
						Map<String, String> map = new HashMap<>();
						for (Map.Entry<String, String> entry : filters.entrySet()) {
							map.put(entry.getKey(), entry.getValue().toString());
						}
						result = infoLogic.getDiagnoses(first, pageSize, sortField, sortType, map);
						lazyDiagnoseList.setRowCount((int) infoLogic.getDiagnosesCount(map));
						System.out.println("first:" + first + " count: " + pageSize);
						System.out.println(
								"result size:" + result.size() + " result count: " + lazyDiagnoseList.getRowCount());
					} catch (Exception e) {
						e.printStackTrace();
					}
					return result;
				}
			};
		}

		return lazyDiagnoseList;
	}

	public void setLazyDiagnoseList(LazyDataModel<Diagnose> lazyDiagnoseList) {
		this.lazyDiagnoseList = lazyDiagnoseList;
	}

	public List<Treatment> getListTreatment() {
		if (listTreatment == null) {
			try {
				listTreatment = logicTwo.getListTreatment();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		return listTreatment;
	}

	public void setListTreatment(List<Treatment> listTreatment) {
		this.listTreatment = listTreatment;
	}
}
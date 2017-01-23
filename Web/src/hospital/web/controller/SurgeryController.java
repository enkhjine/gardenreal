package hospital.web.controller;

import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.Tool;
import hospital.businessentity.TreatmentTypeTreatment;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicSurgeryLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Employee;
import hospital.entity.ICT19;
import hospital.entity.Item;
import hospital.entity.Measurement;
import hospital.entity.PriceHistory;
import hospital.entity.SubOrganizationType;
import hospital.entity.Surgery;
import hospital.entity.SurgeryDoctor;
import hospital.entity.SurgeryIctMap;
import hospital.entity.SurgeryIctModel;
import hospital.entity.SurgeryPrice;
import hospital.entity.SurgeryType;
import hospital.entity.Treatment;
import hospital.entity.TreatmentDtl;
import hospital.entity.TreatmentModel;
import hospital.entity.TreatmentPrice;
import hospital.entity.TreatmentType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.web.controller.ApplicationController;
import hospital.web.controller.UserSessionController;
@SessionScoped
@ManagedBean(name = "surgeryController")
public class SurgeryController {
	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "LogicTreatment")
	ILogicTreatmentLocal logicTreatment;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal logicXray;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "LogicSurgery")
	ILogicSurgeryLocal logicSurgery;
	
	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
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
	
	// Lists
	private List<Surgery> surgeries;
	private List<SurgeryType> surgeryTypes;
	private List<Employee> employees;
	private List<String> selectedEmployeePkId;
	private List<SurgeryDoctor> selectedDoctor;
	private List<ICT19Dtl> listSelectedIctMap;
	private List<ICT19Dtl> tableIctItem;
	private List<ICT19Dtl> listTableIct19;
	private List<ICT19Dtl> listict19TempDtl;
	private List<ICT19Dtl> listICtMaps;
	private List<ICT19Dtl> listSurgeryModel;
	private List<ICT19Dtl> lsitSurgeryModelTemp;
	
	
	
	// Cursors
	private Surgery currentSurgery;
	private SurgeryType currentSurgeryType;
	private SurgeryPrice currentSurgeryPrice;
	private ICT19 ict19;
	private ICT19Dtl ict19Dtl;
	private ICT19Dtl  chosenSelectIct19;
	private SurgeryIctMap  ictMap;
	private SurgeryIctModel ictModel;
	// Filters
	private BigDecimal filterPkId;
	private boolean filterCheck = false;
	private boolean pcsCodeSelectValue;
	private boolean isSurgeryModel;
	private boolean pcsCodeinsertValue;
	private boolean pcsCodeModelValue ;
	private ICT19Dtl selectionModelValue;
	
	// Region - Function
	
	public void getRegData() {
		try {
			employees = infoLogic.getEmployees();
			selectedEmployeePkId = new ArrayList<String>();
			for(SurgeryDoctor doctor: selectedDoctor) {
				doctor.setStatus(Tool.MODIFIED);
				selectedEmployeePkId.add(doctor.getPkId().toString());
			}
			surgeryTypes = logicSurgery.getSurgeryTypes();
			
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:employees");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}
	
	public void saveSurgeryType() {
		try {
			if(currentSurgeryType.getName() == null && currentSurgeryType.getName().trim().equals("")) {
				userSessionController.showWarningMessage("Хагалгааны төрлийн нэр бичнэ үү.");
				return;
			}
			
			currentSurgeryType.setStatus(Tool.ADDED);
			logicSurgery.saveSurgeryType(currentSurgeryType);
			currentSurgeryType = new SurgeryType();
			RequestContext context = RequestContext.getCurrentInstance();
			surgeryTypes = logicSurgery.getSurgeryTypes();
			context.update("form:surgeryTypeName form:ttname");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}
	
	public String saveSurgery() {
		if(currentSurgery.getSurgeryTypePkId().equals(BigDecimal.ZERO)) {
			userSessionController.showWarningMessage("Хагалгааны төрөл сонгоно уу.");
			return "";
		}
		if(currentSurgery.getName() != null && currentSurgery.getName().trim().equals("")) {
			userSessionController.showWarningMessage("Хагалгааны нэр бичнэ үү.");
			return "";
		}
		
		if(currentSurgeryPrice.getBeginDate().before(Tool.addDays(new Date(), -1)) && currentSurgeryPrice.getPkId() == null ) {
			userSessionController.showWarningMessage("Үнэ эхлэх огноо өнгөрсөн огноо байх ёсгүй. Өөр огноо сонгоно уу.");
			return "";
		}
		selectedDoctor.clear();
		for(String employeePkId : selectedEmployeePkId) {
			SurgeryDoctor doctor = new SurgeryDoctor();
			doctor.setEmployeePkId(new BigDecimal(employeePkId));
			doctor.setStatus(Tool.ADDED);
			selectedDoctor.add(doctor);
		}
		
		try {
			logicSurgery.saveSurgery(currentSurgery, currentSurgeryPrice, selectedDoctor, userSessionController.getLoggedInfo());
			if (tableIctItem.size()==0) {
				
			}
			else {
				getTableIctItem();
				logicSurgery.saveSurgeryICt19(getIctMap(), currentSurgery.getPkId(), tableIctItem);
				listICtMaps = logicSurgery.getICTDtlView(currentSurgery.getPkId());
				logicSurgery.saveSurgeryModel(getIctModel(), listICtMaps, currentSurgery.getName(), isSurgeryModel);
				setTableIctItem(null);
			}
			userSessionController.showMessage(99);
			
			return "surgery_list";
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
			return "";
		}
	}
	public void removeIctItem(int index){
		tableIctItem.remove(index);
	}
	public void newSurgery() {
		currentSurgery = new Surgery();
		currentSurgery.setStatus(Tool.ADDED);
		listICtMaps =  new ArrayList<>();
		selectedEmployeePkId = new ArrayList<String>();
		currentSurgeryPrice = new SurgeryPrice();
		selectedDoctor = new ArrayList<SurgeryDoctor>();
		currentSurgeryPrice = new SurgeryPrice();
		currentSurgeryPrice.setBeginDate(new Date());
	}
	public void loadData() {
		try {
			surgeryTypes = logicSurgery.getSurgeryTypes();
			surgeries = logicSurgery.getSurgery(filterPkId, userSessionController.getLoggedInfo().getOrganization().getPkId(), filterCheck);
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}
	public void changeActive(Surgery surgery) {
		try {
			surgery.setStatus(Tool.MODIFIED);
			logicSurgery.saveSurgery(surgery, null, null, userSessionController.getLoggedInfo());
			userSessionController.showMessage(99);
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадлагалахад алдаа гарлаа.");
		}
	}
	public String editSurgery(Surgery surgery) {
		try {
			setTableIctItem(null);
			currentSurgery = surgery;
			currentSurgery.setStatus(Tool.MODIFIED);
			currentSurgeryPrice = new SurgeryPrice();
			currentSurgeryPrice.setBeginDate(surgery.getUsageDate());
			currentSurgeryPrice.setPrice(surgery.getPrice());
			currentSurgeryPrice.setPkId(surgery.getPkId());
			currentSurgeryPrice.setSurgeryPkId(surgery.getPkId());
			selectedDoctor = logicSurgery.getSurgeryDoctor(surgery.getPkId());
			listSelectedIctMap  = logicSurgery.getICTDtlView(currentSurgery.getPkId());
			
			setPcsCodeSelectValue(true);
			for (int i = 0; i < listSelectedIctMap.size(); i++) {
				ICT19Dtl  dtl  =  new ICT19Dtl();
				dtl.setId(listSelectedIctMap.get(i).getId());
				dtl.setNameEn(listSelectedIctMap.get(i).getNameEn());
				dtl.setNameMn(listSelectedIctMap.get(i).getNameMn());
				getTableIctItem().add(dtl);
			}
			
			return "surgery_register";
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
			return "";
		}
	}
	public void deleteSurgery() {
		try {
			currentSurgery.setStatus(Tool.DELETE);
			logicSurgery.saveSurgery(currentSurgery, null, null, userSessionController.getLoggedInfo());
			System.out.println( "currentSurgery  " + currentSurgery.getPkId());
			userSessionController.showMessage(98);
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}
	
	public void viewIct19(){
		try {
			System.out.println( "viewIct" + currentSurgery.getPkId());
			listTableIct19 = logicSurgery.getSurgeryIct19();
		} catch (Exception e) {
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void rowSelectIct19(){
		 ICT19Dtl dtl  =  new  ICT19Dtl();
		 dtl.setPkId(getChosenSelectIct19().getPkId());
		 dtl.setId(getChosenSelectIct19().getId());
		 dtl.setNameEn(getChosenSelectIct19().getNameEn());
		 dtl.setNameMn(getChosenSelectIct19().getNameMn());
		 if(listict19TempDtl==null)
			 getListict19TempDtl();
		 listict19TempDtl.add(dtl);
	}
	public void rowModelSelect(){
		try {
			lsitSurgeryModelTemp =  logicSurgery.getSurgeryNameSearch(getSelectionModelValue().getSurgeryName());
			for (int i = 0; i < lsitSurgeryModelTemp.size(); i++) {
				ICT19Dtl dtl =  new ICT19Dtl();
				dtl.setPkId(lsitSurgeryModelTemp.get(i).getIct19().getPkId());
				dtl.setId(lsitSurgeryModelTemp.get(i).getIct19().getId());
				dtl.setNameEn(lsitSurgeryModelTemp.get(i).getIct19().getNameEn());
				dtl.setNameMn(lsitSurgeryModelTemp.get(i).getIct19().getNameMn());
				if (listict19TempDtl==null)
					getListict19TempDtl();
				listict19TempDtl.add(dtl);
			}
		} catch (Exception e) {
			// TODO: handle exception
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	public void  hidePcsCodeCheck(){
		pcsCodeModelValue = !pcsCodeinsertValue;
		pcsCodeChoose();
	}
	public void hideModelCheck(){
		pcsCodeinsertValue = !pcsCodeModelValue;
		try {
			listSurgeryModel = logicSurgery.getSurgeryModelView();
		} catch (Exception e) {
			// TODO: handle exception
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		pcsCodeChoose();
	}
	public  void pcsCodeChoose(){
		if (pcsCodeinsertValue) {
			RequestContext.getCurrentInstance().update("form:modelPanelId");
		}
		else
			if (pcsCodeModelValue) {
				RequestContext.getCurrentInstance().update("form:modelPanelId");
			}
	}
	public void  removeChosenIct(int index)
	{
		listict19TempDtl.remove(index);
	}
	
	public void  insertChosenIct19(){
		tableIctItem.remove(tableIctItem.size()-1);
		for (ICT19Dtl dtl:getListict19TempDtl()) {
			ICT19Dtl  dtlCopy = new ICT19Dtl();
			dtlCopy  =  (ICT19Dtl) Tool.deepClone(dtl);
			tableIctItem.add(dtlCopy);
		}
		tableIctItem.add(getIct19Dtl());
		listict19TempDtl.clear();
		RequestContext.getCurrentInstance().update("form:pcsCodeId1");
		RequestContext.getCurrentInstance().update("form:groupIctId");
		RequestContext.getCurrentInstance().execute("PF('registerPSCode').hide()");
	}
	public void saveSurgeryModel(){
		System.out.println( "ictMaps " +getListICtMaps().size());
	}
	// EndRegion

	// Region - GETTER SETTER
	
	public BigDecimal getFilterPkId() {
		if (filterPkId == null)
			filterPkId = new BigDecimal("0");
		return filterPkId;
	}

	public void setFilterPkId(BigDecimal filterPkId) {
		this.filterPkId = filterPkId;
	}
	
	public boolean isFilterCheck() {
		return filterCheck;
	}

	public void setFilterCheck(boolean filterCheck) {
		this.filterCheck = filterCheck;
	}

	public List<Surgery> getSurgeries() {
		if(surgeries == null)
			surgeries = new ArrayList<Surgery>();
		return surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public Surgery getCurrentSurgery() {
		if(currentSurgery == null)
			currentSurgery = new Surgery();
		return currentSurgery;
	}

	public void setCurrentSurgery(Surgery currentSurgery) {
		this.currentSurgery = currentSurgery;
	}

	public List<SurgeryType> getSurgeryTypes() {
		return surgeryTypes;
	}

	public void setSurgeryTypes(List<SurgeryType> surgeryTypes) {
		this.surgeryTypes = surgeryTypes;
	}

	public SurgeryType getCurrentSurgeryType() {
		if(currentSurgeryType == null)
			currentSurgeryType = new SurgeryType();
		return currentSurgeryType;
	}

	public void setCurrentSurgeryType(SurgeryType currentSurgeryType) {
		this.currentSurgeryType = currentSurgeryType;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<String> getSelectedEmployeePkId() {
		if(selectedEmployeePkId == null)
			selectedEmployeePkId = new ArrayList<String>();
		return selectedEmployeePkId;
	}

	public void setSelectedEmployeePkId(List<String> selectedEmployeePkId) {
		this.selectedEmployeePkId = selectedEmployeePkId;
	}

	public SurgeryPrice getCurrentSurgeryPrice() {
		return currentSurgeryPrice;
	}

	public void setCurrentSurgeryPrice(SurgeryPrice currentSurgeryPrice) {
		this.currentSurgeryPrice = currentSurgeryPrice;
	}

	public List<SurgeryDoctor> getSelectedDoctor() {
		if(selectedDoctor == null)
			selectedDoctor = new ArrayList<SurgeryDoctor>();
		return selectedDoctor;
	}

	public void setSelectedDoctor(List<SurgeryDoctor> selectedDoctor) {
		this.selectedDoctor = selectedDoctor;
	}



	public List<ICT19Dtl> getTableIctItem() {
		if (tableIctItem==null) {
			tableIctItem =  new ArrayList<>();
			tableIctItem.add(getIct19Dtl());
		}
		return tableIctItem;
	}

	public void setTableIctItem(List<ICT19Dtl> tableIctItem) {
		this.tableIctItem = tableIctItem;
	}

	public ICT19 getIct19() {
		if (ict19 == null) {
			ict19  =  new ICT19();
			ict19.setStatus(Tool.LAST);
		}
		return ict19;
	}

	public void setIct19(ICT19 ict19) {
		this.ict19 = ict19;
	}

	public List<ICT19Dtl> getListTableIct19() {
		if (listTableIct19==null) {
			listTableIct19  =  new ArrayList<>();
		}
		return listTableIct19;
	}

	public void setListTableIct19(List<ICT19Dtl> listTableIct19) {
		this.listTableIct19 = listTableIct19;
	}

	public ICT19Dtl getChosenSelectIct19() {
		return chosenSelectIct19;
	}

	public void setChosenSelectIct19(ICT19Dtl chosenSelectIct19) {
		this.chosenSelectIct19 = chosenSelectIct19;
	}

	public List<ICT19Dtl> getListict19TempDtl() {
		if(listict19TempDtl==null)
			listict19TempDtl=  new ArrayList<>();
		return listict19TempDtl;
	}

	public void setListict19TempDtl(List<ICT19Dtl> listict19TempDtl) {
		this.listict19TempDtl = listict19TempDtl;
	}

	public ICT19Dtl getIct19Dtl() {
		if(ict19Dtl==null){
			ict19Dtl  =  new ICT19Dtl();
			ict19Dtl.setStatus(Tool.LAST);
		}
			
		return ict19Dtl;
	}

	public void setIct19Dtl(ICT19Dtl ict19Dtl) {
		this.ict19Dtl = ict19Dtl;
	}

	public SurgeryIctMap getIctMap() {
		if(ictMap==null){
			ictMap =  new SurgeryIctMap();
			ictMap.setStatus(Tool.ADDED);
		} 
		else if(ictMap==null){
			ictMap  =  new SurgeryIctMap();
			ictMap.setStatus(Tool.MODIFIED);
		}
		return ictMap;
	}

	public void setIctMap(SurgeryIctMap ictMap) {
		this.ictMap = ictMap;
	}

	public boolean isPcsCodeSelectValue() {
		return pcsCodeSelectValue;
	}

	public void setPcsCodeSelectValue(boolean pcsCodeSelectValue) {
		this.pcsCodeSelectValue = pcsCodeSelectValue;
	}

	public List<ICT19Dtl> getListSelectedIctMap() {
		if(listSelectedIctMap==null)
			listSelectedIctMap =  new ArrayList<>();
		return listSelectedIctMap;
	}

	public void setListSelectedIctMap(List<ICT19Dtl> listSelectedIctMap) {
		this.listSelectedIctMap = listSelectedIctMap;
	}

	public List<ICT19Dtl> getListICtMaps() {
		if(listICtMaps==null)
			listICtMaps=  new ArrayList<>();
		return listICtMaps;
	}

	public void setListICtMaps(List<ICT19Dtl> listICtMaps) {
		this.listICtMaps = listICtMaps;
	}

	public boolean isSurgeryModel() {
		return isSurgeryModel;
	}

	public void setSurgeryModel(boolean isSurgeryModel) {
		this.isSurgeryModel = isSurgeryModel;
	}

	public SurgeryIctModel getIctModel() {
		if(ictModel==null){
			ictModel=  new SurgeryIctModel();
			ictModel.setStatus(Tool.ADDED);
		}
		else {
			ictModel=  new SurgeryIctModel();
			ictModel.setStatus(Tool.MODIFIED);
		}
		return ictModel;
	}

	public void setIctModel(SurgeryIctModel ictModel) {
		this.ictModel = ictModel;
	}

	public boolean isPcsCodeinsertValue() {
		return pcsCodeinsertValue;
	}

	public void setPcsCodeinsertValue(boolean pcsCodeinsertValue) {
		this.pcsCodeinsertValue = pcsCodeinsertValue;
	}

	public boolean isPcsCodeModelValue() {
		return pcsCodeModelValue;
	}

	public void setPcsCodeModelValue(boolean pcsCodeModelValue) {
		this.pcsCodeModelValue = pcsCodeModelValue;
	}

	public List<ICT19Dtl> getListSurgeryModel() {
		if(listSurgeryModel==null)
			listSurgeryModel =  new ArrayList<>();
		return listSurgeryModel;
	}

	public void setListSurgeryModel(List<ICT19Dtl> listSurgeryModel) {
		this.listSurgeryModel = listSurgeryModel;
	}

	public ICT19Dtl getSelectionModelValue() {
		return selectionModelValue;
	}

	public void setSelectionModelValue(ICT19Dtl selectionModelValue) {
		this.selectionModelValue = selectionModelValue;
	}

	public List<ICT19Dtl> getLsitSurgeryModelTemp() {
		if(lsitSurgeryModelTemp==null)
			lsitSurgeryModelTemp =  new ArrayList<>();
		return lsitSurgeryModelTemp;
	}

	public void setLsitSurgeryModelTemp(List<ICT19Dtl> lsitSurgeryModelTemp) {
		this.lsitSurgeryModelTemp = lsitSurgeryModelTemp;
	}


	
	//EndRegion
}
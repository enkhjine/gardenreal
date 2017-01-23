package hospital.web.controller;

import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.Tool;
import hospital.businessentity.TreatmentTypeTreatment;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Employee;
import hospital.entity.ICT19;
import hospital.entity.Item;
import hospital.entity.Measurement;
import hospital.entity.PriceHistory;
import hospital.entity.SubOrganizationType;
import hospital.entity.SurgeryIctModel;
import hospital.entity.Treatment;
import hospital.entity.TreatmentDtl;
import hospital.entity.TreatmentIcdMap;
import hospital.entity.TreatmentIctModel;
import hospital.entity.TreatmentModel;
import hospital.entity.TreatmentPrice;
import hospital.entity.TreatmentType;
import hospital.entity.TreatmentTypeEmployeeMap;

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

import hospital.web.controller.ApplicationController;
import hospital.web.controller.UserSessionController;

@SessionScoped
@ManagedBean(name = "treatmentController")
public class TreatmentController {
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

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	// Lists
	private List<Treatment> treatments;
	private List<TreatmentType> treatmentTypes;
	private List<Item> items;
	private List<Measurement> measurements;
	private List<Item> tableItems;
	private List<TreatmentDtl> treatmentDtls;
	private List<TreatmentModel> treatmentModels;
	private List<PriceHistory> priceHistorys;
	private List<SubOrganizationType> subOrganizationTypee;
	private List<SubOrganizationType> subOrganizationTypes;
	private List<TreatmentPrice> treatmentPrices;
	private List<String> employeePkIds;
	private List<Employee> employees;
	private List<TreatmentTypeEmployeeMap> treatmentTypeEmployeeMaps;

	// Cursors
	private Treatment currentTreatment;
	private TreatmentType currentTreatmentType;
	private Item currentItem;
	private PriceHistory currentPriceHistory;
	private TreatmentModel currentTreatmentModel;
	private TreatmentIcdMap icdMap;
	private TreatmentIctModel ictModel;
	private List<ICT19Dtl> listTreatmentModel;
	private List<ICT19Dtl> listTreatmentModelTemp;

	// Filters
	private List<TreatmentType> allTreatmentTypes;
	private List<Item> treatmentItems;
	private Item cursorItem;
	private Item ccItem;
	private String priceHistoryItemName;
	private boolean isModel;
	private BigDecimal filterSotPkId;
	private boolean pcsCodeSelectValue;
	private boolean isTreatmentModel;
	private boolean pcsCodeinsertValue;
	private boolean pcsCodeModelValue ;
	private ICT19Dtl selectionModelValue;

	private String itemKey;
	private TreeNode treeTreatment;
	private TreeNode[] selectedNodes;
	private boolean filterCheck;
	private BigDecimal filterPkId;

	private boolean check1;
	private boolean check2;
	private List<TreatmentIcdMap> treatmentIcdMap;
	private List<ICT19Dtl> tableICT;
	private List<ICT19Dtl> listICT19Dtl;
	private List<ICT19Dtl> listIct19Temp;
	private List<ICT19Dtl> listIctMap;
	private ICT19Dtl ictChosenSelect;
	private ICT19Dtl ict19Dtl;
	private ICT19 ict19;
	private List<ICT19Dtl> listSelectedIcdMap;

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

	public List<Treatment> getTreatments() {
		if (treatments == null)
			treatments = new ArrayList<Treatment>();
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public boolean isCheck2() {

		return check2;
	}

	public void setCheck2(boolean check2) {
		this.check2 = check2;
	}

	public boolean isCheck1() {
		return check1;
	}

	public void setCheck1(boolean check1) {
		this.check1 = check1;
	}

	public List<TreatmentType> getTreatmentTypes() {
		if (treatmentTypes == null)
			treatmentTypes = new ArrayList<TreatmentType>();
		return treatmentTypes;
	}

	public void setTreatmentTypes(List<TreatmentType> treatmentTypes) {
		this.treatmentTypes = treatmentTypes;
	}

	public List<Item> getItems() {
		if (items == null)
			items = new ArrayList<Item>();
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Measurement> getMeasurements() {
		if (measurements == null)
			measurements = new ArrayList<Measurement>();
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<Item> getTableItems() {
		if (tableItems == null || tableItems.size() < 1) {
			tableItems = new ArrayList<Item>();
			tableItems.add(getNewItem());
		}
		return tableItems;

	}

	private Item getNewItem() {
		Item item = new Item();
		item.setStatus(Tool.LAST);
		return item;
	}

	public void setTableItems(List<Item> tableItems) {
		this.tableItems = tableItems;
	}

	public List<TreatmentDtl> getTreatmentDtls() {
		if (treatmentDtls == null)
			treatmentDtls = new ArrayList<TreatmentDtl>();
		return treatmentDtls;
	}

	public void setTreatmentDtls(List<TreatmentDtl> treatmentDtls) {
		this.treatmentDtls = treatmentDtls;
	}

	public Treatment getCurrentTreatment() {
		if (currentTreatment == null)
			newTreatment();
		return currentTreatment;
	}

	public void setCurrentTreatment(Treatment currentTreatment) {
		this.currentTreatment = currentTreatment;
	}

	public TreatmentType getCurrentTreatmentType() {
		if (currentTreatmentType == null)
			currentTreatmentType = new TreatmentType();
		return currentTreatmentType;
	}

	public void setCurrentTreatmentType(TreatmentType currentTreatmentType) {
		this.currentTreatmentType = currentTreatmentType;
	}

	public List<PriceHistory> getPriceHistorys() {
		return priceHistorys;
	}

	public void setPriceHistorys(List<PriceHistory> priceHistorys) {
		this.priceHistorys = priceHistorys;
	}

	public String getPriceHistoryItemName() {
		return priceHistoryItemName;
	}

	public void setPriceHistoryItemName(String priceHistoryItemName) {
		this.priceHistoryItemName = priceHistoryItemName;
	}

	public boolean isModel() {
		return isModel;
	}

	public void setModel(boolean isModel) {
		this.isModel = isModel;
	}

	public Item getCurrentItem() {
		if (currentItem == null)
			currentItem = new Item();
		return currentItem;
	}

	public void setCurrentItem(Item currentItem) {
		this.currentItem = currentItem;
	}

	public void newItem() {

		currentItem = new Item();
		currentItem.setStatus(Tool.ADDED);
	}

	public String modifiedItem(Item item) {
		currentItem = item;
		currentItem.setStatus(Tool.MODIFIED);
		return "treatment_register";
	}

	public void deleteItem(Item item) {

		currentItem = item;
		currentItem.setStatus(Tool.DELETE);
	}

	public String deleteItem() {
		try {
			logicTreatment.saveItem(currentItem,
					userSessionController.getLoggedInfo());
			currentItem = null;
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
		return "item_list";
	}

	public Item getCursorItem() {
		if (cursorItem == null)
			cursorItem = new Item();
		return cursorItem;
	}

	public void setCursorItem(Item cursorItem) {
		this.cursorItem = cursorItem;
	}

	public Item getCcItem() {
		if (ccItem == null)
			ccItem = new Item();
		return ccItem;
	}

	public void setCcItem(Item ccItem) {
		this.ccItem = ccItem;
	}

	public void saveItem() {

		if (currentItem.getName() == null || currentItem.getName() == "") {
			userSessionController.showMessage(50);

		}

		else if (currentItem.getEntityPrice() == null
				|| currentItem.getEntityPrice().compareTo(BigDecimal.ZERO) == 0) {
			userSessionController.showMessage(66);

		}

		else {
			try {
				currentItem.setStatus(Tool.ADDED);
				for (Item i : items) {
					if (i.getName().equals(currentItem.getName())) {
						currentItem.setStatus(Tool.MODIFIED);
						currentItem.setPkId(i.getPkId());
						currentItem.setMeasurementName(i.getMeasurementName());
					}

				}

				ccItem = currentItem;
				logicTreatment.saveItem(currentItem,
						userSessionController.getLoggedInfo());

				priceHistorys = null;
				priceHistoryItemName = null;
				selectedItem();
				RequestContext context = RequestContext.getCurrentInstance();

				items = logicTreatment.getItems(userSessionController
						.getLoggedInfo());
				currentItem = null;
				context.update("form:registerItem form:itemName");

				userSessionController.showMessage(99);
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());

			}
		}
	}

	public void selectedItem() {
		boolean isSelect = false;
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext
				.getCurrentInstance();
		for (Item item : tableItems)
			if (item.getPkId() != null && ccItem.getPkId() != null
					&& item.getPkId().compareTo(ccItem.getPkId()) == 0)

				isSelect = true;
		if (isSelect) {

			getUserSessionController().showMessage(51);
		} else {
			if (Tool.LAST.equals(cursorItem.getStatus())) {
				tableItems.add(getNewItem());
			}
			tableItems.set(tableItems.indexOf(cursorItem), ccItem);
		}

		context.update("form:itemMeasurement form:itemEntityPrice form:ggg form:itemName");
	}

	public void fillDataList() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("document.getElementById('form:itemName').setAttribute('list', 'il');");
	}

	public void fillItem() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("document.getElementById('form:itemName').setAttribute('list', 'il');");
		currentItem.setStatus(Tool.ADDED);
		int kk = 0;
		for (Item i : items) {
			if (i.getName().trim().equals(currentItem.getName().trim())) {
				kk++;
				currentItem = i;
				currentItem.setPkId(i.getPkId());
				currentItem.setMeasurementName(i.getMeasurementName());
				currentItem.setEntityPrice(i.getEntityPrice());
				priceHistoryItemName = i.getName();
				currentItem.setStatus(Tool.MODIFIED);

			}
		}
		if (kk == 0) {
			currentItem.setMeasurementPkId(measurements.get(0).getPkId());
			currentItem.setEntityPrice(new BigDecimal("0"));
		}

	}

	public void showPriceHistory() {

		BigDecimal pkId = BigDecimal.ZERO;
		if (currentItem.getName() == null || currentItem.getName() == "") {
			userSessionController.showWarningMessage("Бараа сонгоогүй байна");
		}

		else {
			for (Item i : items) {
				if (i.getName().equals(currentItem.getName())) {
					pkId = i.getPkId();
					setPriceHistoryItemName(i.getName());
				}
			}
			try {
				if (priceHistorys == null)
					priceHistorys = new ArrayList<PriceHistory>();
				priceHistorys = logicTreatment.getPriceHistorys(pkId,
						userSessionController.getLoggedInfo());
			} catch (Exception e) {
			}
		}
	}

	public String saveTreatment() {
		String ret = "";

		if (currentTreatment.getName() == null
				|| currentTreatment.getName() == "") {
			userSessionController.showMessage(53);
			return ret;
		}
		if (currentTreatment.getStatus().equals(Tool.MODIFIED)) {
			if (currentTreatment.getUsageDate() == null) {
				userSessionController.showMessage(82);
				return ret;
			}
			if (currentTreatment.getUsageDate().before(new Date())) {
				userSessionController.showMessage(83);
				return ret;
			}
		}

		try {
			logicTreatment.saveTreatment(userSessionController.getLoggedInfo(), currentTreatment, tableItems, isModel);
			if (tableICT.size() == 0){
				
			}
			else {
				getTableICT();
				logicTreatment.saveTreatmentIcdMap(getIcdMap(), currentTreatment.getPkId(), tableICT);
				listIctMap = logicTreatment.getIctDtlView(currentTreatment.getPkId());
				logicTreatment.saveTreatmentModel(getIctModel(), listIctMap, currentTreatment.getName(), isModel);
				setTableICT(null);
			}
			ret = "treatment_list";
			treeTreatment = null;
			treatmentTypes = null;
			treatments = null;
			getApplicationController().setListTreatment(null);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}

		return ret;
	}

	public void createTreeTreatment(List<TreatmentType> list,
			List<Treatment> list1) {
		TreatmentTypeTreatment ttt = new TreatmentTypeTreatment();
		treeTreatment = new DefaultTreeNode(ttt, null);

		for (TreatmentType treatmentType : list) {
			TreatmentTypeTreatment temp = new TreatmentTypeTreatment();
			temp.setName(treatmentType.getName());
			temp.setTreatmentDtlPkId(null);
			temp.setTreatmentPkId(null);
			temp.setTreatment(false);
			temp.setTreatmentTypePkId(treatmentType.getPkId());
			TreeNode node = new DefaultTreeNode(temp, treeTreatment);

			for (Treatment treatment : list1) {
				if (treatment.getTreatmentTypePkId().compareTo(
						treatmentType.getPkId()) != 0)
					continue;
				TreatmentTypeTreatment temp1 = new TreatmentTypeTreatment();
				temp1.setName(treatment.getName());
				temp1.setTreatmentDtlPkId(null);
				temp1.setTreatmentPkId(treatment.getPkId());
				temp1.setTreatmentTypePkId(null);
				temp1.setUsageDate(treatment.getUsageDate());
				temp1.setPrice(treatment.getPrice());
				temp1.setTreatment(true);
				temp1.setItemCountStr(treatment.getItemCount() + "");
				temp1.setActiveStatus(treatment.getActiveStatus());
				TreeNode n = new DefaultTreeNode(temp1, node);
			}
		}

	}

	public TreeNode filterTreeTable() {
		try {
			BigDecimal fff = BigDecimal.ZERO;
			if (isFilterCheck() == true) {
			} else
				fff = BigDecimal.ONE;
			List<TreatmentType> list = logicTreatment.getTreatmentTypeBySot(
					userSessionController.getLoggedInfo(), getFilterPkId(),
					BigDecimal.ZERO);
			List<Treatment> list1 = logicTreatment.getTreatmentByPrice(
					userSessionController.getLoggedInfo(), fff);
			createTreeTreatment(list, list1);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		return treeTreatment;
	}

	public TreeNode getTreeTreatment() {

		if (treeTreatment == null) {

			filterTreeTable();
		}

		return treeTreatment;

	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public void getItemListByTreatmentPkId(BigDecimal treatmentPkId) {
		if (treatmentPkId == null
				|| BigDecimal.ZERO.compareTo(treatmentPkId) == 0)
			return;
		try {
			treatmentItems = logicTreatment
					.getTreatmentDtlsByTreatmentPkId(treatmentPkId);
			if (treatmentItems.size() > 0) {
				org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext
						.getCurrentInstance();
				context.execute("PF('itemList').show();");
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public List<Item> getTreatmentItems() {
		if (treatmentItems == null)
			treatmentItems = new ArrayList<Item>();
		return treatmentItems;
	}

	public void setTreatmentItems(List<Item> treatmentItems) {
		this.treatmentItems = treatmentItems;
	}

	public void setTreeTreatment(TreeNode treeTreatment) {
		this.treeTreatment = treeTreatment;
	}

	public void removeItem() {
		tableItems.remove(cursorItem);
	}

	public void addTableItemList() {
		Item item = new Item();
		tableItems.add(item);
		cursorItem = item;
	}

	public void newTreatment() {
		currentTreatment = new Treatment();
		tableItems = null;
		tableICT = null;
		currentTreatment.setStatus(Tool.ADDED);
	}

	public String modifiedTreatment(BigDecimal treatmentPkId) {
		try {
			for (Treatment temp : logicTreatment
					.getTreatments(userSessionController.getLoggedInfo())) {
				if (temp.getPkId().compareTo(treatmentPkId) == 0) {
					currentTreatment = temp;
				}
			}
			currentTreatment.setStatus(Tool.MODIFIED);
			TreatmentIcdMap icdMap = new TreatmentIcdMap();
			icdMap.setStatus(Tool.MODIFIED);
			currentTreatment.setHasDtl(true);
			currentTreatment.setIcdMap(true);
			tableItems = logicTreatment
					.getTreatmentDtlsByTreatmentPkId(currentTreatment.getPkId());
			tableItems.add(getNewItem());
			listSelectedIcdMap = logicTreatment.getIctDtlView(currentTreatment.getPkId());
			for (int i=0; i<listSelectedIcdMap.size(); i++){
				ICT19Dtl dtl = new ICT19Dtl();
				dtl.setId(listSelectedIcdMap.get(i).getId());
				dtl.setNameEn(listSelectedIcdMap.get(i).getNameEn());
				dtl.setNameMn(listSelectedIcdMap.get(i).getNameMn());
				getTableICT().add(dtl);
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
		return "treatment_register";
	}

	public void deleteTreatment(BigDecimal treatmentPkId) {

		try {
			for (Treatment temp : logicTreatment.getTreatments(userSessionController.getLoggedInfo())) {
				if (temp.getPkId().compareTo(treatmentPkId) == 0) {
					currentTreatment = temp;
				}

			}
			if (infoLogic.isRelated(currentTreatment.getPkId(), 5)){
				currentTreatment.setStatus(Tool.DELETE);
			}
			else {
				currentTreatment.setStatus(Tool.UNCHANGED);
				userSessionController
						.showErrorMessage("Тухайн эмчилгээг ашигласан тул устгах боломжгүй!");
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}
	
	public void viewICT19(){
		try{
			listICT19Dtl = logicTreatment.getTreatmentICT();
		} catch(Exception e){
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}	
	}
	
	public void rowSelect(){
		try{
			ICT19Dtl dtl = new ICT19Dtl();
			dtl.setPkId(getIctChosenSelect().getPkId());
			dtl.setId(getIctChosenSelect().getId());
			dtl.setNameEn(getIctChosenSelect().getNameEn());
			dtl.setNameMn(getIctChosenSelect().getNameMn());
			if (listIct19Temp == null)
				getListIct19Temp();
			listIct19Temp.add(dtl);
		} catch(Exception e){
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void removeIndex(int index){
		listIct19Temp.remove(index);
	}
	
	public void removeIctIndex(int index){
		tableICT.remove(index);
	}
	
	public void insertChosen(){
		try{
			tableICT.remove(tableICT.size()-1);
			for (ICT19Dtl dtl : getListIct19Temp()) {
				ICT19Dtl  dtlCopy = new ICT19Dtl();
				dtlCopy  =  (ICT19Dtl) Tool.deepClone(dtl);
				tableICT.add(dtlCopy);
			}
			tableICT.add(getIct19Dtl());
			listIct19Temp.clear();
			RequestContext.getCurrentInstance().update("form:pcsPanel");
			RequestContext.getCurrentInstance().update("form:groupIctId");
			RequestContext.getCurrentInstance().execute("PF('treatmentIctTable').hide()");
		} catch(Exception e){
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public void updateTreatmentCost() {
		BigDecimal amount = BigDecimal.ZERO;

		RequestContext context = RequestContext.getCurrentInstance();
		int i = 0;
		for (Item item : getTableItems()) {
			BigDecimal amount1 = BigDecimal.ZERO;
			if (item.getEntityPrice() == null)
				item.setEntityPrice(BigDecimal.ZERO);
			if (item.getItemCount() == null)
				item.setItemCount(BigDecimal.ZERO);
			amount1 = amount1.add(item.getEntityPrice().multiply(
					item.getItemCount()));
			item.setAmount(amount1);
			amount = amount.add(amount1);
			String updateid = "form:tableadasd:" + i + ":cursorAmount";
			context.update(updateid);
			i++;
		}
		currentTreatment.setCost(amount);

		context.update("form:cost form:price form:tableadasd");
	}

	public void selectModel() {
		try {
			tableItems = logicTreatment
					.getTreatmentDtlsByTreatmentPkId(currentTreatmentModel
							.getTreatmentPkId());
			tableItems.add(getNewItem());
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('registerItem').hide();");
			context.update("form:message");
			userSessionController.showMessage(81);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}
	
	public void hidePcsCodeCheck(){
		pcsCodeModelValue = !pcsCodeinsertValue;
		pcsChoose();
	}
	
	public void hideModelCheck(){
		pcsCodeinsertValue = !pcsCodeModelValue;
		try{
			listTreatmentModel = logicTreatment.getTreatmentIctModelView();
		} catch(Exception e){
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		pcsChoose();
	}
	
	public void pcsChoose(){
		if (pcsCodeinsertValue){
			RequestContext.getCurrentInstance().update("form:modelPanel");
		}
		else if (pcsCodeModelValue){
			RequestContext.getCurrentInstance().update("form:modelPanel");
		}
	}
	
	public void rowModelSelect(){
		try {
			listTreatmentModelTemp = logicTreatment.getModelNameSearch(getSelectionModelValue().getTreatmentName());
			System.out.println("size " + listTreatmentModelTemp.size());
			for (int i=0; i<listTreatmentModelTemp.size()-1; i++){
				ICT19Dtl dtls = new ICT19Dtl();
				dtls.setPkId(listTreatmentModelTemp.get(i).getIct19().getPkId());
				dtls.setId(listTreatmentModelTemp.get(i).getIct19().getId());
				dtls.setNameEn(listTreatmentModelTemp.get(i).getIct19().getNameEn());
				dtls.setNameMn(listTreatmentModelTemp.get(i).getIct19().getNameMn());
				if (listIct19Temp == null)
					getListIct19Temp();
				listIct19Temp.add(dtls);
			}
		} catch (Exception e) {
			userSessionController.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	public PriceHistory getCurrentPriceHistory() {
		if (currentPriceHistory == null)
			currentPriceHistory = new PriceHistory();
		return currentPriceHistory;
	}

	public void setCurrentPriceHistory(PriceHistory currentPriceHistory) {
		this.currentPriceHistory = currentPriceHistory;
	}

	public TreatmentModel getCurrentTreatmentModel() {
		if (currentTreatmentModel == null)
			currentTreatmentModel = new TreatmentModel();
		return currentTreatmentModel;
	}

	public void setCurrentTreatmentModel(TreatmentModel currentTreatmentModel) {
		this.currentTreatmentModel = currentTreatmentModel;
	}

	public List<TreatmentModel> getTreatmentModels() {
		if (treatmentModels == null)
			treatmentModels = new ArrayList<TreatmentModel>();
		return treatmentModels;
	}

	public void setTreatmentModels(List<TreatmentModel> treatmentModels) {
		this.treatmentModels = treatmentModels;
	}

	public boolean isFilterCheck() {
		return filterCheck;
	}

	public void setFilterCheck(boolean filterCheck) {
		this.filterCheck = filterCheck;
	}

	public BigDecimal getFilterPkId() {
		if (filterPkId == null)
			filterPkId = new BigDecimal("0");
		return filterPkId;
	}

	public void setFilterPkId(BigDecimal filterPkId) {
		this.filterPkId = filterPkId;
	}

	public void newTreatmentType() {
		currentTreatmentType = new TreatmentType();
		currentTreatmentType.setStatus(Tool.ADDED);
		refreshEmployee();
	}

	public void refreshEmployee() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			if (currentTreatmentType.getSubOrganizationTypePkId() == null)
				setEmployees(logicTreatment
						.getEmployees(getSubOrganizationTypee().get(0)
								.getPkId()));
			else
				setEmployees(logicTreatment.getEmployees(currentTreatmentType
						.getSubOrganizationTypePkId()));

			context.update("form:laborant");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public String modifiedTreatmentType(TreatmentType treatmentType) {
		RequestContext context = RequestContext.getCurrentInstance();
		currentTreatmentType = (TreatmentType) Tool.deepClone(treatmentType);
		currentTreatmentType.setStatus(Tool.MODIFIED);
		try {
			setEmployees(logicTreatment.getEmployees(currentTreatmentType
					.getPkId()));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		getEmployeePkIds().clear();
		for (TreatmentTypeEmployeeMap m : currentTreatmentType.getMaps()) {
			String tmp = m.getPkId().toString();
			getEmployeePkIds().add(tmp);
		}
		
		context.update("form:treatmentTypeSection");
		context.update("form:laborant");

		return "treatment_register";
	}

	public void saveTreatmentType() {

		if (currentTreatmentType.getName() == null
				|| currentTreatmentType.getName() == "") {
			userSessionController.showMessage(52);

		} else {
			try {

				for (String tmp : getEmployeePkIds()) {
					TreatmentTypeEmployeeMap ttem = new TreatmentTypeEmployeeMap();
					ttem.setEmployeePkId(new BigDecimal(tmp));
					getTreatmentTypeEmployeeMaps().add(ttem);
				}
				logicTreatment.saveTreatmentType(currentTreatmentType,
						treatmentTypeEmployeeMaps);
				userSessionController.showMessage(99);

				currentTreatment.setTreatmentTypePkId(currentTreatmentType
						.getPkId());

				currentTreatmentType = null;
				treatmentTypeEmployeeMaps = null;
				employeePkIds.clear();
				userSessionController.showMessage(99);
				loadData();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:ttname");
				context.update("form:tList");
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());

			}
		}

	}

	public void deleteTreatmentType(TreatmentType treatmentType) {
		currentTreatmentType = (TreatmentType) Tool.deepClone(treatmentType);
		currentTreatmentType.setStatus(Tool.DELETE);
	}

	public String deleteTreatmentType() {
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext
				.getCurrentInstance();
		try {
			logicTreatment.saveTreatmentType(currentTreatmentType,
					currentTreatmentType.getMaps());
			userSessionController.showMessage(98);
			loadData();
			currentTreatmentType = null;

		} catch (Exception ex) {

			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.execute("PF('registerTreatmentType').hide();");
		context.update("form:tList");
		return "treatment_list";
	}

	public void updateTreatmentStatus(BigDecimal treatmentPkId,
			String activeStatus) {
		for (Treatment temp : getTreatments()) {
			if (temp.getPkId().compareTo(treatmentPkId) == 0
					&& !temp.getActiveStatus().equals(activeStatus)) {
				try {
					temp.setStatus(Tool.MODIFIED);
					temp.setActiveStatus(activeStatus);

					if (activeStatus.equals("Идэвхитэй")) {
						temp.setIsActive((byte) 0);
						temp.setActive(false);
					} else {
						temp.setIsActive((byte) 1);
						temp.setActive(true);
					}
					logicTreatment.updateTreatmentStatus(temp);
				} catch (Exception ex) {
					userSessionController.showErrorMessage(ex.getMessage());

				}
			}
		}
	}

	public Treatment getTreatmentPriceByName(BigDecimal treatmentPkId) {

		for (Treatment temp : getTreatments()) {
			if (temp.getPkId().equals(treatmentPkId)) {
				return temp;
			}
		}
		return null;

	}
	
	public List<SubOrganizationType> getSubOrganizationTypes() {
		if (subOrganizationTypes == null) {
			try {
				subOrganizationTypes = infoLogic.getSubOrganizationTypes1();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		}
		return subOrganizationTypes;
	}
	
	public void setSubOrganizationTypes(List<SubOrganizationType> subOrganizationTypes) {
		this.subOrganizationTypes = subOrganizationTypes;
	}

	public List<SubOrganizationType> getSubOrganizationTypee() {
		if (subOrganizationTypee == null) {
			try {
				subOrganizationTypee = infoLogic.getSubOrganizationTypes();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		}
		return subOrganizationTypee;
	}

	public void setSubOrganizationTypee(
			List<SubOrganizationType> subOrganizationTypee) {
		this.subOrganizationTypee = subOrganizationTypee;
	}

	public List<TreatmentType> getAllTreatmentTypes() {
		if (allTreatmentTypes == null) {
			allTreatmentTypes = new ArrayList<TreatmentType>();
		}
		return allTreatmentTypes;
	}

	public void setAllTreatmentTypes(List<TreatmentType> allTreatmentTypes) {
		this.allTreatmentTypes = allTreatmentTypes;
	}

	public void hideChooseModel() {
		check2 = !check1;
		asdasdasd();
	}

	public void hideChooseItem() {
		check1 = !check2;
		asdasdasd();
	}

	private void asdasdasd() {

		if (check1) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("document.getElementById('form:itemName').setAttribute('list', 'il');");
			context.update("form:bigPanel form:forModel form:oneByOne");
		}

		if (check2) {
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:bigPanel form:forModel form:oneByOne");
		}
	}

	public void loadData() {

		try {
			setTreatmentModels(logicTreatment.getTreatmentModels());
			setMeasurements(logicTreatment.getMeasurements());
			setItems(logicTreatment.getItems(userSessionController
					.getLoggedInfo()));
			setAllTreatmentTypes(logicTreatment
					.getAllTreatmentType(getUserSessionController()
							.getLoggedInfo()));
			getCurrentItem();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:ttname");
	}

	public List<TreatmentPrice> getTreatmentPrices() {
		if (treatmentPrices == null)
			treatmentPrices = new ArrayList<TreatmentPrice>();
		return treatmentPrices;
	}

	public void setTreatmentPrices(List<TreatmentPrice> treatmentPrices) {
		this.treatmentPrices = treatmentPrices;
	}

	public void getTreatmentPriceHistoryByPkId(BigDecimal treatmentPkId) {

		try {
			setTreatmentPrices(logicTreatment.getTreatmentPrices(treatmentPkId));
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:treatmentPrices");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
	}

	public List<String> getEmployeePkIds() {
		return employeePkIds;
	}

	public void setEmployeePkIds(List<String> employeePkIds) {
		this.employeePkIds = employeePkIds;
	}

	public List<Employee> getEmployees() {
		if (employees == null)
			employees = new ArrayList<Employee>();
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public BigDecimal getFilterSotPkId() {
		return filterSotPkId;
	}

	public void setFilterSotPkId(BigDecimal filterSotPkId) {
		this.filterSotPkId = filterSotPkId;
	}

	public List<TreatmentTypeEmployeeMap> getTreatmentTypeEmployeeMaps() {
		if (treatmentTypeEmployeeMaps == null)
			treatmentTypeEmployeeMaps = new ArrayList<TreatmentTypeEmployeeMap>();
		return treatmentTypeEmployeeMaps;
	}

	public void setTreatmentTypeEmployeeMaps(
			List<TreatmentTypeEmployeeMap> treatmentTypeEmployeeMaps) {
		this.treatmentTypeEmployeeMaps = treatmentTypeEmployeeMaps;
	}

	public List<TreatmentIcdMap> getTreatmentIcdMap() {
		if (treatmentIcdMap == null)
			treatmentIcdMap = new ArrayList<>();
		return treatmentIcdMap;
	}

	public void setTreatmentIcdMap(List<TreatmentIcdMap> treatmentIcdMap) {
		this.treatmentIcdMap = treatmentIcdMap;
	}

	public List<ICT19Dtl> getTableICT() {
		if (tableICT == null){
			tableICT = new ArrayList<>();
			tableICT.add(getIct19Dtl());
		}
		return tableICT;
	}

	public void setTableICT(List<ICT19Dtl> tableICT) {
		this.tableICT = tableICT;
	}

	public List<ICT19Dtl> getListICT19Dtl() {
		if (listICT19Dtl == null)
			listICT19Dtl = new ArrayList<>();
		return listICT19Dtl;
	}

	public void setListICT19Dtl(List<ICT19Dtl> listICT19Dtl) {
		this.listICT19Dtl = listICT19Dtl;
	}

	public List<ICT19Dtl> getListIct19Temp() {
		if (listIct19Temp == null)
			listIct19Temp = new ArrayList<>();
		return listIct19Temp;
	}

	public void setListIct19Temp(List<ICT19Dtl> listIct19Temp) {
		this.listIct19Temp = listIct19Temp;
	}

	public ICT19Dtl getIctChosenSelect() {
		return ictChosenSelect;
	}

	public void setIctChosenSelect(ICT19Dtl ictChosenSelect) {
		this.ictChosenSelect = ictChosenSelect;
	}

	public ICT19Dtl getIct19Dtl() {
		if (ict19Dtl == null){
			ict19Dtl = new ICT19Dtl();
			ict19Dtl.setStatus(Tool.LAST);
		}
		return ict19Dtl;
	}

	public void setIct19Dtl(ICT19Dtl ict19Dtl) {
		this.ict19Dtl = ict19Dtl;
	}

	public ICT19 getIct19() {
		if (ict19 == null){
			ict19 = new ICT19();
			ict19.setStatus(Tool.LAST);
		}
		return ict19;
	}

	public void setIct19(ICT19 ict19) {
		this.ict19 = ict19;
	}

	public TreatmentIcdMap getIcdMap() {
		if (icdMap == null){
			icdMap = new TreatmentIcdMap();
			icdMap.setStatus(Tool.ADDED);
		} 
		else if (icdMap == null){
			icdMap = new TreatmentIcdMap();
			icdMap.setStatus(Tool.MODIFIED);
		}
		return icdMap;
	}

	public void setIcdMap(TreatmentIcdMap icdMap) {
		this.icdMap = icdMap;
	}

	public TreatmentIctModel getIctModel() {
		if(ictModel==null){
			ictModel=  new TreatmentIctModel();
			ictModel.setStatus(Tool.ADDED);
		}
		else if (ictModel==null) {
			ictModel=  new TreatmentIctModel();
			ictModel.setStatus(Tool.MODIFIED);
		}
		return ictModel;
	}

	public void setIctModel(TreatmentIctModel ictModel) {
		this.ictModel = ictModel;
	}

	public List<ICT19Dtl> getListIctMap(){
		if (listIctMap == null)
			listIctMap = new ArrayList<>();
		return listIctMap;
	}

	public void setListIctMap(List<ICT19Dtl> listIctMap) {
		this.listIctMap = listIctMap;
	}

	public List<ICT19Dtl> getListTreatmentModel() {
		if (listTreatmentModel == null)
			listTreatmentModel = new ArrayList<>();
		return listTreatmentModel;
	}

	public void setListTreatmentModel(List<ICT19Dtl> listTreatmentModel) {
		this.listTreatmentModel = listTreatmentModel;
	}

	public List<ICT19Dtl> getListTreatmentModelTemp() {
		if (listTreatmentModelTemp == null)
			listTreatmentModelTemp = new ArrayList<>();
		return listTreatmentModelTemp;
	}

	public void setListTreatmentModelTemp(List<ICT19Dtl> listTreatmentModelTemp) {
		this.listTreatmentModelTemp = listTreatmentModelTemp;
	}

	public boolean isPcsCodeSelectValue() {
		return pcsCodeSelectValue;
	}

	public void setPcsCodeSelectValue(boolean pcsCodeSelectValue) {
		this.pcsCodeSelectValue = pcsCodeSelectValue;
	}

	public boolean isTreatmentModel() {
		return isTreatmentModel;
	}

	public void setTreatmentModel(boolean isTreatmentModel) {
		this.isTreatmentModel = isTreatmentModel;
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

	public ICT19Dtl getSelectionModelValue() {
		return selectionModelValue;
	}

	public void setSelectionModelValue(ICT19Dtl selectionModelValue) {
		this.selectionModelValue = selectionModelValue;
	}

	public List<ICT19Dtl> getListSelectedIcdMap() {
		if (listSelectedIcdMap == null)
			listSelectedIcdMap = new ArrayList<>();
		return listSelectedIcdMap;
	}

	public void setListSelectedIcdMap(List<ICT19Dtl> listSelectedIcdMap) {
		this.listSelectedIcdMap = listSelectedIcdMap;
	}

}

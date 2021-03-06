package hospital.web.controller;

import hospital.businesslogic.interfaces.IInfoLogicLocal;
import logic.data.Tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import garden.businessentity.Tool;
import garden.entity.Company;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.FoodOrts;
import garden.entity.Orts;
import garden.entity.OrtsCategory;
import garden.entity.OrtsSize;

@SessionScoped
@ManagedBean(name = "infoController")
public class InfoController implements Serializable {
	
	private static final long serialVersionUID = 71056782762570859L;
	
	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	private Company currentCompany;
	private OrtsCategory currentOrtsCategory;
	private List<OrtsCategory> listOrtsCategory;
	private OrtsSize currentOrtsSize;
	private List<OrtsSize> listOrtsSize;
	private Orts currentOrts;
	private UploadedFile ortsFile;
	
	private List<FoodOrts> listFoodOrts;
	private List<FoodOrts> listFoodOrtsTmp;
	private List<Orts> listOrts;
	private List<Orts> ortsListWithJoins;
	private Orts selOrts;
	private Food currentFood;
	private List<FoodCategory> listFoodCategory;
	private List<Food> listFoodTmp;

	private String currentPageInfo;
	
	
	public InfoController() {

	}
	
	
	public String getCurrentPageInfo() {
		return currentPageInfo;
	}
	public void setCurrentPageInfo(String currentPageInfo) {
		this.currentPageInfo = currentPageInfo;
	}
	public String saveCurrentOrts(){
		String ret = "";
		
		if(!Tool.DELETE.equals(getCurrentOrts().getStatus())){
			if(getCurrentOrts().getName().isEmpty()) {
				getUserSessionController().showWarningMessage("Орцны нэр оруулаагүй байна.");
				return "";
			}
			if(getCurrentOrts().getSize() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getSize()) == 0) {
				getUserSessionController().showWarningMessage("Нэгж сонгоогүй байна.");
				return "";
			}
			if(getCurrentOrts().getCategoryPkId() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getCategoryPkId()) == 0) {
				getUserSessionController().showWarningMessage("Төрөл сонгоогүй байна.");
				return "";
			}
			if(getCurrentOrts().getUurag() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getUurag()) == 0) {
				getUserSessionController().showWarningMessage("Уураг оруулаагүй байна.");
				return "";
			}
			if(getCurrentOrts().getUuhtos() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getUuhtos()) == 0) {
				getUserSessionController().showWarningMessage("Өөх тос оруулаагүй байна.");
				return "";
			}
			if(getCurrentOrts().getNuursus() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getNuursus()) == 0) {
				getUserSessionController().showWarningMessage("Нүүрс ус оруулаагүй байна.");
				return "";
			}
			if(getCurrentOrts().getIlchleg() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getIlchleg()) == 0) {
				getUserSessionController().showWarningMessage("Илчлэг оруулаагүй байна.");
				return "";
			}
			if(getCurrentOrts().getShinjilgee().isEmpty()){
				getUserSessionController().showWarningMessage("Шинэжилгээний бичиг оруулаагүй байна.");
				return "";
			}
		}
		if(getCurrentOrts().getSize() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getSize()) == 0) {
			getUserSessionController().showWarningMessage("Нэгж сонгоогүй байна.");
			return "";
		}
		if(getCurrentOrts().getCategoryPkId() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getCategoryPkId()) == 0) {
			getUserSessionController().showWarningMessage("Төрөл сонгоогүй байна.");
			return "";
		}
		if(getCurrentOrts().getUurag() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getUurag()) == 0) {
			getUserSessionController().showWarningMessage("Уураг оруулаагүй байна.");
			return "";
		}
		if(getCurrentOrts().getUuhtos() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getUuhtos()) == 0) {
			getUserSessionController().showWarningMessage("Өөх тос оруулаагүй байна.");
			return "";
		}
		if(getCurrentOrts().getNuursus() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getNuursus()) == 0) {
			getUserSessionController().showWarningMessage("Нүүрс ус оруулаагүй байна.");
			return "";
		}
		if(getCurrentOrts().getIlchleg() == null || BigDecimal.ZERO.compareTo(getCurrentOrts().getIlchleg()) == 0) {
			getUserSessionController().showWarningMessage("Илчлэг оруулаагүй байна.");
			return "";
		}
		if(getCurrentOrts().getShinjilgee().isEmpty()){
			getUserSessionController().showWarningMessage("Шинэжилгээний бичиг оруулаагүй байна.");
			return "";
		}
		
		try{
			infoLogic.saveOrts(getCurrentOrts(), getUserSessionController().getLoggedInfo());
			setCurrentOrts(null);
			listOrts = infoLogic.getOrtsList();
			ortsListWithJoins  = infoLogic.getOrtsList();
			ret = "buteegdehuun";
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		
		return ret;
	}
	
	public void saveCurrentOrtsCategory(){
		try{
			System.out.println("saveCurrentOrtsCategory");
			if(getCurrentOrtsCategory().getName().isEmpty()) {
				getUserSessionController().showWarningMessage("Орцны нэр оруулаагүй байна.");
				return;
			}
			String status = getCurrentOrtsCategory().getStatus();
			infoLogic.saveOrtsCategory(getCurrentOrtsCategory(), getUserSessionController().getLoggedInfo());
			listOrtsCategory = null;
			currentOrtsCategory = null;
			if(Tool.ADDED.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай хадгаллаа.");
			if(Tool.MODIFIED.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай заслаа.");
			if(Tool.DELETE.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай устаглаа.");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void setCurrentOrtsCategoryByPkId(BigDecimal ortsCategoryPkId, String status){
		System.out.println(ortsCategoryPkId + " -> " + status);
		try{
			currentOrtsCategory = infoLogic.getOrtsCategory(ortsCategoryPkId);
			currentOrtsCategory.setStatus(status);
			if(Tool.DELETE.equals(status)){
				saveCurrentOrtsCategory();
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	private String buildAttachmentFileName(String pkId, String fileName) {
		String path = applicationController.getFile();
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path + pkId + "." + FilenameUtils.getExtension(fileName);
	}
	
	public void ortsFileUpload(FileUploadEvent event) {
		try {
			String fileName = buildAttachmentFileName(Tools.newPkId().toString(), event.getFile().getFileName());
			
			OutputStream out;
			InputStream in = event.getFile().getInputstream();

			out = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			getCurrentOrts().setShinjilgee(fileName);

			in.close();
			out.flush();
			out.close();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}
	
	public void foodFileUpload(FileUploadEvent event) {
		try {
			String fileName = buildAttachmentFileName(Tools.newPkId().toString(), event.getFile().getFileName());
			
			OutputStream out;
			InputStream in = event.getFile().getInputstream();

			out = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			
			getCurrentFood().setImage(fileName);

			in.close();
			out.flush();
			out.close();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}
	
	public String saveCurrentFood(){
		if(getCurrentFood().getName().isEmpty()){
			getUserSessionController().showWarningMessage("Хоолны нэр оруулаагүй байна !!!");
			return "";
		}
		if(getListFoodOrts().size() < 1){
			getUserSessionController().showWarningMessage("Хоолны орц оруулаагүй байна !!!");
			return "";
		}
		try{
			infoLogic.saveFood(getCurrentFood(), getListFoodOrts(), getUserSessionController().getLoggedInfo());
			getUserSessionController().showSuccessMessage("Амжилттай хадгаллаа");
			currentFood = null;
			listFoodOrts = null;
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "foodlist";
	}
	
	public void saveCurrentOrtsSize(){
		try{
			System.out.println("saveCurrentOrtsSize");
			if(getCurrentOrtsSize().getName().isEmpty()) {
				getUserSessionController().showWarningMessage("Нэгж нэр оруулаагүй байна.");
				return;
			}
			if(BigDecimal.ZERO.compareTo(getCurrentOrtsSize().getSize()) == 0){
				getUserSessionController().showWarningMessage("Хэмжээс буруу оруулсан байна.");
				return;
			}
			if(Tool.MODIFIED.equals(getCurrentOrtsSize().getStatus())){
				if(getCurrentOrtsSize().getParentPkId().compareTo(getCurrentOrtsSize().getPkId()) == 0){
					getUserSessionController().showWarningMessage("Нэгж дээр өөрийгөө сонгож болохгүй.");
					return;
				}
				
				for (OrtsSize ortsSize : getListOrtsSize()) {
					if(ortsSize.getPkId().compareTo(getCurrentOrtsSize().getParentPkId()) == 0){
						if(ortsSize.getParentPkIds().contains(getCurrentOrtsSize().getPkId())){
							getUserSessionController().showWarningMessage("Нэгж дээр өөрт хариалалтай нэгжийг сонгож болохгүй.");
							return;
						}
					}
				}
			}
			String status = getCurrentOrtsCategory().getStatus();
			infoLogic.saveOrtsSize(getCurrentOrtsSize(), getUserSessionController().getLoggedInfo());
			listOrtsSize = null;
			currentOrtsSize = null;
			if(Tool.ADDED.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай хадгаллаа.");
			if(Tool.MODIFIED.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай заслаа.");
			if(Tool.DELETE.equals(status)) getUserSessionController().showSuccessMessage("Амжилттай устаглаа.");
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void setCurrentOrtsSizeByPkId(BigDecimal ortsSizePkId, String status){
		System.out.println(ortsSizePkId + " -> " + status);
		try{
			currentOrtsSize = infoLogic.getOrtsSize(ortsSizePkId);
			currentOrtsSize.setStatus(status);
			if(Tool.DELETE.equals(status)){
				saveCurrentOrtsSize();
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void newCompany(){
		currentCompany = new Company();
		currentCompany.setPkId(BigDecimal.ZERO);
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:companyDialog");
		context.execute("PF('companyDialog').show();");
	}

	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public Company getCurrentCompany() {
		if(currentCompany == null) {
			currentCompany = new Company();
			currentCompany.setPkId(BigDecimal.ZERO);
		}
		return currentCompany;
	}

	public void setCurrentCompany(Company currentCompany) {
		this.currentCompany = currentCompany;
	}
	
	public OrtsCategory getCurrentOrtsCategory() {
		if(currentOrtsCategory == null) currentOrtsCategory = new OrtsCategory();
		return currentOrtsCategory;
	}
	
	public void setCurrentOrtsCategory(OrtsCategory currentOrtsCategory) {
		this.currentOrtsCategory = currentOrtsCategory;
	}

	public List<OrtsCategory> getListOrtsCategory() {
		if(listOrtsCategory == null) {
			try{
				listOrtsCategory = infoLogic.getListOrtsCategory();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listOrtsCategory;
	}

	public void setListOrtsCategory(List<OrtsCategory> listOrtsCategory) {
		this.listOrtsCategory = listOrtsCategory;
	}
	
	public OrtsSize getCurrentOrtsSize() {
		if(currentOrtsSize == null) currentOrtsSize = new OrtsSize();
		return currentOrtsSize;
	}
	
	public void setCurrentOrtsSize(OrtsSize currentOrtsSize) {
		this.currentOrtsSize = currentOrtsSize;
	}
	
	public List<OrtsSize> getListOrtsSize() {
		if(listOrtsSize == null) {
			try{
				listOrtsSize = infoLogic.getListOrtsSize();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listOrtsSize;
	}
	
	public void setListOrtsSize(List<OrtsSize> listOrtsSize) {
		this.listOrtsSize = listOrtsSize;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Orts getCurrentOrts() {
		if(currentOrts == null) {
			currentOrts = new Orts();
		}
		return currentOrts;
	}

	public void setCurrentOrts(Orts currentOrts) {
		this.currentOrts = currentOrts;
	}

	public UploadedFile getOrtsFile() {
		return ortsFile;
	}

	public void setOrtsFile(UploadedFile ortsFile) {
		this.ortsFile = ortsFile;
	}
	
	public List<FoodOrts> getListFoodOrts() {
		if(listFoodOrts == null) listFoodOrts = new ArrayList<>();
		return listFoodOrts;
	}
	
	public void setListFoodOrts(List<FoodOrts> listFoodOrts) {
		this.listFoodOrts = listFoodOrts;
	}
	
	public List<FoodOrts> getListFoodOrtsTmp() {
		listFoodOrtsTmp = new ArrayList<>();
		for (FoodOrts foodOrts : getListFoodOrts()) {
			listFoodOrtsTmp.add(foodOrts);
		}
		FoodOrts foodOrts = new FoodOrts();
		listFoodOrtsTmp.add(foodOrts);
		return listFoodOrtsTmp;
	}
	
	public void setListFoodOrtsTmp(List<FoodOrts> listFoodOrtsTmp) {
		this.listFoodOrtsTmp = listFoodOrtsTmp;
	}
	
	public List<Orts> getListOrts() {
		if(listOrts == null) {
			try{
				listOrts = infoLogic.getListOrts();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listOrts;
	}
	
	public void setListOrts(List<Orts> listOrts) {
		this.listOrts = listOrts;
	}
	
	public Orts getSelOrts() {
		return selOrts;
	}
	
	public void setSelOrts(Orts selOrts) {
		this.selOrts = selOrts;
	}
	
	public void chosenOrts(){
		if(selOrts != null) {
			for (FoodOrts foodOrts : getListFoodOrts()) {
				if(selOrts.getPkId().compareTo(foodOrts.getOrtsPkId()) == 0) {
					getUserSessionController().showWarningMessage("Сонгосон орц байна.");	
					getUserSessionController().showWarningMessage("Сонгосон орц байна.");					
					return;
				}
			}
			FoodOrts foodOrts = new FoodOrts();
			foodOrts.setStatus(Tool.ADDED);
			foodOrts.setOrtsPkId(selOrts.getPkId());
			foodOrts.setName(selOrts.getName());
			foodOrts.setIlchleg(selOrts.getIlchleg());
			foodOrts.setUurag(selOrts.getUurag());
			foodOrts.setUuhtos(selOrts.getUuhtos());
			foodOrts.setNuursus(foodOrts.getNuursus());
			foodOrts.setSize(BigDecimal.ZERO);
			getListFoodOrts().add(foodOrts);
		}
	}

	public Food getCurrentFood() {
		if(currentFood == null) currentFood = new Food();
		return currentFood;
	}

	public void setCurrentFood(Food currentFood) {
		this.currentFood = currentFood;
	}

	public List<FoodCategory> getListFoodCategory() {
		if(listFoodCategory == null) {
			try{
				listFoodCategory = infoLogic.getListFoodCategory();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		if(listFoodCategory.size() > 0) listFoodCategory.get(0).setStatus("active");
		return listFoodCategory;
	}
	
	public void setListFoodCategory(List<FoodCategory> listFoodCategory) {
		this.listFoodCategory = listFoodCategory;
	}
	
	public List<Food> getListFoodTmp() {
		listFoodTmp = new ArrayList<>();
		for (FoodCategory foodCategory : listFoodCategory) {
			if("active".equals(foodCategory.getStatus())){
				try{
					listFoodTmp = infoLogic.getFoodTmp(foodCategory.getPkId());
				}catch (Exception ex) {
					getUserSessionController().showErrorMessage(ex.getMessage());
				}
			}
		}
		return listFoodTmp;
	}
	
	public void setListFoodTmp(List<Food> listFoodTmp) {
		this.listFoodTmp = listFoodTmp;
	}













































	
	public void setFoodCategoryByPkId(BigDecimal foodCategoryPkId){
		for (FoodCategory foodCategory : listFoodCategory){
			foodCategory.setStatus(foodCategoryPkId.compareTo(foodCategory.getPkId()) == 0 ? "active" : "");
		}	
	}
	
	public void setListFoodOrtsByFoodPkId(BigDecimal foodPkId) {
		if(foodPkId != null){
			try{
				listFoodOrts = infoLogic.getFoodOrtsByFoodPkId(foodPkId);
				currentFood = infoLogic.getFood(foodPkId);
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}
	
	public String editListFoodOrtsByFoodPkId(BigDecimal foodPkId){
		setListFoodOrtsByFoodPkId(foodPkId);
		getCurrentFood().setStatus(Tool.MODIFIED);
		return "food";
	}
	
	public String editOrtsByPkId(BigDecimal pkId){
		setOrtsByPkId(pkId);
		getCurrentOrts().setStatus(Tool.MODIFIED);
		return "orts";
	}
	
	public String newOrts(){
		setCurrentOrts(null);
		return "orts";
	}
	public String deleteListFoodOrtsByFoodPkId(BigDecimal foodPkId){
		setListFoodOrtsByFoodPkId(foodPkId);
		getCurrentFood().setStatus(Tool.DELETE);
		saveCurrentFood();
		getUserSessionController().showWarningMessage("Амжилттай устаглаа.");
		return "foodlist";
	}
	
	public String deleteOrtsByPkId(BigDecimal pkId){
		setOrtsByPkId(pkId);
		getCurrentOrts().setStatus(Tool.DELETE);
		saveCurrentOrts();
		getUserSessionController().showWarningMessage("Амжилттай устаглаа.");
		return "buteegdehuun";
	}
	
	
	public void setOrtsByPkId(BigDecimal pkId){
		try {
			this.currentOrts = infoLogic.getOrtsByPkId(pkId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Orts> getOrtsListWithJoins() {
		if(ortsListWithJoins == null){
			try {
				ortsListWithJoins = infoLogic.getOrtsList();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ortsListWithJoins;
	}

	public void setOrtsListWithJoins(List<Orts> ortsListWithJoins) {
		this.ortsListWithJoins = ortsListWithJoins;
	}
	
	
}
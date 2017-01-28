package hospital.web.controller;

import hospital.businesslogic.interfaces.IInfoLogicLocal;
import mondrian.rolap.BitKey.Big;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import garden.businessentity.Tool;
import garden.entity.Company;
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

	public InfoController() {

	}
	
	public String saveCurrentOrts(){
		String ret = "";
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
//		if(ortsFile == null){
//			getUserSessionController().showWarningMessage("Шинэжилгээний бичиг оруулаагүй байна.");
//			return "";
//		}
		
		try{
			infoLogic.saveOrts(getCurrentOrts(), getUserSessionController().getLoggedInfo());
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

}

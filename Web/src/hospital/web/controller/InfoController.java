package hospital.web.controller;

import hospital.businesslogic.interfaces.IInfoLogicLocal;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import garden.entity.Company;
import garden.entity.OrtsCategory;

@SessionScoped
@ManagedBean(name = "infoController")
public class InfoController {

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	private Company currentCompany;
	private OrtsCategory currentOrtsCategory;
	private List<OrtsCategory> listOrtsCategory;

	public InfoController() {

	}
	
	public void saveCurrentOrtsCategory(){
		try{
			System.out.println("saveCurrentOrtsCategory");
			if(getCurrentOrtsCategory().getName().isEmpty()) {
				getUserSessionController().showWarningMessage("Орцны нэр оруулаагүй байна.");
				return;
			}
			infoLogic.saveOrtsCategory(getCurrentOrtsCategory(), getUserSessionController().getLoggedInfo());
			listOrtsCategory = null;
			getUserSessionController().showSuccessMessage("Амжилттай хадгаллаа.");
			RequestContext.getCurrentInstance().update("@(.listOrtsCategory)");
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
		if(currentOrtsCategory == null) {
			currentOrtsCategory = new OrtsCategory();
			currentOrtsCategory.setPkId(BigDecimal.ZERO);
		}
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

}

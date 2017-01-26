package hospital.web.controller;

import hospital.businesslogic.interfaces.IInfoLogicLocal;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import com.lowagie.toolbox.plugins.Concat;

import garden.entity.Company;

@SessionScoped
@ManagedBean(name = "infoController")
public class InfoController {

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	//Company
	private Company currentCompany;

	public InfoController() {

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

}

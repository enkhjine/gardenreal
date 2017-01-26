package hospital.web.controller;

import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IUserLogicLocal;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "userController")
public class UserSessionController implements Serializable {
	
	private static final long serialVersionUID = 71056782762570859L;
	public static final String AUTH_KEY = "none";
	public static final String urlList = "";
	
	@EJB(beanName = "UserLogic")
	IUserLogicLocal userLogic;
	
	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;
	
	//Propertys begin
	private String showMessage;
	private String errorMessage;
	private String warningMessage;
	private String title;
	//Propertys end
	
	private Integer currentYear;
	private Integer currentMonth;
	private Integer currentDay;
	private BigDecimal currentEmployeePkId;
	
	@ManagedProperty(value="#{applicationController}")
	private ApplicationController applicationController;
	
	@PostConstruct
	public void postConstruct(){
		initData();
	}
	
	//InitData
	public void initData(){
		try{

//			List<Menu> list = infoLogic.getMenus();
//			String str = "Hospital/";
//			for (Menu menu : list) {
//				str = str + "::" + menu.getUrl();
//			}

//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(urlList, str);
		}catch(Exception ex){
			showErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	public void daa(){
		System.out.println("Daa");
		try{
			
		}catch(Exception ex){
			
		}
	}
	
	public void showErrorMessage(String message){
		setWarningMessage(message);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("showMessage();");
	}
	
	public void showSuccessMessage(String message){
		setShowMessage(message);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("showMessage();");
	}
	
	public void showWarningMessage(String message){
		setWarningMessage(message);
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("showMessage();");
	}
	

	
	public String login(){
		String ret = "";
		try{
			
//			List<Menu> list = infoLogic.getMenusFilter(loggedInfo);
//			String str = "Hospital/";
//			for (Menu menu : list) {
//				str = str + "::" + menu.getUrl();
//			}

			
//			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(urlList, str);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(AUTH_KEY, "login");
			
		}catch(Exception ex){
			showErrorMessage(ex.getMessage());
		}		
		
		return ret;
	}
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		System.out.println("LOGOUT");
		return "login";
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public String getShowMessage() {
		String ret = showMessage;
		showMessage = "";
		return ret;
	}

	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}

	public String getErrorMessage() {
		String ret = errorMessage;
		errorMessage = "";
		return ret;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getWarningMessage() {
		String ret = warningMessage;
		warningMessage = "";
		return ret;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}

	public Integer getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(Integer currentMonth) {
		this.currentMonth = currentMonth;
	}

	public Integer getCurrentDay() {
		return currentDay;
	}

	public void setCurrentDay(Integer currentDay) {
		this.currentDay = currentDay;
	}

	public BigDecimal getCurrentEmployeePkId() {
		return currentEmployeePkId;
	}

	public void setCurrentEmployeePkId(BigDecimal currentEmployeePkId) {
		this.currentEmployeePkId = currentEmployeePkId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

package hospital.web.controller;

import hospital.businesslogic.interfaces.IDashboardLogicLocal;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@SessionScoped
@ManagedBean(name = "languageController")
public class LanguageController implements Serializable {

	private String localeCode;
	private Locale localeSelected;
	
	@EJB(beanName = "DashboardLogic")
	IDashboardLogicLocal dashboardLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@PostConstruct
	public void init() {
		localeSelected = (Locale)countries.get("Монгол");
	}
	
	public LanguageController() {

	}
	
	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		countries.put("English", Locale.ENGLISH); //label, value
		countries.put("Монгол", new Locale("mn", "MONGOLIA", "mn"));
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	
	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public void countryLocaleCodeChanged(String newLocaleValue){
		System.out.println("VALUE =========> " + newLocaleValue);
		//loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {
        	if(entry.getValue().toString().equals(newLocaleValue)){
        		FacesContext.getCurrentInstance()
        			.getViewRoot().setLocale((Locale)entry.getValue());
        		localeSelected = (Locale)entry.getValue();
        	}
        }

	}
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public Locale getLocaleSelected() {
		return localeSelected;
	}

	public void setLocaleSelected(Locale localeSelected) {
		this.localeSelected = localeSelected;
	}
}
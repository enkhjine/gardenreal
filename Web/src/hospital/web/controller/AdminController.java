package hospital.web.controller;

import hospital.businesslogic.interfaces.IAdminLogicLocal;
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
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import garden.entity.Company;
import garden.entity.Garden;
import garden.entity.GardenUser;

@SessionScoped
@ManagedBean(name = "adminController")
public class AdminController implements Serializable {

	private static final long serialVersionUID = 71056782762570859L;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "AdminLogic")
	IAdminLogicLocal adminLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	private List<Company> companies;
	private List<Garden> gardens;
	private List<GardenUser> gardenUsers;

	private Company cursorCompany;
	private Garden cursorGarden;
	private GardenUser cursorGardenUser;

	public AdminController() {

	}

	public void getCompanyList() {
		try {
			setCompanies(adminLogic.getListCompany());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void getGardenList() {
		try{
			setGardens(adminLogic.getListGarden());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}

	public void getGardenUserList() {
		try{
			setGardenUsers(adminLogic.gerListGardenUser());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	public void loadData()
	{
		RequestContext context = RequestContext.getCurrentInstance();
		getCompanyList();
		getGardenList();
		getGardenUserList();
		context.update("form:companies");
		context.update("form:gardens");
		context.update("form:gardenUsers");
		
	}

	public void saveCompany() {
		try {
			adminLogic.saveCompany(cursorCompany, userSessionController.getLoggedInfo());
			getCompanyList();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:companies");
			context.execute("PF('companyDialog').hide();");
			setCursorCompany(null);
			userSessionController.showSuccessMessage("Амжилттай!");
		}

		catch (Exception ex) {
			userSessionController.showSuccessMessage("Алдаа гарлаа!");
			ex.printStackTrace();
		}

	}


	public void saveGarden() {
		try{
			adminLogic.saveGarden(cursorGarden, userSessionController.getLoggedInfo());
			getGardenList();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:gardens");
			context.execute("PF('gardenDialog').hide();");
			setCursorGarden(null);
			userSessionController.showSuccessMessage("Амжилттай!");
		}
		catch (Exception ex)
		{
			userSessionController.showSuccessMessage("Алдаа гарлаа!");
			ex.printStackTrace();
		}

	}

	public void saveGardenUser() {
		
		try{
			adminLogic.saveGardenUser(cursorGardenUser, userSessionController.getLoggedInfo());
			getGardenUserList();
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:gardenUserss");
			context.execute("PF('gardenUserDialog').hide();");
			setCursorGardenUser(null);
			userSessionController.showSuccessMessage("Амжилттай!");
		}
		catch (Exception ex)
		{
			userSessionController.showSuccessMessage("Алдаа гарлаа!");
			ex.printStackTrace();
		}

	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public List<Company> getCompanies() {
		if (companies == null)
			companies = new ArrayList<Company>();
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public List<Garden> getGardens() {
		if (gardens == null)
			gardens = new ArrayList<Garden>();
		return gardens;
	}

	public void setGardens(List<Garden> gardens) {
		this.gardens = gardens;
	}

	public List<GardenUser> getGardenUsers() {
		if (gardenUsers == null)
			gardenUsers = new ArrayList<GardenUser>();
		return gardenUsers;
	}

	public void setGardenUsers(List<GardenUser> gardenUsers) {
		this.gardenUsers = gardenUsers;
	}

	public Company getCursorCompany() {
		if (cursorCompany == null)
			cursorCompany = new Company();
		return cursorCompany;
	}

	public void setCursorCompany(Company cursorCompany) {
		this.cursorCompany = cursorCompany;
	}

	public Garden getCursorGarden() {
		if (cursorGarden == null)
			cursorGarden = new Garden();
		return cursorGarden;
	}

	public void setCursorGarden(Garden cursorGarden) {
		this.cursorGarden = cursorGarden;
	}

	public GardenUser getCursorGardenUser() {
		if (cursorGardenUser == null)
			cursorGardenUser = new GardenUser();
		return cursorGardenUser;
	}

	public void setCursorGardenUser(GardenUser cursorGardenUser) {
		this.cursorGardenUser = cursorGardenUser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

package hospital.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "planRequest")
public class PlanControllerRequest {
	
	@ManagedProperty(value="#{userController}")
	private UserSessionController userController;
	
	public PlanControllerRequest(){
		
	}

	public UserSessionController getUserController() {
		return userController;
	}

	public void setUserController(UserSessionController userController) {
		this.userController = userController;
	}

}

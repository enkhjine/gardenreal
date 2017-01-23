package hospital.web.controller;

import hospital.businesslogic.interfaces.IUserLogicLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "planController")
public class PlanController{
	
	//@EJB(beanName = "PlanLogic")
	//IPlanLogicLocal planLogic;
	
	@EJB(beanName = "UserLogic")
	IUserLogicLocal userLogic;
	
	@ManagedProperty(value="#{userController}")
	private UserSessionController userController;
	

	public PlanController(){
		
	}

}

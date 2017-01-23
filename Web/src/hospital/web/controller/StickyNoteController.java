package hospital.web.controller;

import hospital.businesslogic.interfaces.ILogicStickyNoteLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.StickyNoteType;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "noteController")
public class StickyNoteController {

	@EJB(beanName = "LogicStickyNote")
	ILogicStickyNoteLocal noteLogic;
	
	@ManagedProperty(value="#{userController}")
	private UserSessionController userController;
	
	private List<StickyNoteType> listType;
	private StickyNoteType stickyNoteType;
	
	public void listType(){
		try{
			listType = noteLogic.getStickyNoteType(getUserController().getLoggedInfo());
		}catch(Exception ex){
			
		}
	}
	
	public void addStickyNoteType(){
		try{
			if(getStickyNoteType().getName() == null || getStickyNoteType().getName().equals("")) return;
			noteLogic.setStickyNoteType(getStickyNoteType(), getUserController().getLoggedInfo());
		}catch(Exception ex){
			
		}
	}
	
	public List<StickyNoteType> getListType() {
		if(listType == null) listType = new ArrayList<StickyNoteType>();
		return listType;
	}
	
	public void setListType(List<StickyNoteType> listType) {
		this.listType = listType;
	}
	
	public UserSessionController getUserController() {
		return userController;
	}
	
	public void setUserController(UserSessionController userController) {
		this.userController = userController;
	}
	
	public StickyNoteType getStickyNoteType() {
		if(stickyNoteType == null) stickyNoteType = new StickyNoteType();
		return stickyNoteType;
	}
	
	public void setStickyNoteType(StickyNoteType stickyNoteType) {
		this.stickyNoteType = stickyNoteType;
	}
	
}

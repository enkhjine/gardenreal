package hospital.web.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.ProjectStage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import hospital.businesslogic.interfaces.IInfoLogicLocal;

@ApplicationScoped
@ManagedBean(name = "applicationController")
public class ApplicationController implements Serializable {

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cssPath = "/resource/css/source/";
	private String jsPath = "/resource/js/source/";
	private String path = "/resource/js/source/";
	private String file = "D:/GARDEN/FILE/";
	
	public ApplicationController() {

	}

	public void test() {
		System.out.println("OUT : ");
	}
	
	public void test(String text) {
		System.out.println("OUT : " + text);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("ApplicationController : initData");
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		if (fc.isProjectStage(ProjectStage.Production)) {
			setCssPath(request.getContextPath() + "/resource/css/source/");
			setJsPath(request.getContextPath() + "/resource/js/source/");
			setPath(request.getContextPath() + "/resource/");
		} else {
			setCssPath(request.getContextPath() + "/resource/css/source/");
			setJsPath(request.getContextPath() + "/resource/js/source/");
			setPath(request.getContextPath() + "/resource/");
		}
		System.out.println("ApplicationController : initData");
	}

	public String url(String url) {
		return url;
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getJsPath() {
		return jsPath;
	}

	public void setJsPath(String jsPath) {
		this.jsPath = jsPath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
}
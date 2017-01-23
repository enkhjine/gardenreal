package hospital.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.ILogicInsuranceLocal;
import hospital.entity.DiagnoseGroup;
import hospital.entity.InsuranceAmbulanceConfig;
import hospital.entity.InsuranceAmbulanceConfigDtl;
import hospital.entity.InsuranceConfig;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import mondrian.rolap.BitKey.Big;

@SessionScoped
@ManagedBean(name = "insuranceController")
public class InsuranceController {
	
	@EJB(beanName = "LogicInsurance")
	ILogicInsuranceLocal logicInsurance;
	
	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	private InsuranceConfig insuranceConfig;
	
	//Ambulance Даатгалын тохиргоо	
	private InsuranceAmbulanceConfig insuranceAmbulanceConfig;
	private List<InsuranceAmbulanceConfigDtl> listInsuranceAmbulanceConfigDtl;
	private List<InsuranceAmbulanceConfigDtl> listInsuranceAmbulanceConfigDtlTmp;
	private List<DiagnoseGroup> diagnoseGroups;
	private List<DiagnoseGroup> otherDiagnoseGroups;
	
	public void saveInsuranceAmbulanceConfig() {
		try {
			logicInsurance.saveInsuranceAmbulanceConfig(getUserSessionController().getLoggedInfo().getOrganization().getPkId(), getInsuranceAmbulanceConfig(), listInsuranceAmbulanceConfigDtl);
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void saveInsuranceMainAmount() {
		try {
			logicInsurance.saveInsuranceConfig(getInsuranceConfig());
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	
	public void addlistInsuranceAmbulanceConfigDtl() {
		if(getListInsuranceAmbulanceConfigDtlTmp().size() == 0) return;
		InsuranceAmbulanceConfigDtl ambulanceConfigDtl = getListInsuranceAmbulanceConfigDtlTmp().get(getListInsuranceAmbulanceConfigDtlTmp().size() - 1);
		if(ambulanceConfigDtl.getStatus().equals(Tool.LAST)) {
			for(DiagnoseGroup diagnoseGroup : getDiagnoseGroups()) {
				if(diagnoseGroup.getPkId().compareTo(ambulanceConfigDtl.getDiagnoseGroupPkId()) == 0) ambulanceConfigDtl.setDiagnoseGroupName(diagnoseGroup.getName());
			}
			ambulanceConfigDtl.setStatus(Tool.ADDED);
			getListInsuranceAmbulanceConfigDtl().add(ambulanceConfigDtl);
			
			listInsuranceAmbulanceConfigDtlTmp = null;
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:addlistInsuranceAmbulanceConfigDtlTable");
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}
	
	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}
	
	public InsuranceAmbulanceConfig getInsuranceAmbulanceConfig() {
		try {
			if(insuranceAmbulanceConfig == null) insuranceAmbulanceConfig = logicInsurance.getInsuranceAmbulanceConfig(getUserSessionController().getLoggedInfo());
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return insuranceAmbulanceConfig;
	}
	
	public void setInsuranceAmbulanceConfig(
			InsuranceAmbulanceConfig insuranceAmbulanceConfig) {
		this.insuranceAmbulanceConfig = insuranceAmbulanceConfig;
	}
	
	public List<InsuranceAmbulanceConfigDtl> getListInsuranceAmbulanceConfigDtl() {
		try {
			if(listInsuranceAmbulanceConfigDtl == null) listInsuranceAmbulanceConfigDtl = logicInsurance.getListInsuranceAmbulanceConfigDtl(getUserSessionController().getLoggedInfo());
		}catch(Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return listInsuranceAmbulanceConfigDtl;
	}
	
	public void setListInsuranceAmbulanceConfigDtl(
			List<InsuranceAmbulanceConfigDtl> listInsuranceAmbulanceConfigDtl) {
		this.listInsuranceAmbulanceConfigDtl = listInsuranceAmbulanceConfigDtl;
	}
	
	public List<InsuranceAmbulanceConfigDtl> getListInsuranceAmbulanceConfigDtlTmp() {
		if(listInsuranceAmbulanceConfigDtlTmp == null || listInsuranceAmbulanceConfigDtlTmp.size() < 1) {
			listInsuranceAmbulanceConfigDtlTmp = new ArrayList<InsuranceAmbulanceConfigDtl>();
			for (InsuranceAmbulanceConfigDtl item : getListInsuranceAmbulanceConfigDtl()) {
				if(!Tool.UNCHANGED.equals(item.getStatus())) item.setStatus(Tool.ADDED);
				listInsuranceAmbulanceConfigDtlTmp.add(item);
			}
			if(getDiagnoseGroups().size() > getListInsuranceAmbulanceConfigDtl().size()) {
				InsuranceAmbulanceConfigDtl item = new InsuranceAmbulanceConfigDtl();
				item.setStatus(Tool.LAST);
				item.setCost(BigDecimal.ONE);
				item.setOrganizationPkId(getUserSessionController().getLoggedInfo().getOrganization().getPkId());
				listInsuranceAmbulanceConfigDtlTmp.add(item);
			}
		}
		return listInsuranceAmbulanceConfigDtlTmp;
	}
	
	public void setListInsuranceAmbulanceConfigDtlTmp(
			List<InsuranceAmbulanceConfigDtl> listInsuranceAmbulanceConfigDtlTmp) {
		this.listInsuranceAmbulanceConfigDtlTmp = listInsuranceAmbulanceConfigDtlTmp;
	}
	
	public List<DiagnoseGroup> getDiagnoseGroups() {
		try {
			diagnoseGroups = infoLogic.getListDiagnoseGroup(getUserSessionController().getLoggedInfo());
		}catch(Exception ex) {
			
		}
		return diagnoseGroups;
	}
	
	public void setDiagnoseGroups(List<DiagnoseGroup> diagnoseGroups) {
		this.diagnoseGroups = diagnoseGroups;
	}
	
	public List<DiagnoseGroup> getOtherDiagnoseGroups() {
		otherDiagnoseGroups = new ArrayList<DiagnoseGroup>();
		List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
		for(InsuranceAmbulanceConfigDtl ambulanceConfigDtl : getListInsuranceAmbulanceConfigDtl()) {
			bigDecimals.add(ambulanceConfigDtl.getDiagnoseGroupPkId());
		}
		for(DiagnoseGroup diagnoseGroup : getDiagnoseGroups()) {
			if(!bigDecimals.contains(diagnoseGroup.getPkId())) otherDiagnoseGroups.add(diagnoseGroup);
		}
		return otherDiagnoseGroups;
	}
	
	public void setOtherDiagnoseGroups(List<DiagnoseGroup> otherDiagnoseGroups) {
		this.otherDiagnoseGroups = otherDiagnoseGroups;
	}
	
	public InsuranceConfig getInsuranceConfig() {
		if(insuranceConfig == null) {
			try {
				insuranceConfig = logicInsurance.getInsuranceConfig(getUserSessionController().getLoggedInfo().getOrganization().getPkId());
			}catch(Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return insuranceConfig;
	}
	
	public void setInsuranceConfig(InsuranceConfig insuranceConfig) {
		this.insuranceConfig = insuranceConfig;
	}

}

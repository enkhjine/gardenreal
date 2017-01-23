package hospital.web.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicConditionalPrescriptionLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.ConditionalPrescription;
import hospital.entity.ConditionalPrescriptionDtl;
import hospital.entity.ConditionalPrescriptionSubOrganizationTypeMap;
import hospital.entity.Diagnose;
import hospital.entity.Examination;
import hospital.entity.ExaminationType;
import hospital.entity.Medicine;
import hospital.entity.SubOrganizationType;
import hospital.entity.Treatment;
import hospital.entity.TreatmentType;
import hospital.entity.Xray;
import hospital.entity.XrayType;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import antlr.debug.Event;

@SessionScoped
@ManagedBean(name = "cpController")
public class ConditionalPrescriptionController implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName="LogicConditionalPrescription")
	ILogicConditionalPrescriptionLocal logicConPre;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "LogicTreatment")
	ILogicTreatmentLocal logicTreatment;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal logicXray;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "ExaminationLogic")
	ILogicExaminationLocal examLogic;

	@EJB(beanName = "LogicConditionalPrescription")
	ILogicConditionalPrescriptionLocal cpLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	// List
	private List<ConditionalPrescription> conditionalPrescriptions;
	private List<ConditionalPrescriptionDtl> conditionalPrescriptionDtls;
	private List<ConditionalPrescriptionSubOrganizationTypeMap> cpstms;

	// Cursor
	private ConditionalPrescription currentCp;
	private ConditionalPrescriptionDtl currentCpDtl;
	private ConditionalPrescriptionSubOrganizationTypeMap currentCpstm;

	//
	private List<ConditionalPrescriptionDtl> medTempList;
	private List<ConditionalPrescriptionDtl> exaTempList;
	private List<ConditionalPrescriptionDtl> xrayTempList;
	private List<ConditionalPrescriptionDtl> diagnoseTempList;
	private List<ConditionalPrescriptionDtl> treatTempList;
	private List<ConditionalPrescriptionDtl> medList;
	private List<ConditionalPrescriptionDtl> exaList; // Шинжилгээ
	private List<ConditionalPrescriptionDtl> xrayList;
	private	List<ConditionalPrescriptionDtl> treaList;
	private List<ConditionalPrescriptionDtl> diagnoseList;
	private List<ConditionalPrescriptionDtl> allDtlList;


	private List<Medicine> medicines;
	private List<Examination> examinations;
	private List<ExaminationType> exaTypes;
	private List<Xray> xrays;
	private List<Diagnose> diagnoses;
	private List<SubOrganizationType> sos;
	private List<Treatment> treatmentListAuto;


	private List<XrayType> xrayTypes;
	private List<String> soPkIds;

	private Medicine chosenMedicine;
	private Examination chosenExamination;
	private Xray chosenXray;
	private Diagnose chosenDiagnose;
	private XrayType chosenXrayType;
	private ExaminationType chosenExaType;
	private Treatment  chosenTreatment;

	private List<SubOrganizationType> subOrganizationTypes1;
	private List<TreatmentType> listTreatmentType;
	private List<Treatment> listTreatment;


	// Filter
	private String diagnoseFilterKey = "";
	private String text1;

	// Methods
	public void newConditionalPresciption() {
		RequestContext context = RequestContext.getCurrentInstance();
		getCurrentCp();
		currentCp = new ConditionalPrescription();
		currentCp.setCost(new BigDecimal("0"));
		currentCp.setStatus(Tool.ADDED);

		getMedList().clear();
		getExaList().clear();
		getDiagnoseList().clear();
		getXrayList().clear();
		getTreaList().clear();
		getMedList().add(getNewCpDtl());
		getExaList().add(getNewCpDtl());
		getTreaList().add(getNewCpDtl());
		getDiagnoseList().add(getNewCpDtl());
		getXrayList().add(getNewCpDtl());
		getAllDtlList().clear();
		getSoPkIds().clear();
		context.update("form:ddd");
		context.update("form:dtlTab:cpMedList");
		context.update("form:dtlTab:cpDiagnoseList");
		context.update("form:dtlTab:cpExaList");
		context.update("form:dtlTab:cpXrayList");
		context.update("form:dtlTab:treatmentsId");
		context.update("form:sos");

	}

	public void modifyCp(ConditionalPrescription cp) {
		RequestContext context = RequestContext.getCurrentInstance();
		getCurrentCp();
		currentCp = cp;
		currentCp.setStatus(Tool.MODIFIED);
		if (cp.getCost() == null)
			currentCp.setCost(new BigDecimal("0"));
		getMedList().clear();
		getExaList().clear();
		getDiagnoseList().clear();
		getXrayList().clear();
		getTreaList().clear();
		getSoPkIds().clear();
		getCpstms().clear();
		try {
			List<ConditionalPrescriptionDtl> conditionalPrescriptionDtls = cpLogic.getDtls(cp.getPkId());
			for (ConditionalPrescriptionDtl dtl : conditionalPrescriptionDtls) {
				if (dtl.getExaminationPkId() != null)
					exaList.add(dtl);
				if (dtl.getDiagnosePkId() != null)
					diagnoseList.add(dtl);
				if (dtl.getXrayPkId() != null)
					xrayList.add(dtl);
				if (dtl.getMedicinePkId() != null)
					medList.add(dtl);
				if (dtl.getTreatmentPkId()!=null) {
					treaList.add(dtl);
				}
			}
			for (ConditionalPrescriptionSubOrganizationTypeMap map : cpLogic.getMaps(cp.getPkId())) {
				String temp = new String();
				temp = map.getSubOrganizationTypePkId().toString();
				soPkIds.add(temp);
			}
			getMedList().add(getNewCpDtl());
			getExaList().add(getNewCpDtl());
			getDiagnoseList().add(getNewCpDtl());
			getXrayList().add(getNewCpDtl());
			getTreaList().add(getNewCpDtl());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update("form:ddd");
		context.update("form:dtlTab:cpMedList");
		context.update("form:dtlTab:cpDiagnoseList");
		context.update("form:dtlTab:cpExaList");
		context.update("form:dtlTab:cpXrayList");
		context.update("form:dtlTab:treatmentsId");
		context.update("form:sos");

	}

	public void deleteCp(ConditionalPrescription cp) {
		getCurrentCp();
		currentCp = cp;
		currentCp.setStatus(Tool.DELETE);

	}

	public void deleteCp() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			cpLogic.saveCp(currentCp, userSessionController.getLoggedInfo(), getAllDtlList(), getCpstms());
			userSessionController.showMessage(98);
			loadData();
			getCpstms().clear();

			context.execute("PF('confirmDialogDelete').hide();");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void saveCp() {
		getAllDtlList().clear();
		getMedList().remove(getMedList().size() - 1);
		getExaList().remove(getExaList().size() - 1);
		getDiagnoseList().remove(getDiagnoseList().size() - 1);
		getXrayList().remove(getXrayList().size() - 1);
		getTreaList().remove(getTreaList().size()-1);
		allDtlList.addAll(treaList);
		allDtlList.addAll(medList);
		allDtlList.addAll(xrayList);
		allDtlList.addAll(diagnoseList);
		allDtlList.addAll(exaList);
		getCpstms();
		for (String sp : getSoPkIds()) {
			ConditionalPrescriptionSubOrganizationTypeMap map = new ConditionalPrescriptionSubOrganizationTypeMap();
			map.setSubOrganizationTypePkId(new BigDecimal(sp));
			cpstms.add(map);
		}
		if (allDtlList.size() < 1) {

			userSessionController
			.showWarningMessage("Ð‘Ð¾Ð»Ð·Ð¾Ð»Ñ‚ Ð¶Ð¾Ñ€Ñ‹Ð½ Ð¾Ñ€Ñ† Ñ…Ð¾Ð¾Ñ�Ð¾Ð½ Ð±Ð°Ð¹Ð½Ð°!");
		}
		if (cpstms.size() < 1) {
			userSessionController
			.showWarningMessage("Ð‘Ð¾Ð»Ð·Ð¾Ð»Ñ‚ Ð¶Ð¾Ñ€Ñ‹Ð½ Ñ…Ð°Ñ€ÑŠÑ�Ð»Ð°Ð³Ð´Ð°Ñ… Ñ‚Ð°Ñ�Ð³Ð¸Ð¹Ð³ Ñ�Ð¾Ð½Ð³Ð¾Ð½ ÑƒÑƒ!");

			userSessionController.showWarningMessage("Жор хоосон байна!");
		}
		if (cpstms.size() < 1) {
			userSessionController.showWarningMessage("Жор харъялагдах кабинет сонгоогүй байна!");

		}
		if (currentCp.getName() == null || currentCp.getName().isEmpty() || currentCp.getName().equals("")
				|| currentCp.getName().equals(" ")) {

			userSessionController
			.showWarningMessage("Ð‘Ð¾Ð»Ð·Ð¾Ð»Ñ‚ Ð¶Ð¾Ñ€Ñ‹Ð½ Ð½Ñ�Ñ€Ð¸Ð¹Ð³ Ð¾Ñ€ÑƒÑƒÐ»Ð½Ð° ÑƒÑƒ!");
		} else if (currentCp.getUsageDate() == null) {
			userSessionController
			.showWarningMessage("Ð¥Ò¯Ñ‡Ð¸Ð½Ñ‚Ñ�Ð¹ Ñ…ÑƒÐ³Ð°Ñ†Ð°Ð°Ð³ Ð¾Ñ€ÑƒÑƒÐ»Ð½Ð° ÑƒÑƒ!");
			//		} else if (currentCp.getUsageDate().before(new Date())) {
			//			userSessionController
			//					.showWarningMessage("Ð¥Ò¯Ñ‡Ð¸Ð½Ñ‚Ñ�Ð¹ Ñ…ÑƒÐ³Ð°Ñ†Ð°Ð° Ð½ÑŒ Ñ�Ð¸Ñ�Ñ‚ÐµÐ¼Ð¸Ð¹Ð½ Ñ†Ð°Ð³Ð°Ð°Ñ� Ñ…Ð¾Ð¹Ñˆ Ð±Ð°Ð¹Ñ… Ñ‘Ñ�Ñ‚Ð¾Ð¹!");
		} else if (currentCp.getStatementId() == null
				|| currentCp.getStatementId().isEmpty()
				|| currentCp.getStatementId().equals("")
				|| currentCp.getStatementId().equals(" ")) {
			userSessionController
			.showWarningMessage("Ð‘Ð¾Ð»Ð·Ð¾Ð»Ñ‚ Ð¶Ð¾Ñ€Ñ‹Ð½ Ð´ÑƒÐ³Ð°Ð°Ñ€ Ñ…Ð¾Ð¾Ñ�Ð¾Ð½ Ð±Ð°Ð¹Ð½Ð°!");
		} else if (currentCp.getId() == null || currentCp.getId().isEmpty()
				|| currentCp.getId().equals("")
				|| currentCp.getId().equals(" ")) {
			userSessionController
			.showWarningMessage("Ð¢ÑƒÑˆÐ°Ð°Ð»Ñ‹Ð½ Ð´ÑƒÐ³Ð°Ð°Ñ€ Ñ…Ð¾Ð¾Ñ�Ð¾Ð½ Ð±Ð°Ð¹Ð½Ð°!");

			userSessionController.showWarningMessage("Жорын нэр оруулаагүй байна!");
		} else if (currentCp.getUsageDate() == null) {
			userSessionController.showWarningMessage("Жор ашиглах хугацааг оруулаагүй байна!");
			// } else if (currentCp.getUsageDate().before(new Date())) {
			// userSessionController
			// .showWarningMessage("Ð¥Ò¯Ñ‡Ð¸Ð½Ñ‚Ñ�Ð¹ Ñ…ÑƒÐ³Ð°Ñ†Ð°Ð° Ð½ÑŒ
			// Ñ�Ð¸Ñ�Ñ‚ÐµÐ¼Ð¸Ð¹Ð½ Ñ†Ð°Ð³Ð°Ð°Ñ� Ñ…Ð¾Ð¹Ñˆ Ð±Ð°Ð¹Ñ… Ñ‘Ñ�Ñ‚Ð¾Ð¹!");
		} else if (currentCp.getStatementId() == null || currentCp.getStatementId().isEmpty()
				|| currentCp.getStatementId().equals("") || currentCp.getStatementId().equals(" ")) {
			userSessionController.showWarningMessage("Тушаалын дугаар оруулаагүй байна!");
		} else if (currentCp.getId() == null || currentCp.getId().isEmpty() || currentCp.getId().equals("")
				|| currentCp.getId().equals(" ")) {
			userSessionController.showWarningMessage("Жорын дугаар хоосон байна!");

		} else {

			try {
				cpLogic.saveCp(currentCp, userSessionController.getLoggedInfo(), allDtlList, cpstms);
				userSessionController.showMessage(99);
				loadData();
				getSoPkIds().clear();
				getCpstms().clear();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		}
	}

	public void loadData() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			getConditionalPrescriptions();
			getSos();
			conditionalPrescriptions = cpLogic.getCps(userSessionController.getLoggedInfo());
			sos = infoLogic.getSubOrganizationTypes();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update("form:cpGroups");
		context.update("form:sos");
	}

	public void showDetail(BigDecimal cpPkId) {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			getConditionalPrescriptionDtls();
			conditionalPrescriptionDtls = cpLogic.getDtls(cpPkId);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update("form:diii");
	}

	public void removeFromMedTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		medTempList.remove(index);
		context.update("form:tempMedList");
	}

	public void removeFromExaTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		exaTempList.remove(index);
		context.update("form:tempExaList");
	}
	public void removeFromTreatmentList(int index){
		RequestContext context  =  RequestContext.getCurrentInstance();
		treaList.remove(index);
		context.update("form:treatmentsId");
	}

	public void removeFromDiagnoseTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		diagnoseTempList.remove(index);
		context.update("form:tempDiagnoseList");
	}

	public void removeFromXrayTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		xrayTempList.remove(index);
		context.update("form:tempXrayList");
	}

	public void removeFromMedList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		medList.remove(index);
		context.update("form:dtlTab:cpMedList");
	}
	public  void  removeTreatmentTemp(int  index){
		RequestContext  context  =  RequestContext.getCurrentInstance();
		treatTempList.remove(index);
		context.update("form:tempTreatment");
	}

	public void removeFromExaList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		exaList.remove(index);
		context.update("form:dtlTab:cpExaList");
	}

	public void removeFromDiagnoseList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		diagnoseList.remove(index);
		context.update("form:dtlTab:cpDiagnoseList");
	}

	public void removeFromXrayList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		xrayList.remove(index);
		context.update("form:dtlTab:cpXrayList");
	}

	public ConditionalPrescriptionDtl getNewCpDtl() {
		ConditionalPrescriptionDtl cpDtl = new ConditionalPrescriptionDtl();
		cpDtl.setStatus(Tool.LAST);
		return cpDtl;
	}

	public void chooseMedicine() {
		RequestContext context = RequestContext.getCurrentInstance();
		ConditionalPrescriptionDtl cpDtl = new ConditionalPrescriptionDtl();
		cpDtl.setMedicinePkId(chosenMedicine.getPkId());
		cpDtl.setMedName(chosenMedicine.getName());
		cpDtl.setMedId(chosenMedicine.getId());
		cpDtl.setMedTypeName(chosenMedicine.getTypeName());
		cpDtl.setDrugDose(chosenMedicine.getDrugDose());
		cpDtl.setiName(chosenMedicine.getiName());
		if (medList == null)
			getMedTempList();
		medTempList.add(cpDtl);
		context.update("form:tempMedList");
	}

	public void chooseExaType() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setExaminations(examLogic.getExaminationList(chosenExaType.getPkId(), true));
		} catch (Exception ex) {

		}
		context.update("form:exaList");

	}

	public void chooseExamination() {
		RequestContext context = RequestContext.getCurrentInstance();
		ConditionalPrescriptionDtl cpDtl = new ConditionalPrescriptionDtl();
		cpDtl.setExaminationPkId(chosenExamination.getPkId());
		cpDtl.setExaName(chosenExamination.getName());
		cpDtl.setCost(chosenExamination.getPrice());
		if (exaTempList == null)
			getExaTempList();
		exaTempList.add(cpDtl);
		context.update("form:tempExaList");

	}
	public  void  chooseTreatment(){
		RequestContext  context   =  RequestContext.getCurrentInstance();
		ConditionalPrescriptionDtl  treatmentDtl  =  new ConditionalPrescriptionDtl();
		treatmentDtl.setTreatmentPkId(chosenTreatment.getPkId());
		treatmentDtl.setTreatmentName(chosenTreatment.getName());
		treatmentDtl.setId(chosenTreatment.getId());
		//		treatmentDtl.setDescription(chosenTreatment.get);
		treatTempList.add(treatmentDtl);
		context.update("form:tempTreatment");

	}
	public void chooseXrayType() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setXrays(logicXray.getXrayByDianoseTypePkId(chosenXrayType.getPkId()));
		}

		catch (Exception ex) {

		}
		context.update("form:xrayList");
	}

	public void chooseXray() {
		RequestContext context = RequestContext.getCurrentInstance();
		ConditionalPrescriptionDtl cpDtl = new ConditionalPrescriptionDtl();
		cpDtl.setXrayPkId(chosenXray.getPkId());
		cpDtl.setXrayName(chosenXray.getName());
		cpDtl.setCost(chosenXray.getPriceIn());
		getXrayTempList();
		xrayTempList.add(cpDtl);
		context.update("form:tempXrayList");
	}

	public void chooseDiagnose() {
		RequestContext context = RequestContext.getCurrentInstance();
		ConditionalPrescriptionDtl cpDtl = new ConditionalPrescriptionDtl();
		cpDtl.setDiagnosePkId(chosenDiagnose.getPkId());
		cpDtl.setDiagnoseNameMn(chosenDiagnose.getNameMn());
		cpDtl.setDiagnoseIcd(chosenDiagnose.getId());
		cpDtl.setDiagnoseNameRu(chosenDiagnose.getNameRu());
		cpDtl.setDiagnoseNameEn(chosenDiagnose.getNameEn());
		if (diagnoseTempList == null)
			getDiagnoseTempList();
		diagnoseTempList.add(cpDtl);
		context.update("form:tempDiagnoseList");
	}

	public void insertMedicineToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			medList.remove(medList.size() - 1);
			for (ConditionalPrescriptionDtl cc : getMedTempList()) {
				ConditionalPrescriptionDtl cq = new ConditionalPrescriptionDtl();
				cq = (ConditionalPrescriptionDtl) Tool.deepClone(cc);
				medList.add(cq);
			}

			medList.add(getNewCpDtl());
			medTempList.clear();
			context.update("form:dtlTab:cpMedList");
			context.update("form:tempMedList");
			context.execute("PF('regMed').hide();");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void insertTreatment(){
		RequestContext  context  =  RequestContext.getCurrentInstance();
		try {
			treaList.remove(treaList.size()-1);
			for (ConditionalPrescriptionDtl  tc:getTreatTempList()) {
				ConditionalPrescriptionDtl dtTemp  = new ConditionalPrescriptionDtl();
				dtTemp = (ConditionalPrescriptionDtl) Tool.deepClone(tc);
				treaList.add(dtTemp);
			}
			treaList.add(getNewCpDtl());
			treatTempList.clear();
			context.update("form:dtlTab:treatmentsId");
			context.update("form:tempTreatment");
			context.execute("PF('treatmentList').hide()");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertXrayToList() {
		getXrayList();
		RequestContext context = RequestContext.getCurrentInstance();
		xrayList.remove(xrayList.size() - 1);
		for (ConditionalPrescriptionDtl cc : getXrayTempList()) {
			ConditionalPrescriptionDtl cq = new ConditionalPrescriptionDtl();
			cq = (ConditionalPrescriptionDtl) Tool.deepClone(cc);
			xrayList.add(cq);
			if (cq.getCost() != null)
				currentCp.setCost(currentCp.getCost().add(cq.getCost()));
		}
		xrayList.add(getNewCpDtl());
		xrayTempList.clear();
		context.update("form:dtlTab:cpXrayList");
		context.update("form:tempXrayList");
		context.update("form:allCost");
		context.execute("PF('regXray').hide();");

	}

	public void insertDiagnoseToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		diagnoseList.remove(diagnoseList.size() - 1);
		for (ConditionalPrescriptionDtl cc : getDiagnoseTempList()) {
			ConditionalPrescriptionDtl cq = new ConditionalPrescriptionDtl();
			cq = (ConditionalPrescriptionDtl) Tool.deepClone(cc);
			diagnoseList.add(cq);
		}

		diagnoseList.add(getNewCpDtl());
		diagnoseTempList.clear();
		context.update("form:dtlTab:cpDiagnoseList");
		context.update("form:tempDiagnoseList");
		context.execute("PF('regDiagnose').hide();");

	}

	public void insertExaminationToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		exaList.remove(exaList.size() - 1);
		for (ConditionalPrescriptionDtl cc : getExaTempList()) {
			ConditionalPrescriptionDtl cq = new ConditionalPrescriptionDtl();
			cq = (ConditionalPrescriptionDtl) Tool.deepClone(cc);
			exaList.add(cq);
			if (cq.getCost() != null)
				currentCp.setCost(currentCp.getCost().add(cq.getCost()));
		}

		exaList.add(getNewCpDtl());
		exaTempList.size();
		getExaTempList().clear();
		context.update("form:dtlTab:cpExaList");
		context.update("form:tempExaList");
		context.update("form:allCost");
		context.execute("PF('regExa').hide();");

	}

	public void getMedListFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setMedicines(infoLogic.getMedicine(BigDecimal.ZERO, userSessionController.getLoggedInfo(), ""));
			context.update("form:medList");
			context.execute("PF('regMed').show();");
		} catch (Exception ex) {

		}
	}
	public void getTreatmentFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			subOrganizationTypes1 =  infoLogic.getSubOrganizationTypes1();
			context.update("form:treatmentPanel");
			context.execute("PF('treatmentList').show();");
		} catch (Exception ex) {

		}
	}


	public void getXrayTypesFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setXrayTypes(logicXray.getXrayTypes(userSessionController.getLoggedInfo()));
		} catch (Exception ex) {
		}
		context.update("form:xrayTypeList");
		context.execute("PF('regXray').show();");
	}

	public void getDiagnoseFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setDiagnoses(infoLogic.getDiagnoses(getDiagnoseFilterKey()));

		} catch (Exception ex) {

		}
		context.execute("PF('regDiagnose').show()");

	}

	public void getExaTypeFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setExaTypes(examLogic.getExaminationTypeList());
		}

		catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
		context.update("form:exaTypeList");
		context.execute("PF('regExa').show()");

	}

	// Getter Setter
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

	public List<ConditionalPrescription> getConditionalPrescriptions() {
		if (conditionalPrescriptions == null)
			conditionalPrescriptions = new ArrayList<ConditionalPrescription>();

		return conditionalPrescriptions;
	}

	public void setConditionalPrescriptions(List<ConditionalPrescription> conditionalPrescriptions) {
		this.conditionalPrescriptions = conditionalPrescriptions;
	}

	public List<ConditionalPrescriptionDtl> getConditionalPrescriptionDtls() {
		if (conditionalPrescriptionDtls == null)
			conditionalPrescriptionDtls = new ArrayList<ConditionalPrescriptionDtl>();
		return conditionalPrescriptionDtls;
	}

	public void setConditionalPresciptionDtls(List<ConditionalPrescriptionDtl> conditionalPrescriptionDtls) {
		this.conditionalPrescriptionDtls = conditionalPrescriptionDtls;
	}

	public List<ConditionalPrescriptionSubOrganizationTypeMap> getCpstms() {
		if (cpstms == null)
			cpstms = new ArrayList<ConditionalPrescriptionSubOrganizationTypeMap>();
		return cpstms;
	}

	public void setCpstms(List<ConditionalPrescriptionSubOrganizationTypeMap> cpstms) {
		this.cpstms = cpstms;
	}

	public ConditionalPrescription getCurrentCp() {
		if (currentCp == null)
			currentCp = new ConditionalPrescription();
		return currentCp;
	}

	public void setCurrentCp(ConditionalPrescription currentCp) {
		this.currentCp = currentCp;
	}

	public ConditionalPrescriptionDtl getCurrentCpDtl() {
		if (currentCpDtl == null)
			currentCpDtl = new ConditionalPrescriptionDtl();
		return currentCpDtl;
	}

	public void setCurrentCpDtl(ConditionalPrescriptionDtl currentCpDtl) {
		this.currentCpDtl = currentCpDtl;
	}

	public ConditionalPrescriptionSubOrganizationTypeMap getCurrentCpstm() {
		if (currentCpstm == null)
			currentCpstm = new ConditionalPrescriptionSubOrganizationTypeMap();
		return currentCpstm;
	}

	public void setCurrentCpstm(ConditionalPrescriptionSubOrganizationTypeMap currentCpstm) {
		this.currentCpstm = currentCpstm;
	}

	public List<ConditionalPrescriptionDtl> getMedTempList() {
		if (medTempList == null)
			medTempList = new ArrayList<ConditionalPrescriptionDtl>();
		return medTempList;
	}

	public void setMedTempList(List<ConditionalPrescriptionDtl> medTempList) {
		this.medTempList = medTempList;
	}

	public List<ConditionalPrescriptionDtl> getExaTempList() {
		if (exaTempList == null)
			exaTempList = new ArrayList<ConditionalPrescriptionDtl>();
		return exaTempList;
	}

	public void setExaTempList(List<ConditionalPrescriptionDtl> exaTempList) {
		this.exaTempList = exaTempList;
	}

	public List<ConditionalPrescriptionDtl> getXrayTempList() {
		if (xrayTempList == null)
			xrayTempList = new ArrayList<ConditionalPrescriptionDtl>();
		return xrayTempList;
	}

	public void setXrayTempList(List<ConditionalPrescriptionDtl> xrayTempList) {
		this.xrayTempList = xrayTempList;
	}

	public List<ConditionalPrescriptionDtl> getDiagnoseTempList() {
		if (diagnoseTempList == null)
			diagnoseTempList = new ArrayList<ConditionalPrescriptionDtl>();
		return diagnoseTempList;
	}

	public void setDiagnoseTempList(List<ConditionalPrescriptionDtl> diagnoseTempList) {
		this.diagnoseTempList = diagnoseTempList;
	}

	public List<ConditionalPrescriptionDtl> getMedList() {
		if (medList == null) {
			medList = new ArrayList<ConditionalPrescriptionDtl>();
			medList.add(getNewCpDtl());
		}
		return medList;
	}

	public void setMedList(List<ConditionalPrescriptionDtl> medList) {
		this.medList = medList;
	}

	public List<ConditionalPrescriptionDtl> getExaList() {
		if (exaList == null) {
			exaList = new ArrayList<ConditionalPrescriptionDtl>();
			exaList.add(getNewCpDtl());
		}
		return exaList;
	}

	public void setExaList(List<ConditionalPrescriptionDtl> exaList) {
		this.exaList = exaList;
	}

	public List<ConditionalPrescriptionDtl> getXrayList() {
		if (xrayList == null) {
			xrayList = new ArrayList<ConditionalPrescriptionDtl>();
			xrayList.add(getNewCpDtl());

		}
		return xrayList;
	}

	public void setXrayList(List<ConditionalPrescriptionDtl> xrayList) {
		this.xrayList = xrayList;
	}

	public List<ConditionalPrescriptionDtl> getDiagnoseList() {
		if (diagnoseList == null) {
			diagnoseList = new ArrayList<ConditionalPrescriptionDtl>();
			diagnoseList.add(getNewCpDtl());
		}

		return diagnoseList;
	}

	public void setDiagnoseList(List<ConditionalPrescriptionDtl> diagnoseList) {
		this.diagnoseList = diagnoseList;
	}

	public List<ConditionalPrescriptionDtl> getAllDtlList() {
		if (allDtlList == null)
			allDtlList = new ArrayList<ConditionalPrescriptionDtl>();
		return allDtlList;
	}

	public void setAllDtlList(List<ConditionalPrescriptionDtl> allDtlList) {
		this.allDtlList = allDtlList;
	}

	public List<Medicine> getMedicines() {
		if (medicines == null)
			medicines = new ArrayList<Medicine>();
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<Examination> getExaminations() {
		if (examinations == null)
			examinations = new ArrayList<Examination>();
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public List<Xray> getXrays() {
		if (xrays == null)
			xrays = new ArrayList<Xray>();
		return xrays;
	}

	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}

	public List<Diagnose> getDiagnoses() {
		if (diagnoses == null)
			diagnoses = new ArrayList<Diagnose>();
		return diagnoses;
	}

	public void setDiagnoses(List<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public List<XrayType> getXrayTypes() {
		if (xrayTypes == null)
			xrayTypes = new ArrayList<XrayType>();
		return xrayTypes;
	}

	public void setXrayTypes(List<XrayType> xrayTypes) {
		this.xrayTypes = xrayTypes;
	}

	public Medicine getChosenMedicine() {
		if (chosenMedicine == null)
			chosenMedicine = new Medicine();
		return chosenMedicine;
	}

	public void setChosenMedicine(Medicine chosenMedicine) {
		this.chosenMedicine = chosenMedicine;
	}

	public Examination getChosenExamination() {
		if (chosenExamination == null)
			chosenExamination = new Examination();
		return chosenExamination;
	}

	public void setChosenExamination(Examination chosenExamination) {
		this.chosenExamination = chosenExamination;
	}

	public Xray getChosenXray() {
		if (chosenXray == null)
			chosenXray = new Xray();
		return chosenXray;
	}

	public void setChosenXray(Xray chosenXray) {
		this.chosenXray = chosenXray;
	}

	public Diagnose getChosenDiagnose() {
		if (chosenDiagnose == null)
			chosenDiagnose = new Diagnose();
		return chosenDiagnose;
	}

	public void setChosenDiagnose(Diagnose chosenDiagnose) {
		this.chosenDiagnose = chosenDiagnose;
	}

	public XrayType getChosenXrayType() {
		if (chosenXrayType == null)
			chosenXrayType = new XrayType();
		return chosenXrayType;
	}

	public void setChosenXrayType(XrayType chosenXrayType) {
		this.chosenXrayType = chosenXrayType;
	}

	public String getDiagnoseFilterKey() {
		if (diagnoseFilterKey == null)
			diagnoseFilterKey = "";
		return diagnoseFilterKey;
	}

	public void setDiagnoseFilterKey(String diagnoseFilterKey) {
		this.diagnoseFilterKey = diagnoseFilterKey;
	}

	public List<ExaminationType> getExaTypes() {
		if (exaTypes == null)
			exaTypes = new ArrayList<ExaminationType>();
		return exaTypes;
	}

	public void setExaTypes(List<ExaminationType> exaTypes) {
		this.exaTypes = exaTypes;
	}

	public ExaminationType getChosenExaType() {
		if (chosenExaType == null)
			chosenExaType = new ExaminationType();
		return chosenExaType;
	}

	public void setChosenExaType(ExaminationType chosenExaType) {
		this.chosenExaType = chosenExaType;
	}

	public List<SubOrganizationType> getSos() {
		if (sos == null)
			sos = new ArrayList<SubOrganizationType>();
		return sos;
	}

	public void setSos(List<SubOrganizationType> sos) {
		this.sos = sos;
	}

	public List<String> getSoPkIds() {
		if (soPkIds == null)
			soPkIds = new ArrayList<String>();
		return soPkIds;
	}

	public void setSoPkIds(List<String> soPkIds) {
		this.soPkIds = soPkIds;
	}

	public String getText1() {
		return text1;
	}

	public void setText1(String text1) {
		this.text1 = text1;
	}
	public  List<String>  completeText(String q){
		List<String> list   = new ArrayList<>();
		try {

			medicines=infoLogic.getMedicine(BigDecimal.ZERO,userSessionController.getLoggedInfo() , q);
			for (int j = 0; j < medicines.size(); j++) {
				list.add(medicines.get(j).getName().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userSessionController.showErrorMessage("aldaa" + e.getMessage());
			e.printStackTrace();
		}
		return  list;
	}
	public  List<String> completeTreatment(String q){
		List<String> listTr =  new ArrayList<>();
		try {
			treatmentListAuto =infoLogic.getTreatmentLsit(q);
			for (int i = 0; i < treatmentListAuto.size(); i++) {
				listTr.add(treatmentListAuto.get(i).getName().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listTr;
	}
	public void  onLstMedicineStrSelect(SelectEvent  event){
		try {
			for (ConditionalPrescriptionDtl  m :  medList) 
				if (event.getObject().toString().equals(m.getMedName())) 
					return;
			List<Medicine>  listMed  =  logicConPre.getByAnyField(Medicine.class, "name", event.getObject().toString());
			if (listMed.size()>0) {
				ConditionalPrescriptionDtl  dtl  =  new ConditionalPrescriptionDtl();
				dtl.setMedicinePkId(listMed.get(0).getPkId());
				dtl.setMedId(listMed.get(0).getId());
				dtl.setMedName(listMed.get(0).getName());
				dtl.setMedTypeName(listMed.get(0).getTypeName());
				dtl.setRepeatCount(0);
				dtl.setStatus(Tool.ADDED);
				medList.add(dtl);
			}
			else
				getMedListFromDb();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
	public  void  onSelectTreatment(SelectEvent  event){
		try {
			
			for(ConditionalPrescriptionDtl  cdtls:treaList)
				if (event.getObject().equals(cdtls.getTreatmentName())) 
					return;
			List<Treatment>  trlist =  logicConPre.getByAnyField(Treatment.class, "name", event.getObject().toString());
			if (trlist.size()>0) {
				ConditionalPrescriptionDtl  tdtls  =  new ConditionalPrescriptionDtl();
				tdtls.setTreatmentPkId(trlist.get(0).getPkId());
				tdtls.setTreatmentName(trlist.get(0).getName());
				tdtls.setId(trlist.get(0).getId());
				tdtls.setStatus(Tool.ADDED);
				treaList.add(tdtls);
			}
			else getTreatmentFromDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  autoFillTreatment(){
		try {
			if (treaList!=null  && treaList.size()>0  &&  Tool.LAST.equals(treaList.get(treaList.size()-1).getStatus())) {
				ConditionalPrescriptionDtl  codtl  = treaList.get(treaList.size()-1);
				List<Treatment>  listt =  logicConPre.getByAnyField(Treatment.class, "name", codtl.getTreatmentName());
				if (listt.size()>0) {
					ConditionalPrescriptionDtl  dtlss  =  new ConditionalPrescriptionDtl();
					dtlss.setTreatmentPkId(listt.get(0).getPkId());
					dtlss.setTreatmentName(listt.get(0).getName());
					dtlss.setId(listt.get(0).getId());
					dtlss.setStatus(Tool.ADDED);
					treaList.add(dtlss);
				}
			}
			else  getTreatmentFromDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void  autoFillmedicine(){
		try {
			if (medList!=null &&  medList.size()>0 && Tool.LAST.equals(medList.get(medList.size()-1).getStatus())) {
				ConditionalPrescriptionDtl  coDtl   =  medList.get(medList.size()-1);
				List<Medicine>  listMed  =  logicConPre.getByAnyField(Medicine.class, "name", coDtl.getMedName());
				if (listMed.size()>0) {
					ConditionalPrescriptionDtl  dtl  =  new ConditionalPrescriptionDtl();
					dtl.setMedicinePkId(listMed.get(0).getPkId());
					dtl.setMedId(listMed.get(0).getId());
					dtl.setMedTypeName(listMed.get(0).getTypeName());
					dtl.setRepeatCount(0);
					dtl.setStatus(Tool.ADDED);
					medList.add(dtl);
				}
				else
					getMedListFromDb();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

	public  List<String>  completeExamination(String q){
		List<String> list   = new ArrayList<>();
		try {
			examinations=examLogic.getExaminationList(q, true);
			for (int j = 0; j < examinations.size(); j++) {
				list.add(examinations.get(j).getName().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			userSessionController.showErrorMessage("aldaa" + e.getMessage());
			e.printStackTrace();
		}
		return  list;
	}
	public  void onLstExaminationStrSelect(SelectEvent event){
		try {
			//			for(ConditionalPrescriptionDtl  cdt:exaList)
			//				if (event.getObject().equals(cdt.getName().toString())) {
			//					return;
			//				}
			//				else
			//			List<Examination>  listExamination  =  logicConPre.getByAnyField(Examination.class, "name",event.getObject().toString());
			//			if (listExamination.size()>0) {
			//				System.out.println(" nos ");
			//				ConditionalPrescriptionDtl  dtls  =  new ConditionalPrescriptionDtl();
			//				dtls.setExaminationPkId(listExamination.get(0).getPkId());
			//				dtls.setExaName(listExamination.get(0).getName());
			//				dtls.setCost(listExamination.get(0).getPrice());
			//				dtls.setStatus(Tool.ADDED);
			//				exaList.add(dtls);
			//				System.out.println( "exaLister " + exaList.toString());
			//			}
			getExaTypeFromDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void  autoFillexamination1(){
		try {
			if (exaList!=null &&  exaList.size()>0 &&  Tool.LAST.equals(exaList.get(exaList.size()-1).getStatus())) {
				ConditionalPrescriptionDtl  dtls  =  exaList.get(exaList.size()-1);
				List<Examination>  exaList1  =  logicConPre.getByAnyField(Examination.class, "name", dtls.getExaName());
				if (exaList1.size()>0) {
					ConditionalPrescriptionDtl  dtp  =  new ConditionalPrescriptionDtl();
					dtp.setExaminationPkId(exaList1.get(0).getPkId());
					dtp.setExaName(exaList1.get(0).getName());
					dtp.setCost(exaList1.get(0).getPrice());
					dtls.setStatus(Tool.ADDED);
					exaList.add(dtls);
				}
			}
			else getExaTypeFromDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public  void  sortTreatmentTypeBySot(BigDecimal sortPkId){
		try {
			setListTreatmentType(logicTreatment.getTreatmentTypeBySot(userSessionController.getLoggedInfo(), sortPkId, sortPkId)); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public  void  sortByTreatmentType(BigDecimal pkId){
		try {
			listTreatment  = logicTreatment.getTreatments(userSessionController.getLoggedInfo(), pkId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<SubOrganizationType> getSubOrganizationTypes1() {
		if (subOrganizationTypes1==null) {
			subOrganizationTypes1  =  new ArrayList<>();
		}
		return subOrganizationTypes1;
	}

	public void setSubOrganizationTypes1(List<SubOrganizationType> subOrganizationTypes1) {
		this.subOrganizationTypes1 = subOrganizationTypes1;
	}

	public List<TreatmentType> getListTreatmentType() {
		return listTreatmentType;
	}

	public void setListTreatmentType(List<TreatmentType> listTreatmentType) {
		this.listTreatmentType = listTreatmentType;
	}

	public List<Treatment> getListTreatment() {
		return listTreatment;
	}

	public void setListTreatment(List<Treatment> listTreatment) {
		this.listTreatment = listTreatment;
	}


	public List<ConditionalPrescriptionDtl> getTreatTempList() {
		if (treatTempList==null) {
			treatTempList  =  new ArrayList<>();
		}
		return treatTempList;
	}

	public void setTreatTempList(List<ConditionalPrescriptionDtl> treatTempList) {
		this.treatTempList = treatTempList;
	}

	public Treatment getChosenTreatment() {
		if (chosenTreatment==null) {
			chosenTreatment =  new Treatment();
		}
		return chosenTreatment;
	}

	public void setChosenTreatment(Treatment chosenTreatment) {
		this.chosenTreatment = chosenTreatment;
	}

	public List<ConditionalPrescriptionDtl> getTreaList() {
		if (treaList==null) {
			treaList  =  new  ArrayList<>();
			treaList.add(getNewCpDtl());
		}
		return treaList;
	}

	public void setTreaList(List<ConditionalPrescriptionDtl> treaList) {
		this.treaList = treaList;
	}

	public List<Treatment> getTreatmentListAuto() {
		if (treatmentListAuto==null) {
			treatmentListAuto =  new ArrayList<>();
		}
		return treatmentListAuto;
	}

	public void setTreatmentListAuto(List<Treatment> treatmentListAuto) {
		this.treatmentListAuto = treatmentListAuto;
	}

}

package hospital.web.controller;

import hospital.annotation.Label;
import hospital.businessentity.*;
import hospital.businesslogic.interfaces.*;
import hospital.entity.*;
import hospital.report.Gt15Pain;
import logic.data.Tools;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.io.FilenameUtils;
import org.castor.core.util.Base64Encoder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import hospital.businessentity.CustomerSkin;

@SessionScoped
@ManagedBean(name = "inspectionController")
public class InspectionController implements Serializable {

	private static final long serialVersionUID = 1635006922576701405L;

	// Region - Logic
	@EJB(beanName = "LogicCustomer")
	ILogicCustomerLocal logicCustomer;

	@EJB(beanName = "LogicTwo")
	ILogicTwoLocal logicTwo;

	@EJB(beanName = "LogicInspection")
	IInspectionLogicLocal logicInspection;

	@EJB(beanName = "LogicTreatment")
	ILogicTreatmentLocal logicTreatment;

	@EJB(beanName = "LogicXray")
	ILogicXrayLocal logicXray;

	@EJB(beanName = "InfoLogic")
	IInfoLogicLocal infoLogic;

	@EJB(beanName = "LogicConditionalPrescription")
	ILogicConditionalPrescriptionLocal logicConPre;

	@EJB(beanName = "ExaminationLogic")
	ILogicExaminationLocal examLogic;

	@EJB(beanName = "LogicSurgery")
	ILogicSurgeryLocal logicSurgery;

	@EJB(beanName = "LogicTreatmentRequest")
	ILogicTreatmentRequestLocal logicTr;

	// EndRegion

	// Region Controller
	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	// EndRegion

	// Region Lists
	private List<OrderedTreatment> orderedTreatments;
	private List<CustomerDiagnose> listDiagnose;
	private List<CustomerRequest> customerRequests;
	private List<CustomerRequest> ers;
	private List<ConditionalPrescription> listConPre;
	private List<ConditionalPrescriptionDtl> listConPreDtl;
	private List<CustomerMedicine> listMedicine;
	private List<Inspection> listInspection;
	private List<InspectionDtl> listExamination;
	private List<InspectionDtl> listTreatment;
	private List<InspectionDtl> listXray;
	private List<InspectionDtl> listOcsDtl;
	private List<InspectionDtl> listSurgery;
	private List<CustomerMedicine> listOcsCustomerMedicine;
	private List<TreatmentType> treatmentTypes;
	private List<Treatment> ttt;
	private List<Treatment> chosenTreatments;
	private List<ExaminationType> exaTypes;
	private List<Examination> examinations;
	private List<Examination> emrExaminations;
	private List<Examination> exaTempList;
	private List<XrayType> xrayTypes;
	private List<Gt15Pain> inspectionEmployeeSignatureList;
	private List<Xray> xrays;
	private List<Xray> xrayTempList;
	private List<Medicine> medicines;
	private List<Medicine> medTempList;
	private List<SubOrganization> subOrganizations;
	private List<Employee> employees;
	private List<String> selectedOcsSubOrgaPkId;
	private List<String> selectedOcsEmployee;
	private List<String> selectedErmSubOrgaPkId;
	private List<String> selectedErmEmployee;
	private TreeNode nodeInspection;
	private TreeNode[] selectedNode;
	private TreeNode nodeAll;
	private List<CustomerInspectionInfo> listInspectionInfo;
	private List<CustomerInspectionInfo> listMedicineInfo;
	private List<InspectionDtl> listTreatmentInfo;
	private List<InspectionDtl> listSurgeryInfo;
	private List<CustomerXray> listCustomerXrayInfo;
	private List<CustomerTreatment> listCustomerTreatmentInfo;
	private List<ExaminationRequestCompleted> listExaminationRequestCompleted;
	private List<SurgeryType> surgeryTypes;
	private List<Surgery> surgeries;
	private List<Surgery> surgeryTempList;
	private List<DoctorRecipe> doctorRecipes;
	private DoctorRecipe selDoctorRecipe;
	private List<Integer> years;
	private List<Diagnose> approvedFilteredDiagnose;
	private List<CustomerExamination> customerExaminations;
	private List<ExaminationResults> examinationResults;
	private List<ExaminationRequestCompleted> currentCustomerExaminations;
	private List<JSONObject> parentList;
	private List<InspectionForm> inspectionForm = new ArrayList<InspectionForm>();
	private List<InspectionDetail> inspectionDetails;
	private List<EmployeeMemo> employeeMemoList;
	private List<EmployeeMemo> employeeMemoPublicList;
	private List<CustomerProblem> listProblem;
	private List<CustomerAttachment> listReAttachment;
	private List<CustomerAttachment> listNoseAttachment;
	private List<CustomerAttachment> listEarAttachment;
	private List<CustomerAttachment> listThroatAttachment;
	private List<StreamedContent> listReStreamedContent;
	private List<CustomerAttachment> listSkinAttachment;
	private List<CustomerAttachment> listLungAttachment;
	private List<CustomerAttachment> listHematoAttachment;
	private List<CustomerAttachment> listCardioAttachment;
	private List<CustomerAttachment> listkidneyAttachment;
	private List<CustomerAttachment> listEyeAttachment;
	private List<CustomerAttachment> listTradtionAttachment;
	private List<CustomerAttachment> listDefaultAttachment;
	private List<CustomerAttachment> listEndocrineAttachment;
	private List<CustomerAttachment> listThryoidAttachment;
	private List<CustomerAttachment> listDiabetesAttachment;
	private List<CustomerAttachment> listAttachment1;
	private List<CustomerAttachment> listAttachment2;
	private List<CustomerAttachment> listAttachment3;
	private List<CustomerAttachment> listAttachment4;
	private List<CustomerAttachment> listRehabilitationAttachment;
	private List<CustomerAttachment> listJaundiceAttachment;
	private List<CustomerAttachment> listRachitisAttachment;
	private List<CustomerAttachment> listDiarrhoeaAttachment;
	private List<CustomerAttachment> listPediatricInfectionAttachment;
	private List<CustomerAttachment> listTubeInflammationAttachment;
	private List<CustomerAttachment> listPediatricsOtherPainAttachment;
	private List<CustomerAttachment> listHeadacheAttachment;
	private List<CustomerAttachment> listBackacheAttachment;
	private List<CustomerAttachment> listEpilepsyAttachment;
	private List<CustomerAttachment> listApoplexyAttachment;
	private Inspection selectedInspection;
	private List<XrayRequest> xrayRequests;
	private DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private CustomerExamination filterExamination;
	private Gt15Pain inspectiont15Pains;
	private List<CustomerMedicine> listCustomerMedicines;
	private String[] selectedConsoles2;

	// private List<UsingPriceHistory> duph;
	// EndRegion

	// Region - Properties

	private CustomerMatter customerMatter;
	private CustomerPain customerPain;
	private CustomerInspection customerInsp;
	private CustomerPlan customerPlan;
	private CustomerQuestion customerQuestion;
	private XrayRequest currentXrayRequest;
	private Inspection currentInspection;
	private SubOrganization currentSubOrganization;
	private EmployeeRequest currentEmployeeRequest;
	private CustomerRequest currentCustomerRequest;
	private BigDecimal currentConPrePkId;
	private Treatment cursorTreatment;
	private InspectionDtl currentInspectionDtl;
	private ExaminationDtl currentExaminationDtl;
	private int index;
	private CustomerNose customerNose;
	private CustomerEar customerEar;
	private CustomerThroat customerThroat;
	private CustomerLung customerLung;
	private CustomerStomach customerStomach;
	private CustomerCardiology customerCardiology;
	private Surgery chosenSurgery;
	private int currentInspectionPage;
	private DoctorRecipe recipe;
	private BigDecimal currentRecipe;
	private EmployeeMemo employeeMemo;
	private EmployeeMemo employeeMemoPublic;
	private String subOrgaType;
	private StreamedContent imageInspection;
	private CustomerAttachment attachment;
	private CustomerDiagnose diagnoseFilter;
	private String whichDiagnose;
	private int whichDiagnoseType;
	private CustomerColon customerColon;
	private CustomerLiver customerLiver;
	private CustomerPancreas customerPancreas;
	private CustomerEndocrine customerEndocrine;
	private CustomerThryoid customerThryoid;
	private CustomerDiabetes customerDiabetes;
	private CustomerJaundice customerJaundice;
	private CustomerRachitis customerRachitis;
	private CustomerDiarrhoea customerDiarrhoea;
	private CustomerPediatricInfection customerPediatricInfection;
	private CustomerTubeInflammation customerTubeInflammation;
	private CustomerPediatricsOther customerPediatricsOtherPain;
	private CustomerHeadache customerHeadache;
	private CustomerBackache customerBackache;
	private CustomerEpilepsy customerEpilepsy;
	private CustomerApoplexy customerApoplexy;

	private CustomerDiagnose noseAppDiagnose;
	private List<CustomerDiagnose> nosePreDiagnose;
	private CustomerDiagnose earAppDiagnose;
	private List<CustomerDiagnose> earPreDiagnose;
	private CustomerDiagnose throatAppDiagnose;
	private List<CustomerDiagnose> throatPreDiagnose;
	private CustomerDiagnose reInspAppDiagnose;
	private List<CustomerDiagnose> reInspPreDiagnose;
	private CustomerDiagnose preDiagnose;
	private CustomerDiagnose skinAppDiagnose;
	private List<CustomerDiagnose> skinPreDiagnose;
	private CustomerDiagnose hematoAppDiagnose;
	private List<CustomerDiagnose> hematoPreDiagnose;
	private CustomerDiagnose cordiologyAppDiagnose;
	private List<CustomerDiagnose> cardiologyPreDiagnose;
	private CustomerDiagnose kidneyAppDiagnose;
	private List<CustomerDiagnose> kidneyPreDiagnose;
	private CustomerDiagnose lungAppDiagnose;
	private List<CustomerDiagnose> lungPreDiagnose;
	private CustomerDiagnose defaultAppDiagnose;
	private List<CustomerDiagnose> defaultPreDiagnose;
	private CustomerDiagnose eyeAppDiagnose;
	private List<CustomerDiagnose> eyePreDiagnose;
	private CustomerDiagnose traditionAppDiagnose;
	private List<CustomerDiagnose> traditionPreDiagnose;
	private CustomerDiagnose liverAppDiagnose;
	private List<CustomerDiagnose> liverPreDiagnose;
	private CustomerDiagnose stomachAppDiagnose;
	private List<CustomerDiagnose> stomachPreDiagnose;
	private CustomerDiagnose colonAppDiagnose;
	private List<CustomerDiagnose> colonPreDiagnose;
	private CustomerDiagnose pancreasAppDiagnose;
	private List<CustomerDiagnose> pancreasPreDiagnose;
	private CustomerDiagnose endocrineAppDiagnose;
	private List<CustomerDiagnose> endocrinePreDiagnose;
	private CustomerDiagnose thryoidAppDiagnose;
	private List<CustomerDiagnose> thryoidPreDiagnose;
	private CustomerDiagnose diabetesAppDiagnose;
	private List<CustomerDiagnose> diabetesPreDiagnose;
	private CustomerDiagnose rehabilitationAppDiagnose;
	private List<CustomerDiagnose> rehabilitationPreDiagnose;
	private CustomerDiagnose jaundiceAppDiagnose;
	private List<CustomerDiagnose> jaundicePreDiagnose;
	private CustomerDiagnose rachitisAppDiagnose;
	private List<CustomerDiagnose> rachitisPreDiagnose;
	private CustomerDiagnose diarrhoeaAppDiagnose;
	private List<CustomerDiagnose> diarrhoeaPreDiagnose;
	private CustomerDiagnose pediatricInfectionAppDiagnose;
	private List<CustomerDiagnose> pediatricInfectionPreDiagnose;
	private CustomerDiagnose tubeInflammationAppDiagnose;
	private List<CustomerDiagnose> tubeInflammationPreDiagnose;
	private CustomerDiagnose pediatricsOtherPainAppDiagnose;
	private List<CustomerDiagnose> pediatricsOtherPainPreDiagnose;
	private CustomerDiagnose headacheAppDiagnose;
	private List<CustomerDiagnose> headachePreDiagnose;
	private CustomerDiagnose backacheAppDiagnose;
	private List<CustomerDiagnose> backachePreDiagnose;
	private CustomerDiagnose epilepsyAppDiagnose;
	private List<CustomerDiagnose> epilepsyPreDiagnose;
	private CustomerDiagnose apoplexyAppDiagnose;
	private List<CustomerDiagnose> apoplexyPreDiagnose;

	private CustomerPastHistory currentPastHistory;
	private InspectionForm formNose;
	private InspectionForm formEar;
	private InspectionForm formThroat;
	private InspectionForm formSkin;
	private InspectionForm formLung;
	private InspectionForm formDefault;
	private InspectionForm formRehabilitation;
	private InspectionForm formHemato;
	private InspectionForm formCardiology;
	private InspectionForm formKidney;
	private InspectionForm formEye;
	private InspectionForm formTradition;
	private InspectionForm formReInspection;
	private InspectionForm formStomach;
	private InspectionForm formLiver;
	private InspectionForm formPancreas;
	private InspectionForm formColon;
	private InspectionForm formEndocrine;
	private InspectionForm formThryoid;
	private InspectionForm formDiabetes;
	private InspectionForm formJaundice;
	private InspectionForm formRachitis;
	private InspectionForm formDiarrhoea;
	private InspectionForm formPediatricInfection;
	private InspectionForm formTubeInflammation;
	private InspectionForm formPediatricsOtherPain;
	private InspectionForm formHeadache;
	private InspectionForm formBackache;
	private InspectionForm formEpilepsy;
	private InspectionForm formApoplexy;
	private CustomerDefaultInspection customerDefaultInspection;
	private CustomerReInspection customerReInspection;
	private CustomerSkin customerSkin;
	private CustomerHematology customerHematology;
	private CustomerTradition customerTradition;
	private CustomerKidney customerKidney;
	private CustomerRehabilitation customerRehabilitation;
	private CustomerEye customerEye;
	private Gt15Pain gt15Pain;
	private String attachmentName = "";
	// EndRegion

	// Region - Filter
	private Date currentDate;

	private List<String> selectedFilter;

	private Customer selectedCustomer;
	private Employee currentEmployee;
	private ExaminationType chosenExaType;
	private Examination chosenExamination;
	private XrayType chosenXrayType;
	private SurgeryType chosenSurgeryType;
	private Xray chosenXray;
	private Medicine chosenMedicine;
	private BigDecimal selectedTreatmentTypePkId;
	private BigDecimal selectedSurgeryTypePkId;
	private String filterTreatmentName;
	private String filterSurgeryName;
	private String chartData;

	private String subOrgaTypes;

	private boolean hasUldegdel = true;
	private boolean selectCustomer;
	private boolean emr = true;
	private boolean ocs = false;
	private boolean hp = false;
	private boolean pre = true;
	private boolean myInpsection = false;
	private boolean sigNatureSelectCheck = true;
	private boolean usedMedicine = false;
	private boolean painBooleanCheck = true;
	private boolean painEarSelectBooleanCheck;
	private boolean checkUpBooleanCheck = true;
	private boolean cBooleanCheck = true;
	private boolean rateSelectBoolean = true;
	private boolean diagnoseBooleanSelectCheck = true;
	private boolean shcudeluBooleanSelectCheck = true;
	private boolean generalInspection;
	private boolean inspectionSelectBoolean;
	private boolean checkUpSelectBoolean;
	private boolean adviceGeneralBoolean;
	private boolean defaultPreDiagnoseSelectBoolan;

	private boolean mediaBooleanCheck;
	private boolean checkUpBooleanCheckBox;
	private boolean hematoDiagnoseSelectBoolean;
	private boolean adviceSelectBoolean;

	private int countYet;
	private int countDone;
	private int tempSave;
	private int countRepeat;
	private int filterProblemList;
	private int filterProblemListMonth = 1;
	private int countpain;
	private int guestComecount;
	private int guestComeAwayCount;
	private List<Integer> listInt;

	private Date orderDate;
	private Date filterDate1;
	private Date filterDate2;
	private Date beginDate;
	private Date endDate;

	private String filterKey = "";
	private String diagnoseFilterKey = "";
	private String imageType = "";
	private String filterKey1 = "";
	private String inspectionPassword = "";
	private int intValue = -1;
	private String str;
	private String guestsearch;
	private String inpectionTypeString;
	private String pediatricsType;

	private List<Organization> organizations;
	private List<InspectionDetail> detailsList;
	private List<InspectionDtl> dtlList;
	private List<Examination> examinations2List;
	private List<InspectionForm> listInspectionForms;
	private List<CustomerAttachment> lCustomerAttachments;
	private List<Treatment> treatmentsList;
	private List<Xray> xrays2List;
	private List<Surgery> surgeries2List;
	private List<InspectionDetail> listInspectionDetails;
	private List<CustomerDiagnose> listCustomerDiagnosesPrint;

	private BigDecimal conditionalPrescriptionOrgPkId;
	private List<SubOrganizationType> organizationTypes;
	private Date dateFilterBy;
	private JSONObject jsonThroat;
	private JSONObject jsonNose;
	private JSONObject jsonEar;
	private JSONObject jsonLung;
	private JSONObject jsonSkin;
	private JSONObject jsonHematoloty;
	private JSONObject jsonKidney;
	private JSONObject jsonCardiology;
	private JSONObject jsonEye;
	private JSONObject jsonEndocrine;
	private JSONObject jsonThryoid;
	private JSONObject jsonDiabetes;
	private JSONObject jsonObject1;
	private JSONObject jsonObject2;
	private JSONObject jsonObject3;
	private JSONObject jsonObject4;
	private JSONObject jsonRehabilitation;
	private JSONObject jsonJaundice;
	private JSONObject jsonRachitis;
	private JSONObject jsonDiarrhoea;
	private JSONObject jsonPediatricInfection;
	private JSONObject jsonTubeInflammation;
	private JSONObject jsonPediatricsOtherPain;
	private JSONObject jsonHeadache;
	private JSONObject jsonBackache;
	private JSONObject jsonEpilepsy;
	private JSONObject jsonApoplexy;
	private JSONArray jsonPain;
	private JSONArray jsonCheckUp;
	private JSONArray jsonq;

	// EndRegion

	// Region - Functions

	public void fillXrayRequest() {
		getXrayRequests();
		try {
			xrayRequests = logicXray.getXrayRequestByCustomerPkId(selectedCustomer.getPkId());
			RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
			context.update("form:tableXrayInfo");
			context.update("form:xrayInfoUpdate");
			context.update("xrayInfoUpdate");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public String confirmEmployeeRequest() {
		String ret = "doctor_list";
		try {
			if (getCurrentEmployeeRequest().getSaveMood()==0) throw  new Exception("no EMR");
			if(getCurrentInspection().getEmployeeInspectionType()==-1) throw new Exception("no inspectionType");
			getCurrentEmployeeRequest().setMood(2);

			if (inspectionPassword.isEmpty())
				throw new Exception("empty");
			if (infoLogic.checkInspectionPassword(userSessionController.getLoggedInfo().getEmployeePkId(),
					inspectionPassword)) {
				logicInspection.saveEmployeeRequest(currentEmployeeRequest);
				userSessionController.showMessage(99);
			} else
				throw new Exception("password");
		} catch (Exception e) {
			if (e.getMessage().equals("password"))
				userSessionController.showErrorMessage("Нууц үг буруу байна.");
			if (e.getMessage().equals("empty"))
				userSessionController.showWarningMessage("Нууц үг бөглөнө үү.");
			if (e.getMessage().equals("no inspectionType")) {
				userSessionController.showWarningMessage("Үзлэгийн төрлөө сонгоно уу !!!");
			}
			if (e.getMessage().equals("no EMR")) {
				userSessionController.showErrorMessage("EMR хадгалагдаагүй байна");
			}
			e.printStackTrace();
			ret = "";
		}

		return ret;
	}

	public void savePastHistory() {
		try {
			currentPastHistory.setAllergyCount(currentPastHistory.getTmpAllergyCount());
			currentPastHistory.setTreatmentHistoryCount(currentPastHistory.getTmpTreatmentHistoryCount());
			currentPastHistory.setTravelCount(currentPastHistory.getTmpTravelCount());
			currentPastHistory.setLifeStyleCount(currentPastHistory.getTmpLifeStyleCount());
			currentPastHistory.setLifeConditionCount(currentPastHistory.getTmpLifeConditionCount());
			currentPastHistory.setVaccineCount(currentPastHistory.getTmpVaccineCount());
			currentPastHistory.setTravelCount(currentPastHistory.getTmpTravelCount());
			currentPastHistory.setMedicineUsageCount(currentPastHistory.getTmpMedicineUsageCount());
			currentPastHistory.setFamilyDiseaseCount(currentPastHistory.getTmpFamilyDiseaseCount());
			currentPastHistory.setCustomerPkId(selectedCustomer.getPkId());
			logicInspection.saveCustomerPastHistory(currentPastHistory, userSessionController.getLoggedInfo());
			RequestContext.getCurrentInstance().update("form:pastHistory");
			RequestContext.getCurrentInstance().update("form:insp-news");
			userSessionController.showMessage(99);
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Алдаа гарлаа.");
		}
	}

	public void saveMatter() {
		try {
			if (customerMatter.getMatter() != null && customerMatter.getMatter().isEmpty())
				throw new Exception("empty");
			customerMatter.setCustomerPkId(selectedCustomer.getPkId());
			customerMatter.setEmployeePkId(currentEmployee.getPkId());
			customerMatter.setStatus(Tool.ADDED);
			logicInspection.saveCustomerMatter(customerMatter, userSessionController.getLoggedInfo());
			customerMatter = new CustomerMatter();
			userSessionController.showMessage(99);
		} catch (Exception e) {
			if (e.getMessage().equals("empty"))
				userSessionController.showWarningMessage("Асуудлаа бичнэ үү");
			else {
				e.printStackTrace();
				userSessionController.showErrorMessage("Алдаа гарлаа.");
			}
		}
	}

	public void deleteFile(List<CustomerAttachment> list, CustomerAttachment attachment) {
		System.out.println("SIZE HERE ========>" + list.size());
		File file = new File(attachment.getFileName());
		list.remove(attachment);
		if (file.isFile())
			System.out.println("TRUE PLEASE");
		file.delete();
	}

	public void setFileName() {
		attachment.setDescription(attachmentName);
	}

	public void getEmrExaminationData() {
		getEmrExaminations();
		try {
			emrExaminations = examLogic.getEmrExaminationList(getSelectedCustomer().getPkId());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:emrExaminations");
	}

	public void upload(FileUploadEvent event) {
		try {

			CustomerAttachment att = new CustomerAttachment();
			att.setPkId(Tools.newPkId());
			att.setAttachmentType(imageType);
			att.setFileName(buildAttachmentFileName(att.getPkId().toString(), event.getFile().getFileName()));
			att.setStatus(Tool.ADDED);
			OutputStream out;
			InputStream in = event.getFile().getInputstream();

			out = new FileOutputStream(new File(att.getFileName()));
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();
			if ("reinsp".equals(imageType)) {
				getListReAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:reinspection:galleria");
				// RequestContext.getCurrentInstance().execute("makeZoomable()");
			} else if ("nose".equals(imageType)) {
				getListNoseAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:nose-accordion");
			} else if ("ear".equals(imageType)) {
				getListEarAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:ear-accordion");
			} else if ("throat".equals(imageType)) {
				getListThroatAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:throat-accordion");
			} else if ("skin".equals(imageType)) {
				getListSkinAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:skin");
			} else if ("lung".equals(imageType)) {
				getListLungAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:ww");
				// ww = lung
			} else if ("hemato".equals(imageType)) {
				getListHematoAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:hemato");
			} else if ("cardiology".equals(imageType)) {
				getListCardioAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:cardino");
			} else if ("kidney".equals(imageType)) {
				getListkidneyAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:kidney");
			} else if ("eye".equals(imageType)) {
				getListEyeAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:eye");
			} else if ("tradition".equals(imageType)) {
				getListTradtionAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:tradition");
			} else if ("endocrine".equals(imageType)) {
				getListEndocrineAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:endocrine-accordion");
			} else if ("thryoid".equals(imageType)) {
				getListThryoidAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:thryoid-accordion");
			} else if ("diabetes".equals(imageType)) {
				getListDiabetesAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:diabetes-accordion");
			} else if ("stomach".equals(imageType)) {
				getListAttachment1().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:stomach");
			} else if ("liver".equals(imageType)) {
				getListAttachment2().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:liver");
			} else if ("pancreas".equals(imageType)) {
				getListAttachment3().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:pancreas");
			} else if ("colon".equals(imageType)) {
				getListAttachment4().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:colon");
			} else if ("rehabilitation".equals(imageType)) {
				getListRehabilitationAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:rehabilitation");
			} else if ("jaundice".equals(imageType)) {
				getListJaundiceAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:jaundice");
			} else if ("rachitis".equals(imageType)) {
				getListRachitisAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:rachitis");
			} else if ("diarrhoea".equals(imageType)) {
				getListDiarrhoeaAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:diarrhoea");
			} else if ("pediatricInfection".equals(imageType)) {
				getListPediatricInfectionAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:pediatricInfection");
			} else if ("tubeInflammation".equals(imageType)) {
				getListTubeInflammationAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:tubeInflammation");
			} else if ("otherPain".equals(imageType)) {
				getListPediatricsOtherPainAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:otherPain");
		 	} else if ("headache".equals(imageType)) {
				getListHeadacheAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:headache");
			} else if ("backache".equals(imageType)) {
				getListBackacheAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:backache");
			} else if ("epilepsy".equals(imageType)) {
				getListEpilepsyAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:epilepsy");
			} else if ("apoplexy".equals(imageType)) {
				getListApoplexyAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:apoplexy");
			} else if ("default".equals(imageType)) {
				getListDefaultAttachment().add(att);
				RequestContext.getCurrentInstance().update("form:inspection:default");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public StreamedContent getDynamicImage(int imageId1) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (imageId1 == -1)
			return getStreamedImage(getListReAttachment().get(Integer.parseInt(imageId)).getFileName());
		return getStreamedImage(getListReAttachment().get(imageId1).getFileName());
	}

	public StreamedContent getDynamicNoseImage(int imageId1, List<CustomerAttachment> listAttachment) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		if (imageId1 == -1)
			return getStreamedImage(listAttachment.get(Integer.parseInt(imageId)).getFileName());
		return getStreamedImage(listAttachment.get(imageId1).getFileName());
		// return
		// getStreamedImage(getListNoseAttachment().get(Integer.parseInt(imageId)).getFileName());
		// return
		// getStreamedImage(getListNoseAttachment().get(imageId1).getFileName());
	}

	public StreamedContent getDetailDynamicImage(int detailId1, int imageId1) {
		String imageId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		String detailId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("detail_id");
		System.out.println("DETAIL ID1: " + detailId1 + "   DETAIL ID: " + detailId + "   ImageId1:" + imageId1
				+ "  ImageId:" + imageId);
		if (imageId1 == -1 || detailId1 == -1)
			return getStreamedImage(inspectionDetails.get(Integer.parseInt(detailId)).getCustomerAttachment()
					.get(Integer.parseInt(imageId)).getFileName());
		return getStreamedImage(inspectionDetails.get(detailId1).getCustomerAttachment().get(imageId1).getFileName());
	}

	public StreamedContent getListDetialDenamicImage(int detialId, int imageId) {
		String imageIds = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("image_id");
		String detailid = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("detail_id");
		if (imageId == -1 || detialId == -1) {
			return getStreamedImage(listInspectionDetails.get(Integer.parseInt(detailid)).getCustomerAttachment()
					.get(Integer.parseInt(imageIds)).getFileName());
		}
		return getStreamedImage(listInspectionDetails.get(detialId).getCustomerAttachment().get(imageId).getFileName());
	}

	public StreamedContent getStreamedImage(String fileName) {
		StreamedContent content = null;
		System.out.println("FILE NAME ======> " + fileName);
		try {
			File imageFile = new File(fileName);
			FileInputStream in = new FileInputStream(imageFile);
			content = new CustomDefaultStreamedContent(in, "image/" + getExtension(fileName));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;
	}

	private String getExtension(String fileName) {
		for (int i = 0; i < fileName.length(); i++) {
			if (".".equals(fileName.substring(i, i + 1))
					&& !fileName.substring(i + 1, fileName.length()).contains(".")) {
				return fileName.substring(i + 1, fileName.length());
			}
		}
		return "";
	}

	private String buildAttachmentFileName(String pkId, String fileName) {
		String path = applicationController.getInspectionImagePath() + imageType + "/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}

		return path + pkId + "." + FilenameUtils.getExtension(fileName);
	}

	public void filterChanged() {
		for (String str : selectedFilter) {
			System.out.println("String here ===============> " + str);
		}
	}

	public void sortTreatmentByType(BigDecimal treatmentTypePkId) {
		try {
			ttt = logicTreatment.getTreatments(userSessionController.getLoggedInfo(), treatmentTypePkId);

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void choseTreatment() {
		getChosenTreatments().add(getCursorTreatment());
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		context.update("form:treatmentTable1");

	}

	public void sortTreatmentTypeBySot(BigDecimal sotPkId) {
		getTreatmentTypes().clear();
		getTtt().clear();
		try {
			setTreatmentTypes(
					logicTreatment.getTreatmentTypeBySot(userSessionController.getLoggedInfo(), sotPkId, sotPkId));

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
	}

	public void newInspection() {
		currentInspection = new Inspection();
	}

	public void filterByToday() {
		filterDate1 = new Date();
		filterDate2 = new Date();
		refreshCustomerRequests();
	}

	@SuppressWarnings("deprecation")
	public void refreshCustomerRequests() {
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		if (filterDate2.before(filterDate1)) {
			filterDate2.setMonth(filterDate1.getMonth() + 1);
			filterDate2.setDate(filterDate1.getDate() + 1);
			userSessionController.showErrorMessage("Эхлэх өдөр төгсөх өдрөөс хойно байна!");

			context.update("form:endDate");
		} else {
			int status = 0;
			if (filterKey.equals("all"))
				status = 0;
			else if (filterKey.equals("Үзлэгт орсон"))
				status = 1;
			else if (filterKey.equals("Үзлэгт орох"))
				status = 2;
			else if (filterKey.equals("Түр хадгалсан"))
				status = 3;
			else if (filterKey.equals("Дахин үзлэг"))
				status = 4;
			int value = -1;
			if (str.equals("all")) {
				value = -1;
			} else if (str.equals("Өвчний анхан утга")) {
				value = 0;
			} else if (str.equals("Өвчний учир  давтан")) {
				value = 1;
			} else if (str.equals("Урьдчилан сэргийлэх")) {
				value = 2;
			} else if (str.equals("Гэрийн  хяналт")) {
				value = 3;
			} else if (str.equals("Диспенсерийн  хяналт")) {
				value = 4;
			}
			int intGuest = -1;
			if (guestsearch.equals("all")) {
				intGuest = -1;
			} else if (guestsearch.equals("guest")) {
				intGuest = 1;
			} else if (guestsearch.equals("away")) {
				intGuest = 0;
			}
			try {
				filterDate2.setHours(23);
				filterDate2.setMinutes(59);
				getCustomerRequests();
				customerRequests = logicCustomer.getEmployeeRequests(userSessionController.getLoggedInfo(), filterDate1,
						filterDate2, status, getFilterKey1(), value, intGuest);

			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());

			}
			context.update(":form:doctorInspection");
		}
	}

	public String columnDivide(int index1) {
		index1++;
		if (index1 % 3 == 0)
			return "</td></tr><tr><td>";
		return "";
	}

	public String getRegisterPrint() {
		try {
			if (infoLogic.checkInspectionPassword(getUserSessionController().getLoggedInfo().getEmployee().getPkId(),
					inspectionPassword)) {
				jsonNose = toJson(getCustomerNose());
				jsonLung = toJson(getCustomerLung());
				jsonEar = toJson(getCustomerEar());
				jsonThroat = toJson(getCustomerThroat());
				jsonSkin = toJson(getCustomerSkin());
				jsonHematoloty = toJson(getCustomerHematology());
				jsonKidney = toJson(getCustomerKidney());
				jsonCardiology = toJson(getCustomerCardiology());
				jsonEndocrine = toJson(getCustomerEndocrine());
				jsonThryoid = toJson(getCustomerThryoid());
				jsonDiabetes = toJson(getCustomerDiabetes());
				jsonEye = toJson(getCustomerEye());
				jsonObject1 = toJson(getCustomerStomach());
				jsonObject2 = toJson(getCustomerLiver());
				jsonObject3 = toJson(getCustomerPancreas());
				jsonObject4 = toJson(getCustomerColon());
				jsonRehabilitation = toJson(getCustomerRehabilitation());
				jsonJaundice = toJson(getCustomerJaundice());
				jsonRachitis = toJson(getCustomerRachitis());
				jsonDiarrhoea = toJson(getCustomerDiarrhoea());
				jsonPediatricInfection = toJson(getCustomerPediatricInfection());
				jsonTubeInflammation = toJson(getCustomerTubeInflammation());
				jsonPediatricsOtherPain = toJson(getCustomerPediatricsOtherPain());
				jsonHeadache = toJson(getCustomerHeadache());
				jsonBackache = toJson(getCustomerBackache());
				jsonEpilepsy = toJson(getCustomerEpilepsy());
				jsonApoplexy = toJson(getCustomerApoplexy());
				try {
					if (getCurrentEmployeeRequest().getReInspection() == 1) {
						organizations = logicInspection.getOrganization(getSelectedCustomer().getOrganizationPkId());
						inpectionTypeString = "repeatInspection.xhtml";

					} else {
						if (getCurrentEmployeeRequest().getReInspection() == 0) {
							organizations = logicInspection
									.getOrganization(getSelectedCustomer().getOrganizationPkId());
							subOrgaTypes = logicInspection.getSubOrgaTypePkId(
									getUserSessionController().getLoggedInfo().getSuborganizationPkId());
							if (Tool.EARNOSETHROAT.equals(subOrgaTypes)) {
								inpectionTypeString = "registerEarPrint.xhtml";
								System.out.println(subOrgaTypes);
							} else if (Tool.SKIN.equals(subOrgaTypes)) {
								inpectionTypeString = "registerSkinPrint.xhtml";
							} else if (Tool.LUNG.equals(subOrgaTypes)) {
								inpectionTypeString = "registerLungPrinter.xhtml";
							} else if (Tool.HEMATOLOGY.equals(subOrgaTypes)) {
								inpectionTypeString = "registerHematologyPrint.xhtml";
							} else if (Tool.KIDNEY.equals(subOrgaTypes)) {
								inpectionTypeString = "registerKidneyPrint.xhtml";
							} else if (Tool.CARDIOLOGY.equals(subOrgaTypes)) {
								inpectionTypeString = "registerCardiologyPrint.xhtml";
							} else if (Tool.EYE.equals(subOrgaTypes)) {
								inpectionTypeString = "registerEyePrint.xhtml";
							} else if (Tool.GASTRO.equals(subOrgaTypes)) {
								inpectionTypeString = "gastroprint.xhtml";
							} else if (Tool.ENDOCRINE.equals(subOrgaTypes)) {
								inpectionTypeString = "registerEndocrinePrint.xhtml";
							} else if (Tool.REHABILITATION.equals(subOrgaTypes)) {
								inpectionTypeString = "rehabilitationPrint.xhtml";
							} else if (Tool.PEDIATRICS.equals(subOrgaTypes)) {
								inpectionTypeString = "pediatricsPrint.xhtml";
							} else if (Tool.NERVOUS.equals(subOrgaTypes)) {
								inpectionTypeString = "nervousPrint.xhtml";
							} else if (Tool.DEFAULT.equals(subOrgaTypes))
								inpectionTypeString = "registerGeneralPrint.xhtml";
						}
					}

				} catch (Exception e) {
					getUserSessionController().showErrorMessage("eeroror" + e.getMessage());
				}
			} else if (inspectionPassword == null || inspectionPassword.isEmpty()) {
				getUserSessionController().showErrorMessage("Баталгаажуулах код оруулаагүй ");
			} else {
				getJsonNose().clear();
				getJsonEar().clear();
				getJsonThroat().clear();
				getJsonSkin().clear();
				getJsonLung().clear();
				getJsonEndocrine().clear();
				getJsonThryoid().clear();
				getJsonDiabetes().clear();
				getJsonJaundice().clear();
				getJsonRachitis().clear();
				getJsonDiarrhoea().clear();
				getJsonPediatricInfection().clear();
				getJsonTubeInflammation().clear();
				getJsonPediatricsOtherPain().clear();
				getJsonHeadache().clear();
				getJsonBackache().clear();
				getJsonEpilepsy().clear();
				getJsonApoplexy().clear();
				getOrganizations().clear();
				getUserSessionController().showErrorMessage("Баталгаажуулах код буруу !");
			}
		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
		}

		return inpectionTypeString;

	}

	public String subNameReInspectionString() {
		String reInspectionSubName = "";
		String subName = "";
		try {
			subName = logicInspection
					.getSubOrgaTypePkId(getUserSessionController().getLoggedInfo().getSuborganizationPkId());
			if (Tool.EARNOSETHROAT.equals(subName)) {
				reInspectionSubName = "Чих Хамар Хоолой";
			} else if (Tool.SKIN.equals(subName))
				reInspectionSubName = "Арьс Судлал";
			else if (Tool.LUNG.equals(subName))
				reInspectionSubName = "Уушги  Судлал";
			else
				reInspectionSubName = "Давтан үзлэг";
		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
		}
		return reInspectionSubName;
	}

	public String modifiedInspection(Employee e, Customer c, EmployeeRequest er) {
		cleanOCS();
		currentEmployee = new Employee();
		selectedCustomer = new Customer();
		currentEmployeeRequest = new EmployeeRequest();
		if (currentSubOrganization == null) {
			try {
				currentSubOrganization = logicTwo.getByPkId(SubOrganization.class, e.getSubOrganizationPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		newInspection();
		setCurrentEmployee(e);
		setSelectedCustomer(c);
		setCurrentEmployeeRequest(er);
		userSessionController.setCurrentEmployeePkId(currentEmployee.getPkId());
		userSessionController.setCurrentYear(getOrderDate().getYear() + 1900);
		userSessionController.setCurrentMonth(getOrderDate().getMonth() + 1);
		userSessionController.setCurrentDay(getOrderDate().getDay());
		currentInspection.setEmployeePkId(currentEmployee.getPkId());
		currentInspection.setCustomperPkId(selectedCustomer.getPkId());

		try {
			subOrgaType = logicInspection.getSubOrgaTypePkId(currentEmployee.getSubOrganizationPkId());
			if (Tool.EARNOSETHROAT.equals(subOrgaType)) {
				inspectionType = "doctor_register4";
			} else if (Tool.SKIN.equals(subOrgaType)) {
				inspectionType = "doctor_register2";
			} else if (Tool.LUNG.equals(subOrgaType)) {
				inspectionType = "doctor_register1";
			} else if (Tool.DEFAULT.equals(subOrgaType)) {
				inspectionType = "doctor_register";
			} else if (Tool.ENDO.equals(subOrgaType)) {
				inspectionType = "doctor_register";
			} else if (Tool.HEMATOLOGY.equals(subOrgaType)) {
				inspectionType = "doctor_register6";
			} else if (Tool.CARDIOLOGY.equals(subOrgaType)) {
				inspectionType = "doctor_register7";
			} else if (Tool.KIDNEY.equals(subOrgaType)) {
				inspectionType = "doctor_register8";
			} else if (Tool.EYE.equals(subOrgaType)) {
				inspectionType = "doctor_register9";
			} else if (Tool.TRADITION.equals(subOrgaType)) {
				inspectionType = "doctor_register10";
			} else if (Tool.GASTRO.equals(subOrgaType)) {
				inspectionType = "doctor_register-liver-gall";
			} else if (Tool.REHABILITATION.equals(subOrgaType)) {
				inspectionType = "doctor_rehabilitation";
			} else if (Tool.ENDOCRINE.equals(subOrgaType)) {
				inspectionType = "doctor_register-endocrine";
			} else if (Tool.PEDIATRICS.equals(subOrgaType)) {
				inspectionType = "doctor_register-pediatrics";
			} else if (Tool.NERVOUS.equals(subOrgaType)) {
				inspectionType = "doctor_register-nervous";
			} else {
				inspectionType = "doctor_register";
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		loadData();

		return inspectionType;
	}

	public void loadData() {
		try {
			List<Inspection> list = logicCustomer.getByAnyField(Inspection.class, "requestPkId",
					getCurrentEmployeeRequest().getPkId());
			if (list.size() > 0) {
				setCurrentInspection(list.get(0));
				getCurrentInspection().setStatus(Tool.MODIFIED);
				getListXray().clear();
				getListTreatment().clear();
				getListExamination().clear();
				getListSurgery().clear();
				getListMedicine().clear();
				setListgetCurrentInspectionDtl(getCurrentInspection().getPkId());
				getListDiagnose().clear();
				getEarPreDiagnose().clear();
				getNosePreDiagnose().clear();
				getThroatPreDiagnose().clear();
				getSkinPreDiagnose().clear();
				getRehabilitationPreDiagnose().clear();
				getCardiologyPreDiagnose().clear();
				getKidneyPreDiagnose().clear();
				getEyePreDiagnose().clear();
				getTraditionPreDiagnose().clear();
				getLungPreDiagnose().clear();
				getReInspPreDiagnose().clear();
				getDefaultPreDiagnose().clear();

				// Gastro
				getLiverPreDiagnose().clear();
				getColonPreDiagnose().clear();
				getPancreasPreDiagnose().clear();
				getStomachPreDiagnose().clear();
				// Endocrine
				getEndocrinePreDiagnose().clear();
				getThryoidPreDiagnose().clear();
				getDiabetesPreDiagnose().clear();

				// Pediatrics
				getJaundicePreDiagnose().clear();
				getRachitisPreDiagnose().clear();
				getDiarrhoeaPreDiagnose().clear();
				getPediatricInfectionPreDiagnose().clear();
				getTubeInflammationPreDiagnose().clear();
				getPediatricsOtherPainPreDiagnose().clear();
				// Nervous
				getHeadachePreDiagnose().clear();
				getBackachePreDiagnose().clear();
				getEpilepsyPreDiagnose().clear();
				getApoplexyPreDiagnose().clear();
				getHematoPreDiagnose().clear();

				getListNoseAttachment().clear();
				getListEarAttachment().clear();
				getListSkinAttachment().clear();
				getListLungAttachment().clear();
				getListReAttachment().clear();
				getListHematoAttachment().clear();

				getListCardioAttachment().clear();
				getListkidneyAttachment().clear();
				getListEyeAttachment().clear();
				getListTradtionAttachment().clear();
				getListEndocrineAttachment().clear();
				getListThryoidAttachment().clear();
				getListDiabetesAttachment().clear();
				getListDefaultAttachment().clear();
				getListRehabilitationAttachment().clear();
				preDiagnose = new CustomerDiagnose();

				// Gastro
				setCustomerLiver(new CustomerLiver());
				setCustomerStomach(new CustomerStomach());
				setCustomerPancreas(new CustomerPancreas());
				setCustomerColon(new CustomerColon());
				getListAttachment1().clear();
				getListAttachment2().clear();
				getListAttachment3().clear();
				getListAttachment4().clear();

				// Endocrine
				setCustomerEndocrine(new CustomerEndocrine());
				setCustomerThryoid(new CustomerThryoid());
				setCustomerDiabetes(new CustomerDiabetes());

				// Pediatrics
				setCustomerJaundice(new CustomerJaundice());
				setCustomerRachitis(new CustomerRachitis());
				setCustomerDiarrhoea(new CustomerDiarrhoea());
				setCustomerPediatricInfection(new CustomerPediatricInfection());
				setCustomerTubeInflammation(new CustomerTubeInflammation());
				setCustomerPediatricsOtherPain(new CustomerPediatricsOther());
				getListJaundiceAttachment().clear();
				getListRachitisAttachment().clear();
				getListDiarrhoeaAttachment().clear();
				getListPediatricInfectionAttachment().clear();
				getListTubeInflammationAttachment().clear();
				getListPediatricsOtherPainAttachment().clear();
				// Nervous
				setCustomerHeadache(new CustomerHeadache());
				setCustomerBackache(new CustomerBackache());
				setCustomerEpilepsy(new CustomerEpilepsy());
				setCustomerApoplexy(new CustomerApoplexy());
				getListHeadacheAttachment().clear();
				getListBackacheAttachment().clear();
				getListEpilepsyAttachment().clear();
				getListApoplexyAttachment().clear();

				setCustomerSkin(new CustomerSkin());
				setCustomerEar(new CustomerEar());
				setCustomerNose(new CustomerNose());
				setCustomerThroat(new CustomerThroat());
				setCustomerLung(new CustomerLung());
				setCustomerHematology(new CustomerHematology());
				setCustomerCardiology(new CustomerCardiology());
				setCustomerKidney(new CustomerKidney());
				setCustomerEye(new CustomerEye());
				setCustomerTradition(new CustomerTradition());
				setCustomerReInspection(new CustomerReInspection());
				setCustomerRehabilitation(new CustomerRehabilitation());
				setCustomerDefaultInspection(new CustomerDefaultInspection());

				listDiagnose = logicInspection.getCustomerDiagnose(getCurrentInspection().getPkId());
				for (CustomerDiagnose cd : listDiagnose) {
					if ("ear".equals(cd.getOrgan())) {
						earPreDiagnose.add(cd);
					} else if ("nose".equals(cd.getOrgan())) {
						nosePreDiagnose.add(cd);
					} else if ("throat".equals(cd.getOrgan())) {
						throatPreDiagnose.add(cd);
					} else if ("skin".equals(cd.getOrgan())) {
						skinPreDiagnose.add(cd);
					} else if ("lung".equals(cd.getOrgan())) {
						lungPreDiagnose.add(cd);
					} else if ("cardiology".equals(cd.getOrgan())) {
						cardiologyPreDiagnose.add(cd);
					} else if ("kidney".equals(cd.getOrgan())) {
						kidneyPreDiagnose.add(cd);
					} else if ("hemato".equals(cd.getOrgan())) {
						hematoPreDiagnose.add(cd);
					} else if ("reinsp".equals(cd.getOrgan())) {
						reInspPreDiagnose.add(cd);
					} else if ("eye".equals(cd.getOrgan())) {
						eyePreDiagnose.add(cd);
					} else if ("tradition".equals(cd.getOrgan())) {
						traditionPreDiagnose.add(cd);
					} else if ("liver".equals(cd.getOrgan())) {
						liverPreDiagnose.add(cd);
					} else if ("colon".equals(cd.getOrgan())) {
						colonPreDiagnose.add(cd);
					} else if ("pancreas".equals(cd.getOrgan())) {
						pancreasPreDiagnose.add(cd);
					} else if ("stomach".equals(cd.getOrgan())) {
						stomachPreDiagnose.add(cd);
					} else if ("endocrine".equals(cd.getOrgan())) {
						endocrinePreDiagnose.add(cd);
					} else if ("thryoid".equals(cd.getOrgan())) {
						thryoidPreDiagnose.add(cd);
					} else if ("diabetes".equals(cd.getOrgan())) {
						diabetesPreDiagnose.add(cd);
					} else if ("rehabilitation".equals(cd.getOrgan())) {
						rehabilitationPreDiagnose.add(cd);
					} else if ("jaundice".equals(cd.getOrgan())) {
						jaundicePreDiagnose.add(cd);
					} else if ("rachitis".equals(cd.getOrgan())) {
						rachitisPreDiagnose.add(cd);
					} else if ("diarrhoea".equals(cd.getOrgan())) {
						diarrhoeaPreDiagnose.add(cd);
					} else if ("pediatricInfection".equals(cd.getOrgan())) {
						pediatricInfectionPreDiagnose.add(cd);
					} else if ("tubeInflammation".equals(cd.getOrgan())) {
						tubeInflammationPreDiagnose.add(cd);
					} else if ("otherPaint".equals(cd.getOrgan())) {
						pediatricsOtherPainPreDiagnose.add(cd);
					} else if ("headache".equals(cd.getOrgan())) {
						headachePreDiagnose.add(cd);
					} else if ("backache".equals(cd.getOrgan())) {
						backachePreDiagnose.add(cd);
					} else if ("epilepsy".equals(cd.getOrgan())) {
						epilepsyPreDiagnose.add(cd);
					} else if ("apoplexy".equals(cd.getOrgan())) {
						apoplexyPreDiagnose.add(cd);
					} else if ("default".equals(cd.getOrgan())) {
						defaultPreDiagnose.add(cd);
					}

				}
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:inspection:nose-accordion:dubu");
				context.update("form:inspection:throat-accordion:dubu");
				context.update("form:inspection:ear-accordion:dubu");
				context.update("form:inspection:ww:dubu");
				context.update("form:inspection:skin:dubu");
				context.update("form:inspection:default:dubu");
				context.update("form:reinspection:riDiagnoseSection");
				context.update("form:inspection:cardino:dubu");
				context.update("form:inspection:kidney:dubu");
				context.update("form:inspection:hemato:dubu");
				context.update("form:inspection:eye:dubu");
				context.update("form:inspection:tradition:dubu");
				context.update("form:inspection:endocrine-accordion:dubu");
				context.update("form:inspection:thryoid-accordion:dubu");
				context.update("form:inspection:diabetes-accordion:dubu");
				context.update("form:inspection:rehabilitation:dubu");
				context.update("form:inspection:jaundice:dubu");
				context.update("form:inspection:rachitis:dubu");
				context.update("form:inspection:diarrhoea:dubu");
				context.update("form:inspection:pediatricInfection:dubu");
				context.update("form:inspection:tubeInflammation:dubu");
				context.update("form:inspection:otherPain:dubu");
				context.update("form:inspection:headache:dubu");
				context.update("form:inspection:backache:dubu");
				context.update("form:inspection:epilepsy:dubu");
				context.update("form:inspection:apoplexy:dubu");

				// Davtan uzleg esehiig shalgaj bn
				if (currentEmployeeRequest.getReInspection() == 0) {

					if (Tool.EARNOSETHROAT.equals(subOrgaType)) {
						formEar = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "ear");
						formNose = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "nose");
						formThroat = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"throat");
						listEarAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "ear");
						listNoseAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "nose");
						listThroatAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "throat");
						toCustomerObject(formEar, getCustomerEar());
						toCustomerObject(formThroat, getCustomerThroat());
						toCustomerObject(formNose, getCustomerNose());
					} else if (Tool.LUNG.equals(subOrgaType)) {
						listLungAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "lung");
						formLung = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "lung");
						toCustomerObject(formLung, getCustomerLung());
						context.update("form:inspection:lung");

					} else if (Tool.SKIN.equals(subOrgaType)) {
						listSkinAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "skin");
						formSkin = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "skin");
						toCustomerObject(formSkin, getCustomerSkin());
						context.update("form:inspection:skin");
					} else if (Tool.CARDIOLOGY.equals(subOrgaType)) {
						listCardioAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "cardiology");
						formCardiology = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"cardiology");
						toCustomerObject(formCardiology, getCustomerCardiology());
						context.update("form:inspection:cardino");
					} else if (Tool.HEMATOLOGY.equals(subOrgaType)) {
						listHematoAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "hemato");
						formHemato = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"hemato");
						toCustomerObject(formHemato, getCustomerHematology());
						context.update("form:inspection:hemato");
					} else if (Tool.KIDNEY.equals(subOrgaType)) {
						listkidneyAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "kidney");
						formKidney = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"kidney");
						toCustomerObject(formKidney, getCustomerKidney());
						context.update("form:inspection:kidney");
					} else if (Tool.REHABILITATION.equals(subOrgaType)) {
						listRehabilitationAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "rehabilitation");
						formRehabilitation = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"rehabilitation");
						toCustomerObject(formRehabilitation, getCustomerRehabilitation());
						context.update("form:inspection:rehabilitation");
					} else if (Tool.EYE.equals(subOrgaType)) {
						listEyeAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "eye");
						formEye = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "eye");
						toCustomerObject(formEye, getCustomerEye());
						context.update("form:inspection:eye");
					} else if (Tool.DEFAULT.equals(subOrgaType)) {
						listDefaultAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "default");
						formDefault = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"default");
						toCustomerObject(formDefault, getCustomerDefaultInspection());
						context.update("form:inspection:default");
					} else if (Tool.TRADITION.equals(subOrgaType)) {
						listTradtionAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "tradition");
						formTradition = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"tradition");
						toCustomerObject(formTradition, getCustomerTradition());
						context.update("form:inspection:tradition");
					} else if (Tool.GASTRO.equals(subOrgaType)) {
						formStomach = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"stomach");
						formLiver = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "liver");
						formPancreas = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"pancreas");
						formColon = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(), "colon");
						listAttachment1 = logicInspection.getCustomerAttachmentByType(getCurrentInspection().getPkId(),
								"stomach");
						listAttachment2 = logicInspection.getCustomerAttachmentByType(getCurrentInspection().getPkId(),
								"liver");
						listAttachment3 = logicInspection.getCustomerAttachmentByType(getCurrentInspection().getPkId(),
								"pancreas");
						listAttachment4 = logicInspection.getCustomerAttachmentByType(getCurrentInspection().getPkId(),
								"colon");
						toCustomerObject(formStomach, getCustomerStomach());
						toCustomerObject(formLiver, getCustomerLiver());
						toCustomerObject(formPancreas, getCustomerPancreas());
						toCustomerObject(formColon, getCustomerColon());
						context.update("form:inspection:stomach");
						context.update("form:inspection:liver");
						context.update("form:inspection:pancreas");
						context.update("form:inspection:colon");

					} else if (Tool.ENDOCRINE.equals(subOrgaType)) {
						listEndocrineAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "endocrine");
						listThryoidAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "thryoid");
						listDiabetesAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "diabetes");
						formEndocrine = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"endocrine");
						formThryoid = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"thryoid");
						formDiabetes = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"diabetes");
						toCustomerObject(formEndocrine, getCustomerEndocrine());
						toCustomerObject(formThryoid, getCustomerThryoid());
						toCustomerObject(formDiabetes, getCustomerDiabetes());
						context.update("form:inspection:endocrine-accordion");
						context.update("form:inspection:thryoid-accordion");
						context.update("form:inspection:diabetes-accordion");

					} else if (Tool.PEDIATRICS.equals(subOrgaType)) {
						listJaundiceAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "jaundice");
						listRachitisAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "rachitis");
						listDiarrhoeaAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "diarrhoea");
						listPediatricInfectionAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "pediatricInfection");
						listTubeInflammationAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "tubeInflammation");
						listPediatricsOtherPainAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "otherPain");
						formJaundice = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"jaundice");
						formRachitis = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"rachitis");
						formDiarrhoea = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"diarrhoea");
						formPediatricInfection = logicInspection
								.getInspectionFormByType(getCurrentInspection().getPkId(), "pediatricInfection");
						formTubeInflammation = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"tubeInflammation");
						formPediatricsOtherPain = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"otherPain");
						toCustomerObject(formJaundice, getCustomerJaundice());
						toCustomerObject(formRachitis, getCustomerRachitis());
						toCustomerObject(formDiarrhoea, getCustomerDiarrhoea());
						toCustomerObject(formPediatricInfection, getCustomerPediatricInfection());
						toCustomerObject(formTubeInflammation, getCustomerTubeInflammation());
						toCustomerObject(formPediatricsOtherPain, getCustomerPediatricsOtherPain());
						context.update("form:inspection:jaundice:dubu");
						context.update("form:inspection:rachitis:dubu");
						context.update("form:inspection:diarrhoea:dubu");
						context.update("form:inspection:pediatricInfection:dubu");
						context.update("form:inspection:tubeInflammation:dubu");
						context.update("form:inspection:otherPain:dubu");

					} else if (Tool.NERVOUS.equals(subOrgaType)) {
						listHeadacheAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "headache");
						listBackacheAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "backache");
						listEpilepsyAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "epilepsy");
						listApoplexyAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "apoplexy");
						formHeadache = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"headache");
						formBackache = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"backache");
						formEpilepsy = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"epilepsy");
						formApoplexy = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"apoplexy");
						toCustomerObject(formHeadache, getCustomerHeadache());
						toCustomerObject(formBackache, getCustomerBackache());
						toCustomerObject(formEpilepsy, getCustomerEpilepsy());
						toCustomerObject(formApoplexy, getCustomerApoplexy());
						context.update("form:inspection:headache:dubu");
						context.update("form:inspection:backache:dubu");
						context.update("form:inspection:epilepsy:dubu");
						context.update("form:inspection:apoplexy:dubu");
					} else {
						listDefaultAttachment = logicInspection
								.getCustomerAttachmentByType(getCurrentInspection().getPkId(), "default");
						formDefault = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
								"default");
						toCustomerObject(formDefault, getCustomerDefaultInspection());
						context.update("form:inspection:default");
					}

				} else {
					getCustomerDefaultInspection();
					listReAttachment = logicInspection.getCustomerAttachmentByType(getCurrentInspection().getPkId(),
							"reinsp");
					formReInspection = logicInspection.getInspectionFormByType(getCurrentInspection().getPkId(),
							"reinsp");
					toCustomerObject(formReInspection, getCustomerReInspection());
					context.update("form:reinspection");
				}
			} else {
				newInspectionReg();
				getCurrentInspection().setInspectionType(currentEmployeeRequest.getReInspection());
				reInspPreDiagnose.addAll(logicInspection.getCustomerDiagnoseLast(selectedCustomer.getPkId(),
						userSessionController.getLoggedInfo()));
			}
			getInspectionDetails().clear();
			currentPastHistory = logicInspection.getCustomerPast(selectedCustomer.getPkId());
			if (currentPastHistory == null)
				currentPastHistory = new CustomerPastHistory();
			employeeMemoPublicList = logicInspection.getPublicMemo(selectedCustomer.getPkId());
			employeeMemoList = logicInspection.getPrivateMemo(userSessionController.getLoggedInfo().getEmployeePkId(),
					selectedCustomer.getPkId());
			listConPre = logicConPre.getCps(userSessionController.getLoggedInfo());
			doctorRecipes = logicInspection.getDoctorRecipe(getCurrentEmployee().getPkId());
			subOrganizations = infoLogic.getSubOrganizations();
			employees = infoLogic.getEmployees();
			listProblem = logicInspection.getCustomerProblem(selectedCustomer.getPkId(), filterProblemList,
					filterProblemListMonth);
			getInspectionList();
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа!");
		}
	}

	public void problemList() {
		try {
			getListProblem().clear();
			listProblem.addAll(logicInspection.getCustomerProblem(selectedCustomer.getPkId(), filterProblemList,
					filterProblemListMonth));
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void getFilteredDiagnoses() {
		try {

			Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext()
					.getRequestParameterMap();
			whichDiagnose = params.get("diag");
			whichDiagnoseType = Integer.valueOf(params.get("typ"));
			String value = params.get("val");
			boolean byName = Boolean.valueOf(params.get("byName"));
			if (value.trim().length() > 2) {
				approvedFilteredDiagnose.clear();
				if (byName)
					approvedFilteredDiagnose.addAll(logicInspection.getDiagnoseByName(value));
				else
					approvedFilteredDiagnose.addAll(logicInspection.getDiagnoseById(value));
			} else if (value.trim().length() == 0) {
				approvedFilteredDiagnose.clear();
				approvedFilteredDiagnose.addAll(
						logicInspection.getTopDiagnose(userSessionController.getLoggedInfo().getEmployeePkId()));
			}
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа. \n" + e.getMessage());
		}
	}

	private void newInspectionReg() {
		currentInspection = new Inspection();
		currentInspection.setInspectionStartDate(new Date());
		selectedInspection = new Inspection();
		currentInspection.setStatus(Tool.ADDED);
		selectedInspection.setStatus(Tool.ADDED);
		// Gastro
		getLiverPreDiagnose().clear();
		getStomachPreDiagnose().clear();
		getColonPreDiagnose().clear();
		getPancreasPreDiagnose().clear();
		// Endocrine
		getEndocrinePreDiagnose().clear();
		getThryoidPreDiagnose().clear();
		getDiabetesPreDiagnose().clear();
		// Pediatrics
		getJaundicePreDiagnose().clear();
		getRachitisPreDiagnose().clear();
		getDiarrhoeaPreDiagnose().clear();
		getPediatricInfectionPreDiagnose().clear();
		getTubeInflammationPreDiagnose().clear();
		getPediatricsOtherPainPreDiagnose().clear();
		// Nervous
		getHeadachePreDiagnose().clear();
		getBackachePreDiagnose().clear();
		getEpilepsyPreDiagnose().clear();
		getApoplexyPreDiagnose().clear();
		getListXray().clear();
		getListTreatment().clear();
		getListExamination().clear();
		getListSurgery().clear();
		getListMedicine().clear();
		getListDiagnose().clear();
		getEarPreDiagnose().clear();
		getNosePreDiagnose().clear();
		getThroatPreDiagnose().clear();
		getSkinPreDiagnose().clear();
		getLungPreDiagnose().clear();
		getReInspPreDiagnose().clear();
		getDefaultPreDiagnose().clear();
		getCardiologyPreDiagnose().clear();
		getKidneyPreDiagnose().clear();
		getRehabilitationPreDiagnose().clear();
		getEyePreDiagnose().clear();
		getTraditionPreDiagnose().clear();
		getListDefaultAttachment().clear();
		getListRehabilitationAttachment().clear();
		getListNoseAttachment().clear();
		getListEarAttachment().clear();
		getListSkinAttachment().clear();
		getListLungAttachment().clear();
		getListCardioAttachment().clear();
		getListkidneyAttachment().clear();
		getListEyeAttachment().clear();
		getListTradtionAttachment().clear();
		getListReAttachment().clear();
		setCustomerSkin(new CustomerSkin());
		setCustomerEar(new CustomerEar());
		setCustomerNose(new CustomerNose());
		setCustomerThroat(new CustomerThroat());
		setCustomerLung(new CustomerLung());
		setCustomerReInspection(new CustomerReInspection());
		setCustomerDefaultInspection(new CustomerDefaultInspection());
		setCustomerHematology(new CustomerHematology());
		setCustomerCardiology(new CustomerCardiology());
		setCustomerKidney(new CustomerKidney());
		setCustomerEye(new CustomerEye());
		// Endocrine
		setCustomerEndocrine(new CustomerEndocrine());
		setCustomerThryoid(new CustomerThryoid());
		setCustomerDiabetes(new CustomerDiabetes());
		getListEndocrineAttachment().clear();
		getListThryoidAttachment().clear();
		getListDiabetesAttachment().clear();
		// Gastro
		setCustomerStomach(new CustomerStomach());
		setCustomerLiver(new CustomerLiver());
		setCustomerColon(new CustomerColon());
		setCustomerPancreas(new CustomerPancreas());
		setCustomerRehabilitation(new CustomerRehabilitation());
		getListAttachment1().clear();
		getListAttachment2().clear();
		getListAttachment3().clear();
		getListAttachment4().clear();
		// Pediatrics
		setCustomerJaundice(new CustomerJaundice());
		setCustomerRachitis(new CustomerRachitis());
		setCustomerDiarrhoea(new CustomerDiarrhoea());
		setCustomerPediatricInfection(new CustomerPediatricInfection());
		setCustomerTubeInflammation(new CustomerTubeInflammation());
		setCustomerPediatricsOtherPain(new CustomerPediatricsOther());
		getListJaundiceAttachment().clear();
		getListRachitisAttachment().clear();
		getListDiarrhoeaAttachment().clear();
		getListPediatricInfectionAttachment().clear();
		getListTubeInflammationAttachment().clear();
		getListPediatricsOtherPainAttachment().clear();
		// Nervous
		setCustomerHeadache(new CustomerHeadache());
		setCustomerBackache(new CustomerBackache());
		setCustomerEpilepsy(new CustomerEpilepsy());
		setCustomerApoplexy(new CustomerApoplexy());
		getListHeadacheAttachment().clear();
		getListBackacheAttachment().clear();
		getListEpilepsyAttachment().clear();
		getListApoplexyAttachment().clear();
		getNoseAppDiagnose();
		getEarAppDiagnose();
		getReInspAppDiagnose();
		getLungAppDiagnose();
		getSkinAppDiagnose();
		getDefaultAppDiagnose();
		getCordiologyAppDiagnose();
		getKidneyAppDiagnose();
		getEyeAppDiagnose();
		getReInspAppDiagnose();
		getEndocrineAppDiagnose();
		getThryoidAppDiagnose();
		getDiabetesAppDiagnose();
		getRehabilitationAppDiagnose();
		getJaundiceAppDiagnose();
		getRachitisAppDiagnose();
		getDiarrhoeaAppDiagnose();
		getPediatricInfectionAppDiagnose();
		getTubeInflammationAppDiagnose();
		getPediatricsOtherPainAppDiagnose();

	}

	public void useConPre() {
		try {
			listConPreDtl = logicConPre.getDtls(currentConPrePkId);
			for (ConditionalPrescriptionDtl cpDtl : listConPreDtl) {
				if (cpDtl.getExaminationPkId() != null) {
					InspectionDtl dtl = new InspectionDtl();
					dtl.setType("EXAMINATION");
					dtl.setTypePkId(cpDtl.getExaminationPkId());
					dtl.setName(cpDtl.getExaName());
					dtl.setStatus(Tool.ADDED);
					listExamination.add(dtl);
				} else if (cpDtl.getXrayPkId() != null) {
					InspectionDtl dtl = new InspectionDtl();
					dtl.setType("XRAY");
					dtl.setTypePkId(cpDtl.getXrayPkId());
					dtl.setName(cpDtl.getXrayName());
					dtl.setStatus(Tool.ADDED);
					listXray.add(dtl);
				} else if (cpDtl.getMedicinePkId() != null) {
					CustomerMedicine dtl = new CustomerMedicine();
					dtl.setMedicinePkId(cpDtl.getMedicinePkId());
					dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
					dtl.setRepeatType(0);
					dtl.setRepeatCount(0);
					dtl.setExpireDay(0);
					dtl.setDose("");
					dtl.setStatus(Tool.ADDED);
					listMedicine.add(dtl);
				}
			}

			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:inspection:healing-plan");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectedTreatment() {
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();

		for (Treatment t : getChosenTreatments()) {
			boolean isdupplicat = false;
			for (InspectionDtl id : listTreatment)
				if (id.getTypePkId().compareTo(t.getPkId()) == 0)
					isdupplicat = true;
			if (isdupplicat)
				continue;
			InspectionDtl dtl = new InspectionDtl();
			dtl.setType("TREATMENT");
			dtl.setTypePkId(t.getPkId());
			dtl.setName(t.getName());
			dtl.setId(t.getId());
			dtl.setDayLength(1);
			dtl.setCost(t.getPrice());
			dtl.setStatus(Tool.ADDED);
			listTreatment.add(dtl);
		}

		chosenTreatments.clear();
		context.update("form:inspection:tableTreatment");
		context.update("form:treatmentTable1");
		context.execute("PF('treatmentList').hide();");
	}

	public void removeInspectionDtl() {
		RequestContext context = RequestContext.getCurrentInstance();
		if (currentInspectionDtl != null) {
			if (Tool.INSPECTIONTYPE_EXAMINATION.equals(currentInspectionDtl.getType())) {
				getListExamination().remove(currentInspectionDtl);
				context.update("form:inspection:healing-plan");
			}
			if (Tool.INSPECTIONTYPE_XRAY.equals(currentInspectionDtl.getType())) {
				getListXray().remove(currentInspectionDtl);
				context.update("form:inspection:healing-plan");
			}
			if (Tool.INSPECTIONTYPE_SURGERY.equals(currentInspectionDtl.getType())) {
				getListSurgery().remove(currentInspectionDtl);
				context.update("form:inspection:healing-plan");
			}
			if (Tool.INSPECTIONTYPE_TREATMENT.equals(currentInspectionDtl.getType())) {
				getListTreatment().remove(currentInspectionDtl);
				context.update("form:inspection:healing-plan");
			}

			if (currentExaminationDtl == null) {

			} else {
				getListExamination().remove(currentInspectionDtl);
				// currentInspectionDtl.setStatus(Tool.DELETE);
				context.update("form:inspection:healing-plan");
			}
		} else {
			InspectionDtl cDtl = null;
			for (InspectionDtl dtl : getListExamination()) {
				if (currentExaminationDtl.getExaminationPkId().compareTo(dtl.getTypePkId()) == 0) {
					cDtl = dtl;
				}
			}
			cDtl.getExaminationDtls().remove(currentExaminationDtl);
			if (cDtl.getExaminationDtls().size() < 1) {
				getListExamination().remove(currentInspectionDtl);
				// currentInspectionDtl.setStatus(Tool.DELETE);
			}
		}

		currentExaminationDtl = null;
		listExaminationTmp = null;
		listExaminationTmpTree = null;
		listXrayTmp = null;
		listSurgeryTmp = null;
		listTreatmentTmp = null;
	}

	public void removeMedicine() {
		// if(list)
		if (index < listMedicine.size())
			listMedicine.remove(index);
		// listMedicine.get(index).setStatus(Tool.DELETE);
		listMedicineTmp = null;
	}

	public void removeFromExaTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		exaTempList.remove(index);
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void onDiagnoseSelect(Diagnose selectedDiagnose) {
		RequestContext context = RequestContext.getCurrentInstance();
		if ("nose".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getNosePreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getNosePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			context.update("form:inspection:nose-accordion:dubu");
		} else if ("reinsp".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getReInspPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0) {
				getReInspPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:reinspection");
		} else if ("ear".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getEarPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0) {
				getEarPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:ear-accordion:dubu");
		} else if ("throat".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getThroatPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0) {
				getThroatPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:throat-accordion:dubu");
		} else if ("reinsp".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getReInspPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0) {
				getReInspPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:reinspection");
		} else if ("skin".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getSkinPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				addIfNotExist(getSkinPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:skin:dubu");
		} else if ("default".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getDefaultPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getDefaultPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:default:dubu");
		} else if ("lung".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getLungPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getLungPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:ww:dubu");
		} else if ("hemato".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getHematoPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getHematoPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:hemato:dubu");
		} else if ("cardiology".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getCardiologyPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0) {
				getCardiologyPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:cardino:dubu");
		} else if ("kidney".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getKidneyPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getKidneyPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:kidney:dubu");
		} else if ("eye".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getEyePreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getEyePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:eye:dubu");
		} else if ("tradition".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getTraditionPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getTraditionPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:tradition:dubu");
		} else if ("stomach".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getStomachPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getStomachPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:stomach:dubu");
		} else if ("liver".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getLiverPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getLiverPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:liver:dubu");
		} else if ("pancreas".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getPancreasPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getPancreasPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:pancreas:dubu");
		} else if ("colon".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getColonPreDiagnose(), setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getColonPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:colon:dubu");
		} else if ("endocrine".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getEndocrinePreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getEndocrinePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:endocrine-accordion:dubu");
		} else if ("thryoid".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getThryoidPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getThryoidPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:thryoid-accordion:dubu");
		} else if ("diabetes".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1) {
				addIfNotExist(getDiabetesPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			} else if (whichDiagnoseType == 0) {
				getDiabetesPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			}
			RequestContext.getCurrentInstance().update("form:inspection:diabetes-accordion:dubu");
		} else if ("rehabilitation".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getRehabilitationPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getRehabilitationPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:rehabilitation:dubu");
		} else if ("jaundice".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getJaundicePreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getJaundicePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:jaundice:dubu");
		} else if ("rachitis".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getRachitisPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getRachitisPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:rachitis:dubu");
		} else if ("diarrhoea".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getDiarrhoeaPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getDiarrhoeaPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:diarrhoea:dubu");
		} else if ("pediatricInfection".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getPediatricInfectionPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getPediatricInfectionPreDiagnose()
						.add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:pediatricInfection:dubu");
		} else if ("tubeInflammation".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getTubeInflammationPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getTubeInflammationPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:tubeInflammation:dubu");
		} else if ("otherPain".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getPediatricsOtherPainPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getPediatricsOtherPainPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:otherPain:dubu");
		} else if ("headache".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getHeadachePreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getHeadachePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:headache:dubu");
		} else if ("backache".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getBackachePreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getBackachePreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:backache:dubu");
		} else if ("epilepsy".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getEpilepsyPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getEpilepsyPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:epilepsy:dubu");
		} else if ("apoplexy".equals(whichDiagnose)) {
			if (whichDiagnoseType == 1)
				addIfNotExist(getApoplexyPreDiagnose(),
						setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			else if (whichDiagnoseType == 0)
				getApoplexyPreDiagnose().add(setForCustomerDiagnose(new CustomerDiagnose(), selectedDiagnose));
			RequestContext.getCurrentInstance().update("form:inspection:apoplexy:dubu");
		}
	}

	private void addIfNotExist(List<CustomerDiagnose> list, CustomerDiagnose diagnose) {
		int i = 0;
		boolean finding = true;
		while (i < list.size() && finding) {
			if (list.get(i).getDiagnosePkId().compareTo(diagnose.getDiagnosePkId()) == 0) {
				finding = false;
			}
			i++;
		}
		if (finding)
		{
			list.add(diagnose);
			if (diagnose.getId().startsWith("L40")) {
				RequestContext.getCurrentInstance().execute("PF('pasiScore').show();");
			}
		}
	}

	private void setForCustomerDiagnoseVoid(CustomerDiagnose customerDiagnose, Diagnose diagnose) {
		customerDiagnose.setDiagnosePkId(diagnose.getPkId());
		customerDiagnose.setId(diagnose.getId());
		customerDiagnose.setNameEn(diagnose.getNameEn());
		customerDiagnose.setNameMn(diagnose.getNameMn());
		customerDiagnose.setNameRu(diagnose.getNameRu());
		customerDiagnose.setType(whichDiagnoseType);
		customerDiagnose.setOrgan(whichDiagnose);
		customerDiagnose.setStatus(Tool.ADDED);
	}

	private CustomerDiagnose setForCustomerDiagnose(CustomerDiagnose customerDiagnose, Diagnose diagnose) {
		customerDiagnose.setDiagnosePkId(diagnose.getPkId());
		customerDiagnose.setId(diagnose.getId());
		customerDiagnose.setNameEn(diagnose.getNameEn());
		customerDiagnose.setNameMn(diagnose.getNameMn());
		customerDiagnose.setNameRu(diagnose.getNameRu());
		customerDiagnose.setType(whichDiagnoseType);
		customerDiagnose.setOrgan(whichDiagnose);
		customerDiagnose.setStatus(Tool.ADDED);
		return customerDiagnose;
	}

	public void removeTreatment(Treatment treatment) {
		getChosenTreatments().remove(treatment);
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		context.update("form:treatmentTable1");

	}

	public void chooseExaType() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setExaminations(examLogic.getExaminationList(chosenExaType.getPkId(), true));
			examinationTreeRoot = null;
			context.update("form:exaList");
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void getExaTypeFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setExaTypes(examLogic.getExaminationTypeList());
			context.update("form:exaTypeList");
			context.execute("PF('regExa').show()");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void chooseExamination() {
		RequestContext context = RequestContext.getCurrentInstance();
		if (exaTempList == null)
			getExaTempList();
		exaTempList.add(chosenExamination);
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void selDlExa(Examination examination) {
		RequestContext context = RequestContext.getCurrentInstance();
		getExaTempList().remove(examination);
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void selDlExa(ExaminationDtl examinationDtl) {
		RequestContext context = RequestContext.getCurrentInstance();
		Examination examination = examinationDtl.getExamination();
		for (Examination exa : getExaTempList()) {
			if (examination.getPkId().compareTo(exa.getPkId()) == 0)
				examination = exa;
		}

		ExaminationDtl exaDtl = examinationDtl;
		for (ExaminationDtl dtl : examination.getExaminationDtls()) {
			if (exaDtl.getPkId().compareTo(dtl.getPkId()) == 0)
				exaDtl = dtl;
		}

		examination.getExaminationDtls().remove(exaDtl);
		if (examination.getExaminationDtls().size() > 0) {
			examination.calculatePrice();
		} else {
			getExaTempList().remove(examination);
		}
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void chooseExamination(Examination examination) {
		RequestContext context = RequestContext.getCurrentInstance();
		int count = 0;
		for (Examination exa : getExaTempList()) {
			if (exa.getPkId().compareTo(examination.getPkId()) == 0) {
				count++;
				exa.setExaminationDtls(new ArrayList<ExaminationDtl>());
				exa.getExaminationDtls().addAll(examination.getExaminationDtls());
				exa.calculatePrice();
			}
		}
		if (count == 0) {
			Examination exa = (Examination) Tool.deepClone(examination);
			exa.calculatePrice();
			exaTempList.add(exa);
		}
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void chooseExamination(ExaminationDtl examinationDtl) {
		RequestContext context = RequestContext.getCurrentInstance();
		int count = 0;
		for (Examination exa : getExaTempList()) {
			if (exa.getPkId().compareTo(examinationDtl.getExamination().getPkId()) == 0) {
				count++;
				int count1;
				for (ExaminationDtl dtl : exa.getExaminationDtls()) {
					if (dtl.getPkId().compareTo(examinationDtl.getPkId()) == 0)
						return;
				}
				exa.getExaminationDtls().add(examinationDtl);
				exa.calculatePrice();
			}
		}
		if (count == 0) {
			Examination examination = (Examination) Tool.deepClone(examinationDtl.getExamination());
			examination.setExaminationDtls(new ArrayList<ExaminationDtl>());
			examination.getExaminationDtls().add(examinationDtl);
			examination.calculatePrice();
			getExaTempList().add(examination);
		}
		exaTempListRoot = null;
		context.update("form:tempExaList");
	}

	public void insertExaminationToList() {
		RequestContext context = RequestContext.getCurrentInstance();

		for (Examination exam : exaTempList) {
			boolean isdupplicat = false;
			for (InspectionDtl id : listExamination)
				if (!Tool.DELETE.equals(id.getStatus()))
					if (id.getTypePkId().compareTo(exam.getPkId()) == 0 || id.getId().equals(exam.getId()))
						isdupplicat = true;
			if (isdupplicat)
				continue;
			InspectionDtl dtl = new InspectionDtl();
			for (ExaminationDtl examinationDtl : exam.getExaminationDtls())
				dtl.getExaminationDtls().add(examinationDtl);
			dtl.setType("EXAMINATION");
			dtl.setTypePkId(exam.getPkId());
			dtl.setName(exam.getName());
			dtl.setExaminationDtlsSumPrice(exam.getPrice());
			dtl.setId(exam.getId());
			dtl.setStatus(Tool.ADDED);
			listExamination.add(dtl);
		}

		getExaTempList().clear();
		context.update("form:inspection:tableExamination");
		exaTempListRoot = null;
		context.update("form:tempExaList");
		context.execute("PF('regExa').hide();");
	}

	public void chooseXrayType() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setXrays(logicXray.getXrayByDianoseTypePkId(chosenXrayType.getPkId()));
			context.update("form:xrayList");
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void chooseXray() {
		RequestContext context = RequestContext.getCurrentInstance();
		xrayTempList.add(chosenXray);
		context.update("form:tempXrayList");
	}

	public void removeFromXrayTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		xrayTempList.remove(index);
		context.update("form:tempXrayList");
	}

	public void insertXrayToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		for (Xray xray : xrayTempList) {
			boolean isdupplicat = false;
			for (InspectionDtl id : listXray)
				if (id.getTypePkId().compareTo(xray.getPkId()) == 0)
					isdupplicat = true;
			if (isdupplicat)
				continue;
			InspectionDtl dtl = new InspectionDtl();
			dtl.setType("XRAY");
			dtl.setTypePkId(xray.getPkId());
			dtl.setName(xray.getName());
			dtl.setId(xray.getId());
			dtl.setCost(xray.getPriceIn());
			dtl.setStatus(Tool.ADDED);
			listXray.add(dtl);
		}

		xrayTempList.clear();
		context.update("form:tempXrayList");
		context.update("form:inspection:tableXray");
		context.execute("PF('regXray').hide();");
	}

	public void getXrayTypesFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setXrayTypes(logicXray.getXrayTypes(userSessionController.getLoggedInfo()));
			context.update("form:xrayTypeList");
			context.execute("PF('regXray').show();");
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void chooseMedicine() {
		RequestContext context = RequestContext.getCurrentInstance();
		medTempList.add(chosenMedicine);
		context.update("form:tempMedList");
	}

	public void removeFromMedTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		medTempList.remove(index);
		context.update("form:tempMedList");
	}

	public void insertMedicineToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			for (Medicine medicine : medTempList) {
				boolean isdupplicat = false;
				for (CustomerMedicine cm : listMedicine)
					if (cm.getMedicinePkId().compareTo(medicine.getPkId()) == 0 || cm.getId().equals(medicine.getId()))
						isdupplicat = true;
				if (isdupplicat)
					continue;
				CustomerMedicine customerMedicine = new CustomerMedicine();
				customerMedicine.setMedicinePkId(medicine.getPkId());
				customerMedicine.setName(medicine.getName());
				customerMedicine.setId(medicine.getId());
				customerMedicine.setiName(medicine.getiName());
				customerMedicine.setDose(medicine.getDose());
				customerMedicine.setDrugDose(medicine.getDrugDose());
				customerMedicine.setDose(medicine.getDrugDose());
				customerMedicine.setMedicineType(medicine.getTypeName());
				customerMedicine.setCalcDose(medicine.getCalcDose());
				customerMedicine.setCalcDrugDose(medicine.getCalcDrugDose());
				customerMedicine.setCalcType(medicine.getCalcType());
				customerMedicine.setStatus(Tool.ADDED);
				customerMedicine.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
				customerMedicine.setRepeatType(0);
				listMedicine.add(customerMedicine);
			}

			medTempList.clear();
			context.update("form:tempMedList");
			context.update("form:inspection:tableMedicine");
			context.execute("PF('regMed').hide();");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getMedListFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setMedicines(infoLogic.getMedicine(BigDecimal.ZERO, userSessionController.getLoggedInfo(), ""));
			context.update("form:medList");
			context.execute("PF('regMed').show();");
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад гарлаа.");
		}
	}

	public void getSurgeryTypeFromDb() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setSurgeryTypes(logicSurgery.getSurgeryTypes());
			context.update("form:surgeryTypeList");
			context.execute("PF('regSurgery').show()");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void chooseSurgeryType() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setSurgeries(logicSurgery.getSurgery(chosenSurgeryType.getPkId(),
					userSessionController.getLoggedInfo().getOrganization().getPkId(), false));
			context.update("form:surgeryList");
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void chooseSurgery() {
		RequestContext context = RequestContext.getCurrentInstance();
		surgeryTempList.add(chosenSurgery);
		context.update("form:tempSurgeryList");
	}

	public void removeFromSurgeryTempList(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		surgeryTempList.remove(index);
		context.update("form:tempSurgeryList");
	}

	public void insertSurgeryToList() {
		RequestContext context = RequestContext.getCurrentInstance();
		for (Surgery surgery : surgeryTempList) {
			boolean isdupplicat = false;
			for (InspectionDtl id : listSurgery)
				if (id.getTypePkId().compareTo(surgery.getPkId()) == 0 || id.getName().equals(surgery.getName()))
					isdupplicat = true;
			if (isdupplicat)
				continue;
			InspectionDtl dtl = new InspectionDtl();
			dtl.setType("SURGERY");
			dtl.setTypePkId(surgery.getPkId());
			dtl.setName(surgery.getName());
			dtl.setId(surgery.getName());
			dtl.setCost(surgery.getPrice());
			dtl.setStatus(Tool.ADDED);
			listSurgery.add(dtl);
		}

		surgeryTempList.clear();
		context.update("form:tempSurgeryList");
		context.update("form:inspection:tableSurgery");
		context.execute("PF('regSurgery').hide();");
	}

	public void saveEmr() {
		try {
			if (Tool.EARNOSETHROAT.equals(subOrgaType)) {

			} else if (Tool.SKIN.equals(subOrgaType)) {

			} else if (Tool.LUNG.equals(subOrgaType)) {

			} else if (Tool.DEFAULT.equals(subOrgaType)) {

			} else if (Tool.EARNOSETHROAT.equals(subOrgaType)) {

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void saveInspection() {
		try {
			List<InspectionDtl> dtls = new ArrayList<InspectionDtl>();
			dtls.addAll(listExamination);
			dtls.addAll(listXray);
			dtls.addAll(listTreatment);
			dtls.addAll(listSurgery);
			// currentEmployeeRequest.setMood(2);
			if (currentEmployeeRequest.getSaveMood() == 0) {
				currentEmployeeRequest.setSaveMood(1);
			} else if (currentEmployeeRequest.getSaveMood() == 2) {
				currentEmployeeRequest.setSaveMood(3);
			} else if (currentEmployeeRequest.getSaveMood() == 3) {
				currentEmployeeRequest.setSaveMood(3);
			} else
				currentEmployeeRequest.setSaveMood(1);

			if (currentInspection.getPkId() == null)
				getCurrentInspection().setStatus(Tool.ADDED);
			else
				getCurrentInspection().setStatus(Tool.MODIFIED);

			getListDiagnose().clear();
			getInspectionForm().clear();
			List<CustomerPlan> plans = new ArrayList<CustomerPlan>();
			List<CustomerQuestion> questions = new ArrayList<CustomerQuestion>();
			List<CustomerPain> pains = new ArrayList<CustomerPain>();
			List<CustomerAttachment> attachments = new ArrayList<CustomerAttachment>();

			if (getCurrentInspection().getEmployeeInspectionType() == -1)
				throw new Exception("no InspectionType");
			if (getCurrentEmployeeRequest().getSaveMood()== 0) throw  new Exception("no saveEMR");
			getCurrentInspection().setEmployeeInspectionType(getCurrentInspection().getEmployeeInspectionType());
			if (currentEmployeeRequest.getReInspection() == 0) {

				if (Tool.EARNOSETHROAT.equals(subOrgaType)) {
					attachments.addAll(getListNoseAttachment());
					attachments.addAll(getListEarAttachment());
					attachments.addAll(getListThroatAttachment());
					listDiagnose.addAll(getNosePreDiagnose());
					listDiagnose.addAll(getEarPreDiagnose());
					listDiagnose.addAll(getThroatPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");

					currentInspection = logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory);
					JSONObject jsonObj = toJson(getCustomerNose());
					getFormNose();
					getFormEar();
					getFormThroat();
					formNose.setInspectionPkId(getCurrentInspection().getPkId());
					formNose.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formNose.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formNose.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formNose.setInspectionType("nose");
					formNose.setStatus(Tool.ADDED);
					inspectionForm.add(formNose);
					jsonObj = toJson(getCustomerEar());
					formEar.setInspectionPkId(getCurrentInspection().getPkId());
					formEar.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formEar.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formEar.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formEar.setInspectionType("ear");
					formEar.setStatus(Tool.ADDED);
					inspectionForm.add(formEar);
					jsonObj = toJson(getCustomerThroat());
					formThroat.setInspectionPkId(getCurrentInspection().getPkId());
					formThroat.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formThroat.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formThroat.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formThroat.setInspectionType("throat");
					formThroat.setStatus(Tool.ADDED);
					inspectionForm.add(formThroat);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormEar(null);
					setFormNose(null);
					setFormThroat(null);
				} else if (Tool.LUNG.equals(subOrgaType)) {
					attachments.addAll(getListLungAttachment());
					listDiagnose.addAll(getLungPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormLung();
					JSONObject jsonObj = toJson(getCustomerLung());
					formLung.setInspectionPkId(getCurrentInspection().getPkId());
					formLung.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formLung.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formLung.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formLung.setInspectionType("lung");
					formLung.setStatus(Tool.ADDED);

					inspectionForm.add(formLung);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormLung(null);

				} else if (Tool.GASTRO.equals(subOrgaType)) {
					attachments.addAll(getListAttachment1());
					attachments.addAll(getListAttachment2());
					attachments.addAll(getListAttachment3());
					attachments.addAll(getListAttachment4());

					listDiagnose.addAll(getStomachPreDiagnose());
					listDiagnose.addAll(getLiverPreDiagnose());
					listDiagnose.addAll(getPancreasPreDiagnose());
					listDiagnose.addAll(getColonPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormStomach();
					getFormLiver();
					getFormPancreas();
					getFormColon();

					JSONObject jsonObj = toJson(getCustomerStomach());
					formStomach.setInspectionPkId(getCurrentInspection().getPkId());
					formStomach.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formStomach.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formStomach.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formStomach.setInspectionType("stomach");
					formStomach.setStatus(Tool.ADDED);
					inspectionForm.add(formStomach);

					jsonObj = toJson(getCustomerLiver());
					formLiver.setInspectionPkId(getCurrentInspection().getPkId());
					formLiver.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formLiver.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formLiver.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formLiver.setInspectionType("liver");
					formLiver.setStatus(Tool.ADDED);
					inspectionForm.add(formLiver);

					jsonObj = toJson(getCustomerPancreas());
					formPancreas.setInspectionPkId(getCurrentInspection().getPkId());
					formPancreas.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formPancreas.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formPancreas.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formPancreas.setInspectionType("pancreas");
					formPancreas.setStatus(Tool.ADDED);
					inspectionForm.add(formPancreas);

					jsonObj = toJson(getCustomerColon());
					formColon.setInspectionPkId(getCurrentInspection().getPkId());
					formColon.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formColon.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formColon.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formColon.setInspectionType("colon");
					formColon.setStatus(Tool.ADDED);
					inspectionForm.add(formColon);

					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormStomach(null);
					setFormLiver(null);
					setFormPancreas(null);
					setFormColon(null);

				} else if (Tool.SKIN.equals(subOrgaType)) {
					attachments.addAll(listSkinAttachment);
					listDiagnose.addAll(getSkinPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);

					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormSkin();
					JSONObject jsonObj = toJson(getCustomerSkin());
					formSkin.setInspectionPkId(getCurrentInspection().getPkId());
					formSkin.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formSkin.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formSkin.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formSkin.setInspectionType("skin");
					formSkin.setStatus(Tool.ADDED);
					inspectionForm.add(formSkin);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormSkin(null);

				} else if (Tool.HEMATOLOGY.equals(subOrgaType)) {
					listDiagnose.addAll(getHematoPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormHemato();
					JSONObject jsonObject = toJson(getCustomerHematology());
					formHemato.setInspectionPkId(getCurrentInspection().getPkId());
					formHemato.setInspectionPain(((JSONArray) jsonObject.get("p")).toString());
					formHemato.setInspectionCheckup(((JSONArray) jsonObject.get("c")).toString());
					formHemato.setInspectionMedicalHistory(((JSONArray) jsonObject.get("m")).toString());
					formHemato.setInspectionType("hemato");
					formHemato.setStatus(Tool.ADDED);
					inspectionForm.add(formHemato);
					logicInspection.saveInspectionForm(currentInspection, inspectionForm,
							userSessionController.getLoggedInfo());
					setFormHemato(null);
				} else if (Tool.CARDIOLOGY.equals(subOrgaType)) {
					attachments.addAll(getListCardioAttachment());
					listDiagnose.addAll(getCardiologyPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormCardiology();
					JSONObject jsonCarObject = toJson(getCustomerCardiology());
					formCardiology.setInspectionPkId(getCurrentInspection().getPkId());
					formCardiology.setInspectionPain(((JSONArray) jsonCarObject.get("p")).toString());
					formCardiology.setInspectionMedicalHistory(((JSONArray) jsonCarObject.get("m")).toString());
					formCardiology.setInspectionCheckup(((JSONArray) jsonCarObject.get("c")).toString());
					formCardiology.setInspectionType("cardiology");
					formCardiology.setStatus(Tool.ADDED);
					inspectionForm.add(formCardiology);
					logicInspection.saveInspectionForm(currentInspection, inspectionForm,
							userSessionController.getLoggedInfo());
					setFormCardiology(null);
				} else if (Tool.KIDNEY.equals(subOrgaType)) {
					attachments.addAll(getListkidneyAttachment());
					listDiagnose.addAll(getKidneyPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormKidney();
					JSONObject objectKidney = toJson(getCustomerKidney());
					formKidney.setInspectionPkId(getCurrentInspection().getPkId());
					formKidney.setInspectionPain(((JSONArray) objectKidney.get("p")).toString());
					formKidney.setInspectionMedicalHistory(((JSONArray) objectKidney.get("m")).toString());
					formKidney.setInspectionCheckup(((JSONArray) objectKidney.get("c")).toString());
					formKidney.setInspectionType("kidney");
					formKidney.setStatus(Tool.ADDED);
					inspectionForm.add(formKidney);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormKidney(null);
				} else if (Tool.EYE.equals(subOrgaType)) {
					attachments.addAll(getListEyeAttachment());
					listDiagnose.addAll(getEyePreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose)
						cd.setStatus(Tool.ADDED);
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(getCurrentInspection(),
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormEye();
					JSONObject eyeObject = toJson(getCustomerEye());
					formEye.setInspectionPkId(getCurrentInspection().getPkId());
					formEye.setInspectionPain(((JSONArray) eyeObject.get("p")).toString());
					formEye.setInspectionMedicalHistory(((JSONArray) eyeObject.get("m")).toString());
					formEye.setInspectionCheckup(((JSONArray) eyeObject.get("c")).toString());
					formEye.setInspectionType("eye");
					formEye.setStatus(Tool.ADDED);
					inspectionForm.add(formEye);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							getUserSessionController().getLoggedInfo());
					setFormEye(null);
				} else if (Tool.TRADITION.equals(subOrgaType)) {
					attachments.addAll(getListTradtionAttachment());
					listDiagnose.addAll(getTraditionPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose)
						cd.setStatus(Tool.ADDED);
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(getCurrentInspection(),
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormTradition();
					JSONObject jsonTrad = toJson(getCustomerTradition());
					formTradition.setInspectionPkId(getCurrentInspection().getPkId());
					formTradition.setInspectionPain(((JSONArray) jsonTrad.get("p")).toString());
					formTradition.setInspectionCheckup(((JSONArray) jsonTrad.get("c")).toString());
					formTradition.setInspectionMedicalHistory(((JSONArray) jsonTrad.get("m")).toString());
					formTradition.setInspectionType("tradition");
					formTradition.setStatus(Tool.ADDED);
					inspectionForm.add(formTradition);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							getUserSessionController().getLoggedInfo());
					setFormTradition(null);
				} else if (Tool.ENDOCRINE.equals(subOrgaType)) {
					listDiagnose.addAll(getEndocrinePreDiagnose());
					listDiagnose.addAll(getThryoidPreDiagnose());
					listDiagnose.addAll(getDiabetesPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormEndocrine();
					getFormThryoid();
					getFormDiabetes();
					JSONObject jsonObj = toJson(getCustomerEndocrine());
					formEndocrine.setInspectionPkId(getCurrentInspection().getPkId());
					formEndocrine.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formEndocrine.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formEndocrine.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formEndocrine.setInspectionType("endocrine");
					formEndocrine.setStatus(Tool.ADDED);
					inspectionForm.add(formEndocrine);

					jsonObj = toJson(getCustomerThryoid());
					formThryoid.setInspectionPkId(getCurrentInspection().getPkId());
					formThryoid.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formThryoid.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formThryoid.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formThryoid.setInspectionType("thryoid");
					formThryoid.setStatus(Tool.ADDED);
					inspectionForm.add(formThryoid);

					jsonObj = toJson(getCustomerDiabetes());
					formDiabetes.setInspectionPkId(getCurrentInspection().getPkId());
					formDiabetes.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formDiabetes.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formDiabetes.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formDiabetes.setInspectionType("diabetes");
					formDiabetes.setStatus(Tool.ADDED);
					inspectionForm.add(formDiabetes);

					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());

					setFormEndocrine(null);
					setFormThryoid(null);
					setFormDiabetes(null);

				} else if (Tool.REHABILITATION.equals(subOrgaType)) {
					attachments.addAll(getListRehabilitationAttachment());
					listDiagnose.addAll(getRehabilitationPreDiagnose());

					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose)
						cd.setStatus(Tool.ADDED);
					getCurrentInspection();

					setCurrentInspection(logicInspection.saveInspection(getCurrentInspection(),
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormRehabilitation();
					JSONObject jsonObjectRehab = toJson(getCustomerRehabilitation());
					formRehabilitation.setInspectionPkId(getCurrentInspection().getPkId());
					formRehabilitation.setInspectionPain(((JSONArray) jsonObjectRehab.get("p")).toString());
					formRehabilitation.setInspectionMedicalHistory(((JSONArray) jsonObjectRehab.get("m")).toString());
					formRehabilitation.setInspectionCheckup(((JSONArray) jsonObjectRehab.get("c")).toString());
					formRehabilitation.setInspectionType("rehabilitation");
					formRehabilitation.setStatus(Tool.ADDED);
					inspectionForm.add(formRehabilitation);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormRehabilitation(null);
				} else if (Tool.PEDIATRICS.equals(subOrgaType)) {
					listDiagnose.addAll(getJaundicePreDiagnose());
					listDiagnose.addAll(getRachitisPreDiagnose());
					listDiagnose.addAll(getDiarrhoeaPreDiagnose());
					listDiagnose.addAll(getPediatricInfectionPreDiagnose());
					listDiagnose.addAll(getTubeInflammationPreDiagnose());
					listDiagnose.addAll(getPediatricsOtherPainPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormJaundice();
					getFormRachitis();
					getFormDiarrhoea();
					getFormPediatricInfection();
					getFormTubeInflammation();
					getFormPediatricsOtherPain();
					JSONObject jsonObj = toJson(getCustomerJaundice());
					formJaundice.setInspectionPkId(getCurrentInspection().getPkId());
					formJaundice.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formJaundice.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formJaundice.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formJaundice.setInspectionType("jaundice");
					formJaundice.setStatus(Tool.ADDED);
					inspectionForm.add(formJaundice);

					jsonObj = toJson(getCustomerRachitis());
					formRachitis.setInspectionPkId(getCurrentInspection().getPkId());
					formRachitis.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formRachitis.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formRachitis.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formRachitis.setInspectionType("rachitis");
					formRachitis.setStatus(Tool.ADDED);
					inspectionForm.add(formRachitis);

					jsonObj = toJson(getCustomerDiarrhoea());
					formDiarrhoea.setInspectionPkId(getCurrentInspection().getPkId());
					formDiarrhoea.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formDiarrhoea.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formDiarrhoea.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formDiarrhoea.setInspectionType("diarrhoea");
					formDiarrhoea.setStatus(Tool.ADDED);
					inspectionForm.add(formDiarrhoea);

					jsonObj = toJson(getCustomerPediatricInfection());
					formPediatricInfection.setInspectionPkId(getCurrentInspection().getPkId());
					formPediatricInfection.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formPediatricInfection.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formPediatricInfection.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formPediatricInfection.setInspectionType("pediatricInfection");
					formPediatricInfection.setStatus(Tool.ADDED);
					inspectionForm.add(formPediatricInfection);

					jsonObj = toJson(getCustomerTubeInflammation());
					formTubeInflammation.setInspectionPkId(getCurrentInspection().getPkId());
					formTubeInflammation.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formTubeInflammation.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formTubeInflammation.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formTubeInflammation.setInspectionType("tubeInflammation");
					formTubeInflammation.setStatus(Tool.ADDED);
					inspectionForm.add(formTubeInflammation);
					
					jsonObj = toJson(getCustomerPediatricsOtherPain());
					formPediatricsOtherPain.setInspectionPkId(getCurrentInspection().getPkId());
					formPediatricsOtherPain.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formPediatricsOtherPain.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formPediatricsOtherPain.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formPediatricsOtherPain.setInspectionType("otherPain");
					formPediatricsOtherPain.setStatus(Tool.ADDED);
					inspectionForm.add(formPediatricsOtherPain);

					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());

					setFormJaundice(null);
					setFormRachitis(null);
					setFormDiarrhoea(null);
					setFormPediatricInfection(null);
					setFormTubeInflammation(null);
					setFormPediatricsOtherPain(null);

				} else if (Tool.NERVOUS.equals(subOrgaType)) {
					listDiagnose.addAll(getHeadachePreDiagnose());
					listDiagnose.addAll(getBackachePreDiagnose());
					listDiagnose.addAll(getEpilepsyPreDiagnose());
					listDiagnose.addAll(getApoplexyPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));
					getFormHeadache();
					getFormBackache();
					getFormEpilepsy();
					getFormApoplexy();
					JSONObject jsonObj = toJson(getCustomerHeadache());
					formHeadache.setInspectionPkId(getCurrentInspection().getPkId());
					formHeadache.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formHeadache.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formHeadache.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formHeadache.setInspectionType("headache");
					formHeadache.setStatus(Tool.ADDED);
					inspectionForm.add(formHeadache);

					jsonObj = toJson(getCustomerBackache());
					formBackache.setInspectionPkId(getCurrentInspection().getPkId());
					formBackache.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formBackache.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formBackache.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formBackache.setInspectionType("backache");
					formBackache.setStatus(Tool.ADDED);
					inspectionForm.add(formBackache);

					jsonObj = toJson(getCustomerEpilepsy());
					formEpilepsy.setInspectionPkId(getCurrentInspection().getPkId());
					formEpilepsy.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formEpilepsy.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formEpilepsy.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formEpilepsy.setInspectionType("epilepsy");
					formEpilepsy.setStatus(Tool.ADDED);
					inspectionForm.add(formEpilepsy);

					jsonObj = toJson(getCustomerApoplexy());
					formApoplexy.setInspectionPkId(getCurrentInspection().getPkId());
					formApoplexy.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formApoplexy.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formApoplexy.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formApoplexy.setInspectionType("apoplexy");
					formApoplexy.setStatus(Tool.ADDED);
					inspectionForm.add(formApoplexy);

					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());

					setFormHeadache(null);
					setFormBackache(null);
					setFormEpilepsy(null);
					setFormApoplexy(null);

				} else if (Tool.DEFAULT.equals(subOrgaType)) {
					attachments.addAll(getListDefaultAttachment());
					listDiagnose.addAll(getDefaultPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					currentInspection = logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory);

					getFormDefault();
					JSONObject jsonObj = toJson(getCustomerDefaultInspection());
					formDefault.setInspectionPkId(getCurrentInspection().getPkId());
					formDefault.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formDefault.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formDefault.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formDefault.setInspectionType("default");
					formDefault.setStatus(Tool.ADDED);

					inspectionForm.add(formDefault);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormDefault(null);

				}

				else {
					attachments.addAll(getListDefaultAttachment());
					listDiagnose.addAll(getDefaultPreDiagnose());
					if (listDiagnose.size() == 0)
						throw new Exception("no diagnose");
					for (CustomerDiagnose cd : listDiagnose) {
						cd.setStatus(Tool.ADDED);
					}
					getCurrentInspection();
					setCurrentInspection(logicInspection.saveInspection(currentInspection,
							userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine,
							currentEmployeeRequest, plans, questions, attachments, pains, currentPastHistory));

					getFormDefault();
					JSONObject jsonObj = toJson(getCustomerDefaultInspection());
					formDefault.setInspectionPkId(getCurrentInspection().getPkId());
					formDefault.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
					formDefault.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
					formDefault.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
					formDefault.setInspectionType("default");
					formDefault.setStatus(Tool.ADDED);
					inspectionForm.add(formDefault);
					logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
							userSessionController.getLoggedInfo());
					setFormDefault(null);
				}
			} else {
				attachments.addAll(getListReAttachment());
				listDiagnose.addAll(getReInspPreDiagnose());
				if (listDiagnose.size() == 0)
					throw new Exception("no diagnose");
				for (CustomerDiagnose cd : listDiagnose) {
					cd.setStatus(Tool.ADDED);
				}
				getCurrentInspection();
				setCurrentInspection(logicInspection.saveInspection(currentInspection,
						userSessionController.getLoggedInfo(), dtls, listDiagnose, listMedicine, currentEmployeeRequest,
						plans, questions, attachments, pains, currentPastHistory));
				getFormReInspection();
				JSONObject jsonObj = toJson(getCustomerReInspection());
				formReInspection.setInspectionPkId(getCurrentInspection().getPkId());
				formReInspection.setInspectionPain(((JSONArray) jsonObj.get("p")).toString());
				formReInspection.setInspectionMedicalHistory(((JSONArray) jsonObj.get("m")).toString());
				formReInspection.setInspectionCheckup(((JSONArray) jsonObj.get("c")).toString());
				formReInspection.setInspectionType("reinsp");
				formReInspection.setStatus(Tool.ADDED);
				inspectionForm.add(formReInspection);
				logicInspection.saveInspectionForm(getCurrentInspection(), inspectionForm,
						userSessionController.getLoggedInfo());
				setFormReInspection(null);
			}

			userSessionController.showMessage(99);
			Runtime.getRuntime().gc();
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("no diagnose")) {
				userSessionController.showWarningMessage("Онош сонгоно уу.");
			} else if (e.getMessage().equals("no InspectionType")) {
				userSessionController.showWarningMessage("Үзлэгийн  төрлөө  сонгоно уу!!!.");
			} else if (e.getMessage().equals("no saveEMR")) {
				userSessionController.showWarningMessage("EMR хадгалагдаагүй байна");
			} else
				userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}

	public void toggleSkinCheckBox() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String skin = params.get("skin");
		boolean check = Boolean.parseBoolean(params.get("check"));
		if ("scalp".equals(skin)) {
			customerSkin.setScalp(check);
		} else if ("leg".equals(skin)) {
			customerSkin.setLeg(check);
		} else if ("foot".equals(skin)) {
			customerSkin.setFoot(check);
		} else if ("arm".equals(skin)) {
			customerSkin.setArm(check);
		} else if ("neck".equals(skin)) {
			customerSkin.setNeck(check);
		} else if ("body".equals(skin)) {
			customerSkin.setBody(check);
		} else if ("hand".equals(skin)) {
			customerSkin.setHand(check);
		} else if ("face".equals(skin)) {
			customerSkin.setFace(check);
		} else if ("anogenital".equals(skin)) {
			customerSkin.setAnogenital(check);
		}

		RequestContext.getCurrentInstance().update("form:inspection:skin:forev");
	}

	public void toggleSkinValue() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String skins = params.get("skinsValue");
		customerSkin.setEruption(
				"</label> <span style='font-weight: 700; color:black;font-size: 12px; font-family: Times New Roman;'>"
						+ skins + "</span><label> ");
		System.out.println(skins);
	}

	public void toggleDistribution() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String distribution = params.get("skinsDistribution");
		customerSkin.setOutbreak(
				"</label> <span style='font-weight: 700; color:black;font-size: 12px; font-family: Times New Roman;'>"
						+ distribution + "</span><label>");
		System.out.println(distribution);
	}

	public void toggleLocationValue() {
		Map<String, String> mapParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String params = mapParams.get("skinLocation");
		customerSkin.setLocation(
				"</label> <span style='font-weight: 700; color:black;font-size: 12px; font-family: Times New Roman;'>"
						+ params + "</span><label>");
		System.out.println(params);
	}

	public void cardiologyRashObservation() {
		Map<String, String> mapParams = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String cardioParam = mapParams.get("cardiologyObser");
		customerCardiology
				.setObservation("<br/><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' > "
						+ cardioParam + "</span>");
	}

	public void toggleCardioLocation() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String locationStr = mapParam.get("cardioLocation");
		customerCardiology
				.setCardioLocation("<span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' > "
						+ locationStr + "</span>");
	}

	public void toggleCardioTouch() {
		Map<String, String> map = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String touch = map.get("cardioTouch");
		customerCardiology.setTouch(
				"<span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' > " + touch + "</span>");
	}

	public void toggleCardioPatter() {
		Map<String, String> maps = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String param = maps.get("cardioTab");
		customerCardiology.setTab(
				"<span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' > " + param + "</span");
	}

	public void toggleCardioListen() {
		Map<String, String> maps = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String param = maps.get("cardioListen");
		customerCardiology.setListen(
				"<span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' > " + param + "</span");
	}

	public void toggleLeftEye() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("leftEyePain");
		customerEye.setRightEyeToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
		System.out.println("eyeToggle" + paramStr);
	}

	public void neuroToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("neurolog");
		customerRehabilitation.setNeuroExamination(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void funcExamToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("funcExam");
		customerRehabilitation.setFunctionExam(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void cerebalToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("celebralElement");
		customerRehabilitation.setCelebralFuntion(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void cerebellarToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("cerebellar");
		customerRehabilitation.setCelebrallerFuntion(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void fingerNoseLeftToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("fingerNoseLeft");
		customerRehabilitation.setLeftNoseTes(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void fingerRightToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("noseTesRight");
		customerRehabilitation.setRightNoseTes(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void cranialNerveToggleAction() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("cranialNerve");
		customerRehabilitation.setCranialNerve(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void cranialNerveRightToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("cranialNerveRight");
		customerRehabilitation.setRgihtCranialNerve(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void crianalLeftToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("cranialNerveLeft");
		customerRehabilitation.setLeftCranialNerve(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void motorToggleElement() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("motor");
		customerRehabilitation.setMotorToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void motorPowerToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("rightMotorPower");
		customerRehabilitation
				.setRightPower("</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >"
						+ paramStr + "</span><label>");
	}

	public void romRangeToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("romRange");
		customerRehabilitation.setRomRangeToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void motorPowerLeftToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("leftMotorPower");
		customerRehabilitation.setLeftMotorpowerL(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void upperExtremityToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("upperEx");
		customerRehabilitation.setUpperExToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void upperExtremityRightToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("upperRight");
		customerRehabilitation.setUpperExRightToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void upperExtremityLeftToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("upperleft");
		customerRehabilitation.setUpperExleftToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void lowExtremityToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("lowExt");
		customerRehabilitation.setLowExToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void lowExtremityRightToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("lowExtRight");
		customerRehabilitation.setLowExrightToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void lowExtremityLeftToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("lowExtleft");
		customerRehabilitation.setLowExLeftToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void gaitToggleAction() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("gait");
		customerRehabilitation
				.setGaitToggle("</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >"
						+ paramStr + "</span><label>");
	}

	public void ambulationToggleAciton() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("ambulation");
		customerRehabilitation
				.setAmbulation("</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >"
						+ paramStr + "</span><label>");
	}

	public void adlToggleAction() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("adl");
		customerRehabilitation
				.setAdlToggle("</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >"
						+ paramStr + "</span><label>");
	}

	// Zoviur zuun
	public void toggleLeftPain() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("rightEyePain");
		customerEye.setPainEyeLeft(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void paintEyeToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("paintEye");
		customerEye.setPainHistoryEye(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void lifeEyeToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("lifeEye");
		customerEye.setLifeHistoryEye(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	// baruun tal
	public void rightEyeToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("rightEye");
		customerEye.setInspectionnEyeRight(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	// Бодит үзлэг зүүн тал
	public void leftEyeToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("leftEye");
		customerEye.setInspectionnEyeLeft(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void bowelToggleRehab() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("bowel");
		customerRehabilitation.setBowelToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void bladderToggleRehab() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("bladder");
		customerRehabilitation.setBladdderToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	public void windTraditationToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("windElement");
		customerTradition.setWindElementToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	
	public void cookInceptToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("cookIncept");
		customerTradition.setCookInceptToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	
	public void inspectionExamToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("examIns");
		customerTradition.setInspectionToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	
	public void uvurchlulToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("uvurchlul");
		customerTradition.setUvurchlulToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	
	public void examTraditationToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("examtrad");
		customerTradition.setExaminationToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	
	public void badganTraditationToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("badgan");
		customerTradition.setInspectionStomatchtoggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}
	public void yellowPainToggle() {
		Map<String, String> mapParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String paramStr = mapParam.get("yellowElement");
		customerTradition.setYellowToggle(
				"</label><span style='font-size: 13px; font-weight: 700; font-family: Times New Roman' >" + paramStr
						+ "</span><label>");
	}

	private void toCustomerObject(InspectionForm form, Object object) {
		try {
			JSONArray array = toJSONArray(form.getInspectionPain());
			combineArray(toJSONArray(form.getInspectionMedicalHistory()), array);
			combineArray(toJSONArray(form.getInspectionCheckup()), array);
			setFieldValues(object, array);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void get15ViewPrint(BigDecimal pkid, Date d, String name) {
		try {
			gt15Pain = logicInspection.getCustomerSubOrganization(getSelectedCustomer().getPkId(), d, name);
			setInspectionEmployeeSignatureList(logicInspection.getEmployeeSignature(pkid, d));
			listCustomerMedicines = logicInspection.getCustomerMedicinesList(pkid);
			gt15Pain = logicInspection.getCustomerSubOrganization(getSelectedCustomer().getPkId(), d, name);

			detailsList = logicInspection.getInspecionDtl(pkid);
			List<BigDecimal> list = new ArrayList<>();
			for (int i = 0; i < detailsList.size(); i++) {
				if (detailsList.get(i).getTypeDtlPkId() != null) {
					list.add(detailsList.get(i).getTypeDtlPkId());
				}
			}
			examinations2List = logicInspection.getExamination(list, d);
			treatmentsList = logicInspection.getTreatment(list, d);
			xrays2List = logicInspection.getXray(list, d);
			surgeries2List = logicInspection.getSurgery(list);
			listInspectionForms = logicInspection.getInspectionFormPrint(pkid);
			listCustomerDiagnosesPrint = logicInspection.getCustomerDiagnose(pkid);
			lCustomerAttachments = logicInspection.getCustomerAttachment(pkid);
			getListInspectionDetails().clear();
			InspectionDetail detail = new InspectionDetail();
			detail.setInspectionPkId(pkid);
			listInspectionDetails.add(detail);
			JSONParser jsonParser = new JSONParser();
			for (InspectionDetail dtl : listInspectionDetails) {
				for (InspectionForm infoform : listInspectionForms) {
					JSONObject jsonObjectpain = new JSONObject();
					jsonObjectpain.put("t", infoform.getInspectionType());
					jsonObjectpain.put("v", (JSONArray) jsonParser.parse(infoform.getInspectionPain()));
					jsonPain = (JSONArray) jsonParser.parse(infoform.getInspectionPain());
					jsonCheckUp = (JSONArray) jsonParser.parse(infoform.getInspectionCheckup());
					jsonq = (JSONArray) jsonParser.parse(infoform.getInspectionMedicalHistory());
					dtl.getPain().add(jsonObjectpain);
					JSONObject jsonCheckUp = new JSONObject();
					jsonCheckUp.put("t", infoform.getInspectionType());
					jsonCheckUp.put("v", (JSONArray) jsonParser.parse(infoform.getInspectionCheckup()));
					dtl.getCheckup().add(jsonCheckUp);
					JSONObject jsonq = new JSONObject();
					jsonq.put("t", infoform.getInspectionType());
					jsonq.put("v", (JSONArray) jsonParser.parse(infoform.getInspectionMedicalHistory()));
					dtl.getQ().add(jsonq);
					System.out.println("Pain " + jsonPain.size());
				}
				for (CustomerAttachment attachment : lCustomerAttachments) {
					dtl.getCustomerAttachment().add(attachment);
				}
			}

		} catch (Exception e) {
			getUserSessionController().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}

	}

	private JSONArray toJSONArray(String json) {
		JSONParser parser = new JSONParser();
		try {
			return (JSONArray) parser.parse(json);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void combineArray(JSONArray source, JSONArray destination) {
		for (int i = 0; i < source.size(); i++)
			destination.add((JSONObject) source.get(i));
	}

	private void setFieldValues(Object obj, JSONArray array) {
		try {
			JSONObject cursorObj = null;
			for (int i = 0; i < array.size(); i++) {
				cursorObj = (JSONObject) array.get(i);
				Field field = obj.getClass().getField(cursorObj.get("n").toString());
				if (cursorObj.get("d").equals("int"))
					field.set(obj, ((Long) cursorObj.get("v")).intValue());
				else if (cursorObj.get("d").equals("java.math.BigDecimal")) {
					System.out.println("NAME HERE =====> " + cursorObj.get("n"));
					System.out.println("VALUE HERE =======> " + cursorObj.get("v"));
					field.set(obj, new BigDecimal((String) cursorObj.get("v")));
				} else if (cursorObj.get("d").equals("java.lang.String")) {
					field.set(obj, (String) cursorObj.get("v"));
				} else if (cursorObj.get("d").equals("java.util.Date")) {
					field.set(obj, dateFormatter.parse((String) cursorObj.get("v")));
				} else if (cursorObj.get("d").equals("boolean")) {
					field.set(obj, (boolean) cursorObj.get("v"));
				}
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private JSONObject toJson(Object obj) {
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray pain = new JSONArray();
			JSONArray medicalHistory = new JSONArray();
			JSONArray checkup = new JSONArray();
			jsonObj.put("p", pain);
			jsonObj.put("m", medicalHistory);
			jsonObj.put("c", checkup);
			getParentList().clear();
			for (Field field : obj.getClass().getDeclaredFields()) {
				JSONObject fieldJson = new JSONObject();

				if (field.isAnnotationPresent(Label.class)) {
					Label labelAnno = (Label) field.getAnnotation(Label.class);

					if (field.get(obj) != null) {
						fieldJson.put("d", field.getType().getName());
						fieldJson.put("n", field.getName());
						fieldJson.put("t", labelAnno.fieldType());
						fieldJson.put("lt", labelAnno.labelType());
						if (field.getType().getName().equals("java.math.BigDecimal")) {
							if (field.get(obj) != null
									&& ((BigDecimal) field.get(obj)).compareTo(BigDecimal.ZERO) == 1) {
								fieldJson.put("v", ((BigDecimal) field.get(obj)).toString());
								fieldJson.put("l", labelAnno.label());
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("java.lang.String")) {
							if (field.get(obj) != null && !((String) field.get(obj)).equals("")) {
								fieldJson.put("v", (String) field.get(obj));
								fieldJson.put("l", labelAnno.label());
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("java.util.Date")) {
							if (field.get(obj) != null) {
								fieldJson.put("v", (dateFormatter.format((Date) field.get(obj)).toString()));
								fieldJson.put("l", labelAnno.label());
							} else {

							}
						} else if (field.getType().getName().equals("int")) {
							if (field.get(obj) != null && ((int) field.get(obj)) != 0) {
								fieldJson.put("v", ((int) field.get(obj)));
								fieldJson.put("l", labelAnno.label());
								if (!labelAnno.answers().equals("")) {
									JSONArray arr = new JSONArray();
									for (String str : labelAnno.answers()) {
										arr.add(str);
									}
									fieldJson.put("a", arr);
								}
							} else {
								fieldJson.put("v", null);
							}
						} else if (field.getType().getName().equals("boolean")) {
							if (field.get(obj) != null && (boolean) field.get(obj) == true) {
								fieldJson.put("v", ((boolean) field.get(obj)));
								fieldJson.put("l", labelAnno.label());
							}
						}
						if (fieldJson.get("v") != null)
							if (fieldJson.get("lt").equals("m")) {
								medicalHistory.add(fieldJson);
							} else if (fieldJson.get("lt").equals("p")) {
								pain.add(fieldJson);
							} else if (fieldJson.get("lt").equals("c")) {
								checkup.add(fieldJson);
							}
						getParentList().add(fieldJson);
					}
				}
			}
			return jsonObj;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void refreshEmployees() {
		try {
			System.out.println(selectedOcsSubOrgaPkId.size());
		} catch (Exception e) {
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}

	public void toggleEmr(String value) {
		if ("EMR".equals(value)) {
			setEmr(true);
			setOcs(false);
			setHp(false);
		} else if ("OCS".equals(value)) {
			setEmr(false);
			setOcs(true);
			setHp(false);
			getOcsData();
		} else if ("HP".equals(value)) {
			setEmr(false);
			setOcs(false);
			setHp(true);
		}
	}

	public void getInspectionList() {
		try {
			List<BigDecimal> ocsSubOrgaPkId = new ArrayList<BigDecimal>();
			List<BigDecimal> ocsEmployeePkId = new ArrayList<BigDecimal>();
			for (String pkId : getSelectedOcsSubOrgaPkId()) {
				ocsSubOrgaPkId.add(new BigDecimal(pkId));
			}
			for (String pkId : getSelectedOcsEmployee()) {
				ocsEmployeePkId.add(new BigDecimal(pkId));
			}
			listInspection = logicInspection.getInspectionByCustomer(selectedCustomer.getPkId(), ocsEmployeePkId,
					ocsSubOrgaPkId, currentEmployee.getPkId(), myInpsection);
			if (selectedNode != null)
				selectedNode = null;
			createInspectionTreeNode(listInspection);
			getInspectionData();
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void getInspectionData() {
		List<BigDecimal> inspectionPkId = new ArrayList<BigDecimal>();
		getInspectionDetails().clear();
		if (getSelectedInspection() != null) {
			InspectionDetail insp = new InspectionDetail();
			insp.setInspection(getSelectedInspection());
			inspectionPkId.add(getSelectedInspection().getPkId());
			inspectionDetails.add(insp);
		}
		if (selectedNode != null)
			for (TreeNode node : selectedNode) {
				if (node.getType().equals("inspection")) {
					InspectionDetail insp = new InspectionDetail();
					insp.setInspection((Inspection) node.getData());
					inspectionPkId.add(((Inspection) node.getData()).getPkId());
					inspectionDetails.add(insp);
				}
			}
		JSONParser parser = new JSONParser();
		try {
			if (inspectionPkId.size() == 0)
				throw new Exception("selectInspection");
			List<InspectionForm> inspectionForms = logicInspection.getCustomerEnt(inspectionPkId);
			List<CustomerDiagnose> customerDiagnoses = logicInspection.getCustomerDiagnose(inspectionPkId);
			List<InspectionDtl> inspectionDtls = logicInspection.getInspectionDtl(inspectionPkId);
			List<CustomerPain> customerPains = logicInspection.getCustomerPain(inspectionPkId);
			List<CustomerPlan> customerPlans = logicInspection.getCustomerPlan(inspectionPkId);
			List<CustomerQuestion> customerQuestions = logicInspection.getCustomerQuestion(inspectionPkId);
			List<CustomerInspection> customerInspections = logicInspection.getCustomerInspection(inspectionPkId);
			List<CustomerAttachment> customerAttachments = logicInspection.getCustomerAttachment(inspectionPkId);

			for (InspectionDetail detail : inspectionDetails) {
				for (InspectionForm inspectionForm : inspectionForms) {
					if (inspectionForm.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						JSONObject painObj = new JSONObject();
						painObj.put("t", inspectionForm.getInspectionType());
						painObj.put("v", (JSONArray) parser.parse(inspectionForm.getInspectionPain()));
						detail.getPain().add(painObj);
						JSONObject checkupObj = new JSONObject();
						checkupObj.put("t", inspectionForm.getInspectionType());
						checkupObj.put("v", (JSONArray) parser.parse(inspectionForm.getInspectionCheckup()));
						detail.getCheckup().add(checkupObj);
						JSONObject qObj = new JSONObject();
						qObj.put("t", inspectionForm.getInspectionType());
						qObj.put("v", (JSONArray) parser.parse(inspectionForm.getInspectionMedicalHistory()));
						detail.getQ().add(qObj);
					}
				}
				for (CustomerDiagnose diagnose : customerDiagnoses) {
					if (diagnose.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerDiagnoses().add(diagnose);
					}
				}
				for (InspectionDtl inspectionDtl : inspectionDtls) {
					if (inspectionDtl.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getInspectionDtl().add(inspectionDtl);
					}
				}
				for (CustomerPain pain : customerPains) {
					if (pain.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerPain().add(pain);
					}
				}
				for (CustomerPlan plan : customerPlans) {
					if (plan.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerPlan().add(plan);
					}
				}
				for (CustomerQuestion question : customerQuestions) {
					if (question.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerQuestion().add(question);
					}
				}
				for (CustomerInspection inspection : customerInspections) {
					if (inspection.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerInspection().add(inspection);
					}
				}
				for (CustomerAttachment attachment : customerAttachments) {
					if (attachment.getInspectionPkId().equals(detail.getInspection().getPkId())) {
						detail.getCustomerAttachment().add(attachment);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (e.getMessage().equals("selectInspection"))
				userSessionController.showWarningMessage("Үзлэг сонгоно уу.");
			else
				userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	private int findInspectionDetail(BigDecimal inspectionPkId) {
		for (int i = 0; i < inspectionDetails.size(); i++) {
			if (inspectionDetails.get(i).getInspection().getPkId().equals(inspectionPkId))
				return i;
		}
		return -1;
	}

	public void getOcsData() {

		try {
			listExaminationRequestCompleted = logicInspection.getExaminationRequestCompletedByFilter(null, null,
					getSelectedCustomer().getPkId());
			int index = 0;
			getCustomerExaminations().clear();
			getCurrentCustomerExaminations().clear();
			getExaminationResults().clear();
			for (ExaminationRequestCompleted completed : listExaminationRequestCompleted) {
				index = findExamination(completed.getExaminationPkId());
				if (index == -1) {
					CustomerExamination exa = new CustomerExamination();
					exa.setCount(1);
					exa.setExaminationName(completed.getExaminationName());
					exa.setExaminationPkId(completed.getExaminationPkId());
					exa.setExaminationDate(completed.getRequestDate());
					getCustomerExaminations().add(exa);
				} else {
					getCustomerExaminations().get(index).setCount(getCustomerExaminations().get(index).getCount() + 1);
				}
			}

		} catch (Exception e) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
			e.printStackTrace();
		}
		RequestContext.getCurrentInstance().update("form:QOP");
	}

	private int findExamination(BigDecimal examinationPkId) {
		for (int i = 0; i < getCustomerExaminations().size(); i++) {
			if (customerExaminations.get(i).getExaminationPkId().compareTo(examinationPkId) == 0)
				return i;
		}
		return -1;
	}

	public void getExaminationResults(CustomerExamination exam) {
		try {

			examinationResults = logicInspection.getExaminationValueQuestions(exam.getExaminationPkId());
			currentCustomerExaminations = logicInspection.getExaminationResultHeader(null, null,
					selectedCustomer.getPkId(), exam.getExaminationPkId());
			List<ExaminationValueHdr> results = logicInspection.getExaminationResults(null, null,
					selectedCustomer.getPkId(), exam.getExaminationPkId());

			for (ExaminationRequestCompleted completed : currentCustomerExaminations) {
				for (ExaminationResults result : getExaminationResults()) {
					List<Integer> index = findExaminationValueHdr(result.getEvqPkId(), completed.getPkId(), results);
					String concat = "";
					for (Integer i : index) {
						if (index.indexOf(i) == 0)
							concat += results.get(i).getValue();
						else
							concat += " - " + results.get(i).getValue();
					}
					result.getValues().add(concat);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
		RequestContext.getCurrentInstance().update("form:qop");
	}

	private List<Integer> findExaminationValueHdr(BigDecimal questionPkId, BigDecimal requestPkId,
			List<ExaminationValueHdr> results) {
		List<Integer> index = new ArrayList<Integer>();
		for (Integer i = 0; i < results.size(); i++) {
			if (results.get(i).getRequestPkId().compareTo(requestPkId) == 0
					&& results.get(i).getQuestionPkId().compareTo(questionPkId) == 0) {
				index.add(i);
			}
		}
		return index;
	}

	private void createInspectionTableData() {
		getListInspectionInfo().clear();
		getListMedicineInfo().clear();
		getListTreatmentInfo().clear();
		getOrderedTreatments().clear();
		for (Inspection inspection : listInspection) {
			CustomerInspectionInfo info = new CustomerInspectionInfo();
			CustomerInspectionInfo medicineInfo = new CustomerInspectionInfo();
			CustomerInspectionInfo treatmentInfo = new CustomerInspectionInfo();
			info.setSubOrgaName(inspection.getSubOrgaName());
			info.setInspectionDate(inspection.getInspectionDateDate());
			info.setEmployeeName(inspection.getEmployeeName());
			info.setInspectionDtl(new ArrayList<CustomerInspectionInfoDtl>());

			medicineInfo.setSubOrgaName(inspection.getSubOrgaName());
			medicineInfo.setInspectionDate(inspection.getInspectionDateDate());
			medicineInfo.setEmployeeName(inspection.getEmployeeName());
			medicineInfo.setInspectionDtl(new ArrayList<CustomerInspectionInfoDtl>());

			treatmentInfo.setSubOrgaName(inspection.getSubOrgaName());
			treatmentInfo.setInspectionDate(inspection.getInspectionDateDate());
			treatmentInfo.setEmployeeName(inspection.getEmployeeName());
			treatmentInfo.setInspectionDtl(new ArrayList<CustomerInspectionInfoDtl>());

			for (InspectionDtl inspectionDtl : listOcsDtl) {
				if (inspectionDtl.getInspectionPkId().equals(inspection.getPkId())) {
					CustomerInspectionInfoDtl dtl = new CustomerInspectionInfoDtl();
					dtl.setType(inspectionDtl.getType());
					dtl.setName(inspectionDtl.getName());
					dtl.setInspectionPkId(inspectionDtl.getInspectionPkId());

					info.getInspectionDtl().add(dtl);
				}
			}
			for (CustomerTreatment treatment : listCustomerTreatmentInfo) {
				if (treatment.getInspectionPkId().equals(inspection.getPkId())) {
					CustomerInspectionInfoDtl dtl = new CustomerInspectionInfoDtl();
					dtl.setType("TREATMENT");
					dtl.setName(treatment.getTreatmentName());
					dtl.setInspectionPkId(treatment.getInspectionPkId());

					info.getInspectionDtl().add(dtl);
					treatmentInfo.getInspectionDtl().add(dtl);
				}
			}
			for (CustomerMedicine cmedicine : listOcsCustomerMedicine) {
				if (cmedicine.getInspectionPkId().equals(inspection.getPkId())) {
					CustomerInspectionInfoDtl dtl = new CustomerInspectionInfoDtl();
					dtl.setType("MEDICINE");
					dtl.setName(cmedicine.getName());
					dtl.setInspectionPkId(cmedicine.getInspectionPkId());
					dtl.setDose(cmedicine.getDose());
					dtl.setExpireDay(cmedicine.getExpireDay());
					dtl.setRepeatCount(cmedicine.getRepeatCount());
					dtl.setRepeatTime(cmedicine.getRepeatType());

					info.getInspectionDtl().add(dtl);
					medicineInfo.getInspectionDtl().add(dtl);
				}
			}
			Collections.sort(info.getInspectionDtl());
			getListInspectionInfo().add(info);
			getListMedicineInfo().add(medicineInfo);
			// getListTreatmentInfo().add(treatmentInfo);
		}
	}

	public void getCustomerTreatment() {
		try {
			listTreatmentInfo = logicInspection.getCustomerTreatment(getBeginDate(), getEndDate(),
					selectedCustomer.getPkId(), filterTreatmentName, selectedTreatmentTypePkId);
			getOrderedTreatments();
			orderedTreatments = logicInspection.getCustomerTreatments(getBeginDate(), getEndDate(),
					selectedCustomer.getPkId(), filterTreatmentName, selectedTreatmentTypePkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getCustomerTreatmentDtl(BigDecimal ctPkId, String name) {

		try {
			chartData = "xAxis : { categories : [";
			List<CustomerTreatmentDtl> list = logicTr.getDtl(ctPkId);
			for (CustomerTreatmentDtl d : list) {
				chartData += "'" + d.getDstring(d.getTreatmentDate()) + "',";
			}

			chartData += "]}, series : [ { name: ' " + name + " ' ,  data : [";
			for (CustomerTreatmentDtl d : list) {
				chartData += d.getVas() + ",";
			}
			chartData += "]}]";

			System.out.println(chartData);

		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());

		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('charty').show();");
		context.execute("chartUpdate();");
		context.execute("lineChart();");

	}

	public void getCustomerSurgery() {
		try {
			listSurgeryInfo = logicInspection.getCustomerSurgery(getBeginDate(), getEndDate(),
					selectedCustomer.getPkId(), filterSurgeryName, selectedSurgeryTypePkId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createInspectionTreeNode(List<Inspection> listInspection) {
		getNodeInspection().getChildren().clear();
		setSelectedInspection(null);
		Inspection demoins = new Inspection();
		demoins.setOther("Бүгд");
		nodeAll = new CheckboxTreeNode("all", "Бүгд", nodeInspection);
		nodeAll.setExpanded(true);
		boolean isFound = false;
		List<SubOrganization> subOrgas = applicationController.getSubOrganization();
		for (SubOrganization orga : subOrgas) {
			CheckboxTreeNode orgaNode = new CheckboxTreeNode("subOrga", orga, null);
			for (Inspection inspection : listInspection) {
				if (inspection.getSubOrgaPkId().equals(orga.getPkId())) {
					CheckboxTreeNode node = new CheckboxTreeNode("inspection", inspection, null);
					if (inspection.getEmployeePkId().equals(currentEmployee.getPkId()) && !isFound) {
						node.setSelected(true);
						setSelectedInspection(inspection);
						isFound = true;
					}
					orgaNode.getChildren().add(node);
				}
			}
			if (orgaNode.getChildren().size() > 0) {
				orgaNode.setExpanded(true);
				nodeAll.getChildren().add(orgaNode);
			}
		}
	}

	public String onFlowProcess(FlowEvent event) {
		if (usedMedicine) {
			usedMedicine = false; // reset in case user goes back
			return "throat-allergy";
		} else {
			return event.getNewStep();
		}
	}

	public String saveTreatmentTemplate() {
		try {
			for (DoctorRecipe recipe : getDoctorRecipes()) {
				if (recipe.getName().equals(this.recipe.getName())) {
					userSessionController.showWarningMessage("Загварын нэр давхардсан байна");
					return "duplicate";
				}
			}

			recipe.setEmployeePkId(getCurrentEmployee().getPkId());
			recipe.setStatus(Tool.ADDED);
			List<InspectionDtl> dtls = new ArrayList<InspectionDtl>();
			List<DoctorRecipeDtl> recipeDtls = new ArrayList<DoctorRecipeDtl>();
			dtls.addAll(listExamination);
			dtls.addAll(listXray);
			dtls.addAll(listTreatment);
			dtls.addAll(listSurgery);
			for (InspectionDtl dtl : dtls) {
				DoctorRecipeDtl recipeDtl = new DoctorRecipeDtl();
				recipeDtl.setType(dtl.getType());
				recipeDtl.setTimes(dtl.getTimes());
				recipeDtl.setTypePkId(dtl.getTypePkId());
				recipeDtl.setStatus(Tool.ADDED);
				recipeDtl.setQty(dtl.getQty());
				recipeDtl.setDayLength(dtl.getDayLength());
				recipeDtls.add(recipeDtl);
			}
			for (CustomerMedicine dtl : listMedicine) {
				DoctorRecipeDtl recipeDtl = new DoctorRecipeDtl();
				recipeDtl.setType("MEDICINE");
				recipeDtl.setTypePkId(dtl.getMedicinePkId());
				recipeDtl.setQty(dtl.getDose().toString());
				recipeDtl.setTimes(dtl.getRepeatCount());
				recipeDtl.setDayLength(dtl.getExpireDay());
				recipeDtls.add(recipeDtl);
			}

			logicInspection.saveDoctorRecipe(recipe, recipeDtls, userSessionController.getLoggedInfo());
			doctorRecipes = logicInspection.getDoctorRecipe(getCurrentEmployee().getPkId());
			userSessionController.showMessage(99);
			RequestContext.getCurrentInstance().execute("updateDoctorRecipe();");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
		return "";
	}

	public void addTreatments() {
		try {
			List<DoctorRecipeDtl> dtls = logicInspection.getDoctorRecipeDtl(currentRecipe);
			for (DoctorRecipeDtl dtl : dtls) {
				InspectionDtl inspectionDtl = new InspectionDtl();
				inspectionDtl.setType(dtl.getType());
				inspectionDtl.setTypePkId(dtl.getTypePkId());
				inspectionDtl.setTimes(dtl.getTimes());
				inspectionDtl.setQty(dtl.getQty());
				inspectionDtl.setDayLength(dtl.getDayLength());
				inspectionDtl.setStatus(Tool.ADDED);
				inspectionDtl.setName(dtl.getName());

				if (dtl.getType().equals("EXAMINATION")) {
					getListExamination().add(inspectionDtl);
				} else if (dtl.getType().equals("XRAY")) {
					getListXray().add(inspectionDtl);
				} else if (dtl.getType().equals("TREATMENT")) {
					getListTreatment().add(inspectionDtl);
				} else if (dtl.getType().equals("SURGERY")) {
					getListSurgery().add(inspectionDtl);
				} else if (dtl.getType().equals("MEDICINE")) {
					CustomerMedicine medicine = new CustomerMedicine();
					medicine.setMedicinePkId(dtl.getTypePkId());
					medicine.setExpireDay(dtl.getDayLength());
					// medicine.setDose(new BigDecimal(dtl.getQty()));
					medicine.setRepeatCount(dtl.getTimes());
					medicine.setName(dtl.getName());
					medicine.setEmployeePkId(getCurrentEmployee().getPkId());
					medicine.setStatus(Tool.ADDED);
					listMedicine.add(medicine);
				}
			}

			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:inspection:healing-plan");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа.");
		}
	}

	public void onClose(CloseEvent event) {
		RequestContext.getCurrentInstance().execute("removeTile('" + event.getComponent().getId() + "')");
	}

	public void onToggle(ToggleEvent event) {
		RequestContext.getCurrentInstance()
				.execute("toggleTile('" + event.getComponent().getId() + "','" + event.getVisibility().name() + "')");
	}

	public void saveMemo() {
		try {
			employeeMemo.setCustomerPkId(selectedCustomer.getPkId());
			employeeMemo.setNoteType(0);
			logicInspection.saveEmployeeMemo(employeeMemo, userSessionController.getLoggedInfo());
			employeeMemo = new EmployeeMemo();
			employeeMemo.setStatus(Tool.ADDED);
			employeeMemoList = logicInspection.getPrivateMemo(userSessionController.getLoggedInfo().getEmployeePkId(),
					selectedCustomer.getPkId());
			RequestContext.getCurrentInstance().update("form:memo-panel");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}

	public void saveMemoPublic() {
		try {
			employeeMemoPublic.setCustomerPkId(selectedCustomer.getPkId());
			employeeMemoPublic.setNoteType(1);
			logicInspection.saveEmployeeMemo(employeeMemoPublic, userSessionController.getLoggedInfo());
			employeeMemoPublic = new EmployeeMemo();
			employeeMemoPublic.setStatus(Tool.ADDED);
			employeeMemoPublicList = logicInspection.getPublicMemo(selectedCustomer.getPkId());
			RequestContext.getCurrentInstance().update("form:memo-panel");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл хадгалахад алдаа гарлаа.");
		}
	}

	public void removeMemo(EmployeeMemo memo) {
		try {
			memo.setStatus(Tool.DELETE);
			if (memo.getNoteType() == 1)
				employeeMemoPublicList.remove(memo);
			else
				employeeMemoList.remove(memo);
			logicInspection.saveEmployeeMemo(memo, userSessionController.getLoggedInfo());
			RequestContext.getCurrentInstance().update("form:memo-panel");
		} catch (Exception e) {
			e.printStackTrace();
			userSessionController.showErrorMessage("Өгөгдөл устгахад алдаа гарлаа.");
		}
	}
	// EndRegion

	// Region - GETTER SETTER

	public InspectionController() {

	}

	public BigDecimal getCurrentConPrePkId() {
		return currentConPrePkId;
	}

	public void setCurrentConPrePkId(BigDecimal currentConPrePkId) {
		this.currentConPrePkId = currentConPrePkId;
	}

	public ApplicationController getApplicationController() {
		return applicationController;
	}

	public List<ConditionalPrescription> getListConPre() {
		if (listConPre == null)
			listConPre = new ArrayList<ConditionalPrescription>();
		return listConPre;
	}

	public void setListConPre(List<ConditionalPrescription> listConPre) {
		this.listConPre = listConPre;
	}

	public void setApplicationController(ApplicationController applicationController) {
		this.applicationController = applicationController;
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public SubOrganization getCurrentSubOrganization() {
		return currentSubOrganization;

	}

	public void setCurrentSubOrganization(SubOrganization subOrganization) {
		this.currentSubOrganization = subOrganization;
	}

	public Inspection getCurrentInspection() {
		if (currentInspection == null)
			newInspection();
		return currentInspection;
	}

	public void setCurrentInspection(Inspection currentInspection) {
		this.currentInspection = currentInspection;
	}

	public Customer getSelectedCustomer() {
		if (selectedCustomer == null)
			selectedCustomer = new Customer();
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public String getCurrentDate() {
		currentDate = new Date();
		String dateString = new SimpleDateFormat("dd-MM-yyyy").format(this.currentDate);
		return dateString;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getFilterKey() {
		if (filterKey == null)
			filterKey = "";
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public void NewCustomerRequest(CustomerRequest cr) {
		currentCustomerRequest = cr;
	}

	public CustomerRequest getCurrentCustomerRequest() {
		if (currentCustomerRequest == null)
			currentCustomerRequest = new CustomerRequest();
		return currentCustomerRequest;
	}

	public void setCurrentCustomerRequest(CustomerRequest currentCustomerRequest) {
		this.currentCustomerRequest = currentCustomerRequest;
	}

	public Employee getCurrentEmployee() {
		if (currentEmployee == null)
			currentEmployee = new Employee();
		return currentEmployee;
	}

	public void setCurrentEmployee(Employee currentEmployee) {
		this.currentEmployee = currentEmployee;
	}

	public EmployeeRequest getCurrentEmployeeRequest() {
		if (currentEmployeeRequest == null)
			currentEmployeeRequest = new EmployeeRequest();
		return currentEmployeeRequest;
	}

	public void setCurrentEmployeeRequest(EmployeeRequest currentEmployeeRequest) {
		this.currentEmployeeRequest = currentEmployeeRequest;
	}

	public void saveXrayRequest(XrayRequest dr) {

		try {
			logicInspection.saveXrayRequest(dr);
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void deleteImage(XrayRequest dr) {
		try {
			dr.setImage(null);
			logicInspection.saveXrayRequest(dr);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public Date getFilterDate1() {
		if (filterDate1 == null)
			filterDate1 = new Date();
		filterDate1.setHours(0);
		filterDate1.setMinutes(0);
		return filterDate1;
	}

	public void setFilterDate1(Date filterDate1) {
		this.filterDate1 = filterDate1;
	}

	public Date getFilterDate2() {
		if (filterDate2 == null)
			filterDate2 = new Date();
		filterDate2.setHours(23);
		filterDate2.setMinutes(59);
		return filterDate2;
	}

	public void setFilterDate2(Date filterDate2) {
		this.filterDate2 = filterDate2;
	}

	public List<CustomerRequest> getCustomerRequests() {
		if (customerRequests == null)
			customerRequests = new ArrayList<CustomerRequest>();
		return customerRequests;
	}

	public void setCustomerRequests(List<CustomerRequest> customerRequests) {
		this.customerRequests = customerRequests;
	}

	public List<CustomerRequest> getErs() {
		if (ers == null)
			ers = new ArrayList<CustomerRequest>();
		return ers;
	}

	public void setErs(List<CustomerRequest> ers) {
		this.ers = ers;
	}

	public int getCountDone() {
		countDone = 0;
		if (customerRequests == null)
			return 0;
		else
			for (CustomerRequest cr : customerRequests) {
				if (cr.getEmployeeRequest().getInspectionStatus().equals("Орсон"))
					countDone++;
			}
		return countDone;
	}

	public void setCountDone(int countDone) {
		this.countDone = countDone;
	}

	public int getCountYet() {
		countYet = 0;
		if (customerRequests == null)
			return 0;
		else
			for (CustomerRequest cr : customerRequests) {
				if (cr.getEmployeeRequest().getInspectionStatus().equals("Орох"))
					countYet++;
			}
		return countYet;
	}

	public void setCountYet(int countYet) {
		this.countYet = countYet;
	}

	public int getGuestComecount() {
		guestComecount = 0;
		if (customerRequests == null)
			return 0;
		else
			for (CustomerRequest cs : customerRequests) {
				if (cs.getEmployeeRequest().getGuest() == 1)
					guestComecount++;
			}
		return guestComecount;
	}

	public void setGuestComecount(int guestComecount) {
		this.guestComecount = guestComecount;
	}

	public int getCountpain() {
		countpain = 0;
		if (customerRequests == null) {
			return 0;
		} else
			for (CustomerRequest cs : customerRequests) {
				if (cs.getInspection().getInspectionStatus().equals("анхан")) {
					countpain++;
				}
			}
		return countpain;
	}

	public void setCountpain(int countpain) {
		this.countpain = countpain;
	}

	public int getTempSave() {
		tempSave = 0;
		if (customerRequests == null)
			return 0;
		else
			for (CustomerRequest cr : customerRequests) {
				if (cr.getEmployeeRequest().getInspectionStatus().equals("Түр хадгалсан"))
					tempSave++;
			}
		return tempSave;
	}

	public void setTempSave(int tempSave) {
		this.tempSave = tempSave;
	}

	public int getCountRepeat() {
		countRepeat = 0;
		if (customerRequests == null)
			return 0;
		else
			for (CustomerRequest cr : customerRequests) {
				if (cr.getEmployeeRequest().getInspectionStatus().equals("Дахин үзлэг"))
					countRepeat++;
			}
		return countRepeat;
	}

	public void setCountRepeat(int countRepeat) {
		this.countRepeat = countRepeat;
	}

	public XrayRequest getCurrentXrayRequest() {
		if (currentXrayRequest == null)
			currentXrayRequest = new XrayRequest();
		return currentXrayRequest;
	}

	public void setCurrentXrayRequest(XrayRequest currentXrayRequest) {
		this.currentXrayRequest = currentXrayRequest;
	}

	public boolean isHasUldegdel() {
		return hasUldegdel;
	}

	public void setHasUldegdel(boolean hasUldegdel) {
		this.hasUldegdel = hasUldegdel;
	}

	public Date getOrderDate() {
		if (orderDate == null)
			orderDate = new Date();
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isSelectCustomer() {
		return selectCustomer;
	}

	public List<ConditionalPrescriptionDtl> getListConPreDtl() {
		if (listConPreDtl == null)
			listConPreDtl = new ArrayList<ConditionalPrescriptionDtl>();
		return listConPreDtl;
	}

	public void setListConPreDtl(List<ConditionalPrescriptionDtl> listConPreDtl) {
		this.listConPreDtl = listConPreDtl;
	}

	public List<CustomerMedicine> getListMedicine() {
		if (listMedicine == null)
			listMedicine = new ArrayList<CustomerMedicine>();
		return listMedicine;
	}

	public void setListMedicine(List<CustomerMedicine> listMedicine) {
		this.listMedicine = listMedicine;
	}

	public List<InspectionDtl> getListExamination() {
		if (listExamination == null)
			listExamination = new ArrayList<InspectionDtl>();
		return listExamination;
	}

	public void setListExamination(List<InspectionDtl> listExamination) {
		this.listExamination = listExamination;
	}

	public List<InspectionDtl> getListTreatment() {
		if (listTreatment == null)
			listTreatment = new ArrayList<InspectionDtl>();
		return listTreatment;
	}

	public void setListTreatment(List<InspectionDtl> listTreatment) {
		this.listTreatment = listTreatment;
	}

	public List<InspectionDtl> getListXray() {
		if (listXray == null)
			listXray = new ArrayList<InspectionDtl>();
		return listXray;
	}

	public void setListXray(List<InspectionDtl> listXray) {
		this.listXray = listXray;
	}

	public List<CustomerDiagnose> getListDiagnose() {
		if (listDiagnose == null)
			listDiagnose = new ArrayList<CustomerDiagnose>();
		return listDiagnose;
	}

	public void setListDiagnose(List<CustomerDiagnose> listDiagnose) {
		this.listDiagnose = listDiagnose;
	}

	public void setSelectCustomer(boolean selectCustomer) {
		this.selectCustomer = selectCustomer;
	}

	public List<TreatmentType> getTreatmentTypes() {
		if (treatmentTypes == null)
			treatmentTypes = new ArrayList<TreatmentType>();
		return treatmentTypes;
	}

	public void setTreatmentTypes(List<TreatmentType> treatmentTypes) {
		this.treatmentTypes = treatmentTypes;
	}

	public List<Treatment> getTtt() {
		if (ttt == null) {
			ttt = new ArrayList<Treatment>();
		}
		return ttt;
	}

	public void setTtt(List<Treatment> ttt) {
		this.ttt = ttt;
	}

	public List<Treatment> getChosenTreatments() {
		if (chosenTreatments == null)
			chosenTreatments = new ArrayList<Treatment>();
		return chosenTreatments;
	}

	public void setChosenTreatments(List<Treatment> chosenTreatments) {
		this.chosenTreatments = chosenTreatments;
	}

	public Treatment getCursorTreatment() {
		if (cursorTreatment == null)
			cursorTreatment = new Treatment();
		return cursorTreatment;
	}

	public void setCursorTreatment(Treatment cursorTreatment) {
		this.cursorTreatment = cursorTreatment;
	}

	public InspectionDtl getCurrentTreatmentDtl() {
		return currentInspectionDtl;
	}

	public void setCurrentTreatmentDtl(InspectionDtl currentInspectionDtl) {
		this.currentInspectionDtl = currentInspectionDtl;
	}

	public String getDiagnoseFilterKey() {
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

	public List<Examination> getExaminations() {
		if (examinations == null)
			examinations = new ArrayList<Examination>();
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public List<Examination> getExaTempList() {
		if (exaTempList == null)
			exaTempList = new ArrayList<Examination>();
		return exaTempList;
	}

	public void setExaTempList(List<Examination> exaTempList) {
		this.exaTempList = exaTempList;
	}

	public void setCurrentExaminationInspectionDtl(InspectionDtl currentInspectionDtl) {
		this.currentInspectionDtl = currentInspectionDtl;
	}

	public void setCurrentExaminationInspectionDtl(ExaminationDtl currentExaminationDtl) {
		this.currentExaminationDtl = currentExaminationDtl;
	}

	public InspectionDtl getCurrentInspectionDtl() {
		return currentInspectionDtl;
	}

	public void setCurrentInspectionDtl(InspectionDtl currentInspectionDtl) {
		this.currentInspectionDtl = currentInspectionDtl;
	}

	public ExaminationDtl getCurrentExaminationDtl() {
		return currentExaminationDtl;
	}

	public void setCurrentExaminationDtl(ExaminationDtl currentExaminationDtl) {
		this.currentExaminationDtl = currentExaminationDtl;
	}

	public ExaminationType getChosenExaType() {
		return chosenExaType;
	}

	public void setChosenExaType(ExaminationType chosenExaType) {
		this.chosenExaType = chosenExaType;
	}

	public Examination getChosenExamination() {
		return chosenExamination;
	}

	public void setChosenExamination(Examination chosenExamination) {
		this.chosenExamination = chosenExamination;
	}

	public List<XrayType> getXrayTypes() {
		if (xrayTypes == null)
			xrayTypes = new ArrayList<XrayType>();
		return xrayTypes;
	}

	public void setXrayTypes(List<XrayType> xrayTypes) {
		this.xrayTypes = xrayTypes;
	}

	public List<Xray> getXrays() {
		return xrays;
	}

	public void setXrays(List<Xray> xrays) {
		this.xrays = xrays;
	}

	public List<Xray> getXrayTempList() {
		if (xrayTempList == null)
			xrayTempList = new ArrayList<Xray>();
		return xrayTempList;
	}

	public void setXrayTempList(List<Xray> xrayTempList) {
		this.xrayTempList = xrayTempList;
	}

	public XrayType getChosenXrayType() {
		return chosenXrayType;
	}

	public void setChosenXrayType(XrayType chosenXrayType) {
		this.chosenXrayType = chosenXrayType;
	}

	public Xray getChosenXray() {
		return chosenXray;
	}

	public void setChosenXray(Xray chosenXray) {
		this.chosenXray = chosenXray;
	}

	public List<Medicine> getMedicines() {
		if (medicines == null)
			medicines = new ArrayList<Medicine>();
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public List<Medicine> getMedTempList() {
		if (medTempList == null)
			medTempList = new ArrayList<Medicine>();
		return medTempList;
	}

	public void setMedTempList(List<Medicine> medTempList) {
		this.medTempList = medTempList;
	}

	public Medicine getChosenMedicine() {
		return chosenMedicine;
	}

	public void setChosenMedicine(Medicine chosenMedicine) {
		this.chosenMedicine = chosenMedicine;
	}

	public List<SubOrganization> getSubOrganizations() {
		if (subOrganizations == null) {
			try {
				subOrganizations = infoLogic.getSubOrganizations();
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return subOrganizations;
	}

	public void setSubOrganizations(List<SubOrganization> subOrganizations) {
		this.subOrganizations = subOrganizations;
	}

	public List<Employee> getEmployees() {
		if (employees == null)
			employees = new ArrayList<Employee>();
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public List<String> getSelectedOcsSubOrgaPkId() {
		if (selectedOcsSubOrgaPkId == null)
			selectedOcsSubOrgaPkId = new ArrayList<String>();
		return selectedOcsSubOrgaPkId;
	}

	public void setSelectedOcsSubOrgaPkId(List<String> selectedOcsSubOrgaPkId) {
		this.selectedOcsSubOrgaPkId = selectedOcsSubOrgaPkId;
	}

	public List<String> getSelectedOcsEmployee() {
		if (selectedOcsEmployee == null)
			selectedOcsEmployee = new ArrayList<String>();
		return selectedOcsEmployee;
	}

	public void setSelectedOcsEmployee(List<String> selectedOcsEmployee) {
		this.selectedOcsEmployee = selectedOcsEmployee;
	}

	public List<String> getSelectedErmSubOrgaPkId() {
		if (selectedErmSubOrgaPkId == null)
			selectedErmSubOrgaPkId = new ArrayList<String>();
		return selectedErmSubOrgaPkId;
	}

	public void setSelectedErmSubOrgaPkId(List<String> selectedErmSubOrgaPkId) {
		this.selectedErmSubOrgaPkId = selectedErmSubOrgaPkId;
	}

	public List<String> getSelectedErmEmployee() {
		if (selectedErmEmployee == null)
			selectedErmEmployee = new ArrayList<String>();
		return selectedErmEmployee;
	}

	public void setSelectedErmEmployee(List<String> selectedErmEmployee) {
		this.selectedErmEmployee = selectedErmEmployee;
	}

	public TreeNode getNodeInspection() {
		if (nodeInspection == null)
			nodeInspection = new CheckboxTreeNode("Үзлэгүүд", null);
		return nodeInspection;
	}

	public void setNodeInspection(TreeNode nodeInspection) {
		this.nodeInspection = nodeInspection;
	}

	public TreeNode[] getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode[] selectedNode) {
		this.selectedNode = selectedNode;
	}

	public boolean isEmr() {
		return emr;
	}

	public void setEmr(boolean emr) {
		this.emr = emr;
	}

	public boolean isOcs() {
		return ocs;
	}

	public void setOcs(boolean ocs) {
		this.ocs = ocs;
	}

	public TreeNode getNodeAll() {
		return nodeAll;
	}

	public void setNodeAll(TreeNode nodeAll) {
		this.nodeAll = nodeAll;
	}

	public List<CustomerInspectionInfo> getListInspectionInfo() {
		if (listInspectionInfo == null)
			listInspectionInfo = new ArrayList<CustomerInspectionInfo>();
		return listInspectionInfo;
	}

	public void setListInspectionInfo(List<CustomerInspectionInfo> listInspectionInfo) {
		this.listInspectionInfo = listInspectionInfo;
	}

	public List<CustomerMedicine> getListOcsCustomerMedicine() {
		return listOcsCustomerMedicine;
	}

	public void setListOcsCustomerMedicine(List<CustomerMedicine> listOcsCustomerMedicine) {
		this.listOcsCustomerMedicine = listOcsCustomerMedicine;
	}

	public List<CustomerInspectionInfo> getListMedicineInfo() {
		if (listMedicineInfo == null)
			listMedicineInfo = new ArrayList<CustomerInspectionInfo>();
		return listMedicineInfo;
	}

	public void setListMedicineInfo(List<CustomerInspectionInfo> listMedicineInfo) {
		this.listMedicineInfo = listMedicineInfo;
	}

	public List<CustomerXray> getListCustomerXrayInfo() {
		if (listCustomerXrayInfo == null)
			listCustomerXrayInfo = new ArrayList<CustomerXray>();
		return listCustomerXrayInfo;
	}

	public void setListCustomerXrayInfo(List<CustomerXray> listCustomerXrayInfo) {
		this.listCustomerXrayInfo = listCustomerXrayInfo;
	}

	public List<CustomerTreatment> getListCustomerTreatmentInfo() {
		if (listCustomerTreatmentInfo == null)
			listCustomerTreatmentInfo = new ArrayList<CustomerTreatment>();
		return listCustomerTreatmentInfo;
	}

	public void setListCustomerTreatmentInfo(List<CustomerTreatment> listCustomerTreatmentInfo) {
		this.listCustomerTreatmentInfo = listCustomerTreatmentInfo;
	}

	public List<InspectionDtl> getListTreatmentInfo() {
		if (listTreatmentInfo == null)
			listTreatmentInfo = new ArrayList<InspectionDtl>();
		return listTreatmentInfo;
	}

	public void setListTreatmentInfo(List<InspectionDtl> listTreatmentInfo) {
		this.listTreatmentInfo = listTreatmentInfo;
	}

	public List<ExaminationRequestCompleted> getListExaminationRequestCompleted() {
		if (listExaminationRequestCompleted == null)
			listExaminationRequestCompleted = new ArrayList<ExaminationRequestCompleted>();
		return listExaminationRequestCompleted;
	}

	public void setListExaminationRequestCompleted(List<ExaminationRequestCompleted> listExaminationRequestCompleted) {
		this.listExaminationRequestCompleted = listExaminationRequestCompleted;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isHp() {
		return hp;
	}

	public void setHp(boolean hp) {
		this.hp = hp;
	}

	public boolean isUsedMedicine() {
		return usedMedicine;
	}

	public void setUsedMedicine(boolean usedMedicine) {
		this.usedMedicine = usedMedicine;
	}

	public CustomerNose getCustomerNose() {
		if (customerNose == null)
			customerNose = new CustomerNose();
		return customerNose;
	}

	public void setCustomerNose(CustomerNose customerNose) {
		this.customerNose = customerNose;
	}

	public CustomerEar getCustomerEar() {
		if (customerEar == null)
			customerEar = new CustomerEar();
		return customerEar;
	}

	public void setCustomerEar(CustomerEar customerEar) {
		this.customerEar = customerEar;
	}

	public boolean isPre() {
		return pre;
	}

	public void setPre(boolean pre) {
		this.pre = pre;
	}

	public CustomerThroat getCustomerThroat() {
		if (customerThroat == null)
			customerThroat = new CustomerThroat();
		return customerThroat;
	}

	public void setCustomerThroat(CustomerThroat customerThroat) {
		this.customerThroat = customerThroat;
	}

	public CustomerLung getCustomerLung() {
		if (customerLung == null)
			customerLung = new CustomerLung();
		return customerLung;
	}

	public void setCustomerLung(CustomerLung customerLung) {
		this.customerLung = customerLung;
	}

	public List<InspectionDtl> getListSurgery() {
		if (listSurgery == null)
			listSurgery = new ArrayList<InspectionDtl>();
		return listSurgery;
	}

	public void setListSurgery(List<InspectionDtl> listSurgery) {
		this.listSurgery = listSurgery;
	}

	public List<SurgeryType> getSurgeryTypes() {
		return surgeryTypes;
	}

	public void setSurgeryTypes(List<SurgeryType> surgeryTypes) {
		this.surgeryTypes = surgeryTypes;
	}

	public List<Surgery> getSurgeries() {
		return surgeries;
	}

	public void setSurgeries(List<Surgery> surgeries) {
		this.surgeries = surgeries;
	}

	public List<Surgery> getSurgeryTempList() {
		if (surgeryTempList == null)
			surgeryTempList = new ArrayList<Surgery>();
		return surgeryTempList;
	}

	public void setSurgeryTempList(List<Surgery> surgeryTempList) {
		this.surgeryTempList = surgeryTempList;
	}

	public Surgery getChosenSurgery() {
		return chosenSurgery;
	}

	public void setChosenSurgery(Surgery chosenSurgery) {
		this.chosenSurgery = chosenSurgery;
	}

	public SurgeryType getChosenSurgeryType() {
		return chosenSurgeryType;
	}

	public void setChosenSurgeryType(SurgeryType chosenSurgeryType) {
		this.chosenSurgeryType = chosenSurgeryType;
	}

	public int getCurrentInspectionPage() {
		return currentInspectionPage;
	}

	public void setCurrentInspectionPage(int currentInspectionPage) {
		this.currentInspectionPage = currentInspectionPage;
	}

	public DoctorRecipe getRecipe() {
		if (recipe == null)
			recipe = new DoctorRecipe();
		return recipe;
	}

	public void setRecipe(DoctorRecipe recipe) {
		this.recipe = recipe;
	}

	public List<DoctorRecipe> getDoctorRecipes() {
		if (doctorRecipes == null) {
			doctorRecipes = new ArrayList<>();
			try {
				if (currentEmployee != null)
					doctorRecipes = logicInspection.getDoctorRecipe(currentEmployee.getPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return doctorRecipes;
	}

	public void setDoctorRecipes(List<DoctorRecipe> doctorRecipes) {
		this.doctorRecipes = doctorRecipes;
	}

	public BigDecimal getCurrentRecipe() {
		return currentRecipe;
	}

	public void setCurrentRecipe(BigDecimal currentRecipe) {
		this.currentRecipe = currentRecipe;
	}

	public List<Integer> getYears() {
		if (years == null) {
			years = new ArrayList<Integer>();
			for (int i = 1900; i < new Date().getYear() + 1900; i++) {
				years.add(i);
			}
		}
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<CustomerExamination> getCustomerExaminations() {
		if (customerExaminations == null)
			customerExaminations = new ArrayList<CustomerExamination>();
		return customerExaminations;
	}

	public void setCustomerExaminations(List<CustomerExamination> customerExaminations) {
		this.customerExaminations = customerExaminations;
	}

	public List<ExaminationResults> getExaminationResults() {
		if (examinationResults == null)
			examinationResults = new ArrayList<ExaminationResults>();
		return examinationResults;
	}

	public void setExaminationResults(List<ExaminationResults> examinationResults) {
		this.examinationResults = examinationResults;
	}

	public List<ExaminationRequestCompleted> getCurrentCustomerExaminations() {
		if (currentCustomerExaminations == null)
			currentCustomerExaminations = new ArrayList<ExaminationRequestCompleted>();
		return currentCustomerExaminations;
	}

	public void setCurrentCustomerExaminations(List<ExaminationRequestCompleted> currentCustomerExaminations) {
		this.currentCustomerExaminations = currentCustomerExaminations;
	}

	public List<Diagnose> getApprovedFilteredDiagnose() {
		if (approvedFilteredDiagnose == null)
			approvedFilteredDiagnose = new ArrayList<Diagnose>();
		return approvedFilteredDiagnose;
	}

	public void setApprovedFilteredDiagnose(List<Diagnose> approvedFilteredDiagnose) {
		this.approvedFilteredDiagnose = approvedFilteredDiagnose;
	}

	public List<String> getSelectedFilter() {
		if (selectedFilter == null)
			selectedFilter = new ArrayList<String>();
		return selectedFilter;
	}

	public void setSelectedFilter(List<String> selectedFilter) {
		this.selectedFilter = selectedFilter;
	}

	public List<JSONObject> getParentList() {
		if (parentList == null)
			parentList = new ArrayList<JSONObject>();
		return parentList;
	}

	public void setParentList(List<JSONObject> parentList) {
		this.parentList = parentList;
	}

	public List<InspectionDetail> getInspectionDetails() {
		if (inspectionDetails == null)
			inspectionDetails = new ArrayList<InspectionDetail>();
		return inspectionDetails;
	}

	public void setInspectionDetails(List<InspectionDetail> inspectionDetails) {
		this.inspectionDetails = inspectionDetails;
	}

	public List<EmployeeMemo> getEmployeeMemoList() {
		if (employeeMemoList == null)
			employeeMemoList = new ArrayList<EmployeeMemo>();
		return employeeMemoList;
	}

	public void setEmployeeMemoList(List<EmployeeMemo> employeeMemoList) {
		this.employeeMemoList = employeeMemoList;
	}

	public EmployeeMemo getEmployeeMemo() {
		if (employeeMemo == null) {
			employeeMemo = new EmployeeMemo();
			employeeMemo.setStatus(Tool.ADDED);
		}
		return employeeMemo;
	}

	public void setEmployeeMemo(EmployeeMemo employeeMemo) {
		this.employeeMemo = employeeMemo;
	}

	public EmployeeMemo getEmployeeMemoPublic() {
		if (employeeMemoPublic == null) {
			employeeMemoPublic = new EmployeeMemo();
			employeeMemoPublic.setStatus(Tool.ADDED);
		}
		return employeeMemoPublic;
	}

	public void setEmployeeMemoPublic(EmployeeMemo employeeMemoPublic) {
		this.employeeMemoPublic = employeeMemoPublic;
	}

	public List<EmployeeMemo> getEmployeeMemoPublicList() {
		if (employeeMemoPublicList == null)
			employeeMemoPublicList = new ArrayList<EmployeeMemo>();
		return employeeMemoPublicList;
	}

	public void setEmployeeMemoPublicList(List<EmployeeMemo> employeeMemoPublicList) {
		this.employeeMemoPublicList = employeeMemoPublicList;
	}

	public int getFilterProblemList() {
		return filterProblemList;
	}

	public void setFilterProblemList(int filterProblemList) {
		this.filterProblemList = filterProblemList;
	}

	public int getFilterProblemListMonth() {
		return filterProblemListMonth;
	}

	public void setFilterProblemListMonth(int filterProblemListMonth) {
		this.filterProblemListMonth = filterProblemListMonth;
	}

	public List<CustomerProblem> getListProblem() {
		if (listProblem == null)
			listProblem = new ArrayList<CustomerProblem>();
		return listProblem;
	}

	public void setListProblem(List<CustomerProblem> listProblem) {
		this.listProblem = listProblem;
	}
	// EndRegion

	private String inspectionDtlDialogTitle = "Жор";
	private List<Employee> listEmployee;
	private BigDecimal selectedSubOrganizationPkId;
	private BigDecimal selectedEmployeePkId;

	private TreeNode listExaminationTmpTree;

	private BigDecimal ocsSumAmount;
	private String listTmpTabStr;
	private List<CustomerMedicine> listMedicineTmp;
	private List<InspectionDtl> listExaminationTmp;
	private List<InspectionDtl> listXrayTmp;
	private List<InspectionDtl> listSurgeryTmp;
	private List<InspectionDtl> listTreatmentTmp;

	private List<CustomerMedicine> medicineTmp;
	private List<InspectionDtl> examinationTmp;
	private List<InspectionDtl> xrayTmp;
	private List<InspectionDtl> surgeryTmp;
	private List<InspectionDtl> treatmentTmp;

	private Date fillterDateBegin;
	private Date fillterDateEnd;
	private List<Inspection> inspections;
	private Inspection selInspection;
	private boolean firstVisit;
	private TreeNode inspectionRoot;
	private TreeNode doctorRecipeRoot;
	private TreeNode conditionalPrescription;
	private List<ConditionalPrescription> conditionalPrescriptions;
	private ConditionalPrescription selConditionalPrescription;
	private List<String> organList;
	// TOOL
	private List<CustomerMedicine> lstMedicine;
	private List<InspectionDtl> lstExamination;
	private List<InspectionDtl> lstXray;
	private List<InspectionDtl> lstSurgery;
	private List<InspectionDtl> lstTreatment;
	private List<String> lstMedicineStr;
	private List<String> lstExaminationStr;
	private List<String> lstXrayStr;
	private List<String> lstSurgeryStr;
	private List<String> lstTreatmentStr;
	// LOG
	private List<CustomerMedicine> listMedicineLog;
	private List<InspectionDtl> listExaminationLog;
	private List<InspectionDtl> listXrayLog;
	private List<InspectionDtl> listSurgeryLog;
	private List<InspectionDtl> listTreatmentLog;

	// SEARCH
	private String medicineSearchString;
	private String examinationSearchString;
	private String xraySearchString;
	private String surgerySearchString;
	private String treatmentSearchString;

	private String inspectionType;

	// EXAMINATION TREE NODE
	private TreeNode examinationTreeRoot;
	private TreeNode exaTempListRoot;
	private TreeNode selectedExaminationTreeNode;

	public TreeNode getExaTempListRoot() {
		if (exaTempListRoot == null) {
			exaTempListRoot = new DefaultTreeNode(new Examination(), null);
			for (Examination examination : getExaTempList()) {
				examination.setStatus("EXAM");
				TreeNode exam = new DefaultTreeNode(examination, exaTempListRoot);
				exam.setExpanded(true);
				if (examination.getExaminationDtls() != null && examination.getExaminationDtls().size() > 0) {
					for (ExaminationDtl dtl : examination.getExaminationDtls()) {
						dtl.setStatus("DTL");
						dtl.setSelected(true);
						dtl.setExamination(examination);
						new DefaultTreeNode(dtl, exam);
					}
				}
			}
		}
		return exaTempListRoot;
	}

	public void setExaTempListRoot(TreeNode exaTempListRoot) {
		this.exaTempListRoot = exaTempListRoot;
	}

	public TreeNode getExaminationTreeRoot() {
		if (examinationTreeRoot == null) {
			examinationTreeRoot = new DefaultTreeNode(new Examination(), null);
			for (Examination examination : getExaminations()) {
				examination.setStatus("EXAM");
				TreeNode exam = new DefaultTreeNode(examination, examinationTreeRoot);
				if (examination.getExaminationDtls() != null && examination.getExaminationDtls().size() > 0) {
					for (ExaminationDtl dtl : examination.getExaminationDtls()) {
						dtl.setStatus("DTL");
						dtl.setSelected(true);
						dtl.setExamination(examination);
						new DefaultTreeNode(dtl, exam);
					}
				}
			}
		}
		return examinationTreeRoot;
	}

	public void setExaminationTreeRoot(TreeNode examinationTreeRoot) {
		this.examinationTreeRoot = examinationTreeRoot;
	}

	public String getInspectionType() {
		return inspectionType;
	}

	public void setInspectionType(String inspectionType) {
		if (this.inspectionType == null || this.inspectionType.isEmpty()) {
			this.inspectionType = "doctor_register4";
		}
		this.inspectionType = inspectionType;
	}

	public String changeInspectionType(String string) {
		if (string.equals("ocs"))
			this.inspectionType = string;
		else if (string.equals("emr")) {
			try {
				subOrgaType = logicInspection.getSubOrgaTypePkId(currentEmployee.getSubOrganizationPkId());

				if (Tool.EARNOSETHROAT.equals(subOrgaType)) {
					inspectionType = "doctor_register4";
				} else if (Tool.SKIN.equals(subOrgaType)) {
					inspectionType = "doctor_register2";
				} else if (Tool.LUNG.equals(subOrgaType)) {
					inspectionType = "doctor_register1";
				} else if (Tool.HEMATOLOGY.equals(subOrgaType)) {
					inspectionType = "doctor_register6";
				} else if (Tool.CARDIOLOGY.equals(subOrgaType)) {
					inspectionType = "doctor_register7";
				} else if (Tool.KIDNEY.equals(subOrgaType)) {
					inspectionType = "doctor_register8";
				} else if (Tool.EYE.equals(subOrgaType)) {
					inspectionType = "doctor_register9";
				} else if (Tool.TRADITION.equals(subOrgaType)) {
					inspectionType = "doctor_register10";
				} else if (Tool.REHABILITATION.equals(subOrgaType)) {
					inspectionType = "doctor_rehabilitation";
				} else {
					inspectionType = "doctor_register";
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return inspectionType;
	}

	public void medicineSearchString() {
		try {
			setMedicines(infoLogic.getMedicine(BigDecimal.ZERO, userSessionController.getLoggedInfo(),
					medicineSearchString));
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

	}

	public void examinationSearchString() {
		try {
			setExaminations(examLogic.getExaminationList(examinationSearchString, true));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void xraySearchString() {
		try {
			setXrays(logicXray.getXrayByDianose(xraySearchString));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void surgerySearchString() {
		try {
			setSurgeries(logicSurgery.getSurgery(surgerySearchString,
					userSessionController.getLoggedInfo().getOrganization().getPkId(), false));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void treatmentSearchString() {
		try {
			ttt = logicTreatment.getTreatments(userSessionController.getLoggedInfo(), treatmentSearchString);
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void setListgetCurrentInspectionDtl(BigDecimal inspectionPkId) {
		if (inspectionPkId == null || BigDecimal.ZERO.compareTo(inspectionPkId) == 0)
			return;

		try {
			HashMap<String, Object> map = logicInspection.getDtlByInspectionPkId(inspectionPkId);
			listMedicine = (List<CustomerMedicine>) map.get("listMedicineLog");
			listExamination = (List<InspectionDtl>) map.get("listExaminationLog");
			listXray = (List<InspectionDtl>) map.get("listXrayLog");
			listSurgery = (List<InspectionDtl>) map.get("listSurgeryLog");
			listTreatment = (List<InspectionDtl>) map.get("listTreatmentLog");
		} catch (Exception ex) {
			ex.printStackTrace();
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void showLastInspection() {
		if (getInspections().size() > 0) {
			setListLog(getInspections().get(0).getPkId());
			inspectionDtlDialogTitle = "Сүүлийн үзлэг ";
			try {
				inspectionDtlDialogTitle += logicConPre
						.getSubOrganizationNameByInspectionPkId(getInspections().get(0).getPkId()) + ", ";
				inspectionDtlDialogTitle += logicConPre
						.getEmployeeNameByInspectionPkId(getInspections().get(0).getPkId()) + ", ";
				inspectionDtlDialogTitle += getInspections().get(0).getInspectionDate();
			} catch (Exception ex) {
				getUserSessionController().showWarningMessage(ex.getMessage());
			}
		}
	}

	public void cleanOCS() {
		listEmployee = null;
		selectedSubOrganizationPkId = null;
		selectedEmployeePkId = null;
		listMedicineTmp = null;
		listExaminationTmp = null;
		listExaminationTmpTree = null;
		listXrayTmp = null;
		listSurgeryTmp = null;
		listTreatmentTmp = null;
		fillterDateBegin = null;
		fillterDateEnd = null;
		inspections = null;
		inspectionRoot = null;
		doctorRecipeRoot = null;
		conditionalPrescription = null;
		conditionalPrescriptions = null;
		organList = null;
		// TOOL
		lstMedicine = null;
		lstMedicineStr = null;
		lstExamination = null;
		lstXray = null;
		lstSurgery = null;
		lstTreatment = null;
		// LOG
		listMedicineLog = null;
		listExaminationLog = null;
		listXrayLog = null;
		listSurgeryLog = null;
		listTreatmentLog = null;
		firstVisit = true;
		inspectionType = null;
	}

	public void setConditionalPrescription() {
		if (selConditionalPrescription != null) {
			setConditionalPrescription(selConditionalPrescription.getPkId());
		}
	}

	public void setConditionalPrescription(BigDecimal pkId) {
		if (pkId == null || BigDecimal.ZERO.compareTo(pkId) == 0)
			return;
		inspectionDtlDialogTitle = "Title 1";
		try {
			HashMap<String, Object> map = logicInspection.getDtlByConditionalPrescriptionPkId(pkId);
			listMedicineLog = (List<CustomerMedicine>) map.get("listMedicineLog");
			listExaminationLog = (List<InspectionDtl>) map.get("listExaminationLog");
			listXrayLog = (List<InspectionDtl>) map.get("listXrayLog");
			listSurgeryLog = (List<InspectionDtl>) map.get("listSurgeryLog");
			listTreatmentLog = (List<InspectionDtl>) map.get("listTreatmentLog");
			inspectionDtlDialogTitle = (String) map.get("title");
		} catch (Exception ex) {
			ex.printStackTrace();
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionDtlDialog");
		context.execute("PF('inspectionDtlDialog').show();");
	}

	public void setDoctorRecipes() {
		if (selDoctorRecipe != null) {
			setDoctorRecipes(selDoctorRecipe.getPkId());
		}
	}

	public void setDoctorRecipes(BigDecimal pkId) {
		if (pkId == null || BigDecimal.ZERO.compareTo(pkId) == 0)
			return;
		inspectionDtlDialogTitle = "Title 2";
		try {
			HashMap<String, Object> map = logicInspection.getDtlByRecipePkId(pkId);
			listMedicineLog = (List<CustomerMedicine>) map.get("listMedicineLog");
			listExaminationLog = (List<InspectionDtl>) map.get("listExaminationLog");
			listXrayLog = (List<InspectionDtl>) map.get("listXrayLog");
			listSurgeryLog = (List<InspectionDtl>) map.get("listSurgeryLog");
			listTreatmentLog = (List<InspectionDtl>) map.get("listTreatmentLog");
			inspectionDtlDialogTitle = (String) map.get("title");
		} catch (Exception ex) {
			ex.printStackTrace();
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionDtlDialog");
		context.execute("PF('inspectionDtlDialog').show();");
	}

	public void setListLog() {
		if (selInspection != null) {
			setListLog(selInspection.getPkId());
		}
	}

	public void setListLog(BigDecimal inspectionPkId) {
		if (inspectionPkId == null || BigDecimal.ZERO.compareTo(inspectionPkId) == 0)
			return;

		try {
			HashMap<String, Object> map = logicInspection.getDtlByInspectionPkId(inspectionPkId);
			listMedicineLog = (List<CustomerMedicine>) map.get("listMedicineLog");
			listExaminationLog = (List<InspectionDtl>) map.get("listExaminationLog");
			listXrayLog = (List<InspectionDtl>) map.get("listXrayLog");
			listSurgeryLog = (List<InspectionDtl>) map.get("listSurgeryLog");
			listTreatmentLog = (List<InspectionDtl>) map.get("listTreatmentLog");
			inspectionDtlDialogTitle = (String) map.get("title");
			System.out.println(inspectionDtlDialogTitle);
		} catch (Exception ex) {
			ex.printStackTrace();
			getUserSessionController().showErrorMessage(ex.getMessage());
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:inspectionDtlDialog");
		context.execute("PF('inspectionDtlDialog').show();");
	}

	public void setListRecLog() {
		if (listMedicine == null)
			listMedicine = new ArrayList<>();
		if (listExamination == null)
			listExamination = new ArrayList<>();
		if (listXray == null)
			listXray = new ArrayList<>();
		if (listSurgery == null)
			listSurgery = new ArrayList<>();
		if (listTreatment == null)
			listTreatment = new ArrayList<>();
		for (CustomerMedicine medicine : getListMedicineLog()) {
			if (medicine.isSelect()) {
				int count = 0;
				for (CustomerMedicine customerMedicine : listMedicine)
					if (customerMedicine.getMedicinePkId().compareTo(medicine.getMedicinePkId()) == 0)
						count++;
				if (count > 0)
					continue;
				CustomerMedicine customerMedicine = new CustomerMedicine();
				
				customerMedicine.setPkId(medicine.getPkId());
				customerMedicine.setInspectionPkId(medicine.getInspectionPkId());
				customerMedicine.setMedicinePkId(medicine.getMedicinePkId());
				customerMedicine.setEmployeePkId(medicine.getEmployeePkId());
				customerMedicine.setRepeatType(medicine.getRepeatType());
				customerMedicine.setRepeatCount(medicine.getRepeatCount());
				customerMedicine.setDose(medicine.getDose());
				customerMedicine.setTime(medicine.getTime());
				customerMedicine.setDay(medicine.getDay());
				customerMedicine.setExpireDay(medicine.getExpireDay());
				customerMedicine.setM(medicine.getM());
				customerMedicine.setD(medicine.getD());
				customerMedicine.setE(medicine.getE());
				customerMedicine.setN(medicine.getN());
				customerMedicine.setMedicineDescription(medicine.getMedicineDescription());
				customerMedicine.setCreatedDate(medicine.getCreatedDate());
				customerMedicine.setCreatedBy(medicine.getCreatedBy());
				customerMedicine.setUpdatedDate(medicine.getUpdatedDate());
				customerMedicine.setUpdatedBy(medicine.getUpdatedBy());
				customerMedicine.setId(medicine.getId());
				customerMedicine.setName(medicine.getName());
				customerMedicine.setiName(medicine.getiName());
				customerMedicine.setMeasurementName(medicine.getMeasurementName());
				customerMedicine.setStatus(medicine.getStatus());
				customerMedicine.setSelectM(medicine.isSelectM());
				customerMedicine.setSelectD(medicine.isSelectD());
				customerMedicine.setSelectE(medicine.isSelectE());
				customerMedicine.setSelectN(medicine.isSelectN());
				customerMedicine.setSelect(medicine.isSelect());
				customerMedicine.setMedicineTypePkId(medicine.getMedicineTypePkId());
				customerMedicine.setDrugDose(medicine.getDrugDose());
				customerMedicine.setDisplayStr1(medicine.getDisplayStr1());
				customerMedicine.setMeasurementPkId(medicine.getMeasurementPkId());
				customerMedicine.setDisplayStr2(medicine.getDisplayStr2());
				customerMedicine.setMedicineType(medicine.getMedicineType());
				customerMedicine.setConstantMedicineType(medicine.getConstantMedicineType());
				customerMedicine.setCalcDrugDose(medicine.getCalcDrugDose());
				customerMedicine.setCalcDose(medicine.getCalcDose());
				customerMedicine.setCalcType(medicine.getCalcType());
				customerMedicine.setMedicinePkId(medicine.getMedicinePkId());
				customerMedicine.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
				customerMedicine.setStatus(Tool.ADDED);
				customerMedicine.calcMDEN();
				listMedicine.add(customerMedicine);
			}
		}
		for (InspectionDtl dtl : getListExaminationLog()) {
			if (dtl.isSelect()) {
				int count = 0;
				for (InspectionDtl dtl1 : listExamination)
					if (dtl.getTypePkId().compareTo(dtl1.getTypePkId()) == 0)
						count++;
				if (count > 0)
					continue;
				InspectionDtl inspectionDtl = new InspectionDtl();
				inspectionDtl.setType("EXAMINATION");
				inspectionDtl.setTypePkId(dtl.getTypePkId());
				inspectionDtl.setId(dtl.getId());
				inspectionDtl.setName(dtl.getName());
				inspectionDtl.setExaminationDtlsSumPrice(dtl.getExaminationDtlsSumPrice());
				inspectionDtl.setExaminationDtls(dtl.getExaminationDtls());
				inspectionDtl.setStatus(Tool.ADDED);
				listExamination.add(inspectionDtl);
			}
		}
		for (InspectionDtl dtl : getListXrayLog()) {
			if (dtl.isSelect()) {
				int count = 0;
				for (InspectionDtl dtl1 : listXray)
					if (dtl.getTypePkId().compareTo(dtl1.getTypePkId()) == 0)
						count++;
				if (count > 0)
					continue;
				dtl.setStatus(Tool.ADDED);
				listXray.add(dtl);
			}
		}
		for (InspectionDtl dtl : getListSurgeryLog()) {
			if (dtl.isSelect()) {
				int count = 0;
				for (InspectionDtl dtl1 : listSurgery)
					if (dtl.getTypePkId().compareTo(dtl1.getTypePkId()) == 0)
						count++;
				if (count > 0)
					continue;
				dtl.setStatus(Tool.ADDED);
				listSurgery.add(dtl);
			}
		}
		for (InspectionDtl dtl : getListTreatmentLog()) {
			if (dtl.isSelect()) {
				int count = 0;
				for (InspectionDtl dtl1 : listTreatment)
					if (dtl.getTypePkId().compareTo(dtl1.getTypePkId()) == 0)
						count++;
				if (count > 0)
					continue;
				dtl.setStatus(Tool.ADDED);
				listTreatment.add(dtl);
			}
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('inspectionDtlDialog').hide();");
	}

	public void loadLists() {
		listMedicineTmp = null;
		listExaminationTmp = null;
		listExaminationTmpTree = null;
		listXrayTmp = null;
		listSurgeryTmp = null;
		listTreatmentTmp = null;
	}

	public void listEmployees() {
		try {
			listEmployee = logicInspection.getListEmployeeBySubOrganizationPkId(getSelectedSubOrganizationPkId());
			selectedEmployeePkId = BigDecimal.ZERO;
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public List<Employee> getListEmployee() {
		if (listEmployee == null)
			listEmployees();
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public BigDecimal getSelectedEmployeePkId() {
		if (selectedEmployeePkId == null)
			selectedEmployeePkId = BigDecimal.ZERO;
		return selectedEmployeePkId;
	}

	public void setSelectedEmployeePkId(BigDecimal selectedEmployeePkId) {
		this.selectedEmployeePkId = selectedEmployeePkId;
	}

	public BigDecimal getSelectedSubOrganizationPkId() {
		if (selectedSubOrganizationPkId == null)
			selectedSubOrganizationPkId = BigDecimal.ZERO;
		return selectedSubOrganizationPkId;
	}

	public void setSelectedSubOrganizationPkId(BigDecimal selectedSubOrganizationPkId) {
		this.selectedSubOrganizationPkId = selectedSubOrganizationPkId;
	}

	public List<CustomerMedicine> getListMedicineTmp() {
		if (listMedicineTmp == null)
			listMedicineTmp = new ArrayList<>();
		if (listMedicineTmp.size() > 0 && Tool.LAST.equals(listMedicineTmp.get(listMedicineTmp.size() - 1).getStatus()))
			return listMedicineTmp;
		listMedicineTmp.clear();
		for (CustomerMedicine customerMedicine : getListMedicine())
			if (!Tool.DELETE.equals(customerMedicine.getStatus()))
				listMedicineTmp.add(customerMedicine);
		CustomerMedicine lastItem = new CustomerMedicine();
		lastItem.setName("");
		lastItem.setId("");
		lastItem.setStatus(Tool.LAST);
		listMedicineTmp.add(lastItem);
		return listMedicineTmp;
	}

	public void setListMedicineTmp(List<CustomerMedicine> listMedicineTmp) {
		this.listMedicineTmp = listMedicineTmp;
	}

	public TreeNode getListExaminationTmpTree() {
		listExaminationTmpTree = new DefaultTreeNode();
		for (InspectionDtl dtl : getListExaminationTmp()) {
			TreeNode node = new DefaultTreeNode(dtl, listExaminationTmpTree);
			for (ExaminationDtl examinationDtl : dtl.getExaminationDtls()) {
				TreeNode treeNode = new DefaultTreeNode(examinationDtl, node);
			}
		}
		return listExaminationTmpTree;
	}

	public void setListExaminationTmpTree(TreeNode listExaminationTmpTree) {
		this.listExaminationTmpTree = listExaminationTmpTree;
	}

	public List<InspectionDtl> getListExaminationTmp() {
		if (listExaminationTmp == null)
			listExaminationTmp = new ArrayList<>();
		if (listExaminationTmp.size() > 0
				&& Tool.LAST.equals(listExaminationTmp.get(listExaminationTmp.size() - 1).getStatus()))
			return listExaminationTmp;
		listExaminationTmp.clear();
		for (InspectionDtl item : getListExamination())
			if (!Tool.DELETE.equals(item.getStatus()))
				listExaminationTmp.add(item);
		InspectionDtl lastItem = new InspectionDtl();
		lastItem.setName("");
		lastItem.setId("");
		lastItem.setStatus(Tool.LAST);
		listExaminationTmp.add(lastItem);
		return listExaminationTmp;
	}

	public void setListExaminationTmp(List<InspectionDtl> listExaminationTmp) {
		this.listExaminationTmp = listExaminationTmp;
	}

	public List<InspectionDtl> getListXrayTmp() {
		if (listXrayTmp == null)
			listXrayTmp = new ArrayList<>();
		if (listXrayTmp.size() > 0 && Tool.LAST.equals(listXrayTmp.get(listXrayTmp.size() - 1).getStatus()))
			return listXrayTmp;
		listXrayTmp.clear();
		for (InspectionDtl item : getListXray())
			if (!Tool.DELETE.equals(item.getStatus()))
				listXrayTmp.add(item);
		InspectionDtl lastItem = new InspectionDtl();
		lastItem.setName("");
		lastItem.setId("");
		lastItem.setStatus(Tool.LAST);
		listXrayTmp.add(lastItem);
		return listXrayTmp;
	}

	public void setListXrayTmp(List<InspectionDtl> listXrayTmp) {
		this.listXrayTmp = listXrayTmp;
	}

	public List<InspectionDtl> getListSurgeryTmp() {
		if (listSurgeryTmp == null)
			listSurgeryTmp = new ArrayList<>();
		if (listSurgeryTmp.size() > 0 && Tool.LAST.equals(listSurgeryTmp.get(listSurgeryTmp.size() - 1).getStatus()))
			return listSurgeryTmp;
		listSurgeryTmp.clear();
		for (InspectionDtl item : getListSurgery())
			if (!Tool.DELETE.equals(item.getStatus()))
				listSurgeryTmp.add(item);
		InspectionDtl lastItem = new InspectionDtl();
		lastItem.setName("");
		lastItem.setId("");
		lastItem.setStatus(Tool.LAST);
		listSurgeryTmp.add(lastItem);
		return listSurgeryTmp;
	}

	public void setListSurgeryTmp(List<InspectionDtl> listSurgeryTmp) {
		this.listSurgeryTmp = listSurgeryTmp;
	}

	public List<InspectionDtl> getListTreatmentTmp() {
		if (listTreatmentTmp == null)
			listTreatmentTmp = new ArrayList<>();
		if (listTreatmentTmp.size() > 0
				&& Tool.LAST.equals(listTreatmentTmp.get(listTreatmentTmp.size() - 1).getStatus()))
			return listTreatmentTmp;
		listTreatmentTmp.clear();
		for (InspectionDtl item : getListTreatment())
			if (!Tool.DELETE.equals(item.getStatus()))
				listTreatmentTmp.add(item);
		InspectionDtl lastItem = new InspectionDtl();
		lastItem.setName("");
		lastItem.setId("");
		lastItem.setStatus(Tool.LAST);
		listTreatmentTmp.add(lastItem);
		return listTreatmentTmp;
	}

	public void setListTreatmentTmp(List<InspectionDtl> listTreatmentTmp) {
		this.listTreatmentTmp = listTreatmentTmp;
	}

	public void autoFillExamination1() {
		if (listExaminationTmp != null && listExaminationTmp.size() > 0
				&& Tool.LAST.equals(listExaminationTmp.get(listExaminationTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listExaminationTmp.get(listExaminationTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listExamination)
					if (inspectionDtl.getId().equals(dtl.getId())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				List<Examination> cpDtls = logicConPre.getByAnyField(Examination.class, "id", dtl.getId());
				if (cpDtls.size() > 0 && !dtl.getId().isEmpty()) {
					dtl.setType("EXAMINATION");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listExamination.add(dtl);
					listExaminationTmp = null;
					listExaminationTmpTree = null;
				} else {
					getExaTypeFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillExamination2() {
		if (listExaminationTmp != null && listExaminationTmp.size() > 0
				&& Tool.LAST.equals(listExaminationTmp.get(listExaminationTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listExaminationTmp.get(listExaminationTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listExamination)
					if (inspectionDtl.getName().equals(dtl.getName())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				List<Examination> cpDtls = logicConPre.getByAnyField(Examination.class, "name", dtl.getName());
				if (cpDtls.size() > 0 && !dtl.getName().isEmpty()) {
					dtl.setType("EXAMINATION");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listExamination.add(dtl);
					listExaminationTmp = null;
					listExaminationTmpTree = null;
				} else {
					getExaTypeFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillXray1() {
		if (listXrayTmp != null && listXrayTmp.size() > 0
				&& Tool.LAST.equals(listXrayTmp.get(listXrayTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listXrayTmp.get(listXrayTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listXray)
					if (inspectionDtl.getId().equals(dtl.getId())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				List<Xray> cpDtls = logicConPre.getByAnyField(Xray.class, "id", dtl.getId());
				if (cpDtls.size() > 0 && !dtl.getId().isEmpty()) {
					dtl.setType("XRAY");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listXray.add(dtl);
					listXrayTmp = null;
				} else {
					getXrayTypesFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillXray2() {
		if (listXrayTmp != null && listXrayTmp.size() > 0
				&& Tool.LAST.equals(listXrayTmp.get(listXrayTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listXrayTmp.get(listXrayTmp.size() - 1);
				List<Xray> cpDtls = logicConPre.getByAnyField(Xray.class, "name", dtl.getName());
				if (cpDtls.size() > 0 && !dtl.getName().isEmpty()) {
					dtl.setType("XRAY");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listXray.add(dtl);
					listXrayTmp = null;
				} else {
					getXrayTypesFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillSurgery1() {
		if (listSurgeryTmp != null && listSurgeryTmp.size() > 0
				&& Tool.LAST.equals(listSurgeryTmp.get(listSurgeryTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listSurgeryTmp.get(listSurgeryTmp.size() - 1);
				List<Surgery> cpDtls = logicConPre.getByAnyField(Surgery.class, "id", dtl.getId());
				if (cpDtls.size() > 0 && !dtl.getId().isEmpty()) {
					dtl.setType("SURGERY");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					// dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listSurgery.add(dtl);
					listSurgeryTmp = null;
				} else {
					getSurgeryTypeFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillSurgery2() {
		if (listSurgeryTmp != null && listSurgeryTmp.size() > 0
				&& Tool.LAST.equals(listSurgeryTmp.get(listSurgeryTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listSurgeryTmp.get(listSurgeryTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listSurgery)
					if (inspectionDtl.getName().equals(dtl.getName())) {
						dtl.setName("");
						dtl.setId("");
						return;
					}
				List<Surgery> cpDtls = logicConPre.getByAnyField(Surgery.class, "name", dtl.getName());
				if (cpDtls.size() > 0 && !dtl.getName().isEmpty()) {
					dtl.setType("SURGERY");
					dtl.setTypePkId(cpDtls.get(0).getPkId());
					// dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setStatus(Tool.ADDED);
					listSurgery.add(dtl);
					listSurgeryTmp = null;
				} else {
					getSurgeryTypeFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillTreatment1() {
		if (listTreatmentTmp != null && listTreatmentTmp.size() > 0
				&& Tool.LAST.equals(listTreatmentTmp.get(listTreatmentTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listTreatmentTmp.get(listTreatmentTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listTreatment)
					if (inspectionDtl.getId().equals(dtl.getId())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				Treatment cpDtl = logicConPre.getTreatment("id", dtl.getId());
				if (cpDtl != null && !dtl.getId().isEmpty()) {
					dtl.setType("TREATMENT");
					dtl.setTypePkId(cpDtl.getPkId());
					dtl.setId(cpDtl.getId());
					dtl.setName(cpDtl.getName());
					dtl.setStatus(Tool.ADDED);
					dtl.setCost(cpDtl.getCost());
					listTreatment.add(dtl);
					listTreatmentTmp = null;
				} else {
					context().execute("PF('treatmentList').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillTreatment2() {
		if (listTreatmentTmp != null && listTreatmentTmp.size() > 0
				&& Tool.LAST.equals(listTreatmentTmp.get(listTreatmentTmp.size() - 1).getStatus())) {
			try {
				InspectionDtl dtl = listTreatmentTmp.get(listTreatmentTmp.size() - 1);
				for (InspectionDtl inspectionDtl : listTreatment)
					if (inspectionDtl.getName().equals(dtl.getName())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				Treatment cpDtl = logicConPre.getTreatment("name", dtl.getName());
				if (cpDtl != null && !dtl.getName().isEmpty()) {
					dtl.setType("TREATMENT");
					dtl.setTypePkId(cpDtl.getPkId());
					dtl.setId(cpDtl.getId());
					dtl.setName(cpDtl.getName());
					dtl.setStatus(Tool.ADDED);
					dtl.setCost(cpDtl.getCost());
					listTreatment.add(dtl);
					listTreatmentTmp = null;
				} else {
					context().execute("PF('treatmentList').show();");
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public RequestContext context() {
		return RequestContext.getCurrentInstance();
	}

	public void autoFillCustomerMedicine1() {
		if (listMedicineTmp != null && listMedicineTmp.size() > 0
				&& Tool.LAST.equals(listMedicineTmp.get(listMedicineTmp.size() - 1).getStatus())) {
			try {
				CustomerMedicine dtl = listMedicineTmp.get(listMedicineTmp.size() - 1);
				for (CustomerMedicine cm : listMedicine)
					if (cm.getId().equals(dtl.getId())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				List<Medicine> cpDtls = logicConPre.getByAnyField(Medicine.class, "id", dtl.getId());
				if (cpDtls.size() > 0 && !dtl.getId().isEmpty()) {
					dtl.setMedicinePkId(cpDtls.get(0).getPkId());
					dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setRepeatType(0);
					dtl.setRepeatCount(0);
					dtl.setExpireDay(0);
					dtl.setDose("");
					dtl.setStatus(Tool.ADDED);
					listMedicine.add(dtl);
					listMedicineTmp = null;
				} else {
					getMedListFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public void autoFillCustomerMedicine2() {
		if (listMedicineTmp != null && listMedicineTmp.size() > 0
				&& Tool.LAST.equals(listMedicineTmp.get(listMedicineTmp.size() - 1).getStatus())) {
			try {
				CustomerMedicine dtl = listMedicineTmp.get(listMedicineTmp.size() - 1);
				for (CustomerMedicine cm : listMedicine)
					if (cm.getName().equals(dtl.getName())) {
						dtl.setId("");
						dtl.setName("");
						return;
					}
				List<Medicine> cpDtls = logicConPre.getByAnyField(Medicine.class, "name", dtl.getName());
				if (cpDtls.size() > 0 && !dtl.getName().isEmpty()) {
					dtl.setMedicinePkId(cpDtls.get(0).getPkId());
					dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
					dtl.setId(cpDtls.get(0).getId());
					dtl.setName(cpDtls.get(0).getName());
					dtl.setRepeatType(0);
					dtl.setRepeatCount(0);
					dtl.setExpireDay(0);
					dtl.setDose("");
					dtl.setStatus(Tool.ADDED);
					listMedicine.add(dtl);
					listMedicineTmp = null;
				} else {
					getMedListFromDb();
				}
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
	}

	public Date getFillterDateBegin() {
		if (fillterDateBegin == null && currentEmployeeRequest != null
				&& currentEmployeeRequest.getCustomerPkId() != null) {
			try {
				fillterDateBegin = logicConPre.getInspectionBeginDate(currentEmployeeRequest.getCustomerPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return fillterDateBegin;
	}

	public void setFillterDateBegin(Date fillterDateBegin) {
		this.fillterDateBegin = fillterDateBegin;
	}

	public Date getFillterDateEnd() {
		if (fillterDateEnd == null && currentEmployeeRequest != null
				&& currentEmployeeRequest.getCustomerPkId() != null) {
			try {
				fillterDateEnd = logicConPre.getInspectionEndDate(currentEmployeeRequest.getCustomerPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return fillterDateEnd;
	}

	public void setFillterDateEnd(Date fillterDateEnd) {
		this.fillterDateEnd = fillterDateEnd;
	}

	public List<Inspection> getInspections() {
		if (inspections == null) {
			try {
				inspections = logicConPre.getInspectionByDate(getSelectedSubOrganizationPkId(),
						getSelectedEmployeePkId(), currentEmployeeRequest.getCustomerPkId(), getFillterDateBegin(),
						getFillterDateEnd());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return inspections;
	}

	public void setInspections(List<Inspection> inspections) {
		this.inspections = inspections;
	}

	public void showInspection(BigDecimal inspectionPkId) {
		System.out.println(inspectionPkId);
		try {
			listMedicine = logicConPre.getMedicineInspectionDtlByInspectionPkId(inspectionPkId);
			listExamination = logicConPre.getExaminationInspectionDtlByInspectionPkId(inspectionPkId);
			listXray = logicConPre.getXrayInspectionDtlByInspectionPkId(inspectionPkId);
			listSurgery = logicConPre.getSurgeryInspectionDtlByInspectionPkId(inspectionPkId);
			listTreatment = logicConPre.getTreatmentInspectionDtlByInspectionPkId(inspectionPkId);

			listMedicineTmp = null;
			listExaminationTmp = null;
			listExaminationTmpTree = null;
			listXrayTmp = null;
			listSurgeryTmp = null;
			listTreatmentTmp = null;

			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("updateTable();");
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public String dateToStr(Date dte) {
		if (dte == null)
			dte = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(dte);
	}

	public String saveNewOrder() {
		try {
			if (getRecipe().isSelected()) {
				if (saveTreatmentTemplate().equals("duplicate")) {
					return "";
				}
			}
			List<InspectionDtl> dtls = new ArrayList<InspectionDtl>();
			dtls.addAll(listExamination);
			dtls.addAll(listXray);
			dtls.addAll(listTreatment);
			dtls.addAll(listSurgery);

			if (currentEmployeeRequest.getSaveMood() == 0) {
				currentEmployeeRequest.setSaveMood(1);
			} else if (currentEmployeeRequest.getSaveMood() == 2) {
				currentEmployeeRequest.setSaveMood(3);
			} else if (currentEmployeeRequest.getSaveMood() == 3) {
				currentEmployeeRequest.setSaveMood(3);
			} else
				currentEmployeeRequest.setSaveMood(1);

			if (dtls == null || listDiagnose == null || listMedicine == null || currentEmployeeRequest == null
					|| dtls.size() + listDiagnose.size() + listMedicine.size() < 1) {
				throw new Exception("Хадгалах өгөгдөл байхгүй байна ! ! !");
			}

			// currentEmployeeRequest.setMood(2);
			// currentEmployeeRequest.setReInspection(0);
			if (currentInspection.getPkId() != null)
				currentInspection.setStatus(Tool.MODIFIED);
			logicInspection.saveNewOrder(currentInspection, userSessionController.getLoggedInfo(), dtls, listDiagnose,
					listMedicine, currentEmployeeRequest);

			userSessionController.showMessage(99);

			return "doctor_list";
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
			return "";
		}
	}

	public void removeOrder() {

	}

	public List<String> getOrganList() {
		if (organList == null) {
			organList = new ArrayList<>();
			organList.add("Нүд");
			organList.add("Хамар");
			organList.add("Ам");
			organList.add("Чих");
			organList.add("Гар");
			organList.add("Хөл");
			organList.add("Толгой");
		}
		return organList;
	}

	public void setOrganList(List<String> organList) {
		this.organList = organList;
	}

	public List<CustomerMedicine> getLstMedicine() {
		try {
			if (lstMedicine == null) {
				HashMap<String, Object> hashMap = logicInspection
						.getLastInspectionDtlsByEmployee(currentEmployeeRequest.getEmployeePkId(), "Medicine");
				lstMedicine = (List<CustomerMedicine>) hashMap.get("med");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return lstMedicine;
	}

	public void setLstMedicine(List<CustomerMedicine> lstMedicine) {
		this.lstMedicine = lstMedicine;
	}

	public List<InspectionDtl> getLstExamination() {
		try {
			if (lstExamination == null) {
				HashMap<String, Object> hashMap = logicInspection.getLastInspectionDtlsByEmployee(
						currentEmployeeRequest.getEmployeePkId(), Tool.INSPECTIONTYPE_EXAMINATION);
				lstExamination = (List<InspectionDtl>) hashMap.get("ins");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return lstExamination;
	}

	public void setLstExamination(List<InspectionDtl> lstExamination) {
		this.lstExamination = lstExamination;
	}

	public List<InspectionDtl> getLstSurgery() {
		try {
			if (lstSurgery == null) {
				HashMap<String, Object> hashMap = logicInspection.getLastInspectionDtlsByEmployee(
						currentEmployeeRequest.getEmployeePkId(), Tool.INSPECTIONTYPE_SURGERY);
				lstSurgery = (List<InspectionDtl>) hashMap.get("ins");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return lstSurgery;
	}

	public void setLstSurgery(List<InspectionDtl> lstSurgery) {
		this.lstSurgery = lstSurgery;
	}

	public List<InspectionDtl> getLstTreatment() {
		try {
			if (lstTreatment == null) {
				HashMap<String, Object> hashMap = logicInspection.getLastInspectionDtlsByEmployee(
						currentEmployeeRequest.getEmployeePkId(), Tool.INSPECTIONTYPE_TREATMENT);
				lstTreatment = (List<InspectionDtl>) hashMap.get("ins");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return lstTreatment;
	}

	public void setLstTreatment(List<InspectionDtl> lstTreatment) {
		this.lstTreatment = lstTreatment;
	}

	public List<InspectionDtl> getLstXray() {
		try {
			if (lstXray == null) {
				HashMap<String, Object> hashMap = logicInspection.getLastInspectionDtlsByEmployee(
						currentEmployeeRequest.getEmployeePkId(), Tool.INSPECTIONTYPE_XRAY);
				lstXray = (List<InspectionDtl>) hashMap.get("ins");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return lstXray;
	}

	public void setLstXray(List<InspectionDtl> lstXray) {
		this.lstXray = lstXray;
	}

	public List<ConditionalPrescription> getConditionalPrescriptions() {
		if (conditionalPrescriptions == null) {
			try {
				conditionalPrescriptions = logicConPre.getConditionalPrescriptions(getConditionalPrescriptionOrgPkId());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return conditionalPrescriptions;
	}

	public void conditionalPrescriptionsNull() {
		this.conditionalPrescriptions = null;
	}

	public void setConditionalPrescriptions(List<ConditionalPrescription> conditionalPrescriptions) {
		this.conditionalPrescriptions = conditionalPrescriptions;
	}

	public TreeNode getConditionalPrescription() {
		conditionalPrescription = new DefaultTreeNode(new StringBuilder(), null);
		for (ConditionalPrescription cPrescription : getConditionalPrescriptions()) {
			DcRecipe dcRecipe = new DcRecipe();
			dcRecipe.setName(cPrescription.getName());
			dcRecipe.setType("ConditionalPrescriptionHDR");
			dcRecipe.setRecipeTypName("");
			dcRecipe.setConditionalPrescription(cPrescription);
			TreeNode drNode = new DefaultTreeNode(dcRecipe, conditionalPrescription);
			for (ConditionalPrescriptionDtl dtl : cPrescription.getDtls()) {
				DcRecipe dcRecipeDtl = new DcRecipe();
				String name = "";
				if (dtl.getDiagnosePkId() != null)
					name = "TX";
				if (dtl.getExaminationPkId() != null)
					name = "LAB TEST";
				// if("SURGERY".equals(dtl.getType())) name = "SURGERY";
				if (dtl.getMedicinePkId() != null)
					name = "RX";
				if (dtl.getXrayPkId() != null)
					name = "XRAY";
				dcRecipeDtl.setName(dtl.getName());
				dcRecipeDtl.setRecipeTypName(name);
				dcRecipeDtl.setType("ConditionalPrescriptionDTL");
				dcRecipeDtl.setConditionalPrescriptionDtl(dtl);
				new DefaultTreeNode(dcRecipeDtl, drNode);
			}
		}
		return conditionalPrescription;
	}

	public void setConditionalPrescription(TreeNode conditionalPrescription) {
		this.conditionalPrescription = conditionalPrescription;
	}

	public TreeNode getDoctorRecipeRoot() {
		doctorRecipeRoot = new DefaultTreeNode(new StringBuilder(), null);
		for (DoctorRecipe doctorRecipe : getDoctorRecipes()) {
			DcRecipe dcRecipe = new DcRecipe();
			dcRecipe.setName(doctorRecipe.getName());
			dcRecipe.setType("DoctorRecipeHDR");
			dcRecipe.setRecipeTypName("");
			dcRecipe.setDoctorRecipe(doctorRecipe);
			TreeNode drNode = new DefaultTreeNode(dcRecipe, doctorRecipeRoot);
			for (DoctorRecipeDtl dtl : doctorRecipe.getDtls()) {
				DcRecipe dcRecipeDtl = new DcRecipe();
				String name = "";
				if ("TREATMENT".equals(dtl.getType()))
					name = "TX";
				if ("EXAMINATION".equals(dtl.getType()))
					name = "LAB TEST";
				if ("SURGERY".equals(dtl.getType()))
					name = "SURGERY";
				if ("MEDICINE".equals(dtl.getType()))
					name = "RX";
				if ("XRAY".equals(dtl.getType()))
					name = "XRAY";
				dcRecipeDtl.setName(dtl.getName());
				dcRecipeDtl.setRecipeTypName(name);
				dcRecipeDtl.setType("DoctorRecipeDTL");
				dcRecipeDtl.setDoctorRecipeDtl(dtl);
				new DefaultTreeNode(dcRecipeDtl, drNode);
			}
		}
		return doctorRecipeRoot;
	}

	public void setDoctorRecipeRoot(TreeNode doctorRecipeRoot) {
		this.doctorRecipeRoot = doctorRecipeRoot;
	}

	public TreeNode getInspectionRoot() {
		inspectionRoot = new DefaultTreeNode(new StringBuilder(), null);
		for (Inspection inspection : getInspections()) {
			PrevRecipe prevRecipe = new PrevRecipe();
			prevRecipe.setPkId(inspection.getPkId());
			prevRecipe.setDate(inspection.getInspectionDate());
			prevRecipe.setName(inspection.getEmployeeName() + " (" + inspection.getSubOrgaName() + ")");
			prevRecipe.setType("INS");
			prevRecipe.setInspection(inspection);
			TreeNode inspectionNode = new DefaultTreeNode(prevRecipe, inspectionRoot);
			for (InspectionDtl dtl : inspection.getDtls()) {
				PrevRecipe prevRecipeDtl = new PrevRecipe();
				prevRecipeDtl.setPkId(dtl.getTypePkId());
				prevRecipeDtl.setDate("");
				prevRecipeDtl.setName(dtl.getName() + " (" + dtl.getId() + ")");
				prevRecipeDtl.setType("DTL");
				prevRecipeDtl.setInspectionDtl(dtl);
				new DefaultTreeNode(prevRecipeDtl, inspectionNode);
			}
		}
		return inspectionRoot;
	}

	public void setInspectionRoot(TreeNode inspectionRoot) {
		this.inspectionRoot = inspectionRoot;
	}

	public CustomerPlan getCustomerPlan() {
		if (customerPlan == null) {
			customerPlan = new CustomerPlan();
			this.customerPlan.setStatus(Tool.ADDED);
		}
		return customerPlan;
	}

	public void setCustomerPlan(CustomerPlan customerPlan) {
		this.customerPlan = customerPlan;
	}

	public CustomerQuestion getCustomerQuestion() {
		if (customerQuestion == null) {
			customerQuestion = new CustomerQuestion();
			this.customerQuestion.setStatus(Tool.ADDED);
		}
		return customerQuestion;
	}

	public void setCustomerQuestion(CustomerQuestion customerQuestion) {
		this.customerQuestion = customerQuestion;
	}

	public List<CustomerMedicine> getListMedicineLog() {
		if (listMedicineLog == null)
			listMedicineLog = new ArrayList<>();
		return listMedicineLog;
	}

	public void setListMedicineLog(List<CustomerMedicine> listMedicineLog) {
		this.listMedicineLog = listMedicineLog;
	}

	public List<InspectionDtl> getListExaminationLog() {
		if (listExaminationLog == null)
			listExaminationLog = new ArrayList<>();
		return listExaminationLog;
	}

	public void setListExaminationLog(List<InspectionDtl> listExaminationLog) {
		this.listExaminationLog = listExaminationLog;
	}

	public List<InspectionDtl> getListXrayLog() {
		if (listXrayLog == null)
			listXrayLog = new ArrayList<>();
		return listXrayLog;
	}

	public void setListXrayLog(List<InspectionDtl> listXrayLog) {
		this.listXrayLog = listXrayLog;
	}

	public List<InspectionDtl> getListSurgeryLog() {
		if (listSurgeryLog == null)
			listSurgeryLog = new ArrayList<>();
		return listSurgeryLog;
	}

	public void setListSurgeryLog(List<InspectionDtl> listSurgeryLog) {
		this.listSurgeryLog = listSurgeryLog;
	}

	public List<InspectionDtl> getListTreatmentLog() {
		if (listTreatmentLog == null)
			listTreatmentLog = new ArrayList<>();
		return listTreatmentLog;
	}

	public void setListTreatmentLog(List<InspectionDtl> listTreatmentLog) {
		this.listTreatmentLog = listTreatmentLog;
	}

	public CustomerPain getCustomerPain() {
		if (customerPain == null) {
			customerPain = new CustomerPain();
			this.customerPain.setStatus(Tool.ADDED);
		}
		return customerPain;
	}

	public void setCustomerPain(CustomerPain customerPain) {
		this.customerPain = customerPain;
	}

	public CustomerInspection getCustomerInsp() {
		if (customerInsp == null) {
			customerInsp = new CustomerInspection();
			this.customerInsp.setStatus(Tool.ADDED);
		}
		return customerInsp;
	}

	public void setCustomerInsp(CustomerInspection customerInsp) {
		this.customerInsp = customerInsp;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public List<CustomerAttachment> getListReAttachment() {
		if (listReAttachment == null)
			listReAttachment = new ArrayList<CustomerAttachment>();
		return listReAttachment;
	}

	public void setListReAttachment(List<CustomerAttachment> listReAttachment) {
		this.listReAttachment = listReAttachment;
	}

	public List<CustomerAttachment> getListNoseAttachment() {
		if (listNoseAttachment == null)
			listNoseAttachment = new ArrayList<CustomerAttachment>();
		return listNoseAttachment;
	}

	public void setListNoseAttachment(List<CustomerAttachment> listNoseAttachment) {
		this.listNoseAttachment = listNoseAttachment;
	}

	public List<CustomerAttachment> getListEarAttachment() {
		if (listEarAttachment == null)
			listEarAttachment = new ArrayList<CustomerAttachment>();
		return listEarAttachment;
	}

	public void setListEarAttachment(List<CustomerAttachment> listEarAttachment) {
		this.listEarAttachment = listEarAttachment;
	}

	public List<CustomerAttachment> getListThroatAttachment() {
		if (listThroatAttachment == null)
			listThroatAttachment = new ArrayList<CustomerAttachment>();
		return listThroatAttachment;
	}

	public void setListThroatAttachment(List<CustomerAttachment> listThroatAttachment) {
		this.listThroatAttachment = listThroatAttachment;
	}

	public List<StreamedContent> getListReStreamedContent() {
		if (listReStreamedContent == null)
			listReStreamedContent = new ArrayList<StreamedContent>();
		return listReStreamedContent;
	}

	public void setListReStreamedContent(List<StreamedContent> listReStreamedContent) {
		this.listReStreamedContent = listReStreamedContent;
	}

	public StreamedContent getImageInspection() {
		return imageInspection;
	}

	public void setImageInspection(StreamedContent imageInspection) {
		this.imageInspection = imageInspection;
	}

	public CustomerDiagnose getDiagnoseFilter() {
		if (diagnoseFilter == null)
			diagnoseFilter = new CustomerDiagnose();
		return diagnoseFilter;
	}

	public void setDiagnoseFilter(CustomerDiagnose diagnoseFilter) {
		this.diagnoseFilter = diagnoseFilter;
	}

	public String getWhichDiagnose() {
		return whichDiagnose;
	}

	public void setWhichDiagnose(String whichDiagnose) {
		this.whichDiagnose = whichDiagnose;
	}

	public CustomerDiagnose getNoseAppDiagnose() {
		if (noseAppDiagnose == null)
			noseAppDiagnose = new CustomerDiagnose();
		return noseAppDiagnose;
	}

	public void setNoseAppDiagnose(CustomerDiagnose noseAppDiagnose) {
		this.noseAppDiagnose = noseAppDiagnose;
	}

	public List<CustomerDiagnose> getNosePreDiagnose() {
		if (nosePreDiagnose == null)
			nosePreDiagnose = new ArrayList<CustomerDiagnose>();
		return nosePreDiagnose;
	}

	public void setNosePreDiagnose(List<CustomerDiagnose> nosePreDiagnose) {
		this.nosePreDiagnose = nosePreDiagnose;
	}

	public String getInspectionDtlDialogTitle() {
		return inspectionDtlDialogTitle;
	}

	public void setInspectionDtlDialogTitle(String inspectionDtlDialogTitle) {
		this.inspectionDtlDialogTitle = inspectionDtlDialogTitle;
	}

	public boolean isFirstVisit() {
		if (firstVisit) {
			firstVisit = false;
			return true;
		}
		return firstVisit;
	}

	public void setFirstVisit(boolean firstVisit) {
		this.firstVisit = firstVisit;
	}

	public int getWhichDiagnoseType() {
		return whichDiagnoseType;
	}

	public void setWhichDiagnoseType(int whichDiagnoseType) {
		this.whichDiagnoseType = whichDiagnoseType;
	}

	public CustomerDiagnose getEarAppDiagnose() {
		if (earAppDiagnose == null)
			earAppDiagnose = new CustomerDiagnose();
		return earAppDiagnose;
	}

	public void setEarAppDiagnose(CustomerDiagnose earAppDiagnose) {
		this.earAppDiagnose = earAppDiagnose;
	}

	public List<CustomerDiagnose> getEarPreDiagnose() {
		if (earPreDiagnose == null)
			earPreDiagnose = new ArrayList<CustomerDiagnose>();
		return earPreDiagnose;
	}

	public void setEarPreDiagnose(List<CustomerDiagnose> earPreDiagnose) {
		this.earPreDiagnose = earPreDiagnose;
	}

	public CustomerDiagnose getThroatAppDiagnose() {
		if (throatAppDiagnose == null)
			throatAppDiagnose = new CustomerDiagnose();
		return throatAppDiagnose;
	}

	public void setThroatAppDiagnose(CustomerDiagnose throatAppDiagnose) {
		this.throatAppDiagnose = throatAppDiagnose;
	}

	public List<CustomerDiagnose> getThroatPreDiagnose() {
		if (throatPreDiagnose == null)
			throatPreDiagnose = new ArrayList<CustomerDiagnose>();
		return throatPreDiagnose;
	}

	public void setThroatPreDiagnose(List<CustomerDiagnose> throatPreDiagnose) {
		this.throatPreDiagnose = throatPreDiagnose;
	}

	public CustomerDiagnose getReInspAppDiagnose() {
		if (reInspAppDiagnose == null)
			reInspAppDiagnose = new CustomerDiagnose();
		return reInspAppDiagnose;
	}

	public void setReInspAppDiagnose(CustomerDiagnose reInspAppDiagnose) {
		this.reInspAppDiagnose = reInspAppDiagnose;
	}

	public List<CustomerDiagnose> getReInspPreDiagnose() {
		if (reInspPreDiagnose == null)
			reInspPreDiagnose = new ArrayList<CustomerDiagnose>();
		return reInspPreDiagnose;
	}

	public void setReInspPreDiagnose(List<CustomerDiagnose> reInspPreDiagnose) {
		this.reInspPreDiagnose = reInspPreDiagnose;
	}

	public CustomerDiagnose getPreDiagnose() {
		if (preDiagnose == null)
			preDiagnose = new CustomerDiagnose();
		return preDiagnose;
	}

	public void setPreDiagnose(CustomerDiagnose preDiagnose) {
		this.preDiagnose = preDiagnose;
	}

	public CustomerDiagnose getSkinAppDiagnose() {
		if (skinAppDiagnose == null)
			skinAppDiagnose = new CustomerDiagnose();
		return skinAppDiagnose;
	}

	public void setSkinAppDiagnose(CustomerDiagnose skinAppDiagnose) {
		this.skinAppDiagnose = skinAppDiagnose;
	}

	public List<CustomerDiagnose> getSkinPreDiagnose() {
		if (skinPreDiagnose == null)
			skinPreDiagnose = new ArrayList<CustomerDiagnose>();
		return skinPreDiagnose;
	}

	public void setSkinPreDiagnose(List<CustomerDiagnose> skinPreDiagnose) {
		this.skinPreDiagnose = skinPreDiagnose;
	}

	public CustomerDiagnose getLungAppDiagnose() {
		if (lungAppDiagnose == null)
			lungAppDiagnose = new CustomerDiagnose();
		return lungAppDiagnose;
	}

	public void setLungAppDiagnose(CustomerDiagnose lungAppDiagnose) {
		this.lungAppDiagnose = lungAppDiagnose;
	}

	public List<CustomerDiagnose> getLungPreDiagnose() {
		if (lungPreDiagnose == null)
			lungPreDiagnose = new ArrayList<CustomerDiagnose>();
		return lungPreDiagnose;
	}

	public void setLungPreDiagnose(List<CustomerDiagnose> lungPreDiagnose) {
		this.lungPreDiagnose = lungPreDiagnose;
	}

	public List<CustomerAttachment> getListSkinAttachment() {
		if (listSkinAttachment == null)
			listSkinAttachment = new ArrayList<CustomerAttachment>();
		return listSkinAttachment;
	}

	public void setListSkinAttachment(List<CustomerAttachment> listSkinAttachment) {
		this.listSkinAttachment = listSkinAttachment;
	}

	public List<CustomerAttachment> getListLungAttachment() {
		if (listLungAttachment == null)
			listLungAttachment = new ArrayList<CustomerAttachment>();
		return listLungAttachment;
	}

	public void setListLungAttachment(List<CustomerAttachment> listLungAttachment) {
		this.listLungAttachment = listLungAttachment;
	}

	public Inspection getSelectedInspection() {
		if (selectedInspection == null)
			selectedInspection = new Inspection();
		return selectedInspection;
	}

	public void setSelectedInspection(Inspection selectedInspection) {
		this.selectedInspection = selectedInspection;
	}

	public CustomerPastHistory getCurrentPastHistory() {
		if (currentPastHistory == null)
			currentPastHistory = new CustomerPastHistory();
		return currentPastHistory;
	}

	public void setCurrentPastHistory(CustomerPastHistory currentPastHistory) {
		this.currentPastHistory = currentPastHistory;
	}

	public String getMedicineSearchString() {
		return medicineSearchString;
	}

	public void setMedicineSearchString(String medicineSearchString) {
		this.medicineSearchString = medicineSearchString;
	}

	public String getExaminationSearchString() {
		return examinationSearchString;
	}

	public void setExaminationSearchString(String examinationSearchString) {
		this.examinationSearchString = examinationSearchString;
	}

	public String getSurgerySearchString() {
		return surgerySearchString;
	}

	public void setSurgerySearchString(String surgerySearchString) {
		this.surgerySearchString = surgerySearchString;
	}

	public String getTreatmentSearchString() {
		return treatmentSearchString;
	}

	public void setTreatmentSearchString(String treatmentSearchString) {
		this.treatmentSearchString = treatmentSearchString;
	}

	public String getXraySearchString() {
		return xraySearchString;
	}

	public void setXraySearchString(String xraySearchString) {
		this.xraySearchString = xraySearchString;
	}

	public boolean isMyInpsection() {
		return myInpsection;
	}

	public void setMyInpsection(boolean myInpsection) {
		this.myInpsection = myInpsection;
	}

	public void setLstMedicineStr(List<String> lstMedicineStr) {
		this.lstMedicineStr = lstMedicineStr;
	}

	public List<String> getLstMedicineStr() {
		lstMedicineStr = new ArrayList<>();
		for (CustomerMedicine cd : getLstMedicine()) {
			lstMedicineStr.add(cd.getId());
		}
		return lstMedicineStr;
	}

	public List<String> getLstMedicineStr(String s) {
		lstMedicineStr = new ArrayList<>();
		for (CustomerMedicine cd : getLstMedicine()) {
			if (cd.getId().toLowerCase().contains(s.toLowerCase()))
				lstMedicineStr.add(cd.getId());
		}
		return lstMedicineStr;
	}

	public List<String> getLstMedicineStr1(String s) {
		List<String> lstMedicineStr1 = new ArrayList<>();
		for (CustomerMedicine cd : getLstMedicine()) {
			if (cd.getName().toLowerCase().contains(s.toLowerCase()))
				lstMedicineStr1.add(cd.getName());
		}
		return lstMedicineStr1;
	}

	public List<String> getLstMedicineStr2(String s) {
		List<String> lstMedicineStr2 = new ArrayList<>();
		for (CustomerMedicine cd : getLstMedicine()) {
			if (cd.getiName() == null)
				continue;
			if (cd.getiName().toLowerCase().contains(s.toLowerCase()))
				lstMedicineStr2.add(cd.getiName());
		}
		return lstMedicineStr2;
	}

	public void onLstMedicineStrSelect(SelectEvent event) {
		try {
			for (CustomerMedicine cm : listMedicine)
				if (cm.getId().equals(event.getObject().toString()))
					return;
			List<Medicine> cpDtls = logicConPre.getByAnyField(Medicine.class, "id", event.getObject().toString());
			if (cpDtls.size() > 0) {
				CustomerMedicine dtl = new CustomerMedicine();
				dtl.setMedicinePkId(cpDtls.get(0).getPkId());
				dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setiName(cpDtls.get(0).getiName());
				dtl.setRepeatType(0);
				dtl.setRepeatCount(0);
				dtl.setExpireDay(0);
				dtl.setCalcDose(cpDtls.get(0).getCalcDose());
				dtl.setCalcDrugDose(cpDtls.get(0).getCalcDrugDose());
				dtl.setCalcType(cpDtls.get(0).getCalcType());
				dtl.setDrugDose(cpDtls.get(0).getDrugDose());
				dtl.setDose(cpDtls.get(0).getDrugDose());
				dtl.setStatus(Tool.ADDED);

				List<View_ConstantMedicineType> constants = logicConPre.getByAnyField(View_ConstantMedicineType.class,
						"pkId", cpDtls.get(0).getTypePkId());
				if (constants.size() > 0) {
					dtl.setMedicineType(constants.get(0).getName());
				}

				List<Measurement> measurements = logicConPre.getByAnyField(Measurement.class, "pkId",
						cpDtls.get(0).getMeasurementPkId());
				if (measurements.size() > 0) {
					dtl.setMeasurementName(measurements.get(0).getName());
				}

				listMedicine.add(dtl);
				listMedicineTmp = null;
			} else {
				getMedListFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}
	public void onLstMedicineStrSelect1(SelectEvent event) {
		try {
			for (CustomerMedicine cm : listMedicine)
				if (cm.getId().equals(event.getObject().toString()))
					return;
			List<Medicine> cpDtls = logicConPre.getByAnyField(Medicine.class, "name", event.getObject().toString());
			if (cpDtls.size() > 0) {
				CustomerMedicine dtl = new CustomerMedicine();
				dtl.setMedicinePkId(cpDtls.get(0).getPkId());
				dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setiName(cpDtls.get(0).getiName());
				dtl.setRepeatType(0);
				dtl.setRepeatCount(0);
				dtl.setExpireDay(0);
				dtl.setCalcDose(cpDtls.get(0).getCalcDose());
				dtl.setCalcDrugDose(cpDtls.get(0).getCalcDrugDose());
				dtl.setCalcType(cpDtls.get(0).getCalcType());
				dtl.setDrugDose(cpDtls.get(0).getDrugDose());
				dtl.setDose(cpDtls.get(0).getDrugDose());
				dtl.setStatus(Tool.ADDED);

				List<View_ConstantMedicineType> constants = logicConPre.getByAnyField(View_ConstantMedicineType.class,
						"pkId", cpDtls.get(0).getTypePkId());
				if (constants.size() > 0) {
					dtl.setMedicineType(constants.get(0).getName());
				}

				List<Measurement> measurements = logicConPre.getByAnyField(Measurement.class, "pkId",
						cpDtls.get(0).getMeasurementPkId());
				if (measurements.size() > 0) {
					dtl.setMeasurementName(measurements.get(0).getName());
				}

				listMedicine.add(dtl);
				listMedicineTmp = null;
			} else {
				getMedListFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void onLstMedicineStrSelect2(SelectEvent event) {
		try {
			for (CustomerMedicine cm : listMedicine)
				if (cm.getId().equals(event.getObject().toString()))
					return;
			List<Medicine> cpDtls = logicConPre.getByAnyField(Medicine.class, "iName", event.getObject().toString());
			if (cpDtls.size() > 0) {
				CustomerMedicine dtl = new CustomerMedicine();
				dtl.setMedicinePkId(cpDtls.get(0).getPkId());
				dtl.setEmployeePkId(currentEmployeeRequest.getEmployeePkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setiName(cpDtls.get(0).getiName());
				dtl.setRepeatType(0);
				dtl.setRepeatCount(0);
				dtl.setExpireDay(0);
				dtl.setCalcDose(cpDtls.get(0).getCalcDose());
				dtl.setCalcDrugDose(cpDtls.get(0).getCalcDrugDose());
				dtl.setCalcType(cpDtls.get(0).getCalcType());
				dtl.setDrugDose(cpDtls.get(0).getDrugDose());
				dtl.setDose(cpDtls.get(0).getDrugDose());
				dtl.setStatus(Tool.ADDED);

				List<View_ConstantMedicineType> constants = logicConPre.getByAnyField(View_ConstantMedicineType.class,
						"pkId", cpDtls.get(0).getTypePkId());
				if (constants.size() > 0) {
					dtl.setMedicineType(constants.get(0).getName());
				}

				List<Measurement> measurements = logicConPre.getByAnyField(Measurement.class, "pkId",
						cpDtls.get(0).getMeasurementPkId());
				if (measurements.size() > 0) {
					dtl.setMeasurementName(measurements.get(0).getName());
				}

				listMedicine.add(dtl);
				listMedicineTmp = null;
			} else {
				getMedListFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void setLstExaminationStr(List<String> lstExaminationStr) {
		this.lstExaminationStr = lstExaminationStr;
	}

	public List<String> getLstExaminationStr() {
		return lstExaminationStr;
	}

	public List<String> getLstExaminationStr(String s) {
		lstExaminationStr = new ArrayList<>();
		for (InspectionDtl cd : getLstExamination()) {
			if (cd.getId().toLowerCase().contains(s.toLowerCase()))
				lstExaminationStr.add(cd.getId());
		}
		return lstExaminationStr;
	}

	public List<String> getLstExaminationStr1(String s) {
		List<String> lstExaminationStr1 = new ArrayList<>();
		for (InspectionDtl cd : getLstExamination()) {
			if (cd.getName().toLowerCase().contains(s.toLowerCase()))
				lstExaminationStr1.add(cd.getName());
		}
		return lstExaminationStr1;
	}

	public void onLstExaminationStr(SelectEvent event) {
		try {
			for (InspectionDtl inspectionDtl : listExamination)
				if (inspectionDtl.getId().equals(event.getObject().toString()))
					return;
			List<Examination> cpDtls = logicConPre.getByAnyField(Examination.class, "id", event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();

				dtl.setType("EXAMINATION");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setExaminationDtlsSumPrice(logicConPre.examinationDtlPrice(cpDtls.get(0).getPkId()));
				dtl.setExaminationDtls(logicConPre.getExaminationDtlsByExaminationPkId(cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listExamination.add(dtl);
				listExaminationTmp = null;
				listExaminationTmpTree = null;
			} else {
				getExaTypeFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void onLstExaminationStr1(SelectEvent event) {
		try {
			for (InspectionDtl inspectionDtl : listExamination)
				if (inspectionDtl.getId().equals(event.getObject().toString()))
					return;
			List<Examination> cpDtls = logicConPre.getByAnyField(Examination.class, "name",
					event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("EXAMINATION");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setExaminationDtlsSumPrice(logicConPre.examinationDtlPrice(cpDtls.get(0).getPkId()));
				dtl.setExaminationDtls(logicConPre.getExaminationDtlsByExaminationPkId(cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listExamination.add(dtl);
				listExaminationTmp = null;
				listExaminationTmpTree = null;
			} else {
				getExaTypeFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public List<String> getLstXrayStr() {
		return lstXrayStr;
	}

	public void setLstXrayStr(List<String> lstXrayStr) {
		this.lstXrayStr = lstXrayStr;
	}

	public List<String> getLstXrayStr(String s) {
		lstXrayStr = new ArrayList<>();
		for (InspectionDtl cd : getLstXray()) {
			if (cd.getId().toLowerCase().contains(s.toLowerCase()))
				lstXrayStr.add(cd.getId());
		}
		return lstXrayStr;
	}

	public List<String> getLstXrayStr1(String s) {
		List<String> lstXrayStr1 = new ArrayList<>();
		for (InspectionDtl cd : getLstXray()) {
			if (cd.getName().toLowerCase().contains(s.toLowerCase()))
				lstXrayStr1.add(cd.getName());
		}
		return lstXrayStr1;
	}

	public void onLstXrayStr(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listXray)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			List<Xray> cpDtls = logicConPre.getByAnyField(Xray.class, "id", event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("XRAY");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setCost(logicConPre.getCost(Tool.INSPECTIONTYPE_XRAY, cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listXray.add(dtl);
				listXrayTmp = null;
			} else {
				getXrayTypesFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void onLstXrayStr1(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listXray)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			List<Xray> cpDtls = logicConPre.getByAnyField(Xray.class, "name", event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("XRAY");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setCost(logicConPre.getCost(Tool.INSPECTIONTYPE_XRAY, cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listXray.add(dtl);
				listXrayTmp = null;
			} else {
				getXrayTypesFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public List<String> getLstSurgeryStr() {
		return lstSurgeryStr;
	}

	public void setLstSurgeryStr(List<String> lstSurgeryStr) {
		this.lstSurgeryStr = lstSurgeryStr;
	}

	public List<String> getLstSurgeryStr(String s) {
		lstSurgeryStr = new ArrayList<>();
		for (InspectionDtl cd : getLstSurgery()) {
			if (cd.getId().toLowerCase().contains(s.toLowerCase()))
				lstSurgeryStr.add(cd.getId());
		}
		return lstSurgeryStr;
	}

	public List<String> getLstSurgeryStr1(String s) {
		List<String> lstSurgeryStr1 = new ArrayList<>();
		for (InspectionDtl cd : getLstSurgery()) {
			if (cd.getName().toLowerCase().contains(s.toLowerCase()))
				lstSurgeryStr1.add(cd.getName());
		}
		return lstSurgeryStr1;
	}

	public void onLstSurgeryStr(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listSurgery)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			List<Surgery> cpDtls = logicConPre.getByAnyField(Surgery.class, "name", event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("SURGERY");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				// dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setCost(logicConPre.getCost(Tool.INSPECTIONTYPE_SURGERY, cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listSurgery.add(dtl);
				listSurgeryTmp = null;
			} else {
				getSurgeryTypeFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void onLstSurgeryStr1(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listSurgery)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			List<Surgery> cpDtls = logicConPre.getByAnyField(Surgery.class, "name", event.getObject().toString());
			if (cpDtls.size() > 0) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("SURGERY");
				dtl.setTypePkId(cpDtls.get(0).getPkId());
				// dtl.setId(cpDtls.get(0).getId());
				dtl.setName(cpDtls.get(0).getName());
				dtl.setCost(logicConPre.getCost(Tool.INSPECTIONTYPE_SURGERY, cpDtls.get(0).getPkId()));
				dtl.setStatus(Tool.ADDED);
				listSurgery.add(dtl);
				listSurgeryTmp = null;
			} else {
				getSurgeryTypeFromDb();
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public List<String> getLstTreatmentStr() {
		return lstTreatmentStr;
	}

	public void setLstTreatmentStr(List<String> lstTreatmentStr) {
		this.lstTreatmentStr = lstTreatmentStr;
	}

	public List<String> getLstTreatmentStr(String s) {
		lstTreatmentStr = new ArrayList<>();
		for (InspectionDtl cd : getLstTreatment()) {
			if (cd.getId().toLowerCase().contains(s.toLowerCase()))
				lstTreatmentStr.add(cd.getId());
		}
		return lstTreatmentStr;
	}

	public List<String> getLstTreatmentStr1(String s) {
		lstTreatmentStr = new ArrayList<>();
		for (InspectionDtl cd : getLstTreatment()) {
			if (cd.getName().toLowerCase().contains(s.toLowerCase()))
				lstTreatmentStr.add(cd.getName());
		}
		return lstTreatmentStr;
	}

	public void onLstTreatmentStr(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listTreatment)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			Treatment cpDtl = logicConPre.getTreatment("id", event.getObject().toString());
			if (cpDtl != null) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("TREATMENT");
				dtl.setTypePkId(cpDtl.getPkId());
				dtl.setId(cpDtl.getId());
				dtl.setName(cpDtl.getName());
				dtl.setStatus(Tool.ADDED);
				dtl.setCost(cpDtl.getCost());
				listTreatment.add(dtl);
				listTreatmentTmp = null;
			} else {
				context().execute("PF('treatmentList').show();");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public void onLstTreatmentStr1(SelectEvent event) {
		try {
			for (InspectionDtl dtl : listTreatment)
				if (dtl.getId().equals(event.getObject().toString()))
					return;
			Treatment cpDtl = logicConPre.getTreatment("name", event.getObject().toString());
			if (cpDtl != null) {
				InspectionDtl dtl = new InspectionDtl();
				dtl.setType("TREATMENT");
				dtl.setTypePkId(cpDtl.getPkId());
				dtl.setId(cpDtl.getId());
				dtl.setName(cpDtl.getName());
				dtl.setStatus(Tool.ADDED);
				dtl.setCost(cpDtl.getCost());
				listTreatment.add(dtl);
				listTreatmentTmp = null;
			} else {
				context().execute("PF('treatmentList').show();");
			}
		} catch (Exception ex) {
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
	}

	public String getFilterKey1() {
		if (filterKey1 == null)
			filterKey1 = "";
		return filterKey1;
	}

	public void setFilterKey1(String filterKey1) {
		this.filterKey1 = filterKey1;
	}

	public Date getBeginDate() {
		if (beginDate == null) {
			beginDate = new Date();
			beginDate.setMonth(0);
			beginDate.setDate(1);
		}
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		if (endDate == null)
			endDate = new Date();
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSelectedTreatmentTypePkId() {
		return selectedTreatmentTypePkId;
	}

	public void setSelectedTreatmentTypePkId(BigDecimal selectedTreatmentTypePkId) {
		this.selectedTreatmentTypePkId = selectedTreatmentTypePkId;
	}

	public String getFilterTreatmentName() {
		return filterTreatmentName;
	}

	public void setFilterTreatmentName(String filterTreatmentName) {
		this.filterTreatmentName = filterTreatmentName;
	}

	public List<InspectionDtl> getListSurgeryInfo() {
		if (listSurgeryInfo == null)
			listSurgeryInfo = new ArrayList<InspectionDtl>();
		return listSurgeryInfo;
	}

	public void setListSurgeryInfo(List<InspectionDtl> listSurgeryInfo) {
		this.listSurgeryInfo = listSurgeryInfo;
	}

	public BigDecimal getSelectedSurgeryTypePkId() {
		return selectedSurgeryTypePkId;
	}

	public void setSelectedSurgeryTypePkId(BigDecimal selectedSurgeryTypePkId) {
		this.selectedSurgeryTypePkId = selectedSurgeryTypePkId;
	}

	public String getFilterSurgeryName() {
		return filterSurgeryName;
	}

	public void setFilterSurgeryName(String filterSurgeryName) {
		this.filterSurgeryName = filterSurgeryName;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public CustomerMatter getCustomerMatter() {
		if (customerMatter == null)
			customerMatter = new CustomerMatter();
		return customerMatter;
	}

	public void setCustomerMatter(CustomerMatter customerMatter) {
		this.customerMatter = customerMatter;
	}

	public InspectionForm getFormNose() {
		if (formNose == null)
			formNose = new InspectionForm();
		return formNose;
	}

	public void setFormNose(InspectionForm formNose) {
		this.formNose = formNose;
	}

	public InspectionForm getFormEar() {
		if (formEar == null)
			formEar = new InspectionForm();
		return formEar;
	}

	public void setFormEar(InspectionForm formEar) {
		this.formEar = formEar;
	}

	public InspectionForm getFormThroat() {
		if (formThroat == null)
			formThroat = new InspectionForm();
		return formThroat;
	}

	public void setFormThroat(InspectionForm formThroat) {
		this.formThroat = formThroat;
	}

	public String getInspectionPassword() {
		return inspectionPassword;
	}

	public void setInspectionPassword(String inspectionPassword) {
		this.inspectionPassword = inspectionPassword;
	}

	public int getIntValue() {
		return intValue;
	}

	public void setIntValue(int intValue) {
		this.intValue = intValue;
	}

	public String getGuestsearch() {
		return guestsearch;
	}

	public void setGuestsearch(String guestsearch) {
		this.guestsearch = guestsearch;
	}

	public int getGuestComeAwayCount() {
		guestComeAwayCount = 0;
		if (customerRequests == null) {
			return 0;
		} else
			for (CustomerRequest cf : customerRequests) {
				if (cf.getEmployeeRequest().getGuest() == 0)
					guestComeAwayCount++;
			}
		return guestComeAwayCount;
	}

	public void setGuestComeAwayCount(int guestComeAwayCount) {
		this.guestComeAwayCount = guestComeAwayCount;
	}

	public TreeNode getSelectedExaminationTreeNode() {
		return selectedExaminationTreeNode;
	}

	public void setSelectedExaminationTreeNode(TreeNode selectedExaminationTreeNode) {
		this.selectedExaminationTreeNode = selectedExaminationTreeNode;
	}

	public List<XrayRequest> getXrayRequests() {
		if (xrayRequests == null)
			xrayRequests = new ArrayList<XrayRequest>();
		return xrayRequests;
	}

	public void setXrayRequests(List<XrayRequest> xrayRequests) {
		this.xrayRequests = xrayRequests;
	}

	public CustomerSkin getCustomerSkin() {
		if (customerSkin == null) {
			customerSkin = new CustomerSkin();
		}
		return customerSkin;
	}

	public void setCustomerSkin(CustomerSkin customerSkin) {
		this.customerSkin = customerSkin;
	}

	public String getInpectionTypeString() {
		if (inpectionTypeString == null || inpectionTypeString.isEmpty()) {
			inpectionTypeString = "";
		}
		return inpectionTypeString;
	}

	public void setInpectionTypeString(String inpectionTypeString) {
		this.inpectionTypeString = inpectionTypeString;
	}

	public void print() {
		System.out.println("print");
		PrinterJob jon = PrinterJob.getPrinterJob();
		PageFormat pf = jon.defaultPage();
		jon.setPrintable(null, pf);
	}

	public String getListTmpTabStr() {
		listTmpTabStr = "";
		int count = 0;
		if (getListMedicineTmp().size() > 1) {
			listTmpTabStr += "0";
			count++;
		}
		if (getListExaminationTmp().size() > 1) {
			if (count > 0)
				listTmpTabStr += ",";
			listTmpTabStr += "1";
			count++;
		}
		if (getListXrayTmp().size() > 1) {
			if (count > 0)
				listTmpTabStr += ",";
			listTmpTabStr += "2";
			count++;
		}
		if (getListSurgeryTmp().size() > 1) {
			if (count > 0)
				listTmpTabStr += ",";
			listTmpTabStr += "3";
			count++;
		}
		if (getListTreatmentTmp().size() > 1) {
			if (count > 0)
				listTmpTabStr += ",";
			listTmpTabStr += "4";
			count++;
		}
		return listTmpTabStr;
	}

	public void setListTmpTabStr(String listTmpTabStr) {
		this.listTmpTabStr = listTmpTabStr;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public String getDateTime() {
		Date nowDate = new Date();

		return new SimpleDateFormat("yyyy:MM:dd hh:mm").format(nowDate);
	}

	public List<CustomerDiagnose> getDefaultPreDiagnose() {
		if (defaultPreDiagnose == null)
			defaultPreDiagnose = new ArrayList<CustomerDiagnose>();
		return defaultPreDiagnose;
	}

	public void setDefaultPreDiagnose(List<CustomerDiagnose> defaultPreDiagnose) {
		this.defaultPreDiagnose = defaultPreDiagnose;
	}

	public CustomerDiagnose getDefaultAppDiagnose() {
		if (defaultAppDiagnose == null)
			defaultAppDiagnose = new CustomerDiagnose();
		return defaultAppDiagnose;
	}

	public void setDefaultAppDiagnose(CustomerDiagnose defaultAppDiagnose) {
		this.defaultAppDiagnose = defaultAppDiagnose;
	}

	public String getSubOrgaTypes() {
		return subOrgaTypes;
	}

	public void setSubOrgaTypes(String subOrgaTypes) {
		this.subOrgaTypes = subOrgaTypes;
	}

	public List<InspectionDetail> getDetailsList() {
		return detailsList;
	}

	public void setDetailsList(List<InspectionDetail> detailsList) {
		this.detailsList = detailsList;
	}

	public List<Examination> getExaminations2List() {
		return examinations2List;
	}

	public void setExaminations2List(List<Examination> examinations2List) {
		this.examinations2List = examinations2List;
	}

	public List<Treatment> getTreatmentsList() {
		return treatmentsList;
	}

	public void setTreatmentsList(List<Treatment> treatmentsList) {
		this.treatmentsList = treatmentsList;
	}

	public List<Xray> getXrays2List() {
		return xrays2List;
	}

	public void setXrays2List(List<Xray> xrays2List) {
		this.xrays2List = xrays2List;
	}

	public List<Surgery> getSurgeries2List() {
		return surgeries2List;
	}

	public void setSurgeries2List(List<Surgery> surgeries2List) {
		this.surgeries2List = surgeries2List;
	}

	public List<CustomerAttachment> getListDefaultAttachment() {
		if (listDefaultAttachment == null)
			listDefaultAttachment = new ArrayList<CustomerAttachment>();
		return listDefaultAttachment;
	}

	public void setListDefaultAttachment(List<CustomerAttachment> listDefaultAttachment) {
		this.listDefaultAttachment = listDefaultAttachment;
	}

	public InspectionForm getFormSkin() {
		if (formSkin == null)
			formSkin = new InspectionForm();
		return formSkin;
	}

	public void setFormSkin(InspectionForm formSkin) {
		this.formSkin = formSkin;
	}

	public InspectionForm getFormLung() {
		if (formLung == null)
			formLung = new InspectionForm();
		return formLung;
	}

	public void setFormLung(InspectionForm formLung) {
		this.formLung = formLung;
	}

	public InspectionForm getFormDefault() {
		if (formDefault == null)
			formDefault = new InspectionForm();
		return formDefault;
	}

	public void setFormDefault(InspectionForm formDefault) {
		this.formDefault = formDefault;
	}

	public BigDecimal getConditionalPrescriptionOrgPkId() {
		if (conditionalPrescriptionOrgPkId == null) {
			conditionalPrescriptionOrgPkId = BigDecimal.ZERO;
			if (currentEmployee != null) {
				try {
					conditionalPrescriptionOrgPkId = logicConPre
							.getSubOrganizationPkIdByEmployeePkId(currentEmployee.getPkId());
				} catch (Exception ex) {
					getUserSessionController().showErrorMessage(ex.getMessage());
				}
			}
		}
		return conditionalPrescriptionOrgPkId;
	}

	public void setConditionalPrescriptionOrgPkId(BigDecimal conditionalPrescriptionOrgPkId) {
		this.conditionalPrescriptionOrgPkId = conditionalPrescriptionOrgPkId;
	}

	public List<SubOrganizationType> getOrganizationTypes() {
		if (organizationTypes == null) {
			try {
				organizationTypes = new ArrayList<>();
				SubOrganizationType type = new SubOrganizationType();
				type.setPkId(BigDecimal.ZERO);
				type.setName("Бүх");
				organizationTypes.addAll(logicConPre.getOrganizationTypes());
			} catch (Exception ex) {
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return organizationTypes;
	}

	public void setOrganizationTypes(List<SubOrganizationType> organizationTypes) {
		this.organizationTypes = organizationTypes;
	}

	public List<InspectionForm> getInspectionForm() {
		if (inspectionForm == null)
			inspectionForm = new ArrayList<InspectionForm>();
		return inspectionForm;
	}

	public void setInspectionForm(List<InspectionForm> inspectionForm) {
		this.inspectionForm = inspectionForm;
	}

	public CustomerDefaultInspection getCustomerDefaultInspection() {
		if (customerDefaultInspection == null)
			customerDefaultInspection = new CustomerDefaultInspection();
		return customerDefaultInspection;
	}

	public void setCustomerDefaultInspection(CustomerDefaultInspection customerDefaultInspection) {
		this.customerDefaultInspection = customerDefaultInspection;
	}

	public CustomerReInspection getCustomerReInspection() {
		if (customerReInspection == null)
			customerReInspection = new CustomerReInspection();
		return customerReInspection;
	}

	public void setCustomerReInspection(CustomerReInspection customerReInspection) {
		this.customerReInspection = customerReInspection;
	}

	public InspectionForm getFormReInspection() {
		if (formReInspection == null)
			formReInspection = new InspectionForm();
		return formReInspection;
	}

	public void setFormReInspection(InspectionForm formReInspection) {
		this.formReInspection = formReInspection;
	}

	public Date getDateFilterBy() {
		dateFilterBy = new Date();
		System.out.println("date: " + dateFilterBy);
		return dateFilterBy;
	}

	public void setDateFilterBy(Date dateFilterBy) {
		this.dateFilterBy = dateFilterBy;
	}

	public JSONObject getJsonThroat() {
		return jsonThroat;
	}

	public void setJsonThroat(JSONObject jsonThroat) {
		this.jsonThroat = jsonThroat;
	}

	public JSONObject getJsonNose() {
		return jsonNose;
	}

	public void setJsonNose(JSONObject jsonNose) {
		this.jsonNose = jsonNose;
	}

	public JSONObject getJsonEar() {
		return jsonEar;
	}

	public void setJsonEar(JSONObject jsonEar) {
		this.jsonEar = jsonEar;
	}

	public List<Integer> getListInt() {
		return listInt;
	}

	public void setListInt(List<Integer> listInt) {
		this.listInt = listInt;
	}

	public JSONObject getJsonLung() {
		return jsonLung;
	}

	public void setJsonLung(JSONObject jsonLung) {
		this.jsonLung = jsonLung;
	}

	public JSONObject getJsonSkin() {
		return jsonSkin;
	}

	public void setJsonSkin(JSONObject jsonSkin) {
		this.jsonSkin = jsonSkin;
	}

	public List<Examination> getEmrExaminations() {
		if (emrExaminations == null)
			emrExaminations = new ArrayList<Examination>();
		return emrExaminations;
	}

	public void setEmrExaminations(List<Examination> emrExaminations) {
		this.emrExaminations = emrExaminations;
	}

	public CustomerExamination getFilterExamination() {
		if (filterExamination == null)
			filterExamination = new CustomerExamination();
		return filterExamination;
	}

	public void setFilterExamination(CustomerExamination filterExamination) {
		this.filterExamination = filterExamination;
	}

	public String getDateString(Date date) {
		if (date != null)
			return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(date);
		else
			return "";
	}

	public Gt15Pain getGt15Pain() {
		return gt15Pain;
	}

	public void setGt15Pain(Gt15Pain gt15Pain) {
		this.gt15Pain = gt15Pain;
	}

	public Gt15Pain getInspectiont15Pains() {
		return inspectiont15Pains;
	}

	public void setInspectiont15Pains(Gt15Pain inspectiont15Pains) {
		this.inspectiont15Pains = inspectiont15Pains;
	}

	public List<CustomerMedicine> getListCustomerMedicines() {
		return listCustomerMedicines;
	}

	public void setListCustomerMedicines(List<CustomerMedicine> listCustomerMedicines) {
		this.listCustomerMedicines = listCustomerMedicines;
	}

	public List<CustomerMedicine> getMedicineTmp() {
		medicineTmp = new ArrayList<>();
		for (CustomerMedicine dtl : listMedicine) {
			if (!Tool.DELETE.equals(dtl.getStatus()))
				medicineTmp.add(dtl);
		}
		return medicineTmp;
	}

	public void setMedicineTmp(List<CustomerMedicine> medicineTmp) {
		this.medicineTmp = medicineTmp;
	}

	public List<InspectionDtl> getExaminationTmp() {
		examinationTmp = new ArrayList<>();
		for (InspectionDtl dtl : listExamination) {
			if (!Tool.DELETE.equals(dtl.getStatus()))
				examinationTmp.add(dtl);
		}
		return examinationTmp;
	}

	public void setExaminationTmp(List<InspectionDtl> examinationTmp) {
		this.examinationTmp = examinationTmp;
	}

	public List<InspectionDtl> getXrayTmp() {
		xrayTmp = new ArrayList<>();
		for (InspectionDtl dtl : listXray) {
			if (!Tool.DELETE.equals(dtl.getStatus()))
				xrayTmp.add(dtl);
		}
		return xrayTmp;
	}

	public void setXrayTmp(List<InspectionDtl> xrayTmp) {
		this.xrayTmp = xrayTmp;
	}

	public List<InspectionDtl> getSurgeryTmp() {
		surgeryTmp = new ArrayList<>();
		for (InspectionDtl dtl : listSurgery) {
			if (!Tool.DELETE.equals(dtl.getStatus()))
				surgeryTmp.add(dtl);
		}
		return surgeryTmp;
	}

	public void setSurgeryTmp(List<InspectionDtl> surgeryTmp) {
		this.surgeryTmp = surgeryTmp;
	}

	public List<InspectionDtl> getTreatmentTmp() {
		treatmentTmp = new ArrayList<>();
		for (InspectionDtl dtl : listTreatment) {
			if (!Tool.DELETE.equals(dtl.getStatus()))
				treatmentTmp.add(dtl);
		}
		return treatmentTmp;
	}

	public void setTreatmentTmp(List<InspectionDtl> treatmentTmp) {
		this.treatmentTmp = treatmentTmp;
	}

	public List<InspectionForm> getListInspectionForms() {
		return listInspectionForms;
	}

	public void setListInspectionForms(List<InspectionForm> listInspectionForms) {
		this.listInspectionForms = listInspectionForms;
	}

	public List<InspectionDetail> getListInspectionDetails() {
		if (listInspectionDetails == null || listInspectionDetails.isEmpty()) {
			listInspectionDetails = new ArrayList<>();
		}
		return listInspectionDetails;
	}

	public void setListInspectionDetails(List<InspectionDetail> listInspectionDetails) {
		this.listInspectionDetails = listInspectionDetails;
	}

	public String getDateNow() {
		Date d = new Date();
		return new SimpleDateFormat("yyyy-MM-dd hh:mm").format(d);
	}

	public List<CustomerDiagnose> getListCustomerDiagnosesPrint() {
		return listCustomerDiagnosesPrint;
	}

	public void setListCustomerDiagnosesPrint(List<CustomerDiagnose> listCustomerDiagnosesPrint) {
		this.listCustomerDiagnosesPrint = listCustomerDiagnosesPrint;
	}

	public JSONArray getJsonPain() {
		return jsonPain;
	}

	public void setJsonPain(JSONArray jsonPain) {
		this.jsonPain = jsonPain;
	}

	public JSONArray getJsonCheckUp() {
		return jsonCheckUp;
	}

	public void setJsonCheckUp(JSONArray jsonCheckUp) {
		this.jsonCheckUp = jsonCheckUp;
	}

	public JSONArray getJsonq() {
		return jsonq;
	}

	public void setJsonq(JSONArray jsonq) {
		this.jsonq = jsonq;
	}

	public ConditionalPrescription getSelConditionalPrescription() {
		return selConditionalPrescription;
	}

	public void setSelConditionalPrescription(ConditionalPrescription selConditionalPrescription) {
		this.selConditionalPrescription = selConditionalPrescription;
	}

	public DoctorRecipe getSelDoctorRecipe() {
		return selDoctorRecipe;
	}

	public void setSelDoctorRecipe(DoctorRecipe selDoctorRecipe) {
		this.selDoctorRecipe = selDoctorRecipe;
	}

	public Inspection getSelInspection() {
		return selInspection;
	}

	public void setSelInspection(Inspection selInspection) {
		this.selInspection = selInspection;
	}

	public List<CustomerAttachment> getlCustomerAttachments() {
		return lCustomerAttachments;
	}

	public void setlCustomerAttachments(List<CustomerAttachment> lCustomerAttachments) {
		this.lCustomerAttachments = lCustomerAttachments;
	}

	public List<InspectionDtl> getDtlList() {
		return dtlList;
	}

	public void setDtlList(List<InspectionDtl> dtlList) {
		this.dtlList = dtlList;
	}

	public BigDecimal getOcsSumAmount() {
		ocsSumAmount = BigDecimal.ZERO;
		if (listMedicine != null) {
			for (CustomerMedicine item : listMedicine) {

			}
		}
		if (listExamination != null) {
			for (InspectionDtl item : listExamination) {
				ocsSumAmount = ocsSumAmount.add(item.getExaminationDtlsSumPrice());
			}
		}
		if (listXray != null) {
			for (InspectionDtl item : listXray) {
				ocsSumAmount = ocsSumAmount.add(item.getCost());
			}
		}
		if (listSurgery != null) {
			for (InspectionDtl item : listSurgery) {
				ocsSumAmount = ocsSumAmount.add(item.getCost());
			}
		}
		if (listTreatment != null) {
			for (InspectionDtl item : listTreatment) {
				ocsSumAmount = ocsSumAmount.add(item.getCost());
			}
		}
		return ocsSumAmount;
	}

	public void setOcsSumAmount(BigDecimal ocsSumAmount) {
		this.ocsSumAmount = ocsSumAmount;
	}

	public List<Gt15Pain> getInspectionEmployeeSignatureList() {
		return inspectionEmployeeSignatureList;
	}

	public void setInspectionEmployeeSignatureList(List<Gt15Pain> inspectionEmployeeSignatureList) {
		this.inspectionEmployeeSignatureList = inspectionEmployeeSignatureList;
	}

	public String getrBase64String(String name) {
		String ret = "";
		try {
			if (name == null || name.isEmpty() || name == " " || sigNatureSelectCheck == false) {
				ret = "none";
			} else {
				if (sigNatureSelectCheck == true) {
					Path path = Paths.get("D:/MedITm/Rentgen/" + name);
					byte[] buf = Files.readAllBytes(path);

					ret = "data:image/png;base64," + new String(Base64Encoder.encode(buf));
				}

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			ex.printStackTrace();
		}
		return ret;
	}

	public CustomerAttachment getAttachment() {
		if (attachment == null)
			attachment = new CustomerAttachment();
		return attachment;
	}

	public void setAttachment(CustomerAttachment attachment) {
		this.attachmentName = attachment.getDescription();
		RequestContext.getCurrentInstance().update("form:attachmentNamePanel");
		this.attachment = attachment;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public boolean isSigNatureSelectCheck() {
		return sigNatureSelectCheck;
	}

	public void setSigNatureSelectCheck(boolean sigNatureSelectCheck) {
		this.sigNatureSelectCheck = sigNatureSelectCheck;
	}

	public boolean isPainBooleanCheck() {
		return painBooleanCheck;
	}

	public void setPainBooleanCheck(boolean painBooleanCheck) {
		this.painBooleanCheck = painBooleanCheck;
	}

	public boolean isPainEarSelectBooleanCheck() {
		return painEarSelectBooleanCheck;
	}

	public void setPainEarSelectBooleanCheck(boolean painEarSelectBooleanCheck) {
		this.painEarSelectBooleanCheck = painEarSelectBooleanCheck;
	}

	public boolean isCheckUpBooleanCheck() {
		return checkUpBooleanCheck;
	}

	public void setCheckUpBooleanCheck(boolean checkUpBooleanCheck) {
		this.checkUpBooleanCheck = checkUpBooleanCheck;
	}

	public boolean iscBooleanCheck() {
		return cBooleanCheck;
	}

	public void setcBooleanCheck(boolean cBooleanCheck) {
		this.cBooleanCheck = cBooleanCheck;
	}

	public boolean isDiagnoseBooleanSelectCheck() {
		return diagnoseBooleanSelectCheck;
	}

	public void setDiagnoseBooleanSelectCheck(boolean diagnoseBooleanSelectCheck) {
		this.diagnoseBooleanSelectCheck = diagnoseBooleanSelectCheck;
	}

	public boolean isShcudeluBooleanSelectCheck() {
		return shcudeluBooleanSelectCheck;
	}

	public void setShcudeluBooleanSelectCheck(boolean shcudeluBooleanSelectCheck) {
		this.shcudeluBooleanSelectCheck = shcudeluBooleanSelectCheck;
	}

	public CustomerHematology getCustomerHematology() {
		if (customerHematology == null) {
			customerHematology = new CustomerHematology();
		}
		return customerHematology;
	}

	public void setCustomerHematology(CustomerHematology customerHematology) {
		this.customerHematology = customerHematology;
	}

	public CustomerDiagnose getHematoAppDiagnose() {
		if (hematoAppDiagnose == null) {
			hematoAppDiagnose = new CustomerDiagnose();
		}
		return hematoAppDiagnose;
	}

	public void setHematoAppDiagnose(CustomerDiagnose hematoAppDiagnose) {
		this.hematoAppDiagnose = hematoAppDiagnose;
	}

	public List<CustomerDiagnose> getHematoPreDiagnose() {
		if (hematoPreDiagnose == null) {
			hematoPreDiagnose = new ArrayList<>();
		}
		return hematoPreDiagnose;
	}

	public void setHematoPreDiagnose(List<CustomerDiagnose> hematoPreDiagnose) {
		this.hematoPreDiagnose = hematoPreDiagnose;
	}

	public List<CustomerAttachment> getListHematoAttachment() {
		if (listHematoAttachment == null) {
			listHematoAttachment = new ArrayList<>();
		}
		return listHematoAttachment;
	}

	public void setListHematoAttachment(List<CustomerAttachment> listHematoAttachment) {
		this.listHematoAttachment = listHematoAttachment;
	}

	public InspectionForm getFormHemato() {
		if (formHemato == null) {
			formHemato = new InspectionForm();
		}
		return formHemato;
	}

	public void setFormHemato(InspectionForm formHemato) {
		this.formHemato = formHemato;
	}

	public JSONObject getJsonHematoloty() {
		return jsonHematoloty;
	}

	public void setJsonHematoloty(JSONObject jsonHematoloty) {
		this.jsonHematoloty = jsonHematoloty;
	}

	public boolean isMediaBooleanCheck() {
		return mediaBooleanCheck;
	}

	public void setMediaBooleanCheck(boolean mediaBooleanCheck) {
		this.mediaBooleanCheck = mediaBooleanCheck;
	}

	public boolean isCheckUpBooleanCheckBox() {
		return checkUpBooleanCheckBox;
	}

	public void setCheckUpBooleanCheckBox(boolean checkUpBooleanCheckBox) {
		this.checkUpBooleanCheckBox = checkUpBooleanCheckBox;
	}

	public boolean isHematoDiagnoseSelectBoolean() {
		return hematoDiagnoseSelectBoolean;
	}

	public void setHematoDiagnoseSelectBoolean(boolean hematoDiagnoseSelectBoolean) {
		this.hematoDiagnoseSelectBoolean = hematoDiagnoseSelectBoolean;
	}

	public boolean isAdviceSelectBoolean() {
		return adviceSelectBoolean;
	}

	public void setAdviceSelectBoolean(boolean adviceSelectBoolean) {
		this.adviceSelectBoolean = adviceSelectBoolean;
	}

	public String[] getSelectedConsoles2() {
		return selectedConsoles2;
	}

	public void setSelectedConsoles2(String[] selectedConsoles2) {
		this.selectedConsoles2 = selectedConsoles2;
	}

	public boolean isGeneralInspection() {
		return generalInspection;
	}

	public void setGeneralInspection(boolean generalInspection) {
		this.generalInspection = generalInspection;
	}

	public boolean isInspectionSelectBoolean() {
		return inspectionSelectBoolean;
	}

	public void setInspectionSelectBoolean(boolean inspectionSelectBoolean) {
		this.inspectionSelectBoolean = inspectionSelectBoolean;
	}

	public boolean isCheckUpSelectBoolean() {
		return checkUpSelectBoolean;
	}

	public void setCheckUpSelectBoolean(boolean checkUpSelectBoolean) {
		this.checkUpSelectBoolean = checkUpSelectBoolean;
	}

	public boolean isAdviceGeneralBoolean() {
		return adviceGeneralBoolean;
	}

	public void setAdviceGeneralBoolean(boolean adviceGeneralBoolean) {
		this.adviceGeneralBoolean = adviceGeneralBoolean;
	}

	public boolean isDefaultPreDiagnoseSelectBoolan() {
		return defaultPreDiagnoseSelectBoolan;
	}

	public void setDefaultPreDiagnoseSelectBoolan(boolean defaultPreDiagnoseSelectBoolan) {
		this.defaultPreDiagnoseSelectBoolan = defaultPreDiagnoseSelectBoolan;
	}

	public CustomerStomach getCustomerStomach() {
		if (customerStomach == null)
			customerStomach = new CustomerStomach();
		return customerStomach;
	}

	public void setCustomerStomach(CustomerStomach customerStomach) {
		this.customerStomach = customerStomach;
	}

	public CustomerKidney getCustomerKidney() {
		if (customerKidney == null) {
			customerKidney = new CustomerKidney();
		}
		return customerKidney;
	}

	public void setCustomerKidney(CustomerKidney customerKidney) {
		this.customerKidney = customerKidney;
	}

	public CustomerCardiology getCustomerCardiology() {
		if (customerCardiology == null)
			customerCardiology = new CustomerCardiology();
		return customerCardiology;
	}

	public void setCustomerCardiology(CustomerCardiology customerCardiology) {
		this.customerCardiology = customerCardiology;
	}

	public CustomerDiagnose getCordiologyAppDiagnose() {
		if (cordiologyAppDiagnose == null)
			cordiologyAppDiagnose = new CustomerDiagnose();
		return cordiologyAppDiagnose;
	}

	public void setCordiologyAppDiagnose(CustomerDiagnose cordiologyAppDiagnose) {
		this.cordiologyAppDiagnose = cordiologyAppDiagnose;
	}

	public List<CustomerDiagnose> getCardiologyPreDiagnose() {
		if (cardiologyPreDiagnose == null)
			cardiologyPreDiagnose = new ArrayList<CustomerDiagnose>();
		return cardiologyPreDiagnose;
	}

	public void setCardiologyPreDiagnose(List<CustomerDiagnose> cardiologyPreDiagnose) {
		this.cardiologyPreDiagnose = cardiologyPreDiagnose;
	}

	public InspectionForm getFormCardiology() {
		if (formCardiology == null)
			formCardiology = new InspectionForm();
		return formCardiology;
	}

	public void setFormCardiology(InspectionForm formCardiology) {
		this.formCardiology = formCardiology;
	}

	public CustomerDiagnose getKidneyAppDiagnose() {
		if (kidneyAppDiagnose == null)
			kidneyAppDiagnose = new CustomerDiagnose();
		return kidneyAppDiagnose;
	}

	public void setKidneyAppDiagnose(CustomerDiagnose kidneyAppDiagnose) {
		this.kidneyAppDiagnose = kidneyAppDiagnose;
	}

	public List<CustomerDiagnose> getKidneyPreDiagnose() {
		if (kidneyPreDiagnose == null)
			kidneyPreDiagnose = new ArrayList<CustomerDiagnose>();
		return kidneyPreDiagnose;
	}

	public void setKidneyPreDiagnose(List<CustomerDiagnose> kidneyPreDiagnose) {
		this.kidneyPreDiagnose = kidneyPreDiagnose;
	}

	public InspectionForm getFormKidney() {
		if (formKidney == null)
			formKidney = new InspectionForm();
		return formKidney;
	}

	public void setFormKidney(InspectionForm formKidney) {
		this.formKidney = formKidney;
	}

	public JSONObject getJsonKidney() {
		return jsonKidney;
	}

	public void setJsonKidney(JSONObject jsonKidney) {
		this.jsonKidney = jsonKidney;
	}

	public JSONObject getJsonCardiology() {
		return jsonCardiology;
	}

	public void setJsonCardiology(JSONObject jsonCardiology) {
		this.jsonCardiology = jsonCardiology;
	}

	public List<CustomerAttachment> getListCardioAttachment() {
		if (listCardioAttachment == null)
			listCardioAttachment = new ArrayList<CustomerAttachment>();
		return listCardioAttachment;
	}

	public void setListCardioAttachment(List<CustomerAttachment> listCardioAttachment) {
		this.listCardioAttachment = listCardioAttachment;
	}

	public List<CustomerAttachment> getListkidneyAttachment() {
		if (listkidneyAttachment == null)
			listkidneyAttachment = new ArrayList<>();
		return listkidneyAttachment;
	}

	public void setListkidneyAttachment(List<CustomerAttachment> listkidneyAttachment) {
		this.listkidneyAttachment = listkidneyAttachment;
	}

	public CustomerEye getCustomerEye() {
		if (customerEye == null)
			customerEye = new CustomerEye();
		return customerEye;
	}

	public void setCustomerEye(CustomerEye customerEye) {
		this.customerEye = customerEye;
	}

	public CustomerDiagnose getEyeAppDiagnose() {
		if (eyeAppDiagnose == null)
			eyeAppDiagnose = new CustomerDiagnose();
		return eyeAppDiagnose;
	}

	public void setEyeAppDiagnose(CustomerDiagnose eyeAppDiagnose) {
		this.eyeAppDiagnose = eyeAppDiagnose;
	}

	public List<CustomerDiagnose> getEyePreDiagnose() {
		if (eyePreDiagnose == null)
			eyePreDiagnose = new ArrayList<>();
		return eyePreDiagnose;
	}

	public void setEyePreDiagnose(List<CustomerDiagnose> eyePreDiagnose) {
		this.eyePreDiagnose = eyePreDiagnose;
	}

	public InspectionForm getFormEye() {
		if (formEye == null)
			formEye = new InspectionForm();
		return formEye;
	}

	public void setFormEye(InspectionForm formEye) {
		this.formEye = formEye;
	}

	public List<CustomerAttachment> getListEyeAttachment() {
		if (listEyeAttachment == null)
			listEyeAttachment = new ArrayList<>();
		return listEyeAttachment;
	}

	public void setListEyeAttachment(List<CustomerAttachment> listEyeAttachment) {
		this.listEyeAttachment = listEyeAttachment;
	}

	public JSONObject getJsonEye() {
		return jsonEye;
	}

	public void setJsonEye(JSONObject jsonEye) {
		this.jsonEye = jsonEye;
	}

	public List<OrderedTreatment> getOrderedTreatments() {
		return orderedTreatments;
	}

	public void setOrderedTreatments(List<OrderedTreatment> orderedTreatments) {
		this.orderedTreatments = orderedTreatments;
	}

	public String getChartData() {
		if (chartData == null)
			chartData = "";
		return chartData;
	}

	public void setChartData(String chartData) {
		this.chartData = chartData;
	}

	public CustomerTradition getCustomerTradition() {
		if (customerTradition == null) {
			customerTradition = new CustomerTradition();
		}
		return customerTradition;
	}

	public void setCustomerTradition(CustomerTradition customerTradition) {
		this.customerTradition = customerTradition;
	}

	public CustomerDiagnose getTraditionAppDiagnose() {
		if (traditionAppDiagnose == null)
			traditionAppDiagnose = new CustomerDiagnose();
		return traditionAppDiagnose;
	}

	public void setTraditionAppDiagnose(CustomerDiagnose traditionAppDiagnose) {
		this.traditionAppDiagnose = traditionAppDiagnose;
	}

	public List<CustomerDiagnose> getTraditionPreDiagnose() {
		if (traditionPreDiagnose == null)
			traditionPreDiagnose = new ArrayList<>();
		return traditionPreDiagnose;
	}

	public void setTraditionPreDiagnose(List<CustomerDiagnose> traditionPreDiagnose) {
		this.traditionPreDiagnose = traditionPreDiagnose;
	}

	public InspectionForm getFormTradition() {
		if (formTradition == null)
			formTradition = new InspectionForm();
		return formTradition;
	}

	public void setFormTradition(InspectionForm formTradition) {
		this.formTradition = formTradition;
	}

	public List<CustomerAttachment> getListTradtionAttachment() {
		if (listTradtionAttachment == null)
			listTradtionAttachment = new ArrayList<>();
		return listTradtionAttachment;
	}

	public void setListTradtionAttachment(List<CustomerAttachment> listTradtionAttachment) {
		this.listTradtionAttachment = listTradtionAttachment;
	}

	public CustomerColon getCustomerColon() {
		if (customerColon == null)
			customerColon = new CustomerColon();
		return customerColon;
	}

	public void setCustomerColon(CustomerColon customerColon) {
		this.customerColon = customerColon;
	}

	public CustomerLiver getCustomerLiver() {
		if (customerLiver == null)
			customerLiver = new CustomerLiver();
		return customerLiver;
	}

	public void setCustomerLiver(CustomerLiver customerLiver) {
		this.customerLiver = customerLiver;
	}

	public CustomerPancreas getCustomerPancreas() {
		if (customerPancreas == null)
			customerPancreas = new CustomerPancreas();
		return customerPancreas;
	}

	public void setCustomerPancreas(CustomerPancreas customerPancreas) {
		this.customerPancreas = customerPancreas;
	}

	public InspectionForm getFormStomach() {
		if (formStomach == null)
			formStomach = new InspectionForm();
		return formStomach;
	}

	public void setFormStomach(InspectionForm formStomach) {
		this.formStomach = formStomach;
	}

	public InspectionForm getFormLiver() {
		if (formLiver == null)
			formLiver = new InspectionForm();
		return formLiver;
	}

	public void setFormLiver(InspectionForm formLiver) {
		this.formLiver = formLiver;
	}

	public InspectionForm getFormPancreas() {
		if (formPancreas == null)
			formPancreas = new InspectionForm();
		return formPancreas;
	}

	public void setFormPancreas(InspectionForm formPancreas) {
		this.formPancreas = formPancreas;
	}

	public InspectionForm getFormColon() {
		if (formColon == null)
			formColon = new InspectionForm();
		return formColon;
	}

	public void setFormColon(InspectionForm formColon) {
		this.formColon = formColon;
	}

	public List<CustomerDiagnose> getLiverPreDiagnose() {
		if (liverPreDiagnose == null)
			liverPreDiagnose = new ArrayList<CustomerDiagnose>();
		return liverPreDiagnose;
	}

	public void setLiverPreDiagnose(List<CustomerDiagnose> liverPreDiagnose) {
		this.liverPreDiagnose = liverPreDiagnose;
	}

	public List<CustomerDiagnose> getStomachPreDiagnose() {
		if (stomachPreDiagnose == null)
			stomachPreDiagnose = new ArrayList<CustomerDiagnose>();
		return stomachPreDiagnose;
	}

	public void setStomachPreDiagnose(List<CustomerDiagnose> stomachPreDiagnose) {
		this.stomachPreDiagnose = stomachPreDiagnose;
	}

	public List<CustomerDiagnose> getColonPreDiagnose() {
		if (colonPreDiagnose == null)
			colonPreDiagnose = new ArrayList<CustomerDiagnose>();
		return colonPreDiagnose;
	}

	public void setColonPreDiagnose(List<CustomerDiagnose> colonPreDiagnose) {
		this.colonPreDiagnose = colonPreDiagnose;
	}

	public List<CustomerDiagnose> getPancreasPreDiagnose() {
		if (pancreasPreDiagnose == null)
			pancreasPreDiagnose = new ArrayList<CustomerDiagnose>();
		return pancreasPreDiagnose;
	}

	public void setPancreasPreDiagnose(List<CustomerDiagnose> pancreasPreDiagnose) {
		this.pancreasPreDiagnose = pancreasPreDiagnose;
	}

	public CustomerDiagnose getLiverAppDiagnose() {
		if (liverAppDiagnose == null)
			liverAppDiagnose = new CustomerDiagnose();
		return liverAppDiagnose;
	}

	public void setLiverAppDiagnose(CustomerDiagnose liverAppDiagnose) {
		this.liverAppDiagnose = liverAppDiagnose;
	}

	public CustomerDiagnose getStomachAppDiagnose() {
		if (stomachAppDiagnose == null)
			stomachAppDiagnose = new CustomerDiagnose();
		return stomachAppDiagnose;
	}

	public void setStomachAppDiagnose(CustomerDiagnose stomachAppDiagnose) {
		this.stomachAppDiagnose = stomachAppDiagnose;
	}

	public CustomerDiagnose getColonAppDiagnose() {
		if (colonAppDiagnose == null)
			colonAppDiagnose = new CustomerDiagnose();
		return colonAppDiagnose;
	}

	public void setColonAppDiagnose(CustomerDiagnose colonAppDiagnose) {
		this.colonAppDiagnose = colonAppDiagnose;
	}

	public CustomerDiagnose getPancreasAppDiagnose() {
		if (pancreasAppDiagnose == null)
			pancreasAppDiagnose = new CustomerDiagnose();
		return pancreasAppDiagnose;
	}

	public void setPancreasAppDiagnose(CustomerDiagnose pancreasAppDiagnose) {
		this.pancreasAppDiagnose = pancreasAppDiagnose;
	}

	public List<CustomerAttachment> getListEndocrineAttachment() {
		if (listEndocrineAttachment == null)
			listEndocrineAttachment = new ArrayList<>();
		return listEndocrineAttachment;
	}

	public void setListEndocrineAttachment(List<CustomerAttachment> listEndocrineAttachment) {
		this.listEndocrineAttachment = listEndocrineAttachment;
	}

	public List<CustomerAttachment> getListThryoidAttachment() {
		if (listThryoidAttachment == null)
			listThryoidAttachment = new ArrayList<>();
		return listThryoidAttachment;
	}

	public void setListThryoidAttachment(List<CustomerAttachment> listThryoidAttachment) {
		this.listThryoidAttachment = listThryoidAttachment;
	}

	public List<CustomerAttachment> getListDiabetesAttachment() {
		if (listDiabetesAttachment == null)
			listDiabetesAttachment = new ArrayList<>();
		return listDiabetesAttachment;
	}

	public void setListDiabetesAttachment(List<CustomerAttachment> listDiabetesAttachment) {
		this.listDiabetesAttachment = listDiabetesAttachment;
	}

	public CustomerEndocrine getCustomerEndocrine() {
		if (customerEndocrine == null)
			customerEndocrine = new CustomerEndocrine();
		return customerEndocrine;
	}

	public void setCustomerEndocrine(CustomerEndocrine customerEndocrine) {
		this.customerEndocrine = customerEndocrine;
	}

	public CustomerThryoid getCustomerThryoid() {
		if (customerThryoid == null)
			customerThryoid = new CustomerThryoid();
		return customerThryoid;
	}

	public void setCustomerThryoid(CustomerThryoid customerThryoid) {
		this.customerThryoid = customerThryoid;
	}

	public CustomerDiabetes getCustomerDiabetes() {
		if (customerDiabetes == null)
			customerDiabetes = new CustomerDiabetes();
		return customerDiabetes;
	}

	public void setCustomerDiabetes(CustomerDiabetes customerDiabetes) {
		this.customerDiabetes = customerDiabetes;
	}

	public CustomerDiagnose getEndocrineAppDiagnose() {
		if (endocrineAppDiagnose == null)
			endocrineAppDiagnose = new CustomerDiagnose();
		return endocrineAppDiagnose;
	}

	public void setEndocrineAppDiagnose(CustomerDiagnose endocrineAppDiagnose) {
		this.endocrineAppDiagnose = endocrineAppDiagnose;
	}

	public List<CustomerDiagnose> getEndocrinePreDiagnose() {
		if (endocrinePreDiagnose == null)
			endocrinePreDiagnose = new ArrayList<CustomerDiagnose>();
		return endocrinePreDiagnose;
	}

	public void setEndocrinePreDiagnose(List<CustomerDiagnose> endocrinePreDiagnose) {
		this.endocrinePreDiagnose = endocrinePreDiagnose;
	}

	public CustomerDiagnose getThryoidAppDiagnose() {
		if (thryoidAppDiagnose == null)
			thryoidAppDiagnose = new CustomerDiagnose();
		return thryoidAppDiagnose;
	}

	public void setThryoidAppDiagnose(CustomerDiagnose thryoidAppDiagnose) {
		this.thryoidAppDiagnose = thryoidAppDiagnose;
	}

	public List<CustomerDiagnose> getThryoidPreDiagnose() {
		if (thryoidPreDiagnose == null)
			thryoidPreDiagnose = new ArrayList<CustomerDiagnose>();
		return thryoidPreDiagnose;
	}

	public void setThryoidPreDiagnose(List<CustomerDiagnose> thryoidPreDiagnose) {
		this.thryoidPreDiagnose = thryoidPreDiagnose;
	}

	public CustomerDiagnose getDiabetesAppDiagnose() {
		if (diabetesAppDiagnose == null)
			diabetesAppDiagnose = new CustomerDiagnose();
		return diabetesAppDiagnose;
	}

	public void setDiabetesAppDiagnose(CustomerDiagnose diabetesAppDiagnose) {
		this.diabetesAppDiagnose = diabetesAppDiagnose;
	}

	public List<CustomerDiagnose> getDiabetesPreDiagnose() {
		if (diabetesPreDiagnose == null)
			diabetesPreDiagnose = new ArrayList<CustomerDiagnose>();
		return diabetesPreDiagnose;
	}

	public void setDiabetesPreDiagnose(List<CustomerDiagnose> diabetesPreDiagnose) {
		this.diabetesPreDiagnose = diabetesPreDiagnose;
	}

	public InspectionForm getFormEndocrine() {
		if (formEndocrine == null)
			formEndocrine = new InspectionForm();
		return formEndocrine;
	}

	public void setFormEndocrine(InspectionForm formEndocrine) {
		this.formEndocrine = formEndocrine;
	}

	public InspectionForm getFormThryoid() {
		if (formThryoid == null)
			formThryoid = new InspectionForm();
		return formThryoid;
	}

	public void setFormThryoid(InspectionForm formThryoid) {
		this.formThryoid = formThryoid;
	}

	public InspectionForm getFormDiabetes() {
		if (formDiabetes == null)
			formDiabetes = new InspectionForm();
		return formDiabetes;
	}

	public void setFormDiabetes(InspectionForm formDiabetes) {
		this.formDiabetes = formDiabetes;
	}

	public JSONObject getJsonEndocrine() {
		return jsonEndocrine;
	}

	public void setJsonEndocrine(JSONObject jsonEndocrine) {
		this.jsonEndocrine = jsonEndocrine;
	}

	public JSONObject getJsonThryoid() {
		return jsonThryoid;
	}

	public void setJsonThryoid(JSONObject jsonThryoid) {
		this.jsonThryoid = jsonThryoid;
	}

	public JSONObject getJsonDiabetes() {
		return jsonDiabetes;
	}

	public void setJsonDiabetes(JSONObject jsonDiabetes) {
		this.jsonDiabetes = jsonDiabetes;
	}

	public List<CustomerAttachment> getListAttachment1() {
		if (listAttachment1 == null)
			listAttachment1 = new ArrayList<CustomerAttachment>();
		return listAttachment1;
	}

	public void setListAttachment1(List<CustomerAttachment> listAttachment1) {
		this.listAttachment1 = listAttachment1;
	}

	public List<CustomerAttachment> getListAttachment2() {
		if (listAttachment2 == null)
			listAttachment2 = new ArrayList<CustomerAttachment>();
		return listAttachment2;
	}

	public void setListAttachment2(List<CustomerAttachment> listAttachment2) {
		this.listAttachment2 = listAttachment2;
	}

	public List<CustomerAttachment> getListAttachment3() {
		if (listAttachment3 == null)
			listAttachment3 = new ArrayList<CustomerAttachment>();
		return listAttachment3;
	}

	public void setListAttachment3(List<CustomerAttachment> listAttachment3) {
		this.listAttachment3 = listAttachment3;
	}

	public List<CustomerAttachment> getListAttachment4() {
		if (listAttachment4 == null)
			listAttachment4 = new ArrayList<CustomerAttachment>();
		return listAttachment4;
	}

	public void setListAttachment4(List<CustomerAttachment> listAttachment4) {
		this.listAttachment4 = listAttachment4;
	}

	public JSONObject getJsonObject1() {
		return jsonObject1;
	}

	public void setJsonObject1(JSONObject jsonObject1) {
		this.jsonObject1 = jsonObject1;
	}

	public JSONObject getJsonObject2() {
		return jsonObject2;
	}

	public void setJsonObject2(JSONObject jsonObject2) {
		this.jsonObject2 = jsonObject2;
	}

	public JSONObject getJsonObject3() {
		return jsonObject3;
	}

	public void setJsonObject3(JSONObject jsonObject3) {
		this.jsonObject3 = jsonObject3;
	}

	public JSONObject getJsonObject4() {
		return jsonObject4;
	}

	public void setJsonObject4(JSONObject jsonObject4) {
		this.jsonObject4 = jsonObject4;
	}

	public boolean isRateSelectBoolean() {
		return rateSelectBoolean;
	}

	public void setRateSelectBoolean(boolean rateSelectBoolean) {
		this.rateSelectBoolean = rateSelectBoolean;
	}

	public CustomerRehabilitation getCustomerRehabilitation() {
		if (customerRehabilitation == null)
			customerRehabilitation = new CustomerRehabilitation();
		return customerRehabilitation;
	}

	public void setCustomerRehabilitation(CustomerRehabilitation customerRehabilitation) {
		this.customerRehabilitation = customerRehabilitation;
	}

	public CustomerDiagnose getRehabilitationAppDiagnose() {
		if (rehabilitationAppDiagnose == null)
			rehabilitationAppDiagnose = new CustomerDiagnose();
		return rehabilitationAppDiagnose;
	}

	public void setRehabilitationAppDiagnose(CustomerDiagnose rehabilitationAppDiagnose) {
		this.rehabilitationAppDiagnose = rehabilitationAppDiagnose;
	}

	public List<CustomerDiagnose> getRehabilitationPreDiagnose() {
		if (rehabilitationPreDiagnose == null)
			rehabilitationPreDiagnose = new ArrayList<CustomerDiagnose>();
		return rehabilitationPreDiagnose;
	}

	public void setRehabilitationPreDiagnose(List<CustomerDiagnose> rehabilitationPreDiagnose) {
		this.rehabilitationPreDiagnose = rehabilitationPreDiagnose;
	}

	public List<CustomerAttachment> getListRehabilitationAttachment() {
		if (listRehabilitationAttachment == null)
			listRehabilitationAttachment = new ArrayList<>();
		return listRehabilitationAttachment;
	}

	public void setListRehabilitationAttachment(List<CustomerAttachment> listRehabilitationAttachment) {
		this.listRehabilitationAttachment = listRehabilitationAttachment;
	}

	public InspectionForm getFormRehabilitation() {
		if (formRehabilitation == null)
			formRehabilitation = new InspectionForm();
		return formRehabilitation;
	}

	public void setFormRehabilitation(InspectionForm formRehabilitation) {
		this.formRehabilitation = formRehabilitation;
	}

	public JSONObject getJsonRehabilitation() {
		return jsonRehabilitation;
	}

	public void setJsonRehabilitation(JSONObject jsonRehabilitation) {
		this.jsonRehabilitation = jsonRehabilitation;
	}

	public List<CustomerAttachment> getListJaundiceAttachment() {
		if (listJaundiceAttachment == null)
			listJaundiceAttachment = new ArrayList<>();
		return listJaundiceAttachment;
	}

	public void setListJaundiceAttachment(List<CustomerAttachment> listJaundiceAttachment) {
		this.listJaundiceAttachment = listJaundiceAttachment;
	}

	public List<CustomerAttachment> getListRachitisAttachment() {
		if (listRachitisAttachment == null)
			listRachitisAttachment = new ArrayList<>();
		return listRachitisAttachment;
	}

	public void setListRachitisAttachment(List<CustomerAttachment> listRachitisAttachment) {
		this.listRachitisAttachment = listRachitisAttachment;
	}

	public List<CustomerAttachment> getListDiarrhoeaAttachment() {
		if (listDiarrhoeaAttachment == null)
			listDiarrhoeaAttachment = new ArrayList<>();
		return listDiarrhoeaAttachment;
	}

	public void setListDiarrhoeaAttachment(List<CustomerAttachment> listDiarrhoeaAttachment) {
		this.listDiarrhoeaAttachment = listDiarrhoeaAttachment;
	}

	public List<CustomerAttachment> getListPediatricInfectionAttachment() {
		if (listPediatricInfectionAttachment == null)
			listPediatricInfectionAttachment = new ArrayList<>();
		return listPediatricInfectionAttachment;
	}

	public void setListPediatricInfectionAttachment(List<CustomerAttachment> listPediatricInfectionAttachment) {
		this.listPediatricInfectionAttachment = listPediatricInfectionAttachment;
	}

	public List<CustomerAttachment> getListTubeInflammationAttachment() {
		if (listTubeInflammationAttachment == null)
			listTubeInflammationAttachment = new ArrayList<>();
		return listTubeInflammationAttachment;
	}

	public void setListTubeInflammationAttachment(List<CustomerAttachment> listTubeInflammationAttachment) {
		this.listTubeInflammationAttachment = listTubeInflammationAttachment;
	}

	public CustomerJaundice getCustomerJaundice() {
		if (customerJaundice == null)
			customerJaundice = new CustomerJaundice();
		return customerJaundice;
	}

	public void setCustomerJaundice(CustomerJaundice customerJaundice) {
		this.customerJaundice = customerJaundice;
	}

	public CustomerRachitis getCustomerRachitis() {
		if (customerRachitis == null)
			customerRachitis = new CustomerRachitis();
		return customerRachitis;
	}

	public void setCustomerRachitis(CustomerRachitis customerRachitis) {
		this.customerRachitis = customerRachitis;
	}

	public CustomerDiarrhoea getCustomerDiarrhoea() {
		if (customerDiarrhoea == null)
			customerDiarrhoea = new CustomerDiarrhoea();
		return customerDiarrhoea;
	}

	public void setCustomerDiarrhoea(CustomerDiarrhoea customerDiarrhoea) {
		this.customerDiarrhoea = customerDiarrhoea;
	}

	public CustomerPediatricInfection getCustomerPediatricInfection() {
		if (customerPediatricInfection == null)
			customerPediatricInfection = new CustomerPediatricInfection();
		return customerPediatricInfection;
	}

	public void setCustomerPediatricInfection(CustomerPediatricInfection customerPediatricInfection) {
		this.customerPediatricInfection = customerPediatricInfection;
	}

	public CustomerTubeInflammation getCustomerTubeInflammation() {
		if (customerTubeInflammation == null)
			customerTubeInflammation = new CustomerTubeInflammation();
		return customerTubeInflammation;
	}

	public void setCustomerTubeInflammation(CustomerTubeInflammation customerTubeInflammation) {
		this.customerTubeInflammation = customerTubeInflammation;
	}

	public CustomerDiagnose getJaundiceAppDiagnose() {
		if (jaundiceAppDiagnose == null)
			jaundiceAppDiagnose = new CustomerDiagnose();
		return jaundiceAppDiagnose;
	}

	public void setJaundiceAppDiagnose(CustomerDiagnose jaundiceAppDiagnose) {
		this.jaundiceAppDiagnose = jaundiceAppDiagnose;
	}

	public List<CustomerDiagnose> getJaundicePreDiagnose() {
		if (jaundicePreDiagnose == null)
			jaundicePreDiagnose = new ArrayList<CustomerDiagnose>();
		return jaundicePreDiagnose;
	}

	public void setJaundicePreDiagnose(List<CustomerDiagnose> jaundicePreDiagnose) {
		this.jaundicePreDiagnose = jaundicePreDiagnose;
	}

	public CustomerDiagnose getRachitisAppDiagnose() {
		if (rachitisAppDiagnose == null)
			rachitisAppDiagnose = new CustomerDiagnose();
		return rachitisAppDiagnose;
	}

	public void setRachitisAppDiagnose(CustomerDiagnose rachitisAppDiagnose) {
		this.rachitisAppDiagnose = rachitisAppDiagnose;
	}

	public List<CustomerDiagnose> getRachitisPreDiagnose() {
		if (rachitisPreDiagnose == null)
			rachitisPreDiagnose = new ArrayList<CustomerDiagnose>();
		return rachitisPreDiagnose;
	}

	public void setRachitisPreDiagnose(List<CustomerDiagnose> rachitisPreDiagnose) {
		this.rachitisPreDiagnose = rachitisPreDiagnose;
	}

	public CustomerDiagnose getDiarrhoeaAppDiagnose() {
		if (diarrhoeaAppDiagnose == null)
			diarrhoeaAppDiagnose = new CustomerDiagnose();
		return diarrhoeaAppDiagnose;
	}

	public void setDiarrhoeaAppDiagnose(CustomerDiagnose diarrhoeaAppDiagnose) {
		this.diarrhoeaAppDiagnose = diarrhoeaAppDiagnose;
	}

	public List<CustomerDiagnose> getDiarrhoeaPreDiagnose() {
		if (diarrhoeaPreDiagnose == null)
			diarrhoeaPreDiagnose = new ArrayList<CustomerDiagnose>();
		return diarrhoeaPreDiagnose;
	}

	public void setDiarrhoeaPreDiagnose(List<CustomerDiagnose> diarrhoeaPreDiagnose) {
		this.diarrhoeaPreDiagnose = diarrhoeaPreDiagnose;
	}

	public CustomerDiagnose getPediatricInfectionAppDiagnose() {
		if (pediatricInfectionAppDiagnose == null)
			pediatricInfectionAppDiagnose = new CustomerDiagnose();
		return pediatricInfectionAppDiagnose;
	}

	public void setPediatricInfectionAppDiagnose(CustomerDiagnose pediatricInfectionAppDiagnose) {
		this.pediatricInfectionAppDiagnose = pediatricInfectionAppDiagnose;
	}

	public List<CustomerDiagnose> getPediatricInfectionPreDiagnose() {
		if (pediatricInfectionPreDiagnose == null)
			pediatricInfectionPreDiagnose = new ArrayList<CustomerDiagnose>();
		return pediatricInfectionPreDiagnose;
	}

	public void setPediatricInfectionPreDiagnose(List<CustomerDiagnose> pediatricInfectionPreDiagnose) {
		this.pediatricInfectionPreDiagnose = pediatricInfectionPreDiagnose;
	}

	public CustomerDiagnose getTubeInflammationAppDiagnose() {
		if (tubeInflammationAppDiagnose == null)
			tubeInflammationAppDiagnose = new CustomerDiagnose();
		return tubeInflammationAppDiagnose;
	}

	public void setTubeInflammationAppDiagnose(CustomerDiagnose tubeInflammationAppDiagnose) {
		this.tubeInflammationAppDiagnose = tubeInflammationAppDiagnose;
	}

	public List<CustomerDiagnose> getTubeInflammationPreDiagnose() {
		if (tubeInflammationPreDiagnose == null)
			tubeInflammationPreDiagnose = new ArrayList<CustomerDiagnose>();
		return tubeInflammationPreDiagnose;
	}

	public void setTubeInflammationPreDiagnose(List<CustomerDiagnose> tubeInflammationPreDiagnose) {
		this.tubeInflammationPreDiagnose = tubeInflammationPreDiagnose;
	}

	public InspectionForm getFormJaundice() {
		if (formJaundice == null)
			formJaundice = new InspectionForm();
		return formJaundice;
	}

	public void setFormJaundice(InspectionForm formJaundice) {
		this.formJaundice = formJaundice;
	}

	public InspectionForm getFormRachitis() {
		if (formRachitis == null)
			formRachitis = new InspectionForm();
		return formRachitis;
	}

	public void setFormRachitis(InspectionForm formRachitis) {
		this.formRachitis = formRachitis;
	}

	public InspectionForm getFormDiarrhoea() {
		if (formDiarrhoea == null)
			formDiarrhoea = new InspectionForm();
		return formDiarrhoea;
	}

	public void setFormDiarrhoea(InspectionForm formDiarrhoea) {
		this.formDiarrhoea = formDiarrhoea;
	}

	public InspectionForm getFormPediatricInfection() {
		if (formPediatricInfection == null)
			formPediatricInfection = new InspectionForm();
		return formPediatricInfection;
	}

	public void setFormPediatricInfection(InspectionForm formPediatricInfection) {
		this.formPediatricInfection = formPediatricInfection;
	}

	public InspectionForm getFormTubeInflammation() {
		if (formTubeInflammation == null)
			formTubeInflammation = new InspectionForm();
		return formTubeInflammation;
	}

	public void setFormTubeInflammation(InspectionForm formTubeInflammation) {
		this.formTubeInflammation = formTubeInflammation;
	}

	public JSONObject getJsonJaundice() {
		return jsonJaundice;
	}

	public void setJsonJaundice(JSONObject jsonJaundice) {
		this.jsonJaundice = jsonJaundice;
	}

	public JSONObject getJsonRachitis() {
		return jsonRachitis;
	}

	public void setJsonRachitis(JSONObject jsonRachitis) {
		this.jsonRachitis = jsonRachitis;
	}

	public JSONObject getJsonDiarrhoea() {
		return jsonDiarrhoea;
	}

	public void setJsonDiarrhoea(JSONObject jsonDiarrhoea) {
		this.jsonDiarrhoea = jsonDiarrhoea;
	}

	public JSONObject getJsonPediatricInfection() {
		return jsonPediatricInfection;
	}

	public void setJsonPediatricInfection(JSONObject jsonPediatricInfection) {
		this.jsonPediatricInfection = jsonPediatricInfection;
	}

	public JSONObject getJsonTubeInflammation() {
		return jsonTubeInflammation;
	}

	public void setJsonTubeInflammation(JSONObject jsonTubeInflammation) {
		this.jsonTubeInflammation = jsonTubeInflammation;
	}

	public List<CustomerAttachment> getListHeadacheAttachment() {
		if (listHeadacheAttachment == null)
			listHeadacheAttachment = new ArrayList<>();
		return listHeadacheAttachment;
	}

	public void setListHeadacheAttachment(List<CustomerAttachment> listHeadacheAttachment) {
		this.listHeadacheAttachment = listHeadacheAttachment;
	}

	public List<CustomerAttachment> getListBackacheAttachment() {
		if (listBackacheAttachment == null)
			listBackacheAttachment = new ArrayList<>();
		return listBackacheAttachment;
	}

	public void setListBackacheAttachment(List<CustomerAttachment> listBackacheAttachment) {
		this.listBackacheAttachment = listBackacheAttachment;
	}

	public List<CustomerAttachment> getListEpilepsyAttachment() {
		if (listEpilepsyAttachment == null)
			listEpilepsyAttachment = new ArrayList<>();
		return listEpilepsyAttachment;
	}

	public void setListEpilepsyAttachment(List<CustomerAttachment> listEpilepsyAttachment) {
		this.listEpilepsyAttachment = listEpilepsyAttachment;
	}

	public List<CustomerAttachment> getListApoplexyAttachment() {
		if (listApoplexyAttachment == null)
			listApoplexyAttachment = new ArrayList<>();
		return listApoplexyAttachment;
	}

	public void setListApoplexyAttachment(List<CustomerAttachment> listApoplexyAttachment) {
		this.listApoplexyAttachment = listApoplexyAttachment;
	}

	public CustomerHeadache getCustomerHeadache() {
		if (customerHeadache == null)
			customerHeadache = new CustomerHeadache();
		return customerHeadache;
	}

	public void setCustomerHeadache(CustomerHeadache customerHeadache) {
		this.customerHeadache = customerHeadache;
	}

	public CustomerBackache getCustomerBackache() {
		if (customerBackache == null)
			customerBackache = new CustomerBackache();
		return customerBackache;
	}

	public void setCustomerBackache(CustomerBackache customerBackache) {
		this.customerBackache = customerBackache;
	}

	public CustomerEpilepsy getCustomerEpilepsy() {
		if (customerEpilepsy == null)
			customerEpilepsy = new CustomerEpilepsy();
		return customerEpilepsy;
	}

	public void setCustomerEpilepsy(CustomerEpilepsy customerEpilepsy) {
		this.customerEpilepsy = customerEpilepsy;
	}

	public CustomerApoplexy getCustomerApoplexy() {
		if (customerApoplexy == null)
			customerApoplexy = new CustomerApoplexy();
		return customerApoplexy;
	}

	public void setCustomerApoplexy(CustomerApoplexy customerApoplexy) {
		this.customerApoplexy = customerApoplexy;
	}

	public CustomerDiagnose getHeadacheAppDiagnose() {
		if (headacheAppDiagnose == null)
			headacheAppDiagnose = new CustomerDiagnose();
		return headacheAppDiagnose;
	}

	public void setHeadacheAppDiagnose(CustomerDiagnose headacheAppDiagnose) {
		this.headacheAppDiagnose = headacheAppDiagnose;
	}

	public List<CustomerDiagnose> getHeadachePreDiagnose() {
		if (headachePreDiagnose == null)
			headachePreDiagnose = new ArrayList<CustomerDiagnose>();
		return headachePreDiagnose;
	}

	public void setHeadachePreDiagnose(List<CustomerDiagnose> headachePreDiagnose) {
		this.headachePreDiagnose = headachePreDiagnose;
	}

	public CustomerDiagnose getBackacheAppDiagnose() {
		if (backacheAppDiagnose == null)
			backacheAppDiagnose = new CustomerDiagnose();
		return backacheAppDiagnose;
	}

	public void setBackacheAppDiagnose(CustomerDiagnose backacheAppDiagnose) {
		this.backacheAppDiagnose = backacheAppDiagnose;
	}

	public List<CustomerDiagnose> getBackachePreDiagnose() {
		if (backachePreDiagnose == null)
			backachePreDiagnose = new ArrayList<CustomerDiagnose>();
		return backachePreDiagnose;
	}

	public void setBackachePreDiagnose(List<CustomerDiagnose> backachePreDiagnose) {
		this.backachePreDiagnose = backachePreDiagnose;
	}

	public CustomerDiagnose getEpilepsyAppDiagnose() {
		if (epilepsyAppDiagnose == null)
			epilepsyAppDiagnose = new CustomerDiagnose();
		return epilepsyAppDiagnose;
	}

	public void setEpilepsyAppDiagnose(CustomerDiagnose epilepsyAppDiagnose) {
		this.epilepsyAppDiagnose = epilepsyAppDiagnose;
	}

	public List<CustomerDiagnose> getEpilepsyPreDiagnose() {
		if (epilepsyPreDiagnose == null)
			epilepsyPreDiagnose = new ArrayList<CustomerDiagnose>();
		return epilepsyPreDiagnose;
	}

	public void setEpilepsyPreDiagnose(List<CustomerDiagnose> epilepsyPreDiagnose) {
		this.epilepsyPreDiagnose = epilepsyPreDiagnose;
	}

	public CustomerDiagnose getApoplexyAppDiagnose() {
		if (apoplexyAppDiagnose == null)
			apoplexyAppDiagnose = new CustomerDiagnose();
		return apoplexyAppDiagnose;
	}

	public void setApoplexyAppDiagnose(CustomerDiagnose apoplexyAppDiagnose) {
		this.apoplexyAppDiagnose = apoplexyAppDiagnose;
	}

	public List<CustomerDiagnose> getApoplexyPreDiagnose() {
		if (apoplexyPreDiagnose == null)
			apoplexyPreDiagnose = new ArrayList<CustomerDiagnose>();
		return apoplexyPreDiagnose;
	}

	public void setApoplexyPreDiagnose(List<CustomerDiagnose> apoplexyPreDiagnose) {
		this.apoplexyPreDiagnose = apoplexyPreDiagnose;
	}

	public InspectionForm getFormHeadache() {
		if (formHeadache == null)
			formHeadache = new InspectionForm();
		return formHeadache;
	}

	public void setFormHeadache(InspectionForm formHeadache) {
		this.formHeadache = formHeadache;
	}

	public InspectionForm getFormBackache() {
		if (formBackache == null)
			formBackache = new InspectionForm();
		return formBackache;
	}

	public void setFormBackache(InspectionForm formBackache) {
		this.formBackache = formBackache;
	}

	public InspectionForm getFormEpilepsy() {
		if (formEpilepsy == null)
			formEpilepsy = new InspectionForm();
		return formEpilepsy;
	}

	public void setFormEpilepsy(InspectionForm formEpilepsy) {
		this.formEpilepsy = formEpilepsy;
	}

	public InspectionForm getFormApoplexy() {
		if (formApoplexy == null)
			formApoplexy = new InspectionForm();
		return formApoplexy;
	}

	public void setFormApoplexy(InspectionForm formApoplexy) {
		this.formApoplexy = formApoplexy;
	}

	public JSONObject getJsonHeadache() {
		return jsonHeadache;
	}

	public void setJsonHeadache(JSONObject jsonHeadache) {
		this.jsonHeadache = jsonHeadache;
	}

	public JSONObject getJsonBackache() {
		return jsonBackache;
	}

	public void setJsonBackache(JSONObject jsonBackache) {
		this.jsonBackache = jsonBackache;
	}

	public JSONObject getJsonEpilepsy() {
		return jsonEpilepsy;
	}

	public void setJsonEpilepsy(JSONObject jsonEpilepsy) {
		this.jsonEpilepsy = jsonEpilepsy;
	}

	public JSONObject getJsonApoplexy() {
		return jsonApoplexy;
	}

	public void setJsonApoplexy(JSONObject jsonApoplexy) {
		this.jsonApoplexy = jsonApoplexy;
	}

	public String getPediatricsType() {
		return pediatricsType;
	}

	public void setPediatricsType(String pediatricsType) {
		this.pediatricsType = pediatricsType;
	}

	public List<CustomerAttachment> getListPediatricsOtherPainAttachment() {
		if (listPediatricsOtherPainAttachment == null)
			listPediatricsOtherPainAttachment = new ArrayList<CustomerAttachment>();
		return listPediatricsOtherPainAttachment;
	}

	public void setListPediatricsOtherPainAttachment(List<CustomerAttachment> listPediatricsOtherPainAttachment) {
		this.listPediatricsOtherPainAttachment = listPediatricsOtherPainAttachment;
	}

	public CustomerPediatricsOther getCustomerPediatricsOtherPain() {
		if (customerPediatricsOtherPain == null)
			customerPediatricsOtherPain = new CustomerPediatricsOther();
		return customerPediatricsOtherPain;
	}

	public void setCustomerPediatricsOtherPain(CustomerPediatricsOther customerPediatricsOtherPain) {
		this.customerPediatricsOtherPain = customerPediatricsOtherPain;
	}

	public CustomerDiagnose getPediatricsOtherPainAppDiagnose() {
		if (pediatricsOtherPainAppDiagnose == null)
			pediatricsOtherPainAppDiagnose = new CustomerDiagnose();
		return pediatricsOtherPainAppDiagnose;
	}

	public void setPediatricsOtherPainAppDiagnose(CustomerDiagnose pediatricsOtherPainAppDiagnose) {
		this.pediatricsOtherPainAppDiagnose = pediatricsOtherPainAppDiagnose;
	}

	public List<CustomerDiagnose> getPediatricsOtherPainPreDiagnose() {
		if (pediatricsOtherPainPreDiagnose == null)
			pediatricsOtherPainPreDiagnose = new ArrayList<CustomerDiagnose>();
		return pediatricsOtherPainPreDiagnose;
	}

	public void setPediatricsOtherPainPreDiagnose(List<CustomerDiagnose> pediatricsOtherPainPreDiagnose) {
		this.pediatricsOtherPainPreDiagnose = pediatricsOtherPainPreDiagnose;
	}

	public InspectionForm getFormPediatricsOtherPain() {
		if (formPediatricsOtherPain == null)
			formPediatricsOtherPain = new InspectionForm();
		return formPediatricsOtherPain;
	}

	public void setFormPediatricsOtherPain(InspectionForm formPediatricsOtherPain) {
		this.formPediatricsOtherPain = formPediatricsOtherPain;
	}

	public JSONObject getJsonPediatricsOtherPain() {
		if (jsonPediatricsOtherPain == null)
			jsonPediatricsOtherPain = new JSONObject();
		return jsonPediatricsOtherPain;
	}

	public void setJsonPediatricsOtherPain(JSONObject jsonPediatricsOtherPain) {
		this.jsonPediatricsOtherPain = jsonPediatricsOtherPain;
	}

}
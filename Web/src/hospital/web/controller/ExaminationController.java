package hospital.web.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.ExaRequest;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.ExaminationTreeNode;
import hospital.businessentity.ExaminationWithValue;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Customer;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationDoctor;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationGroup;
import hospital.entity.ExaminationGroupDtl;
import hospital.entity.ExaminationLaborant;
import hospital.entity.ExaminationPrice;
import hospital.entity.ExaminationRequest;
import hospital.entity.ExaminationTemplate;
import hospital.entity.ExaminationType;
import hospital.entity.ExaminationValueAnswer;
import hospital.entity.ExaminationValueHdr;
import hospital.entity.ExaminationValueQuestion;
import hospital.entity.Measurement;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;

import org.primefaces.context.RequestContext;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.Barcode39;

@SessionScoped
@ManagedBean(name = "examinationController")
public class ExaminationController implements Serializable {

	private static final long serialVersionUID = 1L;

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

	@EJB(beanName = "ExaminationLogic")
	ILogicExaminationLocal examLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	// Lists
	private List<ExaminationDtl> examinationDtls;
	private List<ExaminationDtl> examinationDtlsToDelete;
	private List<ExaminationType> examinationTypes;
	private List<Measurement> measurementList;
	private List<Employee> laborantEmployees;
	private List<Employee> laboratorEmployees;
	private List<String> examinationDoctors;
	private List<String> examinationLaborants;
	private List<ExaminationLaborant> laborants;
	private List<ExaminationDoctor> doctors;
	private List<Examination> examinationList;
	private List<ExaminationRequest> examinationRequests;
	private List<ExaminationRequest> drequests;
	private List<ExaminationGroup> examinationGroups;
	private List<ExaminationGroupDtl> examinationGroupDtls;
	private List<Examination> exaList;
	private List<Examination> exaList1;
	private List<ExaminationRequest> filteredRequest;
	private List<ExaRequest> exaRequest;
	private List<ExaminationValueQuestion> questions;
	private List<ExaminationValueQuestion> printableQuestions;

	private List<ExaminationWithValue> questionsWithValue;
	private List<ExaminationValueHdr> valueHdrs;
	private List<ExaminationTemplate> templates;
	// Cursors

	// Property
	private Examination currentExamination;
	private ExaminationPrice currentExaminationPrice;
	private ExaminationType examinationType;
	private ExaminationDtl currentExaminationDtl;
	private BigDecimal filterExaTypePkId;
	private ExaminationGroup currentExaminationGroup;
	private TreeNode treeExaminations;
	private TreeNode[] selectedNodes;
	private String filterKey;
	private String filterDkey;
	private String drequestTitle;
	private BigDecimal filterPkId;
	private Date beginDate;
	private Date endDate;
	private boolean requestDone;
	private BigDecimal filterExaminationPkId;
	private int filterRequestMood;
	private ExaRequest chosenDrequest;
	private String barCode;
	private StreamedContent imageRentgen;
	private Customer selectedCustomer;
	private ExaminationRequest selectedExaminationRequest;
	private boolean exaDoctor;
	private List<ExaminationResults> resultsForPrint;
	private List<String> elementNameMn;
	private List<String> elementNameEn;

	// Region - Methods

	@PostConstruct
	public void init() {
		laborants = new ArrayList<ExaminationLaborant>();
		doctors = new ArrayList<ExaminationDoctor>();
		examinationDtlsToDelete = new ArrayList<ExaminationDtl>();
		examinationDtls = new ArrayList<ExaminationDtl>();
		elementNameEn = new ArrayList<String>();
		elementNameMn = new ArrayList<String>();
		addLastRow();
	}

	public void printExaminationHdr(ExaRequest er) {
		try {
			getResultsForPrint().clear();
			printableQuestions = examLogic.getQuestions(er);
			for (ExaminationValueQuestion evq : printableQuestions) {
				ExaminationResults r = new ExaminationResults();
				r.setEvqName(evq.getName());
				if (evq.getMinValue() == null)
					if (evq.getMaxValue() == null) {
						r.setResultString("");
						r.setRange("");
					} else
					{
						r.setRange(evq.getMaxValue());
						
					}
				else if (evq.getMaxValue() == null)
					r.setRange(evq.getMinValue());
				else
					r.setRange(evq.getMinValue() + "-" + evq.getMaxValue());
				for (ExaminationValueAnswer eva : evq.getAnswers()) {
					if (Tool.BOOLEAN.equals(eva.getAnswerType())) {
						if (eva.isBooleanValue()) {
							if (r.getValue() != null)
								r.setValue(r.getValue() + eva.getValue() + "Тийм" + " ,");
							else
								r.setValue(eva.getValue() + "Тийм" + " ,");

						} else {
							if (r.getValue() != null)
								r.setValue(r.getValue() + eva.getValue() + "Үгүй" + " ,");
							else
								r.setValue(eva.getValue() + "Үгүй" + " ,");
						}

						r.setMeasurement(eva.getMeasurement());
					}
					if (Tool.MULTISELECT.equals(eva.getAnswerType())) {
						for (String s : eva.getValues()) {
							if (r.getValue() != null)
								r.setValue(r.getValue() + s + " ,");
							else
								r.setValue(s + " ,");
						}
						r.setMeasurement(eva.getMeasurement());
					}
					if (Tool.INPUTNUMBER.equals(eva.getAnswerType())) {
						if (r.getValue() != null)
							if (eva.getNumberValue() != null)
								r.setValue(r.getValue() + eva.getNumberValue().toString() + " ,");
							else
								r.setValue(r.getValue() + " " + " ,");
						else {
							if (eva.getNumberValue() != null)
								r.setValue(eva.getNumberValue().toString() + " ,");
							else
								r.setValue(" ");
						}
						r.setMeasurement(eva.getMeasurement());
					}
					if (Tool.INPUT.equals(eva.getAnswerType()) || Tool.SELECTABLE.equals(eva.getAnswerType())
							|| Tool.RADIO.equals(eva.getAnswerType()) || Tool.TEXTAREA.equals(eva.getAnswerType())) {
						if (r.getValue() != null)
							if (eva.getValue() != null)
								r.setValue(r.getValue() + eva.getValue() + " ,");
							else
								r.setValue(r.getValue() + " " + " ,");
						else {
							if (eva.getValue() != null)
								r.setValue(eva.getValue() + " ,");
							else
								r.setValue(" ");
						}
						r.setMeasurement(eva.getMeasurement());
					}
				}
				if (r.getValue() != null && r.getValue().length() > 0
						&& r.getValue().charAt(r.getValue().length() - 1) == ',') {
					r.setValue(r.getValue().substring(0, r.getValue().length() - 1));
				}
				
				resultsForPrint.add(r);
			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:dubu");

	}

	public void generateBarCode(ExaminationRequest exaRequest) {
		setSelectedExaminationRequest(exaRequest);
		try {

			Barcode39 code128 = new Barcode39();
			code128.setCode(exaRequest.getPkId().toString().trim());
			code128.setCodeType(Barcode128.CODE128);
			code128.setBarHeight(60f);
			code128.setX(1f);
			setBarCode(String.valueOf(exaRequest.getCustomer().getLastName().charAt(0)) + "."
					+ exaRequest.getCustomer().getFirstName() + " " + exaRequest.getCustomer().getAge() + " "
					+ exaRequest.getCustomer().getGenderChar() + " " + exaRequest.getExaminationName());
			java.awt.Image img1 = code128.createAwtImage(Color.BLACK, Color.WHITE);
			BufferedImage outImage = new BufferedImage(img1.getWidth(null), img1.getHeight(null),
					BufferedImage.TYPE_INT_RGB);
			outImage.getGraphics().drawImage(img1, 0, 0, null);
			ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
			ImageIO.write(outImage, "png", bytesOut);
			bytesOut.flush();
			setImageRentgen(new DefaultStreamedContent(new ByteArrayInputStream(bytesOut.toByteArray()), "image/png"));
			if (exaRequest.isComplete()) {
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:qqqqqq");
				context.update("form:barCodeSection");
				context.execute("updateUrl();");
				context.execute("PF('showImage').show();");
			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void refreshQuestionDetail(ExaRequest er) {
		try {
			setQuestions(examLogic.getQuestions(er));
			if (er.getMood() == 2 || er.getMood() == 3) {
				setValueHdrs(examLogic.getHdrs(er));
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:leExa_title");
				context.execute("PF('leExa').show();");
			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void refreshRequestList() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			examinationRequests = examLogic.getExaminationRequests(getBeginDate(), getEndDate(), filterKey, filterPkId,
					requestDone);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		context.update("form:exaRequests");
	}

	public void refreshExaminationList() {
		try {
			exaList = examLogic.getExaminations(userSessionController.getLoggedInfo());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:ddd");
	}

	public String saveExaminationRequests() {
		String ret = "";
		try {
			examLogic.saveExaminationRequests(examinationRequests, userSessionController.getLoggedInfo());
			refreshRequestList();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return ret;
	}

	public void refreshDrequest() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			for (Examination ex : exaList1)
				if (ex.getPkId().compareTo(getFilterExaminationPkId()) == 0)
					setDrequestTitle(ex.getName());
			exaRequest = examLogic.getDrequests(getFilterExaminationPkId(), getFilterDkey(), getFilterRequestMood(),
					userSessionController.getLoggedInfo().getEmployeePkId());
			setFilteredRequest(drequests);
			context.update("form:title");
			context.update("form:drequests");

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void loadDataDrequest() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			setExaDoctor(examLogic.isDoctor(userSessionController.getLoggedInfo().getEmployeePkId()));
			setExaList1(examLogic.getExaminationsWithRequestCount(userSessionController.getLoggedInfo().getEmployeePkId()));
			
			exaRequest = examLogic.getDrequests(exaList1.get(0).getPkId(), getFilterDkey(), 1, userSessionController.getLoggedInfo().getEmployeePkId());
			setDrequestTitle(exaList1.get(0).getName());
			context.update("form:title");
			context.update("form:drequests");
			context.update("form:exaSection");

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void valueChanged(ExaminationValueAnswer eva) {
		RequestContext context = RequestContext.getCurrentInstance();
		if (eva.getStatus() == null || eva.getStatus().equals(Tool.LAST))
			eva.setStatus(Tool.ADDED);
		if (eva.getStatus().equals(Tool.UNCHANGED))
			eva.setStatus(Tool.MODIFIED);
		for (ExaminationValueQuestion q : questions) {
			for (ExaminationValueAnswer a : q.getAnswers()) {
				if (a.getParentPkId() != null)
					if (eva.getPkId().compareTo(a.getParentPkId()) == 0) {
						boolean temp = false;
						if (eva.isBoolean()) {
							a.setStatus(Tool.LAST);
							if (eva.getDefaultValue().equals("0")) {
								temp = true;
							} else if (eva.getDefaultValue().equals("1")) {
								temp = false;
							}
							if (temp != eva.isBooleanValue()) {
								a.setDisabled(false);

							} else {
								a.setDisabled(true);
							}

						}
						if (eva.isRadio()) {
							a.setStatus(Tool.LAST);
							if (!eva.getValue().equals(eva.getDefaultValue()))
								a.setDisabled(false);
							else
								a.setDisabled(true);

						}
						if (eva.isSelectable()) {
							a.setStatus(Tool.LAST);
							if (!eva.getValue().equals(eva.getDefaultValue()))
								a.setDisabled(false);
							else
								a.setDisabled(true);
						}

					}
			}
		}
		context.update("form:exaValueSection");
		context.execute("fixHeader();");

	}

	public void saveDrequest(String saveType) {
		int st = (Integer.parseInt(saveType));
		RequestContext context = RequestContext.getCurrentInstance();
		for (ExaminationValueQuestion evq : questions) {
			for (ExaminationValueAnswer eva : evq.getAnswers()) {
				if (eva.getStatus() != null)
					if (eva.getStatus().equals(Tool.ADDED)) {
						if (eva.isBoolean()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							if (eva.isBooleanValue())
								evh.setValue("0");
							else
								evh.setValue("1");
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isInput()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							evh.setValue(eva.getValue());
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isSelectable()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							evh.setValue(eva.getValue());
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isRadio()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							evh.setValue(eva.getValue());
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isTextArea()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							evh.setValue(eva.getValue());
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isInputNumber()) {
							ExaminationValueHdr evh = new ExaminationValueHdr();
							evh.setQuestionPkId(evq.getPkId());
							evh.setAnswerPkId(eva.getPkId());
							evh.setRequestPkId(chosenDrequest.getPkId());
							evh.setValue(eva.getNumberValue().toString());
							evh.setStatus(Tool.ADDED);
							getValueHdrs().add(evh);

						}
						if (eva.isMulti()) {
							for (String vs : eva.getValues()) {
								ExaminationValueHdr evh = new ExaminationValueHdr();
								evh.setQuestionPkId(evq.getPkId());
								evh.setAnswerPkId(eva.getPkId());
								evh.setRequestPkId(chosenDrequest.getPkId());
								evh.setValue(vs);
								evh.setStatus(Tool.ADDED);
								getValueHdrs().add(evh);
							}

						}

					} else if (eva.getStatus().equals(Tool.MODIFIED)) {
						if (eva.isBoolean()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									if (eva.isBooleanValue())
										update.setValue("0");
									else
										update.setValue("1");
									update.setStatus(Tool.MODIFIED);
								}

							}

						}
						if (eva.isInput()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									update.setValue(eva.getValue());
									update.setStatus(Tool.MODIFIED);
								}
							}
						}
						if (eva.isSelectable()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									update.setValue(eva.getValue());
									update.setStatus(Tool.MODIFIED);
								}

							}

						}
						if (eva.isRadio()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									update.setValue(eva.getValue());
									update.setStatus(Tool.MODIFIED);
								}

							}

						}
						if (eva.isTextArea()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									update.setValue(eva.getValue());
									update.setStatus(Tool.MODIFIED);
								}
							}

						}
						if (eva.isInputNumber()) {
							for (ExaminationValueHdr update : getValueHdrs()) {
								if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
										&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
									update.setValue(eva.getNumberValue().toString());
									update.setStatus(Tool.MODIFIED);
								}

							}

						}

						if (eva.isMulti()) {
							for (String vs : eva.getValues()) {
								for (ExaminationValueHdr update : getValueHdrs()) {
									if (update.getQuestionPkId().compareTo(evq.getPkId()) == 0
											&& update.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
										update.setValue(vs);
										update.setStatus(Tool.MODIFIED);
									}

								}
							}

						}

					}
			}
		}
		try {
			examLogic.saveDrequest(valueHdrs, userSessionController.getLoggedInfo(), chosenDrequest, st);
			refreshDrequest();
			loadDataDrequest();
			userSessionController.showMessage(99);
			context.execute("PF('leExa').hide();");
			valueHdrs.clear();
			context.update("form:exaValueSection");
			context.execute("fixHeader();");

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void treeExaminations() {
		treeExaminations = new DefaultTreeNode(new ExaminationTreeNode(), null);
		if (exaList == null)
			refreshExaminationList();
		for (Examination examination : exaList) {
			ExaminationTreeNode examinationTreeNode = new ExaminationTreeNode();
			examinationTreeNode.setPkId(examination.getPkId());
			examinationTreeNode.setName(examination.getName());
			TreeNode node = new DefaultTreeNode(examinationTreeNode, treeExaminations);
			for (ExaminationDtl examinationDtl : examination.getExaminationDtls()) {
				ExaminationTreeNode examinationTreeNodeDtl = new ExaminationTreeNode();
				examinationTreeNodeDtl.setPkId(examinationDtl.getPkId());
				examinationTreeNodeDtl.setName(examinationDtl.getElementNameMn());
				@SuppressWarnings("unused")
				TreeNode node1 = new DefaultTreeNode(examinationTreeNodeDtl, node);
			}
		}
	}

	public void newExaminationGroup() {
		treeExaminations();
		getExaminationGroupDtls().clear();
		for (TreeNode tr : treeExaminations.getChildren()) {
			tr.setSelected(false);
			for (TreeNode trr : tr.getChildren()) {
				trr.setSelected(false);
			}

		}

		currentExaminationGroup = new ExaminationGroup();
		currentExaminationGroup.setStatus(Tool.ADDED);
		try {
			currentExaminationGroup.setId(examLogic.getExaminationGroupId());

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		getExaminationGroupDtls().clear();
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:ddd");

	}

	public void modifyExaminationGroup(ExaminationGroup examinationGroup) {
		currentExaminationGroup = examinationGroup;
		currentExaminationGroup.setStatus(Tool.MODIFIED);
		try {
			setExaminationGroupDtls(examLogic.getExaminationGroupDtl(examinationGroup.getPkId()));

			List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
			List<BigDecimal> bigDecimals1 = new ArrayList<BigDecimal>();
			for (ExaminationGroupDtl ex : examinationGroupDtls) {
				bigDecimals.add(ex.getExaminationPkId());
				bigDecimals1.add(ex.getExaminationDtlPkId());
			}

			for (TreeNode tr : treeExaminations.getChildren()) {
				ExaminationTreeNode examinationTreeNode = (ExaminationTreeNode) tr.getData();
				tr.setSelected(bigDecimals.contains(examinationTreeNode.getPkId()));
				for (TreeNode trr : tr.getChildren()) {
					ExaminationTreeNode examinationTreeNode1 = (ExaminationTreeNode) trr.getData();
					trr.setSelected(bigDecimals1.contains(examinationTreeNode1.getPkId()));
				}

			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:ddd");
	}

	public void saveExaminationGroup() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			examinationGroupDtls.clear();
			for (TreeNode tr : treeExaminations.getChildren()) {

				ExaminationTreeNode examinationTreeNode = (ExaminationTreeNode) tr.getData();
				if (tr.isSelected() && tr.getChildCount() == 0) {

					ExaminationGroupDtl dtl = new ExaminationGroupDtl();
					dtl.setExaminationPkId(examinationTreeNode.getPkId());
					examinationGroupDtls.add(dtl);

				} else {
					for (TreeNode trr : tr.getChildren()) {
						if (trr.isSelected()) {
							ExaminationTreeNode examinationTreeNode1 = (ExaminationTreeNode) trr.getData();
							ExaminationGroupDtl dtl = new ExaminationGroupDtl();
							dtl.setExaminationPkId(examinationTreeNode.getPkId());
							dtl.setExaminationDtlPkId(examinationTreeNode1.getPkId());
							examinationGroupDtls.add(dtl);
						}
					}
				}

			}
			if (currentExaminationGroup.getName() == null || currentExaminationGroup.getName().equals("")
					|| currentExaminationGroup.getName().equals(" ")) {
				userSessionController.showWarningMessage("Шинжилгээний багцын нэрийг оруулна уу!");
				context.execute("PF('registerExaminationGroup').hide();");
			} else if (currentExaminationGroup.getId() == null || currentExaminationGroup.getId().equals("")
					|| currentExaminationGroup.getId().equals(" ")) {
				userSessionController.showWarningMessage("Багцын дугаарыг оруулна уу!");
				context.execute("PF('registerExaminationGroup').hide();");
			} else if (examinationGroupDtls.size() <= 0) {
				userSessionController.showWarningMessage("Багцад шинжилгээ сонгоогүй байна!");
				context.execute("PF('registerExaminationGroup').hide();");
			} else {
				examLogic.saveExaminationGroup(currentExaminationGroup, examinationGroupDtls,
						userSessionController.getLoggedInfo());
				treeExaminations = null;
				setExaminationGroups(examLogic.getExaminationGroup(userSessionController.getLoggedInfo()));
			}
		} catch (Exception ex) {

		}

	}

	public void deleteExaminationGroup() {
		try {
			examLogic.saveExaminationGroup(currentExaminationGroup, examinationGroupDtls,
					userSessionController.getLoggedInfo());
			setExaminationGroups(examLogic.getExaminationGroup(userSessionController.getLoggedInfo()));
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:examinationGroups");
			context.execute("PF('confirmDialogDelete').hide();");
			userSessionController.showMessage(98);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void deleteExaminationGroup(ExaminationGroup examinationGroup) {
		currentExaminationGroup = examinationGroup;
		currentExaminationGroup.setStatus(Tool.DELETE);

	}

	public void refreshGroupDetail(BigDecimal examinationGroupPkId) {
		try {
			setExaminationGroupDtls(examLogic.getExaminationGroupDtl(examinationGroupPkId));
			for (ExaminationGroupDtl qqq : examinationGroupDtls) {
				qqq.getExaminationGroupPkId();
				RequestContext context = RequestContext.getCurrentInstance();
				context.update("form:diii");

			}
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}

	}

	public void refreshExaminationGroupList() {
		try {
			setExaminationGroups(examLogic.getExaminationGroup(userSessionController.getLoggedInfo()));
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:examinationGroups");
	}

	public void loadTableData() {
		try {
			examinationTypes = examLogic.getExaminationTypeList();
			examinationList = examLogic.getExaminationList(filterExaTypePkId, false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadData() {
		if (currentExamination == null)
			currentExamination = new Examination();

		try {
			laboratorEmployees = examLogic.getExaminationEmployeeList(1);
			laborantEmployees = examLogic.getExaminationEmployeeList(0);
			examinationTypes = examLogic.getExaminationTypeList();
			measurementList = infoLogic.getMeasurements();
			elementNameEn = examLogic.getElementNameEn();
			elementNameMn = examLogic.getElementNameMn();

			examinationLaborants = new ArrayList<String>();
			examinationDoctors = new ArrayList<String>();
			for (ExaminationLaborant laborant : laborants) {
				laborant.setStatus(Tool.MODIFIED);
				examinationLaborants.add(laborant.getEmployeePkId().toString());
			}
			for (ExaminationDoctor doctor : doctors) {
				doctor.setStatus(Tool.MODIFIED);
				examinationDoctors.add(doctor.getEmployeePkId().toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void saveExaminationType() {
		try {
			if (examinationType.getName() == null || "".equalsIgnoreCase(examinationType.getName().trim()))
				throw new Exception("Шинжилгээний төрлийн нэр хоосон байна!");
			examinationType.setStatus(Tool.ADDED);
			examinationType.setCreatedBy(userSessionController.getLoggedInfo().getUser().getPkId());
			examinationType.setCreatedDate(new Date());
			examinationType.setUpdatedBy(userSessionController.getLoggedInfo().getUser().getPkId());
			examinationType.setUpdatedDate(new Date());
			examLogic.saveExaminationType(examinationType);
			examinationTypes = examLogic.getExaminationTypeList();
			examinationType = new ExaminationType();
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showWarningMessage(ex.getMessage());
		}
	}

	public void handleBlur(ExaminationDtl exaDtl, int index) {
		RequestContext context = RequestContext.getCurrentInstance();

		if (Tool.LAST.equals(exaDtl.getStatus())) {
			exaDtl.setStatus(Tool.ADDED);
			exaDtl = currentExaminationDtl;
			addLastRow();
			context.update("form:tableExaDtl");
		}
		context.execute("document.getElementById('form:tableExaDtl:" + index
				+ ":elementNameMn').setAttribute('list', 'mnList');");
		context.execute("document.getElementById('form:tableExaDtl:" + index
				+ ":elementNameEn').setAttribute('list', 'enList');");

	}

	public void addLastItem() {
		RequestContext context = RequestContext.getCurrentInstance();

	}

	private void addLastRow() {
		ExaminationDtl dtl = new ExaminationDtl();
		dtl.setIsActive((byte) 1);
		dtl.setStatus(Tool.LAST);
		examinationDtls.add(dtl);
	}

	public void removeItem(int index) {
		RequestContext context = RequestContext.getCurrentInstance();
		ExaminationDtl examDtl = examinationDtls.get(index);
		if (examDtl.getPkId() == null)
			examinationDtls.remove(index);
		else {
			examDtl.setStatus(Tool.DELETE);
			examinationDtlsToDelete.add(examDtl);
			examinationDtls.remove(index);
		}

		context.update("form:tableExaDtl");
	}

	public String saveExamination() {
		try {

			if (currentExamination.getExaminationTypePkId().compareTo(BigDecimal.ZERO) == 0
					|| currentExamination.getExaminationTypePkId() == null)
				throw new Exception("Шинжилгээний төрөл сонгоно уу!");

			if (currentExamination.getName() == null || "".equals(currentExamination.getName().trim()))
				throw new Exception("Шинжилгээний нэр хоосон байна");

			if (currentExamination.getRoomNumber() == null || "".equals(currentExamination.getRoomNumber().trim()))
				throw new Exception("Шинжилгээ авах өрөөний дугаарыг оруулна уу!");
			if (currentExamination.getId() == null || "".equals(currentExamination.getId())
					|| " ".equals(currentExamination.getId()))
				throw new Exception("Шинжилгээний дугаарыг оруулна уу!");

			if (Tool.MODIFIED.equals(currentExamination.getStatus())) {
				if (currentExaminationPrice.getPkId() == null) {
					currentExaminationPrice.setStatus(Tool.ADDED);
				} else {
					currentExaminationPrice.setStatus(Tool.MODIFIED);
				}
			}

			if (currentExamination.isHasDtl()) {
				currentExamination.setIsActive((byte) 2);
				if (currentExaminationPrice.getPkId() == null)
					currentExaminationPrice = null;
				else
					currentExaminationPrice.setStatus(Tool.DELETE);
				setDetailStatuses();
				examinationDtls.addAll(examinationDtlsToDelete);
			} else {
				if (currentExaminationPrice.getPrice() == null
						|| currentExaminationPrice.getPrice().equals(BigDecimal.ZERO))
					throw new Exception("Шинжилгээний үнийг оруулна уу!.");
				if (currentExaminationPrice.getBeginDate() == null)
					throw new Exception("Үнэ ашиглах огноог оруулна уу!.");
				if (currentExamination.isActive())
					currentExamination.setIsActive((byte) 1);
				else
					currentExamination.setIsActive((byte) 0);
				for (ExaminationDtl examDtl : getExaminationDtls()) {
					if (examDtl.getPkId() != null) {
						examDtl.setStatus(Tool.DELETE);
					} else
						examDtl.setStatus("");
				}
				examinationDtls.addAll(examinationDtlsToDelete);
			}

			addSelectedLaborantToList();
			addSelectedDoctorToList();
			examLogic.saveExamination(currentExamination, examinationDtls, currentExaminationPrice, laborants, doctors,
					userSessionController.getLoggedInfo());
			clear();
		} catch (Exception ex) {
			userSessionController.showWarningMessage(ex.getMessage());
			ex.printStackTrace();
			return "";
		}
		userSessionController.showMessage(99);
		return "examination_list";
	}

	private void clear() {
		currentExamination = null;
		examinationDtls = null;
		currentExaminationPrice = null;
		examinationLaborants.clear();
		examinationDoctors.clear();
		laborants.clear();
		doctors.clear();
		examinationDtlsToDelete.clear();
	}

	private void setDetailStatuses() {
		int indexLast = 0;
		for (ExaminationDtl examDtl : examinationDtls) {
			if (Tool.LAST.equals(examDtl.getStatus()))
				indexLast = examinationDtls.indexOf(examDtl);
			else {
				if (examDtl.getPkId() == null)
					examDtl.setStatus(Tool.ADDED);
				else
					examDtl.setStatus(Tool.MODIFIED);
			}

		}
		if (indexLast != 0)
			examinationDtls.remove(indexLast);
	}

	private void addSelectedLaborantToList() {
		BigDecimal employeePkId;
		for (String laborantPkId : examinationLaborants) {
			employeePkId = new BigDecimal(laborantPkId);
			if (!findLaborant(employeePkId)) {
				ExaminationLaborant laborant = new ExaminationLaborant();
				laborant.setStatus(Tool.ADDED);
				laborant.setEmployeePkId(employeePkId);
				laborants.add(laborant);
			}
		}
		setDeletedStatusLaborant();
	}

	private boolean findLaborant(BigDecimal employeePkId) {
		boolean found = false;
		for (ExaminationLaborant laborant : laborants) {
			if (employeePkId.equals(laborant.getEmployeePkId())) {
				found = true;
				laborant.setStatus("");
			}
		}

		return found;
	}

	private void setDeletedStatusLaborant() {
		for (ExaminationLaborant laborant : laborants) {
			if (Tool.MODIFIED.equals(laborant.getStatus())) {
				laborant.setStatus(Tool.DELETE);
			}
		}
	}

	private void addSelectedDoctorToList() {
		BigDecimal employeePkId;
		for (String doctorPkId : examinationDoctors) {
			employeePkId = new BigDecimal(doctorPkId);
			if (!findDoctor(employeePkId)) {
				ExaminationDoctor doctor = new ExaminationDoctor();
				doctor.setStatus(Tool.ADDED);
				doctor.setEmployeePkId(employeePkId);
				doctors.add(doctor);
			}
		}
		setDeletedStatusDoctor();
	}

	private boolean findDoctor(BigDecimal employeePkId) {
		boolean found = false;
		for (ExaminationDoctor doctor : doctors) {
			if (employeePkId.equals(doctor.getEmployeePkId())) {
				found = true;
				doctor.setStatus("");
			}
		}

		return found;
	}

	private void setDeletedStatusDoctor() {
		for (ExaminationDoctor doctor : doctors) {
			if (Tool.MODIFIED.equals(doctor.getStatus())) {
				doctor.setStatus(Tool.DELETE);
			}
		}
	}

	public void onRowToggle(ToggleEvent event) {
		Examination exa = (Examination) event.getData();
		try {
			exa.setExaminationDtls(examLogic.getExaminationDtlList(exa.getPkId()));
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:tableExa");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String modifyExamination(Examination examination) {
		try {
			examinationTypes = examLogic.getExaminationTypeList();
			currentExamination = examination;
			currentExamination.setStatus(Tool.MODIFIED);
			examinationDtls = examLogic.getExaminationDtlList(examination.getPkId());
			if (examinationDtls.size() > 0) {
				currentExamination.setHasDtl(true);
			} else {
				currentExamination.setHasDtl(false);
				currentExaminationPrice = examLogic.getExaminationPrice(examination.getPkId());
			}
			laborants = examLogic.getExaminationLaborantList(examination.getPkId());
			doctors = examLogic.getExaminationDoctorList(examination.getPkId());
			loadData();
			addLastRow();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "examination_register";
	}

	public void modifyExaminationDtl(ExaminationDtl examinationDtl) {
		this.currentExaminationDtl = examinationDtl;
		try {
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void newExamination() {
		currentExamination = new Examination();
		if (currentExamination.getExaminationDtls() != null)
			currentExamination.getExaminationDtls().clear();
		currentExamination.setStatus(Tool.ADDED);
	}

	public void changeIsActive(int index) {
		try {
			examinationList.get(index).setStatus(Tool.MODIFIED);
			examLogic.saveExamination(examinationList.get(index));
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void deleteExamination() {
		try {
			currentExamination.setStatus(Tool.DELETE);
			examLogic.saveExamination(currentExamination);
			userSessionController.showMessage(98);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void setCurrentExamination(int index) {
		currentExamination = examinationList.get(index);
	}

	// EndRegion

	// Region - Getter & Setter

	public void fillExaDtls() {
		RequestContext context = RequestContext.getCurrentInstance();
		examinationDtls = new ArrayList<ExaminationDtl>();
		List<ExaminationValueQuestion> evqs = new ArrayList<ExaminationValueQuestion>();
		try {
			evqs = examLogic.getQuestions(currentExamination.getExaminationTemplatePkId());
			for (ExaminationValueQuestion evq : evqs) {
				ExaminationDtl exd = new ExaminationDtl();
				exd.setElementNameEn(evq.getName());
				if (evq.getMinValue() != null)
					exd.setMinValue(new BigDecimal(evq.getMinValue()));
				if (evq.getMaxValue() != null)
					exd.setMaxValue(new BigDecimal(evq.getMaxValue()));
				exd.setStatus(Tool.ADDED);
				examinationDtls.add(exd);
			}
			addLastRow();
			context.update("form:tableExaDtl");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}

	public ExaminationDtl getCurrentExaminationDtl() {
		return currentExaminationDtl;
	}

	public void setCurrentExaminationDtl(ExaminationDtl currentExaminationDtl) {
		this.currentExaminationDtl = currentExaminationDtl;
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

	public Examination getCurrentExamination() {
		if (currentExamination == null) {
			currentExamination = new Examination();
			currentExamination.setActive(true);
		}
		return currentExamination;
	}

	public void setCurrentExamination(Examination currentExamination) {
		this.currentExamination = currentExamination;
	}

	public IInfoLogicLocal getInfoLogic() {
		return infoLogic;
	}

	public void setInfoLogic(IInfoLogicLocal infoLogic) {
		this.infoLogic = infoLogic;
	}

	public List<ExaminationDtl> getExaminationDtls() {
		if (examinationDtls == null) {
			examinationDtls = new ArrayList<ExaminationDtl>();
			addLastRow();
		}
		return examinationDtls;
	}

	public void setExaminationDtls(List<ExaminationDtl> examinationDtls) {
		this.examinationDtls = examinationDtls;
	}

	public List<ExaminationType> getExaminationTypes() {
		return examinationTypes;
	}

	public void setExaminationTypes(List<ExaminationType> examinationTypes) {
		this.examinationTypes = examinationTypes;
	}

	public List<Measurement> getMeasurementList() {
		return measurementList;
	}

	public void setMeasurementList(List<Measurement> measurementList) {
		this.measurementList = measurementList;
	}

	public List<Employee> getLaborantEmployees() {
		if (laborantEmployees == null)
			laborantEmployees = new ArrayList<Employee>();
		return laborantEmployees;
	}

	public void setLaborantEmployees(List<Employee> laborantEmployees) {
		this.laborantEmployees = laborantEmployees;
	}

	public List<Employee> getLaboratorEmployees() {
		if (laboratorEmployees == null)
			laboratorEmployees = new ArrayList<Employee>();
		return laboratorEmployees;
	}

	public void setLaboratorEmployees(List<Employee> laboratorEmployees) {
		this.laboratorEmployees = laboratorEmployees;
	}

	public ExaminationPrice getCurrentExaminationPrice() {
		if (currentExaminationPrice == null) {
			currentExaminationPrice = new ExaminationPrice();
			currentExaminationPrice.setBeginDate(new Date());
		}
		return currentExaminationPrice;
	}

	public void setCurrentExaminationPrice(ExaminationPrice currentExaminationPrice) {
		this.currentExaminationPrice = currentExaminationPrice;
	}

	public List<String> getExaminationDoctors() {
		if (examinationDoctors == null)
			examinationDoctors = new ArrayList<String>();
		return examinationDoctors;
	}

	public void setExaminationDoctors(List<String> examinationDoctors) {
		this.examinationDoctors = examinationDoctors;
	}

	public List<String> getExaminationLaborants() {
		if (examinationLaborants == null) {
			examinationLaborants = new ArrayList<String>();
		}
		return examinationLaborants;
	}

	public void setExaminationLaborants(List<String> examinationLaborants) {
		this.examinationLaborants = examinationLaborants;
	}

	public ExaminationType getExaminationType() {
		if (examinationType == null)
			examinationType = new ExaminationType();
		return examinationType;
	}

	public void setExaminationType(ExaminationType examinationType) {
		this.examinationType = examinationType;
	}

	public List<Examination> getExaminationList() {
		return examinationList;
	}

	public void setExaminationList(List<Examination> examinationList) {
		this.examinationList = examinationList;
	}

	public BigDecimal getFilterExaTypePkId() {
		return filterExaTypePkId;
	}

	public void setFilterExaTypePkId(BigDecimal filterExaTypePkId) {
		this.filterExaTypePkId = filterExaTypePkId;
	}

	public List<ExaminationGroup> getExaminationGroups() {
		if (examinationGroups == null)
			examinationGroups = new ArrayList<ExaminationGroup>();
		return examinationGroups;
	}

	public void setExaminationGroups(List<ExaminationGroup> examinationGroups) {
		this.examinationGroups = examinationGroups;
	}

	public List<ExaminationGroupDtl> getExaminationGroupDtls() {
		if (examinationGroupDtls == null)
			examinationGroupDtls = new ArrayList<ExaminationGroupDtl>();
		return examinationGroupDtls;
	}

	public void setExaminationGroupDtls(List<ExaminationGroupDtl> examinationGroupDtls) {
		this.examinationGroupDtls = examinationGroupDtls;
	}

	public ExaminationGroup getCurrentExaminationGroup() {
		if (currentExaminationGroup == null)
			currentExaminationGroup = new ExaminationGroup();
		return currentExaminationGroup;
	}

	public void setCurrentExaminationGroup(ExaminationGroup currentExaminationGroup) {
		this.currentExaminationGroup = currentExaminationGroup;
	}

	public List<Examination> getExaList() {
		if (exaList == null)
			exaList = new ArrayList<Examination>();
		return exaList;
	}

	public void setExaList(List<Examination> exaList) {
		this.exaList = exaList;
	}

	public TreeNode[] getSelectedNodes() {
		return selectedNodes;
	}

	public void setSelectedNodes(TreeNode[] selectedNodes) {
		this.selectedNodes = selectedNodes;
	}

	public TreeNode getTreeExaminations() {
		if (treeExaminations == null)
			treeExaminations();
		return treeExaminations;
	}

	public void setTreeExaminations(TreeNode treeExaminations) {
		this.treeExaminations = treeExaminations;
	}

	public List<ExaminationRequest> getExaminationRequests() {
		if (examinationRequests == null)
			examinationRequests = new ArrayList<ExaminationRequest>();
		return examinationRequests;
	}

	public void setExaminationRequests(List<ExaminationRequest> examinationRequests) {
		this.examinationRequests = examinationRequests;
	}

	public String getFilterKey() {
		if (filterKey == null)
			filterKey = "";
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public BigDecimal getFilterPkId() {
		if (filterPkId == null)
			filterPkId = new BigDecimal("0");
		return filterPkId;
	}

	public void setFilterPkId(BigDecimal filterPkId) {
		this.filterPkId = filterPkId;
	}

	public Date getBeginDate() {
		if (beginDate == null)
			beginDate = new Date();
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

	public boolean isRequestDone() {
		return requestDone;
	}

	public void setRequestDone(boolean requestDone) {
		this.requestDone = requestDone;
	}

	// EndRegion

	public List<ExaminationRequest> getDrequests() {
		if (drequests == null)
			drequests = new ArrayList<ExaminationRequest>();
		return drequests;
	}

	public void setDrequests(List<ExaminationRequest> drequests) {
		this.drequests = drequests;
	}

	public String getFilterDkey() {
		if (filterDkey == null)
			filterDkey = "";
		return filterDkey;
	}

	public void setFilterDkey(String filterDkey) {
		this.filterDkey = filterDkey;
	}

	public String getDrequestTitle() {
		if (drequestTitle == null)
			drequestTitle = "";
		return drequestTitle;
	}

	public void setDrequestTitle(String drequestTitle) {
		this.drequestTitle = drequestTitle;
	}

	public List<Examination> getExaList1() {
		if (exaList1 == null)
			exaList1 = new ArrayList<Examination>();
		return exaList1;
	}

	public void setExaList1(List<Examination> exaList1) {
		this.exaList1 = exaList1;
	}

	public List<ExaminationRequest> getFilteredRequest() {
		if (filteredRequest == null)
			filteredRequest = new ArrayList<ExaminationRequest>();
		return filteredRequest;
	}

	public void setFilteredRequest(List<ExaminationRequest> filteredRequest) {
		this.filteredRequest = filteredRequest;
	}

	public BigDecimal getFilterExaminationPkId() {
		if (filterExaminationPkId == null)
			filterExaminationPkId = new BigDecimal("0");
		return filterExaminationPkId;
	}

	public void setFilterExaminationPkId(BigDecimal filterExaminationPkId) {
		this.filterExaminationPkId = filterExaminationPkId;
	}

	public int getFilterRequestMood() {
		return filterRequestMood;
	}

	public void setFilterRequestMood(int filterRequestMood) {
		this.filterRequestMood = filterRequestMood;
	}

	public void setFilters(String mood, BigDecimal examinationPkId) {
		setFilterRequestMood(Integer.parseInt(mood));
		setFilterExaminationPkId(examinationPkId);

	}

	public ExaRequest getChosenDrequest() {
		if (chosenDrequest == null)
			chosenDrequest = new ExaRequest();
		return chosenDrequest;
	}

	public void setChosenDrequest(ExaRequest chosenDrequest) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:leExa_title");
		this.chosenDrequest = chosenDrequest;
	}

	public List<ExaRequest> getExaRequest() {
		if (exaRequest == null)
			exaRequest = new ArrayList<ExaRequest>();
		return exaRequest;
	}

	public void setExaRequest(List<ExaRequest> exaRequest) {
		this.exaRequest = exaRequest;
	}

	public List<ExaminationValueQuestion> getQuestions() {
		if (questions == null)
			questions = new ArrayList<ExaminationValueQuestion>();
		return questions;
	}

	public void setQuestions(List<ExaminationValueQuestion> questions) {
		this.questions = questions;
	}

	public List<ExaminationWithValue> getQuestionsWithValue() {
		if (questionsWithValue == null)
			questionsWithValue = new ArrayList<ExaminationWithValue>();
		return questionsWithValue;
	}

	public void setQuestionsWithValue(List<ExaminationWithValue> questionsWithValue) {
		this.questionsWithValue = questionsWithValue;
	}

	public List<ExaminationValueHdr> getValueHdrs() {
		if (valueHdrs == null)
			valueHdrs = new ArrayList<ExaminationValueHdr>();
		return valueHdrs;
	}

	public void setValueHdrs(List<ExaminationValueHdr> valueHdrs) {
		this.valueHdrs = valueHdrs;
	}

	public String getBarCode() {
		if (barCode == null)
			barCode = "";
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public List<ExaminationTemplate> getTemplates() {
		if (templates == null)
			try {
				templates = examLogic.getTemplates();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		return templates;
	}

	public void setTemplates(List<ExaminationTemplate> templates) {
		this.templates = templates;
	}

	public StreamedContent getImageRentgen() {
		return imageRentgen;
	}

	public void setImageRentgen(StreamedContent imageRentgen) {
		this.imageRentgen = imageRentgen;
	}

	public Customer getSelectedCustomer() {
		if (selectedCustomer == null)
			selectedCustomer = new Customer();
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	public ExaminationRequest getSelectedExaminationRequest() {
		if (selectedExaminationRequest == null)
			selectedExaminationRequest = new ExaminationRequest();
		return selectedExaminationRequest;
	}

	public void setSelectedExaminationRequest(ExaminationRequest selectedExaminationRequest) {
		this.selectedExaminationRequest = selectedExaminationRequest;
	}

	public boolean isExaDoctor() {
		try {
			exaDoctor = examLogic.isDoctor(userSessionController.getLoggedInfo().getEmployeePkId());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return exaDoctor;
	}

	public void setExaDoctor(boolean exaDoctor) {
		this.exaDoctor = exaDoctor;
	}

	public List<ExaminationResults> getResultsForPrint() {
		if (resultsForPrint == null)
			resultsForPrint = new ArrayList<ExaminationResults>();
		return resultsForPrint;
	}

	public void setResultsForPrint(List<ExaminationResults> resultsForPrint) {
		this.resultsForPrint = resultsForPrint;
	}

	public List<ExaminationValueQuestion> getPrintableQuestions() {
		if (printableQuestions == null)
			printableQuestions = new ArrayList<ExaminationValueQuestion>();
		return printableQuestions;
	}

	public void setPrintableQuestions(List<ExaminationValueQuestion> printableQuestions) {
		this.printableQuestions = printableQuestions;
	}

	public List<String> getElementNameEn() {
		if (elementNameEn == null)
			elementNameEn = new ArrayList<String>();
		return elementNameEn;
	}

	public void setElementNameEn(List<String> elementNameEn) {
		this.elementNameEn = elementNameEn;
	}

	public void setElementNameMn(List<String> elementNameMn) {
		this.elementNameMn = elementNameMn;
	}

	public List<String> getElementNameMn() {
		if (elementNameMn == null)
			elementNameMn = new ArrayList<String>();
		return elementNameMn;
	}

}

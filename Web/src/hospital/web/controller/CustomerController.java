package hospital.web.controller;

import hospital.businessentity.ExaRequest;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.Tool;
import hospital.businesslogic.LogicConditionalPrescription;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.businesslogic.interfaces.ILogicCustomerLocal;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.Aimag;
import hospital.entity.Customer;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.ExaminationType;
import hospital.entity.ExaminationValueAnswer;
import hospital.entity.ExaminationValueQuestion;
import hospital.entity.Menu;
import hospital.entity.Soum;
import hospital.entity.ViewConstantCountry;
import hospital.entity.ViewConstantIsChild;
import hospital.entity.ViewConstantJob;
import hospital.entity.ViewConstantSocialStatus;
import hospital.entity.View_ConstantArrange;
import hospital.entity.View_ConstantFamilyRelation;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "customerController")
public class CustomerController {

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

	// List
	private List<Customer> customers;
	private List<ViewConstantJob> jobs;
	private List<ViewConstantIsChild> isChilds;
	private List<ViewConstantSocialStatus> socialStatuss;
	private List<Aimag> aimags;
	private List<Soum> sums;
	private List<Soum> insuranceSums;
	private List<ViewConstantCountry> countries;
	private List<View_ConstantFamilyRelation> frs;
	private List<View_ConstantArrange> arranges;
	private List<ExaminationRequestCompleted> erc;
	private List<ExaminationRequestCompleted> filteredErc;
	private List<ExaminationType> examinationTypes;

	// Cursors
	private Customer currentCustomer;
	private Aimag currentAimag;

	// Filters
	private String filterKey;
	private String filterKey1;
	private Date currentDate;
	private String savePath;
	private Customer customer;
	private boolean updateSums = false;
	private Date beginDate;
	private Date endDate;
	private BigDecimal filterPkId;

	//
	private BigDecimal jobPkId;
	private BigDecimal isChildPkId;

	// Cass - and hereglegdej baigaa
	private String customerRegNumber;
	private boolean hasSaveCustomer;

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

	public List<ExaminationType> getExaminationTypes() {
		if (examinationTypes == null)
			examinationTypes = new ArrayList<ExaminationType>();
		return examinationTypes;
	}

	public void setExaminationTypes(List<ExaminationType> examinationTypes) {
		this.examinationTypes = examinationTypes;
	}

	public List<ExaminationRequestCompleted> getFilteredErc() {
		if (filteredErc == null)
			filteredErc = new ArrayList<ExaminationRequestCompleted>();
		return filteredErc;
	}

	public void setFilteredErc(List<ExaminationRequestCompleted> filteredErc) {
		this.filteredErc = filteredErc;
	}

	public BigDecimal getFilterPkId() {
		if (filterPkId == null)
			filterPkId = BigDecimal.ZERO;
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

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndDate() {
		if (endDate == null)
			endDate = new Date();
		return endDate;
	}

	public List<Customer> getCustomers() {
		if (customers == null)
			customers = new ArrayList<Customer>();
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public String getFilterKey1() {
		if (filterKey1 == null)
			filterKey1 = "";
		return filterKey1;
	}

	public void setFilterKey1(String filterKey1) {
		this.filterKey1 = filterKey1;
	}

	public List<ExaminationRequestCompleted> getErc() {
		if (erc == null)
			erc = new ArrayList<ExaminationRequestCompleted>();
		return erc;
	}

	public void setErc(List<ExaminationRequestCompleted> erc) {
		this.erc = erc;
	}

	public List<View_ConstantFamilyRelation> getFrs() {
		if (frs == null) {
			try {
				frs = logicCustomer.getFamilyRelations();
			} catch (Exception ex) {
			}
		}
		return frs;
	}

	public void setFrs(List<View_ConstantFamilyRelation> frs) {
		this.frs = frs;
	}

	public List<View_ConstantArrange> getArranges() {
		if (arranges == null) {
			try {
				arranges = logicCustomer.getArranges();
			} catch (Exception ex) {
			}
		}
		return arranges;
	}

	public void setArranges(List<View_ConstantArrange> arranges) {
		this.arranges = arranges;
	}

	public List<ViewConstantJob> getJobs() {
		if (jobs == null)
			jobs = new ArrayList<ViewConstantJob>();
		return jobs;
	}

	public void setJobs(List<ViewConstantJob> viewConstantJobs) {
		this.jobs = viewConstantJobs;
	}

	public List<ViewConstantIsChild> getIsChilds() {
		if (isChilds == null)
			isChilds = new ArrayList<ViewConstantIsChild>();
		return isChilds;
	}

	public void setIsChilds(List<ViewConstantIsChild> viewConstantIsChilds) {
		this.isChilds = viewConstantIsChilds;
	}

	public List<ViewConstantSocialStatus> getSocialStatuss() {
		if (socialStatuss == null)
			socialStatuss = new ArrayList<ViewConstantSocialStatus>();
		return socialStatuss;
	}

	public void setViewConstantSocialStatuss(List<ViewConstantSocialStatus> viewConstantSocialStatuss) {
		this.socialStatuss = viewConstantSocialStatuss;
	}

	public Customer getCurrentCustomer() {
		if (currentCustomer == null)
			currentCustomer = new Customer();
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public String getFilterKey() {
		if (filterKey == null)
			filterKey = "";
		return filterKey;
	}

	public void setFilterKey(String filterKey) {
		this.filterKey = filterKey;
	}

	public Date getCurrentDate() {
		if (currentDate == null)
			currentDate = new Date();
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public Customer getCustomer() {
		if (customer == null)
			customer = new Customer();
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void newCustomer() {
		try {
			currentCustomer = new Customer();
			// subotnik
			currentCustomer.setCardNumber(infoLogic.getGeneratedPkId());
			currentCustomer.setStatus(Tool.ADDED);
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}
	}

	public String modifiedCustomer(Customer customer) {
		currentCustomer = customer;
		if ("Хүүхэд /0-15/".equals(customer.getSocialStatus())) {
			setIsChildPkId(customer.getJob());
		} else if ("Сонгох".equals(customer.getSocialStatus()) || "Ажилгүй".equals(customer.getSocialStatus())) {
			setIsChildPkId(BigDecimal.ZERO);
			setJobPkId(BigDecimal.ZERO);
		} else {
			setJobPkId(customer.getJob());
		}
		try {
			jobs = logicCustomer.getViewConstantJobs();
			socialStatuss = logicCustomer.getViewConstantSocialStatuss();
			aimags = infoLogic.getAimags();
			sums = infoLogic.getSoums(currentCustomer.getAimagPkId());
			insuranceSums = infoLogic.getSoums(currentCustomer.getInsuranceAimagPkId());
			isChilds = logicCustomer.getViewConstantIsChilds();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		currentCustomer.setStatus(Tool.MODIFIED);
		return "customer_register";

	}

	public void deleteCustomer(Customer customer) {
		currentCustomer = customer;
		// currentCustomer.setStatus(Tool.DELETE);
	}

	public String deleteCustomer() {
		try {
			logicCustomer.saveCustomer(currentCustomer, userSessionController.getLoggedInfo());
			customers = logicCustomer.getCustomers(getFilterKey());
			setCustomerRegNumber(currentCustomer.getRegNumber());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		return "customer_list";
	}

	public String saveCustomer() {
		String ret = "";
		if (currentCustomer.getCardNumber() == null || "".equals(currentCustomer.getCardNumber())) {
			userSessionController.showMessage(60);
			return ret;
		}

		if (currentCustomer.getRegNumber() == null || currentCustomer.getRegNumber().isEmpty()) {
			userSessionController.showMessage(64);
			return ret;
		} else {
			if (!Tool.isRegNumber(currentCustomer.getRegNumber())) {
				currentCustomer.setRegNumber("");
				userSessionController.showMessage(64);
				return ret;
			}
		}

		if (currentCustomer.getLastName() == null || "".equals(currentCustomer.getLastName())) {
			userSessionController.showMessage(61);
			return ret;
		}

		if (currentCustomer.getFirstName() == null || "".equals(currentCustomer.getFirstName())) {
			userSessionController.showMessage(62);
			return ret;
		}

		if (currentCustomer.getPhoneNumber() == null || "".equals(currentCustomer.getPhoneNumber())) {
			userSessionController.showMessage(63);
			return ret;
		}

		if (currentCustomer.getAge() < 0) {
			userSessionController.showMessage(64);
			return ret;
		}

		if (currentCustomer.getMonth() < 0 || currentCustomer.getMonth() > 12) {
			userSessionController.showMessage(64);
			return ret;
		}

		if ("Хүүхэд /0-15/".equals(currentCustomer.getSocialStatus())) {
			currentCustomer.setJob(getIsChildPkId());
		} else if ("Сонгох".equals(currentCustomer.getSocialStatus())
				|| "Ажилгүй".equals(currentCustomer.getSocialStatus())) {
			currentCustomer.setJob(null);
		} else {
			currentCustomer.setJob(getJobPkId());
		}

		try {
			if (currentCustomer.getUserPkId() == null)
				currentCustomer.setUserPkId(userSessionController.getLoggedInfo().getUser().getPkId());
			if (currentCustomer.getStartDate() == null) {
				currentCustomer.setStartDate(new Date());
			}
			currentCustomer.getRegNumber().toUpperCase();
			customer = logicCustomer.saveCustomer(currentCustomer, userSessionController.getLoggedInfo());
			customers = logicCustomer.getCustomers(getFilterKey());
			userSessionController.showMessage(99);
			String regNum = currentCustomer.getRegNumber();
			currentCustomer = new Customer();
			setCustomerRegNumber(regNum);
			setHasSaveCustomer(true);
			// currentCustomer = null;
			// ret = "customer_list";

			if ("customerRequest".equals(getSavePath())) {
				// ret = "customerRequest";
			} else {
				for (Menu menu : getUserSessionController().getMenus()) {
					if (menu.getId().equals("cash_list")) {
						// ret = "cash_register";
					}
				}
			}
		} catch (Exception ex) {
			if (ex.getMessage().equals("duplicate")) {
				userSessionController.showMessage(65);
				return ret;
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('questionDialog').show();");

		applicationController.setListCustomer(null);
		return ret;
	}

	public String getSavePath() {
		if (savePath != null && !savePath.equals("")) {
			String path = savePath;
			savePath = "";
			return path;
		}
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void loadCustomers() {
		try {
			getCustomers();
			customers = logicCustomer.getCustomers(getFilterKey());
			getFilteredErc().clear();
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());

		}

	}

	public void loadData() {
		try {

			foreignDetail();
			org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
			if (currentCustomer.getPkId() == null) {
				jobs = logicCustomer.getViewConstantJobs();
				socialStatuss = logicCustomer.getViewConstantSocialStatuss();
				aimags = infoLogic.getAimags();
				isChilds = logicCustomer.getViewConstantIsChilds();
			}

			hideJob(currentCustomer.getSocialStatus());
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
		// context Update hiine
	}

	public boolean isSelectCustomer() {
		if (currentCustomer != null)
			return true;
		else
			return false;
	}

	public Aimag getCurrentAimag() {
		return currentAimag;
	}

	public void setCurrentAimag(Aimag currentAimag) {
		if (sums == null || sums.size() < 1 || updateSums) {
			try {
				sums = infoLogic.getSoums(currentAimag.getPkId());
				updateSums = false;
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());

			}
		}
		this.currentAimag = currentAimag;
	}

	public List<Aimag> getAimags() {
		return aimags;
	}

	public void setAimags(List<Aimag> aimags) {
		this.aimags = aimags;
	}

	public void setUpdateSums(boolean updateSums) {
		this.updateSums = updateSums;
	}

	public void updateSums() {
		setUpdateSums(true);
		for (Aimag item : aimags) {
			if (currentCustomer.getAimagPkId().compareTo(item.getPkId()) == 0)
				setCurrentAimag(item);
		}
	}

	public List<Soum> getSums() {
		return sums;
	}

	public void setSums(List<Soum> sums) {
		this.sums = sums;
	}

	public void hideJob(String socialStatus) {
		org.primefaces.context.RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		if ("Хүүхэд /0-15/".equals(socialStatus)) {
			context.execute("hideWorker();");
		} else if ("Сонгох".equals(socialStatus) || "Ажилгүй".equals(socialStatus)) {
			context.execute("hideChildWorker();");
		} else {
			context.execute("hideChild();");
		}
	}

	public BigDecimal getJobPkId() {
		return jobPkId;
	}

	public void setJobPkId(BigDecimal jobPkId) {
		this.jobPkId = jobPkId;
	}

	public BigDecimal getIsChildPkId() {
		return isChildPkId;
	}

	public void setIsChildPkId(BigDecimal isChildPkId) {
		this.isChildPkId = isChildPkId;
	}

	public String getCustomerRegNumber() {
		return customerRegNumber;
	}

	public void setCustomerRegNumber(String customerRegNumber) {
		this.customerRegNumber = customerRegNumber;
	}

	public boolean isHasSaveCustomer() {
		if (hasSaveCustomer) {
			hasSaveCustomer = false;
			return true;
		}
		return hasSaveCustomer;
	}

	public void setHasSaveCustomer(boolean hasSaveCustomer) {
		this.hasSaveCustomer = hasSaveCustomer;
	}

	public List<Soum> getInsuranceSums() {
		return insuranceSums;
	}

	public void setInsuranceSums(List<Soum> insuranceSums) {
		this.insuranceSums = insuranceSums;
	}

	public List<ViewConstantCountry> getCountries() {
		if (countries == null)
			try {
				countries = logicCustomer.getViewConstantCountries();
			} catch (Exception ex) {
				userSessionController.showErrorMessage(ex.getMessage());
			}
		return countries;
	}

	public void setCountries(List<ViewConstantCountry> countries) {
		this.countries = countries;
	}

	public void updateInsuranceSums() {
		try {
			setInsuranceSums(infoLogic.getSoums(currentCustomer.getInsuranceAimagPkId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void foreignDetail() {
		RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		for (ViewConstantCountry vcc : getCountries()) {
			if (vcc.getPkId().compareTo(currentCustomer.getCitizenPkId()) == 0 && !vcc.getNameMn().equals("Монгол")) {
				context.execute("showForeignPatient();");
				currentCustomer.setIsLocal(1);
				if (currentCustomer.getBirthDate() != null)
					generateRegNumber();
				break;

			} else {
				context.execute("hideForeignPatient();");
				currentCustomer.setIsLocal(0);
				context.update("form:regNumber");

			}

		}
	}

	public void generateRegNumber() {
		RequestContext context = org.primefaces.context.RequestContext.getCurrentInstance();
		for (ViewConstantCountry vcc : getCountries()) {
			if (vcc.getPkId().compareTo(currentCustomer.getCitizenPkId()) == 0 && !vcc.getNameMn().equals("Монгол")) {
				String rn = "";
				rn = rn + vcc.getId().substring(0, 2);
				rn = rn + Integer.toString(currentCustomer.getBirthDate().getYear() + 1900);
				if ((currentCustomer.getBirthDate().getMonth() + 1) < 10)
					rn = rn + 0;
				rn = rn + Integer.toString(currentCustomer.getBirthDate().getMonth() + 1);
				if ((currentCustomer.getBirthDate().getDate()) < 10)
					rn = rn + 0;
				rn = rn + Integer.toString(currentCustomer.getBirthDate().getDate());
				currentCustomer.setRegNumber(rn);
				context.update("form:regNumber");
				context.update("form:gender");

			}

		}
	}

	public void fillCustomerExaminationRequest(Customer customer) {
		try {
			getErc();
			setBeginDate(customer.getStartDate());
			getEndDate();
			setCurrentCustomer(customer);
			getExaminationTypes();
			examinationTypes = logicCustomer.getExaminationTypesWithRequestCount(customer.getPkId());
			erc = logicCustomer.getExaminationRequestCompleted(getCurrentCustomer().getPkId(), beginDate, endDate,
					getFilterKey1(), getFilterPkId());
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:qd");
			context.update("form:examinationTypes");
			context.update("form:exaRequests");
			context.update("form:beginDate");
			context.update("form:qdFromcl");
			context.update("form:examinationTypesFromcl");
			context.update("form:exaRequestsFromcl");
			context.update("form:beginDateFromcl");
		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void exaTypeChanged() {
		try {
			erc = logicCustomer.getExaminationRequestCompleted(getCurrentCustomer().getPkId(), beginDate, endDate,
					getFilterKey1(), getFilterPkId());
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:exaRequests");
		} catch (Exception ex) {

		}
	}

	public String getDateString(Date date) {
		if (date == null)
			date = new Date();
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public void fillErc(ExaminationRequestCompleted erc) {
		getFilteredErc();
		int i = 1;
		for (ExaminationRequestCompleted er : filteredErc) {
			if (er.getPkId().compareTo(erc.getPkId()) == 0) {
				filteredErc.remove(er);
				i = 0;
				break;
			}
		}
		if (i != 0) {
			filteredErc.add(erc);
		}
	}

	public void checkRegNumber() {
		try {
			if (logicCustomer.checkRegNumber(getCurrentCustomer().getRegNumber())) {
					userSessionController.showErrorMessage("Тухайн регистрийн дугаартай хэрэглэгч бүртгэлтэй байна!!");
			} 

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void printExaminationHdr() {
		try {
			for (ExaminationRequestCompleted erc : getFilteredErc()) {
				ExaRequest er = new ExaRequest(erc);
				List<ExaminationValueQuestion> printableQuestions = new ArrayList<ExaminationValueQuestion>();
				printableQuestions = examLogic.getQuestions(er);
				for (ExaminationValueQuestion evq : printableQuestions) {
					ExaminationResults r = new ExaminationResults();
					r.setEvqName(evq.getName());
					if (evq.getMinValue() == null)
						if (evq.getMaxValue() == null)
							r.setRange("");
						else
							r.setRange(evq.getMaxValue());
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
								|| Tool.RADIO.equals(eva.getAnswerType())
								|| Tool.TEXTAREA.equals(eva.getAnswerType())) {
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
					erc.getExaResults().add(r);
				}
			}

		} catch (Exception ex) {
			userSessionController.showErrorMessage(ex.getMessage());
			System.out.println(ex.getMessage());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("form:dubu");
		context.update("form:dubuQo");
		context.update("dubuQo");

	}

}

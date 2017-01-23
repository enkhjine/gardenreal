package hospital.web.controller;

import hospital.businessentity.TreatmentRequest;
import hospital.businesslogic.interfaces.ILogicTreatmentRequestLocal;
import hospital.entity.CustomerTreatmentDtl;
import hospital.entity.CustomerTreatmentHistory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "trController")
public class TreatmentRequestController {

	@EJB(beanName = "LogicTreatmentRequest")
	ILogicTreatmentRequestLocal logicTr;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;

	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;

	// Lists
	private List<TreatmentRequest> requests;
	private List<CustomerTreatmentHistory> hisotries;
	private List<CustomerTreatmentDtl> dtl;
	private TreatmentRequest currentTreatmentRequest;

	// Filter
	private String filterKey1;
	private String filterKey2;
	private String filterKey3;

	private Date bDate;
	private Date eDate;
	private Date bDate1;
	private Date eDate1;

	public TreatmentRequestController() {

	}

	// methods
	public void refreshRequests() {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			getRequests();
			requests = logicTr.getCustomerTreatments(userSessionController.getLoggedInfo().getEmployeePkId(),
					filterKey1, filterKey2, getbDate(), geteDate());
			context.update("form:treatmentRequests");

		} catch (Exception ex) {
			userSessionController.showErrorMessage("Өгөгдөл татахад алдаа гарлаа");
		}

	}

	public void saveRequest(TreatmentRequest treatmentRequest) {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
			treatmentRequest.changeVas();
			logicTr.saveTreatmentRequest(treatmentRequest.getCustomerTreatments(),
					userSessionController.getLoggedInfo());
			context.execute("changeDate();");
			context.execute("PF('score').hide();");
			userSessionController.showMessage(99);
		} catch (Exception ex) {
			userSessionController.showErrorMessage("Хадгалах үед алдаа гарлаа");
		}
	}

	public void showDialog(TreatmentRequest tr) {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('score').show();");
		setCurrentTreatmentRequest(tr);
		currentTreatmentRequest.changeVas();

	}

	public void getHistories() {
		try {
			hisotries = logicTr.getHistory(userSessionController.getLoggedInfo().getEmployeePkId(), getbDate1(),
					geteDate1(), getFilterKey3());
			RequestContext context = RequestContext.getCurrentInstance();
			context.update("form:historyTable");
			context.execute("PF('trHistory').show();");
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
		}
	}

	public void getDetail(BigDecimal cthPkId) {
		RequestContext context = RequestContext.getCurrentInstance();
		try {
				dtl = logicTr.getDtl(cthPkId);
				context.update("form:ctDtl");
				System.out.println("------>>>"+dtl.size());
//				context.execute("PF('trDtl').show();");
		} catch (Exception ex) {
			ex.printStackTrace();
			userSessionController.showErrorMessage(ex.getMessage());
			
		}

	}

	// getter, setter
	public List<TreatmentRequest> getRequests() {
		if (requests == null)
			requests = new ArrayList<TreatmentRequest>();
		return requests;
	}

	public void setRequests(List<TreatmentRequest> requests) {
		this.requests = requests;
	}

	public String getFilterKey2() {
		if (filterKey2 == null)
			filterKey2 = "";
		return filterKey2;
	}

	public void setFilterKey2(String filterKey2) {
		this.filterKey2 = filterKey2;
	}

	public String getFilterKey1() {
		if (filterKey1 == null)
			filterKey1 = "";
		return filterKey1;
	}

	public void setFilterKey1(String filterKey1) {
		this.filterKey1 = filterKey1;
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

	public TreatmentRequest getCurrentTreatmentRequest() {
		if (currentTreatmentRequest == null)
			currentTreatmentRequest = new TreatmentRequest();
		return currentTreatmentRequest;
	}

	public void setCurrentTreatmentRequest(TreatmentRequest currentTreatmentRequest) {
		this.currentTreatmentRequest = currentTreatmentRequest;
	}

	public List<CustomerTreatmentHistory> getHisotries() {
		if (hisotries == null)
			hisotries = new ArrayList<CustomerTreatmentHistory>();
		return hisotries;
	}

	public void setHisotries(List<CustomerTreatmentHistory> hisotries) {
		this.hisotries = hisotries;
	}

	public Date getbDate() {
		if (bDate == null)
			bDate = new Date();
		bDate.setHours(0);
		bDate.setMinutes(0);
		bDate.setSeconds(1);
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public Date geteDate() {
		if (eDate == null)
			eDate = new Date();
		eDate.setHours(23);
		eDate.setMinutes(59);
		eDate.setSeconds(59);
		return eDate;
	}

	public void seteDate(Date eDate) {
		this.eDate = eDate;
	}

	public Date getbDate1() {
		if (bDate1 == null)
			bDate1 = new Date();
		return bDate1;
	}

	public void setbDate1(Date bDate1) {
		this.bDate1 = bDate1;
	}

	public Date geteDate1() {
		if (eDate1 == null)
			eDate1 = new Date();
		return eDate1;
	}

	public void seteDate1(Date eDate1) {
		this.eDate1 = eDate1;
	}

	public String getFilterKey3() {
		if (filterKey3 == null)
			filterKey3 = "";
		return filterKey3;
	}

	public void setFilterKey3(String filterKey3) {
		this.filterKey3 = filterKey3;
	}

	public List<CustomerTreatmentDtl> getDtl() {
		if (dtl == null)
			dtl = new ArrayList<CustomerTreatmentDtl>();
		return dtl;
	}

	public void setDtl(List<CustomerTreatmentDtl> dtl) {
		this.dtl = dtl;
	}

}

package hospital.businesslogic;

import hospital.businessentity.CustomerEar;
import hospital.businessentity.CustomerExamination;
import hospital.businessentity.CustomerLung;
import hospital.businessentity.CustomerMedicalHistory;
import hospital.businessentity.CustomerNose;
import hospital.businessentity.CustomerProblem;
import hospital.businessentity.CustomerThroat;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.InspectionDetail;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.OrderedTreatment;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInspectionLogicLocal;
import hospital.entity.*;
import hospital.report.Gt15Pain;

import javax.ejb.Stateless;
import javax.print.Doc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicInspection", mappedName = "hospital.businesslogic.LogicInspection")
public class LogicInspection extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.IInspectionLogic, IInspectionLogicLocal {
	@Resource
	SessionContext sessionContext;

	public LogicInspection() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<InspectionDtl> getInspectionDtls() throws Exception {
		return getAll(InspectionDtl.class);
	}

	@Override
	public Inspection saveInspection(Inspection inspection, LoggedUser lu, List<InspectionDtl> inspectionDtls,
			List<CustomerDiagnose> customerDiagnoses, List<CustomerMedicine> customerMedicines, EmployeeRequest er,
			List<CustomerPlan> customerPlan, List<CustomerQuestion> customerQuestion,
			List<CustomerAttachment> customerAttachments, List<CustomerPain> customerPains,
			CustomerPastHistory pastHistory) throws Exception {
		System.out.println("INsppkId" + inspection.getPkId());
		if (Tool.ADDED.equals(inspection.getStatus())) {
			Date date = new Date();
			inspection.setPkId(Tools.newPkId());
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setCustomerPkId(er.getCustomerPkId());
			inspection.setRequestPkId(er.getPkId());
			inspection.setInspectionDate(date);
			inspection.setCreatedDate(date);
			inspection.setCreatedBy(lu.getUser().getPkId());
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);
			update(er);
			insert(inspection);
			if (pastHistory.getPkId() == null) {
				pastHistory.setPkId(Tools.newPkId());
				pastHistory.setCustomerPkId(er.getCustomerPkId());
				insert(pastHistory);
			} else
				update(pastHistory);
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			for (InspectionDtl dtl : inspectionDtls) {
				dtl.setPkId(Tools.newPkId());
				dtl.setInspectionPkId(inspection.getPkId());
				insert(dtl);
			}
			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			for (CustomerDiagnose diagnose : customerDiagnoses) {
				diagnose.setPkId(Tools.newPkId());
				diagnose.setInspectionPkId(inspection.getPkId());
				diagnose.setEmployeePkId(lu.getEmployeePkId());
				diagnose.setDate(date);
				diagnose.setCreatedDate(date);
				diagnose.setCreatedBy(lu.getUser().getPkId());
				diagnose.setUpdatedDate(date);
				diagnose.setUpdatedBy(lu.getUser().getPkId());
				insert(diagnose);
			}
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			for (CustomerMedicine medicine : customerMedicines) {
				medicine.setPkId(Tools.newPkId());
				medicine.setInspectionPkId(inspection.getPkId());
				medicine.setCreatedDate(date);
				medicine.setCreatedBy(lu.getUser().getPkId());
				medicine.setUpdatedDate(date);
				medicine.setUpdatedBy(lu.getUser().getPkId());
				insert(medicine);

			}
			deleteByAnyField(CustomerPlan.class, "inspectionPkId", inspection.getPkId());
			for (CustomerPlan plan : customerPlan) {
				plan.setPkId(Tools.newPkId());
				plan.setEmployeePkId(lu.getEmployeePkId());
				plan.setInspectionPkId(inspection.getPkId());
				plan.setCreatedDate(date);
				plan.setCreatedBy(lu.getUser().getPkId());
				plan.setUpdatedDate(date);
				plan.setUpdatedBy(lu.getUser().getPkId());
				insert(plan);
			}
			deleteByAnyField(CustomerQuestion.class, "inspectionPkId", inspection.getPkId());
			for (CustomerQuestion question : customerQuestion)
				if (Tool.ADDED.equals(question.getStatus())) {
					question.setPkId(Tools.newPkId());
					question.setEmployeePkId(lu.getEmployeePkId());
					question.setInspectionPkId(inspection.getPkId());
					question.setCreatedDate(date);
					question.setCreatedBy(lu.getUser().getPkId());
					question.setUpdatedDate(date);
					question.setUpdatedBy(lu.getUser().getPkId());
					insert(question);
				}
			deleteByAnyField(CustomerAttachment.class, "inspectionPkId", inspection.getPkId());

			for (CustomerAttachment attachment : customerAttachments)

				if (Tool.ADDED.equals(attachment.getStatus())) {
					System.out.println("Inspection" + inspection.getPkId());
					attachment.setEmployeePkId(lu.getEmployeePkId());
					attachment.setInspectionPkId(inspection.getPkId());
					attachment.setCustomerPkId(er.getCustomerPkId());
					attachment.setCreatedDate(date);
					attachment.setCreatedBy(lu.getUser().getPkId());
					attachment.setUpdatedDate(date);
					attachment.setUpdatedBy(lu.getUser().getPkId());
					insert(attachment);
				}
			deleteByAnyField(CustomerPain.class, "inspectionPkId", inspection.getPkId());
			for (CustomerPain pain : customerPains)
				if (Tool.ADDED.equals(pain.getStatus())) {
					pain.setPkId(Tools.newPkId());
					pain.setEmployeePkId(lu.getEmployeePkId());
					pain.setInspectionPkId(inspection.getPkId());
					pain.setCreatedDate(date);
					pain.setCreatedBy(lu.getUser().getPkId());
					pain.setUpdatedDate(date);
					pain.setUpdatedBy(lu.getUser().getPkId());
					insert(pain);
				}

		} else if (Tool.MODIFIED.equals(inspection.getStatus())) {
			setEntityManager(null);
			Date date = new Date();
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setInspectionDate(date);
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);
			update(inspection);
			update(er);
			if (pastHistory.getPkId() == null) {
				pastHistory.setPkId(Tools.newPkId());
				pastHistory.setCustomerPkId(er.getCustomerPkId());
				insert(pastHistory);
			} else
				update(pastHistory);
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			for (InspectionDtl dtl : inspectionDtls) {
				dtl.setPkId(Tools.newPkId());
				dtl.setInspectionPkId(inspection.getPkId());
				insert(dtl);

			}

			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			for (CustomerDiagnose diagnose : customerDiagnoses) {
				diagnose.setPkId(Tools.newPkId());
				diagnose.setInspectionPkId(inspection.getPkId());
				diagnose.setDate(date);
				diagnose.setCreatedDate(date);
				diagnose.setCreatedBy(lu.getUser().getPkId());
				diagnose.setUpdatedDate(date);
				diagnose.setUpdatedBy(lu.getUser().getPkId());
				insert(diagnose);

			}
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			for (CustomerMedicine medicine : customerMedicines) {
				medicine.setPkId(Tools.newPkId());
				medicine.setInspectionPkId(inspection.getPkId());
				medicine.setCreatedDate(date);
				medicine.setCreatedBy(lu.getUser().getPkId());
				medicine.setUpdatedDate(date);
				medicine.setUpdatedBy(lu.getUser().getPkId());
				insert(medicine);

			}
			deleteByAnyField(CustomerAttachment.class, "inspectionPkId", inspection.getPkId());

			for (CustomerAttachment attachment : customerAttachments)

				if (Tool.ADDED.equals(attachment.getStatus())) {
					System.out.println("Inspection" + inspection.getPkId());
					attachment.setEmployeePkId(lu.getEmployeePkId());
					attachment.setInspectionPkId(inspection.getPkId());
					attachment.setCustomerPkId(er.getCustomerPkId());
					attachment.setCreatedDate(date);
					attachment.setCreatedBy(lu.getUser().getPkId());
					attachment.setUpdatedDate(date);
					attachment.setUpdatedBy(lu.getUser().getPkId());
					insert(attachment);
				}
		} else if (Tool.DELETE.equals(inspection.getStatus())) {
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			deleteByPkId(Inspection.class, inspection.getPkId());
		}
		return inspection;
	}

	@Override
	public Inspection saveReInspection(Inspection inspection, LoggedUser lu, EmployeeRequest er,
			List<CustomerDiagnose> customerDiagnoses, List<CustomerPlan> customerPlan,
			List<CustomerQuestion> customerQuestion, List<CustomerAttachment> customerAttachments,
			List<CustomerPain> customerPains, CustomerInspection customerInspection, CustomerPastHistory pastHistory)
			throws Exception {

		if (Tool.ADDED.equals(inspection.getStatus())) {
			Date date = new Date();
			inspection.setPkId(Tools.newPkId());
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setCustomerPkId(er.getCustomerPkId());
			inspection.setRequestPkId(er.getPkId());
			inspection.setInspectionDate(date);
			inspection.setCreatedDate(date);
			inspection.setCreatedBy(lu.getUser().getPkId());
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);
			update(er);
			insert(inspection);
			if (pastHistory.getPkId() == null) {
				pastHistory.setPkId(Tools.newPkId());
				pastHistory.setCustomerPkId(er.getCustomerPkId());
				insert(pastHistory);
			} else
				update(pastHistory);
			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			for (CustomerDiagnose diagnose : customerDiagnoses) {
				diagnose.setPkId(Tools.newPkId());
				diagnose.setInspectionPkId(inspection.getPkId());
				diagnose.setEmployeePkId(lu.getEmployeePkId());
				diagnose.setDate(date);
				diagnose.setCreatedDate(date);
				diagnose.setCreatedBy(lu.getUser().getPkId());
				diagnose.setUpdatedDate(date);
				diagnose.setUpdatedBy(lu.getUser().getPkId());
				insert(diagnose);
			}
			deleteByAnyField(CustomerPlan.class, "inspectionPkId", inspection.getPkId());
			for (CustomerPlan plan : customerPlan) {
				plan.setPkId(Tools.newPkId());
				plan.setEmployeePkId(lu.getEmployeePkId());
				plan.setInspectionPkId(inspection.getPkId());
				plan.setCreatedDate(date);
				plan.setCreatedBy(lu.getUser().getPkId());
				plan.setUpdatedDate(date);
				plan.setUpdatedBy(lu.getUser().getPkId());
				insert(plan);
			}
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			for (CustomerQuestion question : customerQuestion) {
				question.setPkId(Tools.newPkId());
				question.setEmployeePkId(lu.getEmployeePkId());
				question.setInspectionPkId(inspection.getPkId());
				question.setCreatedDate(date);
				question.setCreatedBy(lu.getUser().getPkId());
				question.setUpdatedDate(date);
				question.setUpdatedBy(lu.getUser().getPkId());
				insert(question);
			}
			deleteByAnyField(CustomerAttachment.class, "inspectionPkId", inspection.getPkId());
			for (CustomerAttachment attachment : customerAttachments) {
				attachment.setPkId(Tools.newPkId());
				attachment.setEmployeePkId(lu.getEmployeePkId());
				attachment.setInspectionPkId(inspection.getPkId());
				attachment.setCustomerPkId(er.getCustomerPkId());
				attachment.setCreatedDate(date);
				attachment.setCreatedBy(lu.getUser().getPkId());
				attachment.setUpdatedDate(date);
				attachment.setUpdatedBy(lu.getUser().getPkId());
				insert(attachment);
			}
			deleteByAnyField(CustomerPain.class, "inspectionPkId", inspection.getPkId());
			for (CustomerPain pain : customerPains)
				if (Tool.ADDED.equals(pain.getStatus())) {
					pain.setPkId(Tools.newPkId());
					pain.setEmployeePkId(lu.getEmployeePkId());
					pain.setInspectionPkId(inspection.getPkId());
					pain.setCreatedDate(date);
					pain.setCreatedBy(lu.getUser().getPkId());
					pain.setUpdatedDate(date);
					pain.setUpdatedBy(lu.getUser().getPkId());
					insert(pain);
				}
			if (Tool.ADDED.equals(customerInspection.getStatus())) {
				customerInspection.setPkId(Tools.newPkId());
				customerInspection.setEmployeePkId(lu.getEmployeePkId());
				customerInspection.setInspectionPkId(inspection.getPkId());
				customerInspection.setCreatedDate(date);
				customerInspection.setCreatedBy(lu.getUser().getPkId());
				customerInspection.setUpdatedDate(date);
				customerInspection.setUpdatedBy(lu.getUser().getPkId());
				insert(customerInspection);
			}
		} else if (Tool.MODIFIED.equals(inspection.getStatus())) {
			Date date = new Date();
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setInspectionDate(date);
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);
			update(inspection);
			update(er);
			if (pastHistory.getPkId() == null) {
				pastHistory.setPkId(Tools.newPkId());
				pastHistory.setCustomerPkId(er.getCustomerPkId());
				insert(pastHistory);
			} else
				update(pastHistory);
			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			for (CustomerDiagnose diagnose : customerDiagnoses) {
				{
					diagnose.setPkId(Tools.newPkId());
					diagnose.setInspectionPkId(inspection.getPkId());
					diagnose.setDate(date);
					diagnose.setCreatedDate(date);
					diagnose.setCreatedBy(lu.getUser().getPkId());
					diagnose.setUpdatedDate(date);
					diagnose.setUpdatedBy(lu.getUser().getPkId());
					insert(diagnose);
				}
			}
		} else if (Tool.DELETE.equals(inspection.getStatus())) {
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerDiagnose.class, "inspectionPkId", inspection.getPkId());
			deleteByPkId(Inspection.class, inspection.getPkId());
		}
		return inspection;
	}

	@Override
	public void saveInspectionDtl(InspectionDtl inspectionDtl) throws Exception {
		if (Tool.ADDED.equals(inspectionDtl.getStatus())) {
			inspectionDtl.setPkId(Tools.newPkId());
			insert(inspectionDtl);
		} else if (Tool.MODIFIED.equals(inspectionDtl.getStatus())) {
			update(inspectionDtl);
		} else if (Tool.DELETE.equals(inspectionDtl.getStatus())) {
			deleteByPkId(InspectionDtl.class, inspectionDtl.getPkId());
		}
	}

	@Override
	public List<MedicalHistory> getMedicalHistory(BigDecimal customerPkId) throws Exception {
		List<String> types = Tool.getMedicalHistorys();
		List<MedicalHistory> histories = new ArrayList<MedicalHistory>();
		List<MedicalHistory> inserthistorys = new ArrayList<MedicalHistory>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		BigDecimal pkId = Tools.newPkId();
		for (String type : types) {
			parameters.put("type", type);
			parameters.put("customerPkId", customerPkId);
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM MedicalHistory a WHERE a.customerPkId = :customerPkId AND a.type = :type");
			List<MedicalHistory> histories2 = getByQuery(MedicalHistory.class, jpql.toString(), parameters);
			if (histories2.size() > 0) {
				histories.add(histories2.get(0));
			} else {
				MedicalHistory history = new MedicalHistory();
				pkId = pkId.add(BigDecimal.ONE);
				history.setPkId(pkId);
				history.setCustomerPkId(customerPkId);
				history.setType(type);
				history.setValue(Tool.getMedicalHistoryDefaultValues().get(types.indexOf(type)));
				history.setName(Tool.getMedicalHistoryNames().get(types.indexOf(type)));
				histories.add(history);
				inserthistorys.add(history);
			}
		}
		if (inserthistorys.size() > 0)
			insert(inserthistorys);
		return histories;
	}

	@Override
	public void saveMedicalHistory(CustomerMedicalHistory cmh) throws Exception {
		for (MedicalHistory mh : cmh.getHistorys()) {
			update(mh);
		}
	}

	@Override
	public List<XrayRequest> getXrayHistory(BigDecimal customerPkId) throws Exception {
		List<XrayRequest> list = new ArrayList<XrayRequest>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("customerPkId", customerPkId);
		jpql.append(
				"SELECT NEW hospital.entity.XrayRequest (a.pkId, a.employeePkId, a.customerPkId, a.xrayPkId, a.image,a.description, a.requestDate, b.firstName, b.lastName, b.cardNumber, b.regNumber, b.gender, b.age, c.name, d.firstName, a.requestPkId ) ");
		jpql.append("FROM XrayRequest a ");
		jpql.append("LEFT JOIN ViewCustomer b ON a.customerPkId=b.pkId ");
		jpql.append("LEFT JOIN Xray c ON a.xrayPkId=c.pkId ");
		jpql.append("LEFT JOIN Employee d ON a.employeePkId=d.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId");
		list = getByQuery(XrayRequest.class, jpql.toString(), parameters);
		return list;

	}

	@Override
	public List<XrayRequest> getXrayHistory1(Date beginDate, Date endDate, String searchKey, boolean isDone,
			String searchKey1, BigDecimal employeePkId) throws Exception {
		List<XrayRequest> list = new ArrayList<XrayRequest>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("employeePkId", employeePkId);
		if (searchKey != null)
			parameters.put("searchKey", "%" + searchKey + "%");
		parameters.put("searchKey1", "%" + searchKey1 + "%");
		jpql.append(
				"SELECT NEW hospital.entity.XrayRequest (a.pkId, a.employeePkId, a.customerPkId, a.xrayPkId, a.image,a.description, a.requestDate, b.firstName, b.lastName, b.cardNumber, b.regNumber, b.gender, b.age, c.name, d.firstName, a.requestPkId, a.note ) ");
		jpql.append("FROM XrayRequest a ");
		jpql.append("LEFT JOIN ViewCustomer b ON a.customerPkId=b.pkId ");
		jpql.append("LEFT JOIN Xray c ON a.xrayPkId=c.pkId ");
		jpql.append("LEFT JOIN Employee d ON a.employeePkId=d.pkId ");
		jpql.append("LEFT JOIN XrayEmployeeMap e ON c.pkId = e.xrayPkId ");
		jpql.append("WHERE a.requestDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND c.pkId IS NOT NULL ");
		jpql.append("AND b.pkId IS NOT NULL ");
		jpql.append("AND d.firstName LIKE :searchKey1 ");
		jpql.append("AND e.employeePkId = :employeePkId ");
		if (isDone)
			jpql.append("AND a.image IS NULL ");
		jpql.append(
				"AND ( b.firstName LIKE :searchKey OR b.lastName LIKE :searchKey OR b.cardNumber LIKE :searchKey OR b.regNumber LIKE :searchKey  ) ");
		list = getByQuery(XrayRequest.class, jpql.toString(), parameters);
		for (XrayRequest xr : list) {
			if (xr.getRequestPkId() != null) {
				parameters.put("requestPkId", xr.getRequestPkId());
				StringBuilder jpql1 = new StringBuilder();
				jpql1.append("SELECT a ");
				jpql1.append("FROM Diagnose a ");
				jpql1.append("INNER JOIN CustomerDiagnose b ON a.pkId = b.diagnosePkId ");
				jpql1.append("INNER JOIN Inspection c  ON c.pkId = b.inspectionPkId ");
				jpql1.append("WHERE c.requestPkId = :requestPkId ");
				xr.setDiagnoseList(getByQuery(Diagnose.class, jpql1.toString(), parameters));

			}
		}
		return list;
	}

	@Override
	public void saveXrayRequests(List<XrayRequest> xrayRequests) throws Exception {
		update(xrayRequests);

	}

	@Override
	public void saveXrayRequest(XrayRequest xrayRequest) throws Exception {
		update(xrayRequest);
	}

	@Override
	public List<SubOrganization> listSubOrganizations(BigDecimal customerPkId, Date beginDate, Date endDate,
			boolean inspection, LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		List<Employee> employees = getByAnyField(Employee.class, "userPkId", loggedUser.getUser().getPkId());
		List<BigDecimal> pkIds = new ArrayList<BigDecimal>();
		for (Employee employee : employees) {
			pkIds.add(employee.getPkId());
		}
		parameters.put("pkIds", pkIds);
		jpql.append(
				"SELECT NEW hospital.entity.SubOrganization(a.pkId, a.name, COUNT(c.pkId)) FROM SubOrganization a ");
		jpql.append("INNER JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("INNER JOIN EmployeeRequest c ON b.pkId = c.employeePkId ");
		jpql.append("WHERE c.customerPkId = :customerPkId AND c.mood IN (2, 4) ");
		jpql.append("AND c.beginDate BETWEEN :beginDate AND :endDate ");
		if (inspection && pkIds.size() > 0)
			jpql.append("AND c.employeePkId IN :pkIds ");
		jpql.append("GROUP BY a.name, a.pkId ");
		List<SubOrganization> list = getByQuery(SubOrganization.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<InspectionDtl> getInspectionDtl(LoggedUser lu, BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append(
				"SELECT NEW hospital.entity.InspectionDtl(A, B.name, C.name, D.name, E.name, B.id, C.id, D.id, E.name) FROM InspectionDtl A ");
		jpql.append("LEFT JOIN Treatment B ON A.typePkId = B.pkId ");
		jpql.append("LEFT JOIN Xray C ON A.typePkId = C.pkId ");
		jpql.append("LEFT JOIN Examination D ON A.typePkId = D.pkId ");
		jpql.append("LEFT JOIN Surgery E ON A.typePkId = E.pkId ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public List<InspectionDtl> getInspectionDtl(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append(
				"SELECT NEW hospital.entity.InspectionDtl(A, B.name, C.name, D.name, E.name) FROM InspectionDtl A ");
		jpql.append("LEFT JOIN Treatment B ON A.typePkId = B.pkId ");
		jpql.append("LEFT JOIN Xray C ON A.typePkId = C.pkId ");
		jpql.append("LEFT JOIN Examination D ON A.typePkId = D.pkId ");
		jpql.append("LEFT JOIN Surgery E ON A.typePkId = E.pkId ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public void saveEmployeeRequest(EmployeeRequest employeeRequest, LoggedUser loggedUser) throws Exception {
		Date dte = new Date();
		if (Tool.ADDED.equals(employeeRequest.getStatus())) {
			employeeRequest.setPkId(Tools.newPkId());
			employeeRequest.setUpdatedBy(loggedUser.getUser().getPkId());
			employeeRequest.setUpdatedDate(dte);
			employeeRequest.setCreatedBy(loggedUser.getUser().getPkId());
			employeeRequest.setCreatedDate(dte);
			employeeRequest.setOrganizationPkId(loggedUser.getOrganization().getPkId());
			employeeRequest.setMood(3);
			employeeRequest.setDescription("Дахин үзлэг");

			insert(employeeRequest);
		} else if (Tool.DELETE.equals(employeeRequest.getStatus())) {
			deleteByPkId(EmployeeRequest.class, employeeRequest.getPkId());
		}
	}

	@Override
	public List<SurveyQuestion> getSurveyQuestions(String groupId) throws Exception {
		List<SurveyQuestion> list = new ArrayList<SurveyQuestion>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("groupId", groupId);
		jpql.append("SELECT a ");
		jpql.append("FROM SurveyQuestion a ");
		jpql.append("WHERE a.groupId = :groupId ");
		list = getByQuery(SurveyQuestion.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveSurveyDtls(List<SurveyDtl> dtls, EmployeeRequest er, LoggedUser lu) throws Exception {
		Date date = new Date();
		SurveyHdr sh = new SurveyHdr();
		sh.setPkId(Tools.newPkId());
		sh.setCustomerPkId(er.getCustomerPkId());
		sh.setEmployeePkId(er.getEmployeePkId());
		sh.setRequestPkId(er.getPkId());
		sh.setUpdatedBy(lu.getUser().getPkId());
		sh.setUpdatedDate(date);
		sh.setCreatedBy(lu.getUser().getPkId());
		sh.setCreatedDate(date);
		int i = 0;
		for (SurveyDtl sd : dtls) {
			sd.setPkId(Tools.newPkId());
			sd.setHdrPkId(sh.getPkId());
			i = i + sd.getPoint();
		}
		sh.setTotalPoint(i);
		insert(sh);
		insert(dtls);
	}

	@Override
	public List<SurveyDtl> getSurveyDtl(BigDecimal requestPkId) throws Exception {
		List<SurveyDtl> list = new ArrayList<SurveyDtl>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("requestPkId", requestPkId);
		jpql.append("SELECT a ");
		jpql.append("FROM SurveyDtl a ");
		jpql.append("INNER JOIN SurveyHdr b ON a.hdrPkId = b.pkId ");
		jpql.append("WHERE b.requestPkId = :requestPkId ");
		list = getByQuery(SurveyDtl.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<SurveyHdr> getSurveyHdr(BigDecimal customerPkId) throws Exception {
		List<SurveyHdr> list = new ArrayList<SurveyHdr>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT a ");
		jpql.append("FROM SurveyHdr a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		list = getByQuery(SurveyHdr.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<CustomerMedicine> getCustomerMedicine(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerMedicine(A,B) FROM CustomerMedicine A ");
		jpql.append("INNER JOIN Medicine B ON A.medicinePkId = B.pkId ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		return getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerDiagnose> getCustomerDiagnose(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerDiagnose(A,B) FROM CustomerDiagnose A ");
		jpql.append("INNER JOIN Diagnose B ON A.diagnosePkId = B.pkId ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		return getByQuery(CustomerDiagnose.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerDiagnose> getCustomerDiagnose(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerDiagnose(A,B) FROM CustomerDiagnose A ");
		jpql.append("INNER JOIN Diagnose B ON A.diagnosePkId = B.pkId ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(CustomerDiagnose.class, jpql.toString(), parameters);
	}

	@Override
	public List<Inspection> getInspectionByCustomer(BigDecimal customerPkId, List<BigDecimal> employeePkId,
			List<BigDecimal> subOrgaPkId, BigDecimal currentEmployeePkId, boolean isMyInspection) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.Inspection(A, C.firstName, D.name, D.pkId) FROM Inspection A ");
		jpql.append("INNER JOIN Customer B ON A.customerPkId = B.pkId ");
		jpql.append("INNER JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql.append("INNER JOIN SubOrganization D ON C.subOrganizationPkId = D.pkId ");
		jpql.append("WHERE A.customerPkId = :customerPkId ");

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append("AND A.employeePkId IN :employeePkId ");
		}

		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append("AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		if (isMyInspection) {
			parameters.put("currentEmployeePkId", currentEmployeePkId);
			jpql.append("AND C.pkId = :currentEmployeePkId ");
		}

		jpql.append("ORDER BY A.inspectionDate DESC ");

		return getByQuery(Inspection.class, jpql.toString(), parameters);
	}

	@Override
	public List<InspectionDtl> getInspectionDtlByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId,
			List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append(
				"SELECT NEW hospital.entity.InspectionDtl(A, B.name, C.name, D.name, G.name) FROM InspectionDtl A ");
		jpql.append("LEFT JOIN Treatment B ON A.typePkId = B.pkId ");
		jpql.append("LEFT JOIN Xray C ON A.typePkId = C.pkId ");
		jpql.append("LEFT JOIN Examination D ON A.typePkId = D.pkId ");
		jpql.append("LEFT JOIN Inspection E ON A.inspectionPkId = E.pkId ");
		jpql.append("LEFT JOIN Employee F ON E.employeePkId = F.pkId ");
		jpql.append("LEFT JOIN Surgery G ON A.typePkId = G.pkId ");
		jpql.append("WHERE E.customerPkId = :customerPkId ");

		if (inspectionPkId != null && inspectionPkId.size() > 0) {
			parameters.put("inspectionPkId", inspectionPkId);
			jpql.append(" AND A.inspectionPkId IN :inspectionPkId ");
		}

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND F.pkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND F.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerMedicine> getCustomerMedicineByFilter(List<BigDecimal> subOrgaPkId,
			List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerMedicine(A,B,D.name) FROM CustomerMedicine A ");
		jpql.append("LEFT JOIN Medicine B ON A.medicinePkId = B.pkId ");
		jpql.append("LEFT JOIN Measurement D ON B.measurementPkId = D.pkId ");
		jpql.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql.append("LEFT JOIN Inspection E ON A.inspectionPkId = E.pkId ");
		jpql.append("WHERE E.customerPkId = :customerPkId ");

		if (inspectionPkId != null && inspectionPkId.size() > 0) {
			parameters.put("inspectionPkId", inspectionPkId);
			jpql.append(" AND A.inspectionPkId IN :inspectionPkId ");
		}

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND A.employeePkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerSurgery> getCustomerSurgeryByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId,
			List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerSurgery(A,B.name,C.firstName) FROM CustomerSurgery A ");
		jpql.append("LEFT JOIN Surgery B ON A.surgeryPkId = B.pkId ");
		jpql.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql.append("LEFT JOIN Inspection E ON A.inspectionPkId = E.pkId ");
		jpql.append("WHERE E.customerPkId = :customerPkId ");

		if (inspectionPkId != null && inspectionPkId.size() > 0) {
			parameters.put("inspectionPkId", inspectionPkId);
			jpql.append(" AND A.inspectionPkId IN :inspectionPkId ");
		}

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND A.employeePkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(CustomerSurgery.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerXray> getCustomerXrayByFilter(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId,
			List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerXray(A,B,C.firstName,D.firstName) FROM CustomerXray A ");
		jpql.append("LEFT JOIN Xray B ON A.xrayPkId = B.pkId ");
		jpql.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql.append("LEFT JOIN Employee D ON A.senderEmployeePkId = D.pkId ");
		jpql.append("LEFT JOIN Inspection E ON A.inspectionPkId = E.pkId ");
		jpql.append("WHERE E.customerPkId = :customerPkId ");

		if (inspectionPkId != null && inspectionPkId.size() > 0) {
			parameters.put("inspectionPkId", inspectionPkId);
			jpql.append(" AND A.inspectionPkId IN :inspectionPkId ");
		}

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND A.employeePkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(CustomerXray.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerTreatment> getCustomerTreatmentByFilter(List<BigDecimal> subOrgaPkId,
			List<BigDecimal> employeePkId, List<BigDecimal> inspectionPkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerTreatment(A,B, D.firstName) FROM CustomerTreatment A ");
		jpql.append("LEFT JOIN Treatment B ON A.treatmentPkId = B.pkId ");
		jpql.append("LEFT JOIN Inspection C ON A.inspectionPkId = C.pkId ");
		jpql.append("LEFT JOIN Employee D ON C.employeePkId = D.pkId ");
		jpql.append("WHERE C.customerPkId = :customerPkId ");

		if (inspectionPkId != null && inspectionPkId.size() > 0) {
			parameters.put("inspectionPkId", inspectionPkId);
			jpql.append(" AND A.inspectionPkId IN :inspectionPkId ");
		}

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND C.employeePkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND D.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(CustomerTreatment.class, jpql.toString(), parameters);
	}

	@Override
	public List<ExaminationRequestCompleted> getExaminationRequestCompletedByFilter(List<BigDecimal> subOrgaPkId,
			List<BigDecimal> employeePkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpql1 = new StringBuilder();
		StringBuilder jpql2 = new StringBuilder();
		StringBuilder jpql3 = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(A, B.name, C.firstName) FROM ExaminationRequestCompleted A ");
		jpql.append("LEFT JOIN Examination B ON A.examinationPkId = B.pkId ");
		jpql.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql.append("WHERE A.customerPkId = :customerPkId ");
		
		jpql1.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(A, B.name, C.firstName) FROM ExaminationRequest A ");
		jpql1.append("LEFT JOIN Examination B ON A.examinationPkId = B.pkId ");
		jpql1.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql1.append("WHERE A.customerPkId = :customerPkId ");
		
		jpql2.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(A, B.name, C.firstName) FROM ExaminationRequestActive A ");
		jpql2.append("LEFT JOIN Examination B ON A.examinationPkId = B.pkId ");
		jpql2.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql2.append("WHERE A.customerPkId = :customerPkId ");
		
		jpql3.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(A, B.name, C.firstName) FROM ExaminationRequestTempSave A ");
		jpql3.append("LEFT JOIN Examination B ON A.examinationPkId = B.pkId ");
		jpql3.append("LEFT JOIN Employee C ON A.employeePkId = C.pkId ");
		jpql3.append("WHERE A.customerPkId = :customerPkId ");

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND C.pkId IN :employeePkId ");
			jpql1.append(" AND C.pkId IN :employeePkId ");
			jpql2.append(" AND C.pkId IN :employeePkId ");
			jpql3.append(" AND C.pkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql1.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql2.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql3.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}
		
		List<ExaminationRequestCompleted> completeds = getByQuery(ExaminationRequestCompleted.class, jpql.toString(), parameters);
		completeds.addAll(getByQuery(ExaminationRequestCompleted.class, jpql1.toString(), parameters));
		completeds.addAll(getByQuery(ExaminationRequestCompleted.class, jpql2.toString(), parameters));
		completeds.addAll(getByQuery(ExaminationRequestCompleted.class, jpql3.toString(), parameters));
		return completeds;
	}

	@Override
	public List<ExaminationValueHdr> getExaminationValueHdrByExa(BigDecimal examinationPkId,
			List<BigDecimal> requestPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);

		jpql.append(
				"SELECT NEW hospital.entity.ExaminationValueHdr(A, B.name, C.firstName) FROM ExaminationValueHdr A ");
		jpql.append("INNER JOIN ExaminationRequestCompleted B ON A.RequestPkId = B.PkId ");
		jpql.append("LEFT JOIN ExaminationValueQuestion C ON A.QuestionPkId = C.PkId ");
		jpql.append("WHERE B.ExaminationPkId = :examinationPkId ");

		if (requestPkId != null && requestPkId.size() > 0) {
			parameters.put("requestPkId", requestPkId);
			jpql.append(" AND A.requestPkId IN :requestPkId ");
		}

		return getByQuery(ExaminationValueHdr.class, jpql.toString(), parameters);
	}

	@Override
	public InspectionForm getCustomerLung(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("inspectionType", "lung");

		jpql.append("SELECT A FROM InspectionForm A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");
		jpql.append("AND A.inspectionType = :inspectionType  ");

		List<InspectionForm> list = getByQuery(InspectionForm.class, jpql.toString(), parameters);
		if (list.size() > 0)
			return list.get(0);
		else
			return new InspectionForm();
	}

	@Override
	public List<DoctorRecipe> getDoctorRecipe(BigDecimal employeePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);

		jpql.append("SELECT A FROM DoctorRecipe A ");
		jpql.append("WHERE A.employeePkId = :employeePkId ");

		List<DoctorRecipe> doctorRecipes = getByQuery(DoctorRecipe.class, jpql.toString(), parameters);

		for (DoctorRecipe doctorRecipe : doctorRecipes) {
			jpql = new StringBuilder();
			parameters.put("doctorRecipePkId", doctorRecipe.getPkId());

			jpql.append("SELECT NEW hospital.entity.DoctorRecipeDtl(a, b.name, e.name, d.name, c.name, f.name) ");
			jpql.append("FROM DoctorRecipeDtl a ");
			jpql.append("LEFT JOIN Treatment b ON a.typePkId = b.pkId ");
			jpql.append("LEFT JOIN Surgery c ON a.typePkId = c.pkId ");
			jpql.append("LEFT JOIN Examination d ON a.typePkId = d.pkId ");
			jpql.append("LEFT JOIN Xray e ON a.typePkId = e.pkId ");
			jpql.append("LEFT JOIN Medicine f ON a.typePkId = f.pkId ");
			jpql.append("WHERE a.doctorRecipePkId = :doctorRecipePkId ");

			doctorRecipe.setDtls(getByQuery(DoctorRecipeDtl.class, jpql.toString(), parameters));
		}

		return doctorRecipes;
	}

	@Override
	public List<DoctorRecipeDtl> getDoctorRecipeDtl(BigDecimal doctorRecipePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("doctorRecipePkId", doctorRecipePkId);

		jpql.append(
				"SELECT NEW hospital.entity.DoctorRecipeDtl(A,B.name,C.name,D.name,E.name,F.name) FROM DoctorRecipeDtl A ");
		jpql.append("LEFT JOIN Treatment B ON A.typePkId = B.pkId ");
		jpql.append("LEFT JOIN Xray C ON A.typePkId = C.pkId ");
		jpql.append("LEFT JOIN Examination D ON A.typePkId = D.pkId ");
		jpql.append("LEFT JOIN Surgery E ON A.typePkId = E.pkId ");
		jpql.append("LEFT JOIN Medicine F ON A.typePkId = F.pkId ");
		jpql.append("WHERE A.doctorRecipePkId = :doctorRecipePkId ");

		return getByQuery(DoctorRecipeDtl.class, jpql.toString(), parameters);
	}

	@Override
	public void saveDoctorRecipe(DoctorRecipe recipe, List<DoctorRecipeDtl> recipeDtl, LoggedUser lu) throws Exception {
		Date date = new Date();
		if (Tool.ADDED.equals(recipe.getStatus())) {
			recipe.setPkId(Tools.newPkId());
			recipe.setCreatedBy(lu.getUser().getPkId());
			recipe.setCreatedDate(date);
			recipe.setUpdatedBy(lu.getUser().getPkId());
			recipe.setUpdatedDate(date);
			insert(recipe);
			if (recipeDtl.size() > 0)
				for (DoctorRecipeDtl dtl : recipeDtl) {
					dtl.setDoctorRecipePkId(recipe.getPkId());
					dtl.setPkId(Tools.newPkId());
					insert(dtl);
				}
		} else if (Tool.MODIFIED.equals(recipe.getStatus())) {
			recipe.setUpdatedBy(lu.getUser().getPkId());
			recipe.setUpdatedDate(date);
			update(recipe);
		} else if (Tool.DELETE.equals(recipe.getStatus())) {
			deleteByPkId(DoctorRecipe.class, recipe.getPkId());
			deleteByAnyField(DoctorRecipeDtl.class, "doctorRecipePkId", recipe.getPkId());
		}
	}

	@Override
	public void removeDiagnose(BigDecimal pkId) throws Exception {
		deleteByPkId(CustomerDiagnose.class, pkId);
	}

	@Override
	public List<ExaminationValueHdr> getExaminationResults(List<BigDecimal> subOrgaPkId, List<BigDecimal> employeePkId,
			BigDecimal customerPkId, BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpql1 = new StringBuilder();
		StringBuilder jpql2 = new StringBuilder();
		StringBuilder jpql3 = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("examinationPkId", examinationPkId);

		jpql.append("SELECT A FROM ExaminationValueHdr A ");
		jpql.append("LEFT JOIN ExaminationValueQuestion B ON A.questionPkId = B.pkId ");
		jpql.append("LEFT JOIN ExaminationRequestCompleted C ON A.requestPkId = C.pkId ");
		jpql.append("WHERE C.customerPkId = :customerPkId AND C.examinationPkId = :examinationPkId");
		
		jpql1.append("SELECT A FROM ExaminationValueHdr A ");
		jpql1.append("LEFT JOIN ExaminationValueQuestion B ON A.questionPkId = B.pkId ");
		jpql1.append("LEFT JOIN ExaminationRequest C ON A.requestPkId = C.pkId ");
		jpql1.append("WHERE C.customerPkId = :customerPkId AND C.examinationPkId = :examinationPkId");
		
		jpql2.append("SELECT A FROM ExaminationValueHdr A ");
		jpql2.append("LEFT JOIN ExaminationValueQuestion B ON A.questionPkId = B.pkId ");
		jpql2.append("LEFT JOIN ExaminationRequestActive C ON A.requestPkId = C.pkId ");
		jpql2.append("WHERE C.customerPkId = :customerPkId AND C.examinationPkId = :examinationPkId");
		
		jpql3.append("SELECT A FROM ExaminationValueHdr A ");
		jpql3.append("LEFT JOIN ExaminationValueQuestion B ON A.questionPkId = B.pkId ");
		jpql3.append("LEFT JOIN ExaminationRequestTempSave C ON A.requestPkId = C.pkId ");
		jpql3.append("WHERE C.customerPkId = :customerPkId AND C.examinationPkId = :examinationPkId");

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND C.pkId IN :employeePkId ");
			jpql1.append(" AND C.pkId IN :employeePkId ");
			jpql2.append(" AND C.pkId IN :employeePkId ");
			jpql3.append(" AND C.pkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql1.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql2.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
			jpql3.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		List<ExaminationValueHdr> examinationValueHdrs = getByQuery(ExaminationValueHdr.class, jpql.toString(), parameters);
		examinationValueHdrs.addAll(getByQuery(ExaminationValueHdr.class, jpql1.toString(), parameters));
		examinationValueHdrs.addAll(getByQuery(ExaminationValueHdr.class, jpql2.toString(), parameters));
		examinationValueHdrs.addAll(getByQuery(ExaminationValueHdr.class, jpql3.toString(), parameters));
		return examinationValueHdrs;
	}

	@Override
	public List<ExaminationResults> getExaminationValueQuestions(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);

		jpql.append("SELECT NEW hospital.businessentity.ExaminationResults(B) FROM ExaminationValueQuestion B ");
		jpql.append("LEFT JOIN Examination A  ON A.examinationTemplatePkId = B.examinationTemplatePkId ");

		jpql.append("WHERE A.pkId = :examinationPkId");

		return getByQuery(ExaminationResults.class, jpql.toString(), parameters);
	}

	@Override
	public List<ExaminationRequestCompleted> getExaminationResultHeader(List<BigDecimal> subOrgaPkId,
			List<BigDecimal> employeePkId, BigDecimal customerPkId, BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("examinationPkId", examinationPkId);

		jpql.append("SELECT A FROM ExaminationRequestCompleted A ");
		jpql.append("WHERE A.customerPkId = :customerPkId AND A.examinationPkId = :examinationPkId ");
		jpql.append("ORDER BY A.requestDate ");

		if (employeePkId != null && employeePkId.size() > 0) {
			parameters.put("employeePkId", employeePkId);
			jpql.append(" AND C.pkId IN :employeePkId ");
		}
		if (subOrgaPkId != null && subOrgaPkId.size() > 0) {
			parameters.put("subOrgaPkId", subOrgaPkId);
			jpql.append(" AND C.subOrganizationPkId IN :subOrgaPkId ");
		}

		return getByQuery(ExaminationRequestCompleted.class, jpql.toString(), parameters);
	}

	@Override
	public List<Diagnose> getDiagnoseById(String id) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("id", "%" + id + "%");

		jpql.append("SELECT A FROM Diagnose A ");
		jpql.append("WHERE A.id LIKE :id ");
		jpql.append("ORDER BY A.id ");

		return getByQuery(Diagnose.class, jpql.toString(), parameters, 0, 20);
	}

	@Override
	public List<Diagnose> getDiagnoseByName(String name) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("name", "%" + name + "%");

		jpql.append("SELECT A FROM Diagnose A ");
		jpql.append("WHERE A.nameMn LIKE :name OR A.nameEn LIKE :name OR A.nameRu LIKE :name OR A.id LIKE :name ");
		jpql.append("ORDER BY A.id ");

		return getByQuery(Diagnose.class, jpql.toString(), parameters, 0, 20);
	}

	@Override
	public void saveInspectionForm(Inspection inspection, List<InspectionForm> inspectionForm, LoggedUser lu)
			throws Exception {
		Date date = new Date();
		deleteByAnyField(InspectionForm.class, "inspectionPkId", inspection.getPkId());
		for (InspectionForm form : inspectionForm) {
			if (Tool.ADDED.equals(form.getStatus())) {
				form.setPkId(Tools.newPkId());
				form.setInspectionPkId(inspection.getPkId());
				form.setCreatedBy(lu.getUser().getPkId());
				form.setCreatedDate(date);
				form.setUpdatedBy(lu.getUser().getPkId());
				form.setUpdatedDate(date);
				insert(form);
			} else if (Tool.MODIFIED.equals(inspection.getStatus())) {
				form.setInspectionPkId(inspection.getPkId());
				form.setUpdatedBy(lu.getUser().getPkId());
				form.setUpdatedDate(date);
				update(form);
			} else if (Tool.DELETE.equals(inspection.getStatus())) {
				deleteByAnyField(InspectionForm.class, "inspectionPkId", inspection.getPkId());
			}
		}

	}

	public List<Employee> getListEmployeeBySubOrganizationPkId(BigDecimal subOrganizationPkId) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		jpql.append("SELECT a FROM Employee a ");
		if (BigDecimal.ZERO.compareTo(subOrganizationPkId) != 0)
			jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ");
		return getByQuery(Employee.class, jpql.toString(), parameters);
	}

	@Override
	public Inspection saveNewOrder(Inspection inspection, LoggedUser lu, List<InspectionDtl> inspectionDtls,
			List<CustomerDiagnose> customerDiagnoses, List<CustomerMedicine> customerMedicines, EmployeeRequest er)
			throws Exception {

		if (Tool.ADDED.equals(inspection.getStatus())) {
			Date date = new Date();

			inspection.setPkId(Tools.newPkId());
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setCustomerPkId(er.getCustomerPkId());
			inspection.setRequestPkId(er.getPkId());
			inspection.setInspectionDate(date);
			inspection.setCreatedDate(date);
			inspection.setCreatedBy(lu.getUser().getPkId());
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);
			EmployeeRequestHistory employeeRequestHistory = getByPkId(EmployeeRequestHistory.class, er.getPkId());
			// if(employeeRequestHistory == null){
			update(er);
			// }else {
			// deleteByPkId(EmployeeRequestHistory.class, er.getPkId());
			// er.setHasPayment(1);
			// insert(er);
			// }
			insert(inspection);
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			for (InspectionDtl dtl : inspectionDtls) {
				{
					dtl.setPkId(Tools.newPkId());
					dtl.setInspectionPkId(inspection.getPkId());
					if (dtl.getExaminationDtls().size() > 0) {
						for (ExaminationDtl examinationDtl : dtl.getExaminationDtls()) {
							InspectionDtlExaminationDtlMap map = new InspectionDtlExaminationDtlMap();
							map.setPkId(Tools.newPkId());
							map.setInspectionDtlPkId(dtl.getPkId());
							map.setExaminationDtlPkId(examinationDtl.getPkId());
							insert(map);
						}
					}
					insert(dtl);
				}
			}

			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			for (CustomerMedicine medicine : customerMedicines) {
				{
					medicine.setPkId(Tools.newPkId());
					medicine.setInspectionPkId(inspection.getPkId());
					medicine.setCreatedDate(date);
					medicine.setCreatedBy(lu.getUser().getPkId());
					medicine.setUpdatedDate(date);
					medicine.setUpdatedBy(lu.getUser().getPkId());
					insert(medicine);
				}
			}
		} else if (Tool.MODIFIED.equals(inspection.getStatus())) {
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("inspectionPkId", inspection.getPkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append(
					"DELETE FROM InspectionDtlExaminationDtlMap a WHERE a.inspectionDtlPkId IN (SELECT b.pkId FROM InspectionDtl b WHERE b.inspectionPkId = :inspectionPkId )");
			setEntityManager(null);
			Date date = new Date();
			inspection.setEmployeePkId(er.getEmployeePkId());
			inspection.setInspectionDate(date);
			inspection.setUpdatedBy(lu.getUser().getPkId());
			inspection.setUpdatedDate(date);

			update(inspection);
			update(er);
			executeNonQuery(jpql.toString(), parameters);
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			for (InspectionDtl dtl : inspectionDtls) {
				dtl.setPkId(Tools.newPkId());
				dtl.setInspectionPkId(inspection.getPkId());
				if (dtl.getExaminationDtls().size() > 0) {
					for (ExaminationDtl examinationDtl : dtl.getExaminationDtls()) {
						InspectionDtlExaminationDtlMap map = new InspectionDtlExaminationDtlMap();
						map.setPkId(Tools.newPkId());
						map.setInspectionDtlPkId(dtl.getPkId());
						map.setExaminationDtlPkId(examinationDtl.getPkId());
						insert(map);
					}
				}
				insert(dtl);
			}

			for (CustomerMedicine medicine : customerMedicines) {
				{
					medicine.setPkId(Tools.newPkId());
					medicine.setInspectionPkId(inspection.getPkId());
					medicine.setCreatedDate(date);
					medicine.setCreatedBy(lu.getUser().getPkId());
					medicine.setUpdatedDate(date);
					medicine.setUpdatedBy(lu.getUser().getPkId());
					insert(medicine);
				}
			}
		} else if (Tool.DELETE.equals(inspection.getStatus())) {
			for (InspectionDtl dtl : getByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId())) {
				deleteByAnyField(InspectionDtlExaminationDtlMap.class, "inspectionDtlPkId", dtl.getPkId());
			}
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("inspectionPkId", inspection.getPkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append(
					"DELETE FROM InspectionDtlExaminationDtlMap a WHERE a.inspectionDtlPkId IN (SELECT b.pkId FROM InspectionDtl b WHERE b.inspectionPkId = :inspectionPkId )");
			executeNonQuery(jpql.toString(), parameters);
			deleteByAnyField(InspectionDtl.class, "inspectionPkId", inspection.getPkId());
			deleteByAnyField(CustomerMedicine.class, "inspectionPkId", inspection.getPkId());
			deleteByPkId(Inspection.class, inspection.getPkId());
		}
		return inspection;
	}

	public List<InspectionDtl> getInspectionDtls(String type) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("dte", new Date());

		if (Tool.INSPECTIONTYPE_EXAMINATION.equals(type)) {
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('EXAMINATION', a.pkId, a.id, a.name) FROM Examination a ");
			jpql.append("INNER JOIN ExaminationPrice b ON a.pkId = b.examinationPkId ");
		} else if (Tool.INSPECTIONTYPE_SURGERY.equals(type)) {
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('SURGERY', a.pkId, a.name, a.name) FROM Surgery a ");
			jpql.append("INNER JOIN SurgeryPrice b ON a.pkId = b.surgeryPkId ");
		} else if (Tool.INSPECTIONTYPE_TREATMENT.equals(type)) {
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('TREATMENT', a.pkId, a.id, a.name) FROM Treatment a ");
			jpql.append("INNER JOIN TreatmentPrice b ON a.pkId = b.treatmentPkId ");
		} else if (Tool.INSPECTIONTYPE_XRAY.equals(type)) {
			jpql.append("SELECT DISTINCT NEW hospital.entity.InspectionDtl('XRAY', a.pkId, a.id, a.name) FROM Xray a ");
			jpql.append("INNER JOIN XrayPrice b ON a.pkId = b.xrayPkId ");
		}

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public List<InspectionForm> getCustomerEnt(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM InspectionForm A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(InspectionForm.class, jpql.toString(), parameters);
	}

	@Override
	public void saveEmployeeMemo(EmployeeMemo employeeMemo, LoggedUser lu) throws Exception {
		BigDecimal pkId = Tools.newPkId();
		if (employeeMemo.getStatus().equals(Tool.ADDED)) {
			Date date = new Date();
			employeeMemo.setPkId(pkId);
			employeeMemo.setEmployeePkId(lu.getEmployeePkId());
			employeeMemo.setCreatedBy(lu.getUser().getPkId());
			employeeMemo.setCreatedDate(date);
			employeeMemo.setUpdatedBy(lu.getUser().getPkId());
			employeeMemo.setUpdatedDate(date);
			insert(employeeMemo);
		} else if (employeeMemo.getStatus().equals(Tool.DELETE)) {
			deleteByAnyField(EmployeeMemo.class, "pkId", employeeMemo.getPkId());
		}
	}

	@Override
	public List<EmployeeMemo> getPrivateMemo(BigDecimal employeePkId, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.EmployeeMemo(A,B.firstName, B.lastName) FROM EmployeeMemo A ");
		jpql.append("INNER JOIN Employee B ON A.employeePkId = B.pkId ");
		jpql.append(
				"WHERE A.employeePkId = :employeePkId AND A.customerPkId = :customerPkId AND A.noteType = 0 ORDER BY A.createdDate DESC");

		return getByQuery(EmployeeMemo.class, jpql.toString(), parameters);
	}

	@Override
	public List<EmployeeMemo> getPublicMemo(BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT NEW hospital.entity.EmployeeMemo(A,B.firstName, B.lastName) FROM EmployeeMemo A ");
		jpql.append("INNER JOIN Employee B ON A.employeePkId = B.pkId ");
		jpql.append("WHERE A.customerPkId = :customerPkId AND A.noteType = 1 ORDER BY A.createdDate DESC");

		return getByQuery(EmployeeMemo.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerProblem> getCustomerProblem(BigDecimal customerPkId, int filterProblemList, int month)
			throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -month);
		Date date = cal.getTime();

		parameters.put("customerPkId", customerPkId);
		parameters.put("date", date);

		/**
		 * Бүгд 0 Онош 1 Гол зовиур 2 Бусад 3
		 */
		List<CustomerProblem> list = new ArrayList<CustomerProblem>();
		if (filterProblemList == 1 || filterProblemList == 0) {
			jpql.append(
					"SELECT NEW hospital.businessentity.CustomerProblem(A.pkId, A.date, C.nameMn) FROM CustomerDiagnose A ");
			jpql.append("INNER JOIN Inspection B ON A.inspectionPkId = B.pkId ");
			jpql.append("INNER JOIN Diagnose C ON A.diagnosePkId = C.pkId ");
			jpql.append("WHERE B.customerPkId = :customerPkId AND A.date > :date ORDER BY A.createdDate DESC");
			list.addAll(getByQuery(CustomerProblem.class, jpql.toString(), parameters));
		}
		if (filterProblemList == 3 || filterProblemList == 0) {
			jpql.delete(0, jpql.length());
			jpql.append(
					"SELECT NEW hospital.businessentity.CustomerProblem(A.pkId, A.matterDate, A.matter) FROM CustomerMatter A ");
			jpql.append("WHERE A.customerPkId = :customerPkId AND A.matterDate > :date ORDER BY A.matterDate DESC");
			list.addAll(getByQuery(CustomerProblem.class, jpql.toString(), parameters));
		}

		return list;
	}

	@Override
	public String getSubOrgaTypePkId(BigDecimal subOrganizationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);

		jpql.append("SELECT B.type FROM SubOrganization A ");
		jpql.append("INNER JOIN SubOrganizationType B ON A.subOrganizationTypePkId = B.pkId ");
		jpql.append("WHERE A.pkId = :subOrganizationPkId ");

		return (String) getByQuerySingle(jpql.toString(), parameters);
	}

	@Override
	public HashMap<String, Object> getLastInspectionDtlsByEmployee(BigDecimal employeePkId, String type)
			throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);
		parameters.put("type", type);
		List<InspectionDtl> dtls = new ArrayList<InspectionDtl>();
		List<CustomerMedicine> customerMedicines = new ArrayList<CustomerMedicine>();

		if (Tool.INSPECTIONTYPE_TREATMENT.equals(type)) {
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('TREATMENT', d.pkId, d.id, d.name) FROM Employee a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
			jpql.append("INNER JOIN Treatment d ON c.typePkId = d.pkId ");
			jpql.append("WHERE a.pkId = :employeePkId ");
			jpql.append("GROUP BY d.pkId, d.id, d.name ");
			jpql.append("ORDER BY COUNT(c.pkId) DESC ");

			dtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
			List<BigDecimal> list = new ArrayList<BigDecimal>();
			for (InspectionDtl d : dtls) {
				list.add(d.getTypePkId());
			}

			parameters.put("list", list);
			jpql = new StringBuilder();
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('TREATMENT', a.pkId, a.id, a.name) FROM Treatment a ");
			if (list.size() > 0)
				jpql.append("WHERE a.pkId NOT IN :list ");
			dtls.addAll(getByQuery(InspectionDtl.class, jpql.toString(), parameters));
		} else if (Tool.INSPECTIONTYPE_EXAMINATION.equals(type)) {
			jpql.append(
					"SELECT NEW hospital.entity.InspectionDtl('EXAMINATION', d.pkId, d.id, d.name) FROM Employee a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
			jpql.append("INNER JOIN Examination d ON c.typePkId = d.pkId ");
			jpql.append("WHERE a.pkId = :employeePkId ");
			jpql.append("GROUP BY d.pkId, d.id, d.name ");
			jpql.append("ORDER BY COUNT(c.pkId) DESC ");

			dtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
			List<BigDecimal> list = new ArrayList<BigDecimal>();
			for (InspectionDtl d : dtls) {
				list.add(d.getTypePkId());
			}

			parameters.put("list", list);
			jpql = new StringBuilder();
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('EXAMINATION', a.pkId, a.id, a.name) FROM Examination a ");
			if (list.size() > 0)
				jpql.append("WHERE a.pkId NOT IN :list ");
			dtls.addAll(getByQuery(InspectionDtl.class, jpql.toString(), parameters));
		} else if (Tool.INSPECTIONTYPE_SURGERY.equals(type)) {
			jpql.append("SELECT NEW hospital.entity.InspectionDtl('SURGERY', d.pkId, d.name, d.name) FROM Employee a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
			jpql.append("INNER JOIN Surgery d ON c.typePkId = d.pkId ");
			jpql.append("WHERE a.pkId = :employeePkId ");
			jpql.append("GROUP BY d.pkId, d.name, d.name ");
			jpql.append("ORDER BY COUNT(c.pkId) DESC ");

			dtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
			List<BigDecimal> list = new ArrayList<BigDecimal>();
			for (InspectionDtl d : dtls) {
				list.add(d.getTypePkId());
			}

			parameters.put("list", list);
			jpql = new StringBuilder();
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.InspectionDtl('SURGERY', a.pkId, a.name, a.name) FROM Surgery a ");
			if (list.size() > 0)
				jpql.append("WHERE a.pkId NOT IN :list ");
			dtls.addAll(getByQuery(InspectionDtl.class, jpql.toString(), parameters));
		} else if (Tool.INSPECTIONTYPE_XRAY.equals(type)) {
			jpql.append("SELECT NEW hospital.entity.InspectionDtl('XRAY', d.pkId, d.id, d.name) FROM Employee a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
			jpql.append("INNER JOIN Xray d ON c.typePkId = d.pkId ");
			jpql.append("WHERE a.pkId = :employeePkId ");
			jpql.append("GROUP BY d.pkId, d.id, d.name ");
			jpql.append("ORDER BY COUNT(c.pkId) DESC ");

			dtls = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
			List<BigDecimal> list = new ArrayList<BigDecimal>();
			for (InspectionDtl d : dtls) {
				list.add(d.getTypePkId());
			}

			parameters.put("list", list);
			jpql = new StringBuilder();
			jpql.append("SELECT DISTINCT NEW hospital.entity.InspectionDtl('XRAY', a.pkId, a.id, a.name) FROM Xray a ");
			if (list.size() > 0)
				jpql.append("WHERE a.pkId NOT IN :list ");
			dtls.addAll(getByQuery(InspectionDtl.class, jpql.toString(), parameters));
		} else if ("Medicine".equals(type)) {
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.CustomerMedicine(d.pkId, d.id, d.name, d.iName) FROM Employee a ");
			jpql.append("INNER JOIN Inspection b ON a.pkId = b.employeePkId ");
			jpql.append("INNER JOIN CustomerMedicine c ON b.pkId = c.inspectionPkId ");
			jpql.append("INNER JOIN Medicine d ON c.medicinePkId = d.pkId ");
			jpql.append("WHERE a.pkId = :employeePkId ");
			jpql.append("GROUP BY d.pkId, d.id, d.name, d.iName ");
			jpql.append("ORDER BY COUNT(c.pkId) DESC ");

			customerMedicines = getByQuery(CustomerMedicine.class, jpql.toString(), parameters);

			List<BigDecimal> list = new ArrayList<BigDecimal>();
			for (CustomerMedicine d : customerMedicines) {
				list.add(d.getMedicinePkId());
			}

			parameters.put("list", list);
			jpql = new StringBuilder();
			jpql.append(
					"SELECT DISTINCT NEW hospital.entity.CustomerMedicine(a.pkId, a.id, a.name, a.iName) FROM Medicine a ");
			if (list.size() > 0)
				jpql.append("WHERE a.pkId NOT IN :list ");
			customerMedicines.addAll(getByQuery(CustomerMedicine.class, jpql.toString(), parameters));
			hashMap.put("med", customerMedicines);
		}

		hashMap.put("ins", dtls);

		return hashMap;
	}

	@Override
	public HashMap<String, Object> getDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql;
		parameters.put("inspectionPkId", inspectionPkId);

		List<CustomerMedicine> listMedicineLog = new ArrayList<CustomerMedicine>();
		List<InspectionDtl> listExaminationLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listXrayLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listSurgeryLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listTreatmentLog = new ArrayList<InspectionDtl>();
		String title = "";

		jpql = new StringBuilder();
		jpql.append("SELECT a FROM Inspection a WHERE a.pkId = :inspectionPkId ");
		List<Inspection> inspections = getByQuery(Inspection.class, jpql.toString(), parameters);
		if (inspections.size() > 0) {
			parameters.put("requestPkId", inspections.get(0).getRequestPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a WHERE a.pkId = :requestPkId ");
			List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
			if (employeeRequests.size() > 0) {
				Customer customer = getByPkId(Customer.class, employeeRequests.get(0).getCustomerPkId());
				Employee employee = getByPkId(Employee.class, employeeRequests.get(0).getEmployeePkId());
				if (customer != null && employee != null) {
					title = employee.getFirstName() + " - " + customer.getFirstName() + " ("
							+ employeeRequests.get(0).getBeginDate() + ")";
				}
			} else {
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM EmployeeRequestHistory a WHERE a.pkId = :requestPkId ");
				List<EmployeeRequestHistory> employeeRequestHistories = getByQuery(EmployeeRequestHistory.class,
						jpql.toString(), parameters);
				if (employeeRequestHistories.size() > 0) {
					Customer customer = getByPkId(Customer.class, employeeRequestHistories.get(0).getCustomerPkId());
					Employee employee = getByPkId(Employee.class, employeeRequestHistories.get(0).getEmployeePkId());
					if (customer != null && employee != null) {
						title = employee.getFirstName() + " - " + customer.getFirstName() + " ("
								+ employeeRequestHistories.get(0).getBeginDate() + ")";
					}
				}
			}
		}

		// listMedicineLog
		jpql = new StringBuilder();
		jpql.append(
				"SELECT NEW hospital.entity.CustomerMedicine(a, b.id, b.name, b.iName , b.drugDose, b.calcDose, b.calcDrugDose, b.calcType, b.typePkId) ");
		jpql.append("FROM CustomerMedicine a ");
		jpql.append("INNER JOIN Medicine b ON a.medicinePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		listMedicineLog = getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
		for (CustomerMedicine medicine : listMedicineLog) {
			View_ConstantMedicineType constantMedicineType = getByPkId(View_ConstantMedicineType.class,
					medicine.getMedicineTypePkId());
			if (constantMedicineType != null)
				medicine.setMedicineType(constantMedicineType.getName());
		}

		// listExaminationLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM InspectionDtl a ");
		jpql.append("INNER JOIN Examination b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		jpql.append("AND a.type = 'EXAMINATION' ");
		listExaminationLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl inspection : listExaminationLog) {
			parameters.put("inspectionDtlPkId", inspection.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.ExaminationDtl(c, b, a) ");
			jpql.append("FROM ElementPrice a ");
			jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
			jpql.append("INNER JOIN ExaminationDtl c ON b.pkId = c.elementPkId ");
			jpql.append("INNER JOIN InspectionDtlExaminationDtlMap d ON c.pkId = d.examinationDtlPkId ");
			jpql.append("WHERE d.inspectionDtlPkId = :inspectionDtlPkId ");

			inspection.setOcsListType("INSPECTION");
			List<ExaminationDtl> maps = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
			if (maps.size() > 0) {
				for (ExaminationDtl dtl : maps) {
					dtl.setOcsListType("EXAMINATION");
					inspection.getExaminationDtls().add(dtl);
				}
			} else {
				parameters.put("examinationPkId", inspection.getTypePkId());
				parameters.put("dte", new Date());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM ExaminationPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				
				jpql.append("AND a.examinationPkId = :examinationPkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<ExaminationPrice> examinationPrices = getByQuery(ExaminationPrice.class, jpql.toString(),
						parameters);
				if (examinationPrices.size() > 0) {
					inspection.setCost(examinationPrices.get(0).getPrice());
					inspection.setExaminationDtlsSumPrice(examinationPrices.get(0).getPrice());
				}
			}
		}

		// listXrayLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM InspectionDtl a ");
		jpql.append("INNER JOIN Xray b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		jpql.append("AND a.type = 'XRAY' ");
		listXrayLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl dtl : listXrayLog) {
			jpql = new StringBuilder();
			parameters.put("xrayPkId", dtl.getTypePkId());
			parameters.put("dte", new Date());
			jpql.append("SELECT a FROM XrayPrice a ");
			jpql.append("WHERE a.xrayPkId = :xrayPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<XrayPrice> list = getByQuery(XrayPrice.class, jpql.toString(), parameters);
			if (list.size() > 0) {
				dtl.setCost(list.get(0).getPrice());
			}
		}

		// listSurgeryLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.name, b.name) ");
		jpql.append("FROM InspectionDtl a ");
		jpql.append("INNER JOIN Surgery b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		jpql.append("AND a.type = 'SURGERY' ");
		listSurgeryLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl dtl : listSurgeryLog) {
			jpql = new StringBuilder();
			parameters.put("surgeryPkId", dtl.getTypePkId());
			parameters.put("dte", new Date());
			jpql.append("SELECT a FROM SurgeryPrice a ");
			jpql.append("WHERE a.surgeryPkId = :surgeryPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<SurgeryPrice> list = getByQuery(SurgeryPrice.class, jpql.toString(), parameters);
			if (list.size() > 0) {
				dtl.setCost(list.get(0).getPrice());
			}
		}

		// listTreatmentLog
		jpql = new StringBuilder();
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM InspectionDtl a ");
		jpql.append("INNER JOIN Treatment b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		jpql.append("AND a.type = 'TREATMENT' ");
		listTreatmentLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl dtl : listTreatmentLog) {
			jpql = new StringBuilder();
			parameters.put("treatmentPkId", dtl.getTypePkId());
			parameters.put("dte", new Date());
			jpql.append("SELECT a FROM TreatmentPrice a ");
			jpql.append("WHERE a.treatmentPkId = :treatmentPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<TreatmentPrice> list = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);
			if (list.size() > 0) {
				dtl.setCost(list.get(0).getPrice());
			}
		}

		ret.put("listMedicineLog", listMedicineLog);
		ret.put("listExaminationLog", listExaminationLog);
		ret.put("listXrayLog", listXrayLog);
		ret.put("listSurgeryLog", listSurgeryLog);
		ret.put("listTreatmentLog", listTreatmentLog);
		ret.put("title", title);

		return ret;
	}

	@Override
	public HashMap<String, Object> getDtlByRecipePkId(BigDecimal pkId) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql;
		parameters.put("pkId", pkId);

		List<CustomerMedicine> listMedicineLog = new ArrayList<CustomerMedicine>();
		List<InspectionDtl> listExaminationLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listXrayLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listSurgeryLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listTreatmentLog = new ArrayList<InspectionDtl>();
		String title = "";

		jpql = new StringBuilder();
		jpql.append("SELECT a FROM DoctorRecipe a WHERE a.pkId = :pkId ");
		List<DoctorRecipe> doctorRecipes = getByQuery(DoctorRecipe.class, jpql.toString(), parameters);
		if (doctorRecipes.size() > 0) {
			title = doctorRecipes.get(0).getName() + " (" + doctorRecipes.get(0).getCreatedDate() + ")";
		}

		// listMedicineLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.CustomerMedicine(a, b) ");
		jpql.append("FROM DoctorRecipeDtl a ");
		jpql.append("INNER JOIN Medicine b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		// listExaminationLog = getByQuery(InspectionDtl.class, jpql.toString(),
		// parameters);
		listMedicineLog = getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
		for (CustomerMedicine customerMedicine : listMedicineLog) {
			View_ConstantMedicineType constantMedicineType = getByPkId(View_ConstantMedicineType.class,
					customerMedicine.getMedicineTypePkId());
			if (constantMedicineType != null)
				customerMedicine.setMedicineType(constantMedicineType.getName());
			customerMedicine.calcMDEN();
		}

		// listExaminationLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM DoctorRecipeDtl a ");
		jpql.append("INNER JOIN Examination b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		listExaminationLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl dtl : listExaminationLog) {
			dtl.setExaminationDtlsSumPrice(examinationDtlPrice(dtl.getTypePkId()));
			Date dte = new Date();
			parameters.put("dte", dte);
			parameters.put("examinationPkId", dtl.getTypePkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM ExaminationDtl a ");
			jpql.append("WHERE a.examinationPkId = :examinationPkId ");
			List<ExaminationDtl> dtls = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
			for (ExaminationDtl examinationDtl : dtls) {
				parameters.put("elementPkId", examinationDtl.getElementPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM Element a ");
				jpql.append("WHERE a.pkId = :elementPkId ");
				List<Element> elements = getByQuery(Element.class, jpql.toString(), parameters);
				if (elements.size() > 0) {
					Element element = elements.get(0);
					parameters.put("elementPkId", element.getPkId());
					jpql = new StringBuilder();
					jpql.append("SELECT a FROM ElementPrice a ");
					jpql.append("WHERE a.elementPkId = :elementPkId ");
					jpql.append("AND a.beginDate < :dte ");
					jpql.append("ORDER BY a.beginDate DESC ");
					List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
					if (elementPrices.size() > 0) {
						examinationDtl.setPrice(elementPrices.get(0).getPrice());
						dtl.getExaminationDtls().add(examinationDtl);
					}
				}
			}
		}

		// listXrayLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM DoctorRecipeDtl a ");
		jpql.append("INNER JOIN Xray b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		listXrayLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);

		// listSurgeryLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.name, b.name) ");
		jpql.append("FROM DoctorRecipeDtl a ");
		jpql.append("INNER JOIN Surgery b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		listSurgeryLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);

		// listTreatmentLog
		jpql = new StringBuilder();
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM DoctorRecipeDtl a ");
		jpql.append("INNER JOIN Treatment b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		listTreatmentLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);

		ret.put("listMedicineLog", listMedicineLog);
		ret.put("listExaminationLog", listExaminationLog);
		ret.put("listXrayLog", listXrayLog);
		ret.put("listSurgeryLog", listSurgeryLog);
		ret.put("listTreatmentLog", listTreatmentLog);
		ret.put("title", title);

		return ret;
	}

	public BigDecimal examinationDtlPrice(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);
		parameters.put("dte", new Date());
		jpql.append("SELECT a FROM ExaminationPrice a ");
		jpql.append("WHERE a.examinationPkId = :examinationPkId ");
		jpql.append("AND a.beginDate < :dte ");
		jpql.append("ORDER BY a.beginDate DESC ");
		List<ExaminationPrice> examinationPrices = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
		if (examinationPrices.size() > 0) {
			return examinationPrices.get(0).getPrice();
		}
		return BigDecimal.ZERO;
	}

	@Override
	public HashMap<String, Object> getDtlByConditionalPrescriptionPkId(BigDecimal pkId) throws Exception {
		HashMap<String, Object> ret = new HashMap<String, Object>();

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql;
		parameters.put("pkId", pkId);

		List<CustomerMedicine> listMedicineLog = new ArrayList<CustomerMedicine>();
		List<InspectionDtl> listExaminationLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listXrayLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listSurgeryLog = new ArrayList<InspectionDtl>();
		List<InspectionDtl> listTreatmentLog = new ArrayList<InspectionDtl>();
		String title = "";

		jpql = new StringBuilder();
		jpql.append("SELECT a FROM ConditionalPrescription a WHERE a.pkId = :pkId ");
		List<ConditionalPrescription> conditionalPrescriptions = getByQuery(ConditionalPrescription.class,
				jpql.toString(), parameters);
		if (conditionalPrescriptions.size() > 0) {
			title = conditionalPrescriptions.get(0).getName() + " (" + conditionalPrescriptions.get(0).getUsageDate()
					+ ")";
		}

		// listMedicineLog
		jpql = new StringBuilder();
		jpql.append(
				"SELECT NEW hospital.entity.CustomerMedicine(a, b.drugDose, b.measurementPkId, b.iName, b.id, b.name, b.calcDrugDose, b.calcDose, b.calcType, b.typePkId) ");
		jpql.append("FROM ConditionalPrescriptionDtl a ");
		jpql.append("INNER JOIN Medicine b ON a.medicinePkId = b.pkId ");
		jpql.append("WHERE a.conditionalPrescriptionPkId = :pkId ");
		listMedicineLog = getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
		for (CustomerMedicine ml : listMedicineLog) {
			View_ConstantMedicineType constantMedicineType = getByPkId(View_ConstantMedicineType.class,
					ml.getMedicineTypePkId());
			if (constantMedicineType != null)
				ml.setMedicineType(constantMedicineType.getName());
			ml.calcMDEN();
		}

		// listExaminationLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM ConditionalPrescriptionDtl a ");
		jpql.append("INNER JOIN Examination b ON a.examinationPkId = b.pkId ");
		jpql.append("WHERE a.conditionalPrescriptionPkId = :pkId ");
		listExaminationLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		for (InspectionDtl dtl : listExaminationLog) {
			dtl.setExaminationDtlsSumPrice(examinationDtlPrice(dtl.getTypePkId()));
			Date dte = new Date();
			parameters.put("dte", dte);
			parameters.put("examinationPkId", dtl.getTypePkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM ExaminationDtl a ");
			jpql.append("WHERE a.examinationPkId = :examinationPkId ");
			List<ExaminationDtl> dtls = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
			for (ExaminationDtl examinationDtl : dtls) {
				parameters.put("elementPkId", examinationDtl.getElementPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM Element a ");
				jpql.append("WHERE a.pkId = :elementPkId ");
				List<Element> elements = getByQuery(Element.class, jpql.toString(), parameters);
				if (elements.size() > 0) {
					Element element = elements.get(0);
					parameters.put("elementPkId", element.getPkId());
					jpql = new StringBuilder();
					jpql.append("SELECT a FROM ElementPrice a ");
					jpql.append("WHERE a.elementPkId = :elementPkId ");
					jpql.append("AND a.beginDate < :dte ");
					jpql.append("ORDER BY a.beginDate DESC ");
					List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
					if (elementPrices.size() > 0) {
						examinationDtl.setPrice(elementPrices.get(0).getPrice());
						dtl.getExaminationDtls().add(examinationDtl);
					}
				}
			}
		}

		// listXrayLog
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id, b.name) ");
		jpql.append("FROM ConditionalPrescriptionDtl a ");
		jpql.append("INNER JOIN Xray b ON a.xrayPkId = b.pkId ");
		jpql.append("WHERE a.conditionalPrescriptionPkId = :pkId ");
		listXrayLog = getByQuery(InspectionDtl.class, jpql.toString(), parameters);

		// //listSurgeryLog
		// jpql = new StringBuilder();
		// jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.name,
		// b.name) ");
		// jpql.append("FROM DoctorRecipeDtl a ");
		// jpql.append("INNER JOIN Surgery b ON a.typePkId = b.pkId ");
		// jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		// listSurgeryLog = getByQuery(InspectionDtl.class, jpql.toString(),
		// parameters);
		//
		// //listTreatmentLog
		// jpql = new StringBuilder();
		// jpql = new StringBuilder();
		// jpql.append("SELECT NEW hospital.entity.InspectionDtl(a, b.id,
		// b.name) ");
		// jpql.append("FROM DoctorRecipeDtl a ");
		// jpql.append("INNER JOIN Treatment b ON a.typePkId = b.pkId ");
		// jpql.append("WHERE a.doctorRecipePkId = :pkId ");
		// listTreatmentLog = getByQuery(InspectionDtl.class, jpql.toString(),
		// parameters);

		ret.put("listMedicineLog", listMedicineLog);
		ret.put("listExaminationLog", listExaminationLog);
		ret.put("listXrayLog", listXrayLog);
		ret.put("listSurgeryLog", listSurgeryLog);
		ret.put("listTreatmentLog", listTreatmentLog);
		ret.put("title", title);

		return ret;
	}

	@Override
	public CustomerPain getCustomerPainSingle(BigDecimal inspectionPkId) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerPain A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		List<CustomerPain> list = getByQuery(CustomerPain.class, jpql.toString(), parameters);
		if (list.size() == 0)
			return new CustomerPain();
		return list.get(0);
	}

	@Override
	public CustomerPlan getCustomerPlanSingle(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerPlan A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		List<CustomerPlan> list = getByQuery(CustomerPlan.class, jpql.toString(), parameters);
		if (list.size() == 0)
			return new CustomerPlan();
		return list.get(0);
	}

	@Override
	public CustomerQuestion getCustomerQuestionSingle(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerQuestion A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		List<CustomerQuestion> list = getByQuery(CustomerQuestion.class, jpql.toString(), parameters);
		if (list.size() == 0)
			return new CustomerQuestion();
		return list.get(0);
	}

	@Override
	public CustomerInspection getCustomerInspectionSingle(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerQuestion A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		List<CustomerInspection> list = getByQuery(CustomerInspection.class, jpql.toString(), parameters);
		if (list.size() == 0)
			return new CustomerInspection();
		return list.get(0);
	}

	@Override
	public List<CustomerAttachment> getCustomerAttachment(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerAttachment A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(CustomerAttachment.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerPain> getCustomerPain(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerPain A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(CustomerPain.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerPlan> getCustomerPlan(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerPlan A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(CustomerPlan.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerQuestion> getCustomerQuestion(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerQuestion A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");

		return getByQuery(CustomerQuestion.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerInspection> getCustomerInspection(List<BigDecimal> inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerInspection A ");
		jpql.append("WHERE A.inspectionPkId IN :inspectionPkId ");
		return getByQuery(CustomerInspection.class, jpql.toString(), parameters, 0, 10);
	}

	@Override
	public List<Diagnose> getTopDiagnose(BigDecimal employeePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);

		jpql.append("SELECT NEW hospital.entity.Diagnose(B, COUNT(A.diagnosePkId)) FROM CustomerDiagnose A ");
		jpql.append("LEFT JOIN Diagnose B ON A.diagnosePkId = B.pkId ");
		jpql.append("WHERE A.employeePkId = :employeePkId ");
		jpql.append("GROUP BY B ");
		jpql.append("ORDER BY COUNT(A.diagnosePkId) DESC");

		return getByQuery(Diagnose.class, jpql.toString(), parameters, 0, 20);
	}

	@Override
	public CustomerPastHistory getCustomerPast(BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);

		jpql.append("SELECT A FROM CustomerPastHistory A ");
		jpql.append("WHERE A.customerPkId = :customerPkId ");

		List<CustomerPastHistory> obj = getByQuery(CustomerPastHistory.class, jpql.toString(), parameters);
		if (obj == null || obj.size() == 0)
			return null;
		return obj.get(0);
	}

	@Override
	public List<InspectionDtl> getCustomerTreatment(Date beginDate, Date endDate, BigDecimal customerPkId,
			String treatmentName, BigDecimal treatmentTypePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("customerPkId", customerPkId);
		parameters.put("treatmentName", "%" + treatmentName + "%");
		parameters.put("treatmentTypePkId", treatmentTypePkId);

		jpql.append(
				"SELECT NEW hospital.entity.InspectionDtl(B, D.name, E.name, A.inspectionDate, C.doneCount, F.firstName) FROM InspectionDtl B ");
		jpql.append("LEFT JOIN Inspection A ON A.pkId = B.inspectionPkId ");
		jpql.append("LEFT JOIN Employee F ON F.pkId = A.employeePkId ");
		jpql.append("LEFT JOIN CustomerTreatment C ON B.pkId = C.inspectionDtlPkId ");
		jpql.append("LEFT JOIN Treatment D ON B.typePkId = D.pkId ");
		jpql.append("LEFT JOIN TreatmentType E ON D.treatmentTypePkId = E.pkId ");
		jpql.append("WHERE A.inspectionDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND A.customerPkId = :customerPkId AND D.name LIKE :treatmentName ");
		if (treatmentTypePkId.compareTo(BigDecimal.ZERO) == 1)
			jpql.append("AND D.treatmentTypePkId = :treatmentTypePkId");

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public List<InspectionDtl> getCustomerSurgery(Date beginDate, Date endDate, BigDecimal customerPkId,
			String surgeryName, BigDecimal surgeryTypePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("customerPkId", customerPkId);
		parameters.put("surgeryName", "%" + surgeryName + "%");
		parameters.put("surgeryTypePkId", surgeryTypePkId);

		jpql.append(
				"SELECT NEW hospital.entity.InspectionDtl(B, D.name, E.name, A.inspectionDate, 0, F.firstName) FROM InspectionDtl B ");
		jpql.append("LEFT JOIN Inspection A ON A.pkId = B.inspectionPkId ");
		jpql.append("LEFT JOIN Employee F ON F.pkId = A.employeePkId ");
		jpql.append("LEFT JOIN CustomerSurgery C ON B.pkId = C.inspectionDtlPkId ");
		jpql.append("LEFT JOIN Surgery D ON B.typePkId = D.pkId ");
		jpql.append("LEFT JOIN SurgeryType E ON D.surgeryTypePkId = E.pkId ");
		jpql.append("WHERE A.inspectionDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND A.customerPkId = :customerPkId AND D.name LIKE :surgeryName ");
		if (surgeryTypePkId.compareTo(BigDecimal.ZERO) == 1)
			jpql.append("AND D.surgeryTypePkId = :surgeryTypePkId");

		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}

	@Override
	public void saveCustomerPastHistory(CustomerPastHistory pastHistory, LoggedUser lu) throws Exception {
		if (pastHistory.getPkId() == null) {
			pastHistory.setPkId(Tools.newPkId());
			insert(pastHistory);
		} else {
			update(pastHistory);
		}
	}

	@Override
	public void saveCustomerMatter(CustomerMatter matter, LoggedUser lu) throws Exception {
		if (matter.getStatus().equals(Tool.ADDED)) {
			matter.setPkId(Tools.newPkId());
			Date date = new Date();
			matter.setMatterDate(date);
			matter.setCreatedBy(lu.getEmployeePkId());
			matter.setCreatedDate(date);
			matter.setUpdatedBy(lu.getEmployeePkId());
			matter.setUpdatedDate(date);
			insert(matter);
		} else if (matter.getStatus().equals(Tool.MODIFIED)) {
			Date date = new Date();
			matter.setUpdatedBy(lu.getEmployeePkId());
			matter.setUpdatedDate(date);
			update(matter);
		} else if (matter.getStatus().equals(Tool.DELETE)) {
			deleteByAnyField(EmployeeMemo.class, "pkId", matter.getPkId());
		}
	}

	@Override
	public void saveEmployeeRequest(EmployeeRequest er) throws Exception {
		update(er);
	}

	@Override
	public List<Organization> getOrganization(BigDecimal orPkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("key", orPkId);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT  a  FROM  Organization  a ");
		builder.append("WHERE  a.pkId= :key ");
		return getByQuery(Organization.class, builder.toString(), map);
	}

	@Override
	public List<InspectionDetail> getInspecionDtl(BigDecimal inspectionPkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("inspectionPkId", inspectionPkId);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT NEW hospital.businessentity.InspectionDetail(a.type,a.typePkId) ");
		builder.append("FROM InspectionDtl  a ");
		builder.append("WHERE  a.inspectionPkId =:inspectionPkId ");
		return getByQuery(InspectionDetail.class, builder.toString(), map);
	}

	@Override
	public List<Examination> getExamination(List<BigDecimal> pkId, Date date) throws Exception {
		CustomHashMap map = new CustomHashMap();
		if (pkId.size() == 0)
			return null;
		else {
			map.put("inspectionPkId", pkId);
			map.put("date", date);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT  NEW  hospital.entity.Examination(A.name,B.description)  FROM Examination  A ");
			builder.append("INNER JOIN InspectionDtl B ON A.pkId = B.typePkId  ");
			builder.append("INNER JOIN Inspection  C  ON B.inspectionPkId = C.pkId ");
			builder.append(
					"WHERE  A.pkId IN :inspectionPkId AND  C.inspectionDate = :date AND B.description  IS NOT NULL ");
			return getByQuery(Examination.class, builder.toString(), map);
		}
	}

	@Override
	public List<Treatment> getTreatment(List<BigDecimal> pkId, Date date) throws Exception {
		CustomHashMap map = new CustomHashMap();
		if (pkId.size() == 0)
			return null;
		else {
			map.put("inspectionPkId", pkId);
			map.put("date", date);
			StringBuilder builder = new StringBuilder();
			builder.append(
					"SELECT NEW  hospital.entity.Treatment(A.name,B.qty,B.times,B.dayLength)   FROM Treatment  A ");
			builder.append("INNER JOIN InspectionDtl B  ON A.pkId = B.typePkId  ");
			builder.append("INNER JOIN Inspection C  ON B.inspectionPkId  = C.pkId  ");
			builder.append("WHERE  a.pkId IN  :inspectionPkId AND  C.inspectionDate = :date ");
			return getByQuery(Treatment.class, builder.toString(), map);
		}
	}

	@Override
	public List<Xray> getXray(List<BigDecimal> pkId, Date d) throws Exception {
		CustomHashMap map = new CustomHashMap();
		if (pkId.size() == 0)
			return null;
		else {
			map.put("inspectionPkId", pkId);
			map.put("date", d);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT NEW hospital.entity.Xray( A.name,B.description)  FROM Xray  A ");
			builder.append("INNER JOIN InspectionDtl  B ON A.pkId = B.typePkId ");
			builder.append("INNER JOIN Inspection  C ON B.inspectionPkId= C.pkId ");
			builder.append(
					"WHERE  a.pkId IN  :inspectionPkId AND C.inspectionDate=:date AND B.description IS  NOT NULL ");
			builder.append("GROUP BY A.name,B.description ");
			return getByQuery(Xray.class, builder.toString(), map);
		}
	}

	@Override
	public List<Surgery> getSurgery(List<BigDecimal> pkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		if (pkId.size() == 0)
			return null;
		else {
			map.put("inspectionPkId", pkId);
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT a FROM Surgery  a ");
			builder.append("WHERE  a.pkId IN  :inspectionPkId ");
			return getByQuery(Surgery.class, builder.toString(), map);
		}
	}

	@Override
	public InspectionForm getInspectionFormByType(BigDecimal inspectionPkId, String type) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("inspectionType", type);
		jpql.append("SELECT A FROM InspectionForm A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");
		jpql.append("AND A.inspectionType LIKE :inspectionType ");

		List<InspectionForm> list = getByQuery(InspectionForm.class, jpql.toString(), parameters);
		if (list.size() > 0)
			return list.get(0);
		else
			return new InspectionForm();
	}

	@Override
	public List<CustomerAttachment> getCustomerAttachmentByType(BigDecimal inspectionPkId, String type)
			throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("type", type);

		jpql.append("SELECT A FROM CustomerAttachment A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");
		jpql.append("AND A.attachmentType = :type ");

		return getByQuery(CustomerAttachment.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerSurgery> getCustomerSurgery(BigDecimal pkId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gt15Pain getCustomerSubOrganization(BigDecimal customerPkId, Date date, String subname) throws Exception {
		CustomHashMap map = new CustomHashMap();
		Gt15Pain gt15Pain = new Gt15Pain();
		map.put("customerPkId", customerPkId);
		map.put("date", date);
		map.put("subname", subname);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT d FROM Inspection a ");
		builder.append("INNER JOIN Employee  c ON a.employeePkId =  c.pkId ");
		builder.append("INNER JOIN SubOrganization  d  ON c.subOrganizationPkId = d.pkId ");
		builder.append("WHERE a.customerPkId = :customerPkId AND a.inspectionDate=:date AND d.name=:subname ");
		builder.append("ORDER BY a.inspectionDate DESC ");
		Customer customer = getByPkId(Customer.class, customerPkId);
		if (customer != null) {
			gt15Pain.setCustomers(getByPkId(Customer.class, customer.getPkId()));
			gt15Pain.setOrganization(getByPkId(Organization.class, customer.getOrganizationPkId()));
			gt15Pain.setAimag(getByPkId(Aimag.class, customer.getAimagPkId()));
			gt15Pain.setSoum(getByPkId(Soum.class, customer.getSumPkId()));
		}
		gt15Pain.setSubOrganizations(getByQuery(SubOrganization.class, builder.toString(), map));
		return gt15Pain;
	}

	@Override
	public List<Gt15Pain> getEmployeeSignature(BigDecimal inspectionPkId, Date date) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("inspectionPkId", inspectionPkId);
		map.put("date", date);
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT NEW hospital.report.Gt15Pain(a,b) FROM Inspection a ");
		builder.append("INNER JOIN Employee b  ON a.employeePkId = b.pkId ");
		builder.append("WHERE a.pkId = :inspectionPkId AND a.inspectionDate=:date ");
		return getByQuery(Gt15Pain.class, builder.toString(), map);
	}

	@Override
	public List<InspectionForm> getInspectionFormPrint(BigDecimal inspectionPkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		StringBuilder builder = new StringBuilder();
		map.put("inspectionPkId", inspectionPkId);
		builder.append("SELECT b FROM  InspectionForm b ");
		builder.append("WHERE b.inspectionPkId =:inspectionPkId  ");
		return getByQuery(InspectionForm.class, builder.toString(), map);
	}

	@Override
	public List<CustomerMedicine> getCustomerMedicinesList(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT NEW hospital.entity.CustomerMedicine(A,B,C) FROM CustomerMedicine A ");
		jpql.append("LEFT JOIN Medicine B ON A.medicinePkId = B.pkId ");
		jpql.append("LEFT JOIN View_ConstantMedicineType  C ON B.typePkId = C.pkId ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");

		return getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerAttachment> getCustomerAttachment(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);

		jpql.append("SELECT A FROM CustomerAttachment A ");
		jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");
		return getByQuery(CustomerAttachment.class, jpql.toString(), parameters);
	}

	@Override
	public List<CustomerDiagnose> getCustomerDiagnoseLast(BigDecimal customerPkId, LoggedUser lu) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("employeePkId", lu.getEmployeePkId());

		jpql.append("SELECT B FROM EmployeeRequest A ");
		jpql.append("INNER JOIN Inspection B ON A.pkId = B.requestPkId ");
		jpql.append("WHERE A.employeePkId = :employeePkId AND A.reInspection = 0 AND B.customerPkId = :customerPkId ");
		jpql.append("ORDER BY A.beginDate DESC ");

		List<Inspection> list = getByQuery(Inspection.class, jpql.toString(), parameters, 0, 1);
		if (list.size() == 1) {
			jpql.delete(0, jpql.length());
			parameters.put("inspectionPkId", list.get(0).getPkId());
			jpql.append("SELECT NEW hospital.entity.CustomerDiagnose(A,B) FROM CustomerDiagnose A ");
			jpql.append("INNER JOIN Diagnose B ON A.diagnosePkId = B.pkId ");
			jpql.append("WHERE A.inspectionPkId = :inspectionPkId ");
			return getByQuery(CustomerDiagnose.class, jpql.toString(), parameters);
		} else {
			return new ArrayList<CustomerDiagnose>();
		}
	}

	@Override
	public List<OrderedTreatment> getCustomerTreatments(Date beginDate, Date endDate, BigDecimal customerPkId,
			String treatmentName, BigDecimal treatmentTypePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpql1 = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		List<OrderedTreatment> ctList = new ArrayList<OrderedTreatment>();
		List<OrderedTreatment> cthList = new ArrayList<OrderedTreatment>();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("customerPkId", customerPkId);
		parameters.put("treatmentName", "%" + treatmentName + "%");
		parameters.put("treatmentTypePkId", treatmentTypePkId);

		jpql.append(
				"SELECT NEW hospital.businessentity.OrderedTreatment(a.pkId, d.name, b.name, a.times, a.doneCount, f.firstName, a.createdDate) ");
		jpql.append("FROM CustomerTreatment a ");
		jpql.append("LEFT JOIN Treatment b ON a.treatmentPkId = b.pkId ");
		jpql.append("LEFT JOIN Inspection c ON a.inspectionPkId = c.pkId ");
		jpql.append("LEFT JOIN TreatmentType d ON d.pkId = b.treatmentTypePkId ");
		jpql.append("LEFT JOIN Employee f ON f.pkId = c.employeePkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		if (treatmentTypePkId != null && treatmentTypePkId.intValue() != 0)
			jpql.append("AND b.treatmentTypePkId = :treatmentTypePkId ");
		jpql.append("AND b.name LIKE :treatmentName ");
		ctList = getByQuery(OrderedTreatment.class, jpql.toString(), parameters);
		
		jpql1.append(
				"SELECT NEW hospital.businessentity.OrderedTreatment(a.pkId, d.name, b.name, a.times, a.doneCount, f.firstName, a.createdDate) ");
		jpql1.append("FROM CustomerTreatmentHistory a ");
		jpql1.append("LEFT JOIN Treatment b ON a.treatmentPkId = b.pkId ");
		jpql1.append("LEFT JOIN Inspection c ON a.inspectionPkId = c.pkId ");
		jpql1.append("LEFT JOIN TreatmentType d ON d.pkId = b.treatmentTypePkId ");
		jpql1.append("LEFT JOIN Employee f ON f.pkId = c.employeePkId ");
		jpql1.append("WHERE a.customerPkId = :customerPkId ");
		if (treatmentTypePkId != null && treatmentTypePkId.intValue() != 0)
			jpql1.append("AND b.treatmentTypePkId = :treatmentTypePkId ");
		jpql1.append("AND b.name LIKE :treatmentName ");
		cthList = getByQuery(OrderedTreatment.class, jpql1.toString(), parameters);
		ctList.addAll(cthList);
		
		return ctList;
	}
}
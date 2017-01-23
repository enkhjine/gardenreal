package hospital.businesslogic;

import hospital.businessentity.CompletedTreatment;
import hospital.businessentity.EmployeeRequestCount;
import hospital.businessentity.EmployeeRequestEntity;
import hospital.businessentity.ExaminationDashboard;
import hospital.businessentity.ExaminationEmployeeDashboard;
import hospital.businessentity.IncomeBySubOrga;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.PaymentEntity;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IDashboardLogicLocal;
import hospital.entity.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.criteria.ParameterExpression;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "DashboardLogic", mappedName = "hospital.businesslogic.DashboardLogic")
public class LogicDashboard extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IDashboardLogic,
		IDashboardLogicLocal {

	@Resource
	SessionContext sessionContext;

	public LogicDashboard() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<SubOrganization> getSubOrganizations() throws Exception {
		return getAll(SubOrganization.class);
	}

	@Override
	public List<Employee> getEmployees(BigDecimal subOrganizationPkId)
			throws Exception {

		List<Employee> list = new ArrayList<Employee>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a ");
		jpql.append("FROM Employee a ");
		jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ");
		list = getByQuery(Employee.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<IncomeBySubOrga> getDailyBillBySubOrganization(Date beginDate, Date endDate)
			throws Exception {

		List<IncomeBySubOrga> list = new ArrayList<IncomeBySubOrga>();
		List<ViewEmployee> employee = new ArrayList<ViewEmployee>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.IncomeBySubOrga(SUM(c.amount), a.name, a.pkId) FROM SubOrganization a ");
		jpql.append("LEFT JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("LEFT JOIN PaymentDtl c ON c.employeePkId = b.pkId ");
		jpql.append("LEFT JOIN Payment d ON c.paymentPkId = d.pkId ");
		jpql.append("WHERE (d.date BETWEEN :beginDate AND :endDate) OR d.pkId IS NULL ");
		jpql.append("GROUP BY a.name, a.pkId");
		
		list = getByQuery(IncomeBySubOrga.class, jpql.toString(), parameters);
		
		jpql.delete(0, jpql.length());

		jpql.append("SELECT NEW hospital.entity.ViewEmployee(SUM(B.amount), A) FROM ViewEmployee A ");
		jpql.append("LEFT JOIN PaymentDtl B ON B.employeePkId = A.employeePkId ");
		jpql.append("LEFT JOIN Payment C ON B.paymentPkId = C.pkId ");
		jpql.append("WHERE (C.date BETWEEN :beginDate AND :endDate) OR C.pkId IS NULL ");
		jpql.append("GROUP BY A ");
		
		employee = getByQuery(ViewEmployee.class, jpql.toString(), parameters); 
		
		for(IncomeBySubOrga income: list) {
			for(ViewEmployee view: employee) {
				if(income.getSubOrganizationPkId().compareTo(view.getSubOrganizationPkId()) == 0)
					income.getIncomeByEmployee().add(view);
			}
		}
		
		return list;
	}

	@Override
	public List<ViewIncomePlan> getPlanHistory()
			throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT B ");
		jpql.append("FROM SubOrganization A ");
		jpql.append("LEFT JOIN ViewIncomePlan B ON A.pkId = B.subOrganizationPkId");

		return getByQuery(ViewIncomePlan.class, jpql.toString(), parameters);
	}
	
	@Override
	public List<IncomePlan> getPlanHistory(BigDecimal subOrganizationPkId)
			throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		parameters.put("subOrganizationPkId", subOrganizationPkId);
		jpql.append("SELECT a ");
		jpql.append("FROM IncomePlan a ");
		jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ORDER BY a.createdDate DESC ");

		return getByQuery(IncomePlan.class, jpql.toString(), parameters);
	}

	@Override
	public void saveMonthlyPlan(IncomePlan incomePlan, LoggedUser lu)
			throws Exception {
		Date date = new Date();
		IncomePlan plan = new IncomePlan();
		plan.setPkId(Tools.newPkId());
		plan.setUpdatedBy(lu.getUser().getPkId());
		plan.setCreatedBy(lu.getUser().getPkId());
		plan.setUpdatedDate(date);
		plan.setCreatedDate(date);
		plan.setSubOrganizationPkId(incomePlan.getSubOrganizationPkId());
		plan.setPrice(incomePlan.getPrice());

		insert(plan);
	}

	@Override
	public List<BigDecimal> getBillByEmployee(BigDecimal employeePkId,
			Date beginDate, Date endDate) throws Exception {

		List<BigDecimal> list = new ArrayList<BigDecimal>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT SUM(a.amount) FROM PaymentDtl a ");
		jpql.append("INNER JOIN Payment b ON  b.pkId = a.paymentPkId ");
		jpql.append("WHERE a.employeePkId =:employeePkId ");
		jpql.append("AND  b.date BETWEEN :beginDate AND :endDate ");

		list = getByQuery(BigDecimal.class, jpql.toString(), parameters);

		return list;
	}

	@Override
	public List<CompletedTreatment> getTreatmentCountBySubOrganization(
			BigDecimal subOrganizationPkId, Date beginDate, Date endDate)
			throws Exception {

		List<CompletedTreatment> list = new ArrayList<CompletedTreatment>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.CompletedTreatment(d.name, COUNT (d.pkId)) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
		jpql.append("INNER JOIN InspectionDtl c ON c.inspectionPkId= b.pkId ");
		jpql.append("INNER JOIN Treatment d ON d.pkId = c.treatmentPkId ");
		jpql.append("INNER JOIN TreatmentType e ON e.pkId = d.treatmentTypePkId ");
		jpql.append("INNER JOIN SubOrganizationType f ON f.pkId = e.subOrganizationTypePkId ");
		jpql.append("INNER JOIN SubOrganization g ON g.subOrganizationTypePkId = f.pkId ");
		jpql.append("WHERE g.pkId = :subOrganizationPkId  ");
		jpql.append("AND b.inspectionDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND  a.mood IN (2, 4 ) ");
		jpql.append("GROUP BY d.name  ");

		list = getByQuery(CompletedTreatment.class, jpql.toString(), parameters);

		return list;
	}

	@Override
	public List<Employee> getEmployeeWithInspectionCount(Date beginDate,
			Date endDate) throws Exception {

		List<Employee> list = new ArrayList<Employee>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Employee (a.firstName, c.name, count(a.pkId)) AS qq ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN EmployeeRequestHistory b ON b.employeePkId = a.pkId ");		
		jpql.append("INNER JOIN SubOrganization c ON c.pkId = a.subOrganizationPkId ");
		jpql.append("INNER JOIN Inspection d ON d.requestPkId = b.pkId ");
		jpql.append("WHERE b.mood IN (2, 4) ");
		jpql.append("AND  d.inspectionDate BETWEEN :beginDate AND :endDate ");
		jpql.append(" ");
		jpql.append("GROUP BY a.firstName, c.name ");
		jpql.append("ORDER BY qq ");

		list = getByQuery(Employee.class, jpql.toString(), parameters);

		return list;
	}

	@Override
	public List<Employee> getPaymentByEmployee(BigDecimal subOrganizationPkId,
			Date beginDate, Date endDate) throws Exception {
		
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Employee(A, FUNC('MONTH',C.date), SUM(B.amount)) ");
		jpql.append("FROM Employee A ");
		jpql.append("LEFT JOIN PaymentDtl B ON A.pkId = B.employeePkId ");		
		jpql.append("LEFT JOIN Payment C ON B.paymentPkId = C.pkId ");
		jpql.append("WHERE A.subOrganizationPkId = :subOrganizationPkId AND C.date BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY A, FUNC('MONTH',C.date) ");
		
		return getByQuery(Employee.class, jpql.toString(), parameters);
	}

	@Override
	public List<IncomeBySubOrga> getMonthlyBillBySubOrganization(
			Date beginDate, Date endDate) throws Exception {
		
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		StringBuilder jpql = new StringBuilder();
//		jpql.append("SELECT NEW hospital.businessentity.IncomeBySubOrga(a.name, a.pkId, FUNC('MONTH',d.date),SUM(c.amount)) 
//		FROM SubOrganization a ");
//		jpql.append("LEFT JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
//		jpql.append("LEFT JOIN PaymentDtl c ON c.employeePkId = b.pkId ");
//		jpql.append("LEFT JOIN Payment d ON c.paymentPkId = d.pkId ");
//		jpql.append("WHERE (d.date BETWEEN :beginDate AND :endDate) OR d.pkId IS NULL ");
//		jpql.append("GROUP BY a.name, a.pkId, FUNC('MONTH',d.date) ");
		
		jpql.append("SELECT NEW hospital.businessentity.IncomeBySubOrga(SUM(c.amount), a.name, a.pkId, "
				+ "FUNC('MONTH',d.date)) FROM SubOrganization a ");
		jpql.append("LEFT JOIN Employee b ON a.pkId = b.subOrganizationPkId ");
		jpql.append("LEFT JOIN PaymentDtl c ON c.employeePkId = b.pkId ");
		jpql.append("LEFT JOIN Payment d ON c.paymentPkId = d.pkId ");
		jpql.append("WHERE (d.date BETWEEN :beginDate AND :endDate) ");
		jpql.append("GROUP BY a.name, a.pkId, FUNC('MONTH',d.date)");
		
		return getByQuery(IncomeBySubOrga.class, jpql.toString(), parameters);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ExaminationDashboard> getExaminationCount(Date beginDate, Date endDate) throws Exception {
		
		//Lists 
		List<ExaminationDashboard> examinationDashboards = new ArrayList<ExaminationDashboard>();
		List<Examination> examinations = new ArrayList<Examination>();
		List<ExaDashBoard> exaDashboards = new ArrayList<ExaDashBoard>();
		
		//Objects
		ExaminationDashboard examinationDash = new ExaminationDashboard();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();

		Calendar bcal = Calendar.getInstance();
		Calendar ecal = Calendar.getInstance();
		bcal.setTime(beginDate);
		ecal.setTime(endDate);
		Date bDate = bcal.getTime();
		bDate.setHours(0);
		bDate.setMinutes(0);
		bDate.setSeconds(0);
		Date eDate = ecal.getTime();
		eDate.setHours(23);
		eDate.setMinutes(59);
		eDate.setSeconds(59);
		
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String bdateStr = sdfr.format(bDate);
		String edateStr = sdfr.format(eDate);
		
		parameters.put("beginDate", bdateStr);
		parameters.put("endDate", edateStr);
		parameters.put("eDate", endDate);
		
		jpql.append("SELECT a ");
		jpql.append("FROM Examination a ");
		
		examinations = getByQuery(Examination.class, jpql.toString(), null);
		
		for(Examination examination : examinations){
			ExaminationDashboard exa = new ExaminationDashboard();
			exa.setExaminationName(examination.getName());
			exa.setExaminationPkId(examination.getPkId());
			
			parameters.put("examinationPkId", examination.getPkId());
			
			jpql = new StringBuilder();
			jpql.delete(0, jpql.length());
			jpql.append("SELECT NEW hospital.entity.ExaDashBoard(a.customerPkId, b.price) ");
			jpql.append("FROM ExaminationRequestCompleted a ");
			jpql.append("INNER JOIN ExaminationPrice b ON a.examinationPkId = b.examinationPkId ");
			jpql.append("WHERE a.examinationPkId = :examinationPkId ");
			jpql.append("AND a.requestDate BETWEEN :beginDate AND :endDate ");
			jpql.append("AND b.beginDate <= :eDate ");
			
			exaDashboards = getByQuery(ExaDashBoard.class, jpql.toString(), parameters);
			
			for(ExaDashBoard exaDashBoard : exaDashboards){
				if(!bigDecimals.contains(exaDashBoard.getCustomerPkId())){
					bigDecimals.add(exaDashBoard.getCustomerPkId());
				}
				exa.setCustomerCount(exa.getCustomerCount() + 1);
				exa.setInAmount(exa.getInAmount().add(exaDashBoard.getPrice()));
			}
			examinationDash.getExaminations().add(exa);			
		}		
		
		examinationDashboards.add(examinationDash);
				
		return examinationDashboards;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ExaminationEmployeeDashboard> getEmployeeCount(BigDecimal examinationPkId, long allCustomerCount, 
			Date beginDate, Date endDate) throws Exception {
		List<ExaminationEmployeeDashboard> examinationDashboards = new ArrayList<ExaminationEmployeeDashboard>();
		List<ExaEmpDashboard> employees = new ArrayList<ExaEmpDashboard>();
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		//List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
		Calendar bcal = Calendar.getInstance();
		Calendar ecal = Calendar.getInstance();
		bcal.setTime(beginDate);
		ecal.setTime(endDate);
		Date bDate = bcal.getTime();
		bDate.setHours(0);
		bDate.setMinutes(0);
		bDate.setSeconds(0);
		Date eDate = ecal.getTime();
		eDate.setHours(23);
		eDate.setMinutes(59);
		eDate.setSeconds(59);
		
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String bdateStr = sdfr.format(bDate);
		String edateStr = sdfr.format(eDate);	

//		System.out.println("Examination Primary Key: = " + examinationPkId + "\n");		
//		System.out.println("Begin Date : = " + bdateStr + "\n");
//		System.out.println("End Date: = " + edateStr + "\n");
//		System.out.println("All Customer Count: = " + allCustomerCount);
		
		parameters.put("beginDate", bdateStr);
		parameters.put("endDate", edateStr);
		parameters.put("examinationPkId", examinationPkId);
		
		jpql.append("SELECT NEW hospital.entity.ExaEmpDashboard(a.firstName, COUNT(b.customerPkId)) ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN ExaminationRequest b ON b.employeePkId = a.pkId ");
		jpql.append("WHERE b.examinationPkId = :examinationPkId ");
		jpql.append("AND b.requestDate BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY a.firstName ");
			
		employees = getByQuery(ExaEmpDashboard.class, jpql.toString(), parameters);
		
		for(ExaEmpDashboard employee : employees){
			ExaminationEmployeeDashboard examinationDashboard = new ExaminationEmployeeDashboard();
			examinationDashboard.setEmployeeName(employee.getEmployeeName());
			examinationDashboard.setCustomerCount(employee.getCustomerCount() + 1);
			if(allCustomerCount > 0){
				examinationDashboard.setPersentageCount((employee.getCustomerCount()/allCustomerCount) * 100);
			}else{
				examinationDashboard.setPersentageCount(0);
			}
			examinationDashboards.add(examinationDashboard);
		}
		//System.out.println("---------////////" + examinationDashboards.size());
		return examinationDashboards;
		
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<LoginDialog> getLoginDialog(Date beginDate) throws Exception {
		List<LoginDialog> loginDialogs = new ArrayList<LoginDialog>();
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		//List<BigDecimal> bigDecimals = new ArrayList<BigDecimal>();
		Calendar bcal = Calendar.getInstance();
		bcal.setTime(beginDate);
		Date bDate = bcal.getTime();
		bDate.setHours(0);
		bDate.setMinutes(0);
		bDate.setSeconds(0);
		
		parameters.put("beginDate", bDate);
		
		jpql.append("SELECT a ");
		jpql.append("FROM LoginDialog a ");		
		jpql.append("ORDER BY a.pkId ASC ");
		
		loginDialogs = getByQuery(LoginDialog.class, jpql.toString(), parameters);
		
		return loginDialogs;
	}

	@Override
	public void saveLoginDialog(LoginDialog dialog, String c ,Date d ) throws Exception {
	    if (Tool.ADDED.equals(dialog.getStatus())) {
	    	 dialog.setPkId(Tools.newPkId());
		     dialog.setComments(c);
		     dialog.setBeginDate(d);
		     insert(dialog);
		}
	}

	@Override
	public List<LoginDialog> setLoginDialog(BigDecimal dialogPkId) throws Exception {
		List<LoginDialog> loginDialogs = new ArrayList<LoginDialog>();
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		
		parameters.put("dialogPkId", dialogPkId);
		
		jpql.append("SELECT a ");
		jpql.append("FROM LoginDialog a ");		
		jpql.append("WHERE a.pkId = :dialogPkId");
		
		loginDialogs = getByQuery(LoginDialog.class, jpql.toString(), parameters);
		
		return loginDialogs;
	}

	@Override
	public void deleteLoginDialog(LoginDialog lodialog) throws Exception {
	    if (Tool.DELETE.equals(lodialog.getStatus())) {
			deleteByPkId(LoginDialog.class, lodialog.getPkId());
		}		
	}

	@Override
	public void updateLoginDialog(LoginDialog lodialog) throws Exception {
		if(Tool.MODIFIED.equals(lodialog.getStatus())){	
//			System.out.println("Comments: = " + lodialog.getComments());			
			update(lodialog);			
		}		
	}

	@Override
	public List<PaymentDtl> getMonthlyBillByPaymentDtlType(Date beginDate, Date endDate) throws Exception {
		List<PaymentDtl> pay = new ArrayList<PaymentDtl>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a ");
		jpql.append("FROM PaymentDtl a ");
		jpql.append("INNER JOIN Payment b on a.paymentPkId = b.pkId ");
		jpql.append("WHERE b.createdDate BETWEEN :beginDate AND :endDate ");
		
		pay = getByQuery(PaymentDtl.class, jpql.toString(), parameters);
		
		return pay;
	}

	@Override
	public List<EmployeeRequestCount> getEmployeeRequestCount(Date beginDate, Date endDate) throws Exception {
		StringBuilder jpql  = new StringBuilder();
		CustomHashMap parameter = new CustomHashMap();
		parameter.put("beginDate", beginDate);
		parameter.put("endDate", endDate);
		jpql.append("SELECT DISTINCT NEW hospital.businessentity.EmployeeRequestCount (b.name, a.firstName, a.lastName , a.pkId ) ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN EmployeeRequest c ON a.pkId = c.employeePkId ");
		jpql.append("INNER JOIN SubOrganization b ON a.subOrganizationPkId = b.pkId ");
		jpql.append("WHERE c.createdDate BETWEEN :beginDate AND :endDate ");
		List<EmployeeRequestCount> list = new ArrayList<EmployeeRequestCount>();
		list = getByQuery(EmployeeRequestCount.class, jpql.toString(), parameter);
		for(EmployeeRequestCount erc:list)
		{
			StringBuilder jpql1  = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("employeePkId", erc.getEmployeePkId());
			parameters.put("beginDate", beginDate);
			parameters.put("endDate", endDate);
			jpql1.append("SELECT a ");
			jpql1.append("FROM EmployeeRequest a ");
			jpql1.append("WHERE a.employeePkId = :employeePkId ");
			jpql1.append("AND a.createdDate BETWEEN :beginDate AND :endDate");
			erc.setRequestList(getByQuery(EmployeeRequest.class, jpql1.toString(), parameters));
		}
		return list;
	}

	@Override
	public List<PaymentEntity> getPaymentDtlType(String pdt, Date beginDate, Date endDate) throws Exception {
		StringBuilder jpql = new StringBuilder();
		List<PaymentEntity> list = new ArrayList<PaymentEntity>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		
		if (pdt.equals(Tool.INSPECTIONPAYMENT)) {
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (a.type, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate AND a.type = 'INSPECTIONPAYMENT'");
			jpql.append("GROUP BY a.type ");
		}
		else
		if(pdt.equals(Tool.INSPECTIONTYPE_TREATMENT))
		{
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (b.name, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN Treatment b ON b.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory c on c.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE c.date BETWEEN :beginDate AND :endDate ");
			jpql.append("GROUP BY b.name ");
		}
		else
		if(pdt.equals(Tool.INSPECTIONTYPE_EXAMINATION) )
		{
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (b.name, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN Examination b ON b.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory c on c.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE c.date BETWEEN :beginDate AND :endDate ");
			jpql.append("GROUP BY b.name ");
		}
		else
		if(pdt.equals(Tool.INSPECTIONTYPE_SURGERY))
		{
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (b.name, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN Surgery b ON b.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory c on c.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE c.date BETWEEN :beginDate AND :endDate ");
			jpql.append("GROUP BY b.name ");
		}
		else
		if(pdt.equals(Tool.INSPECTIONTYPE_XRAY))
		{
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (b.name, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN Xray b ON b.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory c on c.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE c.date BETWEEN :beginDate AND :endDate ");
			jpql.append("GROUP BY b.name ");
		}
		else
		if(pdt.equals("totalCost"))
		{
			jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (a.type, COUNT(a.pkId), SUM(a.amount)) ");
			jpql.append("FROM PaymentDtl a ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
			jpql.append("GROUP BY a.type ");
		}
		
		jpql.append("ORDER BY SUM(a.amount) DESC");
		
		list = getByQuery(PaymentEntity.class, jpql.toString(), parameters);
		
		return list;
	}

	@Override
	public List<PaymentEntity> getTotalPaymentDtl(String pdt, Date beginDate, Date endDate) throws Exception {
		
		CustomHashMap map = new CustomHashMap();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		List<PaymentEntity> list = new ArrayList<PaymentEntity>();
		jpql.append("SELECT NEW hospital.businessentity.PaymentEntity (COUNT(a.pkId), SUM(a.amount)) ");
		jpql.append("FROM PaymentDtl a ");
		if (pdt.equals(Tool.INSPECTIONPAYMENT))
		{
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate AND a.type='INSPECTIONPAYMENT' ");
		}
		else
		if (pdt.equals(Tool.INSPECTIONTYPE_XRAY))
		{
			jpql.append("INNER JOIN Xray c on c.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
		}
		else
		if (pdt.equals(Tool.INSPECTIONTYPE_SURGERY))
		{
			jpql.append("INNER JOIN Surgery c on c.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
		}
		else
		if (pdt.equals(Tool.INSPECTIONTYPE_TREATMENT))
		{
			jpql.append("INNER JOIN Treatment c on c.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
		}
		else
		if (pdt.equals(Tool.INSPECTIONTYPE_EXAMINATION))
		{
			jpql.append("INNER JOIN Examination c on c.pkId = a.typePkId ");
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
		}
		else
		if (pdt.equals("totalCost"))
		{
			jpql.append("INNER JOIN PaymentHistory b on b.paymentPkId = a.paymentPkId ");
			jpql.append("WHERE b.date BETWEEN :beginDate AND :endDate ");
		}
		
		list = getByQuery(PaymentEntity.class, jpql.toString(), map);
		return list;
	}

	@Override
	public List<EmployeeRequestCount> getSubOrganizationName(Date beginDate, Date endDate) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("beginDate", beginDate);
		map.put("endDate", endDate);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.EmployeeRequestCount (c.name) ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN EmployeeRequest b on b.employeePkId = a.pkId ");
		jpql.append("INNER JOIN SubOrganization c on c.pkId = a.subOrganizationPkId ");
		jpql.append("WHERE b.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("GROUP BY c.name ");
		return getByQuery(EmployeeRequestCount.class, jpql.toString(), map);
	}
	
	}

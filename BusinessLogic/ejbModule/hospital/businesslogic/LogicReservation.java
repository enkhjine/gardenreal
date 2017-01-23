package hospital.businesslogic;

import hospital.businessentity.LTime;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicReservationLocal;
import hospital.entity.Customer;
import hospital.entity.Degree;
import hospital.entity.DegreePriceHistory;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.EmployeeRequestPaymentMap;
import hospital.entity.Payment;
import hospital.entity.PaymentDtl;
import hospital.entity.PaymentHistory;
import hospital.entity.SubOrganization;
import hospital.entity.SubOrganizationType;
import hospital.entity.Treatment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicReservation", mappedName = "hospital.businesslogic.LogicReservation")
public class LogicReservation extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicReservation,
		ILogicReservationLocal {

	@Resource
	SessionContext sessionContext;

	public LogicReservation() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}
	
	public String getPaymentId(LoggedUser loggedUser) throws Exception{
		
		String id = "";
		
		Date beginDate = new Date();
		Date endDate = new Date();
		
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(1);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM Payment a ");
		jpql.append("WHERE a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		
		int count = getByQuery(Payment.class, jpql.toString(), parameters).size();
		
		id = beginDate.getYear() - 100 + "" + ((beginDate.getMinutes() + 1) < 10 ? ("0" + (beginDate.getMinutes() + 1)) : (beginDate.getMinutes() + 1)) + (beginDate.getDate() < 10 ? "0" + beginDate.getDate() : beginDate.getDate()) ;
		if(count < 100) id = id + "0";
		if(count < 10) id = id + "0";
		id = id + (count + 1);
		return id;
	}
	
	public BigDecimal getDegreePriceByDegreePkId(BigDecimal degreePkId, int isSecond) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("dte", new Date());
		parameters.put("degreePkId", degreePkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT c FROM Degree b ");
		jpql.append("INNER JOIN DegreePriceHistory c ON b.pkId = c.degreePkId ");
		jpql.append("WHERE c.priceUsageDate < :dte ");
		jpql.append("AND b.pkId = :degreePkId ");
		jpql.append("ORDER BY c.priceUsageDate DESC ");
		List<DegreePriceHistory> degreePriceHistories = getByQuery(DegreePriceHistory.class, jpql.toString(), parameters);
		if(degreePriceHistories.size() > 0) {
			if(isSecond == 1) {
				return degreePriceHistories.get(0).getRePrice();
			}else {
				return degreePriceHistories.get(0).getPrice();
			}
		}
		
		return BigDecimal.ZERO;
	}

	public BigDecimal saveEmployeeRequest(EmployeeRequest employeeRequest,
			LoggedUser loggedUser) throws Exception {
		Date dte = new Date();

		employeeRequest.setBeginDate(Tool.MILLISECONDZERO(employeeRequest.getBeginDate()));
		employeeRequest.setEndDate(Tool.MILLISECONDZERO(employeeRequest.getEndDate()));
		if (Tool.ADDED.equals(employeeRequest.getStatus())) {
			employeeRequest.setPkId(Tools.newPkId());
			employeeRequest.setUpdatedBy(loggedUser.getUser().getPkId());
			employeeRequest.setUpdatedDate(dte);
			employeeRequest.setCreatedBy(loggedUser.getUser().getPkId());
			employeeRequest.setCreatedDate(dte);
			employeeRequest.setOrganizationPkId(loggedUser.getOrganization().getPkId());
			employeeRequest.setMood(1);
			//employeeRequest.setId(Tools.newPkId().toString());
			if(employeeRequest.getIsExpress() == 1){
				employeeRequest.setGuest(1);
			}
			if (employeeRequest.isPaidRequestAmount()==true) {
				employeeRequest.setHasPayment(1);
			}
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("customerPkId", employeeRequest.getCustomerPkId());
			parameters.put("employeePkId", employeeRequest.getEmployeePkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequestHistory a ");
			jpql.append("WHERE a.customerPkId = :customerPkId ");
			jpql.append("AND a.employeePkId = :employeePkId ");
			
			if(employeeRequest.isFreeInspection())
			{
				employeeRequest.setHasPayment(2);
			}
			//Ã�Â¢Ã“Â©Ã�Â»Ã�Â±Ã“Â©Ã‘â‚¬Ã“Â©Ã“Â© Ã‘â€šÃ“Â©Ã�Â»Ã‘ï¿½Ã“Â©Ã�Â½ Ã�Â±Ã�Â¾Ã�Â»
			if(employeeRequest.isPaidRequestAmount()) {
				Employee employee = getByPkId(Employee.class, employeeRequest.getEmployeePkId());
				Degree degree = getByPkId(Degree.class, employee.getDegreePkId());
				degree.setPrice(getDegreePriceByDegreePkId(degree.getPkId(), employeeRequest.getReInspection()));
				
				BigDecimal paymentPkId = Tools.newPkId();
				Payment payment = new Payment();
				payment.setPkId(paymentPkId);
				payment.setId(getPaymentId(loggedUser));
				payment.setOrganizationPkId(loggedUser.getOrganization().getPkId());
				payment.setCustomerPkId(employeeRequest.getCustomerPkId());
				payment.setBasicAmount(degree.getPrice());
				payment.setDiscountPercent(BigDecimal.ZERO);
				payment.setDiscountAmount(BigDecimal.ZERO);
				payment.setAmount(degree.getPrice());
				payment.setPaidAmount(degree.getPrice());
				payment.setDescription("");
				payment.setDate(dte);
				payment.setCreatedDate(dte);
				payment.setCreatedBy(loggedUser.getUser().getPkId());
				payment.setUpdatedDate(dte);
				payment.setUpdatedBy(loggedUser.getUser().getPkId());
				
				employeeRequest.setPaymentPkId(payment.getPkId());
				
				PaymentHistory history = new PaymentHistory();
				paymentPkId = paymentPkId.add(BigDecimal.ONE);
				history.setPkId(paymentPkId);
				history.setPaymentPkId(payment.getPkId());
				history.setCashInAmount(degree.getPrice());
				history.setCardInAmount(BigDecimal.ZERO);
				history.setDate(dte);
				
				PaymentDtl dtl = new PaymentDtl();
				paymentPkId = paymentPkId.add(BigDecimal.ONE);
				dtl.setPkId(paymentPkId);
				dtl.setPaymentPkId(payment.getPkId());
				dtl.setEmployeePkId(employeeRequest.getEmployeePkId());
				dtl.setType(Tool.INSPECTIONPAYMENT);
				dtl.setTypePkId(BigDecimal.ZERO);
				dtl.setAmount(degree.getPrice());
				dtl.setDiscountAmount(BigDecimal.ZERO);
				
				EmployeeRequestPaymentMap map = new EmployeeRequestPaymentMap();
				map.setPkId(payment.getPkId());
				map.setPaymentPkId(payment.getPkId());
				map.setEmployeeRequestPkId(employeeRequest.getPkId());
				
				insert(payment);
				insert(dtl);
				insert(history);
				insert(map);
			}
			employeeRequest.setPaymentPkId(null);
			insert(employeeRequest);
		} else if (Tool.DELETE.equals(employeeRequest.getStatus())) {
			// delete(employeeRequest);
			if(employeeRequest.getHasPayment() == 1){
				List<EmployeeRequestPaymentMap> employeeRequestPaymentMaps = getByAnyField(EmployeeRequestPaymentMap.class, "employeeRequestPkId", employeeRequest.getPkId());
				for (EmployeeRequestPaymentMap employeeRequestPaymentMap : employeeRequestPaymentMaps) {
					deleteByAnyField(PaymentHistory.class, "paymentPkId", employeeRequestPaymentMap.getPaymentPkId());
					deleteByAnyField(PaymentDtl.class, "paymentPkId", employeeRequestPaymentMap.getPaymentPkId());
					deleteByPkId(EmployeeRequestPaymentMap.class, employeeRequestPaymentMap.getPkId());
					deleteByPkId(Payment.class, employeeRequestPaymentMap.getPaymentPkId());
				}
			}
			deleteByPkId(EmployeeRequest.class, employeeRequest.getPkId());
		}
		return employeeRequest.getPkId();
	}

	public List<EmployeeRequest> getEmployeeRequestByDate(Date beginDate,
			Date endDate, LoggedUser loggedUser) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", loggedUser.getOrganization()
				.getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql.append("SELECT NEW hospital.entity.EmployeeRequest(a.pkId, a.organizationPkId, a.customerPkId, a.employeePkId, a.description, a.beginDate, a.endDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, b.lastName, b.firstName, a.mood, a.isExpress, a.insurance, a.hasPayment) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		jpql.append("AND (a.endDate BETWEEN :beginDate AND :endDate OR a.endDate BETWEEN :beginDate AND :endDate) ");
		jpql.append("AND a.beginDate <> a.endDate ");
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.EmployeeRequest(a.pkId, a.organizationPkId, a.customerPkId, a.employeePkId, a.description, a.beginDate, a.endDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, b.lastName, b.firstName, a.mood, a.isExpress, a.isInsurance) FROM EmployeeRequestHistory a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		jpql.append("AND (a.endDate BETWEEN :beginDate AND :endDate OR a.endDate BETWEEN :beginDate AND :endDate) ");
		jpql.append("AND a.beginDate <> a.endDate ");
		employeeRequests.addAll(getByQuery(EmployeeRequest.class, jpql.toString(), parameters));
		return employeeRequests;
	}

	public List<EmployeeRequest> getEmployeeRequests(BigDecimal pkId)
			throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", pkId);
		jpql.append("SELECT NEW hospital.entity.EmployeeRequest(a.pkId, a.organizationPkId, a.customerPkId, a.employeePkId, a.description, a.hasPayment, a.beginDate, a.endDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, b) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.EmployeeRequest(a.pkId, a.organizationPkId, a.customerPkId, a.employeePkId, a.description, a.hasPayment, a.beginDate, a.endDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, b) FROM EmployeeRequestHistory a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		employeeRequests.addAll(getByQuery(EmployeeRequest.class, jpql.toString(), parameters));
		return employeeRequests;
	}

	@Override
	public List<EmployeeRequest> getEmployeeInspectionHistory(
			Customer customer, LoggedUser loggedUser, Date beginDate,
			Date endDate) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customer.getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		jpql.append("SELECT NEW hospital.entity.EmployeeRequest(a.pkId, a.id, a.organizationPkId, a.customerPkId, a.employeePkId, a.date, a.description, a.mood, a.beginTime, a.beginMinute, a.endTime, a.endMinute, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, c, d) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
		jpql.append("INNER JOIN Employee c ON a.employeePkId = c.pkId ");
		jpql.append("INNER JOIN Inspection d ON a.pkId = d.requestPkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId AND (a.mood = 2 OR a.mood = 4) ");
		jpql.append("AND d.inspectionDate BETWEEN :beginDate AND :endDate ");
		List<EmployeeRequest> employeeRequests = getByQuery(
				EmployeeRequest.class, jpql.toString(), parameters);
		for (EmployeeRequest employeeRequest : employeeRequests) {
			parameters.put("inspectionPkId", employeeRequest.getInspection()
					.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT b FROM InspectionDtl a ");
			jpql.append("INNER JOIN Treatment b ON a.treatmentPkId = b.pkId ");
			jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
			employeeRequest.setTreatments(getByQuery(Treatment.class,
					jpql.toString(), parameters));
		}

		List<EmployeeRequest> employeeRequests2 = new ArrayList<EmployeeRequest>();
		for (EmployeeRequest employeeRequest : employeeRequests) {
			employeeRequest.setSumAmount(BigDecimal.ZERO);
			if (employeeRequest.getTreatments().size() < 1) {
				continue;
			} else {
				for (Treatment t : employeeRequest.getTreatments()) {
					employeeRequest.setSumAmount(employeeRequest.getSumAmount()
							.add(t.getPrice()));
				}
			}
			employeeRequests2.add(employeeRequest);
		}

		return employeeRequests2;
	}

	public boolean getDuplicateRequest(Date beginDate, Date endDate, BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", Tool.MILLISECONDZERO(beginDate));
		parameters.put("endDate", Tool.MILLISECONDZERO(endDate));
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT a FROM EmployeeRequest a ");
		jpql.append("WHERE a.customerPkId = :customerPkId AND (((a.beginDate > :beginDate AND a.beginDate < :endDate) OR (a.endDate > :beginDate AND a.endDate < :endDate)) OR ((:beginDate > a.beginDate AND :beginDate < a.endDate) OR (:endDate > a.beginDate AND :endDate < a.endDate))) ");
		List<EmployeeRequest> employees = getByQuery(EmployeeRequest.class,
				jpql.toString(), parameters);
		if(employees.size() > 0) return true;
		return false;
	}
	
	public Employee getEmployee(BigDecimal employeePkId, BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("employeePkId", employeePkId);
		parameters.put("customerPkId", customerPkId);
		Employee employee = getByPkId(Employee.class, employeePkId);
		
		if(employee != null) {
			SubOrganization organization = getByPkId(SubOrganization.class, employee.getSubOrganizationPkId());
			if(organization != null) employee.setSubOrganizationName(organization.getName());
			
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("WHERE a.employeePkId = :employeePkId ");
			jpql.append("AND a.customerPkId =:customerPkId ");
			employee.setEmployeeInspectionCount(employee.getEmployeeInspectionCount() + getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequestHistory a ");
			jpql.append("WHERE a.employeePkId = :employeePkId ");
			jpql.append("AND a.customerPkId =:customerPkId ");
			employee.setEmployeeInspectionCount(employee.getEmployeeInspectionCount() + getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			
			jpql = new StringBuilder();
			jpql.append("SELECT DISTINCT a FROM EmployeeRequest a ");
			jpql.append("INNER JOIN Employee c ON c.pkId = a.employeePkId ");
			jpql.append("INNER JOIN SubOrganization b ON c.subOrganizationPkId = b.pkId ");
			jpql.append("AND a.customerPkId =:customerPkId ");
			employee.setSubOrganizationInspectionCount(employee.getSubOrganizationInspectionCount() + getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			jpql = new StringBuilder();
			jpql.append("SELECT DISTINCT a FROM EmployeeRequestHistory a ");
			jpql.append("INNER JOIN Employee c ON c.pkId = a.employeePkId ");
			jpql.append("INNER JOIN SubOrganization b ON c.subOrganizationPkId = b.pkId ");
			jpql.append("AND a.customerPkId =:customerPkId ");
			employee.setSubOrganizationInspectionCount(employee.getSubOrganizationInspectionCount() + getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			
			return employee;
		}
		
		return null;
	}
	
	public EmployeeRequest getDupplicateEmployeeRequestByDate(EmployeeRequest employeeRequest) throws Exception{
		EmployeeRequest request = (EmployeeRequest) Tool.deepClone(employeeRequest);
		request.setBeginTime(0);
		request.setBeginMinute(0);
		request.setEndTime(23);
		request.setEndMinute(59);
		request.setBeginDate(Tool.MILLISECONDZERO(request.getBeginDate()));
		request.setEndDate(Tool.MILLISECONDZERO(request.getEndDate()));
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("beginDate", request.getBeginDate());
		parameters.put("endDate", request.getEndDate());
		parameters.put("employeePkId", request.getEmployeePkId());
		parameters.put("customerPkId", request.getCustomerPkId());
		
		jpql.append("SELECT a FROM EmployeeRequest a ");
		jpql.append("WHERE a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.employeePkId = :employeePkId ");
		jpql.append("AND a.customerPkId = :customerPkId ");
		
		List<EmployeeRequest> employeeRequests = getByQuery(EmployeeRequest.class, jpql.toString(), parameters);
		
		if(employeeRequests.size() > 0) {
			employeeRequests.get(0).setEmployee(getByPkId(Employee.class, employeeRequests.get(0).getEmployeePkId()));
			return employeeRequests.get(0);
		}
		
		return null;
	}

}

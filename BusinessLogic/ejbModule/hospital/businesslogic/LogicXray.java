package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicXrayLocal;
import hospital.entity.*;

import javax.ejb.Stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicXray", mappedName = "hospital.businesslogic.LogicXray")
public class LogicXray extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicXray, ILogicXrayLocal {
	@Resource
	SessionContext sessionContext;

	public LogicXray() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<Xray> getXrays() throws Exception {

		List<Xray> list = new ArrayList<Xray>();
		StringBuilder jpql = new StringBuilder();
		jpql.append(
				"SELECT NEW hospital.entity.Xray(a.pkId, a.xrayTypePkId, a.name, a.roomNumber, c.price, c.beginDate,  b.name) ");
		jpql.append("FROM Xray a ");
		jpql.append("INNER JOIN XrayType b ON a.xrayTypePkId = b.pkId ");
		jpql.append("INNER JOIN View_Xray c ON a.pkId = c.xrayPkId ");
		list = getByQuery(Xray.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<XrayType> getXrayTypes(LoggedUser lu) throws Exception {

		List<XrayType> list = new ArrayList<XrayType>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a ");
		jpql.append("FROM XrayType a ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		list = getByQuery(XrayType.class, jpql.toString(), parameters);
		return list;

	}

	@Override
	public void saveXrayType(LoggedUser lu, XrayType xrayType) throws Exception {

		if (Tool.ADDED.equals(xrayType.getStatus())) {
			xrayType.setPkId(Tools.newPkId());
			xrayType.setOrganizationPkId(lu.getUser().getOrganizationPkId());
			insert(xrayType);
		} else if (Tool.MODIFIED.equals(xrayType.getStatus())) {
			update(xrayType);
		} else if (Tool.DELETE.equals(xrayType.getStatus())) {
			deleteByPkId(XrayType.class, xrayType.getPkId());
		}

	}

	@Override
	public void saveXray(LoggedUser lu, Xray xray, List<XrayEmployeeMap> xrayEmployeeList) throws Exception {

		if (Tool.ADDED.equals(xray.getStatus())) {
			xray.setPkId(Tools.newPkId());
			insert(xray);
			XrayPrice xp = new XrayPrice();
			xp.setPkId(Tools.newPkId());
			xp.setPrice(xray.getPriceIn());
			xp.setUpdatedBy(lu.getUser().getPkId());
			xp.setCreatedBy(lu.getUser().getPkId());
			xp.setCreatedDate(new Date());
			xp.setUpdatedDate(new Date());
			xp.setBeginDate(new Date());
			xp.setXrayPkId(xray.getPkId());
			insert(xp);
			for (XrayEmployeeMap map : xrayEmployeeList) {
				map.setXrayPkId(xray.getPkId());
				map.setPkId(Tools.newPkId());
				insert(map);
			}
		} else if (Tool.MODIFIED.equals(xray.getStatus())) {
			update(xray);
			XrayPrice xp = new XrayPrice();
			xp.setPkId(Tools.newPkId());
			xp.setPrice(xray.getPriceIn());
			xp.setUpdatedBy(lu.getUser().getPkId());
			xp.setCreatedBy(lu.getUser().getPkId());
			xp.setCreatedDate(new Date());
			xp.setUpdatedDate(new Date());
			xp.setBeginDate(xray.getPriceInUsageDate());
			xp.setXrayPkId(xray.getPkId());
			insert(xp);
			deleteByAnyField(XrayEmployeeMap.class, "xrayPkId", xray.getPkId());
			for (XrayEmployeeMap map : xrayEmployeeList) {
				map.setXrayPkId(xray.getPkId());
				map.setPkId(Tools.newPkId());
				insert(map);
			}
		} else if (Tool.DELETE.equals(xray.getStatus())) {
			deleteByPkId(Xray.class, xray.getPkId());
			deleteByAnyField(XrayEmployeeMap.class, "xrayPkId", xray.getPkId());
		}

	}

	public List<Xray> getXrayByEmployeePkId(BigDecimal customerPkId, LoggedUser loggedUser) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT NEW hospital.entity.Xray(d.pkId, d.xrayTypePkId, d.name, d.roomNumber, f.price, f.beginDate, d.isActive, d.createdDate, d.createdBy, d.updatedDate, d.updatedBy, a.pkId) FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Inspection b ON a.pkId = b.requestPkId ");
		jpql.append("INNER JOIN InspectionDtl c ON b.pkId = c.inspectionPkId ");
		jpql.append("INNER JOIN Xray d ON c.xrayPkId = d.pkId ");
		jpql.append("INNER JOIN View_Xray f ON f.xrayPkId = d.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId AND (a.mood = 2 OR a.mood = 4) ");
		jpql.append("AND a.pkId NOT IN (SELECT e.employeeRequestPkId FROM EmployeeRequestPaymentMap e ) ");
		List<Xray> xrays = getByQuery(Xray.class, jpql.toString(), parameters);
		return xrays;
	}

	public List<Xray> getXrayByDianoseTypePkId(BigDecimal xrayTypePkId) throws Exception {
		List<Xray> list = new ArrayList<Xray>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("xrayTypePkId", xrayTypePkId);
		jpql.append(
				"SELECT NEW hospital.entity.Xray(a.pkId, a.id, a.xrayTypePkId, a.name, a.roomNumber, c.price, c.beginDate,  b.name) ");
		jpql.append("FROM Xray a ");
		jpql.append("INNER JOIN XrayType b ON a.xrayTypePkId = b.pkId ");
		jpql.append("INNER JOIN View_Xray c ON a.pkId = c.xrayPkId ");
		jpql.append("WHERE a.xrayTypePkId =:xrayTypePkId ");
		list = getByQuery(Xray.class, jpql.toString(), parameters);
		return list;
	}

	public List<Xray> getXrayByDianose(String xraySearchString) throws Exception {
		List<Xray> list = new ArrayList<Xray>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("xraySearchString", "%" + xraySearchString + "%");
		jpql.append(
				"SELECT NEW hospital.entity.Xray(a.pkId, a.xrayTypePkId, a.name, a.roomNumber, c.price, c.beginDate,  b.name) ");
		jpql.append("FROM Xray a ");
		jpql.append("INNER JOIN XrayType b ON a.xrayTypePkId = b.pkId ");
		jpql.append("INNER JOIN View_Xray c ON a.pkId = c.xrayPkId ");
		jpql.append("WHERE a.id LIKE :xraySearchString ");
		jpql.append("AND a.name LIKE :xraySearchString ");
		list = getByQuery(Xray.class, jpql.toString(), parameters);
		return list;
	}

	public List<Xray> getPaymentXrayByCustomer(BigDecimal customerPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT DISTINCT NEW hospital.entity.Xray(a.pkId, a.name, f.price, b.pkId, e.pkId) ");
		jpql.append("FROM Xray a ");
		jpql.append("INNER JOIN XrayRequest b ON b.xrayPkId = a.pkId ");
		jpql.append("INNER JOIN InspectionDtl c ON a.pkId = c.xrayPkId ");
		jpql.append("INNER JOIN Inspection d ON d.pkId = c.inspectionPkId ");
		jpql.append("INNER JOIN EmployeeRequest e ON e.pkId = d.requestPkId ");
		jpql.append("INNER JOIN View_Xray f ON f.xrayPkId = a.pkId ");
		jpql.append("WHERE e.customerPkId = :customerPkId ");
		jpql.append("AND b.customerPkId = :customerPkId ");
		jpql.append("AND b.employeePkId = e.employeePkId ");
		jpql.append("AND b.paymentPkId IS NULL ");
		jpql.append("AND e.paymentPkId IS NULL ");
		jpql.append("AND e.mood IN (2, 4) ");

		List<Xray> xrays = getByQuery(Xray.class, jpql.toString(), parameters);
		return xrays;
	}

	public List<XrayRequest> getBase64Images() throws Exception {
		List<XrayRequest> list = new ArrayList<XrayRequest>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		jpql.append(
				"SELECT NEW hospital.entity.XrayRequest (a.pkId, a.employeePkId, a.customerPkId, a.xrayPkId, a.image, a.requestDate, b.firstName, b.lastName, b.cardNumber, b.regNumber, b.gender, b.age, c.name, d.firstName, a.requestPkId ) ");
		jpql.append("FROM XrayRequest a ");
		jpql.append("LEFT JOIN ViewCustomer b ON a.customerPkId=b.pkId ");
		jpql.append("LEFT JOIN Xray c ON a.xrayPkId=c.pkId ");
		jpql.append("LEFT JOIN Employee d ON a.employeePkId=d.pkId ");
		jpql.append("WHERE a.image LIKE '%base64%'");
		list = getByQuery(XrayRequest.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<XrayPrice> getXrayPrices(BigDecimal xrayPkId) throws Exception {
		List<XrayPrice> list = new ArrayList<XrayPrice>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("xrayPkId", xrayPkId);

		jpql.append("SELECT NEW hospital.entity.XrayPrice (a.price, a.beginDate, a.updatedDate, b.id, c.name ) ");
		jpql.append("FROM XrayPrice a ");
		jpql.append("INNER JOIN Users b ON a.updatedBy = b.pkId ");
		jpql.append("INNER JOIN Xray c ON a.xrayPkId = c.pkId ");
		jpql.append("WHERE a.xrayPkId = :xrayPkId ");

		list = getByQuery(XrayPrice.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<XrayRequest> getXrayRequestByCustomerPkId(BigDecimal customerPkId) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("customerPkId", customerPkId);

		jpql.append(
				"SELECT NEW hospital.entity.XrayRequest (a.pkId, a.employeePkId, a.customerPkId, a.xrayPkId, a.image,a.description, a.requestDate, b.firstName, b.lastName, b.cardNumber, b.regNumber, b.gender, b.age, c.name, d.firstName, a.requestPkId,b.phoneNumber,d.signature, c.windowType ) ");
		jpql.append("FROM XrayRequest a ");
		jpql.append("LEFT JOIN ViewCustomer b ON a.customerPkId=b.pkId ");
		jpql.append("LEFT JOIN Xray c ON a.xrayPkId=c.pkId ");
		jpql.append("LEFT JOIN Employee d ON a.employeePkId=d.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("AND c.pkId IS NOT NULL ");
		jpql.append("AND b.pkId IS NOT NULL ");
		List<XrayRequest> list = new ArrayList<XrayRequest>();
		list = getByQuery(XrayRequest.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<XrayEmployeeMap> getXrayEmployeeMap(BigDecimal xrayPkId) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("xrayPkId", xrayPkId);

		jpql.append("SELECT A ");
		jpql.append("FROM XrayEmployeeMap A ");
		jpql.append("WHERE A.xrayPkId = :xrayPkId ");

		return getByQuery(XrayEmployeeMap.class, jpql.toString(), parameters);
	}

	@Override
	public List<XrayRequest> getXrayRequests(Date beginDate, Date endDate, String searchKey, boolean isDone,
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
		jpql.append("SELECT NEW hospital.entity.XrayRequest (a, b, c.name,c.windowType) ");
		jpql.append("FROM XrayRequest a ");
		jpql.append("LEFT JOIN Customer b ON a.customerPkId=b.pkId ");
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
	public void saveEndoscope(LoggedUser lu, EndoscopyDetail es, List<CustomerAttachment> ca) throws Exception {
		if (Tool.ADDED.equals(es.getStatus())) {
			es.setPkId(Tools.newPkId());
			for (CustomerAttachment c : ca) {
				c.setPkId(Tools.newPkId());
				c.setInspectionPkId(es.getXrayRequestPkId());
				insert(c);
			}
			es.setInspectedBy(lu.getEmployeePkId());
			es.setSavedDate(new Date());

			insert(es);
		}
		if (Tool.MODIFIED.equals(es.getStatus())) {
			setEntityManager(null);
			es.setInspectedBy(lu.getEmployeePkId());
			es.setSavedDate(new Date());
			if (es.getPkId() != null)
				update(es);
			else {
				es.setPkId(Tools.newPkId());
				insert(es);
			}
			deleteByAnyField(CustomerAttachment.class, "inspectionPkId", es.getXrayRequestPkId());
			for (CustomerAttachment c : ca) {
				c.setPkId(Tools.newPkId());
				c.setInspectionPkId(es.getXrayRequestPkId());
				insert(c);
			}

		}

	}

	@Override
	public EndoscopyDetail getEndoDtl(BigDecimal requestPkId) throws Exception {
		List<EndoscopyDetail> list = new ArrayList<EndoscopyDetail>();
		list = getByAnyField(EndoscopyDetail.class, "xrayRequestPkId", requestPkId);
		if (list.size() >= 1) {
			return list.get(0);
		} else
			return null;

	}

	@Override
	public void saveColonoScopic(BigDecimal pkIds, List<XrayDtl> dtls, List<CustomerAttachment> attachments)
			throws Exception {
		System.out.println("statusss " + pkIds);
		for (XrayDtl dtl : dtls) {
			if (Tool.ADDED.equals(dtl.getStatus())) {
				dtl.setPkId(Tools.newPkId());
				dtl.setXrayRequestPkId(pkIds);
				insert(dtl);
			}
			Date date = new Date();
			for (CustomerAttachment att : attachments) {
				if (Tool.ADDED.equals(dtl.getStatus())) {
					att.setPkId(Tools.newPkId());
					att.setInspectionPkId(pkIds);
					att.setAttachmentType("colono");
					att.setCreatedDate(date);
				}
				insert(att);
			}
			if (Tool.MODIFIED.equals(dtl.getStatus())) {
				System.out.println("modifieds " + dtl.getStatus());
				update(dtl);
			}
		}
	}

	@Override
	public Ultrasound getUltrasound(BigDecimal requestPkId) throws Exception {
		List<Ultrasound> list = new ArrayList<Ultrasound>();
		list = getByAnyField(Ultrasound.class, "xrayRequestPkId", requestPkId);
		if (list.size() >= 1) {
			return list.get(0);
		} else
			return null;

	}

	@Override
	public void saveUltrasound(LoggedUser lu, Ultrasound us) throws Exception {
		Date d = new Date();
		if (us.getStatus().equals(Tool.ADDED)) {
			us.setPkId(Tools.newPkId());
			us.setSavedby(lu.getEmployeePkId());
			us.setSavedDate(d);
			insert(us);
		} else if (us.getStatus().equals(Tool.MODIFIED)) {
			us.setSavedby(lu.getEmployeePkId());
			us.setSavedDate(d);
			insert(us);

		}
	}

	@Override
	public XrayDtl getListXrayDtl(BigDecimal pkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("pkId", pkId);

		StringBuilder builder = new StringBuilder();
		builder.append("SELECT A FROM  XrayDtl  A ");
		builder.append("WHERE A.xrayRequestPkId= :pkId ");
		List<XrayDtl> list = getByQuery(XrayDtl.class, builder.toString(), map);
		if (list.size() >= 1)
			return list.get(0);
		else
			return null;

	}

	@Override
	public XrayRequest getXrayRequestByCustomer(BigDecimal customerPkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("customerPkId", customerPkId);
		StringBuilder builder = new StringBuilder();
		XrayRequest xray = new XrayRequest();
		builder.append(
				"SELECT NEW  hospital.entity.XrayRequest(B.lastName,B.firstName,B.regNumber,C.firstName,C.signature ) ");
		builder.append("FROM XrayRequest A ");
		builder.append("INNER JOIN ViewCustomer B ON A.customerPkId =B.pkId ");
		builder.append("INNER JOIN Employee  C  ON A.xrayEmployeePkId  =  C.pkId ");
		builder.append("WHERE  A.customerPkId = :customerPkId ");
		List<XrayRequest> list = getByQuery(XrayRequest.class, builder.toString(), map);
		if (list.size() >= 0) {
			xray = list.get(0);
		}
		return xray;
	}

	@Override
	public List<EndoTemplate> getTemplates() throws Exception {
		return getAll(EndoTemplate.class);
	}

	@Override
	public void saveTemplate(EndoTemplate endoTemplate, LoggedUser lu) throws Exception {
		Date now = new Date();
		if (Tool.ADDED.equals(endoTemplate.getStatus())) {
			endoTemplate.setPkId(Tools.newPkId());
			endoTemplate.setCreatedDate(now);
			endoTemplate.setCreatedBy(lu.getEmployeePkId());
			endoTemplate.setUpdatedBy(lu.getEmployeePkId());
			endoTemplate.setUpdatedDate(now);
			insert(endoTemplate);
		} else if (Tool.ADDED.equals(endoTemplate.getStatus())) {
			endoTemplate.setUpdatedBy(lu.getEmployeePkId());
			update(endoTemplate);
			endoTemplate.setUpdatedDate(now);
		} else if (Tool.DELETE.equals(endoTemplate.getStatus())) {
			if (endoTemplate.getPkId() != null)
				deleteByPkId(EndoTemplate.class, endoTemplate.getPkId());
		}

	}
}
package hospital.businesslogic;

import java.math.BigDecimal;
import java.security.Policy.Parameters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicConditionalPrescriptionLocal;
import hospital.entity.ConditionalPrescription;
import hospital.entity.ConditionalPrescriptionDtl;
import hospital.entity.ConditionalPrescriptionSubOrganizationTypeMap;
import hospital.entity.CustomerMedicine;
import hospital.entity.ElementPrice;
import hospital.entity.Employee;
import hospital.entity.EmployeeRequest;
import hospital.entity.EmployeeRequestHistory;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationPrice;
import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import hospital.entity.SubOrganization;
import hospital.entity.SubOrganizationType;
import hospital.entity.SurgeryPrice;
import hospital.entity.Treatment;
import hospital.entity.TreatmentPrice;
import hospital.entity.XrayPrice;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicConditionalPrescription", mappedName = "hospital.businesslogic.LogicConditionalPrescription")
public class LogicConditionalPrescription extends logic.SuperBusinessLogic
		implements
		hospital.businesslogic.interfaces.ILogicConditionalPrescription,
		ILogicConditionalPrescriptionLocal {

	@Resource
	SessionContext sessionContext;

	public LogicConditionalPrescription() {

	}

	@Override
	public List<ConditionalPrescription> getCps(LoggedUser lu) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ConditionalPrescription> list = new ArrayList<ConditionalPrescription>();
		jpql.append("SELECT NEW hospital.entity.ConditionalPrescription(a.pkId, a.id, a.statementId, a.name, a.price, a.usageDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, a.cost, COUNT(b.pkId)) ");
		jpql.append("FROM ConditionalPrescription a ");
		jpql.append("INNER JOIN ConditionalPrescriptionDtl b ON a.pkId = b.conditionalPrescriptionPkId  ");
		jpql.append("GROUP BY a.pkId, a.id, a.statementId, a.name, a.price, a.usageDate, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, a.cost ");
		list = getByQuery(ConditionalPrescription.class, jpql.toString(),
				parameters);
		return list;
	}
	
	@Override
	public String getSubOrganizationNameByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		Inspection inspection = getByPkId(Inspection.class, inspectionPkId);
		if(inspection != null){
			EmployeeRequest employeeRequest = getByPkId(EmployeeRequest.class, inspection.getRequestPkId());
			BigDecimal employeePkId = null;
			if(employeeRequest != null){
				employeePkId = employeeRequest.getEmployeePkId();
			}else {
				EmployeeRequestHistory employeeRequestHistory = getByPkId(EmployeeRequestHistory.class, inspection.getRequestPkId());
				if(employeeRequestHistory != null){
					employeePkId = employeeRequestHistory.getEmployeePkId();
				}
			}
			if(employeePkId != null) {
				Employee employee = getByPkId(Employee.class, employeePkId);
				if(employee != null) {
					SubOrganization organization = getByPkId(SubOrganization.class, employee.getSubOrganizationPkId());
					if(organization != null) return organization.getName();
				}
			}
		}
		return "";
	}
	
	@Override
	public String getEmployeeNameByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		Inspection inspection = getByPkId(Inspection.class, inspectionPkId);
		if(inspection != null){
			EmployeeRequest employeeRequest = getByPkId(EmployeeRequest.class, inspection.getRequestPkId());
			BigDecimal employeePkId = null;
			if(employeeRequest != null){
				employeePkId = employeeRequest.getEmployeePkId();
			}else {
				EmployeeRequestHistory employeeRequestHistory = getByPkId(EmployeeRequestHistory.class, inspection.getRequestPkId());
				if(employeeRequestHistory != null){
					employeePkId = employeeRequestHistory.getEmployeePkId();
				}
			}
			if(employeePkId != null) {
				Employee employee = getByPkId(Employee.class, employeePkId);
				if(employee != null) {
					return employee.getFirstName();
				}
			}
		}
		return "";
	}

	@Override
	public List<ConditionalPrescriptionDtl> getDtls(BigDecimal cpPkId)
			throws Exception {
		List<ConditionalPrescriptionDtl> list = new ArrayList<ConditionalPrescriptionDtl>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("cpPkId", cpPkId);
		jpql.append("SELECT NEW hospital.entity.ConditionalPrescriptionDtl(a.pkId, a.conditionalPrescriptionPkId, a.examinationPkId, a.medicinePkId, a.xrayPkId, a.diagnosePkId, a.repeatType, a.repeatCount, a.dose, a.expireDay, a.description, a.cost, g.id, z.name, g.name, c.name, f.name, d.nameMn, d.nameEn, d.nameRu, d.id, a.m ,a.d ,a.e ,a.n ,a.time ,a.day) ");
		jpql.append("FROM ConditionalPrescriptionDtl a ");
		jpql.append("LEFT JOIN ConditionalPrescription b ON a.conditionalPrescriptionPkId = b.pkId ");
		jpql.append("LEFT JOIN Examination c ON c.pkId = a.examinationPkId ");
		jpql.append("LEFT JOIN Diagnose d ON d.pkId = a.diagnosePkId ");
		jpql.append("LEFT JOIN Xray f ON f.pkId = a.xrayPkId ");
		jpql.append("LEFT JOIN Medicine g ON  g.pkId = a.medicinePkId ");
		jpql.append("LEFT JOIN View_ConstantMedicineType z ON z.pkId = g.typePkId ");
		jpql.append("WHERE a.conditionalPrescriptionPkId =:cpPkId  ");
		list = getByQuery(ConditionalPrescriptionDtl.class, jpql.toString(),
				parameters);
		return list;
	}

	@Override
	public void saveCp(ConditionalPrescription conditionalPrescription,
			LoggedUser lu, List<ConditionalPrescriptionDtl> dtls,
			List<ConditionalPrescriptionSubOrganizationTypeMap> maps)
			throws Exception {
		Date now = new Date();
		if (conditionalPrescription.getStatus().equals(Tool.ADDED)) {
			conditionalPrescription.setPkId(Tools.newPkId());
			conditionalPrescription.setCreatedBy(lu.getUser().getPkId());
			conditionalPrescription.setUpdatedBy(lu.getUser().getPkId());
			conditionalPrescription.setUpdatedDate(now);
			conditionalPrescription.setCreatedDate(now);
			if (dtls != null)
				for (ConditionalPrescriptionDtl dtl : dtls) {
					dtl.setPkId(Tools.newPkId());
					dtl.setConditionalPrescriptionPkId(conditionalPrescription
							.getPkId());

				}
			if (maps != null)
				for (ConditionalPrescriptionSubOrganizationTypeMap m : maps) {
					m.setPkId(Tools.newPkId());
					m.setConditionalPrescriptionPkId(conditionalPrescription
							.getPkId());
				}
			insert(conditionalPrescription);
			insert(dtls);
			insert(maps);
		} else if (conditionalPrescription.getStatus().equals(Tool.MODIFIED)) {
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("cpPkId", conditionalPrescription.getPkId());
			deleteByAnyField(ConditionalPrescriptionDtl.class,
					"conditionalPrescriptionPkId",
					conditionalPrescription.getPkId());
			deleteByAnyField(
					ConditionalPrescriptionSubOrganizationTypeMap.class,
					"conditionalPrescriptionPkId",
					conditionalPrescription.getPkId());
			if (dtls != null)
				for (ConditionalPrescriptionDtl dtl : dtls) {
					dtl.setPkId(Tools.newPkId());
					dtl.setConditionalPrescriptionPkId(conditionalPrescription
							.getPkId());

				}
			if (maps != null)
				for (ConditionalPrescriptionSubOrganizationTypeMap m : maps) {
					m.setPkId(Tools.newPkId());
					m.setConditionalPrescriptionPkId(conditionalPrescription
							.getPkId());
				}
			conditionalPrescription.setUpdatedBy(lu.getUser().getPkId());
			conditionalPrescription.setUpdatedDate(now);
			update(conditionalPrescription);
			insert(dtls);
			insert(maps);
		} else if (conditionalPrescription.getStatus().equals(Tool.DELETE)) {
			deleteByAnyField(ConditionalPrescriptionDtl.class,
					"conditionalPrescriptionPkId",
					conditionalPrescription.getPkId());
			deleteByAnyField(
					ConditionalPrescriptionSubOrganizationTypeMap.class,
					"conditionalPrescriptionPkId",
					conditionalPrescription.getPkId());
			deleteByPkId(ConditionalPrescription.class,
					conditionalPrescription.getPkId());
			
			
		}

	}

	@Override
	public String getCpId() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.id FROM ConditionalPrescription a ");
		jpql.append("ORDER BY a.id DESC ");

		List<String> list = getByQuery(String.class, jpql.toString(), null, 0,
				1);
		String lastCardNumber = (list == null || list.size() < 1) ? "0" : list
				.get(0);
		int cardNumber = Integer.parseInt(lastCardNumber);
		cardNumber++;
		return getCusStringtomerCardNumberString(cardNumber);
	}

	public String getCusStringtomerCardNumberString(int cardNumber) {
		String lastCardNumber = "";
		if (cardNumber < 10000000)
			lastCardNumber += "0";
		if (cardNumber < 1000000)
			lastCardNumber += "0";
		if (cardNumber < 100000)
			lastCardNumber += "0";
		if (cardNumber < 10000)
			lastCardNumber += "0";
		if (cardNumber < 1000)
			lastCardNumber += "0";
		if (cardNumber < 100)
			lastCardNumber += "0";
		if (cardNumber < 10)
			lastCardNumber += "0";
		lastCardNumber += "" + cardNumber;
		return lastCardNumber;
	}

	@Override
	public List<ConditionalPrescriptionSubOrganizationTypeMap> getMaps(
			BigDecimal cpPkId) throws Exception {
		List<ConditionalPrescriptionSubOrganizationTypeMap> list = new ArrayList<ConditionalPrescriptionSubOrganizationTypeMap>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("cpPkId", cpPkId);
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");

		return getByAnyField(
				ConditionalPrescriptionSubOrganizationTypeMap.class,
				"conditionalPrescriptionPkId", cpPkId);
	}
	
	public Date getInspectionBeginDate(BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT a.inspectionDate FROM Inspection a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("ORDER BY a.inspectionDate ASC ");
		List<Date> dates = getByQuery(Date.class, jpql.toString(), parameters);
		if(dates.size() > 0) return dates.get(0);
		return new Date();
	}
	
	public Date getInspectionEndDate(BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT a.inspectionDate FROM Inspection a ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("ORDER BY a.inspectionDate DESC ");
		List<Date> dates = getByQuery(Date.class, jpql.toString(), parameters);
		if(dates.size() > 0) return dates.get(0);
		return new Date();
	}
	
	public List<Inspection> getInspectionByDate(BigDecimal subOrganizationPkId, BigDecimal employeePkId, BigDecimal customerPkId, Date beginDate, Date endDate) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("subOrganizationPkId", subOrganizationPkId);
		parameters.put("employeePkId", employeePkId);
		parameters.put("customerPkId", customerPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		
		jpql.append("SELECT NEW hospital.entity.Inspection(a.pkId, c.name, b.firstName, a.inspectionDate) FROM Inspection a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN SubOrganization c ON b.subOrganizationPkId = c.pkId ");
		jpql.append("WHERE a.customerPkId = :customerPkId ");
		jpql.append("AND a.inspectionDate BETWEEN :beginDate AND :endDate ");
		jpql.append("AND ((a.pkId IN (SELECT DISTINCT asd1.inspectionPkId FROM InspectionDtl asd1)) ");
		jpql.append("OR (a.pkId IN (SELECT DISTINCT asd2.inspectionPkId FROM CustomerMedicine asd2))) ");
		if(BigDecimal.ZERO.compareTo(subOrganizationPkId) != 0)
			jpql.append("AND b.subOrganizationPkId = :subOrganizationPkId ");
		if(BigDecimal.ZERO.compareTo(employeePkId) != 0)
			jpql.append("AND a.employeePkId = :employeePkId ");
		jpql.append("ORDER BY a.inspectionDate DESC");
		
		List<Inspection> inspections = getByQuery(Inspection.class, jpql.toString(), parameters);
		for (Inspection inspection : inspections) {
			parameters.put("inspectionPkId", inspection.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.InspectionDtl(a.type, a.typePkId, b.id, b.name, c.id, c.name, d.name, d.name, e.id, e.name) FROM InspectionDtl a ");
			jpql.append("LEFT JOIN Treatment b ON a.typePkId = b.pkId ");
			jpql.append("LEFT JOIN Xray c ON a.typePkId = c.pkId ");
			jpql.append("LEFT JOIN Surgery d ON a.typePkId = d.pkId ");
			jpql.append("LEFT JOIN Examination e ON a.typePkId = e.pkId ");
			jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
			
			inspection.setDtls(getByQuery(InspectionDtl.class, jpql.toString(), parameters));
		}
		
		return inspections;
	}
	
	public List<InspectionDtl> getExaminationInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_EXAMINATION);
		
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(b.pkId, b.id, b.name, 1) FROM InspectionDtl a ");
		jpql.append("INNER JOIN Examination b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		
		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}
	
	public List<InspectionDtl> getXrayInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_XRAY);
		
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(b.pkId, b.id, b.name, 4) FROM InspectionDtl a ");
		jpql.append("INNER JOIN Xray b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		
		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}
	
	public List<InspectionDtl> getSurgeryInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_SURGERY);
		
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(b.pkId, b.name, b.name, 2) FROM InspectionDtl a ");
		jpql.append("INNER JOIN Surgery b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		
		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}
	
	public List<InspectionDtl> getTreatmentInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		parameters.put("type", Tool.INSPECTIONTYPE_TREATMENT);
		
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(b.pkId, b.id, b.name, 3) FROM InspectionDtl a ");
		jpql.append("INNER JOIN Treatment b ON a.typePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		
		return getByQuery(InspectionDtl.class, jpql.toString(), parameters);
	}
	
	public List<CustomerMedicine> getMedicineInspectionDtlByInspectionPkId(BigDecimal inspectionPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		
		jpql.append("SELECT NEW hospital.entity.CustomerMedicine(a.medicinePkId, a.employeePkId, b.id, b.name) FROM CustomerMedicine a ");
		jpql.append("INNER JOIN Medicine b ON a.medicinePkId = b.pkId ");
		jpql.append("WHERE a.inspectionPkId = :inspectionPkId ");
		
		return getByQuery(CustomerMedicine.class, jpql.toString(), parameters);
	}
	
	public Treatment getTreatment(String type, String str) throws Exception{
		
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("str", str);
		
		jpql.append("SELECT a FROM Treatment a ");
		
		if("id".equals(type)) {
			jpql.append("WHERE a.id = :str ");
		}else if("name".equals(type)) {
			jpql.append("WHERE a.name = :str ");
		}
		
		List<Treatment> treatments = getByQuery(Treatment.class, jpql.toString(), parameters);
		if(treatments.size() < 1) return null;
		parameters.put("treatmentPkId", treatments.get(0).getPkId());
		parameters.put("dte", new Date());
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM TreatmentPrice a ");
		jpql.append("WHERE a.treatmentPkId = :treatmentPkId ");
		jpql.append("AND a.beginDate < :dte ");
		jpql.append("ORDER BY a.beginDate DESC ");
		
		List<TreatmentPrice> treatmentPrices = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);
		if(treatmentPrices.size() < 1) treatments.get(0).setCost(BigDecimal.ZERO);
		treatments.get(0).setCost(treatmentPrices.get(0).getPrice());
		
		return treatments.get(0);
	}
	
	public List<SubOrganizationType> getOrganizationTypes() throws Exception{
		return getAll(SubOrganizationType.class);
	}
	
	public List<ConditionalPrescription> getConditionalPrescriptions(BigDecimal subOrganizationTypePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		jpql.append("SELECT a FROM ConditionalPrescription a ");
		if(subOrganizationTypePkId.compareTo(BigDecimal.ZERO) != 0){
			parameters.put("subOrganizationTypePkId", subOrganizationTypePkId);
			jpql.append("INNER JOIN ConditionalPrescriptionSubOrganizationTypeMap b ON a.pkId = b.conditionalPrescriptionPkId ");
			jpql.append("WHERE b.subOrganizationTypePkId = :subOrganizationTypePkId ");
		}
		List<ConditionalPrescription> conditionalPrescriptions = getByQuery(ConditionalPrescription.class, jpql.toString(), parameters);
		for (ConditionalPrescription conditionalPrescription : conditionalPrescriptions) {
			parameters.put("conditionalPrescriptionPkId", conditionalPrescription.getPkId());
			
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.ConditionalPrescriptionDtl(a, b.nameMn, c.name, d.name, e.name) ");
			jpql.append("FROM ConditionalPrescriptionDtl a ");
			jpql.append("LEFT JOIN Diagnose b ON a.diagnosePkId = b.pkId ");
			jpql.append("LEFT JOIN Medicine c ON a.medicinePkId = c.pkId ");
			jpql.append("LEFT JOIN Examination d ON a.examinationPkId = d.pkId ");
			jpql.append("LEFT JOIN Xray e ON a.xrayPkId = e.pkId ");
			jpql.append("WHERE a.conditionalPrescriptionPkId = :conditionalPrescriptionPkId ");
			
			conditionalPrescription.setDtls(getByQuery(ConditionalPrescriptionDtl.class, jpql.toString(), parameters));
		}
		
		return conditionalPrescriptions;
	}
	
	public BigDecimal getSubOrganizationPkIdByEmployeePkId(BigDecimal employeePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("pkId", employeePkId);
		jpql.append("SELECT DISTINCT c FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b ON a.subOrganizationPkId = b.pkId ");
		jpql.append("INNER JOIN SubOrganizationType c ON b.subOrganizationTypePkId = c.pkId ");
		jpql.append("WHERE a.pkId = :pkId ");
		List<SubOrganizationType> list = getByQuery(SubOrganizationType.class, jpql.toString(), parameters);
		if(list.size() > 0) return list.get(0).getPkId();
		return BigDecimal.ZERO;
	}
	
	public BigDecimal examinationDtlPrice(BigDecimal examinationPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);
		parameters.put("dte", new Date());
		jpql.append("SELECT a FROM ExaminationPrice a ");
		jpql.append("WHERE a.examinationPkId = :examinationPkId ");
		jpql.append("AND a.beginDate < :dte ");
		jpql.append("ORDER BY a.beginDate DESC ");
		List<ExaminationPrice> examinationPrices = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
		if(examinationPrices.size() > 0) {
			return examinationPrices.get(0).getPrice();
		}
		return BigDecimal.ZERO;
	}
	
	public List<ExaminationDtl> getExaminationDtlsByExaminationPkId(BigDecimal examinationPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ExaminationDtl> dtls = new ArrayList<ExaminationDtl>();
		
		parameters.put("dte", new Date());
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT NEW hospital.entity.ExaminationDtl(a, b) FROM ExaminationDtl a ");
		jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
		jpql.append("WHERE a.examinationPkId = :examinationPkId ");
		
		dtls = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
		
		for (ExaminationDtl examinationDtl : dtls) {
			parameters.put("elementPkId", examinationDtl.getElementPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM ElementPrice a ");
			jpql.append("WHERE a.elementPkId = :elementPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
			examinationDtl.setElementPricePkId(BigDecimal.ZERO);
			examinationDtl.setPrice(BigDecimal.ZERO);
			if(elementPrices.size() > 0){
				examinationDtl.setElementPricePkId(elementPrices.get(0).getPkId());
				examinationDtl.setPrice(elementPrices.get(0).getPrice());
			}
		}
		
		return dtls;
	}
	
	public BigDecimal getCost(String type, BigDecimal typePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("typePkId", typePkId);;
		parameters.put("dte", new Date());
		if(Tool.INSPECTIONTYPE_XRAY.equals(type)){
			jpql.append("SELECT a FROM XrayPrice a ");
			jpql.append("WHERE a.xrayPkId = :typePkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			
			List<XrayPrice> list = getByQuery(XrayPrice.class, jpql.toString(), parameters);
			if(list.size() > 0) return list.get(0).getPrice();
		}
		
		if(Tool.INSPECTIONTYPE_SURGERY.equals(type)){
			jpql.append("SELECT a FROM SurgeryPrice a ");
			jpql.append("WHERE a.surgeryPkId = :typePkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			
			List<SurgeryPrice> list = getByQuery(SurgeryPrice.class, jpql.toString(), parameters);
			if(list.size() > 0) return list.get(0).getPrice();
		}
		
		return BigDecimal.ZERO;
	}

}

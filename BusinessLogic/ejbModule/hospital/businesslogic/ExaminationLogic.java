package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.ExaRequest;
import hospital.businessentity.ExaminationResults;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.entity.Customer;
import hospital.entity.Element;
import hospital.entity.ElementPrice;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationDoctor;
import hospital.entity.ExaminationDtl;
import hospital.entity.ExaminationEmployeeMap;
import hospital.entity.ExaminationGroup;
import hospital.entity.ExaminationGroupDtl;
import hospital.entity.ExaminationLaborant;
import hospital.entity.ExaminationPrice;
import hospital.entity.ExaminationRequest;
import hospital.entity.ExaminationRequestActive;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.ExaminationRequestTempSave;
import hospital.entity.ExaminationTemplate;
import hospital.entity.ExaminationType;
import hospital.entity.ExaminationValueAnswer;
import hospital.entity.ExaminationValueHdr;
import hospital.entity.ExaminationValueQuestion;
import hospital.entity.Measurement;
import hospital.entity.Organization;
import hospital.entity.TreatmentDtl;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "ExaminationLogic", mappedName = "hospital.businesslogic.ExaminationLogic")
public class ExaminationLogic extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicExamination, ILogicExaminationLocal {
	@Resource
	SessionContext sessionContext;

	public ExaminationLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<ExaminationType> getExaminationTypeList() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM ExaminationType a ");
		// jpql.append("INNER JOIN Examination b ON a.pkId =
		// b.examinationTypePkId ");
		return getByQuery(ExaminationType.class, jpql.toString(), null);
	}

	@Override
	public List<Employee> getExaminationEmployeeList(int type) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("type", type);
		parameters.put("sotType", Tool.LABORATORY);
		jpql.append("SELECT a ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b ON b.pkId = a.subOrganizationPkId ");
		jpql.append("INNER JOIN SubOrganizationType c ON b.subOrganizationTypePkId = c.pkId ");
		jpql.append("WHERE a.isInspect =:type ");
		jpql.append("AND c.type =:sotType ");

		return getByQuery(Employee.class, jpql.toString(), parameters);
	}

	@Override
	public void saveExaminationType(ExaminationType examinationType) throws Exception {
		if (Tool.ADDED.equals(examinationType.getStatus())) {
			examinationType.setPkId(Tools.newPkId());
			insert(examinationType);
		} else if (Tool.MODIFIED.equals(examinationType.getStatus())) {
			update(examinationType);
		} else if (Tool.DELETE.equals(examinationType.getStatus())) {
			deleteByPkId(ExaminationType.class, examinationType.getPkId());
		}
	}

	@Override
	public void saveExamination(Examination examination, List<ExaminationDtl> examinationDtls,
			ExaminationPrice examinationPrice, List<ExaminationLaborant> laborants, List<ExaminationDoctor> doctors,
			LoggedUser lu) throws Exception {

		Date now = new Date();

		if (Tool.ADDED.equals(examination.getStatus())) {
			examination.setPkId(Tools.newPkId());
			examination.setCreatedBy(lu.getUser().getPkId());
			examination.setUpdatedBy(lu.getUser().getPkId());
			examination.setUpdatedDate(now);
			examination.setCreatedDate(now);
			insert(examination);
			if (examination.isHasDtl()) {
				examination.setExaminationTemplatePkId(examination.getPkId());
				ExaminationTemplate et = new ExaminationTemplate();
				et.setPkId(examination.getPkId());
				et.setName(examination.getName());
				insert(et);

				for (ExaminationDtl examinationDtl : examinationDtls) {
					// Element -g examinationDtl -s salgaj baina
					StringBuilder jpql = new StringBuilder();
					CustomHashMap parameters = new CustomHashMap();
					parameters.put("nameMn", examinationDtl.getElementNameMn());
					parameters.put("nameEn", examinationDtl.getElementNameEn());
					parameters.put("measurementPkId", examinationDtl.getMeasurementPkId());

					jpql.append("SELECT a ");
					jpql.append("FROM Element a ");
					jpql.append("WHERE a.nameMn = :nameMn ");
					jpql.append("AND a.nameEn = :nameEn AND a.measurementPkId = :measurementPkId ");

					List<Element> e = new ArrayList<Element>();
					e = getByQuery(Element.class, jpql.toString(), parameters);
					Element element = new Element();
					if (e.size() < 1) {
						element.setPkId(Tools.newPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
						element.setCreatedBy(lu.getUser().getPkId());
						element.setCreatedDate(now);
						element.setUpdatedBy(lu.getUser().getPkId());
						element.setUpdatedDate(now);
						insert(element);
					} else {
						element.setPkId(e.get(0).getPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
						update(element);
					}
					List<ElementPrice> ep = new ArrayList<ElementPrice>();
					ep = getByAnyField(ElementPrice.class, "elementPkId", element.getPkId());
					ElementPrice elementPrice = new ElementPrice();
					if (ep.size() < 1) {
						elementPrice.setPkId(Tools.newPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setCreatedBy(lu.getUser().getPkId());
						elementPrice.setCreatedDate(now);
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						insert(elementPrice);
					} else {
						elementPrice.setPkId(ep.get(0).getPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						update(elementPrice);
					}

					ExaminationValueQuestion evq = new ExaminationValueQuestion();
					evq.setPkId(element.getPkId());
					evq.setExaminationTemplatePkId(examination.getPkId());
					evq.setName(examinationDtl.getElementNameEn());
					evq.setMaxValue(examinationDtl.getMaxValue().toString());
					evq.setMinValue(examinationDtl.getMinValue().toString());

					if (e.size() < 1) {
						insert(evq);
						ExaminationValueAnswer eva = new ExaminationValueAnswer();
						eva.setPkId(Tools.newPkId());
						eva.setQuestionPkId(evq.getPkId());
						eva.setAnswerType(Tool.INPUT);
						Measurement qq = getByPkId(Measurement.class, element.getMeasurementPkId());
						eva.setMeasurement(qq.getName());
						eva.setAnswerLabel(examinationDtl.getMinValue() + " - " + examinationDtl.getMaxValue());
						insert(eva);

					} else {
						update(evq);
					}
					examinationDtl.setPkId(Tools.newPkId());
					examinationDtl.setExaminationPkId(examination.getPkId());
					examinationDtl.setElementPkId(element.getPkId());
					insert(examinationDtl);
				}
			}
			List<ExaminationEmployeeMap> maps = new ArrayList<ExaminationEmployeeMap>();
			deleteByAnyField(ExaminationEmployeeMap.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationLaborant.class, "examinationPkId", examination.getPkId());
			for (ExaminationLaborant laborant : laborants) {
				laborant.setPkId(Tools.newPkId());
				laborant.setExaminationPkId(examination.getPkId());
				ExaminationEmployeeMap eem = new ExaminationEmployeeMap();
				eem.setPkId(laborant.getPkId());
				eem.setExaminationPkId(examination.getPkId());
				eem.setEmployeePkId(laborant.getEmployeePkId());
				eem.setType(0);
				maps.add(eem);
			}
			for (ExaminationDoctor doctor : doctors) {

				doctor.setPkId(Tools.newPkId());
				doctor.setExaminationPkId(examination.getPkId());
				ExaminationEmployeeMap eem = new ExaminationEmployeeMap();
				eem.setPkId(doctor.getPkId());
				eem.setExaminationPkId(examination.getPkId());
				eem.setEmployeePkId(doctor.getEmployeePkId());
				eem.setType(1);
				maps.add(eem);
			}
			if (!examination.isHasDtl()) {
				examinationPrice.setPkId(Tools.newPkId());
				examinationPrice.setExaminationPkId(examination.getPkId());
				insert(examinationPrice);
			}
			insert(maps);
			insert(laborants);
			insert(doctors);
		} else if (Tool.MODIFIED.equals(examination.getStatus())) {
			examination.setUpdatedBy(lu.getUser().getPkId());
			examination.setUpdatedDate(now);
			update(examination);
			if (examination.isHasDtl()) {
				deleteByAnyField(ExaminationTemplate.class, "pkId", examination.getPkId());
				examination.setExaminationTemplatePkId(examination.getPkId());
				ExaminationTemplate et = new ExaminationTemplate();
				et.setPkId(examination.getPkId());
				et.setName(examination.getName());
				insert(et);
			}
			for (ExaminationDtl examinationDtl : examinationDtls) {
				if (Tool.ADDED.equals(examinationDtl.getStatus())) {
					// Element-g examinationDtl-s salgaj bn
					StringBuilder jpql = new StringBuilder();
					CustomHashMap parameters = new CustomHashMap();
					parameters.put("nameMn", examinationDtl.getElementNameMn());
					parameters.put("nameEn", examinationDtl.getElementNameEn());
					parameters.put("measurementPkId", examinationDtl.getMeasurementPkId());

					jpql.append("SELECT a ");
					jpql.append("FROM Element a ");
					jpql.append("WHERE a.nameMn = :nameMn ");
					jpql.append("AND a.nameEn = :nameEn AND a.measurementPkId = :measurementPkId ");

					List<Element> e = new ArrayList<Element>();
					e = getByQuery(Element.class, jpql.toString(), parameters);
					Element element = new Element();
					if (e.size() < 1) {
						element.setPkId(Tools.newPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
						element.setCreatedBy(lu.getUser().getPkId());
						element.setCreatedDate(now);
						element.setUpdatedBy(lu.getUser().getPkId());
						element.setUpdatedDate(now);
						insert(element);
					} else {
						element.setPkId(e.get(0).getPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
					}

					ExaminationValueQuestion evq = new ExaminationValueQuestion();
					evq.setPkId(element.getPkId());
					evq.setExaminationTemplatePkId(examination.getPkId());
					evq.setName(examinationDtl.getElementNameEn());
					evq.setMaxValue(examinationDtl.getMaxValue().toString());
					evq.setMinValue(examinationDtl.getMinValue().toString());
					if (e.size() < 1) {
						insert(evq);
						ExaminationValueAnswer eva = new ExaminationValueAnswer();
						eva.setPkId(Tools.newPkId());
						eva.setQuestionPkId(evq.getPkId());
						eva.setAnswerType(Tool.INPUT);
						Measurement qq = getByPkId(Measurement.class, element.getMeasurementPkId());
						eva.setMeasurement(qq.getName());
						eva.setAnswerLabel(examinationDtl.getMinValue() + " - " + examinationDtl.getMaxValue());
						insert(eva);

					} else {
						update(evq);
						deleteByAnyField(ExaminationValueAnswer.class, "questionPkId", evq.getPkId());
						ExaminationValueAnswer eva = new ExaminationValueAnswer();
						eva.setPkId(Tools.newPkId());
						eva.setQuestionPkId(evq.getPkId());
						eva.setAnswerType(Tool.INPUT);
						Measurement qq = getByPkId(Measurement.class, element.getMeasurementPkId());
						eva.setMeasurement(qq.getName());
						eva.setAnswerLabel(examinationDtl.getMinValue() + " - " + examinationDtl.getMaxValue());
						insert(eva);
					}

					List<ElementPrice> ep = new ArrayList<ElementPrice>();
					ep = getByAnyField(ElementPrice.class, "elementPkId", element.getPkId());
					ElementPrice elementPrice = new ElementPrice();
					if (ep.size() < 1) {
						elementPrice.setPkId(Tools.newPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setCreatedBy(lu.getUser().getPkId());
						elementPrice.setCreatedDate(now);
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						insert(elementPrice);
					} else {
						elementPrice.setPkId(ep.get(0).getPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						update(elementPrice);
					}

					examinationDtl.setPkId(Tools.newPkId());
					examinationDtl.setExaminationPkId(examination.getPkId());
					examinationDtl.setElementPkId(element.getPkId());
					insert(examinationDtl);
				} else if (Tool.MODIFIED.equals(examinationDtl.getStatus())) {
					StringBuilder jpql = new StringBuilder();
					CustomHashMap parameters = new CustomHashMap();
					parameters.put("nameMn", examinationDtl.getElementNameMn());
					parameters.put("nameEn", examinationDtl.getElementNameEn());
					parameters.put("measurementPkId", examinationDtl.getMeasurementPkId());

					jpql.append("SELECT a ");
					jpql.append("FROM Element a ");
					jpql.append("WHERE a.nameMn = :nameMn ");
					jpql.append("AND a.nameEn = :nameEn AND a.measurementPkId = :measurementPkId ");

					List<Element> e = new ArrayList<Element>();
					e = getByQuery(Element.class, jpql.toString(), parameters);
					Element element = new Element();
					if (e.size() < 1) {
						element.setPkId(Tools.newPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
						element.setCreatedBy(lu.getUser().getPkId());
						element.setCreatedDate(now);
						element.setUpdatedBy(lu.getUser().getPkId());
						element.setUpdatedDate(now);
						insert(element);
					} else {
						element.setPkId(e.get(0).getPkId());
						element.setNameMn(examinationDtl.getElementNameMn());
						element.setNameEn(examinationDtl.getElementNameEn());
						element.setMeasurementPkId(examinationDtl.getMeasurementPkId());
					}

					ExaminationValueQuestion evq = new ExaminationValueQuestion();
					evq.setPkId(element.getPkId());
					evq.setExaminationTemplatePkId(examination.getPkId());
					evq.setName(examinationDtl.getElementNameEn());
					evq.setMaxValue(examinationDtl.getMaxValue().toString());
					evq.setMinValue(examinationDtl.getMinValue().toString());
					if (e.size() < 1) {
						insert(evq);
						ExaminationValueAnswer eva = new ExaminationValueAnswer();
						eva.setPkId(Tools.newPkId());
						eva.setQuestionPkId(evq.getPkId());
						eva.setAnswerType(Tool.INPUT);
						Measurement qq = getByPkId(Measurement.class, element.getMeasurementPkId());
						eva.setMeasurement(qq.getName());
						eva.setAnswerLabel(examinationDtl.getMinValue() + " - " + examinationDtl.getMaxValue());
						insert(eva);

					} else {
						update(evq);
						deleteByAnyField(ExaminationValueAnswer.class, "questionPkId", evq.getPkId());
						ExaminationValueAnswer eva = new ExaminationValueAnswer();
						eva.setPkId(Tools.newPkId());
						eva.setQuestionPkId(evq.getPkId());
						eva.setAnswerType(Tool.INPUT);
						Measurement qq = getByPkId(Measurement.class, element.getMeasurementPkId());
						eva.setMeasurement(qq.getName());
						eva.setAnswerLabel(examinationDtl.getMinValue() + " - " + examinationDtl.getMaxValue());
						insert(eva);
					}

					List<ElementPrice> ep = new ArrayList<ElementPrice>();
					ep = getByAnyField(ElementPrice.class, "elementPkId", element.getPkId());
					ElementPrice elementPrice = new ElementPrice();
					if (ep.size() < 1) {
						elementPrice.setPkId(Tools.newPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setCreatedBy(lu.getUser().getPkId());
						elementPrice.setCreatedDate(now);
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						insert(elementPrice);
					} else {
						elementPrice.setPkId(ep.get(0).getPkId());
						elementPrice.setPrice(examinationDtl.getPrice());
						elementPrice.setElementPkId(element.getPkId());
						elementPrice.setUpdatedBy(lu.getUser().getPkId());
						elementPrice.setUpdatedDate(now);
						update(elementPrice);
					}

					examinationDtl.setExaminationPkId(examination.getPkId());
					examinationDtl.setElementPkId(element.getPkId());
					update(examinationDtl);

					update(examinationDtl);
				} else if (Tool.DELETE.equals(examinationDtl.getStatus())) {
					deleteByPkId(ExaminationDtl.class, examinationDtl.getPkId());
					deleteByPkId(ExaminationValueQuestion.class, examinationDtl.getElementPkId());
				}
			}
			deleteByAnyField(ExaminationEmployeeMap.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationLaborant.class, "examinationPkId", examination.getPkId());
			setEntityManager(null);
			for (ExaminationLaborant laborant : laborants) {
				laborant.setPkId(Tools.newPkId());
				laborant.setExaminationPkId(examination.getPkId());
				insert(laborant);
				ExaminationEmployeeMap eem = new ExaminationEmployeeMap();
				eem.setPkId(laborant.getPkId());
				eem.setExaminationPkId(examination.getPkId());
				eem.setEmployeePkId(laborant.getEmployeePkId());
				eem.setType(0);
				insert(eem);
			}
			deleteByAnyField(ExaminationDoctor.class, "examinationPkId", examination.getPkId());
			for (ExaminationDoctor doctor : doctors) {
				doctor.setPkId(Tools.newPkId());
				doctor.setExaminationPkId(examination.getPkId());
				ExaminationEmployeeMap eem = new ExaminationEmployeeMap();
				eem.setPkId(doctor.getPkId());
				eem.setExaminationPkId(examination.getPkId());
				eem.setEmployeePkId(doctor.getEmployeePkId());
				eem.setType(1);
				insert(eem);
				insert(doctor);

			}

			if (!examination.isHasDtl())
				if (Tool.ADDED.equals(examinationPrice.getStatus())) {
					examinationPrice.setPkId(Tools.newPkId());
					examinationPrice.setExaminationPkId(examination.getPkId());
					insert(examinationPrice);
				} else if (Tool.MODIFIED.equals(examinationPrice.getStatus())) {
					ExaminationPrice ep = new ExaminationPrice();
					ep.setPkId(Tools.newPkId());
					ep.setPrice(examinationPrice.getPrice());
					ep.setBeginDate(examinationPrice.getBeginDate());
					ep.setCreatedBy(lu.getUser().getPkId());
					ep.setCreatedDate(now);
					ep.setExaminationPkId(examination.getPkId());
					ep.setUpdatedBy(lu.getUser().getPkId());
					ep.setUpdatedDate(now);
					insert(ep);
				} else if (Tool.DELETE.equals(examinationPrice.getStatus())) {
					deleteByPkId(ExaminationPrice.class, examinationPrice.getPkId());
				}
		} else if (Tool.DELETE.equals(examination.getStatus())) {
			deleteByAnyField(ExaminationDtl.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationDoctor.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationLaborant.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationEmployeeMap.class, "examinationPkId", examination.getPkId());
			deleteByPkId(Examination.class, examination.getPkId());

		}
	}

	@Override
	public List<Examination> getExaminationList(BigDecimal examinationTypePkId, boolean isSetPrice) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationTypePkId", examinationTypePkId);

		jpql.append("SELECT NEW hospital.entity.Examination(A, B.name, C.price) FROM Examination A ");
		jpql.append("LEFT JOIN ExaminationType B ON A.examinationTypePkId = B.pkId ");
		jpql.append("LEFT JOIN View_Examination C ON A.pkId = C.examinationPkId ");
		if (!BigDecimal.ZERO.equals(examinationTypePkId))
			jpql.append("WHERE B.pkId = :examinationTypePkId");

		List<Examination> examinations = getByQuery(Examination.class, jpql.toString(), parameters);
		for (Examination examination : examinations) {
			parameters.put("examinationPkId", examination.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.ExaminationDtl(a, b, c) FROM ExaminationDtl a ");
			jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
			jpql.append("INNER JOIN ElementPrice c ON b.pkId = c.elementPkId ");
			jpql.append(" ");
			jpql.append("WHERE a.examinationPkId = :examinationPkId ");

			examination.setExaminationDtls(getByQuery(ExaminationDtl.class, jpql.toString(), parameters));
		}

		return examinations;
	}

	@Override
	public List<Examination> getExaminationList(String examinationSearchString, boolean isSetPrice) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationSearchString", "%" + examinationSearchString + "%");

		jpql.append("SELECT NEW hospital.entity.Examination(A, B.name, C.price) FROM Examination A ");
		jpql.append("LEFT JOIN ExaminationType B ON A.examinationTypePkId = B.pkId ");
		jpql.append("LEFT JOIN View_Examination C ON A.pkId = C.examinationPkId ");
		jpql.append("WHERE A.name LIKE :examinationSearchString ");
		jpql.append("OR A.id LIKE :examinationSearchString ");

		return getByQuery(Examination.class, jpql.toString(), parameters);
	}

	@Override
	public List<ExaminationDtl> getExaminationDtlList(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);

		jpql.append("SELECT NEW hospital.entity.ExaminationDtl(A, B, C) FROM ExaminationDtl A ");
		jpql.append("INNER JOIN Element B ON A.elementPkId = B.pkId ");
		jpql.append("INNER JOIN ElementPrice C ON B.pkId = C.elementPkId ");
		jpql.append("WHERE A.examinationPkId = :examinationPkId ");

		return getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
	}

	@Override
	public ExaminationPrice getExaminationPrice(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT A FROM ExaminationPrice A ");
		jpql.append("WHERE A.examinationPkId = :examinationPkId ");

		return (ExaminationPrice) getByQuerySingle(jpql.toString(), parameters);
	}

	@Override
	public List<ExaminationLaborant> getExaminationLaborantList(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT A FROM ExaminationLaborant A ");
		jpql.append("WHERE A.examinationPkId = :examinationPkId ");

		return getByQuery(ExaminationLaborant.class, jpql.toString(), parameters);
	}

	@Override
	public List<ExaminationDoctor> getExaminationDoctorList(BigDecimal examinationPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT A FROM ExaminationDoctor A ");
		jpql.append("WHERE A.examinationPkId = :examinationPkId ");

		return getByQuery(ExaminationDoctor.class, jpql.toString(), parameters);
	}

	@Override
	public void saveExamination(Examination examination) throws Exception {
		if (Tool.MODIFIED.equals(examination.getStatus()))
			update(examination);
		else if (Tool.DELETE.equals(examination.getStatus())) {
			deleteByAnyField(ExaminationDtl.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationDoctor.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationLaborant.class, "examinationPkId", examination.getPkId());
			deleteByAnyField(ExaminationEmployeeMap.class, "examinationPkId", examination.getPkId());
			deleteByPkId(Examination.class, examination.getPkId());
		}
	}

	@Override
	public List<ExaminationGroup> getExaminationGroup(LoggedUser lu) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ExaminationGroup> list = new ArrayList<ExaminationGroup>();
		jpql.append(
				"SELECT NEW hospital.entity.ExaminationGroup(a.pkId, a.id, a.name, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, a.isActive, count(b.pkId)) ");
		jpql.append("FROM ExaminationGroup a ");
		jpql.append("LEFT JOIN ExaminationGroupDtl b ON a.pkId = b.examinationGroupPkId  ");
		jpql.append(
				"GROUP BY a.pkId, a.id, a.name, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, a.isActive ");
		list = getByQuery(ExaminationGroup.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<ExaminationGroupDtl> getExaminationGroupDtl(BigDecimal examinationGroupPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationGroupPkId", examinationGroupPkId);
		List<ExaminationGroupDtl> list = new ArrayList<ExaminationGroupDtl>();
		jpql.append(
				"SELECT NEW hospital.entity.ExaminationGroupDtl(a.pkId, a.examinationPkId, a.examinationGroupPkId, a.examinationDtlPkId, b.name, c.nameMn, b.updatedDate, f.id ) ");
		jpql.append("FROM ExaminationGroupDtl a ");
		jpql.append("LEFT JOIN Examination b ON b.pkId = a.examinationPkId  ");
		jpql.append("LEFT JOIN ExaminationDtl d ON d.pkId = a.examinationDtlPkId ");
		jpql.append("LEFT JOIN Element c ON c.pkId = d.elementPkId ");
		jpql.append("LEFT JOIN Users f ON f.pkId = b.updatedBy ");
		jpql.append("WHERE a.examinationGroupPkId =:examinationGroupPkId ");
		list = getByQuery(ExaminationGroupDtl.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveExaminationGroup(ExaminationGroup examinationGroup, List<ExaminationGroupDtl> examinationGroupDtls,
			LoggedUser lu) throws Exception {

		if (examinationGroup.getStatus().equals(Tool.ADDED)) {
			examinationGroup.setPkId(Tools.newPkId());
			examinationGroup.setCreatedBy(lu.getUser().getPkId());
			examinationGroup.setCreatedDate(new Date());
			examinationGroup.setUpdatedBy(lu.getUser().getPkId());
			examinationGroup.setUpdatedDate(new Date());
			insert(examinationGroup);
			for (ExaminationGroupDtl egd : examinationGroupDtls) {
				egd.setPkId(Tools.newPkId());
				egd.setExaminationGroupPkId(examinationGroup.getPkId());
			}
			insert(examinationGroupDtls);

		} else if (examinationGroup.getStatus().equals(Tool.MODIFIED)) {
			examinationGroup.setUpdatedBy(lu.getUser().getPkId());
			examinationGroup.setUpdatedDate(new Date());
			update(examinationGroup);
			deleteByAnyField(ExaminationGroupDtl.class, "examinationGroupPkId", examinationGroup.getPkId());
			for (ExaminationGroupDtl egd : examinationGroupDtls) {
				egd.setPkId(Tools.newPkId());
				egd.setExaminationGroupPkId(examinationGroup.getPkId());
			}
			insert(examinationGroupDtls);
		}

		else if (examinationGroup.getStatus().equals(Tool.DELETE)) {
			deleteByPkId(ExaminationGroup.class, examinationGroup.getPkId());
		}

	}

	@Override
	public List<Examination> getExaminations(LoggedUser lu) throws Exception {

		List<Examination> list = new ArrayList<Examination>();
		CustomHashMap parameters = new CustomHashMap();
		list = getAll(Examination.class);
		for (Examination e : list) {
			List<ExaminationDtl> eList = new ArrayList<ExaminationDtl>();
			parameters.clear();
			parameters.put("examinationPkId", e.getPkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append(
					"SELECT NEW hospital.entity.ExaminationDtl(b.pkId, b.examinationPkId, b.elementPkId, a.nameMn) ");
			jpql.append("FROM Element a ");
			jpql.append("INNER JOIN ExaminationDtl b ON a.pkId = b.elementPkId ");
			jpql.append("WHERE b.examinationPkId =:examinationPkId  ");

			eList = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
			e.setExaminationDtls(eList);
		}

		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<ExaminationRequest> getExaminationRequests(Date beginDate, Date endDate, String filterKey,
			BigDecimal examinationPkId, boolean doneStatus) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		beginDate.setMinutes(0);
		beginDate.setHours(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		List<ExaminationRequest> list = new ArrayList<ExaminationRequest>();
		parameters.put("examinationPkId", examinationPkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("filterKey", "%" + filterKey + "%");
		jpql.append(
				"SELECT NEW hospital.entity.ExaminationRequest (a.pkId, a.customerPkId, a.examinationPkId, a.requestDate,  a.mood, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, c.name, b ) ");
		jpql.append("FROM ExaminationRequest a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("INNER JOIN Examination c ON a.examinationPkId = c.pkId ");
		jpql.append("WHERE a.requestDate between :beginDate AND :endDate ");
		if (examinationPkId.compareTo(BigDecimal.ZERO) != 0)
			jpql.append("AND a.examinationPkId =:examinationPkId ");
		jpql.append(
				"AND (b.cardNumber LIKE :filterKey OR b.lastName LIKE :filterKey OR b.firstName LIKE :filterKey OR b.regNumber LIKE :filterKey ) ");
		list = getByQuery(ExaminationRequest.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<ExaRequest> getDrequests(BigDecimal examinationPkId, String filterKey, int mood,
			BigDecimal employeePkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ExaRequest> list = new ArrayList<ExaRequest>();
		parameters.put("examinationPkId", examinationPkId);
		parameters.put("filterKey", "%" + filterKey + "%");
		parameters.put("mood", mood);
		parameters.put("employeePkId", employeePkId);
		jpql.append(
				"SELECT NEW hospital.businessentity.ExaRequest(a.pkId, a.customerPkId, a.examinationPkId, a.requestDate, a.mood,  c.name,  b, a.createdDate, a.updatedDate, a.createdBy, a.updatedBy, a.employeePkId ) ");
		if (mood == 1)
			jpql.append("FROM ExaminationRequestActive a ");
		if (mood == 2)
			jpql.append("FROM ExaminationRequestTempSave a ");
		if (mood == 3)
			jpql.append("FROM ExaminationRequestCompleted a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("INNER JOIN Examination c ON a.examinationPkId = c.pkId ");
		jpql.append("INNER JOIN ExaminationEmployeeMap d on a.examinationPkId = d.examinationPkId ");
		jpql.append("WHERE a.mood =:mood ");
		jpql.append("AND a.examinationPkId =:examinationPkId ");
		jpql.append("AND d.employeePkId =:employeePkId ");
		jpql.append(
				"AND (b.cardNumber LIKE :filterKey OR b.lastName LIKE :filterKey OR b.firstName LIKE :filterKey OR b.regNumber LIKE :filterKey ) ");

		list = getByQuery(ExaRequest.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveExaminationRequests(List<ExaminationRequest> examinationRequests, LoggedUser lu) throws Exception {
		BigDecimal luPkId = lu.getUser().getPkId();
		Date now = new Date();
		List<ExaminationRequestActive> list = new ArrayList<ExaminationRequestActive>();
		for (ExaminationRequest exr : examinationRequests) {
			if (exr.getMood() == 1) {
				ExaminationRequestActive exra = new ExaminationRequestActive();
				exra.setPkId(exr.getPkId());
				exra.setExaminationPkId(exr.getExaminationPkId());
				exra.setCustomerPkId(exr.getCustomerPkId());
				exra.setRequestDate(exr.getRequestDate());
				exra.setMood(exr.getMood());
				exra.setCreatedBy(luPkId);
				exra.setUpdatedBy(luPkId);
				exra.setCreatedDate(now);
				exra.setUpdatedDate(now);
				exra.setEmployeePkId(exr.getEmployeePkId());
				list.add(exra);
				deleteByPkId(ExaminationRequest.class, exr.getPkId());
			}

		}
		insert(list);

	}

	@Override
	public List<Examination> getExaminationsWithRequestCount(BigDecimal employeePkId) throws Exception {
		StringBuilder jpql1 = new StringBuilder();
		StringBuilder jpql2 = new StringBuilder();
		StringBuilder jpql3 = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<Examination> list1 = new ArrayList<Examination>();
		List<Examination> list2 = new ArrayList<Examination>();
		List<Examination> list3 = new ArrayList<Examination>();
		parameters.put("employeePkId", employeePkId);
		jpql1.append(
				"SELECT NEW hospital.entity.Examination(a.pkId, a.name, SUM(CASE b.mood WHEN 3 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 2 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 1 THEN 1 ELSE 0 END)  ) ");
		jpql1.append("FROM Examination a ");
		jpql1.append("LEFT JOIN ExaminationRequestCompleted b on a.pkId = b.examinationPkId ");
		jpql1.append("LEFT JOIN ExaminationEmployeeMap d ON a.pkId = d.examinationPkId ");
		jpql1.append("WHERE d.employeePkId =:employeePkId ");
		jpql1.append("GROUP BY a.pkId, a.name ");

		jpql2.append(
				"SELECT NEW hospital.entity.Examination(a.pkId, a.name, SUM(CASE b.mood WHEN 3 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 2 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 1 THEN 1 ELSE 0 END)  ) ");
		jpql2.append("FROM Examination a ");
		jpql2.append("LEFT JOIN ExaminationRequestTempSave b ON a.pkId = b.examinationPkId ");
		jpql2.append("LEFT JOIN ExaminationEmployeeMap d ON a.pkId = d.examinationPkId ");
		jpql2.append("WHERE d.employeePkId =:employeePkId ");
		jpql2.append("GROUP BY a.pkId, a.name ");

		jpql3.append(
				"SELECT NEW hospital.entity.Examination(a.pkId, a.name, SUM(CASE b.mood WHEN 3 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 2 THEN 1 ELSE 0 END), SUM(CASE b.mood WHEN 1 THEN 1 ELSE 0 END)  ) ");
		jpql3.append("FROM Examination a ");
		jpql3.append("LEFT JOIN ExaminationRequestActive b ON a.pkId = b.examinationPkId ");
		jpql3.append("LEFT JOIN ExaminationEmployeeMap d ON a.pkId = d.examinationPkId ");
		jpql3.append("WHERE d.employeePkId =:employeePkId ");
		jpql3.append("GROUP BY a.pkId, a.name ");
		list1 = getByQuery(Examination.class, jpql1.toString(), parameters);
		list2 = getByQuery(Examination.class, jpql2.toString(), parameters);
		list3 = getByQuery(Examination.class, jpql3.toString(), parameters);

		for (int i = 0; i < list1.size(); i++) {
			list1.get(i).setCountActiveRequest(list1.get(i).getCountActiveRequest()
					+ list2.get(i).getCountActiveRequest() + list3.get(i).getCountActiveRequest());
			list1.get(i).setCountTempRequest(list1.get(i).getCountTempRequest() + list2.get(i).getCountTempRequest()
					+ list3.get(i).getCountTempRequest());
			list1.get(i).setCountDoneRequest(list1.get(i).getCountDoneRequest() + list2.get(i).getCountDoneRequest()
					+ list3.get(i).getCountDoneRequest());
		}
		return list1;
	}

	@Override
	public String getExaminationGroupId() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.id FROM ExaminationGroup a ");
		jpql.append("ORDER BY a.id DESC ");
		List<String> list = getByQuery(String.class, jpql.toString(), null, 0, 1);
		String lastCardNumber = (list == null || list.size() < 1) ? "0" : list.get(0);
		int cardNumber = Integer.parseInt(lastCardNumber);
		cardNumber++;
		return getCusStringtomerCardNumberString(cardNumber);
	}

	@Override
	public List<ExaminationValueQuestion> getQuestions(ExaRequest er) throws Exception {
		StringBuilder jpql = new StringBuilder();
		StringBuilder jpql1 = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("requestPkId", er.getPkId());
		parameters.put("examinationPkId", er.getExaminationPkId());
		List<ExaminationValueQuestion> list = new ArrayList<ExaminationValueQuestion>();

		jpql.append("SELECT b ");
		jpql.append("FROM Examination a ");
		jpql.append("INNER JOIN ExaminationValueQuestion b ON b.examinationTemplatePkId = a.examinationTemplatePkId ");
		jpql.append("WHERE a.pkId =:examinationPkId ");
		list = getByQuery(ExaminationValueQuestion.class, jpql.toString(), parameters);
		for (ExaminationValueQuestion q : list) {
			for (ExaminationValueAnswer a : q.getAnswers()) {
				a.setValue(null);
				a.setValues(null);
				a.setBooleanValue(false);
				a.setStatus(null);
			}
		}
		if (er.getMood() == 2 || er.getMood() == 3) {
			jpql1.append("SELECT a ");
			jpql1.append("FROM ExaminationValueHdr a ");
			jpql1.append("WHERE a.requestPkId =:requestPkId ");
			List<ExaminationValueHdr> hdrs = new ArrayList<ExaminationValueHdr>();
			hdrs = getByQuery(ExaminationValueHdr.class, jpql1.toString(), parameters);
			for (ExaminationValueQuestion evq : list) {
				for (ExaminationValueAnswer eva : evq.getAnswers()) {
					if (eva.getAnswerType().equals(Tool.BOOLEAN)) {
						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								if (hdr.getValue().equals("1")) {
									eva.setStatus(Tool.UNCHANGED);
									eva.setBooleanValue(false);
								} else if (hdr.getValue().equals("0")) {
									eva.setStatus(Tool.UNCHANGED);
									eva.setBooleanValue(true);
								}
							}
						}

					}

					if (eva.getAnswerType().equals(Tool.SELECTABLE)) {
						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								eva.setValue(hdr.getValue());
							}
						}

					}
					if (eva.getAnswerType().equals(Tool.INPUT)) {
						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								eva.setValue(hdr.getValue());
							}
						}

					}

					if (eva.getAnswerType().equals(Tool.RADIO)) {

						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								eva.setValue(hdr.getValue());
							}
						}
					}
					if (eva.getAnswerType().equals(Tool.INPUTNUMBER)) {

						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								eva.setNumberValue(new BigDecimal(eva.getValue()));
							}
						}
					}
					if (eva.getAnswerType().equals(Tool.TEXTAREA)) {

						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								eva.setValue(hdr.getValue());
							}
						}
					}

					if (eva.getAnswerType().equals(Tool.MULTISELECT)) {
						for (ExaminationValueHdr hdr : hdrs) {
							if (hdr.getAnswerPkId().compareTo(eva.getPkId()) == 0) {
								eva.setStatus(Tool.UNCHANGED);
								if (eva.getValues() == null)
									eva.setValues(new ArrayList<String>());
								eva.getValues().add(hdr.getValue());
							}
						}

					}
				}
			}
		}

		return list;
	}

	@Override
	public void saveDrequest(List<ExaminationValueHdr> valueHdrs, LoggedUser lu, ExaRequest chosenRequest, int saveType)
			throws Exception {
		Date now = new Date();
		for (ExaminationValueHdr evh : valueHdrs) {
			if (evh.getStatus() != null)
				if (evh.getStatus().equals(Tool.ADDED)) {
					evh.setPkId(Tools.newPkId());
					evh.setCreatedBy(lu.getUser().getPkId());
					evh.setRequestPkId(chosenRequest.getPkId());
					evh.setCreatedDate(now);
					evh.setUpdatedBy(lu.getUser().getPkId());
					evh.setUpdatedDate(now);
					if (saveType == 1)
						evh.setLaborantPkId(lu.getEmployeePkId());
					if (saveType == 2) {
						if (evh.getLaborantPkId() == null)
							evh.setLaborantPkId(lu.getEmployeePkId());
						evh.setDoctorPkId(lu.getEmployeePkId());
					}
					if (saveType == 3)
						if (evh.getLaborantPkId() == null)
							evh.setLaborantPkId(lu.getEmployeePkId());
					evh.setDoctorPkId(lu.getEmployeePkId());
					insert(evh);
				} else if (evh.getStatus().equals(Tool.MODIFIED)) {
					evh.setRequestPkId(chosenRequest.getPkId());
					evh.setUpdatedBy(lu.getUser().getPkId());
					evh.setUpdatedDate(now);
					update(evh);
				}
		}

		if (saveType == 1) {
			deleteByPkId(ExaminationRequestActive.class, chosenRequest.getPkId());
			deleteByPkId(ExaminationRequestTempSave.class, chosenRequest.getPkId());
			ExaminationRequestTempSave ert = new ExaminationRequestTempSave(chosenRequest);
			ert.setCreatedBy(lu.getUser().getPkId());
			ert.setCreatedDate(now);
			ert.setUpdatedBy(lu.getUser().getPkId());
			ert.setUpdatedDate(now);
			ert.setMood(2);
			insert(ert);
		} else if (saveType == 2) {
			deleteByPkId(ExaminationRequestTempSave.class, chosenRequest.getPkId());
			deleteByPkId(ExaminationRequestActive.class, chosenRequest.getPkId());
			ExaminationRequestCompleted erc = new ExaminationRequestCompleted(chosenRequest);
			erc.setCreatedBy(lu.getUser().getPkId());
			erc.setCreatedDate(now);
			erc.setUpdatedBy(lu.getUser().getPkId());
			erc.setUpdatedDate(now);
			erc.setMood(3);
			insert(erc);
		} else if (saveType == 3) {
			deleteByPkId(ExaminationRequestTempSave.class, chosenRequest.getPkId());
			deleteByPkId(ExaminationRequestActive.class, chosenRequest.getPkId());
			ExaminationRequestCompleted erc = new ExaminationRequestCompleted(chosenRequest);
			erc.setCreatedBy(lu.getUser().getPkId());
			erc.setCreatedDate(now);
			erc.setUpdatedBy(lu.getUser().getPkId());
			erc.setUpdatedDate(now);
			erc.setMood(3);
			insert(erc);
		}

	}

	@Override
	public List<ExaminationValueQuestion> getQuestions(BigDecimal examinationTemplatePkId) throws Exception {
		return getByAnyField(ExaminationValueQuestion.class, "examinationTemplatePkId", examinationTemplatePkId);
	}

	@Override
	public List<ExaminationValueHdr> getHdrs(ExaRequest er) throws Exception {
		StringBuilder jpql = new StringBuilder();

		CustomHashMap parameters = new CustomHashMap();
		parameters.put("requestPkId", er.getPkId());

		List<ExaminationValueHdr> list = new ArrayList<ExaminationValueHdr>();

		jpql.append("SELECT a ");
		jpql.append("FROM ExaminationValueHdr a ");
		jpql.append("WHERE a.requestPkId =:requestPkId ");
		list = getByQuery(ExaminationValueHdr.class, jpql.toString(), parameters);
		for (ExaminationValueHdr temp : list)
			temp.setStatus(Tool.UNCHANGED);
		return list;
	}

	@Override
	public List<ExaminationTemplate> getTemplates() throws Exception {
		return getAll(ExaminationTemplate.class);
	}

	@Override
	public void saveExaminationValueHdrs(List<ExaminationValueHdr> valueHdrs, BigDecimal requestPkId) throws Exception {
		Date now = new Date();
		for (ExaminationValueHdr evh : valueHdrs) {
			if (evh.getStatus() != null)
				if (evh.getStatus().equals(Tool.ADDED)) {
					evh.setPkId(Tools.newPkId());
					evh.setRequestPkId(requestPkId);
					evh.setCreatedDate(now);
					evh.setUpdatedDate(now);
					insert(evh);
				} else if (evh.getStatus().equals(Tool.MODIFIED)) {
					evh.setRequestPkId(requestPkId);
					evh.setUpdatedDate(now);
					update(evh);
				}
		}
		if (valueHdrs != null || valueHdrs.size() >= 1) {
			ExaminationRequestTempSave erts = new ExaminationRequestTempSave();
			ExaminationRequestActive era = new ExaminationRequestActive();
			if (getByPkId(ExaminationRequestActive.class, requestPkId) != null) {
				era = getByPkId(ExaminationRequestActive.class, requestPkId);
				era.setMood(2);
				ExaRequest err = new ExaRequest(era);
				deleteByPkId(ExaminationRequestActive.class, requestPkId);
				ExaminationRequestTempSave ert = new ExaminationRequestTempSave(err);
				insert(ert);
			}
			if (getByPkId(ExaminationRequestTempSave.class, requestPkId) != null) {
				erts = getByPkId(ExaminationRequestTempSave.class, requestPkId);
				deleteByPkId(ExaminationRequestTempSave.class, requestPkId);
				insert(erts);
			}
		}

	}

	@Override
	public ExaRequest getRequest(BigDecimal requestPkId) throws Exception {
		ExaRequest exa = new ExaRequest();
		if (getByPkId(ExaminationRequestActive.class, requestPkId) != null) {
			exa = new ExaRequest(getByPkId(ExaminationRequestActive.class, requestPkId));
		}
		if (getByPkId(ExaminationRequestTempSave.class, requestPkId) != null) {
			exa = new ExaRequest(getByPkId(ExaminationRequestTempSave.class, requestPkId));
		}
		if (getByPkId(ExaminationRequestCompleted.class, requestPkId) != null) {
			exa = new ExaRequest(getByPkId(ExaminationRequestCompleted.class, requestPkId));
		}
		return exa;
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
	public boolean isDoctor(BigDecimal employeePkId) throws Exception {
		List<ExaminationEmployeeMap> eems = new ArrayList<ExaminationEmployeeMap>();
		eems = getByAnyField(ExaminationEmployeeMap.class, "employeePkId", employeePkId);
		int i = 0;
		for (ExaminationEmployeeMap eem : eems) {
			if (eem.getType() == 1) {
				i++;
			}
		}
		if (i >= 1)
			return false;
		else
			return true;
	}

	@Override
	public List<String> getElementNameMn() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.nameMn FROM Element a ");
		return getByQuery(String.class, jpql.toString(), null);
	}

	@Override
	public List<String> getElementNameEn() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.nameEn FROM Element a ");
		return getByQuery(String.class, jpql.toString(), null);
	}

	@Override
	public List<Examination> getEmrExaminationList(BigDecimal customerPkId) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Examination(a, count(b.pkId)) ");
		jpql.append("FROM Examination a ");
		jpql.append("LEFT JOIN ExaminationRequestCompleted b on b.examinationPkId = a.pkId ");
		jpql.append("GROUP BY a ");

		return getByQuery(Examination.class, jpql.toString(), parameters);
	}

}
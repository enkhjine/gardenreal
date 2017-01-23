package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businessentity.TreatmentRequest;
import hospital.businesslogic.interfaces.ILogicInsuranceLocal;
import hospital.businesslogic.interfaces.ILogicTreatmentRequestLocal;
import hospital.entity.Customer;
import hospital.entity.CustomerTreatment;
import hospital.entity.CustomerTreatmentDtl;
import hospital.entity.CustomerTreatmentHistory;
import hospital.entity.Diagnose;
import hospital.entity.InsuranceAmbulanceConfig;
import hospital.entity.InsuranceAmbulanceConfigDtl;
import hospital.entity.InsuranceConfig;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicTreatmentRequest", mappedName = "hospital.businesslogic.LogicTreatmentRequest")
public class LogicTreatmentRequest extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicTreatmentRequest, ILogicTreatmentRequestLocal {

	@Resource
	SessionContext sessionContext;

	public LogicTreatmentRequest() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	public List<TreatmentRequest> getCustomerTreatments(BigDecimal employeePkId, String filterKey1, String filterKey2,
			Date beginDate, Date endDate) throws Exception {

		List<TreatmentRequest> list = new ArrayList<TreatmentRequest>();
		List<Customer> customers = new ArrayList<Customer>();
		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT DISTINCT a ");
		jpql.append("FROM Customer a ");
		jpql.append("INNER JOIN CustomerTreatment b on a.pkId = b.customerPkId ");
		customers = getByQuery(Customer.class, jpql.toString(), null);
		for (Customer c : customers) {
			TreatmentRequest tr = new TreatmentRequest();
			tr.setCustomer(c);
			list.add(tr);
		}

		for (TreatmentRequest tr : list) {
			StringBuilder jpql1 = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("filter1", "%" + filterKey1 + "%");
			parameters.put("filter2", "%" + filterKey2 + "%");
			parameters.put("customerPkId", tr.getCustomer().getPkId());
			parameters.put("employeePkId", employeePkId);
			parameters.put("beginDate", beginDate);
			parameters.put("endDate", endDate);
			jpql1.append("SELECT NEW hospital.entity.CustomerTreatment (a, d  , c.firstName, x.firstName )  ");
			jpql1.append("FROM CustomerTreatment a ");
			jpql1.append("LEFT JOIN Customer b ON b.pkId = a.customerPkId ");
			jpql1.append("LEFT JOIN Inspection k ON k.pkId = a.inspectionPkId ");
			jpql1.append("LEFT JOIN Employee c ON c.pkId = k.employeePkId ");
			jpql1.append("LEFT JOIN Treatment d ON d.pkId = a.treatmentPkId ");
			jpql1.append("LEFT JOIN TreatmentTypeEmployeeMap e ON e.treatmentTypePkId = d.treatmentTypePkId ");
			jpql1.append("LEFT JOIN Employee x ON x.pkId = a.employeePkId ");
			jpql1.append("WHERE a.customerPkId =:customerPkId ");
			jpql1.append("AND e.employeePkId =:employeePkId ");
			jpql1.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
			//jpql1.append("AND (c.firstName LIKE :filter2) ");
			jpql1.append(
					"AND (b.lastName LIKE :filter1 OR b.firstName LIKE :filter1 OR b.regNumber LIKE :filter1 OR b.cardNumber LIKE :filter1) ");
			List<CustomerTreatment> ct = new ArrayList<CustomerTreatment>();
			ct = getByQuery(CustomerTreatment.class, jpql1.toString(), parameters);

			for (CustomerTreatment customerTreatment : ct) {
				StringBuilder sb = new StringBuilder();
				CustomHashMap pm = new CustomHashMap();
				pm.put("inspectionPkId", customerTreatment.getInspectionPkId());
				sb.append("SELECT a ");
				sb.append("FROM Diagnose a ");
				sb.append("INNER JOIN CustomerDiagnose b ON b.diagnosePkId = a.pkId ");
				sb.append("INNER JOIN Inspection c ON c.pkId = b.inspectionPkId ");
				sb.append("WHERE c.pkId = :inspectionPkId ");
				sb.append("AND b.type = 1 ");
				customerTreatment.setDiagnoseList(getByQuery(Diagnose.class, sb.toString(), pm));

			}
			tr.setCustomerTreatments(ct);

		}
		List<TreatmentRequest> retList = new ArrayList<TreatmentRequest>();
		for (TreatmentRequest tr : list) {
			if (tr.getCustomerTreatments().size() > 0) {
				TreatmentRequest ttr = new TreatmentRequest();
				ttr = (TreatmentRequest) Tool.deepClone(tr);
				retList.add(ttr);
			}

		}

		return retList;
	}

	@Override
	public void saveTreatmentRequest(List<CustomerTreatment> customerTreatments, LoggedUser lu) throws Exception {
		Date now = new Date();
		for (CustomerTreatment ct : customerTreatments) {
			if (ct.isDone())
				if (ct.getDoneCount() == ct.getTimes()) {
					deleteByPkId(CustomerTreatment.class, ct.getPkId());
					CustomerTreatmentHistory cth = new CustomerTreatmentHistory(ct);
					cth.setEmployeePkId(lu.getUser().getPkId());
					cth.setUpdatedDate(now);
					if (getByPkId(CustomerTreatmentHistory.class, ct.getPkId()) == null)
						insert(cth);
					else
						update(cth);

					CustomerTreatmentDtl ctd = new CustomerTreatmentDtl();
					ctd.setPkId(Tools.newPkId());
					ctd.setEmployeePkId(lu.getEmployeePkId());
					ctd.setCustomerTreatmentPkId(ct.getPkId());
					ctd.setCreatedBy(lu.getEmployeePkId());
					ctd.setCreatedDate(now);
					ctd.setUpdatedBy(lu.getEmployeePkId());
					ctd.setUpdatedDate(now);
					ctd.setTreatmentDate(now);
					ctd.setVas(ct.getVas());
					insert(ctd);

				} else {
						update(ct);
					CustomerTreatmentDtl ctd = new CustomerTreatmentDtl();
					ctd.setPkId(Tools.newPkId());
					ctd.setEmployeePkId(lu.getEmployeePkId());
					ctd.setCustomerTreatmentPkId(ct.getPkId());
					ctd.setCreatedBy(lu.getEmployeePkId());
					ctd.setCreatedDate(now);
					ctd.setUpdatedBy(lu.getEmployeePkId());
					ctd.setUpdatedDate(now);
					ctd.setTreatmentDate(now);
					ctd.setVas(ct.getVas());
					insert(ctd);
				}

		}

	}

	public List<CustomerTreatmentHistory> getHistory(BigDecimal employeePkId, Date beginDate, Date endDate, String key)
			throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkId", employeePkId);
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("key", "%" + key + "%");
		jpql.append("SELECT NEW hospital.entity.CustomerTreatmentHistory (a, d, c.firstName, b )  ");
		jpql.append("FROM CustomerTreatmentHistory a ");
		jpql.append("INNER JOIN Customer b ON b.pkId = a.customerPkId ");
		jpql.append("LEFT JOIN Inspection k ON k.pkId = a.inspectionPkId ");
		jpql.append("LEFT JOIN Employee c ON c.pkId = k.employeePkId ");
		jpql.append("INNER JOIN Treatment d ON d.pkId = a.treatmentPkId ");
		jpql.append("INNER JOIN TreatmentTypeEmployeeMap e ON e.treatmentTypePkId = d.treatmentTypePkId ");
		jpql.append("AND e.employeePkId = :employeePkId ");
		jpql.append("AND a.createdDate BETWEEN :beginDate AND :endDate ");
		jpql.append(
				"AND (b.lastName LIKE :key OR b.firstName LIKE :key OR b.regNumber LIKE :key OR b.cardNumber LIKE :key) ");
		List<CustomerTreatmentHistory> list = getByQuery(CustomerTreatmentHistory.class, jpql.toString(), parameters);
		for (CustomerTreatmentHistory customerTreatment : list) {
			StringBuilder sb = new StringBuilder();
			CustomHashMap pm = new CustomHashMap();
			pm.put("inspectionPkId", customerTreatment.getInspectionPkId());
			sb.append("SELECT a ");
			sb.append("FROM Diagnose a ");
			sb.append("INNER JOIN CustomerDiagnose b ON b.diagnosePkId = a.pkId ");
			sb.append("INNER JOIN Inspection c ON c.pkId = b.inspectionPkId ");
			sb.append("WHERE c.pkId = :inspectionPkId ");
			sb.append("AND b.type = 1 ");
			customerTreatment.setDiagnoseList(getByQuery(Diagnose.class, sb.toString(), pm));
		}
		return list;
	}

	public List<CustomerTreatmentDtl> getDtl(BigDecimal ctPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("ctPkId", ctPkId);
		jpql.append("SELECT NEW hospital.entity.CustomerTreatmentDtl (a, b.firstName ) ");
		jpql.append("FROM  CustomerTreatmentDtl a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("WHERE a.customerTreatmentPkId = :ctPkId ");

		return getByQuery(CustomerTreatmentDtl.class, jpql.toString(), parameters);
	}

}

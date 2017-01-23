package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businessentity.TreatmentRequest;
import hospital.businesslogic.interfaces.ILogicSurgeryRequestLocal;
import hospital.entity.Customer;
import hospital.entity.CustomerTreatment;
import hospital.entity.CustomerTreatmentHistory;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicSurgeryRequest", mappedName = "hospital.businesslogic.LogicTreatmentRequest")
public class LogicSurgeryRequest extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicSurgeryRequest,
		ILogicSurgeryRequestLocal {

	@Resource
	SessionContext sessionContext;

	public LogicSurgeryRequest() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<TreatmentRequest> getCustomerTreatments(
			BigDecimal employeePkId, String filterKey1, String filterKey2)
			throws Exception {
		
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
			jpql1.append("SELECT NEW hospital.entity.CustomerTreatment (a, d ,c.firstName )  ");
			jpql1.append("FROM CustomerTreatment a ");
			jpql1.append("INNER JOIN Customer b ON b.pkId = a.customerPkId ");
			jpql1.append("INNER JOIN Employee c ON c.pkId = a.employeePkId ");
			jpql1.append("INNER JOIN Treatment d ON d.pkId = a.treatmentPkId ");
			jpql1.append("WHERE a.customerPkId =:customerPkId ");
			jpql1.append("AND (c.firstName like :filter2) ");
			jpql1.append("AND (b.lastName LIKE :filter1 OR b.firstName LIKE :filter1 OR b.regNumber LIKE :filter1 OR b.cardNumber LIKE :filter1) ");
			List<CustomerTreatment> ct = new ArrayList<CustomerTreatment>();
			ct = getByQuery(CustomerTreatment.class, jpql1.toString(),
					parameters);

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
	public void saveTreatmentRequest(
			List<CustomerTreatment> customerTreatments, LoggedUser lu)
			throws Exception {
		Date now  = new Date();
		for (CustomerTreatment ct : customerTreatments) {
			if (ct.isDone())
				if (ct.getDoneCount() - 1 == ct.getTimes()) {
					deleteByPkId(CustomerTreatment.class, ct.getPkId());
					CustomerTreatmentHistory cth = new CustomerTreatmentHistory(
							ct);
					cth.setPkId(Tools.newPkId());
					cth.setEmployeePkId(lu.getUser().getPkId());
					cth.setUpdatedDate(now);
					insert(cth);

				} else {
					if (ct.isDone())
						ct.setDoneCount(ct.getDoneCount() - 1);
					else
						ct.setDoneCount(ct.getDoneCount() + 1);
					ct.setUpdatedDate(now);
					update(ct);
					CustomerTreatmentHistory cth = new CustomerTreatmentHistory(
							ct);
					cth.setPkId(Tools.newPkId());
					cth.setEmployeePkId(lu.getUser().getPkId());
					insert(cth);
				}

		}

	}

}

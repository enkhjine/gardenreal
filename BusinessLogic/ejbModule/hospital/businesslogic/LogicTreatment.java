package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicTreatmentLocal;
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

@Stateless(name = "LogicTreatment", mappedName = "hospital.businesslogic.LogicTreatment")
public class LogicTreatment extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicTreatment, ILogicTreatmentLocal {
	@Resource
	SessionContext sessionContext;

	public LogicTreatment() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<Item> getItems(LoggedUser logUser) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("organizationPkId", logUser.getOrganization().getPkId());
		jpql.append("SELECT DISTINCT NEW hospital.entity.Item(a.pkId, a.organizationPkId, a.name, a.entityPrice, a.priceUsageDate, a.measurementPkId, b.name) ");
		jpql.append("FROM Item a  ");
		jpql.append("INNER JOIN Measurement b on a.measurementPkId = b.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		List<Item> list = new ArrayList<Item>();
		list = getByQuery(Item.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<Treatment> getTreatments(LoggedUser loggedUser)
			throws Exception {

		List<Treatment> list = new ArrayList<Treatment>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("organizationPkId", loggedUser.getOrganization()
				.getPkId());
		jpql.append("SELECT NEW hospital.entity.Treatment(a.pkId, a.organizationPkId, a.treatmentTypePkId, a.name, d.price, d.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, a.cost ,  COUNT(b.pkId))  ");
		jpql.append("FROM Treatment a ");
		jpql.append("LEFT JOIN TreatmentDtl b ON a.pkId = b.treatmentPkId ");
		jpql.append("LEFT JOIN View_Treatment d ON d.treatmentPkId = a.pkId ");

		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		jpql.append("AND d.treatmentPkId = a.pkId ");

		jpql.append("GROUP BY a.pkId, a.organizationPkId, a.treatmentTypePkId, a.name, d.price, d.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, a.cost ");
		list = getByQuery(Treatment.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<TreatmentType> getTreatmentTypes(LoggedUser user)
			throws Exception {

		List<TreatmentType> list = new ArrayList<TreatmentType>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("userPkId", user.getUser().getPkId());
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT d FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b on  b.pkId = a.subOrganizationPkId ");
		jpql.append("INNER JOIN TreatmentType d on b.subOrganizationTypePkId = d.subOrganizationTypePkId  ");
		jpql.append("where a.userPkId = :userPkId  ");
		list = getByQuery(TreatmentType.class, jpql.toString(), parameters);
		return list;

	}

	@Override
	public List<TreatmentType> getTreatmentTypeBySot(LoggedUser lu,
			BigDecimal sotPkId, BigDecimal price) throws Exception {

		List<TreatmentType> list = new ArrayList<TreatmentType>();
		BigDecimal tt = sotPkId;
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("subOrganizationTypePkId", sotPkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM TreatmentType a ");
		if (tt.compareTo(BigDecimal.ZERO) != 0) {
			jpql.append("where a.subOrganizationTypePkId=:subOrganizationTypePkId  ");
		}
		list = getByQuery(TreatmentType.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<Treatment> getTreatmentByPrice(LoggedUser lu, BigDecimal price)
			throws Exception {

		List<Treatment> list = new ArrayList<Treatment>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("price", price);
		parameters.put("organizationPkId", lu.getUser().getOrganizationPkId());
		jpql.append("SELECT NEW hospital.entity.Treatment(a.pkId, a.organizationPkId, a.treatmentTypePkId, a.name, d.price, d.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, a.cost ,  COUNT(b.pkId)) ");
		jpql.append("FROM Treatment a ");
		jpql.append("LEFT JOIN TreatmentDtl b ON a.pkId = b.treatmentPkId ");
		jpql.append("LEFT JOIN View_Treatment d ON d.treatmentPkId = a.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		jpql.append("AND d.treatmentPkId = a.pkId ");
		if (price.compareTo(BigDecimal.ZERO) == 0)
			jpql.append("AND d.price = :price ");

		jpql.append("GROUP BY a.pkId, a.organizationPkId, a.treatmentTypePkId, a.name, d.price, d.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy , a.cost ");
		list = getByQuery(Treatment.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<PriceHistory> getPriceHistorys(BigDecimal itemPkId,
			LoggedUser lu) throws Exception {

		List<PriceHistory> list = new ArrayList<PriceHistory>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("itemPkId", itemPkId);
		jpql.append("SELECT NEW hospital.entity.PriceHistory (a.pkId, a.itemPkId, a.itemName, a.price, a.priceUsageDate, c.name) ");
		jpql.append("FROM PriceHistory a ");
		jpql.append("INNER JOIN Item b ON a.itemPkId = b.pkId ");
		jpql.append("INNER JOIN Measurement c ON b.measurementPkId = c.pkId ");
		jpql.append("WHERE a.itemPkId = :itemPkId ");
		list = getByQuery(PriceHistory.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveItem(Item item, LoggedUser lu) throws Exception {

		if (Tool.ADDED.equals(item.getStatus())) {
			item.setPkId(Tools.newPkId());
			item.setOrganizationPkId(lu.getUser().getOrganizationPkId());
			item.setCreatedBy(lu.getUser().getPkId());
			item.setCreatedDate(new Date());
			item.setUpdatedBy(lu.getUser().getPkId());
			item.setUpdatedDate(new Date());
			insert(item);

			PriceHistory ph = new PriceHistory();
			ph.setPkId(Tools.newPkId());
			ph.setPriceUsageDate(item.getPriceUsageDate());
			ph.setItemPkId(item.getPkId());
			ph.setItemName(item.getName());
			ph.setPrice(item.getEntityPrice());
			ph.setUpdatedBy(lu.getUser().getPkId());
			ph.setUpdatedDate(new Date());
			ph.setCreatedBy(lu.getUser().getPkId());
			ph.setCreatedDate(new Date());
			insert(ph);
		} else if (Tool.MODIFIED.equals(item.getStatus())) {
			item.setOrganizationPkId(lu.getUser().getOrganizationPkId());
			update(item);
			List<PriceHistory> phs = getByAnyField(PriceHistory.class,
					"itemPkId", item.getPkId());
			int temp = 0;
			for (PriceHistory p : phs) {
				if (p.getPrice() == null) {
					p.setPrice(BigDecimal.ZERO);
				}
				if (p.getPrice().compareTo(item.getEntityPrice()) == 0) {
					temp = 1;
				}
			}
			if (temp == 0) {
				PriceHistory ph = new PriceHistory();
				ph.setPkId(Tools.newPkId());
				ph.setPriceUsageDate(item.getPriceUsageDate());
				ph.setItemPkId(item.getPkId());
				ph.setItemName(item.getName());
				ph.setPrice(item.getEntityPrice());
				ph.setUpdatedBy(lu.getUser().getPkId());
				ph.setUpdatedDate(new Date());
				ph.setCreatedBy(lu.getUser().getPkId());
				ph.setCreatedDate(new Date());
				insert(ph);
			}
		} else if (Tool.DELETE.equals(item.getStatus())) {
			deleteByPkId(Item.class, item.getPkId());
		}

	}

	@Override
	public void saveTreatmentType(TreatmentType treatmentType,
			List<TreatmentTypeEmployeeMap> maps) throws Exception {

		if (Tool.ADDED.equals(treatmentType.getStatus())) {
			treatmentType.setPkId(Tools.newPkId());
			if (maps != null) {
				for (TreatmentTypeEmployeeMap m : maps) {
					m.setPkId(Tools.newPkId());
					m.setTreatmentTypePkId(treatmentType.getPkId());
				}
				insert(maps);
			}
			insert(treatmentType);
		} else if (Tool.MODIFIED.equals(treatmentType.getStatus())) {

			update(treatmentType);
			deleteByAnyField(TreatmentTypeEmployeeMap.class,
					"treatmentTypePkId", treatmentType.getPkId());
			if (maps != null || maps.size() < 1) {
				for (TreatmentTypeEmployeeMap m : maps) {
					m.setPkId(Tools.newPkId());
					m.setTreatmentTypePkId(treatmentType.getPkId());

				}
			}
			insert(maps);

		} else if (Tool.DELETE.equals(treatmentType.getStatus())) {
			deleteByPkId(TreatmentType.class, treatmentType.getPkId());
			for (TreatmentTypeEmployeeMap ttem : maps) {
				deleteByPkId(TreatmentTypeEmployeeMap.class, ttem.getPkId());
			}
		}

	}

	@Override
	public void savePriceHistory(DegreePriceHistory priceHistory,
			LoggedUser lu, BigDecimal itemPkId) throws Exception {
		Date now = new Date();
		priceHistory.setCreatedBy(lu.getUser().getPkId());
		priceHistory.setUpdatedBy(lu.getUser().getPkId());
		priceHistory.setUpdatedDate(now);
		priceHistory.setCreatedDate(now);
		priceHistory.setDegreePkId(itemPkId);
		Degree dd = getByPkId(Degree.class, itemPkId);
		priceHistory.setPkId(Tools.newPkId());
		insert(priceHistory);

	}

	@Override
	public void saveTreatmentDtl(TreatmentDtl treatmentDtl) throws Exception {

		if (Tool.ADDED.equals(treatmentDtl.getStatus())) {
			treatmentDtl.setPkId(Tools.newPkId());
			insert(treatmentDtl);
		} else if (Tool.MODIFIED.equals(treatmentDtl.getStatus())) {
			update(treatmentDtl);
		} else if (Tool.DELETE.equals(treatmentDtl.getStatus())) {
			deleteByPkId(TreatmentDtl.class, treatmentDtl.getPkId());
		}

	}

	@Override
	public List<TreatmentDtl> getTreatmentDtls() throws Exception {
		return getAll(TreatmentDtl.class);
	}

	@Override
	public List<Item> getTreatmentDtlsByTreatmentPkId(BigDecimal pkId)
			throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("treatmentPkId", pkId);
		jpql.append("SELECT NEW hospital.entity.Item(a.pkId, a.organizationPkId, a.name, b.itemCount, a.entityPrice, a.priceUsageDate, a.measurementPkId, c.name ) FROM Item a ");
		jpql.append("INNER JOIN Measurement c ON c.pkId = a.measurementPkId ");
		jpql.append("INNER JOIN TreatmentDtl b ON a.pkId = b.itemPkId ");
		jpql.append("WHERE b.treatmentPkId = :treatmentPkId ");
		List<Item> items = getByQuery(Item.class, jpql.toString(), parameters);
		return items;
	}

	@Override
	public void saveTreatment(LoggedUser loggedUser, Treatment treatment,
			List<Item> items, boolean isModel) throws Exception {

		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		if (Tool.ADDED.equals(treatment.getStatus())) {
			parameters.put("name", treatment.getName());
			parameters.put("organizationPkId", loggedUser.getOrganization()
					.getPkId());
			jpql.append("SELECT a ");
			jpql.append("FROM Treatment a  ");
			jpql.append("WHERE a.name = :name ");
			jpql.append("AND a.organizationPkId = :organizationPkId ");
			List<Treatment> list = getByQuery(Treatment.class, jpql.toString(),
					parameters);
			if (list.size() > 0)
				throw new Exception("duplicate");
			treatment.setPkId(pkId);
			treatment.setCreatedBy(loggedUser.getUser().getPkId());
			treatment.setCreatedDate(dte);
			treatment.setUpdatedBy(loggedUser.getUser().getPkId());
			treatment.setUpdatedDate(dte);
			treatment.setOrganizationTypePkId((loggedUser.getUser()
					.getOrganizationPkId()));
			insert(treatment);
			
			if (isModel == true) {
				TreatmentModel tm = new TreatmentModel();
				tm.setPkId(Tools.newPkId());
				tm.setTreatmentPkId(treatment.getPkId());
				tm.setName(treatment.getName());
				insert(tm);
			}

			TreatmentPrice tp = new TreatmentPrice();
			tp.setPkId(Tools.newPkId());
			tp.setCreatedBy(loggedUser.getUser().getPkId());
			tp.setCreatedDate(dte);
			tp.setUpdatedBy(loggedUser.getUser().getPkId());
			tp.setUpdatedDate(dte);
			tp.setPrice(treatment.getPrice());
			tp.setBeginDate(dte);
			tp.setTreatmentPkId(treatment.getPkId());
			insert(tp);
			if (treatment.isHasDtl()) {
				List<TreatmentDtl> dtls = new ArrayList<TreatmentDtl>();
				BigDecimal i = BigDecimal.ZERO;
				for (Item item : items) {

					if (item.getPkId() == null
							|| item.getPkId().compareTo(BigDecimal.ZERO) == 0)
						continue;
					TreatmentDtl dtl = new TreatmentDtl();
					pkId = pkId.add(BigDecimal.ONE);
					i = i.add(BigDecimal.ONE);
					dtl.setItemCount(i);
					dtl.setTreatmentPkId(treatment.getPkId());
					dtl.setItemPkId(item.getPkId());
					dtl.setPkId(pkId);
					dtl.setItemCount(item.getItemCount());
					dtls.add(dtl);

				}

				insert(dtls);
			}
		} else if (Tool.MODIFIED.equals(treatment.getStatus())) {
			parameters.put("name", treatment.getName());
			parameters.put("treatmentTypePkId",
					treatment.getTreatmentTypePkId());
			parameters.put("organizationPkId", loggedUser.getOrganization()
					.getPkId());
			jpql.append("SELECT a ");
			jpql.append("FROM Treatment a ");
			jpql.append("WHERE a.name = :name ");
			jpql.append("AND a.treatmentTypePkId = :treatmentTypePkId ");
			jpql.append("AND a.organizationPkId = :organizationPkId ");
			List<Treatment> list = getByQuery(Treatment.class, jpql.toString(),
					parameters);
			if (list.size() > 1)
				throw new Exception("duplicate");
			treatment.setUpdatedBy(loggedUser.getUser().getPkId());
			treatment.setUpdatedDate(dte);
			deleteByAnyField(TreatmentDtl.class, "treatmentPkId",
					treatment.getPkId());
			if (isModel == true) {
				deleteByAnyField(TreatmentModel.class, "treatmentPkId", treatment.getPkId());
				TreatmentModel tm = new TreatmentModel();
				tm.setPkId(Tools.newPkId());
				tm.setTreatmentPkId(treatment.getPkId());
				tm.setName(treatment.getName());
				insert(tm);
			}
			update(treatment);

			TreatmentPrice tp = new TreatmentPrice();
			tp.setPkId(Tools.newPkId());
			tp.setCreatedBy(loggedUser.getUser().getPkId());
			tp.setCreatedDate(dte);
			tp.setUpdatedBy(loggedUser.getUser().getPkId());
			tp.setUpdatedDate(dte);
			tp.setPrice(treatment.getPrice());
			tp.setTreatmentPkId(treatment.getPkId());
			tp.setBeginDate(treatment.getUsageDate());
			insert(tp);
			if (treatment.isHasDtl()) {
				List<TreatmentDtl> dtls = new ArrayList<TreatmentDtl>();
				BigDecimal i = BigDecimal.ZERO;
				for (Item item : items) {
					if (item.getPkId() == null
							|| item.getPkId().compareTo(BigDecimal.ZERO) == 0)
						continue;
					TreatmentDtl dtl = new TreatmentDtl();
					pkId = pkId.add(BigDecimal.ONE);
					i = i.add(BigDecimal.ONE);
					dtl.setItemCount(i);
					dtl.setTreatmentPkId(treatment.getPkId());
					dtl.setItemPkId(item.getPkId());
					dtl.setPkId(pkId);
					dtl.setItemCount(item.getItemCount());
					dtls.add(dtl);
				}
				insert(dtls);
			}
		} else if (Tool.DELETE.equals(treatment.getStatus())) {
			deleteByAnyField(TreatmentPrice.class, "treatmentPkId", treatment.getPkId());
			deleteByAnyField(TreatmentDtl.class, "treatmentPkId",
					treatment.getPkId());
			deleteByAnyField(TreatmentModel.class, "treatmentPkId", treatment.getPkId());
			deleteByPkId(Treatment.class, treatment.getPkId());
			deleteByAnyField(TreatmentIcdMap.class, "treatmentPkId", treatment.getPkId());
		}
	}

	@Override
	public void updateTreatmentStatus(Treatment treatment) throws Exception {
		update(treatment);
	}

	@Override
	public List<TreatmentType> getAllTreatmentType(LoggedUser lu)
			throws Exception {
		List<TreatmentType> list = new ArrayList<TreatmentType>();
		StringBuilder jpql1 = new StringBuilder();
		jpql1.append("SELECT NEW hospital.entity.TreatmentType(a.pkId, a.subOrganizationTypePkId, a.name, b.name ) ");
		jpql1.append("FROM TreatmentType a ");
		jpql1.append("INNER JOIN SubOrganizationType b ON a.subOrganizationTypePkId = b.pkId ");
		list = getByQuery(TreatmentType.class, jpql1.toString(), null);

		for (TreatmentType tt : list) {
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("ttPkId", tt.getPkId());
			jpql.append("SELECT NEW hospital.entity.TreatmentTypeEmployeeMap(a.pkId, a.treatmentTypePkId, a.employeePkId, b.firstName ) ");
			jpql.append("FROM TreatmentTypeEmployeeMap a ");
			jpql.append("INNER JOIN Employee b ON b.pkId = a.employeePkId ");
			jpql.append("WHERE a.treatmentTypePkId =:ttPkId ");
			tt.setMaps(getByQuery(TreatmentTypeEmployeeMap.class,
					jpql.toString(), parameters));

		}
		return list;

	}

	@Override
	public List<TreatmentModel> getTreatmentModels() throws Exception {
		// Загвар эмчилгээний жагсаалт
		return getAll(TreatmentModel.class);
	}

	@Override
	public List<DegreePriceHistory> getPriceHistories(BigDecimal itemPkId,
			LoggedUser lu) throws Exception {

		List<DegreePriceHistory> list = new ArrayList<DegreePriceHistory>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		parameters.put("degreePkId", itemPkId);
		jpql.append("SELECT NEW hospital.entity.DegreePriceHistory(a.pkId, a.degreePkId, a.priceUsageDate, a.price, a.rePrice, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, b.name, c.name, d.name) ");
		jpql.append("FROM DegreePriceHistory a ");
		jpql.append("INNER JOIN Degree b on a.degreePkId = b.pkId ");
		jpql.append("INNER JOIN Users d on a.createdBy = c.pkId ");
		jpql.append("INNER JOIN Users c on a.updatedBy = d.pkId ");
		jpql.append("WHERE a.degreePkId = :degreePkId ORDER BY a.priceUsageDate DESC ");

		list = getByQuery(DegreePriceHistory.class, jpql.toString(), parameters);
		return list;

	}

	@Override
	public List<InspectionDtl> getDetails(EmployeeRequest er) throws Exception {

		List<InspectionDtl> list = new ArrayList<InspectionDtl>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("requestPkId", er.getPkId());
		jpql.append("SELECT NEW hospital.entity.InspectionDtl(b.pkId, b.inspectionPkId,b.treatmentPkId, b.xrayPkId,b.toothPkId, b.cost, c.name, d.name) FROM Inspection a ");
		jpql.append("LEFT JOIN InspectionDtl b ON a.pkId = b.inspectionPkId ");
		jpql.append("LEFT JOIN Treatment c ON b.treatmentPkId = c.pkId ");
		jpql.append("LEFT JOIN Xray d ON b.xrayPkId = d.pkId ");
		jpql.append("WHERE a.requestPkId = :requestPkId AND b.pkId IS NOT NULL AND (c.pkId IS NOT NULL OR d.pkId IS NOT NULL) ");
		list = getByQuery(InspectionDtl.class, jpql.toString(), parameters);
		return list;
	}

	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu,
			Date beginDate, Date endDate, int status) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("userPkId", lu.getUser().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("WHERE a.date BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		jpql.append("AND b.userPkId = :userPkId ");

		if (status == 1) {
			// Үзлэгт орсон
			jpql.append("AND a.mood in (2, 4) ");
		}
		if (status == 2) {
			// Үзлэгт орох
			jpql.append("AND a.mood in (1, 0) ");
		}
		if (status == 3) {
			// Түр хадгалсан
			jpql.append("AND a.mood in (5, 6) ");
		}
		if (status == 4) {
			// Дахин үзлэг
			jpql.append("AND a.mood in (3) ");
		}

		jpql.append("ORDER BY a.beginTime, a.beginMinute ");

		List<CustomerRequest> employeeRequests = getByQuery(
				CustomerRequest.class, jpql.toString(), parameters);

		for (CustomerRequest customerRequest : employeeRequests) {
			jpql = new StringBuilder();
			parameters.put("requestPkId", customerRequest.getEmployeeRequest()
					.getPkId());
			jpql.append("SELECT a FROM Inspection a WHERE a.requestPkId = :requestPkId");
			List<Inspection> inspections = getByQuery(Inspection.class,
					jpql.toString(), parameters);
			if (inspections.size() > 0)
				customerRequest.setInspection(inspections.get(0));

			parameters.put("customerPkId", customerRequest.getCustomer()
					.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT NEW hospital.entity.Payment(a.amount, SUM(b.payment)) FROM Payment a ");
			jpql.append("INNER JOIN PaymentHistory b ON a.pkId = b.paymentPkId ");
			jpql.append("WHERE a.customerPkId = :customerPkId ");
			jpql.append("GROUP BY a.amount ");
			List<Payment> list = getByQuery(Payment.class, jpql.toString(),
					parameters);
			BigDecimal loanAmount = BigDecimal.ZERO;
			for (Payment payment : list) {
				loanAmount = loanAmount.add(payment.getCustomerPkId())
						.subtract(payment.getAmount());
			}

			customerRequest.setLoanAmount(loanAmount);
		}

		return employeeRequests;
	}

	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu,
			Date beginDate, Date endDate, BigDecimal subOrganizationPkId,
			boolean self) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", lu.getOrganization().getPkId());
		parameters.put("userPkId", lu.getUser().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);

		jpql.append("SELECT NEW hospital.businessentity.CustomerRequest(a, b, c, d.name) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON a.customerPkId = c.pkId ");
		jpql.append("INNER JOIN SubOrganization d ON b.subOrganizationPkId = d.pkId ");
		jpql.append("WHERE a.date BETWEEN :beginDate AND :endDate ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");

		if (subOrganizationPkId.compareTo(BigDecimal.ZERO) == 0) {

		} else {
			parameters.put("subOrganizationPkId", subOrganizationPkId);
			jpql.append("AND b.subOrganizationPkId = :subOrganizationPkId ");
		}
		if (self == true) {
			List<Employee> employees = getByAnyField(Employee.class,
					"userPkId", lu.getUser().getPkId());
			List<BigDecimal> pkIds = new ArrayList<BigDecimal>();
			for (Employee employee : employees) {
				pkIds.add(employee.getPkId());
			}
			parameters.put("pkIds", pkIds);
			if (pkIds.size() > 0) {
				jpql.append("AND a.employeePkId IN :pkIds ");
			}
		}

		List<CustomerRequest> employeeRequests = getByQuery(
				CustomerRequest.class, jpql.toString(), parameters);

		for (CustomerRequest customerRequest : employeeRequests) {
			jpql = new StringBuilder();
			parameters.put("requestPkId", customerRequest.getEmployeeRequest()
					.getPkId());
			jpql.append("SELECT a FROM Inspection a WHERE a.requestPkId = :requestPkId");
			List<Inspection> inspections = getByQuery(Inspection.class,
					jpql.toString(), parameters);
			if (inspections.size() > 0)
				customerRequest.setInspection(inspections.get(0));
		}

		return employeeRequests;
	}

	@Override
	public List<Treatment> getTreatments(LoggedUser lu,
			BigDecimal treatmentTypePkId) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		parameters.put("organizationPkId", lu.getOrganization().getPkId());

		parameters.put("treatmentTypePkId", treatmentTypePkId);
		jpql.append("SELECT NEW hospital.entity.Treatment(a.pkId, a.id, a.organizationPkId, a.treatmentTypePkId, a.name, c.price, c.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, a.cost ,  COUNT(b.pkId))  ");
		jpql.append("FROM Treatment a ");
		jpql.append("LEFT JOIN TreatmentDtl b ON a.pkId = b.treatmentPkId ");
		jpql.append("LEFT JOIN View_Treatment c ON c.treatmentPkId = a.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId   ");
		jpql.append("AND a.treatmentTypePkId = :treatmentTypePkId ");
		jpql.append("GROUP BY a.pkId, a.id, a.organizationPkId, a.treatmentTypePkId, a.name, c.price, c.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy , a.cost ");
		List<Treatment> list = new ArrayList<Treatment>();
		list = getByQuery(Treatment.class, jpql.toString(), parameters);
		return list;
	}
	
	@Override
	public List<Treatment> getTreatments(LoggedUser lu,
			String treatmentSearchString) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		parameters.put("organizationPkId", lu.getOrganization().getPkId());

		parameters.put("treatmentSearchString", "%"+treatmentSearchString+"%");
		jpql.append("SELECT NEW hospital.entity.Treatment(a.pkId, a.id, a.organizationPkId, a.treatmentTypePkId, a.name, c.price, c.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, a.cost ,  COUNT(b.pkId))  ");
		jpql.append("FROM Treatment a ");
		jpql.append("LEFT JOIN TreatmentDtl b ON a.pkId = b.treatmentPkId ");
		jpql.append("LEFT JOIN View_Treatment c ON c.treatmentPkId = a.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId   ");
		jpql.append("AND (a.id LIKE :treatmentSearchString OR a.name LIKE :treatmentSearchString) ");
		jpql.append("GROUP BY a.pkId, a.id, a.organizationPkId, a.treatmentTypePkId, a.name, c.price, c.beginDate, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy , a.cost ");
		List<Treatment> list = new ArrayList<Treatment>();
		list = getByQuery(Treatment.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<SubOrganizationType> getSubOrganizationTypes() throws Exception {
		// Кабинетийн төрлийн жагсаалт
		return getAll(SubOrganizationType.class);
	}

	@Override
	public List<Measurement> getMeasurements() throws Exception {
		// Хэмжих нэгжийн жагсаалт
		return getAll(Measurement.class);
	}

	@Override
	public List<TreatmentPrice> getTreatmentPrices(BigDecimal treatmentPkId)
			throws Exception {
		List<TreatmentPrice> list = new ArrayList<TreatmentPrice>();
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("treatmentPkId", treatmentPkId);
		jpql.append("SELECT NEW hospital.entity.TreatmentPrice (a.price, a.beginDate, a.updatedDate, b.id, c.name ) ");
		jpql.append("FROM TreatmentPrice a ");
		jpql.append("INNER JOIN Users b ON a.updatedBy = b.pkId ");
		jpql.append("INNER JOIN Treatment c ON a.treatmentPkId = c.pkId ");
		jpql.append("WHERE a.treatmentPkId = :treatmentPkId ");
		list = getByQuery(TreatmentPrice.class, jpql.toString(), parameters);

		return list;
	}

	@Override
	public List<Employee> getEmployees(BigDecimal sotPkId) throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("sotPkId", sotPkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a  ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b on a.subOrganizationPkId = b.pkId ");
		jpql.append("WHERE b.subOrganizationTypePkId =:sotPkId ");
		list = getByQuery(Employee.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<ICT19Dtl> getTreatmentICT() throws Exception {
		CustomHashMap map = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  NEW hospital.businessentity.ICT19Dtl(A.pkId,A.id,A.nameEn,A.nameMn) FROM ICT19  A ");
		jpql.append("ORDER BY A.id ASC ");
		return getByQuery(ICT19Dtl.class, jpql.toString(), map);
	}

	@Override
	public void saveTreatmentIcdMap(TreatmentIcdMap icdmap, BigDecimal treatmentPkId, List<ICT19Dtl> listict)
			throws Exception {
		BigDecimal pkId = Tools.newPkId();
		for (ICT19Dtl ict19 : listict){
			TreatmentIcdMap map = new TreatmentIcdMap();
			if (ict19.getPkId()!=null){
				if (Tool.ADDED.equals(icdmap.getStatus())){
					pkId = pkId.add(BigDecimal.ONE);
					map.setPkId(pkId);
					map.setTreatmentPkId(treatmentPkId);
					map.setIcdPkId(ict19.getPkId());
					map.setCreatedDate(new Date());
					insert(map);
				}
				else if (Tool.MODIFIED.equals(icdmap.getStatus())) {
					update(map);
				}
			}
		}
	}

	@Override
	public List<ICT19Dtl> getIctDtlView(BigDecimal currentpkId) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("pkId", currentpkId);
		StringBuilder  builder  =  new StringBuilder();
		builder.append("SELECT NEW hospital.businessentity.ICT19Dtl(A,B.id,B.nameEn,B.nameMn)  FROM TreatmentIcdMap A ");
		builder.append("INNER JOIN ICT19  B  ON A.icdPkId  = B.pkId ");
		builder.append("WHERE  A.treatmentPkId = :pkId ");
		return getByQuery(ICT19Dtl.class ,builder.toString(), map);
	}

	@Override
	public void saveTreatmentModel(TreatmentIctModel ictModel, List<ICT19Dtl> ict19Dtl, String name, boolean isModel)
			throws Exception {
		if (isModel == true){
			if (Tool.ADDED.equals(ictModel.getStatus())){
				for (ICT19Dtl ictDtl : ict19Dtl){
					TreatmentIctModel trModel = new TreatmentIctModel();
					trModel.setPkId(Tools.newPkId());
					trModel.setTreatmentMapPkId(ictDtl.getTreatmentIctMap().getPkId());
					trModel.setTreatmentName(name);
					trModel.setCreatedDate(new Date());
					insert(trModel);
				}
			} else if (Tool.MODIFIED.equals(ictModel.getStatus())){
				for (ICT19Dtl ictDtl : ict19Dtl){
					deleteByAnyField(TreatmentIctModel.class, "treatmentMapPkId", ictDtl.getPkId());
					TreatmentIctModel trModel = new TreatmentIctModel();
					trModel.setPkId(Tools.newPkId());
					trModel.setTreatmentName(name);
					trModel.setTreatmentMapPkId(ictDtl.getTreatmentIctMap().getPkId());
					trModel.setCreatedDate(new Date());
					insert(trModel);
				}
			}
		}
		
	}

	@Override
	public List<ICT19Dtl> getTreatmentIctModelView() throws Exception {
		
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.ICT19Dtl(b.treatmentName) ");
		jpql.append("FROM TreatmentIcdMap a ");
		jpql.append("INNER JOIN TreatmentIctModel b on b.treatmentMapPkId = a.pkId ");
		jpql.append("GROUP BY b.treatmentName ");
		return getByQuery(ICT19Dtl.class, jpql.toString(), null);
	}

	@Override
	public List<ICT19Dtl> getModelNameSearch(String name) throws Exception {
		CustomHashMap map = new CustomHashMap();
		map.put("treatmentName", name);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.businessentity.ICT19Dtl(a,b,c) ");
		jpql.append("FROM TreatmentIcdMap a ");
		jpql.append("INNER JOIN TreatmentIctModel b on b.treatmentMapPkId = a.pkId ");
		jpql.append("INNER JOIN ICT19 c on c.pkId = a.icdPkId ");
		jpql.append("WHERE b.treatmentName = :treatmentName ");
		return getByQuery(ICT19Dtl.class, jpql.toString(), map);
	}

}

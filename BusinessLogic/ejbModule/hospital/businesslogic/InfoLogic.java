package hospital.businesslogic;

import hospital.businessentity.CustomerRequest;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IInfoLogicLocal;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "InfoLogic", mappedName = "hospital.businesslogic.InfoLogic")
public class InfoLogic extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IInfoLogic, IInfoLogicLocal {

	@Resource
	SessionContext sessionContext;

	public InfoLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	// Davaadorj

	@Override
	public List<Aimag> getAimags() throws Exception {

		List<Aimag> list = new ArrayList<Aimag>();
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Aimag a ");
		jpql.append("ORDER BY a.insuranceCode ASC ");
		list = getByQuery(Aimag.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Soum> getSoums() throws Exception {
		List<Soum> list = new ArrayList<Soum>();
		list = getAll(Soum.class);
		return list;
	}

	@Override
	public List<Soum> getSoums(BigDecimal aimagPkId) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("aimagPkId", aimagPkId);
		jpql.append("SELECT a FROM Soum a WHERE a.aimagPkId = :aimagPkId");

		List<Soum> list = getByQuery(Soum.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<Organization> getOrganizations() throws Exception {

		List<Organization> list = new ArrayList<Organization>();
		list = getAll(Organization.class);
		return list;
	}

	@Override
	public void saveOrganization(Organization organization) throws Exception {

		if (Tool.ADDED.equals(organization.getStatus())) {
			organization.setPkId(Tools.newPkId());
			insert(organization);
		} else if (Tool.MODIFIED.equals(organization.getStatus())) {
			update(organization);
		} else if (Tool.DELETE.equals(organization.getStatus())) {
			deleteByPkId(Organization.class, organization.getPkId());
		}
	}

	@Override
	public List<Menu> getMenus() throws Exception {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Menu a ");

		List<Menu> list = getByQuery(Menu.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Menu> getMenusFilter(LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		BigDecimal bigDecimal = loggedUser.getUser().getPkId();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("bigDecimal", bigDecimal);
		jpql = new StringBuilder();
		jpql.append("SELECT b.menuPkId FROM UserRoleMap a ");
		jpql.append("INNER JOIN RoleMenuMap b ON a.rolePkId = b.rolePkId ");
		jpql.append("WHERE a.userPkId = :bigDecimal ");

		List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class,
				jpql.toString(), parameters);
		parameters.put("list", bigDecimals);

		jpql = new StringBuilder();
		jpql.append("SELECT a FROM Menu a ");
		jpql.append("WHERE a.pkId NOT IN :list ");

		List<Menu> list = getByQuery(Menu.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<Menu> getMenus(LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		BigDecimal bigDecimal = loggedUser.getUser().getPkId();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("bigDecimal", bigDecimal);
		jpql = new StringBuilder();
		jpql.append("SELECT b.menuPkId FROM UserRoleMap a ");
		jpql.append("INNER JOIN RoleMenuMap b ON a.rolePkId = b.rolePkId ");
		jpql.append("WHERE a.userPkId = :bigDecimal ");

		List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class,
				jpql.toString(), parameters);
		parameters.put("list", bigDecimals);

		jpql = new StringBuilder();
		jpql.append("SELECT a FROM Menu a ");
		jpql.append("WHERE a.pkId IN ( ");
		jpql.append("SELECT MIN(b.pkId) FROM Menu b ");
		jpql.append("GROUP BY b.sort ");
		jpql.append(") AND a.pkId IN :list ");
		jpql.append("ORDER BY a.sort ");
		
		List<Menu> list = getByQuery(Menu.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveRole(Role role) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		BigDecimal pkId = Tools.newPkId();

		// Шинээр нэмэх
		if (Tool.ADDED.equals(role.getStatus())) {
			role.setPkId(pkId);
			if (role.getParentPkId().equals(BigDecimal.ZERO))
				role.setParentPkId(null);

			BigDecimal decimal = Tools.newPkId();
			List<BigDecimal> list = role.getMenuPkIds();
			List<RoleMenuMap> maps = new ArrayList<RoleMenuMap>();
			for (Object menu : list) {
				BigDecimal menuPkId = new BigDecimal(menu.toString());
				RoleMenuMap map = new RoleMenuMap();
				decimal = decimal.add(BigDecimal.ONE);
				map.setPkId(decimal);
				map.setMenuPkId(menuPkId);
				map.setRolePkId(pkId);
				maps.add(map);
			}

			insert(role);
			insert(maps);
			calculateRoleSortString();
		} else if (Tool.MODIFIED.equals(role.getStatus())) {
			// Засах

			if (BigDecimal.ZERO.compareTo(role.getParentPkId()) == 0) {
				role.setParentPkId(null);
			}

			// List<BigDecimal> bigDecimals = role.getMenuPkIds();
			// if(bigDecimals == null || bigDecimals.size() < 1){
			jpql = new StringBuilder();
			parameters.put("rolePkId", role.getPkId());
			jpql.append("DELETE FROM RoleMenuMap a WHERE a.rolePkId = :rolePkId ");

			executeNonQuery(jpql.toString(), parameters);
			// }

			BigDecimal decimal = Tools.newPkId();
			List<BigDecimal> list = role.getMenuPkIds();
			List<RoleMenuMap> maps = new ArrayList<RoleMenuMap>();
			for (Object menu : list) {
				BigDecimal menuPkId = new BigDecimal(menu.toString());
				RoleMenuMap map = new RoleMenuMap();
				decimal = decimal.add(BigDecimal.ONE);
				map.setPkId(decimal);
				map.setMenuPkId(menuPkId);
				map.setRolePkId(role.getPkId());
				maps.add(map);
			}

			update(role);
			insert(maps);
			calculateRoleSortString();
		} else if (Tool.DELETE.equals(role.getStatus())) {
			// Устгах
			deleteRole(role);
		}
	}

	public void deleteRole(Role role) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("pkId", role.getPkId());
		jpql.append("SELECT a FROM Role a WHERE a.parentPkId = :pkId");
		List<Role> list = getByQuery(Role.class, jpql.toString(), parameters);

		for (Role role2 : list) {
			deleteRole(role2);
		}

		deleteByAnyField(RoleMenuMap.class, "rolePkId", role.getPkId());
		deleteByPkId(Role.class, role.getPkId());
	}

	public void calculateRoleSortString() throws Exception {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Role a WHERE a.parentPkId IS NULL");
		List<Role> list = getByQuery(Role.class, jpql.toString(), null);
		int index = 0;
		for (Role role : list) {
			index++;
			role.setSortString(index + "00");
			calculateRoleSortString(role.getPkId(), role.getSortString());
		}
		update(list);
	}

	public void calculateRoleSortString(BigDecimal pkId, String shortStr)
			throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("parentPkId", pkId);
		jpql.append("SELECT a FROM Role a WHERE a.parentPkId = :parentPkId");
		List<Role> list = getByQuery(Role.class, jpql.toString(), parameters);
		int index = Integer.parseInt(shortStr);
		int str = index * 1000 + 100;
		index = 0;
		for (Role role : list) {
			role.setSortString(str + index + "");
			calculateRoleSortString(role.getPkId(), role.getSortString());
			index++;
		}

	}

	public List<BigDecimal> getMenuPkIdByRole(BigDecimal rolePkId)
			throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("rolePkId", rolePkId);
		jpql.append("SELECT a.menuPkId FROM RoleMenuMap a WHERE a.rolePkId = :rolePkId");

		List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class,
				jpql.toString(), parameters);
		return bigDecimals;
	}

	@Override
	public void saveUser(Users user) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();

		if (user.isHasPasswordChanged())
			user.setPassword(Tool.MD5(user.getPassword()));

		if (Tool.ADDED.equals(user.getStatus())) {

			parameters.put("id", user.getId());
			jpql.append("SELECT a.pkId FROM Users a WHERE a.id = :id ");
			List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class,
					jpql.toString(), parameters);
			if (bigDecimals.size() > 0)
				throw new Exception("duplicate");

			user.setPkId(Tools.newPkId());
			List<BigDecimal> decimals = user.getRolePkIds();

			List<UserRoleMap> list = new ArrayList<UserRoleMap>();
			BigDecimal pkId = Tools.newPkId();
			for (BigDecimal bigDecimal : decimals) {
				pkId = pkId.add(BigDecimal.ONE);
				UserRoleMap map = new UserRoleMap();
				map.setPkId(pkId);
				map.setUserPkId(user.getPkId());
				map.setRolePkId(bigDecimal);

				list.add(map);
			}
			insert(user);
			insert(list);
		} else if (Tool.MODIFIED.equals(user.getStatus())) {
			parameters.put("id", user.getId());
			jpql.append("SELECT a.pkId FROM Users a WHERE a.id = :id ");
			List<BigDecimal> bigDecimals = getByQuery(BigDecimal.class,
					jpql.toString(), parameters);
			if (bigDecimals.size() > 1)
				throw new Exception("duplicate");

			deleteByAnyField(UserRoleMap.class, "userPkId", user.getPkId());

			List<BigDecimal> decimals = user.getRolePkIds();

			List<UserRoleMap> list = new ArrayList<UserRoleMap>();
			BigDecimal pkId = Tools.newPkId();
			for (BigDecimal bigDecimal : decimals) {
				pkId = pkId.add(BigDecimal.ONE);
				UserRoleMap map = new UserRoleMap();
				map.setPkId(pkId);
				map.setUserPkId(user.getPkId());
				map.setRolePkId(bigDecimal);

				list.add(map);
			}
			update(user);
			insert(list);
		} else if (Tool.DELETE.equals(user.getStatus())) {
			deleteByAnyField(UserRoleMap.class, "userPkId", user.getPkId());
			deleteByPkId(Users.class, user.getPkId());
		}
	}

	@Override
	public List<Role> getRoles() throws Exception {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM Role a ORDER BY a.sortString");
		List<Role> list = getByQuery(Role.class, jpql.toString(), null);
		List<BigDecimal> rolePkIds = new ArrayList<BigDecimal>();
		for (Role role : list)
			rolePkIds.add(role.getPkId());

		CustomHashMap parameters = new CustomHashMap();
		parameters.put("rolePkIds", rolePkIds);
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.Menu(b.rolePkId, a.name) FROM Menu a ");
		jpql.append("INNER JOIN RoleMenuMap b ON a.pkId = b.menuPkId ");
		jpql.append("WHERE b.rolePkId IN :rolePkIds ");

		List<Menu> menus = getByQuery(Menu.class, jpql.toString(), parameters);

		for (Role role : list) {

			List<Menu> listMenu = new ArrayList<Menu>();
			for (Menu mnu : menus) {
				if (mnu.getRolePkId().compareTo(role.getPkId()) == 0) {
					listMenu.add(mnu);
				}
			}

			for (Menu menu : listMenu) {
				role.setMenuNames(role.getMenuNames() + menu.getName());
				if (listMenu.indexOf(menu) < listMenu.size() - 1)
					role.setMenuNames(role.getMenuNames() + ", ");
			}
		}

		return list;
	}

	@Override
	public List<UserRoleMap> getUserRoleMaps() throws Exception {

		List<UserRoleMap> list = new ArrayList<UserRoleMap>();
		list = getAll(UserRoleMap.class);
		return list;
	}

	// Otgoo
	@Override
	public List<Degree> getDegrees() throws Exception {

		List<Degree> list = new ArrayList<Degree>();
		list = getAll(Degree.class);
		return list;
	}

	@Override
	public List<Employee> getEmployees() throws Exception {

		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT NEW hospital.entity.Employee(a.pkId, a.subOrganizationPkId,b.name, a.lastName, a.firstName, a.regNumber, a.roomNumber, a.degreePkId, a.email,a.id,  a.phone, c.name, a.isInspect, a.isDentist, a.inspectionTime, a.userPkId) ");
		jpql.append("FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b ON a.subOrganizationPkId = b.pkId ");
		jpql.append("INNER JOIN Degree c ON a.degreePkId = c.pkId ");

		List<Employee> list = new ArrayList<Employee>();
		list = getByQuery(Employee.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<SubOrganization> getSubOrganizations() throws Exception {

		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT NEW hospital.entity.SubOrganization(a.pkId, a.organizationPkId, a.roomNumber, a.subOrganizationTypePkId, a.inspectionTime, a.name , count(b.pkId) , c.name )	");
		jpql.append("FROM SubOrganization a ");
		jpql.append("left join Employee b on a.pkId = b.subOrganizationPkId ");
		jpql.append("left join SubOrganizationType c on a.subOrganizationTypePkId = c.pkId ");
		jpql.append("where c.pkId is not null ");
		jpql.append("GROUP BY a.pkId, a.organizationPkId, a.subOrganizationTypePkId, a.name, a.roomNumber, a.inspectionTime, c.name");

		List<SubOrganization> list = new ArrayList<SubOrganization>();

		list = getByQuery(SubOrganization.class, jpql.toString(), null);
		return list;
	}
	
	@Override
	public List<SubOrganization> getListSubOrganization() throws Exception {

		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT DISTINCT a FROM SubOrganization a ");
		jpql.append("INNER JOIN Employee b on a.pkId = b.subOrganizationPkId ");

		List<SubOrganization> list = new ArrayList<SubOrganization>();

		list = getByQuery(SubOrganization.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Users> getUsers(LoggedUser loggedUser) throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("organizationPkId", loggedUser.getOrganization()
				.getPkId());
		jpql.append("SELEC new hospital.entity.Users(a.pkId, a.organizationPkId, a.id, a.name, a.password, a.isActive, b) FROM Users a ");
		jpql.append("INNER JOIN Organization b ON a.organizationPkId = b.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId");
		List<Users> list = new ArrayList<Users>();
		list = getAll(Users.class);
		return list;
	}

	@Override
	public void saveSubOrganization(SubOrganization suborganization,
			LoggedUser lu) throws Exception {

		if (Tool.ADDED.equals(suborganization.getStatus())) {
			suborganization.setPkId(Tools.newPkId());
			suborganization.setOrganizationPkId(lu.getOrganization().getPkId());
			insert(suborganization);
		} else if (Tool.MODIFIED.equals(suborganization.getStatus())) {
			update(suborganization);
		} else if (Tool.DELETE.equals(suborganization.getStatus())) {
			deleteByPkId(SubOrganization.class, suborganization.getPkId());
		}

	}

	@Override
	public void saveEmployee(Employee employee) throws Exception {

		if (Tool.ADDED.equals(employee.getStatus())) {
			employee.setPkId(Tools.newPkId());
			//employee.setId(getEmployeeGeneratedId());
			insert(employee);
		} else if (Tool.MODIFIED.equals(employee.getStatus())) {
			update(employee);
		} else if (Tool.DELETE.equals(employee.getStatus())) {
			deleteByPkId(Employee.class, employee.getPkId());
		}
	}

	@Override
	public List<SubOrganizationType> getSubOrganizationTypes() throws Exception {

		List<SubOrganizationType> list = new ArrayList<SubOrganizationType>();
		list = getAll(SubOrganizationType.class);
		return list;
	}
	
	@Override
	public List<SubOrganizationType> getSubOrganizationTypes1() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT DISTINCT a FROM SubOrganizationType a ");
		jpql.append("INNER JOIN TreatmentType b ON a.pkId = b.subOrganizationTypePkId ");
		
		List<SubOrganizationType> list = getByQuery(SubOrganizationType.class, jpql.toString(), null);

		return list;
	}

	public List<CustomerRequest> getEmployeeRequests(LoggedUser lu,
			Date beginDate, Date endDate) throws Exception {

		int status = 0;

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
	public List<Menu> getListMenus(LoggedUser loggedUser) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();

		parameters.put("userPkId", loggedUser.getUser().getPkId());
		jpql.append("SELECT DISTINCT a FROM Menu a ");
		jpql.append("INNER JOIN RoleMenuMap b ON b.menuPkId = a.pkId ");
		jpql.append("INNER JOIN UserRoleMap c ON c.rolePkId = b.rolePkId ");
		jpql.append("WHERE c.userPkId = :userPkId ");

		return getByQuery(Menu.class, jpql.toString(), parameters);
	}

	public String getEmployeeGeneratedId() throws Exception {
		StringBuilder jpql = new StringBuilder();

		jpql.append("SELECT a FROM Employee a ");
		jpql.append("ORDER BY a.id DESC  ");
		CustomHashMap parameters = new CustomHashMap();

		List<Employee> employees = getByQuery(Employee.class, jpql.toString(),
				parameters, 0, 1);
		if (employees == null || employees.size() < 1)
			return Tool.getEmployeeStringCardNumber("0");

		return Tool.getEmployeeStringCardNumber(employees.get(0).getId());
	}

	@Override
	public List<BigDecimal> getRolePkIds(BigDecimal userPkId) throws Exception {
		List<UserRoleMap> list = getByAnyField(UserRoleMap.class, "userPkId",
				userPkId);
		List<BigDecimal> rolePkIds = new ArrayList<BigDecimal>();
		for (UserRoleMap map : list) {
			rolePkIds.add(map.getRolePkId());
		}
		return rolePkIds;
	}

	public String getCustomerCardNumber() throws Exception {

		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT CASE WHEN a.id < b.id THEN b.id ELSE a.id END FROM EmployeeRequest a INNER JOIN EmployeeRequestHistory b ON a.organizationPkId = b.organizationPkId  ");

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
	public String getGeneratedPkId() throws Exception {
		return getCustomerCardNumber();
	}

	// License

	@Override
	public void saveLicense(SystemConfig config) throws Exception {

		if (Tool.ADDED.equals(config.getStatus())) {
			config.setPkId(Tools.newPkId());
			insert(config);
		} else if (Tool.MODIFIED.equals(config.getStatus())) {
			update(config);
		} else if (Tool.DELETE.equals(config.getStatus())) {
			delete(config);
		}
	}

	@Override
	public SystemConfig getLicense() throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("name", "License");

		jpql.append("SELECT a FROM SystemConfig a WHERE a.name = :name ");
		List<SystemConfig> list = getByQuery(SystemConfig.class,
				jpql.toString(), parameters);
		if (list.size() == 0)
			return null;
		else
			return list.get(0);
	}

	@Override
	public boolean isRelated(BigDecimal pkId, int type) throws Exception {
		boolean ret = false;
		if (type == 1) {
			// SubOrganization
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			List<Employee> list = new ArrayList<Employee>();
			parameters.put("subOrganizationPkId", pkId);
			jpql.append("SELECT a ");
			jpql.append("FROM Employee a ");
			jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ");
			list = getByQuery(Employee.class, jpql.toString(), parameters);
			if (list.size() >= 1)
				ret = false;
			else
				ret = true;

		} else if (type == 2) {
			// Employee
			StringBuilder jpql = new StringBuilder();
			StringBuilder jpql1 = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("employeePkId", pkId);
			List<EmployeeRequest> erList = new ArrayList<EmployeeRequest>();
			List<EmployeeRequestHistory> erHList = new ArrayList<EmployeeRequestHistory>();
			jpql.append("SELECT a ");
			jpql.append("FROM EmployeeRequest a ");
			jpql.append("WHERE a.employeePkId =:employeePkId  ");
			erList = getByQuery(EmployeeRequest.class, jpql.toString(),
					parameters);
			if (erList.size() >= 1) {
				ret = false;
			} else {
				jpql1.append("SELECT a ");
				jpql1.append("FROM EmployeeRequestHistory a ");
				jpql1.append("WHERE a.employeePkId =:employeePkId  ");
				erHList = getByQuery(EmployeeRequestHistory.class,
						jpql1.toString(), parameters);
				if (erHList.size() >= 1)
					ret = false;
				else
					ret = true;
			}

		} else if (type == 3) {
			// User
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("employeePkId", pkId);
			List<Employee> employeeList = new ArrayList<Employee>();
			jpql.append("SELECT a ");
			jpql.append("FROM Employee a ");
			jpql.append("WHERE a.userPkId = :employeePkId ");
			employeeList = getByQuery(Employee.class, jpql.toString(),
					parameters);
			if (employeeList.size() >= 1)
				ret = false;
			else
				ret = true;

		} else if (type == 4) {
			// Customer

		} else if (type == 5) {
			// Treatment
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("treatmentPkId", pkId);
			List<InspectionDtl> idList = new ArrayList<InspectionDtl>();
			jpql.append("SELECT a ");
			jpql.append("FROM InspectionDtl a ");
			jpql.append("WHERE a.typePkId = :treatmentPkId ");
			idList = getByQuery(InspectionDtl.class, jpql.toString(),
					parameters);
			if (idList.size() >= 1)
				ret = false;
			else
				ret = true;

		} else if (type == 6) {
			// Xray
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("xrayPkId", pkId);
			List<InspectionDtl> idList = new ArrayList<InspectionDtl>();
			jpql.append("SELECT a ");
			jpql.append("FROM InspectionDtl a ");
			jpql.append("WHERE a.typePkId = :xrayPkId ");
			idList = getByQuery(InspectionDtl.class, jpql.toString(),
					parameters);
			if (idList.size() >= 1)
				ret = false;
			else
				ret = true;
		}
		return ret;
	}

	@Override
	public List<Medicine> getMedicine(BigDecimal atcPkId, LoggedUser lu,
			String filterKey) throws Exception {
		List<Medicine> list = new ArrayList<Medicine>();
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("atcPkId", atcPkId);
		parameters.put("filter", "%" + filterKey + "%");
		jpql.append("SELECT NEW hospital.entity.Medicine(a.pkId, a.id, a.name, a.iName, a.atcPkId, a.typePkId, a.measurementPkId, a.bioActive, a.minAge, a.maxAge, a.warningMessage, a.description, b.nameMn, c.name, d.name, a.drugDose, a.dayDose, a.dose, a.calcDose, a.calcDrugDose, a.calcType) ");
		jpql.append("FROM Medicine a ");
		jpql.append("LEFT JOIN  View_ConstantATC b ON b.pkId = a.atcPkId ");
		jpql.append("LEFT JOIN  View_ConstantMedicineType c ON c.pkId = a.typePkId ");
		jpql.append("LEFT JOIN  Measurement d ON d.pkId = a.measurementPkId ");
		jpql.append("WHERE (a.name LIKE :filter OR a.iName like :filter ) ");
		jpql.append(" ");
		jpql.append(" ");
		if (atcPkId.compareTo(BigDecimal.ZERO) != 0) {
			jpql.append("AND a.atcPkId =:atcPkId ");
		}
		list = getByQuery(Medicine.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public void saveMedicine(Medicine medicine, LoggedUser lu) throws Exception {
		if (medicine.getStatus().equals(Tool.ADDED)) {
			medicine.setCreatedBy(lu.getUser().getPkId());
			medicine.setCreatedDate(new Date());
			medicine.setUpdatedBy(lu.getUser().getPkId());
			medicine.setUpdatedDate(new Date());
			medicine.setPkId(Tools.newPkId());
			insert(medicine);

		} else if (medicine.getStatus().equals(Tool.MODIFIED)) {
			medicine.setUpdatedBy(lu.getUser().getPkId());
			medicine.setUpdatedDate(new Date());
			update(medicine);
		}

		else if (medicine.getStatus().equals(Tool.DELETE)) {
			deleteByPkId(Medicine.class, medicine.getPkId());
		}

		else if (medicine.getStatus().equals(Tool.UNCHANGED)) {

		}

	}

	@Override
	public List<View_ConstantATC> getAtcs() throws Exception {
		// TODO Auto-generated method stub
		return getAll(View_ConstantATC.class);
	}

	@Override
	public List<View_ConstantMedicineType> getMedicineTypes() throws Exception {
		// TODO Auto-generated method stub
		return getAll(View_ConstantMedicineType.class);
	}

	@Override
	public List<Measurement> getMeasurements() throws Exception {
		// TODO Auto-generated method stub
		return getAll(Measurement.class);
	}

	@Override
	public String getGeneredMedicineId() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a.id FROM Medicine a ");
		jpql.append("ORDER BY a.id DESC ");

		List<String> list = getByQuery(String.class, jpql.toString(), null, 0,
				1);
		String lastCardNumber = (list == null || list.size() < 1) ? "0" : list
				.get(0);
		int cardNumber = Integer.parseInt(lastCardNumber);
		cardNumber++;
		return getCusStringtomerCardNumberString(cardNumber);
	}

	@Override
	public List<Diagnose> getListDiagnose(int selectedPageNumber, String filterKey)
			throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("key", "%" + filterKey + "%");
		jpql.append("SELECT a FROM Diagnose a ");
		jpql.append("WHERE a.id LIKE :key     ");
		jpql.append("OR a.nameMn LIKE :key ");
		jpql.append("OR a.nameEn LIKE :key ");
		jpql.append("OR  a.nameRu LIKE :key ");
		jpql.append("ORDER BY a.id ASC ");

		List<Diagnose> diagnoses = getByQuery(Diagnose.class, jpql.toString(),
				parameters, (selectedPageNumber - 1) * Tool.pageSizeLimit,
				Tool.pageSizeLimit);
		return diagnoses;
	}

	public int getDiagnoseCount() throws Exception {
		Object object = getByQuerySingle("SELECT COUNT(a.id) FROM Diagnose a",
				null);
		Long count = (Long) object;
		return Tool.getPageCount(count.intValue());
	}

	public List<DiagnoseGroup> getListDiagnoseGroup(LoggedUser loggedUser)
			throws Exception {
		return getAll(DiagnoseGroup.class);
	}

	public List<DiagnoseGroup> getListDiagnoseGroup() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM DiagnoseGroup a ");

		List<DiagnoseGroup> diagnoseGroups = getByQuery(DiagnoseGroup.class,
				jpql.toString(), null);
		for (DiagnoseGroup diagnoseGroup : diagnoseGroups) {
			diagnoseGroup.setListDiagnoseGroupDtl(getByAnyField(
					DiagnoseGroupDtl.class, "diagnoseGroupPkId",
					diagnoseGroup.getPkId()));
		}

		return diagnoseGroups;
	}

	public List<String> getDiagnoseTypeList() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM  ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		List<String> list = getByQuery(String.class, jpql.toString(), null);

		return list;
	}

	public void saveDiagnoseGroup(DiagnoseGroup diagnoseGroup,
			List<DiagnoseGroupDtl> diagnoseGroupDtls, LoggedUser loggedUser)
			throws Exception {
		if (Tool.ADDED.equals(diagnoseGroup.getStatus())) {
			Date dte = new Date();
			BigDecimal pkId = Tools.newPkId();
			diagnoseGroup.setPkId(pkId);
			diagnoseGroup.setCreatedDate(dte);
			diagnoseGroup.setCreatedBy(loggedUser.getUser().getPkId());
			diagnoseGroup.setUpdatedDate(dte);
			diagnoseGroup.setUpdatedBy(loggedUser.getUser().getPkId());

			for (DiagnoseGroupDtl diagnoseGroupDtl : diagnoseGroupDtls) {
				pkId = BigDecimal.ONE.add(pkId);
				diagnoseGroupDtl.setPkId(pkId);
				diagnoseGroupDtl.setDiagnoseGroupPkId(diagnoseGroup.getPkId());
			}

			insert(diagnoseGroupDtls);
			insert(diagnoseGroup);
		} else if (Tool.MODIFIED.equals(diagnoseGroup.getStatus())) {
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("diagnoseGroupPkId", diagnoseGroup.getPkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append("DELETE FROM DiagnoseGroupDtl a WHERE a.diagnoseGroupPkId = :diagnoseGroupPkId ");
			executeNonQuery(jpql.toString(), parameters);

			Date dte = new Date();
			BigDecimal pkId = Tools.newPkId();
			for (DiagnoseGroupDtl diagnoseGroupDtl : diagnoseGroupDtls) {
				pkId = BigDecimal.ONE.add(pkId);
				diagnoseGroupDtl.setPkId(pkId);
				diagnoseGroupDtl.setDiagnoseGroupPkId(diagnoseGroup.getPkId());
			}

			insert(diagnoseGroupDtls);
			update(diagnoseGroup);
		} else if (Tool.DELETE.equals(diagnoseGroup.getStatus())) {
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("diagnoseGroupPkId", diagnoseGroup.getPkId());
			StringBuilder jpql = new StringBuilder();
			jpql.append("DELETE FROM DiagnoseGroupDtl a ");
			jpql.append("WHERE a.diagnoseGroupPkId = :diagnoseGroupPkId ");

			executeNonQuery(jpql.toString(), parameters);
			deleteByPkId(DiagnoseGroup.class, diagnoseGroup.getPkId());
		}
	}

	@Override
	public List<Medicine> getAllMedicines() throws Exception {

		return getAll(Medicine.class);
	}

	@Override
	public List<Diagnose> getDiagnoses(String diagnoseName) throws Exception {

		List<Diagnose> list = new ArrayList<Diagnose>();
		if (!diagnoseName.isEmpty() || diagnoseName != null
				|| !diagnoseName.equals("") || !diagnoseName.equals(" ")) {
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("diagnoseName", "%" + diagnoseName + "%");
			jpql.append("SELECT a ");
			jpql.append("FROM Diagnose a ");
			jpql.append("WHERE a.id LIKE :diagnoseName ");
			list = getByQuery(Diagnose.class, jpql.toString(), parameters);

		}
		return list;
	}
	@Override
	public List<Diagnose> getDiagnoses(int first, int count, String sortField, String sortType, Map<String, String> filters) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		
		jpql.append("SELECT a ");
		jpql.append("FROM Diagnose a ");
		
		if(!filters.isEmpty()){
			jpql.append("WHERE ");
			for(Map.Entry<String, String> filter: filters.entrySet()){
				parameters.put(filter.getKey(), "%" + filter.getValue() + "%");
				filter.getValue();
				jpql.append("a." + filter.getKey() + " LIKE :" + filter.getKey() + " AND ");
			}
			jpql.delete(jpql.length() - 4, jpql.length());
		}
		if(sortField != null && !sortField.isEmpty() && sortType != null && !sortType.isEmpty()){
			jpql.append("ORDER BY a." + sortField + " " + sortType + " ");
		}
		return getByQuery(Diagnose.class, jpql.toString(), parameters, first, count);
	}
	
	@Override
	public List<Customer> getCustomers(int first, int count, String sortField, String sortType, Map<String, String> filters) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		
		jpql.append("SELECT a ");
		jpql.append("FROM Customer a ");
		
		if(!filters.isEmpty()){
			jpql.append("WHERE ");
			for(Map.Entry<String, String> filter: filters.entrySet()){
				parameters.put(filter.getKey(), "%" + filter.getValue() + "%");
				filter.getValue();
				jpql.append("a." + filter.getKey() + " LIKE :" + filter.getKey() + " AND ");
			}
			jpql.delete(jpql.length() - 4, jpql.length());
		}
		if(sortField != null && !sortField.isEmpty() && sortType != null && !sortType.isEmpty()){
			jpql.append("ORDER BY a." + sortField + " " + sortType + " ");
		}
		return getByQuery(Customer.class, jpql.toString(), parameters, first, count);
	}
	
	@Override
	public long getDiagnosesCount(Map<String, String> filters) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		jpql.append("SELECT COUNT(a.id) ");
		jpql.append("FROM Diagnose a ");
		
		if(!filters.isEmpty()){
			jpql.append("WHERE ");
			for(Map.Entry<String, String> filter: filters.entrySet()){
				parameters.put(filter.getKey(), "%" + filter.getValue() + "%");
				filter.getValue();
				jpql.append("a." + filter.getKey() + " LIKE :" + filter.getKey() + " AND ");
			}
			jpql.delete(jpql.length() - 4, jpql.length());
		}
		
		return (Long) getByQuerySingle(jpql.toString(), parameters);
	}
	
	@Override
	public long getCustomerCount(Map<String, String> filters) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		jpql.append("SELECT COUNT(a.pkId) ");
		jpql.append("FROM Customer a ");
		
		if(!filters.isEmpty()){
			jpql.append("WHERE ");
			for(Map.Entry<String, String> filter: filters.entrySet()){
				parameters.put(filter.getKey(), "%" + filter.getValue() + "%");
				filter.getValue();
				jpql.append("a." + filter.getKey() + " LIKE :" + filter.getKey() + " AND ");
			}
			jpql.delete(jpql.length() - 4, jpql.length());
		}
		
		return (Long) getByQuerySingle(jpql.toString(), parameters);
	}

	@Override
	public UserConfig getPanelPositions(LoggedUser lu) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("userPkId", lu.getUser().getPkId());
		jpql.append("SELECT a ");
		jpql.append("FROM UserConfig a WHERE a.userPkId = :userPkId");
		
		List<UserConfig> userConfig = getByQuery(UserConfig.class, jpql.toString(), parameters);
		if(userConfig.size() > 0)
			return userConfig.get(0);
		else 
			return new UserConfig();
	}

	@Override
	public void savePanelPositions(UserConfig userConfig, LoggedUser lu)
			throws Exception {
		if(userConfig.getPkId() != null)
			update(userConfig);
		else {
			userConfig.setPkId(Tools.newPkId());
			userConfig.setUserPkId(lu.getUser().getPkId());
			insert(userConfig);
		}
	}


	@Override
	public List<TreatmentType> getTreatmentTypes() throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		jpql.append("SELECT a ");
		jpql.append("FROM TreatmentType a");
		
		return getByQuery(TreatmentType.class, jpql.toString(), parameters);
	}

	@Override
	public List<SurgeryType> getSurgeryTypes() throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		jpql.append("SELECT a ");
		jpql.append("FROM SurgeryType a");
		
		return getByQuery(SurgeryType.class, jpql.toString(), parameters);
	}
	public List<Organization> getMenuCount(BigDecimal id) throws Exception {
		StringBuilder builder = new StringBuilder();
		CustomHashMap map = new CustomHashMap();
		map.put("keyId", id);
		builder.append(" SELECT  o  FROM Organization o  ");
		builder.append(" WHERE  o.pkId = :keyId ");
		builder.append(" ");
		
		return getByQuery(Organization.class, builder.toString(),map);
	}

	@Override
	public boolean checkInspectionPassword(BigDecimal employeePkId, String inspectionPassword) throws Exception {
		LoggedUser user = new LoggedUser();

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("employeePkId", employeePkId);
		parameters.put("inspectionPassword", inspectionPassword);

		jpql.append("SELECT A FROM Employee A ");
		jpql.append(" WHERE A.pkId = :employeePkId AND A.inspectionPassword = :inspectionPassword ");

		List<LoggedUser> list = getByQuery(LoggedUser.class, jpql.toString(), parameters);
		if(list.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public List<Treatment> getTreatmentLsit(String search) throws Exception {
		CustomHashMap map  =  new  CustomHashMap();
		map.put("search",  "%" + search +"%");
		StringBuilder  builder   =  new StringBuilder();
		builder.append("SELECT A FROM  Treatment A ");
		builder.append("WHERE (A.name  LIKE :search  OR A.id LIKE :search )");
		return getByQuery(Treatment.class, builder.toString(),map);
	}
}

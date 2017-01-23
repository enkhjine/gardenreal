package hospital.businesslogic;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.IUserLogicLocal;
import hospital.entity.Employee;
import hospital.entity.Menu;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import logic.data.CustomHashMap;

@Stateless(name = "UserLogic", mappedName = "hospital.businesslogic.UserLogic")
public class UserLogic extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IUserLogic, IUserLogicLocal {

	@Resource
	SessionContext sessionContext;

	public UserLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public void logicTest() throws Exception {
		List<Menu> menus = new ArrayList<Menu>();
		menus = getAll(Menu.class);
		System.out.println(menus.size());
	}

	@Override
	public LoggedUser login(LoggedUser loggedUser) throws Exception {

		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("id", loggedUser.getUsername());
		parameters.put("password", Tool.MD5(loggedUser.getPassword()));

		System.out.println("MD% : " + Tool.MD5(loggedUser.getPassword()));

		jpql.append("SELECT NEW hospital.businessentity.LoggedUser(a, b) FROM Users a ");
		jpql.append("INNER JOIN Organization b ON b.pkId = a.organizationPkId ");

		jpql.append(" WHERE a.id = :id AND a.password = :password ");

		List<LoggedUser> list = getByQuery(LoggedUser.class, jpql.toString(), parameters);

		if (list.size() < 1) {
			throw new Exception("NONE");
		} 
		List<Employee> employees = getByAnyField(Employee.class, "userPkId", list.get(0).getUser().getPkId());
		if(employees.size() > 0) {
			list.get(0).setEmployee(employees.get(0));
		}else {
			throw new Exception("Тухайн хэрэглэгч дээр ажилтан тохируулаагүй байна.");
		}

		return list.get(0);
	}

}

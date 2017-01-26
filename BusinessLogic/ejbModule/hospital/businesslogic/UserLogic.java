package hospital.businesslogic;

import hospital.businesslogic.interfaces.IUserLogicLocal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

@Stateless(name = "UserLogic", mappedName = "hospital.businesslogic.UserLogic")
public class UserLogic extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IUserLogic, IUserLogicLocal {

	@Resource
	SessionContext sessionContext;

	public UserLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Garden"
	}

}

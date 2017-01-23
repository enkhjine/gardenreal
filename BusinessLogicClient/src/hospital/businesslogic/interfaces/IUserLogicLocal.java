package hospital.businesslogic.interfaces;

import hospital.businessentity.LoggedUser;

import javax.ejb.Local;

@Local
public interface IUserLogicLocal {
	public void logicTest() throws Exception;
	public LoggedUser login(LoggedUser loggedUser) throws Exception;
}

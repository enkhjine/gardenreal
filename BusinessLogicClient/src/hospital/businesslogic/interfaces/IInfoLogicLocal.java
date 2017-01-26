package hospital.businesslogic.interfaces;

import java.util.List;

import javax.ejb.Local;

import garden.businessentity.LoggedInfo;
import garden.entity.OrtsCategory;

@Local
public interface IInfoLogicLocal {
	public void saveOrtsCategory(OrtsCategory category, LoggedInfo loggedInfo) throws Exception;
	public List<OrtsCategory> getListOrtsCategory() throws Exception;
}

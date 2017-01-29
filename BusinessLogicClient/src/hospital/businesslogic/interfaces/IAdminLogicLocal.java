package hospital.businesslogic.interfaces;

import java.util.List;

import javax.ejb.Local;

import garden.businessentity.LoggedInfo;
import garden.entity.Company;
import garden.entity.Garden;
import garden.entity.GardenUser;

@Local
public interface IAdminLogicLocal {
	public List<Company> getListCompany() throws Exception;
	public List<Garden> getListGarden() throws Exception;
	public List<GardenUser> gerListGardenUser() throws Exception;
	
	public void saveCompany(Company company, LoggedInfo loggedInfo) throws Exception;
	public void saveGarden(Garden garden, LoggedInfo loggedInfo) throws Exception;
	public void saveGardenUser(GardenUser gardenUser, LoggedInfo loggedInfo) throws Exception;
	
}

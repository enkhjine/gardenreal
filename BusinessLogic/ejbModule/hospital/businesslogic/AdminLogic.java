package hospital.businesslogic;

import hospital.businesslogic.interfaces.IAdminLogicLocal;
import hospital.businesslogic.interfaces.IUserLogicLocal;
import logic.data.Tools;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import garden.businessentity.LoggedInfo;
import garden.businessentity.Tool;
import garden.entity.Company;
import garden.entity.Garden;
import garden.entity.GardenUser;

@Stateless(name = "AdminLogic", mappedName = "hospital.businesslogic.AdminLogic")
public class AdminLogic extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.IAdminLogic, IAdminLogicLocal {

	@Resource
	SessionContext sessionContext;

	public AdminLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Garden"
	}

	@Override
	public List<Company> getListCompany() throws Exception {
		return getAll(Company.class);
	}

	@Override
	public List<Garden> getListGarden() throws Exception {
		return getAll(Garden.class);
	}

	@Override
	public List<GardenUser> gerListGardenUser() throws Exception {
		return getAll(GardenUser.class);
	}

	@Override
	public void saveCompany(Company company, LoggedInfo loggedInfo) throws Exception {
		Date now = new Date();
		if (Tool.ADDED.equals(company.getStatus())) {
			company.setPkId(Tools.newPkId());
			company.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			company.setCreatedDate(now);
			insert(company);

		} else if (Tool.MODIFIED.equals(company.getStatus())) {
			company.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			company.setUpdatedDate(now);
			update(company);

		} else if (Tool.DELETE.equals(company.getStatus())) {
			deleteByPkId(Company.class, company.getPkId());

		}

	}

	@Override
	public void saveGarden(Garden garden, LoggedInfo loggedInfo) throws Exception {
		Date now = new Date();
		if (Tool.ADDED.equals(garden.getStatus())) {
			garden.setPkId(Tools.newPkId());
			garden.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			garden.setCreatedDate(now);
			insert(garden);

		} else if (Tool.MODIFIED.equals(garden.getStatus())) {
			garden.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			garden.setUpdatedDate(now);
			update(garden);

		} else if (Tool.DELETE.equals(garden.getStatus())) {
			deleteByPkId(Garden.class, garden.getPkId());
		}

	}

	@Override
	public void saveGardenUser(GardenUser gardenUser, LoggedInfo loggedInfo) throws Exception {
		if (Tool.ADDED.equals(gardenUser.getStatus())) {

		} else if (Tool.ADDED.equals(gardenUser.getStatus())) {

		} else if (Tool.ADDED.equals(gardenUser.getStatus())) {

		}

	}

}

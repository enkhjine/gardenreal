package hospital.businesslogic;

import hospital.businesslogic.interfaces.IInfoLogicLocal;
import logic.data.Tools;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import garden.businessentity.LoggedInfo;
import garden.entity.OrtsCategory;

@Stateless(name = "InfoLogic", mappedName = "hospital.businesslogic.InfoLogic")
public class InfoLogic extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IInfoLogic, IInfoLogicLocal {

	@Resource
	SessionContext sessionContext;

	public InfoLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Garden"
	}
	
	
	public void saveOrtsCategory(OrtsCategory category, LoggedInfo loggedInfo) throws Exception{
		Date dte = new Date();
		if(BigDecimal.ZERO.compareTo(category.getPkId()) == 0){
			category.setPkId(Tools.newPkId());
			category.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			category.setCreatedDate(dte);
			insert(category);
		}else {
			category.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			category.setUpdatedDate(dte);
			update(category);
		}
	}
	
	public List<OrtsCategory> getListOrtsCategory() throws Exception{
		return getAll(OrtsCategory.class);
	}
	
	
}

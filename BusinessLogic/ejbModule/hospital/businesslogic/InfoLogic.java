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
import garden.businessentity.Tool;
import garden.entity.OrtsCategory;
import garden.entity.OrtsSize;

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
		if(Tool.ADDED.equals(category.getStatus())){
			category.setPkId(Tools.newPkId());
			category.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			category.setCreatedDate(dte);
			insert(category);
		}
		if(Tool.MODIFIED.equals(category.getStatus())){
			category.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			category.setUpdatedDate(dte);
			update(category);
		}
		if(Tool.DELETE.equals(category.getStatus())){
			delete(category);
		}
	}
	
	public List<OrtsCategory> getListOrtsCategory() throws Exception{
		return getAll(OrtsCategory.class);
	}
	
	public OrtsCategory getOrtsCategory(BigDecimal ortsCategoryPkId) throws Exception{
		return getByPkId(OrtsCategory.class, ortsCategoryPkId);
	}
	
	public List<OrtsSize> getListOrtsSize() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM OrtsSize a ");
		jpql.append("ORDER BY a.orderStr ASC ");
		
		List<OrtsSize> listOrtsSize = getByQuery(OrtsSize.class, jpql.toString(), null);
		return listOrtsSize;
	}
	
	
}

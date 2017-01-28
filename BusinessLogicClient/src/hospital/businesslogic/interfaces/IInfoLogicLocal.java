package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import garden.businessentity.LoggedInfo;
import garden.entity.OrtsCategory;
import garden.entity.OrtsSize;

@Local
public interface IInfoLogicLocal {
	public void saveOrtsCategory(OrtsCategory category, LoggedInfo loggedInfo) throws Exception;
	public List<OrtsCategory> getListOrtsCategory() throws Exception;
	public OrtsCategory getOrtsCategory(BigDecimal ortsCategoryPkId) throws Exception;
	public List<OrtsSize> getListOrtsSize() throws Exception;
}

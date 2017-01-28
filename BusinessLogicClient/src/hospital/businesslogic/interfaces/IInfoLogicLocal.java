package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import garden.businessentity.LoggedInfo;
import garden.entity.Orts;
import garden.entity.OrtsCategory;
import garden.entity.OrtsSize;

@Local
public interface IInfoLogicLocal {
	public void saveOrtsCategory(OrtsCategory category, LoggedInfo loggedInfo) throws Exception;
	public List<OrtsCategory> getListOrtsCategory() throws Exception;
	public OrtsCategory getOrtsCategory(BigDecimal ortsCategoryPkId) throws Exception;
	public void saveOrtsSize(OrtsSize category, LoggedInfo loggedInfo) throws Exception;
	public List<OrtsSize> getListOrtsSize() throws Exception;
	public OrtsSize getOrtsSize(BigDecimal ortsSizePkId) throws Exception;
	public void saveOrts(Orts orts, LoggedInfo loggedInfo) throws Exception;
	public List<Orts> getListOrts() throws Exception;
}

package hospital.businesslogic.interfaces;


import garden.businessentity.LoggedInfo;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.PlanCategory;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;


@Local
public interface IPlanLogicLocal {
	public List<PlanCategory> getListPlanCategory(LoggedInfo loggedInfo) throws Exception;
	public List<FoodCategory> getListFoodCategorys() throws Exception;
	public List<Food> getListFood(BigDecimal categoryPkId) throws Exception;
	public List<Food> getListFood() throws Exception;
	public BigDecimal getIlchlegByFoodPkId(BigDecimal foodPkId) throws Exception;
}

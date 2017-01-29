package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.List;

import garden.businessentity.LoggedInfo;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.PlanCategory;
import hospital.businesslogic.interfaces.IPlanLogicLocal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;


@Stateless(name = "PlanLogic", mappedName = "hospital.businesslogic.PlanLogic")
public class PlanLogic extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.IPlanLogic, IPlanLogicLocal {

	@Resource
	SessionContext sessionContext;

	public PlanLogic() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Garden"
	}
	
	public List<PlanCategory> getListPlanCategory(LoggedInfo loggedInfo) throws Exception{
		return getByAnyField(PlanCategory.class, "gardenPkId", loggedInfo.getGarden().getPkId());
	}
	
	public List<FoodCategory> getListFoodCategorys() throws Exception{
		return getAll(FoodCategory.class);
	}
	
	public List<Food> getListFood(BigDecimal categoryPkId) throws Exception{
		return getByAnyField(Food.class, "categoryPkId", categoryPkId);
	}
	
}

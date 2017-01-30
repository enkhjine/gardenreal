package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.List;

import garden.businessentity.LoggedInfo;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.FoodOrts;
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
	
	public BigDecimal getIlchlegByFoodPkId(BigDecimal foodPkId) throws Exception{
		List<FoodOrts> foodOrts = getByAnyField(FoodOrts.class, "foodPkId", foodPkId);
		BigDecimal ret = BigDecimal.ZERO;
		for (FoodOrts food : foodOrts) {
			ret = ret.add(food.getIlchleg());
		}
		return ret;
	}
	
	public List<Food> getListFood(BigDecimal categoryPkId) throws Exception{
		List<Food> foods = getByAnyField(Food.class, "categoryPkId", categoryPkId);
		for (Food food : foods) {
			food.setIlchleg(getIlchlegByFoodPkId(food.getPkId()));
		}
		return foods;
	}
	
	public List<Food> getListFood() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT NEW garden.entity.Food(a, b) FROM Food a ");
		jpql.append("INNER JOIN FoodCategory b ON a.categoryPkId = b.pkId ");
		List<Food> foods = getByQuery(Food.class, jpql.toString(), null);
		for (Food food : foods) {
			food.setIlchleg(getIlchlegByFoodPkId(food.getPkId()));
		}
		return foods;
	}
}

package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import garden.businessentity.LoggedInfo;
import garden.businessentity.PlanDtl;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.FoodOrts;
import garden.entity.FoodPlanPlanCategory;
import garden.entity.PlanCategory;
import hospital.businesslogic.interfaces.IPlanLogicLocal;
import logic.data.CustomHashMap;
import logic.data.Tools;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


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
	
	public List<FoodPlanPlanCategory> getListFoodPlanPlanCategory(Date beginDate, Date endDate, LoggedInfo loggedInfo) throws Exception{
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("gardenPkId", loggedInfo.getGarden().getPkId());
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		
		jpql.append("SELECT NEW garden.entity.FoodPlanPlanCategory(a, b, c) FROM FoodPlanPlanCategory a ");
		jpql.append("INNER JOIN PlanCategory b ON a.planCategoryPkId = b.pkId ");
		jpql.append("INNER JOIN Food c ON a.foodPkId = c.pkId ");
		jpql.append("WHERE a.gardenPkId = :gardenPkId ");
		jpql.append("AND a.date > :beginDate ");
		jpql.append("AND a.date < :endDate ");
		
		return getByQuery(FoodPlanPlanCategory.class, jpql.toString(), parameters);
	}
	
	public void saveFoodPlanPlanCategory(List<PlanDtl> list, LoggedInfo loggedInfo) throws Exception{
		Date dte = new Date();
		BigDecimal pkId = Tools.newPkId();
		for (PlanDtl dtl : list) {
			FoodPlanPlanCategory category = new FoodPlanPlanCategory();
			pkId = pkId.add(BigDecimal.ZERO);
			category.setPkId(pkId);
			category.setPlanCategoryPkId(dtl.getPlanCategoryPkId());
			category.setFoodPkId(dtl.getFoodPkId());
			category.setGardenPkId(loggedInfo.getGarden().getPkId());
			category.setDate(dte);
			category.setIsConfirm((byte)0);
			category.setCreatedDate(dte);
			category.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			category.setUpdatedDate(dte);
			category.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
		}
	}
}

package hospital.businesslogic;

import hospital.businesslogic.interfaces.IInfoLogicLocal;
import logic.data.CustomHashMap;
import logic.data.Tools;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import garden.businessentity.LoggedInfo;
import garden.businessentity.Tool;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.FoodOrts;
import garden.entity.Orts;
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
	
	public String getOrderStr(int count){
		if(count < 10) return "00"+count;
		if(count < 100) return "0"+count;
		return ""+count;
	}
	
	public String getOrtsSizeOrderStr(BigDecimal ortsSizePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		String ret = "";
		jpql.append("SELECT a FROM OrtsSize a ");
		jpql.append("WHERE a.parentPkId IS NULL ");
		List<OrtsSize> list = getByQuery(OrtsSize.class, jpql.toString(), null);
		ret = getOrderStr(list.size()+1);
		if(ortsSizePkId != null) {
			OrtsSize parent = getByPkId(OrtsSize.class, ortsSizePkId);
			if(parent != null) {
				parameters.put("parentPkId", ortsSizePkId);
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM OrtsSize a ");
				jpql.append("WHERE a.parentPkId = :parentPkId ");
				list = getByQuery(OrtsSize.class, jpql.toString(), parameters);
				ret = parent.getOrderStr() + getOrderStr(list.size()+1);
			}
		}
		return ret;
	}
	
	public void updateOrtsSizeOrderStr() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM OrtsSize a ");
		jpql.append("WHERE a.parentPkId IS NULL ");
		List<OrtsSize> list = getByQuery(OrtsSize.class, jpql.toString(), null);
		int index = 0;
		for (OrtsSize ortsSize : list) {
			index++;
			ortsSize.setOrderStr(getOrderStr(index));
			update(ortsSize);
			updateOrtsSizeOrderStr(ortsSize);
		}
	}
	
	public void updateOrtsSizeOrderStr(OrtsSize size) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("parentPkId", size.getPkId());
		jpql.append("SELECT a FROM OrtsSize a ");
		jpql.append("WHERE a.parentPkId = :parentPkId");
		List<OrtsSize> list = getByQuery(OrtsSize.class, jpql.toString(), parameters);
		int index = 0;
		for (OrtsSize ortsSize : list) {
			index++;
			ortsSize.setOrderStr(size.getOrderStr() + getOrderStr(index));
			update(ortsSize);
			updateOrtsSizeOrderStr(ortsSize);
		}
	}
	
	public void saveOrtsSize(OrtsSize size, LoggedInfo loggedInfo) throws Exception{
		Date dte = new Date();
		if(BigDecimal.ZERO.compareTo(size.getParentPkId()) == 0) size.setParentPkId(null);
		if(Tool.ADDED.equals(size.getStatus())){
			size.setPkId(Tools.newPkId());
			size.setOrderStr(getOrtsSizeOrderStr(size.getParentPkId()));
			size.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			size.setCreatedDate(dte);
			insert(size);
		}
		if(Tool.MODIFIED.equals(size.getStatus())){
			size.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			size.setUpdatedDate(dte);
			update(size);
			updateOrtsSizeOrderStr();
		}
		if(Tool.DELETE.equals(size.getStatus())){
			StringBuilder jpql = new StringBuilder();
			CustomHashMap parameters = new CustomHashMap();
			parameters.put("orderStr", size.getOrderStr()+"%");
			jpql.append("DELETE FROM OrtsSize a WHERE a.orderStr LIKE :orderStr ");
			delete(size);
			executeNonQuery(jpql.toString(), parameters);
			updateOrtsSizeOrderStr();
		}
	}
	
	public List<OrtsSize> getListOrtsSize() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM OrtsSize a ");
		jpql.append("ORDER BY a.orderStr ASC ");
		
		List<OrtsSize> listOrtsSize = getByQuery(OrtsSize.class, jpql.toString(), null);
		for (OrtsSize ortsSize : listOrtsSize) {
			int len = ortsSize.getOrderStr().length();
			int count = 3;
			while(count < len){
				List<OrtsSize> list = getByAnyField(OrtsSize.class, "orderStr", ortsSize.getOrderStr().substring(0, count));
				if(list.size() > 0) {
					ortsSize.getParentNames().add(list.get(0).getName());
					ortsSize.getParentPkIds().add(list.get(0).getPkId());
				}
				count += 3;
			}
		}
		return listOrtsSize;
	}
	
	public OrtsSize getOrtsSize(BigDecimal ortsSizePkId) throws Exception{
		return getByPkId(OrtsSize.class, ortsSizePkId);
	}
	
	public void saveOrts(Orts orts, LoggedInfo loggedInfo) throws Exception{
		Date dte = new Date();
		if(Tool.ADDED.equals(orts.getStatus())){
			orts.setPkId(Tools.newPkId());
			orts.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			orts.setCreatedDate(dte);
			insert(orts);
		}
		else if(Tool.DELETE.equals(orts.getStatus())){
			delete(orts);
		}
		else if(Tool.MODIFIED.equals(orts.getStatus())){
			orts.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			orts.setUpdatedDate(dte);
			update(orts);
		}
	}
	public Orts getOrtsByPkId(BigDecimal pkId) throws Exception{
		return getByPkId(Orts.class, pkId);
		
	}
	public List<Orts> getListOrts() throws Exception{
		return getAll(Orts.class);
	}
	
	public List<FoodCategory> getListFoodCategory() throws Exception{
		return getAll(FoodCategory.class);
	}
	
	public void saveFood(Food food, List<FoodOrts> foodOrts, LoggedInfo loggedInfo) throws Exception{
		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		if(Tool.ADDED.equals(food.getStatus())){
			food.setPkId(pkId);
			food.setCreatedBy(loggedInfo.getGardenUser().getPkId());
			food.setCreatedDate(dte);
			
			for (FoodOrts foodOrt : foodOrts) {
				pkId = pkId.add(BigDecimal.ONE);
				foodOrt.setPkId(pkId);
				foodOrt.setFoodPkId(food.getPkId());
				foodOrt.setCreatedBy(loggedInfo.getGardenUser().getPkId());
				foodOrt.setCreatedDate(dte);
				foodOrt.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
				foodOrt.setUpdatedDate(dte);
			}
			
			insert(food);
			insert(foodOrts);
		}
		if(Tool.MODIFIED.equals(food.getStatus())){
			food.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
			food.setUpdatedDate(dte);
			for (FoodOrts foodOrt : foodOrts) {
				pkId = pkId.add(BigDecimal.ONE);
				foodOrt.setPkId(pkId);
				foodOrt.setFoodPkId(food.getPkId());
				foodOrt.setCreatedBy(loggedInfo.getGardenUser().getPkId());
				foodOrt.setCreatedDate(dte);
				foodOrt.setUpdatedBy(loggedInfo.getGardenUser().getPkId());
				foodOrt.setUpdatedDate(dte);
			}
			deleteByAnyField(FoodOrts.class, "foodPkId", food.getPkId());
			update(food);
			insert(foodOrts);
		}
		if(Tool.DELETE.equals(food.getPkId())){
			deleteByAnyField(FoodOrts.class, "foodPkId", food.getPkId());
			delete(food);
		}
	}
	
	public List<Food> getFoodTmp(BigDecimal foodCategoryPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("categoryPkId", foodCategoryPkId);
		
		jpql.append("SELECT NEW garden.entity.Food(a.pkId, a.name, SUM(b.ilchleg), SUM(b.uurag), SUM(b.uuhtos), SUM(b.nuursus), SUM(b.size)) FROM Food a ");
		jpql.append("INNER JOIN FoodOrts b ON a.pkId = b.foodPkId ");
		jpql.append("WHERE a.categoryPkId = :categoryPkId ");
		jpql.append("GROUP BY a.pkId, a.name ");
		
		List<Food> foods = getByQuery(Food.class, jpql.toString(), parameters);
		return foods;
	}
	
	public List<Orts> getOrtsList() throws Exception{
		StringBuilder jpql = new StringBuilder();
		jpql.append("Select New garden.entity.Orts(o,c.name, s.name) from Orts o ");
		jpql.append("left join OrtsCategory c on o.categoryPkId = c.pkId ");
		jpql.append("left join OrtsSize s on o.size = s.pkId");
		
		List<Orts> ortss = getByQuery(Orts.class, jpql.toString(), null);
		return ortss;
	}
	public List<FoodOrts> getFoodOrtsByFoodPkId(BigDecimal foodPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("foodPkId", foodPkId);
		jpql.append("SELECT NEW garden.entity.FoodOrts(a, b) FROM FoodOrts a ");
		jpql.append("INNER JOIN Orts b ON a.ortsPkId = b.pkId ");
		jpql.append("WHERE a.foodPkId = :foodPkId ");
		List<FoodOrts> foodOrts = getByQuery(FoodOrts.class, jpql.toString(), parameters);
		return foodOrts;
	}
	
	public Food getFood(BigDecimal foodPkId) throws Exception{
		return getByPkId(Food.class, foodPkId);
	}
}

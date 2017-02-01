package hospital.web.controller;

import garden.businessentity.Plan;
import garden.businessentity.PlanDtl;
import garden.businessentity.Tool;
import garden.entity.Food;
import garden.entity.FoodCategory;
import garden.entity.FoodPlanPlanCategory;
import garden.entity.PlanCategory;
import hospital.businesslogic.interfaces.IPlanLogicLocal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import mondrian.rolap.BitKey.Big;

import org.apache.velocity.runtime.directive.Foreach;
import org.primefaces.context.RequestContext;

@SessionScoped
@ManagedBean(name = "planController")
public class PlanController implements Serializable {
	
	private static final long serialVersionUID = 71056782762570859L;
	
	@EJB(beanName = "PlanLogic")
	IPlanLogicLocal planLogic;

	@ManagedProperty(value = "#{userController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty(value = "#{applicationController}")
	private ApplicationController applicationController;
	
	public PlanController() {

	}
	
	private List<PlanCategory> listPlanCategory;
	private Date date;
	private Date beginDate;
	private Date endDate;
	private List<Plan> listPlan;
	private PlanDtl dtl;
	private List<FoodCategory> categories;
	private List<Food> foods;
	private List<Food> listFood;
	private FoodCategory category;
	private BigDecimal categoryPkId;
	private List<PlanDtl> listPlanDtlB;
	private List<PlanDtl> listPlanDtl;
	private List<PlanDtl> listPlanDtlTmp;
	
	public String changeDate(){
		listPlanDtlB = null;
		listPlanDtl = null;
		listPlan = null;
		beginDate = null;
		endDate = null;
		return "plan";
	}
	
	public Date getBeginDate() {
		if(beginDate == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(getDate());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			cal.add(Calendar.DAY_OF_WEEK, -7);
		    beginDate = cal.getTime();
		}
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		if(endDate == null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(getDate());
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			cal.add(Calendar.DAY_OF_WEEK, 7);
			endDate = cal.getTime();
		}
		return endDate;
	}
	
	public void dtlDialogShow(PlanDtl dtl){
		System.out.println("DAVAADORJ");
		this.dtl = dtl;
		RequestContext context = RequestContext.getCurrentInstance();
		context.update("@(.dtlDialog)");
		context.execute("PF('dtlDialog').show();");
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ApplicationController getApplicationController() {
		return applicationController;
	}
	
	public void setApplicationController(
			ApplicationController applicationController) {
		this.applicationController = applicationController;
	}
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}
	
	public void setUserSessionController(
			UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}
	
	public List<PlanCategory> getListPlanCategory() {
		if(listPlanCategory == null) {
			try{
				listPlanCategory = planLogic.getListPlanCategory(getUserSessionController().getLoggedInfo());
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listPlanCategory;
	}

	public void setListPlanCategory(List<PlanCategory> listPlanCategory) {
		this.listPlanCategory = listPlanCategory;
	}
	
	public List<Plan> getListPlan() {
		if(listPlan == null) {
			listPlan = new ArrayList<>();
			GregorianCalendar gcal = new GregorianCalendar();
			gcal.setTime(getBeginDate());
			while (!gcal.getTime().after(getEndDate())) {
			    Date d = gcal.getTime();
			    gcal.add(Calendar.DATE, 1);
			    
			    Plan plan = new Plan();
			    plan.setDate(d);
			    
			    for (PlanCategory category : getListPlanCategory()) {
			    	boolean isAdd = false;
			    	for (PlanDtl planDtl : getListPlanDtl()) {
						if(category.getPkId().compareTo(planDtl.getPlanCategoryPkId()) == 0 && Tool.isFairDateByDay(planDtl.getDate(), plan.getDate())) {
							plan.getDtls().add(planDtl);
							isAdd = true;
						}
					}
			    	for(PlanDtl planDtl : getListPlanDtlB()){
			    		if(category.getPkId().compareTo(planDtl.getPlanCategoryPkId()) == 0 && Tool.isFairDateByDay(planDtl.getDate(), plan.getDate())) {
							plan.getDtls().add(planDtl);
							isAdd = true;
						}
			    	}
			    	if(!isAdd){
			    		PlanDtl dtl = new PlanDtl();
						dtl.setDate(plan.getDate());
						dtl.setPlanCategoryPkId(category.getPkId());
						dtl.setPlanCategoryName(category.getName());
						dtl.setValue("төлөвлөөгүй байна ! ! !");
						plan.getDtls().add(dtl);
			    	}
				}
			    
			    listPlan.add(plan);
			}
		}
		return listPlan;
	}
	
	public void setListPlan(List<Plan> listPlan) {
		this.listPlan = listPlan;
	}

	public PlanDtl getDtl() {
		if(dtl == null) dtl = new PlanDtl();
		return dtl;
	}

	public void setDtl(PlanDtl dtl) {
		this.dtl = dtl;
	}
	
	public List<Food> getFoods() {
		if(foods == null && category != null) {
			try{
				foods = planLogic.getListFood(category.getPkId());
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return foods;
	}
	
	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}
	
	public List<FoodCategory> getCategories() {
		if(categories == null) {
			try{
				categories = planLogic.getListFoodCategorys();
				if(categories.size() > 0) setCategory(categories.get(0));
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return categories;
	}
	
	public void setCategories(List<FoodCategory> categories) {
		this.categories = categories;
	}
	
	public FoodCategory getCategory() {
		return category;
	}
	
	public void changeCategory(){
		for (FoodCategory cat : getCategories()) {
			if(cat.getPkId().compareTo(categoryPkId) == 0) setCategory(cat);
		}
	}
	
	public void setCategory(FoodCategory category) {
		foods = null;
		this.category = category;
	}

	public BigDecimal getCategoryPkId() {
		return categoryPkId;
	}

	public void setCategoryPkId(BigDecimal categoryPkId) {
		this.categoryPkId = categoryPkId;
	}
	
	public List<PlanDtl> getListPlanDtl() {
		if(listPlanDtl == null) listPlanDtl = new ArrayList<>();
		return listPlanDtl;
	}
	
	public void setListPlanDtl(List<PlanDtl> listPlanDtl) {
		this.listPlanDtl = listPlanDtl;
	}
	public List<PlanDtl> getListPlanDtlTmp() {
		listPlanDtlTmp = new ArrayList<>();
		for (PlanDtl dtl : getListPlanDtl()) {
			listPlanDtlTmp.add(dtl);
		}
		PlanDtl planDtl = new PlanDtl();
		planDtl.setStatus(Tool.LAST);
		listPlanDtlTmp.add(planDtl);
		return listPlanDtlTmp;
	}
	
	public void setListPlanDtlTmp(List<PlanDtl> listPlanDtlTmp) {
		this.listPlanDtlTmp = listPlanDtlTmp;
	}
	
	public List<Food> getListFood() {
		if(listFood == null) {
			try{
				listFood = planLogic.getListFood();
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listFood;
	}
	
	public void setListFood(List<Food> listFood) {
		this.listFood = listFood;
	}
	
	public String addListPlanDtl(){
		try{
			Food food = null;
			PlanCategory category = null;
			for (Food food2 : getFoods()) {
				if(food2.getPkId().compareTo(getDtl().getFoodPkId()) == 0) food = food2;
			}
			for (PlanCategory planCategory : getListPlanCategory()) {
				if(planCategory.getPkId().compareTo(getDtl().getPlanCategoryPkId()) == 0) category = planCategory;
			}
			if(food != null && category != null) {
				PlanDtl planDtl = new PlanDtl();
				planDtl.setDate(getDtl().getDate());
				planDtl.setValue("\""+food.getName()+"\""+" захиалсан.");
				planDtl.setPlanCategoryPkId(category.getPkId());
				planDtl.setPlanCategoryName(category.getName());
				planDtl.setFoodPkId(food.getPkId());
				planDtl.setFoodName(food.getName());
				planDtl.setIlchleg(food.getIlchleg());
				planDtl.setBackGroundColor("#00ffff");
				planDtl.setColor("#337ab7");
				planDtl.setStatus(Tool.ADDED);
				
				getListPlanDtl().add(planDtl);
				listPlan = null;
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "plan";
	}

	public Date getDate() {
		if(date == null) date = new Date();
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<PlanDtl> getListPlanDtlB() {
		if(listPlanDtlB == null) {
			try{
				listPlanDtlB = new ArrayList<>();
				List<FoodPlanPlanCategory> categories = planLogic.getListFoodPlanPlanCategory(getBeginDate(), getEndDate(), getUserSessionController().getLoggedInfo());
				for (FoodPlanPlanCategory foodPlanPlanCategory : categories) {
					PlanDtl planDtl = new PlanDtl();
					planDtl.setDate(foodPlanPlanCategory.getDate());
					planDtl.setValue("\""+foodPlanPlanCategory.getFoodName()+"\""+" захиалсан");
					planDtl.setPlanCategoryPkId(foodPlanPlanCategory.getPlanCategoryPkId());
					planDtl.setPlanCategoryName(foodPlanPlanCategory.getPlanCategoryName());
					planDtl.setFoodPkId(foodPlanPlanCategory.getFoodPkId());
					planDtl.setFoodName(foodPlanPlanCategory.getFoodName());
					planDtl.setBackGroundColor("GREEN");
					planDtl.setColor("white");
					planDtl.setStatus(Tool.BASIC);
					planDtl.setIlchleg(BigDecimal.ZERO);
					listPlanDtlB.add(planDtl);
				}
			}catch(Exception ex){
				getUserSessionController().showErrorMessage(ex.getMessage());
			}
		}
		return listPlanDtlB;
	}
	
	public void setListPlanDtlB(List<PlanDtl> listPlanDtlB) {
		this.listPlanDtlB = listPlanDtlB;
	}
	
	public String saveFoodPlanPlanCategory(){
		try{
			planLogic.saveFoodPlanPlanCategory(getListPlanDtl(), getUserSessionController().getLoggedInfo());
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "";
	}
}

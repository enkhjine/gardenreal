package hospital.web.controller;

import garden.businessentity.Plan;
import garden.businessentity.PlanDtl;
import garden.entity.Food;
import garden.entity.FoodCategory;
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
	private Date beginDate;
	private Date endDate;
	private List<Plan> listPlan;
	private PlanDtl dtl;
	private List<FoodCategory> categories;
	private List<Food> foods;
	private FoodCategory category;
	private BigDecimal categoryPkId;
	
	public Date getBeginDate() {
		if(beginDate == null) {
			Calendar c = Calendar.getInstance();   // this takes current date
		    c.set(Calendar.DAY_OF_MONTH, 1);
		    beginDate = c.getTime();
		}
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		if(endDate == null){
			Calendar c = Calendar.getInstance();
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
			endDate = c.getTime();
		}
		return endDate;
	}
	
	public String savePlanDtlTmp(){
		try{
			Food food = null;
			for (Food food2 : getFoods()) {
				if(food2.getPkId().compareTo(getDtl().getFoodPkId()) == 0) food = food2;
			}
			if(food != null) {
				getDtl().setFoodPkId(food.getPkId());
				getDtl().setFoodName(food.getName());
				getDtl().setValue("\""+food.getName()+"\""+" захиалсан.");
			}
		}catch(Exception ex){
			getUserSessionController().showErrorMessage(ex.getMessage());
		}
		return "plan";
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
					PlanDtl dtl = new PlanDtl();
					dtl.setDate(plan.getDate());
					dtl.setPlanCategoryPkId(category.getPkId());
					dtl.setPlanCategoryName(category.getName());
					dtl.setValue("захиалаг өгөөгүй байна ! ! !");
					plan.getDtls().add(dtl);
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
	
}

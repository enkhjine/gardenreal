package garden.businessentity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDtl {
	
	private Date date;
	private String value;
	private BigDecimal planCategoryPkId;
	private String planCategoryName;
	private String dateStr;
	private BigDecimal foodPkId;
	private String foodName;
	private String backGroundColor;
	private String color;
	private String status;
	private BigDecimal ilchleg;
	
	public PlanDtl(){
		super();
		this.status = Tool.UNCHANGED;
		this.backGroundColor = "white";
	}

	public Date getDate() {
		if(date == null) date = new Date();
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public BigDecimal getPlanCategoryPkId() {
		return planCategoryPkId;
	}

	public void setPlanCategoryPkId(BigDecimal planCategoryPkId) {
		this.planCategoryPkId = planCategoryPkId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlanCategoryName() {
		return planCategoryName;
	}

	public void setPlanCategoryName(String planCategoryName) {
		this.planCategoryName = planCategoryName;
	}
	
	public String getDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateStr = sdf.format(getDate());
		return dateStr;
	}
	
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public BigDecimal getFoodPkId() {
		return foodPkId;
	}

	public void setFoodPkId(BigDecimal foodPkId) {
		this.foodPkId = foodPkId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getBackGroundColor() {
		return backGroundColor;
	}

	public void setBackGroundColor(String backGroundColor) {
		this.backGroundColor = backGroundColor;
	}
	
	public BigDecimal getIlchleg() {
		if(ilchleg == null) ilchleg = BigDecimal.ZERO;
		return ilchleg;
	}
	
	public void setIlchleg(BigDecimal ilchleg) {
		this.ilchleg = ilchleg;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
}

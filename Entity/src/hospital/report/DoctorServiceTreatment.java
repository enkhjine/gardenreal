package hospital.report;

import java.math.BigDecimal;

public class DoctorServiceTreatment {

	private String id;
	private String name;
	private int allCount;
	private int count;
	private BigDecimal amount;
	
	public DoctorServiceTreatment(){
		super();
	}
	
	public int getAllCount() {
		return allCount;
	}
	
	public void setAllCount(int allCount) {
		this.allCount = allCount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}

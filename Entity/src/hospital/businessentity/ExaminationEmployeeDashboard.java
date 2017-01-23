package hospital.businessentity;

public class ExaminationEmployeeDashboard {
	private String employeeName;
	private long customerCount;
	private long persentageCount;
	
	public ExaminationEmployeeDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ExaminationEmployeeDashboard(String employeeName, long customerCount) {
		super();
		this.employeeName = employeeName;
		this.customerCount = customerCount;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}
	
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public long getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(long customerCount) {
		this.customerCount = customerCount;
	}

	public long getPersentageCount() {
		return persentageCount;
	}

	public void setPersentageCount(long persentageCount) {
		this.persentageCount = persentageCount;
	}
	
}

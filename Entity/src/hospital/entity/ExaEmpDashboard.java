package hospital.entity;

import java.math.BigDecimal;
import java.util.List;

public class ExaEmpDashboard {
	private BigDecimal customerPkId;
	private String employeeName;
	private long customerCount;
	List<ExaminationRequest> requestList;
	private int customerCountTotal;
	
	public ExaEmpDashboard( String employeeName, BigDecimal customerPkId) {
		super();
		this.customerPkId = customerPkId;
		this.employeeName = employeeName;
	}
	
	public ExaEmpDashboard(String employeeName, long customerCount) {
		super();
		this.employeeName = employeeName;
		this.customerCount = customerCount;
	}

	public ExaEmpDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId == null ? BigDecimal.ZERO : customerPkId;
	}
	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
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

	public int getCustomerCountTotal() {
		for (ExaminationRequest e : requestList){
			customerCountTotal = customerCountTotal + 1;
		}
		return customerCountTotal;
	}

	public void setCustomerCountTotal(int customerCountTotal) {
		this.customerCountTotal = customerCountTotal;
	}
	
	
	
}

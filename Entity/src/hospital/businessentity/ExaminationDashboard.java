package hospital.businessentity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExaminationDashboard{

	private static final long serialVersionUID = 1L;
	
	private String examinationName;
	private long customerCount;
	private BigDecimal inAmount;
	private BigDecimal examinationPkId;
	private String employeeName;
	
	private List<ExaminationDashboard> examinations;	
	
	public String getExaminationName() {
		return examinationName;
	}
	public void setExaminationName(String examinationName) {
		this.examinationName = examinationName;
	}
	
	public long getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(long customerCount) {
		this.customerCount = customerCount;
	}	
	
	public BigDecimal getInAmount() {
		return inAmount == null ? BigDecimal.ZERO : inAmount;
	}
	public void setInAmount(BigDecimal inAmount) {
		this.inAmount = inAmount;
	}
	
	public ExaminationDashboard(String examinationName, long customerCount, BigDecimal inAmount) {
		super();
		this.examinationName = examinationName;
		this.customerCount = customerCount;
		this.inAmount = inAmount;
	}
	
	public ExaminationDashboard(String examinationName, long customerCount) {
		super();
		this.examinationName = examinationName;
		this.customerCount = customerCount;
	}
	
	public ExaminationDashboard(String examinationName) {
		super();
		this.examinationName = examinationName;
	}
	
	public ExaminationDashboard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public List<ExaminationDashboard> getExaminations() {
		if(examinations == null) examinations = new ArrayList<>();
		return examinations;
	}
	
	public void setExaminations(List<ExaminationDashboard> examinations) {
		this.examinations = examinations;
	}
	public BigDecimal getExaminationPkId() {
		return examinationPkId;
	}
	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}

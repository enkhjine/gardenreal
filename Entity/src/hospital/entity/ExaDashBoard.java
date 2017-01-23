package hospital.entity;

import java.math.BigDecimal;

public class ExaDashBoard {
	private BigDecimal customerPkId;
	private BigDecimal price;
	private BigDecimal employeePkId;
	private BigDecimal examinationPkId;
	
	public ExaDashBoard(BigDecimal customerPkId, BigDecimal price) {
		super();
		this.customerPkId = customerPkId;
		this.price = price;
	}	
	
	public ExaDashBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getCustomerPkId() {
		return customerPkId == null ? BigDecimal.ZERO : customerPkId;
	}
	public void setCustomerPkId(BigDecimal customerPkId) {
		this.customerPkId = customerPkId;
	}
	public BigDecimal getPrice() {
		return price == null ? BigDecimal.ZERO : price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getEmployeePkId() {
		return employeePkId;
	}

	public void setEmployeePkId(BigDecimal employeePkId) {
		this.employeePkId = employeePkId;
	}

	public BigDecimal getExaminationPkId() {
		return examinationPkId == null ? BigDecimal.ZERO : examinationPkId;
	}

	public void setExaminationPkId(BigDecimal examinationPkId) {
		this.examinationPkId = examinationPkId;
	}	
}

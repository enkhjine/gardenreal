package hospital.businessentity;

import hospital.entity.ViewEmployee;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class IncomeBySubOrga implements Serializable {
	private BigDecimal amount;
	private BigDecimal subOrganizationPkId;
	private String subOrganizationName;
	private List<ViewEmployee> incomeByEmployee;
	private int month;
	
	public IncomeBySubOrga(){
		super();
	}
	public IncomeBySubOrga(BigDecimal amount, String subOrganizationName, BigDecimal subOrganizationPkId){
		super();
		this.amount = amount;
		this.subOrganizationName = subOrganizationName;
		this.subOrganizationPkId = subOrganizationPkId;
		this.incomeByEmployee = new ArrayList<ViewEmployee>();
	}
	public IncomeBySubOrga(BigDecimal amount, String subOrganizationName, BigDecimal subOrganizationPkId, int month){
		super();
		this.amount = amount;
		this.subOrganizationName = subOrganizationName;
		this.subOrganizationPkId = subOrganizationPkId;
		this.month = month;
		this.incomeByEmployee = new ArrayList<ViewEmployee>();
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getSubOrganizationPkId() {
		return subOrganizationPkId;
	}

	public void setSubOrganizationPkId(BigDecimal subOrganizationPkId) {
		this.subOrganizationPkId = subOrganizationPkId;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}
	public List<ViewEmployee> getIncomeByEmployee() {
		if (incomeByEmployee == null)
			incomeByEmployee = new ArrayList<ViewEmployee>();
		return incomeByEmployee;
	}
	public void setIncomeByEmployee(List<ViewEmployee> incomeByEmployee) {
		this.incomeByEmployee = incomeByEmployee;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
}
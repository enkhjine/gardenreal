package hospital.businessentity;

import java.math.BigDecimal;

public class ExaminationTreeNode {
	BigDecimal pkId;
	String name;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

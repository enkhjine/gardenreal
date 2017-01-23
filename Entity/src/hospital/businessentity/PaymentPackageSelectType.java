package hospital.businessentity;

import java.math.BigDecimal;

public class PaymentPackageSelectType {
	private String type;
	private String name;
	private BigDecimal pkId;
	
	public PaymentPackageSelectType(){
		super();
	}
	
	public PaymentPackageSelectType(String type, String name, BigDecimal pkId){
		super();
		this.type = type;
		this.name = name;
		this.pkId = pkId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
}

package hospital.businessentity;

import java.math.BigDecimal;

public class PaymentPackageSelectItem {

	private String type;
	private String id;
	private String name;
	private BigDecimal pkId;
	private BigDecimal price;
	
	public PaymentPackageSelectItem(){
		super();
	}
	
	public PaymentPackageSelectItem(String type, String name){
		super();
		this.type = type;
		this.name = name;
	}
	
	public PaymentPackageSelectItem(String name, BigDecimal pkId){
		super();
		this.name = name;
		this.pkId = pkId;
	}
	
	public PaymentPackageSelectItem(String type, String name, BigDecimal pkId){
		super();
		this.type = type;
		this.name = name;
		this.pkId = pkId;
	}
	
	public PaymentPackageSelectItem(String type, String id, String name, BigDecimal pkId){
		super();
		this.type = type;
		this.id = id;
		this.name = name;
		this.pkId = pkId;
	}
	
	public PaymentPackageSelectItem(String type, String name, BigDecimal pkId, BigDecimal price){
		super();
		this.type = type;
		this.name = name;
		this.pkId = pkId;
		this.price = price;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getPkId() {
		return pkId;
	}
	
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	
	public BigDecimal getPrice() {
		if(price == null) price = BigDecimal.ZERO;
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
}

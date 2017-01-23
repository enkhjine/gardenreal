package hospital.businessentity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsingPriceHistory {
	private String name;
	private BigDecimal pkId;
	private Date priceUsageDate;
	private BigDecimal price;
	private BigDecimal rePrice;
	private int count;

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

	public String  getPriceUsageDate() {
		String dateString = new SimpleDateFormat("yyyy-MM-dd")
		.format(this.priceUsageDate);
		return dateString;
		
	}

	public void setPriceUsageDate(Date priceUsageDate) {
		this.priceUsageDate = priceUsageDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public BigDecimal getRePrice() {
		return rePrice;
	}
	
	public void setRePrice(BigDecimal rePrice) {
		this.rePrice = rePrice;
	}

}

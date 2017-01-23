package hospital.businessentity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerProblem {

	private BigDecimal pkId;
	private Date date;
	private String problem;

	public CustomerProblem() {
		super();
	}
	public CustomerProblem(BigDecimal pkId, Date date, String problem) {
		super();
		this.pkId = pkId;
		this.date = date;
		this.problem = problem;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	public String getStringDate() {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		return (ft.format(date));
	}
}
package hospital.businessentity;

import java.math.BigDecimal;
import java.util.Date;

import hospital.entity.Inspection;
import hospital.entity.InspectionDtl;
import sun.nio.cs.ext.Big5;

public class PrevRecipe {
	private BigDecimal pkId;
	private String date;
	private String name;
	private String type;
	private Inspection inspection;
	private InspectionDtl inspectionDtl;
	
	public PrevRecipe(){
		super();
	}
	
	public BigDecimal getPkId() {
		return pkId;
	}
	
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public Inspection getInspection() {
		return inspection;
	}

	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	public InspectionDtl getInspectionDtl() {
		return inspectionDtl;
	}

	public void setInspectionDtl(InspectionDtl inspectionDtl) {
		this.inspectionDtl = inspectionDtl;
	}
}

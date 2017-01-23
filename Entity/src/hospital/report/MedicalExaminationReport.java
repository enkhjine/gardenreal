package hospital.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicalExaminationReport {

	private Date date;
	private String subOrganizationName;
	private String employeeName;

	private BigDecimal sumAmount1;
	private BigDecimal sumAmount2;
	private BigDecimal sumAmount3;
	private int genderCount1;
	private int genderCount2;
	private int genderCount3;
	private int genderCount4;
	private int genderCount5;
	private int genderCount6;
	private int sumGenderCount1;
	private int sumGenderCount2;
	private int sumGenderCount3;
	private String dateStr;
	private List<MedicalExaminationReport> examinationReports;
	private List<MedicalExaminationReport> subOrganizationReports;
	
	public MedicalExaminationReport(){
		super();
	}
	
	public MedicalExaminationReport(Date date, String subOrganizationName, String employeeName, BigDecimal sumAmount1,
			BigDecimal sumAmount2,	BigDecimal sumAmount3, String dateStr) {
		super();
		this.date = date;
		this.subOrganizationName = subOrganizationName;
		this.employeeName = employeeName;
		this.sumAmount1 = sumAmount1;
		this.sumAmount2 = sumAmount2;
		this.sumAmount3 = sumAmount3;
		this.dateStr = dateStr;
	}

	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getSubOrganizationName() {
		return subOrganizationName;
	}
	
	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}
	
	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	public BigDecimal getSumAmount1() {
		return sumAmount1 == null ? BigDecimal.ZERO : sumAmount1;
	}
	
	public void setSumAmount1(BigDecimal sumAmount1) {
		this.sumAmount1 = sumAmount1;
	}

	public BigDecimal getSumAmount2() {
		return sumAmount2 == null ? BigDecimal.ZERO : sumAmount2;
	}

	public void setSumAmount2(BigDecimal sumAmount2) {
		this.sumAmount2 = sumAmount2;
	}

	public BigDecimal getSumAmount3() {
		sumAmount3 = getSumAmount1().add(getSumAmount2());
		return sumAmount3;
	}

	public void setSumAmount3(BigDecimal sumAmount3) {
		this.sumAmount3 = sumAmount3;
	}

	public String getDateStr() {
		SimpleDateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
		dateStr = sdfr.format(getDate());
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	public List<MedicalExaminationReport> getExaminationReports() {
		if(examinationReports == null) examinationReports = new ArrayList<>();
		return examinationReports;
	}

	public void setExaminationReports(List<MedicalExaminationReport> examinationReports) {
		this.examinationReports = examinationReports;
	}

	public List<MedicalExaminationReport> getSubOrganizationReports() {
		if(subOrganizationReports == null) subOrganizationReports = new ArrayList<>();
		return subOrganizationReports;
	}

	public void setSubOrganizationReports(List<MedicalExaminationReport> subOrganizationReports) {
		this.subOrganizationReports = subOrganizationReports;
	}

	public int getGenderCount1() {
		return genderCount1;
	}

	public void setGenderCount1(int genderCount1) {
		this.genderCount1 = genderCount1;
	}

	public int getGenderCount2() {
		return genderCount2;
	}

	public void setGenderCount2(int genderCount2) {
		this.genderCount2 = genderCount2;
	}

	public int getGenderCount3() {
		return genderCount3;
	}

	public void setGenderCount3(int genderCount3) {
		this.genderCount3 = genderCount3;
	}

	public int getGenderCount4() {
		return genderCount4;
	}

	public void setGenderCount4(int genderCount4) {
		this.genderCount4 = genderCount4;
	}

	public int getGenderCount5() {
		genderCount5 = this.getGenderCount1() + this.getGenderCount3();
		return genderCount5;
	}

	public void setGenderCount5(int genderCount5) {
		this.genderCount5 = genderCount5;
	}

	public int getGenderCount6() {
		genderCount6 = this.getGenderCount2() + this.getGenderCount4();
		return genderCount6;
	}

	public void setGenderCount6(int genderCount6) {
		this.genderCount6 = genderCount6;
	}

	public int getSumGenderCount1() {
		sumGenderCount1 = this.getGenderCount1() + this.getGenderCount2();
		return sumGenderCount1;
	}

	public void setSumGenderCount1(int sumGenderCount1) {
		this.sumGenderCount1 = sumGenderCount1;
	}

	public int getSumGenderCount2() {
		sumGenderCount2 = this.getGenderCount3() + this.getGenderCount4();
		return sumGenderCount2;
	}

	public void setSumGenderCount2(int sumGenderCount2) {
		this.sumGenderCount2 = sumGenderCount2;
	}

	public int getSumGenderCount3() {
		sumGenderCount3 = this.getSumGenderCount1() + this.getSumGenderCount2();
		return sumGenderCount3;
	}

	public void setSumGenderCount3(int sumGenderCount3) {
		this.sumGenderCount3 = sumGenderCount3;
	}	
	
}
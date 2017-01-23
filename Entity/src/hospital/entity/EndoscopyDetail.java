package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "EndoscopyDetail")
public class EndoscopyDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PkId", nullable = false, length = 18)
	private BigDecimal pkId;

	@Column(name = "XrayRequestPkId")
	private BigDecimal xrayRequestPkId;

	@Column(name = "Plimp")
	private String plimp;

	@Column(name = "Pldesc")
	private String pldesc;

	@Column(name = "PlimpValue")
	private String plimpValue;

	@Column(name = "PldescValue")
	private String pldescValue;

	@Column(name = "Ceimp")
	private String ceimp;

	@Column(name = "Cedesc")
	private String cedesc;

	@Column(name = "CeimpValue")
	private String ceimpValue;

	@Column(name = "CedescValue")
	private String cedescValue;

	@Column(name = "Stimp")
	private String stimp;

	@Column(name = "Stdesc")
	private String stdesc;

	@Column(name = "StimpValue")
	private String stimpValue;

	@Column(name = "StdescValue")
	private String stdescValue;

	@Column(name = "Doimp")
	private String doimp;

	@Column(name = "Dodesc")
	private String dodesc;

	@Column(name = "DoimpValue")
	private String doimpValue;

	@Column(name = "DodescValue")
	private String dodescValue;

	@Column(name = "InspectedBy")
	private BigDecimal inspectedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SavedDate")
	private Date savedDate;

	@Column(name = "Diagnose")
	private String diagnose;

	@Column(name = "Treatment")
	private String treatment;

	@Column(name = "Auth")
	private String auth;

	@Column(name = "Hungry")
	private String hungry;

	@Column(name = "Blood")
	private String blood;

	@Column(name = "PreparePuncture")
	private String preparePuncture;

	@Column(name = "AntiBiotic")
	private String antiBiotic;

	@Column(name = "Acid")
	private String acid;

	@Column(name = "Sectional")
	private String sectional;

	@Column(name = "Narcos")
	private String narcos;

	@Column(name = "Hpureasa")
	private String hpureasa;

	@Column(name = "Indigocarmin")
	private String indigocarmin;

	@Column(name = "MethyleneBlue")
	private String methyleneBlue;

	@Column(name = "Lugol")
	private String lugol;

	@Column(name = "AllData")
	private String allData;

	@Column(name = "AllDs")
	private String allDs;

	@Transient
	private boolean indi;

	@Transient
	private boolean methy;

	@Transient
	private boolean lugo;

	@Transient
	private String status;

	@Transient
	private String allValue;

	@Transient
	private String[] datas;

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getXrayRequestPkId() {
		return xrayRequestPkId;
	}

	public void setXrayRequestPkId(BigDecimal xrayRequestPkId) {
		this.xrayRequestPkId = xrayRequestPkId;
	}

	public String getPlimp() {
		return plimp;
	}

	public void setPlimp(String plimp) {
		this.plimp = plimp;
	}

	public String getPldesc() {
		return pldesc;
	}

	public void setPldesc(String pldesc) {
		this.pldesc = pldesc;
	}

	public String getPlimpValue() {
		return plimpValue;
	}

	public void setPlimpValue(String plimpValue) {
		this.plimpValue = plimpValue;
	}

	public String getPldescValue() {
		return pldescValue;
	}

	public void setPldescValue(String pldescValue) {
		this.pldescValue = pldescValue;
	}

	public String getCeimp() {
		return ceimp;
	}

	public void setCeimp(String ceimp) {
		this.ceimp = ceimp;
	}

	public String getCedesc() {
		return cedesc;
	}

	public void setCedesc(String cedesc) {
		this.cedesc = cedesc;
	}

	public String getCeimpValue() {
		return ceimpValue;
	}

	public void setCeimpValue(String ceimpValue) {
		this.ceimpValue = ceimpValue;
	}

	public String getCedescValue() {
		return cedescValue;
	}

	public void setCedescValue(String cedescValue) {
		this.cedescValue = cedescValue;
	}

	public String getStimp() {
		return stimp;
	}

	public void setStimp(String stimp) {
		this.stimp = stimp;
	}

	public String getStdesc() {
		return stdesc;
	}

	public void setStdesc(String stdesc) {
		this.stdesc = stdesc;
	}

	public String getStimpValue() {
		return stimpValue;
	}

	public void setStimpValue(String stimpValue) {
		this.stimpValue = stimpValue;
	}

	public String getStdescValue() {
		return stdescValue;
	}

	public void setStdescValue(String stdescValue) {
		this.stdescValue = stdescValue;
	}

	public String getDoimp() {
		return doimp;
	}

	public void setDoimp(String doimp) {
		this.doimp = doimp;
	}

	public String getDodesc() {
		return dodesc;
	}

	public void setDodesc(String dodesc) {
		this.dodesc = dodesc;
	}

	public String getDoimpValue() {
		return doimpValue;
	}

	public void setDoimpValue(String doimpValue) {
		this.doimpValue = doimpValue;
	}

	public String getDodescValue() {
		return dodescValue;
	}

	public void setDodescValue(String dodescValue) {
		this.dodescValue = dodescValue;
	}

	public BigDecimal getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(BigDecimal inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public Date getSavedDate() {
		return savedDate;
	}

	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAllData() {
		if (allData == null)
			allData = "DESC:";
		else if ("".equals(allData.trim()))
			allData = "DESC:";
		return allData;
	}

	public void setAllData(String allData) {
		this.allData = allData;
	}

	public String[] getDatas() {
		String dd = plimpValue + pldescValue + ceimpValue + cedescValue + stimpValue + stdescValue + doimpValue
				+ dodescValue;
		if (dd != null)
			datas = dd.split(";;;");
		return datas;
	}

	public void setDatas(String[] datas) {
		this.datas = datas;
	}

	public String getDiagnose() {
		return diagnose;
	}

	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getHungry() {
		return hungry;
	}

	public void setHungry(String hungry) {
		this.hungry = hungry;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getPreparePuncture() {
		return preparePuncture;
	}

	public void setPreparePuncture(String preparePuncture) {
		this.preparePuncture = preparePuncture;
	}

	public String getAntiBiotic() {
		return antiBiotic;
	}

	public void setAntiBiotic(String antiBiotic) {
		this.antiBiotic = antiBiotic;
	}

	public String getAcid() {
		return acid;
	}

	public void setAcid(String acid) {
		this.acid = acid;
	}

	public String getSectional() {
		return sectional;
	}

	public void setSectional(String sectional) {
		this.sectional = sectional;
	}

	public String getNarcos() {
		return narcos;
	}

	public void setNarcos(String narcos) {
		this.narcos = narcos;
	}

	public String getHpureasa() {
		return hpureasa;
	}

	public void setHpureasa(String hpureasa) {
		this.hpureasa = hpureasa;
	}

	public String getIndigocarmin() {
		return indigocarmin;
	}

	public void setIndigocarmin(String indigocarmin) {
		this.indigocarmin = indigocarmin;
	}

	public String getMethyleneBlue() {
		return methyleneBlue;
	}

	public void setMethyleneBlue(String methyleneBlue) {
		this.methyleneBlue = methyleneBlue;
	}

	public String getLugol() {
		return lugol;
	}

	public void setLugol(String lugol) {
		this.lugol = lugol;
	}

	public boolean isIndi() {
		if ("Indigocarmin".equals(indigocarmin))
			indi = true;
		else
			indi = false;
		return indi;
	}

	public void setIndi(boolean indi) {
		if (indi)
			setIndigocarmin("Indigocarmin");
		else
			setIndigocarmin("");
		this.indi = indi;
	}

	public boolean isMethy() {
		if ("Methylene Blue".equals(methyleneBlue))
			methy = true;
		else
			methy = false;
		return methy;
	}

	public void setMethy(boolean methy) {
		if (methy)
			setMethyleneBlue("Methylene Blue");
		else
			setMethyleneBlue("");
		this.methy = methy;
	}

	public boolean isLugo() {
		if ("Lugol's solution 1%".equals(lugol))
			lugo = true;
		else
			lugo = false;
		return lugo;
	}

	public void setLugo(boolean lugo) {
		if (lugo)
			setLugol("Lugol's solution 1%");
		else
			setLugol("");
		this.lugo = lugo;
	}

	public String getAllDs() {
		return allDs;
	}

	public void setAllDs(String allDs) {
		this.allDs = allDs;
	}

	public String getAllValue() {
		allValue = plimpValue + pldescValue + ceimpValue + cedescValue + stimpValue + stdescValue + doimpValue
				+ dodescValue;
		allValue = allValue.replace("'", "\\'");
		return allValue;
	}

	public void setAllValue(String allValue) {
		this.allValue = allValue;
	}

}

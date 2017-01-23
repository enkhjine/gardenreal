package hospital.businessentity;

import java.io.Serializable;
import java.math.BigDecimal;

import hospital.entity.ICT19;
import hospital.entity.SurgeryIctMap;
import hospital.entity.SurgeryIctModel;
import hospital.entity.TreatmentIcdMap;
import hospital.entity.TreatmentIctModel;

public class ICT19Dtl implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal pkId;
	private String id;
	private String nameEn;
	private String NameMn;
	private String status;
	private ICT19  ict19;
	private SurgeryIctMap  ictMap;
	private SurgeryIctModel  model;
	private String surgeryName;
	private String treatmentName;
	private TreatmentIcdMap treatmentIctMap;
	private TreatmentIctModel treatmentIctModel;
	private TreatmentIcdMap icdMap;
	
	public ICT19Dtl(){
		
	}
	public ICT19Dtl(BigDecimal pkId, String id , String nameEn, String nameMn){
		this.pkId=pkId;
		this.id=id;
		this.nameEn=nameEn;
		this.NameMn  = nameMn;
	}
	public  ICT19Dtl(SurgeryIctMap  map,String id , String nameEn, String nameMn){
		this.ictMap=map;
		this.id=id;
		this.nameEn=nameEn;
		this.NameMn =nameMn;
	}
	public ICT19Dtl(SurgeryIctMap map, SurgeryIctModel model,ICT19 dtl){
		this.ictMap=map;
		this.model=model;
		this.ict19=dtl;
	}
	public ICT19Dtl(String name){
		this.surgeryName=name;
	}
	public  ICT19Dtl(ICT19  ic19){
		this.ict19=ic19;
	}
	
	public ICT19Dtl(TreatmentIcdMap map, String id, String nameEn, String nameMn){
		this.treatmentIctMap = map;
		this.id = id;
		this.nameEn = nameEn;
		this.NameMn = nameMn;
	}
	
	public ICT19Dtl(TreatmentIcdMap map, TreatmentIctModel model, ICT19 dtl){
		this.treatmentIctMap = map;
		this.treatmentIctModel = model;
		this.ict19 = dtl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameMn() {
		return NameMn;
	}

	public void setNameMn(String nameMn) {
		NameMn = nameMn;
	}
	public BigDecimal getPkId() {
		return pkId;
	}
	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ICT19 getIct19() {
		return ict19;
	}
	public void setIct19(ICT19 ict19) {
		this.ict19 = ict19;
	}
	public SurgeryIctMap getIctMap() {
		return ictMap;
	}
	public void setIctMap(SurgeryIctMap ictMap) {
		this.ictMap = ictMap;
	}
	public SurgeryIctModel getModel() {
		return model;
	}
	public void setModel(SurgeryIctModel model) {
		this.model = model;
	}
	public String getSurgeryName() {
		return surgeryName;
	}
	public void setSurgeryName(String surgeryName) {
		this.surgeryName = surgeryName;
	}
	public TreatmentIcdMap getTreatmentIctMap() {
		return treatmentIctMap;
	}
	public void setTreatmentIctMap(TreatmentIcdMap treatmentIctMap) {
		this.treatmentIctMap = treatmentIctMap;
	}
	public TreatmentIctModel getTreatmentIctModel() {
		return treatmentIctModel;
	}
	public void setTreatmentIctModel(TreatmentIctModel treatmentIctModel) {
		this.treatmentIctModel = treatmentIctModel;
	}
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatmentName(String treatmentName) {
		this.treatmentName = treatmentName;
	}
	public TreatmentIcdMap getIcdMap() {
		return icdMap;
	}
	public void setIcdMap(TreatmentIcdMap icdMap) {
		this.icdMap = icdMap;
	}
	
	
}

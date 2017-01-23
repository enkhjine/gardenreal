package hospital.businesslogic.interfaces;


import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.Local;

import hospital.entity.TreatmentPrice;

@Local
public interface ILogicSurgeryLocal {
	
//	public List<Item> getItems(LoggedUser logUser) throws Exception;
//	public List<Treatment> getTreatments(LoggedUser lu) throws Exception;
//	public List<TreatmentType> getTreatmentTypes(LoggedUser lu) throws Exception;	
//	public List<PriceHistory> getPriceHistorys(BigDecimal itemPkId, LoggedUser lu) throws Exception;
//	public List<TreatmentDtl> getTreatmentDtls() throws Exception;
//	public List<Item> getTreatmentDtlsByTreatmentPkId(BigDecimal pkId) throws Exception;
//	public List<TreatmentType> getAllTreatmentType(LoggedUser lu) throws Exception;
//	public List<TreatmentModel> getTreatmentModels() throws Exception;	
//	public List<InspectionDtl> getDetails (EmployeeRequest er) throws Exception;
//	public List<Treatment> getTreatmentByPrice(LoggedUser lu, BigDecimal price) throws Exception;	
//	public List<Treatment> getTreatments(LoggedUser lu, BigDecimal treatmentTypePkId) throws Exception;
//	public List<DegreePriceHistory> getPriceHistories(BigDecimal itemPkId, LoggedUser lu) throws Exception;
//	public List<TreatmentType> getTreatmentTypeBySot(LoggedUser lu, BigDecimal sotPkId, BigDecimal price) throws Exception;
//	public List <SubOrganizationType> getSubOrganizationTypes() throws Exception;
//	public List<Measurement> getMeasurements() throws Exception;
//	public List<TreatmentPrice> getTreatmentPrices(BigDecimal treatmentPkId) throws Exception;
	public List<SurgeryType> getSurgeryTypes() throws Exception;
	public List<Surgery> getSurgery(BigDecimal surgeryTypePkId,BigDecimal organizationPkId ,boolean withPrice) throws Exception;
	public List<Surgery> getSurgery(String surgerySearchString,BigDecimal organizationPkId ,boolean withPrice) throws Exception;
	public List<SurgeryDoctor> getSurgeryDoctor(BigDecimal surgeryPkId) throws Exception;
	
//	public void updateTreatmentStatus(Treatment treatment) throws Exception;
//	public void saveItem(Item item, LoggedUser lu) throws Exception;	
//	public void saveTreatment(LoggedUser loggedUser, Treatment treatment, List<Item> items, boolean isModel) throws Exception;
//	public void saveTreatmentType(TreatmentType treatmentType) throws Exception;	
//	public void savePriceHistory(DegreePriceHistory priceHistory, LoggedUser lu, BigDecimal itemPkId) throws Exception;
	public void saveSurgeryType(SurgeryType surgeryType) throws Exception;
	public void saveSurgery(Surgery surgery, SurgeryPrice price ,List<SurgeryDoctor> doctors, LoggedUser lu) throws Exception;
	public List<ICT19Dtl> getSurgeryIct19() throws Exception;
	public void saveSurgeryICt19(SurgeryIctMap ictMap, BigDecimal surgeryPkId,List<ICT19Dtl> ictDtl)throws Exception;
	public List<ICT19Dtl> getICTDtlView(BigDecimal currentSurgeryPkId) throws Exception;
	public void  saveSurgeryModel(SurgeryIctModel model,List<ICT19Dtl> ict19Dtls,String name,boolean isModel) throws Exception;
	public List<ICT19Dtl> getSurgeryModelView() throws Exception;
	public List<ICT19Dtl> getSurgeryNameSearch(String surgeryName) throws Exception;
}

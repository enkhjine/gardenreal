package hospital.businesslogic.interfaces;


import hospital.businessentity.CustomerMedicalHistory;
import hospital.businessentity.CustomerRequest;
import hospital.businessentity.ICT19Dtl;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import hospital.entity.TreatmentPrice;

@Local
public interface ILogicTreatmentLocal {
	
	public List<Item> getItems(LoggedUser logUser) throws Exception;
	public List<Treatment> getTreatments(LoggedUser lu) throws Exception;
	public List<TreatmentType> getTreatmentTypes(LoggedUser lu) throws Exception;	
	public List<PriceHistory> getPriceHistorys(BigDecimal itemPkId, LoggedUser lu) throws Exception;
	public List<TreatmentDtl> getTreatmentDtls() throws Exception;
	public List<Item> getTreatmentDtlsByTreatmentPkId(BigDecimal pkId) throws Exception;
	public List<TreatmentType> getAllTreatmentType(LoggedUser lu) throws Exception;
	public List<TreatmentModel> getTreatmentModels() throws Exception;	
	public List<InspectionDtl> getDetails (EmployeeRequest er) throws Exception;
	public List<Treatment> getTreatmentByPrice(LoggedUser lu, BigDecimal price) throws Exception;	
	public List<Treatment> getTreatments(LoggedUser lu, BigDecimal treatmentTypePkId) throws Exception;
	public List<Treatment> getTreatments(LoggedUser lu, String treatmentSearchString) throws Exception;
	public List<DegreePriceHistory> getPriceHistories(BigDecimal itemPkId, LoggedUser lu) throws Exception;
	public List<TreatmentType> getTreatmentTypeBySot(LoggedUser lu, BigDecimal sotPkId, BigDecimal price) throws Exception;
	public List <SubOrganizationType> getSubOrganizationTypes() throws Exception;
	public List<Measurement> getMeasurements() throws Exception;
	public List<TreatmentPrice> getTreatmentPrices(BigDecimal treatmentPkId) throws Exception;
	public List<Employee> getEmployees(BigDecimal sotPkId) throws Exception;
	public List<ICT19Dtl> getTreatmentICT() throws Exception;
	
	
	public void updateTreatmentStatus(Treatment treatment) throws Exception;
	public void saveItem(Item item, LoggedUser lu) throws Exception;	
	public void saveTreatment(LoggedUser loggedUser, Treatment treatment, List<Item> items, boolean isModel) throws Exception;
	public void saveTreatmentType(TreatmentType treatmentType,  List<TreatmentTypeEmployeeMap> maps) throws Exception;	
	public void savePriceHistory(DegreePriceHistory priceHistory, LoggedUser lu, BigDecimal itemPkId) throws Exception;
	public void saveTreatmentDtl(TreatmentDtl treatmentDtl) throws Exception;
	public void saveTreatmentIcdMap(TreatmentIcdMap icdmap, BigDecimal treatmentPkId, List<ICT19Dtl> listict) throws Exception;
	public List<ICT19Dtl> getIctDtlView(BigDecimal currentpkId) throws Exception;
	public void saveTreatmentModel(TreatmentIctModel ictModel, List<ICT19Dtl> ict19Dtl, String name, boolean isModel) throws Exception;
	public List<ICT19Dtl> getTreatmentIctModelView() throws Exception;
	public List<ICT19Dtl> getModelNameSearch(String name) throws Exception;
}

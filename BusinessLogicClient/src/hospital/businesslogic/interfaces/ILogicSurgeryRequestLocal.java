package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.TreatmentRequest;
import hospital.entity.CustomerTreatment;
import hospital.entity.InsuranceAmbulanceConfig;
import hospital.entity.InsuranceAmbulanceConfigDtl;
import hospital.entity.InsuranceConfig;

import javax.ejb.Local;

@Local
public interface ILogicSurgeryRequestLocal {
	public List<TreatmentRequest> getCustomerTreatments(BigDecimal employeePkId, String filterKey1, String filterKey2) throws Exception;
	public void saveTreatmentRequest(List<CustomerTreatment> customerTreatments, LoggedUser lu) throws Exception;
	
}

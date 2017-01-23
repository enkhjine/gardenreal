package hospital.businesslogic.interfaces;

import java.math.BigDecimal;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.entity.InsuranceAmbulanceConfig;
import hospital.entity.InsuranceAmbulanceConfigDtl;
import hospital.entity.InsuranceConfig;

import javax.ejb.Local;

@Local
public interface ILogicInsuranceLocal {
	public InsuranceAmbulanceConfig getInsuranceAmbulanceConfig(LoggedUser loggedUser) throws Exception;
	public List<InsuranceAmbulanceConfigDtl> getListInsuranceAmbulanceConfigDtl(LoggedUser loggedUser) throws Exception;
	public InsuranceConfig getInsuranceConfig(BigDecimal organizationPkId) throws Exception;
	
	public void saveInsuranceAmbulanceConfig(BigDecimal organizationPkId, InsuranceAmbulanceConfig insuranceAmbulanceConfig, List<InsuranceAmbulanceConfigDtl> insuranceAmbulanceConfigDtls) throws Exception;
	public void saveInsuranceConfig(InsuranceConfig insuranceConfig) throws Exception;
}

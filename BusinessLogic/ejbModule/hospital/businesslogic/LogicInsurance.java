package hospital.businesslogic;

import java.math.BigDecimal;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicInsuranceLocal;
import hospital.entity.InsuranceAmbulanceConfig;
import hospital.entity.InsuranceAmbulanceConfigDtl;
import hospital.entity.InsuranceConfig;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicInsurance", mappedName = "hospital.businesslogic.LogicInsurance")
public class LogicInsurance extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicInsurance,
		ILogicInsuranceLocal {

	@Resource
	SessionContext sessionContext;

	public LogicInsurance() {

	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}
	
	public InsuranceConfig getInsuranceConfig(BigDecimal organizationPkId) throws Exception{
		List<InsuranceConfig> insuranceConfigs = getByAnyField(InsuranceConfig.class, "organizationPkId", organizationPkId);
		if(insuranceConfigs.size() > 0) return insuranceConfigs.get(0);
		else {
			InsuranceConfig insuranceConfig = new InsuranceConfig();
			insuranceConfig.setPkId(Tools.newPkId());
			insuranceConfig.setHasAmbulance(1);
			insuranceConfig.setOrganizationPkId(organizationPkId);
			insuranceConfig.setAmount(new BigDecimal(108000));
			insert(insuranceConfig);
			insuranceConfigs.add(insuranceConfig);
			return insuranceConfigs.get(0);
		}
	}
	
	public void saveInsuranceConfig(InsuranceConfig insuranceConfig) throws Exception{
		update(insuranceConfig);
	}

	public InsuranceAmbulanceConfig getInsuranceAmbulanceConfig(LoggedUser loggedUser) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM InsuranceConfig a WHERE a.organizationPkId = :organizationPkId ");
		List<InsuranceConfig> insuranceConfigs = getByQuery(InsuranceConfig.class, jpql.toString(), parameters);
		if(insuranceConfigs.size() < 1 || insuranceConfigs.get(0).getHasAmbulance() != 1) return null;
		
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM InsuranceAmbulanceConfig a WHERE a.organizationPkId = :organizationPkId ");
		
		List<InsuranceAmbulanceConfig> ambulanceConfigs = getByQuery(InsuranceAmbulanceConfig.class, jpql.toString(), parameters);
		
		if(ambulanceConfigs.size() < 1) {
			InsuranceAmbulanceConfig ambulanceConfig = new InsuranceAmbulanceConfig();
			ambulanceConfig.setPkId(Tools.newPkId());
			ambulanceConfig.setType(2);
			ambulanceConfig.setFirstInspection(1);
			ambulanceConfig.setReInspection(1);
			ambulanceConfig.setPrice(new BigDecimal(15000));
			ambulanceConfig.setOrganizationPkId(loggedUser.getOrganization().getPkId());
			insert(ambulanceConfig);
			return ambulanceConfig;
		}
		
		return ambulanceConfigs.get(0);
	}
	
	public List<InsuranceAmbulanceConfigDtl> getListInsuranceAmbulanceConfigDtl(LoggedUser loggedUser) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT NEW hospital.entity.InsuranceAmbulanceConfigDtl(a.pkId, a.organizationPkId, a.diagnoseGroupPkId, b.name, a.cost) FROM InsuranceAmbulanceConfigDtl a ");
		jpql.append("INNER JOIN DiagnoseGroup b ON a.diagnoseGroupPkId = b.pkId ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		List<InsuranceAmbulanceConfigDtl> insuranceAmbulanceConfigDtls = getByQuery(InsuranceAmbulanceConfigDtl.class, jpql.toString(), parameters);
		return insuranceAmbulanceConfigDtls;
	}
	
	public void saveInsuranceAmbulanceConfig(BigDecimal organizationPkId, InsuranceAmbulanceConfig insuranceAmbulanceConfig, List<InsuranceAmbulanceConfigDtl> insuranceAmbulanceConfigDtls) throws Exception{
		deleteByAnyField(InsuranceAmbulanceConfigDtl.class, "organizationPkId", organizationPkId);
		
		BigDecimal pkId = Tools.newPkId();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", organizationPkId);
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT a FROM InsuranceAmbulanceConfig a WHERE a.organizationPkId = :organizationPkId ");
		if(getByQuery(InsuranceAmbulanceConfig.class, jpql.toString(), parameters).size() == 0) {
			insuranceAmbulanceConfig.setPkId(pkId);
			insert(insuranceAmbulanceConfig);
		}else {
			update(insuranceAmbulanceConfig);
		}
		for(InsuranceAmbulanceConfigDtl ambulanceConfigDtl : insuranceAmbulanceConfigDtls) {
			pkId = pkId.add(BigDecimal.ONE);
			ambulanceConfigDtl.setPkId(pkId);
			if(Tool.ADDED.equals(ambulanceConfigDtl.getStatus())) insert(ambulanceConfigDtl);
		}
	}

}

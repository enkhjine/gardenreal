package hospital.businesslogic;

import hospital.businesslogic.interfaces.ILogicBlankLocal;
import hospital.report.Am10;
import hospital.report.Am13a;
import hospital.report.Am13b;
import hospital.report.Am8;
import hospital.report.Am9a;
import hospital.report.Am9b;
import logic.data.CustomHashMap;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.ejb.SessionContext;

@Stateless(name = "LogicBlank", mappedName = "hospital.businesslogic.LogicBlank")
public class LogicBlank extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicBlank, ILogicBlankLocal {

	@Resource
	SessionContext sessionContext;

	public LogicBlank() {

	}

	@Override
	public List<Am8> getA8(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		

		return null;
	}

	@Override
	public List<Am9a> getA9a(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		return null;
	}

	@Override
	public List<Am9b> getA9b(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		return null;
	}

	@Override
	public List<Am10> getA10(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		return null;
	}

	@Override
	public List<Am13a> getA13a(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		return null;
	}

	@Override
	public List<Am13b> getA13b(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		return null;
	}

}

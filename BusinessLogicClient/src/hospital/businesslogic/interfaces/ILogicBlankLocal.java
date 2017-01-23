package hospital.businesslogic.interfaces;

import hospital.report.Am10;
import hospital.report.Am13a;
import hospital.report.Am13b;
import hospital.report.Am8;
import hospital.report.Am9a;
import hospital.report.Am9b;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Local;



@Local
public interface ILogicBlankLocal {
	public List<Am8> getA8(BigDecimal inspectionPkId) throws Exception ;
	public List<Am9a> getA9a(BigDecimal inspectionPkId) throws Exception ;
	public List<Am9b> getA9b(BigDecimal inspectionPkId) throws Exception ;
	public List<Am10> getA10(BigDecimal inspectionPkId) throws Exception ;
	public List<Am13a> getA13a(BigDecimal inspectionPkId) throws Exception ;
	public List<Am13b> getA13b(BigDecimal inspectionPkId) throws Exception ;
	
}

package hospital.businesslogic.interfaces;

import hospital.businessentity.InspectionHistory;
import hospital.businessentity.LoggedUser;
import hospital.entity.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ILogicXrayLocal {

	public List<Xray> getXrays() throws Exception;

	public List<XrayType> getXrayTypes(LoggedUser lu) throws Exception;

	public List<Xray> getXrayByDianoseTypePkId(BigDecimal xrayTypePkId) throws Exception;

	public List<Xray> getXrayByDianose(String xraySearchString) throws Exception;

	public List<Xray> getXrayByEmployeePkId(BigDecimal customerPkId, LoggedUser loggedUser) throws Exception;

	public List<Xray> getPaymentXrayByCustomer(BigDecimal customerPkId) throws Exception;

	public List<XrayRequest> getBase64Images() throws Exception;

	public List<XrayPrice> getXrayPrices(BigDecimal diagnosePkId) throws Exception;

	public List<XrayRequest> getXrayRequestByCustomerPkId(BigDecimal customerPkId) throws Exception;
	
	public List<EndoTemplate> getTemplates() throws Exception;
	
	public void saveTemplate (EndoTemplate endoTemplate, LoggedUser lu) throws Exception;
	
	public XrayRequest  getXrayRequestByCustomer(BigDecimal  customerPkId)  throws  Exception;

	public List<XrayEmployeeMap> getXrayEmployeeMap(BigDecimal xrayPkId) throws Exception;

	public List<XrayRequest> getXrayRequests(Date beginDate, Date endDate, String searchKey, boolean isDone,
			String searchKey1, BigDecimal employeePkId) throws Exception;

	public EndoscopyDetail getEndoDtl(BigDecimal requestPkId) throws Exception;

	public Ultrasound getUltrasound(BigDecimal requestPkId) throws Exception;

	public void saveXrayType(LoggedUser lu, XrayType xrayType) throws Exception;

	public void saveXray(LoggedUser lu, Xray xray, List<XrayEmployeeMap> xrayEmployeeList) throws Exception;

	public void saveEndoscope(LoggedUser lu, EndoscopyDetail es, List<CustomerAttachment> ca) throws Exception;

	
	public void saveColonoScopic(BigDecimal request,List<XrayDtl> dtl,List<CustomerAttachment> attachments) throws Exception;

	public XrayDtl  getListXrayDtl(BigDecimal pkId) throws Exception;
	
	public void saveUltrasound(LoggedUser lu, Ultrasound us) throws Exception;
	
	


}

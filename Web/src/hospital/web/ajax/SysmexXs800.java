package hospital.web.ajax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hospital.businessentity.CustomValue;
import hospital.businessentity.ExaRequest;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicExaminationLocal;
import hospital.entity.ExaminationValueAnswer;
import hospital.entity.ExaminationValueHdr;
import hospital.entity.ExaminationValueQuestion;
import hospital.web.controller.UserSessionController;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.primefaces.context.RequestContext;

@WebService(serviceName = "SysmexXs800", targetNamespace = "http://sysmexXs800/")
public class SysmexXs800 {
	@EJB(beanName = "ExaminationLogic")
	ILogicExaminationLocal examLogic;

	private List<ExaminationValueHdr> valueHdrs;

	@WebMethod
	public String creatingValueHdrs(
			@WebParam(name = "requestPkId") String requestPkId,
			@WebParam(name = "values") List<CustomValue> values) {
		BigDecimal qq = new BigDecimal(requestPkId);
		String ret = "";
		try {
			ExaRequest exaRequest = examLogic.getRequest(qq);
			getValueHdrs().clear();
			List<ExaminationValueQuestion> qlist = examLogic
					.getQuestions(exaRequest);
			setValueHdrs(examLogic.getHdrs(exaRequest));
			if (exaRequest.getMood() != 3) {
				for (ExaminationValueQuestion evq : qlist) {
					for (CustomValue cv : values) {
						if (evq.getName().equals(cv.getValueName())) {
							for (ExaminationValueAnswer eva : evq.getAnswers()) {
								if (exaRequest.getMood() == 2) {
									for (ExaminationValueHdr hdr : valueHdrs) {
										if (hdr.getAnswerPkId().compareTo(
												eva.getPkId()) == 0) {
											hdr.setValue(cv.getValue());
											hdr.setStatus(Tool.MODIFIED);
										}
									}
								} else if (exaRequest.getMood() == 1) {
									ExaminationValueHdr evh = new ExaminationValueHdr();
									evh.setAnswerPkId(eva.getPkId());
									evh.setQuestionPkId(evq.getPkId());
									evh.setRequestPkId(qq);
									evh.setValue(cv.getValue());
									evh.setStatus(Tool.ADDED);
									eva.setValue(cv.getValue());
									getValueHdrs().add(evh);
								}

							}
						}
					}
				}
				examLogic.saveExaminationValueHdrs(valueHdrs, qq);
				ret = "Амжилттай хадгаллаа";
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ret = "Алдаа гарлаа";
		}
		return ret;
	}

	public List<ExaminationValueHdr> getValueHdrs() {
		if (valueHdrs == null)
			valueHdrs = new ArrayList<ExaminationValueHdr>();
		return valueHdrs;
	}

	public void setValueHdrs(List<ExaminationValueHdr> valueHdrs) {
		this.valueHdrs = valueHdrs;
	}

	public SysmexXs800() {
		super();
	}
}

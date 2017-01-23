package hospital.report;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.entity.Customer;
import hospital.entity.CustomerTreatmentDtl;
import hospital.entity.Diagnose;

public class TreatmentRequestReport {
	private List<CustomerTreatmentDtl> dtls;
	private Customer customer;
	private List<Diagnose> diagnoses;
	private String subOrganizationName;
	private int requestedCount;
	BigDecimal inspectionPkId;
	BigDecimal requestPkId;

	public TreatmentRequestReport() {
		super();
	}

	public TreatmentRequestReport(BigDecimal pkId) {
		this.requestPkId = pkId;
	}

	public TreatmentRequestReport(BigDecimal requestPkId, int requestedCount, BigDecimal inspectionPkId,
			Customer customer, String subOrganizationName) {
		super();
		this.requestPkId = requestPkId;
		this.customer = customer;
		this.subOrganizationName = subOrganizationName;
		this.requestedCount = requestedCount;
		this.inspectionPkId = inspectionPkId;
	}

	public TreatmentRequestReport(BigDecimal requestPkId, int requestedCount, BigDecimal inspectionPkId,
			Customer customer) {
		super();
		this.requestPkId = requestPkId;
		this.customer = customer;
		this.requestedCount = requestedCount;
		this.inspectionPkId = inspectionPkId;
	}

	public List<CustomerTreatmentDtl> getDtls() {

		if (dtls.size() > 11) {
			List<CustomerTreatmentDtl> retList = new ArrayList<>();
			for (int i = 0; i <= 9; i++) {

				retList.add(dtls.get(i));

			}
			return retList;
		} else {
			List<CustomerTreatmentDtl> retList = new ArrayList<>();
			retList.addAll(dtls);
			for (int i = dtls.size(); i <= 9; i++) {

				retList.add(new CustomerTreatmentDtl());

			}
			return retList;
		}

	}

	public void setDtls(List<CustomerTreatmentDtl> dtls) {
		this.dtls = dtls;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Diagnose> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(List<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public String getSubOrganizationName() {
		return subOrganizationName;
	}

	public void setSubOrganizationName(String subOrganizationName) {
		this.subOrganizationName = subOrganizationName;
	}

	public int getRequestedCount() {
		return requestedCount;
	}

	public void setRequestedCount(int requestedCount) {
		this.requestedCount = requestedCount;
	}

	public BigDecimal getInspectionPkId() {
		return inspectionPkId;
	}

	public void setInspectionPkId(BigDecimal inspectionPkId) {
		this.inspectionPkId = inspectionPkId;
	}

	public BigDecimal getRequestPkId() {
		return requestPkId;
	}

	public void setRequestPkId(BigDecimal requestPkId) {
		this.requestPkId = requestPkId;
	}

	public String getDateString(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

}

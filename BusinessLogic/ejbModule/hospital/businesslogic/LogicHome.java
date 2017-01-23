package hospital.businesslogic;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicHome;
import hospital.businesslogic.interfaces.ILogicHomeLocal;

import hospital.entity.*;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import logic.data.CustomHashMap;
import logic.data.Tools;
import sun.tools.jar.resources.jar;
@Stateless(name = "LogicHome", mappedName = "hospital.businesslogic.LogicHome")
public class LogicHome extends logic.SuperBusinessLogic implements hospital.businesslogic.interfaces.ILogicHomeLocal, ILogicHome{
	@Resource
	SessionContext sessionContext;

	public LogicHome() {
		// TODO Auto-generated constructor stub
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "Hospital"
	}

	@Override
	public List<Organization> getOrganizations() throws Exception {
		CustomHashMap  map =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		builder.append(" SELECT  count(ors.pkId) FROM Organization ors ");
		builder.append(" GROUP BY  ors.pkId ");
		List<Organization>  list  =  getByQuery(Organization.class, builder.toString(),map);
		return list;
	}

	@Override
	public List<Role> getAccessLaw() throws Exception {
		CustomHashMap  map =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		builder.append(" SELECT  count(r.pkId) FROM Role r");
		builder.append(" WHERE  r.parentPkId  IS  NULL ");
		builder.append(" GROUP BY  r.pkId ");
		List<Role>  list  =  getByQuery(Role.class, builder.toString(),map);
		return list;
	}

	@Override
	public List<Users> getUsers() throws Exception {
		CustomHashMap  map =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		builder.append(" SELECT  count(u.pkId) FROM Users u");
		builder.append(" GROUP BY  u.pkId ");
		List<Users>  list  =  getByQuery(Users.class, builder.toString(),map);
		return list;
	}

	@Override
	public List<SubOrganization> getRoom() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM SubOrganization a ");
		jpql.append(" GROUP BY a.pkId");
		List<SubOrganization> list = new ArrayList<SubOrganization>();
		list = getByQuery(SubOrganization.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Employee> getEmployee() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM Employee a ");
		jpql.append(" GROUP BY a.pkId");
		List<Employee> list = new ArrayList<Employee>();
		list = getByQuery(Employee.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Treatment> getTreatment() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM Employee a ");
		jpql.append(" GROUP BY a.pkId");
		List<Employee> list = new ArrayList<Employee>();
		list = getByQuery(Employee.class, jpql.toString(), null);
		return null;
	}

	@Override
	public List<Diagnose> getDiagnosist() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM Diagnose a ");
		jpql.append("GROUP BY a.pkId");
		List<Diagnose> list = new ArrayList<Diagnose>();
		list = getByQuery(Diagnose.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Customer> getCustomer() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM Customer a ");
		jpql.append("GROUP BY a.pkId");
		List<Customer> list = new ArrayList<Customer>();
		list = getByQuery(Customer.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Medicine> getMedicine() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM Medicine a ");
		jpql.append("GROUP BY a.pkId");
		List<Medicine> list = new ArrayList<Medicine>();
		list = getByQuery(Medicine.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<DiagnoseGroup> getDiagnoseGroup() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM DiagnoseGroup a ");
		jpql.append("GROUP BY a.pkId");
		List<DiagnoseGroup> list = new ArrayList<DiagnoseGroup>();
		list = getByQuery(DiagnoseGroup.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Xray> getDiagnose() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  Xray a ");
		jpql.append("GROUP BY a.pkId");
		List<Xray> list = new ArrayList<Xray>();
		list = getByQuery(Xray.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<EmployeeRequest> getCahsierList() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(e.pkId) FROM  ViewCustomer vc ");
		jpql.append(" INNER JOIN EmployeeRequest  e  ON vc.pkId =  e.customerPkId ");
		jpql.append(" INNER JOIN Inspection   i  ON  e.pkId  =  i.requestPkId ");
		jpql.append(" GROUP BY e.pkId ");
		List<EmployeeRequest> list = new ArrayList<EmployeeRequest>();
		list = getByQuery(EmployeeRequest.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Xray> getDiagnoseList() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(xr.pkId) FROM  XrayRequest xr  ");
		jpql.append(" LEFT JOIN ViewCustomer vc  ON  xr.customerPkId = vc.pkId ");
		jpql.append(" LEFT JOIN Xray   x  ON  xr.xrayPkId=  x.pkId ");
		jpql.append(" LEFT JOIN Employee   e  ON  xr.employeePkId=  e.pkId ");
		jpql.append(" GROUP BY xr.pkId ");
		List<Xray> list = new ArrayList<Xray>();
		list = getByQuery(Xray.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Examination> getSurveyList() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  Examination  a ");
		jpql.append("GROUP BY a.pkId");
		List<Examination> list = new ArrayList<Examination>();
		list = getByQuery(Examination.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Examination> getSurveyPackage() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  ExaminationGroup  a ");
		jpql.append("GROUP BY a.pkId");
		List<Examination> list = new ArrayList<Examination>();
		list = getByQuery(Examination.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<ConditionalPrescription> getConditionalPrescriptions() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  ConditionalPrescription  a ");
		jpql.append("GROUP BY a.pkId");
		List<ConditionalPrescription> list = new ArrayList<ConditionalPrescription>();
		list = getByQuery(ConditionalPrescription.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<ExaminationRequest> getSurveyRequest() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  ConditionalPrescription  a ");
		jpql.append("GROUP BY a.pkId");
		List<ExaminationRequest> list = new ArrayList<ExaminationRequest>();
		list = getByQuery(ExaminationRequest.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Surgery> getSurvey() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  Surgery  a ");
		jpql.append("GROUP BY a.pkId");
		List<Surgery> list = new ArrayList<Surgery>();
		list = getByQuery(Surgery.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<ExaminationRequest> getExaminationRequestList() throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stubExaminationRequest
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  ExaminationRequest  a ");
		jpql.append("GROUP BY a.pkId");
		List<ExaminationRequest> list = new ArrayList<ExaminationRequest>();
		list = getByQuery(ExaminationRequest.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<ExaminationRequest> getExaminationRequestResult() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT  count(a.pkId) FROM  ExaminationRequestActive  a ");
		jpql.append("GROUP BY a.pkId");
		List<ExaminationRequest> list = new ArrayList<ExaminationRequest>();
		list = getByQuery(ExaminationRequest.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Inspection> getInspection() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" SELECT  count(a.pkId) FROM  Inspection  a ");
		jpql.append("GROUP BY a.pkId");
		List<Inspection> list = new ArrayList<Inspection>();
		list = getByQuery(Inspection.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Xray> getisDiagnose() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  Xray a ");
		jpql.append(" WHERE  a.isActive =0 ");
		jpql.append("GROUP BY a.pkId");
		List<Xray> list = new ArrayList<Xray>();
		list = getByQuery(Xray.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Surgery> getisActiveSurvey() throws Exception {
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  count(a.pkId) FROM  Surgery  a ");
		jpql.append(" WHERE  a.active = 1  ");
		jpql.append("GROUP BY a.pkId");
		List<Surgery> list = new ArrayList<Surgery>();
		list = getByQuery(Surgery.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<SubOrganization> getSelectionSubOrganization() throws Exception {
		// TODO Auto-generated method stub
		StringBuilder jpql = new StringBuilder();
		jpql.append("SELECT  a FROM SubOrganization a ");
		List<SubOrganization> list = new ArrayList<SubOrganization>();
		list = getByQuery(SubOrganization.class, jpql.toString(), null);
		return list;
	}

	@Override
	public List<Employee> getSelectionEmployee(List<BigDecimal> name) throws Exception {
		CustomHashMap  map =  new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		map.put("names", name);
		jpql.append("SELECT DISTINCT a  FROM Employee a ");
		jpql.append(" WHERE a.subOrganizationPkId IN :names ");
		List<Employee> list = new ArrayList<Employee>();
		list = getByQuery(Employee.class, jpql.toString(), map);
		return list;
	}

	@Override
	public void setPostSave(Post p,String text, BigDecimal e,List<BigDecimal> receiverPkIds,Date time) throws Exception {
		//	public void setPostSave(Post  p,String text, BigDecimal e, List<BigDecimal> receiverPkIds) throws Exception {
		if (Tool.ADDED.equals(p.getStatus())) {
			p.setPkId(Tools.newPkId());
			p.setSenderEmployeePkId(e);
			p.setPost(text);
			p.setCreateDate(time);
			insert(p);	
			for(BigDecimal pkId : receiverPkIds){
				PostMap map = new PostMap();
				map.setPkId(Tools.newPkId());
				map.setPostPkId(p.getPkId());
				map.setReceiverEmployeePkId(pkId);
				insert(map);
			}
		}

	}
	@Override
	public void  setPostDeleteModify(Post  p) throws Exception {
		if (Tool.DELETE.equals(p.getStatus())) {
			deleteByAnyField(PostMap.class, "postPkId", p.getPostpkid());
			deleteByPkId(Post.class, p.getPkId());
		}
		else 
			if (Tool.MODIFIED.equals(p.getStatus())) {			 
				update(p);
			}
	}
	@Override
	public List<Post> getPostView(BigDecimal id) throws Exception {
		// TODO Auto-generated method stub
		CustomHashMap  map  =  new  CustomHashMap();
		map.put("id", id);
		StringBuilder builder   =  new StringBuilder();
		builder.append("SELECT NEW hospital.entity.Post(c.firstName,a.createDate, a.post,d.image,a.pkId,b.postPkId,a.senderEmployeePkId ) ");
		builder.append("FROM Post a ");
		builder.append("INNER JOIN PostMap b ON b.postPkId = a.pkId ");
		builder.append("INNER JOIN Employee c ON a.senderEmployeePkId = c.pkId ");
		builder.append("INNER JOIN Users d ON c.userPkId = d.pkId ");
		builder.append("WHERE  b.receiverEmployeePkId =:id  OR  a.senderEmployeePkId=:id ");
		builder.append("GROUP BY  c.firstName,a.createDate,a.post,d.image,a.pkId,b.postPkId,a.senderEmployeePkId  ");
		builder.append("ORDER BY a.createDate DESC ");
		return getByQuery(Post.class,builder.toString(),map);
	}

	@Override
	public List<Post> getPostPostPkId(BigDecimal id) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		map.put("postpkid", id);
		StringBuilder builder   =  new StringBuilder();
		builder.append("SELECT NEW hospital.entity.Post(a.post,a.createDate,c.firstName,a.senderEmployeePkId,a.pkId, b.postPkId ,b.pkId ) ");
		builder.append("FROM Post a ");
		builder.append("INNER JOIN PostMap b ON b.postPkId = a.pkId ");
		builder.append("INNER JOIN Employee c ON a.senderEmployeePkId = c.pkId ");
		builder.append(" WHERE b.postPkId = :postpkid ");
		return getByQuery(Post.class, builder.toString(),map);
	}

	@Override
	public List<Post> getPostMapReceiverPkId(BigDecimal pkId) throws Exception {
		CustomHashMap  map  =  new  CustomHashMap();
		map.put("pkId", pkId);
		StringBuilder builder   =  new StringBuilder();
		builder.append("SELECT c.firstName FROM Post a ");
		builder.append("INNER JOIN PostMap b ON a.pkId  =b.postPkId ");
		builder.append("INNER JOIN Employee c ON b.receiverEmployeePkId = c.pkId ");
		builder.append("WHERE  b.postPkId = :pkId ");
		return getByQuery(Post.class,builder.toString(),map);
	}

	@Override
	public List<Post> getPostPostPkIdView(BigDecimal id) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		map.put("pkid", id);
		StringBuilder builder   =  new StringBuilder();
		builder.append("SELECT NEW hospital.entity.Post(a.post,a.createDate,c.firstName,a.senderEmployeePkId,a.pkId,b.postPkId , b.pkId) ");
		builder.append("FROM Post a ");
		builder.append("INNER JOIN PostMap b ON b.postPkId = a.pkId ");
		builder.append("INNER JOIN Employee c ON a.senderEmployeePkId = c.pkId ");
		builder.append(" WHERE c.pkId  = :pkid ");
		return getByQuery(Post.class, builder.toString(),map);
	}

	@Override
	public List<Post> getPostPostUpdate(BigDecimal id) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		map.put("postpkid", id);
		StringBuilder builder   =  new StringBuilder();
		builder.append("SELECT NEW hospital.entity.Post(a.post,a.createDate,c.firstName,a.senderEmployeePkId,a.pkId, b.postPkId ,b.pkId ) ");
		builder.append("FROM Post a ");
		builder.append("INNER JOIN PostMap b ON b.postPkId = a.pkId ");
		builder.append("INNER JOIN Employee c ON a.senderEmployeePkId = c.pkId ");
		builder.append(" WHERE b.pkId = :postpkid ");
		return getByQuery(Post.class, builder.toString(),map);
	}

}

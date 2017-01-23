package hospital.businesslogic;


import hospital.businesslogic.interfaces.ILogicMenuLocal;
import hospital.entity.Aimag;
import hospital.entity.Customer;
import hospital.entity.CustomerDiagnose;
import hospital.entity.CustomerInspection;
import hospital.entity.CustomerMedicine;
import hospital.entity.CustomerPain;
import hospital.entity.CustomerPlan;
import hospital.entity.CustomerQuestion;
import hospital.entity.CustomerSurgery;
import hospital.entity.CustomerTreatment;
import hospital.entity.Diagnose;
import hospital.entity.Employee;
import hospital.entity.Examination;
import hospital.entity.ExaminationRequestCompleted;
import hospital.entity.Inspection;
import hospital.entity.InspectionForm;
import hospital.entity.Medicine;
import hospital.entity.Organization;
import hospital.entity.Soum;
import hospital.entity.SubOrganization;
import hospital.entity.Surgery;
import hospital.entity.Treatment;
import hospital.report.Am10;
import hospital.report.Am11;
import hospital.report.Am13a;
import hospital.report.Am25;
import hospital.report.Am29;
import hospital.report.Am8;
import hospital.report.Am9a;
import hospital.report.Gt15Pain;
import logic.data.CustomHashMap;
import sun.security.krb5.internal.tools.Klist;

import javax.ejb.Stateless;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

@Stateless(name = "LogicMenu", mappedName = "hospital.businesslogic.LogicMenu")
public class LogicMenu extends logic.SuperBusinessLogic
		implements hospital.businesslogic.interfaces.ILogicMenu, ILogicMenuLocal {

	@Resource
	SessionContext sessionContext;

	public LogicMenu() {

	}

	@Override
	public List<Inspection> getInspections(BigDecimal customerPkId,BigDecimal  employee) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", customerPkId);
		parameters.put("employeePkId", employee);
		jpql.append("SELECT NEW hospital.entity.Inspection(a.pkId, b.name, c.firstName, a.inspectionDate) ");
		jpql.append("FROM Inspection a ");
		jpql.append("INNER JOIN Employee c ON c.pkId = a.employeePkId ");
		jpql.append("INNER JOIN SubOrganization b ON b.pkId = c.subOrganizationPkId ");
		jpql.append("WHERE a.customerPkId =:customerPkId and c.pkId =:employeePkId  ");
		List<Inspection> list = new ArrayList<Inspection>();
		list = getByQuery(Inspection.class, jpql.toString(), parameters);
		
		return list;
	}

	@Override
	public Am8 getAm8(BigDecimal inspectionPkId) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("inspectionPkId", inspectionPkId);
		
		jpql.append("SELECT NEW hospital.report.Am8(b, d, f.nameEn, a.pkId)  ");
		jpql.append("FROM Inspection a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("INNER JOIN CustomerDiagnose c on  c.inspectionPkId = a.pkId ");
		jpql.append("INNER JOIN Employee d ON d.pkId  = a.employeePkId ");
		jpql.append("INNER JOIN Diagnose f ON f.pkId  = c.diagnosePkId ");
		jpql.append("WHERE c.type = 1 ");
		jpql.append("AND a.pkId = :inspectionPkId ");
		
		List<Am8> list = new ArrayList<Am8>();
		list = getByQuery(Am8.class, jpql.toString(), parameters);
		Am8 ret = new Am8();
		
		if(list.size()>=1)
			ret = list.get(0);
		
		return ret;
	}
	
	@Override
	public List<ExaminationRequestCompleted> getCompletedRequests(BigDecimal customerPkId) throws Exception {
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		if(customerPkId!=null)
			parameters.put("customerPkId", customerPkId);
		
		jpql.append("SELECT NEW hospital.entity.ExaminationRequestCompleted(a.pkId, a.customerPkId, a.examinationPkId, a.requestDate, b.name, a.mood, c) ");
		jpql.append("FROM ExaminationRequestCompleted a ");
		jpql.append("INNER JOIN Examination b ON a.examinationPkId = b.pkId ");
		jpql.append("INNER JOIN Customer c ON c.pkId = a.customerPkId ");
		if(customerPkId!=null)
			jpql.append("WHERE a.customerPkId = :customerPkId ");
		
		List<ExaminationRequestCompleted> list = new ArrayList<ExaminationRequestCompleted>();
		list = getByQuery(ExaminationRequestCompleted.class, jpql.toString(), parameters);
		return list;
	}

	@Override
	public List<Employee> getEmployees(BigDecimal soPkId) throws Exception {
		return getByAnyField(Employee.class, "subOrganizationPkId", soPkId);
	}

	@Override
	public Employee getCursorEmployee(BigDecimal pkId) throws Exception {
		return getByPkId(Employee.class, pkId);
	}

	@Override
	public Am9a getAm9a(BigDecimal inspectionPkId) throws Exception {
		CustomHashMap  map   =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("inspectionPkId", inspectionPkId);
		Am9a am9a = new Am9a();
		Inspection inspection = getByPkId(Inspection.class, inspectionPkId);
		if(inspection != null){
			am9a.setCustomer(getByPkId(Customer.class, inspection.getCustomerPkId()));
			am9a.setEmp( getByPkId(Employee.class, inspection.getEmployeePkId()));
		 }
		builder = new StringBuilder();
		builder.append("SELECT a FROM Medicine a ");
		builder.append(" INNER JOIN CustomerMedicine b ON a.pkId = b.medicinePkId "); // 
		builder.append(" ");
		builder.append("");
		builder.append("WHERE b.inspectionPkId  = :inspectionPkId ");
		am9a.setMedicines(getByQuery(Medicine.class, builder.toString(), map));
		return am9a;
	}

	@Override
	public Am9a getAm9aDiagnose(BigDecimal employee, BigDecimal customer, BigDecimal  inspectionId) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("employeePkId", employee);
		map.put("customerPkid", customer);
		map.put("inspectionPkId", inspectionId);
		Am9a  am9a  =  new Am9a();
		Customer  cus  =  getByPkId(Customer.class, customer);
		System.out.println( "menuLogic organizationId" + cus.getOrganizationPkId());
		am9a.setOrganization(getByPkId(Organization.class, cus.getOrganizationPkId()));
		builder  =  new StringBuilder();
		builder.append(" SELECT distinct d FROM CustomerDiagnose  cd ");
		builder.append(" INNER JOIN Inspection i ON cd.inspectionPkId  =  i.pkId ");
		builder.append(" INNER JOIN Customer c ON c.pkId  =  i.customerPkId  ");
		builder.append(" INNER JOIN Diagnose d ON cd.diagnosePkId = d.pkId   ");
		builder.append(" WHERE  cd.type=1 and cd.employeePkId = :employeePkId  and i.customerPkId= :customerPkid and cd.inspectionPkId=:inspectionPkId");
		builder.append(" ");
		am9a.setCustomerDiagnose(getByQuery(CustomerDiagnose.class, builder.toString(),map));
		return am9a;
	}

	@Override
	public Am10 getAm10(BigDecimal customerPkId) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("customerPkId", customerPkId);
		Am10 am10  =  new Am10();
		builder  =  new StringBuilder();
		builder.append(" SELECT  c FROM Customer  c");
		builder.append(" WHERE c.pkId = :customerPkId");
		builder.append(" ");
		Customer  customer  =  getByPkId(Customer.class, customerPkId);
	
		if (customer!=null) {
		  	 am10.setOrganization(getByPkId(Organization.class,customer.getOrganizationPkId()));
		  	 
		}
		am10.setCustomer(getByQuery(Customer.class, builder.toString(),map));
		return am10;
	}

	@Override
	public Am11 getAm11(BigDecimal customerPkId) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("customerPkId", customerPkId);
		Am11 am11  =  new Am11();
		Customer  customer  =  getByPkId(Customer.class, customerPkId);
		if (customer!=null) {
			am11.setOrganization(getByPkId(Organization.class, customer.getOrganizationPkId()));
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT  e FROM Customer  e");
		builder.append(" WHERE e.pkId = :customerPkId ");
		builder.append(" ");
		am11.setCustomer(getByQuery(Customer.class,builder.toString() ,map));
		return am11;
	}

	@Override
	public Am13a getAm13a(BigDecimal customerPkId) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("customerPkId", customerPkId);
		Am13a  am13a=  new Am13a();
		Customer  customer  =  getByPkId(Customer.class, customerPkId);
		if (customer!=null) {
			am13a.setOrganization(getByPkId(Organization.class, customer.getOrganizationPkId()));
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT  c FROM Customer  c");
		builder.append(" WHERE c.pkId = :customerPkId");
		builder.append(" ");
		am13a.setCustomer(getByQuery(Customer.class, builder.toString(),map));
		
		return am13a;
	}

	@Override
	public Am11 getAm11Employee(BigDecimal employee) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("employeePkId", employee);
		Am11 am11  =  new Am11();
		Employee  emp  =  getByPkId(Employee.class,employee);
		if (emp!=null) {
			am11.setSubOrganization(getByPkId(SubOrganization.class, emp.getSubOrganizationPkId()));
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT  e FROM Employee e");
		builder.append(" WHERE e.pkId = :employeePkId ");
		builder.append(" ");
		am11.setEmployees(getByQuery(Employee.class,builder.toString() ,map));
		return am11;
	}

	@Override
	public Am11 getAm11EmployeeCustomer(BigDecimal employee, BigDecimal customer) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("employeePkId", employee);
		map.put("customerPkid", customer);
		Am11 am11  =  new Am11();
		builder  =  new StringBuilder();
		builder.append(" SELECT distinct d FROM CustomerDiagnose  cd ");
		builder.append(" INNER JOIN Inspection i ON cd.inspectionPkId  =  i.pkId ");
		builder.append(" INNER JOIN Customer c ON c.pkId  =  i.customerPkId  ");
		builder.append(" INNER JOIN Diagnose d ON cd.diagnosePkId  = d.pkId ");
		builder.append(" WHERE  cd.type=1 and cd.employeePkId = :employeePkId  and i.customerPkId= :customerPkid");
		builder.append(" ");
		am11.setCustomerDiagnose(getByQuery(CustomerDiagnose.class, builder.toString(),map));
		return am11;
	}

	@Override
	public Am25 getAm25(BigDecimal customer) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("customerPkId", customer);
		Am25  am25  =  new Am25();
		Customer  customers  =  getByPkId(Customer.class, customer);
		if (customers!=null) {
			am25.setCustomers(getByPkId(Customer.class, customers.getPkId()));
			am25.setOrganization(getByPkId(Organization.class, customers.getOrganizationPkId()));
			
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT  c FROM CustomerTreatment  c");
		builder.append(" WHERE c.customerPkId= :customerPkId");
		builder.append(" ORDER BY c.createdDate ASC");
		am25.setCustomerTreatments(getByQuery(CustomerTreatment.class, builder.toString(),map));
		am25.setTreatmentcount((getByQuery(CustomerTreatment.class, builder.toString(),map).size()));
		return am25;
	}

//	@Override
//	public Am25 getAm25Employee(BigDecimal employee) throws Exception {
//		CustomHashMap  map  =  new CustomHashMap();
//		StringBuilder  builder  =  new StringBuilder();
//		map.put("employeePkId", employee);
//		Am25  am25  =  new Am25();
//		Employee  emp  =  getByPkId(Employee.class,employee);
//		if (emp!=null) {
//			am25.setSubOrganization(getByPkId(SubOrganization.class, emp.getSubOrganizationPkId()));
//		}
//		builder  =  new StringBuilder();
//		builder.append(" SELECT  e FROM Employee e");
//		builder.append(" WHERE e.pkId = :employeePkId ");
//		builder.append(" ");
//		am25.setEmployees(getByQuery(Employee.class,builder.toString() ,map));
//		return am25;
//	}

	@Override
	public Am25 getAm25Diagnose(BigDecimal employee, BigDecimal customer) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("employeePkId", employee);
		map.put("customerPkid", customer);

		Am25  am25  =  new Am25();
		Employee  employee2  = getByPkId(Employee.class, employee);
		if (employee2!=null) {
			am25.setEmployees(getByPkId(Employee.class, employee2.getPkId()));
			am25.setSubOrganization(getByPkId(SubOrganization.class, employee2.getSubOrganizationPkId()));
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT distinct d FROM CustomerDiagnose  cd ");
		builder.append(" INNER JOIN Inspection i ON cd.inspectionPkId  =  i.pkId ");
		builder.append(" INNER JOIN Customer c ON c.pkId  =  i.customerPkId  ");
		builder.append(" INNER JOIN Diagnose d  ON   cd.diagnosePkId  = d.pkId");
		builder.append(" WHERE  cd.type=1 and cd.employeePkId = :employeePkId  and i.customerPkId= :customerPkid");
		builder.append(" ");
		am25.setCustomerDiagnose(getByQuery(CustomerDiagnose.class, builder.toString(),map));
		return am25;
	}

	@Override
	public Am29 getAm29Customer(BigDecimal customer) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("customerPkId", customer);
		Am29  am29 =  new Am29();
		Customer  customers  =  getByPkId(Customer.class, customer);
		if (customers!=null) {
			am29.setOrganization(getByPkId(Organization.class, customers.getOrganizationPkId()));
		}
		builder  =  new StringBuilder();
		builder.append(" SELECT  c FROM Customer  c");
		builder.append(" WHERE c.pkId = :customerPkId");
		builder.append(" ");
		am29.setCustomers(getByQuery(Customer.class, builder.toString(),map));
		return am29;
	}

	@Override
	public Am29 getAm29Diagnose(BigDecimal employee, BigDecimal customer) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		StringBuilder  builder  =  new StringBuilder();
		map.put("employeePkId", employee);
		map.put("customerPkid", customer);
		Am29  am29  =  new Am29();
		builder  =  new StringBuilder();
		Employee e =  getByPkId(Employee.class, employee);
		if (e!=null) {
			am29.setEmployee(getByPkId(Employee.class, e.getPkId()));
		}
		builder.append(" SELECT distinct d FROM CustomerDiagnose  cd ");
		builder.append(" INNER JOIN Inspection i ON cd.inspectionPkId  =  i.pkId ");
		builder.append(" INNER JOIN Customer c ON c.pkId  =  i.customerPkId  ");
		builder.append(" INNER JOIN Diagnose d ON  cd.diagnosePkId  = d.pkId ");
		builder.append(" WHERE  cd.type=1 and cd.employeePkId = :employeePkId  and i.customerPkId= :customerPkid");
		builder.append(" ");
		builder.append("");
		builder.append("");
		am29.setCustomerDiagnose(getByQuery(CustomerDiagnose.class, builder.toString(),map));
		return am29;
	}
//
//	@Override
//	public Am29 getAm29CustomerThratmentDate(BigDecimal customer) throws Exception {
//		// TODO Auto-generated method stub
//		CustomHashMap  map =  new CustomHashMap();
//		map.put("key",customer);
//		StringBuilder  builder  =  new StringBuilder();
//		builder.append("SELECT a FROM CustomerTreatment a  ");
//		builder.append("WHERE a.customerPkId   = :key");
//		Am29  am29  =  new Am29();
//		am29.setCustomerTreatment(getByQuery(CustomerTreatment.class,builder.toString(),map));
//		return a;
//	}

	@Override
	public List<Gt15Pain> getCustomerSubOrganization(BigDecimal customerPkId) throws Exception {
		CustomHashMap  map  =  new CustomHashMap();
		map.put("customerPkId", customerPkId);
		StringBuilder  builder   =  new StringBuilder();
		builder.append("SELECT  NEW hospital.report.Gt15Pain(a.inspectionDate,a.pkId, c.firstName,d) ");
		builder.append("FROM Inspection a ");
		builder.append("INNER JOIN Employee  c ON a.employeePkId =  c.pkId ");
		builder.append("INNER JOIN SubOrganization  d  ON c.subOrganizationPkId = d.pkId ");
		builder.append("WHERE a.customerPkId = :customerPkId ");
		builder.append("ORDER BY a.inspectionDate DESC "); 
		return getByQuery(Gt15Pain.class,builder.toString(),map);
	}

	@Override
	public Am8 getAm8Cutomer(BigDecimal customerPkId) throws Exception {
		// TODO Auto-generated method stub
		CustomHashMap  map  =  new CustomHashMap();
		map.put("customerPkId", customerPkId);
		StringBuilder  builder   =  new StringBuilder();
		builder.append("SELECT  NEW hospital.report.Am8(A,B,C,D) FROM Customer A ");
		builder.append("LEFT JOIN Aimag B  ON A.aimagPkId = B.pkId ");
		builder.append("LEFT JOIN Soum  C  ON  B.pkId=  C.aimagPkId ");
		builder.append("LEFT JOIN ViewConstantJob  D  ON  A.job  = D.pkId ");
		builder.append("WHERE A.pkId = :customerPkId");
		Am8  am8  =  new Am8();
		List<Am8>  list   =  getByQuery(Am8.class, builder.toString(),map);
		if (list.size()>0) {
			am8  = list.get(0);
		}
		return am8;
	}
}

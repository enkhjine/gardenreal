package hospital.businesslogic;

import hospital.businessentity.CashierReportMonth;
import hospital.businessentity.InspectionHistory;
import hospital.businessentity.LoggedUser;
import hospital.businessentity.Tool;
import hospital.businesslogic.interfaces.ILogicTwoLocal;
import hospital.entity.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicTwo", mappedName = "hospital.businesslogic.LogicTwo")
public class LogicTwo extends logic.SuperBusinessLogic implements hospital.businesslogic.interfaces.ILogicTwo,ILogicTwoLocal {
	
	@Resource
	SessionContext sessionContext;
	
	public LogicTwo(){
		 
	}
	
	public void setDataBaseInfo() throws Exception{
		//dataBaseName = "Hospital"
	}
	
	@Override
	public void saveEconomicCalendar(LoggedUser loggedUser, EconomicCalendar calendar) throws Exception{
		
		
		Date dte = new Date();
		
		calendar.setUpdatedBy(loggedUser.getUser().getPkId());
		calendar.setUpdatedDate(dte);
		
		update(calendar);
	}
	
	@Override
	public EconomicCalendar getEconomicCalendar(LoggedUser loggedUser) throws Exception{
		EconomicCalendar calendar;
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql;
		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		
		if(loggedUser == null || loggedUser.getOrganization() == null || loggedUser.getOrganization().getPkId() == null) throw new Exception("none");
		
		BigDecimal organizationPkId = loggedUser.getOrganization().getPkId();
		parameters.put("organizationPkId", organizationPkId);
		
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM EconomicCalendar a WHERE a.organizationPkId = :organizationPkId ");
		List<EconomicCalendar> calendars = getByQuery(EconomicCalendar.class, jpql.toString(), parameters);
		
		if(calendars.size() < 1){
			calendar = new EconomicCalendar();
			calendar.setPkId(pkId);
			calendar.setOrganizationPkId(organizationPkId);
			calendar.setBeginHour(9);
			calendar.setBeginMinute(0);
			calendar.setEndHour(18);
			calendar.setEndMinute(0);
			calendar.setLunchBeginHour(12);
			calendar.setLunchBeginMinute(0);
			calendar.setLunchEndHour(13);
			calendar.setLunchEndMinute(0);
			calendar.setIsSaturday(0);
			calendar.setIsSunday(0);
			calendar.setCreatedBy(loggedUser.getUser().getPkId());
			calendar.setCreatedDate(dte);
			calendar.setUpdatedBy(loggedUser.getUser().getPkId());
			calendar.setUpdatedDate(dte);
			
			insert(calendar);
			saveEconomicCalendar(loggedUser, calendar);
		}else {
			calendar = calendars.get(0);
		}
		
		return calendar;
	}
	
	public EconomicCalendar getEconomicCalendar() throws Exception{
		
		
		LoggedUser loggedUser = new LoggedUser();
		loggedUser.setOrganization(getAll(Organization.class).get(0));
		loggedUser.setUser(getAll(Users.class).get(0));
		
		EconomicCalendar calendar;
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql;
		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		
		if(loggedUser == null || loggedUser.getOrganization() == null || loggedUser.getOrganization().getPkId() == null) throw new Exception("none");
		
		BigDecimal organizationPkId = loggedUser.getOrganization().getPkId();
		parameters.put("organizationPkId", organizationPkId);
		
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM EconomicCalendar a WHERE a.organizationPkId = :organizationPkId ");
		List<EconomicCalendar> calendars = getByQuery(EconomicCalendar.class, jpql.toString(), parameters);
		
		if(calendars.size() < 1){
			calendar = new EconomicCalendar();
			calendar.setPkId(pkId);
			calendar.setOrganizationPkId(organizationPkId);
			calendar.setBeginHour(9);
			calendar.setBeginMinute(0);
			calendar.setEndHour(18);
			calendar.setEndMinute(0);
			calendar.setLunchBeginHour(12);
			calendar.setLunchBeginMinute(0);
			calendar.setLunchEndHour(13);
			calendar.setLunchEndMinute(0);
			calendar.setIsSaturday(0);
			calendar.setIsSunday(0);
			calendar.setCreatedBy(loggedUser.getUser().getPkId());
			calendar.setCreatedDate(dte);
			calendar.setUpdatedBy(loggedUser.getUser().getPkId());
			calendar.setUpdatedDate(dte);
			
			insert(calendar);
			saveEconomicCalendar(loggedUser, calendar);
		}else {
			calendar = calendars.get(0);
		}
		
		return calendar;
	}
	
	public List<SubOrganization> getSubOrganizations(LoggedUser loggedUser) throws Exception{
		List<SubOrganization> list = getAll(SubOrganization.class);
		return list;
	}
	
	public List<Employee> getEmployeeBySubOrganizationPkId(BigDecimal pkId) throws Exception{
		List<Employee> employees = getByAnyField(Employee.class, "subOrganizationPkId", pkId);
		return employees;
	}
	
	public void calculateEconomicCalendarDate(EconomicCalendarHdr calendarHdr) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("hdrPkId", calendarHdr.getPkId());
		jpql = new StringBuilder();
		jpql.append("DELETE FROM EconomicCalendarDate a ");
		jpql.append("WHERE a.hdrPkId = :hdrPkId ");
		
		executeNonQuery(jpql.toString(), parameters);
		
		Calendar beginDate = Calendar.getInstance();
		beginDate.set(Calendar.YEAR, calendarHdr.getBeginDate().getYear() + 1900);
		beginDate.set(Calendar.MONTH, calendarHdr.getBeginDate().getMonth());
		beginDate.set(Calendar.DATE, calendarHdr.getBeginDate().getDate());
		Calendar endDate = Calendar.getInstance();
		endDate.set(Calendar.YEAR, calendarHdr.getEndDate().getYear() + 1900);
		endDate.set(Calendar.MONTH, calendarHdr.getEndDate().getMonth());
		endDate.set(Calendar.DATE, calendarHdr.getEndDate().getDate());
		
		List<EconomicCalendarDate> calendarDates = new ArrayList<EconomicCalendarDate>();
		BigDecimal pkId = Tools.newPkId();
		
		if(calendarHdr.getConfigurationStr() != null && calendarHdr.getConfigurationStr().length() > 0){
			String string = calendarHdr.getConfigurationStr();
			if(string.charAt(0) == '0'){
				//Давтамж тохируулах Долоо хоногоор
				for(Calendar c = beginDate; endDate.getTime().after(c.getTime()); c.add(Calendar.DATE, 1)){
					int dayOfWeek = c.get(c.DAY_OF_WEEK);
					if((dayOfWeek == 2 && string.charAt(2) == 'Y') || (dayOfWeek == 3 && string.charAt(3) == 'Y') || (dayOfWeek == 4 && string.charAt(4) == 'Y') || (dayOfWeek == 5 && string.charAt(5) == 'Y') || (dayOfWeek == 6 && string.charAt(6) == 'Y') || (dayOfWeek == 7 && string.charAt(7) == 'Y') || (dayOfWeek == 1 && string.charAt(8) == 'Y')){
						EconomicCalendarDate calendarDate = new EconomicCalendarDate();
						pkId = pkId.add(BigDecimal.ONE);
						calendarDate.setPkId(pkId);
						calendarDate.setHdrPkId(calendarHdr.getPkId());
						calendarDate.setYear(c.getTime().getYear());
						calendarDate.setMonth(c.getTime().getMonth());
						calendarDate.setDay(c.getTime().getDate());
						calendarDates.add(calendarDate);
					}
				}
			}else if(string.charAt(0) == '1'){
				if(string.charAt(2) == '0'){
					//Давтамж тохируулах Сараар Өдөр
					for(Calendar c = beginDate; endDate.getTime().after(c.getTime()); c.add(Calendar.DATE, 1)){
						if(calendarHdr.getMonthBeginDate() <= c.getTime().getDate() && calendarHdr.getMonthEndDate() >= c.getTime().getDate()){
							EconomicCalendarDate calendarDate = new EconomicCalendarDate();
							pkId = pkId.add(BigDecimal.ONE);
							calendarDate.setPkId(pkId);
							calendarDate.setHdrPkId(calendarHdr.getPkId());
							calendarDate.setYear(c.getTime().getYear());
							calendarDate.setMonth(c.getTime().getMonth());
							calendarDate.setDay(c.getTime().getDate());
							calendarDates.add(calendarDate);
						}
					}
				}else if(string.charAt(2) == '1'){
					//Давтамж тохируулах Сараар Долоо хоног
					int dayOfWeek1 = 0;
					for(Calendar c = beginDate; endDate.getTime().after(c.getTime()); c.add(Calendar.DATE, 1)){
						int dayOfWeek2 = c.get(c.DAY_OF_WEEK);
						if(dayOfWeek2 == 2){
							int co = c.getTime().getDate();
							dayOfWeek1 = 5;
							if(co <= 28) dayOfWeek1 = 4;
							if(co <= 21) dayOfWeek1 = 3;
							if(co <= 14) dayOfWeek1 = 2;
							if(co <= 7) dayOfWeek1 = 1;
						}
						if(dayOfWeek1 == calendarHdr.getMonthIndex()){
							int dayOfWeek = c.get(c.DAY_OF_WEEK);
							if((dayOfWeek == 2 && string.charAt(2) == 'Y') || (dayOfWeek == 3 && string.charAt(3) == 'Y') || (dayOfWeek == 4 && string.charAt(4) == 'Y') || (dayOfWeek == 5 && string.charAt(5) == 'Y') || (dayOfWeek == 6 && string.charAt(6) == 'Y') || (dayOfWeek == 7 && string.charAt(7) == 'Y') || (dayOfWeek == 1 && string.charAt(8) == 'Y')){
								EconomicCalendarDate calendarDate = new EconomicCalendarDate();
								pkId = pkId.add(BigDecimal.ONE);
								calendarDate.setPkId(pkId);
								calendarDate.setHdrPkId(calendarHdr.getPkId());
								calendarDate.setYear(c.getTime().getYear());
								calendarDate.setMonth(c.getTime().getMonth());
								calendarDate.setDay(c.getTime().getDate());
								calendarDates.add(calendarDate);
							}
						}
					}
				}
			}
			
		}else {
			for(Calendar c = beginDate; endDate.getTime().after(c.getTime()); c.add(Calendar.DATE, 1)){
				EconomicCalendarDate calendarDate = new EconomicCalendarDate();
				pkId = pkId.add(BigDecimal.ONE);
				calendarDate.setPkId(pkId);
				calendarDate.setHdrPkId(calendarHdr.getPkId());
				calendarDate.setYear(c.getTime().getYear());
				calendarDate.setMonth(c.getTime().getMonth());
				calendarDate.setDay(c.getTime().getDate());
				calendarDates.add(calendarDate);
			}
		}
		
		insert(calendarDates);
	}
	
	public boolean isDuplicateEconomicCalendarHdr(LoggedUser loggedUser, EconomicCalendarHdr calendarHdr) throws Exception {
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("employeePkId", loggedUser.getEmployeePkId());
		
		jpql.append("SELECT a FROM EconomicCalendarHdr a ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		
		return false;
	}
	
	public void saveEconomicCalendarHdr(LoggedUser loggedUser, EconomicCalendarHdr calendarHdr, List<Employee> employees) throws Exception{
		BigDecimal pkId = Tools.newPkId();
		Date dte = new Date();
		
		calendarHdr.setOrganizationPkId(loggedUser.getOrganization().getPkId());
		calendarHdr.setUpdatedBy(loggedUser.getUser().getPkId());
		calendarHdr.setUpdatedDate(dte);
		
		if(Tool.ADDED.equals(calendarHdr.getStatus())){
			calendarHdr.setPkId(pkId);
			calendarHdr.setCreatedBy(loggedUser.getUser().getPkId());
			calendarHdr.setCreatedDate(dte);
			insert(calendarHdr);
			calculateEconomicCalendarDate(calendarHdr);
			if(employees != null && employees.size() > 0){
				List<EconomicCalendarDtl> calendarDtls = new ArrayList<EconomicCalendarDtl>();
				for (Employee employee : employees) {
					pkId = pkId.add(BigDecimal.ONE);
					
					EconomicCalendarDtl calendarDtl = new EconomicCalendarDtl();
					calendarDtl.setPkId(pkId);
					calendarDtl.setHdrPkId(calendarHdr.getPkId());
					calendarDtl.setEmployeePkId(employee.getPkId());
					calendarDtls.add(calendarDtl);
				}
				
				insert(calendarDtls);
			}
		}
	}
	
	public List<EconomicCalendarHdr> getEconomicCalendarListHdr(List<BigDecimal> employeePkIds, int year, int month, int day) throws Exception{
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("employeePkIds", employeePkIds);
		parameters.put("year", year);
		parameters.put("month", month);
		parameters.put("day", day);
		jpql.append("SELECT DISTINCT a FROM EconomicCalendarHdr a ");
		jpql.append("INNER JOIN EconomicCalendarDtl b ON a.pkId = b.hdrPkId ");
		jpql.append("INNER JOIN EconomicCalendarDate c ON a.pkId = c.hdrPkId ");
		jpql.append("WHERE b.employeePkId IN :employeePkIds ");
		jpql.append("AND b.year = :year ");
		jpql.append("AND b.month = :month ");
		jpql.append("AND b.day = :day ");
		
		return getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
	}
	
	public EconomicCalendarHdr getNewEconomicCalendarHdr(EconomicCalendarDate date, EconomicCalendarHdr economicCalendarHdr) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("hdrPkId", economicCalendarHdr.getPkId());
		jpql = new StringBuilder();
		jpql.append("SELECT a FROM Employee a ");
		jpql.append("INNER JOIN EconomicCalendarDtl b ON b.employeePkId = a.pkId ");
		jpql.append("WHERE b.hdrPkId = :hdrPkId ");
		
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.YEAR, date.getYear() + 1900);
		c3.set(Calendar.MONTH, date.getMonth());
		c3.set(Calendar.DATE, date.getDay());
		c3.set(Calendar.HOUR, 0);
		c3.set(Calendar.MINUTE, 0);
		c3.set(Calendar.SECOND, 0);
		
		Calendar c4 = Calendar.getInstance();
		c4.set(Calendar.YEAR, date.getYear() + 1900);
		c4.set(Calendar.MONTH, date.getMonth());
		c4.set(Calendar.DATE, date.getDay());
		c4.set(Calendar.HOUR, 23);
		c4.set(Calendar.MINUTE, 59);
		c4.set(Calendar.SECOND, 59);
		
		EconomicCalendarHdr calendarHdr = new EconomicCalendarHdr();
		calendarHdr.setEmployees(getByQuery(Employee.class, jpql.toString(), parameters));
		calendarHdr.setPkId(economicCalendarHdr.getPkId());
		calendarHdr.setOrganizationPkId(economicCalendarHdr.getOrganizationPkId());
		calendarHdr.setType(economicCalendarHdr.getType());
		calendarHdr.setTypePkId(economicCalendarHdr.getTypePkId());
		calendarHdr.setName(economicCalendarHdr.getName());
		calendarHdr.setConfigurationStr(economicCalendarHdr.getConfigurationStr());
		calendarHdr.setBeginDate(c3.getTime());
		calendarHdr.setEndDate(c4.getTime());
		calendarHdr.setRateType(economicCalendarHdr.getRateType());
		calendarHdr.setBeginTimeHour(economicCalendarHdr.getBeginTimeHour());
		calendarHdr.setBeginTimeMinute(economicCalendarHdr.getBeginTimeMinute());
		calendarHdr.setEndTimeHour(economicCalendarHdr.getEndTimeHour());
		calendarHdr.setEndTimeMinute(economicCalendarHdr.getEndTimeMinute());
		calendarHdr.setCreatedDate(economicCalendarHdr.getCreatedDate());
		calendarHdr.setCreatedBy(economicCalendarHdr.getCreatedBy());
		calendarHdr.setUpdatedDate(economicCalendarHdr.getUpdatedDate());
		calendarHdr.setUpdatedBy(economicCalendarHdr.getUpdatedBy());
		calendarHdr.setEmployees(economicCalendarHdr.getEmployees());
		return calendarHdr;
	}
	
	public List<EconomicCalendarHdr> getEconomicCalendarHdr(LoggedUser loggedUser, BigDecimal decimal) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("typePkId", decimal);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM EconomicCalendarHdr a ");
		if(decimal == null || decimal.compareTo(BigDecimal.ZERO) == 0){
			jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		}else {
			jpql.append("WHERE a.typePkId = :typePkId ");
		}
		
		List<EconomicCalendarHdr> retCalendars = new ArrayList<EconomicCalendarHdr>();
		List<EconomicCalendarHdr> calendarHdrs = getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
		
		for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs) {
			if(economicCalendarHdr.getConfigurationStr() == null || economicCalendarHdr.getConfigurationStr().length() < 1){
				SubOrganization organization = getByPkId(SubOrganization.class, economicCalendarHdr.getTypePkId());
				if(organization != null){
					economicCalendarHdr.setSubOrganizationName(organization.getName());
				}else {
					Employee employee = getByPkId(Employee.class, economicCalendarHdr.getTypePkId());
					if(employee != null) {
						organization = getByPkId(SubOrganization.class, employee.getSubOrganizationPkId());
						economicCalendarHdr.setSubOrganizationName(organization.getName());
					}
				}
				
				for(Date indexDate = economicCalendarHdr.getBeginDate(); indexDate.compareTo(economicCalendarHdr.getEndDate()) <= 0; indexDate = Tool.addDays(indexDate, 1)) {
					EconomicCalendarDate calendarDate = new EconomicCalendarDate();
					calendarDate.setYear(indexDate.getYear());
					calendarDate.setMonth(indexDate.getMonth());
					calendarDate.setDay(indexDate.getDate());
					retCalendars.add(getNewEconomicCalendarHdr(calendarDate, economicCalendarHdr));
				}
			}else {
				parameters.put("hdrPkId", economicCalendarHdr.getPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM Employee a ");
				jpql.append("INNER JOIN EconomicCalendarDtl b ON b.employeePkId = a.pkId ");
				jpql.append("WHERE b.hdrPkId = :hdrPkId ");
				
				SubOrganization organization = getByPkId(SubOrganization.class, economicCalendarHdr.getTypePkId());
				if(organization != null){
					economicCalendarHdr.setSubOrganizationName(organization.getName());
				}else {
					Employee employee = getByPkId(Employee.class, economicCalendarHdr.getTypePkId());
					if(employee != null) {
						economicCalendarHdr.setSubOrganizationName(employee.getFirstName());
					}
				}
				economicCalendarHdr.setEmployees(getByQuery(Employee.class, jpql.toString(), parameters));
				
				jpql = new StringBuilder();
				parameters.put("hdrPkId", economicCalendarHdr.getPkId());
				parameters.put("year", (new Date()).getYear());
				jpql.append("SELECT a FROM EconomicCalendarDate a WHERE a.hdrPkId = :hdrPkId AND a.year = :year ");
				List<EconomicCalendarDate> calendarDates = getByQuery(EconomicCalendarDate.class, jpql.toString(), parameters);
				if(calendarDates == null || calendarDates.size() < 1){
					retCalendars.add(economicCalendarHdr);
				}else {
					for(EconomicCalendarDate date : calendarDates){
						retCalendars.add(getNewEconomicCalendarHdr(date, economicCalendarHdr));
					}
				}
			}
		}
		
		return retCalendars;
	}
	
	public List<EconomicCalendarHdr> getEmployeeEconomicCalendarHdr(LoggedUser loggedUser, BigDecimal decimal) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("typePkId", decimal);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM EconomicCalendarHdr a ");
		if(decimal == null || decimal.compareTo(BigDecimal.ZERO) == 0){
			jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		}else {
			jpql.append("WHERE a.typePkId = :typePkId ");
		}
		
		List<EconomicCalendarHdr> retCalendars = new ArrayList<EconomicCalendarHdr>();
		List<EconomicCalendarHdr> calendarHdrs = getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
		
		for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs) {
			if(economicCalendarHdr.getConfigurationStr() == null || economicCalendarHdr.getConfigurationStr().length() < 1){
				SubOrganization organization = getByPkId(SubOrganization.class, economicCalendarHdr.getTypePkId());
				if(organization != null){
					economicCalendarHdr.setSubOrganizationName(organization.getName());
				}else {
					Employee employee = getByPkId(Employee.class, economicCalendarHdr.getTypePkId());
					if(employee != null) {
						organization = getByPkId(SubOrganization.class, employee.getSubOrganizationPkId());
						economicCalendarHdr.setSubOrganizationName(organization.getName());
					}
				}
				
				for(Date indexDate = economicCalendarHdr.getBeginDate(); indexDate.compareTo(economicCalendarHdr.getEndDate()) <= 0; indexDate = Tool.addDays(indexDate, 1)) {
					EconomicCalendarDate calendarDate = new EconomicCalendarDate();
					calendarDate.setYear(indexDate.getYear());
					calendarDate.setMonth(indexDate.getMonth());
					calendarDate.setDay(indexDate.getDate());
					retCalendars.add(getNewEconomicCalendarHdr(calendarDate, economicCalendarHdr));
				}
			}else {
				parameters.put("hdrPkId", economicCalendarHdr.getPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM Employee a ");
				jpql.append("INNER JOIN EconomicCalendarDtl b ON b.employeePkId = a.pkId ");
				jpql.append("WHERE b.hdrPkId = :hdrPkId ");
				
				SubOrganization organization = getByPkId(SubOrganization.class, economicCalendarHdr.getTypePkId());
				if(organization != null){
					economicCalendarHdr.setSubOrganizationName(organization.getName());
				}else {
					Employee employee = getByPkId(Employee.class, economicCalendarHdr.getTypePkId());
					if(employee != null) {
						economicCalendarHdr.setSubOrganizationName(employee.getFirstName());
					}
				}
				economicCalendarHdr.setEmployees(getByQuery(Employee.class, jpql.toString(), parameters));
				
				jpql = new StringBuilder();
				parameters.put("hdrPkId", economicCalendarHdr.getPkId());
				parameters.put("year", (new Date()).getYear());
				jpql.append("SELECT a FROM EconomicCalendarDate a WHERE a.hdrPkId = :hdrPkId AND a.year = :year ");
				List<EconomicCalendarDate> calendarDates = getByQuery(EconomicCalendarDate.class, jpql.toString(), parameters);
				if(calendarDates == null || calendarDates.size() < 1){
					retCalendars.add(economicCalendarHdr);
				}else {
					for(EconomicCalendarDate date : calendarDates){
						retCalendars.add(getNewEconomicCalendarHdr(date, economicCalendarHdr));
					}
				}
			}
		}
		
		jpql = new StringBuilder();
		jpql.append("SELECT NEW hospital.entity.EconomicCalendarHdr(c, b.organizationPkId, b.firstName, a.description, a.beginDate, a.endDate) ");
		jpql.append("FROM EmployeeRequest a ");
		jpql.append("INNER JOIN Customer b ON a.customerPkId = b.pkId ");
		jpql.append("INNER JOIN Employee c ON a.employeePkId = c.pkId ");
		
		List<EconomicCalendarHdr> calendarHdrs2 = getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
		retCalendars.addAll(calendarHdrs2);
		return retCalendars;
	}
	
	@Override
	public void deletetEmployeeCalendar(BigDecimal calendarPkId) throws Exception{
		deleteByAnyField(EconomicCalendarDtl.class, "hdrPkId", calendarPkId);
		deleteByAnyField(EconomicCalendarDate.class, "hdrPkId", calendarPkId);
		deleteByPkId(EconomicCalendarHdr.class, calendarPkId);
	}
	
	public List<EconomicCalendarDtl> getEconomicCalendarDtlByDate(Date beginDate, Date endDate, LoggedUser loggedUser, BigDecimal typePkId, int year, int month, int day) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("typePkId", typePkId);
		List<BigDecimal> pkIds = getByQuery(BigDecimal.class, "SELECT a.pkId FROM Employee a WHERE a.subOrganizationPkId = :typePkId", parameters);
		
		parameters.put("beginDate", beginDate);
		parameters.put("endDate", endDate);
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		parameters.put("year", year);
		parameters.put("pkIds", pkIds);
		parameters.put("month", month);
		parameters.put("day", day);
		
		jpql.append("SELECT NEW hospital.entity.EconomicCalendarHdr(a.pkId, a.organizationPkId, a.type, a.typePkId, a.name, a.rateType, a.beginTimeHour, a.beginTimeMinute, a.endTimeHour, a.endTimeMinute, COUNT(b.pkId)) FROM EconomicCalendarHdr a ");
		jpql.append("LEFT JOIN EconomicCalendarDtl b ON a.pkId = b.hdrPkId ");
		jpql.append("LEFT JOIN EconomicCalendarDate c ON a.pkId = c.hdrPkId ");
		jpql.append("WHERE (( a.beginDate BETWEEN :beginDate AND :endDate ");
		jpql.append("OR a.endDate BETWEEN :beginDate AND :endDate) OR (a.beginDate < :beginDate AND a.endDate > :endDate)) ");
		jpql.append("AND a.organizationPkId = :organizationPkId ");
		if(pkIds.size() > 0) {
			jpql.append("AND (a.typePkId = :typePkId OR a.typePkId IN :pkIds) ");
		}else {
			jpql.append("AND a.typePkId = :typePkId ");
		}
		jpql.append("AND (a.configurationStr IS NULL OR a.configurationStr = '' OR (c.year = :year AND c.month = :month AND c.day = :day )) ");
		jpql.append("GROUP BY a.pkId, a.organizationPkId, a.type, a.typePkId, a.name, a.rateType, a.beginTimeHour, a.beginTimeMinute, a.endTimeHour, a.endTimeMinute ");
		
		List<EconomicCalendarHdr> calendarHdrs = getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
		List<EconomicCalendarDtl> calendarDtls = new ArrayList<EconomicCalendarDtl>();
		
		for (EconomicCalendarHdr economicCalendarHdr : calendarHdrs) {
			jpql = new StringBuilder();
			if(economicCalendarHdr.getDtlCount() < 1){
				parameters.put("subOrganizationPkId", economicCalendarHdr.getTypePkId());
				jpql.append("SELECT a FROM Employee a ");
				jpql.append("WHERE a.subOrganizationPkId = :subOrganizationPkId ");
			}else {
				parameters.put("hdrPkId", economicCalendarHdr.getPkId());
				jpql.append("SELECT a FROM Employee a ");
				jpql.append("INNER JOIN EconomicCalendarDtl b ON b.employeePkId = a.pkId ");
				jpql.append("WHERE b.hdrPkId = :hdrPkId ");
			}
			
			List<Employee> employees = getByQuery(Employee.class, jpql.toString(), parameters);
			for (Employee employee : employees) {
				EconomicCalendarDtl calendarDtl = new EconomicCalendarDtl();
				calendarDtl.setEmployee(employee);
				calendarDtl.setCalendarHdr(economicCalendarHdr);
				calendarDtls.add(calendarDtl);
			}
		}
		return calendarDtls;
	}
	
	public Payment getPaymentByRequestPkId(BigDecimal employeeRequestPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("employeeRequestPkId", employeeRequestPkId);
		
		jpql.append("SELECT DISTINCT a FROM Payment a ");
		jpql.append("INNER JOIN EmployeeRequestPaymentMap b ON a.pkId = b.paymentPkId ");
		jpql.append("WHERE b.employeeRequestPkId = :employeeRequestPkId ");
		
		List<Payment> list = getByQuery(Payment.class, jpql.toString(), parameters);
		if(list.size() > 0) return list.get(0);
		return null;
	}
	
	public List<InspectionHistory> getInspectionHistories(BigDecimal pkId) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerPkId", pkId);
		
		jpql.append("SELECT NEW hospital.businessentity.InspectionHistory(a, d, b, c) ");
		jpql.append("FROM Inspection a ");
		jpql.append("INNER JOIN Employee b ON a.employeePkId = b.pkId ");
		jpql.append("INNER JOIN EmployeeRequest c ON a.requestPkId = c.pkId ");
		jpql.append("INNER JOIN Customer d ON a.customerPkId = d.pkId ");
		jpql.append("WHERE d.pkId = :customerPkId ");
		
		return getByQuery(InspectionHistory.class, jpql.toString(), parameters);
	}
	
	public void getInpectionHistoryByCustomerPkId(BigDecimal customerPkId, LoggedUser loggedUser) throws Exception {
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("customerPkId", customerPkId);
		
		jpql.append("SELECT a FROM Customer a  ");
		jpql.append("INNER JOIN EmployeeRequest b ON a.pkId = b.customerPkId ");
		jpql.append("INNER JOIN Inspection c ON b.pkId = c.requestPkId ");
		jpql.append("WHERE b.customerPkId = :customerPkId AND (b.mood == 2 OR b.mood = 4) ");
		
	}
	
	public List<CashierReportMonth> getCashierReportMonth(LoggedUser loggedUser, Date beginDate, Date endDate) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", loggedUser.getOrganization());
		
		List<CashierReportMonth> cashierReportMonths = new ArrayList<CashierReportMonth>();
		for(Date dte = beginDate; dte.before(endDate); dte = addDate(dte)){
			CashierReportMonth cashierReportMonth = new CashierReportMonth();
			cashierReportMonth.setDate(dte);
			
			Date bDate = new Date();
			Date eDate = new Date();
			bDate.setYear(dte.getYear());
			bDate.setMonth(dte.getMonth());
			bDate.setDate(dte.getDate());
			bDate.setHours(1);
			bDate.setMinutes(0);
			bDate.setSeconds(0);
			eDate.setYear(dte.getYear());
			eDate.setMonth(dte.getMonth());
			eDate.setDate(dte.getDate());
			eDate.setHours(23);
			eDate.setMinutes(59);
			eDate.setSeconds(59);
			parameters.put("beginDate", bDate);
			parameters.put("endDate", eDate);
			
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("LEFT JOIN Inspection b ON a.pkId = b.requestPkId ");
			jpql.append("WHERE a.mood = 4 AND a.organizationPkId = :organizationPkId AND b.inspectionDate BETWEEN :beginDate AND :endDate ");
			cashierReportMonth.setValue1(getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM EmployeeRequest a ");
			jpql.append("LEFT JOIN Inspection b ON a.pkId = b.requestPkId ");
			jpql.append("WHERE a.mood = 2 AND a.organizationPkId = :organizationPkId AND b.inspectionDate BETWEEN :beginDate AND :endDate ");
			cashierReportMonth.setValue2(getByQuery(EmployeeRequest.class, jpql.toString(), parameters).size());
			
			//
			
			cashierReportMonths.add(cashierReportMonth);
		}
		
		return cashierReportMonths;
	}
	
	private Date addDate(Date dt){
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		return dt;
	}
	
	public List<Employee> getEmployeeReport(LoggedUser loggedUser) throws Exception{
		
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM Employee a ");
		jpql.append("INNER JOIN SubOrganization b ON a.subOrganizationPkId = b.pkId ");
		jpql.append("WHERE b.organizationPkId = :organizationPkId ");
		
		return getByQuery(Employee.class, jpql.toString(), parameters);
	}
	
	public void changePassword(LoggedUser loggedUser, String newPassword) throws Exception{
		
		
		Users users = loggedUser.getUser();
		users.setPassword(Tool.MD5(newPassword));
		update(users);
	}
	
	public List<Item> getItems(LoggedUser loggedUser) throws Exception{
		
		
		List<Item> items = new ArrayList<Item>();
		
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("organizationPkId", loggedUser.getOrganization().getPkId());
		jpql.append("SELECT a FROM Item a ");
		jpql.append("WHERE a.organizationPkId = :organizationPkId ");
		
		items = getByQuery(Item.class, jpql.toString(), parameters);
		
		return items;
	}
	
	public List<Employee> getEmployeesBySubOrganizationPkId(LoggedUser loggedUser, BigDecimal organizationPkId) throws Exception{
		
		
		List<Employee> employees = getByAnyField(Employee.class, "subOrganizationPkId", organizationPkId);
		
		return employees;
	}
	
	public String getSystemConfigsByCustomerNo() throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("customerNo", "CustomerNo");
		jpql.append("SELECT a FROM SystemConfig a WHERE a.name = :customerNo ");
		List<SystemConfig> configs = getByQuery(SystemConfig.class, jpql.toString(), parameters);
		if(configs.size() > 0) return configs.get(0).getValue();
		return "NONE";
	}
	
	public List<EconomicCalendarHdr> getEconomicCalendarHdrs(BigDecimal customerPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		parameters.put("customerPkId", customerPkId);
		jpql.append("SELECT a FROM EconomicCalendarHdr a ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append("WHERE a.type = 1 ");
		jpql.append("AND a.typePkId = :customerPkId ");
		jpql.append(" ");
		
		List<EconomicCalendarHdr> calendarHdrs = getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters);
		
		return calendarHdrs;
	}
	
	public List<ExaminationType> getExaminationTypes() throws Exception{
		return getAll(ExaminationType.class);
	}
	
	public List<SurgeryType> getSurgeryType() throws Exception{
		return getAll(SurgeryType.class);
	}
	
	public List<Surgery> getListSurgery(BigDecimal surgeryTypePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("surgeryTypePkId", surgeryTypePkId);
		parameters.put("dte", new Date());
		jpql.append("SELECT a FROM Surgery a ");
		jpql.append("WHERE a.surgeryTypePkId = :surgeryTypePkId ");
		
		List<Surgery> list = getByQuery(Surgery.class, jpql.toString(), parameters);
		for (Surgery surgery : list) {
			parameters.put("surgeryPkId", surgery.getPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM SurgeryPrice a ");
			jpql.append("WHERE a.surgeryPkId = :surgeryPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<SurgeryPrice> prices = getByQuery(SurgeryPrice.class, jpql.toString(), parameters);
			if(prices.size() > 0) {
				surgery.setPrice(prices.get(0).getPrice());
			}
		}
		return list;
	}
	
	public List<Examination> getExamination(BigDecimal examinationTypePkId) throws Exception{
		StringBuilder jpql = new StringBuilder();		
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("examinationTypePkId", examinationTypePkId);
		parameters.put("dte", new Date());
		jpql.append("SELECT NEW hospital.entity.Examination(a.pkId, a.name, a.examinationTypePkId, a.roomNumber, a.isActive, a.id, a.createdBy, a.createdDate, a.updatedBy, a.updatedDate, a.examinationTemplatePkId) ");
		jpql.append("FROM Examination a ");
		jpql.append("WHERE a.examinationTypePkId = :examinationTypePkId ");
		List<Examination> examinations = getByQuery(Examination.class, jpql.toString(), parameters);
		for (Examination examination : examinations) {
			examination.setExaminationDtls(getExaminationDtlsByExaminationPkId(examination.getPkId()));
			if(examination.getExaminationDtls().size() > 0){
				examination.calculatePrice();
			}else {
				parameters.put("examinationPkId", examination.getPkId());
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM ExaminationPrice a ");
				jpql.append("WHERE a.beginDate < :dte ");
				jpql.append("AND a.examinationPkId = :examinationPkId ");
				jpql.append("ORDER BY a.beginDate DESC ");
				List<ExaminationPrice> list = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
				if(list.size() > 0) {
					examination.setPrice(list.get(0).getPrice());
				}else {
					jpql = new StringBuilder();
					jpql.append("SELECT a FROM ExaminationPrice a ");
					jpql.append("AND a.examinationPkId = :examinationPkId ");
					jpql.append("ORDER BY a.beginDate DESC ");
					list = getByQuery(ExaminationPrice.class, jpql.toString(), parameters);
					if(list.size() > 0) {
						examination.setPrice(list.get(0).getPrice());
					}
				}
			}
		}
		return examinations;
	}
	
	public List<ExaminationDtl> getExaminationDtlsByExaminationPkId(BigDecimal examinationPkId) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		List<ExaminationDtl> dtls = new ArrayList<ExaminationDtl>();
		
		parameters.put("dte", new Date());
		parameters.put("examinationPkId", examinationPkId);
		jpql.append("SELECT NEW hospital.entity.ExaminationDtl(a, b) FROM ExaminationDtl a ");
		jpql.append("INNER JOIN Element b ON a.elementPkId = b.pkId ");
		jpql.append("WHERE a.examinationPkId = :examinationPkId ");
		
		dtls = getByQuery(ExaminationDtl.class, jpql.toString(), parameters);
		
		for (ExaminationDtl examinationDtl : dtls) {
			parameters.put("elementPkId", examinationDtl.getElementPkId());
			jpql = new StringBuilder();
			jpql.append("SELECT a FROM ElementPrice a ");
			jpql.append("WHERE a.elementPkId = :elementPkId ");
			jpql.append("AND a.beginDate < :dte ");
			jpql.append("ORDER BY a.beginDate DESC ");
			List<ElementPrice> elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
			examinationDtl.setElementPricePkId(BigDecimal.ZERO);
			examinationDtl.setPrice(BigDecimal.ZERO);
			examinationDtl.setSelected(true);
			examinationDtl.setExaminationPkId(examinationPkId);
			if(elementPrices.size() > 0){
				examinationDtl.setElementPricePkId(elementPrices.get(0).getPkId());
				examinationDtl.setPrice(elementPrices.get(0).getPrice());
			}else {
				jpql = new StringBuilder();
				jpql.append("SELECT a FROM ElementPrice a ");
				jpql.append("WHERE a.elementPkId = :elementPkId ");
				elementPrices = getByQuery(ElementPrice.class, jpql.toString(), parameters);
				if(elementPrices.size() > 0){
					examinationDtl.setElementPricePkId(elementPrices.get(0).getPkId());
					examinationDtl.setPrice(elementPrices.get(0).getPrice());
				}
			}
		}
		
		return dtls;
	}
	
	public boolean isDuplicateEconomicCalendar(EconomicCalendarHdr economicCalendarHdr) throws Exception{
		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		
		jpql.append("SELECT a FROM EconomicCalendarHdr a ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		jpql.append(" ");
		
		if(getByQuery(EconomicCalendarHdr.class, jpql.toString(), parameters).size() > 0) return true;
		
		return false;
	}
	
	public List<XrayType> getXrayTypes() throws Exception{
		return getAll(XrayType.class);
	}
	
	public List<Xray> getXrays(BigDecimal xrayTypePkId) throws Exception{
		CustomHashMap parameters = new CustomHashMap();
		StringBuilder jpql = new StringBuilder();
		parameters.put("xrayTypePkId", xrayTypePkId);
		parameters.put("dte", new Date());
		jpql.append("SELECT NEW hospital.entity.Xray(a.pkId, a.id, a.xrayTypePkId, a.name, a.roomNumber, a.isActive, a.createdDate, a.createdBy, a.updatedDate, a.updatedBy, b.price) ");
		jpql.append("FROM Xray a ");
		jpql.append("INNER JOIN XrayPrice b ON a.pkId = b.xrayPkId ");
		jpql.append("WHERE a.xrayTypePkId = :xrayTypePkId ");
		jpql.append("AND b.beginDate < :dte ");
		return getByQuery(Xray.class, jpql.toString(), parameters);
	}
	
	public List<Treatment> getListTreatment() throws Exception{
		return getAll(Treatment.class);
	}
	
}


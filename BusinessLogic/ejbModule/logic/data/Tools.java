package logic.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Tools {
    private static String logFileName = "C:\\MEDIT\\LOG";
    public static CustomHashMap JBOSS_COMMAND_DATASOURCE_EXCEPTION = new CustomHashMap();
    public static final String JPQL_GETCODE_SEARCH_TYPE = "searchType";
    public static final String JPQL_GETCODE_SEARCH_LIKE_FORMAT = " LOWER(o.%s) LIKE :searchValue ";
    public static final String JPQL_GETCODE_SEARCH_TYPE_IdName = "IdName";
    public static final String JPQL_GETCODE_SEARCH_TYPE_Id = "Id";
    public static final String JPQL_GETCODE_SEARCH_TYPE_Name = "Name";

    static {
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("100", (Object)"\"outcome\" => \"success\"");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("101", (Object)"not found");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("103", (Object)"Duplicate resource");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("104", (Object)"Removing services has lead to unsatisfied dependencies");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("105", (Object)"Connection is not valid");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("106", (Object)"is already registered");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("107", (Object)"Unrecognized argument name");
        JBOSS_COMMAND_DATASOURCE_EXCEPTION.put("108", (Object)"Exception");
    }

    public static String encrypt(String value) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("MD5");
        byte[] tmp = value.getBytes();
        sha.update(tmp);
        return new String(sha.digest());
    }

    public static String getLicenseAccessKey() throws UnsupportedEncodingException {
        byte[] bytes = new byte[]{95, 75, 119, 52, 125, 110, 63, 110, 87, 104, 59, 33, 85, 42, 54, 68};
        return new String(bytes, "UTF-8");
    }

    public static String getClassName(Class<?> type) {
        String name = type.getName();
        if (name.lastIndexOf(".") > 0) {
            name = name.substring(name.lastIndexOf(".") + 1);
        }
        return name;
    }

    public static <T> Object invokeMethod(Class<T> type, String method) throws Exception {
        return Tools.invokeMethod(type, method, (Object)null, null);
    }

    public static <T> Object invokeMethod(Class<T> type, String method, Object object) throws Exception {
        return Tools.invokeMethod(type, method, object, null);
    }

    public static <T> Object invokeMethod(Class<T> type, String method, Object object, Object[] parameters) throws Exception {
        return Tools.invokeMethod(type, method, object, parameters, null);
    }

    public static <T> Object invokeMethod(Class<T> type, String method, Object[] parameters, Class<?>[] parameterTypes) throws Exception {
        return Tools.invokeMethod(type, method, null, parameters, parameterTypes);
    }

    public static <T> Object invokeMethod(Class<T> type, String method, Object object, Object[] parameters, Class<?>[] parameterTypes) throws Exception {
    	//Davaadorj
    	Method m = type.getMethod(method, parameterTypes);
        return m.invoke(object, parameters);
    }

    public static void writeToLog(String log, boolean throwLog) throws Exception {
        Tools.writeToLog(log);
        if (throwLog) {
            throw new Exception(log);
        }
    }

    public static void writeToLog(Exception ex) {
        SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        String logFileName = String.format("%s_%s.log", Tools.logFileName, dateformatYYYYMMDD.format(new Date()));
        String message = String.format("%s - %s", ex.getStackTrace()[0].getMethodName(), ex.getMessage());
        Tools.writeToLog(logFileName, message);
    }

    public static void writeToLog(String log) {
        SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyyMMdd");
        String logFileName = String.format("%s_%s.log", Tools.logFileName, dateformatYYYYMMDD.format(new Date()));
        Tools.writeToLog(logFileName, log);
    }

    public static void writeToLog(String fileName, String log) {
        try {
            Logger logger = Logger.getLogger("BIS");
            FileHandler fh = new FileHandler(fileName, true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.warning(log);
            fh.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setLogFileName(String logFileName) {
        Tools.logFileName = logFileName;
    }

    public static String getLogFileName() {
        return logFileName;
    }

    public static <T> void writeToFile(T object, String filename) throws Exception {
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
        encoder.writeObject(object);
        encoder.close();
    }

    @SuppressWarnings("unchecked")
	public static <T> T readFromFile(String filename) throws Exception {
        XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
        Object o = decoder.readObject();
        decoder.close();
        return (T)o;
    }

    public static BigDecimal newPkId() {
        return Tools.newPkId(NewPkIdType.longYear);
    }
    public static BigDecimal newPkId12() {
        return Tools.newPkId(NewPkIdType.shortYear);
    }
    
    public static BigDecimal newDecimal10() {    	
    	Date date = new Date();

        BigDecimal nYear = new BigDecimal((new SimpleDateFormat("yyyy")).format(date));
        BigDecimal nMonth = new BigDecimal((new SimpleDateFormat("MM")).format(date));
        BigDecimal nDay = new BigDecimal((new SimpleDateFormat("dd")).format(date));
        BigDecimal nHour = new BigDecimal((new SimpleDateFormat("hh")).format(date));
        BigDecimal nMinute = new BigDecimal((new SimpleDateFormat("mm")).format(date));
        BigDecimal nSecond = new BigDecimal((new SimpleDateFormat("ss")).format(date));
    	
        BigDecimal year = (new BigDecimal("32140800")).multiply(nYear.subtract(new BigDecimal(2016)));
        BigDecimal month = (new BigDecimal("2678400")).multiply(nMonth);
        BigDecimal day = (new BigDecimal("86400")).multiply(nDay);
    	BigDecimal hour = (new BigDecimal("3600")).multiply(nHour);
    	BigDecimal minute = (new BigDecimal("60")).multiply(nMinute);
    	BigDecimal second = nSecond;
    	
    	Random randomGenerator = new Random();
    	int randomInt = randomGenerator.nextInt(10);
    	
    	BigDecimal ret = BigDecimal.ZERO;
    	ret = ret.add(year);
    	ret = ret.add(month);
    	ret = ret.add(day);
    	ret = ret.add(hour);
    	ret = ret.add(minute);
    	ret = ret.add(second);
    	ret = ret.multiply(new BigDecimal(10));
    	ret = ret.add(new BigDecimal(randomInt));
    	return ret;
    }

    public static BigDecimal newPkId(NewPkIdType newPkIdType) {
        try {
            Thread.sleep(50);
        }
        catch (InterruptedException e) {
            Tools.writeToLog(e.getMessage());
        }
        Date dateNow = new Date();
        SimpleDateFormat dateformatYYYYMMDD = null;
        dateformatYYYYMMDD = newPkIdType.equals((Object)NewPkIdType.longYear) ? new SimpleDateFormat("yyyyMMddhhmmssSSS") : new SimpleDateFormat("yyMMddhhmmss");
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10);
        StringBuilder nowYYYYMMDD = new StringBuilder(dateformatYYYYMMDD.format(dateNow));
        String str = String.valueOf(nowYYYYMMDD.toString()) + String.valueOf(randomInt);
        return new BigDecimal(str);
    }
    
    public static Class<?> getClass(String applicationName, String name) throws Exception {
        return Tools.getClass(applicationName, name, "");
    }

    public static Class<?> getClass(String applicationName, String name, String entityType) throws Exception {
        if (entityType.isEmpty()) {
            if (name.toLowerCase().contains((CharSequence)"listinfo")) {
                return Tools.getListInfoClass(applicationName, name);
            }
            return Tools.getEntityClass(applicationName, name);
        }
        return Tools.getEntityTypeClass(applicationName, name, entityType);
    }

    public static Class<?> getEntityClass(String applicationName, String entityName) throws Exception {
        try {
            entityName = String.format("bis.%s.entity.%s", applicationName.toLowerCase(), entityName);
            return Class.forName(entityName);
        }
        catch (ClassNotFoundException ex) {
            Tools.writeToLog(ex.getMessage(), true);
            throw ex;
        }
    }

    public static Class<?> getListInfoClass(String applicationName, String entityName) throws Exception {
        try {
            entityName = String.format("bis.%s.listinfo.%s", applicationName.toLowerCase(), entityName);
            return Class.forName(entityName);
        }
        catch (ClassNotFoundException ex) {
            Tools.writeToLog(ex.getMessage(), true);
            throw ex;
        }
    }

    public static Class<?> getEntityTypeClass(String applicationName, String entityName, String entityType) throws Exception {
        try {
            entityName = String.format("bis.%s.%s.%s", applicationName.toLowerCase(), entityType, entityName);
            return Class.forName(entityName);
        }
        catch (ClassNotFoundException ex) {
            Tools.writeToLog(ex.getMessage(), true);
            throw ex;
        }
    }

    public static Context getInitialContext(String applicationIpAddress, String applicationServerType, int applicationServerPort) throws NamingException {
        Hashtable<String, String> env = new Hashtable<String, String>();
        if (applicationServerType.toLowerCase().equals("weblogic")) {
            env.put("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
            env.put("java.naming.provider.url", String.format("t3://%s:%d", applicationIpAddress, applicationServerPort));
        } else {
            env.put("java.naming.provider.url", String.format("jnp://%s:%d", applicationIpAddress, applicationServerPort));
            env.put("java.naming.factory.initial", "org.jboss.as.naming.InitialContextFactory");
            env.put("java.naming.factory.url.pkgs", "org.jboss.as.naming.interfaces.java");
        }
        env.put("java.naming.security.principal", "Admin");
        env.put("java.naming.security.credentials", "Nz$_jyhY03ASrNa5dSIA4#9eo$JuOk");
        Tools.writeToLog("before new InitialContext");
        return new InitialContext(env);
    }

    private static String getApplicationServerType(int applicationServerPort) throws NamingException {
        String applicationServerType = "jboss";
        if (String.valueOf(applicationServerPort).startsWith("7")) {
            applicationServerType = "weblogic";
        }
        return applicationServerType;
    }

    public static Object lookupResourceByJndi(String jndi, String applicationIpAddress, String applicationServerType, int applicationServerPort) throws NamingException {
        return Tools.getInitialContext(applicationIpAddress, applicationServerType, applicationServerPort).lookup(jndi);
    }

    public static Object lookupResourceByJndi(String jndi, String applicationIpAddress, int applicationServerPort) throws NamingException {
        return Tools.getInitialContext(applicationIpAddress, Tools.getApplicationServerType(applicationServerPort), applicationServerPort).lookup(jndi);
    }

    public static Object lookupResource(String applicationName, String className, String applicationIpAddress, int applicationServerPort) throws NamingException {
        String applicationServerType = Tools.getApplicationServerType(applicationServerPort);
        return Tools.lookupResource(applicationName, className, applicationIpAddress, applicationServerType, applicationServerPort);
    }

    public static Object lookupResource(String applicationName, String className, String applicationIpAddress, String applicationServerType, int applicationServerPort) throws NamingException {
        String jndi = applicationServerType.equalsIgnoreCase("weblogic") ? String.format("BIS.%1$s.BusinessLogic.%2$s#bis.%3$s.businesslogic.interfaces.I%2$s", applicationName, className, applicationName.toLowerCase()) : String.format("ejb:%1$s/BIS_%1$s_BusinessLogic//%2$s!bis.%3$s.businesslogic.interfaces.I%2$s", applicationName, className, applicationName.toLowerCase());
        Tools.writeToLog(String.format("lookup Resource server, port : %s %s %d", applicationServerType, applicationIpAddress, applicationServerPort));
        Tools.writeToLog("lookup Resource jndi :" + jndi);
        return Tools.getInitialContext(applicationIpAddress, applicationServerType, applicationServerPort).lookup(jndi);
    }

    public static Connection getConnectionByJndi(String jndiName) {
        String DATASOURCE_CONTEXT = jndiName;
        Connection result = null;
        try {
            InitialContext initialContext = new InitialContext();
            DataSource datasource = (DataSource)initialContext.lookup(DATASOURCE_CONTEXT);
            if (datasource != null) {
                result = datasource.getConnection();
                Tools.writeToLog("SUCCEED");
            } else {
                Tools.writeToLog("Failed to lookup datasource.");
            }
        }
        catch (NamingException ex) {
            Tools.writeToLog("Cannot get connection: " + ex);
        }
        catch (SQLException ex) {
            Tools.writeToLog("Cannot get connection: " + ex);
        }
        return result;
    }

    public static Connection getConnection(String db_conn_string, String user_name, String password) {
        String DRIVER_CLASS_NAME = "weblogic.jdbc.sqlserver.SQLServerDriver";
        Connection result = null;
        try {
            Class.forName(DRIVER_CLASS_NAME).newInstance();
        }
        catch (Exception ex) {
            Tools.writeToLog("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
        }
        try {
            result = DriverManager.getConnection(db_conn_string, user_name, password);
            Tools.writeToLog("SUCCEEDED");
        }
        catch (SQLException e) {
            Tools.writeToLog("Driver loaded, but cannot connect to db: " + db_conn_string);
        }
        return result;
    }

    public static String getApplicationServerName() {
        String factory = "";
        try {
            InitialContext ic = new InitialContext();
            factory = ic.getEnvironment().get("java.naming.factory.url.pkgs").toString();
            factory = factory.contains((CharSequence)"weblogic") ? "weblogic" : (factory.contains((CharSequence)"jboss") ? "jboss" : "unkhown");
        }
        catch (Exception ex) {
            Tools.writeToLog("Application server name not defined" + ex.getMessage());
            return "unkhown";
        }
        return factory;
    }

    public static String convertPassword(String loginPassword) {
        StringBuilder convertedPassword = new StringBuilder();
        for (int i = 0; i < loginPassword.length(); ++i) {
            convertedPassword.append((char)(loginPassword.charAt(i) ^ 27));
        }
        return convertedPassword.toString();
    }

    public static String getJpqlOrder(String jpql, CustomHashMap sortOrders) {
        if (sortOrders != null && sortOrders.size() > 0) {
            jpql = String.valueOf(jpql) + " ORDER BY ";
            int index = 0;
            for (CustomHashMapEntry entry : sortOrders.getHashMapEntries()) {
                if (index > 0) {
                    jpql = String.valueOf(jpql) + ", ";
                }
                jpql = String.valueOf(jpql) + String.format("o.%s %s", entry.getKey(), entry.getEditValue());
                ++index;
            }
        }
        return jpql;
    }

    public static String getJpqlCount(String jpql) throws Exception {
        String[] queries = jpql.split(" FROM ");
        if (queries.length != 2) {
            throw new Exception("Not defined FROM clause");
        }
        StringBuilder query = new StringBuilder();
        query.append("SELECT COUNT(1) FROM ").append(queries[1]);
        return query.toString();
    }

    private static int runProcessObject(Object command) {
        int processComplete;
        Process runtimeProcess = null;
        processComplete = -1;
        try {
            try {
                if (command instanceof String) {
                    runtimeProcess = Runtime.getRuntime().exec((String)command);
                } else if (command instanceof String[]) {
                    runtimeProcess = Runtime.getRuntime().exec((String[])command);
                }
                processComplete = runtimeProcess.waitFor();
            }
            catch (Exception ex) {
                Tools.writeToLog("runProcess: " + ex.getMessage());
                if (runtimeProcess != null) {
                    runtimeProcess.destroy();
                }
            }
        }
        finally {
            if (runtimeProcess != null) {
                runtimeProcess.destroy();
            }
        }
        return processComplete;
    }

    public static int runProcessJboss(Object command) {
        int processComplete;
        Process runtimeProcess = null;
        processComplete = -1;
        try {
            try {
                if (command instanceof String) {
                    runtimeProcess = Runtime.getRuntime().exec((String)command);
                } else if (command instanceof String[]) {
                    runtimeProcess = Runtime.getRuntime().exec((String[])command);
                }
                try {
                    InputStream ips = runtimeProcess.getInputStream();
                    String output = "";
                    int c = 0;
                    while (!((c = ips.read()) == -1 || output.contains((CharSequence)"Press any key to continue"))) {
                        output = String.valueOf(output) + (char)c;
                    }
                    for (CustomHashMapEntry entry : JBOSS_COMMAND_DATASOURCE_EXCEPTION.getHashMapEntries()) {
                        if (!output.contains((CharSequence)entry.getEditValue().toString())) continue;
                        processComplete = Integer.parseInt(entry.getKey());
                    }
                }
                catch (Exception e) {
                    Tools.writeToLog("runProcess: " + e.getMessage());
                }
            }
            catch (Exception ex) {
                Tools.writeToLog("runProcess: " + ex.getMessage());
                if (runtimeProcess != null) {
                    runtimeProcess.destroy();
                }
            }
        }
        finally {
            if (runtimeProcess != null) {
                runtimeProcess.destroy();
            }
        }
        return processComplete;
    }

    public static int runProcess(String command) {
        return Tools.runProcessObject(command);
    }

    public static int runProcess(String[] commands) {
        return Tools.runProcessObject(commands);
    }

    public static String getJpqlSearchValue(String searchValue) {
        return searchValue.toLowerCase().contains((CharSequence)"%") ? searchValue.toLowerCase() : "%" + searchValue.toLowerCase() + "%";
    }

    public static String getJpqlQueryIdName(CustomHashMap parameters) {
        String searchType = "IdName";
        if (parameters != null && parameters.get("searchType") != null) {
            searchType = parameters.get("searchType").toString();
            parameters.remove("searchType");
        }
        StringBuilder stringBuilder = new StringBuilder();
        if ("Name".equals(searchType)) {
            stringBuilder.append(String.format(" LOWER(o.%s) LIKE :searchValue ", "name"));
        } else if ("Id".equals(searchType)) {
            stringBuilder.append(String.format(" LOWER(o.%s) LIKE :searchValue ", "id"));
        } else {
            stringBuilder.append(String.format(" LOWER(o.%s) LIKE :searchValue ", "id")).append(" OR ").append(String.format(" LOWER(o.%s) LIKE :searchValue ", "name"));
        }
        return stringBuilder.toString();
    }
}

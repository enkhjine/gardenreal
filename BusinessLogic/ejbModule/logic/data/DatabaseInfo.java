package logic.data;

import java.io.PrintStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

import javax.xml.bind.annotation.XmlTransient;

import logic.ApplicationServerInfo;
import logic.DatabaseType;
import logic.Info;
import logic.JpaProviderType;

public class DatabaseInfo
implements Serializable,
Cloneable {
    private static final long serialVersionUID = 1;
    private String systemName;
    private String server;
    private String database;
    private String jndi;
    private String user;
    private String password;
    private HashMap<String, Object> properties;
    private String key;
    private String applicationName;
    private String applicationIpAddress;
    private String systemInfoKey;
    private String defaultSchema;
    private String organization;
    private DatabaseType databaseType;
    private String applicationServerType;
    private String databasePort;
    private JpaProviderType jpaProvider;

    public DatabaseInfo() {
    }

    public DatabaseInfo(String applicationName) throws Exception {
        this.jndi = applicationName;
        this.defaultSchema = applicationName;
        this.databaseType = DatabaseType.UNKNOWN;
        this.applicationName = applicationName;
        this.prepareProperties();
    }

    private void prepareProperties() throws Exception {
        boolean isEclipseLink = this.jpaProvider == null || this.jpaProvider.equals("") || this.jpaProvider == JpaProviderType.EclipseLink;
        this.properties = new HashMap();
        if (this.password == null || this.password.isEmpty()) {
            this.password = Info.getDbDrowssap();
        }
        if (isEclipseLink) {
            this.properties.put("eclipselink.session-name", "");
            this.properties.put("eclipselink.jdbc.exclusive-connection.mode", "Transactional");
            this.properties.put("eclipselink.cache.shared.default", "false");
            this.properties.put("eclipselink.jdbc.uppercase-columns", "true");
            this.properties.put("provider", "org.eclipse.persistence.jpa.PersistenceProvider");
            this.properties.put("eclipselink.logging.level.sql", "FINE");
            this.properties.put("eclipselink.logging.parameters", "true");
            this.properties.put("eclipselink.weaving", "static");
        } else {
            this.properties.put("provider", "org.hibernate.ejb.HibernatePersistence");
            this.properties.put("org.hibernate.SQL.level", "FINEST");
            this.properties.put("org.hibernate.type.level", "FINEST");
            this.properties.put("hibernate.show_sql", "true");
            if (this.databaseType == DatabaseType.ORACLE) {
                this.properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
            } else if (this.databaseType == DatabaseType.SQLSERVER) {
                this.properties.put("hibernate.dialect", "org.hibernate.dialect.SQLServer2008Dialect");
            } else if (this.databaseType == DatabaseType.MYSQL) {
                this.properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
            }
        }
        if (!(this.jndi == null || this.jndi.isEmpty())) {
            if ((this.applicationServerType == null || this.applicationServerType.isEmpty()) && !ApplicationServerInfo.isUnknownApplicationServer()) {
                this.applicationServerType = ApplicationServerInfo.getApplicationServerName();
                System.out.println("ApplicationServerName: " + this.applicationServerType);
                if (this.applicationServerType.equals("unkhown")) {
                    this.applicationServerType = "glassfish";
                }
                System.out.println("ApplicationServerName: " + this.applicationServerType);
                if (this.applicationServerType.equalsIgnoreCase("jboss") && !this.jndi.startsWith("java")) {
                    this.jndi = String.format("java:/%s", this.jndi);
                }
            }
            this.properties.put("javax.persistence.transactionType", "JTA");
            if (this.jpaProvider == JpaProviderType.NativeHibernate) {
                this.properties.put("hibernate.connection.datasource", this.jndi);
            } else {
                this.properties.put("javax.persistence.jtaDataSource", this.jndi);
            }
            if (this.applicationServerType == null || this.applicationServerType.isEmpty() || this.applicationServerType.toLowerCase().equals("jboss")) {
                if (isEclipseLink) {
                    this.properties.put("eclipselink.target-server", "JBoss");
                } else {
                    this.properties.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.JBossTransactionManagerLookup");
                }
            } else if (this.applicationServerType.equals("glassfish")) {
                if (isEclipseLink) {
                    this.properties.put("eclipselink.target-server", "SunAS9");
                } else {
                    this.properties.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.SunONETransactionManagerLookup");
                }
            } else if (isEclipseLink) {
                this.properties.put("eclipselink.target-server", "WebLogic_10");
            } else {
                this.properties.put("hibernate.transaction.manager_lookup_class", "org.hibernate.transaction.WeblogicTransactionManagerLookup");
            }
        } else {
            this.properties.put("javax.persistence.transactionType", "RESOURCE_LOCAL");
            this.properties.put("javax.persistence.jtaDataSource", "");
            String port = this.databasePort;
            if (this.databaseType == DatabaseType.ORACLE) {
                if (port == null || port.isEmpty()) {
                    port = "1521";
                }
                this.properties.put("javax.persistence.jdbc.url", String.format("jdbc:oracle:thin:@%s:%s:%s", this.server, port, this.database));
                this.properties.put("javax.persistence.jdbc.user", this.user);
                this.properties.put("javax.persistence.jdbc.driver", "oracle.jdbc.OracleDriver");
                this.properties.put("javax.persistence.jdbc.password", this.password);
            } else if (this.databaseType == DatabaseType.MYSQL) {
                if (port == null || port.isEmpty()) {
                    port = "3306";
                }
                this.properties.put("javax.persistence.jdbc.url", String.format("jdbc:mysql://%s:%s/%s", this.server, port, this.database));
                this.properties.put("javax.persistence.jdbc.user", this.user);
                this.properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
                this.properties.put("javax.persistence.jdbc.password", this.password);
            } else if (this.databaseType == DatabaseType.SQLSERVER) {
                if (port == null || port.isEmpty()) {
                    port = "1433";
                }
                this.properties.put("javax.persistence.jdbc.url", String.format("jdbc:sqlserver://%s:%s;databaseName=%s;", this.server, port, this.database));
                this.properties.put("javax.persistence.jdbc.user", this.user);
                this.properties.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
                this.properties.put("javax.persistence.jdbc.password", this.password);
            } else {
                throw new Exception("DatabaseType is unknown");
            }
            if (isEclipseLink) {
                this.properties.put("eclipselink.ddl-generation", "create-tables");
                this.properties.put("eclipselink.ddl-generation.output-mode", "database");
            } else {
                this.properties.put("hibernate.hbm2ddl.auto", "update");
            }
        }
        this.properties.put("javax.persistence.cache.storeMode", "REFRESH");
    }
    
    @XmlTransient
    public HashMap<String, Object> getProperties() throws Exception {
        if (this.properties == null) {
            this.prepareProperties();
        }
        return this.properties;
    }
}
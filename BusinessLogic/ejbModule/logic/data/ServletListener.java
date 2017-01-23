package logic.data;

import logic.data.ApplicationServerInfo;
import logic.data.Tools;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent arg0) {
    	String applicationServerName = Tools.getApplicationServerName();
        ApplicationServerInfo.setApplicationServerName((String)applicationServerName);
        Tools.writeToLog((String)String.format("contextInitialized applicationServerName = %s", applicationServerName));
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        Tools.writeToLog((String)"contextDestroyed");
    }
}
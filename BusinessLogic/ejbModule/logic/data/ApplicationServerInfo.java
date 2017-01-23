package logic.data;

public class ApplicationServerInfo {
    private static String applicationServerName = "unknown";

    public static void setApplicationServerName(String applicationServerName) {
        ApplicationServerInfo.applicationServerName = applicationServerName;
    }

    public static String getApplicationServerName() {
        return applicationServerName;
    }

    public static boolean isUnknownApplicationServer() {
        return applicationServerName.equalsIgnoreCase("unknown");
    }
}
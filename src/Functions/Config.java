
package Functions;

public class Config {
    private static String portNumber = "1527";
    private static String dbName = "myLeauge";

    public static String hostName = "jdbc:derby://localhost:" +portNumber+"/" + dbName;
    public static String username = "root" ;
    public static String password = "password" ;
}

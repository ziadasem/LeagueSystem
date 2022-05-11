
package Functions;

public class Config {
    private static String portNumber = "1527";
    private static String dbName = "myleague";
    public static String hostName = "jdbc:derby://localhost:" +portNumber+"/" + dbName;
    public static String username = "root" ;
    public static String password = "password" ;

    private static final String Admin_ID = "0000";
    public static String get_AdminID(){
        return Admin_ID;
    }
}
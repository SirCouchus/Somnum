package systemproperties;

/**
 * This class gets the operating system
 * of the machine running the JVM
 */
public class OS {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public String getOS(){
        return OS;
    }
}

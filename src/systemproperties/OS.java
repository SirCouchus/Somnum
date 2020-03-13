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

    public String osSanitizer(String OS){
        String c2File = "";
        if(OS.indexOf("win")>=0){
            c2File = "c2.exe";
        } else if(OS.indexOf("mac")>=0){
            c2File = "c2_signed_mac_binary";
        } else if(OS.indexOf("nux")>=0){
            c2File = "linux_binary";
        }
        return c2File;
    }
}

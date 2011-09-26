package org.wiztools.jwget;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 *
 * @author subwiz
 */
public class JWGetMain {
    
    private static void get(String urlStr) throws IOException {
        final URL url = new URL(urlStr);
        
        // filename:
        final String fileName = FileNameGenerator.getGenerated(url);
        System.out.println("Output file name: " + fileName);
        OutputStream os = new FileOutputStream(fileName);
        InputStream is = url.openStream();
        byte[] buf = new byte[1024*64];
        int len = -1;
        while((len=is.read(buf))!=-1) {
            os.write(buf, 0, len);
        }
        is.close();
        os.close();
    }
    
    public static void main(String[] arg) {
        if(arg.length == 0) {
            System.err.println("Enter URLs as arguments.");
            System.exit(1);
        }
        
        try{
            for(int i=0; i<arg.length; i++) {
                get(arg[i]);
            }
        }
        catch(IOException ex) {
            ex.printStackTrace(System.err);
            System.exit(2);
        }
    }
}

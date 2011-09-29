package org.wiztools.jwget;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author subwiz
 */
class FileNameGenerator {
    
    private static String generateFileName(URL url) {
        StringBuilder sb = new StringBuilder();
        sb.append(url.getProtocol()).append("_");
        sb.append(url.getHost());
        if(url.getPort() != -1) {
            sb.append("_")
                    .append(url.getPort());
        }
        sb.append(processSpecialChars(url.getFile()));
        return sb.toString();
    }
    
    private static String processSpecialChars(String str) {
        str = str.replaceAll("/", "_");
        str = str.replaceAll("\\?", "_");
        str = str.replaceAll("=", "_");
        return str;
    }
    
    static String getGenerated(URL url) {
        final String urlFileName = url.getFile();
        
        Pattern p = Pattern.compile("^.*/([^/]+)$");
        Matcher m = p.matcher(urlFileName);
        if(m.matches()) {
            final String name = m.group(1);
            return processSpecialChars(name);
        }
        
        return generateFileName(url);
    }
}

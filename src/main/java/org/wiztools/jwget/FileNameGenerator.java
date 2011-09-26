package org.wiztools.jwget;

import java.net.URL;

/**
 *
 * @author subwiz
 */
class FileNameGenerator {
    static String getGenerated(URL url) {
        final String urlFileName = url.getFile();
        
        if(urlFileName.equals("") || urlFileName.equals("/")) {
            StringBuilder sb = new StringBuilder();
            sb.append(url.getProtocol()).append("_");
            sb.append(url.getHost());
            if(url.getPort() != -1) {
                sb.append("_")
                        .append(url.getPort());
            }
            return sb.toString();
        }
        String str = urlFileName.replaceAll("/", "_");
        str = str.replaceAll("\\?", "_");
        str = str.replaceAll("=", "_");
        return str;
    }
}

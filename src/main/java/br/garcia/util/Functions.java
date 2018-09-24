package br.garcia.util;

import org.springframework.http.HttpHeaders;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Functions {

    private Functions() {
        throw new IllegalStateException("Utility class");
    }

    public static String getIdFromHeaders(HttpHeaders headers){

        List<String> idOccurrences = headers.get("id");

        if(idOccurrences.size() == 1){
            return idOccurrences.get(0);
        }

        return "";
    }

    public static String getApplicationRootPath() throws URISyntaxException {
        URL u = Functions.class.getProtectionDomain().getCodeSource().getLocation();
        File f = new File(u.toURI());

        return f.getParentFile().getParent() + "/";
    }
}

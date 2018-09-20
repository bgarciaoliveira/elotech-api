package br.garcia.util;

import org.springframework.http.HttpHeaders;

import java.util.List;

public class Functions {

    public static String getIdFromHeaders(HttpHeaders headers){

        List<String> idOccurrences = headers.get("id");

        if(idOccurrences.size() == 1){
            return idOccurrences.get(0);
        }

        return "";
    }
}

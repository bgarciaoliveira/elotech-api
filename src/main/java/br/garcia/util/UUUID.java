package br.garcia.util;

import java.util.UUID;

public class UUUID {

    public static String generate(){
        return UUID
            .randomUUID()
            .toString()
            .toUpperCase()
            .replace("-", "");
    }
}

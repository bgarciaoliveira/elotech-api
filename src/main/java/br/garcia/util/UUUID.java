package br.garcia.util;

import java.util.UUID;

public class UUUID {

    private UUUID() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate(){
        return UUID
            .randomUUID()
            .toString()
            .toUpperCase()
            .replace("-", "");
    }
}

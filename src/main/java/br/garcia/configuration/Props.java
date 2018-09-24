package br.garcia.configuration;

import br.garcia.util.Functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

public class Props {

    public static Properties applicationProperties = new Properties();

    public static void init() throws IOException, URISyntaxException {

        String appConfigPath = Functions.getApplicationRootPath() + "application.properties";

        applicationProperties.load(new FileInputStream(appConfigPath));
    }
}

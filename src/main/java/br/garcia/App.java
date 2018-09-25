package br.garcia;

import br.garcia.configuration.Props;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws IOException, URISyntaxException {

        Props.init();

        SpringApplication.run(App.class, args);
    }
}

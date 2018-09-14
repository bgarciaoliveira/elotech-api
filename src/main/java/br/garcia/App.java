package br.garcia;

import br.garcia.util.HashLibrary;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        SpringApplication.run(App.class, args);
    }
}

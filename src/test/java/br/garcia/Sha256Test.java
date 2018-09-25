package br.garcia;

import br.garcia.util.HashLibrary;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Sha256Test {

    @Test
    public void generate() throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String result = HashLibrary.sha256("bruno");

        Assert.assertEquals("CCC68482D9E0EEE0789E64C7674421076738F8836857EA89BCD0AFB832BF3FC3", result);
    }
}

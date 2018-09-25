package br.garcia;

import br.garcia.annotation.validator.EmailValidator;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class EmailValidationTest {

    @Test
    public void testaValidacaoRegex(){

        EmailValidator validator = new EmailValidator();
        boolean result = validator.isEmail("bruno@gmail.com");

        Assert.assertEquals(true, result);
    }
}

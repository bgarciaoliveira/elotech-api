package br.garcia;

import br.garcia.annotation.validator.CpfValidator;
import org.junit.Assert;
import org.junit.Test;

public class CpfValidationTest {

    @Test
    public void testaValidacaoRegex(){

        CpfValidator validator = new CpfValidator();
        boolean result = validator.isCpf("81596422025");

        Assert.assertEquals(true, result);
    }
}

package br.garcia.annotation.validator;

import br.garcia.annotation.Cpf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static java.util.Objects.nonNull;

public class CpfValidator implements ConstraintValidator<Cpf, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return nonNull(s) && !s.isEmpty() && isCpf(s);

    }

    public boolean isCpf(String cpf) {
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try{
            Long.parseLong(cpf);
        } catch(NumberFormatException e){
            return false;
        }

        int d1;
        int d2;
        int digito1;
        int digito2;
        int resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount));
            d1 = d1 + (11 - nCount) * digitoCPF;
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        resto = (d1 % 11);
        if (resto < 2)
            digito1 = 0;
        else
            digito1 = 11 - resto;

        d2 += 2 * digito1;

        resto = (d2 % 11);

        if (resto < 2)
            digito2 = 0;
        else
            digito2 = 11 - resto;

        String nDigVerific = cpf.substring(cpf.length() - 2);

        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        return nDigVerific.equals(nDigResult);
    }
}

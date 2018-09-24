package br.garcia.annotation.validator;

import br.garcia.annotation.Email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import static java.util.Objects.nonNull;

public class EmailValidator implements ConstraintValidator<Email, String>{

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return nonNull(s) && !s.isEmpty() && isEmail(s);
    }

    public boolean isEmail(String email){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}

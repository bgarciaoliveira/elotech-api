package br.garcia.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    public static Boolean checkCpf(String cpf){

        final Pattern patternGeneric = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}");
        final Pattern patternNumbers = Pattern.compile("(?=^((?!((([0]{11})|([1]{11})|([2]{11})|([3]{11})|([4]{11})|([5]{11})|([6]{11})|([7]{11})|([8]{11})|([9]{11})))).)*$)([0-9]{11})");
        if (cpf != null && patternGeneric.matcher(cpf).matches()) {
            cpf = cpf.replaceAll("-|\\.", "");
            if (patternNumbers.matcher(cpf).matches()) {
                int[] numbers = new int[11];
                for (int i = 0; i < 11; i++) numbers[i] = Character.getNumericValue(cpf.charAt(i));
                int i;
                int sum = 0;
                int factor = 100;
                for (i = 0; i < 9; i++) {
                    sum += numbers[i] * factor;
                    factor -= 10;
                }
                int leftover = sum % 11;
                leftover = leftover == 10 ? 0 : leftover;
                if (leftover == numbers[9]) {
                    sum = 0;
                    factor = 110;
                    for (i = 0; i < 10; i++) {
                        sum += numbers[i] * factor;
                        factor -= 10;
                    }
                    leftover = sum % 11;
                    leftover = leftover == 10 ? 0 : leftover;
                    return leftover == numbers[10];
                }
            }
        }
        return false;
    }

    public static Boolean checkEmail(String email){
        boolean isEmailIdValid = false;
        if (email.length() > 0) {
            final String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }

    public static Boolean checkNome(String nome){
        return nome.length() >= 4 && nome.length() <= 20;
    }

    public static Boolean checkSenha(String senha){
        return senha.length() <= 32;
    }

    public static Boolean checkTitulo(String titulo){
        return titulo.length() <= 100;

    }

    public static Boolean checkDescricao(String descricao){
        return descricao.length() <= 500;
    }

    public static Boolean checkStatus(int status){
        return status >= 1 && status <= 5;
    }
}

package com.example.cinema.object;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelephoneValidator {

    private static final String TELEPHONE_PATTERN = "^(034|033|032|038)\\d{7}$";

    private Pattern pattern;
    private Matcher matcher;

    public TelephoneValidator() {
        pattern = Pattern.compile(TELEPHONE_PATTERN);
    }

    public boolean validate(final String telephone) {
        matcher = pattern.matcher(telephone);
        return matcher.matches();
    }

//    public static void main(String[] args) {
//        TelephoneValidator validator = new TelephoneValidator();
//        String phoneNumber = "0321234567";
//
//        if (validator.validate(phoneNumber)) {
//            System.out.println("Le numéro de téléphone est valide.");
//        } else {
//            System.out.println("Le numéro de téléphone n'est pas valide.");
//        }
//    }
}

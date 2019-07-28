package com.freenow.test.helper.pageObjects;

public class Assertions {

    public static void verifyIsValidEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        assert email.matches(regex);
        System.out.println("OK --> Email: " + email + " is valid");
    }
}

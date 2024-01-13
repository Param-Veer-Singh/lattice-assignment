package com.lattice.latticeassignment;

public class EmailValidator {

    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atSymbolIndex = email.indexOf('@');
        if (atSymbolIndex == -1) {
            return false;
        }

        if(email.contains("@.")){
            return false;
        }

        if (email.contains("..")) {
            return false;
        }

        if (email.endsWith(".")) {
            return false;
        }

        if (email.startsWith(".")) {
            return false;
        }

        String domain = email.substring(atSymbolIndex + 1);
        for (char c : domain.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '.') {
                return false;
            }
        }

        return true;
    }

}

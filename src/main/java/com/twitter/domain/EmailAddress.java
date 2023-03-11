package com.twitter.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class EmailAddress {

    private String email;

    public EmailAddress() {
    }

    public EmailAddress(String email) {
        if(isValid(email)){
            this.email = email;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(isValid(email)){
            this.email = email;
        }
    }

    /**
     * Checks if email is valid
     * Email type accepted:
     * [A-Z] characters allowed
     * [a-z] characters allowed
     * [0-9] numbers allowed
     * Email may contain only dot(.), dash(-) and underscore(_)
     * @param emailAddress The string to check
     * @return boolean
     */
    public Boolean isValid(String emailAddress){
        String regexPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAddress that = (EmailAddress) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

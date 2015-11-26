package edu.cmu.foreverhungry.model;

/**
 * Created by Sudhir Ravi on 11/14/15.
 */
public class RegistrationInfo {
    String name;
    String email;
    String password;
    String confirmPassword;

    public RegistrationInfo(String name, String email, String password, String confirmPassword) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public boolean register() {
        // TODO
        return false;
    }

    /**
     * Verify if the registrationInfo information is valid
     * @return true if the information is valid; false otherwise
     */
    public boolean validate() {
        return (isDuplicate() && isValidEmail() && validateConfirmPassword());
    }

    /**
     *
     * Helper function to check if the username is a duplicate
     * @return true if the username is a duplicate; false otherwise
     */
    private boolean isDuplicate() {
        //TODO
        return false;
    }

    /**
     *
     * Helper function to check if the email is valid
     * @return true if the email is valid; false otherwise
     */
    private boolean isValidEmail() {
        //TODO
        return false;
    }

    private boolean validateConfirmPassword() {
        if(!password.equals(confirmPassword)) {
            return false;
        }

        return true;
    }
}

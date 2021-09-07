/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anpdt.registration;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class RegistrationInsertErrors implements Serializable{
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String confirmNotMathced;
    private String lastnameLengthErr;
    private String usernameIsExisted;
    private String flagUsername;

    public RegistrationInsertErrors() {
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public String getFlagUsername() {
        return flagUsername;
    }

    public void setFlagUsername(String flagUsername) {
        this.flagUsername = flagUsername;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMathced() {
        return confirmNotMathced;
    }

    public void setConfirmNotMathced(String confirmNotMathced) {
        this.confirmNotMathced = confirmNotMathced;
    }

    public String getLastnameLengthErr() {
        return lastnameLengthErr;
    }

    public void setLastnameLengthErr(String lastnameLengthErr) {
        this.lastnameLengthErr = lastnameLengthErr;
    }

    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}

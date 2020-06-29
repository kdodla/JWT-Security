package com.infoshare.nightingale.userlogin.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDetailsDto implements Serializable {
	
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private String email;
    private String confirmEmail;
    private Boolean terms;
    private ArrayList<String> roles;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public Boolean getTerms() {
        return terms;
    }

    public void setTerms(Boolean terms) {
        this.terms = terms;
    }

	public ArrayList<String> getRoles() {
		return roles;
	}

	public void setRoles(ArrayList<String> roles) {
		this.roles = roles;
	}

	

}

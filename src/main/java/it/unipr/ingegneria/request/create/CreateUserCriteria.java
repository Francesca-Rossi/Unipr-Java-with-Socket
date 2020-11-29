package it.unipr.ingegneria.request.create;

import it.unipr.ingegneria.utils.Type;
import java.io.Serializable;

public class CreateUserCriteria implements Serializable {

    private String name;
    private String surname;
    private String email;
    private String password;
    private String userType;


    public String getName() {
        return name;
    }

    public CreateUserCriteria setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public CreateUserCriteria setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserCriteria setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CreateUserCriteria setPassword(String password) {
        this.password = password;
        return this;
    }


    public String getUserType() {
        return userType;
    }

    public CreateUserCriteria setUserType(Type userType) {
        this.userType = userType.toString();
        return this;
    }
}

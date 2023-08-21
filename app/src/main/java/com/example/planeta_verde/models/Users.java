package com.example.planeta_verde.models;

import androidx.annotation.NonNull;

public class Users {
    protected String firstname, surname, email, username, password;
    protected static String activeUser;

    public Users() {
    }

    public Users(String firstname, String surname, String email, String username, String password) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static void setActiveUser(String username){
        activeUser = username;
    }

    public static String getActiveUser(){
        return activeUser;
    }

    @NonNull
    @Override
    public String toString() {
        return "Nombre: "+firstname+"\nApellidos: "+surname;
    }
}

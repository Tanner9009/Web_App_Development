package com.Gamefinders.domain.enums;

public enum Role {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String value;

    private Role(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}

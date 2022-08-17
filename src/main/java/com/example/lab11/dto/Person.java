package com.example.lab11.dto;

public class Person {
    private long id;
    private String firstName;
    private String lastName;
    private int birthdayYear;

    public Person() {}

    public Person(long id, String lastName) {
        this.id = id;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getBirthdayYear() {
        return birthdayYear;
    }

    public void setBirthdayYear(int birthdayYear) {
        this.birthdayYear = birthdayYear;
    }

    @Override
    public String toString() {
        return this.id + " " + this.lastName;
    }
}

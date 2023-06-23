package com.example.chatappv2.listEmails;

public class User {

    public User(String firstName, String lastName, String age, String profilePic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profilePic = profilePic;
    }

    String firstName, lastName, age, profilePic;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getProfilePicUrl() {
        return profilePic;
    }
}

package com.SocialMediaApp.WebServices;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "name")
    private String username;

//    @Column(name = "date_of_birth")

    private LocalDate birthdate;

    public User(){}

    public User(Integer id, String username, LocalDate birthdate) {
        super();
        this.id = id;
        this.username = username;
        this.birthdate = birthdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }

}

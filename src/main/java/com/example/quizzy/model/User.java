package com.example.quizzy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Data
@Getter
@Setter
@ToString
@Table(name = "users")
@Entity
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;


}
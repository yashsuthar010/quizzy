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
@Table(name = "questions")
@Entity
public class Question {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @Column(name = "question")
    private String question;

    @Column(name = "option_a")
    private String optionA;

    @Column(name = "option_b")
    private String optionB;

    @Column(name = "option_c")
    private String optionC;

    @Column(name = "option_d")
    private String optionD;

    @Column(name = "correct_option")
    private String correctOption;

}
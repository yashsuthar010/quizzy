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
@Table(name = "submissions")
@Entity
public class Submission {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "quiz_session_id")
    private QuizSession quizSession;

    @Column(name = "user_answer")
    private String userAnswer;

    @Column(name = "is_correct")
    private boolean isCorrect;


}

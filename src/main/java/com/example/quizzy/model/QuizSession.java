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
@Table(name = "quiz_session")
@Entity
public class QuizSession {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_active")
    private boolean isActive;

}

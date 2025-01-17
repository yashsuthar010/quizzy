package com.example.quizzy.repository;

import com.example.quizzy.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM questions ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Question> findRandomQuestion();
}

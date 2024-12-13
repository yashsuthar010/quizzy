package com.example.quizzy.repository;

import com.example.quizzy.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    @Query(value = "select s from Submission s where s.quizSession.id =:quizSessionId")
    List<Submission> getAllSubmissionByQuizSessionId(@Param("quizSessionId") Long quizSessionId);
}

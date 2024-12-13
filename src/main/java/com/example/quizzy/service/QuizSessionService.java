package com.example.quizzy.service;

import com.example.quizzy.model.QuizSession;
import com.example.quizzy.repository.QuizSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizSessionService {

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSession save(QuizSession quizSession) {
        return quizSessionRepository.save(quizSession);
    }

    public QuizSession getQuizSessionById(Long id) {
        return quizSessionRepository.findById(id).orElse(null);
    }
}

package com.example.quizzy.service;

import com.example.quizzy.model.Question;
import com.example.quizzy.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question getRandomQuestion() {
        return questionRepository.findRandomQuestion().orElse(null);
    }

    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }
}

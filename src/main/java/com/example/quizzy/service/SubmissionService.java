package com.example.quizzy.service;

import com.example.quizzy.model.Question;
import com.example.quizzy.model.QuizSession;
import com.example.quizzy.model.Submission;
import com.example.quizzy.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository submissionRepository;

    public Submission submitUserAnswer(QuizSession quizSession, Question question, String userAnswer) {
        boolean isAnswerCorrect = question.getCorrectOption().equalsIgnoreCase(userAnswer);

        Submission submission = new Submission();
        submission.setQuestion(question);
        submission.setQuizSession(quizSession);
        submission.setUserAnswer(userAnswer);
        submission.setCorrect(isAnswerCorrect);

        return submissionRepository.save(submission);
    }

    public List<Submission> getResults(Long quizSessionId) {
        return submissionRepository.getAllSubmissionByQuizSessionId(quizSessionId);
    }

}

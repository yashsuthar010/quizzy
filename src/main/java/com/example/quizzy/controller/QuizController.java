package com.example.quizzy.controller;

import com.example.quizzy.model.Question;
import com.example.quizzy.model.QuizSession;
import com.example.quizzy.model.Submission;
import com.example.quizzy.model.User;
import com.example.quizzy.service.QuestionService;
import com.example.quizzy.service.QuizSessionService;
import com.example.quizzy.service.SubmissionService;
import com.example.quizzy.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class QuizController {

    @Autowired
    private UserService userService;
    @Autowired
    private QuizSessionService quizSessionService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/start")
    public ResponseEntity<QuizSession> startQuiz(){
        User user = userService.getUser();

        if(user == null){
            return ResponseEntity.notFound().build();
        }
        // creating new quiz session
        QuizSession quizSession = new QuizSession();
        quizSession.setUser(user);
        quizSession.setActive(true);

        try{
            QuizSession savedSession =  quizSessionService.save(quizSession);
            return ResponseEntity.ok(savedSession);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/question")
    public ResponseEntity<Question> quizQuestion(){
        try{
            Question question = questionService.getRandomQuestion();

            if(question == null){
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(question);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<Map<String, Object>> submitAnswer(HttpServletRequest request){
        String sessionId = request.getParameter("sessionId");
        String questionId = request.getParameter("questionId");
        String userAnswer = request.getParameter("userAnswer");

        log.info("calling submit ****");

        if(StringUtils.isBlank(sessionId) || StringUtils.isBlank(questionId) || StringUtils.isBlank(userAnswer)){
            return ResponseEntity.badRequest().build();
        }

        log.info("calling submit ****111");

        QuizSession quizSession = quizSessionService.getQuizSessionById(Long.valueOf(sessionId));
        if(quizSession == null) return ResponseEntity.notFound().build();

        Question question = questionService.getQuestionById(Long.valueOf(questionId));
        if(question == null) return ResponseEntity.notFound().build();

        try{
            Submission submission = submissionService.submitUserAnswer(quizSession, question, userAnswer);

            Map<String, Object> response = new HashMap<>();
            response.put("id", submission.getId());
            response.put("question", submission.getQuestion().getQuestion());
            response.put("userAnswer", submission.getUserAnswer());
            response.put("isCorrect", submission.isCorrect());


            return ResponseEntity.ok(response);
        }catch (Exception e){
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/results")
    public ResponseEntity<Map<String, Object>> getResults(HttpServletRequest request) {

        String sessionId = request.getParameter("sessionId");

        if(StringUtils.isBlank(sessionId)){
            return ResponseEntity.badRequest().build();
        }

        List<Submission> submissions = submissionService.getResults(Long.valueOf(sessionId));

        if(submissions.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        long correct = submissions.stream().filter(Submission::isCorrect).count();
        long incorrect = submissions.size() - correct;

        Map<String, Object> response = new HashMap<>();
        response.put("totalQuestions", submissions.size());
        response.put("correct", correct);
        response.put("incorrect", incorrect);
        response.put("details", submissions);

        return ResponseEntity.ok(response);
    }

}

# Quizzy

Quizzy is a simple quiz application built using Spring Boot and MySQL. This application allows users to interact with RESTful API endpoints to start a quiz, answer multiple-choice questions, and view their performance.

## Key Features

Start a Quiz Session: Begin a new quiz session.
Get Random Questions: Retrieve a random multiple-choice question from the database.

Submit Answers: Submit answers to questions and track performance.

View Results: Get a detailed summary of answered questions, including the number of correct and incorrect answers.

## Technologies Used

Spring Boot
MySQL Database
Hibernate/JPA
API Endpoints

1. Start a Quiz Session

Endpoint: /startMethod: GET

Starts a new quiz session for the user.
Returns the QuizSession object if successful.

Sample Response:

{
  "id": 1,
  "user": {
    "id": 1,
    "name": "John Doe"
  },
  "active": true
}

2. Get a Random Question

Endpoint: /questionMethod: GET

Retrieves a random question from the database.
Returns the Question object if a question is available.

Sample Response:

{
  "id": 101,
  "question": "What is the capital of France?",
  "optionA": "Paris",
  "optionB": "London",
  "optionC": "Berlin",
  "optionD": "Madrid",
  "correctOption": "A"
}

3. Submit an Answer

Endpoint: /submitMethod: POST

Request Parameters:

sessionId: ID of the current quiz session.
questionId: ID of the question being answered.
userAnswer: The option selected by the user (e.g., A, B, C, or D).

Sample Request:

{
  "sessionId": 1,
  "questionId": 101,
  "userAnswer": "A"
}

Sample Response:

{
  "id": 201,
  "question": "What is the capital of France?",
  "userAnswer": "A",
  "isCorrect": true
}

4. View Quiz Results

Endpoint: /resultsMethod: GET

Request Parameters:

sessionId: ID of the quiz session to view results.

Sample Response:

{
  "totalQuestions": 5,
  "correct": 4,
  "incorrect": 1,
  "details": [
    {
      "question": "What is the capital of France?",
      "userAnswer": "A",
      "isCorrect": true
    },
    {
      "question": "What is 2+2?",
      "userAnswer": "3",
      "isCorrect": false
    }
  ]
}

Assumptions

The application assumes a single user in the system. Questions are pre-seeded into the database. The application does not include APIs for creating users or questions.

How to Run the Application

Clone the repository and navigate to the project directory.
Configure your MySQL database and update the application.properties file:

spring.datasource.url=jdbc:mysql://localhost:3306/quiz_db
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.jpa.hibernate.ddl-auto=update

Run the application:

./mvnw spring-boot:run

Use Postman or any API testing tool to interact with the endpoints.

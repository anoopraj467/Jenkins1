package com.sapient.springboot.survey;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class SurveyService {

    private static List<Survey> surveys = new ArrayList<>();

    static {
        Question question1 = new Question("Question1",
                "Most Popular Cloud Platform Today", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
        Question question2 = new Question("Question2",
                "Fastest Growing Cloud Platform", Arrays.asList(
                "AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
        Question question3 = new Question("Question3",
                "Most Popular DevOps Tool", Arrays.asList(
                "Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retrieveAllService() {
        return surveys;
    }

    public Survey retrieveSurveyById(String surveyId) {
        Predicate<? super Survey> predicate = survey -> survey.getId().equals(surveyId);
        Optional<Survey> OptionalSurvey = surveys.stream().filter(predicate).findFirst();
        if(OptionalSurvey.isPresent())
            return OptionalSurvey.get();
        return null;
    }

    public List<Question> retrieveAllSurveyQuestion(String surveyId) {
        Survey survey = retrieveSurveyById(surveyId);

        if (survey==null) return null;
        return survey.getQuestions();
    }

    public Question retrieveSpecificSurveyQuestion(String surveyId, String questionId) {
       List<Question> surveyQuestion = retrieveAllSurveyQuestion(surveyId);
       if(surveyQuestion==null)
           return null;
       Optional<Question> optionalQuestion = surveyQuestion.stream().filter(question -> question.getId().equalsIgnoreCase(questionId)).findFirst();
       if(optionalQuestion.isEmpty())
           return null;
       return optionalQuestion.get();
    }

    public void addNewSurveyQuestion(String surveyId, Question question) {
        List<Question> surveyQuestion = retrieveAllSurveyQuestion(surveyId);
        surveyQuestion.add(question);
    }
}

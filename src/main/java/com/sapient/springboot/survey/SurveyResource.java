package com.sapient.springboot.survey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class SurveyResource {

    private SurveyService surveyService;

    Logger logger = LoggerFactory.getLogger(SurveyResource.class);

    public SurveyResource(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping("/surveys")
    public List<Survey> retrieveAllService(){
        logger.trace("FATAL ERROR");
        return surveyService.retrieveAllService();
    }
    @RequestMapping("/surveys/{surveyId}")
    public Survey retrieveSurveyById(@PathVariable String surveyId){
        Survey survey = surveyService.retrieveSurveyById(surveyId);
        if(survey == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return survey;
    }

    @RequestMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveAllSurveyQuestion(@PathVariable String surveyId){
        List<Question> questions = surveyService.retrieveAllSurveyQuestion(surveyId);
        if(questions == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }


    @RequestMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId , @PathVariable String questionId){
        Question questions = surveyService.retrieveSpecificSurveyQuestion(surveyId,questionId);
        if(questions == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return questions;
    }

    @RequestMapping(value = "/surveys/{surveyId}/questions", method = RequestMethod.POST)
    public void addNewSurveyQuestion(@PathVariable String surveyId, @RequestBody Question question){
        surveyService.addNewSurveyQuestion(surveyId,question);
    }
}

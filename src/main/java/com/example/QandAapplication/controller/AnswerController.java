package com.example.QandAapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.QandAapplication.exception.ResourceNotFound;
import com.example.QandAapplication.model.Answer;
import com.example.QandAapplication.repository.AnswerRepository;
import com.example.QandAapplication.repository.QuestionRepository;

@RestController
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@RequestMapping(path="/questions/{questionId}/answers", method= RequestMethod.GET)
	public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId){
		return answerRepo.findByQuestionId(questionId);
	}	
	
	@RequestMapping(path="/questions/{questionID}/answers", method= RequestMethod.POST)
	public Answer addAnswer(@PathVariable Long questionID,@Valid @RequestBody Answer answer) {
		return questionRepo.findById(questionID).map(question -> {
			answer.setQuestion(question);
			return answerRepo.save(answer);
		}).orElseThrow(()-> new ResourceNotFound("Unable to find question with id : " + questionID));
	}
	
	@RequestMapping(path="/questions/{questionID}/answers/{answerID}", method= RequestMethod.PUT)
	public Answer UpdateAnswer(@PathVariable Long questionID,@PathVariable Long answerID, @Valid @RequestBody Answer answer) {
		if(!questionRepo.existsById(questionID))
			throw new ResourceNotFound("Unable to find question with id : " + questionID);
		return answerRepo.findById(answerID).map(answer_->{
			answer_.setText(answer.getText());
			return answerRepo.save(answer_);
		}).orElseThrow(()-> new ResourceNotFound("Unable to find answer with id : "+ answerID));
	}
	
	@RequestMapping(path="/questions/{questionID}/answers/{answerID}", method= RequestMethod.DELETE)
	public ResponseEntity<?> deleteAnswer(@PathVariable Long questionID, @PathVariable Long answerID){
		if(!questionRepo.existsById(questionID))
			throw new ResourceNotFound("Unable to find question with id : " + questionID);
		return answerRepo.findById(answerID).map(answer->{
			answerRepo.delete(answer);
			return ResponseEntity.ok().build();
		}).orElseThrow(()->
			new ResourceNotFound("Unable to find answer with id : "+answerID)
		);
	}
}

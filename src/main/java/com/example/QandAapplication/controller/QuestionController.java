package com.example.QandAapplication.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.QandAapplication.exception.ResourceNotFound;
import com.example.QandAapplication.model.Question;
import com.example.QandAapplication.repository.QuestionRepository;

@RestController
public class QuestionController {
	@Autowired
	QuestionRepository questionRepo;
	
	@RequestMapping(path="/questions", method= RequestMethod.GET)
	public Page<Question> getQuestioPage(Pageable page){
		return questionRepo.findAll(page);	
	}

	@RequestMapping(path="/questions", method= RequestMethod.POST)
	public Question createQuestion(@Valid @RequestBody Question question) {
		return questionRepo.save(question);
	}
	
	@RequestMapping(path= "/questions/{questionID}", method= RequestMethod.PUT)
	public Question updateQuestion(@PathVariable Long questionID, @Valid @RequestBody Question RequestQuestion) {
		return questionRepo.findById(questionID).map(question -> {
			question.setTitle(RequestQuestion.getTitle());
			question.setDescription(RequestQuestion.getDescription());
			return questionRepo.save(question);
		}).orElseThrow(()-> new ResourceNotFound("Question not found with id " + questionID));
	}
	
	@RequestMapping(path= "/questions/{questionID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionID) {
		return questionRepo.findById(questionID).map(question -> {
			questionRepo.delete(question);
			return ResponseEntity.ok().build();
		}).orElseThrow(()-> new ResourceNotFound("Question not found with id : "+questionID));
	}
}

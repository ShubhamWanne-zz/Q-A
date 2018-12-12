package com.example.QandAapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.QandAapplication.model.Answer;
import com.example.QandAapplication.repository.AnswerRepository;
import com.example.QandAapplication.repository.QuestionRepository;

@RestController
public class AnswerController {
	@Autowired
	private AnswerRepository answerRepo;

	@Autowired
	private QuestionRepository questionRepo;

	@RequestMapping(path="/questions/{questionId}/answers")
	public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId){
		return answerRepo.findByQuestionId(questionId);
	}	
	
}

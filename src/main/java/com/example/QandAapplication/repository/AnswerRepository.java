package com.example.QandAapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QandAapplication.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
	List<Answer> findByQuestionId(Long id);
}

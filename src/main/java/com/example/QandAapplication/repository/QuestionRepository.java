
package com.example.QandAapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.QandAapplication.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long>{

}

package com.harsh.question_service.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.harsh.question_service.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findByCategory(String category);

    @Query(
    		  value = "SELECT * FROM questions WHERE category = :category ORDER BY RAND() LIMIT :num",
    		  nativeQuery = true
    		)
    		List<Question> findRandomQuestionsByCategory(
    		        @Param("category") String category,
    		        @Param("num") int num
    		);
}

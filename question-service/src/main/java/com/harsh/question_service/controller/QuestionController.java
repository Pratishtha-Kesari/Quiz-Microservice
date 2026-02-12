package com.harsh.question_service.controller;


import com.harsh.question_service.dto.QuestionWrapper;
import com.harsh.question_service.entity.Question;
import com.harsh.question_service.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public Question add(@RequestBody Question q){
        return questionService.addQuestion(q);
    }

    @GetMapping("/category/{cat}")
    public List<Question> byCategory(@PathVariable String cat){
        return questionService.getByCategory(cat);
    }

    @GetMapping("/quiz")
    public List<QuestionWrapper> forQuiz(
            @RequestParam String category,
            @RequestParam int num){
        return questionService.getQuestionsForQuiz(category, num);
    }

    @PostMapping("/ids")
    public List<Question> byIds(@RequestBody List<Integer> ids){
        return questionService.getQuestionsByIds(ids);
    }
}

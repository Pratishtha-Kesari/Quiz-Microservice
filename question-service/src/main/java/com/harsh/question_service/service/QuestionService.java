package com.harsh.question_service.service;


import com.harsh.question_service.dto.QuestionWrapper;
import com.harsh.question_service.entity.Question;
import com.harsh.question_service.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question addQuestion(Question q) {
        return questionRepository.save(q);
    }

    public List<Question> getByCategory(String category) {
        return questionRepository.findByCategory(category);
    }

    public List<QuestionWrapper> getQuestionsForQuiz(String category, int num) {
        List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, num);

        return questions.stream()
                .map(q -> new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                )).toList();
    }

    public List<Question> getQuestionsByIds(List<Integer> ids){
        return questionRepository.findAllById(ids);
    }
}


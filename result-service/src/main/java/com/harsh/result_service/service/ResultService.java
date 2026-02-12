package com.harsh.result_service.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.harsh.result_service.dto.ResponseDto;
import com.harsh.result_service.entity.Result;
import com.harsh.result_service.feign.QuestionFeign;
import com.harsh.result_service.repository.ResultRepository;

@Service
public class ResultService {

    private final ResultRepository resultRepository;
    private final QuestionFeign questionFeign;

    public ResultService(ResultRepository resultRepository,
                         QuestionFeign questionFeign) {
        this.resultRepository = resultRepository;
        this.questionFeign = questionFeign;
    }

    public Integer calculateResult(Integer quizId,
                                   Integer userId,
                                   List<ResponseDto> responses) {

        // Extract question IDs
        List<Integer> qIds = responses.stream()
                .map(ResponseDto::getQuestionId)
                .toList();

        // Get correct answers from Question Service
        Map<Integer, String> correctAnswers =
                questionFeign.getCorrectAnswers(qIds);

        // Map user answers
        Map<Integer, String> userAnswers = responses.stream()
                .collect(Collectors.toMap(
                        ResponseDto::getQuestionId,
                        ResponseDto::getResponse
                ));

        int score = 0;
        for (Integer qId : qIds) {
            if (correctAnswers.get(qId)
                    .equals(userAnswers.get(qId))) {
                score++;
            }
        }

        Result result = new Result();
        result.setQuizId(quizId);
        result.setUserId(userId);
        result.setScore(score);

        resultRepository.save(result);

        return score;
    }
}

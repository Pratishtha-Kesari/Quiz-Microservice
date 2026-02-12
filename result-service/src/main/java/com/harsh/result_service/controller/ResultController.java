package com.harsh.result_service.controller;

import com.harsh.result_service.dto.ResponseDto;
import com.harsh.result_service.service.ResultService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/submit/{quizId}/{userId}")
    public Integer submitQuiz(@PathVariable Integer quizId,
                              @PathVariable Integer userId,
                              @RequestBody List<ResponseDto> responses) {

        return resultService.calculateResult(quizId, userId, responses);
    }
}

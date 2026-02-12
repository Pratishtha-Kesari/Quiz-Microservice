package com.harsh.result_service.feign;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "question-service")
public interface QuestionFeign {

    @PostMapping("/questions/correct-answers")
    Map<Integer, String> getCorrectAnswers(@RequestBody List<Integer> questionIds);
}

package com.harsh.result_service.dto;


import lombok.Data;

@Data
public class ResponseDto {
    private Integer questionId;
    private String response;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

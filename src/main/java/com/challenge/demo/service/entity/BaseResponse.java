package com.challenge.demo.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    @JsonProperty("isSuccess")
    private boolean isSuccess;
    private Error error;

    @Builder(builderMethodName = "responseBuilder")
    public BaseResponse(boolean isSuccess, Error error) {
        this.isSuccess = isSuccess;
        this.error = error;
    }
}

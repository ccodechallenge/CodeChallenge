package com.challenge.demo.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

    private Error error;

    @Builder(builderMethodName = "responseBuilder")
    public BaseResponse(Error error) {
        this.error = error;
    }
}

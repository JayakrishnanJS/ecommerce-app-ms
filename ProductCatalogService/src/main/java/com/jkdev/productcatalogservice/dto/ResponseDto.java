package com.jkdev.productcatalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class ResponseDto {

    private String statusCode;

    private String statusMsg;
}

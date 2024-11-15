package com.mavene.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseHeader {
    private String refId;
    private Integer responseCode;
    private String responseMessage;
    private String customerMessage;
    private String timestamp;
}


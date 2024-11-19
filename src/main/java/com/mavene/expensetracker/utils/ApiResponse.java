package com.mavene.expensetracker.utils;

import com.mavene.expensetracker.dto.ResponseDto;
import com.mavene.expensetracker.dto.ResponseHeaderDto;

import java.time.Instant;
import java.util.UUID;

public class ApiResponse {
    public static <T> ResponseDto<T> createResponse(
            Integer responseCode,
            String responseMessage,
            String customerMessage,
            T body
    ) {
        ResponseDto<T> response = new ResponseDto<>();

        // Set header
        ResponseHeaderDto header = new ResponseHeaderDto();
        header.setRefId(UUID.randomUUID().toString());
        header.setResponseCode(responseCode);
        header.setResponseMessage(responseMessage);
        header.setCustomerMessage(customerMessage);
        header.setTimestamp(Instant.now().toString());
        response.setHeader(header);

        // Set body directly
        response.setBody(body);

        return response;
    }

    // Helper method to create error response
    public static <T> ResponseDto<T> createErrorResponse(
            int responseCode,
            String responseMessage,
            String customerMessage
    ) {
        ResponseDto<T> response = new ResponseDto<>();

        // Set header
        ResponseHeaderDto header = new ResponseHeaderDto();
        header.setRefId(UUID.randomUUID().toString());
        header.setResponseCode(responseCode);
        header.setResponseMessage(responseMessage);
        header.setCustomerMessage(customerMessage);
        header.setTimestamp(Instant.now().toString());
        response.setHeader(header);

        // Set body directly
        response.setBody(null);

        return response;
    }
}




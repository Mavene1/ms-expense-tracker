package com.mavene.expensetracker.utils;

import com.mavene.expensetracker.dto.ResponseHeader;
import com.mavene.expensetracker.dto.ResponseStructure;

import java.time.Instant;
import java.util.UUID;

public class ResponseUtil {
    public static <T> ResponseStructure<T> createResponse(
            Integer responseCode,
            String responseMessage,
            String customerMessage,
            T body
    ) {
        ResponseStructure<T> response = new ResponseStructure<>();

        // Set header
        ResponseHeader header = new ResponseHeader();
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
}




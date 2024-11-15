package com.mavene.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseStructure<T> {
    private ResponseHeader header;
    private T body;
}

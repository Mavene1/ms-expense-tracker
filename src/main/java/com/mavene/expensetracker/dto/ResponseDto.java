package com.mavene.expensetracker.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto<T> {
    private ResponseHeaderDto header;
    private T body;
}

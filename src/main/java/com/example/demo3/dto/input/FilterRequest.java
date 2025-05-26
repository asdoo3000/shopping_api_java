package com.example.demo3.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterRequest {
    private String field;
    private String op;
    private Object value;
    private Object from;
    private Object to;
}

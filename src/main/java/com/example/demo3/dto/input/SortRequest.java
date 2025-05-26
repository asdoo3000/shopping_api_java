package com.example.demo3.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SortRequest {
    private String field;
    private String direction;
}

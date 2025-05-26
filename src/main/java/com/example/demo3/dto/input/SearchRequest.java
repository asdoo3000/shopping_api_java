package com.example.demo3.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchRequest {
    public List<FilterRequest> filters;
    public List<SortRequest> sorts;

}

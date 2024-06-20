package com.java.msorder.model.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(USE_DEFAULTS)
public class PageCriteria {

    private Integer page=0;
    private Integer count=5;
}

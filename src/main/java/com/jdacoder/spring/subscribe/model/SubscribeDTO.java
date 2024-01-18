package com.jdacoder.spring.subscribe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/* All rights reserved by JdaCoder */

@Getter
@Setter
@AllArgsConstructor
public class SubscribeDTO {

    private Long userId;
    private Long categoryId;
    private String categoryName;
    private int remainingContent;
    private Double price;
    private String startDate;

    public SubscribeDTO() {
    }
}

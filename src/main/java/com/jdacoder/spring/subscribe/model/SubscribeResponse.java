package com.jdacoder.spring.subscribe.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SubscribeResponse {

    private Long userId;
    private Long categoryId;
    private String categoryName;
    private int remainingContent;
    private double price;
    private String startDate;

}

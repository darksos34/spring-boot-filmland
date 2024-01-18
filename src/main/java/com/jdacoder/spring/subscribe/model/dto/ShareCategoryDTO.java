package com.jdacoder.spring.subscribe.model.dto;

import lombok.Getter;
import lombok.Setter;

/* All rights reserved by JdaCoder */

@Getter
@Setter
public class ShareCategoryDTO {

    private Long categoryId;
    private String recipientUsername;
    private Long sharingUserId;

    public ShareCategoryDTO(Long categoryId, String recipientUsername) {
        this.categoryId = categoryId;
        this.recipientUsername = recipientUsername;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getRecipientUsername() {
        return recipientUsername;
    }

    public void setRecipientUsername(String recipientUsername) {
        this.recipientUsername = recipientUsername;
    }

    public void getSharingUserId(Long sharingUserId) {
        this.sharingUserId = sharingUserId;
    }
}

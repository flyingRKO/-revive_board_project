package com.example.communityforum.domain.constants;

import lombok.Getter;

public enum FaqType {
    PAYMENT ("결제/취소"),
    INQUIRY("이용문의"),
    ETC("기타");

    @Getter
    private final String description;

    FaqType( String description) {
        this.description = description;
    }
}

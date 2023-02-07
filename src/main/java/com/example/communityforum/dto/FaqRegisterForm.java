package com.example.communityforum.dto;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Getter
@Setter
public class FaqRegisterForm {

    private int faqId;

    @NotEmpty
    private String type;

    @NotEmpty(message = "제목이 형식과 맞지 않습니다. 2~50자 이내로 입력해주세요.")
    @Size(min = 2, max = 50, message = "{FaqRegisterForm.title.Length}")
    private String title;

    @NotEmpty( message = "제목이 형식과 맞지 않습니다. 100~500자 이내로 입력해주세요.")
    @Size(min = 10, max = 500, message = "{FaqRegisterForm.contents.Length}")
    private String content;

}

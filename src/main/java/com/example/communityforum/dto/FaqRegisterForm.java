package com.example.communityforum.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FaqRegisterForm {

    private int faqId;

    @NotEmpty
    private String type;

    @NotEmpty
    @Size(min = 2, max = 50, message = "제목이 형식과 맞지 않습니다. 2~50자 이내로 입력해주세요.")
    private String title;

    @NotEmpty
    @Size(min = 10, max = 500, message = "내용이 형식과 맞지 않습니다. 10~500자 이내로 입력해주세요.")
    private String content;

}

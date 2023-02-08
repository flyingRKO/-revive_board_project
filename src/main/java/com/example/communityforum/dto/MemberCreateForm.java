package com.example.communityforum.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class MemberCreateForm {

    @Pattern(regexp="^[a-zA-Z0-9]{6,10}$", message = "사용자 ID는 영어와 숫자로 포함해서 6~10자리 이내로 입력해주세요.")
    private String memberId;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "비밀번호는 필수항목입니다, 비밀번호는 영어와 숫자로 포함해서 8~15자리 이내로 입력해주세요.")
    private String memberPassword;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$", message = "")
    private String memberPassword2;

    @Pattern(regexp="^[가-힣]{1,10}$", message = "이름은 한글로 1~10자 사이로 입력해주세요.")
    private String name;

    @Size(min = 10, max = 11, message = "")
    @Digits(integer = 11, fraction = 0, message = "휴대폰 번호는 10-11자리 숫자만 입력해주세요.")
    private String phone;

    @AssertTrue(message = "약관에 동의해주세요.")
    private Boolean term;

}
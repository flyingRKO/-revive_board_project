package com.example.communityforum.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;

import com.example.communityforum.domain.constants.FaqType;
import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data //getter,setter,tostring,equals다 만들어준다
@GroupSequence({
        FaqRegisterForm.class,
})
public class FaqRegisterForm {

    private int faqId;

    @NotEmpty(message = "{FaqRegisterForm.FaqType.NotEmpty}")
    private String type;

    @NotEmpty(message = "{FaqRegisterForm.title.NotEmpty}")
    @Length(min = 2, max = 50, message = "{FaqRegisterForm.title.Length}")
    private String title;

    @NotEmpty( message = "{FaqRegisterForm.contents.NotEmpty}")
    @Length(min = 10, max = 500, message = "{FaqRegisterForm.contents.Length}")
    private String content;

}

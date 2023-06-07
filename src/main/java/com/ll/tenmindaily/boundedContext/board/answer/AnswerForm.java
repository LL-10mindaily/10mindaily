package com.ll.tenmindaily.boundedContext.board.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {

<<<<<<< HEAD
    @NotEmpty(message = "내용은 필수 항목 입니다.")
=======
    @NotEmpty(message ="내용은 필수 항목 입니다.")
>>>>>>> 3630690 (Nagiltae (#9))
    private String content;
}

package com.ll.tenmindaily.boundedContext.board.question;



import com.ll.tenmindaily.boundedContext.board.answer.AnswerForm;
import com.ll.tenmindaily.boundedContext.board.user.SiteUser;
import com.ll.tenmindaily.boundedContext.board.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할
@Controller
public class QuestionController {

    private final QuestionService questionService;
    private final UserService userService; //---- 유저 객체 구현후 추후 수정 --------------------

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw){

        Page<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "usr/board/question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        //변하는 id 값을 얻을 때에는 위와 같이 @PathVariable 애너테이션을 사용
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "usr/board/question_detail";
    }

    //매개변수로 바인딩한 객체는 Model 객체로 전달하지 않아도 템플릿에서 사용이 가능=QuestionForm
    @PreAuthorize("isAuthenticated()") //로그인이 필요한 메서드
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm){
        return "question_form";
    }

    //QuestionForm 이 만들어 지면서 유효성 검증을 함. subject, content 는 questionForm 에 들어있음
    // 자동으로 바인딩 된다
    // @Valid = @NotEmpty, @Size 등으로 설정한 검증 기능이 동작
    // bindingResult =  검증이 수행된 결과
    //BindingResult 매개변수는 항상 @Valid 매개변수 바로 뒤에 위치
    @PreAuthorize("isAuthenticated()") //로그인이 필요한 메서드
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "usr/board/question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName()); //---- 유저 객체 구현후 추후 수정 --------------------
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);//--- 유저 객체 구현후 추후 수정 --------------------
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal){
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        } //---- 유저 객체 구현후 추후 수정 --------------------
        question.setSubject(question.getSubject());
        question.setContent(question.getContent());
        return "usr/board/question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
                                 Principal principal, @PathVariable("id") Integer id){
        if(bindingResult.hasErrors()){
            return "usr/board/question_form";
        }
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }//---- 유저 객체 구현후 추후 수정 --------------------
        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%S", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }//---- 유저 객체 구현후 추후 수정 --------------------
        this.questionService.delete(question);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        SiteUser siteUser = this.userService.getUser(principal.getName());//---- 유저 객체 구현후 추후 수정 -------
        this.questionService.vote(question, siteUser);//------ 유저 객체 구현후 추후 수정 -------
        return String.format("redirect:/question/detail/%s", id);
    }

}

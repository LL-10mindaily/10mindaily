package com.ll.tenmindaily.boundedContext.board.question;



import com.ll.tenmindaily.boundedContext.board.answer.Answer;
import com.ll.tenmindaily.boundedContext.board.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //값을 따로 세팅하지 않아도 1씩 자동으로 증가
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)// 참조 엔티티의 속성명,답변들도 모두 함께 삭제
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author; //---- 유저 객체 구현후 추후 수정 --------------------

    @ManyToMany
    Set<SiteUser> voter; //---- 유저 객체 구현후 추후 수정 --------------------

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;
}

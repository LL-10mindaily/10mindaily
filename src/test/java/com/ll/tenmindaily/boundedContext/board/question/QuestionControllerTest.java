package com.ll.tenmindaily.boundedContext.board.question;

import com.ll.tenmindaily.boundedContext.board.answer.Answer;
import com.ll.tenmindaily.boundedContext.board.answer.AnswerRepository;
import com.ll.tenmindaily.boundedContext.board.category.Category;
import com.ll.tenmindaily.boundedContext.board.category.CategoryRepository;
<<<<<<< HEAD
import com.ll.tenmindaily.boundedContext.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
=======
import com.ll.tenmindaily.boundedContext.member.entity.Member;
import com.ll.tenmindaily.boundedContext.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
>>>>>>> 3630690 (Nagiltae (#9))

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
=======
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> 3630690 (Nagiltae (#9))

@SpringBootTest
class QuestionControllerTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 뭐임?");
        q1.setContent("몰라임마");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("질문이요");
        q2.setContent("닥치세요");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);
    }
<<<<<<< HEAD

    @Test
    void testCreateData() { //테스트 게시물 만들기


        for (int i = 1; i <= 10; i++) {
=======
    @Test
    void testCreateData(){ //테스트 게시물 만들기


        for(int i=1; i<=10; i++){
>>>>>>> 3630690 (Nagiltae (#9))
            String subject = String.format("주식 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("nagt1997").orElse(null), categoryRepository.findByInvestment("stock"));
        }

<<<<<<< HEAD
        for (int i = 11; i <= 20; i++) {
=======
        for(int i=11; i<=20; i++){
>>>>>>> 3630690 (Nagiltae (#9))
            String subject = String.format("코인 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("user1").orElse(null), categoryRepository.findByInvestment("coin"));
        }

<<<<<<< HEAD
        for (int i = 21; i <= 30; i++) {
=======
        for(int i=21; i<=30; i++){
>>>>>>> 3630690 (Nagiltae (#9))
            String subject = String.format("부동산 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("user2").orElse(null), categoryRepository.findByInvestment("realestate"));
        }

    }

    @Test
<<<<<<< HEAD
    void testcategory() { //테스트 카테고리 만들기
=======
    void testcategory(){ //테스트 카테고리 만들기
>>>>>>> 3630690 (Nagiltae (#9))
        Category c1 = new Category();
        c1.setId(1);
        c1.setInvestment("stock");
        categoryRepository.save(c1);

        Category c2 = new Category();
        c2.setId(2);
        c2.setInvestment("coin");
        categoryRepository.save(c2);

        Category c3 = new Category();
        c3.setId(3);
        c3.setInvestment("realestate");
        categoryRepository.save(c3);
    }

/*
    @Test
    void testFindAll() {
        List<Question> all = this.questionRepository.findAll();
        //findAll은 데이터를 조회할때 사용하는 메서드
        assertEquals(38, all.size()); //게시물 전체 숫자

        Question q = all.get(0);
        assertEquals("sbb가 뭐임?", q.getSubject());
    }
*/

    @Test
    void testFindById() {
        Optional<Question> oq = this.questionRepository.findById(1);
<<<<<<< HEAD
        if (oq.isPresent()) {//Boolean 타입, Optional 객체가 값을 가진다 true, 없다 false 리턴
=======
        if(oq.isPresent()){//Boolean 타입, Optional 객체가 값을 가진다 true, 없다 false 리턴
>>>>>>> 3630690 (Nagiltae (#9))
            Question q = oq.get();
            assertEquals("sbb가 뭐임?", q.getSubject());
        }
    }

/*    @Test
    void testFindBySubject() { //게시물 이름으로 검색
        Question q = this.questionRepository.findBySubject("sbb가 뭐임?");
        assertEquals(1, q.getId());
    }

    @Test
    void testFindBySubjectAndContent() { //게시물 이름이람 내용으로 검색
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 뭐임?", "몰라임마");
        assertEquals(1, q.getId());
    }*/

    @Test
    void testFindBySubjectLike() { //검색어가 들어간 게시물 찾기
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 뭐임?", q.getSubject());
    }

/*    @Test
    void testModifyData() { //
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //게시물 있나 확인
        Question q = oq.get();
        q.setSubject("수정합니다");
        this.questionRepository.save(q);
    }*/

/*
    @Test
    void testDeleteData() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //게시물 있나 확인
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }
*/

    @Test
    void testCreateAnswerAndSave() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);
        a.setCreateDate(LocalDateTime.now());
        this.answerRepository.save(a);
    }

    @Test
    void testAnswerCheck() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

/*    @Transactional
    @Test
    void testAnswerCheck2() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> aList = q.getAnswerList();

        assertEquals(2, aList.size());
        assertEquals("네 자동으로 생성됩니다.", aList.get(0).getContent());
    }*/

}
package com.ll.tenmindaily.boundedContext.board.question;

import com.ll.tenmindaily.boundedContext.board.answer.Answer;
import com.ll.tenmindaily.boundedContext.board.answer.AnswerRepository;
import com.ll.tenmindaily.boundedContext.board.category.Category;
import com.ll.tenmindaily.boundedContext.board.category.CategoryRepository;
import com.ll.tenmindaily.boundedContext.member.entity.Member;
import com.ll.tenmindaily.boundedContext.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testFindAll() {
        List<Question> all = this.questionRepository.findAll();
        //findAll은 데이터를 조회할때 사용하는 메서드
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 뭐임?", q.getSubject());
    }

    @Test
    void testFindById() {
        Optional<Question> oq = this.questionRepository.findById(1);
        if(oq.isPresent()){//Boolean 타입, Optional 객체가 값을 가진다 true, 없다 false 리턴
            Question q = oq.get();
            assertEquals("sbb가 뭐임?", q.getSubject());
        }
    }

    @Test
    void testFindBySubject() {
        Question q = this.questionRepository.findBySubject("sbb가 뭐임?");
        assertEquals(1, q.getId());
    }

    @Test
    void testFindBySubjectAndContent() {
        Question q = this.questionRepository.findBySubjectAndContent("sbb가 뭐임?", "몰라임마");
        assertEquals(1, q.getId());
    }

    @Test
    void testFindBySubjectLike() {
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 뭐임?", q.getSubject());
    }

    @Test
    void testModifyData() {
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //게시물 있나 확인
        Question q = oq.get();
        q.setSubject("수정합니다");
        this.questionRepository.save(q);
    }

    @Test
    void testDeleteData() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent()); //게시물 있나 확인
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());
    }

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

    @Transactional
    @Test
    void testAnswerCheck2() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> aList = q.getAnswerList();

        assertEquals(1, aList.size());
        assertEquals("네 자동으로 생성됩니다.", aList.get(0).getContent());
    }

    @Test
    void testCreateData(){

        for(int i=1; i<=10; i++){
            String subject = String.format("주식 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("nagt1997").orElse(null), categoryRepository.findByInvestment("stock"));
        }

        for(int i=11; i<=20; i++){
            String subject = String.format("코인 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("user1").orElse(null), categoryRepository.findByInvestment("coin"));
        }

        for(int i=21; i<=30; i++){
            String subject = String.format("부동산 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("user2").orElse(null), categoryRepository.findByInvestment("realestate"));
        }

        for(int i=21; i<=30; i++){
            String subject = String.format("뮤직 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("user2").orElse(null), categoryRepository.findByInvestment("music"));
        }

        for(int i=31; i<=40; i++){
            String subject = String.format("리셀 데이터입니다.:[%03d]", i);
            String content = "내용";
            this.questionService.create(subject, content, memberRepository.findByUsername("nagt1997").orElse(null), categoryRepository.findByInvestment("resell"));
        }
    }

    @Test
    void testcategory(){
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

        Category c4 = new Category();
        c4.setId(4);
        c4.setInvestment("music");
        categoryRepository.save(c4);

        Category c5 = new Category();
        c5.setId(5);
        c5.setInvestment("art");
        categoryRepository.save(c5);

        Category c6 = new Category();
        c6.setId(6);
        c6.setInvestment("resell");
        categoryRepository.save(c6);

        Category c7 = new Category();
        c7.setId(7);
        c7.setInvestment("hobby");
        categoryRepository.save(c7);

        Category c8 = new Category();
        c8.setId(8);
        c8.setInvestment("app");
        categoryRepository.save(c8);

        Category c9 = new Category();
        c9.setId(9);
        c9.setInvestment("sns");
        categoryRepository.save(c9);
    }


}
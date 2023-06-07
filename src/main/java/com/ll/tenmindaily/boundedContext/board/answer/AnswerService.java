package com.ll.tenmindaily.boundedContext.board.answer;


<<<<<<< HEAD
=======

>>>>>>> 3630690 (Nagiltae (#9))
import com.ll.tenmindaily.base.exception.DataNotFoundException;
import com.ll.tenmindaily.boundedContext.board.question.Question;
import com.ll.tenmindaily.boundedContext.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

<<<<<<< HEAD
    public Answer craete(Question question, String content, Member author) {
=======
    public Answer craete(Question question, String content, Member author){
>>>>>>> 3630690 (Nagiltae (#9))
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }
<<<<<<< HEAD

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
=======
    public Answer getAnswer(Integer id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        } else{
>>>>>>> 3630690 (Nagiltae (#9))
            throw new DataNotFoundException("answer not found");
        }
    }

<<<<<<< HEAD
    public void modify(Answer answer, String content) {
=======
    public void modify(Answer answer, String content){
>>>>>>> 3630690 (Nagiltae (#9))
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
<<<<<<< HEAD

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, Member member) {
=======
    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, Member member){
>>>>>>> 3630690 (Nagiltae (#9))
        answer.getVoter().add(member);
        this.answerRepository.save(answer);
    }
}

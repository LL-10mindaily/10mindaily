package com.ll.tenmindaily.boundedContext.board.answer;



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

    public Answer craete(Question question, String content, Member author){//--------추후 수정---------
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author); //------------- 추후 수정 --------------------
        this.answerRepository.save(answer);
        return answer;
    }
    public Answer getAnswer(Integer id){ //답변 조회
        Optional<Answer> answer = this.answerRepository.findById(id);
        if(answer.isPresent()){
            return answer.get();
        } else{
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content){ //답변 수정
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    //답변 추천에 사용자 정보 저장 ------------- 추후 수정 --------------------
    public void vote(Answer answer, Member member){
        answer.getVoter().add(member);
        this.answerRepository.save(answer);
    }
}

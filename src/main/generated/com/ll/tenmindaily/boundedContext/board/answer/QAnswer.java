package com.ll.tenmindaily.boundedContext.board.answer;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAnswer is a Querydsl query type for Answer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAnswer extends EntityPathBase<Answer> {

    private static final long serialVersionUID = 1908484725L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAnswer answer = new QAnswer("answer");

    public final com.ll.tenmindaily.boundedContext.member.entity.QMember author;

    public final ListPath<com.ll.tenmindaily.boundedContext.board.comment.Comment, com.ll.tenmindaily.boundedContext.board.comment.QComment> commentList = this.<com.ll.tenmindaily.boundedContext.board.comment.Comment, com.ll.tenmindaily.boundedContext.board.comment.QComment>createList("commentList", com.ll.tenmindaily.boundedContext.board.comment.Comment.class, com.ll.tenmindaily.boundedContext.board.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final com.ll.tenmindaily.boundedContext.board.question.QQuestion question;

    public final SetPath<com.ll.tenmindaily.boundedContext.member.entity.Member, com.ll.tenmindaily.boundedContext.member.entity.QMember> voter = this.<com.ll.tenmindaily.boundedContext.member.entity.Member, com.ll.tenmindaily.boundedContext.member.entity.QMember>createSet("voter", com.ll.tenmindaily.boundedContext.member.entity.Member.class, com.ll.tenmindaily.boundedContext.member.entity.QMember.class, PathInits.DIRECT2);

    public QAnswer(String variable) {
        this(Answer.class, forVariable(variable), INITS);
    }

    public QAnswer(Path<? extends Answer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAnswer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAnswer(PathMetadata metadata, PathInits inits) {
        this(Answer.class, metadata, inits);
    }

    public QAnswer(Class<? extends Answer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.tenmindaily.boundedContext.member.entity.QMember(forProperty("author")) : null;
        this.question = inits.isInitialized("question") ? new com.ll.tenmindaily.boundedContext.board.question.QQuestion(forProperty("question"), inits.get("question")) : null;
    }

}


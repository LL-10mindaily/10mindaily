package com.ll.tenmindaily.boundedContext.board.question;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestion is a Querydsl query type for Question
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestion extends EntityPathBase<Question> {

    private static final long serialVersionUID = -259131403L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestion question = new QQuestion("question");

    public final ListPath<com.ll.tenmindaily.boundedContext.board.answer.Answer, com.ll.tenmindaily.boundedContext.board.answer.QAnswer> answerList = this.<com.ll.tenmindaily.boundedContext.board.answer.Answer, com.ll.tenmindaily.boundedContext.board.answer.QAnswer>createList("answerList", com.ll.tenmindaily.boundedContext.board.answer.Answer.class, com.ll.tenmindaily.boundedContext.board.answer.QAnswer.class, PathInits.DIRECT2);

    public final com.ll.tenmindaily.boundedContext.board.user.QSiteUser author;

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createDate = createDateTime("createDate", java.time.LocalDateTime.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> modifyDate = createDateTime("modifyDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final NumberPath<Integer> view = createNumber("view", Integer.class);

    public final SetPath<com.ll.tenmindaily.boundedContext.board.user.SiteUser, com.ll.tenmindaily.boundedContext.board.user.QSiteUser> voter = this.<com.ll.tenmindaily.boundedContext.board.user.SiteUser, com.ll.tenmindaily.boundedContext.board.user.QSiteUser>createSet("voter", com.ll.tenmindaily.boundedContext.board.user.SiteUser.class, com.ll.tenmindaily.boundedContext.board.user.QSiteUser.class, PathInits.DIRECT2);

    public QQuestion(String variable) {
        this(Question.class, forVariable(variable), INITS);
    }

    public QQuestion(Path<? extends Question> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestion(PathMetadata metadata, PathInits inits) {
        this(Question.class, metadata, inits);
    }

    public QQuestion(Class<? extends Question> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new com.ll.tenmindaily.boundedContext.board.user.QSiteUser(forProperty("author")) : null;
    }

}


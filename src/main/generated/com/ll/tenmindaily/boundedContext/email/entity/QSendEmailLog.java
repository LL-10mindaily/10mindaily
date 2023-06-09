package com.ll.tenmindaily.boundedContext.email.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSendEmailLog is a Querydsl query type for SendEmailLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSendEmailLog extends EntityPathBase<SendEmailLog> {

    private static final long serialVersionUID = 1604632792L;

    public static final QSendEmailLog sendEmailLog = new QSendEmailLog("sendEmailLog");

    public final StringPath body = createString("body");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final DateTimePath<java.time.LocalDateTime> failDate = createDateTime("failDate", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath message = createString("message");

    public final StringPath resultCode = createString("resultCode");

    public final DateTimePath<java.time.LocalDateTime> sendEndDate = createDateTime("sendEndDate", java.time.LocalDateTime.class);

    public final StringPath subject = createString("subject");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QSendEmailLog(String variable) {
        super(SendEmailLog.class, forVariable(variable));
    }

    public QSendEmailLog(Path<? extends SendEmailLog> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSendEmailLog(PathMetadata metadata) {
        super(SendEmailLog.class, metadata);
    }

}


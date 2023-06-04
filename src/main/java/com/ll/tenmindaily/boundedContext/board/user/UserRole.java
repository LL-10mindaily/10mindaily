package com.ll.tenmindaily.boundedContext.board.user;

import lombok.Getter;

@Getter
//인증후에 사용자에게 부여할 권한관리, ADMIN, USER
public enum UserRole {
    ADMIN("ROLE_ADMIN"),USER("ROLE_USER");

    UserRole(String value){
        this.value = value;
    }

    private String value;
}

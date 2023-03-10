package com.tinder.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LoginMessageEnum {
    LOGIN_SUCCESS("Successfully logged in"),
    LOGIN_FAIL("Wrong password or email, try again");

    private final String message;
}

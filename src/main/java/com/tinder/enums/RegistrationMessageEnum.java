package com.tinder.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RegistrationMessageEnum {
    SUCCESS("Registration completed successfully"),
    FAIL("Password confirmation failed, please try again!"),
    EMAIL_ALREADY_USED("This email already used");

    private final String message;
}

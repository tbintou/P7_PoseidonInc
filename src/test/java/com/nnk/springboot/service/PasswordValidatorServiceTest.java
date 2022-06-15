package com.nnk.springboot.service;

import com.nnk.springboot.service.implementation.PasswordValidator;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordValidatorServiceTest {

    @Test
    public void passwordRegex(String password) {
        assertTrue(PasswordValidator.isValid(password));
    }

    @Test
    public void passwordRegexInvalid(String password) {
        assertFalse(PasswordValidator.isValid(password));
    }

    @Test
    public static Stream<String> passwordProvider() {
        return Stream.of(
                "HHHgggaaa°123",
                "passworD&456",
                "J<?@-z97",
                "0987654321+mnopQAR"
        );
    }

    @Test
    public static Stream<String> passwordProvideInvalid() {
        return Stream.of(
                "test1234",
                "toto",
                "&@!$-;<",
                "Password34",
                " ",
                ""
        );
    }

}

package jwt.utils;

import jwt.model.JwtToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
class JwtUtilsTest {

    @Test
    void parseToken() {
        JwtToken subject = new JwtToken("v.p@mail.com",
                LocalDateTime.of(2023, 12, 7, 15, 30),
                "very_secret_secret");

        List<String> result = JwtUtils.parseToken(subject.toString());
        List<String> expectedResult = List.of(
                "v.p@mail.com",
                "2023_12_07T15_30_00",
                "very_secret_secret"
        );

        assertIterableEquals(expectedResult, result);
    }

    @Test
    void test_isValid_resultTrue() {
        String token = "v.p@mail.com#3023_12_07T20_30_00#" + JwtUtils.SECRET;

        boolean result = JwtUtils.isValid(token, "v.p@mail.com");

        Assertions.assertTrue(result);
    }

    @Test
    void test_isInvalid_isExpired_resultFalse() {
        String token = "v.p@mail.com#2023_01_01T20_30_00#" + JwtUtils.SECRET;

        boolean result = JwtUtils.isValid(token, "v.p@mail.com");

        Assertions.assertFalse(result);
    }

    @Test
    void test_isInvalid_userNotEquals_resultFalse() {
        String token = "a.r@mail.com#3023_12_07T20_30_00#" + JwtUtils.SECRET;

        boolean result = JwtUtils.isValid(token, "v.p@mail.com");

        Assertions.assertFalse(result);
    }

    @Test
    void test_isInvalid_secretInvalid_resultFalse() {
        String token = "a.r@mail.com#3023_12_07T20_30_00#asdasda1";

        boolean result = JwtUtils.isValid(token, "v.p@mail.com");

        Assertions.assertFalse(result);
    }

    @Test
    void test_isInvalid_invalidSignatur_resultFalse() {
        String token = "invalidSignatur";

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> JwtUtils.isValid(token, "v.p@mail.com"));

        Assertions.assertNotNull(exception);
        Assertions.assertEquals("Error, token signature is invalid", exception.getMessage());
    }
}

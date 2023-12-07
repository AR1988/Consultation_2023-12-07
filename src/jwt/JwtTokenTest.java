package jwt;

import jwt.model.JwtToken;
import org.junit.jupiter.api.Test;
import jwt.utils.JwtUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class JwtTokenTest {

    @Test
    public void testToString() {
        JwtToken subject = new JwtToken("v.p@mail.com",
                LocalDateTime.of(2023, 12, 7, 15, 30),
                JwtUtils.SECRET);

        String result = subject.toString();
        String expectedResult = "v.p@mail.com#2023_12_07T15_30_00#" + JwtUtils.SECRET;

        assertEquals(expectedResult, result, "Test is invalid");
    }
}

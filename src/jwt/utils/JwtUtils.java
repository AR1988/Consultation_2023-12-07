package jwt.utils;

import jwt.model.JwtToken;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class JwtUtils {

    public static final String SECRET = "askjdhbj16513kij!";

    public static String generateToken(String name, LocalDateTime expiredAt) {
        JwtToken jwtToken = new JwtToken(name, expiredAt, SECRET);
        return jwtToken.toString();
    }

    public static boolean isValid(String token, String userEmail) {
        List<String> parsedList = parseToken(token);
        if (parsedList.size() == 3) {
            String email = parsedList.get(0);
            String expiredAtAsSting = parsedList.get(1);
            String secret = parsedList.get(2);
            LocalDateTime expiredAt = LocalDateTime.parse(expiredAtAsSting, DateTimeFormatter.ofPattern("yyyy_MM_dd'T'HH_mm_ss"));


            if (!userEmail.equalsIgnoreCase(email)) {
                return false;
            }
            if (expiredAt.isBefore(LocalDateTime.now())) {
                return false;
            }
            if (!secret.equals(SECRET)) {
                return false;
            }

            return true;

        }
        throw new IllegalArgumentException("Error, token signature is invalid");
    }

    public static List<String> parseToken(String token) {
        return Stream.of(token.split("#")).collect(Collectors.toList());
    }



}

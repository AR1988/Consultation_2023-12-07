package jwt.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class JwtToken {
    private String userEmail;
    private LocalDateTime expiredDate;
    private String secret;

    public JwtToken(String userEmail, LocalDateTime expiredDate, String secret) {
        this.userEmail = userEmail;
        this.expiredDate = expiredDate;
        this.secret = secret;
    }

    @Override
    public String toString() {
        return userEmail + "#" +
                expiredDate.format(DateTimeFormatter.ofPattern("yyyy_MM_dd'T'HH_mm_ss")) +
                "#" + secret;
    }
}

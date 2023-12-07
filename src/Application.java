import jwt.model.JwtToken;
import jwt.utils.JwtUtils;
import users.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class Application {

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        userRepository.initUsers(10);

        List<String> tokens = userRepository.getAllEmails()
                .stream()
                .map(email -> JwtUtils.generateToken(email, LocalDateTime.now().plusDays(2)))
                .collect(Collectors.toList());

        List<JwtToken> jwtUtils = tokens.stream()
                .map(x -> JwtUtils.parseToken(x))
                .filter(x -> x.size() == 3)
                .map(token -> {
                    String email = token.get(0);
                    String expiredAtAsSting = token.get(1);
                    String secret = token.get(2);
                    LocalDateTime expiredAt = LocalDateTime.parse(expiredAtAsSting, DateTimeFormatter.ofPattern("yyyy_MM_dd'T'HH_mm_ss"));
                    return new JwtToken(email, expiredAt, secret);
                }).collect(Collectors.toList());
    }
}

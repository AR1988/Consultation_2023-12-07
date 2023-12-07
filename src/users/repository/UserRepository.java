package users.repository;

import com.github.javafaker.Faker;
import users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class UserRepository {

    private Faker faker = new Faker();

    private List<User> source = new ArrayList<>();
    private Map<String, User> userEmails = new HashMap<>();

    public void initUsers(int maxUsers) {
        source = IntStream.range(0, maxUsers)
                .mapToObj(value -> new User(faker.internet().emailAddress(), faker.name().firstName()))
                .collect(Collectors.toList());

        userEmails = source.stream()
                .collect(Collectors.toMap(key -> key.getUserEmail(), o -> o));
    }

    public List<String> getAllEmails() {
//        return userEmails.keySet().stream().collect(Collectors.toList());
//        return new ArrayList<>(userEmails.keySet());
        return source.stream()
                .map(User::getUserEmail)
                .collect(Collectors.toList());
    }
}

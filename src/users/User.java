package users;

/**
 * @author Andrej Reutow
 * created on 07.12.2023
 */
public class User {
    private String userEmail;
    private String firstName;

    public User(String userEmail, String firstName) {
        this.userEmail = userEmail;
        this.firstName = firstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}

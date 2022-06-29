package pojo;

import lombok.Data;

@Data
public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCredentials getCredsFrom(User user) {
        return new UserCredentials(user);
    }
}
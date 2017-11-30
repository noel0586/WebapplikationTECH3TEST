package Model;

/**
 * Created by leonemborg on 30/03/2017.
 */
public class User {

    private String user;
    private String password;
    private String role;

    public User(String role, String user, String password) {
        this.user = user;
        this.password = password;
        this.role = role;
    }

    public User() {
        this.user = "";
        this.password = "";
        this.role = "user";
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setAdmin(String admin) {
        this.role = role;
    }
}

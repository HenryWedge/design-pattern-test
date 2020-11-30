package de.ppi.here.socialmedia.util;

public class UserContext {

    private Integer userId;
    private String username;
    private String role;
    private String token;

    public UserContext(final Integer userId, final String username, final String role, final String token) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}

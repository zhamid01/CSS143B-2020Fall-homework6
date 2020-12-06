package Problem2;

import java.util.Objects;

public class Identity implements Comparable {
    public String username;
    public String password;

    public Identity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public int compareTo(Object o) {
        Identity id = (Identity) o;
        return this.username.compareTo(id.username);
    }

    @Override
    public String toString() {
        return "Identity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identity identity = (Identity) o;
        return Objects.equals(username, identity.username) &&
                Objects.equals(password, identity.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}

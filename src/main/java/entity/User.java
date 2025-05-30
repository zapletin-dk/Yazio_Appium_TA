package entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String username;
    private String password;
    private String email;
    private String phone;

    private User(Builder builder) {
        this.username = builder.username;
        this.password = builder.password;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String username;
        private String password;
        private String email;
        private String phone;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

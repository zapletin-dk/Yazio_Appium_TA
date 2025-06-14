package entity;

import com.github.javafaker.Faker;

public class UserFactory {
    private static final Faker faker = new Faker();

    public static User getExistingUser() {
        return new User.Builder()
                .email("yazio_test@yopmail.com")
                .password("qwerty123").build();
    }

    public static User createRandomUser() {
        return new User.Builder()
                .username(faker.name().username())
                .password(faker.internet().password())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().cellPhone())
                .build();
    }

}

package utils;

import com.github.javafaker.Faker;

public class UserFactory {
    public static User existingUser;

    static {
        existingUser = new UserFactory().createUser();
        existingUser.setEmail("yazio_test@yopmail.com");
        existingUser.setPassword("qwerty123");
    }

    Faker faker = new Faker();

    public User createUser() {
        return new User(faker.name().username(), faker.internet().password(), faker.internet().emailAddress(), faker.phoneNumber().cellPhone());
    }

}

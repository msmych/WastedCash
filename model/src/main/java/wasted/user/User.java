package wasted.user;

import static java.time.Instant.now;
import static java.util.OptionalLong.empty;
import static java.util.UUID.randomUUID;

import java.util.OptionalLong;
import java.util.UUID;
import java.time.Instant;

public class User {

    public final UUID id;
    public final OptionalLong telegramId;
    public final Instant createdDate;
    public final Instant updatedDate;

    private User(UUID id, OptionalLong telegramId) {
        this.id = id;
        this.telegramId = telegramId;
        this.createdDate = now();
        this.updatedDate = now();
    }

    public static User user() {
        return new User(randomUUID(), empty());
    }

    public static User telegramUser(int telegramId) {
        return new User(randomUUID(), OptionalLong.of(telegramId));
    }
}
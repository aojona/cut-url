package ru.kirill.cuturl.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

@SpringBootTest
public abstract class IntegrationTestBase {

    private static final GenericContainer<?> REDIS =
            new GenericContainer<>(DockerImageName.parse("arm64v8/redis:7"));

    @BeforeAll
    static void runContainer() {
        REDIS.start();
    }
}

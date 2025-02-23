package ru.netology.conditionall1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConditionalL1ApplicationTests {
    @Autowired
    RestTemplate restTemplate;

    private static final GenericContainer<?> devContainer = new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    private static final GenericContainer<?> prodContainer = new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @BeforeAll
    public static void setUp() {
        devContainer.start();
        prodContainer.start();
    }

    @Test
    void devProfileTest() {
        String devBaseUrl = "http://localhost:" + devContainer.getMappedPort(8080) + "/profile";
        ResponseEntity<String> response = restTemplate.getForEntity(devBaseUrl, String.class);
        assertEquals("Current profile is dev", response.getBody());
    }

    @Test
    void prodProfileTest() {
        String prodBaseUrl = "http://localhost:" + prodContainer.getMappedPort(8081) + "/profile";
        ResponseEntity<String> response = restTemplate.getForEntity(prodBaseUrl, String.class);
        assertEquals("Current profile is production", response.getBody());
    }


}

package be.vdab.restclient.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DefaultReqresClientTest {
    private final DefaultReqresClient client;

    public DefaultReqresClientTest(DefaultReqresClient client) {
        this.client = client;
    }

    @Test
    void findBestaandeUser() {
        assertThat(client.findById(1)).hasValueSatisfying(user ->
                assertThat(user.getData().getId()).isOne());
    }
    @Test
    void findOnbestaandeUser() {
        assertThat(client.findById(-1)).isEmpty();
    }
}
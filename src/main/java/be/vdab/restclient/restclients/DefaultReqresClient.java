package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
class DefaultReqresClient implements ReqresClient{
    private final WebClient client;
    private final String userURI;
    DefaultReqresClient(WebClient.Builder builder,
                        @Value("${regres.user}") String userURI) {
        client = builder.build();
        this.userURI = userURI;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.of(client.get()
                    .uri(userURI, uriBuilder -> uriBuilder.build(id))
                    .retrieve().bodyToMono(User.class).block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}

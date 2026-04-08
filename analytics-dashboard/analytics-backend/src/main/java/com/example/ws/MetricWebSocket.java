
package com.example.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.*;
import reactor.core.publisher.Flux;
import java.time.Duration;

@Component
public class MetricWebSocket implements WebSocketHandler {

    @Override
    public Flux<WebSocketMessage> handle(WebSocketSession session){
        return session.send(
            Flux.interval(Duration.ofSeconds(1))
                .map(i -> "CPU:" + Math.random()*100)
                .map(session::textMessage)
        );
    }
}


package com.example.controller;

import com.example.model.Metric;
import com.example.repo.MetricRepo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.time.Duration;

@RestController
@RequestMapping("/api")
public class MetricController {

    private final MetricRepo repo;

    public MetricController(MetricRepo repo){
        this.repo = repo;
    }

    @GetMapping("/history")
    public Flux<Metric> history(){
        return repo.findAll();
    }

    @GetMapping(value="/metrics/stream", produces="text/event-stream")
    public Flux<Metric> stream(){
        return Flux.interval(Duration.ofSeconds(1))
            .map(i -> new Metric("CPU", Math.random()*100))
            .flatMap(repo::save);
    }
}

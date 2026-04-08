
package com.example.repo;

import com.example.model.Metric;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MetricRepo extends ReactiveMongoRepository<Metric, String> {}

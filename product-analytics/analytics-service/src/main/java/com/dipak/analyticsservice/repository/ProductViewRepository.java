package com.dipak.analyticsservice.repository;

import com.dipak.analyticsservice.entity.ProductViewCount;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductViewRepository extends ReactiveCrudRepository<ProductViewCount, Integer> {

    Flux<ProductViewCount> findTop5ByOrderByCountDesc();

}

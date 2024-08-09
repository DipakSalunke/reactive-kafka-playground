package com.dipak.analyticsservice.service;

import com.dipak.analyticsservice.dto.ProductTrendingDto;
import com.dipak.analyticsservice.repository.ProductViewRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class ProductTrendingBroadcastService {

    private final ProductViewRepository repository;
    @Getter
    private Flux<List<ProductTrendingDto>> trends;
    private final com.dipak.analyticsservice.service.ProductViewEventConsumer productViewEventConsumer;

    @PostConstruct
    private void init(){
        this.trends = this.repository.findTop5ByOrderByCountDesc()
                .map(pvc -> new ProductTrendingDto(pvc.getId(), pvc.getCount()))
                .collectList()
                .filter(Predicate.not(List::isEmpty))
                //.repeatWhen(l -> l.delayElements(Duration.ofSeconds(3)))
                .repeatWhen(l -> productViewEventConsumer.companionFlux())
                .distinctUntilChanged()
                .cache(1);
    }

}

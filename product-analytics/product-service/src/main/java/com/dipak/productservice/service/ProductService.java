package com.dipak.productservice.service;

import com.dipak.productservice.dto.ProductDto;
import com.dipak.productservice.event.ProductViewEvent;
import com.dipak.productservice.repository.ProductRepository;
import com.dipak.productservice.util.EntityDtoUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductViewEventProducer productViewEventProducer;

    public Mono<ProductDto> getProduct(int id){
        return this.repository.findById(id)
                .doOnNext(e -> this.productViewEventProducer.emitEvent(new ProductViewEvent(e.getId())))
                .map(EntityDtoUtil::toDto);
    }

}

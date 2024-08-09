package com.dipak.productservice.util;

import com.dipak.productservice.dto.ProductDto;
import com.dipak.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product){
        var dto = new ProductDto();
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

}

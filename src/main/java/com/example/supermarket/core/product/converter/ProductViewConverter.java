package com.example.supermarket.core.product.converter;

import com.example.supermarket.core.order.Order;
import com.example.supermarket.core.order.web.OrderView;
import com.example.supermarket.core.product.Product;
import com.example.supermarket.core.product.web.ProductView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductViewConverter implements Converter<Product, ProductView> {
    @Override
    public ProductView convert(Product product) {
        return null;
    }
}

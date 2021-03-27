package com.example.supermarket.core.product.converter;

import com.example.supermarket.core.product.Product;
import com.example.supermarket.core.product.web.ProductView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProductViewConverter implements Converter<Product, ProductView> {
    @Override
    public ProductView convert(@Nullable Product product) {
        ProductView view=new ProductView();
        view.setId(product.getId());
        view.setProductType(product.getProductType());
        view.setPrice(product.getPrice());
        view.setName(product.getName());
        view.setDetail(product.getDetail());
        view.setDescription(product.getDescription());
        return view;
    }
}

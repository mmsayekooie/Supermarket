package com.example.supermarket.core.order.converter;

import com.example.supermarket.core.address.Addresss;
import com.example.supermarket.core.address.web.AddresssView;
import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.customer.web.CustomerView;
import com.example.supermarket.core.order.Order;
import com.example.supermarket.core.order.web.OrderView;
import com.example.supermarket.core.product.Product;
import com.example.supermarket.core.product.converter.ProductViewConverter;
import com.example.supermarket.core.product.web.ProductView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrderViewConverter implements Converter<Order, OrderView> {
   private final ProductViewConverter productViewConverter;

    public OrderViewConverter(ProductViewConverter productViewConverter) {
        this.productViewConverter = productViewConverter;
    }

    @Override
    public OrderView convert(@Nullable Order order) {
        OrderView view=new OrderView();
        view.setPaidfor();
        Set<ProductView> views = new HashSet<>();
        Set<Product> products= order.getProducts();
        products.forEach(product -> {
            ProductView productView = productViewConverter.convert(product);
            views.add(productView);
        });
        view.setProducts(views);
        return view;
    }
}

package com.example.supermarket.core.order;

import com.example.supermarket.base.BaseRequest;
import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.customer.CustomerRepo;
import com.example.supermarket.core.date.DDate;
import com.example.supermarket.core.date.DDateRepo;
import com.example.supermarket.core.order.converter.OrderViewConverter;
import com.example.supermarket.core.order.web.OrderBaseReq;
import com.example.supermarket.core.order.web.OrderView;
import com.example.supermarket.core.product.Product;
import com.example.supermarket.core.product.ProductRepo;
import com.example.supermarket.error.EntityNotFoundException;
import com.example.supermarket.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final OrderViewConverter orderViewConverter;
    private final CustomerRepo customerRepo;
    private final DDateRepo dDateRepo;
    private final ProductRepo productRepo;
    private final MessageUtil messageUtil;

    public OrderService(OrderRepo orderRepo, OrderViewConverter orderViewConverter, CustomerRepo customerRepo, DDateRepo dDateRepo, ProductRepo productRepo, MessageUtil messageUtil) {
        this.orderRepo = orderRepo;
        this.orderViewConverter = orderViewConverter;
        this.customerRepo = customerRepo;
        this.dDateRepo = dDateRepo;
        this.productRepo = productRepo;
        this.messageUtil = messageUtil;
    }

    public Order findOrderOrThrow(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("order.NotFound", id)));
    }

    public OrderView getOrder(Long id) {
        Order order = findOrderOrThrow(id);
        return orderViewConverter.convert(order);
    }

    public Page<OrderView> findAllOrder(Pageable pageable) {
        Page<Order> orders = orderRepo.findAll(pageable);
        List<OrderView> orderViews = new ArrayList<>();
        orders.forEach(order -> {
            OrderView orderView = orderViewConverter.convert(order);
            orderViews.add(orderView);
        });
        return new PageImpl<>(orderViews, pageable, orders.getTotalElements());
    }

    public OrderView create(OrderBaseReq req) {
        Order order = new Order();
        this.prepare(order, req);
        Order orderSave = orderRepo.save(order);
        return orderViewConverter.convert(orderSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            orderRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("order.NotFound", id));
        }
    }

    public OrderView update(Order order, OrderBaseReq req) {
        Order newOrder = this.prepare(order,req);
        Order orderSave = orderRepo.save(newOrder);
        return orderViewConverter.convert(orderSave);
    }

    public Order prepare(Order order, OrderBaseReq orderBaseReq){
        Customer customer=customerRepo.getOne(orderBaseReq.getCustomerId());
        order.setCustomer(customer);
        DDate dDate=dDateRepo.getOne(orderBaseReq.getdDateId());
        order.setdDate(dDate);
        order.setPaidfor(orderBaseReq.getPaidfor());
        List<Product> productList = productRepo.findAllById(orderBaseReq.getProducts()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Product> products = new HashSet<>(productList);
        order.setProducts(products);
        return order;
    }
}

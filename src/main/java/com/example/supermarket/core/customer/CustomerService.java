package com.example.supermarket.core.customer;

import com.example.supermarket.base.BaseRequest;
import com.example.supermarket.core.address.Addresss;
import com.example.supermarket.core.address.AddresssRepo;
import com.example.supermarket.core.customer.converter.CustomerViewConverter;
import com.example.supermarket.core.customer.web.CustomerBaseReq;
import com.example.supermarket.core.customer.web.CustomerView;
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
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerViewConverter customerViewConverter;
    private final AddresssRepo addresssRepo;
    private final MessageUtil messageUtil;

    public CustomerService(CustomerRepo customerRepo, CustomerViewConverter customerViewConverter, AddresssRepo addresssRepo, MessageUtil messageUtil) {

        this.customerRepo = customerRepo;
        this.customerViewConverter = customerViewConverter;
        this.addresssRepo = addresssRepo;
        this.messageUtil = messageUtil;
    }

    public Customer findCustomerOrThrow(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id)));
    }

    public CustomerView getCustomer(Long id) {
        Customer customer = findCustomerOrThrow(id);
        return customerViewConverter.convert(customer);
    }

    public Page<CustomerView> findAllPlayer(Pageable pageable) {
        Page<Customer> customers = customerRepo.findAll(pageable);
        List<CustomerView> customerViews = new ArrayList<>();
        customers.forEach(player -> {
            CustomerView customerView = customerViewConverter.convert(player);
            customerViews.add(customerView);
        });
        return new PageImpl<>(customerViews, pageable, customers.getTotalElements());
    }

    public CustomerView create(CustomerBaseReq req) {
        Customer customer = new Customer();
        this.prepare(customer, req);
        Customer customerSave = customerRepo.save(customer);
        return customerViewConverter.convert(customerSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            customerRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id));
        }
    }

    public CustomerView update(Customer customer, CustomerBaseReq req) {
        Customer newCustomer = this.prepare(customer,req);
        Customer customerSave = customerRepo.save(newCustomer);
        return customerViewConverter.convert(customerSave);
    }

    public Customer prepare(Customer customer, CustomerBaseReq customerBaseReq){
        customer.setName(customerBaseReq.getName());
        customer.setPhone(customerBaseReq.getPhone());
        customer.setEmail(customerBaseReq.getEmail());
        customer.setDetail(customerBaseReq.getDetail());
        customer.setDateBecomeCustomer(customerBaseReq.getDateBecomeCustomer());
        List<Addresss> addresssList = addresssRepo.findAllById(customerBaseReq.getAddressses()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Addresss> addressses = new HashSet<>(addresssList);
        customer.setAddressses(addressses);
        return customer;
    }

}

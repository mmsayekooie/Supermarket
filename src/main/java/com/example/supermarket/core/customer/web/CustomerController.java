package com.example.supermarket.core.customer.web;


import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.customer.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService  service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public CustomerView getCustomer(@PathVariable Long id) {
        return service.getCustomer(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody
    public Page<CustomerView> getAllCustomer(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllCustomer(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CustomerView create(@RequestBody @Valid CustomerBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CustomerView updateCustomer(@PathVariable(name = "id") Long id,
                                      @RequestBody @Valid CustomerBaseReq req) {
        Customer customer = service.findCustomerOrThrow(id);
        return service.update(customer, req);
    }
}

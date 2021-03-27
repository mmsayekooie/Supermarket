package com.example.supermarket.core.customer.converter;

import com.example.supermarket.core.address.Addresss;
import com.example.supermarket.core.address.converter.AddresssViewConverter;
import com.example.supermarket.core.address.web.AddresssView;
import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.customer.web.CustomerView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class CustomerViewConverter implements Converter<Customer, CustomerView> {
    private final AddresssViewConverter addresssViewConverter;

    public CustomerViewConverter(AddresssViewConverter addresssViewConverter) {
        this.addresssViewConverter = addresssViewConverter;
    }

    @Override
    public CustomerView convert(@Nullable Customer customer) {
        CustomerView view=new CustomerView();
        view.setId(customer.getId());
        view.setName(customer.getName());
        view.setPhone(customer.getPhone());
        view.setEmail(customer.getEmail());
        view.setDateBecomeCustomer(customer.getDateBecomeCustomer());
        Set<AddresssView> views = new HashSet<>();
        Set<Addresss> addressses= customer.getAddressses();
        addressses.forEach(addresss -> {
            AddresssView addresssView = addresssViewConverter.convert(addresss);
            views.add(addresssView);
        });
        view.setAddressses(views);
        return view;

    }
}

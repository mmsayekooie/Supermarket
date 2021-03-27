package com.example.supermarket.core.supplier.converter;

import com.example.supermarket.core.supplier.Supplier;
import com.example.supermarket.core.supplier.web.SupplierView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class SupplierViewConverter implements Converter<Supplier,SupplierView> {
    @Override
    public SupplierView convert(@Nullable Supplier supplier) {
        SupplierView view=new SupplierView();
        view.setId(supplier.getId());
        view.setName(supplier.getName());
        view.setEmail(supplier.getEmail());
        view.setFactory(supplier.getFactory());
        view.setPhone(supplier.getPhone());
        return view;
    }
}

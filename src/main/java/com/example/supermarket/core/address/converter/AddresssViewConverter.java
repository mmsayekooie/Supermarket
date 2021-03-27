package com.example.supermarket.core.address.converter;

import com.example.supermarket.core.address.Addresss;
import com.example.supermarket.core.address.web.AddresssView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class AddresssViewConverter implements Converter<Addresss, AddresssView> {

    @Override
    public AddresssView convert(@Nullable Addresss addresss) {
        AddresssView view=new AddresssView();
        view.setId(addresss.getId());
        view.setCountry(addresss.getCountry());
        view.setCity(addresss.getCity());
        view.setState(addresss.getState());
        view.setZipCode(addresss.getZipCode());
        view.setLine1(addresss.getLine1());
        view.setLine2(addresss.getLine2());
        view.setBlock(addresss.getBlock());
        view.setDetail(addresss.getDetail());
        return view;
    }
}

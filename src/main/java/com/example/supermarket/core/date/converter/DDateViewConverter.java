package com.example.supermarket.core.date.converter;

import com.example.supermarket.core.date.DDate;
import com.example.supermarket.core.date.web.DDateView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DDateViewConverter implements Converter<DDate,DDateView> {
    @Override
    public DDateView convert(@Nullable DDate dDate) {
        DDateView view=new DDateView();
        view.setDate(dDate.getDate());
        view.setDayOfWeek(dDate.getDayOfWeek());
        return null;
    }
}

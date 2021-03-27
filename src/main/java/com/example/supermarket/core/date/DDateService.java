package com.example.supermarket.core.date;

import com.example.supermarket.core.date.converter.DDateViewConverter;
import com.example.supermarket.core.date.web.DDateBaseReq;
import com.example.supermarket.core.date.web.DDateView;
import com.example.supermarket.error.EntityNotFoundException;
import com.example.supermarket.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DDateService {
    private final DDateRepo dDateRepo;
    private final DDateViewConverter dDateViewConverter;
    private final MessageUtil messageUtil;

    public DDateService(DDateRepo dDateRepo, DDateViewConverter dDateViewConverter, MessageUtil messageUtil) {
        this.dDateRepo = dDateRepo;
        this.dDateViewConverter = dDateViewConverter;
        this.messageUtil = messageUtil;
    }

    public DDate findDateOrThrow(Long id) {
        return dDateRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("Date.NotFound", id)));
    }

    public Page<DDateView> findAllDates(Pageable pageable){
        Page<DDate> dDates = dDateRepo.findAll(pageable);
        List<DDateView> dDateViews = new ArrayList<>();
        dDates.forEach(dDate -> {
            DDateView dDateView = dDateViewConverter.convert(dDate);
            dDateViews.add(dDateView);
        });
        return new PageImpl<>(dDateViews, pageable, dDates.getTotalElements());
    }

    public DDateView create(DDateBaseReq req) {
        DDate dDate = new DDate();
        this.prepare(dDate,req);
        DDate dateSave = dDateRepo.save(dDate);
        return dDateViewConverter.convert(dateSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            dDateRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("date.NotFound", id));
        }
    }

    public DDateView update(DDate dDate, DDateBaseReq req){
        DDate newDate = this.prepare(dDate,req);
        DDate dateSave= dDateRepo.save(newDate);
        return dDateViewConverter.convert(dateSave);
    }

    public DDate prepare(DDate dDate, DDateBaseReq dDateBaseReq){
        dDate.setDate(dDateBaseReq.getDate());
        dDate.setDayOfWeek(dDateBaseReq.getDayOfWeek());
        return dDate;
    }
}

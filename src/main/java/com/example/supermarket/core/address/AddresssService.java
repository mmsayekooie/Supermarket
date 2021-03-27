package com.example.supermarket.core.address;

import com.example.supermarket.core.address.converter.AddresssViewConverter;
import com.example.supermarket.core.address.web.AddresssBaseReq;
import com.example.supermarket.core.address.web.AddresssView;
import com.example.supermarket.error.EntityNotFoundException;
import com.example.supermarket.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddresssService {
    private AddresssRepo addresssRepo;
    private AddresssViewConverter addresssViewConverter;
    private final MessageUtil messageUtil;

    public AddresssService(AddresssRepo addresssRepo, AddresssViewConverter addresssViewConverter,MessageUtil messageUtil) {
        this.addresssRepo = addresssRepo;
        this.addresssViewConverter = addresssViewConverter;
        this.messageUtil = messageUtil;
    }
    public AddresssView getAddresss(Long id) {
        Addresss addresss = findAddresssOrThrow(id);
        return addresssViewConverter.convert(addresss);
    }

    public Addresss findAddresssOrThrow(Long id) {
        return addresssRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("team.NotFound", id)));
    }

    public Page<AddresssView> findAllAddresss(Pageable pageable){
        Page<Addresss> Addressses = addresssRepo.findAll(pageable);
        List<AddresssView> AddresssViews = new ArrayList<>();
        Addressses.forEach(addresss -> {
            AddresssView addresssView = addresssViewConverter.convert(addresss);
            AddresssViews.add(addresssView);
        });
        return new PageImpl<>(AddresssViews, pageable, Addressses.getTotalElements());
    }

    public AddresssView create(AddresssBaseReq req) {
        Addresss addresss = new Addresss();
        this.prepare(addresss,req);
        Addresss addressSave = addresssRepo.save(addresss);
        return addresssViewConverter.convert(addressSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            addresssRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("address.NotFound", id));
        }
    }

    public AddresssView update(Addresss addresss, AddresssBaseReq req){
        Addresss newAddress = this.prepare(addresss,req);
        Addresss addressSave= addresssRepo.save(newAddress);
        return addresssViewConverter.convert(addressSave);
    }

    public Addresss prepare(Addresss addresss, AddresssBaseReq addresssBaseReq){
        addresss.setCountry(addresssBaseReq.getCountry());
        addresss.setState(addresssBaseReq.getState());
        addresss.setCity(addresssBaseReq.getCity());
        addresss.setLine1(addresssBaseReq.getLine1());
        addresss.setLine2(addresssBaseReq.getLine2());
        addresss.setZipCode(addresssBaseReq.getZipCode());
        addresss.setBlock(addresssBaseReq.getBlock());
        addresss.setDetail(addresssBaseReq.getDetail());
        return addresss;
    }

}
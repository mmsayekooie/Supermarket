package com.example.supermarket.core.supplier;

import com.example.supermarket.core.supplier.converter.SupplierViewConverter;
import com.example.supermarket.core.supplier.web.SupplierBaseReq;
import com.example.supermarket.core.supplier.web.SupplierView;
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
public class SupplierService {
    private final SupplierRepo supplierRepo;
    private final SupplierViewConverter supplierViewConverter;
    private final MessageUtil messageUtil;

    public SupplierService(SupplierRepo supplierRepo, SupplierViewConverter supplierViewConverter, MessageUtil messageUtil) {
        this.supplierRepo = supplierRepo;
        this.supplierViewConverter = supplierViewConverter;
        this.messageUtil = messageUtil;
    }

    public Supplier findSupplierOrThrow(Long id) {
        return supplierRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("supplier.NotFound", id)));
    }

    public SupplierView getSupplier(Long id) {
        Supplier supplier = findSupplierOrThrow(id);
        return supplierViewConverter.convert(supplier);
    }

    public Page<SupplierView> findAllSupplier(Pageable pageable) {
        Page<Supplier> suppliers = supplierRepo.findAll(pageable);
        List<SupplierView> supplierViews = new ArrayList<>();
        suppliers.forEach(supplier -> {
            SupplierView supplierView = supplierViewConverter.convert(supplier);
            supplierViews.add(supplierView);
        });
        return new PageImpl<>(supplierViews, pageable, suppliers.getTotalElements());
    }

    public SupplierView create(SupplierBaseReq req) {
        Supplier supplier = new Supplier();
        this.prepare(supplier, req);
        Supplier supplierSave = supplierRepo.save(supplier);
        return supplierViewConverter.convert(supplierSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            supplierRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("supplier.NotFound", id));
        }
    }

    public SupplierView update(Supplier supplier, SupplierBaseReq req) {
        Supplier newSupplier = this.prepare(supplier,req);
        Supplier supplierSave = supplierRepo.save(newSupplier);
        return supplierViewConverter.convert(supplierSave);
    }

    public Supplier prepare(Supplier supplier, SupplierBaseReq supplierBaseReq){
        supplier.setPhone(supplierBaseReq.getPhone());
        supplier.setFactory(supplierBaseReq.getFactory());
        supplier.setEmail(supplierBaseReq.getEmail());
        supplier.setName(supplierBaseReq.getName());
        return supplier;
    }
}

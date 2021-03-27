package com.example.supermarket.core.supplier.web;

import com.example.supermarket.core.supplier.Supplier;
import com.example.supermarket.core.supplier.SupplierService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class SupplierController {
    private final SupplierService service;

    public SupplierController(SupplierService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    @ResponseBody
    public SupplierView getSupplier(@PathVariable Long id) {
        return service.getSupplier(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody
    public Page<SupplierView> getAllSupplier(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllSupplier(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SupplierView create(@RequestBody @Valid SupplierBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplier(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public SupplierView updateSupplier(@PathVariable(name = "id") Long id,
                                     @RequestBody @Valid SupplierBaseReq req) {
        Supplier supplier = service.findSupplierOrThrow(id);
        return service.update(supplier, req);
    }

}

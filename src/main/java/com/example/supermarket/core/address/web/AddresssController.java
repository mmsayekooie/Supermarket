package com.example.supermarket.core.address.web;

import com.example.supermarket.core.address.Addresss;
import com.example.supermarket.core.address.AddresssService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddresssController {
    private final AddresssService service;

    public AddresssController(AddresssService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public AddresssView getAddress(@PathVariable Long id) {
        return service.getAddresss(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody
    public Page<AddresssView> getAllAddress(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllAddresss(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public AddresssView create(@RequestBody @Valid AddresssBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AddresssView updateAddress(@PathVariable(name = "id") Long id,
                                      @RequestBody @Valid AddresssBaseReq req) {
        Addresss addresss = service.findAddresssOrThrow(id);
        return service.update(addresss, req);
    }
}


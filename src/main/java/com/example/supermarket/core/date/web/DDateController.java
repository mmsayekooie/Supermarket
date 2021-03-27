package com.example.supermarket.core.date.web;


import com.example.supermarket.core.date.DDate;
import com.example.supermarket.core.date.DDateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/date")
public class DDateController {
    private final DDateService service;

    public DDateController(DDateService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DDateView getDate(@PathVariable Long id) {
        return service.getDate(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody
    public Page<DDateView> getAllDate(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllDates(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DDateView create(@RequestBody @Valid DDateBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDate(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public DDateView updateDate(@PathVariable(name = "id") Long id,
                                       @RequestBody @Valid DDateBaseReq req) {
        DDate dDate = service.findDateOrThrow(id);
        return service.update(dDate, req);
    }
}

package com.example.supermarket.core.product.web;

import com.example.supermarket.core.product.Product;
import com.example.supermarket.core.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ProductView getProduct(@PathVariable Long id) {
        return service.getProduct(id);
    }

    @GetMapping({"", "/"})
    @ResponseBody
    public Page<ProductView> getAllProduct(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllProduct(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ProductView create(@RequestBody @Valid ProductBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ProductView updateProduct(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid ProductBaseReq req) {
        Product product = service.findProductOrThrow(id);
        return service.update(product, req);
    }
}

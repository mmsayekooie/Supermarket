package com.example.supermarket.core.product;

import com.example.supermarket.core.product.converter.ProductViewConverter;
import com.example.supermarket.core.product.web.ProductBaseReq;
import com.example.supermarket.core.product.web.ProductView;
import com.example.supermarket.core.supplier.Supplier;
import com.example.supermarket.core.supplier.SupplierRepo;
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
public class ProductService {
    private final ProductRepo productRepo;
    private final ProductViewConverter productViewConverter;
    private final SupplierRepo supplierRepo;
    private final MessageUtil messageUtil;

    public ProductService(ProductRepo productRepo, ProductViewConverter productViewConverter,SupplierRepo supplierRepo, MessageUtil messageUtil) {
        this.productRepo = productRepo;
        this.productViewConverter = productViewConverter;
        this.messageUtil = messageUtil;
        this.supplierRepo=supplierRepo;
    }

    public Product findProductOrThrow(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("product.NotFound", id)));
    }

    public ProductView getProduct(Long id) {
        Product product = findProductOrThrow(id);
        return productViewConverter.convert(product);
    }

    public Page<ProductView> findAllProduct(Pageable pageable) {
        Page<Product> products = productRepo.findAll(pageable);
        List<ProductView>  productViews = new ArrayList<>();
        products.forEach(product -> {
            ProductView productView = productViewConverter.convert(product);
            productViews.add(productView);
        });
        return new PageImpl<>(productViews, pageable, products.getTotalElements());
    }

    public ProductView create(ProductBaseReq req) {
        Product product = new Product();
        this.prepare(product, req);
        Product productSave = productRepo.save(product);
        return productViewConverter.convert(productSave);
    }

    @Transactional
    public void delete(Long id) {
        try {
            productRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("product.NotFound", id));
        }
    }

    public ProductView update(Product product, ProductBaseReq req) {
        Product newProduct = this.prepare(product,req);
        Product productSave = productRepo.save(newProduct);
        return productViewConverter.convert(productSave);
    }

    public Product prepare(Product product, ProductBaseReq productBaseReq){
        product.setProductType(productBaseReq.getProductType());
        product.setDescription(productBaseReq.getDescription());
        product.setDetail(productBaseReq.getDetail());
        product.setName(productBaseReq.getName());
        product.setPrice(productBaseReq.getPrice());
        Supplier supplier=supplierRepo.getOne(productBaseReq.getSupplierId());
        product.setSupplier(supplier);
        return product;
    }
}

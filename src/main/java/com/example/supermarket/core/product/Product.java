package com.example.supermarket.core.product;

import com.example.supermarket.core.supplier.Supplier;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "sp_products")
public class Product {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "sp_supplier_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "sp_supplier_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sp_supplier_id_seq")
    private long id;
    @Column(name = "productType")
    private String productType;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private String price;
    @Column(name = "description")
    private String description;
    @Column(name = "detail")
    private String detail;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_supplier")
    private Supplier supplier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}

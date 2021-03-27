package com.example.supermarket.core.order;

import com.example.supermarket.core.customer.Customer;
import com.example.supermarket.core.date.DDate;
import com.example.supermarket.core.product.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sp_order")
public class Order {
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
    @Column(name = "paidfor")
    private String paidfor;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "sp_order_item",
            joinColumns = { @JoinColumn(name = "id_order") },
            inverseJoinColumns = { @JoinColumn(name = "id_product") })
    private Set<Product> products = new HashSet<>();
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_customer")
    private Customer customer;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_Date")
    private DDate dDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPaidfor() {
        return paidfor;
    }

    public void setPaidfor(String paidfor) {
        this.paidfor = paidfor;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public DDate getdDate() {
        return dDate;
    }

    public void setdDate(DDate dDate) {
        this.dDate = dDate;
    }
}

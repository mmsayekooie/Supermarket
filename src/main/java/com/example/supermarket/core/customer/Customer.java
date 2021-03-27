package com.example.supermarket.core.customer;

import com.example.supermarket.core.address.Addresss;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sp_customer")
public class Customer {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "sp_customer_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "sp_customer_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sp_customer_id_seq")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_become_customer")
    private Date dateBecomeCustomer;
    @Column(name = "detail")
    private String detail;
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(name = "sp_customer_address",
            joinColumns = { @JoinColumn(name = "id_customer") },
            inverseJoinColumns = { @JoinColumn(name = "id_address") })
    private Set<Addresss> addressses = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDateBecomeCustomer() {
        return dateBecomeCustomer;
    }

    public void setDateBecomeCustomer(Date dateBecomeCustomer) {
        this.dateBecomeCustomer = dateBecomeCustomer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Set<Addresss> getAddressses() {
        return addressses;
    }

    public void setAddressses(Set<Addresss> addressses) {
        this.addressses = addressses;
    }
}

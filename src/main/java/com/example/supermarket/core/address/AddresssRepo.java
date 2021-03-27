package com.example.supermarket.core.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddresssRepo extends JpaRepository<Addresss, Long> {
}

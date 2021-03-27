package com.example.supermarket.core.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DDateRepo extends JpaRepository<DDate,Long>{
}

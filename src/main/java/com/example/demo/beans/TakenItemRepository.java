package com.example.demo.beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface TakenItemRepository extends JpaRepository<TakenItem, Long> {
    @Override
    Optional<TakenItem> findById(@Param("id") Long id);
    
}

package com.example.demo.beans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface DiskRepository extends JpaRepository<Disk, Long> {
    @Override
    Optional<Disk> findById(@Param("id") Long id);
    
}

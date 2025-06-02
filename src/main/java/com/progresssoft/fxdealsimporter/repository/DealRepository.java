package com.progresssoft.fxdealsimporter.repository;

import com.progresssoft.fxdealsimporter.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealRepository extends JpaRepository<Deal,String> {
}

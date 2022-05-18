package com.rm.c.dao;

import com.rm.c.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractDAO extends JpaRepository<Contract, Long> {
    
}

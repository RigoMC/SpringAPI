package com.rm.c.dao;

import com.rm.c.domain.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractTypeDAO extends JpaRepository<ContractType, Long> {
    
}

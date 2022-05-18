package com.rm.c.web;

import com.rm.c.domain.ContractType;
import com.rm.c.dao.ContractTypeDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ContractTypeController {
    
    @Autowired
    private ContractTypeDAO contractTypeDAO;
    
    public ContractTypeController(ContractTypeDAO contractTypeDAO) {
        this.contractTypeDAO = contractTypeDAO;
    }
    
    @GetMapping("/contractstypes")
    public List<ContractType> all() {
        return contractTypeDAO.findAll();
    }
}

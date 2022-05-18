package com.rm.c.web;

import com.rm.c.dao.ContractDAO;
import com.rm.c.domain.Contract;
import com.rm.c.domain.Employee;
import com.rm.c.service.ContractService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractController {
    
    @Autowired
    private final ContractDAO contractDAO;
     
    EntityManager em;
    
    @Autowired
    ContractService contractService;

    public ContractController(ContractDAO contractDAO,EntityManagerFactory emf) {
        em = emf.createEntityManager();
        this.contractDAO = contractDAO;
    }

    @GetMapping("/contracts")
    public List<Contract> all() {
        return contractDAO.findAll();
    }
    
    //TWO---------------------------------------------->
    @PutMapping("/contracts/add")
    public void addContract(Contract contract) {
        JSONObject responseList = new JSONObject();
        responseList.put("Success", "The Contract has been created successfully");
        String query = "SELECT * FROM Contract WHERE Employee_Id = "+contract.getEmployeeId()+" AND Is_Active = 1";
        
        if(!resultEmpty(query)){
            cancelContract(contract.getEmployeeId());
            responseList.put("Info", "The employee had an active contract, it was canceled and the new one was created");
        }
        contractService.addContract(contract);
        
    }
    
    //FIVE---------------------------------------------->
    @DeleteMapping("/contracts/delete")
    public JSONObject deletecontract(Employee employee){
        JSONObject responseList = new JSONObject();
        String query = "SELECT * FROM contract WHERE Employee_Id = "+employee.getEmployeeId();
        
        if(resultEmpty(query)){
            responseList.put("Error","The employee don't have an active contract");
            return responseList;
        }
        
        cancelContract(employee.getEmployeeId());
        responseList.put("Success","The contract has been deleted successfully");
            return responseList;
    }
    
    
    public void cancelContract(Long id){
        String query = "UPDATE Contract SET Is_Active = 0, Date_To = now() WHERE Employee_Id = "+id+" AND Is_Active = 1";
        em.createNativeQuery(query).executeUpdate();
    }
    
    public boolean resultEmpty(String query){
        List<Contract> res = em.createNativeQuery(query).getResultList();
        return res.isEmpty();
    }
    
    
}

package com.rm.c.web;

import com.rm.c.dao.EmployeeDAO;
import com.rm.c.domain.Contract;
import com.rm.c.domain.Employee;
import com.rm.c.service.EmployeeService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

@RestController
public class EmployeeController {
    
    EntityManager em;
    
    
    @Autowired
    private final EmployeeDAO employeeDAO;
    
    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeDAO employeeDAO,EntityManagerFactory emf) {
        em = emf.createEntityManager();
        this.employeeDAO = employeeDAO;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return employeeDAO.findAll();
    }
    
    //ONE---------------------------------------------->
    @GetMapping("/employees/contracts")
    public List<Object> getContractsList() {
        String query = """
                       select concat(e.Name,' ',e.Last_Name) as Name, e.Tax_Id_Number, e.email,
                       		IF(c.Is_Active = 1,ct.Name,null) as ContractName,
                               IF(c.Is_Active = 1,c.Date_From,null) as ContractStart,
                               IF(c.Is_Active = 1,c.Date_To,null) as ContractEnd,
                               IF(c.Is_Active = 1,c.Salary_Per_Day,null) as Salary
                       	from Employee e 
                           INNER JOIN Contract c ON c.Employee_Id=e.Employee_Id
                           INNER JOIN Contract_Type ct ON ct.Contract_Type_Id=c.Contract_Type_Id
                           WHERE e.Is_Active = 1;
                       """;
        return em.createNativeQuery(query).getResultList();
    }
    
    //THREE---------------------------------------------->
    @PutMapping("/employees/add")
    public JSONObject addEmployee(Employee employee){
        JSONObject responseList = new JSONObject();
        if(!employee.validateTaxIdNumber()){
            //error
            responseList.put("Error","The TaxIdNumber does not have the correct format");
            return responseList;
        }
        String query = "SELECT * FROM Employee WHERE Tax_Id_Number = "+employee.getTaxIdNumber();
        
        if(!resultEmpty(query)){
            responseList.put("Error","Employee already exists");
            return responseList;
        }
        
        employeeService.addEmployee(employee);
        responseList.put("Success","The employee has been added successfully");
        return responseList;
    }
    
    //FOUR---------------------------------------------->
    @PutMapping("/employees/update")
    public JSONObject updateEmployee(Employee employee){
        JSONObject responseList = new JSONObject();
        if(!employee.validateTaxIdNumber()){
            responseList.put("Error","The TaxIdNumber does not have the correct format");
            return responseList;
        }
        
        String query = "SELECT * FROM Employee WHERE Tax_Id_Number = "+employee.getTaxIdNumber();
        
        if(resultEmpty(query)){
            responseList.put("Error","Employee don't exists");
            return responseList;
        }
        employeeService.addEmployee(employee);
        responseList.put("Error","The employee has been edited successfully");
            return responseList;
    }
    
    public boolean resultEmpty(String query){
        List<Contract> res = em.createNativeQuery(query).getResultList();
        return res.isEmpty();
    }
}

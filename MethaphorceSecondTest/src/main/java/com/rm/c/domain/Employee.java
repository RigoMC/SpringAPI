package com.rm.c.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "Employee")
public class Employee implements Serializable{
    
    private static final long serialVersionUIS = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    
    @NotEmpty
    private String taxIdNumber;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String lastName;
    
    @NotEmpty
    private Date birthDate;
    
    @NotEmpty
    private String email;
    
    @NotEmpty
    private String cellPhone;
    
    @NotEmpty
    private boolean isActive;
    
    @NotEmpty
    private Date dateCreated;
    
    public boolean validateTaxIdNumber(){
        String rfcRegex = "/^([A-ZÑ&]{3,4}) ?(?:- ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:- ?)?([A-Z\\d]{2})([A\\d])$/";
        Pattern pattern = Pattern.compile(rfcRegex);
        Matcher match = pattern.matcher(this.taxIdNumber);
        return match.matches();
    }
}

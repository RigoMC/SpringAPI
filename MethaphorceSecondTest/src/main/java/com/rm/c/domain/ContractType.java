package com.rm.c.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "ContractType")
public class ContractType implements Serializable{
    
    private static final long serialVersionUIS = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractTypeId;
    
    @NotEmpty
    private String name;
    
    private String description;
    
    @NotEmpty
    private boolean isActive;
    
    @NotEmpty
    private Date dateCreated;
    
}

package com.rm.c.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "Contract")
public class Contract implements Serializable{
    private static final long serialVersionUIS = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;
    
    @NotEmpty
    private Long employeeId;
    
    @NotEmpty
    private Long contractTypeId;
    
    @NotEmpty
    private Date dateFrom;
    
    @NotEmpty
    private Date dateTo;
    
    @NotEmpty
    private float salaryPerDay;
    
    @NotEmpty
    private boolean isActive;
    
    @NotEmpty
    private Date dateCreated;
}

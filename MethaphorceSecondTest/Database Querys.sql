create database MethaphorceBd;
use MethaphorceBd;
create table Contract_Type ( Contract_Type_Id int auto_increment, Name varchar(80) not null, Description varchar(255), Is_Active boolean not null, Date_Created datetime not null , Constraint ContractTypePK primary key(Contract_Type_Id));
create table Employee ( Employee_Id int auto_increment, Tax_Id_Number varchar(13) not null, Name varchar(60) not null, 
						Last_Name varchar(120) not null, Birth_Date date not null, Email varchar(60) not null,Cell_Phone varchar(20) not null, 
                        Is_Active boolean not null, Date_Created datetime not null, constraint EmployeePK primary key (Employee_Id));
create table Contract (Contract_Id bigint auto_increment, Employee_Id int not null, Contract_Type_Id int not null, 
						Date_From datetime not null, Date_To datetime not null, Salary_Per_Day decimal not null, 
                        Is_Active boolean not null, Date_Created datetime not null, constraint ContractPK primary key (Contract_Id),
                        constraint ContractFkEmployee foreign key (Employee_Id) references Employee(Employee_Id),
                        constraint ContractFkContractType foreign key (Contract_Type_Id) references Contract_Type(Contract_Type_Id));
drop database MethaphorceBd;
show tables;
describe contract;
select * from contract_type;
select * from Employee;
select * from contract;

#Number 1 
select concat(e.Name,' ',e.Last_Name) as Name, e.Tax_Id_Number, e.email,
		IF(c.Is_Active = 1,ct.Name,null) as ContractName,
        IF(c.Is_Active = 1,c.Date_From,null) as ContractStart,
        IF(c.Is_Active = 1,c.Date_To,null) as ContractEnd,
        IF(c.Is_Active = 1,c.Salary_Per_Day,null) as Salary
	from Employee e 
    INNER JOIN Contract c ON c.Employee_Id=e.Employee_Id
    INNER JOIN Contract_Type ct ON ct.Contract_Type_Id=c.Contract_Type_Id
    WHERE e.Is_Active = 1;
    
#Number 2
SELECT * FROM Contract WHERE Employee_Id = 1;
UPDATE Contract SET Is_Active = 0, Date_To = now() WHERE Employee_Id = 1 AND Is_Active = 1;

#Number 3
SELECT * FROM Employee WHERE Tax_Id_Number = 123456;

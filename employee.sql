drop database if exists crudb;
create database cruddb;
\c cruddb

create table employee(id serial primary key,name varchar(20),dept varchar(20),salary decimal(10,2));

insert into employee(name,dept,salary) values('Om','IT',30000);
insert into employee(name,dept,salary) values('Vinayak','Sales',35000);
insert into employee(name,dept,salary) values('Rudresh','Finance',40000);

select * from employee;
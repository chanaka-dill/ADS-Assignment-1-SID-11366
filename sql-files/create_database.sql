create database office_db;


use office_db;


create table departments (
    dept_id int primary key auto_increment,
    dept_name varchar(100) not null
);


create table employees (
    emp_id int primary key auto_increment,
    emp_name varchar(100) not null,
    dept_id int,
    salary decimal(10, 2),
    email varchar(100) unique,
    city varchar(50) default 'Colombo',
    foreign key (dept_id) references departments(dept_id)
);


create table employee_audit (
    audit_id int primary key auto_increment,
    emp_id int,
    action_type varchar(10),
    action_time timestamp default current_timestamp,
    details text
);
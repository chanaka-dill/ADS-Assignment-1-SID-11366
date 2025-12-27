DELIMITER $$


create procedure get_all_employees()
begin
    select * from employees;
end$$


create procedure get_employee_by_id(IN id INT)
begin
    select * from employees where emp_id = id;
end$$


create procedure count_employees_by_dept(IN d_id INT, OUT cnt INT)
begin
    select COUNT(*) into cnt from employees where dept_id = d_id;
end$$


create procedure add_new_employee(
    in e_name varchar(100),
    in d_id int,
    in sal decimal(10, 2),
    in e_email varchar(100),
    in e_city varchar(50)
)
begin
    start transaction;
    insert into employees (emp_name, dept_id, salary, email, city)
    values (e_name, d_id, sal, e_email, e_city);
    commit;
end $$

DELIMITER ;

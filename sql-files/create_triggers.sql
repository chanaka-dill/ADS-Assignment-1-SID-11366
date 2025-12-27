DELIMITER $$

create trigger prevent_negative_salary
before insert on employees
for each row
begin
    if New.salary < 0 then
        signal sqlstate '45000'
        set message_text = 'Salary cannot be negative!';
    end if;
end$$

create trigger prevent_negative_salary_update
before update on employees
for each row
begin
    if New.salary < 0 then
        signal sqlstate '45000'
        set message_text = 'Salary cannot be negative!';
    end if;
end$$

create trigger audit_insert
after insert on employees
for each row
begin
    insert into employee_audit (emp_id, action_type, details)
    values (New.emp_id, 'INSERT', CONCAT('Inserted employee: ', New.emp_name, ', Salary: ', New.salary));
end$$

create trigger audit_update
after update on employees
for each row
begin
    insert into employee_audit (emp_id, action_type, details)
    values (NEW.emp_id, 'UPDATE', CONCAT('Updated employee: ', NEW.emp_name, ', Old Salary: ', OLD.salary, ', New Salary: ', NEW.salary));
end$$

create trigger audit_delete
after delete on employees
for each row
begin
    insert into employee_audit (emp_id, action_type, details)
    values (OLD.emp_id, 'DELETE', CONCAT('Deleted employee: ', OLD.emp_name, ', Salary: ', OLD.salary));
end$$

DELIMITER ;
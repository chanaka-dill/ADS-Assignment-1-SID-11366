insert into departments (dept_name) values ('HR'), ('Engineering'), ('Sales');

INSERT INTO employees (emp_name, dept_id, salary, email)
VALUES
('Chanaka Dilshan', 1, 50000.00, 'chanaka@email.com');

INSERT INTO employees (emp_name, dept_id, salary, email, city)
VALUES
('Yohan Rathnaweera', 2, 60000.00, 'yohan@email.com', 'Kandy');

INSERT INTO employees (emp_name, dept_id, salary, email)
VALUES
('Oshan Rajapaksha', 3, 55000.00, 'oshan@email.com');

-- test negative salary
INSERT INTO employees (emp_name, dept_id, salary, email) VALUES ('Bob Negative', 1, -1000.00, 'bob@example.com');

UPDATE employees SET salary = 65000.00 WHERE emp_id = 1;

DELETE FROM employees WHERE emp_id = 4;
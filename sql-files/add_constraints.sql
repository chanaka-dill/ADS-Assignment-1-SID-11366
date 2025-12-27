alter table employees
add constraint emp_name_not_null check (emp_name is not null);


alter table employees
add unique (email);


alter table employees
alter column city set default 'Colombo';


alter table employees
add foreign key (dept_id) references departments(dept_id);
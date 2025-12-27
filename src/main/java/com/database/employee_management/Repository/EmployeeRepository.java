package com.database.employee_management.Repository;

import com.database.employee_management.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    //get_all_employees() procedure
    @Procedure(procedureName = "get_all_employees")
    List<Employee> getAllEmployeesViaProcedure();

    //get_employee_by_id(id) procedure
    @Procedure(procedureName = "get_employee_by_id")
    Employee getEmployeeByEmpIdViaProcedure(@Param("id") int id);
}
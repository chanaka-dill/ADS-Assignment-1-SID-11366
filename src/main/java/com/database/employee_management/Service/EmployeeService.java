package com.database.employee_management.Service;

import com.database.employee_management.Dto.EmployeeRequest;
import com.database.employee_management.Entity.Employee;
import com.database.employee_management.Entity.Department;
import com.database.employee_management.Repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    //get all employees via stored procedure
    @Transactional
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployeesViaProcedure();
    }

    //get employee by id via a stored procedure
    @Transactional
    public Employee getEmployeeById(int id) {
        return employeeRepository.getEmployeeByEmpIdViaProcedure(id);
    }

    //count employees by department via stored procedure
    public int countEmployeesByDepartment(int deptId) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("count_employees_by_dept");
        query.registerStoredProcedureParameter("d_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("cnt", Integer.class, ParameterMode.OUT);
        query.setParameter("d_id", deptId);
        query.execute();
        return (Integer) query.getOutputParameterValue("cnt");
    }

    //add new employee via stored procedure
    @Transactional
    public String addEmployee(EmployeeRequest request) {
        if (request == null || request.getDeptId() == null) {
            throw new IllegalArgumentException("deptId is required");
        }
        // Execute a stored procedure directly here
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("add_new_employee");
        query.registerStoredProcedureParameter("e_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("d_id", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("sal", BigDecimal.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("e_email", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("e_city", String.class, ParameterMode.IN);

        query.setParameter("e_name", request.getEmpName());
        query.setParameter("d_id", request.getDeptId());
        query.setParameter("sal", request.getSalary());
        query.setParameter("e_email", request.getEmail());
        query.setParameter("e_city", request.getCity());

        query.execute();
        return "Employee added";
    }

    //update employee details
    @Transactional
    public Employee updateEmployee(int id, EmployeeRequest request) {
        Employee existing = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        if (request != null) {
            if (request.getEmpName() != null) {
                existing.setEmpName(request.getEmpName());
            }
            if (request.getDeptId() != null) {
                Department dept = Department.builder().deptId(request.getDeptId()).build();
                existing.setDepartment(dept);
            }
            if (request.getSalary() != null) {
                existing.setSalary(request.getSalary());
            }
            if (request.getEmail() != null) {
                existing.setEmail(request.getEmail());
            }
            if (request.getCity() != null) {
                existing.setCity(request.getCity());
            }
        }
        return employeeRepository.save(existing);
    }

    //delete employee
    @Transactional
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
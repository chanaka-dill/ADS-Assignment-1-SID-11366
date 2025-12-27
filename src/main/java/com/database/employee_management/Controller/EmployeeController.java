package com.database.employee_management.Controller;

import com.database.employee_management.Dto.EmployeeRequest;
import com.database.employee_management.Entity.Employee;
import com.database.employee_management.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // GET all employees via stored procedure
    @GetMapping("/get-all")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // GET employee by ID via stored procedure
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    //COUNT employees by department via stored procedure
    @GetMapping("/count-by-department/{deptId}")
    public ResponseEntity<Integer> countByDept(@PathVariable int deptId) {
        int count = employeeService.countEmployeesByDepartment(deptId);
        return ResponseEntity.ok(count);
    }

    //POST new employee via stored procedure
    @PostMapping("/add")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeRequest request) {
        try {
            String res = employeeService.addEmployee(request);
            return ResponseEntity.ok(res);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //PUT update employee
    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable int id, @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    //DELETE employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted");
    }
}
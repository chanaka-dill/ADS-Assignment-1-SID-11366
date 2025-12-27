package com.database.employee_management.Controller;

import com.database.employee_management.Dto.DepartmentRequest;
import com.database.employee_management.Entity.Department;
import com.database.employee_management.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    //add a new department
    @PostMapping("/add")
    public Department addDepartment(@RequestBody DepartmentRequest request) {
        return departmentService.addDepartment(request);
    }

    //update department
    @PutMapping("/update/{id}")
    public Department updateDepartment(@PathVariable int id, @RequestBody DepartmentRequest request) {
        return departmentService.updateDepartment(id, request);
    }

    //delete department
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted");
    }

    //GET all departments
    @GetMapping("/get-all")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    //GET department by ID
    @GetMapping("/get-by-id/{id}")
    public Department getDepartmentById(@PathVariable int id) {
        return departmentService.getDepartmentById(id);
    }
}
package com.database.employee_management.Service;

import com.database.employee_management.Dto.DepartmentRequest;
import com.database.employee_management.Entity.Department;
import com.database.employee_management.Repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    // add a new department
    @Transactional
    public Department addDepartment(DepartmentRequest request) {

        if (request == null || request.getDeptName() == null) {
            throw new IllegalArgumentException("deptName is required");
        }
        Department department = Department.builder().deptName(request.getDeptName()).build();
        return departmentRepository.save(department);
    }

    // update department
    @Transactional
    public Department updateDepartment(int id, DepartmentRequest request) {
        if (request == null || request.getDeptName() == null) {
            throw new IllegalArgumentException("deptName is required");
        }
        Department existing = getDepartmentById(id);
        existing.setDeptName(request.getDeptName());
        return departmentRepository.save(existing);
    }

    // DELETE department
    @Transactional
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    // GET all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // GET department by ID
    public Department getDepartmentById(int id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department not found"));
    }
}
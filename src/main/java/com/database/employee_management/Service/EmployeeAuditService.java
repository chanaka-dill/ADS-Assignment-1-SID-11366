package com.database.employee_management.Service;

import com.database.employee_management.Entity.EmployeeAudit;
import com.database.employee_management.Repository.EmployeeAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeAuditService {

    private final EmployeeAuditRepository auditRepository;

    // GET all audits
    public List<EmployeeAudit> getAllAudits() {
        return auditRepository.findAll();
    }

    // GET audit by ID
    public EmployeeAudit getAuditById(int id) {
        return auditRepository.findById(id).orElseThrow(() -> new RuntimeException("Audit not found"));
    }

    // GET audits by employee ID
    public List<EmployeeAudit> getAuditsByEmployeeId(int empId) {
        return auditRepository.findByEmpId(empId);
    }
}
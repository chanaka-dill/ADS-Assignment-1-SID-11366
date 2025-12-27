package com.database.employee_management.Controller;

import com.database.employee_management.Entity.EmployeeAudit;
import com.database.employee_management.Service.EmployeeAuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class EmployeeAuditController {

    private final EmployeeAuditService auditService;

    // GET all audits
    @GetMapping("/get-all")
    public List<EmployeeAudit> getAllAudits() {
        return auditService.getAllAudits();
    }

    // GET audit by ID
    @GetMapping("/get-by-id/{id}")
    public EmployeeAudit getAuditById(@PathVariable int id) {
        return auditService.getAuditById(id);
    }

    // GET audits by employee ID
    @GetMapping("/get-by-employee/{empId}")
    public List<EmployeeAudit> getAuditsByEmployeeId(@PathVariable int empId) {
        return auditService.getAuditsByEmployeeId(empId);
    }
}
package com.database.employee_management.Repository;

import com.database.employee_management.Entity.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAuditRepository extends JpaRepository<EmployeeAudit, Integer> {

    List<EmployeeAudit> findByEmpId(int empId);
}

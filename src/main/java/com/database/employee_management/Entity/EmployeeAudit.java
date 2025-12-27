package com.database.employee_management.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_audit")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private int id;

    @Column(name = "emp_id")
    private int empId;

    @Column(name = "action_type")
    private String actionType;

    @Column(name = "action_time")
    private LocalDateTime actionTime;

    @Column(name = "details")
    private String details;
}
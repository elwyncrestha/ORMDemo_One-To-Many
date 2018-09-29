package com.elvin.model;

import javax.persistence.*;

@Entity
@Table(name = "department_tbl")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "departmentId", nullable = false)
    private int departmentId;

    @Column(name = "departmentName", nullable = false)
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "collegeId", nullable = false)
    private College college;

    public Department(String departmentName, College college) {
        this.departmentName = departmentName;
        this.college = college;
    }

    public Department(int departmentId, String departmentName) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Department() {
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public College getCollege() {
        return college;
    }

    public void setCollege(College college) {
        this.college = college;
    }
}

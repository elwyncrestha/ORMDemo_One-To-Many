package com.elvin.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "college_tbl")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "collegeId", nullable = false)
    private int collegeId;

    @Column(name = "collegeName", nullable = false)
    private String collegeName;

    @OneToMany(mappedBy = "college")
    private Set<Department> departments;

    public College(int collegeId, String collegeName, Set<Department> departments) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.departments = departments;
    }

    public College(int collegeId, String collegeName) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
    }

    public College(String collegeName) {
        this.collegeName = collegeName;
    }

    public College() {
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }
}

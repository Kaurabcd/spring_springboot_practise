package com.beans.dto;
import java.time.LocalDate;

public class EmployeeDTO {
    Long id;
    private String name;
    private String email;
    private Integer age;
    private String department;
    private LocalDate joiningDate;
    boolean isActive;

    public void setId(Long id) {
        this.id = id;
    }

    public EmployeeDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public EmployeeDTO(Long id, String name, String email, Integer age, String department, LocalDate joiningDate, boolean isActive) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.department = department;
        this.joiningDate = joiningDate;
        this.isActive = isActive;
    }
}
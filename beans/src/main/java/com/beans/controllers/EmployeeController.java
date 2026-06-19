    package com.beans.controllers;

    import org.springframework.web.bind.annotation.*;
    import com.beans.dto.EmployeeDTO;

    import java.time.LocalDate;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {

        @GetMapping("/{employeeId}")
        public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
            EmployeeDTO employeeDTO = new EmployeeDTO(id,"Kiran","kkaur", 25,"IT", LocalDate.of(2026,9,12),true);
            return employeeDTO;
        }

        @GetMapping()
        public String getEmployeeParams(@RequestParam String sortBy, @RequestParam(name="inputAge") Integer age){
            return "Employee param is : " + sortBy + " age is : " + age;
        }

        @PostMapping()
        public EmployeeDTO addEmployee(@RequestBody EmployeeDTO inputEmployee){
                inputEmployee.setId(100L);
                return inputEmployee;
            }
        }
    

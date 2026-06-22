    package com.beans.controllers;

    import com.beans.entity.EmployeeEntity;
    import com.beans.repository.EmployeeRepository;
    import com.beans.services.EmployeeService;
    import org.springframework.web.bind.annotation.*;
    import com.beans.dto.EmployeeDTO;
    import java.time.LocalDate;
    import java.util.List;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {

        private final EmployeeService employeeService;
        public EmployeeController(EmployeeService employeeService){
            this.employeeService = employeeService;
        }

        @GetMapping("/{employeeId}")
        public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
           return employeeService.getEmployeeById(id);
        }

        @PostMapping()
        public EmployeeDTO addEmployee(@RequestBody EmployeeDTO inputEmployee){
            return employeeService.addEmployee(inputEmployee);
            }

        @GetMapping()
        public List<EmployeeDTO> getAllEmployees(){
            return employeeService.getAllEmployees();
        }


        //        @GetMapping()
//        public String getEmployeeParams(@RequestParam String sortBy, @RequestParam(name="inputAge") Integer age){
//            return "Employee param is : " + sortBy + " age is : " + age;
//        }
        }


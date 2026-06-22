    package com.beans.controllers;

    import com.beans.entity.EmployeeEntity;
    import com.beans.repository.EmployeeRepository;
    import org.springframework.web.bind.annotation.*;
    import com.beans.dto.EmployeeDTO;

    import java.time.LocalDate;
    import java.util.List;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {

        private final EmployeeRepository employeeRepository;
        public EmployeeController(EmployeeRepository employeeRepository){
            this.employeeRepository = employeeRepository;
        }
        @GetMapping("/{employeeId}")
        public EmployeeDTO getEmployeeById(@PathVariable(name="employeeId") Long id){
            EmployeeDTO employeeDTO = new EmployeeDTO(id,"Kiran","kkaur", 25,"IT", LocalDate.of(2026,9,12),true);
            return employeeDTO;
        }

//        @GetMapping()
//        public String getEmployeeParams(@RequestParam String sortBy, @RequestParam(name="inputAge") Integer age){
//            return "Employee param is : " + sortBy + " age is : " + age;
//        }

        @PostMapping()
        public EmployeeEntity addEmployee(@RequestBody EmployeeEntity inputEmployee){
                return employeeRepository.save(inputEmployee);
            }

        @GetMapping()
        public List<EmployeeEntity> getAllEmployees(){
            return employeeRepository.findAll();
        }
        }


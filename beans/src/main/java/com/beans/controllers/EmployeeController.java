    package com.beans.controllers;

    import com.beans.entity.EmployeeEntity;
    import com.beans.exceptions.ResourceNotFoundException;
    import com.beans.repository.EmployeeRepository;
    import com.beans.services.EmployeeService;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import com.beans.dto.EmployeeDTO;
    import java.time.LocalDate;
    import java.util.List;
    import java.util.Map;
    import java.util.NoSuchElementException;
    import java.util.Optional;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {

        private final EmployeeService employeeService;
        public EmployeeController(EmployeeService employeeService){
            this.employeeService = employeeService;
        }

        @GetMapping("/{employeeId}")
        public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="employeeId") Long id){
           Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
//           if(employeeDTO.isPresent()){
//               return ResponseEntity.ok(employeeDTO.get());
//           }
//           return ResponseEntity.notFound().build();
//            return employeeDTO
//                    .map(emp->ResponseEntity.ok(emp))
//                    .orElseThrow(()->new NoSuchElementException("Not found"));

            return employeeDTO
                    .map(emp->ResponseEntity.ok(emp))
                    .orElseThrow(()->new ResourceNotFoundException("EMployee not found with id"+ id));
        }

//        @ExceptionHandler(NoSuchElementException.class)
//        public ResponseEntity<String> resourceNotFound(){
//            return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
//        }

        @PostMapping()
        public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO inputEmployee){
            return ResponseEntity.status(201).body(employeeService.addEmployee(inputEmployee));
            }

        @GetMapping()
        public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

            return ResponseEntity.ok(employeeService.getAllEmployees());
        }

        @PutMapping(path = "/{employeeId}")
        public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO){
            return ResponseEntity.ok(employeeService.updateEmployeeById(employeeId, employeeDTO));
        }

        @DeleteMapping(path ="/{employeeId}")
        public ResponseEntity<Void> deleteEmployeeById(@PathVariable Long employeeId){
            if(employeeService.deleteEmployeeById(employeeId)) return ResponseEntity.noContent().build();
            return ResponseEntity.notFound().build();

        }

        @PatchMapping(path = "/{employeeId}")
        public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates, @PathVariable Long employeeId){
            return ResponseEntity.ok(employeeService.updatePartialEmployeeById(employeeId,updates));
        }

        //        @GetMapping()
//        public String getEmployeeParams(@RequestParam String sortBy, @RequestParam(name="inputAge") Integer age){
//            return "Employee param is : " + sortBy + " age is : " + age;
//        }
        }


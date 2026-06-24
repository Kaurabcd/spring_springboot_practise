package com.beans.services;

import com.beans.dto.EmployeeDTO;
import com.beans.entity.EmployeeEntity;
import com.beans.repository.EmployeeRepository;
import org.apache.el.util.ReflectionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;
    public final ModelMapper modelMapper;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper){
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){
        EmployeeEntity toSaveDto = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity toReturnEntity = employeeRepository.save(toSaveDto);
        return modelMapper.map(toReturnEntity,EmployeeDTO.class);
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return employeeEntity.map(emp -> modelMapper.map(emp, EmployeeDTO.class));

    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO){
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);  //worked as hashmap if id already exist then update otherwise add new data
        return  modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean deleteEmployeeById (Long id){
        boolean ifExists = employeeRepository.existsById(id);
        if(!ifExists) return false;
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updates){
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElseThrow(()->new RuntimeException(("Employee not found")));
        updates.forEach((fieldName,value)->{

            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, fieldName);
            if(fieldToBeUpdated != null) {
                fieldToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
            }
        });
        employeeRepository.save(employeeEntity);
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

}

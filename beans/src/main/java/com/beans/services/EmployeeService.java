package com.beans.services;

import com.beans.dto.EmployeeDTO;
import com.beans.entity.EmployeeEntity;
import com.beans.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    public final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }


    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO){
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity toSaveDto = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity toReturnEntity = employeeRepository.save(toSaveDto);
        return modelMapper.map(toReturnEntity,EmployeeDTO.class);
    }

    public EmployeeDTO getEmployeeById(Long id){
        ModelMapper modelMapper = new ModelMapper();
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        return modelMapper.map(employeeEntity,EmployeeDTO.class);

    }

}

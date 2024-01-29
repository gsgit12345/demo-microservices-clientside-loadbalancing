package com.springboot.demo.service;

import com.springboot.demo.entity.Employee;
import com.springboot.demo.repository.EmployeeRepo;
import com.springboot.demo.response.AddressResponse;
import com.springboot.demo.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public EmployeeResponse getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        // Get ServiceInstance list using serviceId
        ServiceInstance serviceInstance = loadBalancerClient.choose("ADDRESS-SERVICE");

        // Read URI and Add path that returns url
        String uri = serviceInstance.getUri().toString();

        // Get metadata
        String contextPath = serviceInstance.getMetadata().get("configPath");

        // Make HTTP call and get Response data
        AddressResponse addressResponse = restTemplate.getForObject(uri + contextPath + "/address/{id}", AddressResponse.class, id);

        employeeResponse.setAddressResponse(addressResponse);
        return employeeResponse;
    }

}

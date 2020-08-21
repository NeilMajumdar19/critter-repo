package com.udacity.jdnd.c3.critter.user;

import com.udacity.jdnd.c3.critter.entity.Customer;
import com.udacity.jdnd.c3.critter.entity.Employee;
import com.udacity.jdnd.c3.critter.entity.Pet;
import com.udacity.jdnd.c3.critter.pet.PetDTO;
import com.udacity.jdnd.c3.critter.service.CustomerService;
import com.udacity.jdnd.c3.critter.service.EmployeeService;
import com.udacity.jdnd.c3.critter.service.PetService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PetService petService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        List<Pet> pets = new ArrayList<>();
        for(Long petId : customerDTO.getPetIds())
            pets.add(petService.getPetById(petId));
        customer.setPets(pets);
        return customerDTO;

    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return convertCustomersToCustomerDTOs(customerService.getCustomers());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        return convertCustomerToCustomerDTO(petService.getPetById(petId).getOwner());
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO)
    {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeService.saveEmployee(employee);
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return convertEmployeeToEmployeeDTO(employeeService.getEmployeeById(employeeId));

    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        employeeService.setAvailability(daysAvailable, employee);
    }

    //get Employees by Days Available, use LocalDate.getDay()
    //make list of employees without skills and remove from list, loop through all skills
    //given by EmployeeRequest
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private static CustomerDTO convertCustomerToCustomerDTO(Customer customer)
    {
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private static List<CustomerDTO> convertCustomersToCustomerDTOs(List<Customer> customers)
    {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(Customer c : customers)
            customerDTOS.add(convertCustomerToCustomerDTO(c));
        return customerDTOS;
    }

    private static EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee)
    {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

}

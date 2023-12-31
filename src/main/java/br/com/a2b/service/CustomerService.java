package br.com.a2b.service;

import java.util.ArrayList;
import java.util.List;

import br.com.a2b.dto.CustomerDTO;
import br.com.a2b.entity.CustomerEntity;
import br.com.a2b.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CustomerService {
	
	@Inject
	private CustomerRepository customerRepository;
	
	public List<CustomerDTO> findAllCustomers(){
		List<CustomerDTO> customers = new ArrayList<>();
		
		customerRepository.findAll().stream().forEach(item -> {
			customers.add(mapCustomerEntityToCustomerDTO(item));
		});

		return customers;
	}

	public void createNewCustomer(CustomerDTO customerDTO) {

		customerRepository.persist(mapCustomerDtoTocCustomerEntity(customerDTO));

	}

	public void changeCustomer(Long id, CustomerDTO customerDTO) {

		CustomerEntity customerEntity = customerRepository.findById(id);
		customerEntity = mapCustomerDtoTocCustomerEntity(customerDTO);

		customerRepository.persist(customerEntity);


	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}
	
	private CustomerDTO mapCustomerEntityToCustomerDTO(CustomerEntity customerEntity) {

		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setAddress(customerEntity.getAddress());
		customerDTO.setAge(customerEntity.getAge());
		customerDTO.setEmail(customerEntity.getEmail());
		customerDTO.setName(customerEntity.getName());
		customerDTO.setPhone(customerEntity.getPhone());

		return customerDTO;
	}

	private CustomerEntity mapCustomerDtoTocCustomerEntity(CustomerDTO customerDTO) {

		CustomerEntity customerEntity = new CustomerEntity();

		customerEntity.setAddress(customerDTO.getAddress());
		customerEntity.setAge(customerDTO.getAge());
		customerEntity.setEmail(customerDTO.getEmail());
		customerEntity.setName(customerDTO.getName());
		customerEntity.setPhone(customerDTO.getPhone());

		return customerEntity;
	}
	
	

}

package xam.cross.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Customer;
import xam.cross.entity.Role;
import xam.cross.entity.ShoppingCart;
import xam.cross.repository.CustomerRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired ShoppingCartService cartService;
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}

	public Customer findOne(int id) {
		return customerRepository.findOne(id);
	}

	public Customer addToDb(Customer customer){
		Set<Role> roles = new HashSet<Role>();
		Role role = roleService.findByName("ROLE_USER");
		roles.add(role);
		customer.setRoles(roles);
		customer.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		customer.setPassword(encoder.encode(customer.getPassword()));
		ShoppingCart cart = new ShoppingCart();
		cart = cartService.save(cart);
		customer.setCart(cart);
		save(customer);
		return customer;
	}
	
	public void save(Customer customer) {
		customerRepository.save(customer);	
	}

	public Customer findOneWithBooks(String username) {
		Customer customer = customerRepository.findByEmail(username);
		return customer;
	}

	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
}

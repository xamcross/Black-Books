package xam.cross.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Book;
import xam.cross.entity.Customer;
import xam.cross.entity.Role;
import xam.cross.entity.ShoppingCart;
import xam.cross.repository.BookRepository;
import xam.cross.repository.CustomerRepository;
import xam.cross.repository.ShoppingCartRepository;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired ShoppingCartService cartService;
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}

	public Customer findOne(int id) {
		return customerRepository.findOne(id);
	}

	public Customer findOneWithBooks(int id) {
		Customer customer = customerRepository.findOne(id);
		ShoppingCart cart = cartRepository.findByCustomer(customer);
		System.out.println("cart = " + cart);
		List<Book> books = bookRepository.findByCarts(cart);
		System.out.println("books = " + books);
		cart.setOrderedBooks(books);
		customer.setCart(cart);
		return customer;
	}

	
	public void save(Customer customer) {
		List<Role> roles = new ArrayList<Role>();
		Role role = roleService.findByName("ROLE_USER");
		roles.add(role);
		customer.setRoles(roles);
		customer.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		customer.setPassword(encoder.encode(customer.getPassword()));
		customerRepository.save(customer);
		ShoppingCart cart = new ShoppingCart();
		cart.setCustomer(customer);
		cartService.save(cart);
	}

	public Customer findOneWithBooks(String username) {
		Customer customer = customerRepository.findByEmail(username);
		return findOneWithBooks(customer.getId());
	}

	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
}

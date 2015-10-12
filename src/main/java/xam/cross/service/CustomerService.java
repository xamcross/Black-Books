package xam.cross.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Book;
import xam.cross.entity.Customer;
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
	
	public List<Customer> findAll(){
		return customerRepository.findAll();
	}

	public Customer findOne(int id) {
		return customerRepository.findOne(id);
	}

	@Transactional
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
		customerRepository.save(customer);
	}
	
}

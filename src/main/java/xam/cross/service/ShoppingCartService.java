package xam.cross.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Customer;
import xam.cross.entity.ShoppingCart;
import xam.cross.repository.BookRepository;
import xam.cross.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private ShoppingCartRepository cartRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired BookService bookService;

	public void save(ShoppingCart cart) {
		cartRepository.save(cart);
	}

	public ShoppingCart findOne(String email) {
		Customer customer = customerService.findByEmail(email);
		return cartRepository.findByCustomer(customer);
	}

	public void removeOneBook(int cartId, int bookId) {
		ShoppingCart cart = cartRepository.findOne(cartId);
		cart.getOrderedBooks().remove(bookService.findOne(bookId));
		cartRepository.save(cart);
	}


}

package xam.cross.service;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

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
import xam.cross.repository.RoleRepository;
import xam.cross.repository.ShoppingCartRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired BookRepository bookRepository;

	
	@PostConstruct
	public void init(){
		ShoppingCart adminCart = new ShoppingCart();
		
		
		Book harryPotter = new Book();
		harryPotter.setAuthors("Joan Rowling");
		harryPotter.setPrice(3.99);
		harryPotter.setDescription("You are a wizard, Harry");
		harryPotter.setTitle("Harry Potter");
		
		harryPotter = bookRepository.save(harryPotter);
		
		Book mobyDick = new Book();
		mobyDick.setAuthors("Herman Melville");
		mobyDick.setPrice(4.99);
		mobyDick.setDescription("Pale mammal gets hunted by a crazy old man");
		mobyDick.setTitle("Moby Dick");

		mobyDick = bookRepository.save(mobyDick);
		
		adminCart.addBook(harryPotter);
		adminCart.addBook(mobyDick);
		adminCart = shoppingCartRepository.save(adminCart);
		
		
		Role roleUser = new Role("ROLE_USER");
		roleUser = roleRepository.save(roleUser);
		
		Role roleAdmin = new Role("ROLE_ADMIN");
		roleAdmin = roleRepository.save(roleAdmin);
		
		Set <Role> roles = new HashSet<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		
		Customer admin = new Customer();
		admin.setEmail("admin@blackbooks.com");
		admin.setEnabled(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		admin.setPassword(encoder.encode("admin"));
		admin.setRoles(roles);
		admin.setCart(adminCart);
		customerRepository.save(admin);

	}
	
}

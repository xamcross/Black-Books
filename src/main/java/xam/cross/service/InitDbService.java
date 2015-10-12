package xam.cross.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private BookRepository bookRepository;
	
	@PostConstruct
	public void init(){
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		Customer admin = new Customer();
		admin.setEmail("admin@blackbooks.com");
		admin.setPassword("admin");
		List <Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
		roles.add(roleAdmin);
		admin.setRoles(roles);
		customerRepository.save(admin);
		
		ShoppingCart adminCart = new ShoppingCart();
		adminCart.setCustomer(admin);
		shoppingCartRepository.save(adminCart);
		
		Book harryPotter = new Book();
		harryPotter.setAuthors("Joan Rowling");
		harryPotter.setPrice(3.99);
		harryPotter.setDescription("You are a wizard, Harry");
		harryPotter.setTitle("Harry Potter");
		List<ShoppingCart> carts1 = new ArrayList<ShoppingCart>();
		carts1.add(adminCart);
		harryPotter.setCarts(carts1);
		bookRepository.save(harryPotter);
		
		Book mobyDick = new Book();
		mobyDick.setAuthors("Herman Melville");
		mobyDick.setPrice(4.99);
		mobyDick.setDescription("Hunted pale mammal");
		mobyDick.setTitle("Moby Dick");
		List<ShoppingCart> carts2 = new ArrayList<ShoppingCart>();
		carts2.add(adminCart);
		harryPotter.setCarts(carts2);
		bookRepository.save(mobyDick);
		
		
		
	}
	
}
package xam.cross.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xam.cross.entity.Customer;
import xam.cross.entity.Role;
import xam.cross.entity.ShoppingCart;
import xam.cross.service.CustomerService;
import xam.cross.service.RoleService;
import xam.cross.service.ShoppingCartService;

@Controller
public class CustomerController {

	@RequestMapping(value="/customers")
	public String customers(Model model){	
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@ModelAttribute("customer")
	@Transactional
	public Customer construct(){
		Customer customer = new Customer();
		List<Role> roles = new ArrayList<Role>();
		Role role = roleService.findByName("ROLE_USER");
		roles.add(role);
		customer.setRoles(roles);
		return customer;
	}
	
	
	@RequestMapping(value="/customers/{id}")
	public String customerInfo(@PathVariable int id, Model model){
		Customer customer = customerService.findOneWithBooks(id);
		model.addAttribute("customer", customer);
		model.addAttribute("cart", customer.getCart());
		return "customer-info";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(Model model){
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute("customer") Customer customer){
		customerService.save(customer);
		ShoppingCart cart = new ShoppingCart();
		cart.setCustomer(customer);
		cartService.save(cart);
		return "index";
	}
	
}
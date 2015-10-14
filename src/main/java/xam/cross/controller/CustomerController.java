package xam.cross.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xam.cross.entity.Customer;
import xam.cross.service.CustomerService;

@Controller
public class CustomerController {

	@RequestMapping(value="/customers")
	public String customers(Model model){
		model.addAttribute("customers", customerService.findAll());
		return "customers";
	}
	
	@Autowired
	private CustomerService customerService;
	
	@ModelAttribute("customer")
	@Transactional
	public Customer construct(){
		Customer customer = new Customer();
		return customer;
	}
	
	
	@RequestMapping(value="/customers/{id}")
	public String customerInfo(@PathVariable int id, Model model){
		Customer customer = customerService.findOne(id);
		model.addAttribute("customer", customer);
		model.addAttribute("cart", customer.getCart());
		return "customer-info";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm(){
		return "registration";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute("customer") Customer customer){
		customerService.addToDb(customer);
		return "redirect:register.html?success=true";
	}
	
	@RequestMapping(value="/cabinet")
	public String cabinet(Model model, Principal principal){
		String username = principal.getName();
		Customer customer = customerService.findOneWithBooks(username);
		model.addAttribute("customer", customer);
		model.addAttribute("cart", customer.getCart());
		return "cabinet";
	}
	
}
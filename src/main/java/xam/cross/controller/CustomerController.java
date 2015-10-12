package xam.cross.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	
	@RequestMapping(value="/customers/{id}")
	public String customerInfo(@PathVariable int id, Model model){
		Customer customer = customerService.findOneWithBooks(id);
		model.addAttribute("customer", customer);
		model.addAttribute("cart", customer.getCart());
		return "customer-info";
	}
}
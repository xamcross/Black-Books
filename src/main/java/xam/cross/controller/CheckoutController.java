package xam.cross.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xam.cross.entity.Customer;
import xam.cross.entity.ShoppingCart;
import xam.cross.service.CustomerService;
import xam.cross.service.ShoppingCartService;

@Controller
@Transactional
public class CheckoutController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@RequestMapping(value="/checkout", method=RequestMethod.GET)
	public String checkout(Model model, Principal principal){
		String email = principal.getName();
		Customer customer = customerService.findOneWithBooks(email);
		ShoppingCart cart = customer.getCart();
		model.addAttribute("cart", cart);
		return "checkout";
	}
	
	@RequestMapping(value="/checkout/{id}", method=RequestMethod.GET)
	public String checkoutRemove(@PathVariable("id") int id, Model model, Principal principal){
		String email = principal.getName();
		Customer customer = customerService.findOneWithBooks(email);
		ShoppingCart cart = customer.getCart();
		cartService.removeOneBook(cart.getId(), id);
		model.addAttribute("cart", cart);
		return "redirect:/checkout";
	}
}

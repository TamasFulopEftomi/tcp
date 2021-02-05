package com.eftomi.tcp.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.eftomi.tcp.dto.ItemNumberSetDTO;
import com.eftomi.tcp.dto.LoginDTO;
import com.eftomi.tcp.dto.RegistrationDTO;
import com.eftomi.tcp.entity.CargoItem;
import com.eftomi.tcp.entity.User;
import com.eftomi.tcp.service.CargoService;
import com.eftomi.tcp.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		model.addAttribute("registrationDTO", new RegistrationDTO());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(LoginDTO loginDTO, HttpSession session, Model model) {
		boolean loggedIn = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
		if (loggedIn) {
			Optional<User> optUser = userService.getUser(loginDTO.getEmail());
			if (optUser.isPresent()) {
				session.setAttribute("username", optUser.get().getName());
			} else {
				// exception handling
			}
			return "redirect:/index";
		} else {
			model.addAttribute("loginError", String.format("Email address %s or it's password is not valid!", loginDTO.getEmail()));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/registration")
	public String registration(RegistrationDTO registrationDTO, HttpSession session, Model model) {
		boolean registeredIn = userService.registration(registrationDTO.getName(), registrationDTO.getEmail(), registrationDTO.getPassword());
		if (registeredIn) {
			session.setAttribute("username", registrationDTO.getName());
			return "redirect:/index";
		} else {
			model.addAttribute("registrationError", String.format("(s)", "Az email létezik vagy nem felel meg az előírásoknak."));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "login";
		}
	}
	
	@GetMapping("/index")
	public String index(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("stock_nav", false);
		model.addAttribute("deliveryNoteCreateNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		
		return "index";
	}
	
	@GetMapping("/packagingInstruction")
	public String packagingInstruction(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingInstructionNav", true);
		model.addAttribute("deliveryNoteCreateSelectNav", false);
		model.addAttribute("deliveryNoteCreateQuantity", false);
		model.addAttribute("modifyQuantityNav", false);
		
		model.addAttribute("packagingInstruction", cargoService.packagingInstruction());
		return "index";
	}
	
	@GetMapping("/deliveryNoteCreate")
	public String deliveryNoteCreate(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateSelectNav", true);
		model.addAttribute("deliveryNoteCreateQuantity", false);
		model.addAttribute("modifyQuantityNav", false);
		
		cargoService.clearCargoItem();
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "index";
	}
	
	@PostMapping("/deliveryNoteCreateSelect")
	public String deliveryNoteCreateSelect(Model model, ItemNumberSetDTO itemNumberSetDTO, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateSelectNav", true);
		model.addAttribute("deliveryNoteCreateQuantity", false);
		model.addAttribute("modifyQuantityNav", false);
		
		session.setAttribute("itemNumberSetDTO", itemNumberSetDTO);
		String list = itemNumberSetDTO.getItemNumberSet().stream()
				.map(n -> String.valueOf(n))
				.collect(Collectors.joining(", "));
		list = list.equals("") ? null : list;
		model.addAttribute("list", list);
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "index";
	}
	
	@PostMapping("/deliveryNoteCreateQuantity")
	public String deliveryNoteCreateQuantity(Model model, HttpSession session ) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateSelectNav", false);
		model.addAttribute("deliveryNoteCreateQuantityNav", true);
		model.addAttribute("modifyQuantityNav", false);
		
		ItemNumberSetDTO itemNumberSetDTO = (ItemNumberSetDTO) session.getAttribute("itemNumberSetDTO");
		cargoService.createDeliveryNote(itemNumberSetDTO.getItemNumberSet());
		Iterable<CargoItem> deliveryNote = cargoService.getAllCargoItems();
		model.addAttribute("deliveryNote", deliveryNote);
		return "index";
	}
	
	@PostMapping("/modifyQuantity")
	public String modifyQuantity(Model model, int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateSelectNav", false);
		model.addAttribute("deliveryNoteCreateQuantityNav", false);
		model.addAttribute("modifyQuantityNav", true);
		
		Optional<CargoItem> optCargoItem = cargoService.getCargoItem(id);
		if (optCargoItem.isPresent()) {
			CargoItem cargoItem = optCargoItem.get();
				model.addAttribute("cargoItem", cargoItem);
				return "index";
			} else {
		return null;
			}
	}
	
	@PostMapping("/modifyCargoItem")
	public String modifyCargoItem(Model model, HttpSession session, CargoItem cargoItem) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateSelectNav", false);
		model.addAttribute("deliveryNoteCreateQuantityNav", true);
		model.addAttribute("modifyQuantityNav", false);
		
		cargoService.calculateCargoItemQuantities(cargoItem);
		Iterable<CargoItem> deliveryNote = cargoService.getAllCargoItems();
		model.addAttribute("deliveryNote", deliveryNote);
		return "index";
		
	}
	
}

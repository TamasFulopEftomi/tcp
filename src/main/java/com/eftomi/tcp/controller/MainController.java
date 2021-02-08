package com.eftomi.tcp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.eftomi.tcp.dto.CargoListDTO;
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
	public String indx(Model model) {
		model.addAttribute("loginDTO", new LoginDTO());
		model.addAttribute("registrationDTO", new RegistrationDTO());
		return "index";
	}
	
	@PostMapping("/index")
	public String index(LoginDTO loginDTO, HttpSession session, Model model) {
		boolean loggedIn = userService.login(loginDTO.getEmail(), loginDTO.getPassword());
		if (loggedIn) {
			Optional<User> optUser = userService.getUser(loginDTO.getEmail());
			if (optUser.isPresent()) {
				session.setAttribute("username", optUser.get().getName());
			} else {
				// exception handling
			}
			return "redirect:/display";
		} else {
			model.addAttribute("loginError", String.format("Email address %s or it's password is not valid!", loginDTO.getEmail()));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "index";
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
			return "redirect:/display";
		} else {
			model.addAttribute("registrationError", String.format("(s)", "Az email létezik vagy nem felel meg az előírásoknak."));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "index";
		}
	}
	
	@GetMapping("/display")
	public String display(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		return "display";
	}
	
	@GetMapping("/packagingAndShippingPolicy")
	public String packagingAndShippingPolicy(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", true);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		return "display";
	}
	
	@GetMapping("/packagingInstruction")
	public String packagingInstruction(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", true);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		model.addAttribute("packagingInstruction", cargoService.packagingInstruction());
		return "display";
	}
	
	@GetMapping("/emptiesList")
	public String emptiesList(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", true);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		model.addAttribute("emptiesList", cargoService.emptiesList());
		return "display";
	}
	
	@GetMapping("/createCargoList")
	public String createCargoList(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", true);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		cargoService.clearCargoItem();
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "display";
	}
	
	@PostMapping("/createCargoListSelect")
	public String createCargoListSelect(Model model, ItemNumberSetDTO itemNumberSetDTO, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", true);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", false);
		
		session.setAttribute("itemNumberSetDTO", itemNumberSetDTO);
		String list = itemNumberSetDTO.getItemNumberSet().stream()
				.map(n -> String.valueOf(n))
				.collect(Collectors.joining(", "));
		list = list.equals("") ? null : list;
		model.addAttribute("list", list);
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "display";
	}
	
	@PostMapping("/createCargoListQuantity")
	public String createCargoListQuantity(Model model, HttpSession session ) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", true);
		model.addAttribute("modifyQuantityNav", false);
		
		ItemNumberSetDTO itemNumberSetDTO = (ItemNumberSetDTO) session.getAttribute("itemNumberSetDTO");
		cargoService.cargoListCreate(itemNumberSetDTO.getItemNumberSet());
		List<CargoItem> cargoItemList = cargoService.getAllCargoItems();
		CargoListDTO cargoListDTO = cargoService.calculateCargo(cargoItemList);
		model.addAttribute("cargoItemList", cargoItemList);
		model.addAttribute("cargoListDTO", cargoListDTO);
		return "display";
	}
	
	@PostMapping("/modifyQuantity")
	public String modifyQuantity(Model model, int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", false);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", false);
		model.addAttribute("modifyQuantityNav", true);
		
		Optional<CargoItem> optCargoItem = cargoService.getCargoItem(id);
		if (optCargoItem.isPresent()) {
			CargoItem cargoItem = optCargoItem.get();
				model.addAttribute("cargoItem", cargoItem);
				return "display";
			} else {
		return null;
			}
	}
	
	@PostMapping("/modifyCargoItem")
	public String modifyCargoItem(Model model, HttpSession session, CargoItem cargoItem) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingAndShippingPolicyNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("emptiesListNav", false);
		model.addAttribute("createCargoListSelectNav", false);
		model.addAttribute("createCargoListMainNav", true);
		model.addAttribute("modifyQuantityNav", false);
		
		cargoService.calculateCargoItemQuantities(cargoItem);
		List<CargoItem> cargoItemList = cargoService.getAllCargoItems();
		CargoListDTO cargoListDTO = cargoService.calculateCargo(cargoItemList);
		model.addAttribute("cargoItemList", cargoItemList);
		model.addAttribute("cargoListDTO", cargoListDTO);
		return "display";
		
	}
	
}

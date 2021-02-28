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

import com.eftomi.tcp.dto.CargoItemDTO;
import com.eftomi.tcp.dto.CargoListDTO;
import com.eftomi.tcp.dto.DisplaySectionDTO;
import com.eftomi.tcp.dto.ItemNumberSetDTO;
import com.eftomi.tcp.dto.LoginDTO;
import com.eftomi.tcp.dto.RegistrationDTO;
import com.eftomi.tcp.entity.CargoItem;
import com.eftomi.tcp.entity.User;
import com.eftomi.tcp.service.CargoService;
import com.eftomi.tcp.service.UserService;
import com.eftomi.tcp.service.exception.UserNotFoundException;
import com.sun.el.parser.ParseException;

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
				throw new UserNotFoundException("N/A");
			}
			return "redirect:/display";
		} else {
			model.addAttribute("loginError", String.format("Email address %s or password is incorrect!", loginDTO.getEmail()));
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
			model.addAttribute("registrationError", String.format("Email address %s already exists in the database or it does not meet the email "
					+ "address format requirements or the name or the password field length shorter than 3 character!", registrationDTO.getEmail()));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "index";
		}
	}
	
	@GetMapping("/display")
	public String display(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setMenuNav(true);
		displaySectionDTO.setAboutNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		return "display";
	}
	
	@GetMapping("/specifications")
	public String specifications(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setSpecificationsNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		return "display";
	}
	
	@GetMapping("/packagingInstruction")
	public String packagingInstruction(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setPackagingInstructionNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		model.addAttribute("packagingInstruction", cargoService.packagingInstruction());
		return "display";
	}
	
	@GetMapping("/emptiesList")
	public String emptiesList(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setEmptiesListNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		model.addAttribute("boxesAndContainersList", cargoService.boxesAndContainersList());
		model.addAttribute("palletsAndLidsList", cargoService.palletsAndLidsList());
		return "display";
	}
	
	@GetMapping("/createCargoList")
	public String createCargoList(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setCreateCargoListSelectNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);	
		
		cargoService.clearCargoItem();
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		session.setAttribute("qtyError", null);
		return "display";
	}
	
	@PostMapping("/createCargoListSelect")
	public String createCargoListSelect(Model model, ItemNumberSetDTO itemNumberSetDTO, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setCreateCargoListSelectNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
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
	public String createCargoListQuantity(HttpSession session) {
		ItemNumberSetDTO itemNumberSetDTO = (ItemNumberSetDTO) session.getAttribute("itemNumberSetDTO");
		cargoService.cargoListCreate(itemNumberSetDTO.getItemNumberSet());
		return "redirect:/cargoListPlanner";
	}
	
	@GetMapping("/cargoListPlanner")
	public String cargoListPlanner(Model model, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setMenuNav(true);
		displaySectionDTO.setCreateCargoListMainNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		List<CargoItem> cargoItemList = cargoService.getAllCargoItems();
		CargoListDTO cargoListDTO = cargoService.calculateCargo(cargoItemList);
		model.addAttribute("cargoItemList", cargoItemList);
		model.addAttribute("cargoListDTO", cargoListDTO);
		model.addAttribute("qtyError", session.getAttribute("qtyError"));
		return "display";
	}
	
	@PostMapping("/modifyQuantity")
	public String modifyQuantity(Model model, int id, HttpSession session) {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		
		DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
		displaySectionDTO.setModifyQuantityNav(true);
		model.addAttribute("displaySectionDTO", displaySectionDTO);
		
		Optional<CargoItem> optCargoItem = cargoService.getCargoItem(id);
		if (optCargoItem.isPresent()) {
			CargoItem cargoItem = optCargoItem.get();
			CargoItemDTO cargoItemDTO = new CargoItemDTO();
			cargoItemDTO.setId(cargoItem.getId());
			cargoItemDTO.setItemNumber(cargoItem.getItemNumber());
			cargoItemDTO.setQtyNeeds(cargoItem.getQtyNeeds());
			cargoItemDTO.setQtyToBeDelivered(cargoItem.getQtyToBeDelivered());
			cargoItemDTO.setQtyNeedsString(cargoItem.getQtyNeeds() + "");
				model.addAttribute("cargoItemDTO", cargoItemDTO);
				return "display";
			} else {
				return "/";
			}
	}
	
	@PostMapping("/modifyCargoItem")
	public String modifyCargoItem(Model model, HttpSession session, CargoItemDTO cargoItemDTO) throws ParseException {
		String username = (String) session.getAttribute("username");
		model.addAttribute("username", username);
		if (cargoService.validQuantity(cargoItemDTO.getQtyNeedsString())) {
			CargoItem cargoItem = new CargoItem();
			cargoItem.setId(cargoItemDTO.getId());
			cargoItem.setItemNumber(cargoItemDTO.getItemNumber());
			cargoItem.setQtyNeeds(cargoService.parseQuantity(cargoItemDTO.getQtyNeedsString()));
			cargoItem.setQtyToBeDelivered(cargoItemDTO.getQtyToBeDelivered());
			
			DisplaySectionDTO displaySectionDTO = new DisplaySectionDTO();
			displaySectionDTO.setMenuNav(true);
			displaySectionDTO.setCreateCargoListMainNav(true);
			model.addAttribute("displaySectionDTO", displaySectionDTO);
			
			cargoService.calculateCargoItemQuantities(cargoItem);
			List<CargoItem> cargoItemList = cargoService.getAllCargoItems();
			CargoListDTO cargoListDTO = cargoService.calculateCargo(cargoItemList);
			model.addAttribute("cargoItemList", cargoItemList);
			model.addAttribute("cargoListDTO", cargoListDTO);
			session.setAttribute("qtyError", null);
			return "display";
		} else {
			session.setAttribute("qtyError", "Please enter a positive integer on the form on the Change Requested Quantity page!");
			return "redirect:/cargoListPlanner";
		}
		
	}
	
}

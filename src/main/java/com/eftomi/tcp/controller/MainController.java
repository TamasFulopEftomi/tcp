package com.eftomi.tcp.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eftomi.tcp.dto.ItemNumberSetDTO;
import com.eftomi.tcp.dto.LoginDTO;
import com.eftomi.tcp.dto.RegistrationDTO;
import com.eftomi.tcp.entity.Box;
import com.eftomi.tcp.entity.Item;
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
			session.setAttribute("email", loginDTO.getEmail());
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
			session.setAttribute("email", registrationDTO.getEmail());
			return "redirect:/index";
		} else {
			model.addAttribute("registrationError", String.format("(s)", "Az email létezik vagy nem felel meg az előírásoknak."));
			model.addAttribute("loginDTO", new LoginDTO());
			model.addAttribute("registrationDTO", new RegistrationDTO());
			return "login";
		}
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("stock_nav", false);
		model.addAttribute("deliveryNoteCreateNav", false);
		return "index";
	}
	
	
	@ResponseBody
	@GetMapping("/print")
	public String print() {
		return "Helló Belló";
	}
	
	@GetMapping("/packagingInstruction")
	public String packagingInstruction(Model model) {
		model.addAttribute("menuNav", true);
		model.addAttribute("packagingInstructionNav", true);
		model.addAttribute("deliveryNoteCreateNav", false);	
		
		model.addAttribute("packagingInstruction", cargoService.packagingInstruction());
		return "index";
	}
	
	@GetMapping("/deliveryNoteCreate")
	public String deliveryNoteCreate(Model model) {
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateNav", true);		
		
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "index";
	}
	
	@PostMapping("/deliveryNoteCreate")
	public String deliveryNoteCreate(Model model, ItemNumberSetDTO itemNumberSetDTO) {
		model.addAttribute("menunNav", false);
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateNav", true);		
		
		
		String list = itemNumberSetDTO.getItemNumberSet().stream()
				.map(n -> String.valueOf(n))
				.collect(Collectors.joining(", "));
		model.addAttribute("list", list);
		model.addAttribute("itemMap", cargoService.getItemNumberMap());
		model.addAttribute("itemNumberSetDTO", new ItemNumberSetDTO());
		return "index";
	}
	
	@GetMapping("/other_nav")
	public String other(Model model) {
		model.addAttribute("packagingInstructionNav", false);
		model.addAttribute("deliveryNoteCreateNav", false);
		return "index";
	}

}

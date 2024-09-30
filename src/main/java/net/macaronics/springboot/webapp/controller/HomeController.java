package net.macaronics.springboot.webapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping(value = { "", "/" })
	public String home(@AuthenticationPrincipal UserDetails userDetails, HttpSession session) {
		if (userDetails == null || userDetails.getUsername() == null) {
			return "redirect:/user/login";
		}
		session.setAttribute("name", userDetails.getUsername());
		return "home";
	}

}

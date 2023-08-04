package com.identicum.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {
	
	private static final Logger logger = LoggerFactory.getLogger(DefaultController.class);

	@GetMapping({"/", "/home"})
	public String home() {
		return "/home";
	}

	@GetMapping("/user")
	public String user(Model model, @AuthenticationPrincipal OidcUser principal ) {
		try {
			logger.debug("Principal: " + new ObjectMapper().writeValueAsString(principal));
		} catch(JsonProcessingException jpe) {
			logger.error("Error found: " + jpe.getMessage());
		}
		model.addAttribute("name", principal.getName());
		model.addAttribute("claims", principal.getClaims());
		return "/user";
	}

	@GetMapping("/about")
	public String about() {
		return "/about";
	}

}

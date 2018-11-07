package com.apap.tutorial08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.apap.tutorial08.model.PasswordModel;
import com.apap.tutorial08.model.UserRoleModel;
import com.apap.tutorial08.service.UserRoleService;

@Controller
@RequestMapping ("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping (value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, RedirectAttributes redirectAttributes) {
		String cekText = "";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (user.getPassword().matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}")) {
			userService.addUser(user);
			cekText = "User berhasil ditambahkan!";
		}
		else {
			cekText = "Error! password minimal 8 karakter, mengandung angka dan huruf";
		}
		redirectAttributes.addFlashAttribute("cekText", cekText);
		return ("redirect:/");
		
	}
	
	@RequestMapping (value = "/updatePass")
	private String updatePass () {
		return "updatePass";
	}
	
	@RequestMapping (value = "/updatePass", method = RequestMethod.POST)
	public String updatePassSubmit (@ModelAttribute PasswordModel passObjek, RedirectAttributes redirectAttributes) {
		String param = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserRoleModel user = userService.getUser(auth.getName());
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passObjek.getPassKonf().equals(passObjek.getPassBaru())) {
			if (passwordEncoder.matches(passObjek.getPassLama(), user.getPassword())) {
				if(passObjek.getPassBaru().matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}")) {
					userService.updatePassUser(user, passObjek.getPassBaru());
					param = "Berhasil update password!";
				}
				else {
					param = "Error! password minimal 8 karakter, mengandung angka dan huruf";
				}
			}
			else {
				param = "Error! password lama tidak sesuai";
			}
		}
		else {
			param = "Error! password baru dan konfirmasi password baru tidak sesuai";
		}
		redirectAttributes.addFlashAttribute("cekText",param);
		return ("redirect:/user/updatePass");			
	}
}


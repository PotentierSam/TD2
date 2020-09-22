package s4.spring.td2.controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.td2.models.Organization;
import s4.spring.td2.models.User;
import s4.spring.td2.repositories.OrgaRepository;
import s4.spring.td2.repositories.UserRepository;
@Controller
public class TestController {
	@Autowired
	private OrgaRepository repo;
	
	@Autowired
	private UserRepository urepo;
	
	@RequestMapping("users/new/{name}/{orgaName}")
	public @ResponseBody String  addUserInOrga(@PathVariable String name,@PathVariable String orgaName) {
		Optional<Organization> opt=repo.findByName(orgaName);
		if(opt.isPresent()) {
			User user=new User();
			user.setFirstname(name);
			user.setOrganization(opt.get());
			urepo.saveAndFlush(user);
			return user +" ajouté";
		}
		return "Organisation"+orgaName+"est inexistant";
	}
}

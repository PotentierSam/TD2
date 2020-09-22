package s4.spring.td2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.td2.models.Organization;
import s4.spring.td2.repositories.OrgaRepository;

@Controller
public class OrgaController {
	
	@Autowired
	private OrgaRepository orgaRepo;
	@RequestMapping("/orgas")
	public String index(ModelMap model) {
		List<Organization> orgas=orgaRepo.findAll();
		model.put("orgas", orgas);
		return "index";
	}
	
	@RequestMapping("/orga/new")
	public String orgaNew() {
		return "organew";
	}
	
	@PostMapping("/orga/new")
	public RedirectView addNew(@RequestParam String name,@RequestParam String domain,@RequestParam String aliases) {
		Organization orga=new Organization();
		if(name!=null) {
			orga.setName(name);
			orga.setDomain(domain);
			orga.setAliases(aliases);
			orgaRepo.saveAndFlush(orga);
			
		}
		return new RedirectView("/orgas");
	}
	
	@RequestMapping("orga/{id}")
	public @ResponseBody String getOrga(@PathVariable int id) {
		Optional<Organization> opt=orgaRepo.findById(id);
		if(opt.isPresent()) {
			return opt.get()+"";
		}
		return "Organisation non trouvé";
	}
	
	@RequestMapping("orgas/delete/{id}")
	public @ResponseBody RedirectView deleteOrga(@PathVariable int id) {
		Optional<Organization> opt=orgaRepo.findById(id); 
		orgaRepo.delete(opt.get());
		return new RedirectView("/orgas");
	}
}

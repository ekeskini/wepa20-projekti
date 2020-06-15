/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.Account.Account;
import wepa20.Skill.Recommendation;
import wepa20.Skill.Skill;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class ProfileController {
    @Autowired
    private ProfileBuilderService profBuilder;
    
    @GetMapping("/profile/{username}")
    public String profileView(@PathVariable String username, Model model) {
        Account currentprofileaccount = profBuilder.getAccountByUsername(username);
        String currentuser = SecurityContextHolder
                .getContext().getAuthentication().getName();
        model.addAttribute("currentprofileusername", username);
        model.addAttribute("currentuser", currentuser);
        model.addAttribute("currentprofileskills", currentprofileaccount.getSkills());
        return "profilepage";
    }
    @PostMapping("/profile/{username}/{skillid}")
    public String postRecommendation(@PathVariable String username, 
            @PathVariable Long skillid, @RequestParam String recommendationdescription) {
        String currentusername = SecurityContextHolder
                .getContext().getAuthentication().getName();
        Account currentuser = profBuilder.getAccountByUsername(currentusername);
        Recommendation newrecommendation = 
                new Recommendation(recommendationdescription, profBuilder.getSkillById(skillid), currentuser);
        profBuilder.saveNewRecommendation(newrecommendation);
        return "redirect:/profile/" + username;
    }
    
    @PostMapping("/profile/postskill")
    public String postSkill(@RequestParam String skilldescription) {
        String currentusername = SecurityContextHolder
                .getContext().getAuthentication().getName();
        Account currentuser = profBuilder.getAccountByUsername(currentusername);
        Skill newskill = new Skill(skilldescription, currentuser, new ArrayList<>());
        profBuilder.saveNewSkill(newskill);
        return "redirect:/profile/" + currentusername;
    }
}

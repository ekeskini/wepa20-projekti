/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.controllers;

import com.google.common.io.Files;
import java.io.IOException;
import static java.lang.System.out;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wepa20.entities.Account;
import wepa20.services.ProfileBuilderService;
import wepa20.entities.Recommendation;
import wepa20.entities.Skill;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class ProfileController {
    @Autowired
    private ProfileBuilderService profBuilder;
    
    @GetMapping("/user")
    public String redirectToOwnProfile() {
        return "redirect:/user/" + SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @GetMapping("/user/{username}")
    public String profileView(@PathVariable String username, Model model) {
        Account currentprofileaccount = profBuilder.getAccountByUsername(username);
        
        String currentuser = SecurityContextHolder
                .getContext().getAuthentication().getName();
        
        model.addAttribute("currentuser", currentuser);       
        model.addAttribute("currentprofileaccount", currentprofileaccount);
        
        model.addAttribute("top3skills", profBuilder.getTopSkills(currentprofileaccount));
        
        List<Skill> otherSkills = profBuilder.getSkillsOrdered(currentprofileaccount);
        
        if (otherSkills.size() > 3) {
            model.addAttribute("otherskills", otherSkills.subList(3, otherSkills.size() - 1));
        }
        return "profilepage";
    }
    @PostMapping("/user/{username}/{skillid}")
    public String postRecommendation(@PathVariable String username, 
            @PathVariable Long skillid, @RequestParam String recommendationdescription) {
        
        String currentusername = SecurityContextHolder
            .getContext().getAuthentication().getName();
        
        //Check that user is not recommending themselves
        if (currentusername.equals(username)) {
            return "redirect:/user/" + username;
        }          
        Account currentuser = profBuilder.getAccountByUsername(currentusername);
        Skill s = profBuilder.getSkillById(skillid);
        Recommendation newrecommendation = 
                new Recommendation(recommendationdescription, s, currentuser);
        profBuilder.saveNewRecommendation(s, newrecommendation);
        return "redirect:/user/" + username;
    }
    
    @PostMapping("/user/postskill")
    public String postSkill(@RequestParam String skilldescription) {
        String currentusername = SecurityContextHolder
                .getContext().getAuthentication().getName();
        Account currentuser = profBuilder.getAccountByUsername(currentusername);
        Skill newskill = new Skill(skilldescription, currentuser, new ArrayList<>(), 0);
        profBuilder.saveNewSkill(newskill);
        return "redirect:/user/" + currentusername;
    }
    
    @GetMapping(path = "/user/{username}/profilepic", produces="image/*")
    @ResponseBody
    public byte[] getProfilePic(@PathVariable String username) throws IOException {
        byte[] profilepic = profBuilder.getAccountByUsername(username).getProfilePic();
        
        if (profilepic.length == 0) {
            
            //clumsy solution to the placeholder - no time to implemented through thymeleaf :)
            return Files.toByteArray(Paths.get("./src/main/resources/public/img/placeholder.jpg").toFile());
        }
        return profilepic;
    }
}

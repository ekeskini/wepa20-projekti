/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.controllers;

import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.entities.Account;
import wepa20.repositories.AccountRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class SearchController {
    @Autowired
    private AccountRepository accountRepository;
    
    @Transactional
    @GetMapping("/search")
    public String search(Model model, @RequestParam String term) {      
        
        if (term.isEmpty()) {
            model.addAttribute("accounts", 
                    accountRepository.findAll(Sort.by(Sort.Direction.ASC, "username")));
            return "searchresults";
        }
        //Removing possible @ sign in search beginning
        if (term.charAt(0) == '@') {
            term = term.substring(1);
        }
        
        model.addAttribute("accounts", accountRepository.findByUsernameContaining(term));
        return "searchresults";
        
    }
    
}

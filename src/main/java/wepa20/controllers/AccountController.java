/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.controllers;

import java.util.ArrayList;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa20.entities.Account;
import wepa20.entities.AccountConnectionManager;
import wepa20.repositories.AccountConnectionManagerRepository;
import wepa20.repositories.AccountRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller

public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConnectionManagerRepository acmRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String loginView() {
        return "login";
    }
    @GetMapping("/registration")
    public String registrationView() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String createAccount(@RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String username, @RequestParam String password) {
        try {
            Account acc = new Account();
            acc.setFirstName(firstName);
            acc.setLastName(lastName);
            acc.setUsername(username);       
            acc.setPassword(passwordEncoder.encode(password));
            acc.setProfilePic(new byte[0]);           
            accountRepository.save(acc);
            AccountConnectionManager acm = 
                new AccountConnectionManager(acc, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            acc.setConnectionManager(acm);
            acmRepository.save(acm);        
            //clumsy due to lack of accountservice (to be fixed)
            accountRepository.save(acc);
        } catch (Exception e) {
            return "redirect:/registration?error";
        }
        return "redirect:/";
    }
}

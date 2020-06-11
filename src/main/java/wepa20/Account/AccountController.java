/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/registration")
    public String registrationView() {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String createAccount(@RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String username, @RequestParam String password) {
        Account acc = new Account();
        acc.setFirstName(firstName);
        acc.setLastName(lastName);
        acc.setUsername(username);
        acc.setPassword(passwordEncoder.encode(password));
        accountRepository.save(acc);
        return "redirect:/";
    }
}

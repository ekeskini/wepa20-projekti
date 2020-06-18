/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.controllers;

import java.io.IOException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wepa20.entities.Account;
import wepa20.repositories.AccountRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class SettingsController {
    @Autowired
    public AccountRepository accountRepository;
    @GetMapping("/settings")
    public String settingsView() {
        return "settings";
    }
    @PostMapping("/settings/setprofilepic")
    @Transactional
    public String add(@RequestParam("file") MultipartFile file) throws IOException {
        
        if (!(file.getContentType().matches("image/...") || file.getContentType().matches("image/...."))
                || file.getBytes().length > 100000) {
            
            return "redirect:/settings";
        }
        Account a = accountRepository.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName());
        
        a.setProfilePic(file.getBytes());
        return "redirect:/settings";
    }
}

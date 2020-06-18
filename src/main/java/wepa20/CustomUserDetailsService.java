/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import wepa20.entities.Account;
import wepa20.repositories.AccountRepository;

/**
 *
 * @author Elias Keski-Nisula
 */

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private AccountRepository accountrepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountrepository.findByUsername(username);
        
        if (account == null) {
            throw new UsernameNotFoundException("No such user: " + username);
        }
        
        return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
        true,
        true,
        true,
        true,
        Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
    
}

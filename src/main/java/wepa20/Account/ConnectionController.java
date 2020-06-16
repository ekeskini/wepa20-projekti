/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.Account;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Elias Keski-Nisula
 */
@Controller
public class ConnectionController {
    @Autowired
    private ConnectionBuilderService connectionBuilder;
    
    @GetMapping("/connections")
    public String connectionView(Model model) {
        AccountConnectionManager currentuserACM = connectionBuilder.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()).getConnectionManager();
        
        model.addAttribute("receivedrequests", currentuserACM.getReceivedRequests());
        model.addAttribute("sentrequests", currentuserACM.getSentRequests());
        System.out.println(currentuserACM.getSentRequests().size());
        model.addAttribute("acceptedconnections", currentuserACM.getAcceptedConnections());
        return "connections";
    }
    @PostMapping("/{username}/requestconnection")
    public String requestConnection(@PathVariable String username) {
        AccountConnectionManager sender = connectionBuilder.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()).getConnectionManager();
        AccountConnectionManager receiver = connectionBuilder.findByUsername(username).getConnectionManager();
        //one should not be able to request a connection to themselves
        if(sender.equals(receiver)) {
            return "redirec:/connections";
        }
        ConnectionRequest newrequest = new ConnectionRequest(sender, receiver);
        
        connectionBuilder.saveNewRequest(newrequest);
        return "redirect:/connections";
    }
    @PostMapping("/user/{requestid}/acceptrequest")
    public String acceptConnection(@PathVariable Long requestid) {
        AccountConnectionManager receiver = connectionBuilder.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName()).getConnectionManager();
        List<ConnectionRequest> yourReceivedRequests = receiver.getReceivedRequests();
        ConnectionRequest requestToBeAccepted = connectionBuilder.getRequestById(requestid);
        
        //check if the request to be accepted belongs to the current user
        if (!yourReceivedRequests.contains(requestToBeAccepted)) {
            return "redirect:/connections";
        }
        
        connectionBuilder.acceptRequest(requestToBeAccepted);
        return "redirect:/connections";
    }
    @PostMapping("/connections/{username}/remove")
    public String removeConnection(@PathVariable String username) {
        String currentusername = SecurityContextHolder.getContext().getAuthentication().getName();
        
        connectionBuilder.removeConnection(currentusername, username);
        
        return "redirect:/connections";
    }
}

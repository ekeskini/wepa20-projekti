/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.services;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wepa20.entities.Account;
import wepa20.entities.AccountConnectionManager;
import wepa20.repositories.AccountConnectionManagerRepository;
import wepa20.repositories.AccountRepository;
import wepa20.entities.ConnectionRequest;
import wepa20.repositories.ConnectionRequestRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
@Service
@Transactional
public class ConnectionBuilderService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountConnectionManagerRepository acmRepository;
    @Autowired
    private ConnectionRequestRepository requestRepository;

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void saveNewRequest(ConnectionRequest newrequest) {
        requestRepository.save(newrequest);
    }
    
    public AccountConnectionManager getACM(String username) {
        return this.findByUsername(username).getConnectionManager();
    }
    
    public ConnectionRequest getRequestById(Long id) {
        return requestRepository.getOne(id);
    }
    public void acceptRequest(ConnectionRequest r) {
        AccountConnectionManager receiver = r.getReceiver();
        AccountConnectionManager sender = r.getSender();
        
        sender.addAcceptedConnection(receiver.getAccount().getUsername());
        receiver.addAcceptedConnection(sender.getAccount().getUsername());
        
        requestRepository.delete(r);
    }
    public void declineRequest(ConnectionRequest r) {
        requestRepository.delete(r);
    }
    public void removeConnection(String currentusername, String connection) {
        AccountConnectionManager yourACM = this.findByUsername(currentusername).getConnectionManager();
        AccountConnectionManager otherACM = this.findByUsername(connection).getConnectionManager();
        
        yourACM.getAcceptedConnections().remove(connection);
        otherACM.getAcceptedConnections().remove(currentusername);
    }
    
    public List<AccountConnectionManager> getSentRequestACM(AccountConnectionManager acm) {
        List<AccountConnectionManager> usernames = acm.getSentRequests()
                .stream()
                .map(r -> r.getReceiver())
                .collect(Collectors.toList());
        return usernames;
    } 
    
    public List<AccountConnectionManager> gerReceivedRequestACM(AccountConnectionManager acm) {
        List<AccountConnectionManager> usernames = acm.getReceivedRequests()
                .stream()
                .map(r -> r.getSender())
                .collect(Collectors.toList());
        return usernames;
    }
    
}

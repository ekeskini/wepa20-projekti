/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Elias Keski-Nisula
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountConnectionManager extends AbstractPersistable<Long>{
    @OneToOne
    private Account account;
    
    @ElementCollection
    private List<String> acceptedConnections = new ArrayList<>();
    
    @OneToMany(mappedBy="receiver")
    private List<ConnectionRequest> receivedRequests;
    @OneToMany(mappedBy="sender")
    private List<ConnectionRequest> sentRequests;
    
    @Transactional
    public void addAcceptedConnection(String username) {
        this.acceptedConnections.add(username);
    }
    
    
}

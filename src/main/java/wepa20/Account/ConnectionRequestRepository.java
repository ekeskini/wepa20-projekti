/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.Account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Elias Keski-Nisula
 */
public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequest, Long>{
    
}
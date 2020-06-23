/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.repositories;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.AccountConnectionManager;

/**
 *
 * @author Elias Keski-Nisula
 */
@Transactional
public interface AccountConnectionManagerRepository extends JpaRepository<AccountConnectionManager, Long>{
    
}

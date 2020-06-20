package wepa20.repositories;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.Account;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
    
    List<Account> findByUsernameContaining(String term);
}

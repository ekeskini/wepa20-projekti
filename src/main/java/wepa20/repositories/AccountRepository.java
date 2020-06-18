package wepa20.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
    Account findByUsername(String username);
}

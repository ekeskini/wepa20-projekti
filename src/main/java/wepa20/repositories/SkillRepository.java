package wepa20.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import wepa20.entities.Account;
import wepa20.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long>{
    Page<Skill> findAllByUser(Account user, Pageable p);
    
    List<Skill> findByUserOrderByRecommendationAmountDesc(Account user);
}

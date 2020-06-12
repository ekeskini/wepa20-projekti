package wepa20.Skill;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wepa20.Account.Account;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Skill extends AbstractPersistable<Long>{
    @NotEmpty
    private String description;
    
    @ManyToOne
    private Account user;
}

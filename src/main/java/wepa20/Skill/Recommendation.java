/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wepa20.Skill;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import wepa20.Account.Account;

/**
 *
 * @author Elias Keski-Nisula
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation extends AbstractPersistable<Long>{
    private String description;
    
    @ManyToOne
    private Skill recommendedskill;
    
    @ManyToOne
    private Account poster;
    
    
}

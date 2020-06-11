package projekti.Skill;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.Account.Account;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Skill {
	@NotEmpty
	private String description;
	
	private Map<Account, String> recommendations;
	
	@ManyToOne
	private Account user;
}

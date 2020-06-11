package projekti.Account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import projekti.Skill.Skill;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String username;
	
	@Lob
	private byte[] profilePic;
	@ManyToMany
	private List<Account> connections = new ArrayList<>();
	
	@OneToMany(mappedBy="user")
	private List<Skill> skills = new ArrayList<>();
}

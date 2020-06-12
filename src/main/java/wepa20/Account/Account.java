package wepa20.Account;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wepa20.Comment.Comment;
import wepa20.Post.Post;
import wepa20.Skill.Skill;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account extends AbstractPersistable<Long>{
	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	@NotEmpty
	private String username;
	@NotEmpty
        private String password;
        
        @OneToMany(mappedBy="poster")
        private List<Post> posts = new ArrayList<>();
        
        @OneToMany(mappedBy="poster")
        private List<Comment> comments = new ArrayList<>();
	@Lob
	private byte[] profilePic;
}

package projekti.message;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.Account.Account;
import projekti.Comment.Comment;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	@NotEmpty
	private String content;
	
	private List<Account> likes = new ArrayList<>();
	private List<Comment> comments = new ArrayList<>();
}

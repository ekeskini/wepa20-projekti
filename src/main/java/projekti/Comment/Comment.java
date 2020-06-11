package projekti.Comment;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projekti.message.Post;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
	@NotEmpty
	private String content;
	
	private Post parent;
}

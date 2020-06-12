package wepa20.Post;

import java.time.LocalDateTime;
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
import wepa20.Comment.Comment;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post extends AbstractPersistable<Long>{
        @ManyToOne
        private Account poster;
        
	private LocalDateTime timestamp;
	
	@NotEmpty
	private String content;
        
        @OneToMany(mappedBy="parent")
        private List<Comment> comments = new ArrayList<>();
}

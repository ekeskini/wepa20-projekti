package wepa20.Comment;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wepa20.Post.Post;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Comment extends AbstractPersistable<Long>{
	
	private LocalDateTime timestamp;
	
}

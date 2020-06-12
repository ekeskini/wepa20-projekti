package wepa20.Comment;


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
import wepa20.Post.Post;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Comment extends AbstractPersistable<Long>{	
	@ManyToOne
        private Account poster;
        
        private LocalDateTime timestamp;
	
        @NotEmpty
        private String content;
        
        @ManyToOne
        private Post parent;
        
        private Integer likes;
        
        @ManyToMany
        private List<Account> likers = new ArrayList<>();
        
}

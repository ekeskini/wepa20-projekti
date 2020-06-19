package wepa20.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Comment extends AbstractPersistable<Long>{	
	@ManyToOne
        private Account poster;
        
        private LocalDateTime timestamp;
	
        @NotEmpty
        @Column(columnDefinition="TEXT")
        private String content;
        
        @ManyToOne
        private Post parent;
        
        private Integer likes;
        
        @ManyToMany
        private List<Account> likers = new ArrayList<>();
        
}

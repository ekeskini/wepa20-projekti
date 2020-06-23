package wepa20.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
        @Column(unique=true)     
        @Size(min = 3, max = 15)
	private String username;
	
        @NotEmpty
        private String password;
        
        @OneToOne(mappedBy = "account")
        @Basic(fetch = FetchType.LAZY)
        private AccountConnectionManager connectionManager;
        
        @OneToMany(mappedBy="poster")
        private List<Post> posts = new ArrayList<>();
        
        @OneToMany(mappedBy="poster")
        private List<Comment> comments = new ArrayList<>();
        
        @OneToMany(mappedBy="user")
        @Basic(fetch = FetchType.LAZY)
        private List<Skill> skills = new ArrayList<>();
        
        @ManyToMany(mappedBy="likers")
        @Basic(fetch = FetchType.LAZY)
        private List<Post> likedposts = new ArrayList<>();
        
        @ManyToMany(mappedBy="likers")
        @Basic(fetch = FetchType.LAZY)
        private List<Comment> likedcomments = new ArrayList<>();
        
	@Lob
        @Basic(fetch = FetchType.LAZY)
        @Type(type = "org.hibernate.type.BinaryType")
	private byte[] profilePic;
}
